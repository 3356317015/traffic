package com.zhima.basic.jdbc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhima.basic.exception.UserSystemException;

/** OperObj概要说明：操作对象
 * @author lcy
 */
public class OperObj {
	/**
	 * 数据库表名
	 */
	public String tabName;
	/***
	 * 主键字段
	 */
	public Param fieldPK;
	/**
	 * 所有数据List
	 */
	public List<Param> fieldList;
	/**
	 * 二进制数据List
	 */
	public List<Param> blobList;
	
	
	/**
	 * toField方法描述：获取数据库字段对应键值对
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-6 上午11:48:35
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 实体对象
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void toField(Object obj) throws Exception{
		fieldList = new ArrayList<Param>();
		blobList =  new ArrayList<Param>();
		try {
			Class clas = Class.forName(obj.getClass().getName());
			//获取表名
			Annotation[] annotations = clas.getDeclaredAnnotations();
			if(null != annotations && annotations.length>0){
				for(Annotation ann:annotations){
					tabName = ((TableTag)ann).tabName();
				}
			}
			//获取所有成员变量
			Field[] fields = clas.getDeclaredFields();
			//组装数据库字段、值的键值对
			for(Field field:fields){
				//获取注释
				Annotation[] fieldAnns = field.getDeclaredAnnotations();
				//存在注释
				if(null != fieldAnns && fieldAnns.length>0){
					for(Annotation ann:fieldAnns){
						boolean isDb = ((FieldTag)ann).db();
						if (isDb == true){
							//字段名
							String fieldName = ((FieldTag)ann).field();
							Boolean isPK = ((FieldTag)ann).pk();
							String cName = ((FieldTag)ann).cName();
							int length = ((FieldTag)ann).length();
							//获取字段的get方法
							StringBuffer fieldStr = new StringBuffer("get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
							Method fieldMethod = clas.getMethod(fieldStr.toString(),new Class[]{});
							String fieldType = field.getType().getName();
							Object fieldValue = fieldMethod.invoke(obj,new Object[]{});
							//判断值是否存在
							if(null != fieldValue){
								if(null != isPK && isPK == true){
									fieldPK = new Param(fieldName,fieldType,fieldValue.toString(),null);
								}else {
									if ("java.sql.Blob".equals(fieldType)){
										blobList.add(new Param(fieldName,fieldType,fieldValue.toString(),(Blob) fieldValue));
										fieldList.add(new Param(fieldName,fieldType,fieldValue.toString(),(Blob) fieldValue));
									}else{
										fieldList.add(new Param(fieldName,fieldType,fieldValue.toString(),null));
										if (length>0){
											if (fieldValue.toString().length()>length){
												throw new Exception("[" +cName+"]限制"+length+"位，添加数据失败。");
											}
										}
									}
								}
							}else{
								if ("java.sql.Blob".equals(fieldType)){
									blobList.add(new Param(fieldName,fieldType,null,null));
									fieldList.add(new Param(fieldName,fieldType,null,null));
								}
							}
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			throw new UserSystemException("类名不存在!");
		} catch (SecurityException e) {
			throw new UserSystemException("此方法不允许访问!");
		} catch (NoSuchMethodException e) {
			throw new UserSystemException("此方法不存在!");
		} catch (IllegalArgumentException e) {
			throw new UserSystemException("对象不是声明类的一个实例!");
		} catch (IllegalAccessException e) {
			throw new UserSystemException("此方法不允许访问!");
		} catch (InvocationTargetException e) {
			throw new UserSystemException("方法错误!");
		}
	}
	
	/**
	 * toObject方法描述：ResultSet转为实体对象集合
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-5 下午11:57:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param object 实体对象
	 * @param rs 数据集合
	 * @return List<?> 实体对象集合
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> toObject(Object object,ResultSet rs){
		List<Object> objects = new ArrayList<Object>();
		try {
			Class clas = Class.forName(object.getClass().getName());
			//获取所有成员变量
			Field[] fields = clas.getDeclaredFields();
			List<Method> methods = new ArrayList<Method>();
			List<String> fieldNames = new ArrayList<String>();
			List<String> fieldTypes = new ArrayList<String>();
			//组装数据库字段、值的键值对
			for(Field field:fields){
				//获取注释
				Annotation[] annotations = field.getDeclaredAnnotations();
				//存在注释
				if(null != annotations && annotations.length>0){
					for(Annotation annotation:annotations){
						//字段名,set方法获取
						Class fieldType = field.getType();
						StringBuffer fieldStr = new StringBuffer("set"
								+field.getName().substring(0,1).toUpperCase()
								+field.getName().substring(1));
						Method method = clas.getMethod(fieldStr.toString(),new Class[]{fieldType});	
						methods.add(method);
						fieldTypes.add(fieldType.getName());
						String fieldName = ((FieldTag)annotation).field();
						fieldNames.add(fieldName);
					}
				}
			}

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNum = rsmd.getColumnCount();
			while(rs.next()){
				Object obj = clas.newInstance();
				for(int j=1;j<=columnNum;j++){
					String columnName = rsmd.getColumnLabel(j);
					for(int i=0;i<methods.size();i++){
						String filedName = fieldNames.get(i);
						if(columnName.equals(filedName) || columnName.toLowerCase().equals(filedName)){
							Method method = methods.get(i);
							if (fieldTypes.get(i).equals("java.lang.Integer")||fieldTypes.get(i).equals("java.lang.int") ){
								method.invoke(obj, new Object[]{rs.getInt(filedName)});
							}else if (fieldTypes.get(i).equals("java.lang.Double")||fieldTypes.get(i).equals("double")){
								method.invoke(obj, new Object[]{rs.getDouble(filedName)});
							}else if (fieldTypes.get(i).equals("java.sql.Blob")){
								method.invoke(obj, new Object[]{rs.getBlob(filedName)});
							}else {
								if(null != rs.getObject(filedName)){
									method.invoke(obj, new Object[]{rs.getObject(filedName).toString()});
								}else {
									method.invoke(obj, new Object[]{""});
								}
							}
							break;
						}
					}
				}
				objects.add(obj);
			}
			return objects;
		} catch (ClassNotFoundException e) {
			throw new UserSystemException("类名不存在!");
		} catch (SecurityException e) {
			throw new UserSystemException("此方法不允许访问!");
		} catch (NoSuchMethodException e) {
			throw new UserSystemException("此方法不存在!");
		} catch (SQLException e) {
			throw new UserSystemException("数据访问出错!");
		} catch (IllegalArgumentException e) {
			throw new UserSystemException("对象不是声明类的一个实例!");
		} catch (IllegalAccessException e) {
			throw new UserSystemException("此方法不允许访问!");
		} catch (InvocationTargetException e) {
			throw new UserSystemException("方法错误!");
		} catch (InstantiationException e) {
			throw new UserSystemException("实例失败!");
		} 
	}
	
	/**
	 * getFieldByEName方法慨述:通过对象的英文名称得到字段
	 * @param obj 对象
	 * @param eName 英文注解名称
	 * @return Field 字段
	 */
	public void setObjValue(Object obj,String eName,String value){
		Field field = this.getFieldByEName(obj, eName);
		this.setObjValue(obj, field, value);
	}
	
