
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.VmsLiner;
import com.zhima.util.DateUtils;

/**
 * VmsLinerDao概要说明：播音班次数据操作
 * @author lcy
 */
public class VmsLinerDao extends BaseDao{
	public VmsLinerDao(Connection conn){
		super(conn);
	}
	
	public VmsLiner insert(VmsLiner vmsLiner, Map<String, Object> config){
		String pk = super.insert(vmsLiner,config);
		vmsLiner.setLinerSeq(pk);
		return vmsLiner;
	}
	
	public void update(VmsLiner vmsLiner, Map<String, Object> config){
		super.update(vmsLiner,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String routeSeq){
		String strSql = "delete from vms_liner where liner_seq=?";
		List params = new ArrayList();
		params.add(routeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByLinerDate(String organizeSeq,String linerDate){
		String strSql = "delete from vms_liner where organize_seq=? and liner_date<?";
		List params = new ArrayList();
		params.add(organizeSeq);
		params.add(linerDate);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsLiner> queryByPK(String linerSeq){
		String strSql = "select * from vms_liner where liner_seq=?";
		List params = new ArrayList();
		params.add(linerSeq);
		List<VmsLiner> vmsLiners = (List<VmsLiner>) super.queryAll(strSql,params,new VmsLiner());
		return vmsLiners;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsLiner> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("SELECT * FROM vms_liner WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		List<VmsLiner> vmsLiners = (List<VmsLiner>) super.queryAll(strSql.toString(),params,new VmsLiner());
		return vmsLiners;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsLiner> queryPageByCustom(String organizeSeq, String routeCode, String linerId,
			String reportStatus, String printbillStatus, int start,int limit){
		StringBuffer strSql = new StringBuffer("SELECT * FROM vms_liner WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeCode && !"".equals(routeCode)){
			strSql.append(" and (route_code like ? or route_name like ?)");
			params.add("%" + routeCode + "%");
			params.add("%" + routeCode + "%");
		}
		if (null != linerId && !"".equals(linerId)){
			strSql.append(" and liner_id = ?");
			params.add(linerId);
		}
		strSql.append(" and liner_date = ?");
		params.add(DateUtils.getNow(DateUtils.FORMAT_SHORT));
		if (null != reportStatus && !"".equals(reportStatus)){
			strSql.append(" and report_status = ?");
			params.add(reportStatus);
		}
		if (null != printbillStatus && !"".equals(printbillStatus)){
			strSql.append(" and printbill_status = ?");
			params.add(printbillStatus);
		}
		strSql.append(" order by liner_time");
		List<VmsLiner> vmsLiners = (List<VmsLiner>) super.queryPage(strSql.toString(),params,new VmsLiner(),start,limit);
		return vmsLiners;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsLiner> queryAllByCustom(String organizeSeq, String routeCode, String linerId,
			String reportStatus, String printbillStatus){
		StringBuffer strSql = new StringBuffer("SELECT * FROM vms_liner WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeCode && !"".equals(routeCode)){
			strSql.append(" and (route_code like ? or route_name like ?)");
			params.add("%" + routeCode + "%");
			params.add("%" + routeCode + "%");
		}
		strSql.append(" and liner_date = ?");
		params.add(DateUtils.getNow(DateUtils.FORMAT_SHORT));
		if (null != linerId && !"".equals(linerId)){
			strSql.append(" and liner_id = ?");
			params.add(linerId);
		}
		if (null != reportStatus && !"".equals(reportStatus)){
			strSql.append(" and report_status = ?");
			params.add(reportStatus);
		}
		if (null != printbillStatus && !"".equals(printbillStatus)){
			strSql.append(" and printbill_status = ?");
			params.add(printbillStatus);
		}
		strSql.append(" order by liner_time");
		List<VmsLiner> vmsLiners = (List<VmsLiner>) super.queryAll(strSql.toString(),params,new VmsLiner());
		return vmsLiners;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String routeCode, String linerId,
			String reportStatus, String printbillStatus){
		StringBuffer strSql = new StringBuffer("SELECT count(1) FROM vms_liner WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeCode && !"".equals(routeCode)){
			strSql.append(" and (route_code like ? or route_name like ?)");
			params.add("%" + routeCode + "%");
			params.add("%" + routeCode + "%");
		}
		if (null != linerId && !"".equals(linerId)){
			strSql.append(" and liner_id = ?");
			params.add(linerId);
		}
		strSql.append(" and liner_date = ?");
		params.add(DateUtils.getNow(DateUtils.FORMAT_SHORT));
		if (null != reportStatus && !"".equals(reportStatus)){
			strSql.append(" and report_status = ?");
			params.add(reportStatus);
		}
		if (null != printbillStatus && !"".equals(printbillStatus)){
			strSql.append(" and printbill_status = ?");
			params.add(printbillStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsLiner> queryByStatusAndTime(String organizeSeq,String linerStatus, String voiceTime){
		StringBuffer strSql = new StringBuffer("SELECT * FROM vms_liner WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != linerStatus && !"".equals(linerStatus)){
			strSql.append(" and report_status = ?");
			params.add(linerStatus);
		}
		if (null != voiceTime && !"".equals(voiceTime)){
			strSql.append(" and liner_time = ?");
			params.add(voiceTime);
		}
		strSql.append(" order by liner_time");
		List<VmsLiner> vmsLiners = (List<VmsLiner>) super.queryAll(strSql.toString(),params,new VmsLiner());
		return vmsLiners;
	}

	@SuppressWarnings("unchecked")
	public List<VmsLiner> queryTrafficLiner(String strSql) {
		List<VmsLiner> vmsLiners = (List<VmsLiner>) super.queryAll(strSql,null,new VmsLiner());
		return vmsLiners;
	}

}
