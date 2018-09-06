
package com.zhima.frame.action.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUserSecret;

/**
 * ISamUserColumn概要说明：用户自定义表格接口
 * @author lcy
 */
public interface ISamUserSecret {
	
	/**
	 * queryByUserSecret方法描述：查询表格保密列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-4 上午11:34:36
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param className 类名
	 * @param gridName 表格名称
	 * @return List<SamUserSecret> 表格保密列
	 * @throws UserBusinessException
	 */
	public List<SamUserSecret> queryByGridSecret(String className,String gridName) throws UserBusinessException;

	/**
	 * updateUserSecret方法描述：修改表格保密列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-4 下午01:59:17
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param secrets 保密项
	 * @param gridConfig 表格配置信息
	 * @throws UserBusinessException void
	 */
	public void updateUserSecret(List<SamUserSecret> secrets,
			String className,String gridName, Map<String, Object> config) throws UserBusinessException;
	


}
	
	
