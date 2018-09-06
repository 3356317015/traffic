
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamParameter;
import com.zhima.util.DateUtils;


public class SamParameterDao extends BaseDao{
	public SamParameterDao(Connection conn){
		super(conn);
	}
	
	public SamParameter insert(SamParameter samParameter, Map<String, Object> config){
		String pk = super.insert(samParameter,config);
		samParameter.setParameterSeq(pk);
		return samParameter;
	}
	
	public void update(SamParameter samParameter, Map<String, Object> config){
		super.update(samParameter,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String parameterSeq){
		String strSql = "delete from sam_parameter where parameter_seq=?";
		List params = new ArrayList();
		params.add(parameterSeq);
		super.executeSql(strSql, params);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<SamParameter> queryByAll(){
		String strSql = "select * from sam_parameter";
		List<SamParameter> parameters = (List<SamParameter>) super.queryAll(strSql,null,new SamParameter());
		return parameters;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamParameter> queryPageByCustom(String organizeSeq,String groupName,int start,int limit){
		StringBuffer strSql = new StringBuffer("select sam_parameter.*,sam_organize.organize_name" +
				" from sam_parameter" +
				" left join sam_organize on sam_parameter.organize_seq = sam_organize.organize_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and sam_parameter.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != groupName && !"".equals(groupName)){
			strSql.append(" and sam_parameter.group_name = ?");
			params.add(groupName);
		}
		strSql.append(" order by sam_parameter.parameter_code");
		List<SamParameter> parameters = (List<SamParameter>) super.queryPage(strSql.toString(),params,new SamParameter(),start,limit);
		return parameters;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamParameter> queryAllByCustom(String organizeSeq,String groupName){
		StringBuffer strSql = new StringBuffer("select sam_parameter.*,sam_organize.organize_name" +
				" from sam_parameter" +
				" left join sam_organize on sam_parameter.organize_seq = sam_organize.organize_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and sam_parameter.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != groupName && !"".equals(groupName)){
			strSql.append(" and sam_parameter.group_name = ?");
			params.add(groupName);
		}
		strSql.append(" order by parameter_code");
		List<SamParameter> parameters = (List<SamParameter>) super.queryAll(strSql.toString(),params,new SamParameter());
		return parameters;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq,String groupName){
		StringBuffer strSql = new StringBuffer("select count(1) from sam_parameter where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and sam_parameter.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != groupName && !"".equals(groupName)){
			strSql.append(" and sam_parameter.group_name = ?");
			params.add(groupName);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings("unchecked")
	public SamParameter queryByCode(String organizeSeq, String parameterCode, String parameterName,
			String defalutValue) throws UserBusinessException {
		String sql = "select * from sam_parameter where organize_seq=? and parameter_code = ?";
		List<String> params = new ArrayList<String>();
		params.add(organizeSeq);
		params.add(parameterCode);
		List<SamParameter> parameters = (List<SamParameter>) super.queryAll(sql, params, new SamParameter());
		if(null != parameters && parameters.size() > 0){
			return parameters.get(0);
		} else {
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			SamParameter parameter = new SamParameter();
			parameter.setOrganizeSeq(organizeSeq);
			parameter.setParameterCode(parameterCode);
			parameter.setParameterName(parameterName);
			parameter.setParameterValue(defalutValue);
			parameter.setCreateTime(currTime);
			parameter.setUpdateTime(currTime);
			String pk  = super.insert(parameter,null);
			parameter.setParameterSeq(pk);
			return parameter;
		}
	}


}
