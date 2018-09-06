package com.zhima.basic;

import com.service.traffic.action.epd.EpdCarAction;
import com.service.traffic.action.epd.EpdCardriverAction;
import com.service.traffic.action.epd.EpdCargradeAction;
import com.service.traffic.action.epd.EpdCarinfoAction;
import com.service.traffic.action.epd.EpdCertificateAction;
import com.service.traffic.action.epd.EpdCheckgateAction;
import com.service.traffic.action.epd.EpdCompanyAction;
import com.service.traffic.action.epd.EpdContractAction;
import com.service.traffic.action.epd.EpdContractdetailAction;
import com.service.traffic.action.epd.EpdDealorganizeAction;
import com.service.traffic.action.epd.EpdDriverAction;
import com.service.traffic.action.epd.EpdDriverinfoAction;
import com.service.traffic.action.epd.EpdFareAction;
import com.service.traffic.action.epd.EpdFaresuitAction;
import com.service.traffic.action.epd.EpdFaresuitdetailAction;
import com.service.traffic.action.epd.EpdFaretypeAction;
import com.service.traffic.action.epd.EpdParkingAction;
import com.service.traffic.action.epd.EpdPlanAction;
import com.service.traffic.action.epd.EpdPlanBuildAction;
import com.service.traffic.action.epd.EpdPlancarAction;
import com.service.traffic.action.epd.EpdPlancheckAction;
import com.service.traffic.action.epd.EpdPlandealAction;
import com.service.traffic.action.epd.EpdPlanseatAction;
import com.service.traffic.action.epd.EpdPlanserviceAction;
import com.service.traffic.action.epd.EpdPlanstationAction;
import com.service.traffic.action.epd.EpdRegionAction;
import com.service.traffic.action.epd.EpdReturnrateAction;
import com.service.traffic.action.epd.EpdRouteAction;
import com.service.traffic.action.epd.EpdRouteServiceAction;
import com.service.traffic.action.epd.EpdRouteStationAction;
import com.service.traffic.action.epd.EpdRouteTypeAction;
import com.service.traffic.action.epd.EpdServicerightAction;
import com.service.traffic.action.epd.EpdStationAction;
import com.service.traffic.action.epd.EpdTickettypeAction;


/**
 * SamFinal概要说明：系统服务接口定义
 * @author lcy
 */
public class EpdFinal {
	public static String ServerAddres="http://127.0.0.1:8080/axis2/services/StationEpdServer";
	public static String ServerNamespace="http://traffic.service.com";
	/**
	 * 车辆
	 * EpdCarAction
	 */
	EpdCarAction epdCarAction = new EpdCarAction();
	public static final String EpdCar_Insert = "EpdCar_Insert";
	public static final String EpdCar_Update = "EpdCar_Update";
	public static final String EpdCar_Delete = "EpdCar_Delete";
	public static final String EpdCar_QueryPageByCustom = "EpdCar_QueryPageByCustom";
	public static final String EpdCar_QueryAllByCustom = "EpdCar_QueryAllByCustom";
	public static final String EpdCar_QueryCountByCustom = "EpdCar_QueryCountByCustom";
	public static final String EpdCar_QueryByAll = "EpdCar_QueryByAll";
	public static final String EpdCar_QueryByPlanId = "EpdCar_QueryByPlanId";
	public static final String EpdCar_QueryByRouteSeq = "EpdCar_QueryByRouteSeq";
	public static final String EpdCar_QueryByOrganizeSeq = "EpdCar_QueryByOrganizeSeq";

	/**
	 * 车辆驾驶员
	 * EpdCardriverAction
	 */
	EpdCardriverAction cardriverAction = new EpdCardriverAction();
	public static final String EpdCardriver_QueryByCarSeq = "EpdCardriver_QueryByCarSeq";
	
