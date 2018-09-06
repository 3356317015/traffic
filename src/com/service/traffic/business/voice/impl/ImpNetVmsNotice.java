package com.service.traffic.business.voice.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.voice.INetVmsNotice;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.traffic.dao.VmsNoticeDao;
import com.service.traffic.dao.VmsNoticesetDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.util.DateUtils;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpNetVmsNotice implements INetVmsNotice {

	@Override
	public VmsNotice insert(VmsNotice vmsNotice, Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			vmsNotice.setUpdateTime(currTime);
			VmsNotice newNotice = vmsNoticeDao.insert(vmsNotice,config);
			conn.commit();
			return newNotice;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(VmsNotice vmsNotice, Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			vmsNotice.setUpdateTime(currTime);
			vmsNoticeDao.update(vmsNotice,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<VmsNotice> vmsNotices,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			VmsNoticesetDao vmsNoticesetDao = new VmsNoticesetDao(conn); 
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != vmsNotices && vmsNotices.size()>0){
				for (int i = 0; i < vmsNotices.size(); i++) {
					samLogDetailDao.deleteDataLog(vmsNotices.get(i).getNoticeSeq()
							, new VmsNotice(),config);
					vmsNoticeDao.deleteByPK(vmsNotices.get(i).getNoticeSeq());
					vmsNoticesetDao.deleteByNoticeSeq(vmsNotices.get(i).getNoticeSeq());
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
	public List<VmsNotice> queryByPK(String noticeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			List<VmsNotice> notices = vmsNoticeDao.queryByPK(noticeSeq);
			conn.commit();
			return notices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	

	@Override
	public List<VmsNotice> queryPageByOrganizeSeq(String organizeSeq,
			int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			List<VmsNotice> notices = vmsNoticeDao.queryPageByOrganizeSeq(organizeSeq, start, limit);
			conn.commit();
			return notices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<VmsNotice> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			List<VmsNotice> notices = vmsNoticeDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return notices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public int queryCountByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			int count = vmsNoticeDao.queryCountByOrganizeSeq(organizeSeq);
			conn.commit();
			return count;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<VmsNotice> queryPageByCustom(String organizeSeq, String noticeName,
			String noticeStatus,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			List<VmsNotice> notices = vmsNoticeDao.queryPageByCustom(organizeSeq,
					noticeName, noticeStatus, start, limit);
			conn.commit();
			return notices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<VmsNotice> queryAllByCustom(String organizeSeq, String noticeName,
			String noticeStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			List<VmsNotice> notices = vmsNoticeDao.queryAllByCustom(organizeSeq,
					noticeName, noticeStatus);
			conn.commit();
			return notices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String noticeName,
			String noticeStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			int count = vmsNoticeDao.queryCountByCustom(organizeSeq,
					noticeName, noticeStatus);
			conn.commit();
			return count;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<VmsNotice> queryByStatusAndTime(String organizeSeq,
			String noticeStatus, String currTime) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsNoticeDao vmsNoticeDao = new VmsNoticeDao(conn);
			List<VmsNotice> notices = vmsNoticeDao.queryByStatusAndTime(organizeSeq,
					noticeStatus, currTime);
			conn.commit();
			return notices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}