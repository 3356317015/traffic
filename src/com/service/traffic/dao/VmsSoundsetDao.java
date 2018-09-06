
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.VmsSoundset;


public class VmsSoundsetDao extends BaseDao{
	public VmsSoundsetDao(Connection conn){
		super(conn);
	}
	
	public VmsSoundset insert(VmsSoundset vmsSoundset, Map<String, Object> config){
		String pk = super.insert(vmsSoundset,config);
		vmsSoundset.setSoundsetSeq(pk);
		return vmsSoundset;
	}
	
	public void update(VmsSoundset vmsSoundset, Map<String, Object> config){
		super.update(vmsSoundset,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String soundsetSeq){
		String strSql = "delete from vms_soundset where soundset_seq=?";
		List params = new ArrayList();
		params.add(soundsetSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsSoundset> queryByPK(String soundSeq){
		String strSql = "select * from vms_soundset where soundset_seq=?";
		List params = new ArrayList();
		params.add(soundSeq);
		List<VmsSoundset> vmsSoundsets = (List<VmsSoundset>) super.queryAll(strSql,params,new VmsSoundset());
		return vmsSoundsets;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsSoundset> queryBySoundSeq(String soundSeq) {
		StringBuffer strSql = new StringBuffer("SELECT * FROM vms_soundset WHERE 1=1");
		List params = new ArrayList();
		if (null != soundSeq && !"".equals(soundSeq)){
			strSql.append(" and sound_seq = ?");
			params.add(soundSeq);
		}
		List<VmsSoundset> vmsSoundsets = (List<VmsSoundset>) super.queryAll(strSql.toString(),params,new VmsSoundset());
		return vmsSoundsets;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteBySoundSeq(String soundSeq) {
		String strSql = "delete from vms_soundset where sound_seq=?";
		List params = new ArrayList();
		params.add(soundSeq);
		super.executeSql(strSql, params);
	}

}