	/**
	 * 车型
	 * EpdCargradeAction
	 */
	EpdCargradeAction cargradeAction = new EpdCargradeAction();
	public static final String EpdCargrade_Insert = "EpdCargrade_Insert";
	public static final String EpdCargrade_Update = "EpdCargrade_Update";
	public static final String EpdCargrade_Delete = "EpdCargrade_Delete";
	public static final String EpdCargrade_QueryByPK = "EpdCargrade_QueryByPK";
	public static final String EpdCargrade_QueryByAll = "EpdCargrade_QueryByAll";
	public static final String EpdCargrade_QueryByRouteSeq = "EpdCargrade_QueryByRouteSeq";
	public static final String EpdCargrade_QueryByOrganizeSeq = "EpdCargrade_QueryByOrganizeSeq";
	public static final String EpdCargrade_QueryPageByCustom = "EpdCargrade_QueryPageByCustom";
	public static final String EpdCargrade_QueryAllByCustom = "EpdCargrade_QueryAllByCustom";
	public static final String EpdCargrade_QueryCountByCustom = "EpdCargrade_QueryCountByCustom";
	
	/**
	 * 车辆信息
	 * EpdCarinfoAction
	 */
	EpdCarinfoAction carinfoAction = new EpdCarinfoAction();
	public static final String EpdCarinfo_QueryByCarSeq = "EpdCarinfo_QueryByCarSeq";
	
	/**
	 * 资质信息
	 * EpdCertificateAction
	 */
	EpdCertificateAction certificateAction = new EpdCertificateAction();
	public static final String EpdCertificate_Insert = "EpdCertificate_Insert";
	public static final String EpdCertificate_Update = "EpdCertificate_Update";
	public static final String EpdCertificate_Delete = "EpdCertificate_Delete";
	public static final String EpdCertificate_QueryByPK = "EpdCertificate_QueryByPK";
	public static final String EpdCertificate_QueryByAll = "EpdCertificate_QueryByAll";
	public static final String EpdCertificate_QueryPageByCustom = "EpdCertificate_QueryPageByCustom";
	public static final String EpdCertificate_QueryAllByCustom = "EpdCertificate_QueryAllByCustom";
	public static final String EpdCertificate_QueryCountByCustom = "EpdCertificate_QueryCountByCustom";
	
	/**
	 * 检票口
	 * EpdCheckgateAction
	 */
	EpdCheckgateAction checkgateAction = new EpdCheckgateAction();
	public static final String EpdCheckgate_Insert = "EpdCheckgate_Insert";
	public static final String EpdCheckgate_Update = "EpdCheckgate_Update";
	public static final String EpdCheckgate_Delete = "EpdCheckgate_Delete";
	public static final String EpdCheckgate_QueryByPK = "EpdCheckgate_QueryByPK";
	public static final String EpdCheckgate_QueryByAll = "EpdCheckgate_QueryByAll";
	public static final String EpdCheckgate_QueryByOrganizeSeq = "EpdCheckgate_QueryByOrganizeSeq";
	public static final String EpdCheckgate_QueryPageByCustom = "EpdCheckgate_QueryPageByCustom";
	public static final String EpdCheckgate_QueryAllByCustom = "EpdCheckgate_QueryAllByCustom";
	public static final String EpdCheckgate_QueryCountByCustom = "EpdCheckgate_QueryCountByCustom";
	
	/**
	 * 发车位
	 * EpdCheckgateAction
	 */
	EpdParkingAction parkingAction = new EpdParkingAction();
	public static final String EpdParking_Insert = "EpdParking_Insert";
	public static final String EpdParking_Update = "EpdParking_Update";
	public static final String EpdParking_Delete = "EpdParking_Delete";
	public static final String EpdParking_QueryByPK = "EpdParking_QueryByPK";
	public static final String EpdParking_QueryByAll = "EpdParking_QueryByAll";
	public static final String EpdParking_QueryByOrganizeSeq = "EpdParking_QueryByOrganizeSeq";
	public static final String EpdParking_QueryByOrganizeAndCheckSeq = "EpdParking_QueryByOrganizeAndCheckSeq";
	public static final String EpdParking_QueryPageByCustom = "EpdParking_QueryPageByCustom";
	public static final String EpdParking_QueryAllByCustom = "EpdParking_QueryAllByCustom";
	public static final String EpdParking_QueryCountByCustom = "EpdParking_QueryCountByCustom";
	
