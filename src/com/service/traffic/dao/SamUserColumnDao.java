
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamUserColumn;

/**
 * SamUserColumnDao概要说明：用户自定义表格操作类
 * @author lcy
 */
public class SamUserColumnDao extends BaseDao{
	public SamUserColumnDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加自定义表格列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-2 下午11:48:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userColumn 自定义表格列
	 * @return SamUserColumn 自定义表格列
	 */
	public SamUserColumn insert(SamUserColumn userColumn, Map<String, Object> config){
		String pk = super.insert(userColumn,config);
		userColumn.setUserColumnSeq(pk);
		return userColumn;
	}
	
	/**
	 * update方法描述：修改用户自定义表格列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-2 下午11:48:40
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userColumn 自定义表格列
	 */
	public void update(SamUserColumn userColumn, Map<String, Object> config){
		super.update(userColumn,config);
	}
	
	/**
	 * deleteByPK方法描述：删除自定义表格列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-2 下午11:49:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userColumnSeq 自定义表格列序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String userColumnSeq){
		String strSql = "delete from sam_user_column where user_column_seq=?";
		List params = new ArrayList();
		params.add(userColumnSeq);
		super.executeSql(strSql, params);
	}

	/**
	 * queryByUserSeq方法描述：根据用户序号查询自定义表格列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-3 上午12:08:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param className 类名
	 * @param gridName 表格名称
	 * @return List<SamUserColumn> 用户自定义表格列
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamUserColumn> queryByUserSeq(String className, String gridName, String userSeq){
		String strSql = "select * from sam_user_column where class_name = ? and grid_name = ? and user_seq = ? order by sn";
		List params = new ArrayList();
		params.add(className);
		params.add(gridName);
		params.add(userSeq);
		List<SamUserColumn> userColumns = (List<SamUserColumn>) super.queryAll(strSql,params,new SamUserColumn());
		if (null != userColumns && userColumns.size()>0){
			return userColumns;
		} else{
			return null;
		}
	}

	/**
	 * deleteByUser方法描述：删除用户个性化表格设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-4 上午12:29:48
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param className 类名
	 * @param gridName 表格名称
	 * @param userSeq 用户主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByUser(String className, String gridName, String userSeq) {
		String strSql = "delete from sam_user_column where class_name=? and grid_name =? and user_seq = ?";
		List params = new ArrayList();
		params.add(className);
		params.add(gridName);
		params.add(userSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByUserSeq(String userSeq) {
		String strSql = "delete from sam_user_column where user_seq = ?";
		List params = new ArrayList();
		params.add(userSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByModuleClass(String className) {
		String strSql = "delete from sam_user_column where class_name=?";
		List params = new ArrayList();
		params.add(className);
		super.executeSql(strSql, params);
	}
}
