package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="退票记录表",eName="ItsReturnticket",tabName="ins_returnticket")
public class InsReturnticket implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="废票序号",eName="returnticketSeq",field="returnticket_seq",pk=true)
	private String returnticketSeq="";
	
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
	
	@FieldTag(cName="废票方式",eName="returnType",field="return_type")
	private String returnType="";
	  
	@FieldTag(cName="废票员",eName="returnUser",field="return_user")
	private String returnUser="";
	  
	@FieldTag(cName="废票员姓名",eName="returnUsername",field="return_username")
	private String returnUsername="";
	  
	@FieldTag(cName="废票机构号",eName="returnOrganize",field="return_organize")
	private String returnOrganize="";
	  
	@FieldTag(cName="废票机构名称",eName="returnOrganizename",field="return_organizename")
	private String returnOrganizename="";
	  
	@FieldTag(cName="废票点",eName="returnService",field="return_service")
	private String returnService="";
	  
	@FieldTag(cName="废票点名称",eName="returnServicename",field="return_servicename")
	private String returnServicename="";
	
	@FieldTag(cName="废票时间",eName="invalidTime",field="return_time")
	private String returnTime="";
	
	@FieldTag(cName="交款序号",eName="paymentSeq",field="payment_seq")
	private String paymentSeq="";
	  
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark;
	  
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	public InsReturnticket() {
	}

	public String getReturnticketSeq() {
		return returnticketSeq;
	}

	public void setReturnticketSeq(String returnticketSeq) {
		this.returnticketSeq = returnticketSeq;
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

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getReturnUser() {
		return returnUser;
	}

	public void setReturnUser(String returnUser) {
		this.returnUser = returnUser;
	}

	public String getReturnUsername() {
		return returnUsername;
	}

	public void setReturnUsername(String returnUsername) {
		this.returnUsername = returnUsername;
	}

	public String getReturnOrganize() {
		return returnOrganize;
	}

	public void setReturnOrganize(String returnOrganize) {
		this.returnOrganize = returnOrganize;
	}

	public String getReturnOrganizename() {
		return returnOrganizename;
	}

	public void setReturnOrganizename(String returnOrganizename) {
		this.returnOrganizename = returnOrganizename;
	}

	public String getReturnService() {
		return returnService;
	}

	public void setReturnService(String returnService) {
		this.returnService = returnService;
	}

	public String getReturnServicename() {
		return returnServicename;
	}

	public void setReturnServicename(String returnServicename) {
		this.returnServicename = returnServicename;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
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
