package com.service.traffic.business.voice.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.voice.INetVmsSoundset;
import com.service.traffic.dao.VmsSoundDao;
import com.service.traffic.dao.VmsSoundsetDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.VmsSound;
import com.zhima.traffic.model.VmsSoundset;
import com.zhima.util.DateUtils;

/**
 * ImpNetVmsSoundset概要说明：语音公告设置
 * @author lcy
 */
public class ImpNetVmsSoundset implements INetVmsSoundset {

	@Override
	public void update(VmsSound vmsSound, List<VmsSoundset> vmsSoundsets,
			Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			VmsSoundsetDao vmsSoundsetDao = new VmsSoundsetDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			vmsSound.setUpdateTime(currTime);
			vmsSoundDao.update(vmsSound, config);
			vmsSoundsetDao.deleteBySoundSeq(vmsSound.getSoundSeq());
			if (null != vmsSoundsets && vmsSoundsets.size()>0){
				for (int i = 0; i < vmsSoundsets.size(); i++) {
					vmsSoundsets.get(i).setSoundSeq(vmsSound.getSoundSeq());
					vmsSoundsets.get(i).setUpdateTime(currTime);
					vmsSoundsetDao.insert(vmsSoundsets.get(i), config);
				}
			}
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<VmsSoundset> queryBySoundSeq(String noticeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundsetDao vmsSoundsetDao = new VmsSoundsetDao(conn);
			List<VmsSoundset> vmsSoundsets = vmsSoundsetDao.queryBySoundSeq(noticeSeq);
			conn.commit();
			return vmsSoundsets;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
}