
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamUserGroup;
/**
 * SamUserGroupDao概要说明：用户角色数据库操作类
 * @author lcy
 */
public class SamUserGroupDao extends BaseDao{
	public SamUserGroupDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:59:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserGroup 用户角色
	 * @return SamUserGroup 用户角色
	 */
	public SamUserGroup insert(SamUserGroup samUserGroup, Map<String, Object> config){
		String pk = super.insert(samUserGroup,config);
		samUserGroup.setUserGroupSeq(pk);
		return samUserGroup;
	}
	
	/**
	 * update方法描述：修改用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:59:06
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserGroup 用户角色
	 */
	public void update(SamUserGroup samUserGroup, Map<String, Object> config){
		super.update(samUserGroup,config);
	}
	
	/**
	 * deleteByPk方法描述：根据主键删除用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午06:58:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userGroupSeq 用户角色主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPk(String userGroupSeq){
		String strSql = "delete from sam_user_group where user_group_seq=?";
		List params = new ArrayList();
		params.add(userGroupSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * deleteByGroupSeq方法描述：根据角色序号删除用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:59:09
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByGroupSeq(String groupSeq){
		String strSql = "delete from sam_user_group where group_seq=?";
		List params = new ArrayList();
		params.add(groupSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * deleteByUserSeq方法描述：根据用户序号删除用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午09:56:35
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 用户序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByUserSeq(String userSeq) {
		String strSql = "delete from sam_user_group where user_seq=?";
		List params = new ArrayList();
		params.add(userSeq);
		super.executeSql(strSql, params);
	}

	/**
	 * queryByUserSeq方法描述：根据用户序号查询用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午07:06:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 用户序号
	 * @return List<SamUserGroup> 用户角色
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamUserGroup> queryByUserSeq(String userSeq){
		String strSql = "select * from sam_user_group where user_seq=?";
		List params = new ArrayList();
		params.add(userSeq);
		List<SamUserGroup> samUserGroups = (List<SamUserGroup>) super.queryAll(strSql,params,new SamUserGroup());
		if (null != samUserGroups && samUserGroups.size()>0){
			return samUserGroups;
		} else{
			return null;
		}
	}
	
}
