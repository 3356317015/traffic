package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="票据表",eName="InsTicket",tabName="ins_ticket")
public class InsTicket implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="票据序号",eName="ticketSeq",field="ticket_seq",pk=true)
	private String ticketSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="订单号",eName="orderNo",field="order_no")
	private String orderNo="";
	
	@FieldTag(cName="事务号",eName="transId",field="trans_id")
	private String transId="";
	
	@FieldTag(cName="保险号",eName="insuranceCode",field="insurance_code")
	private String insuranceCode="";
	
	@FieldTag(cName="公司序号",eName="companySeq",field="company_seq")
	private String companySeq="";
	
	@FieldTag(cName="保险名称",eName="insuranceName",field="insurance_name")
	private String insuranceName="";
	
	@FieldTag(cName="保险费",eName="premium",field="premium")
	private Double premium=0.00;
	
	@FieldTag(cName="意外伤害险",eName="amountOne",field="amount_one")
	private Double amountOne=0.00;
	
	@FieldTag(cName="意外医疗险",eName="amountTwo",field="amount_two")
	private Double amountTwo=0.00;
	
	@FieldTag(cName="支付方式",eName="payMode",field="pay_mode")
	private String payMode="";
	
	@FieldTag(cName="客户名称",eName="customer",field="customer")
	private String customer="";
	
	@FieldTag(cName="联系电话",eName="telephone",field="telephone")
	private String telephone="";
	
	@FieldTag(cName="证件类型",eName="cerType",field="cer_type")
	private String cerType="";
	
	@FieldTag(cName="证件号码",eName="idNumber",field="id_number")
	private String idNumber="";
	
	@FieldTag(cName="发车站代码",eName="startOrganize",field="start_organize")
	private String startOrganize="";
	
	@FieldTag(cName="发车站名称",eName="startOrganizename",field="start_organizename")
	private String startOrganizename="";

	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";
	
	@FieldTag(cName="座位号",eName="seatId",field="seat_id")
	private String seatId="";
	
	@FieldTag(cName="发车时间",eName="linerTime",field="liner_time")
	private String linerTime="";
	
	@FieldTag(cName="班次类型",eName="linerType",field="liner_type")
	private String linerType="";
	
	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="线路名称",eName="routeName",field="route_name")
	private String routeName="";
	
	@FieldTag(cName="站点序号",eName="stationSeq",field="station_seq")
	private String stationSeq="";
	
	@FieldTag(cName="站点名称",eName="stationName",field="station_name")
	private String stationName="";
	
	//票据类型0单票1团体票
	@FieldTag(cName="票据类型",eName="ticketType",field="ticket_type")
	private Integer ticketType=1;
	
	@FieldTag(cName="票型名称",eName="faretypeName",field="faretype_name")
	private String faretypeName="";

	@FieldTag(cName="票价",eName="ticketFare",field="ticket_fare")
	private String ticketFare="";

	@FieldTag(cName="售票类型",eName="saleType",field="sale_type")
	private String saleType="";

	@FieldTag(cName="售票员",eName="saleUser",field="sale_user")
	private String saleUser="";

	@FieldTag(cName="售票员姓名",eName="saleUsername",field="sale_username")
	private String saleUsername="";

	@FieldTag(cName="售票机构",eName="saleOrganize",field="sale_organize")
	private String saleOrganize="";

	@FieldTag(cName="售票机构名",eName="saleOrganizename",field="sale_organizename")
	private String saleOrganizename="";
	
	@FieldTag(cName="售票点",eName="saleService",field="sale_service")
	private String saleService="";
	
	@FieldTag(cName="售票点名称",eName="saleServicename",field="sale_servicename")
	private String saleServicename="";
	
	@FieldTag(cName="售票时间",eName="saleTime",field="sale_time")
	private String saleTime="";
	
	//票据状态0作废1售票2检票3退票
	@FieldTag(cName="票据状态",eName="insuranceStatus",field="insurance_status")
	private Integer insuranceStatus=0;
	
	@FieldTag(cName="交款号",eName="paymentSeq",field="payment_seq")
	private String paymentSeq="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public InsTicket() {
	}

	public String getTicketSeq() {
		return ticketSeq;
	}

	public void setTicketSeq(String ticketSeq) {
		this.ticketSeq = ticketSeq;
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

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCerType() {
		return cerType;
	}

	public void setCerType(String cerType) {
		this.cerType = cerType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getStartOrganize() {
		return startOrganize;
	}

	public void setStartOrganize(String startOrganize) {
		this.startOrganize = startOrganize;
	}

	public String getStartOrganizename() {
		return startOrganizename;
	}

	public void setStartOrganizename(String startOrganizename) {
		this.startOrganizename = startOrganizename;
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

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getLinerTime() {
		return linerTime;
	}

	public void setLinerTime(String linerTime) {
		this.linerTime = linerTime;
	}

	public String getLinerType() {
		return linerType;
	}

	public void setLinerType(String linerType) {
		this.linerType = linerType;
	}

	public String getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(String routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getStationSeq() {
		return stationSeq;
	}

	public void setStationSeq(String stationSeq) {
		this.stationSeq = stationSeq;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Integer getTicketType() {
		return ticketType;
	}

	public void setTicketType(Integer ticketType) {
		this.ticketType = ticketType;
	}

	public String getFaretypeName() {
		return faretypeName;
	}

	public void setFaretypeName(String faretypeName) {
		this.faretypeName = faretypeName;
	}

	public String getTicketFare() {
		return ticketFare;
	}

	public void setTicketFare(String ticketFare) {
		this.ticketFare = ticketFare;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getSaleUser() {
		return saleUser;
	}

	public void setSaleUser(String saleUser) {
		this.saleUser = saleUser;
	}

	public String getSaleUsername() {
		return saleUsername;
	}

	public void setSaleUsername(String saleUsername) {
		this.saleUsername = saleUsername;
	}

	public String getSaleOrganize() {
		return saleOrganize;
	}

	public void setSaleOrganize(String saleOrganize) {
		this.saleOrganize = saleOrganize;
	}

	public String getSaleOrganizename() {
		return saleOrganizename;
	}

	public void setSaleOrganizename(String saleOrganizename) {
		this.saleOrganizename = saleOrganizename;
	}

	public String getSaleService() {
		return saleService;
	}

	public void setSaleService(String saleService) {
		this.saleService = saleService;
	}

	public String getSaleServicename() {
		return saleServicename;
	}

	public void setSaleServicename(String saleServicename) {
		this.saleServicename = saleServicename;
	}

	public String getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}

	public Integer getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(Integer insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
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