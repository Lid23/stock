package com.noodles.collectdata.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {
	
	public static void main(String args[]){
		/**Map type
		Map<String, String> map = new HashMap<String, String>();
		map.put("head_portrait", "0");
		map.put("crop_image", "0");
		map.put("image", "asdfasdfa");
		String json = JsonUtil.toJson(map);
		Map<String, String> jsonMap = JsonUtil.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
		*/

	}

	/** 
	 * 对象转换成json字符串 
	 * @param obj  
	 * @return  
	 */
	public static String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/** 
	 * json字符串转成对象 
	 * @param str   
	 * @param type 
	 * @return  
	 */
	public static <T> T fromJson(String str, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(str, type);
	}

	/** 
	 * json字符串转成对象 
	 * @param str   
	 * @param type  
	 * @return  
	 */
	public static <T> T fromJson(String str, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(str, type);
	}
}
