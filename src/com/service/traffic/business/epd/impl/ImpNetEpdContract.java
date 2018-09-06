package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdContract;
import com.service.traffic.dao.EpdCarDao;
import com.service.traffic.dao.EpdContractDao;
import com.service.traffic.dao.EpdContractdetailDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdContract;
import com.zhima.traffic.model.EpdContractdetail;
import com.zhima.traffic.model.EpdReturnrate;

public class ImpNetEpdContract implements INetEpdContract {

	@Override
	public EpdContract insert(EpdContract epdContract,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractDao epdContractDao = new EpdContractDao(conn);
			List<EpdContract> contracts = epdContractDao.queryByValid(epdContract);
			if (null != contracts && contracts.size()>0){
				throw new UserBusinessException("运营合同代码已存在，请使用其他代码。");
			}
			EpdContract contract = epdContractDao.insert(epdContract,config);
			conn.commit();
			return contract;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdContract epdContract,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractDao epdContractDao = new EpdContractDao(conn);
			List<EpdContract> contracts = epdContractDao.queryByValid(epdContract);
			if (null != contracts && contracts.size()>0){
				throw new UserBusinessException("运营合同代码已存在，请使用其他代码。");
			}
			epdContractDao.update(epdContract,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdContract> epdContracts,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractDao epdContractDao = new EpdContractDao(conn);
			EpdContractdetailDao epdContractdetailDao = new EpdContractdetailDao(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdContracts && epdContracts.size()>0){
				for (int i = 0; i < epdContracts.size(); i++) {
					int count = epdCarDao.queryCountByContractSeq(epdContracts.get(i).getContractSeq());
					if (count>0){
						throw new UserBusinessException("合同" + epdContracts.get(i).getContractCode() + "有" + count +"辆车应用，不允许删除。");
					}
					
					List<EpdContractdetail> epdContractdetails = epdContractdetailDao.queryByContractSeq(epdContracts.get(i).getContractSeq());
					if (null != epdContractdetails && epdContractdetails.size()>0){
						for (int j = 0; j < epdContractdetails.size(); j++) {
							samLogDetailDao.deleteDataLog(epdContractdetails.get(i).getContractdetailSeq(), new EpdContractdetail(),config);
						}
					}
					samLogDetailDao.deleteDataLog(epdContracts.get(i).getContractSeq(), new EpdReturnrate(),config);
					epdContractdetailDao.deleteByContractSeq(epdContracts.get(i).getContractSeq());
					epdContractDao.deleteByPK(epdContracts.get(i).getContractSeq());
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
	public List<EpdContract> queryByPK(String contractSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractDao epdContractDao = new EpdContractDao(conn);
			List<EpdContract> contracts = epdContractDao.queryByPK(contractSeq);
			conn.commit();
			return contracts;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdContract> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractDao epdContractDao = new EpdContractDao(conn);
			List<EpdContract> contracts = epdContractDao.queryByAll();
			conn.commit();
			return contracts;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdContract> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdContractDao epdContractDao = new EpdContractDao(conn);
			List<EpdContract> contracts = epdContractDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return contracts;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
}