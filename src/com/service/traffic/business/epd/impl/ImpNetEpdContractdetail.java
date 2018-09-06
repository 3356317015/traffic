package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdContractdetail;
import com.service.traffic.dao.EpdContractdetailDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdContractdetail;

public class ImpNetEpdContractdetail implements INetEpdContractdetail {

	@Override
	public EpdContractdetail insert(EpdContractdetail epdContractdetail,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractdetailDao epdContractdetailDao = new EpdContractdetailDao(conn);
			List<EpdContractdetail> contracts = epdContractdetailDao.queryByValid(epdContractdetail);
			if (null != contracts && contracts.size()>0){
				throw new UserBusinessException("运营合同项目已存在，不允许重复。");
			}
			EpdContractdetail contractdetail = epdContractdetailDao.insert(epdContractdetail,config);
			conn.commit();
			return contractdetail;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdContractdetail epdContractdetail,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractdetailDao epdContractdetailDao = new EpdContractdetailDao(conn);
			List<EpdContractdetail> contracts = epdContractdetailDao.queryByValid(epdContractdetail);
			if (null != contracts && contracts.size()>0){
				throw new UserBusinessException("运营合同项目已存在，不允许重复。");
			}
			epdContractdetailDao.update(epdContractdetail,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdContractdetail> epdContractdetails,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractdetailDao epdContractdetailDao = new EpdContractdetailDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdContractdetails && epdContractdetails.size()>0){
				for (int i = 0; i < epdContractdetails.size(); i++) {
					samLogDetailDao.deleteDataLog(epdContractdetails.get(i).getContractSeq()
							, new EpdContractdetail(),config);
					epdContractdetailDao.deleteByPK(epdContractdetails.get(i).getContractSeq());
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
	public List<EpdContractdetail> queryByPK(String contractdetailSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractdetailDao epdContractdetailDao = new EpdContractdetailDao(conn);
			List<EpdContractdetail> contractdetails = epdContractdetailDao.queryByPK(contractdetailSeq);
			conn.commit();
			return contractdetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdContractdetail> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractdetailDao epdContractdetailDao = new EpdContractdetailDao(conn);
			List<EpdContractdetail> contractdetails = epdContractdetailDao.queryByAll();
			conn.commit();
			return contractdetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdContractdetail> queryByContractSeq(String contractSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractdetailDao epdContractdetailDao = new EpdContractdetailDao(conn);
			List<EpdContractdetail> contractdetails = epdContractdetailDao.queryByContractSeq(contractSeq);
			conn.commit();
			return contractdetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
}