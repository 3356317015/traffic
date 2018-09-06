
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdRoutetype;

/**
 * EpdRouteTypeDao概要说明：线路类型数据操作
 * @author lcy
 */
public class EpdRouteTypeDao extends BaseDao{
	public EpdRouteTypeDao(Connection conn){
		super(conn);
	}
	
	public EpdRoutetype insert(EpdRoutetype epdRoutetype, Map<String, Object> config){
		String pk = super.insert(epdRoutetype,config);
		epdRoutetype.setRoutetypeSeq(pk);
		return epdRoutetype;
	}
	
	public void update(EpdRoutetype epdRoutetype, Map<String, Object> config){
		super.update(epdRoutetype,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String routetypeSeq){
		String strSql = "delete from epd_routetype where routetype_seq=?";
		List params = new ArrayList();
		params.add(routetypeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoutetype> queryByValid(EpdRoutetype epdRoutetype) {
		StringBuffer strSql = new StringBuffer("select * from epd_routetype where organize_seq=?" +
				" and routetype_code = ?");
		List params = new ArrayList();
		params.add(epdRoutetype.getOrganizeSeq());
		params.add(epdRoutetype.getRoutetypeCode());
		String routetypeSeq = epdRoutetype.getRoutetypeSeq();
		if (null != routetypeSeq && !"".equals(routetypeSeq)){
			strSql.append(" and routetype_seq <> ?");
			params.add(routetypeSeq);
		}
		List<EpdRoutetype> routetypes = (List<EpdRoutetype>) super.queryAll(strSql.toString(),
				params,new EpdRoutetype());
		return routetypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoutetype> queryByPK(String routetypeSeq){
		String strSql = "select * from epd_routetype where routetype_seq=?";
		List params = new ArrayList();
		params.add(routetypeSeq);
		List<EpdRoutetype> epdRoutetypes = (List<EpdRoutetype>) super.queryAll(strSql,params,new EpdRoutetype());
		return epdRoutetypes;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdRoutetype> queryByAll(){
		String strSql = "select * from epd_routetype";
		List<EpdRoutetype> epdRoutetypes = (List<EpdRoutetype>) super.queryAll(strSql,null,new EpdRoutetype());
		return epdRoutetypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoutetype> queryByOrganizeSeq(String organizeSeq) {
		String strSql = "select * from epd_routetype where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<EpdRoutetype> epdRoutetypes = (List<EpdRoutetype>) super.queryAll(strSql,params,new EpdRoutetype());
		return epdRoutetypes;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoutetype> queryPageByCustom(String organizeSeq, String routetypeCode, String routetypeName,int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_routetype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routetypeCode && !"".equals(routetypeCode)){
			strSql.append(" and routetype_code like ?");
			params.add("%" + routetypeCode + "%");
		}
		if (null != routetypeName && !"".equals(routetypeName)){
			strSql.append(" and routetype_name like ?");
			params.add("%" + routetypeName + "%");
		}
		strSql.append(" order by routetype_code");
		List<EpdRoutetype> epdRoutetypes = (List<EpdRoutetype>) super.queryPage(strSql.toString(),params,new EpdRoutetype(),start,limit);
		return epdRoutetypes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRoutetype> queryAllByCustom(String organizeSeq, String routetypeCode, String routetypeName){
		StringBuffer strSql = new StringBuffer("select * from epd_routetype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routetypeCode && !"".equals(routetypeCode)){
			strSql.append(" and routetype_code like ?");
			params.add("%" + routetypeCode + "%");
		}
		if (null != routetypeName && !"".equals(routetypeName)){
			strSql.append(" and routetype_name like ?");
			params.add("%" + routetypeName + "%");
		}
		strSql.append(" order by routetype_code");
		List<EpdRoutetype> epdRoutetypes = (List<EpdRoutetype>) super.queryAll(strSql.toString(),params,new EpdRoutetype());
		return epdRoutetypes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String routetypeCode, String routetypeName){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_routetype where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routetypeCode && !"".equals(routetypeCode)){
			strSql.append(" and routetype_code like ?");
			params.add("%" + routetypeCode + "%");
		}
		if (null != routetypeName && !"".equals(routetypeName)){
			strSql.append(" and routetype_name like ?");
			params.add("%" + routetypeName + "%");
		}
		return super.queryCount(strSql.toString(),params);
	}

}
