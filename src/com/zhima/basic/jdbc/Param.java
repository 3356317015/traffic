
package com.zhima.basic.jdbc;

import java.sql.Blob;

/**
 * Param概要说明：对象参数信息
 * @author lcy
 */
public class Param {
  
	/***
	 * 参数主键
	 */
	private String paramSeq;
	/**
	 * 参数字段
	 */
	private String paramField;
	/***
	 * 参数类型
	 */
	private String paramType;
	/**
	 * 参数值
	 */
	private String paramValue;
	/**
	 * Blod数据
	 */
	private Blob blobData;

	/***
	 * 
	 * 构造函数:
	 * @param paramField
	 * @param paramType
	 * @param paramValue
	 */
	public Param(String paramField,String paramType,String paramValue,Blob blobData){
		this.paramField = paramField;
		this.paramType = paramType;
		this.paramValue = paramValue;
		this.blobData = blobData;
	}
	public String getParamSeq() {
		return paramSeq;
	}
	public void setParamSeq(String paramSeq) {
		this.paramSeq = paramSeq;
	}
	public String getParamField() {
		return paramField;
	}
	public void setParamField(String paramField) {
		this.paramField = paramField;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public Blob getBlobData() {
		return blobData;
	}
	public void setBlobData(Blob blobData) {
		this.blobData = blobData;
	}
	
}