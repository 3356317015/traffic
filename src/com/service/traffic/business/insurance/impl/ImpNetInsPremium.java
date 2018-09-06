package com.service.traffic.business.insurance.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.insurance.INetInsPremium;
import com.service.traffic.dao.InsPremiumDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.InsPremium;

/**
 * ImpLogin概要说明：用户登录接口
 * @author lcy
 */
public class ImpNetInsPremium implements INetInsPremium {

	@Override
	public InsPremium insert(InsPremium insPremium,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsPremiumDao premiumDao = new InsPremiumDao(conn);
			List<InsPremium> premiums = premiumDao.queryByValid(insPremium);
			if (null != premiums && premiums.size()>0){
				throw new UserBusinessException("保费费率已存在，不允许重复。");
			}
			InsPremium premium = premiumDao.insert(insPremium,config);
			conn.commit();
			return premium;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(InsPremium insPremium,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsPremiumDao premiumDao = new InsPremiumDao(conn);
			List<InsPremium> premiums = premiumDao.queryByValid(insPremium);
			if (null != premiums && premiums.size()>0){
				throw new UserBusinessException("保费费率已存在，不允许重复。");
			}
			premiumDao.update(insPremium,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<InsPremium> premiums,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsPremiumDao premiumDao = new InsPremiumDao(conn);
			if (null != premiums && premiums.size()>0){
				for (int i = 0; i < premiums.size(); i++) {
					premiumDao.deleteByPK(premiums.get(i).getPremiumSeq());
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
	public List<InsPremium> queryPageByCustom(String organizeSeq, String companySeq
			,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsPremiumDao premiumDao = new InsPremiumDao(conn);
			List<InsPremium> premiums = premiumDao.queryPageByCustom(organizeSeq, companySeq, start, limit);
			conn.commit();
			return premiums;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<InsPremium> queryAllByCustom(String organizeSeq, String companySeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsPremiumDao premiumDao = new InsPremiumDao(conn);
			List<InsPremium> premiums = premiumDao.queryAllByCustom(organizeSeq, companySeq);
			conn.commit();
			return premiums;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String companySeq)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsPremiumDao premiumDao = new InsPremiumDao(conn);
			int count = premiumDao.queryCountByCustom(organizeSeq, companySeq);
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
	public List<InsPremium> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsPremiumDao premiumDao = new InsPremiumDao(conn);
			List<InsPremium> premiums = premiumDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return premiums;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}