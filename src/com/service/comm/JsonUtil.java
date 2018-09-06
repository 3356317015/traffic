package com.service.comm;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


public class JsonUtil {

	@SuppressWarnings("rawtypes")
	public static Object convertObject(String strJson, Class beanClass){
		if (null != strJson && strJson.length()>0 && !"[null]".equals(strJson)){
			return JSONObject.toBean(JSONObject.fromObject(strJson),beanClass);
		}else{
			return null;
		}
	}
	
	public static Object convertArray(String strJson, Object object){
		if (null != strJson && strJson.length()>0 && !"[null]".equals(strJson)){
			JSONArray jsonArray = JSONArray.fromObject(strJson.toString()); 
			return JSONArray.toList(jsonArray, object, new JsonConfig());
		}else{
			return null;
		}
	}
	
}

