package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="退票表",eName="ItsReturnticket",tabName="its_returnticket")
public class ItsReturnticket implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="退票序号",eName="returnticketSeq",field="returnticket_seq",pk=true)
	private String returnticketSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="订单号",eName="orderNo",field="order_no")
	private String orderNo="";
	
	@FieldTag(cName="事务号",eName="transId",field="trans_id")
	private String transId="";
	
	@FieldTag(cName="退票单序号",eName="returnbillSeq",field="returnbill_seq")
	private String returnbillSeq="";
	
	@FieldTag(cName="票据序号",eName="ticketSeq",field="ticket_seq")
	private String ticketSeq="";
	
	@FieldTag(cName="票号",eName="ticketCode",field="ticket_code")
	private String ticketCode="";
	
	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";
	
	@FieldTag(cName="座位号",eName="seatId",field="seat_id")
	private Integer seatId; 
	
	@FieldTag(cName="票价",eName="ticketFare",field="ticket_fare")
	private Double ticketFare=0.0;
	
	@FieldTag(cName="退票比例",eName="returnRate",field="return_rate")
	private Double returnRate=0.0;
	
	@FieldTag(cName="退款",eName="returnFare",field="return_fare")
	private Double returnFare=0.0;
	
	@FieldTag(cName="手续费",eName="handFare",field="hand_fare")
	private Double handFare=0.0;
	
	@FieldTag(cName="退票类别",eName="returnType",field="return_type")
	private String returnType="";
	
	@FieldTag(cName="退票员",eName="returnUser",field="return_user")
	private String returnUser="";
	
	@FieldTag(cName="退票员姓名",eName="returnUsername",field="return_username")
	private String returnUsername="";

	@FieldTag(cName="退票机构",eName="returnOrganize",field="return_organize")
	private String returnOrganize="";
	
	@FieldTag(cName="退票机构名称",eName="returnOrganizename",field="return_organizename")
	private String returnOrganizename="";
	
	@FieldTag(cName="退票点",eName="returnService",field="return_service")
	private String returnService="";
	
	@FieldTag(cName="退票点名称",eName="returnServicename",field="return_servicename")
	private String returnServicename="";
	
	@FieldTag(cName="退票时间",eName="returnTime",field="return_time")
	private String returnTime="";
	
	@FieldTag(cName="交款号",eName="paymentSeq",field="payment_seq")
	private String paymentSeq="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="最后修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors
	
	public ItsReturnticket() {
	}

	/** default constructor */
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

	public String getReturnbillSeq() {
		return returnbillSeq;
	}

	public void setReturnbillSeq(String returnbillSeq) {
		this.returnbillSeq = returnbillSeq;
	}

	public String getTicketSeq() {
		return ticketSeq;
	}

	public void setTicketSeq(String ticketSeq) {
		this.ticketSeq = ticketSeq;
	}

	public String getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
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

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Double getTicketFare() {
		return ticketFare;
	}

	public void setTicketFare(Double ticketFare) {
		this.ticketFare = ticketFare;
	}

	public Double getReturnRate() {
		return returnRate;
	}

	public void setReturnRate(Double returnRate) {
		this.returnRate = returnRate;
	}

	public Double getReturnFare() {
		return returnFare;
	}

	public void setReturnFare(Double returnFare) {
		this.returnFare = returnFare;
	}

	public Double getHandFare() {
		return handFare;
	}

	public void setHandFare(Double handFare) {
		this.handFare = handFare;
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