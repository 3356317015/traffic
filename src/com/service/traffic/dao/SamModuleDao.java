
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamModule;

/**
 * SamModuleDao概要说明：模块操作类
 * @author lcy
 */
public class SamModuleDao extends BaseDao{
	public SamModuleDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加功能模块
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-29 下午11:31:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModule 添加到数据库前模块信息
	 * @return SamModule 添加到数据库后模块信息
	 */
	public SamModule insert(SamModule samModule, Map<String, Object> config){
		String pk = super.insert(samModule,config);
		samModule.setModuleSeq(pk);
		return samModule;
	}
	
	/**
	 * update方法描述：修改功能模块信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-29 下午11:32:51
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModule 修改的功能模块信息
	 */
	public void update(SamModule samModule, Map<String, Object> config){
		super.update(samModule,config);
	}
	
	/**
	 * deleteByPK方法描述：根据主键删除
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-29 下午11:33:51
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq 功能模块序号主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String moduleSeq){
		String strSql = "delete from sam_module where module_seq=?";
		List params = new ArrayList();
		params.add(moduleSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * queryByPK方法描述：根据主键查询模块信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-20 下午07:57:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq 模块主键
	 * @return SamModule 模块信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SamModule queryByPK(String moduleSeq){
		String strSql = "select * from sam_module where module_seq=?";
		List params = new ArrayList();
		params.add(moduleSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(strSql, params, new SamModule());
		if (null != samModules && samModules.size()>0){
			return samModules.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * queryByAll方法描述：查询所有模块信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-29 下午11:36:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamModule> 模块清单
	 */
	@SuppressWarnings("unchecked")
	public List<SamModule> queryByAll(){
		String strSql = "select * from sam_module order by parent_seq,sn";
		List<SamModule> samModules = (List<SamModule>) super.queryAll(strSql,null,new SamModule());
		return samModules;
	}
	
