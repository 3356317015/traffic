package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="计划乘车点",eName="EpdPlanservice",tabName="epd_planservice")
public class EpdPlanservice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="计划站点序号",eName="planserviceSeq",field="planservice_seq",pk=true)
	private String planserviceSeq="";
	
	@FieldTag(cName="计划序号",eName="planSeq",field="plan_seq")
	private String planSeq="";

	@FieldTag(cName="计划ID",eName="planId",field="plan_id")
	private String planId="";
	
	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="乘车点序号",eName="serviceSeq",field="service_seq")
	private String serviceSeq="";
	
	@FieldTag(cName="乘车点顺序",eName="serviceOrder",field="service_order")
	private Integer serviceOrder=0;
	
	@FieldTag(cName="是否启用",eName="ifUsing",field="if_using")
	private String ifUsing="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="线路名称",eName="routeName",field="route_name")
	private String routeName="";
	
	@FieldTag(cName="乘车点名称",eName="serviceName",field="service_name",db=false)
	private String serviceName="";

	// Constructors

	/** default constructor */
	public EpdPlanservice() {
	}

	/** full constructor */

	// Property accessors

	public String getPlanserviceSeq() {
		return planserviceSeq;
	}

	public void setPlanserviceSeq(String planserviceSeq) {
		this.planserviceSeq = planserviceSeq;
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

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}