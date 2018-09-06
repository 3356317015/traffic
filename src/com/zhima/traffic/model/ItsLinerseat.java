package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="班次席位",eName="ItsLinerseat",tabName="its_linerseat")
public class ItsLinerseat implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="班次席位序号",eName="linerseatSeq",field="linerseat_seq",pk=true)
	private String linerseatSeq="";
	
	@FieldTag(cName="班次序号",eName="linerSeq",field="liner_seq")
	private String linerSeq="";
	
	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";

	@FieldTag(cName="席位号",eName="seatId",field="seat_id")
	private Integer seatId=0;
	
	@FieldTag(cName="席位状态",eName="seatStatus",field="seat_status")
	private Integer seatStatus=0;
	
	@FieldTag(cName="席位类别",eName="seatType",field="seat_type")
	private Integer seatType=0;
	
	@FieldTag(cName="票据序号",eName="ticketSeq",field="ticket_seq")
	private String ticketSeq="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="票号",eName="ticketId",field="ticket_id",db=false)
	private String ticketId="";
	
	@FieldTag(cName="目的地",eName="stationName",field="station_name",db=false)
	private String stationName = "";
	
	@FieldTag(cName="票型",eName="faretypeName",field="faretype_name",db=false)
	private String faretypeName = "";
	
	@FieldTag(cName="票价",eName="ticketFare",field="ticket_fare",db=false)
	private Double ticketFare = 0.0;
	
	@FieldTag(cName="乘客姓名",eName="customer",field="customer",db=false)
	private String customer = "";
	
	@FieldTag(cName="乘客电话",eName="telephone",field="telephone",db=false)
	private String telephone = "";
	
	@FieldTag(cName="票据状态",eName="ticketStatus",field="ticket_status",db=false)
	private Integer ticketStatus=0;
	
	@FieldTag(cName="销售方式",eName="saleType",field="sale_type",db=false)
	private String saleType = "";
	
	@FieldTag(cName="售票员姓名",eName="saleUsername",field="sale_username",db=false)
	private String saleUsername="";
	
	@FieldTag(cName="售票机构名",eName="saleOrganizename",field="sale_organizename",db=false)
	private String saleOrganizename="";
	
	@FieldTag(cName="售票点名称",eName="saleServicename",field="sale_servicename",db=false)
	private String saleServicename="";

	@FieldTag(cName="销售时间",eName="saleTime",field="sale_time",db=false)
	private String saleTime = "";
	
	@FieldTag(cName="检票员姓名",eName="checkUsername",field="check_username",db=false)
	private String checkUsername = "";
	
	@FieldTag(cName="检票时间",eName="checkTime",field="check_time",db=false)
	private String checkTime = "";

	// Constructors

	/** default constructor */
	public ItsLinerseat() {
	}
	

	/** full constructor */
	
	// Property accessors

	public String getLinerseatSeq() {
		return linerseatSeq;
	}

	public void setLinerseatSeq(String linerseatSeq) {
		this.linerseatSeq = linerseatSeq;
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

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Integer getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(Integer seatStatus) {
		this.seatStatus = seatStatus;
	}

	public Integer getSeatType() {
		return seatType;
	}

	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}

	public String getTicketSeq() {
		return ticketSeq;
	}

	public void setTicketSeq(String ticketSeq) {
		this.ticketSeq = ticketSeq;
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

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getFaretypeName() {
		return faretypeName;
	}

	public void setFaretypeName(String faretypeName) {
		this.faretypeName = faretypeName;
	}

	public Double getTicketFare() {
		return ticketFare;
	}

	public void setTicketFare(Double ticketFare) {
		this.ticketFare = ticketFare;
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

	public Integer getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(Integer ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getSaleUsername() {
		return saleUsername;
	}

	public void setSaleUsername(String saleUsername) {
		this.saleUsername = saleUsername;
	}

	public String getSaleOrganizename() {
		return saleOrganizename;
	}

	public void setSaleOrganizename(String saleOrganizename) {
		this.saleOrganizename = saleOrganizename;
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
	
	
}