
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.VmsSound;


public class VmsSoundDao extends BaseDao{
	public VmsSoundDao(Connection conn){
		super(conn);
	}
	
	public VmsSound insert(VmsSound vmsSound, Map<String, Object> config){
		String pk = super.insert(vmsSound,config);
		vmsSound.setSoundSeq(pk);
		return vmsSound;
	}
	
	public void update(VmsSound vmsSound, Map<String, Object> config){
		super.update(vmsSound,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String soundSeq){
		String strSql = "delete from vms_sound where sound_seq=?";
		List params = new ArrayList();
		params.add(soundSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsSound> queryByPK(String soundSeq){
		String strSql = "select * from vms_sound where sound_seq=?";
		List params = new ArrayList();
		params.add(soundSeq);
		List<VmsSound> vmsSounds = (List<VmsSound>) super.queryAll(strSql,params,new VmsSound());
		return vmsSounds;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsSound> queryPageByCustom(String organizeSeq, String soundType,int start,int limit){
		StringBuffer strSql = new StringBuffer("SELECT vms_sound.*,a.play_time" +
				" FROM vms_sound" +
					" LEFT JOIN (" +
					" SELECT vms_soundset.sound_seq AS sound_seq," +
						"GROUP_CONCAT(vms_soundset.play_time) AS play_time" +
					" FROM vms_soundset " +
					" GROUP BY vms_soundset.sound_seq) a" +
					" ON a.sound_seq=vms_sound.sound_seq" +
				" WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and vms_sound.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != soundType && !"".equals(soundType)){
			strSql.append(" and vms_sound.sound_type = ?");
			params.add(soundType);
		}
		List<VmsSound> vmsSounds = (List<VmsSound>) super.queryPage(strSql.toString(),params,
				new VmsSound(),start,limit);
		return vmsSounds;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsSound> queryAllByCustom(String organizeSeq,String soundType){
		StringBuffer strSql = new StringBuffer("SELECT vms_sound.*,a.play_time" +
				" FROM vms_sound" +
					" LEFT JOIN (" +
					" SELECT vms_soundset.sound_seq AS sound_seq," +
						"GROUP_CONCAT(vms_soundset.play_time) AS play_time" +
					" FROM vms_soundset " +
					" GROUP BY vms_soundset.sound_seq) a" +
					" ON a.sound_seq=vms_sound.sound_seq" +
				" WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and vms_sound.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != soundType && !"".equals(soundType)){
			strSql.append(" and vms_sound.sound_type = ?");
			params.add(soundType);
		}
		List<VmsSound> vmsSounds = (List<VmsSound>) super.queryAll(strSql.toString(),params,new VmsSound());
		return vmsSounds;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq,String soundType){
		StringBuffer strSql = new StringBuffer("SELECT count(1) FROM vms_sound WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != soundType && !"".equals(soundType)){
			strSql.append(" and sound_type = ?");
			params.add(soundType);
		}
		return super.queryCount(strSql.toString(),params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsSound> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("SELECT vms_sound.*,a.play_time" +
				" FROM vms_sound" +
					" LEFT JOIN (" +
					" SELECT vms_soundset.sound_seq AS sound_seq," +
						"GROUP_CONCAT(vms_soundset.play_time) AS play_time" +
					" FROM vms_soundset " +
					" GROUP BY vms_soundset.sound_seq) a" +
					" ON a.sound_seq=vms_sound.sound_seq" +
				" WHERE 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and vms_sound.organize_seq = ?");
			params.add(organizeSeq);
		}
		List<VmsSound> vmsSounds = (List<VmsSound>) super.queryAll(strSql.toString(),params,new VmsSound());
		return vmsSounds;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsSound> queryByStatusAndTime(String organizeSeq,
			String soundStatus, String currTime) {
		StringBuffer strSql = new StringBuffer("SELECT vms_sound.*,vms_soundset.play_number" +
				" FROM vms_sound,vms_soundset" +
				" WHERE vms_sound.sound_seq = vms_soundset.sound_seq");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and vms_sound.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != soundStatus && !"".equals(soundStatus)){
			strSql.append(" and vms_sound.sound_status = ?");
			params.add(soundStatus);
		}
		if (null != currTime && !"".equals(currTime)){
			strSql.append(" and vms_soundset.play_time = ?");
			params.add(currTime);
		}
		List<VmsSound> vmsSounds = (List<VmsSound>) super.queryAll(strSql.toString(),params,new VmsSound());
		return vmsSounds;
	}

}
