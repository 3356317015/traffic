package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="退票凭证",eName="ItsReturnbill",tabName="its_returnbill")
public class ItsReturnbill implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="退票凭证序号",eName="returnbillSeq",field="returnbill_seq",pk=true)
	private String returnbillSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="票据号",eName="ticketId",field="ticket_id")
	private String ticketId="";
	
	@FieldTag(cName="退票数量",eName="returnNum",field="return_num")
	private Integer returnNum=0;
	
	@FieldTag(cName="票价金额",eName="ticketFare",field="ticket_fare")
	private Double ticketFare=0.0;
	
	@FieldTag(cName="退票金额",eName="returnFare",field="return_fare")
	private Double returnFare=0.0;
	
	@FieldTag(cName="手续费",eName="handFare",field="hand_fare")
	private Double handFare=0.0;
	
	@FieldTag(cName="退票员",eName="returnUser",field="return_user")
	private String returnUser="";
	
	@FieldTag(cName="退票员姓名",eName="returnUsername",field="return_username")
	private String returnUsername="";
	
	@FieldTag(cName="退票时间",eName="returnTime",field="return_time")
	private String returnTime="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public ItsReturnbill() {
	}
	
	/** full constructor */

	// Property accessors

	public String getReturnbillSeq() {
		return returnbillSeq;
	}

	public void setReturnbillSeq(String returnbillSeq) {
		this.returnbillSeq = returnbillSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public Integer getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}

	public Double getTicketFare() {
		return ticketFare;
	}

	public void setTicketFare(Double ticketFare) {
		this.ticketFare = ticketFare;
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

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
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