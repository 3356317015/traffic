
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdStation;


public class EpdStationDao extends BaseDao{
	public EpdStationDao(Connection conn){
		super(conn);
	}
	
	public EpdStation insert(EpdStation epdStation, Map<String, Object> config){
		String pk = super.insert(epdStation,config);
		epdStation.setStationSeq(pk);
		return epdStation;
	}
	
	public void update(EpdStation epdStation, Map<String, Object> config){
		super.update(epdStation,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String stationSeq){
		String strSql = "delete from epd_station where station_seq=?";
		List params = new ArrayList();
		params.add(stationSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * queryByValid方法描述：查询重复站点
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-25 上午11:20:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdStation 重复站点
	 * @return List<EpdStation>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdStation> queryByValid(EpdStation epdStation) {
		StringBuffer strSql = new StringBuffer("select * from epd_station where organize_seq =?" +
				" and (station_code = ? or station_spell =?)");
		List params = new ArrayList();
		params.add(epdStation.getOrganizeSeq());
		params.add(epdStation.getStationCode());
		params.add(epdStation.getStationSpell());
		String stationSeq = epdStation.getStationSeq();
		if (null != stationSeq && !"".equals(stationSeq)){
			strSql.append(" and station_seq <> ?");
			params.add(stationSeq);
		}
		List<EpdStation> epdStations = (List<EpdStation>) super.queryAll(strSql.toString(),params,new EpdStation());
		return epdStations;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdStation> queryByAll(){
		String strSql = "select epd_station.*" +
					",CONCAT(IFNULL(city,''),IFNULL(county,''),IFNULL(towns,'')) AS region_name" +
				" from epd_station" +
				" left join epd_region on epd_station.region_seq = epd_region.region_seq";
		List<EpdStation> epdStations = (List<EpdStation>) super.queryAll(strSql,null,new EpdStation());
		return epdStations;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdStation> queryPageByCustom(String organizeSeq, String stationCode,String stationSpell,String stationName,
			String stationStatus,int start,int limit){
		StringBuffer strSql = new StringBuffer("select epd_station.*" +
					",CONCAT(IFNULL(city,''),IFNULL(county,''),IFNULL(towns,'')) AS region_name" +
				" from epd_station" +
				" left join epd_region on epd_station.region_seq = epd_region.region_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_station.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != stationCode && !"".equals(stationCode)){
			strSql.append(" and epd_station.station_code like ?");
			params.add("%" + stationCode + "%");
		}
		if (null != stationSpell && !"".equals(stationSpell)){
			strSql.append(" and epd_station.station_spell like ?");
			params.add("%" + stationSpell + "%");
		}
		if (null != stationName && !"".equals(stationName)){
			strSql.append(" and epd_station.station_name like ?");
			params.add("%" + stationName + "%");
		}
		
		if (null != stationStatus && !"".equals(stationStatus)){
			strSql.append(" and station_status = ?");
			params.add(stationStatus);
		}
		strSql.append(" order by station_code");
		List<EpdStation> epdStations = (List<EpdStation>) super.queryPage(strSql.toString(),params,new EpdStation(),start,limit);
		return epdStations;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdStation> queryAllByCustom(String organizeSeq, String stationCode,String stationSpell,String stationName,
			String stationStatus){
		StringBuffer strSql = new StringBuffer("select epd_station.*" +
					",CONCAT(IFNULL(city,''),IFNULL(county,''),IFNULL(towns,'')) AS region_name" +
				" from epd_station" +
				" left join epd_region on epd_station.region_seq = epd_region.region_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_station.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != stationCode && !"".equals(stationCode)){
			strSql.append(" and epd_station.station_code like ?");
			params.add("%" + stationCode + "%");
		}
		if (null != stationSpell && !"".equals(stationSpell)){
			strSql.append(" and epd_station.station_spell like ?");
			params.add("%" + stationSpell + "%");
		}
		if (null != stationName && !"".equals(stationName)){
			strSql.append(" and epd_station.station_name like ?");
			params.add("%" + stationName + "%");
		}
		
		if (null != stationStatus && !"".equals(stationStatus)){
			strSql.append(" and epd_station.station_status = ?");
			params.add(stationStatus);
		}
		strSql.append(" order by epd_station.station_code");
		List<EpdStation> stations = (List<EpdStation>) super.queryAll(strSql.toString(),params,new EpdStation());
		return stations;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String stationCode,String stationSpell,String stationName,
			String stationStatus){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_station where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != stationCode && !"".equals(stationCode)){
			strSql.append(" and station_code like ?");
			params.add("%" + stationCode + "%");
		}
		if (null != stationSpell && !"".equals(stationSpell)){
			strSql.append(" and station_spell like ?");
			params.add("%" + stationSpell + "%");
		}
		if (null != stationName && !"".equals(stationName)){
			strSql.append(" and station_name like ?");
			params.add("%" + stationName + "%");
		}
		
		if (null != stationStatus && !"".equals(stationStatus)){
			strSql.append(" and station_status = ?");
			params.add(stationStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdStation> queryByStatus(String organizeSeq, String stationStatus) {
		StringBuffer strSql = new StringBuffer("select * from epd_station where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != stationStatus && !"".equals(stationStatus)){
			strSql.append(" and station_status = ?");
			params.add(stationStatus);
		}
		List<EpdStation> epdStations = (List<EpdStation>) super.queryAll(strSql.toString(),params,new EpdStation());
		return epdStations;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdStation> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from epd_station where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		List<EpdStation> epdStations = (List<EpdStation>) super.queryAll(strSql.toString(),params,new EpdStation());
		return epdStations;
	}

}
