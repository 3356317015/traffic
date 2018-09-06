
package com.zhima.frame.action.sam;

import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUpinfo;

/**
 * ISamUpgrade概要说明：在线升级操作接口
 * @author lcy
 */
public interface ISamUpinfo {
	
	/**
	 * insert方法描述：添加升级文件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 下午12:56:10
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUpgrade 升级清单
	 * @return SamUpgrade 升级清单
	 * @throws UserBusinessException
	 */
	public SamUpinfo insert(String fileVer, String remark,Map<String, Object> config) throws UserBusinessException;
}
	
	
