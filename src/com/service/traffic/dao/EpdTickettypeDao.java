
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdTickettype;

public class EpdTickettypeDao extends BaseDao{
	public EpdTickettypeDao(Connection conn){
		super(conn);
	}
	
	public EpdTickettype insert(EpdTickettype epdTickettype,Map<String, Object> config){
		String pk = super.insert(epdTickettype,config);
		epdTickettype.setTickettypeSeq(pk);
		return epdTickettype;
	}
	
	public void update(EpdTickettype epdTickettype,Map<String, Object> config){
		super.update(epdTickettype,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String tickettypeSeq){
		String strSql = "delete from epd_tickettype where tickettype_seq=?";
		List params = new ArrayList();
		params.add(tickettypeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdTickettype> queryByValid(EpdTickettype epdTickettype) {
		StringBuffer strSql = new StringBuffer("select * from epd_tickettype where organize_seq = ? and category = ? and ticket_name = ?");
		List params = new ArrayList();
		params.add(epdTickettype.getOrganizeSeq());
		params.add(epdTickettype.getCategory());
		params.add(epdTickettype.getTicketName());
		String tickettypeSeq = epdTickettype.getTickettypeSeq();
		if (null != tickettypeSeq && !"".equals(tickettypeSeq)){
			strSql.append(" and tickettype_seq <> ?");
			params.add(tickettypeSeq);
		}
		List<EpdTickettype> tickettypes = (List<EpdTickettype>) super.queryAll(strSql.toString(),
				params,new EpdTickettype());
		return tickettypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdTickettype> queryByPK(String tickettypeSeq){
		String strSql = "select * from epd_tickettype where tickettype_seq=?";
		List params = new ArrayList();
		params.add(tickettypeSeq);
		List<EpdTickettype> tickettypes = (List<EpdTickettype>) super.queryAll(strSql,params,new EpdTickettype());
		return tickettypes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdTickettype> queryByOrganizeSeq(String organizeSeq) {
		String strSql = "select * from epd_tickettype where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<EpdTickettype> tickettypes = (List<EpdTickettype>) super.queryAll(strSql,params,new EpdTickettype());
		return tickettypes;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdTickettype> queryPageByCustom(String organizeSeq,
			String category, int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_tickettype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != category && !"".equals(category)){
			strSql.append(" and category = ?");
			params.add(category);
		}
		strSql.append(" order by category,ticket_name");
		List<EpdTickettype> tickettypes = (List<EpdTickettype>) super.queryPage(strSql.toString(),
				params,new EpdTickettype(),start,limit);
		return tickettypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdTickettype> queryAllByCustom(String organizeSeq, String category){
		StringBuffer strSql = new StringBuffer("select * from epd_tickettype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != category && !"".equals(category)){
			strSql.append(" and category = ?");
			params.add(category);
		}
		strSql.append(" order by category,ticket_name");
		List<EpdTickettype> tickettypes = (List<EpdTickettype>) super.queryAll(strSql.toString(),params,new EpdTickettype());
		return tickettypes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String category){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_tickettype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != category && !"".equals(category)){
			strSql.append(" and category = ?");
			params.add(category);
		}
		return super.queryCount(strSql.toString(),params);
	}

}
