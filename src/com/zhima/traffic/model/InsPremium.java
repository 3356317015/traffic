package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="保险费",eName="InsPremium",tabName="ins_premium")
public class InsPremium implements java.io.Serializable {
	private static final long serialVersionUID = 1L;	

	@FieldTag(cName="保险费序号",eName="premiumSeq",field="premium_seq",pk=true)
	private String premiumSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="公司序号",eName="companySeq",field="company_seq")
	private String companySeq="";
	
	@FieldTag(cName="保险费",eName="premium",field="premium")
	private Double premium=0.00;
	
	@FieldTag(cName="开始票价",eName="startPrice",field="start_price")
	private Double startPrice=0.00;
	
	@FieldTag(cName="结束票价",eName="limitPrice",field="limit_price")
	private Double limitPrice=0.00;
	
	@FieldTag(cName="意外伤害险",eName="amountOne",field="amount_one")
	private Double amountOne=0.00;
	
	@FieldTag(cName="意外医疗险",eName="amountTwo",field="amount_two")
	private Double amountTwo=0.00;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";

	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="保险代码",eName="insuranceCode",field="insurance_code",db=false)
	private String insuranceCode="";
	
	@FieldTag(cName="保险名称",eName="insuranceName",field="insurance_name",db=false)
	private String insuranceName="";
	
	// Constructors
	/** default constructor */
	public InsPremium() {
	}
	/** full constructor */
	
	// Property accessors
	public String getPremiumSeq() {
		return premiumSeq;
	}

	public void setPremiumSeq(String premiumSeq) {
		this.premiumSeq = premiumSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getCompanySeq() {
		return companySeq;
	}

	public void setCompanySeq(String companySeq) {
		this.companySeq = companySeq;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(Double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public Double getAmountOne() {
		return amountOne;
	}

	public void setAmountOne(Double amountOne) {
		this.amountOne = amountOne;
	}

	public Double getAmountTwo() {
		return amountTwo;
	}

	public void setAmountTwo(Double amountTwo) {
		this.amountTwo = amountTwo;
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
	
	public String getInsuranceCode() {
		return insuranceCode;
	}
	
	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}
	
	public String getInsuranceName() {
		return insuranceName;
	}
	
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	
}