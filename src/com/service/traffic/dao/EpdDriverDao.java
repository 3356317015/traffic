
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdDriver;


public class EpdDriverDao extends BaseDao{
	public EpdDriverDao(Connection conn){
		super(conn);
	}
	
	public EpdDriver insert(EpdDriver epdDriver, Map<String, Object> config){
		String pk = super.insert(epdDriver,config);
		epdDriver.setDriverSeq(pk);
		return epdDriver;
	}
	
	public void update(EpdDriver epdDriver, Map<String, Object> config){
		super.update(epdDriver,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String driverSeq){
		String strSql = "delete from epd_driver where driver_seq=?";
		List params = new ArrayList();
		params.add(driverSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDriver> queryByValid(EpdDriver epdDriver) {
		StringBuffer strSql = new StringBuffer("select * from epd_driver where organize_seq =?" +
				" and id_number = ?");
		List params = new ArrayList();
		params.add(epdDriver.getOrganizeSeq());
		params.add(epdDriver.getIdNumber());
		String driverSeq = epdDriver.getDriverSeq();
		if (null != driverSeq && !"".equals(driverSeq)){
			strSql.append(" and driver_seq <> ?");
			params.add(driverSeq);
		}
		List<EpdDriver> drivers = (List<EpdDriver>) super.queryAll(strSql.toString(),params,new EpdDriver());
		return drivers;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdDriver> queryByAll(){
		String strSql = "select * from epd_driver";
		List<EpdDriver> drivers = (List<EpdDriver>) super.queryAll(strSql,null,new EpdDriver());
		return drivers;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDriver> queryPageByCustom(String organizeSeq, String idNumber,String driverName,String companyName,
			String status,int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_driver where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != idNumber && !"".equals(idNumber)){
			strSql.append(" and id_number like ?");
			params.add("%" + idNumber + "%");
		}
		if (null != driverName && !"".equals(driverName)){
			strSql.append(" and driver_name like ?");
			params.add("%" + driverName + "%");
		}
		if (null != companyName && !"".equals(companyName)){
			strSql.append(" and driver_companyname like ?");
			params.add("%" + companyName + "%");
		}
		
		if (null != status && !"".equals(status)){
			strSql.append(" and epd_driver.driver_status = ?");
			params.add(status);
		}
		strSql.append(" order by epd_driver.driver_name");
		List<EpdDriver> drivers = (List<EpdDriver>) super.queryPage(strSql.toString(),params,new EpdDriver(),start,limit);
		return drivers;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdDriver> queryAllByCustom(String organizeSeq, String idNumber,String driverName,String companyName,
			String status){
		StringBuffer strSql = new StringBuffer("select * from epd_driver where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != idNumber && !"".equals(idNumber)){
			strSql.append(" and id_number like ?");
			params.add("%" + idNumber + "%");
		}
		if (null != driverName && !"".equals(driverName)){
			strSql.append(" and driver_name like ?");
			params.add("%" + driverName + "%");
		}
		if (null != companyName && !"".equals(companyName)){
			strSql.append(" and driver_companyname like ?");
			params.add("%" + companyName + "%");
		}
		
		if (null != status && !"".equals(status)){
			strSql.append(" and driver_status = ?");
			params.add(status);
		}
		strSql.append(" order by epd_driver.driver_name");
		List<EpdDriver> drivers = (List<EpdDriver>) super.queryAll(strSql.toString(),params,new EpdDriver());
		return drivers;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String idNumber,String driverName,String companyName,
			String status){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_driver where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != idNumber && !"".equals(idNumber)){
			strSql.append(" and epd_driver.id_number like ?");
			params.add("%" + idNumber + "%");
		}
		if (null != driverName && !"".equals(driverName)){
			strSql.append(" and driver_name like ?");
			params.add("%" + driverName + "%");
		}
		if (null != companyName && !"".equals(companyName)){
			strSql.append(" and driver_company like ?");
			params.add("%" + companyName + "%");
		}
		
		if (null != status && !"".equals(status)){
			strSql.append(" and driver_status = ?");
			params.add(status);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EpdDriver> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from epd_driver where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by epd_driver.driver_name");
		List<EpdDriver> drivers = (List<EpdDriver>) super.queryAll(strSql.toString(),params,new EpdDriver());
		return drivers;
	}

}