	/**
	 * 公司
	 * EpdCompanyAction
	 */
	EpdCompanyAction companyAction = new EpdCompanyAction();
	public static final String EpdCompany_Insert = "EpdCompany_Insert";
	public static final String EpdCompany_Update = "EpdCompany_Update";
	public static final String EpdCompany_Delete = "EpdCompany_Delete";
	public static final String EpdCompany_QueryByOrganizeSeq = "EpdCompany_QueryByOrganizeSeq";
	public static final String EpdCompany_QueryPageByCustom = "EpdCompany_QueryPageByCustom";
	public static final String EpdCompany_QueryAllByCustom = "EpdCompany_QueryAllByCustom";
	public static final String EpdCompany_QueryCountByCustom = "EpdCompany_QueryCountByCustom";
	public static final String EpdCompany_QueryByAll = "EpdCompany_QueryByAll";
	
	/**
	 * 合同
	 * EpdContractAction
	 */
	EpdContractAction contractAction = new EpdContractAction();
	public static final String EpdContract_Insert = "EpdContract_Insert";
	public static final String EpdContract_Update = "EpdContract_Update";
	public static final String EpdContract_Delete = "EpdContract_Delete";
	public static final String EpdContract_QueryByOrganizeSeq = "EpdContract_QueryByOrganizeSeq";
	public static final String EpdContract_QueryByPK = "EpdContract_QueryByPK";
	public static final String EpdContract_QueryByAll = "EpdContract_QueryByAll";
	
	/**
	 * 合同明细
	 * EpdContractdetailAction
	 */
	EpdContractdetailAction contractdetailAction = new EpdContractdetailAction();
	public static final String EpdContractdetail_Insert = "EpdContractdetail_Insert";
	public static final String EpdContractdetail_Update = "EpdContractdetail_Update";
	public static final String EpdContractdetail_Delete = "EpdContractdetail_Delete";
	public static final String EpdContractdetail_QueryByPK = "EpdContractdetail_QueryByPK";
	public static final String EpdContractdetail_QueryByAll = "EpdContractdetail_QueryByAll";
	public static final String EpdContractdetail_QueryByContractSeq = "EpdContractdetail_QueryByContractSeq";
	
	/**
	 * 配载协议
	 * EpdDealorganizeAction
	 */
	EpdDealorganizeAction dealorganizeAction = new EpdDealorganizeAction();
	public static final String EpdDealorganize_Insert = "EpdDealorganize_Insert";
	public static final String EpdDealorganize_Update = "EpdDealorganize_Update";
	public static final String EpdDealorganize_Delete = "EpdDealorganize_Delete";
	public static final String EpdDealorganize_QueryPageByCustom = "EpdDealorganize_QueryPageByCustom";
	public static final String EpdDealorganize_QueryAllByCustom = "EpdDealorganize_QueryAllByCustom";
	public static final String EpdDealorganize_QueryCountByCustom = "EpdDealorganize_QueryCountByCustom";
	public static final String EpdDealorganize_QueryByAll = "EpdDealorganize_QueryByAll";

	/**
	 * 驾驶员
	 * EpdDriverAction
	 */
	EpdDriverAction driverAction = new EpdDriverAction();
	public static final String EpdDriver_Insert = "EpdDriver_Insert";
	public static final String EpdDriver_Update = "EpdDriver_Update";
	public static final String EpdDriver_Delete = "EpdDriver_Delete";
	public static final String EpdDriver_QueryPageByCustom = "EpdDriver_QueryPageByCustom";
	public static final String EpdDriver_QueryAllByCustom = "EpdDriver_QueryAllByCustom";
	public static final String EpdDriver_QueryCountByCustom = "EpdDriver_QueryCountByCustom";
	public static final String EpdDriver_QueryByAll = "EpdDriver_QueryByAll";
	public static final String EpdDriver_QueryByOrganizeSeq = "EpdDriver_QueryByOrganizeSeq";

	/**
	 * 驾驶员信息
	 * EpdDriverinfoAction
	 */
	EpdDriverinfoAction driverinfoAction = new EpdDriverinfoAction();
	public static final String EpdDriverinfo_QueryByDriverSeq = "EpdDriverinfo_QueryByDriverSeq";
	
