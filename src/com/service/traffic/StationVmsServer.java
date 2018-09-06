package com.service.traffic;

import com.service.traffic.action.voice.VmsLinerAction;
import com.service.traffic.action.voice.VmsNoticeAction;
import com.service.traffic.action.voice.VmsNoticesetAction;
import com.service.traffic.action.voice.VmsParameterAction;
import com.service.traffic.action.voice.VmsRouteAction;
import com.service.traffic.action.voice.VmsSoundAction;
import com.service.traffic.action.voice.VmsSoundsetAction;
import com.service.traffic.action.voice.VmsTemplateAction;
import com.zhima.util.DesUtils;

/**
 * ImpEpdCar概要说明：车辆接口实现
 * @author lcy
 */
public class StationVmsServer {
	DesUtils desUtils = new DesUtils();
	
	
	/**
	 * 语音参数
	 * VmsParameterAction
	 */
	VmsParameterAction parameterAction = new VmsParameterAction();
		
	public String EpdParking_Update(String security,String parameter) {
		return parameterAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String EpdParking_QueryByOrganizeSeq(String security,String parameter) {
		return parameterAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String EpdParking_QueryByParameterCode(String security,String parameter) {
		return parameterAction.queryByParameterCode(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	/**
	 * 播音模板
	 * VmsTemplateAction
	 */
	VmsTemplateAction templateAction = new VmsTemplateAction();
	
	public String VmsTemplate_Insert(String security,String parameter) {
		return templateAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsTemplate_Update(String security,String parameter) {
		return templateAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String VmsTemplate_Delete(String security,String parameter) {
		return templateAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsTemplate_QueryByOrganizeSeq(String security,String parameter) {
		return templateAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 播音线路
	 * VmsRouteAction
	 */
	VmsRouteAction routeAction = new VmsRouteAction();
	public String VmsRoute_Insert(String security,String parameter) {
		return routeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsRoute_Update(String security,String parameter) {
		return routeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsRoute_Delete(String security,String parameter) {
		return routeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsRoute_QueryByPK(String security,String parameter) {
		return routeAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String VmsRoute_QueryByOrganizeSeq(String security,String parameter) {
		return routeAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsRoute_QueryPageByCustom(String security,String parameter) {
		return routeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsRoute_QueryAllByCustom(String security,String parameter) {
		return routeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsRoute_QueryCountByCustom(String security,String parameter) {
		return routeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsRoute_QueryTrafficRoute(String security,String parameter) {
		return routeAction.queryTrafficRoute(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 文本公告
	 * VmsNoticeAction
	 */
	VmsNoticeAction noticeAction = new VmsNoticeAction();
	public String VmsNotice_Insert(String security,String parameter) {
		return noticeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_Update(String security,String parameter) {
		return noticeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_Delete(String security,String parameter) {
		return noticeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_QueryByPK(String security,String parameter) {
		return noticeAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_QueryPageByOrganizeSeq(String security,String parameter) {
		return noticeAction.queryPageByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_QueryByOrganizeSeq(String security,String parameter) {
		return noticeAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_QueryCountByOrganizeSeq(String security,String parameter) {
		return noticeAction.queryCountByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_QueryPageByCustom(String security,String parameter) {
		return noticeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_QueryAllByCustom(String security,String parameter) {
		return noticeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_QueryCountByCustom(String security,String parameter) {
		return noticeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsNotice_QueryByStatusAndTime(String security,String parameter) {
		return noticeAction.queryByStatusAndTime(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 文本公告设置
	 * VmsNoticesetAction
	 */
	VmsNoticesetAction noticesetAction = new VmsNoticesetAction();

	public String VmsNoticeset_Update(String security,String parameter) {
		return noticesetAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsNoticeset_QueryByPK(String security,String parameter) {
		return noticesetAction.queryByNoticeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 语音公告
	 * VmsNoticeAction
	 */
	VmsSoundAction soundAction = new VmsSoundAction();
	public String VmsSound_Insert(String security,String parameter) {
		return soundAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsSound_Update(String security,String parameter) {
		return soundAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String VmsSound_Delete(String security,String parameter) {
		return soundAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsSound_QueryByPK(String security,String parameter) {
		return soundAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String VmsSound_QueryByOrganizeSeq(String security,String parameter) {
		return soundAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsSound_QueryPageByCustom(String security,String parameter) {
		return soundAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsSound_QueryAllByCustom(String security,String parameter) {
		return soundAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsSound_QueryCountByCustom(String security,String parameter) {
		return soundAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String VmsSound_QueryByStatusAndTime(String security,String parameter) {
		return noticeAction.queryByStatusAndTime(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	/**
	 * 文本公告设置
	 * VmsNoticesetAction
	 */
	VmsSoundsetAction soundsetAction = new VmsSoundsetAction();

	public String VmsSoundset_Update(String security,String parameter) {
		return soundsetAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsSoundset_QueryByPK(String security,String parameter) {
		return soundsetAction.queryByNoticeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	/**
	 * 播音班次设置
	 */
	VmsLinerAction vmsLinerAction = new VmsLinerAction();
	public String VmsLiner_Insert(String security,String parameter) {
		return vmsLinerAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsLiner_Update(String security,String parameter) {
		return vmsLinerAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsLiner_Delete(String security,String parameter) {
		return vmsLinerAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsLiner_DeleteByLinerDate(String security,String parameter) {
		return vmsLinerAction.deleteByLinerDate(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsLiner_QueryByPK(String security,String parameter) {
		return vmsLinerAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsLiner_QueryByOrganizeSeq(String security,String parameter) {
		return vmsLinerAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsLiner_QueryPageByCustom(String security,String parameter) {
		return vmsLinerAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsLiner_QueryAllByCustom(String security,String parameter) {
		return vmsLinerAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String VmsLiner_QueryCountByCustom(String security,String parameter) {
		return vmsLinerAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String VmsLiner_QueryAllByLinerTime(String security,String parameter) {
		return vmsLinerAction.queryAllByLinerTime(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String VmsLiner_ImportTrafficLiner(String security,String parameter) {
		return vmsLinerAction.importTrafficLiner(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

}