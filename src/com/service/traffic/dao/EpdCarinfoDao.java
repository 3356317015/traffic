
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdCarinfo;

public class EpdCarinfoDao extends BaseDao{
	public EpdCarinfoDao(Connection conn){
		super(conn);
	}
	
	public EpdCarinfo insert(EpdCarinfo epdCarinfo,Map<String, Object> config){
		String pk = super.insert(epdCarinfo,config);
		epdCarinfo.setCarinfoSeq(pk);
		return epdCarinfo;
	}
	
	public void update(EpdCarinfo epdCarinfo,Map<String, Object> config){
		super.update(epdCarinfo,config);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateBatchByCardName(String cerName, String oldCerName) {
		String strSql = "update epd_carinfo set card_name = ? where card_name = ?";
		List params = new ArrayList();
		params.add(cerName);
		params.add(oldCerName);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String carinfoSeq){
		String strSql = "delete from epd_carinfo where carinfo_seq=?";
		List params = new ArrayList();
		params.add(carinfoSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByCardName(String cardName){
		String strSql = "delete from epd_carinfo where card_name=?";
		List params = new ArrayList();
		params.add(cardName);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByCarSeq(String carSeq) {
		String strSql = "delete from epd_carinfo where car_seq=?";
		List params = new ArrayList();
		params.add(carSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCarinfo> queryByValid(EpdCarinfo epdCarinfo) {
		StringBuffer strSql = new StringBuffer("select * from epd_carinfo where car_seq = ? and card_name = ?");
		List params = new ArrayList();
		params.add(epdCarinfo.getCarSeq());
		params.add(epdCarinfo.getCardName());
		String carinfoSeq = epdCarinfo.getCarinfoSeq();
		if (null != carinfoSeq && !"".equals(carinfoSeq)){
			strSql.append(" and carinfo_seq <> ?");
			params.add(carinfoSeq);
		}
		List<EpdCarinfo> carinfos = (List<EpdCarinfo>) super.queryAll(strSql.toString(),
				params,new EpdCarinfo());
		return carinfos;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCarinfo> queryByPK(String carinfoSeq){
		String strSql = "select * from epd_carinfo where carinfo_seq=?";
		List params = new ArrayList();
		params.add(carinfoSeq);
		List<EpdCarinfo> carinfos = (List<EpdCarinfo>) super.queryAll(strSql.toString(),
				params,new EpdCarinfo());
		return carinfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdCarinfo> queryByAll(){
		String strSql = "select * from epd_carinfo";
		List<EpdCarinfo> carinfos = (List<EpdCarinfo>) super.queryAll(strSql.toString(),
				null,new EpdCarinfo());
		return carinfos;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCarinfo> queryByCarSeq(String carSeq) {
		String strSql = "select * from epd_carinfo where car_seq=?";
		List params = new ArrayList();
		params.add(carSeq);
		List<EpdCarinfo> carinfos = (List<EpdCarinfo>) super.queryAll(strSql.toString(),
				params,new EpdCarinfo());
		return carinfos;
	}

}
