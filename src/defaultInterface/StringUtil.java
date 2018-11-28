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
	 * ��������������Ѹ���������س���
	 * 
	 * @return �����
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
	 * ɱ�պ�������"null"��null����ת��Ϊ""
	 * 
	 * @param str
	 *            String ��������ַ���
	 * @return String �������ַ���
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
	 * ��String���ͱ���ת��ΪASCII�� 1�����ASCIIΪ73<i>��79[O]������
	 * 2��ֻת��'0-9'��'A-Z'(��'I'��'O')��ASCII�룬��Ӧ��ASCII��Ϊ'48-57'��'65-90'(��'73'��'79')
	 * 
	 * @param transParam
	 *            ��ת���ı�
	 * @return ת�����ASCII��
	 */
	public static String stringToASCII(String transParam) {
		// ������Ҫ��ֱ�ӷ���null
		if (transParam == null || transParam.length() == 0) {
			return null;
		}
		char[] transChars = transParam.toCharArray();
		String ascii = "";
		// �ַ�ת��Ϊ���֣���ƴ��ΪASCII��
		int charASCII = -1;
		for (int i = 0; i < transChars.length; i++) {
			charASCII = (int) transChars[i];
			// ���Ϊ73(I)��79(O)������
			if (charASCII == 73 || charASCII == 79) {
				charASCII++;
			}
			ascii += charASCII;
		}
		return ascii;
	}

	/**
	 * �ж�ĳ�ַ����Ƿ�Ϊ�գ�Ϊ�յı�׼�� str==null �� str.length()==0
	 * 
	 * @param str
	 *            String ��������ַ���
	 * @return boolean �ַ���Ϊ�շ���true�����򷵻�false
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.length() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ж�ĳ�ַ����Ƿ�ǿգ����� !isEmpty(String str)
	 * 
	 * @param str
	 *            String ��������ַ���
	 * @return boolean �ַ���Ϊ�ǿշ���true�����򷵻�false
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * �ж�ĳ�ַ����Ƿ�Ϊ�ջ򳤶�Ϊ0���ɿհ׷�����
	 * 
	 * @param str
	 *            String ��������ַ���
	 * @return boolean �ַ���ΪΪ�ջ򳤶�Ϊ0���ɿհ׷����ɷ���true�����򷵻�false
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
	 * �ж�ĳ�ַ����Ƿ�Ϊ���ҳ��Ȳ�Ϊ0�Ҳ��ɿհ׷����ɣ����� !isBlank(String str)
	 * 
	 * @param str
	 *            String ��������ַ���
	 * @return boolean �ַ�����Ϊ���ҳ��Ȳ�Ϊ0�Ҳ��ɿհ׷����ɷ���true�����򷵻�false
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isBlank(str);
	}

	/**
	 * ȥ���ַ������ߵĿո񲢴�����ַ���
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * ���ַ��� source �е� oldStr �滻Ϊ newStr, ���Դ�Сд���з�ʽ���в���
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
	 * /** ���ַ��� source �е� oldStr �滻Ϊ newStr, matchCase Ϊ�Ƿ����ô�Сд���в���
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
		// ���ȼ����ַ����Ƿ����, �����ھͲ������滻
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
				// �µĲ��ҿ�ʼ��λ���滻����ַ����Ľ�β
				findStartPos = findStartPos + newStr.length() - b;
			}
		}
		return source;
	}

	/**
	 * ���ַ����в����ַ����� ��������ʾ����
	 * splitWith("$2002$$2003$*5465464**2000*$2004$@23443@$23432$","$","@*#")
	 * ���أ�$2002$$2003$$2004$
	 * splitWith("$2002$$2003$*5465464**2000*$2004$$20@0@4$","*","@#$")
	 * ���أ�*5465464**2000*
	 * 
	 * @param src
	 *            String Դ�ַ���
	 * @param regex
	 *            String ����ַ���
	 * @param other
	 *            String ����ַ���
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
	 * ���ݴ��������ת������ָ����ʽ�ָ����ַ�����splitWord��null��' '�Զ��ŷָ�
	 * 
	 * @param arrays
	 * @return String ����"1,2,3,4,5,6"
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
	 * ���ݴ����listת������ָ����ʽ�ָ����ַ�����splitWord��null��' '�Զ��ŷָ�
	 * 
	 * @param stringlist
	 * @return String ����"1,2,3,4,5,6"
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
	 * ������ָ���ָ����ָ����ַ�������ת��Ϊȥ���ָ���������
	 * 
	 * @param string
	 *            ����"1,2,3,4,5,6"
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
	 * ������ָ���ָ����ָ����ַ�������ת��Ϊȥ���ָ�����List
	 * 
	 * @param string
	 *            ����"1,2,3,4,5,6"
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
	 * ���ַ����в����Ƿ����ָ���ַ���
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
	 * ����ָ���ֽڳ��ȵ��ַ���
	 * 
	 * @param coding
	 *            String �����ʽ
	 * @param str
	 *            String �ַ���
	 * @param length
	 *            int ָ������
	 * @return String ���ص��ַ���
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
	 * �������ַ�����������
	 * 
	 * @param arr
	 *            String[]
	 */
	public static void sortch(String arr[]) {
		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
		Arrays.sort(arr, cmp);
	}

	/**
	 * �˳������е�Σ�� HTML ����, ��Ҫ�ǽű�����, ������Ļ�����Լ��ű��¼��������
	 * 
	 * @param content
	 *            String
	 * @return String
	 */
	public static String replaceHtmlCode(String content) {
		if (isEmpty(content))
			return "";
		// ��Ҫ�˳��Ľű��¼��ؼ���
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
		// �˳��ű��¼�����
		for (int i = 0; i < eventKeywords.length; i++) {
			content = replace(content, eventKeywords[i],
					"_" + eventKeywords[i], false); // ���һ��"_", ʹ�¼�������Ч
		}
		return content;
	}

	/**
	 * * ���ַ�����'<','>','\','\r','\n','"',' '����ת����html�е�ת���ַ�
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
	 * ���ַ�����'<','>','\','"',' '����ת����html�е�ת���ַ�
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
	 * ���ַ����е�'\n',' ' ת����html�е�ת���ַ�
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
	 * ���ַ����е�'"' ǰ�����ת���'\'
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
	 * ת������ ISO-8859-1��GB2312
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
	 * ת������ GB2312��ISO-8859-1
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
	 * �ַ������벻ͬ����֮���ת��,���ַ����ӱ���coding0ת��Ϊcoding1
	 * 
	 * @param text
	 *            String
	 * @param coding0
	 *            �ַ�����0
	 * @param coding1
	 *            �ַ�����1
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
	 * ��URL����UTF8����
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
	 * ��URL����UTF8����
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
	 * ��URLUTF8����ת�����ַ�
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
	 * ���UTF8�����Ƿ���Ч
	 * 
	 * @param text
	 *            String
	 * @return boolean ���������Ч����true�����򷵻�false
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
	 * ����ַ����Ƿ���UTF8����
	 * 
	 * @param text
	 *            String
	 * @return boolean �����UTF8���뷵��true�����򷵻�false
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
	 * ��Str���ܷ��ؼ����룬ͬʱ�ѡ�%��ת��Ϊ$
	 * 
	 * @param str
	 *            String Դ�ַ���
	 * @param coding
	 *            String ���뷽ʽ:"UTF-8"
	 * @return ��ת������ַ���
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
	 * �ѡ�$��ת��Ϊ��%����ͬʱ��Str���ܷ��غ���
	 * 
	 * @param ENStr
	 *            String Դ�ַ���
	 * @param coding
	 *            String ���뷽ʽ:"UTF-8"
	 * @return ��ת������ַ���
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
	 * ת���ֽ�����Ϊ16�����ִ�
	 * 
	 * @param b
	 *            String �ֽ�����
	 * @return 16�����ִ�
	 */

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 16���������ַ�
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
	 * ��һ���ַ���������ĸ��Ϊ��д����Сд
	 * 
	 * @param srcString
	 *            String
	 * @param flag
	 *            boolean true����дת��ΪСд��false��Сдת��Ϊ��д
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
	 * �ַ�����ת
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
	 * �ж��ַ����Ƿ�ȫ�������ַ�
	 * 
	 * @param str
	 *            String
	 * @return boolean ���ȫ�������ַ�����true�����򷵻�false
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * �ж��Ƿ�Ϊ����
	 * 
	 * @param str
	 *            String
	 * @return boolean �������������true�����򷵻�false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[1-9][\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * �ж��Ƿ�Ϊ������������double��float
	 * 
	 * @return boolean ����Ǹ���������true�����򷵻�false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * �ж��Ƿ�Ϊ��ֵ���ַ���
	 * 
	 * @return boolean ����Ƿ���true�����򷵻�false
	 */
	public static boolean isNumber(String str) {
		return isDouble(str) || isNumeric(str);
	}

	/**
	 * �ж�������ַ����Ƿ����Email��ʽ.
	 * 
	 * @return boolean ����Ƿ���email��ʽ����true�����򷵻�false
	 */
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * �ж�������ַ����Ƿ�Ϊ������
	 * 
	 * @return boolean ����ַ���Ϊ�����ַ���true�����򷵻�false
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * �� ���ǲ��ǺϷ��ֻ�
	 * 
	 * @return boolean ����Ƿ����ֻ���ʽ����true�����򷵻�false
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
	 * ��������ǽ��ַ����е�\n�س�����<br>
	 * ����
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
		// Ϊ����SQL�����in�ֶ�ֵ̫�࣬�����Ľض�ƴ�Ӵ���
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
	 * �ж϶����Ƿ�Ϊ�գ�Ϊ�յı�׼�� str==null �� str.length()==0
	 * 
	 * @param o
	 *            Object ������Ķ���
	 * @return boolean ����Ϊ�շ���true�����򷵻�false
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
	 * �ж϶����Ƿ�ǿ�
	 * 
	 * @param o
	 *            Object ������Ķ���
	 * @return boolean ����Ϊ�ǿշ���true�����򷵻�false
	 */

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	/**
	 * ��������Ԫ����ĳ��ֵ���±�
	 * 
	 * @param array
	 *            String[] ����
	 * @param value
	 *            String ĳ��ֵ
	 * @return int ����Ϊ�ջ򲻴��ڷ���0�����򷵻��±�ֵ
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
	 * ���ַ����в����ַ����� ��������ʾ���� $2002$$2003$","$",",") ���أ�2002$,2003
	 * *2002**2003**5465464**2000**2004**20*","*",",")
	 * ���أ�2002,2003,5465464,2000,2004,20
	 * 
	 * @param src
	 *            String Դ�ַ���
	 * @param regex
	 *            String ����ַ���
	 * @param other
	 *            String ����ַ���
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
	 * ���ݴ���ļ���ת������ָ����ʽ�ָ����ַ�����splitWord��null��' '�Զ��ŷָ�
	 * 
	 * @param stringlist
	 * @return String ����"1,2,3,4,5,6"
	 */

	public static String setToString(Set<String> collection, String splitWord) {
		List<String> stringlist = null;
		if (collection != null) {
			stringlist = new ArrayList<String>(collection);
		}
		return listToString(stringlist, splitWord);
	}

	/**
	 * ����������ʵIP
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

	// ��POJOת����JSON
	public static String bean2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	// ���������ַ�
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
