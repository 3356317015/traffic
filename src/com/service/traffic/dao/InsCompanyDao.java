
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.InsCompany;

/**
 * EpdCompanyDao概要说明：公司信息数据库操作类
 * @author lcy
 */
public class InsCompanyDao extends BaseDao{
	public InsCompanyDao(Connection conn){
		super(conn);
	}
	
	public InsCompany insert(InsCompany insCompany, Map<String, Object> config){
		String pk = super.insert(insCompany,config);
		insCompany.setCompanySeq(pk);
		return insCompany;
	}
	
	public void update(InsCompany insCompany, Map<String, Object> config){
		super.update(insCompany,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String companySeq){
		String strSql = "delete from ins_company where company_seq=?";
		List params = new ArrayList();
		params.add(companySeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsCompany> queryPageByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType, String industry, int start, int limit){
		StringBuffer strSql = new StringBuffer("select * from ins_company where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != insuranceCode && !"".equals(insuranceCode)){
			strSql.append(" and insurance_code like ?");
			params.add("%" + insuranceCode + "%");
		}
		if (null != insuranceName && !"".equals(insuranceName)){
			strSql.append(" and insurance_name like ?");
			params.add("%" + insuranceName + "%");
		}
		if (null != companyType && !"".equals(companyType)){
			strSql.append(" and company_type like ?");
			params.add("%" + companyType + "%");
		}
		if (null != industry && !"".equals(industry)){
			strSql.append(" and industry like ?");
			params.add("%" + industry + "%");
		}
		strSql.append(" order by insurance_code");
		List<InsCompany> companies = (List<InsCompany>) super.queryPage(strSql.toString(),params,new InsCompany(),start,limit);
		return companies;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsCompany> queryAllByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType, String industry){
		StringBuffer strSql = new StringBuffer("select * from ins_company where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != insuranceCode && !"".equals(insuranceCode)){
			strSql.append(" and insurance_code like ?");
			params.add("%" + insuranceCode + "%");
		}
		if (null != insuranceName && !"".equals(insuranceName)){
			strSql.append(" and insurance_name like ?");
			params.add("%" + insuranceName + "%");
		}
		if (null != companyType && !"".equals(companyType)){
			strSql.append(" and company_type like ?");
			params.add("%" + companyType + "%");
		}
		if (null != industry && !"".equals(industry)){
			strSql.append(" and industry like ?");
			params.add("%" + industry + "%");
		}
		strSql.append(" order by insurance_code");
		List<InsCompany> companies = (List<InsCompany>) super.queryAll(strSql.toString(),params,new InsCompany());
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
	public int queryCountByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType, String industry){
		StringBuffer strSql = new StringBuffer("select count(1) from ins_company where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != insuranceCode && !"".equals(insuranceCode)){
			strSql.append(" and insurance_code like ?");
			params.add("%" + insuranceCode + "%");
		}
		if (null != insuranceName && !"".equals(insuranceName)){
			strSql.append(" and insurance_name like ?");
			params.add("%" + insuranceName + "%");
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
	public List<InsCompany> queryByOrganizeSeq(String organizeSeq) {
		StringBuffer strSql = new StringBuffer("select * from ins_company where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by insurance_code");
		List<InsCompany> companies = (List<InsCompany>) super.queryAll(strSql.toString(),params,new InsCompany());
		return companies;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<InsCompany> queryByValid(InsCompany insCompany) {
		StringBuffer strSql = new StringBuffer("select * from ins_company where organize_seq =?" +
				" and insurance_code = ?");
		List params = new ArrayList();
		params.add(insCompany.getOrganizeSeq());
		params.add(insCompany.getInsuranceCode());
		String companySeq = insCompany.getCompanySeq();
		if (null != companySeq && !"".equals(companySeq)){
			strSql.append(" and company_seq <> ?");
			params.add(companySeq);
		}
		List<InsCompany> companies = (List<InsCompany>) super.queryAll(strSql.toString(),
				params,new InsCompany());
		return companies;
	}
}
