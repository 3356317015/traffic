package com.service.traffic;

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
import com.service.traffic.action.epd.EpdStationAction;
import com.service.traffic.action.epd.EpdTickettypeAction;
import com.zhima.util.DesUtils;

/**
 * ImpEpdCar概要说明：车辆接口实现
 * @author lcy
 */
public class StationEpdServer {
	DesUtils desUtils = new DesUtils();
	/**
	 * 车辆
	 * EpdCarAction
	 */
	EpdCarAction carAction = new EpdCarAction();
	public String EpdCar_Insert(String security,String parameter) {
		return carAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdCar_Update(String security,String parameter) {
		return carAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdCar_Delete(String security,String parameter) {
		return carAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdCar_QueryPageByCustom(String security,String parameter) {
		return carAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdCar_QueryAllByCustom(String security,String parameter) {
		return carAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdCar_QueryCountByCustom(String security,String parameter) {
		return carAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCar_QueryByAll(String security) {
		return carAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	public String EpdCar_QueryByPlanId(String security,String parameter) {
		return carAction.queryByPlanId(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCar_QueryByRouteSeq(String security,String parameter) {
		return carAction.queryByRouteSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdCar_QueryByOrganizeSeq(String security,String parameter) {
		return carAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 车辆驾驶员
	 * EpdCardriverAction
	 */
	EpdCardriverAction cardriverAction = new EpdCardriverAction();
	public String EpdCardriver_QueryByCarSeq(String security,String parameter) {
		return cardriverAction.queryByCarSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 车型
	 * EpdCargradeAction
	 */
	EpdCargradeAction cargradeAction = new EpdCargradeAction();
	public String EpdCargrade_Insert(String security,String parameter) {
		return cargradeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdCargrade_Update(String security,String parameter) {
		return cargradeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdCargrade_Delete(String security,String parameter) {
		return cargradeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdCargrade_QueryByPK(String security,String parameter) {
		return cargradeAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdCargrade_QueryByAll(String security) {
		return cargradeAction.queryByAll(
				desUtils.getDesString(security));
	}
	public String EpdCargrade_QueryByRouteSeq(String security,String parameter) {
		return cargradeAction.queryByRouteSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdCargrade_QueryByOrganizeSeq(String security,String parameter) {
		return cargradeAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdCargrade_QueryPageByCustom(String security,String parameter) {
		return cargradeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdCargrade_QueryAllByCustom(String security,String parameter) {
		return cargradeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdCargrade_QueryCountByCustom(String security,String parameter) {
		return cargradeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 车辆信息
	 * EpdCarinfoAction
	 */
	EpdCarinfoAction carinfoAction = new EpdCarinfoAction();
	public String EpdCarinfo_QueryByCarSeq(String security,String parameter) {
		return carinfoAction.queryByCarSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 资质信息
	 * EpdCertificateAction
	 */
	EpdCertificateAction certificateAction = new EpdCertificateAction();
	public String EpdCertificate_Insert(String security,String parameter) {
		return certificateAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCertificate_Update(String security,String parameter) {
		return certificateAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCertificate_Delete(String security,String parameter) {
		return certificateAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCertificate_QueryByPK(String security,String parameter) {
		return certificateAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdCertificate_QueryByAll(String security) {
		return certificateAction.queryByAll(
				desUtils.getDesString(security));
	}

	public String EpdCertificate_QueryPageByCustom(String security,String parameter) {
		return certificateAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCertificate_QueryAllByCustom(String security,String parameter) {
		return certificateAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCertificate_QueryCountByCustom(String security,String parameter) {
		return certificateAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 检票口
	 * EpdCheckgateAction
	 */
	EpdCheckgateAction checkgateAction = new EpdCheckgateAction();
		
	public String EpdCheckgate_Insert(String security,String parameter) {
		return checkgateAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCheckgate_Update(String security,String parameter) {
		return checkgateAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCheckgate_Delete(String security,String parameter) {
		return checkgateAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCheckgate_QueryByPK(String security,String parameter) {
		return checkgateAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdCheckgate_QueryByAll(String security) {
		return checkgateAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	public String EpdCheckgate_QueryByOrganizeSeq(String security,String parameter) {
		return checkgateAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCheckgate_QueryPageByCustom(String security,String parameter) {
		return checkgateAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCheckgate_QueryAllByCustom(String security,String parameter) {
		return checkgateAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCheckgate_QueryCountByCustom(String security,String parameter) {
		return checkgateAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 检票口
	 * EpdCheckgateAction
	 */
	EpdParkingAction parkingAction = new EpdParkingAction();
		
	public String EpdParking_Insert(String security,String parameter) {
		return parkingAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdParking_Update(String security,String parameter) {
		return parkingAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdParking_Delete(String security,String parameter) {
		return parkingAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdParking_QueryByPK(String security,String parameter) {
		return parkingAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdParking_QueryByAll(String security) {
		return parkingAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	public String EpdParking_QueryByOrganizeAndCheckSeq(String security,String parameter) {
		return parkingAction.queryByOrganizeAndCheckSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdParking_QueryByOrganizeSeq(String security,String parameter) {
		return parkingAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdParking_QueryPageByCustom(String security,String parameter) {
		return parkingAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdParking_QueryAllByCustom(String security,String parameter) {
		return parkingAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdParking_QueryCountByCustom(String security,String parameter) {
		return parkingAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 公司
	 * EpdCompanyAction
	 */
	EpdCompanyAction companyAction = new EpdCompanyAction();
	public String EpdCompany_Insert(String security,String parameter) {
		return companyAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCompany_Update(String security,String parameter) {
		return companyAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCompany_Delete(String security,String parameter) {
		return companyAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCompany_QueryByOrganizeSeq(String security,String parameter) {
		return companyAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdCompany_QueryPageByCustom(String security,String parameter) {
		return companyAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCompany_QueryAllByCustom(String security,String parameter) {
		return companyAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCompany_QueryCountByCustom(String security,String parameter) {
		return companyAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdCompany_QueryByAll(String security) {
		return companyAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	/**
	 * 合同
	 * EpdContractAction
	 */
	EpdContractAction contractAction = new EpdContractAction();
	public String EpdContract_Insert(String security,String parameter) {
		return contractAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdContract_Update(String security,String parameter) {
		return contractAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdContract_Delete(String security,String parameter) {
		return contractAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdContract_QueryByPK(String security,String parameter) {
		return contractAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdContract_QueryByAll(String security) {
		return contractAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	public String EpdContract_QueryByOrganizeSeq(String security,String parameter) {
		return contractAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 合同明细
	 * EpdContractdetailAction
	 */
	EpdContractdetailAction contractdetailAction = new EpdContractdetailAction();

	public String EpdContractdetail_Insert(String security,String parameter) {
		return contractdetailAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdContractdetail_Update(String security,String parameter) {
		return contractdetailAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdContractdetail_Delete(String security,String parameter) {
		return contractdetailAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdContractdetail_QueryByPK(String security,String parameter) {
		return contractdetailAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdContractdetail_QueryByAll(String security) {
		return contractdetailAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	public String EpdContractdetail_QueryByContractSeq(String security,String parameter) {
		return contractdetailAction.queryByContractSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 配载协议
	 * EpdDealorganizeAction
	 */
	EpdDealorganizeAction dealorganizeAction = new EpdDealorganizeAction();
	public String EpdDealorganize_Insert(String security,String parameter) {
		return dealorganizeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDealorganize_Update(String security,String parameter) {
		return dealorganizeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDealorganize_Delete(String security,String parameter) {
		return dealorganizeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDealorganize_QueryPageByCustom(String security,String parameter) {
		return dealorganizeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDealorganize_QueryAllByCustom(String security,String parameter) {
		return dealorganizeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDealorganize_QueryCountByCustom(String security,String parameter) {
		return dealorganizeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdDealorganize_QueryByAll(String security) {
		return dealorganizeAction.queryByAll(
				desUtils.getDesString(security));
	}

	/**
	 * 驾驶员
	 * EpdDriverAction
	 */
	EpdDriverAction driverAction = new EpdDriverAction();
	public String EpdDriver_Insert(String security,String parameter) {
		return driverAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDriver_Update(String security,String parameter) {
		return driverAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDriver_Delete(String security,String parameter) {
		return driverAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDriver_QueryPageByCustom(String security,String parameter) {
		return driverAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDriver_QueryAllByCustom(String security,String parameter) {
		return driverAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdDriver_QueryCountByCustom(String security,String parameter) {
		return driverAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdDriver_QueryByAll(String security) {
		return driverAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	public String EpdDriver_QueryByOrganizeSeq(String security,String parameter) {
		return driverAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	/**
	 * 驾驶员信息
	 * EpdDriverinfoAction
	 */
	EpdDriverinfoAction driverinfoAction = new EpdDriverinfoAction();
	public String EpdDriverinfo_QueryByDriverSeq(String security,String parameter) {
		return driverinfoAction.queryByDriverSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 票价
	 * EpdFareAction
	 */
	EpdFareAction fareAction = new EpdFareAction();
	public String EpdFare_Insert(String security,String parameter) {
		return fareAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFare_Update(String security,String parameter) {
		return fareAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFare_Delete(String security,String parameter) {
		return fareAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFare_QueryByPK(String security,String parameter) {
		return fareAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdFare_QueryByAll(String security) {
		return fareAction.queryByAll(
				desUtils.getDesString(security));
	}

	public String EpdFare_QueryPageByCustom(String security,String parameter) {
		return fareAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFare_QueryAllByCustom(String security,String parameter) {
		return fareAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFare_QueryCountByCustom(String security,String parameter) {
		return fareAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFare_QueryByRouteSeq(String security,String parameter) {
		return fareAction.queryByRouteSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFare_UpdateBatch(String security,String parameter) {
		return fareAction.updateBatch(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 价套
	 * EpdFaresuitAction
	 */
	EpdFaresuitAction faresuitAction = new EpdFaresuitAction();
	public String EpdFaresuit_Insert(String security,String parameter) {
		return faresuitAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuit_Update(String security,String parameter) {
		return faresuitAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuit_Delete(String security,String parameter) {
		return faresuitAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuit_QueryByPK(String security,String parameter) {
		return faresuitAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdFaresuit_QueryByAll(String security,String parameter) {
		return faresuitAction.queryByAll(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuit_QueryPageByCustom(String security,String parameter) {
		return faresuitAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuit_QueryAllByCustom(String security,String parameter) {
		return faresuitAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuit_QueryCountByCustom(String security,String parameter) {
		return faresuitAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdFaresuit_QueryByOrganizeSeq(String security,String parameter) {
		return faresuitAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 价套运价
	 * EpdFaresuitdetailAction
	 */
	EpdFaresuitdetailAction faresuitdetailAction = new EpdFaresuitdetailAction();
	public String EpdFaresuitdetail_Insert(String security,String parameter) {
		return faresuitdetailAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuitdetail_Update(String security,String parameter) {
		return faresuitdetailAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuitdetail_Delete(String security,String parameter) {
		return faresuitdetailAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuitdetail_QueryByPK(String security,String parameter) {
		return faresuitdetailAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdFaresuitdetail_QueryByAll(String security) {
		return faresuitdetailAction.queryByAll(
				desUtils.getDesString(security));
	}

	public String EpdFaresuitdetail_QueryPageByCustom(String security,String parameter) {
		return faresuitdetailAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuitdetail_QueryAllByCustom(String security,String parameter) {
		return faresuitdetailAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuitdetail_QueryCountByCustom(String security,String parameter) {
		return faresuitdetailAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuitdetail_QueryByRouteSeq(String security,String parameter) {
		return faresuitdetailAction.queryByRouteSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuitdetail_UpdateBatch(String security,String parameter) {
		return faresuitdetailAction.updateBatch(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaresuitdetail_QueryByRouteSeqAndFaresuitSeq(String security,String parameter) {
		return faresuitdetailAction.queryByRouteSeqAndFaresuitSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 票型
	 * EpdFaretypeAction
	 */
	EpdFaretypeAction faretypeAction = new EpdFaretypeAction();
	public String EpdFaretype_Insert(String security,String parameter) {
		return faretypeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdFaretype_Update(String security,String parameter) {
		return faretypeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdFaretype_Delete(String security,String parameter) {
		return faretypeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdFaretype_QueryByPK(String security,String parameter) {
		return faretypeAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdFaretype_QueryByAll(String security) {
		return faretypeAction.queryByAll(
				desUtils.getDesString(security));
	}

	public String EpdFaretype_QueryPageByCustom(String security,String parameter) {
		return faretypeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaretype_QueryAllByCustom(String security,String parameter) {
		return faretypeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdFaretype_QueryCountByCustom(String security,String parameter) {
		return faretypeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdFaretype_QueryByOrganizeSeq(String security,String parameter) {
		return faretypeAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 计划
	 * EpdPlanAction
	 */
	EpdPlanAction planAction = new EpdPlanAction();
	public String EpdPlan_Insert(String security,String parameter) {
		return planAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlan_Update(String security,String parameter) {
		return planAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlan_Delete(String security,String parameter) {
		return planAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlan_QueryByPK(String security,String parameter) {
		return planAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdPlan_QueryByRouteSeq(String security,String parameter) {
		return planAction.queryByRouteSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdPlan_QueryPageByCustom(String security,String parameter) {
		return planAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdPlan_QueryAllByCustom(String security,String parameter) {
		return planAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdPlan_QueryCountByCustom(String security,String parameter) {
		return planAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlan_QueryByAll(String security) {
		return planAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	public String EpdPlan_QueryByOrganizeSeq(String security,String parameter) {
		return planAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 生成计划
	 * EpdPlanBuildAction
	 */
	EpdPlanBuildAction planBuildAction = new EpdPlanBuildAction();
	public String EpdPlanBuild_QueryByAll(String security) {
		return planBuildAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	public String EpdPlanBuild_PlanBuild(String security,String parameter) {
		return planBuildAction.planBuild(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 计划车辆
	 * EpdPlancarAction
	 */
	EpdPlancarAction plancarAction = new EpdPlancarAction();

	public String EpdPlancar_Insert(String security,String parameter) {
		return plancarAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlancar_Update(String security,String parameter) {
		return plancarAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlancar_Delete(String security,String parameter) {
		return plancarAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlancar_QueryByPK(String security,String parameter) {
		return plancarAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlancar_QueryByPlanSeqAndPlanId(String security,String parameter) {
		return plancarAction.queryByPlanSeqAndPlanId(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlancar_UpdateBatch(String security,String parameter) {
		return plancarAction.updateBatch(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 计划检票口
	 * EpdPlancheckAction
	 */
	EpdPlancheckAction plancheckAction = new EpdPlancheckAction();
	public String EpdPlancheck_Insert(String security,String parameter) {
		return plancheckAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlancheck_Update(String security,String parameter) {
		return plancheckAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlancheck_Delete(String security,String parameter) {
		return plancheckAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlancheck_QueryByPK(String security,String parameter) {
		return plancheckAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlancheck_QueryByPlanSeqAndPlanId(String security,String parameter) {
		return plancheckAction.queryByPlanSeqAndPlanId(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 计划协议
	 * EpdPlandealAction
	 */
	EpdPlandealAction plandealAction = new EpdPlandealAction();
	public String EpdPlandeal_Insert(String security,String parameter) {
		return plandealAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlandeal_Update(String security,String parameter) {
		return plandealAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlandeal_Delete(String security,String parameter) {
		return plandealAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlandeal_QueryByPK(String security,String parameter) {
		return plandealAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlandeal_QueryByPlanSeqAndPlanId(String security,String parameter) {
		return plandealAction.queryByPlanSeqAndPlanId(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 计划席位
	 * EpdPlanseatAction
	 */
	EpdPlanseatAction planseatAction = new EpdPlanseatAction();
	public String EpdPlanseat_Insert(String security,String parameter) {
		return planseatAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanseat_Update(String security,String parameter) {
		return planseatAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanseat_Delete(String security,String parameter) {
		return planseatAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanseat_QueryByPK(String security,String parameter) {
		return planseatAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanseat_QueryByPlanSeqAndPlanId(String security,String parameter) {
		return planseatAction.queryByPlanSeqAndPlanId(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 计划站点
	 * EpdPlanstationAction
	 */
	EpdPlanstationAction planstationAction = new EpdPlanstationAction();
	public String EpdPlanstation_Insert(String security,String parameter) {
		return planstationAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanstation_Update(String security,String parameter) {
		return planstationAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanstation_Delete(String security,String parameter) {
		return planstationAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanstation_QueryByPK(String security,String parameter) {
		return planstationAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanstation_QueryByPlanSeqAndPlanId(String security,String parameter) {
		return planstationAction.queryByPlanSeqAndPlanId(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 计划乘车点
	 * EpdPlanserviceAction
	 */
	EpdPlanserviceAction planserviceAction = new EpdPlanserviceAction();
	public String EpdPlanservice_Insert(String security,String parameter) {
		return planserviceAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanservice_Update(String security,String parameter) {
		return planserviceAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanservice_Delete(String security,String parameter) {
		return planserviceAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanservice_QueryByPK(String security,String parameter) {
		return planserviceAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdPlanservice_QueryByPlanSeq(String security,String parameter) {
		return planserviceAction.queryByPlanSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 行政区
	 * EpdRegionAction
	 */
	EpdRegionAction regionAction = new EpdRegionAction();
	public String EpdRegion_Insert(String security,String parameter) {
		return regionAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRegion_Update(String security,String parameter) {
		return regionAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRegion_Delete(String security,String parameter) {
		return regionAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRegion_QueryByPK(String security,String parameter) {
		return regionAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRegion_QueryByAll(String security) {
		return regionAction.queryByAll(
				desUtils.getDesString(security));
	}

	public String EpdRegion_QueryPageByCustom(String security,String parameter) {
		return regionAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRegion_QueryAllByCustom(String security,String parameter) {
		return regionAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRegion_QueryCountByCustom(String security,String parameter) {
		return regionAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdRegion_QueryGroupCity(String security,String parameter) {
		return regionAction.queryGroupCity(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRegion_QueryGroupCounty(String security,String parameter) {
		return regionAction.queryGroupCounty(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRegion_QueryGroupTowns(String security,String parameter) {
		return regionAction.queryGroupTowns(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 退票费率
	 * EpdReturnrateAction
	 */
	EpdReturnrateAction returnrateAction = new EpdReturnrateAction();
	public String EpdReturnrate_Insert(String security,String parameter) {
		return returnrateAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdReturnrate_Update(String security,String parameter) {
		return returnrateAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdReturnrate_Delete(String security,String parameter) {
		return returnrateAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdReturnrate_QueryByPK(String security,String parameter) {
		return returnrateAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdReturnrate_QueryByAll(String security) {
		return returnrateAction.queryByAll(
				desUtils.getDesString(security));
	}
	public String EpdReturnrate_QueryPageByCustom(String security,String parameter) {
		return returnrateAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdReturnrate_QueryAllByCustom(String security,String parameter) {
		return returnrateAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdReturnrate_QueryCountByCustom(String security,String parameter) {
		return returnrateAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 线路
	 * EpdRouteAction
	 */
	EpdRouteAction routeAction = new EpdRouteAction();
	public String EpdRoute_Insert(String security,String parameter) {
		return routeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRoute_Update(String security,String parameter) {
		return routeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRoute_Delete(String security,String parameter) {
		return routeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRoute_QueryByPK(String security,String parameter) {
		return routeAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRoute_QueryByAll(String security) {
		return routeAction.queryByAll(
				desUtils.getDesString(security));
	}
	public String EpdRoute_QueryByOrganizeSeq(String security,String parameter) {
		return routeAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRoute_QueryPageByCustom(String security,String parameter) {
		return routeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRoute_QueryAllByCustom(String security,String parameter) {
		return routeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRoute_QueryCountByCustom(String security,String parameter) {
		return routeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRoute_QueryByNoFare(String security,String parameter) {
		return routeAction.queryByNoFare(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 线路站点
	 * EpdRouteStationAction
	 */
	EpdRouteStationAction routeStationAction = new EpdRouteStationAction();
	public String EpdRouteStation_Insert(String security,String parameter) {
		return routeStationAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRouteStation_Update(String security,String parameter) {
		return routeStationAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRouteStation_Delete(String security,String parameter) {
		return routeStationAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRouteStation_QueryByPK(String security,String parameter) {
		return routeStationAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRouteStation_QueryByRouteSeq(String security,String parameter) {
		return routeStationAction.queryByRouteSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	EpdRouteServiceAction  routeServiceAction = new EpdRouteServiceAction();
	public String EpdRouteService_Insert(String security,String parameter) {
		return routeServiceAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRouteService_Update(String security,String parameter) {
		return routeServiceAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRouteService_Delete(String security,String parameter) {
		return routeServiceAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRouteService_QueryByPK(String security,String parameter) {
		return routeServiceAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRouteService_QueryByRouteSeq(String security,String parameter) {
		return routeServiceAction.queryByRouteSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 线路类型
	 * EpdRouteTypeAction
	 */
	EpdRouteTypeAction routeTypeAction = new EpdRouteTypeAction();
	public String EpdRouteType_Insert(String security,String parameter) {
		return routeTypeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRouteType_Update(String security,String parameter) {
		return routeTypeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRouteType_Delete(String security,String parameter) {
		return routeTypeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRouteType_QueryByPK(String security,String parameter) {
		return routeTypeAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRouteType_QueryPageByCustom(String security,String parameter) {
		return routeTypeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRouteType_QueryAllByCustom(String security,String parameter) {
		return routeTypeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdRouteType_QueryCountByCustom(String security,String parameter) {
		return routeTypeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdRouteType_QueryByOrganizeSeq(String security,String parameter) {
		return routeTypeAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdRouteType_QueryByAll(String security) {
		return routeTypeAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	/**
	 * 站点
	 * EpdStationAction
	 */
	EpdStationAction stationAction = new EpdStationAction();
	public String EpdStation_Insert(String security,String parameter) {
		return stationAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdStation_Update(String security,String parameter) {
		return stationAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdStation_Delete(String security,String parameter) {
		return stationAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdStation_QueryPageByCustom(String security,String parameter) {
		return stationAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdStation_QueryAllByCustom(String security,String parameter) {
		return stationAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdStation_QueryCountByCustom(String security,String parameter) {
		return stationAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdStation_QueryByAll(String security) {
		return stationAction.queryByAll(
				desUtils.getDesString(security));
	}
	
	public String EpdStation_QueryByOrganizeSeq(String security,String parameter) {
		return stationAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdStation_QueryByStatus(String security,String parameter) {
		return stationAction.queryByStatus(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 票据类型
	 * EpdTickettypeAction
	 */
	EpdTickettypeAction tickettypeAction = new EpdTickettypeAction();
	public String EpdTickettype_Insert(String security,String parameter) {
		return tickettypeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdTickettype_Update(String security,String parameter) {
		return tickettypeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdTickettype_Delete(String security,String parameter) {
		return tickettypeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdTickettype_QueryByPK(String security,String parameter) {
		return tickettypeAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdTickettype_QueryByOrganizeSeq(String security,String parameter) {
		return tickettypeAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdTickettype_QueryPageByCustom(String security,String parameter) {
		return tickettypeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdTickettype_QueryAllByCustom(String security,String parameter) {
		return tickettypeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String EpdTickettype_QueryCountByCustom(String security,String parameter) {
		return tickettypeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

}