	/**
	 * 票价
	 * EpdFareAction
	 */
	EpdFareAction fareAction = new EpdFareAction();
	public static final String EpdFare_Insert = "EpdFare_Insert";
	public static final String EpdFare_Update = "EpdFare_Update";
	public static final String EpdFare_Delete = "EpdFare_Delete";
	public static final String EpdFare_QueryByPK = "EpdFare_QueryByPK";
	public static final String EpdFare_QueryByAll = "EpdFare_QueryByAll";
	public static final String EpdFare_QueryPageByCustom = "EpdFare_QueryPageByCustom";
	public static final String EpdFare_QueryAllByCustom = "EpdFare_QueryAllByCustom";
	public static final String EpdFare_QueryCountByCustom = "EpdFare_QueryCountByCustom";
	public static final String EpdFare_QueryByRouteSeq = "EpdFare_QueryByRouteSeq";
	public static final String EpdFare_UpdateBatch = "EpdFare_UpdateBatch";
	
	/**
	 * 价套
	 * EpdFaresuitAction
	 */
	EpdFaresuitAction faresuitAction = new EpdFaresuitAction();
	public static final String EpdFaresuit_Insert = "EpdFaresuit_Insert";
	public static final String EpdFaresuit_Update = "EpdFaresuit_Update";
	public static final String EpdFaresuit_Delete = "EpdFaresuit_Delete";
	public static final String EpdFaresuit_QueryByPK = "EpdFaresuit_QueryByPK";
	public static final String EpdFaresuit_QueryByAll = "EpdFaresuit_QueryByAll";
	public static final String EpdFaresuit_QueryPageByCustom = "EpdFaresuit_QueryPageByCustom";
	public static final String EpdFaresuit_QueryAllByCustom = "EpdFaresuit_QueryAllByCustom";
	public static final String EpdFaresuit_QueryCountByCustom = "EpdFaresuit_QueryCountByCustom";
	public static final String EpdFaresuit_QueryByOrganizeSeq = "EpdFaresuit_QueryByOrganizeSeq";
	
	/**
	 * 价套运价
	 * EpdFaresuitdetailAction
	 */
	EpdFaresuitdetailAction faresuitdetailAction = new EpdFaresuitdetailAction();
	public static final String EpdFaresuitdetail_Insert = "EpdFaresuitdetail_Insert";
	public static final String EpdFaresuitdetail_Update = "EpdFaresuitdetail_Update";
	public static final String EpdFaresuitdetail_Delete = "EpdFaresuitdetail_Delete";
	public static final String EpdFaresuitdetail_QueryByPK = "EpdFaresuitdetail_QueryByPK";
	public static final String EpdFaresuitdetail_QueryByAll = "EpdFaresuitdetail_QueryByAll";
	public static final String EpdFaresuitdetail_QueryPageByCustom = "EpdFaresuitdetail_QueryPageByCustom";
	public static final String EpdFaresuitdetail_QueryAllByCustom = "EpdFaresuitdetail_QueryAllByCustom";
	public static final String EpdFaresuitdetail_QueryCountByCustom = "EpdFaresuitdetail_QueryCountByCustom";
	public static final String EpdFaresuitdetail_QueryByRouteSeq = "EpdFaresuitdetail_QueryByRouteSeq";
	public static final String EpdFaresuitdetail_UpdateBatch = "EpdFaresuitdetail_UpdateBatch";
	public static final String EpdFaresuitdetail_QueryByRouteSeqAndFaresuitSeq = "EpdFaresuitdetail_QueryByRouteSeqAndFaresuitSeq";
	
	/**
	 * 票型
	 * EpdFaretypeAction
	 */
	EpdFaretypeAction faretypeAction = new EpdFaretypeAction();
	public static final String EpdFaretype_Insert = "EpdFaretype_Insert";
	public static final String EpdFaretype_Update = "EpdFaretype_Update";
	public static final String EpdFaretype_Delete = "EpdFaretype_Delete";
	public static final String EpdFaretype_QueryByPK = "EpdFaretype_QueryByPK";
	public static final String EpdFaretype_QueryByAll = "EpdFaretype_QueryByAll";
	public static final String EpdFaretype_QueryPageByCustom = "EpdFaretype_QueryPageByCustom";
	public static final String EpdFaretype_QueryAllByCustom = "EpdFaretype_QueryAllByCustom";
	public static final String EpdFaretype_QueryCountByCustom = "EpdFaretype_QueryCountByCustom";
	public static final String EpdFaretype_QueryByOrganizeSeq = "EpdFaretype_QueryByOrganizeSeq";
	
