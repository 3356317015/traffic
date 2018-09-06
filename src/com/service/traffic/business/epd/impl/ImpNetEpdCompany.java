package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdCompany;
import com.service.traffic.dao.EpdCompanyDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCompany;

/**
 * ImpLogin概要说明：用户登录接口
 * @author lcy
 */
public class ImpNetEpdCompany implements INetEpdCompany {

	@Override
	public EpdCompany insert(EpdCompany epdCompany,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCompanyDao companyDao = new EpdCompanyDao(conn);
			List<EpdCompany> companies = companyDao.queryByValid(epdCompany);
			if (null != companies && companies.size()>0){
				throw new UserBusinessException("公司代码已存在，不允许重复。");
			}
			EpdCompany company = companyDao.insert(epdCompany,config);
			conn.commit();
			return company;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdCompany epdCompany,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCompanyDao companyDao = new EpdCompanyDao(conn);
			List<EpdCompany> companies = companyDao.queryByValid(epdCompany);
			if (null != companies && companies.size()>0){
				throw new UserBusinessException("公司代码已存在，不允许重复。");
			}
			companyDao.update(epdCompany,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdCompany> companies,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCompanyDao companyDao = new EpdCompanyDao(conn);
			if (null != companies && companies.size()>0){
				for (int i = 0; i < companies.size(); i++) {
					companyDao.deleteByPK(companies.get(i).getCompanySeq());
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
	public List<EpdCompany> queryPageByCustom(String organizeSeq, String companyCode,String companyName,
			String companyType, String industry,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCompanyDao companyDao = new EpdCompanyDao(conn);
			List<EpdCompany> companies = companyDao.queryPageByCustom(organizeSeq, companyCode, 
					companyName, companyType, industry, start, limit);
			conn.commit();
			return companies;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdCompany> queryAllByCustom(String organizeSeq, String companyCode,String companyName,
			String companyType, String industry) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCompanyDao companyDao = new EpdCompanyDao(conn);
			List<EpdCompany> companies = companyDao.queryAllByCustom(organizeSeq, companyCode, 
					companyName, companyType, industry);
			conn.commit();
			return companies;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String companyCode,String companyName,String companyType,
			String industry)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCompanyDao companyDao = new EpdCompanyDao(conn);
			int count = companyDao.queryCountByCustom(organizeSeq, companyCode, 
					companyName, companyType, industry);
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
	public List<EpdCompany> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCompanyDao companyDao = new EpdCompanyDao(conn);
			List<EpdCompany> companies = companyDao.queryByAll();
			conn.commit();
			return companies;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdCompany> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCompanyDao companyDao = new EpdCompanyDao(conn);
			List<EpdCompany> companies = companyDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return companies;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}