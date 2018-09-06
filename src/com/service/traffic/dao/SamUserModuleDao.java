
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamUserModule;

/**
 * SamModuleDao概要说明：用户权限操作类
 * @author lcy
 */
public class SamUserModuleDao extends BaseDao{
	public SamUserModuleDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加用户权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午10:20:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserModule 添加到数据库无主键权限
	 * @return SamUserModule　添加到数据库有主键权限
	 */
	public SamUserModule insert(SamUserModule samUserModule, Map<String, Object> config){
		String pk = super.insert(samUserModule,config);
		samUserModule.setUserModuleSeq(pk);
		return samUserModule;
	}
	
	/**
	 * update方法描述：修改用户权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午10:21:48
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserModule 修改的用户权限
	 */
	public void update(SamUserModule samUserModule, Map<String, Object> config){
		super.update(samUserModule,config);
	}
	
	/**
	 * deleteByModuleSeq方法描述：根据模块序号删除用户权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午09:48:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByModuleSeq(String moduleSeq){
		String strSql = "delete from sam_user_module where module_seq=?";
		List params = new ArrayList();
		params.add(moduleSeq);
		super.executeSql(strSql, params);
	}

	/**
	 * deleteByRightSeq方法描述：根据模块权限序号删除用户权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:32:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleRightSeq 模块权限序号
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByRightSeq(String moduleRightSeq) {
		String strSql = "delete from sam_user_module where right_seq=?";
		List params = new ArrayList();
		params.add(moduleRightSeq);
		super.executeSql(strSql, params);
	}

	/**
	 * deleteByUserSeq方法描述：根据用户序号删除用户权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午09:58:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 用户序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByUserSeq(String userSeq) {
		String strSql = "delete from sam_user_module where user_seq=?";
		List params = new ArrayList();
		params.add(userSeq);
		super.executeSql(strSql, params);
	}
	
}
