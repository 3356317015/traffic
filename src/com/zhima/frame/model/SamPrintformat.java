package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="打印格式",eName="SamPrintformat",tabName="sam_printformat")
public class SamPrintformat implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="打印格式序号",eName="printformatSeq",field="printformat_seq",pk=true)
	private String printformatSeq=""; 
	
	@FieldTag(cName="打印设置序号",eName="printsetSeq",field="printset_seq")
	private String printsetSeq="";
	
	@FieldTag(cName="打印项",eName="printItem",field="print_item")
	private String printItem="";
	
	@FieldTag(cName="打印内容",eName="printName",field="print_name")
	private String printName="";
	
	@FieldTag(cName="字体大小",eName="fontSize",field="font_size")
	private Integer fontSize=0;
	
	@FieldTag(cName="字体加粗",eName="fontBold",field="font_bold")
	private Integer fontBold=0;
	
	@FieldTag(cName="字体对齐方式",eName="fontAlign",field="font_align")
	private Integer fontAlign=0;
	
	@FieldTag(cName="打印垂直坐标",eName="printTop",field="print_top")
	private Double printTop=0.0;   
	
	@FieldTag(cName="打印水平坐标",eName="printLeft",field="print_left")
	private Double printLeft=0.0;   
	
	@FieldTag(cName="打印项高度",eName="printHeight",field="print_height")
	private Double printHeight=0.0;   
	
	@FieldTag(cName="打印项宽度",eName="printWidth",field="print_width")
	private Double printWidth=0.0; 
	
	@FieldTag(cName="打印循环",eName="printLoop",field="print_loop")
	private Integer printLoop=0;
	
	@FieldTag(cName="打印间距",eName="pirntSpacing",field="pirnt_spacing")
	private Double pirntSpacing=0.0;
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	
	// Constructors

	/** default constructor */
	public SamPrintformat(){
		
	}

	// Property accessors

	public String getPrintformatSeq() {
		return printformatSeq;
	}


	public void setPrintformatSeq(String printformatSeq) {
		this.printformatSeq = printformatSeq;
	}


	public String getPrintsetSeq() {
		return printsetSeq;
	}


	public void setPrintsetSeq(String printsetSeq) {
		this.printsetSeq = printsetSeq;
	}


	public String getPrintItem() {
		return printItem;
	}


	public void setPrintItem(String printItem) {
		this.printItem = printItem;
	}


	public String getPrintName() {
		return printName;
	}


	public void setPrintName(String printName) {
		this.printName = printName;
	}


	public Integer getFontSize() {
		return fontSize;
	}


	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}


	public Integer getFontBold() {
		return fontBold;
	}


	public void setFontBold(Integer fontBold) {
		this.fontBold = fontBold;
	}


	public Integer getFontAlign() {
		return fontAlign;
	}


	public void setFontAlign(Integer fontAlign) {
		this.fontAlign = fontAlign;
	}


	public Double getPrintTop() {
		return printTop;
	}


	public void setPrintTop(Double printTop) {
		this.printTop = printTop;
	}


	public Double getPrintLeft() {
		return printLeft;
	}


	public void setPrintLeft(Double printLeft) {
		this.printLeft = printLeft;
	}


	public Double getPrintHeight() {
		return printHeight;
	}


	public void setPrintHeight(Double printHeight) {
		this.printHeight = printHeight;
	}


	public Double getPrintWidth() {
		return printWidth;
	}


	public void setPrintWidth(Double printWidth) {
		this.printWidth = printWidth;
	}


	public Integer getPrintLoop() {
		return printLoop;
	}


	public void setPrintLoop(Integer printLoop) {
		this.printLoop = printLoop;
	}


	public Double getPirntSpacing() {
		return pirntSpacing;
	}


	public void setPirntSpacing(Double pirntSpacing) {
		this.pirntSpacing = pirntSpacing;
	}


	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
