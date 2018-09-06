
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamTableseq;

/**
 * SamTableseqDao概要说明：数据库表主键序号操作类
 * @author lcy
 */
public class SamTableseqDao extends BaseDao{
	public SamTableseqDao(Connection conn){
		super(conn);
	}
	
	/**
	 * insert方法描述：添加主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:24:31
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samTableseq　主键设置
	 * @return SamTableseq 主键设置
	 * @throws UserBusinessException
	 */
	public SamTableseq insert(SamTableseq samTableseq, Map<String, Object> config){
		Map<String, Object> newConfig = new HashMap<String, Object>();
		config.put("operLogSeq", null);
		config.put("organizeSeq", config.get("organizeSeq").toString());
		config.put("userCode", config.get("userCode").toString());
		super.insert(samTableseq, newConfig);
		return samTableseq;
	}
	
	/**
	 * update方法描述：修改主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:25:07
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samTableseq　主键设置
	 * @throws UserBusinessException
	 */
	public void update(SamTableseq samTableseq, Map<String, Object> config){
		super.update(samTableseq,config);
	}

	/**
	 * delete方法描述：删除主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:25:41
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samTableseqs 主键设置
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String tableName){
		String strSql = "delete from sam_tableseq where table_name=?";
		List params = new ArrayList();
		params.add(tableName);
		super.executeSql(strSql, params);
	}
	
	/**
	 * queryByTableName方法描述：根所表名查询主键序号信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:06:04
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param tableName 数据库表名
	 * @return SamTableseq 主键序号
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SamTableseq queryByPk(String organizeSeq, String tableName){
		String strSql = "select * from sam_tableseq where organize_seq=? and table_name=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		params.add(tableName);
		List<SamTableseq> samTablePks = (List<SamTableseq>) super.queryAll(strSql,params,new SamTableseq());
		if (null != samTablePks && samTablePks.size()>0){
			return samTablePks.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * updateByTableName方法描述：更新数据库表当前主键值
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:06:55
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param pkValue 当前主键值
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateByTableName (String pkValue){
		String strSql = "update sam_tableseq set seq_value= ? where table_name=?";
		List params = new ArrayList();
		params.add(pkValue);
		super.executeSql(strSql, params);
	}

	/**
	 * queryPageByAll方法描述：分页查询主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:26:24
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param start 分页开始行号
	 * @param limit 分页结束行号
	 * @return List<SamTableseq> 主键设置
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamTableseq> queryPageByCustom(String organizeSeq, int start, int limit) {
		StringBuffer strSql = new StringBuffer("select * from sam_tableseq where 1=1");
		List params = new ArrayList();
		if (null !=  organizeSeq && organizeSeq.length()>0){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by table_name");
		return (List<SamTableseq>) super.queryPage(strSql.toString(), params, new SamTableseq(), start, limit);
	}

	/**
	 * queryCountByAll方法描述：查询主键设置记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:27:09
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 记录数
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select count(1) from sam_tableseq where 1=1");
		List params = new ArrayList();
		if (null !=  organizeSeq && organizeSeq.length()>0){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		return super.queryCount(strSql.toString(), params);
	}

	/**
	 * queryByAll方法描述：查询所有主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:27:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamTableseq> 主键设置
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamTableseq> queryAllByCustom(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from sam_tableseq where 1=1");
		List params = new ArrayList();
		if (null !=  organizeSeq && organizeSeq.length()>0){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by table_name");
		return (List<SamTableseq>) super.queryAll(strSql.toString(), params, new SamTableseq());
	}

}