	/**
	 * queryTopModule方法描述：查询顶级权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-3 下午01:00:59
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamModule> 模块清单
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModule> queryTopModule(String organizeSeq){
		String strSql = "select * from sam_module where organize_seq = ? and parent_seq = '0' order by parent_seq,sn";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(strSql,params,new SamModule());
		return samModules;
	}
	
	/**
	 * querySubModule方法描述：查询子级权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-3 下午01:27:36
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamModule> 模块清单
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModule> querySubModule(String organizeSeq) {
		String strSql = "select * from sam_module where organize_seq = ? and  parent_seq <> '0' order by parent_seq,sn";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(strSql,params,new SamModule());
		return samModules;
	}
	
	/**
	 * querySubAll方法描述：根据父项节点查询权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-20 下午03:39:55
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param parentSeq 父项序号
	 * @return List<SamModule> 模块清单
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamModule> querySubAll(String parentSeq) {
		String strSql = "select * from sam_module where parent_seq = ? order by sn";
		List params = new ArrayList();
		params.add(parentSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(strSql,params,new SamModule());
		return samModules;
	}
	
	/**
	 * queryByUser方法描述：查询用户权限模块
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-2 下午05:48:07
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamModule> 模块清单
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModule> queryByUser(String userSeq){
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT * FROM (");
		sqlStr.append("SELECT sam_module.*");
			sqlStr.append(" FROM sam_group,sam_group_module,sam_user,sam_user_group,sam_module");
		sqlStr.append(" WHERE sam_user.user_seq = sam_user_group.user_seq");
			sqlStr.append(" AND sam_user_group.group_seq = sam_group_module.group_seq");
			sqlStr.append(" AND sam_group_module.module_seq = sam_module.module_seq");
			sqlStr.append(" AND sam_group_module.group_seq=sam_group.group_seq");
			sqlStr.append(" AND sam_group.status = '1'");
			sqlStr.append(" AND sam_user.user_seq = ?");
		sqlStr.append(" UNION ALL");
		sqlStr.append(" SELECT sam_module.* FROM sam_user,sam_user_module,sam_module");
		sqlStr.append(" WHERE sam_user.user_seq = sam_user_module.user_seq");
			sqlStr.append(" AND sam_module.module_seq = sam_user_module.module_seq");
			sqlStr.append(" AND sam_user.user_seq = ?) sam_module");
		sqlStr.append(" WHERE sam_module.status = '1'" +
				" ORDER BY sam_module.parent_seq,sam_module.sn ASC");
		List params = new ArrayList();
		params.add(userSeq);
		params.add(userSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(sqlStr.toString(),params,new SamModule());
		if (null != samModules && samModules.size()>0){
			for (int i = samModules.size()-1; i > 0; i--) {
				if (samModules.get(i).getModuleSeq().equals(samModules.get(i-1).getModuleSeq())){
					samModules.remove(i);
				}
			}
		}
		return samModules;
	}
	
	/**
	 * queryByGruopSeq方法描述：根据角色序号查询所有功能
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午08:16:48
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色序号
	 * @return List<SamModule> 功能清单
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModule> queryByGroupSeq(String groupSeq){
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT sam_module.*");
		sqlStr.append(" FROM sam_group,sam_group_module,sam_module");
		sqlStr.append(" WHERE sam_group_module.module_seq = sam_module.module_seq");
			sqlStr.append(" AND sam_group_module.group_seq=sam_group.group_seq");
			sqlStr.append(" AND sam_group.group_seq = ?");
			sqlStr.append(" AND (sam_group_module.right_seq IS NULL OR sam_group_module.right_seq ='')");
			sqlStr.append("	ORDER BY sam_module.parent_seq,sam_module.sn ASC");
		List params = new ArrayList();
		params.add(groupSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(sqlStr.toString(),params,new SamModule());
		return samModules;
	}
	
	/**
	 * queryByUserSeq方法描述：根据用户序号查询用户功能
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午10:33:03
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 用户序号
	 * @return List<SamModule> 功能清单
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamModule> queryByUserSeq(String userSeq) {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT sam_module.*");
		sqlStr.append(" FROM sam_user,sam_user_module,sam_module");
		sqlStr.append(" WHERE sam_user_module.module_seq = sam_module.module_seq");
			sqlStr.append(" AND sam_user_module.user_seq=sam_user.user_seq");
			sqlStr.append(" AND sam_user.user_seq = ?");
			sqlStr.append(" AND (sam_user_module.right_seq IS NULL OR sam_user_module.right_seq ='')");
			sqlStr.append("	ORDER BY sam_module.parent_seq,sam_module.sn ASC");
		List params = new ArrayList();
		params.add(userSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(sqlStr.toString(),params,new SamModule());
		return samModules;
	}

	/**
	 * queryModuleParent方法描述：查询功能节点信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-9 上午10:39:43
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamModule> 功能节点信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModule> queryModuleParent(String organizeSeq) {
		String strSql = "select * from sam_module where organize_seq = ? and module_type = '0' order by parent_seq,sn";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(strSql,params,new SamModule());
		return samModules;
	}

	/**
	 * queryByType方法描述：根据模块类型查询
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 上午12:23:38
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleType 模块类型
	 * @return List<SamModule> 所有功能模块
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModule> queryByType(String organizeSeq, String moduleType) {
		String strSql = "select * from sam_module where organize_seq=? and module_type =?";
		List params = new ArrayList();
		params.add(organizeSeq);
		params.add(moduleType);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(strSql,params,new SamModule());
		return samModules;
	}

	/**
	 * queryByParentSeq方法描述：根据父项序号查询模块
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2014-2-16 下午09:17:14
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param parentSeq 父项模块序号
	 * @return List<SamModule> 模块
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamModule> queryByParentAndUser(String parentSeq,String userSeq) {
		String strSql = "SELECT * FROM sam_module,sam_user_toolbar" +
				" WHERE sam_user_toolbar.module_seq = sam_module.module_seq" +
				" AND sam_module.parent_seq=? AND user_seq=?";
		List params = new ArrayList();
		params.add(parentSeq);
		params.add(userSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(strSql,params,new SamModule());
		return samModules;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamModule> queryToolbarByUserSeq(String userSeq) {
		String strSql = "SELECT * FROM sam_module,sam_user_toolbar" +
				" WHERE sam_user_toolbar.module_seq = sam_module.module_seq" +
				" AND user_seq=?";
		List params = new ArrayList();
		params.add(userSeq);
		List<SamModule> samModules = (List<SamModule>) super.queryAll(strSql,params,new SamModule());
		return samModules;
	}
	
}
