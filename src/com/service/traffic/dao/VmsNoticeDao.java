
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.VmsNotice;


public class VmsNoticeDao extends BaseDao{
	public VmsNoticeDao(Connection conn){
		super(conn);
	}
	
	public VmsNotice insert(VmsNotice vmsNotice, Map<String, Object> config){
		String pk = super.insert(vmsNotice,config);
		vmsNotice.setNoticeSeq(pk);
		return vmsNotice;
	}
	
	public void update(VmsNotice vmsNotice, Map<String, Object> config){
		super.update(vmsNotice,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String noticeSeq){
		String strSql = "delete from vms_notice where notice_seq=?";
		List params = new ArrayList();
		params.add(noticeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsNotice> queryByPK(String noticeSeq){
		String strSql = "select * from vms_notice where notice_seq=?";
		List params = new ArrayList();
		params.add(noticeSeq);
		List<VmsNotice> vmsNotices = (List<VmsNotice>) super.queryAll(strSql,params,new VmsNotice());
		return vmsNotices;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsNotice> queryPageByCustom(String organizeSeq, String noticeName,
			String noticeStatus,int start,int limit){
		StringBuffer strSql = new StringBuffer("SELECT vms_notice.*,a.play_time" +
				" FROM vms_notice" +
					" LEFT JOIN (" +
					" SELECT vms_noticeset.notice_seq AS notice_seq," +
						"GROUP_CONCAT(vms_noticeset.play_time) AS play_time" +
					" FROM vms_noticeset " +
					" GROUP BY vms_noticeset.notice_seq) a" +
					" ON a.notice_seq=vms_notice.notice_seq" +
				" WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and vms_notice.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != noticeName && !"".equals(noticeName)){
			strSql.append(" and vms_notice.notice_name like ?");
			params.add("%" + noticeName + "%");
		}
		if (null != noticeStatus && !"".equals(noticeStatus)){
			strSql.append(" and vms_notice.notice_status = ?");
			params.add(noticeStatus);
		}
		List<VmsNotice> vmsNotices = (List<VmsNotice>) super.queryPage(strSql.toString(),params,
				new VmsNotice(),start,limit);
		return vmsNotices;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsNotice> queryAllByCustom(String organizeSeq,String noticeName,
			String noticeStatus){
		StringBuffer strSql = new StringBuffer("SELECT vms_notice.*,a.play_time" +
				" FROM vms_notice" +
					" LEFT JOIN (" +
					" SELECT vms_noticeset.notice_seq AS notice_seq," +
						"GROUP_CONCAT(vms_noticeset.play_time) AS play_time" +
					" FROM vms_noticeset " +
					" GROUP BY vms_noticeset.notice_seq) a" +
					" ON a.notice_seq=vms_notice.notice_seq" +
				" WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and vms_notice.organize_seq = ?");
			params.add(organizeSeq);
		}

		if (null != noticeName && !"".equals(noticeName)){
			strSql.append(" and vms_notice.notice_name like ?");
			params.add("%" + noticeName + "%");
		}
		if (null != noticeStatus && !"".equals(noticeStatus)){
			strSql.append(" and vms_notice.notice_status = ?");
			params.add(noticeStatus);
		}
		List<VmsNotice> vmsNotices = (List<VmsNotice>) super.queryAll(strSql.toString(),params,new VmsNotice());
		return vmsNotices;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String noticeName,
			String noticeStatus){
		StringBuffer strSql = new StringBuffer("SELECT count(1) FROM vms_notice WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != noticeName && !"".equals(noticeName)){
			strSql.append(" and notice_name like ?");
			params.add("%" + noticeName + "%");
		}
		if (null != noticeStatus && !"".equals(noticeStatus)){
			strSql.append(" and notice_status = ?");
			params.add(noticeStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<VmsNotice> queryPageByOrganizeSeq(String organizeSeq,
			int start, int limit) {
		StringBuffer strSql = new StringBuffer("SELECT vms_notice.*,a.play_time" +
				" FROM vms_notice" +
					" LEFT JOIN (" +
						" SELECT vms_noticeset.notice_seq AS notice_seq," +
							"GROUP_CONCAT(vms_noticeset.play_time) AS play_time" +
						" FROM vms_noticeset " +
						" GROUP BY vms_noticeset.notice_seq) a" +
					" ON a.notice_seq=vms_notice.notice_seq" +
				" WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and vms_notice.organize_seq = ?");
			params.add(organizeSeq);
		}
		List<VmsNotice> vmsNotices = (List<VmsNotice>) super.queryPage(strSql.toString(),params,
				new VmsNotice(),start,limit);
		return vmsNotices;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsNotice> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("SELECT vms_notice.*,a.play_time" +
				" FROM vms_notice" +
					" LEFT JOIN (" +
					" SELECT vms_noticeset.notice_seq AS notice_seq," +
						"GROUP_CONCAT(vms_noticeset.play_time) AS play_time" +
					" FROM vms_noticeset " +
					" GROUP BY vms_noticeset.notice_seq) a" +
					" ON a.notice_seq=vms_notice.notice_seq" +
				" WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and vms_notice.organize_seq = ?");
			params.add(organizeSeq);
		}
		List<VmsNotice> vmsNotices = (List<VmsNotice>) super.queryAll(strSql.toString(),params,new VmsNotice());
		return vmsNotices;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByOrganizeSeq(String organizeSeq){
		StringBuffer strSql = new StringBuffer("SELECT count(1) FROM vms_notice WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<VmsNotice> queryByStatusAndTime(String organizeSeq,
			String noticeStatus, String currTime) {
		StringBuffer strSql = new StringBuffer(
				"SELECT vms_notice.*,vms_noticeset.play_number" +
				" FROM vms_notice,vms_noticeset" +
				" WHERE vms_notice.notice_seq and vms_noticeset.notice_seq");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and vms_notice.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != noticeStatus && !"".equals(noticeStatus)){
			strSql.append(" and vms_notice.notice_status = ?");
			params.add(noticeStatus);
		}
		if (null != currTime && !"".equals(currTime)){
			strSql.append(" and vms_noticeset.play_time = ?");
			params.add(currTime);
		}
		List<VmsNotice> vmsNotices = (List<VmsNotice>) super.queryAll(strSql.toString(),params,new VmsNotice());
		return vmsNotices;
	}

}
