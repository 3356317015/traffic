
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamUserSecret;

/**
 * SamUserSecretDao概要说明：表格保密列
 * @author lcy
 */
public class SamUserSecretDao extends BaseDao{
	public SamUserSecretDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：新增表格保密列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-3 上午12:02:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSecret 表格保密列
	 * @return SamUserSecret 表格保密列
	 */
	public SamUserSecret insert(SamUserSecret userSecret, Map<String, Object> config){
		String pk = super.insert(userSecret,config);
		userSecret.setUserSecretSeq(pk);
		return userSecret;
	}
	
	/**
	 * update方法描述：修改表格保密列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-3 上午12:03:36
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSecret 表格保密列
	 */
	public void update(SamUserSecret userSecret, Map<String, Object> config){
		super.update(userSecret,config);
	}
	
	/**
	 * deleteByPK方法描述：删除表格保密列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-3 上午12:05:16
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSecretSeq void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String userSecretSeq){
		String strSql = "delete from sam_user_secret where user_secret_seq=?";
		List params = new ArrayList();
		params.add(userSecretSeq);
		super.executeSql(strSql, params);
	}

	/**
	 * queryByUserSeq方法描述：查询用户表格表格保密列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-3 上午12:06:40
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param className 类名
	 * @param gridName 表格名称
	 * @return List<SamUserSecret> 表格保密列
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamUserSecret> queryByUserSeq(String className, String gridName,String userSeq){
		String strSql = "select * from sam_user_secret where class_name = ? and grid_name = ? and user_seq = ?";
		List params = new ArrayList();
		params.add(className);
		params.add(gridName);
		params.add(userSeq);
		List<SamUserSecret> userSecrets = (List<SamUserSecret>) super.queryAll(strSql,params,new SamUserSecret());
		if (null != userSecrets && userSecrets.size()>0){
			return userSecrets;
		} else{
			return null;
		}
	}
	
	/**
	 * queryByGridName方法描述：查询表格保密项
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-4 下午12:30:43
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param className 类名
	 * @param gridName 表格名称
	 * @return List<SamUserSecret> 保密列
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamUserSecret> queryByGridName(String className, String gridName){
		String strSql = "select * from sam_user_secret where class_name = ? and grid_name = ?";
		List params = new ArrayList();
		params.add(className);
		params.add(gridName);
		List<SamUserSecret> userSecrets = (List<SamUserSecret>) super.queryAll(strSql,params,new SamUserSecret());
		if (null != userSecrets && userSecrets.size()>0){
			return userSecrets;
		} else{
			return null;
		}
	}

	/**
	 * deleteByGrid方法描述：删除表格保密项
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-4 下午02:02:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param className 类名
	 * @param gridName 表格名称
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByGrid(String className, String gridName) {
		String strSql = "delete from sam_user_secret where class_name=? and grid_name =?";
		List params = new ArrayList();
		params.add(className);
		params.add(gridName);
		super.executeSql(strSql, params);
		
	}
}
