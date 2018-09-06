package com.service.traffic.business.voice.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.voice.INetVmsTemplate;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.traffic.dao.VmsTemplateDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.VmsTemplate;
import com.zhima.util.DateUtils;

public class ImpNetVmsTemplate implements INetVmsTemplate {

	@Override
	public VmsTemplate insert(VmsTemplate vmsTemplate, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsTemplateDao vmsTemplateDao = new VmsTemplateDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			vmsTemplate.setUpdateTime(currTime);
			VmsTemplate newVmsTemplate = vmsTemplateDao.insert(vmsTemplate,config);
			conn.commit();
			return newVmsTemplate;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public void update(List<VmsTemplate> vmsTemplates,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsTemplateDao vmsTemplateDao = new VmsTemplateDao(conn);
			if (null != vmsTemplates && vmsTemplates.size()>0){
				for (int i = 0; i < vmsTemplates.size(); i++) {
					vmsTemplateDao.update(vmsTemplates.get(i),config);
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
	public void delete(List<VmsTemplate> vmsTemplates,
			Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsTemplateDao vmsTemplateDao = new VmsTemplateDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != vmsTemplates && vmsTemplates.size()>0){
				for (int i = 0; i < vmsTemplates.size(); i++) {
					samLogDetailDao.deleteDataLog(vmsTemplates.get(i).getTemplateSeq()
							, new VmsTemplate(),config);
					vmsTemplateDao.deleteByPK(vmsTemplates.get(i).getTemplateSeq());
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
	public List<VmsTemplate> queryByOrganizeAndType(String organizeSeq, String templateType)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsTemplateDao vmsTemplateDao = new VmsTemplateDao(conn);
			List<VmsTemplate> vmsParameters = vmsTemplateDao.queryByOrganizeAndType(
					organizeSeq,templateType);
			conn.commit();
			return vmsParameters;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}