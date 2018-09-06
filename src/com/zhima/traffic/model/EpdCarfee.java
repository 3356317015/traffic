package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="车辆收费项",eName="EpdCarfee",tabName="epd_carfee")
public class EpdCarfee implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="车辆收费序号",eName="carfeeSeq",field="carfee_seq",pk=true)
	private String carfeeSeq="";
	
	@FieldTag(cName="车辆序号",eName="carSeq",field="car_seq")
	private String carSeq="";
	
	@FieldTag(cName="管理费",eName="manageFee",field="manage_fee")
	private Double manageFee=0.00;
	
	@FieldTag(cName="安检费",eName="safecarFee",field="safecar_fee")
	private Double safecarFee=0.00;
	
	@FieldTag(cName="清洁费",eName="clearFee",field="clear_fee")
	private Double clearFee=0.00;
	
	@FieldTag(cName="停车费",eName="stopcarFee",field="stopcar_fee")
	private Double stopcarFee=0.00;
	
	@FieldTag(cName="出站费",eName="outstationFee",field="outstation_fee")
	private Double outstationFee=0.00;
	
	@FieldTag(cName="其他费用1",eName="otherOne",field="other_one")
	private Double otherfeeOne=0.00;
	
	@FieldTag(cName="其他费用2",eName="otherTwo",field="other_two")
	private Double otherTwo=0.00;
	
	@FieldTag(cName="其他费用3",eName="otherThree",field="other_three")
	private Double otherThree=0.00;
	
	@FieldTag(cName="其他费用4",eName="otherFour",field="other_four")
	private Double otherFour=0.00;
	
	@FieldTag(cName="其他费用5",eName="otherFive",field="other_five")
	private Double otherFive=0.00;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors
	
	public EpdCarfee(){}

	// Property accessors
	public String getCarfeeSeq() {
		return carfeeSeq;
	}

	public void setCarfeeSeq(String carfeeSeq) {
		this.carfeeSeq = carfeeSeq;
	}

	public String getCarSeq() {
		return carSeq;
	}

	public void setCarSeq(String carSeq) {
		this.carSeq = carSeq;
	}

	public Double getManageFee() {
		return manageFee;
	}

	public void setManageFee(Double manageFee) {
		this.manageFee = manageFee;
	}

	public Double getSafecarFee() {
		return safecarFee;
	}

	public void setSafecarFee(Double safecarFee) {
		this.safecarFee = safecarFee;
	}

	public Double getClearFee() {
		return clearFee;
	}

	public void setClearFee(Double clearFee) {
		this.clearFee = clearFee;
	}

	public Double getStopcarFee() {
		return stopcarFee;
	}

	public void setStopcarFee(Double stopcarFee) {
		this.stopcarFee = stopcarFee;
	}

	public Double getOutstationFee() {
		return outstationFee;
	}

	public void setOutstationFee(Double outstationFee) {
		this.outstationFee = outstationFee;
	}

	public Double getOtherfeeOne() {
		return otherfeeOne;
	}

	public void setOtherfeeOne(Double otherfeeOne) {
		this.otherfeeOne = otherfeeOne;
	}

	public Double getOtherTwo() {
		return otherTwo;
	}

	public void setOtherTwo(Double otherTwo) {
		this.otherTwo = otherTwo;
	}

	public Double getOtherThree() {
		return otherThree;
	}

	public void setOtherThree(Double otherThree) {
		this.otherThree = otherThree;
	}

	public Double getOtherFour() {
		return otherFour;
	}

	public void setOtherFour(Double otherFour) {
		this.otherFour = otherFour;
	}

	public Double getOtherFive() {
		return otherFive;
	}

	public void setOtherFive(Double otherFive) {
		this.otherFive = otherFive;
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