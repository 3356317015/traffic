package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="升级清单",eName="SamUpgrade",tabName="sam_upgrade")
public class SamUpgrade implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="升级序号",eName="upgradeSeq",field="upgrade_seq",pk=true)
	private String upgradeSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="文件名",eName="fileName",field="file_name")
	private String fileName="";
	
	@FieldTag(cName="文件大小",eName="fileSize",field="file_size")
	private String fileSize="";
	
	@FieldTag(cName="文件路径",eName="filePath",field="file_path")
	private String filePath="";
	
	@FieldTag(cName="版本号",eName="fileVer",field="file_ver")
	private String fileVer="";
	
	@FieldTag(cName="文件来源",eName="fileUrl",field="file_url")
	private String fileUrl="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";
	
	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	/** default constructor */
	public SamUpgrade() {
	}

	/** full constructor */	
	

	// Property accessors
	
	public String getUpgradeSeq() {
		return upgradeSeq;
	}

	public void setUpgradeSeq(String upgradeSeq) {
		this.upgradeSeq = upgradeSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileVer() {
		return fileVer;
	}

	public void setFileVer(String fileVer) {
		this.fileVer = fileVer;
	}
	
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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