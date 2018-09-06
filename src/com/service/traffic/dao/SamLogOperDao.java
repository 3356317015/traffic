
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamLogOper;

/**
 * SamLogOperDao概要说明：操作日志类
 * @author lcy
 */
public class SamLogOperDao extends BaseDao{
	public SamLogOperDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午03:29:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samLogOper 添加前的操作日志
	 * @return samLogOper 添加后带主键的操作日志
	 */
	public SamLogOper insert(SamLogOper samLogOper, Map<String, Object> config){
		String pk = super.insert(samLogOper,config);
		samLogOper.setOperLogSeq(pk);
		return samLogOper;
	}
	
	/**
	 * update方法描述：修改操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午03:30:06
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samLogOper 操作日志
	 */
	public void update(SamLogOper samLogOper, Map<String, Object> config){
		super.update(samLogOper,config);
	}
	
	/**
	 * updateBypk方法描述：根据主键更新操作日志信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午07:49:14
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param config 操作日志主键
	 * @param status 状态
	 * @param operDesc 操作描述
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateBypk(String operLogSeq, String status, String operDesc) {
		String sql = "update sam_log_oper set status = ?, oper_desc= ? where oper_log_seq=?";
		List params = new ArrayList();
		params.add(status);
		params.add(operDesc);
		params.add(operLogSeq);
		super.executeSql(sql, params);
	}
	
	/**
	 * deleteByPK方法描述：根据主键删除操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午03:30:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param config 操作日志主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String operLogSeq){
		String sql = "delete from sam_log_oper where oper_log_seq=?";
		List params = new ArrayList();
		params.add(operLogSeq);
		super.executeSql(sql, params);
	}
	
	/**
	 * queryPageByCustom方法描述：自定义条件分页查询操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午11:45:48
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleCode 模块代码
	 * @param operType 操作类型
	 * @param status 操作状态
	 * @param operUser 操作人
	 * @param startDate 操作开始日期
	 * @param endDate 操作结束日期
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<SamLogOper> 操作日志
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamLogOper> queryPageByCustom(String organizeSeq, String moduleCode, String operType, String status, 
			String operUser, String startDate, String endDate, int start, int limit){
		StringBuffer strSql = new StringBuffer("select * from sam_log_oper where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != moduleCode && !"".equals(moduleCode)){
			strSql.append(" and module_code = ?");
			params.add(moduleCode);
		}
		if (null != operType && !"".equals(operType)){
			strSql.append(" and right_name = ?");
			params.add(operType);
		}
		if (null != status && !"".equals(status)){
			strSql.append(" and status = ?");
			params.add(status);
		}
		if (null != operUser && !"".equals(operUser)){
			strSql.append(" and oper_code = ?");
			params.add(operUser);
		}
		if (null != startDate && !"".equals(startDate)){
			strSql.append(" and oper_time >= ?");
			params.add(startDate);
		}
		if (null != endDate && !"".equals(endDate)){
			strSql.append(" and oper_time <= ?");
			params.add(endDate + " 23:59:59");
		}
		strSql.append(" order by oper_time");
		List<SamLogOper> samLogOpers = (List<SamLogOper>) super.queryPage(strSql.toString(),params,new SamLogOper(),start,limit);
		return samLogOpers;
	}
	
	/**
	 * queryAllByCustom方法描述：自定义条件查询操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午11:47:22
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleCode 模块代码
	 * @param operType 操作类型
	 * @param status 操作状态
	 * @param operUser 操作人
	 * @param startDate 操作开始日期
	 * @param endDate 操作结束日期
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamLogOper> queryAllByCustom(String organizeSeq, String moduleCode, String operType, String status, 
			String operUser, String startDate, String endDate){
		StringBuffer strSql = new StringBuffer("select * from sam_log_oper where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != moduleCode && !"".equals(moduleCode)){
			strSql.append(" and module_code = ?");
			params.add(moduleCode);
		}
		if (null != operType && !"".equals(operType)){
			strSql.append(" and right_name = ?");
			params.add(operType);
		}
		if (null != status && !"".equals(status)){
			strSql.append(" and status = ?");
			params.add(status);
		}
		if (null != operUser && !"".equals(operUser)){
			strSql.append(" and oper_code = ?");
			params.add(operUser);
		}
		if (null != startDate && !"".equals(startDate)){
			strSql.append(" and oper_time >= ?");
			params.add(startDate);
		}
		if (null != endDate && !"".equals(endDate)){
			strSql.append(" and oper_time <= ?");
			params.add(endDate + " 23:59:59");
		}
		strSql.append(" order by oper_time");
		List<SamLogOper> samLogOpers = (List<SamLogOper>) super.queryAll(strSql.toString(),params,new SamLogOper());
		return samLogOpers;
	}
	
	/**
	 * queryCountByCustom方法描述：自定义条件查询操作日志记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午11:47:59
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleCode 模块代码
	 * @param operType 操作类型
	 * @param status 操作状态
	 * @param operUser 操作人
	 * @param startDate 操作开始日期
	 * @param endDate 操作结束日期
	 * @return int 记录数
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String moduleCode, String operType, String status, 
			String operUser, String startDate, String endDate){
		StringBuffer strSql = new StringBuffer("select count(1) from sam_log_oper where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != moduleCode && !"".equals(moduleCode)){
			strSql.append(" and module_code = ?");
			params.add(moduleCode);
		}
		if (null != operType && !"".equals(operType)){
			strSql.append(" and right_name = ?");
			params.add(operType);
		}
		if (null != status && !"".equals(status)){
			strSql.append(" and status = ?");
			params.add(status);
		}
		if (null != operUser && !"".equals(operUser)){
			strSql.append(" and oper_code = ?");
			params.add(operUser);
		}
		if (null != startDate && !"".equals(startDate)){
			strSql.append(" and oper_time >= ?");
			params.add(startDate);
		}
		if (null != endDate && !"".equals(endDate)){
			strSql.append(" and oper_time <= ?");
			params.add(endDate + " 23:59:59");
		}
		strSql.append(" order by oper_time");
		return super.queryCount(strSql.toString(),params);
	}

}
