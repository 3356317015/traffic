package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="安检项目",eName="EpdSafeitem",tabName="epd_safeitem")
public class EpdSafeitem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="项目检查序号",eName="safeitemSeq",field="safeitem_seq",pk=true)
	private String safeitemSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="安检类型",eName="safeType",field="safe_type")
	private String safeType="";
	
	@FieldTag(cName="安检项目名称",eName="safeName",field="safe_name")
	private String safeName="";
	
	@FieldTag(cName="检查内容",eName="safeDetail",field="safe_detail")
	private String safeDetail="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	

	// Constructors

	/** default constructor */
	public EpdSafeitem() {
	}

	/** full constructor */

	// Property accessors
	public String getSafeitemSeq() {
		return safeitemSeq;
	}

	public void setSafeitemSeq(String safeitemSeq) {
		this.safeitemSeq = safeitemSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getSafeType() {
		return safeType;
	}


	public void setSafeType(String safeType) {
		this.safeType = safeType;
	}


	public String getSafeName() {
		return safeName;
	}


	public void setSafeName(String safeName) {
		this.safeName = safeName;
	}


	public String getSafeDetail() {
		return safeDetail;
	}


	public void setSafeDetail(String safeDetail) {
		this.safeDetail = safeDetail;
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