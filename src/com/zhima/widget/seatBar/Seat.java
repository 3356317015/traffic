/**
 *******************************************************************************
 * 
 * (c) Copyright 2011 合智智能交通有限责任公司
 *
 * 文 件  名：Seats.java
 * 系统名称：kylwsp
 * 模块名称：(请更改成该模块名称)
 * 创 建  人：黄辉勇
 * 创建日期：2011-4-10 下午04:39:20
 * 修 改 人：黄辉勇
 * 修改日期：(请填上修改该文件时的日期)  
 * 版     本： V1.0.0
 *******************************************************************************  
 */
package com.zhima.widget.seatBar;

public class Seat {
	// 座位序号
	private String seatSeq = "";
	// 座位号
	private String seatId = "";
	// 座位状态(0禁售,1可售,2已售,3预留,4配载)
	private Integer seatState = 1;
	// 票据状态(0作废,1未检,2已检,3退票)
	private Integer ticketStates = 1;
	// 售票方式(1本站售,2终端售,3异站售,4代售点,5网售、6手机售)
	private String stationName = "";
	private String faretypeName = "";
	private String ticketId = "";
	private Double ticketFare = 0.0;
	private String customer = "";
	private String telephone = "";
	private String saleType = "";
	private String saleUsername = "";
	private String saleOrganizename = "";
	private String saleServicename = "";
	private String saleTime = "";
	private String checkUsername = "";
	private String checkTime = "";
	
	public Seat(){
		
	}

	public String getSeatSeq() {
		return seatSeq;
	}

	public void setSeatSeq(String seatSeq) {
		this.seatSeq = seatSeq;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public Integer getSeatState() {
		return seatState;
	}

	public void setSeatState(Integer seatState) {
		this.seatState = seatState;
	}

	public Integer getTicketStates() {
		return ticketStates;
	}

	public void setTicketStates(Integer ticketStates) {
		this.ticketStates = ticketStates;
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

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
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
