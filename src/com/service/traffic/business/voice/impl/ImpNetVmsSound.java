package com.service.traffic.business.voice.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.voice.INetVmsSound;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.traffic.dao.VmsSoundDao;
import com.service.traffic.dao.VmsSoundsetDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.VmsSound;
import com.zhima.util.DateUtils;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpNetVmsSound implements INetVmsSound {

	@Override
	public List<VmsSound> inserts(List<VmsSound> vmsSounds,Map<String, Object> config
			) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			
			List<VmsSound> sounds = new ArrayList<VmsSound>();
			if (null != vmsSounds && vmsSounds.size()>0){
				for (int i = 0; i < vmsSounds.size(); i++) {
					vmsSounds.get(i).setUpdateTime(currTime);
					VmsSound newSound = vmsSoundDao.insert(vmsSounds.get(i),config);
					sounds.add(newSound);
				}
			}
			conn.commit();
			return sounds;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(VmsSound vmsSound, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			vmsSound.setUpdateTime(currTime);
			vmsSoundDao.update(vmsSound,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public void delete(List<VmsSound> vmsSounds,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			VmsSoundsetDao vmsSoundsetDao = new VmsSoundsetDao(conn); 
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != vmsSounds && vmsSounds.size()>0){
				for (int i = 0; i < vmsSounds.size(); i++) {
					samLogDetailDao.deleteDataLog(vmsSounds.get(i).getSoundSeq()
							, new VmsSound(),config);
					vmsSoundDao.deleteByPK(vmsSounds.get(i).getSoundSeq());
					vmsSoundsetDao.deleteBySoundSeq(vmsSounds.get(i).getSoundSeq());
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
	public List<VmsSound> queryByPK(String soundSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			List<VmsSound> sounds = vmsSoundDao.queryByPK(soundSeq);
			conn.commit();
			return sounds;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<VmsSound> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			List<VmsSound> sounds = vmsSoundDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return sounds;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<VmsSound> queryPageByCustom(String organizeSeq, String soundType,
			int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			List<VmsSound> notices = vmsSoundDao.queryPageByCustom(organizeSeq,
					soundType, start, limit);
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
	public List<VmsSound> queryAllByCustom(String organizeSeq, String soundType
			) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			List<VmsSound> sounds = vmsSoundDao.queryAllByCustom(organizeSeq, soundType);
			conn.commit();
			return sounds;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String soundType
			) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			int count = vmsSoundDao.queryCountByCustom(organizeSeq,
					soundType);
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
	public List<VmsSound> queryByStatusAndTime(String organizeSeq,
			String soundStatus, String currTime) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsSoundDao vmsSoundDao = new VmsSoundDao(conn);
			List<VmsSound> notices = vmsSoundDao.queryByStatusAndTime(organizeSeq,
					soundStatus, currTime);
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