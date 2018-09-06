
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamUserToolbar;

/**
 * SamUserToolbarDao概要说明：用户工具列
 * @author lcy
 */
public class SamUserToolbarDao extends BaseDao{
	public SamUserToolbarDao(Connection conn){
		super(conn);
	}

	public SamUserToolbar insert(SamUserToolbar userToolbar, Map<String, Object> config){
		String pk = super.insert(userToolbar,config);
		userToolbar.setToolbarSeq(pk);
		return userToolbar;
	}
	
	public void update(SamUserToolbar userToolbar, Map<String, Object> config){
		super.update(userToolbar,config);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String toolbarSeq){
		String strSql = "delete from sam_user_toolbar where toolbar_seq=?";
		List params = new ArrayList();
		params.add(toolbarSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamUserToolbar> queryByUserSeq(String userSeq,String moduleSeq){
		StringBuffer strSql = new StringBuffer("select * from sam_user_toolbar where 1=1");
		List params = new ArrayList();
		if (null != userSeq && !"".equals(userSeq)){
			strSql.append(" and user_seq = ?");
			params.add(userSeq);
		}
		if (null != moduleSeq && !"".equals(moduleSeq)){
			strSql.append(" and module_seq = ?");
			params.add(moduleSeq);
		}
		return (List<SamUserToolbar>) super.queryAll(strSql.toString(),params,new SamUserToolbar());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByModuleSeq(String moduleSeq) {
		String strSql = "delete from sam_user_toolbar where module_seq=?";
		List params = new ArrayList();
		params.add(moduleSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByUserSeq(String userSeq) {
		String strSql = "delete from sam_user_toolbar where user_seq = ?";
		List params = new ArrayList();
		params.add(userSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByModuleSeqAndUserSeq(String userSeq, String moduleSeq) {
		StringBuffer strSql = new StringBuffer("delete from sam_user_toolbar where user_seq = ?");
		List params = new ArrayList();
		params.add(userSeq);
		if (null != moduleSeq && !"".equals(moduleSeq)){
			strSql.append(" and module_seq = ?");
			params.add(moduleSeq);
		}
		super.executeSql(strSql.toString(), params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int queryCountByUserSeq(String userSeq) {
		StringBuffer strSql = new StringBuffer("SELECT count(1)" +
				" FROM sam_user_toolbar,sam_module" +
				" WHERE sam_user_toolbar.module_seq=sam_module.module_seq" +
					" AND sam_module.module_type<>'0'");
		List params = new ArrayList();
		if (null != userSeq && !"".equals(userSeq)){
			strSql.append(" AND user_seq = ?");
			params.add(userSeq);
		}
		return super.queryCount(strSql.toString(),params);
	}
	
}
