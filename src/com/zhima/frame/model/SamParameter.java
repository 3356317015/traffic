package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="参数",eName="SamParameter",tabName="sam_parameter")
public class SamParameter implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="参数序号",eName="parameterSeq",field="parameter_seq",pk=true)
	private String parameterSeq="";
	
	@FieldTag(cName="网点序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="参数代码",eName="parameterCode",field="parameter_code")
	private String parameterCode="";
	
	@FieldTag(cName="参数名称",eName="parameterName",field="parameter_name")
	private String parameterName="";
	
	@FieldTag(cName="参数值",eName="parameterValue",field="parameter_value")
	private String parameterValue="";

	@FieldTag(cName="参数组",eName="groupName",field="group_name")
	private String groupName="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";

	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="服务网点名称",eName="organizeName",field="organize_name",db=false)
	private String organizeName="";
	

	public SamParameter() {
	}


	public String getParameterSeq() {
		return parameterSeq;
	}


	public void setParameterSeq(String parameterSeq) {
		this.parameterSeq = parameterSeq;
	}


	public String getOrganizeSeq() {
		return organizeSeq;
	}


	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}


	public String getParameterCode() {
		return parameterCode;
	}


	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}


	public String getParameterName() {
		return parameterName;
	}


	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}


	public String getParameterValue() {
		return parameterValue;
	}


	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
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


	public String getOrganizeName() {
		return organizeName;
	}


	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}

	
}