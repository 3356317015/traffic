
package com.service.traffic.business.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModule;

/**
 * ISamModule概要说明：模块管理接口
 * @author lcy
 */
public interface INetSamModule {
	
	/**
	 * insert方法描述：添加模块信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-18 下午08:13:19
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModule 添加到数据为前模块信息
	 * @return SamModule 带主键的模块信息
	 * @throws UserBusinessException
	 */
	public SamModule insert(SamModule samModule,String copyModuleSeq,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * update方法描述：修改模块信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-18 下午08:14:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModule 修改的模块信息
	 * @throws UserBusinessException
	 */
	public void update(SamModule samModule,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * deleteByPk方法描述：根据主键删除模块信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-20 下午11:47:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleSeq 模块主键
	 * @throws UserBusinessException
	 */
	public void deleteByPk(SamModule samModule,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryTopModule方法描述：查询顶级权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-3 下午12:58:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamModule> 模块清单
	 * @throws UserBusinessException
	 */
	public List<SamModule> queryTopModule(String organizeSeq) throws UserBusinessException;
	
	/**
	 * queryChildModule方法描述：查询子权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-3 下午01:25:20
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamModule> 模块清单
	 * @throws UserBusinessException
	 */
	public List<SamModule> querySubModule(String organizeSeq) throws UserBusinessException;
	

	/**
	 * querySubAll方法描述：根据父节点查询所有功能
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-20 下午03:36:40
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param parentSeq 父节点序号
	 * @return List<SamModule> 模块清单
	 * @throws UserBusinessException
	 */
	public List<SamModule> querySubAll(String parentSeq) throws UserBusinessException;
	
	/**
	 * queryModuleByUser方法描述：按用户代码查询用户有效功能
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-29 下午11:40:41
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userCode 用户代码
	 * @return List<SamModule> 模块清单
	 * @throws UserBusinessException
	 */
	public List<SamModule> queryModuleByUser(String userSeq) throws UserBusinessException;

	/**
	 * queryByGroupSeq方法描述：根据角序序号查询功能信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午08:19:36
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色序号
	 * @return List<SamModule> 功能清单
	 * @throws UserBusinessException
	 */
	public List<SamModule> queryByGroupSeq(String groupSeq) throws UserBusinessException;
	
	/**
	 * queryModuleParent方法描述：查询节点
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-9 上午10:37:06
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamModule> 节点
	 * @throws UserBusinessException
	 */
	public List<SamModule> queryModuleParent(String organizeSeq) throws UserBusinessException;

	/**
	 * queryByUserSeq方法描述：根据用户序号查询用户功能
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午10:27:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 用户序号
	 * @return List<SamModule> 功能清单
	 * @throws UserBusinessException
	 */
	public List<SamModule> queryByUserSeq(String userSeq) throws UserBusinessException;

	/**
	 * queryByType方法描述：根据模块类型查询
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 上午12:07:51
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleType 模块类型
	 * @return List<SamModule> 模块
	 * @throws UserBusinessException
	 */
	public List<SamModule> queryByType(String organizeSeq, String moduleType) throws UserBusinessException;

	public List<SamModule> queryToolbarByUserSeq(String userSeq) throws UserBusinessException;


}
	
	