	/**
	 * getFieldByEName方法慨述:通过对象的英文名称得到字段
	 * @param obj 对象
	 * @param eName 英文注解名称
	 * @return Field 字段
	 */
	public Field getFieldByEName(Object obj,String eName){
		Field[] fields = this.getAllField(obj);
		Field retField = null;
		if(fields != null){
			for(int i=0;i<fields.length;i++){
				Field field = fields[i];
				if(field.getAnnotation(FieldTag.class) != null){
					if(eName.equals(field.getAnnotation(FieldTag.class).eName())){
						retField = field;
						break;
					}
				}
			}
		}
		return retField;
	}
	
	/**
	 * getTableAllField方法慨述:得到对象所有字段
	 * @param obj
	 * @return Field[]
	 */
	public Field[] getAllField(Object obj){
		Field[] fields = null;
		try {
			Class<?> clas = Class.forName(obj.getClass().getName());
			//获取所有成员变量
			Field[] allFields = clas.getDeclaredFields();
			fields = allFields;
		} catch (ClassNotFoundException e) {
			throw new UserSystemException("类名不存在!",e);
		}
		return fields;
	}
	
	/**
	 * setObjValue方法描述：设置对象字段值
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-21 下午03:28:07
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @param field 字段
	 * @param value 值
	 */
	public void setObjValue(Object obj,Field field,String value){
		try {
			if(field != null && field.getAnnotation(FieldTag.class) != null){
	    		field.setAccessible(true);
	    		Object objValue = new Object();
	    		if(!field.getType().equals(String.class)){
	    			if(value == null || value.trim().length() <= 0){
	    				value = "0";
	    			}
	    		}
	    		if(field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)){
	    			objValue = Boolean.valueOf(value);
	    		}else if(field.getType().equals(Byte.class) || field.getType().equals(byte.class)){
	    			objValue = Byte.valueOf(value);
	    		}else if(field.getType().equals(Character.class) ){
	    			objValue = value.charAt(0);
	    		}else if(field.getType().equals(Double.class) || field.getType().equals(double.class)){
	    			objValue = Double.valueOf(value);
	    		}else if(field.getType().equals(Float.class) || field.getType().equals(float.class)){
	    			objValue = Float.valueOf(value);
	    		}else if(field.getType().equals(Integer.class) || field.getType().equals(int.class)){
	    			objValue = Integer.valueOf(value);
	    		}else if(field.getType().equals(Long.class) || field.getType().equals(long.class)){
	    			objValue = Long.valueOf(value);
	    		}else if(field.getType().equals(Short.class) || field.getType().equals(short.class)){
	    			objValue = Short.valueOf(value);
	    		}else{
	    			objValue = value;
	    		}
	    		field.set(obj, objValue);
			}
		} catch (Exception e) {
			throw new UserSystemException("得到字段值失败!",e);
		}
	}
}