	/**
	 * 计划
	 * EpdPlanAction
	 */
	EpdPlanAction planAction = new EpdPlanAction();
	public static final String EpdPlan_Insert = "EpdPlan_Insert";
	public static final String EpdPlan_Update = "EpdPlan_Update";
	public static final String EpdPlan_Delete = "EpdPlan_Delete";
	public static final String EpdPlan_QueryByPK = "EpdPlan_QueryByPK";
	public static final String EpdPlan_QueryByRouteSeq = "EpdPlan_QueryByRouteSeq";
	public static final String EpdPlan_QueryPageByCustom = "EpdPlan_QueryPageByCustom";
	public static final String EpdPlan_QueryAllByCustom = "EpdPlan_QueryAllByCustom";
	public static final String EpdPlan_QueryCountByCustom = "EpdPlan_QueryCountByCustom";
	public static final String EpdPlan_QueryByAll = "EpdPlan_QueryByAll";
	public static final String EpdPlan_QueryByOrganizeSeq = "EpdPlan_QueryByOrganizeSeq";
	/**
	 * 生成计划
	 * EpdPlanBuildAction
	 */
	EpdPlanBuildAction planBuildAction = new EpdPlanBuildAction();
	public static final String EpdPlanBuild_QueryByAll = "EpdPlanBuild_QueryByAll";
	public static final String EpdPlanBuild_PlanBuild = "EpdPlanBuild_PlanBuild";
	
	/**
	 * 计划车辆
	 * EpdPlancarAction
	 */
	EpdPlancarAction plancarAction = new EpdPlancarAction();
	public static final String EpdPlancar_Insert = "EpdPlancar_Insert";
	public static final String EpdPlancar_Update = "EpdPlancar_Update";
	public static final String EpdPlancar_Delete = "EpdPlancar_Delete";
	public static final String EpdPlancar_QueryByPK = "EpdPlancar_QueryByPK";
	public static final String EpdPlancar_QueryByPlanSeqAndPlanId = "EpdPlancar_QueryByPlanSeqAndPlanId";
	public static final String EpdPlancar_UpdateBatch = "EpdPlancar_UpdateBatch";
	
	/**
	 * 计划检票口
	 * EpdPlancheckAction
	 */
	EpdPlancheckAction plancheckAction = new EpdPlancheckAction();
	public static final String EpdPlancheck_Insert = "EpdPlancheck_Insert";
	public static final String EpdPlancheck_Update = "EpdPlancheck_Update";
	public static final String EpdPlancheck_Delete = "EpdPlancheck_Delete";
	public static final String EpdPlancheck_QueryByPK = "EpdPlancheck_QueryByPK";
	public static final String EpdPlancheck_QueryByPlanSeqAndPlanId = "EpdPlancheck_QueryByPlanSeqAndPlanId";
	
	/**
	 * 计划协议
	 * EpdPlandealAction
	 */
	EpdPlandealAction plandealAction = new EpdPlandealAction();
	public static final String EpdPlandeal_Insert = "EpdPlandeal_Insert";
	public static final String EpdPlandeal_Update = "EpdPlandeal_Update";
	public static final String EpdPlandeal_Delete = "EpdPlandeal_Delete";
	public static final String EpdPlandeal_QueryByPK = "EpdPlandeal_QueryByPK";
	public static final String EpdPlandeal_QueryByPlanSeqAndPlanId = "EpdPlandeal_QueryByPlanSeqAndPlanId";
	
	/**
	 * 计划席位
	 * EpdPlanseatAction
	 */
	EpdPlanseatAction planseatAction = new EpdPlanseatAction();
	public static final String EpdPlanseat_Insert = "EpdPlanseat_Insert";
	public static final String EpdPlanseat_Update = "EpdPlanseat_Update";
	public static final String EpdPlanseat_Delete = "EpdPlanseat_Delete";
	public static final String EpdPlanseat_QueryByPK = "EpdPlanseat_QueryByPK";
	public static final String EpdPlanseat_QueryByPlanSeqAndPlanId = "EpdPlanseat_QueryByPlanSeqAndPlanId";
	
	/**
	 * 计划站点
	 * EpdPlanstationAction
	 */
	EpdPlanstationAction planstationAction = new EpdPlanstationAction();
	public static final String EpdPlanstation_Insert = "EpdPlanstation_Insert";
	public static final String EpdPlanstation_Update = "EpdPlanstation_Update";
	public static final String EpdPlanstation_Delete = "EpdPlanstation_Delete";
	public static final String EpdPlanstation_QueryByPK = "EpdPlanstation_QueryByPK";
	public static final String EpdPlanstation_QueryByPlanSeqAndPlanId = "EpdPlanstation_QueryByPlanSeqAndPlanId";
	
