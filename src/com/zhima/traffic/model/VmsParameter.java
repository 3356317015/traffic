package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="播音参数",eName="VmsParameter",tabName="vms_parameter")
public class VmsParameter implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="参数序号",eName="parameterSeq",field="parameter_seq",pk=true)
	private String parameterSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="参数类型",eName="parameterType",field="parameter_type")
	private String parameterType="";

	@FieldTag(cName="参数代码",eName="parameterCode",field="parameter_code")
	private String parameterCode="";
	
	@FieldTag(cName="参数名称",eName="parameterName",field="parameter_name")
	private String parameterName="";
	
	@FieldTag(cName="参数值",eName="parameterValue",field="parameter_value")
	private String parameterValue="";
	
	@FieldTag(cName="参数描述",eName="parameterDesc",field="parameter_desc")
	private String parameterDesc="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	

	// Constructors

	/** default constructor */
	public VmsParameter() {
	}
	/** full constructor */

	// Property accessors

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



	public String getParameterType() {
		return parameterType;
	}



	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
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


	
	public String getParameterDesc() {
		return parameterDesc;
	}
	
	
	
	public void setParameterDesc(String parameterDesc) {
		this.parameterDesc = parameterDesc;
	}
	
	
	
	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}