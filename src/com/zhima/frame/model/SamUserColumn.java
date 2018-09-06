package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="用户自定义列",eName="SamUserColumn",tabName="sam_user_column")
public class SamUserColumn implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="自定义列序号",eName="userColumnSeq",field="user_column_seq",pk=true)
	private String userColumnSeq="";
	
	@FieldTag(cName="用户序号",eName="userSeq",field="user_seq")
	private String userSeq="";
	
	@FieldTag(cName="类名",eName="className",field="class_name")
	private String className="";
	
	@FieldTag(cName="表名",eName="gridName",field="grid_name")
	private String gridName="";
	
	@FieldTag(cName="列名",eName="columnEname",field="column_ename")
	private String columnEname="";
	
	@FieldTag(cName="列名称",eName="columnCname",field="column_cname")
	private String columnCname="";
	
	@FieldTag(cName="列宽",eName="width",field="width")
	private Integer width=0;
	
	@FieldTag(cName="顺序",eName="sn",field="sn")
	private String sn="";
	
	@FieldTag(cName="对齐方式",eName="alignment",field="alignment")
	private String alignment="";
	
	// Constructors
	/** default constructor */
	public SamUserColumn() {
	}
	
	/** full constructor */		
	public SamUserColumn(String userColumnSeq, String userSeq,
			String className, String gridName, String columnEname,
			String columnCname, Integer width, String sn, String alignment) {
		super();
		this.userColumnSeq = userColumnSeq;
		this.userSeq = userSeq;
		this.className = className;
		this.gridName = gridName;
		this.columnEname = columnEname;
		this.columnCname = columnCname;
		this.width = width;
		this.sn = sn;
		this.alignment = alignment;
	}

	// Property accessors
	public String getUserColumnSeq() {
		return userColumnSeq;
	}

	public void setUserColumnSeq(String userColumnSeq) {
		this.userColumnSeq = userColumnSeq;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getGridName() {
		return gridName;
	}

	public void setGridName(String gridName) {
		this.gridName = gridName;
	}

	public String getColumnEname() {
		return columnEname;
	}

	public void setColumnEname(String columnEname) {
		this.columnEname = columnEname;
	}

	public String getColumnCname() {
		return columnCname;
	}

	public void setColumnCname(String columnCname) {
		this.columnCname = columnCname;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	
}