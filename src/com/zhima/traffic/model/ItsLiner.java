package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName = "日班次", eName = "ItsLiner" ,tabName="its_liner")
public class ItsLiner implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName = "班次序号", eName = "linerSeq",field="liner_seq",pk=true)
	private String linerSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName = "班次日期", eName = "linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName = "日班次号", eName = "linerId",field="liner_id")
	private String linerId="";
	
	@FieldTag(cName = "首发时间", eName = "firstTime",field="first_time")
	private String firstTime="";

	@FieldTag(cName = "发车时间", eName = "linerTime",field="liner_time")
	private String linerTime="";
	
	@FieldTag(cName = "班次类型", eName = "linerType",field="liner_type")
	private String linerType="";

	@FieldTag(cName = "线路序号", eName = "routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName = "线路名称", eName = "routeName",field="route_name")
	private String routeName="";
	
	@FieldTag(cName = "车辆等级号", eName = "cargradeSeq",field="cargrade_seq")
	private String cargradeSeq="";

	@FieldTag(cName = "车辆等级名称", eName = "cargradeName",field="cargrade_name")
	private String cargradeName="";
	
	@FieldTag(cName = "席位是否打印", eName = "ifPrintseat",field="if_printseat")
	private Integer ifPrintseat=0;
	
	@FieldTag(cName = "席位数", eName = "seatNum",field="seat_num")
	private Integer seatNum=0;

	@FieldTag(cName = "免票数", eName = "freeNum",field="free_num")
	private Integer freeNum=0;

	@FieldTag(cName = "半票数", eName = "halfNum",field="half_num")
	private Integer halfNum=0;
	
	@FieldTag(cName = "售票数", eName = "saleNum",field="sale_num")
	private Integer saleNum=0;
	
	@FieldTag(cName = "售半票", eName = "saleHalf",field="sale_half")
	private Integer saleHalf=0;
	
	@FieldTag(cName = "售免票", eName = "saleFree",field="sale_free")
	private Integer saleFree=0;
	
	@FieldTag(cName = "锁位数", eName = "lockNum",field="lock_num")
	private Integer lockNum=0;
	
	@FieldTag(cName = "禁售数", eName = "stopNum",field="stop_num")
	private Integer stopNum=0;
	
	@FieldTag(cName = "配载数", eName = "dealNum",field="deal_num")
	private Integer dealNum=0;
	
	@FieldTag(cName = "预留数", eName = "reverseNum",field="reverse_num")
	private Integer reverseNum=0;
	
	@FieldTag(cName = "退票数", eName = "returnNum",field="return_num")
	private Integer returnNum=0;

	@FieldTag(cName = "废票数", eName = "invalidNum",field="invalid_num")
	private Integer invalidNum=0;

	@FieldTag(cName = "检票数", eName = "checkNum",field="check_num")
	private Integer checkNum=0;
	
	@FieldTag(cName = "检票口序号", eName = "checkgateSeq",field="checkgate_seq")
	private String checkgateSeq="";

	@FieldTag(cName = "登车口序号", eName = "parkingSeq",field="parking_seq")
	private String parkingSeq="";

	@FieldTag(cName = "班次状态", eName = "linerStatus",field="liner_status")
	private String linerStatus="";

	@FieldTag(cName = "报班状态", eName = "reportStatus",field="report_status")
	private Integer reportStatus=0;

	@FieldTag(cName = "报班车辆", eName = "carNumber",field="car_number")
	private String carNumber="";
	
	@FieldTag(cName = "驾驶员姓名", eName = "driverName",field="driver_name")
	private String driverName="";
	
	@FieldTag(cName = "报班时间", eName = "reportTime",field="report_time")
	private String reportTime="";
	
	@FieldTag(cName = "打单状态", eName = "printbillStatus",field="printbill_status")
	private Integer printbillStatus=0;
	
	@FieldTag(cName = "打单时间", eName = "printbillTime",field="printbill_time")
	private String printbillTime="";
	
	@FieldTag(cName = "出站状态", eName = "outstationStatus",field="outstation_status")
	private Integer outstationStatus=0;
	
	@FieldTag(cName = "出站时间", eName = "outstationTime",field="outstation_time")
	private String outstationTime="";
	
	@FieldTag(cName = "到达时间", eName = "estimateTime",field="estimate_time")
	private String estimateTime="";
	
	@FieldTag(cName = "是否配载", eName = "ifDeal",field="if_deal")
	private Integer ifDeal=0;
	
	@FieldTag(cName = "是否始发", eName = "ifMain",field="if_main")
	private Integer ifMain=0;

	@FieldTag(cName = "是否网络售票", eName = "ifNetsale",field="if_netsale")
	private Integer ifNetsale=0;
	
	@FieldTag(cName = "启用乘车点", eName = "usingService", field = "using_service")
	private Integer usingService = 0;

	@FieldTag(cName = "备注", eName = "remark",field="remark")
	private String remark="";
	
	@FieldTag(cName = "创建人", eName = "createUser",field="create_user")
	private String createUser="";
	
	@FieldTag(cName = "创建时间", eName = "createTime",field="create_time")
	private String createTime="";

	@FieldTag(cName = "修改时间", eName = "updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName = "检票口名称", eName = "checkName",field="check_name",db=false)
	private String checkName="";
	
	@FieldTag(cName = "登车口名称", eName = "parkingName",field="parking_name",db=false)
	private String parkingName="";
	
	@FieldTag(cName = "途经站", eName = "stationName", field = "station_name",db=false)
	private String stationName = "";
	
	@FieldTag(cName = "票价", eName = "price", field = "price",db=false)
	private Double price = 0.00;
	
	@FieldTag(cName = "余票数", eName = "moreSeat", field = "more_seat",db=false)
	private Integer moreSeat = 0;
	
	@FieldTag(cName = "半票数", eName = "moreHalf", field = "more_half",db=false)
	private Integer moreHalf = 0;
	
	@FieldTag(cName = "免票数", eName = "moreFree", field = "more_free",db=false)
	private Integer moreFree = 0;

	/** default constructor */
	
	/** full constructor */

	// Property accessors
	public ItsLiner() {
	}

	public String getLinerSeq() {
		return linerSeq;
	}

	public void setLinerSeq(String linerSeq) {
		this.linerSeq = linerSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
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

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public String getLinerTime() {
		return linerTime;
	}

	public void setLinerTime(String linerTime) {
		this.linerTime = linerTime;
	}

	public String getLinerType() {
		return linerType;
	}

	public void setLinerType(String linerType) {
		this.linerType = linerType;
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

	public String getCargradeSeq() {
		return cargradeSeq;
	}

	public void setCargradeSeq(String cargradeSeq) {
		this.cargradeSeq = cargradeSeq;
	}

	public String getCargradeName() {
		return cargradeName;
	}

	public void setCargradeName(String cargradeName) {
		this.cargradeName = cargradeName;
	}

	public Integer getIfPrintseat() {
		return ifPrintseat;
	}

	public void setIfPrintseat(Integer ifPrintseat) {
		this.ifPrintseat = ifPrintseat;
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

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getSaleHalf() {
		return saleHalf;
	}

	public void setSaleHalf(Integer saleHalf) {
		this.saleHalf = saleHalf;
	}

	public Integer getSaleFree() {
		return saleFree;
	}

	public void setSaleFree(Integer saleFree) {
		this.saleFree = saleFree;
	}

	public Integer getLockNum() {
		return lockNum;
	}

	public void setLockNum(Integer lockNum) {
		this.lockNum = lockNum;
	}

	public Integer getStopNum() {
		return stopNum;
	}

	public void setStopNum(Integer stopNum) {
		this.stopNum = stopNum;
	}

	public Integer getDealNum() {
		return dealNum;
	}

	public void setDealNum(Integer dealNum) {
		this.dealNum = dealNum;
	}

	public Integer getReverseNum() {
		return reverseNum;
	}

	public void setReverseNum(Integer reverseNum) {
		this.reverseNum = reverseNum;
	}

	public Integer getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}

	public Integer getInvalidNum() {
		return invalidNum;
	}

	public void setInvalidNum(Integer invalidNum) {
		this.invalidNum = invalidNum;
	}

	public Integer getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}

	public String getCheckgateSeq() {
		return checkgateSeq;
	}

	public void setCheckgateSeq(String checkgateSeq) {
		this.checkgateSeq = checkgateSeq;
	}


	public String getParkingSeq() {
		return parkingSeq;
	}

	public void setParkingSeq(String parkingSeq) {
		this.parkingSeq = parkingSeq;
	}

	public String getLinerStatus() {
		return linerStatus;
	}

	public void setLinerStatus(String linerStatus) {
		this.linerStatus = linerStatus;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public Integer getPrintbillStatus() {
		return printbillStatus;
	}

	public void setPrintbillStatus(Integer printbillStatus) {
		this.printbillStatus = printbillStatus;
	}
	
	public String getPrintbillTime() {
		return printbillTime;
	}

	public void setPrintbillTime(String printbillTime) {
		this.printbillTime = printbillTime;
	}

	public Integer getOutstationStatus() {
		return outstationStatus;
	}

	public void setOutstationStatus(Integer outstationStatus) {
		this.outstationStatus = outstationStatus;
	}
	
	public String getOutstationTime() {
		return outstationTime;
	}

	public void setOutstationTime(String outstationTime) {
		this.outstationTime = outstationTime;
	}

	public String getEstimateTime() {
		return estimateTime;
	}

	public void setEstimateTime(String estimateTime) {
		this.estimateTime = estimateTime;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getMoreSeat() {
		return moreSeat;
	}

	public void setMoreSeat(Integer moreSeat) {
		this.moreSeat = moreSeat;
	}

	public Integer getMoreHalf() {
		return moreHalf;
	}

	public void setMoreHalf(Integer moreHalf) {
		this.moreHalf = moreHalf;
	}

	public Integer getMoreFree() {
		return moreFree;
	}

	public void setMoreFree(Integer moreFree) {
		this.moreFree = moreFree;
	}
	
}