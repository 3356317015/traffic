package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="用户权限信息",eName="SamUserModule",tabName="sam_user_module")
public class SamUserModule implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="用户权限序号",eName="userModuleSeq",field="user_module_seq",pk=true)
	private String userModuleSeq="";
	
	@FieldTag(cName="用户序号",eName="userSeq",field="user_seq")
	private String userSeq="";
	
	@FieldTag(cName="模块序号",eName="moduleSeq",field="module_seq")
	private String moduleSeq="";
	
	@FieldTag(cName="权限序号",eName="rightSeq",field="right_seq")
	private String rightSeq="";
	
	// Constructors
	/** default constructor */
	public SamUserModule() {
	}
	
	/** full constructor */		
	public SamUserModule(String userModuleSeq, String userSeq,
			String moduleSeq, String rightSeq) {
		super();
		this.userModuleSeq = userModuleSeq;
		this.userSeq = userSeq;
		this.moduleSeq = moduleSeq;
		this.rightSeq = rightSeq;
	}

	// Property accessors
	public String getUserModuleSeq() {
		return userModuleSeq;
	}

	public void setUserModuleSeq(String userModuleSeq) {
		this.userModuleSeq = userModuleSeq;
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

	public String getRightSeq() {
		return rightSeq;
	}

	public void setRightSeq(String rightSeq) {
		this.rightSeq = rightSeq;
	}
	
}