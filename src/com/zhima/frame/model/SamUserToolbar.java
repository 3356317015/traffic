package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="用户自定义列",eName="SamUserToolbar",tabName="sam_user_toolbar")
public class SamUserToolbar implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="工具列列序号",eName="toolbarSeq",field="toolbar_seq",pk=true)
	private String toolbarSeq="";
	
	@FieldTag(cName="用户序号",eName="userSeq",field="user_seq")
	private String userSeq="";
	
	@FieldTag(cName="模块序号",eName="moduleSeq",field="module_seq")
	private String moduleSeq="";
	
	// Constructors
	/** default constructor */
	public SamUserToolbar() {
	}
	
	/** full constructor */		
	public SamUserToolbar(String toolbarSeq, String userSeq, String moduleSeq) {
		super();
		this.toolbarSeq = toolbarSeq;
		this.userSeq = userSeq;
		this.moduleSeq = moduleSeq;
	}

	// Property accessors
	public String getToolbarSeq() {
		return toolbarSeq;
	}

	public void setToolbarSeq(String toolbarSeq) {
		this.toolbarSeq = toolbarSeq;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getModuleSeq() {
		return moduleSeq;
	}

	public void setModuleSeq(String moduleSeq) {
		this.moduleSeq = moduleSeq;
	}
	
}