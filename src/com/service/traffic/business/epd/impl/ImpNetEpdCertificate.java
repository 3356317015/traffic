package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdCertificate;
import com.service.traffic.dao.EpdCarinfoDao;
import com.service.traffic.dao.EpdCertificateDao;
import com.service.traffic.dao.EpdDriverinfoDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCertificate;

public class ImpNetEpdCertificate implements INetEpdCertificate {

	@Override
	public EpdCertificate insert(EpdCertificate epdCertificate,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCertificateDao epdCertificateDao = new EpdCertificateDao(conn);
			List<EpdCertificate> certificates = epdCertificateDao.queryByValid(epdCertificate);
			if (null != certificates && certificates.size()>0){
				throw new UserBusinessException("项质项目已存在，不允许重复。");
			}
			EpdCertificate certificate = epdCertificateDao.insert(epdCertificate,config);
			conn.commit();
			return certificate;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdCertificate epdCertificate,String oldCertype, String oldCername
			,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCertificateDao epdCertificateDao = new EpdCertificateDao(conn);
			EpdCarinfoDao epdCarinfoDao = new EpdCarinfoDao(conn);
			EpdDriverinfoDao epdDriverinfoDao = new EpdDriverinfoDao(conn);
			List<EpdCertificate> certificates = epdCertificateDao.queryByValid(epdCertificate);
			if (null != certificates && certificates.size()>0){
				throw new UserBusinessException("项质项目已存在，不允许重复。");
			}
			if (oldCertype.equals(epdCertificate.getCerType())){
				if("1".equals(oldCertype)){
					epdCarinfoDao.deleteByCardName(oldCername);
				}else if ("2".equals(oldCertype)){
					epdDriverinfoDao.deleteByCardName(oldCername);
				}
			}
			if ("1".equals(epdCertificate.getCerType())){
				epdCarinfoDao.updateBatchByCardName(epdCertificate.getCerName(),oldCername);
			}else if ("2".equals(epdCertificate.getCerType())){
				epdDriverinfoDao.updateBatchByCardName(epdCertificate.getCerName(),oldCername);
			}
			epdCertificateDao.update(epdCertificate,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdCertificate> epdCertificates,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCertificateDao epdCertificateDao = new EpdCertificateDao(conn);
			EpdCarinfoDao epdCarinfoDao = new EpdCarinfoDao(conn);
			EpdDriverinfoDao epdDriverinfoDao = new EpdDriverinfoDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdCertificates && epdCertificates.size()>0){
				for (int i = 0; i < epdCertificates.size(); i++) {
					samLogDetailDao.deleteDataLog(epdCertificates.get(i).getCertificateSeq()
							, new EpdCertificate(),config);
					if ("1".equals(epdCertificates.get(i).getCerType())){
						epdCarinfoDao.deleteByCardName(epdCertificates.get(i).getCerName());
					}else if ("2".equals(epdCertificates.get(i).getCerType())){
						epdDriverinfoDao.deleteByCardName(epdCertificates.get(i).getCerName());
					}
					epdCertificateDao.deleteByPK(epdCertificates.get(i).getCertificateSeq());
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
	public List<EpdCertificate> queryByPK(String certificateSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCertificateDao epdCertificateDao = new EpdCertificateDao(conn);
			List<EpdCertificate> certificates = epdCertificateDao.queryByPK(certificateSeq);
			conn.commit();
			return certificates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdCertificate> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCertificateDao epdCertificateDao = new EpdCertificateDao(conn);
			List<EpdCertificate> certificates = epdCertificateDao.queryByAll();
			conn.commit();
			return certificates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdCertificate> queryPageByCustom(String organizeSeq, String cerType, String cerName, String cerStatus,
			int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCertificateDao epdCertificateDao = new EpdCertificateDao(conn);
			List<EpdCertificate> certificates = epdCertificateDao.queryPageByCustom(organizeSeq,
					cerType, cerName, cerStatus, start, limit);
			conn.commit();
			return certificates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdCertificate> queryAllByCustom(String organizeSeq, String cerType, String cerName,
			String cerStatus)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCertificateDao epdCertificateDao = new EpdCertificateDao(conn);
			List<EpdCertificate> certificates = epdCertificateDao.queryAllByCustom(organizeSeq,
					cerType, cerName, cerStatus);
			conn.commit();
			return certificates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String cerType, String cerName,
			String cerStatus)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCertificateDao epdCertificateDao = new EpdCertificateDao(conn);
			int count = epdCertificateDao.queryCountByCustom(organizeSeq, cerType, 
					cerName,cerStatus);
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