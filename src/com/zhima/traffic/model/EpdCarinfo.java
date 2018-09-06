package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="车辆资质项",eName="EpdCarinfo",tabName="epd_carinfo")
public class EpdCarinfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="车辆资质序号",eName="carinfoSeq",field="carinfo_seq",pk=true)
	private String carinfoSeq="";
	
	@FieldTag(cName="车辆序号",eName="carSeq",field="car_seq")
	private String carSeq="";
	
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
	public EpdCarinfo() {
	}

	public String getCarinfoSeq() {
		return carinfoSeq;
	}

	public void setCarinfoSeq(String carinfoSeq) {
		this.carinfoSeq = carinfoSeq;
	}

	public String getCarSeq() {
		return carSeq;
	}

	public void setCarSeq(String carSeq) {
		this.carSeq = carSeq;
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