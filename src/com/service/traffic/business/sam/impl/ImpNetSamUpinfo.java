package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.Map;

import com.service.traffic.business.sam.INetSamUpinfo;
import com.service.traffic.dao.SamUpinfoDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamUpinfo;
import com.zhima.util.DateUtils;

public class ImpNetSamUpinfo implements INetSamUpinfo {

	@Override
	public SamUpinfo insert(String fileVer,String remark,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUpinfoDao samUpinfoDao = new SamUpinfoDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			SamUpinfo samUpinfo = new SamUpinfo();
			samUpinfo.setFileVer(fileVer);
			samUpinfo.setUpgradeInfo(remark);
			samUpinfo.setCreateTime(currTime);
			samUpinfo.setUpdateTime(currTime);
			SamUpinfo newSamUpinfo = samUpinfoDao.insert(samUpinfo,config);
			conn.commit();
			return newSamUpinfo;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}
