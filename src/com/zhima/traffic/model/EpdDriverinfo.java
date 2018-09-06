package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="驾驶员资质项",eName="EpdDriverinfo",tabName="epd_driverinfo")
public class EpdDriverinfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="驾驶员资质序号",eName="driverinfoSeq",field="driverinfo_seq",pk=true)
	private String driverinfoSeq="";
	
	@FieldTag(cName="驾驶员序号",eName="driverSeq",field="driver_seq")
	private String driverSeq="";
	
	@FieldTag(cName="证件名称",eName="cardName",field="card_name")
	private String cardName="";
	
	@FieldTag(cName="证件号码",eName="cardCode",field="card_code")
	private String cardCode="";
	
	@FieldTag(cName="开始日期",eName="startDate",field="start_date")
	private String startDate="";
	
	@FieldTag(cName="结束日期",eName="endDate",field="end_date")
	private String endDate="";
	
	@FieldTag(cName="审核",eName="review",field="review")
	private String review="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors

	/** default constructor */
	public EpdDriverinfo() {
	}

	public String getDriverinfoSeq() {
		return driverinfoSeq;
	}

	public void setDriverinfoSeq(String driverinfoSeq) {
		this.driverinfoSeq = driverinfoSeq;
	}

	public String getDriverSeq() {
		return driverSeq;
	}

	public void setDriverSeq(String driverSeq) {
		this.driverSeq = driverSeq;
	}
	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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