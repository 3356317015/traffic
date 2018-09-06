package com.zhima.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class MapUtil {
	/**
	 * 将byte数组转换为Base64的字符串
	 * @param bytes byte数组
	 * @return
	 */
	public static String packageParameter(Map<String, Object> map){
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		if (null != map && map.size()>0){
			Iterator<Entry<String, Object>> it = map.entrySet().iterator();
			while (it.hasNext()) {
	        	 //HashMap
	        	 //String
	        	 //Integer
	        	 //ArrayList
	        	 Entry<String, Object> entry = it.next();
	        	 //System.out.println(entry.getKey());
	        	 //System.out.println(entry.getValue().getClass().getSimpleName());
	        	 if (null == entry.getValue() || "".equals(entry.getValue())){
	        		 mapParameter.put(entry.getKey(), "");
	        	 }else{
	        		 if ("String".equals(entry.getValue().getClass().getSimpleName())){
		        		 mapParameter.put(entry.getKey(), StringUtils.format(entry.getValue().toString()));
		        	 }else if("Integer".equals(entry.getValue().getClass().getSimpleName())){
		        		 mapParameter.put(entry.getKey(), StringUtils.format(entry.getValue().toString()));
		        	 }else if("Double".equals(entry.getValue().getClass().getSimpleName())){
		        		 mapParameter.put(entry.getKey(), StringUtils.format(entry.getValue().toString()));
		        	 }else if("HashMap".equals(entry.getValue().getClass().getSimpleName())){
		        		 String string = JSONObject.fromObject(entry.getValue()).toString();
		        		 mapParameter.put(entry.getKey(), StringUtils.format(string));
		        	 }else if("ArrayList".equals(entry.getValue().getClass().getSimpleName())){
		        		 JSONArray jsonarray = JSONArray.fromObject(entry.getValue());
		        		 mapParameter.put(entry.getKey(), StringUtils.format(jsonarray.toString()));
		        	 }else{
		        		 String strJson = JSONObject.fromObject(entry.getValue()).toString();
		        		 mapParameter.put(entry.getKey(), StringUtils.format(strJson));
		        	 }
	        	 }
			}
			return JSONObject.fromObject(mapParameter).toString();
		}
		return "";
	}
	
}

