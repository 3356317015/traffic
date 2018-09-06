
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.ItsTicket;

public class ItsTicketDao extends BaseDao{
	public ItsTicketDao(Connection conn){
		super(conn);
	}
	
	public ItsTicket insert(ItsTicket itsTicket,Map<String, Object> config){
		String pk = super.insert(itsTicket,config);
		itsTicket.setTicketSeq(pk);
		return itsTicket;
	}
	
	public void update(ItsTicket itsTicket,Map<String, Object> config){
		super.update(itsTicket,config);
	}

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsTicket> queryByPK(String ticketSeq){
		String strSql = "select * from its_ticket where ticket_seq=?";
		List params = new ArrayList();
		params.add(ticketSeq);
		List<ItsTicket> tickets = (List<ItsTicket>) super.queryAll(strSql,params,new ItsTicket());
		return tickets;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsTicket> queryByLinerSeq(String linerSeq){
		String strSql = "select its_ticket.*,sam_service.sale_type" +
				" from its_ticket" +
					" left join sam_service on sam_service.service_seq=its_ticket.sale_service" +
				" where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<ItsTicket> tickets = (List<ItsTicket>) super.queryAll(strSql,params,new ItsTicket());
		return tickets;
	}

}
