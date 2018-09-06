
package com.zhima.frame.action.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUserColumn;
import com.zhima.widget.grid.GridColumn;

/**
 * ISamUserColumn概要说明：用户自定义表格接口
 * @author lcy
 */
public interface ISamUserColumn {
	
	/**
	 * queryByUserColumn方法描述：查询用户个性化表格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-3 下午10:41:10
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param className 类名
	 * @param gridName 表格名称
	 * @return List<SamUserColumn> 用户表格个性设置
	 * @throws UserBusinessException
	 */
	public List<SamUserColumn> queryByUserColumn(String className,String gridName,String userSeq) throws UserBusinessException;
	
	/**
	 * queryByUserColumn方法描述：查询用户表格列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-3 上午12:13:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param columns 表格基础列
	 * @param className 类名
	 * @param gridName 表格名称
	 * @return List<GridColumn> 列信息
	 * @throws UserBusinessException
	 */
	public List<GridColumn> queryByGridColumn(List<GridColumn> columns,String className,String gridName, String userSeq) throws UserBusinessException;

	/**
	 * queryByValidColumn方法描述：查询用户有效列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-3 下午11:02:51
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param columns 基础列
	 * @param className 类名
	 * @param gridName 表格名称
	 * @return List<SamUserColumn> 有效表格列
	 * @throws UserBusinessException
	 */
	public List<SamUserColumn> queryByValidColumn(List<GridColumn> columns, String className, String gridName, String userSeq) throws UserBusinessException;

	/**
	 * updateUserColumn方法描述：更新用户表格设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-4 上午12:22:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userColumns 用户表格设置
	 * @throws UserBusinessException void
	 */
	public void updateUserColumn(List<SamUserColumn> userColumns,String className, String gridName,
			String userSeq, Map<String, Object> config) throws UserBusinessException;

	/**
	 * deleteByUserGird方法描述：删除表格用户个性化设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-4 上午01:33:49
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param gridConfig
	 * @throws UserBusinessException void
	 */
	public void deleteByUserGrid(String className, String gridName, String userSeq, Map<String, Object> config) throws UserBusinessException;

}
	
	
