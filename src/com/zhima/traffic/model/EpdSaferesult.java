package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="检查结果",eName="EpdSaferesult",tabName="epd_saferesult")
public class EpdSaferesult implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@FieldTag(cName="检查结果序号",eName="saferesultSeq",field="saferesult_seq",pk=true)
	private String saferesultSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="检查类型",eName="safeType",field="safe_type")
	private String safeType="";
	
	@FieldTag(cName="结果类型",eName="resultType",field="result_type")
	private String resultType="";
	
	@FieldTag(cName="结果文描述",eName="resultText",field="result_text")
	private String resultText="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	public EpdSaferesult() {
	}
	// Property accessors
	public String getSaferesultSeq() {
		return saferesultSeq;
	}

	public void setSaferesultSeq(String saferesultSeq) {
		this.saferesultSeq = saferesultSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}
	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}
	public String getSafeType() {
		return safeType;
	}

	public void setSafeType(String safeType) {
		this.safeType = safeType;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
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