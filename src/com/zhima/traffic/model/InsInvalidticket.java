package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="废票记录表",eName="InsInvalidticket",tabName="ins_invalidticket")
public class InsInvalidticket implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="废票序号",eName="invalidticketSeq",field="invalidticket_seq",pk=true)
	private String invalidticketSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="订单号",eName="orderNo",field="order_no")
	private String orderNo="";
	
	@FieldTag(cName="事务号",eName="transId",field="trans_id")
	private String transId="";
	  
	@FieldTag(cName="票据序号",eName="ticketSeq",field="ticket_seq")
	private String ticketSeq="";
	  
	@FieldTag(cName="保险号",eName="insuranceCode",field="insurance_code")
	private String insuranceCode="";
	  
	@FieldTag(cName="公司序号'",eName="companySeq",field="company_seq")
	private String companySeq="";
	  
	@FieldTag(cName="保险名称",eName="insuranceName",field="insurance_name")
	private String insuranceName="";
	
	@FieldTag(cName="保险费",eName="premium",field="premium")
	private Double premium=0.00;
	
	@FieldTag(cName="意外伤害险",eName="amountOne",field="amount_one")
	private Double amountOne=0.00;
	
	@FieldTag(cName="意外医疗险",eName="amountTwo",field="amount_two")
	private Double amountTwo=0.00;
	
	@FieldTag(cName="废票方式",eName="invalidType",field="invalid_type")
	private String invalidType="";
	  
	@FieldTag(cName="废票员",eName="invalidUser",field="invalid_user")
	private String invalidUser="";
	  
	@FieldTag(cName="废票员姓名",eName="invalidUsername",field="invalid_username")
	private String invalidUsername="";
	  
	@FieldTag(cName="废票机构号",eName="invalidOrganize",field="invalid_organize")
	private String invalidOrganize="";
	  
	@FieldTag(cName="废票机构名称",eName="invalidOrganizename",field="invalid_organizename")
	private String invalidOrganizename="";
	  
	@FieldTag(cName="废票点",eName="invalidService",field="invalid_service")
	private String invalidService="";
	  
	@FieldTag(cName="废票点名称",eName="invalidServicename",field="invalid_servicename")
	private String invalidServicename="";
	
	@FieldTag(cName="废票时间",eName="invalidTime",field="invalid_time")
	private String invalidTime="";
	
	@FieldTag(cName="交款序号",eName="paymentSeq",field="payment_seq")
	private String paymentSeq="";
	  
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark;
	  
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	public InsInvalidticket() {
	}

	public String getInvalidticketSeq() {
		return invalidticketSeq;
	}

	public void setInvalidticketSeq(String invalidticketSeq) {
		this.invalidticketSeq = invalidticketSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getTicketSeq() {
		return ticketSeq;
	}

	public void setTicketSeq(String ticketSeq) {
		this.ticketSeq = ticketSeq;
	}

	public String getInsuranceCode() {
		return insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getCompanySeq() {
		return companySeq;
	}

	public void setCompanySeq(String companySeq) {
		this.companySeq = companySeq;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
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

	public String getInvalidType() {
		return invalidType;
	}

	public void setInvalidType(String invalidType) {
		this.invalidType = invalidType;
	}

	public String getInvalidUser() {
		return invalidUser;
	}

	public void setInvalidUser(String invalidUser) {
		this.invalidUser = invalidUser;
	}

	public String getInvalidUsername() {
		return invalidUsername;
	}

	public void setInvalidUsername(String invalidUsername) {
		this.invalidUsername = invalidUsername;
	}

	public String getInvalidOrganize() {
		return invalidOrganize;
	}

	public void setInvalidOrganize(String invalidOrganize) {
		this.invalidOrganize = invalidOrganize;
	}

	public String getInvalidOrganizename() {
		return invalidOrganizename;
	}

	public void setInvalidOrganizename(String invalidOrganizename) {
		this.invalidOrganizename = invalidOrganizename;
	}

	public String getInvalidService() {
		return invalidService;
	}

	public void setInvalidService(String invalidService) {
		this.invalidService = invalidService;
	}

	public String getInvalidServicename() {
		return invalidServicename;
	}

	public void setInvalidServicename(String invalidServicename) {
		this.invalidServicename = invalidServicename;
	}

	public String getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}

	public String getPaymentSeq() {
		return paymentSeq;
	}

	public void setPaymentSeq(String paymentSeq) {
		this.paymentSeq = paymentSeq;
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