	/**
	 * 计划站点
	 * EpdPlanserviceAction
	 */
	EpdPlanserviceAction planserviceAction = new EpdPlanserviceAction();
	public static final String EpdPlanservice_Insert = "EpdPlanservice_Insert";
	public static final String EpdPlanservice_Update = "EpdPlanservice_Update";
	public static final String EpdPlanservice_Delete = "EpdPlanservice_Delete";
	public static final String EpdPlanservice_QueryByPK = "EpdPlanservice_QueryByPK";
	public static final String EpdPlanservice_QueryByPlanSeq = "EpdPlanservice_QueryByPlanSeq";
	
	/**
	 * 行政区
	 * EpdRegionAction
	 */
	EpdRegionAction regionAction = new EpdRegionAction();
	public static final String EpdRegion_Insert = "EpdRegion_Insert";
	public static final String EpdRegion_Update = "EpdRegion_Update";
	public static final String EpdRegion_Delete = "EpdRegion_Delete";
	public static final String EpdRegion_QueryByPK = "EpdRegion_QueryByPK";
	public static final String EpdRegion_QueryByAll = "EpdRegion_QueryByAll";
	public static final String EpdRegion_QueryPageByCustom = "EpdRegion_QueryPageByCustom";
	public static final String EpdRegion_QueryAllByCustom = "EpdRegion_QueryAllByCustom";
	public static final String EpdRegion_QueryCountByCustom = "EpdRegion_QueryCountByCustom";
	public static final String EpdRegion_QueryGroupCity = "EpdRegion_QueryGroupCity";
	public static final String EpdRegion_QueryGroupCounty = "EpdRegion_QueryGroupCounty";
	public static final String EpdRegion_QueryGroupTowns = "EpdRegion_QueryGroupTowns";
	
	/**
	 * 退票费率
	 * EpdReturnrateAction
	 */
	EpdReturnrateAction returnrateAction = new EpdReturnrateAction();
	public static final String EpdReturnrate_Insert = "EpdReturnrate_Insert";
	public static final String EpdReturnrate_Update = "EpdReturnrate_Update";
	public static final String EpdReturnrate_Delete = "EpdReturnrate_Delete";
	public static final String EpdReturnrate_QueryByPK = "EpdReturnrate_QueryByPK";
	public static final String EpdReturnrate_QueryByAll = "EpdReturnrate_QueryByAll";
	public static final String EpdReturnrate_QueryByOrganizeSeq = "EpdReturnrate_QueryByOrganizeSeq";
	public static final String EpdReturnrate_QueryPageByCustom = "EpdReturnrate_QueryPageByCustom";
	public static final String EpdReturnrate_QueryAllByCustom = "EpdReturnrate_QueryAllByCustom";
	public static final String EpdReturnrate_QueryCountByCustom = "EpdReturnrate_QueryCountByCustom";
	
	/**
	 * 线路
	 * EpdRouteAction
	 */
	EpdRouteAction routeAction = new EpdRouteAction();
	public static final String EpdRoute_Insert = "EpdRoute_Insert";
	public static final String EpdRoute_Update = "EpdRoute_Update";
	public static final String EpdRoute_Delete = "EpdRoute_Delete";
	public static final String EpdRoute_QueryByPK = "EpdRoute_QueryByPK";
	public static final String EpdRoute_QueryByAll = "EpdRoute_QueryByAll";
	public static final String EpdRoute_QueryByOrganizeSeq = "EpdRoute_QueryByOrganizeSeq";
	public static final String EpdRoute_QueryPageByCustom = "EpdRoute_QueryPageByCustom";
	public static final String EpdRoute_QueryAllByCustom = "EpdRoute_QueryAllByCustom";
	public static final String EpdRoute_QueryCountByCustom = "EpdRoute_QueryCountByCustom";
	public static final String EpdRoute_QueryByNoFare = "EpdRoute_QueryByNoFare";
	
