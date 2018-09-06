package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="打印设置",eName="SamPrintset",tabName="sam_printset")
public class SamPrintsets implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="打印设置主键",eName="printsetSeq",field="printset_seq",pk=true)
	private String printsetSeq="";
	
	@FieldTag(cName="打印设置名称",eName="printsetName",field="printset_name")
	private String printsetName="";
	
	@FieldTag(cName="打印设置类型",eName="printsetType",field="printset_type")
	private Integer printsetType =0;
	
	@FieldTag(cName="纸张高度",eName="paperHeight",field="paper_height")
	private Double paperHeight =0.0;
	
	@FieldTag(cName="纸张宽度",eName="paperWidth",field="paper_width")
	private Double paperWidth =0.0;
	
	@FieldTag(cName="打印型号",eName="printModel",field="print_model")
	private String printModel="";
	
	@FieldTag(cName="票据类型",eName="ticketType",field="ticket_type")
	private String ticketType="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public SamPrintsets(){
		
	}
	
	// Property accessors
	public String getPrintsetSeq() {
		return printsetSeq;
	}

	public void setPrintsetSeq(String printsetSeq) {
		this.printsetSeq = printsetSeq;
	}

	public String getPrintsetName() {
		return printsetName;
	}

	public void setPrintsetName(String printsetName) {
		this.printsetName = printsetName;
	}

	public Integer getPrintsetType() {
		return printsetType;
	}

	public void setPrintsetType(Integer printsetType) {
		this.printsetType = printsetType;
	}

	public Double getPaperHeight() {
		return paperHeight;
	}

	public void setPaperHeight(Double paperHeight) {
		this.paperHeight = paperHeight;
	}

	public Double getPaperWidth() {
		return paperWidth;
	}

	public void setPaperWidth(Double paperWidth) {
		this.paperWidth = paperWidth;
	}

	public String getPrintModel() {
		return printModel;
	}

	public void setPrintModel(String printModel) {
		this.printModel = printModel;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
