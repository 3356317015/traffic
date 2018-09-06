package com.zhima.basic;

import com.service.traffic.action.insurance.InsCompanyAction;
import com.service.traffic.action.insurance.InsPremiumAction;
import com.service.traffic.action.insurance.InsTicketpoolAction;
import com.service.traffic.action.insurance.InsTickettypeAction;


/**
 * SamFinal概要说明：系统服务接口定义
 * @author lcy
 */
public class InsFinal {
	public static String ServerAddres="http://127.0.0.1:8080/axis2/services/StationInsServer";
	public static String ServerNamespace="http://traffic.service.com";
	
	/**
	 * 公司
	 * InsCompanyAction
	 */
	InsCompanyAction companyAction = new InsCompanyAction();
	public static final String InsCompany_Insert = "InsCompany_Insert";
	public static final String InsCompany_Update = "InsCompany_Update";
	public static final String InsCompany_Delete = "InsCompany_Delete";
	public static final String InsCompany_QueryByOrganizeSeq = "InsCompany_QueryByOrganizeSeq";
	public static final String InsCompany_QueryPageByCustom = "InsCompany_QueryPageByCustom";
	public static final String InsCompany_QueryAllByCustom = "InsCompany_QueryAllByCustom";
	public static final String InsCompany_QueryCountByCustom = "InsCompany_QueryCountByCustom";
	
	/**
	 * 公司
	 * InsPremiumAction
	 */
	InsPremiumAction premiumAction = new InsPremiumAction();
	public static final String InsPremium_Insert = "InsPremium_Insert";
	public static final String InsPremium_Update = "InsPremium_Update";
	public static final String InsPremium_Delete = "InsPremium_Delete";
	public static final String InsPremium_QueryByOrganizeSeq = "InsPremium_QueryByOrganizeSeq";
	public static final String InsPremium_QueryPageByCustom = "InsPremium_QueryPageByCustom";
	public static final String InsPremium_QueryAllByCustom = "InsPremium_QueryAllByCustom";
	public static final String InsPremium_QueryCountByCustom = "InsPremium_QueryCountByCustom";
	
	/**
	 * 票据类型
	 * InsTickettypeAction
	 */
	InsTickettypeAction tickettypeAction = new InsTickettypeAction();
	public static final String InsTickettype_Insert = "InsTickettype_Insert";
	public static final String InsTickettype_Update = "InsTickettype_Update";
	public static final String InsTickettype_Delete = "InsTickettype_Delete";
	public static final String InsTickettype_QueryByPK = "InsTickettype_QueryByPK";
	public static final String InsTickettype_QueryByOrganizeSeq = "InsTickettype_QueryByOrganizeSeq";
	public static final String InsTickettype_QueryPageByCustom = "InsTickettype_QueryPageByCustom";
	public static final String InsTickettype_QueryAllByCustom = "InsTickettype_QueryAllByCustom";
	public static final String InsTickettype_QueryCountByCustom = "InsTickettype_QueryCountByCustom";
	
	/**
	 * 票据名称
	 * ticketpoolAction
	 */
	InsTicketpoolAction ticketpoolAction = new InsTicketpoolAction();
	public static final String InsTicketpool_Insert = "InsTicketpool_Insert";
	public static final String InsTicketpool_Update = "InsTicketpool_Update";
	public static final String InsTicketpool_Send = "InsTicketpool_Send";
	public static final String InsTicketpool_Delete = "InsTicketpool_Delete";
	public static final String InsTicketpool_QueryValid = "InsTicketpool_QueryValid";
	public static final String InsTicketpool_QueryByPK = "InsTicketpool_QueryByPK";
	public static final String InsTicketpool_QueryByOrganizeSeq = "InsTicketpool_QueryByOrganizeSeq";
	public static final String InsTicketpool_QueryPageByCustom = "InsTicketpool_QueryPageByCustom";
	public static final String InsTicketpool_QueryAllByCustom = "InsTicketpool_QueryAllByCustom";
	public static final String InsTicketpool_QueryCountByCustom = "InsTicketpool_QueryCountByCustom";
	
}
