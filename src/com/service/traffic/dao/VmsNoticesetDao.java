
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.VmsNoticeset;


public class VmsNoticesetDao extends BaseDao{
	public VmsNoticesetDao(Connection conn){
		super(conn);
	}
	
	public VmsNoticeset insert(VmsNoticeset vmsNoticeset, Map<String, Object> config){
		String pk = super.insert(vmsNoticeset,config);
		vmsNoticeset.setNoticesetSeq(pk);
		return vmsNoticeset;
	}
	
	public void update(VmsNoticeset vmsNoticeset, Map<String, Object> config){
		super.update(vmsNoticeset,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String noticesetSeq){
		String strSql = "delete from vms_noticeset where noticeset_seq=?";
		List params = new ArrayList();
		params.add(noticesetSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsNoticeset> queryByPK(String noticeSeq){
		String strSql = "select * from vms_noticeset where noticeset_seq=?";
		List params = new ArrayList();
		params.add(noticeSeq);
		List<VmsNoticeset> vmsNoticesets = (List<VmsNoticeset>) super.queryAll(strSql,params,new VmsNoticeset());
		return vmsNoticesets;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsNoticeset> queryByNoticeSeq(String noticeSeq) {
		StringBuffer strSql = new StringBuffer("SELECT * FROM vms_noticeset WHERE 1=1");
		List params = new ArrayList();
		if (null != noticeSeq && !"".equals(noticeSeq)){
			strSql.append(" and notice_seq = ?");
			params.add(noticeSeq);
		}
		List<VmsNoticeset> vmsNoticesets = (List<VmsNoticeset>) super.queryAll(strSql.toString(),params,new VmsNoticeset());
		return vmsNoticesets;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByNoticeSeq(String noticeSeq) {
		String strSql = "delete from vms_noticeset where notice_seq=?";
		List params = new ArrayList();
		params.add(noticeSeq);
		super.executeSql(strSql, params);
	}

}
