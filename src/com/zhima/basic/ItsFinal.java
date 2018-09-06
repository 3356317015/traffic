package com.zhima.basic;

import com.service.traffic.action.its.ItsLinerAction;
import com.service.traffic.action.its.ItsLinerFareAction;
import com.service.traffic.action.its.ItsLinercheckAction;
import com.service.traffic.action.its.ItsLinerdealAction;
import com.service.traffic.action.its.ItsLinerseatAction;
import com.service.traffic.action.its.ItsLinerserviceAction;
import com.service.traffic.action.its.ItsLinerstationAction;
import com.service.traffic.action.its.ItsTicketpoolAction;


/**
 * SamFinal概要说明：系统服务接口定义
 * @author lcy
 */
public class ItsFinal {
	public static String ServerAddres="http://127.0.0.1:8080/axis2/services/StationItsServer";
	public static String ServerNamespace="http://traffic.service.com";

	ItsLinerFareAction linerfareAction = new ItsLinerFareAction();
	public static final String ItsLinerFare_Insert="ItsLinerFare_Insert";
	public static final String ItsLinerFare_Update="ItsLinerFare_Update";
	public static final String ItsLinerFare_UpdateAttribute="ItsLinerFare_UpdateAttribute";
	public static final String ItsLinerFare_Delete="ItsLinerFare_Delete";
	public static final String ItsLinerFare_QueryByPK="ItsLinerFare_QueryByPK";
	public static final String ItsLinerFare_QueryByLinerSeq="ItsLinerFare_QueryByLinerSeq";
	public static final String ItsLinerFare_QueryByAll="ItsLinerFare_QueryByAll";
	public static final String ItsLinerFare_QueryPageByCustom="ItsLinerFare_QueryPageByCustom";
	public static final String ItsLinerFare_QueryAllByCustom="ItsLinerFare_QueryAllByCustom";
	public static final String ItsLinerFare_QueryCountByCustom="ItsLinerFare_QueryCountByCustom";

	ItsLinerAction linerAction = new ItsLinerAction();
	public static final String ItsLiner_Insert="ItsLiner_Insert";
	public static final String ItsLiner_UpdateLiner="ItsLiner_UpdateLiner";
	public static final String ItsLiner_UpdateAttribute="ItsLiner_UpdateAttribute";
	public static final String ItsLiner_Delete="ItsLiner_Delete";
	public static final String ItsLiner_QueryByPK="ItsLiner_QueryByPK";
	public static final String ItsLiner_QueryPageByCustom="ItsLiner_QueryPageByCustom";
	public static final String ItsLiner_QueryCountByCustom="ItsLiner_QueryCountByCustom";
	
	ItsLinerstationAction linerstationAction = new ItsLinerstationAction();
	public static final String ItsLinerstation_UpdateAttribute="ItsLinerstation_UpdateAttribute";
	public static final String ItsLinerstation_QueryByLinerSeq="ItsLinerstation_QueryByLinerSeq";
	
	ItsLinerserviceAction linerserviceAction = new ItsLinerserviceAction();
	public static final String ItsLinerservice_UpdateAttribute="ItsLinerservice_UpdateAttribute";
	public static final String ItsLinerservice_QueryByLinerSeq="ItsLinerservice_QueryByLinerSeq";
	
	ItsLinercheckAction linercheckAction = new ItsLinercheckAction();
	public static final String ItsLinercheck_UpdateAttribute="ItsLinercheck_UpdateAttribute";
	public static final String ItsLinercheck_QueryByLinerSeq="ItsLinercheck_QueryByLinerSeq";
	
	ItsLinerdealAction linerdealAction = new ItsLinerdealAction();
	public static final String ItsLinerdeal_QueryByLinerSeq = "ItsLinerdeal_QueryByLinerSeq";
	public static final String ItsLinerdeal_UpdateAttribute="ItsLinerdeal_UpdateAttribute";
	
	ItsLinerseatAction linerseatAction = new ItsLinerseatAction();
	public static final String ItsLinerseat_UpdateAttribute="ItsLinerseat_UpdateAttribute";
	public static final String ItsLinerseat_QueryByLinerSeq="ItsLinerseat_QueryByLinerSeq";
	public static final String ItsLinerseat_QueryPageByLinerSeq="ItsLinerseat_QueryPageByLinerSeq";
	public static final String ItsLinerseat_QueryCountByLinerSeq="ItsLinerseat_QueryCountByLinerSeq";
	
	/**
	 * 票据名称
	 * ticketpoolAction
	 */
	ItsTicketpoolAction ticketpoolAction = new ItsTicketpoolAction();
	public static final String ItsTicketpool_Insert = "ItsTicketpool_Insert";
	public static final String ItsTicketpool_Update = "ItsTicketpool_Update";
	public static final String ItsTicketpool_Send = "ItsTicketpool_Send";
	public static final String ItsTicketpool_Delete = "ItsTicketpool_Delete";
	public static final String ItsTicketpool_QueryByPK = "ItsTicketpool_QueryByPK";
	public static final String ItsTicketpool_QueryByOrganizeSeq = "ItsTicketpool_QueryByOrganizeSeq";
	public static final String ItsTicketpool_QueryPageByCustom = "ItsTicketpool_QueryPageByCustom";
	public static final String ItsTicketpool_QueryAllByCustom = "ItsTicketpool_QueryAllByCustom";
	public static final String ItsTicketpool_QueryCountByCustom = "ItsTicketpool_QueryCountByCustom";
	
}
