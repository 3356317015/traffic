
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdCompany;

/**
 * EpdCompanyDao概要说明：公司信息数据库操作类
 * @author lcy
 */
public class EpdCompanyDao extends BaseDao{
	public EpdCompanyDao(Connection conn){
		super(conn);
	}
	
	/**
	 * insert方法描述：添加公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:55:08
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdCompany 公司
	 * @return epdCompany 公司
	 */
	public EpdCompany insert(EpdCompany epdCompany, Map<String, Object> config){
		String pk = super.insert(epdCompany,config);
		epdCompany.setCompanySeq(pk);
		return epdCompany;
	}
	
	/**
	 * update方法描述：修改公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdCompany 公司
	 */
	public void update(EpdCompany epdCompany, Map<String, Object> config){
		super.update(epdCompany,config);
	}

	/**
	 * deleteByPK方法描述：根据主键删除公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param companySeq 公司主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String companySeq){
		String strSql = "delete from epd_company where company_seq=?";
		List params = new ArrayList();
		params.add(companySeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * queryByAll方法描述：查询所有公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:57:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdCompany> 公司
	 */
	@SuppressWarnings("unchecked")
	public List<EpdCompany> queryByAll(){
		String strSql = "select * from epd_company";
		List<EpdCompany> companies = (List<EpdCompany>) super.queryAll(strSql,null,new EpdCompany());
		return companies;
	}
	
	/**
	 * queryPageByCustom方法描述：自定义分页查询公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 下午05:08:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param companyCode 公司代码
	 * @param companyName 公司名称
	 * @param companyType 公司性质
	 * @param industry 行业类型
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<EpdCompany> 公司
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCompany> queryPageByCustom(String organizeSeq, String companyCode,String companyName,
			String companyType, String industry, int start, int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_company where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companyCode && !"".equals(companyCode)){
			strSql.append(" and company_code like ?");
			params.add("%" + companyCode + "%");
		}
		if (null != companyName && !"".equals(companyName)){
			strSql.append(" and company_name like ?");
			params.add("%" + companyName + "%");
		}
		if (null != companyType && !"".equals(companyType)){
			strSql.append(" and company_type like ?");
			params.add("%" + companyType + "%");
		}
		if (null != industry && !"".equals(industry)){
			strSql.append(" and industry like ?");
			params.add("%" + industry + "%");
		}
		strSql.append(" order by company_code");
		List<EpdCompany> companies = (List<EpdCompany>) super.queryPage(strSql.toString(),params,new EpdCompany(),start,limit);
		return companies;
	}
	
	/**
	 * queryAllByCustom方法描述：自定义查询公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 下午05:10:46
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param companyCode 公司代码
	 * @param companyName 公司名称
	 * @param companyType 公司性质
	 * @param industry 行业类型
	 * @return List<EpdCompany> 公司
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCompany> queryAllByCustom(String organizeSeq, String companyCode,String companyName,
			String companyType, String industry){
		StringBuffer strSql = new StringBuffer("select * from epd_company where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companyCode && !"".equals(companyCode)){
			strSql.append(" and company_code like ?");
			params.add("%" + companyCode + "%");
		}
		if (null != companyName && !"".equals(companyName)){
			strSql.append(" and company_name like ?");
			params.add("%" + companyName + "%");
		}
		if (null != companyType && !"".equals(companyType)){
			strSql.append(" and company_type like ?");
			params.add("%" + companyType + "%");
		}
		if (null != industry && !"".equals(industry)){
			strSql.append(" and industry like ?");
			params.add("%" + industry + "%");
		}
		strSql.append(" order by company_code");
		List<EpdCompany> companies = (List<EpdCompany>) super.queryAll(strSql.toString(),params,new EpdCompany());
		return companies;
	}
	
	/**
	 * queryCountByCustom方法描述：自定义查询公司记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 下午05:11:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param companyCode 公司代码
	 * @param companyName 公司名称
	 * @param companyType 公司性质
	 * @param industry 行业类型
	 * @return int 记录数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String companyCode,String companyName,
			String companyType, String industry){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_company where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != companyCode && !"".equals(companyCode)){
			strSql.append(" and company_code like ?");
			params.add("%" + companyCode + "%");
		}
		if (null != companyName && !"".equals(companyName)){
			strSql.append(" and company_name like ?");
			params.add("%" + companyName + "%");
		}
		if (null != companyType && !"".equals(companyType)){
			strSql.append(" and company_type like ?");
			params.add("%" + companyType + "%");
		}
		if (null != industry && !"".equals(industry)){
			strSql.append(" and industry like ?");
			params.add("%" + industry + "%");
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EpdCompany> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from epd_company where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by company_code");
		List<EpdCompany> companies = (List<EpdCompany>) super.queryAll(strSql.toString(),params,new EpdCompany());
		return companies;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCompany> queryByValid(EpdCompany epdCompany) {
		StringBuffer strSql = new StringBuffer("select * from epd_company where organize_seq =?" +
				" and company_code = ?");
		List params = new ArrayList();
		params.add(epdCompany.getOrganizeSeq());
		params.add(epdCompany.getCompanyCode());
		String companySeq = epdCompany.getCompanySeq();
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and company_seq <> ?");
			params.add(companySeq);
		}
		List<EpdCompany> companies = (List<EpdCompany>) super.queryAll(strSql.toString(),
				params,new EpdCompany());
		return companies;
	}
}
