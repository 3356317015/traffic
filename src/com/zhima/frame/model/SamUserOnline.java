package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="在线用户",eName="SamUserOnline",tabName="sam_user_online")
public class SamUserOnline implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="在线用户序号",eName="onlineSeq",field="online_seq",pk=true)
	private String onlineSeq="";
	
	@FieldTag(cName="组织机构",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="用户代码",eName="userCode",field="user_code")
	private String userCode="";
	
	@FieldTag(cName="用户姓名",eName="userName",field="user_name")
	private String userName="";
	
	@FieldTag(cName="版本号",eName="sysVersion",field="sys_version")
	private String sysVersion="";

	@FieldTag(cName="备注",eName="loginIp",field="login_ip")
	private String loginIp="";
	
	@FieldTag(cName="登录时间",eName="loginTime",field="login_time")
	private String loginTime="";
	
	@FieldTag(cName="在线时间",eName="onlineTime",field="online_time")
	private String onlineTime="";
	
	// Constructors
	
	/** default constructor */
	public SamUserOnline() {
	}
	
	/** full constructor */
	public SamUserOnline(String onlineSeq, String userCode, String userName,
			String loginIp, String loginTime, String onlineTime) {
		super();
		this.onlineSeq = onlineSeq;
		this.userCode = userCode;
		this.userName = userName;
		this.loginIp = loginIp;
		this.loginTime = loginTime;
		this.onlineTime = onlineTime;
	}
	
	// Property accessors
	public String getOnlineSeq() {
		return onlineSeq;
	}

	public void setOnlineSeq(String onlineSeq) {
		this.onlineSeq = onlineSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	
}