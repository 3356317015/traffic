
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamGroupModule;

/**
 * SamGroupModuleDao概要说明：角色权限数据库操作类
 * @author lcy
 */
public class SamGroupModuleDao extends BaseDao{
	public SamGroupModuleDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加组权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午09:45:27
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samGroupModule 添加到数据库无主键组权限
	 * @return SamGroupModule　添加到数据库有主键组权限
	 */
	public SamGroupModule insert(SamGroupModule samGroupModule, Map<String, Object> config){
		String pk = super.insert(samGroupModule,config);
		samGroupModule.setGroupModuleSeq(pk);
		return samGroupModule;
	}
	
	/**
	 * update方法描述：修改组权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午09:46:16
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samGroupModule 修改的组权限
	 */
	public void update(SamGroupModule samGroupModule, Map<String, Object> config){
		super.update(samGroupModule,config);
	}
	
	/**
	 * deleteByModuleSeq方法描述：根据模块序号删除组权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午09:48:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByModuleSeq(String moduleSeq){
		String strSql = "delete from sam_group_module where module_seq=?";
		List params = new ArrayList();
		params.add(moduleSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPk(String groupModuleSeq){
		String strSql = "delete from sam_group_module where group_module_seq=?";
		List params = new ArrayList();
		params.add(groupModuleSeq);
		super.executeSql(strSql, params);
	}

	/**
	 * deleteByRightSeq方法描述：根据模块权限序号删除组权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:31:19
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleRightSeq 模块权限序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByRightSeq(String moduleRightSeq) {
		String strSql = "delete from sam_group_module where right_seq=?";
		List params = new ArrayList();
		params.add(moduleRightSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * deleteByGroupSeq方法描述：根据角色序号删除组权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:48:05
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByGroupSeq(String groupSeq){
		String strSql = "delete from sam_group_module where group_seq=?";
		List params = new ArrayList();
		params.add(groupSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamGroupModule> queryByGroupSeq(String groupSeq) {
		String strSql = "select * from sam_group_module where group_seq=?";
		List params = new ArrayList();
		params.add(groupSeq);
		return (List<SamGroupModule>) super.queryAll(strSql, params, new SamGroupModule());
	}
	
}
