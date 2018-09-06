
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.VmsRoute;

/**
 * EpdRouteDao概要说明：线路数据操作
 * @author lcy
 */
public class VmsRouteDao extends BaseDao{
	public VmsRouteDao(Connection conn){
		super(conn);
	}
	
	public VmsRoute insert(VmsRoute vmsRoute, Map<String, Object> config){
		String pk = super.insert(vmsRoute,config);
		vmsRoute.setRouteSeq(pk);
		return vmsRoute;
	}
	
	public void update(VmsRoute vmsRoute, Map<String, Object> config){
		super.update(vmsRoute,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String routeSeq){
		String strSql = "delete from vms_route where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsRoute> queryByPK(String routeSeq){
		String strSql = "select * from vms_route where route_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		List<VmsRoute> vmsRoutes = (List<VmsRoute>) super.queryAll(strSql,params,new VmsRoute());
		return vmsRoutes;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsRoute> queryPageByCustom(String organizeSeq, String routeCode, String routeName,
			String voiceStatus,int start,int limit){
		StringBuffer strSql = new StringBuffer("SELECT * FROM vms_route WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeCode && !"".equals(routeCode)){
			strSql.append(" and route_code like ?");
			params.add("%" + routeCode + "%");
		}
		if (null != routeName && !"".equals(routeName)){
			strSql.append(" and route_name like ?");
			params.add("%" + routeName + "%");
		}
		if (null != voiceStatus && !"".equals(voiceStatus)){
			strSql.append(" and voice_status = ?");
			params.add(voiceStatus);
		}
		strSql.append(" order by route_code");
		List<VmsRoute> vmsRoutes = (List<VmsRoute>) super.queryPage(strSql.toString(),params,new VmsRoute(),start,limit);
		return vmsRoutes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsRoute> queryAllByCustom(String organizeSeq, String routeCode, String routeName,
			String voiceStatus){
		StringBuffer strSql = new StringBuffer("SELECT * FROM vms_route WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeCode && !"".equals(routeCode)){
			strSql.append(" and route_code like ?");
			params.add("%" + routeCode + "%");
		}
		if (null != routeName && !"".equals(routeName)){
			strSql.append(" and route_name like ?");
			params.add("%" + routeName + "%");
		}
		if (null != voiceStatus && !"".equals(voiceStatus)){
			strSql.append(" and voice_status = ?");
			params.add(voiceStatus);
		}
		strSql.append(" order by route_code");
		List<VmsRoute> vmsRoutes = (List<VmsRoute>) super.queryAll(strSql.toString(),params,new VmsRoute());
		return vmsRoutes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String routeCode, String routeName,
			String voiceStatus){
		StringBuffer strSql = new StringBuffer("SELECT count(1) FROM vms_route WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeCode && !"".equals(routeCode)){
			strSql.append(" and route_code like ?");
			params.add("%" + routeCode + "%");
		}
		if (null != routeName && !"".equals(routeName)){
			strSql.append(" and route_name like ?");
			params.add("%" + routeName + "%");
		}
		if (null != voiceStatus && !"".equals(voiceStatus)){
			strSql.append(" and voice_status = ?");
			params.add(voiceStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsRoute> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("SELECT * FROM vms_route WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		List<VmsRoute> vmsRoutes = (List<VmsRoute>) super.queryAll(strSql.toString(),params,new VmsRoute());
		return vmsRoutes;
	}

	@SuppressWarnings("unchecked")
	public List<VmsRoute> queryTrafficRoute(String strSql) {
		List<VmsRoute> vmsRoutes = (List<VmsRoute>) super.queryAll(strSql,null,new VmsRoute());
		return vmsRoutes;
	}

}
