package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="变量信息",eName="SamVariables",tabName="sam_variables")
public class SamVariables implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="变量",eName="variablesSeq",field="variables_seq",pk=true)
	private String variablesSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="变量类别",eName="variableType",field="variable_type")
	private String variableType="";
	
	@FieldTag(cName="变量代码",eName="variableCode",field="variable_code")
	private String variableCode="";
	
	@FieldTag(cName="变量名称",eName="variableName",field="variable_name")
	private String variableName="";
	
	@FieldTag(cName="状态",eName="status",field="status")
	private String status="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";
	
	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";

	public SamVariables() {
	}

	public String getVariablesSeq() {
		return variablesSeq;
	}

	public void setVariablesSeq(String variablesSeq) {
		this.variablesSeq = variablesSeq;
	}
	
	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	public String getVariableCode() {
		return variableCode;
	}

	public void setVariableCode(String variableCode) {
		this.variableCode = variableCode;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	

}