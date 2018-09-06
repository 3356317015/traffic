package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="组权限信息",eName="SamGroupModule",tabName="sam_group_module")
public class SamGroupModule implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="组权限序号",eName="groupModuleSeq",field="group_module_seq",pk=true)
	private String groupModuleSeq="";
	
	@FieldTag(cName="组序号",eName="groupSeq",field="group_seq")
	private String groupSeq="";
	
	@FieldTag(cName="模块序号",eName="moduleSeq",field="module_seq")
	private String moduleSeq="";
	
	@FieldTag(cName="权限序号",eName="rightSeq",field="right_seq")
	private String rightSeq="";
	
	// Constructors
	/** default constructor */
	public SamGroupModule() {
	}
	
	/** full constructor */	
	public SamGroupModule(String groupModuleSeq, String groupSeq,
			String moduleSeq, String rightSeq) {
		super();
		this.groupModuleSeq = groupModuleSeq;
		this.groupSeq = groupSeq;
		this.moduleSeq = moduleSeq;
		this.rightSeq = rightSeq;
	}
	
	// Property accessors
	public String getGroupModuleSeq() {
		return groupModuleSeq;
	}

	public void setGroupModuleSeq(String groupModuleSeq) {
		this.groupModuleSeq = groupModuleSeq;
	}

	public String getGroupSeq() {
		return groupSeq;
	}

	public void setGroupSeq(String groupSeq) {
		this.groupSeq = groupSeq;
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