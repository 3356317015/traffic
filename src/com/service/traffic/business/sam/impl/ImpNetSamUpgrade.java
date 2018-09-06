package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamUpgrade;
import com.service.traffic.dao.SamUpblobDao;
import com.service.traffic.dao.SamUpgradeDao;
import com.service.traffic.dao.SamUpinfoDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamUpblob;
import com.zhima.frame.model.SamUpgrade;
import com.zhima.util.DateUtils;

public class ImpNetSamUpgrade implements INetSamUpgrade {

	@SuppressWarnings("unused")
	@Override
	public SamUpgrade insert(SamUpgrade samUpgrade,List<SamUpblob> samUpblobs,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		SamUpgrade upgrade;
		try{
			PoolHandler.pool.beginConn(conn);
			SamUpgradeDao samUpgradeDao = new SamUpgradeDao(conn);
			SamUpblobDao samUpblobDao = new SamUpblobDao(conn);
			SamUpinfoDao samUpinfoDao = new SamUpinfoDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			samUpgrade.setCreateTime(currTime);
			samUpgrade.setUpdateTime(currTime);
			upgrade = samUpgradeDao.insert(samUpgrade,config);
			if (null != samUpblobs && samUpblobs.size()>0){
				for (int i = 0; i < samUpblobs.size(); i++) {
					samUpblobs.get(i).setUpgradeSeq(upgrade.getUpgradeSeq());
					samUpblobs.get(i).setCreateTime(currTime);
					samUpblobs.get(i).setUpdateTime(currTime);
					samUpblobDao.insert(samUpblobs.get(i),config);
					
				}
			}
			conn.commit();
			return upgrade;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	
	@Override
	public void delete(List<SamUpgrade> samUpgrades,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUpgradeDao samUpgradeDao = new SamUpgradeDao(conn);
			SamUpblobDao samUpblobDao = new SamUpblobDao(conn);
			if (null != samUpgrades && samUpgrades.size()>0){
				for (int i = 0; i < samUpgrades.size(); i++) {
					samUpgradeDao.deleteByPK(samUpgrades.get(i).getUpgradeSeq());
					samUpblobDao.deleteByUpgradeSeq(samUpgrades.get(i).getUpgradeSeq());
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
	public List<SamUpgrade> queryPageByCustom(String organizeSeq, int start, int limit)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUpgradeDao samUpgradeDao = new SamUpgradeDao(conn);
			List<SamUpgrade> samUpgrades = samUpgradeDao.queryPageByCustom(organizeSeq, start, limit);
			conn.commit();
			return samUpgrades;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public int queryCountByCustom(String organizeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUpgradeDao samUpgradeDao = new SamUpgradeDao(conn);
			int count = samUpgradeDao.queryCountByCustom(organizeSeq);
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
	public SamUpgrade queryMaxVer(String organizeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUpgradeDao samUpgradeDao = new SamUpgradeDao(conn);
			SamUpgrade upgrade = samUpgradeDao.queryMaxVer(organizeSeq);
			conn.commit();
			return upgrade;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}


	@Override
	public int queryUpgradeByVer(String organizeSeq,String sysVersion)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUpgradeDao samUpgradeDao = new SamUpgradeDao(conn);
			int count = samUpgradeDao.queryUpgradeByVer(organizeSeq, sysVersion);
			conn.commit();
			return count;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
}
