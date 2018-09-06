
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdServiceright;;


public class EpdServicerightDao extends BaseDao{
	public EpdServicerightDao(Connection conn){
		super(conn);
	}
	
	public EpdServiceright insert(EpdServiceright epdServiceright, Map<String, Object> config){
		String pk = super.insert(epdServiceright,config);
		epdServiceright.setRightSeq(pk);
		return epdServiceright;
	}
	
	public void update(EpdServiceright epdServiceright, Map<String, Object> config){
		super.update(epdServiceright,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String rightSeq){
		String strSql = "delete from epd_serviceright where right_seq=?";
		List params = new ArrayList();
		params.add(rightSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdServiceright> queryByServiceSeq(String serviceSeq) {
		StringBuffer strSql = new StringBuffer("select * from epd_serviceright where 1=1");
		List params = new ArrayList();
		if (null != serviceSeq && !"".equals(serviceSeq)){
			strSql.append(" and service_seq = ?");
			params.add(serviceSeq);
		}
		List<EpdServiceright> servicerights = (List<EpdServiceright>) super.queryAll(
				strSql.toString(),params,new EpdServiceright());
		return servicerights;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByServiceSeq(String serviceSeq) {
		String strSql = "delete from epd_serviceright where service_seq=?";
		List params = new ArrayList();
		params.add(serviceSeq);
		super.executeSql(strSql, params);
	}

}
