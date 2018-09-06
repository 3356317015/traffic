
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamUpgrade;

/**
 * SamUpgradeDao概要说明：在线升级数据操作类
 * @author lcy
 */
public class SamUpgradeDao extends BaseDao{
	
	public SamUpgradeDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加升级文件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 上午12:15:16
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUpgrade 升级文件
	 * @return SamUpgrade 升级文件
	 */
	public SamUpgrade insert(SamUpgrade samUpgrade, Map<String, Object> config){
		String pk = super.insert(samUpgrade,config);
		samUpgrade.setUpgradeSeq(pk);
		return samUpgrade;
	}
	
	/**
	 * update方法描述：修改升级文件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 上午12:15:48
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUpgrade 升级文件
	 */
	public void update(SamUpgrade samUpgrade, Map<String, Object> config){
		super.update(samUpgrade,config);
	}
	
	/**
	 * deleteByPK方法描述：根据主键删除升组文件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 上午12:16:10
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param upgradeSeq 主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String upgradeSeq){
		String strSql = "delete from sam_upgrade where upgrade_seq=?";
		List params = new ArrayList();
		params.add(upgradeSeq);
		super.executeSql(strSql, params);
	}
	
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamUpgrade> queryPageByCustom(String organizeSeq, int start, int limit) {
		String strSql = "select * from sam_upgrade where organize_seq =? order by file_ver,file_name";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamUpgrade> samUpgrades = (List<SamUpgrade>) super.queryPage(strSql,params,new SamUpgrade(),start,limit);
		return samUpgrades;
	}
	
	/**
	 * queryCountByCustom方法描述：查询升级文件清单记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 上午12:10:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 记录数
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int queryCountByCustom(String organizeSeq){
		String strSql = "select count(1) from sam_upgrade where organize_seq =?";
		List params = new ArrayList();
		params.add(organizeSeq);
		return super.queryCount(strSql, params);
	}

	/**
	 * queryMaxVer方法描述：查询最大版本号
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 下午12:35:22
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return SamUpgrade
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SamUpgrade queryMaxVer(String organizeSeq) {
		String strSql = "select * from sam_upgrade where organize_seq =? order by file_ver desc";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamUpgrade> samUpgrades = (List<SamUpgrade>) super.queryAll(strSql,params,new SamUpgrade());
		if (null != samUpgrades && samUpgrades.size()>0){
			return samUpgrades.get(0);
		}else{
			return null;
		}
	}

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryUpgradeByVer(String organizeSeq, String sysVersion) {
		String strSql = "select count(1) from sam_upgrade where organize_seq =? and file_ver > ?";
		List params = new ArrayList();
		params.add(organizeSeq);
		params.add(sysVersion);
		return super.queryCount(strSql,params);
	}
	
}
