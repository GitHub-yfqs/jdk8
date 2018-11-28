package defaultInterface;

import java.security.AccessController;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.security.action.GetPropertyAction;

public class Test {
	public static void main(String[] args) {
		char slash = AccessController.doPrivileged(
	            new GetPropertyAction("file.separator")).charAt(0);
		
		System.out.print(slash);
		
		//String  json = {"invoiceCenter":{"returnInfo":{"returnCode":"0000","returnMessage":"MDAwMA=="},"globalInfo":{"timestamp":"1542781153405","interfaceCode":"FPZX.CHFPSQ","username":"360100999999301","interfacePassWord":"0949566150k4WOn7zxAeWmBHYB1yVzmw=="},"data":{"content":"aWJzUlBtVW9zUGlxS0JHRE9yT2VqSHgxRml0Nk5oczFiZVEyZllnUktCZUVYbUorbW5wZFpaYy9k\r\nbkJreGduVE1keWF4UnoyNXBJNQ0KOWVCMCt1Y0hHU0ZnTDFZMHpVMGlmcmtvN3k3bEFQWVJOTEt1\r\nWTNYYVJtQll6bjhoSDlaUS9ydEZyMGhSNC9JbmxrQUI3Y01jSWc9PQ==\r\n","dataDescription":{"zipCode":"0","encryptCode":"1"}}}};
		Map<String, Object> content = new HashMap<>();
		content.put("ddh", "2018080621001004410200629748");
		content.put("fpqqlsh", "JX88323285231801");
		content.put("yfpdm", "036001611113");
		content.put("yfphm", "30092208");
		Map<String, Object> code = new HashMap<>();
		code.put("code", "0000");
		content.put("resultCode", code);
		JSONObject contentObj = JSONObject.fromObject(content);
		/*System.out.println(contentObj);
		Map<String, Object> invoiceCenter = getInvoiceCenter(content, "FPZX.CHFPSQ", "360100999999301",JSONObject.fromObject(code).toString(),"0000");
		JSONObject respObj = JSONObject.fromObject(invoiceCenter);
		String json = respObj.toString();
		System.out.println("======响应Json字符串======"+respObj.toString());
		
		Map map = getParams(json);*/
		System.out.println(getStrFromJson(JSONObject.fromObject(contentObj), "resultCode"));;
		System.out.println(getStrFromJson(JSONObject.fromObject(contentObj), "code"));;
	}
	
	/*
	 * 获取整个请求参数值
	 */
	public static Map getParams(String JsonStr){
		Map paramsMap = new HashMap(); 
		JSONObject jsonObject = JSONObject.fromObject(JsonStr);
		if(null == jsonObject || jsonObject.isEmpty()) return null;
		Iterator it=jsonObject.keys();
        while (it.hasNext()){
        	JSONObject invoiceCenterObj = JSONObject.fromObject(jsonObject.get(it.next()));
        	if(null == invoiceCenterObj || invoiceCenterObj.isEmpty()) return null;
        	it=invoiceCenterObj.keys();
            while (it.hasNext()){
            	JSONObject paramInfoObj = JSONObject.fromObject(invoiceCenterObj.get(it.next()));
            	if(null == paramInfoObj || paramInfoObj.isEmpty()) return null;
            	it=paramInfoObj.keys();
                while (it.hasNext()){
                	//it.next() 值是变化的 出现两次 第一次和第二次的值是不同的
                	/*System.out.println(it.next());
                	System.out.println(it.next());
                	System.out.println(paramInfoObj.get(it.next()));
                	System.out.println(it.next());*/
                	Object next = it.next();
                	paramsMap.put(next, "" == paramInfoObj.get(next)? "":paramInfoObj.get(next));
                }
             }
         }
        JSONObject dataDescObject = JSONObject.fromObject(paramsMap.get("dataDescription"));
        it=dataDescObject.keys();
        while (it.hasNext()){
        	paramsMap.put(it.next(), "" == dataDescObject.get(it.next())? "":dataDescObject.get(it.next()));
        }
        JSONObject contentObject = JSONObject.fromObject(paramsMap.get("content"));
        it=contentObject.keys();
        while (it.hasNext()){
        	paramsMap.put(it.next(), "" == contentObject.get(it.next())? "":contentObject.get(it.next()));
        }
        return paramsMap;
		
	}
	
