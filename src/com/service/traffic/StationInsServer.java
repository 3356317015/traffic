package com.service.traffic;

import com.service.traffic.action.insurance.InsCompanyAction;
import com.service.traffic.action.insurance.InsPremiumAction;
import com.service.traffic.action.insurance.InsTicketpoolAction;
import com.service.traffic.action.insurance.InsTickettypeAction;
import com.zhima.util.DesUtils;


public class StationInsServer {
	DesUtils desUtils = new DesUtils();

	/**
	 * 公司
	 * EpdCompanyAction
	 */
	InsCompanyAction companyAction = new InsCompanyAction();
	public String InsCompany_Insert(String security,String parameter) {
		return companyAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsCompany_Update(String security,String parameter) {
		return companyAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsCompany_Delete(String security,String parameter) {
		return companyAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsCompany_QueryByOrganizeSeq(String security,String parameter) {
		return companyAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String InsCompany_QueryPageByCustom(String security,String parameter) {
		return companyAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsCompany_QueryAllByCustom(String security,String parameter) {
		return companyAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsCompany_QueryCountByCustom(String security,String parameter) {
		return companyAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 保险费率
	 * EpdPremiumAction
	 */
	InsPremiumAction premiumAction = new InsPremiumAction();
	public String InsPremium_Insert(String security,String parameter) {
		return premiumAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsPremium_Update(String security,String parameter) {
		return premiumAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsPremium_Delete(String security,String parameter) {
		return premiumAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsPremium_QueryByOrganizeSeq(String security,String parameter) {
		return premiumAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String InsPremium_QueryPageByCustom(String security,String parameter) {
		return premiumAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsPremium_QueryAllByCustom(String security,String parameter) {
		return premiumAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String InsPremium_QueryCountByCustom(String security,String parameter) {
		return premiumAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 票据类型
	 * InsTickettypeAction
	 */
	InsTickettypeAction tickettypeAction = new InsTickettypeAction();
	public String InsTickettype_Insert(String security,String parameter) {
		return tickettypeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTickettype_Update(String security,String parameter) {
		return tickettypeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTickettype_Delete(String security,String parameter) {
		return tickettypeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTickettype_QueryByPK(String security,String parameter) {
		return tickettypeAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTickettype_QueryByOrganizeSeq(String security,String parameter) {
		return tickettypeAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTickettype_QueryPageByCustom(String security,String parameter) {
		return tickettypeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTickettype_QueryAllByCustom(String security,String parameter) {
		return tickettypeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTickettype_QueryCountByCustom(String security,String parameter) {
		return tickettypeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	/**
	 * 票据类型
	 * InsTickettypeAction
	 */
	InsTicketpoolAction ticketpoolAction = new InsTicketpoolAction();
	public String InsTicketpool_Insert(String security,String parameter) {
		return ticketpoolAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTicketpool_Update(String security,String parameter) {
		return ticketpoolAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTicketpool_Delete(String security,String parameter) {
		return ticketpoolAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTicketpool_QueryByPK(String security,String parameter) {
		return ticketpoolAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTicketpool_QueryByOrganizeSeq(String security,String parameter) {
		return ticketpoolAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTicketpool_QueryPageByCustom(String security,String parameter) {
		return ticketpoolAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTicketpool_QueryAllByCustom(String security,String parameter) {
		return ticketpoolAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTicketpool_QueryCountByCustom(String security,String parameter) {
		return ticketpoolAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String InsTicketpool_QueryValid(String security,String parameter) {
		return ticketpoolAction.queryValid(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

}