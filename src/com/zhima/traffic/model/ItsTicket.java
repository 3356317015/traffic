package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="票据表",eName="ItsTicket",tabName="its_ticket")
public class ItsTicket implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="票据序号",eName="ticketSeq",field="ticket_seq",pk=true)
	private String ticketSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="订单号",eName="orderNo",field="order_no")
	private String orderNo="";
	
	@FieldTag(cName="事务号",eName="transId",field="trans_id")
	private String transId="";
	
	@FieldTag(cName="发车站代码",eName="startOrganize",field="start_organize")
	private String startOrganize="";
	
	@FieldTag(cName="发车站名称",eName="startOrganizename",field="start_organizename")
	private String startOrganizename="";
	
	@FieldTag(cName="乘车点序号",eName="serviceSeq",field="service_seq")
	private String serviceSeq="";
	
	@FieldTag(cName="乘车点名称",eName="serviceName",field="service_name")
	private String serviceName="";
	
	@FieldTag(cName="发票代码",eName="invoiceCode",field="invoice_code")
	private String invoiceCode="";
	
	@FieldTag(cName="发票号码",eName="taxCode",field="tax_code")
	private String taxCode="";
	
	@FieldTag(cName="票据号",eName="ticketId",field="ticket_id")
	private String ticketId="";
	
	@FieldTag(cName="班次序号",eName="linerSeq",field="liner_seq")
	private String linerSeq="";

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
	
	@FieldTag(cName="票型序号",eName="faretypeSeq",field="faretype_seq")
	private String faretypeSeq="";
	
	@FieldTag(cName="票型名称",eName="faretypeName",field="faretype_name")
	private String faretypeName="";
	
	@FieldTag(cName="客户名称",eName="customer",field="customer")
	private String customer="";
	
	@FieldTag(cName="联系电话",eName="telephone",field="telephone")
	private String telephone="";
	
	@FieldTag(cName="证件类型",eName="cerType",field="cer_type")
	private String cerType="";
	
	@FieldTag(cName="证件号码",eName="idNumber",field="id_number")
	private String idNumber="";
	
	@FieldTag(cName="票价",eName="ticketFare",field="ticket_fare")
	private Double ticketFare=0.00;
	
	@FieldTag(cName="站务费",eName="stationFee",field="station_fee")
	private Double stationFee=0.00;
	
	@FieldTag(cName="燃油费",eName="fuelFee",field="fuel_fee")
	private Double fuelFee=0.00;

	@FieldTag(cName="票据张数",eName="ticketNum",field="ticket_num")
	private Integer ticketNum=0;
	
	//票据类型0单票1团体票
	@FieldTag(cName="票据类型",eName="ticketType",field="ticket_type")
	private Integer ticketType=1;
	
	//票据状态0作废1售票2检票3退票
	@FieldTag(cName="票据状态",eName="ticketStatus",field="ticket_status")
	private Integer ticketStatus=0;

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
	
	@FieldTag(cName="检票口名称",eName="checkName",field="check_name")
	private String checkName="";
	
	@FieldTag(cName="登车口名称",eName="gateName",field="gate_name")
	private String gateName="";
	
	@FieldTag(cName="检票方式",eName="checkType",field="check_type")
	private String checkType="";
	
	@FieldTag(cName="检票车辆",eName="checkCar",field="check_car")
	private String checkCar="";
	
	@FieldTag(cName="检票员ID",eName="checkUser",field="check_user")
	private String checkUser="";
	
	@FieldTag(cName="检票员姓名",eName="checkUsername",field="check_username")
	private String checkUsername="";
	
	@FieldTag(cName="检票时间",eName="checkTime",field="check_time")
	private String checkTime="";
	
	@FieldTag(cName="交款号",eName="paymentSeq",field="payment_seq")
	private String paymentSeq="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="售票类型",eName="saleType",field="sale_type",db=false)
	private String saleType="";

	// Constructors

	/** default constructor */
	public ItsTicket() {
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
	
	public String getServiceSeq() {
		return serviceSeq;
	}

	public void setServiceSeq(String serviceSeq) {
		this.serviceSeq = serviceSeq;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
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

	public String getFaretypeSeq() {
		return faretypeSeq;
	}

	public void setFaretypeSeq(String faretypeSeq) {
		this.faretypeSeq = faretypeSeq;
	}

	public String getFaretypeName() {
		return faretypeName;
	}

	public void setFaretypeName(String faretypeName) {
		this.faretypeName = faretypeName;
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

	public Double getTicketFare() {
		return ticketFare;
	}

	public void setTicketFare(Double ticketFare) {
		this.ticketFare = ticketFare;
	}

	public Double getStationFee() {
		return stationFee;
	}

	public void setStationFee(Double stationFee) {
		this.stationFee = stationFee;
	}

	public Double getFuelFee() {
		return fuelFee;
	}

	public void setFuelFee(Double fuelFee) {
		this.fuelFee = fuelFee;
	}

	public Integer getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Integer getTicketType() {
		return ticketType;
	}

	public void setTicketType(Integer ticketType) {
		this.ticketType = ticketType;
	}

	public Integer getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(Integer ticketStatus) {
		this.ticketStatus = ticketStatus;
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

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCheckCar() {
		return checkCar;
	}

	public void setCheckCar(String checkCar) {
		this.checkCar = checkCar;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getCheckUsername() {
		return checkUsername;
	}

	public void setCheckUsername(String checkUsername) {
		this.checkUsername = checkUsername;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
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

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

}