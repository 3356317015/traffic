package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="班次乘车点",eName="ItsLinerservice",tabName="its_linerservice")
public class ItsLinerservice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="班次乘车点",eName="linerserviceSeq",field="linerservice_seq",pk=true)
	private String linerserviceSeq="";
	
	@FieldTag(cName="班次序号",eName="linerSeq",field="liner_seq")
	private String linerSeq="";
	
	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";

	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="线路名称",eName="routeName",field="route_name")
	private String routeName="";
	
	@FieldTag(cName="乘车点序号",eName="serviceSeq",field="service_seq")
	private String serviceSeq="";
	
	@FieldTag(cName="乘车点名称",eName="serviceName",field="service_name")
	private String serviceName="";
	
	@FieldTag(cName="乘车点顺序",eName="serviceOrder",field="service_order")
	private Integer serviceOrder=0;
	
	@FieldTag(cName="是否启用",eName="ifUsing",field="if_using")
	private String ifUsing="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="已售数量",eName="saleNum",field="sale_num",db=false)
	private Integer saleNum=0;
	
	@FieldTag(cName="检票数量",eName="checkNum",field="check_num",db=false)
	private Integer checkNum=0;
	

	// Constructors

	/** default constructor */
	public ItsLinerservice() {
	}
	/** full constructor */

	// Property accessors
	public String getLinerserviceSeq() {
		return linerserviceSeq;
	}
	
	
	public void setLinerserviceSeq(String linerserviceSeq) {
		this.linerserviceSeq = linerserviceSeq;
	}
	
	
	public void setLinerSeq(String linerSeq) {
		this.linerSeq = linerSeq;
	}


	public String getLinerSeq() {
		return linerSeq;
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


	public String getRouteSeq() {
		return routeSeq;
	}


	public void setRouteSeq(String routeSeq) {
		this.routeSeq = routeSeq;
	}


	public String getRouteName() {
		return routeName;
	}


	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}


	public String getServiceSeq() {
		return serviceSeq;
	}


	public void setServiceSeq(String serviceSeq) {
		this.serviceSeq = serviceSeq;
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public Integer getServiceOrder() {
		return serviceOrder;
	}


	public void setServiceOrder(Integer serviceOrder) {
		this.serviceOrder = serviceOrder;
	}


	public String getIfUsing() {
		return ifUsing;
	}


	public void setIfUsing(String ifUsing) {
		this.ifUsing = ifUsing;
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


	public Integer getSaleNum() {
		return saleNum;
	}


	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}


	public Integer getCheckNum() {
		return checkNum;
	}


	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}


}