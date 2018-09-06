
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdDriverinfo;

public class EpdDriverinfoDao extends BaseDao{
	public EpdDriverinfoDao(Connection conn){
		super(conn);
	}
	
	public EpdDriverinfo insert(EpdDriverinfo epdDriverinfo, Map<String, Object> config){
		String pk = super.insert(epdDriverinfo,config);
		epdDriverinfo.setDriverinfoSeq(pk);
		return epdDriverinfo;
	}
	
	public void update(EpdDriverinfo epdDriverinfo, Map<String, Object> config){
		super.update(epdDriverinfo,config);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateBatchByCardName(String cerName, String oldCerName) {
		String strSql = "update epd_driverinfo set card_name = ? where card_name = ?";
		List params = new ArrayList();
		params.add(cerName);
		params.add(oldCerName);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String driverinfoSeq){
		String strSql = "delete from epd_driverinfo where driverinfo_seq=?";
		List params = new ArrayList();
		params.add(driverinfoSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByCardName(String cardName){
		String strSql = "delete from epd_driverinfo where card_name=?";
		List params = new ArrayList();
		params.add(cardName);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByDriverSeq(String driverSeq) {
		String strSql = "delete from epd_driverinfo where driver_seq=?";
		List params = new ArrayList();
		params.add(driverSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDriverinfo> queryByValid(EpdDriverinfo epdDriverinfo) {
		StringBuffer strSql = new StringBuffer("select * from epd_driverinfo where driver_seq = ? and card_name = ?");
		List params = new ArrayList();
		params.add(epdDriverinfo.getDriverSeq());
		params.add(epdDriverinfo.getCardName());
		String driverinfoSeq = epdDriverinfo.getDriverinfoSeq();
		if (null != driverinfoSeq && !"".equals(driverinfoSeq)){
			strSql.append(" and driverinfo_seq <> ?");
			params.add(driverinfoSeq);
		}
		List<EpdDriverinfo> driverinfos = (List<EpdDriverinfo>) super.queryAll(strSql.toString(),
				params,new EpdDriverinfo());
		return driverinfos;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDriverinfo> queryByPK(String driverinfoSeq){
		String strSql = "select * from epd_driverinfo where driverinfo_seq=?";
		List params = new ArrayList();
		params.add(driverinfoSeq);
		List<EpdDriverinfo> driverinfos = (List<EpdDriverinfo>) super.queryAll(strSql.toString(),
				params,new EpdDriverinfo());
		return driverinfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdDriverinfo> queryByAll(){
		String strSql = "select * from epd_driverinfo";
		List<EpdDriverinfo> driverinfos = (List<EpdDriverinfo>) super.queryAll(strSql.toString(),
				null,new EpdDriverinfo());
		return driverinfos;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDriverinfo> queryByDriverSeq(String driverSeq) {
		String strSql = "select * from epd_driverinfo where driver_seq=?";
		List params = new ArrayList();
		params.add(driverSeq);
		List<EpdDriverinfo> driverinfos = (List<EpdDriverinfo>) super.queryAll(strSql.toString(),
				params,new EpdDriverinfo());
		return driverinfos;
	}

}
