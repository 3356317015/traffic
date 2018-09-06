package com.service.traffic.business.insurance.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.insurance.INetInsCompany;
import com.service.traffic.dao.InsCompanyDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.InsCompany;

/**
 * ImpLogin概要说明：用户登录接口
 * @author lcy
 */
public class ImpNetInsCompany implements INetInsCompany {

	@Override
	public InsCompany insert(InsCompany insCompany,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsCompanyDao companyDao = new InsCompanyDao(conn);
			List<InsCompany> companies = companyDao.queryByValid(insCompany);
			if (null != companies && companies.size()>0){
				throw new UserBusinessException("公司代码已存在，不允许重复。");
			}
			InsCompany company = companyDao.insert(insCompany,config);
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
	public void update(InsCompany insCompany,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsCompanyDao companyDao = new InsCompanyDao(conn);
			List<InsCompany> companies = companyDao.queryByValid(insCompany);
			if (null != companies && companies.size()>0){
				throw new UserBusinessException("公司代码已存在，不允许重复。");
			}
			companyDao.update(insCompany,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<InsCompany> companies,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsCompanyDao companyDao = new InsCompanyDao(conn);
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
	public List<InsCompany> queryPageByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType, String industry,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsCompanyDao companyDao = new InsCompanyDao(conn);
			List<InsCompany> companies = companyDao.queryPageByCustom(organizeSeq, insuranceCode, 
					insuranceName, companyType, industry, start, limit);
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
	public List<InsCompany> queryAllByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType, String industry) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsCompanyDao companyDao = new InsCompanyDao(conn);
			List<InsCompany> companies = companyDao.queryAllByCustom(organizeSeq, insuranceCode, 
					insuranceName, companyType, industry);
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
	public int queryCountByCustom(String organizeSeq, String insuranceCode,String insuranceName,String companyType,
			String industry)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsCompanyDao companyDao = new InsCompanyDao(conn);
			int count = companyDao.queryCountByCustom(organizeSeq, insuranceCode, 
					insuranceName, companyType, industry);
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
	public List<InsCompany> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsCompanyDao companyDao = new InsCompanyDao(conn);
			List<InsCompany> companies = companyDao.queryByOrganizeSeq(organizeSeq);
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