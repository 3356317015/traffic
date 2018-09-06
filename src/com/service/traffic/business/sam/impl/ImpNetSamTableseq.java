package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamTableseq;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.traffic.dao.SamTableseqDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamTableseq;
import com.zhima.util.DateUtils;

/**
 * ImpSamTableseq概要说明：主键设置接口
 * @author lcy
 */
public class ImpNetSamTableseq implements INetSamTableseq {

	@Override
	public SamTableseq insert(SamTableseq samTableseq,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			String sql = "insert into sam_tableseq (organize_seq,table_name,seq_rules,seq_len,seq_value,create_time,update_time) values(?,?,?,?,?,?,?)";
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, samTableseq.getOrganizeSeq());
			pstmt.setString(2, samTableseq.getTableName());
			pstmt.setString(3, samTableseq.getSeqRules());
			pstmt.setInt(4, samTableseq.getSeqLen());
		    pstmt.setString(5, samTableseq.getSeqValue());
		    pstmt.setString(6, currTime);
		    pstmt.setString(7, currTime);
		    pstmt.executeUpdate();
			conn.commit();
			return samTableseq;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(SamTableseq samTableseq,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			String sql = "update sam_tableseq set seq_rules=?,seq_len=?,seq_value=?,create_time=?,update_time=? where organize_seq = ? and table_name=?";
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, samTableseq.getSeqRules());
			pstmt.setInt(2, samTableseq.getSeqLen());
		    pstmt.setString(3, samTableseq.getSeqValue());
		    pstmt.setString(4, currTime);
		    pstmt.setString(5, currTime);
			pstmt.setString(6, samTableseq.getOrganizeSeq());
			pstmt.setString(7, samTableseq.getTableName());
		    pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<SamTableseq> samTableseqs,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamTableseqDao samTableseqDao = new SamTableseqDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != samTableseqs && samTableseqs.size()>0){
				for (int i = 0; i < samTableseqs.size(); i++) {
					samLogDetailDao.deleteDataLog(samTableseqs.get(i).getTableName()
							, new SamTableseq(),config);
					samTableseqDao.deleteByPK(samTableseqs.get(i).getTableName());
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
	public SamTableseq queryByPk(String organizeSeq, String tableName)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamTableseqDao samTableseqDao = new SamTableseqDao(conn);
			SamTableseq samTableseq = samTableseqDao.queryByPk(organizeSeq, tableName);
			conn.commit();
			return samTableseq;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<SamTableseq> queryPageByCustom(String orgainzeSeq, int start, int limit)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamTableseqDao samTableseqDao = new SamTableseqDao(conn);
			List<SamTableseq> samTableseqs = samTableseqDao.queryPageByCustom(orgainzeSeq, start,limit);
			conn.commit();
			return samTableseqs;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String orgainzeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamTableseqDao samTableseqDao = new SamTableseqDao(conn);
			int count = samTableseqDao.queryCountByCustom(orgainzeSeq);
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
	public List<SamTableseq> queryAllByCustom(String orgainzeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamTableseqDao samTableseqDao = new SamTableseqDao(conn);
			List<SamTableseq> samTableseqs = samTableseqDao.queryAllByCustom(orgainzeSeq);
			conn.commit();
			return samTableseqs;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}