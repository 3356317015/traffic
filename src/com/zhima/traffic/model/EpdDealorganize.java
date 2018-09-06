package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="配载站",eName="EpdDealorganize",tabName="epd_dealorganize")
public class EpdDealorganize implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="配载站序号",eName="dealorganizeSeq",field="dealorganize_seq",pk=true)
	private String dealorganizeSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="配载站",eName="dealorganize",field="dealorganize")
	private String dealorganize="";
	
	@FieldTag(cName="服务地址",eName="organizeUrl",field="organize_url")
	private String organizeUrl="";
	
	@FieldTag(cName="发送车辆信息",eName="sendCar",field="send_car")
	private Integer sendCar=0;
	
	@FieldTag(cName="发送驾驶员信息",eName="sendDriver",field="send_driver")
	private Integer sendDriver=0;
	
	@FieldTag(cName="发送安检信息",eName="sendSafe",field="send_safe")
	private Integer sendSafe=0;
	
	@FieldTag(cName="接收车辆信息",eName="receiveCar",field="receive_car")
	private Integer receiveCar=0;
	
	@FieldTag(cName="接收驾驶员信息",eName="receiveDriver",field="receive_driver")
	private Integer receiveDriver=0;
	
	@FieldTag(cName="接收安检信息",eName="receiveSafe",field="receive_safe")
	private Integer receiveSafe=0;
	
	@FieldTag(cName="配载站状态",eName="dealStatus",field="deal_status")
	private Integer dealStatus=0;

	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="最后修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="结算公司代码",eName="organizeName",field="organize_name",db=false)
	private String organizeName="";

	public String getDealorganizeSeq() {
		return dealorganizeSeq;
	}

	public void setDealorganizeSeq(String dealorganizeSeq) {
		this.dealorganizeSeq = dealorganizeSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}
	
	public String getDealorganize() {
		return dealorganize;
	}

	public void setDealorganize(String dealorganize) {
		this.dealorganize = dealorganize;
	}

	public String getOrganizeUrl() {
		return organizeUrl;
	}

	public void setOrganizeUrl(String organizeUrl) {
		this.organizeUrl = organizeUrl;
	}

	public Integer getSendCar() {
		return sendCar;
	}

	public void setSendCar(Integer sendCar) {
		this.sendCar = sendCar;
	}

	public Integer getSendDriver() {
		return sendDriver;
	}

	public void setSendDriver(Integer sendDriver) {
		this.sendDriver = sendDriver;
	}

	public Integer getSendSafe() {
		return sendSafe;
	}

	public void setSendSafe(Integer sendSafe) {
		this.sendSafe = sendSafe;
	}

	public Integer getReceiveCar() {
		return receiveCar;
	}

	public void setReceiveCar(Integer receiveCar) {
		this.receiveCar = receiveCar;
	}

	public Integer getReceiveDriver() {
		return receiveDriver;
	}

	public void setReceiveDriver(Integer receiveDriver) {
		this.receiveDriver = receiveDriver;
	}

	public Integer getReceiveSafe() {
		return receiveSafe;
	}

	public void setReceiveSafe(Integer receiveSafe) {
		this.receiveSafe = receiveSafe;
	}

	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
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