	public static Map<String, Object> getInvoiceCenter(Map<String, Object> content,String interfaceCode,String nsrsbh,String returnCode,String returnMessage){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> invoiceCenter = new HashMap<String, Object>();
		Map<String, Object> globalInfo = new HashMap<String, Object>();
		Map<String, Object> returnInfo = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> dataDescription = new HashMap<String, Object>();
		String interfacePassWord = nsrsbh; 
		String contentString = null;
		try {
			if ("FPZX.CHFPSQ".equals(interfaceCode)) {
				JSONObject contentObj = JSONObject.fromObject(content);
				contentString = contentObj.toString();
			} else if ("FPZX.CXFPSFCH".equals(interfaceCode)) {
				List<Map<String, Object>> mList = (List<Map<String, Object>>)content.get("mList");
				JSONArray jsonArray = JSONArray.fromObject(mList);
				contentString = jsonArray.toString();
			}

			globalInfo.put("interfaceCode", interfaceCode);
			globalInfo.put("interfacePassWord",interfacePassWord);
			globalInfo.put("timestamp", String.valueOf(System.currentTimeMillis()));
			globalInfo.put("username", nsrsbh);
			returnInfo.put("returnCode", returnCode);
			returnInfo.put("returnMessage", Base64.encodeBase64String(returnMessage.getBytes("UTF-8")));

			String zipCode = "0";
			System.out.println("=====json明文===="+contentString);
			// base64(是否压缩(加密方式(明文)));
			//byte[] caByte = null;
			// 3DES加密
			//String key = interfacePassWord.substring(10, interfacePassWord.length());
			//caByte = DesUtil.encrypt(contentString, key).getBytes("UTF-8");
			// 压缩
			/*zipCode = (caByte.length / 8 / 1024) > 10 ? "1" : "0";
			byte[] zipBytes = null;
			if (zipCode.equals("1")) {
				zipBytes = ZipUtils.gzip(caByte.toString()).getBytes("UTF-8");
			} else {
				zipBytes = caByte;
			}*/
			// base64
			//contentString = BASE64.encryptBASE64(zipBytes);/*Base64.encodeBase64String(zipBytes);*/
			System.out.println("=====加密后json明文===="+contentString);
			
			/*try {
				// base64(是否压缩(加密方式(明文)));
				byte[] caByte = null;
				// 3DES加密
				caByte = DesUtil.encrypt(contentString, key).getBytes("UTF-8");
				// base64
				contentString =  BASE64.encryptBASE64(caByte);
				System.out.println("=====加密后json明文===="+contentString);
			} catch (Exception e) {
				System.out.println("加密出错");
			}*/

			dataDescription.put("encryptCode", "1");
			dataDescription.put("zipCode", zipCode);
			data.put("dataDescription", dataDescription);
			data.put("content", contentString);

			/*dataDescription.put("encryptCode", "0");
			dataDescription.put("zipCode", "0");
			data.put("dataDescription", dataDescription);*/

			//加密压缩  后期完善
			/*data.put("content", Base64.encodeBase64String(contentString.getBytes("UTF-8")));*/
		} catch (Exception e) {
			System.out.println("生成报文异常");
		}
		invoiceCenter.put("globalInfo", globalInfo);
		invoiceCenter.put("returnInfo", returnInfo);
		invoiceCenter.put("data", data);
		
		map.put("invoiceCenter", invoiceCenter);
		return map;
	}
	
	/**
	 * 从json中获取key对应的string值
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getStrFromJson(JSONObject json, String key) {
		return getStrFromJson(json, key, "");
	}
	public static String getStrFromJson(JSONObject json, String key, String defaultVal) {
		if(StringUtil.isBlank(key)) return "";
		if(null == json || json.isEmpty()) return "";
		Iterator iterator = json.keys();
		while (iterator.hasNext()) {
			Object netx = iterator.next();
			System.out.println(netx);
			try {
				JSONObject invoiceCenterObj = JSONObject.fromObject(json.get(netx));
				if (invoiceCenterObj!=null&&!invoiceCenterObj.isEmpty()) {
					Iterator iterator2 = invoiceCenterObj.keys();
					while (iterator2.hasNext()) {
						System.out.println(iterator2.next());
						return null;
						
					}
				}
			} catch (Exception e) {
				System.out.println(netx+"不是json对象");
			}
			
			
		}
		Object obj = json.get(key);
		return null == obj ? defaultVal : obj.toString();
	
}
}
