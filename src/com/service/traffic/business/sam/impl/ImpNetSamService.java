package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamService;
import com.service.traffic.dao.SamServiceDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamService;

/**
 * ImpSamOrganize概要说明：服务网点接口
 * @author lcy
 */
public class ImpNetSamService implements INetSamService {

	@Override
	public SamService insert(SamService samService,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamServiceDao samServiceDao = new SamServiceDao(conn);
			List<SamService> samServices = samServiceDao.queryByValid(samService);
			if (null != samServices && samServices.size()>0){
				throw new UserBusinessException("销售点代码或名称已存在，不允许重复。");
			}
			SamService service = samServiceDao.insert(samService,config);
			conn.commit();
			return service;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(SamService samService,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamServiceDao samServiceDao = new SamServiceDao(conn);
			List<SamService> services = samServiceDao.queryByValid(samService);
			if (null != services && services.size()>0){
				throw new UserBusinessException("销售点代码或名称已存在，不允许重复。");
			}
			samServiceDao.update(samService,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<SamService> samServices,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamServiceDao samServiceDao = new SamServiceDao(conn);
			if (null != samServices && samServices.size()>0){
				for (int i = 0; i < samServices.size(); i++) {
					samServiceDao.deleteByPK(samServices.get(i).getServiceSeq());
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
	public List<SamService> queryPageByCustom(String organizeSeq,
			String serviceCode,String serviceName,String serviceStatus,int start,int limit)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamServiceDao samServiceDao = new SamServiceDao(conn);
			List<SamService> samServices = samServiceDao.queryPageByCustom(organizeSeq, serviceCode,
					serviceName, serviceStatus, start, limit);
			conn.commit();
			return samServices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<SamService> queryAllByCustom(String organizeSeq,
			String serviceCode,String serviceName,String serviceStatus)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamServiceDao samServiceDao = new SamServiceDao(conn);
			List<SamService> samServices = samServiceDao.queryAllByCustom(organizeSeq, serviceCode,
					serviceName, serviceStatus);
			conn.commit();
			return samServices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq,
			String serviceCode,String serviceName,String serviceStatus)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamServiceDao samServiceDao = new SamServiceDao(conn);
			int count = samServiceDao.queryCountByCustom(organizeSeq, serviceCode,
					serviceName, serviceStatus);
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
	public List<SamService> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamServiceDao samServiceDao = new SamServiceDao(conn);
			List<SamService> samServices = samServiceDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return samServices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<SamService> queryByOrganizeAndSaleType(String organizeSeq,
			String saleType) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamServiceDao samServiceDao = new SamServiceDao(conn);
			List<SamService> samServices = samServiceDao.queryByOrganizeAndSaleType(organizeSeq, saleType);
			conn.commit();
			return samServices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}