package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="检票口",eName="EpdCheckgate",tabName="epd_checkgate")
public class EpdCheckgate implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="检票口序号",eName="checkgateSeq",field="checkgate_seq",pk=true)
	private String checkgateSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="检票口号",eName="checkCode",field="check_code")
	private String checkCode="";
	
	@FieldTag(cName="检票口名称",eName="checkName",field="check_name")
	private String checkName="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="发车位",eName="gateName",field="gate_name",db=false)
	private String gateName="";
	
	@FieldTag(cName="默认检票口",eName="defaultCheck",field="default_check",db=false)
	private String defaultCheck="0";

	// Constructors

	/** default constructor */
	public EpdCheckgate() {
	}

	/** full constructor */

	// Property accessors
	public String getCheckgateSeq() {
		return checkgateSeq;
	}

	public void setCheckgateSeq(String checkgateSeq) {
		this.checkgateSeq = checkgateSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
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

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public String getDefaultCheck() {
		return defaultCheck;
	}

	public void setDefaultCheck(String defaultCheck) {
		this.defaultCheck = defaultCheck;
	}

}