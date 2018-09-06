package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="价套设置",eName="EpdFaresuit",tabName="epd_faresuit")
public class EpdFaresuit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="价套序号",eName="faresuitSeq",field="faresuit_seq",pk=true)
	private String faresuitSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="价套代码",eName="faresuitCode",field="faresuit_code")
	private String faresuitCode="";
	
	@FieldTag(cName="价套名称",eName="faresuitName",field="faresuit_name")
	private String faresuitName="";
	
	@FieldTag(cName="开始日期",eName="startDate",field="start_date")
	private String startDate="";
	
	@FieldTag(cName="结束日期",eName="endDate",field="end_date")
	private String endDate="";
	
	@FieldTag(cName="是否有效",eName="faresuitStatus",field="faresuit_status")
	private String faresuitStatus="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public EpdFaresuit() {
	}
	

	/** full constructor */

	// Property accessors
	public String getFaresuitSeq() {
		return faresuitSeq;
	}


	public void setFaresuitSeq(String faresuitSeq) {
		this.faresuitSeq = faresuitSeq;
	}


	public String getOrganizeSeq() {
		return organizeSeq;
	}


	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}


	public String getFaresuitCode() {
		return faresuitCode;
	}


	public void setFaresuitCode(String faresuitCode) {
		this.faresuitCode = faresuitCode;
	}


	public String getFaresuitName() {
		return faresuitName;
	}


	public void setFaresuitName(String faresuitName) {
		this.faresuitName = faresuitName;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFaresuitStatus() {
		return faresuitStatus;
	}


	public void setFaresuitStatus(String faresuitStatus) {
		this.faresuitStatus = faresuitStatus;
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