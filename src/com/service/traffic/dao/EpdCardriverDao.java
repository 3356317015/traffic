
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdCardriver;

public class EpdCardriverDao extends BaseDao{
	public EpdCardriverDao(Connection conn){
		super(conn);
	}
	
	public EpdCardriver insert(EpdCardriver epdCardriver,Map<String, Object> config){
		String pk = super.insert(epdCardriver,config);
		epdCardriver.setCardriverSeq(pk);
		return epdCardriver;
	}
	
	public void update(EpdCardriver epdCardriver,Map<String, Object> config){
		super.update(epdCardriver,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String cardriverSeq){
		String strSql = "delete from epd_cardriver where cardriver_seq=?";
		List params = new ArrayList();
		params.add(cardriverSeq);
		super.executeSql(strSql, params);
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByCarSeq(String carSeq) {
		String strSql = "delete from epd_cardriver where car_seq=?";
		List params = new ArrayList();
		params.add(carSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCardriver> queryByPK(String cardriverSeq){
		String strSql = "select * from epd_cardriver where cardriver_seq=?";
		List params = new ArrayList();
		params.add(cardriverSeq);
		List<EpdCardriver> cardrivers = (List<EpdCardriver>) super.queryAll(strSql.toString(),
				params,new EpdCardriver());
		return cardrivers;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdCardriver> queryByAll(){
		String strSql = "select * from epd_cardriver";
		List<EpdCardriver> cardrivers = (List<EpdCardriver>) super.queryAll(strSql.toString(),
				null,new EpdCardriver());
		return cardrivers;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCardriver> queryByCarSeq(String carSeq) {
		String strSql = "select epd_cardriver.*," +
						"epd_driver.driver_code," +
						"epd_driver.driver_name," +
						"epd_driver.sex," +
						"epd_driver.id_number," +
						"epd_driver.telephone," +
						"epd_driver.driving_type," +
						"epd_driver.driving_valid," +
						"epd_driver.driver_companyname," +
						"epd_driver.driver_status" +
				" from epd_cardriver" +
						" left join epd_driver on epd_driver.driver_seq = epd_cardriver.driver_seq" +
				" where car_seq=? order by driver_order";
		List params = new ArrayList();
		params.add(carSeq);
		List<EpdCardriver> cardrivers = (List<EpdCardriver>) super.queryAll(strSql.toString(),
				params,new EpdCardriver());
		return cardrivers;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryByDriverSeq(String driverSeq) {
		StringBuffer strSql = new StringBuffer("select count(1) from epd_cardriver where driver_seq=?");
		List params = new ArrayList();
		params.add(driverSeq);
		return super.queryCount(strSql.toString(),params);
	}

}
