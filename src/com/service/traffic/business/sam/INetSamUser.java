
package com.service.traffic.business.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUser;

/**
 * ISamUser概要说明：用户操作接口
 * @author lcy
 */
public interface INetSamUser {
	/**
	 * insert方法描述：添加用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-7 上午10:56:54
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUser 添加数据库前用户信息
	 * @return SamUser 添加数据库后用户信息
	 * @throws UserBusinessException
	 */
	public SamUser insert(SamUser samUser,Map<String, Object> config) throws UserBusinessException;
	/**
	 * update方法描述：更新用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-7 上午11:07:46
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUser 更新数据库前用户信息
	 * @return SamUser 更新数据库后用户信息
	 * @throws UserBusinessException
	 */
	public void update(SamUser samUser,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * deleteByPk方法描述：根据主键删除用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午09:50:23
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 用户主键
	 * @throws UserBusinessException void
	 */
	public void deleteByPk(String userSeq,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryByAll方法描述：查询所有用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午10:01:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamUser> 用户清单
	 * @throws UserBusinessException
	 */
	public List<SamUser> queryByAll() throws UserBusinessException;
	
	/**
	 * queryByOrganize方法描述：根据网点查询用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-12-21 下午11:47:23
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeSeq
	 * @throws UserBusinessException
	 * @return List<?>
	 */
	public List<SamUser> queryByOrganize(String organizeSeq) throws UserBusinessException;
}
	
	
