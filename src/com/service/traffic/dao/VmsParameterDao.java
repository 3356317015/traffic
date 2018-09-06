
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.VmsParameter;


public class VmsParameterDao extends BaseDao{
	public VmsParameterDao(Connection conn){
		super(conn);
	}
	
	public VmsParameter insert(VmsParameter vmsParameter, Map<String, Object> config){
		String pk = super.insert(vmsParameter,config);
		vmsParameter.setParameterSeq(pk);
		return vmsParameter;
	}
	
	public void update(VmsParameter vmsParameter, Map<String, Object> config){
		super.update(vmsParameter,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String parameterSeq){
		String strSql = "delete from vms_parameter where parameter_seq=?";
		List params = new ArrayList();
		params.add(parameterSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsParameter> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from vms_parameter where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		List<VmsParameter> vmsParameters = (List<VmsParameter>) super.queryAll(strSql.toString(),params,new VmsParameter());
		return vmsParameters;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<VmsParameter> queryByParameterCode(String organizeSeq,
			String parameterCode) {
		StringBuffer strSql = new StringBuffer("select * from vms_parameter where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != parameterCode && !"".equals(parameterCode)){
			strSql.append(" and parameter_code = ?");
			params.add(parameterCode);
		}
		List<VmsParameter> vmsParameters = (List<VmsParameter>) super.queryAll(strSql.toString(),params,new VmsParameter());
		return vmsParameters;
	}

}
