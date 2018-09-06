
package com.service.traffic.business.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModuleRight;

/**
 * ISamModuleRight概要说明：模块权限管理接口
 * @author lcy
 */
public interface INetSamModuleRight {
	
	/**
	 * insert方法描述：添加模块权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:21:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModuleRight 添加前模块权限
	 * @return SamModuleRight 添加后模块权限
	 * @throws UserBusinessException
	 */
	public SamModuleRight insert(SamModuleRight samModuleRight,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * update方法描述：修改模块权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:23:17
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModuleRight 修改的模块权限
	 * @throws UserBusinessException void
	 */
	public void update(SamModuleRight samModuleRight,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * deleteByPk方法描述：根据模块权限序号主键删除
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:23:40
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleRightSeq 模块权限序号主键
	 * @throws UserBusinessException void
	 */
	public void deleteByPk(List<SamModuleRight> samModuleRights, Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryModuleRight方法描述：根据功能序号查询模块权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-3 下午02:46:19
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq 功能序号
	 * @return List<SamModuleRight> 功能权限清单
	 * @throws UserBusinessException
	 */
	public List<SamModuleRight> queryModuleRight (String moduleSeq) throws UserBusinessException;
	
	/**
	 * queryByModuleSeq方法描述：根据功能序号查询权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 上午12:31:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq 模块序号
	 * @return List<SamModuleRight> 功能权限信息
	 * @throws UserBusinessException
	 */
	public List<SamModuleRight> queryByModuleSeq(String moduleSeq) throws UserBusinessException;

	/**
	 * queryByGroupSeq方法描述：根据角色序号查询权限信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午11:47:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色序号
	 * @return List<SamModuleRight> 权限列表
	 * @throws UserBusinessException
	 */
	public List<SamModuleRight> queryByGroupSeq(String groupSeq) throws UserBusinessException;

	/**
	 * queryByUserSeq方法描述：根据用户序号查询权限信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午10:34:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 用户序号
	 * @return List<SamModuleRight> 权限列表
	 * @throws UserBusinessException
	 */
	public List<SamModuleRight> queryByUserSeq(String userSeq) throws UserBusinessException;

	/**
	 * queryRightByUser方法描述：按用户代码查询用户有效功能权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-3 下午03:54:14
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userCode 用户代码
	 * @return List<SamModuleRight> 模块权限清单
	 * @throws UserBusinessException
	 */
	public List<SamModuleRight> queryRightByUser(String userSeq) throws UserBusinessException;
	
	/**
	 * queryAllRightName方法描述：查询所有操作
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 上午02:04:36
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq 模块序号
	 * @return List<SamModuleRight> 操作权限
	 * @throws UserBusinessException
	 */
	public List<SamModuleRight> queryRightByModuleSeq(String moduleSeq) throws UserBusinessException;

	public List<SamModuleRight> queryRightByOrganizeSeq(String organizeSeq) throws UserBusinessException;
}
	
	
