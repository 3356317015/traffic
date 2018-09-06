
package com.zhima.frame.action.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamGroup;
import com.zhima.frame.model.SamUserGroup;
import com.zhima.frame.model.SamUserModule;

/**
 * ISamUserGroup概要说明：用户角色接口类
 * @author lcy
 */
public interface ISamUserGroup {
	
	/**
	 * insert方法描述：添加用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午06:50:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserGroup 用户角色
	 * @return SamUserGroup 用户角色
	 * @throws UserBusinessException
	 */
	public SamUserGroup insert(SamUserGroup samUserGroup,Map<String, Object> config) throws UserBusinessException;

	/**
	 * update方法描述：修改用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午06:51:13
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserGroup 用户角色
	 * @throws UserBusinessException void
	 */
	public void update(SamUserGroup samUserGroup,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * deleteByPk方法描述：根据主键删除用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午06:51:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userGroupSeq　用户角色主键
	 * @throws UserBusinessException void
	 */
	public void deleteByPk(String userGroupSeq,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryByUserSeq方法描述：根据用户序号查询用户角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午06:52:09
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 用户序号
	 * @return List<SamUserGroup> 用户角色
	 * @throws UserBusinessException
	 */
	public List<SamUserGroup> queryByUserSeq(String userSeq) throws UserBusinessException;

	/**
	 * updateUserRight方法描述：更新用户权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午11:49:06
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq
	 * @param samGroups
	 * @param samUserModules
	 * @throws UserBusinessException void
	 */
	public void updateUserRight(String userSeq, List<SamGroup> samGroups,
			List<SamUserModule> samUserModules,Map<String, Object> config) throws UserBusinessException;
}
	
	
