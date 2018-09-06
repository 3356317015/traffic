
package com.zhima.frame.action.sam;

import java.util.List;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUserOnline;

/**
 * ISamUserOnline概要说明：在线用户操作接口
 * @author lcy
 */
public interface ISamUserOnline {
	/**
	 * queryPageByAll方法描述：分页查询所有在线用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午10:01:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamUserOnline> 用户清单
	 * @throws UserBusinessException
	 */
	public List<SamUserOnline> queryPageByAll(String organizeSeq, int start, int limit) throws UserBusinessException;
	
	/**
	 * queryCountByAll方法描述：查询在线用户记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午09:50:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 记录数
	 * @throws UserBusinessException
	 */
	public int queryCountByAll(String organizeSeq) throws UserBusinessException;
	
	/**
	 * clearUser方法描述：清理不在线用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午09:37:59
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @throws UserBusinessException void
	 */
	public void clearUser(String organizeSeq, int onlineUpdateTime) throws UserBusinessException;
}
	
	
