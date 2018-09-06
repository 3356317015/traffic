
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamUpinfo;

/**
 * SamUpblobDao概要说明：升级数据操作类
 * @author lcy
 */
public class SamUpinfoDao extends BaseDao{
	public SamUpinfoDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加升级数据文件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 下午01:34:40
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUpblob 升级数据文件
	 * @return SamUpblob 升级数据文件
	 */
	public SamUpinfo insert(SamUpinfo samUpinfo, Map<String, Object> config){
		String pk = super.insert(samUpinfo,config);
		samUpinfo.setUpinfoSeq(pk);
		return samUpinfo;
	}
	
	/**
	 * update方法描述：修改升级数据文件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 下午01:35:06
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUpblob 升级数据文件
	 */
	public void update(SamUpinfo samUpinfo, Map<String, Object> config){
		super.update(samUpinfo,config);
	}
	
	/**
	 * deleteByUpgradeSeq方法描述：根据升级序号删除升级文件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-30 下午01:33:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param upgradeSeq 升级序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPk(String upinfoSeq){
		String strSql = "delete from sam_upinfo where upinfo_seq=?";
		List params = new ArrayList();
		params.add(upinfoSeq);
		super.executeSql(strSql, params);
	}

}
