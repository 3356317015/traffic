
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdParking;

public class EpdParkingDao extends BaseDao{
	public EpdParkingDao(Connection conn){
		super(conn);
	}
	
	public EpdParking insert(EpdParking epdParking, Map<String, Object> config){
		String pk = super.insert(epdParking,config);
		epdParking.setParkingSeq(pk);
		return epdParking;
	}
	
	public void update(EpdParking epdParking, Map<String, Object> config){
		super.update(epdParking,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String parkingSeq){
		String strSql = "delete from epd_parking where parking_seq=?";
		List params = new ArrayList();
		params.add(parkingSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdParking> queryByValid(EpdParking epdParking) {
		StringBuffer strSql = new StringBuffer("select * from epd_parking where organize_seq =?" +
				" and parking_code = ?");
		List params = new ArrayList();
		params.add(epdParking.getOrganizeSeq());
		params.add(epdParking.getParkingCode());
		String parkingSeq = epdParking.getParkingSeq();
		if (null != parkingSeq && !"".equals(parkingSeq)){
			strSql.append(" and parking_seq <> ?");
			params.add(parkingSeq);
		}
		List<EpdParking> parkings = (List<EpdParking>) super.queryAll(strSql.toString(),
				params,new EpdParking());
		return parkings;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdParking> queryByPK(String parkingSeq){
		String strSql = "select * from epd_parking where parking_seq=?";
		List params = new ArrayList();
		params.add(parkingSeq);
		List<EpdParking> parkings = (List<EpdParking>) super.queryAll(strSql,params,new EpdParking());
		return parkings;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdParking> queryByAll(){
		String strSql = "select * from epd_parking";
		List<EpdParking> parkings = (List<EpdParking>) super.queryAll(strSql,null,new EpdParking());
		return parkings;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdParking> queryPageByCustom(String organizeSeq, String parkingCode,
			String parkingName,int start,int limit){
		StringBuffer strSql = new StringBuffer(
				"select epd_parking.*," +
					"epd_checkgate.check_name" +
				" from epd_parking" +
					" left join epd_checkgate on epd_checkgate.checkgate_seq=epd_parking.checkgate_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_parking.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != parkingCode && !"".equals(parkingCode)){
			strSql.append(" and epd_parking.parking_code = ?");
			params.add(parkingCode);
		}
		if (null != parkingName && !"".equals(parkingName)){
			strSql.append(" and epd_parking.parking_name = ?");
			params.add(parkingName);
		}
		strSql.append(" order by parking_code");
		List<EpdParking> parkings = (List<EpdParking>) super.queryPage(strSql.toString(),
				params,new EpdParking(),start,limit);
		return parkings;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdParking> queryAllByCustom(String organizeSeq, String parkingCode,
			String parkingName){
		StringBuffer strSql = new StringBuffer(
				"select epd_parking.*," +
					"epd_checkgate.check_name" +
				" from epd_parking" +
					" left join epd_checkgate on epd_checkgate.checkgate_seq=epd_parking.checkgate_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_parking.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != parkingCode && !"".equals(parkingCode)){
			strSql.append(" and epd_parking.parking_code = ?");
			params.add(parkingCode);
		}
		if (null != parkingName && !"".equals(parkingName)){
			strSql.append(" and epd_parking.parking_name = ?");
			params.add(parkingName);
		}
		strSql.append(" order by epd_parking.parking_code");
		List<EpdParking> parkings = (List<EpdParking>) super.queryAll(strSql.toString(),params,new EpdParking());
		return parkings;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String parkingCode,
			String parkingName){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_parking where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != parkingCode && !"".equals(parkingCode)){
			strSql.append(" and parking_code = ?");
			params.add(parkingCode);
		}
		if (null != parkingName && !"".equals(parkingName)){
			strSql.append(" and parking_name = ?");
			params.add(parkingName);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdParking> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer(
				"select epd_parking.*," +
					"epd_checkgate.check_name" +
				" from epd_parking" +
					" left join epd_checkgate on epd_checkgate.checkgate_seq=epd_parking.checkgate_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_parking.organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by epd_parking.parking_code");
		List<EpdParking> parkings = (List<EpdParking>) super.queryAll(strSql.toString(),params,new EpdParking());
		return parkings;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdParking> queryByOrganizeAndCheckSeq(String organizeSeq,
			List<EpdCheckgate> checkgates) {
		StringBuffer strSql = new StringBuffer(
				"select epd_parking.*," +
					"epd_checkgate.check_name" +
				" from epd_parking,epd_checkgate" +
				" where epd_checkgate.checkgate_seq=epd_parking.checkgate_seq");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_parking.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != checkgates && checkgates.size()>0){
			String checkSeq = "'" + checkgates.get(0).getCheckgateSeq() + "'";
			if (checkgates.size()>0){
				for (int i = 1; i < checkgates.size(); i++) {
					checkSeq += ","+ "'" + checkgates.get(i).getCheckgateSeq() + "'";
				}
			}
			strSql.append(" and epd_parking.checkgate_seq in (" + checkSeq  +")");
		}
		strSql.append(" order by epd_parking.parking_code");
		List<EpdParking> parkings = (List<EpdParking>) super.queryAll(strSql.toString(),params,new EpdParking());
		return parkings;
	}

}