	/**
	 * 线路站点
	 * EpdRouteStationAction
	 */
	EpdRouteStationAction routeStationAction = new EpdRouteStationAction();
	public static final String EpdRouteStation_Insert = "EpdRouteStation_Insert";
	public static final String EpdRouteStation_Update = "EpdRouteStation_Update";
	public static final String EpdRouteStation_Delete = "EpdRouteStation_Delete";
	public static final String EpdRouteStation_QueryByPK = "EpdRouteStation_QueryByPK";
	public static final String EpdRouteStation_QueryByRouteSeq = "EpdRouteStation_QueryByRouteSeq";
	
	/**
	 * 线路乘车点
	 * EpdRouteStationAction
	 */
	EpdRouteServiceAction routeServiceAction = new EpdRouteServiceAction();
	public static final String EpdRouteService_Insert = "EpdRouteService_Insert";
	public static final String EpdRouteService_Update = "EpdRouteService_Update";
	public static final String EpdRouteService_Delete = "EpdRouteService_Delete";
	public static final String EpdRouteService_QueryByPK = "EpdRouteService_QueryByPK";
	public static final String EpdRouteService_QueryByRouteSeq = "EpdRouteService_QueryByRouteSeq";
	
	/**
	 * 线路类型
	 * EpdRouteTypeAction
	 */
	EpdRouteTypeAction routeTypeAction = new EpdRouteTypeAction();
	public static final String EpdRouteType_Insert = "EpdRouteType_Insert";
	public static final String EpdRouteType_Update = "EpdRouteType_Update";
	public static final String EpdRouteType_Delete = "EpdRouteType_Delete";
	public static final String EpdRouteType_QueryByPK = "EpdRouteType_QueryByPK";
	public static final String EpdRouteType_QueryPageByCustom = "EpdRouteType_QueryPageByCustom";
	public static final String EpdRouteType_QueryAllByCustom = "EpdRouteType_QueryAllByCustom";
	public static final String EpdRouteType_QueryCountByCustom = "EpdRouteType_QueryCountByCustom";
	public static final String EpdRouteType_QueryByOrganizeSeq = "EpdRouteType_QueryByOrganizeSeq";
	public static final String EpdRouteType_QueryByAll = "EpdRouteType_QueryByAll";
	/**
	 * 站点
	 * EpdStationAction
	 */
	EpdStationAction stationAction = new EpdStationAction();
	public static final String EpdStation_Insert = "EpdStation_Insert";
	public static final String EpdStation_Update = "EpdStation_Update";
	public static final String EpdStation_Delete = "EpdStation_Delete";
	public static final String EpdStation_QueryPageByCustom = "EpdStation_QueryPageByCustom";
	public static final String EpdStation_QueryAllByCustom = "EpdStation_QueryAllByCustom";
	public static final String EpdStation_QueryCountByCustom = "EpdStation_QueryCountByCustom";
	public static final String EpdStation_QueryByAll = "EpdStation_QueryByAll";
	public static final String EpdStation_QueryByOrganizeSeq = "EpdStation_QueryByOrganizeSeq";
	public static final String EpdStation_QueryByStatus = "EpdStation_QueryByStatus";
	
	/**
	 * 票据类型
	 * EpdTickettypeAction
	 */
	EpdTickettypeAction tickettypeAction = new EpdTickettypeAction();
	public static final String EpdTickettype_Insert = "EpdTickettype_Insert";
	public static final String EpdTickettype_Update = "EpdTickettype_Update";
	public static final String EpdTickettype_Delete = "EpdTickettype_Delete";
	public static final String EpdTickettype_QueryByPK = "EpdTickettype_QueryByPK";
	public static final String EpdTickettype_QueryByOrganizeSeq = "EpdTickettype_QueryByOrganizeSeq";
	public static final String EpdTickettype_QueryPageByCustom = "EpdTickettype_QueryPageByCustom";
	public static final String EpdTickettype_QueryAllByCustom = "EpdTickettype_QueryAllByCustom";
	public static final String EpdTickettype_QueryCountByCustom = "EpdTickettype_QueryCountByCustom";
	
	/**
	 * 销售点权限
	 * EpdServicerightAction
	 */
	EpdServicerightAction servicerightAction = new EpdServicerightAction();
	public static final String EpdServiceright_QueryByServiceSeq = "EpdServiceright_QueryByServiceSeq";
	public static final String EpdServiceright_SaveRight = "EpdServiceright_SaveRight";

}
