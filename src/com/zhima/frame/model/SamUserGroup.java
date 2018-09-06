package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="用户角色信息",eName="SamUserGroup",tabName="sam_user_group")
public class SamUserGroup implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="用户权限序号",eName="userGroupSeq",field="user_group_seq",pk=true)
	private String userGroupSeq="";
	
	@FieldTag(cName="用户序号",eName="userSeq",field="user_seq")
	private String userSeq="";
	
	@FieldTag(cName="角色序号",eName="groupSeq",field="group_seq")
	private String groupSeq="";
	
	// Constructors
	/** default constructor */
	public SamUserGroup() {
	}
	
	/** full constructor */		
	public SamUserGroup(String userGroupSeq, String userSeq, String groupSeq) {
		super();
		this.userGroupSeq = userGroupSeq;
		this.userSeq = userSeq;
		this.groupSeq = groupSeq;
	}

	// Property accessors
	public String getUserGroupSeq() {
		return userGroupSeq;
	}

	public void setUserGroupSeq(String userGroupSeq) {
		this.userGroupSeq = userGroupSeq;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getGroupSeq() {
		return groupSeq;
	}

	public void setGroupSeq(String groupSeq) {
		this.groupSeq = groupSeq;
	}
	
}