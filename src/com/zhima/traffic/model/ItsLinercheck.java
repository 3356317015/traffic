package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="班次检票口",eName="ItsLinercheck",tabName="its_linercheck")
public class ItsLinercheck implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="班次检票口序号",eName="linercheckSeq",field="linercheck_seq",pk=true)
	private String linercheckSeq="";
	
	@FieldTag(cName="班次序号",eName="linerSeq",field="liner_seq")
	private String linerSeq="";
	
	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";
	
	@FieldTag(cName="检票口",eName="checkgateSeq",field="checkgate_seq")
	private String checkgateSeq="";

	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="检票口号",eName="checkCode",field="check_code", db=false)
	private String checkCode="";
	
	@FieldTag(cName="检票口名称",eName="checkName",field="check_name", db=false)
	private String checkName="";

	// Constructors

	/** default constructor */
	public ItsLinercheck() {
	}
	
	/** full constructor */
	
	// Property accessors


	public String getLinercheckSeq() {
		return linercheckSeq;
	}

	public void setLinercheckSeq(String linercheckSeq) {
		this.linercheckSeq = linercheckSeq;
	}

	public String getLinerSeq() {
		return linerSeq;
	}

	public void setLinerSeq(String linerSeq) {
		this.linerSeq = linerSeq;
	}

	public String getLinerDate() {
		return linerDate;
	}

	public void setLinerDate(String linerDate) {
		this.linerDate = linerDate;
	}

	public String getLinerId() {
		return linerId;
	}

	public void setLinerId(String linerId) {
		this.linerId = linerId;
	}

	public String getCheckgateSeq() {
		return checkgateSeq;
	}

	public void setCheckgateSeq(String checkgateSeq) {
		this.checkgateSeq = checkgateSeq;
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

}