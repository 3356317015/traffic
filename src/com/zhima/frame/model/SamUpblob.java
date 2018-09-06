package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="升级文件",eName="samUpblob",tabName="sam_upblob")
public class SamUpblob implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="升级文件序号",eName="upblobSeq",field="upblob_seq",pk=true)
	private String upblobSeq="";
	
	@FieldTag(cName="升级序号",eName="upgradeSeq",field="upgrade_seq")
	private String upgradeSeq="";
	
	@FieldTag(cName="升级文件",eName="fileBlob",field="file_blob")
	private String fileBlob;
	
	@FieldTag(cName="序号",eName="orderId",field="order_id")
	private Integer orderId=0;

	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";
	
	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	/** default constructor */
	public SamUpblob() {
	}
	
	/** full constructor */	
	

	// Property accessors
	
	public String getRemark() {
		return remark;
	}

	public String getUpblobSeq() {
		return upblobSeq;
	}

	public void setUpblobSeq(String upblobSeq) {
		this.upblobSeq = upblobSeq;
	}

	public String getUpgradeSeq() {
		return upgradeSeq;
	}

	public void setUpgradeSeq(String upgradeSeq) {
		this.upgradeSeq = upgradeSeq;
	}

	public String getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(String fileBlob) {
		this.fileBlob = fileBlob;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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