package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamOrganize;
import com.service.traffic.dao.SamOrganizeDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamOrganize;

/**
 * ImpSamOrganize概要说明：服务网点接口
 * @author lcy
 */
public class ImpNetSamOrganize implements INetSamOrganize {

	@Override
	public SamOrganize insert(SamOrganize samOrganize,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao samOrganizeDao = new SamOrganizeDao(conn);
			List<SamOrganize> organizes = samOrganizeDao.queryByValid(samOrganize);
			if (null != organizes && organizes.size()>0){
				throw new UserBusinessException("机构代码或名称已存在，不允许重复。");
			}
			/*if ("1".equals(samOrganize.getOrganizeType())){
				organizes = samOrganizeDao.queryByLocalhost(samOrganize);
				if (null != organizes && organizes.size()>0){
					throw new UserBusinessException("机构类型[本站]不允许多个，添加不合法。");
				}
			}*/
			SamOrganize organize = samOrganizeDao.insert(samOrganize,config);
			conn.commit();
			return organize;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(SamOrganize samOrganize,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao samOrganizeDao = new SamOrganizeDao(conn);
			List<SamOrganize> organizes = samOrganizeDao.queryByValid(samOrganize);
			if (null != organizes && organizes.size()>0){
				throw new UserBusinessException("机构代码或名称已存在，不允许重复。");
			}
			/*if ("1".equals(samOrganize.getOrganizeType())){
				organizes = samOrganizeDao.queryByLocalhost(samOrganize);
				if (null != organizes && organizes.size()>0){
					throw new UserBusinessException("机构类型[本站]不允许多个，添加不合法。");
				}
			}*/
			samOrganizeDao.update(samOrganize,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<SamOrganize> samOrganizes,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao samOrganizeDao = new SamOrganizeDao(conn);
			if (null != samOrganizes && samOrganizes.size()>0){
				for (int i = 0; i < samOrganizes.size(); i++) {
					samOrganizeDao.deleteByPK(samOrganizes.get(i).getOrganizeSeq());
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
	public List<SamOrganize> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao samOrganizeDao = new SamOrganizeDao(conn);
			List<SamOrganize> samOrganizes = samOrganizeDao.queryByAll();
			conn.commit();
			return samOrganizes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<SamOrganize> queryPageByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao samOrganizeDao = new SamOrganizeDao(conn);
			List<SamOrganize> organizes = samOrganizeDao.queryPageByCustom(organizeCode, organizeName,
					organizeLevel, organizeType, organizeStatus, start, limit);
			conn.commit();
			return organizes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<SamOrganize> queryAllByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao organizeDao = new SamOrganizeDao(conn);
			List<SamOrganize> organizes = organizeDao.queryAllByCustom(organizeCode, organizeName,
					organizeLevel, organizeType, organizeStatus);
			conn.commit();
			return organizes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao organizeDao = new SamOrganizeDao(conn);
			int count = organizeDao.queryCountByCustom(organizeCode, organizeName,
					organizeLevel, organizeType, organizeStatus);
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
	public List<SamOrganize> queryByOrganizeType(String organizeType)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao organizeDao = new SamOrganizeDao(conn);
			List<SamOrganize> organizes = organizeDao.queryByOrganizeType(organizeType);
			conn.commit();
			return organizes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<SamOrganize> queryDealByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao organizeDao = new SamOrganizeDao(conn);
			List<SamOrganize> organizes = organizeDao.queryDealByOrganizeSeq(organizeSeq);
			conn.commit();
			return organizes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}