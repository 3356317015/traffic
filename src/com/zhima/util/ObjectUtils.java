package com.zhima.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class ObjectUtils {
	/**
	 * getPropertyValueFormObject方法描述：带入对象、对象中的属性名称，取得该属性的值
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:12:19
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj
	 * @param strProperty
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String getPropertyValueFormObject(Object obj,
			String strProperty) throws Exception {
		LinkedHashMap newMap = ObjectUtils.objectToMap(obj);
		if (newMap == null) {
			return "";
		} else {
			Object objReturn = newMap.get(strProperty);
			if (objReturn == null) {
				return "";
			} else {
				return objReturn.toString();
			}
		}
	}
	@SuppressWarnings("rawtypes")
	public static Iterator getObjectElements(Object obj) throws Exception {
		HashMap map = ObjectUtils.objectToMap(obj);
		Set entrys = map.entrySet();
		return entrys.iterator();
	}
	/**
	 * 将指定对象属性名称和属性值转化为Map键值对
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static LinkedHashMap objectToMap(Object obj) throws Exception {
		if (obj == null) {
			throw new Exception("对象为空");
		}
		Class clazz = obj.getClass();
		LinkedHashMap map = new LinkedHashMap();
		getClass(clazz, map, obj);
		LinkedHashMap newMap = convertHashMap(map);
		return newMap;
	}
	/**
	 * getClass方法描述：将对象中类属性加入到HashMap中 
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:14:10
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param clazz
	 * @param map
	 * @param obj
	 * @throws Exception void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void getClass(Class clazz, LinkedHashMap map, Object obj)
			throws Exception {
		if (clazz.getSimpleName().equals("Object")) {
			return;
		}

		Field[] fields = clazz.getDeclaredFields();
		if (null == fields || fields.length <= 0) {
			throw new Exception("当前对象中没有任何属性值");
		}
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String name = fields[i].getName();
			Object value = fields[i].get(obj);
			map.put(name, value);
		}
		Class superClzz = clazz.getSuperclass();
		getClass(superClzz, map, obj);
	}
	
	/**
	 * convertHashMap方法描述：转换HashMap
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:14:46
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param map
	 * @return
	 * @throws Exception LinkedHashMap
	 */
	@SuppressWarnings("rawtypes")
	private static LinkedHashMap convertHashMap(LinkedHashMap map) throws Exception {

		LinkedHashMap newMap = new LinkedHashMap();
		Set keys = map.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			Object key = it.next();
			convertToString(map.get(key), newMap, key);
		}

		return newMap;
	}
	
	/**
	 * convertToString方法描述：将值存入HashMap中
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:14:55
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param value
	 * @param newMap
	 * @param key void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void convertToString(Object value, HashMap newMap, Object key) {
		if (value != null) {
			Class clazz = value.getClass();
			if (isBaseType(clazz)) {
				newMap.put(key, value.toString());
			} else if (clazz == String.class) {
				newMap.put(key, value.toString());
			} else if (clazz == Date.class) {
				Date date = (Date) value;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				newMap.put(key, sdf.format(date));
			} else if (clazz == Timestamp.class) {
				Timestamp timestamp = (Timestamp) value;
				long times = timestamp.getTime();
				Date date = new Date(times);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				newMap.put(key, sdf.format(date));
			} else if (clazz == java.sql.Date.class) {
				java.sql.Date sqlDate = (java.sql.Date) value;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				newMap.put(key, sdf.format(sqlDate));
			} else {
				newMap.put(key, value);
			}
		} else {
			newMap.put(key, value);
		}
	}
	
	/**
	 * isBaseType方法描述：判断类型是否为基本类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:15:05
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param clazz
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	private static boolean isBaseType(Class clazz) {
		if (clazz == Integer.class) {
			return true;
		}
		if (clazz == Long.class) {
			return true;
		}
		if (clazz == Double.class) {
			return true;
		}
		if (clazz == Byte.class) {
			return true;
		}
		if (clazz == Float.class) {
			return true;
		}
		if (clazz == Short.class) {
			return true;
		}
		if (clazz == Boolean.class) {
			return true;
		}
		return false;
	}
	
	/**
	 * getPropertyByType方法描述：得到对象属性的类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:13:28
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @param strProperty 属性名称
	 * @return Class 类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getPropertyByType(Object obj,String strProperty){
		Class clazz = obj.getClass();
		Class clasType = null;
		Field[] fields = clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			Field field = fields[i];
			if(field.getName().equals(strProperty)){
				clasType = field.getType();
				break;
			}
		}
		return clasType;
	}
	/**
	 * setValue方法描述：根据对象属性名将对对象属性赋值
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:15:33
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws UserSystemException void
	 *//*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setValue(Object obj,String fieldName,String value)throws UserSystemException{
		try {
	    	Class c=Class.forName(obj.getClass().toString().substring(6));
	    	String stringLetter=fieldName.substring(0, 1).toUpperCase(); 
	    	String setName="set"+stringLetter+fieldName.substring(1); 
	    	Class paramType = c.getDeclaredField(fieldName).getType();
	    	Method setMethod=c.getMethod(setName, new Class[]{paramType}); 
	        if(paramType.getName().equals("java.lang.Integer")){
	        	setMethod.invoke(obj, new Object[]{Integer.valueOf(value)});
	    	}else if(paramType.getName().equals("java.lang.Double")){
	    		setMethod.invoke(obj, new Object[]{Double.valueOf(value)});
	    	}else if(paramType.getName().equals("java.lang.Float")){
	    		setMethod.invoke(obj, new Object[]{Float.valueOf(value)});
	    	}else{
	    		setMethod.invoke(obj, new Object[]{value});
	    	}
		} catch (Exception e) {
			throw new UserSystemException(e.getMessage());
		}  
	}
	

	
	*//**
	 * getObjectTag方法描述：得到对象中注释，并将注释名和值转换为字符串
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:16:04
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj
	 * @return
	 * @throws Exception String
	 *//*
	@SuppressWarnings({ "rawtypes" })
	public static String getObjectTag(Object obj) throws Exception{
		String str=null;
		String tableName = null;
		String fieldName = null;
		String fieldValue = null;
		
		Class cls = obj.getClass();
		Annotation[] anno = cls.getAnnotations();
		for(Annotation tag : anno){
			//得到类中注释的表名
			tableName = ((TableTag) tag).cName();
			if(tableName!=null && tableName !=""){
				str = tableName+"表:";
			}
		}
		if(tableName !=null && tableName !=""){
			//取类中的属性注释
			Field[] fieldArray = cls.getDeclaredFields();
			for(Field field :fieldArray){
				Annotation[] fieldAnno = field.getDeclaredAnnotations();
				for(Annotation tag : fieldAnno){
					//得到注释中cName及，字段中文名称
					fieldName = ((FieldTag) tag).cName();
					if(fieldName!=null && fieldName !=""){
						//得到字段中值
						fieldValue = String.valueOf(getPropertyValueFormObject(obj,((FieldTag) tag).cName()));
						if(fieldValue!=null && fieldValue !=""){
							str += fieldName+":"+fieldValue+";";
						}
					}
				}
			}
		}
		return str;
	}
	
	*//**
	 * isValue方法描述：判断value是否相等
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:16:23
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj
	 * @param fild
	 * @param value
	 * @return boolean
	 *//*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isValue(Object obj,String fild,String value){
	    try {
	    	Class c=Class.forName(obj.getClass().toString().substring(6));
	    	String stringLetter=fild.substring(0, 1).toUpperCase(); 
	    	//获得相应属性的getXXX和setXXX方法名称 
	    	String getName="get"+stringLetter+fild.substring(1); 
	    	//获取相应的方法 
	    	Method getMethod=c.getMethod(getName, new Class[]{}); 
	    	//调用源对象的getXXX（）方法  
	    	getMethod.invoke(obj, new Object[]{});
	    	if(value.equals(getMethod.invoke(obj, new Object[]{}))){
	    		return true;
	    	}
		} catch (Exception e) {
			System.out.println("获得值出错!");
		}  
		return false;
	}
	
	*//**
	 * returnFunction方法描述：获取方法公式的对应字段数组
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:16:34
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param formula
	 * @return List<String>
	 *//*
	public static List<String> returnFunction(String formula){
		List<String> lis = new ArrayList<String>();
		String str = formula;
		while((formula.length()-str.length()) < formula.length() && (str.contains("[") || str.contains("]")) ){
			lis.add(str.substring(str.indexOf("[")+1,str.indexOf("]")));
			str = str.substring(str.indexOf("]")+1,str.length());
		}
		return lis;
	}*/
	
	/**
	 * BigDecima方法描述：转换数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午12:16:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param value
	 * @param scale 小数位数
	 * @return String
	 */
	public static String BigDecima(Double value,int scale){
		BigDecimal b = new  BigDecimal(value);   
		BigDecimal bf  =  b.setScale(scale,BigDecimal.ROUND_HALF_UP);
		return bf.toString();
	}
}
