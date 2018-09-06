
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamVariables;

/**
 * SamVariablesDao概要说明：变量设置操作类
 * @author lcy
 */
public class SamVariablesDao extends BaseDao{
	public SamVariablesDao(Connection conn){
		super(conn);
	}

	public SamVariables insert(SamVariables samVariables, Map<String, Object> config){
		String pk = super.insert(samVariables,config);
		samVariables.setVariablesSeq(pk);
		return samVariables;
	}
	
	public void update(SamVariables samVariables, Map<String, Object> config){
		super.update(samVariables,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String variablesSeq){
		String strSql = "delete from sam_variables where variables_seq=?";
		List params = new ArrayList();
		params.add(variablesSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamVariables> queryByValid(SamVariables samVariables) {
		StringBuffer strSql = new StringBuffer("select * from sam_variables where organize_seq =?" +
				" and variable_type =? and variable_code =?");
		List params = new ArrayList();
		params.add(samVariables.getOrganizeSeq());
		params.add(samVariables.getVariableType());
		params.add(samVariables.getVariableCode());
		String variablesSeq = samVariables.getVariablesSeq();
		if (null != variablesSeq && !"".equals(variablesSeq)){
			strSql.append(" and variables_seq <> ?");
			params.add(variablesSeq);
		}
		List<SamVariables> variables = (List<SamVariables>) super.queryAll(strSql.toString(),params,new SamVariables());
		return variables;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<SamVariables> queryByAll(){
		String strSql = "select * from sam_variables";
		List<SamVariables> samVariables = (List<SamVariables>) super.queryAll(strSql,null,new SamVariables());
		return samVariables;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamVariables> queryPageByCustom(String organizeSeq,String variableType,String variableCode,String variableName,
			String status,int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from sam_variables where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != variableType && !"".equals(variableType)){
			strSql.append(" and variable_type = ?");
			params.add(variableType);
		}
		if (null != variableCode && !"".equals(variableCode)){
			strSql.append(" and variable_code like ?");
			params.add("%" + variableCode + "%");
		}
		if (null != variableName && !"".equals(variableName)){
			strSql.append(" and variable_name like ?");
			params.add("%" + variableName + "%");
		}
		if (null != status && !"".equals(status)){
			strSql.append(" and status = ?");
			params.add(status);
		}
		strSql.append(" order by variable_type,variable_code");
		List<SamVariables> samVariables = (List<SamVariables>) super.queryPage(strSql.toString(),params,new SamVariables(),start,limit);
		return samVariables;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamVariables> queryAllByCustom(String organizeSeq,String variableType,String variableCode,String variableName,
			String status){
		StringBuffer strSql = new StringBuffer("select * from sam_variables where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != variableType && !"".equals(variableType)){
			strSql.append(" and variable_type = ?");
			params.add(variableType);
		}
		if (null != variableCode && !"".equals(variableCode)){
			strSql.append(" and variable_code like ?");
			params.add("%" + variableCode + "%");
		}
		if (null != variableName && !"".equals(variableName)){
			strSql.append(" and variable_name like ?");
			params.add("%" + variableName + "%");
		}
		if (null != status && !"".equals(status)){
			strSql.append(" and status = ?");
			params.add(status);
		}
		strSql.append(" order by variable_type,variable_code");
		List<SamVariables> samVariables = (List<SamVariables>) super.queryAll(strSql.toString(),params,new SamVariables());
		return samVariables;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq,String variableType,String variableCode,String variableName,
			String status){
		StringBuffer strSql = new StringBuffer("select count(1) from sam_variables where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != variableType && !"".equals(variableType)){
			strSql.append(" and variable_type = ?");
			params.add(variableType);
		}
		if (null != variableCode && !"".equals(variableCode)){
			strSql.append(" and variable_code like ?");
			params.add("%" + variableCode + "%");
		}
		if (null != variableName && !"".equals(variableName)){
			strSql.append(" and variable_name like ?");
			params.add("%" + variableName + "%");
		}
		if (null != status && !"".equals(status)){
			strSql.append(" and status = ?");
			params.add(status);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamVariables> queryByVariableType(String organizeSeq,String variableType) {
		StringBuffer strSql = new StringBuffer("select * from sam_variables where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != variableType && !"".equals(variableType)){
			strSql.append(" and variable_type = ?");
			params.add(variableType);
		}
		strSql.append(" order by variable_type");
		List<SamVariables> samVariables = (List<SamVariables>) super.queryAll(strSql.toString(),params,new SamVariables());
		return samVariables;
	}


}
