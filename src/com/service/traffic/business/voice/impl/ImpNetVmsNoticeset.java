package com.service.traffic.business.voice.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.voice.INetVmsNoticeset;
import com.service.traffic.dao.VmsNoticeDao;
import com.service.traffic.dao.VmsNoticesetDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.traffic.model.VmsNoticeset;
import com.zhima.util.DateUtils;

/**
 * ImpNetVmsNoticeset概要说明：文字语音播放设置
 * @author lcy
 */
public class ImpNetVmsNoticeset implements INetVmsNoticeset {

	@Override
	public void update(VmsNotice vmsNotice, List<VmsNoticeset> vmsNoticesets,
			Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			VmsNoticesetDao vmsNoticesetDao = new VmsNoticesetDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			vmsNotice.setUpdateTime(currTime);
			vmsNoticeDao.update(vmsNotice, config);
			vmsNoticesetDao.deleteByNoticeSeq(vmsNotice.getNoticeSeq());
			if (null != vmsNoticesets && vmsNoticesets.size()>0){
				for (int i = 0; i < vmsNoticesets.size(); i++) {
					vmsNoticesets.get(i).setNoticeSeq(vmsNotice.getNoticeSeq());
					vmsNoticesets.get(i).setUpdateTime(currTime);
					vmsNoticesetDao.insert(vmsNoticesets.get(i), config);
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
	public List<VmsNoticeset> queryByNoticeSeq(String noticeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticesetDao vmsNoticesetDao = new VmsNoticesetDao(conn);
			List<VmsNoticeset> vmsNoticesets = vmsNoticesetDao.queryByNoticeSeq(noticeSeq);
			conn.commit();
			return vmsNoticesets;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
}