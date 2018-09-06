package com.zhima.basic;

import com.service.traffic.action.voice.VmsLinerAction;
import com.service.traffic.action.voice.VmsNoticeAction;
import com.service.traffic.action.voice.VmsNoticesetAction;
import com.service.traffic.action.voice.VmsParameterAction;
import com.service.traffic.action.voice.VmsRouteAction;
import com.service.traffic.action.voice.VmsSoundAction;
import com.service.traffic.action.voice.VmsSoundsetAction;
import com.service.traffic.action.voice.VmsTemplateAction;


/**
 * SamFinal概要说明：系统服务接口定义
 * @author lcy
 */
public class VmsFinal {
	public static String ServerAddres="http://127.0.0.1:8080/axis2/services/StationVmsServer";
	public static String ServerNamespace="http://traffic.service.com";

	/**
	 * 播音参数
	 */
	VmsParameterAction vmsParameterAction = new VmsParameterAction();
	public static final String VmsParameter_Update="VmsParameter_Update";
	public static final String VmsParameter_QueryByOrganizeSeq="VmsParameter_QueryByOrganizeSeq";
	public static final String VmsParameter_QueryByParameterCode="VmsParameter_QueryByParameterCode";
	
	/**
	 * 播音模板
	 */
	VmsTemplateAction templateAction = new VmsTemplateAction();
	public static final String VmsTemplate_Insert = "VmsTemplate_Insert";
	public static final String VmsTemplate_Update="VmsTemplate_Update";
	public static final String VmsTemplate_Delete = "VmsTemplate_Delete";
	public static final String VmsTemplate_QueryByOrganizeAndType="VmsTemplate_QueryByOrganizeAndType";
	
	/**
	 * 播音线路
	 */
	VmsRouteAction routeAction = new VmsRouteAction();
	public static final String VmsRoute_Insert = "VmsRoute_Insert";
	public static final String VmsRoute_Inserts = "VmsRoute_Inserts";
	public static final String VmsRoute_Update = "VmsRoute_Update";
	public static final String VmsRoute_Delete = "VmsRoute_Delete";
	public static final String VmsRoute_QueryByPK = "VmsRoute_QueryByPK";
	public static final String VmsRoute_QueryByOrganizeSeq = "VmsRoute_QueryByOrganizeSeq";
	public static final String VmsRoute_QueryPageByCustom = "VmsRoute_QueryPageByCustom";
	public static final String VmsRoute_QueryAllByCustom = "VmsRoute_QueryAllByCustom";
	public static final String VmsRoute_QueryCountByCustom = "VmsRoute_QueryCountByCustom";
	public static final String VmsRoute_QueryTrafficRoute = "VmsRoute_QueryTrafficRoute";
	
	/**
	 * 文本公告
	 */
	VmsNoticeAction noticeAction = new VmsNoticeAction();
	public static final String VmsNotice_Insert = "VmsNotice_Insert";
	public static final String VmsNotice_Update = "VmsNotice_Update";
	public static final String VmsNotice_Delete = "VmsNotice_Delete";
	public static final String VmsNotice_QueryByPK = "VmsNotice_QueryByPK";
	public static final String VmsNotice_QueryPageByOrganizeSeq = "VmsNotice_QueryPageByOrganizeSeq";
	public static final String VmsNotice_QueryByOrganizeSeq = "VmsNotice_QueryByOrganizeSeq";
	public static final String VmsNotice_QueryCountByOrganizeSeq = "VmsNotice_QueryCountByOrganizeSeq";
	public static final String VmsNotice_QueryPageByCustom = "VmsNotice_QueryPageByCustom";
	public static final String VmsNotice_QueryAllByCustom = "VmsNotice_QueryAllByCustom";
	public static final String VmsNotice_QueryCountByCustom = "VmsNotice_QueryCountByCustom";
	public static final String VmsNotice_QueryByStatusAndTime = "VmsNotice_QueryByStatusAndTime";
	
	/**
	 * 文本公告设置
	 */
	VmsNoticesetAction noticesetAction = new VmsNoticesetAction();
	public static final String VmsNoticeset_update = "VmsNoticeset_update";
	public static final String VmsNoticeset_queryByNoticeSeq = "VmsNoticeset_queryByNoticeSeq";
	
	/**
	 * 语音公告
	 */
	VmsSoundAction soundAction = new VmsSoundAction();
	public static final String VmsSound_Inserts = "VmsSound_Inserts";
	public static final String VmsSound_Update = "VmsSound_Update";
	public static final String VmsSound_Delete = "VmsSound_Delete";
	public static final String VmsSound_QueryByPK = "VmsSound_QueryByPK";
	public static final String VmsSound_QueryByOrganizeSeq = "VmsSound_QueryByOrganizeSeq";
	public static final String VmsSound_QueryPageByCustom = "VmsSound_QueryPageByCustom";
	public static final String VmsSound_QueryAllByCustom = "VmsSound_QueryAllByCustom";
	public static final String VmsSound_QueryCountByCustom = "VmsSound_QueryCountByCustom";
	public static final String VmsSound_QueryByStatusAndTime = "VmsSound_QueryByStatusAndTime";
	
	/**
	 * 语音公告设置
	 */
	VmsSoundsetAction soundsetAction = new VmsSoundsetAction();
	public static final String VmsSoundset_update = "VmsSoundset_update";
	public static final String VmsSoundset_queryBySoundSeq = "VmsSoundset_queryBySoundSeq";

	/**
	 * 语音班次设置
	 */
	VmsLinerAction vmsLinerAction = new VmsLinerAction();
	public static final String VmsLiner_Insert = "VmsLiner_Insert";
	public static final String VmsLiner_Update = "VmsLiner_Update";
	public static final String VmsLiner_Delete = "VmsLiner_Delete";
	public static final String VmsLiner_DeleteByLinerDate = "VmsLiner_DeleteByLinerDate";
	public static final String VmsLiner_QueryByPK = "VmsLiner_QueryByPK";
	public static final String VmsLiner_QueryByOrganizeSeq = "VmsLiner_QueryByOrganizeSeq";
	public static final String VmsLiner_QueryPageByCustom = "VmsLiner_QueryPageByCustom";
	public static final String VmsLiner_QueryAllByCustom = "VmsLiner_QueryAllByCustom";
	public static final String VmsLiner_QueryCountByCustom = "VmsLiner_QueryCountByCustom";
	public static final String VmsLiner_QueryByStatusAndTime = "VmsLiner_QueryByStatusAndTime";
	public static final String VmsLiner_ImportTrafficLiner = "VmsLiner_ImportTrafficLiner";
	
}
