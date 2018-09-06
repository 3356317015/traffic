package com.service.traffic;

import com.service.traffic.action.its.ItsLinerAction;
import com.service.traffic.action.its.ItsLinerFareAction;
import com.service.traffic.action.its.ItsLinercheckAction;
import com.service.traffic.action.its.ItsLinerdealAction;
import com.service.traffic.action.its.ItsLinerseatAction;
import com.service.traffic.action.its.ItsLinerserviceAction;
import com.service.traffic.action.its.ItsLinerstationAction;
import com.service.traffic.action.its.ItsTicketpoolAction;
import com.zhima.util.DesUtils;

/**
 * StationSamServer概要说明：系统服务接口
 * @author lcy
 */
public class StationItsServer {
	DesUtils desUtils = new DesUtils();
	
	ItsLinerAction linerAction = new ItsLinerAction();
	public String ItsLiner_Insert(String security,String parameter){
		return linerAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	};
	
	public String ItsLiner_UpdateLiner(String security,String parameter){
		return linerAction.updateLiner(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	};
	
	public String ItsLiner_Delete(String security,String parameter){
		return linerAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	};
	
	public String ItsLiner_QueryByPK(String security,String parameter){
		return linerAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	};
	
	public String ItsLiner_QueryPageByCustom(String security,String parameter){
		return linerAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	};
	
	public String ItsLiner_QueryCountByCustom(String security,String parameter){
		return linerAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	};
	
	public String ItsLiner_UpdateAttribute(String security,String parameter){
		return linerAction.updateAttribute(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	};
	
	
	/**
	 * 班次价格
	 */
	ItsLinerFareAction linerFareAction = new ItsLinerFareAction();
	public String ItsLineFare_Insert(String security,String parameter){
		return linerFareAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	};

	public String ItsLineFare_Update(String security,String parameter){
		return linerFareAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ItsLineFare_Delete(String security,String parameter){
		return linerFareAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ItsLineFare_QueryByPK(String security,String parameter){
		return linerFareAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String ItsLineFare_QueryByLinerSeq(String security,String parameter){
		return linerFareAction.queryByLinerSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String ItsLineFare_QueryByAll(String security,String parameter){
		return linerFareAction.queryByAll(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ItsLineFare_QueryPageByCustom(String security,String parameter){
		return linerFareAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ItsLineFare_QueryAllByCustom(String security,String parameter){
		return linerFareAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ItsLineFare_QueryCountByCustom(String security,String parameter){
		return linerFareAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String ItsLineFare_UpdateAttribute(String security,String parameter){
		return linerFareAction.updateAttribute(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	/**
	 * 班次站点
	 */
	ItsLinerstationAction linerstationAction = new ItsLinerstationAction();
	public String ItsLinerstation_QueryByLinerSeq(String security,String parameter){
		return linerstationAction.queryByLinerSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsLinerstation_UpdateAttribute(String security,String parameter){
		return linerstationAction.updateAttribute(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 班次检票口
	 */
	ItsLinercheckAction linercheckAction = new ItsLinercheckAction();
	public String ItsLinercheck_QueryByLinerSeq(String security,String parameter){
		return linercheckAction.queryByLinerSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsLinercheck_UpdateAttribute(String security,String parameter){
		return linercheckAction.updateAttribute(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 班次配载
	 */
	ItsLinerdealAction linerdealAction = new ItsLinerdealAction();
	public String ItsLinerdeal_QueryByLinerSeq(String security,String parameter){
		return linerdealAction.queryByLinerSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsLinerdeal_UpdateAttribute(String security,String parameter){
		return linerdealAction.updateAttribute(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	ItsLinerserviceAction linerserviceAction = new ItsLinerserviceAction();
	public String ItsLinerservice_QueryByLinerSeq(String security,String parameter){
		return linerserviceAction.queryByLinerSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsLinerservice_UpdateAttribute(String security,String parameter){
		return linerserviceAction.updateAttribute(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 班次座位
	 */
	ItsLinerseatAction linerseatAction = new ItsLinerseatAction();
	public String ItsLinerseat_UpdateAttribute(String security,String parameter){
		return linerseatAction.updateAttribute(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsLinerseat_QueryByLinerSeq(String security,String parameter){
		return linerseatAction.queryByLinerSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsLinerseat_QueryPageByLinerSeq(String security,String parameter){
		return linerseatAction.queryPageByLinerSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsLinerseat_QueryCountByLinerSeq(String security,String parameter){
		return linerseatAction.queryCountByLinerSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	

	/**
	 * 票据类型
	 * ItsTicketpoolAction
	 */
	ItsTicketpoolAction ticketpoolAction = new ItsTicketpoolAction();
	public String ItsTicketpool_Insert(String security,String parameter) {
		return ticketpoolAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsTicketpool_Update(String security,String parameter) {
		return ticketpoolAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsTicketpool_Delete(String security,String parameter) {
		return ticketpoolAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsTicketpool_QueryByPK(String security,String parameter) {
		return ticketpoolAction.queryByPK(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsTicketpool_QueryByOrganizeSeq(String security,String parameter) {
		return ticketpoolAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsTicketpool_QueryPageByCustom(String security,String parameter) {
		return ticketpoolAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsTicketpool_QueryAllByCustom(String security,String parameter) {
		return ticketpoolAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String ItsTicketpool_QueryCountByCustom(String security,String parameter) {
		return ticketpoolAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
}