package defaultInterface;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class StringUtil {
	/**
	 * 产生随机数，并把该随机数返回出来
	 * 
	 * @return 随机数
	 */
	public static String getRadomString() {
		Random radom = new Random();
		return String.valueOf(radom.nextInt());
	}

	public static String getUUIDString() {
		return UUID.randomUUID().toString();
	}

	public static String upperCharacter(Integer order) {
		if (order == null)
			return "";
		char s = (char) (order.intValue() + 64);
		return String.valueOf(s);
	}

	public static void main(String[] a) {
		Integer order = new Integer(2);
		char s = (char) (order.intValue() + 64);
		System.out.println(String.valueOf(s));
	}

	/**
	 * 杀空函数，将"null"和null对象转换为""
	 * 
	 * @param str
	 *            String 待处理的字符串
	 * @return String 处理后的字符串
	 */
	public static String killNull(String str) {
		String returnStr = null;
		if (str == null || "null".equals(str.toLowerCase())) {
			returnStr = "";
		} else {
			returnStr = str;
		}
		return returnStr;
	}

	/**
	 * 将String类型变量转换为ASCII码 1、如果ASCII为73<i>、79[O]，跳过
	 * 2、只转换'0-9'和'A-Z'(除'I'、'O')的ASCII码，对应的ASCII码为'48-57'和'65-90'(除'73'、'79')
	 * 
	 * @param transParam
	 *            待转换的变
	 * @return 转换后的ASCII码
	 */
	public static String stringToASCII(String transParam) {
		// 不满足要求，直接返回null
		if (transParam == null || transParam.length() == 0) {
			return null;
		}
		char[] transChars = transParam.toCharArray();
		String ascii = "";
		// 字符转换为数字，并拼接为ASCII码
		int charASCII = -1;
		for (int i = 0; i < transChars.length; i++) {
			charASCII = (int) transChars[i];
			// 如果为73(I)、79(O)，自增
			if (charASCII == 73 || charASCII == 79) {
				charASCII++;
			}
			ascii += charASCII;
		}
		return ascii;
	}

	/**
	 * 判断某字符串是否为空，为空的标准是 str==null 或 str.length()==0
	 * 
	 * @param str
	 *            String 待处理的字符串
	 * @return boolean 字符串为空返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.length() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断某字符串是否非空，等于 !isEmpty(String str)
	 * 
	 * @param str
	 *            String 待处理的字符串
	 * @return boolean 字符串为非空返回true，否则返回false
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断某字符串是否为空或长度为0或由空白符构成
	 * 
	 * @param str
	 *            String 待处理的字符串
	 * @return boolean 字符串为为空或长度为0或由空白符构成返回true，否则返回false
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断某字符串是否不为空且长度不为0且不由空白符构成，等于 !isBlank(String str)
	 * 
	 * @param str
	 *            String 待处理的字符串
	 * @return boolean 字符串不为空且长度不为0且不由空白符构成返回true，否则返回false
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isBlank(str);
	}

	/**
	 * 去除字符串两边的空格并处理空字符串
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, 并以大小写敏感方式进行查找
	 * 
	 * @param source
	 *            String
	 * @param oldStr
	 *            String
	 * @param newStr
	 *            String
	 * @return String
	 */
	public static String replace(String source, String oldStr, String newStr) {
		return replace(source, oldStr, newStr, true);
	}

	/*
	 * /** 将字符串 source 中的 oldStr 替换为 newStr, matchCase 为是否设置大小写敏感查找
	 * 
	 * @param source String
	 * 
	 * @param oldStr String
	 * 
	 * @param newStr String
	 * 
	 * @param matchCase boolean
	 * 
	 * @return String
	 */
	public static String replace(String source, String oldStr, String newStr,
			boolean matchCase) {
		if (isEmpty(source)) {
			return null;
		}
		// 首先检查旧字符串是否存在, 不存在就不进行替换
		if (source.toLowerCase().indexOf(oldStr.toLowerCase()) == -1) {
			return source;
		}
		int findStartPos = 0;
		int a = 0;
		while (a > -1) {
			int b = 0;
			String str1, str2, str3, str4, strA, strB;
			str1 = source;
			str2 = str1.toLowerCase();
			str3 = oldStr;
			str4 = str3.toLowerCase();
			if (matchCase) {
				strA = str1;
				strB = str3;
			} else {
				strA = str2;
				strB = str4;
			}
			a = strA.indexOf(strB, findStartPos);
			if (a > -1) {
				b = oldStr.length();
				findStartPos = a + b;
				StringBuffer bbuf = new StringBuffer(source);
				source = bbuf.replace(a, a + b, newStr) + "";
				// 新的查找开始点位于替换后的字符串的结尾
				findStartPos = findStartPos + newStr.length() - b;
			}
		}
		return source;
	}

	/**
	 * 从字符串中查找字符串。 规则如下示例。
	 * splitWith("$2002$$2003$*5465464**2000*$2004$@23443@$23432$","$","@*#")
	 * 返回：$2002$$2003$$2004$
	 * splitWith("$2002$$2003$*5465464**2000*$2004$$20@0@4$","*","@#$")
	 * 返回：*5465464**2000*
	 * 
	 * @param src
	 *            String 源字符串
	 * @param regex
	 *            String 拆分字符串
	 * @param other
	 *            String 拆分字符串
	 * @return String static
	 * 
	 */
	public static String splitWith(String src, String regex, String other) {
		String retString = "";
		for (int i = 0; i < src.length() - 1; i++) {
			String traStr = src.substring(i, i + 1);
			int countRegex = 0;
			if (traStr.equals(regex) || countRegex % 2 != 0) {
				if (traStr.equals(regex))
					countRegex++;
				int k = src.indexOf(regex, i + 1);
				if (k != -1) {
					String dis = src.substring(i, k + 1);
					if (checkOther(dis, other))
						retString += dis;
					else {
						src = src.substring(k, src.length());
						retString += splitWith(src, regex, other);
						return retString;
					}
					i = k;
				}
			}
		}
		return retString;
	}

	/**
	 * 根据传入的数组转换成以指定格式分隔的字符串，splitWord是null或' '以逗号分隔
	 * 
	 * @param arrays
	 * @return String 例如"1,2,3,4,5,6"
	 */

	public static String arrayToString(String[] arrays, String splitWord) {
		String resultString = "";
		if (arrays != null && arrays.length != 0) {
			StringBuffer tmpstring = new StringBuffer();
			boolean flag = false;
			for (int i = 0; i < arrays.length; i++) {
				if (flag) {
					if (StringUtil.isBlank(splitWord)) {
						tmpstring.append(",");
					} else {
						tmpstring.append(splitWord);
					}
				}
				tmpstring.append(arrays[i].trim());
				flag = true;
			}
			resultString = tmpstring.toString();
		}
		return resultString;
	}

	/**
	 * 根据传入的list转换成以指定格式分隔的字符串，splitWord是null或' '以逗号分隔
	 * 
	 * @param stringlist
	 * @return String 例如"1,2,3,4,5,6"
	 */

	public static String listToString(List stringlist, String splitWord) {
		String resultString = "";
		if (stringlist != null && stringlist.size() != 0) {
			StringBuffer tmpstring = new StringBuffer();
			boolean flag = false;
			for (int i = 0; i < stringlist.size(); i++) {
				if (flag) {
					if (StringUtil.isBlank(splitWord)) {
						tmpstring.append(",");
					} else {
						tmpstring.append(splitWord);
					}
				}
				tmpstring.append(stringlist.get(i).toString().trim());
				flag = true;
			}
			resultString = tmpstring.toString();
		}
		return resultString;
	}

	/**
	 * 根据以指定分隔符分隔的字符串参数转换为去除分隔符的数组
	 * 
	 * @param string
	 *            例如"1,2,3,4,5,6"
	 * @return String[]
	 */

	public static String[] stringToArray(String string, String splitWord) {
		String[] tmpArray = null;
		if (StringUtil.isNotBlank(string)) {
			if (StringUtil.isNotBlank(splitWord)) {
				tmpArray = string.split(splitWord);
			} else {
				tmpArray = new String[1];
				tmpArray[0] = new String(string);
			}
		}
		return tmpArray;
	}

	/**
	 * 根据以指定分隔符分隔的字符串参数转换为去除分隔符的List
	 * 
	 * @param string
	 *            例如"1,2,3,4,5,6"
	 * @return List
	 */

	public static List<String> stringToList(String string, String splitWord) {
		List<String> tmpList = null;
		if (StringUtil.isNotBlank(string)) {
			if (StringUtil.isNotBlank(splitWord)) {
				tmpList = Arrays.asList(string.split(splitWord));
			} else {
				String[] tmpArray = { string };
				tmpList = Arrays.asList(tmpArray);
			}
		}
		return tmpList;
	}

	/**
	 * 在字符串中查找是否存在指定字符串
	 * 
	 * @return boolean
	 */
	private static boolean checkOther(String dist, String other) {
		for (int i = 0; i < other.length(); i++) {
			if (dist.indexOf(other.substring(i, i + 1)) != -1)
				return false;
		}
		return true;
	}

	/**
	 * 返回指定字节长度的字符串
	 * 
	 * @param coding
	 *            String 编码格式
	 * @param str
	 *            String 字符串
	 * @param length
	 *            int 指定长度
	 * @return String 返回的字符串
	 * 
	 */
	public static String toLength(String coding, String str, int length) {
		if (str == null) {
			return null;
		}
		if (length <= 0) {
			return "";
		}
		try {
			if (str.getBytes(coding).length <= length) {
				return str;
			}
		} catch (Exception ex) {
		}
		StringBuffer buff = new StringBuffer();

		int index = 0;
		char c;
		length -= 3;
		while (length > 0) {
			c = str.charAt(index);
			if (c < 128) {
				length--;
			} else {
				length--;
				length--;
			}
			buff.append(c);
			index++;
		}
		buff.append("...");
		return buff.toString();
	}

	/**
	 * 对中文字符串数组排序
	 * 
	 * @param arr
	 *            String[]
	 */
	public static void sortch(String arr[]) {
		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
		Arrays.sort(arr, cmp);
	}

	/**
	 * 滤除内容中的危险 HTML 代码, 主要是脚本代码, 滚动字幕代码以及脚本事件处理代码
	 * 
	 * @param content
	 *            String
	 * @return String
	 */
	public static String replaceHtmlCode(String content) {
		if (isEmpty(content))
			return "";
		// 需要滤除的脚本事件关键字
		String[] eventKeywords = { "onmouseover", "onmouseout", "onmousedown",
				"onmouseup", "onmousemove", "onclick", "ondblclick",
				"onkeypress", "onkeydown", "onkeyup", "ondragstart",
				"onerrorupdate", "onhelp", "onreadystatechange", "onrowenter",
				"onrowexit", "onselectstart", "onload", "onunload",
				"onbeforeunload", "onblur", "onerror", "onfocus", "onresize",
				"onscroll", "oncontextmenu" };
		content = replace(content, "<script", "&ltscript", false);
		content = replace(content, "</script", "&lt/script", false);
		content = replace(content, "<marquee", "&ltmarquee", false);
		content = replace(content, "</marquee", "&lt/marquee", false);
		content = replace(content, "\r\n", "<BR>");
		// 滤除脚本事件代码
		for (int i = 0; i < eventKeywords.length; i++) {
			content = replace(content, eventKeywords[i],
					"_" + eventKeywords[i], false); // 添加一个"_", 使事件代码无效
		}
		return content;
	}

	/**
	 * * 将字符串中'<','>','\','\r','\n','"',' '符号转换成html中的转义字符
	 * 
	 * @param str
	 *            String
	 * 
	 * @return String
	 */
	public static String escapeHTMLTags(String str) {
		StringBuffer stringBuffer = new StringBuffer(str.length() + 6);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
			case 60: // '<'
				stringBuffer.append("&lt;");
				break;

			case 62: // '>'
				stringBuffer.append("&gt;");
				break;

			case 13: // '\r'
				stringBuffer.append("<br>");
				break;
			case 10: // '\n'
				stringBuffer.append("<br>");
				break;

			case 39: // '\''
				stringBuffer.append("&acute");
				break;

			case 34: // '"'
				stringBuffer.append("&quot");
				break;
			case 32: // ' '
				stringBuffer.append("&nbsp;");
				break;

			default:
				stringBuffer.append(c);
				break;
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * 将字符串中'<','>','\','"',' '符号转换成html中的转义字符
	 * 
	 * @param str
	 *            String
	 * 
	 * @return String
	 */
	public static String escapeHTMLTagsGW(String str) {
		StringBuffer stringBuffer = new StringBuffer(str.length() + 6);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
			case 60: // '<'
				stringBuffer.append("&lt;");
				break;

			case 62: // '>'
				stringBuffer.append("&gt;");
				break;

			case 39: // '\''
				stringBuffer.append("&acute");
				break;

			case 34: // '"'
				stringBuffer.append("&quot");
				break;
			case 32: // ' '
				stringBuffer.append("&nbsp;");
				break;

			default:
				stringBuffer.append(c);
				break;
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * 将字符串中的'\n',' ' 转换成html中的转义字符
	 * 
	 * @param str
	 *            String
	 * 
	 * @return String
	 */
	public static String escapeHTMLTagsSimple(String str) {
		if (StringUtil.isNotBlank(str)) {
			StringBuffer stringBuffer = new StringBuffer(str.length() + 6);
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				switch (c) {
				case 10: // '\n'
					stringBuffer.append("<br>");
					break;
				case 32: // ' '
					stringBuffer.append("&nbsp;");
					break;
				default:
					stringBuffer.append(c);
					break;
				}
			}
			return stringBuffer.toString();
		} else {
			return "";
		}

	}

	/**
	 * 将字符串中的'"' 前面加上转义符'\'
	 * 
	 * @param str
	 *            String
	 * 
	 * @return String
	 */
	public static String escapeHTMLQuot(String str) {
		StringBuffer stringBuffer = new StringBuffer(str.length() + 6);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == 34)
				stringBuffer.append("\\");
			stringBuffer.append(c);
		}
		return stringBuffer.toString();
	}

	public static String utf2gbk(String str) {
		if (str != null && !"".equals(str.trim())) {
			try {
				return URLDecoder.decode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String gbk2utf(String str) {
		if (str != null && !"".equals(str.trim())) {
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换编码 ISO-8859-1到GB2312
	 * 
	 * @param text
	 *            String
	 */
	public static String ISO2GB(String text) {
		String result = "";
		try {
			result = new String(text.getBytes("ISO-8859-1"), "GB2312");
		} catch (UnsupportedEncodingException ex) {
			result = ex.toString();
		}
		return result;

	}

	/**
	 * 转换编码 GB2312到ISO-8859-1
	 * 
	 * @param text
	 *            String
	 */
	public static String GB2ISO(String text) {
		String result = "";
		try {
			result = new String(text.getBytes("GB2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return result;

	}

	/**
	 * 字符串编码不同编码之间的转换,将字符串从编码coding0转换为coding1
	 * 
	 * @param text
	 *            String
	 * @param coding0
	 *            字符编码0
	 * @param coding1
	 *            字符编码1
	 */
	public static String Transcoding(String text, String coding0, String coding1) {
		String result = "";
		try {
			result = new String(text.getBytes(coding0), coding1);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return result;

	}

	/**
	 * 将URL进行UTF8编码
	 * 
	 * @param text
	 *            String
	 * @return String
	 */
	public static String Utf8URLencode(String text) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				result.append(c);
			} else {
				byte[] b = new byte[0];
				try {
					b = Character.toString(c).getBytes("UTF-8");
				} catch (Exception ex) {
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					result.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return result.toString();
	}

	/**
	 * 将URL进行UTF8解码
	 * 
	 * @param text
	 *            String
	 * @return String
	 */

	public static String Utf8URLdecode(String text) {
		String result = "";
		int p = 0;
		if (text != null && text.length() > 0) {
			text = text.toLowerCase();
			p = text.indexOf("%e");
			if (p == -1)
				return text;
			while (p != -1) {
				result += text.substring(0, p);
				text = text.substring(p, text.length());
				if (text == "" || text.length() < 9)
					return result;
				result += CodeToWord(text.substring(0, 9));
				text = text.substring(9, text.length());
				p = text.indexOf("%e");
			}
		}
		return result + text;
	}

	/**
	 * 将URLUTF8编码转换成字符
	 * 
	 * @param text
	 *            String
	 * @return String
	 */
	public static String CodeToWord(String text) {
		String result;
		if (Utf8codeCheck(text)) {
			byte[] code = new byte[3];
			code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
			code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
			code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
			try {
				result = new String(code, "UTF-8");
			} catch (UnsupportedEncodingException ex) {
				result = null;
			}
		} else {
			result = text;
		}
		return result;
	}

	/**
	 * 检查UTF8编码是否有效
	 * 
	 * @param text
	 *            String
	 * @return boolean 如果编码有效返回true，否则返回false
	 */
	public static boolean Utf8codeCheck(String text) {
		String sign = "";
		if (text.startsWith("%e"))
			for (int i = 0, p = 0; p != -1; i++) {
				p = text.indexOf("%", p);
				if (p != -1)
					p++;
				sign += p;
			}
		return sign.equals("147-1");
	}

	/**
	 * 检查字符串是否是UTF8编码
	 * 
	 * @param text
	 *            String
	 * @return boolean 如果是UTF8编码返回true，否则返回false
	 */

	public static boolean isUtf8Url(String text) {
		text = text.toLowerCase();
		int p = text.indexOf("%");
		if (p != -1 && text.length() - p > 9) {
			text = text.substring(p, p + 9);
		}
		return Utf8codeCheck(text);
	}

	/**
	 * 把Str加密返回加密码，同时把“%”转换为$
	 * 
	 * @param str
	 *            String 源字符串
	 * @param coding
	 *            String 编码方式:"UTF-8"
	 * @return 被转换后的字符串
	 */
	public static String URLEncoder(String str, String coding)
			throws UnsupportedEncodingException {
		String ENStr = "";
		if (str != null) {
			ENStr = java.net.URLEncoder.encode(str, coding);
			ENStr = ENStr.replace('%', '$');
		}
		return ENStr;
	}

	/**
	 * 把“$”转换为“%”，同时把Str解密返回汉字
	 * 
	 * @param ENStr
	 *            String 源字符串
	 * @param coding
	 *            String 编码方式:"UTF-8"
	 * @return 被转换后的字符串
	 */

	public static String URLDecoder(String ENStr, String coding)
			throws UnsupportedEncodingException {
		String Str = "";
		if (ENStr != null) {
			ENStr = ENStr.replace('$', '%');
			java.net.URLDecoder urlDecoder = new java.net.URLDecoder();
			Str = urlDecoder.decode(ENStr, coding);
		}
		return Str;
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            String 字节数组
	 * @return 16进制字串
	 */

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 16进制数组字符
	 * 
	 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 将一个字符串的首字母改为大写或者小写
	 * 
	 * @param srcString
	 *            String
	 * @param flag
	 *            boolean true：大写转化为小写；false：小写转化为大写
	 * @return String
	 * 
	 */
	public static String toLowerCaseInitial(String srcString, boolean flag) {
		StringBuffer sb = new StringBuffer();
		if (flag) {
			sb.append(Character.toLowerCase(srcString.charAt(0)));
		} else {
			sb.append(Character.toUpperCase(srcString.charAt(0)));
		}
		sb.append(srcString.substring(1));
		return sb.toString();

	}

	/**
	 * 字符串翻转
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * 判断字符串是否全是数字字符
	 * 
	 * @param str
	 *            String
	 * @return boolean 如果全是数字字符返回true，否则返回false
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为整数
	 * 
	 * @param str
	 *            String
	 * @return boolean 如果是整数返回true，否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[1-9][\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @return boolean 如果是浮点数返回true，否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为数值型字符串
	 * 
	 * @return boolean 如果是返回true，否则返回false
	 */
	public static boolean isNumber(String str) {
		return isDouble(str) || isNumeric(str);
	}

	/**
	 * 判断输入的字符串是否符合Email样式.
	 * 
	 * @return boolean 如果是符合email格式返回true，否则返回false
	 */
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断输入的字符串是否为纯汉字
	 * 
	 * @return boolean 如果字符串为纯汉字返回true，否则返回false
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判 断是不是合法手机
	 * 
	 * @return boolean 如果是符合手机格式返回true，否则返回false
	 */
	public static boolean isHandPhone(String handphone) {
		try {
			if (!handphone.substring(0, 1).equals("1")) {
				return false;
			}
			if (handphone == null || handphone.length() != 11) {
				return false;
			}
			String check = "^[1]\\d{10}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(handphone);
			boolean isMatched = matcher.matches();
			if (isMatched) {
				return true;
			} else {
				return false;
			}
		} catch (RuntimeException e) {
			return false;
		}
	}

	/**
	 * 这个方法是将字符串中的\n回车换成<br>
	 * 换行
	 */
	public static String AddBr(String Content) {
		if (Content == null) {
			return "";
		}
		String makeContent = new String();
		StringTokenizer strToken = new StringTokenizer(Content, "\n");
		while (strToken.hasMoreTokens()) {
			makeContent = makeContent + "<br>" + strToken.nextToken();
		}
		return makeContent;
	}

	public static String convertStr(String strIds, String fields) {
		StringBuffer where = new StringBuffer();
		String[] tmp = strIds.split(",");
		int max = 500;
		// 为处理SQL语句中in字段值太多，所做的截断拼接处理
		if (tmp.length > max) {
			int t = tmp.length % max == 0 ? tmp.length / max : tmp.length / max
					+ 1;
			for (int i = 0; i < t; i++) {
				if (i == 0) {
					where.append(fields + " in (-1");
					for (int j = 0; j < max; j++) {
						if ((i * max + j) < tmp.length) {
							where.append(",").append(tmp[i * max + j]);
						}
					}
					where.append(")");
				} else {
					where.append(" or " + fields + " in (-1");
					for (int j = 0; j < max; j++) {
						if ((i * max + j) < tmp.length) {
							where.append(",").append(tmp[i * max + j]);
						}
					}
					where.append(")");
				}
			}
		} else {
			where.append(fields + " in (");
			where.append(strIds);
			where.append(")");
		}
		return where.toString();
	}

	/**
	 * 判断对象是否为空，为空的标准是 str==null 或 str.length()==0
	 * 
	 * @param o
	 *            Object 待处理的对象
	 * @return boolean 对象为空返回true，否则返回false
	 */

	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}

		String s = null;
		if (!(o instanceof String)) {
			s = o.toString();
		} else {
			s = (String) o;
		}

		if (s == null || s.trim().length() == 0 || "null".equals(s)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断对象是否非空
	 * 
	 * @param o
	 *            Object 待处理的对象
	 * @return boolean 对象为非空返回true，否则返回false
	 */

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	/**
	 * 返回数组元素中某个值的下标
	 * 
	 * @param array
	 *            String[] 数组
	 * @param value
	 *            String 某个值
	 * @return int 对象为空或不存在返回0，否则返回下标值
	 */
	public static int getArrayIndex(String[] array, String value) {
		if (array != null) {
			if (array.length > 0) {
				for (int i = 0; i < array.length; i++) {
					if (array[i].equals(value)) {
						return i;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 从字符串中查找字符串。 规则如下示例。 $2002$$2003$","$",",") 返回：2002$,2003
	 * *2002**2003**5465464**2000**2004**20*","*",",")
	 * 返回：2002,2003,5465464,2000,2004,20
	 * 
	 * @param src
	 *            String 源字符串
	 * @param regex
	 *            String 拆分字符串
	 * @param other
	 *            String 拆分字符串
	 * @return String static
	 * 
	 */
	public static String replaceWith(String source, String oldStr, String newStr) {
		source = StringUtil.replace(source, oldStr + oldStr, newStr);
		source = StringUtil.replace(source, oldStr, "");
		source = StringUtil.trim(source);
		return source;
	}

	/**
	 * 根据传入的集合转换成以指定格式分隔的字符串，splitWord是null或' '以逗号分隔
	 * 
	 * @param stringlist
	 * @return String 例如"1,2,3,4,5,6"
	 */

	public static String setToString(Set<String> collection, String splitWord) {
		List<String> stringlist = null;
		if (collection != null) {
			stringlist = new ArrayList<String>(collection);
		}
		return listToString(stringlist, splitWord);
	}

	/**
	 * 获得请求的真实IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int i = 0; i < ips.length; i++) {
				if (!ips[i].equals("")
						&& !ips[i].toLowerCase().equals("unknown")) {
					ip = ips[i];
				}
			}
		}
		return ip;
	}

	// 将POJO转换成JSON
	public static String bean2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	// 处理特殊字符
	public static String replaceUnlawfulCharTwo(String xml) {
		if (isBlank(xml))
			return xml;
		String tmpXML = xml.replace("\'", "&apos;");
		tmpXML = tmpXML.replace("\"", "&quot;");
		tmpXML = tmpXML.replace("&", "&amp;");
		tmpXML = tmpXML.replace("<", "&lt;");
		tmpXML = tmpXML.replace(">", "&gt;");
		tmpXML = tmpXML.replace("'", "&apos;");
		return tmpXML;
	}

	public static String replaceUnlawfulChar(String xml) {
		if (isBlank(xml))
			return xml;
		String tmpXML = xml.replace("\'", "&apos;");
		tmpXML = tmpXML.replace("\"", "&quot;");
		tmpXML = tmpXML.replace("&", "&amp;");
		return tmpXML;
	}
	public static String objToStr(Object o){
		return o ==null ? "":o.toString();
	}
}
