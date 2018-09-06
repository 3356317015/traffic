package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="线路乘车点",eName="EpdRouteservice",tabName="epd_routeservice")
public class EpdRouteservice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="线路乘车点序号",eName="routeserviceSeq",field="routeservice_seq",pk=true)
	private String routeserviceSeq="";
	
	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="乘车点序号",eName="serviceSeq",field="service_seq")
	private String serviceSeq="";
	
	@FieldTag(cName="乘车点顺序",eName="serviceOrder",field="service_order")
	private Integer serviceOrder=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="乘车点名称",eName="serviceName",field="service_name", db=false)
	private String serviceName="";

	
	// Constructors

	/** default constructor */
	public EpdRouteservice() {
	}

	/** full constructor */

	// Property accessors


	public String getRouteserviceSeq() {
		return routeserviceSeq;
	}

	public void setRouteserviceSeq(String routeserviceSeq) {
		this.routeserviceSeq = routeserviceSeq;
	}
	
	public String getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(String routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getServiceSeq() {
		return serviceSeq;
	}


	public void setServiceSeq(String serviceSeq) {
		this.serviceSeq = serviceSeq;
	}


	public Integer getServiceOrder() {
		return serviceOrder;
	}


	public void setServiceOrder(Integer serviceOrder) {
		this.serviceOrder = serviceOrder;
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}