
package com.service.traffic.business.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUpblob;
import com.zhima.frame.model.SamUpgrade;

/**
 * ISamUpgrade概要说明：在线升级操作接口
 * @author lcy
 */
public interface INetSamUpgrade {
	
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
	public SamUpgrade insert(SamUpgrade samUpgrade,List<SamUpblob> samUpblobs,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryPageByCustom方法描述：分页查询升级文件清单
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 上午12:09:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<SamUpgrade> 升级清单
	 * @throws UserBusinessException
	 */
	public List<SamUpgrade> queryPageByCustom(String organizeSeq, int start, int limit) throws UserBusinessException;
	
	/**
	 * queryCountByCustom方法描述：查询升级文件清单记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 上午12:10:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 记录数
	 * @throws UserBusinessException
	 */
	public int queryCountByCustom(String organizeSeq) throws UserBusinessException;

	/**
	 * delete方法描述：根据主键删除升级文件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 上午12:28:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUpgrades 升级文件
	 * @throws UserBusinessException void
	 */
	public void delete(List<SamUpgrade> samUpgrades,Map<String, Object> config) throws UserBusinessException;

	/**
	 * queryMaxVer方法描述：查询最大版本号
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 下午12:29:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return SamUpgrade
	 * @throws UserBusinessException
	 */
	public SamUpgrade queryMaxVer(String organizeSeq) throws UserBusinessException;

	/**
	 * queryUpgradeByVer方法描述：根据客户端版本号检测是否有新版本
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-1 下午10:31:18
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sysVersion 当前版本号
	 * @return int 升级文件数
	 * @throws UserBusinessException
	 */
	public int queryUpgradeByVer(String organizeSeq, String sysVersion) throws UserBusinessException;
	

}
	
	
