package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="升级清单",eName="SamUpinfo",tabName="sam_upinfo")
public class SamUpinfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="升级信息序号",eName="upinfoSeq",field="upinfo_seq",pk=true)
	private String upinfoSeq="";
	
	@FieldTag(cName="版本号",eName="fileVer",field="file_ver")
	private String fileVer="";
	
	@FieldTag(cName="升级信息",eName="upgradeInfo",field="upgrade_info")
	private String upgradeInfo="";

	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";
	
	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	/** default constructor */
	public SamUpinfo() {
	}
	
	/** full constructor */	
	

	// Property accessors
	public String getUpinfoSeq() {
		return upinfoSeq;
	}

	public void setUpinfoSeq(String upinfoSeq) {
		this.upinfoSeq = upinfoSeq;
	}

	public String getFileVer() {
		return fileVer;
	}

	public void setFileVer(String fileVer) {
		this.fileVer = fileVer;
	}

	public String getUpgradeInfo() {
		return upgradeInfo;
	}

	public void setUpgradeInfo(String upgradeInfo) {
		this.upgradeInfo = upgradeInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}