
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.ItsTicketpool;

public class ItsTicketpoolDao extends BaseDao{
	public ItsTicketpoolDao(Connection conn){
		super(conn);
	}
	
	public ItsTicketpool insert(ItsTicketpool itsTicketpool,Map<String, Object> config){
		String pk = super.insert(itsTicketpool,config);
		itsTicketpool.setTicketpoolSeq(pk);
		return itsTicketpool;
	}
	
	public void update(ItsTicketpool itsTicketpool,Map<String, Object> config){
		super.update(itsTicketpool,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String ticketpoolSeq){
		String strSql = "delete from its_ticketpool where ticketpool_seq=?";
		List params = new ArrayList();
		params.add(ticketpoolSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsTicketpool> queryByValid(ItsTicketpool itsTicketpool) {
		StringBuffer strSql = new StringBuffer("select * from its_ticketpool where organize_seq =? and ticket_name = ? and oper_type = ?");
		List params = new ArrayList();
		params.add(itsTicketpool.getOrganizeSeq());
		params.add(itsTicketpool.getTicketName());
		params.add(itsTicketpool.getOperType());
		String ticketpoolSeq = itsTicketpool.getTicketpoolSeq();
		if (null != ticketpoolSeq && !"".equals(ticketpoolSeq)){
			strSql.append(" and ticketpool_seq <> ?");
			params.add(ticketpoolSeq);
		}
		
		strSql.append(" and ((ticket_start<= ? and ticket_limit>=?) or (ticket_start<= ? and ticket_limit>=?))");
		params.add(itsTicketpool.getTicketStart());
		params.add(itsTicketpool.getTicketStart());
		params.add(itsTicketpool.getTicketLimit());
		params.add(itsTicketpool.getTicketLimit());

		List<ItsTicketpool> ticketpools = (List<ItsTicketpool>) super.queryAll(strSql.toString(),
				params,new ItsTicketpool());
		return ticketpools;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsTicketpool> queryByPK(String ticketpoolSeq){
		String strSql = "select * from its_ticketpool where ticketpool_seq=?";
		List params = new ArrayList();
		params.add(ticketpoolSeq);
		List<ItsTicketpool> ticketpools = (List<ItsTicketpool>) super.queryAll(strSql,params,new ItsTicketpool());
		return ticketpools;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsTicketpool> queryByOrganizeSeq(String organizeSeq) {
		String strSql = "select * from its_ticketpool where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<ItsTicketpool> ticketpools = (List<ItsTicketpool>) super.queryAll(strSql,params,new ItsTicketpool());
		return ticketpools;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsTicketpool> queryPageByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus, int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from its_ticketpool where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != tickettypeSeq && !"".equals(tickettypeSeq)){
			strSql.append(" and tickettype_seq = ?");
			params.add(tickettypeSeq);
		}
		if (null != operType && !"".equals(operType)){
			strSql.append(" and oper_type = ?");
			params.add(operType);
		}
		if (null != userCode && !"".equals(userCode)){
			strSql.append(" and user_code = ?");
			params.add(userCode);
		}
		if (null != poolStatus && !"".equals(poolStatus)){
			strSql.append(" and pool_status = ?");
			params.add(poolStatus);
		}
		strSql.append(" order by ticket_name,oper_type,user_code");
		List<ItsTicketpool> ticketpools = (List<ItsTicketpool>) super.queryPage(strSql.toString(),
				params,new ItsTicketpool(),start,limit);
		return ticketpools;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsTicketpool> queryAllByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus){
		StringBuffer strSql = new StringBuffer("select * from its_ticketpool where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != tickettypeSeq && !"".equals(tickettypeSeq)){
			strSql.append(" and tickettype_seq = ?");
			params.add(tickettypeSeq);
		}
		if (null != operType && !"".equals(operType)){
			strSql.append(" and oper_type = ?");
			params.add(operType);
		}
		if (null != userCode && !"".equals(userCode)){
			strSql.append(" and user_code = ?");
			params.add(userCode);
		}
		if (null != poolStatus && !"".equals(poolStatus)){
			strSql.append(" and pool_status = ?");
			params.add(poolStatus);
		}
		if (null != operType && !"".equals(operType)){
			if("1".equals(operType)){
				strSql.append(" order by ticket_name,oper_type,ticket_start");
			}else{
				strSql.append(" order by ticket_name,oper_type,user_code,ticket_start");
			}
		}
		
		List<ItsTicketpool> ticketpools = (List<ItsTicketpool>) super.queryAll(strSql.toString(),params,new ItsTicketpool());
		return ticketpools;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus){
		StringBuffer strSql = new StringBuffer("select count(1) from its_ticketpool where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != tickettypeSeq && !"".equals(tickettypeSeq)){
			strSql.append(" and tickettype_seq = ?");
			params.add(tickettypeSeq);
		}
		if (null != operType && !"".equals(operType)){
			strSql.append(" and oper_type = ?");
			params.add(operType);
		}
		if (null != userCode && !"".equals(userCode)){
			strSql.append(" and user_code = ?");
			params.add(userCode);
		}
		if (null != poolStatus && !"".equals(poolStatus)){
			strSql.append(" and pool_status = ?");
			params.add(poolStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}

}
