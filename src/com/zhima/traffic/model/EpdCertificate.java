package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="资质项",eName="EpdCertificate",tabName="epd_certificate")
public class EpdCertificate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="证件项目序号",eName="certificateSeq",field="certificate_seq",pk=true)
	private String certificateSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="证件类型",eName="cerType",field="cer_type")
	private String cerType="";
	
	@FieldTag(cName="证件名称",eName="cerName",field="cer_name")
	private String cerName="";
	
	@FieldTag(cName="状态",eName="cerStatus",field="cer_status")
	private String cerStatus="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors

	/** default constructor */
	public EpdCertificate() {
	}
	/** full constructor */
	
	// Property accessors
	
	public String getCertificateSeq() {
		return certificateSeq;
	}

	public void setCertificateSeq(String certificateSeq) {
		this.certificateSeq = certificateSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}
	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}
	public String getCerType() {
		return cerType;
	}
	public void setCerType(String cerType) {
		this.cerType = cerType;
	}
	public String getCerName() {
		return cerName;
	}
	public void setCerName(String cerName) {
		this.cerName = cerName;
	}
	public String getCerStatus() {
		return cerStatus;
	}
	public void setCerStatus(String cerStatus) {
		this.cerStatus = cerStatus;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}