
package com.service.traffic.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;
import com.zhima.frame.model.SamLogDetail;

/**
 * SamLogDetailDao概要说明：数据日志操作类
 * @author lcy
 */
public class SamLogDetailDao extends BaseDao{
	public SamLogDetailDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加数据操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午09:08:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samLogDetail 添加数据库前数据日志
	 * @return samLogDetail 添加数据库后数据日志
	 */
	public SamLogDetail insert(SamLogDetail samLogDetail, Map<String, Object> config){
		String pk = super.insert(samLogDetail,config);
		samLogDetail.setDetailLogSeq(pk);
		return samLogDetail;
	}
	
	/**
	 * update方法描述：作改数据操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午09:09:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samLogDetail 修改数据库前数据日志
	 */
	public void update(SamLogDetail samLogDetail, Map<String, Object> config){
		super.update(samLogDetail,config);
	}
	
	/**
	 * deleteByPK方法描述：根据主键删除操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午03:30:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param detailLogSeq 操作日志主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String detailLogSeq){
		String strSql = "delete from sam_log_detail where detail_log_seq=?";
		List params = new ArrayList();
		params.add(detailLogSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * queryByOperSeq方法描述：根据操作日志序号要询日志详细
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午01:29:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param operSeq 操作日志序号
	 * @return List<SamLogDetail> 日志详细
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamLogDetail> queryByOperSeq(String operSeq) {
		String strSql = "select * from sam_log_detail where oper_log_seq=?";
		List params = new ArrayList();
		params.add(operSeq);
		return (List<SamLogDetail>) super.queryAll(strSql,params,new SamLogDetail());
	}

	/**
	 * deleteByOperSeq方法描述：根据操作日志序号删除日志详细
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午02:17:57
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param config 操作日志序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByOperSeq(String operLogSeq) {
		String sql = "delete from sam_log_detail where oper_log_seq=?";
		List params = new ArrayList();
		params.add(operLogSeq);
		super.executeSql(sql, params);
	}
	
	/**
	 * getValueByObjectPro方法慨述:通过对象，对象中的某个属性名称得到属性的值(调用get方法)
	 * 创 建  人：鲁承毅
	 * 创建时间：2012-3-15 下午05:03:42
	 * 修 改  人：鲁承毅
	 * 修改日期：2012-3-15 下午05:03:42
	 * @param obj 对象
	 * @param property 属性名称
	 * @return Object  属性值
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object getValueByObjectPro(Object obj,String proName) throws Exception{
		Object value = null;
		try {
			if(null != obj && null != proName && "" != proName){
				Class newClass = Class.forName(obj.getClass().getName());
				String ufieldName = proName.substring(0,1).toUpperCase();
				String methodNamea = "get" + ufieldName + proName.substring(1);
				Method method = newClass.getMethod(methodNamea,new Class[]{});
				value = method.invoke(obj, new Object[]{});
			}
		} catch (Exception e) {
			throw e;
		} 
		return value;
	}
	/**
	 * insertDataLog方法描述：插入新数据日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午09:25:27
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param object 插入数据对象
	 * @param pk 主键
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public void insertDataLog(Object object, String pkValue,Map<String, Object> config){
		try {
			

			Class newClass = Class.forName(object.getClass().getName());
			Field[] filds = newClass.getDeclaredFields();
			Annotation[] tableAnnotations = newClass.getAnnotations();
			/**
			 * 表信息
			 */
			String tableName = ((TableTag) tableAnnotations[0]).tabName();
			String className = ((TableTag) tableAnnotations[0]).eName();
			String tableDesc = ((TableTag) tableAnnotations[0]).cName();
			//记录新增数据表及主键
			insertItem(tableName,pkValue,"1","I");
			//验证是否记录日志
			String operLogSeq = "";
			if (null != config && config.size()>0){
				operLogSeq = config.get("operLogSeq").toString();
				if (null == operLogSeq || operLogSeq.length()<=0){
					return;
				}
			}else{
				return;
			}
			for (int i = 1; i < filds.length; i++) {
				/**
				 * 字段信息
				 */
				String eName = filds[i].getName();
				if ("interface java.sql.Blob".equals(filds[i].getType().toString())){
					continue;
				}
				Object newValue = getValueByObjectPro(object, eName);
				Annotation[] secAnno = filds[i].getDeclaredAnnotations();
				String fieldName = "";
				String cName = "";
				String match = "";
				boolean isPk = false;
				for (int j = 0; j < secAnno.length; j++) {
					fieldName = ((FieldTag) secAnno[j]).field();
					cName = ((FieldTag) secAnno[j]).cName();
					match = ((FieldTag) secAnno[j]).match();
					isPk =  ((FieldTag) secAnno[j]).pk();
				}
				SamLogDetail samLogData = new SamLogDetail();
				samLogData.setOperLogSeq(operLogSeq);
				samLogData.setTableName(tableName);
				samLogData.setTableDesc(tableDesc);
				samLogData.setFieldName(fieldName);
				samLogData.setFieldDesc(cName);
				if (isPk == true){
					samLogData.setNewValue(pkValue);
				}else {
					if (null != match && !"".equals(match)){
						String strArr[] = match.split(",");
						for (int j = 0; j < strArr.length; j++) {
							if (newValue.toString().equals(strArr[j].split("/")[0])){
								samLogData.setNewValue(strArr[j]);
								break;
							}
						}
					}else{
						samLogData.setNewValue(newValue.toString());
					}
				}
				super.insertLog(samLogData,config);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * updateDataLog方法描述：修改数据日志记录
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午09:26:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param object void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public void updateDataLog(String pkName, String pkValue, Object object,Map<String, Object> config){
		try {
			Class newClass = Class.forName(object.getClass().getName());
			Field[] filds = newClass.getDeclaredFields();
			Annotation[] tableAnnotations = newClass.getAnnotations();
			/**
			 * 表信息
			 */
			String tableName = ((TableTag) tableAnnotations[0]).tabName();
			String className = ((TableTag) tableAnnotations[0]).eName();
			String tableDesc = ((TableTag) tableAnnotations[0]).cName();
			
			//记录新增数据表及主键
			insertItem(tableName,pkValue,"1","U");
			String operLogSeq = "";
			if (null != config && config.size()>0){
				operLogSeq = config.get("operLogSeq").toString();
				if (null == operLogSeq || operLogSeq.length()<=0){
					return;
				}
			}else{
				return;
			}
			
			//查询旧数据
			String strSql = "select * from " +tableName+ " where " + pkName + " = ?";
			List params = new ArrayList();
			params.add(pkValue);
			List<?> list = super.queryAll(strSql,params,object);
			Object obj = null;
			if (null != list && list.size()>0){
				obj = list.get(0);
			}
			
			for (int i = 1; i < filds.length; i++) {
				/**
				 * 字段信息
				 */
				String eName = filds[i].getName();
				if ("interface java.sql.Blob".equals(filds[i].getType().toString())){
					continue;
				}
				Object oldValue = getValueByObjectPro(obj, eName);
				Object newValue = getValueByObjectPro(object, eName);
				Annotation[] secAnno = filds[i].getDeclaredAnnotations();
				String fieldName = "";
				String cName = "";
				String match = "";
				for (int j = 0; j < secAnno.length; j++) {
					fieldName = ((FieldTag) secAnno[j]).field();
					cName = ((FieldTag) secAnno[j]).cName();
					match = ((FieldTag) secAnno[j]).match();
				}
				SamLogDetail samLogData = new SamLogDetail();
				samLogData.setOperLogSeq(operLogSeq);
				samLogData.setTableName(tableName);
				samLogData.setTableDesc(tableDesc);
				samLogData.setFieldName(fieldName);
				samLogData.setFieldDesc(cName);
				//旧值
				if (null != match && !"".equals(match)){
					String strArr[] = match.split(",");
					for (int j = 0; j < strArr.length; j++) {
						if (oldValue.toString().equals(strArr[j].split("/")[0])){
							samLogData.setOldValue(strArr[j]);
							break;
						}
					}
				}else{
					if (null != oldValue){
						samLogData.setOldValue(oldValue.toString());
					}else{
						samLogData.setOldValue(null);
					}
					
				}
				//新值
				if (null != match && !"".equals(match)){
					String strArr[] = match.split(",");
					for (int j = 0; j < strArr.length; j++) {
						if (newValue.toString().equals(strArr[j].split("/")[0])){
							samLogData.setNewValue(strArr[j]);
							break;
						}
					}
				}else{
					if (null != newValue){
						samLogData.setNewValue(newValue.toString());
					}else{
						samLogData.setNewValue(null);
					}
				}
				super.insertLog(samLogData,config);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * updateDataLog方法描述：修改数据日志记录
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午09:26:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param object void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public void executeDataLog(String pkValue, Object object,Map<String, Object> config){
		try {
			Class newClass = Class.forName(object.getClass().getName());
			Field[] filds = newClass.getDeclaredFields();
			Annotation[] tableAnnotations = newClass.getAnnotations();
			/**
			 * 表信息
			 */
			String tableName = ((TableTag) tableAnnotations[0]).tabName();
			String className = ((TableTag) tableAnnotations[0]).eName();
			String tableDesc = ((TableTag) tableAnnotations[0]).cName();
			
			//记录新增数据表及主键
			insertItem(tableName,pkValue,"1","U");
			String operLogSeq = "";
			if (null != config && config.size()>0){
				operLogSeq = config.get("operLogSeq").toString();
				if (null == operLogSeq || operLogSeq.length()<=0){
					return;
				}
			}else{
				return;
			}
			
			String pkName="";
			//通过删除数据表主键查询旧数据
			for (int i = 1; i < filds.length; i++) {
				Annotation[] secAnno = filds[i].getDeclaredAnnotations();
				String fieldName = "";
				boolean isPk = false;
				for (int j = 0; j < secAnno.length; j++) {
					fieldName = ((FieldTag) secAnno[j]).field();
					isPk =  ((FieldTag) secAnno[j]).pk();
				}
				if (isPk == true){
					pkName = fieldName;
					break;
				}
			}
			//查询旧数据
			String strSql = "select * from " +tableName+ " where " + pkName + " = ?";
			List params = new ArrayList();
			params.add(pkValue);
			List<?> list = super.queryAll(strSql,params,object);
			Object obj = null;
			if (null != list && list.size()>0){
				obj = list.get(0);
			}
			
			for (int i = 1; i < filds.length; i++) {
				/**
				 * 字段信息
				 */
				String eName = filds[i].getName();
				if ("interface java.sql.Blob".equals(filds[i].getType().toString())){
					continue;
				}
				Object oldValue = getValueByObjectPro(obj, eName);
				Object newValue = getValueByObjectPro(object, eName);
				Annotation[] secAnno = filds[i].getDeclaredAnnotations();
				String fieldName = "";
				String cName = "";
				String match = "";
				for (int j = 0; j < secAnno.length; j++) {
					fieldName = ((FieldTag) secAnno[j]).field();
					cName = ((FieldTag) secAnno[j]).cName();
					match = ((FieldTag) secAnno[j]).match();
				}
				SamLogDetail samLogData = new SamLogDetail();
				samLogData.setOperLogSeq(operLogSeq);
				samLogData.setTableName(tableName);
				samLogData.setTableDesc(tableDesc);
				samLogData.setFieldName(fieldName);
				samLogData.setFieldDesc(cName);
				//旧值
				if (null != match && !"".equals(match)){
					String strArr[] = match.split(",");
					for (int j = 0; j < strArr.length; j++) {
						if (oldValue.toString().equals(strArr[j].split("/")[0])){
							samLogData.setOldValue(strArr[j]);
							break;
						}
					}
				}else{
					samLogData.setOldValue(oldValue.toString());
				}
				//新值
				if (null != match && !"".equals(match)){
					String strArr[] = match.split(",");
					for (int j = 0; j < strArr.length; j++) {
						if (newValue.toString().equals(strArr[j].split("/")[0])){
							samLogData.setNewValue(strArr[j]);
							break;
						}
					}
				}else{
					samLogData.setNewValue(newValue.toString());
				}
				super.insertLog(samLogData,config);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * deleteDataLog方法描述：删除数据日志记录
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午09:26:36
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param object void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void deleteDataLog(String pkValue, Object object, Map<String, Object> config){
		try {
			Class newClass = Class.forName(object.getClass().getName());
			Field[] filds = newClass.getDeclaredFields();
			Annotation[] tableAnnotations = newClass.getAnnotations();
			/**
			 * 表信息
			 */
			String tableName = ((TableTag) tableAnnotations[0]).tabName();
			String className = ((TableTag) tableAnnotations[0]).eName();
			String tableDesc = ((TableTag) tableAnnotations[0]).cName();
			
			//记录新增数据表及主键
			insertItem(tableName,pkValue,"1","D");
			String operLogSeq = "";
			if (null != config && config.size()>0){
				operLogSeq = config.get("operLogSeq").toString();
				if (null == operLogSeq || operLogSeq.length()<=0){
					return;
				}
			}else{
				return;
			}
			String pkName="";
			//通过删除数据表主键查询旧数据
			for (int i = 1; i < filds.length; i++) {
				Annotation[] secAnno = filds[i].getDeclaredAnnotations();
				String fieldName = "";
				boolean isPk = false;
				for (int j = 0; j < secAnno.length; j++) {
					fieldName = ((FieldTag) secAnno[j]).field();
					isPk =  ((FieldTag) secAnno[j]).pk();
				}
				if (isPk == true){
					pkName = fieldName;
					break;
				}
			}
			String strSql = "select * from " +tableName+ " where " + pkName + " = ?";
			List params = new ArrayList();
			params.add(pkValue);
			List<?> list = super.queryAll(strSql,params,object);
			Object obj = null;
			if (null != list && list.size()>0){
				obj = list.get(0);
			}
			
			for (int i = 1; i < filds.length; i++) {
				/**
				 * 字段信息
				 */
				String eName = filds[i].getName();
				if ("interface java.sql.Blob".equals(filds[i].getType().toString())){
					continue;
				}
				Object oldValue = getValueByObjectPro(obj, eName);
				Annotation[] secAnno = filds[i].getDeclaredAnnotations();
				String fieldName = "";
				String cName = "";
				String match = "";
				for (int j = 0; j < secAnno.length; j++) {
					fieldName = ((FieldTag) secAnno[j]).field();
					cName = ((FieldTag) secAnno[j]).cName();
					match = ((FieldTag) secAnno[j]).match();
				}
				SamLogDetail samLogData = new SamLogDetail();
				samLogData.setOperLogSeq(operLogSeq);
				samLogData.setTableName(tableName);
				samLogData.setTableDesc(tableDesc);
				samLogData.setFieldName(fieldName);
				samLogData.setFieldDesc(cName);
	
				if (null != match && !"".equals(match)){
					String strArr[] = match.split(",");
					for (int j = 0; j < strArr.length; j++) {
						if (oldValue.toString().equals(strArr[j].split("/")[0])){
							samLogData.setOldValue(strArr[j]);
							break;
						}
					}
				}else{
					samLogData.setOldValue(oldValue.toString());
				}
				super.insertLog(samLogData,config);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertItem(String tableName, String tableSeq, String itemType, String operType){
		return;
		/*if ("U".equals(itemType) || "D".equals(itemType)){
			List params = new ArrayList();
			params.add(tableName);
			params.add(tableSeq);
			int count = super.queryCount("select count(1) from sam_tasking_item where table_name =? and table_seq =? and item_type='1'", params);
			if (count>0){
				if ("u".equals(itemType)){
					return;
				}else{
					super.executeSql("delete from sam_tasking_item where table_name =? and table_seq =? and item_type='1'", params);
				}
			}
		}
		SamTaskingItem taskingItem = new SamTaskingItem();
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		taskingItem.setTableName(tableName);
		taskingItem.setTableSeq(tableSeq);
		taskingItem.setItemType(itemType);
		taskingItem.setOperType(operType);
		taskingItem.setCreateTime(currTime);
		taskingItem.setUpdateTime(currTime);
		super.insertLog(taskingItem);*/
	}

}
