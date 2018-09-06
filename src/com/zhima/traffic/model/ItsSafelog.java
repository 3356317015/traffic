package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="安检项目日志",eName="ItsSafelog",tabName="its_safelog")
public class ItsSafelog implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	@FieldTag(cName="检查日志序号",eName="safelogSeq",field="safelog_seq",pk=true)
	private String safelogSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="检查序号",eName="safeSeq",field="safe_seq")
	private String safeSeq="";
	
	@FieldTag(cName="检查类型",eName="safeType",field="safe_type")
	private String safeType="";
	
	@FieldTag(cName="检查项目名称",eName="safeName",field="safe_name")
	private String safeName="";
	
	@FieldTag(cName="检查状态",eName="checkState",field="check_state")
	private Integer checkState=0;
	
	@FieldTag(cName="检查结果",eName="resultText",field="result_text")
	private String resultText="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	public ItsSafelog() {
	}
	
	// Property accessors
	public String getSafelogSeq() {
		return safelogSeq;
	}

	public void setSafelogSeq(String safelogSeq) {
		this.safelogSeq = safelogSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getSafeSeq() {
		return safeSeq;
	}

	public void setSafeSeq(String safeSeq) {
		this.safeSeq = safeSeq;
	}

	public String getSafeType() {
		return safeType;
	}

	public void setSafeType(String safeType) {
		this.safeType = safeType;
	}

	public String getSafeName() {
		return safeName;
	}

	public void setSafeName(String safeName) {
		this.safeName = safeName;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
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