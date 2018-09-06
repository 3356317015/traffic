package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="表格秘密列",eName="SamUserSecret",tabName="sam_user_secret")
public class SamUserSecret implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="秘密列序号",eName="userSecretSeq",field="user_secret_seq",pk=true)
	private String userSecretSeq="";
	
	@FieldTag(cName="用户序号",eName="userSeq",field="user_seq")
	private String userSeq="";
	
	@FieldTag(cName="类名",eName="className",field="class_name")
	private String className="";
	
	@FieldTag(cName="表名",eName="gridName",field="grid_name")
	private String gridName="";
	
	@FieldTag(cName="列名",eName="columnEname",field="column_ename")
	private String columnEname="";
	
	// Constructors
	/** default constructor */
	public SamUserSecret() {
	}
	
	/** full constructor */		
	public SamUserSecret(String userSecretSeq, String userSeq,
			String className, String gridName, String columnEname) {
		super();
		this.userSecretSeq = userSecretSeq;
		this.userSeq = userSeq;
		this.className = className;
		this.gridName = gridName;
		this.columnEname = columnEname;
	}
	
	// Property accessors
	public String getUserSecretSeq() {
		return userSecretSeq;
	}

	public void setUserSecretSeq(String userSecretSeq) {
		this.userSecretSeq = userSecretSeq;
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

	
}