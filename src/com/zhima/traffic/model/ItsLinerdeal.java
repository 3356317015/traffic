package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="班次协议信息",eName="ItsLinerdeal",tabName="its_linerdeal")
public class ItsLinerdeal implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="班次协议序号",eName="linerdealSeq",field="linerdeal_seq",pk=true)
	private String linerdealSeq="";
	
	@FieldTag(cName="班次序号",eName="linerSeq",field="liner_seq")
	private String linerSeq="";
	
	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";
	
	@FieldTag(cName="协议班次号",eName="dealId",field="deal_id")
	private String dealId="";
	
	@FieldTag(cName="协议机构",eName="dealOrganize",field="deal_organize")
	private String dealOrganize="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="机构名称",eName="organizeName",field="organize_name",db=false)
	private String organizeName="";

	/** default constructor */
	public ItsLinerdeal() {
	}

	public String getLinerdealSeq() {
		return linerdealSeq;
	}

	public void setLinerdealSeq(String linerdealSeq) {
		this.linerdealSeq = linerdealSeq;
	}

	public String getLinerSeq() {
		return linerSeq;
	}

	public void setLinerSeq(String linerSeq) {
		this.linerSeq = linerSeq;
	}

	public String getLinerDate() {
		return linerDate;
	}

	public void setLinerDate(String linerDate) {
		this.linerDate = linerDate;
	}

	public String getLinerId() {
		return linerId;
	}

	public void setLinerId(String linerId) {
		this.linerId = linerId;
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
