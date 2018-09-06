package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="计划班次",eName="EpdPlan",tabName="epd_plan")
public class EpdPlan implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="计划序号",eName="planSeq",field="plan_seq",pk=true)
	private String planSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="车辆等级",eName="cargradeSeq",field="cargrade_seq")
	private String cargradeSeq="";
	
	@FieldTag(cName="计划班次号",eName="planId",field="plan_id")
	private String planId="";

	@FieldTag(cName="计划类型",eName="planType",field="plan_type")
	private String planType="";
	
	@FieldTag(cName="计划状态",eName="planStatus",field="plan_status")
	private String planStatus="";
	
	@FieldTag(cName="首班时间",eName="firstTime",field="first_time")
	private String firstTime="";
	
	@FieldTag(cName="发车时间",eName="planTime",field="plan_time")
	private String planTime="";
	
	@FieldTag(cName="默认检票口",eName="checkgateSeq",field="checkgate_seq")
	private String checkgateSeq="";
	
	@FieldTag(cName="登车口序号",eName="parkingSeq",field="parking_seq")
	private String parkingSeq="";
	
	@FieldTag(cName="开始日期",eName="startDate",field="start_date")
	private String startDate="";
	
	@FieldTag(cName="终止时间",eName="endDate",field="end_date")
	private String endDate="";
	
	@FieldTag(cName="席位数",eName="seatNum",field="seat_num")
	private Integer seatNum=0;
	
	@FieldTag(cName="免票数",eName="freeNum",field="free_num")
	private Integer freeNum=0;
	
	@FieldTag(cName="半票数",eName="halfNum",field="half_num")
	private Integer halfNum=0;
	
	@FieldTag(cName="禁售数",eName="stopNum",field="stop_num")
	private Integer stopNum=0;
	
	@FieldTag(cName="预留数",eName="reverseNum",field="reverse_num")
	private Integer reverseNum=0;
	
	@FieldTag(cName="运行参数",eName="planOption",field="plan_option")
	private String planOption="";
	
	@FieldTag(cName="预售天数",eName="preDays",field="pre_days")
	private Integer preDays=0;
	
	@FieldTag(cName="席位是否打印",eName="ifPrintseat",field="if_printseat")
	private Integer ifPrintseat=0;
	
	@FieldTag(cName="允许网络售票",eName="ifNetsale",field="if_netsale")
	private Integer ifNetsale=0;
	
	@FieldTag(cName = "启用乘车点", eName = "usingService", field = "using_service")
	private Integer usingService = 0;
	
	@FieldTag(cName="协议班次",eName="ifDeal",field="if_deal")
	private Integer ifDeal=0;
	
	@FieldTag(cName="否主始发",eName="ifMain",field="if_main")
	private Integer ifMain=0;
	
	@FieldTag(cName="配载车次",eName="dealId",field="deal_id")
	private String dealId="";
	
	@FieldTag(cName="配载车站",eName="dealOrganize",field="deal_organize")
	private String dealOrganize="";

	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="检票口号",eName="checkCode",field="check_code",db=false)
	private String checkCode="";

	@FieldTag(cName="线路代码",eName="routeCode",field="route_code",db=false)
	private String routeCode="";
	
	@FieldTag(cName="线路名称",eName="routeName",field="route_name",db=false)
	private String routeName="";
	
	@FieldTag(cName="车辆等级名称",eName="cargradeName",field="cargrade_name",db=false)
	private String cargradeName="";
	
	@FieldTag(cName="检票口",eName="checkName",field="check_name",db=false)
	private String checkName="";
	
	@FieldTag(cName="发车位名称",eName="parkingName",field="parking_name",db=false)
	private String parkingName="";
	
	@FieldTag(cName="配载车站名称",eName="dealOrganizename",field="deal_organizename",db=false)
	private String dealOrganizename="";
	// Constructors

	/** default constructor */
	public EpdPlan() {
	}

	public String getPlanSeq() {
		return planSeq;
	}

	public void setPlanSeq(String planSeq) {
		this.planSeq = planSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(String routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getCargradeSeq() {
		return cargradeSeq;
	}

	public void setCargradeSeq(String cargradeSeq) {
		this.cargradeSeq = cargradeSeq;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getCheckgateSeq() {
		return checkgateSeq;
	}

	public void setCheckgateSeq(String checkgateSeq) {
		this.checkgateSeq = checkgateSeq;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getParkingSeq() {
		return parkingSeq;
	}

	public void setParkingSeq(String parkingSeq) {
		this.parkingSeq = parkingSeq;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public Integer getFreeNum() {
		return freeNum;
	}

	public void setFreeNum(Integer freeNum) {
		this.freeNum = freeNum;
	}

	public Integer getHalfNum() {
		return halfNum;
	}

	public void setHalfNum(Integer halfNum) {
		this.halfNum = halfNum;
	}

	public Integer getStopNum() {
		return stopNum;
	}

	public void setStopNum(Integer stopNum) {
		this.stopNum = stopNum;
	}

	public Integer getReverseNum() {
		return reverseNum;
	}

	public void setReverseNum(Integer reverseNum) {
		this.reverseNum = reverseNum;
	}

	public String getPlanOption() {
		return planOption;
	}

	public void setPlanOption(String planOption) {
		this.planOption = planOption;
	}

	public Integer getPreDays() {
		return preDays;
	}

	public void setPreDays(Integer preDays) {
		this.preDays = preDays;
	}

	public Integer getIfPrintseat() {
		return ifPrintseat;
	}

	public void setIfPrintseat(Integer ifPrintseat) {
		this.ifPrintseat = ifPrintseat;
	}

	public Integer getIfNetsale() {
		return ifNetsale;
	}

	public void setIfNetsale(Integer ifNetsale) {
		this.ifNetsale = ifNetsale;
	}

	public Integer getUsingService() {
		return usingService;
	}

	public void setUsingService(Integer usingService) {
		this.usingService = usingService;
	}

	public Integer getIfDeal() {
		return ifDeal;
	}

	public void setIfDeal(Integer ifDeal) {
		this.ifDeal = ifDeal;
	}

	public Integer getIfMain() {
		return ifMain;
	}

	public void setIfMain(Integer ifMain) {
		this.ifMain = ifMain;
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

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getCargradeName() {
		return cargradeName;
	}

	public void setCargradeName(String cargradeName) {
		this.cargradeName = cargradeName;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public String getDealOrganizename() {
		return dealOrganizename;
	}

	public void setDealOrganizename(String dealOrganizename) {
		this.dealOrganizename = dealOrganizename;
	}

}