
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.ItsLiner;

public class ItsLinerDao extends BaseDao{
	public ItsLinerDao(Connection conn){
		super(conn);
	}
	
	public ItsLiner insert(ItsLiner itsLiner, Map<String, Object> config){
		String pk = super.insert(itsLiner,config);
		itsLiner.setLinerSeq(pk);
		return itsLiner;
	}
	
	public void update(ItsLiner itsLiner, Map<String, Object> config){
		super.update(itsLiner,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String linerSeq){
		String strSql = "delete from its_liner where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLiner> queryByValid(ItsLiner itsLiner) {
		StringBuffer strSql = new StringBuffer("select * from its_liner where liner_date = ? and liner_id = ?");
		List params = new ArrayList();
		params.add(itsLiner.getLinerDate());
		params.add(itsLiner.getLinerId());
		String linerSeq = itsLiner.getLinerSeq();
		if (null != linerSeq && !"".equals(linerSeq)){
			strSql.append(" and liner_seq <> ?");
			params.add(linerSeq);
		}
		List<ItsLiner> itsLiners = (List<ItsLiner>) super.queryAll(strSql.toString(),
				params,new ItsLiner());
		return itsLiners;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLiner> queryByPK(String linerSeq){
		String strSql = "select * from its_liner where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<ItsLiner> liners = (List<ItsLiner>) super.queryAll(strSql,params,new ItsLiner());
		return liners;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItsLiner> queryByAll(){
		String strSql = "select * from its_liner";
		List<ItsLiner> liners = (List<ItsLiner>) super.queryAll(strSql,null,new ItsLiner());
		return liners;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByDateAndId(String linerDate, String linerId) {
		StringBuffer strSql = new StringBuffer("select count(1) from its_liner where liner_date = ? and liner_id = ?");
		List params = new ArrayList();
		params.add(linerDate);
		params.add(linerId);
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItsLiner> queryPageByCustom(String organizeSeq,
			String routeSeq, String stationSeq, String linerId,
			String cargradeSeq, String linerStatus, String ifReport,
			String startDate, String limitDate, int start, int limit) {
		StringBuffer strSql = new StringBuffer("SELECT its_liner.*," +
				"epd_checkgate.check_name," +
				"epd_parking.parking_name," +
				"GROUP_CONCAT(its_linerstation.station_name) AS station_name"+
			" FROM its_liner" +
				" left join epd_checkgate on epd_checkgate.checkgate_seq = its_liner.checkgate_seq"+
				" left join epd_parking on epd_parking.parking_seq = its_liner.parking_seq" +
				",its_linerstation" +
				
			" WHERE its_linerstation.liner_seq = its_liner.liner_seq");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and its_liner.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and its_liner.route_seq = ?");
			params.add(routeSeq);
		}
		
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and its_liner.station_seq = ?");
			params.add(stationSeq);
		}
		
		if (null != linerId && !"".equals(linerId)){
			strSql.append(" and its_liner.liner_id = ?");
			params.add(linerId);
		}
		if (null != cargradeSeq && !"".equals(cargradeSeq)){
			strSql.append(" and its_liner.cargrade_seq = ?");
			params.add(cargradeSeq);
		}
		if (null != linerStatus && !"".equals(linerStatus)){
			strSql.append(" and its_liner.liner_status = ?");
			params.add(linerStatus);
		}
		
		if (null != ifReport && !"".equals(ifReport)){
			strSql.append(" and its_liner.if_report = ?");
			params.add(ifReport);
		}
		
		if (null != startDate && !"".equals(startDate)){
			strSql.append(" and its_liner.liner_date>=?");
			params.add(startDate);
		}
		if (null != limitDate && !"".equals(limitDate)){
			strSql.append(" and its_liner.liner_date<=?");
			params.add(limitDate);
		}
		strSql.append(" GROUP BY its_liner.liner_seq");

		List<ItsLiner> itsLiners = (List<ItsLiner>) super.queryPage(strSql.toString(),
				params,new ItsLiner(),start,limit);
		return itsLiners;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer queryCountByCustom(String organizeSeq,
			String routeSeq, String stationSeq, String linerId,
			String cargradeSeq, String linerStatus, String ifReport,
			String startDate, String limitDate) {
		StringBuffer strSql = new StringBuffer("SELECT count(1)"+
				" FROM its_liner,its_linerstation" +
				" WHERE its_linerstation.liner_seq = its_liner.liner_seq");
			List params = new ArrayList();
			if (null != organizeSeq && !"".equals(organizeSeq)){
				strSql.append(" and its_liner.organize_seq = ?");
				params.add(organizeSeq);
			}
			if (null != routeSeq && !"".equals(routeSeq)){
				strSql.append(" and its_liner.route_seq = ?");
				params.add(routeSeq);
			}
			
			if (null != stationSeq && !"".equals(stationSeq)){
				strSql.append(" and its_liner.station_seq = ?");
				params.add(stationSeq);
			}
			
			if (null != linerId && !"".equals(linerId)){
				strSql.append(" and its_liner.liner_id = ?");
				params.add(linerId);
			}
			if (null != cargradeSeq && !"".equals(cargradeSeq)){
				strSql.append(" and its_liner.cargrade_seq = ?");
				params.add(cargradeSeq);
			}
			if (null != linerStatus && !"".equals(linerStatus)){
				strSql.append(" and its_liner.liner_status = ?");
				params.add(linerStatus);
			}
			
			if (null != ifReport && !"".equals(ifReport)){
				strSql.append(" and its_liner.if_report = ?");
				params.add(ifReport);
			}
			
			if (null != startDate && !"".equals(startDate)){
				strSql.append(" and its_liner.liner_date>=?");
				params.add(startDate);
			}
			if (null != limitDate && !"".equals(limitDate)){
				strSql.append(" and its_liner.liner_date<=?");
				params.add(limitDate);
			}
			strSql.append(" GROUP BY its_liner.liner_seq");
			return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateAttribute(ItsLiner itsLiner, Map<String, Object> config) {
		String strSql = "update its_liner set liner_time=?,if_netsale=?,liner_status=?,update_time=? where liner_seq=?";
		List params = new ArrayList();
		params.add(itsLiner.getLinerTime());
		params.add(itsLiner.getIfNetsale());
		params.add(itsLiner.getLinerStatus());
		params.add(itsLiner.getUpdateTime());
		params.add(itsLiner.getLinerSeq());
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateNumber(ItsLiner itsLiner, Map<String, Object> config) {
		String strSql = "update its_liner set if_printseat=?,seat_num=?,free_num=?,half_num=?,stop_num=?,update_time=?  where liner_seq=?";
		List params = new ArrayList();
		params.add(itsLiner.getIfPrintseat());
		params.add(itsLiner.getSeatNum());
		params.add(itsLiner.getFreeNum());
		params.add(itsLiner.getHalfNum());
		params.add(itsLiner.getStopNum());
		params.add(itsLiner.getUpdateTime());
		params.add(itsLiner.getLinerSeq());
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateCheckgate(ItsLiner itsLiner) {
		String strSql = "update its_liner set checkgate_seq=?,parking_seq=?,update_time=? where liner_seq=?";
		List params = new ArrayList();
		params.add(itsLiner.getCargradeSeq());
		params.add(itsLiner.getParkingSeq());
		params.add(itsLiner.getUpdateTime());
		params.add(itsLiner.getLinerSeq());
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateDeal(ItsLiner itsLiner) {
		String strSql = "update its_liner set if_deal=?,if_main=?,update_time=? where liner_seq=?";
		List params = new ArrayList();
		params.add(itsLiner.getIfDeal());
		params.add(itsLiner.getIfMain());
		params.add(itsLiner.getUpdateTime());
		params.add(itsLiner.getLinerSeq());
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateService(ItsLiner itsLiner) {
		String strSql = "update its_liner set using_service=?,update_time=? where liner_seq=?";
		List params = new ArrayList();
		params.add(itsLiner.getUsingService());
		params.add(itsLiner.getUpdateTime());
		params.add(itsLiner.getLinerSeq());
		super.executeSql(strSql, params);
	}


}
