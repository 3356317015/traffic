
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamGroup;

/**
 * SamModuleDao概要说明：角色数据库操作类
 * @author lcy
 */
public class SamGroupDao extends BaseDao{
	public SamGroupDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:39:14
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samGroup 角色
	 * @return SamGroup 角色
	 */
	public SamGroup insert(SamGroup samGroup, Map<String, Object> config){
		String pk = super.insert(samGroup,config);
		samGroup.setGroupSeq(pk);
		return samGroup;
	}
	
	/**
	 * update方法描述：修改角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:39:35
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samGroup 角色
	 */
	public void update(SamGroup samGroup, Map<String, Object> config){
		super.update(samGroup,config);
	}
	
	/**
	 * deleteByPK方法描述：根据主键删除角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:39:38
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String groupSeq){
		String strSql = "delete from sam_group where group_seq=?";
		List params = new ArrayList();
		params.add(groupSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * queryByAll方法描述：查询所有角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:39:43
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamGroup> 角色清单
	 */
	@SuppressWarnings("unchecked")
	public List<SamGroup> queryByAll(){
		String strSql = "select * from sam_group order by group_code";
		List<SamGroup> samGroups = (List<SamGroup>) super.queryAll(strSql,null,new SamGroup());
		return samGroups;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamGroup> queryByOrganizeSeq(String organizeSeq) {
		String strSql = "select * from sam_group where organize_seq = ? order by group_code";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamGroup> samGroups = (List<SamGroup>) super.queryAll(strSql,params,new SamGroup());
		return samGroups;
	}
	
}
