
package com.zhima.frame.action.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamGroupModule;

public interface ISamGroupModule {
	/**
	 * updateGroupRight方法描述：更新角色权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午12:32:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色序号
	 * @param samGroupModules 角色权限列表
	 * @throws UserBusinessException void
	 */
	public void updateGroupRight(String groupSeq, List<SamGroupModule> samGroupModules,Map<String, Object> config) throws UserBusinessException;

}
	
	
