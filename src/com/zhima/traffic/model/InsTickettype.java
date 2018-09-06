package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="票据类型",eName="InsTickettype",tabName="ins_tickettype")
public class InsTickettype implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="票据类型序号",eName="tickettypeSeq",field="tickettype_seq",pk=true)
	private String tickettypeSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="公司序号",eName="companySeq",field="company_seq")
	private String companySeq="";
	
	@FieldTag(cName="票据名称",eName="ticketName",field="ticket_name")
	private String ticketName="";
	
	@FieldTag(cName="总份数",eName="ticketNum",field="ticket_num")
	private Integer ticketNum=0;
	
	@FieldTag(cName="每份页数",eName="ticketPages",field="ticket_pages")
	private Integer ticketPages=0;
	
	@FieldTag(cName="票据宽度",eName="ticketWidth",field="ticket_width")
	private Integer ticketWidth=0;
	
	@FieldTag(cName="票据高度",eName="ticketHeight",field="ticket_height")
	private Integer ticketHeight=0;
	
	@FieldTag(cName="票据图片",eName="ticketImage",field="ticket_image")
	private String ticketImage="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="保险代码",eName="insuranceCode",field="insurance_code",db=false)
	private String insuranceCode="";
	
	@FieldTag(cName="保险名称",eName="insuranceName",field="insurance_name",db=false)
	private String insuranceName="";

	public String getTickettypeSeq() {
		return tickettypeSeq;
	}

	public void setTickettypeSeq(String tickettypeSeq) {
		this.tickettypeSeq = tickettypeSeq;
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

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public Integer getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Integer getTicketPages() {
		return ticketPages;
	}

	public void setTicketPages(Integer ticketPages) {
		this.ticketPages = ticketPages;
	}

	public Integer getTicketWidth() {
		return ticketWidth;
	}

	public void setTicketWidth(Integer ticketWidth) {
		this.ticketWidth = ticketWidth;
	}

	public Integer getTicketHeight() {
		return ticketHeight;
	}

	public void setTicketHeight(Integer ticketHeight) {
		this.ticketHeight = ticketHeight;
	}

	public String getTicketImage() {
		return ticketImage;
	}

	public void setTicketImage(String ticketImage) {
		this.ticketImage = ticketImage;
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