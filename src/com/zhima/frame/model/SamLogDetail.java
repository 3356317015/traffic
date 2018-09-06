package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="数据日志信息",eName="samLogDetail",tabName="sam_log_detail")
public class SamLogDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="数据日志序号",eName="detailLogSeq",field="detail_log_seq",pk=true)
	private String detailLogSeq="";
	
	@FieldTag(cName="功能日志序号",eName="operLogSeq",field="oper_log_seq")
	private String operLogSeq="";
	
	@FieldTag(cName="表名",eName="tableName",field="table_name")
	private String tableName="";
	
	@FieldTag(cName="表描述",eName="tableDesc",field="table_desc")
	private String tableDesc="";
	
	@FieldTag(cName="字段",eName="fieldName",field="field_name")
	private String fieldName="";
	
	@FieldTag(cName="名称",eName="fieldDesc",field="field_desc")
	private String fieldDesc="";
	
	@FieldTag(cName="旧值",eName="oldValue",field="old_value")
	private String oldValue="";
	
	@FieldTag(cName="新值",eName="newValue",field="new_value")
	private String newValue="";

	// Constructors
	
	/** default constructor */
	public SamLogDetail() {
	}
	
	/** full constructor */

	// Property accessors

	public String getTableName() {
		return tableName;
	}

	public String getDetailLogSeq() {
		return detailLogSeq;
	}

	public void setDetailLogSeq(String detailLogSeq) {
		this.detailLogSeq = detailLogSeq;
	}

	public String getOperLogSeq() {
		return operLogSeq;
	}

	public void setOperLogSeq(String operLogSeq) {
		this.operLogSeq = operLogSeq;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	

}