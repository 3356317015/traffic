package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="协议计划",eName="EpdPlandeal",tabName="epd_plandeal")
public class EpdPlandeal implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	@FieldTag(cName="协议计划序号",eName="plandealSeq",field="plandeal_seq",pk=true)
	private String plandealSeq="";
	
	@FieldTag(cName="计划序号",eName="planSeq",field="plan_seq")
	private String planSeq="";
	
	@FieldTag(cName="计划ID",eName="planId",field="plan_id")
	private String planId="";
	
	@FieldTag(cName="协议ID",eName="dealId",field="deal_id")
	private String dealId="";
	
	@FieldTag(cName="机构号",eName="dealOrganize",field="deal_organize")
	private String dealOrganize="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="机构名称",eName="organizeName",field="organize_name",db=false)
	private String organizeName="";

	// Constructors

	/** default constructor */
	public EpdPlandeal() {
	}
	/** full constructor */

	// Property accessors
	public String getPlandealSeq() {
		return plandealSeq;
	}

	public void setPlandealSeq(String plandealSeq) {
		this.plandealSeq = plandealSeq;
	}

	public String getPlanSeq() {
		return planSeq;
	}
	public void setPlanSeq(String planSeq) {
		this.planSeq = planSeq;
	}
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getDealId() {
		return dealId;
	}
	
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	
	public String getDealOrganize() {
		return dealOrganize;
	}

	public void setDealOrganize(String dealOrganize) {
		this.dealOrganize = dealOrganize;
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
	
	public String getOrganizeName() {
		return organizeName;
	}
	
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}

}