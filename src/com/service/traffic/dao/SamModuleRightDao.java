
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamModuleRight;

/**
 * SamModuleDao概要说明：模块操作类
 * @author lcy
 */
public class SamModuleRightDao extends BaseDao{
	public SamModuleRightDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加模块权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-29 下午11:31:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModuleRight 添加到数据库前模块权限信息
	 * @return SamModuleRight 添加到数据库后模块权限信息
	 */
	public SamModuleRight insert(SamModuleRight samModuleRight, Map<String, Object> config){
		String pk = super.insert(samModuleRight,config);
		samModuleRight.setRightSeq(pk);
		return samModuleRight;
	}
	
	/**
	 * update方法描述：修改模块权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-29 下午11:32:51
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModule 修改的功能模块权限信息
	 */
	public void update(SamModuleRight samModuleRight, Map<String, Object> config){
		super.update(samModuleRight,config);
	}
	
	/**
	 * deleteByPK方法描述：根据主键删除
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-29 下午11:33:51
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param rightSeq 模块权限序号主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String rightSeq){
		String strSql = "delete from sam_module_right where right_seq=?";
		List params = new ArrayList();
		params.add(rightSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * deleteByModuleSeq方法描述：根据功能序号删除
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午09:36:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq 功能序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByModuleSeq(String moduleSeq){
		String strSql = "delete from sam_module_right where module_seq=?";
		List params = new ArrayList();
		params.add(moduleSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * queryByModuleSeq方法描述：根据模块序号查询权限信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-29 下午11:36:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamModuleRight> 模块权限清单
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModuleRight> queryByModuleSeq(String moduleSeq){
		String strSql = "select * from sam_module_right where module_seq = ? order by sn";
		List params = new ArrayList();
		params.add(moduleSeq);
		List<SamModuleRight> samModuleRights = (List<SamModuleRight>) super.queryAll(strSql,params,new SamModuleRight());
		return samModuleRights;
	}
	/**
	 * queryByUser方法描述：根所用户代码查询用户所有功能权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-3 下午03:57:19
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userCode 用户代码
	 * @return List<SamModuleRightBo> 用户所有功能权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamModuleRight> queryByUser(String userSeq) {
		StringBuffer strSql = new StringBuffer();
		strSql.append("SELECT * FROM (");
		strSql.append("SELECT sam_module_right.*,sam_module.module_name,sam_module.module_class,sam_module.pack_name");
		strSql.append(" FROM sam_group,sam_group_module,sam_user,sam_user_group,sam_module_right,sam_module");
		strSql.append(" WHERE sam_user.user_seq = sam_user_group.user_seq");
			strSql.append(" AND sam_user_group.group_seq = sam_group_module.group_seq");
			strSql.append(" AND sam_group_module.right_seq = sam_module_right.right_seq");
			strSql.append(" AND sam_group_module.group_seq=sam_group.group_seq");
			strSql.append(" AND sam_module_right.module_seq=sam_module.module_seq");
			strSql.append(" AND sam_group.status = '1'");
			strSql.append(" AND sam_user.user_seq = ?");
		strSql.append(" UNION ALL");
		strSql.append(" SELECT sam_module_right.*,sam_module.module_name,sam_module.module_class,sam_module.pack_name");
			strSql.append(" FROM sam_user,sam_user_module,sam_module_right,sam_module");
		strSql.append(" WHERE sam_user.user_seq = sam_user_module.user_seq");
			strSql.append(" AND sam_module_right.right_seq = sam_user_module.right_seq");
			strSql.append(" AND sam_module_right.module_seq=sam_module.module_seq");
			strSql.append(" AND sam_user.user_seq = ?) sam_module_right");
		strSql.append(" WHERE sam_module_right.status = '1'" +
				" ORDER BY sam_module_right.module_seq,sam_module_right.sn ASC");
		List params = new ArrayList();
		params.add(userSeq);
		params.add(userSeq);
		List<SamModuleRight> samModuleRights = (List<SamModuleRight>) super.queryAll(strSql.toString(),params,new SamModuleRight());
		if (null != samModuleRights && samModuleRights.size()>0){
			for (int i = samModuleRights.size()-1; i > 0; i--) {
				if (samModuleRights.get(i).getRightSeq().equals(samModuleRights.get(i-1).getRightSeq())){
					samModuleRights.remove(i);
				}
			}
		}
		return samModuleRights;
	}
	
	/**
	 * queryByGroupSeq方法描述：根据角色序号查询权限信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午11:46:04
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色序号
	 * @return List<SamModuleRight> 权限列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamModuleRight> queryByGroupSeq(String groupSeq) {
		StringBuffer strSql = new StringBuffer();
		strSql.append("SELECT sam_module_right.*");
		strSql.append(" FROM sam_group,sam_group_module,sam_module_right");
		strSql.append(" WHERE sam_group_module.right_seq = sam_module_right.right_seq");
			strSql.append(" AND sam_group_module.group_seq=sam_group.group_seq");
			strSql.append(" AND sam_group.group_seq = ?");
			strSql.append(" AND (sam_group_module.right_seq is not null OR sam_group_module.right_seq <>'')");
			strSql.append(" ORDER BY sam_module_right.module_seq,sam_module_right.sn ASC");
		List params = new ArrayList();
		params.add(groupSeq);
		List<SamModuleRight> samModuleRights = (List<SamModuleRight>) super.queryAll(strSql.toString(),params,new SamModuleRight());
		return samModuleRights;
	}

	/**
	 * queryByUserSeq方法描述：根据用户序号查询权限信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午10:40:07
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 用户序号
	 * @return List<SamModuleRight> 权限列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModuleRight> queryByUserSeq(String userSeq) {
		StringBuffer strSql = new StringBuffer();
		strSql.append("SELECT sam_module_right.*");
		strSql.append(" FROM sam_user,sam_user_module,sam_module_right");
		strSql.append(" WHERE sam_user_module.right_seq = sam_module_right.right_seq");
			strSql.append(" AND sam_user_module.user_seq=sam_user.user_seq");
			strSql.append(" AND sam_user.user_seq = ?");
			strSql.append(" AND (sam_user_module.right_seq is not null OR sam_user_module.right_seq <>'')");
			strSql.append(" ORDER BY sam_module_right.module_seq,sam_module_right.sn ASC");
		List params = new ArrayList();
		params.add(userSeq);
		List<SamModuleRight> samModuleRights = (List<SamModuleRight>) super.queryAll(strSql.toString(),params,new SamModuleRight());
		return samModuleRights;
	}

	/**
	 * queryAllRightName方法描述：查询所有权限操作
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 上午02:09:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq 模块序号
	 * @return List<SamModuleRight> 权限
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModuleRight>queryRightByModuleSeq(String moduleSeq) {
		StringBuffer strSql = new StringBuffer("SELECT right_name FROM sam_module_right where 1=1");
		List params = new ArrayList();
		if (null != moduleSeq && !"".equals(moduleSeq)){
			strSql.append(" and module_seq = ?");
			params.add(moduleSeq);
		}
		strSql.append(" GROUP BY right_name");
		return (List<SamModuleRight>) super.queryAll(strSql.toString(), params, new SamModuleRight());
	}

	@SuppressWarnings("unchecked")
	public List<SamModuleRight> queryRightByOrganizeSeq(String organizeSeq) {
		String strSql = "select sam_module_right.* from sam_module_right,sam_module" +
				" where sam_module_right.module_seq = sam_module.module_seq" +
				" order by module_seq";
		List<SamModuleRight> samModuleRights = (List<SamModuleRight>) super.queryAll(strSql,null,new SamModuleRight());
		return samModuleRights;
	}

}
