
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdRoute;

/**
 * EpdRouteDao概要说明：线路数据操作
 * @author lcy
 */
public class EpdRouteDao extends BaseDao{
	public EpdRouteDao(Connection conn){
		super(conn);
	}
	
	public EpdRoute insert(EpdRoute epdRoute, Map<String, Object> config){
		String pk = super.insert(epdRoute,config);
		epdRoute.setRouteSeq(pk);
		return epdRoute;
	}
	
	public void update(EpdRoute epdRoute, Map<String, Object> config){
		super.update(epdRoute,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String routeSeq){
		String strSql = "delete from epd_route where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoute> queryByPK(String routeSeq){
		String strSql = "select * from epd_route where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		List<EpdRoute> epdRoutes = (List<EpdRoute>) super.queryAll(strSql,params,new EpdRoute());
		return epdRoutes;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdRoute> queryByAll(){
		String strSql = "select * from epd_route";
		List<EpdRoute> epdRoutes = (List<EpdRoute>) super.queryAll(strSql,null,new EpdRoute());
		return epdRoutes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoute> queryByValid(EpdRoute epdRoute) {
		StringBuffer strSql = new StringBuffer("select * from epd_route where organize_seq = ?" +
				" and (route_code = ? or route_spell =?)");
		List params = new ArrayList();
		params.add(epdRoute.getOrganizeSeq());
		params.add(epdRoute.getRouteCode());
		params.add(epdRoute.getRouteSpell());
		String routeSeq = epdRoute.getRouteSeq();
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and route_seq <> ?");
			params.add(routeSeq);
		}
		List<EpdRoute> epdRoutes = (List<EpdRoute>) super.queryAll(strSql.toString(),params,new EpdRoute());
		return epdRoutes;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoute> queryPageByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType, String routeStatus,int start,int limit){
		StringBuffer strSql = new StringBuffer("SELECT epd_route.*,a.station_name,epd_routetype.routetype_name AS routetype_name" +
				" FROM epd_route" +
				" LEFT JOIN (" +
					" SELECT epd_routestation.route_seq AS route_seq,GROUP_CONCAT(epd_station.station_name) AS station_name" +
					" FROM epd_routestation,epd_station " +
					" WHERE epd_routestation.station_seq = epd_station.station_seq GROUP BY epd_routestation.route_seq) a" +
				" ON a.route_seq=epd_route.route_seq" +
				" LEFT JOIN epd_routetype ON epd_routetype.routetype_seq = epd_route.routetype_seq " +
				" WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_route.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeCode && !"".equals(routeCode)){
			strSql.append(" and epd_route.route_code like ?");
			params.add("%" + routeCode + "%");
		}
		if (null != routeSpell && !"".equals(routeSpell)){
			strSql.append(" and epd_route.route_spell like ?");
			params.add("%" + routeSpell + "%");
		}
		if (null != routeName && !"".equals(routeName)){
			strSql.append(" and epd_route.route_name like ?");
			params.add("%" + routeName + "%");
		}
		if (null != routeType && !"".equals(routeType)){
			strSql.append(" and epd_route.routetype_seq = ?");
			params.add(routeType);
		}
		if (null != roadType && !"".equals(roadType)){
			strSql.append(" and epd_route.road_type = ?");
			params.add(routeType);
		}
		if (null != routeStatus && !"".equals(routeStatus)){
			strSql.append(" and epd_route.route_status = ?");
			params.add(routeStatus);
		}
		strSql.append(" order by epd_route.route_code");
		List<EpdRoute> epdRoutes = (List<EpdRoute>) super.queryPage(strSql.toString(),params,new EpdRoute(),start,limit);
		return epdRoutes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoute> queryAllByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType, String routeStatus){
		StringBuffer strSql = new StringBuffer("SELECT epd_route.*,a.station_name,epd_routetype.routetype_name AS routetype_name" +
				" LEFT JOIN (" +
					" SELECT epd_routestation.route_seq AS route_seq,GROUP_CONCAT(epd_station.station_name) AS station_name" +
					" FROM epd_routestation,epd_station " +
					" WHERE epd_routestation.station_seq = epd_station.station_seq GROUP BY epd_routestation.route_seq) a" +
					" ON a.route_seq=epd_route.route_seq" +
				" LEFT JOIN epd_routetype ON epd_routetype.routetype_seq = epd_route.routetype_seq " +
				" WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_route.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeCode && !"".equals(routeCode)){
			strSql.append(" and epd_route.route_code like ?");
			params.add("%" + routeCode + "%");
		}
		if (null != routeSpell && !"".equals(routeSpell)){
			strSql.append(" and epd_route.route_spell like ?");
			params.add("%" + routeSpell + "%");
		}
		if (null != routeName && !"".equals(routeName)){
			strSql.append(" and epd_route.route_name like ?");
			params.add("%" + routeName + "%");
		}
		if (null != routeType && !"".equals(routeType)){
			strSql.append(" and epd_route.routetype_seq = ?");
			params.add(routeType);
		}
		if (null != roadType && !"".equals(roadType)){
			strSql.append(" and epd_route.road_type = ?");
			params.add(routeType);
		}
		if (null != routeStatus && !"".equals(routeStatus)){
			strSql.append(" and epd_route.route_status = ?");
			params.add(routeStatus);
		}
		strSql.append(" order by epd_route.route_code");
		List<EpdRoute> routes = (List<EpdRoute>) super.queryAll(strSql.toString(),params,new EpdRoute());
		return routes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType, String routeStatus){
		StringBuffer strSql = new StringBuffer("SELECT count(1) FROM epd_route where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeCode && !"".equals(routeCode)){
			strSql.append(" and route_code like ?");
			params.add("%" + routeCode + "%");
		}
		if (null != routeSpell && !"".equals(routeSpell)){
			strSql.append(" and route_spell like ?");
			params.add("%" + routeSpell + "%");
		}
		if (null != routeName && !"".equals(routeName)){
			strSql.append(" and route_name like ?");
			params.add("%" + routeName + "%");
		}
		if (null != routeType && !"".equals(routeType)){
			strSql.append(" and routetype_seq = ?");
			params.add(routeType);
		}
		if (null != roadType && !"".equals(roadType)){
			strSql.append(" and road_type = ?");
			params.add(routeType);
		}
		if (null != routeStatus && !"".equals(routeStatus)){
			strSql.append(" and route_status = ?");
			params.add(routeStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoute> queryByNoFare(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from epd_route" +
				" where organize_seq =? and route_seq not in (select route_seq from epd_fare group by route_seq)");
		strSql.append(" order by route_code");
		List params = new ArrayList();
		params.add(organizeSeq);
		List<EpdRoute> routes = (List<EpdRoute>) super.queryAll(strSql.toString(),params,new EpdRoute());
		return routes;
	}

	/**
	 * queryByOrganizeSeq方法描述：
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2018-3-11 下午10:13:17
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeSeq
	 * @return
	 * @return List<EpdRoute>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoute> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer(
				"SELECT epd_route.*,a.station_name" +
				" FROM epd_route" +
					" LEFT JOIN (" +
						" SELECT epd_routestation.route_seq AS route_seq,GROUP_CONCAT(epd_station.station_name) AS station_name" +
						" FROM epd_routestation,epd_station " +
						" WHERE epd_routestation.station_seq = epd_station.station_seq GROUP BY epd_routestation.route_seq) a" +
					" ON a.route_seq=epd_route.route_seq" +
				" WHERE epd_route.organize_seq =?");
		strSql.append(" ORDER BY epd_route.route_code");
		List params = new ArrayList();
		params.add(organizeSeq);
		List<EpdRoute> routes = (List<EpdRoute>) super.queryAll(strSql.toString(),params,new EpdRoute());
		return routes;
	}

}
