
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdContractdetail;

public class EpdContractdetailDao extends BaseDao{
	public EpdContractdetailDao(Connection conn){
		super(conn);
	}
	
	public EpdContractdetail insert(EpdContractdetail epdContractdetail, Map<String, Object> config){
		String pk = super.insert(epdContractdetail,config);
		epdContractdetail.setContractdetailSeq(pk);
		return epdContractdetail;
	}
	
	public void update(EpdContractdetail epdContractdetail, Map<String, Object> config){
		super.update(epdContractdetail,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String contractdetailSeq){
		String strSql = "delete from epd_contractdetail where contractdetail_seq=?";
		List params = new ArrayList();
		params.add(contractdetailSeq);
		super.executeSql(strSql, params);
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteByContractSeq(String contractSeq) {
		String strSql = "delete from epd_contractdetail where contract_seq=?";
		List params = new ArrayList();
		params.add(contractSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdContractdetail> queryByValid(EpdContractdetail epdContractdetail) {
		StringBuffer strSql = new StringBuffer("select * from epd_contractdetail where contract_seq = ? and item_code=? and item_belong=?");
		List params = new ArrayList();
		params.add(epdContractdetail.getContractSeq());
		params.add(epdContractdetail.getItemCode());
		params.add(epdContractdetail.getItemBelong());
		String contractdetailSeq = epdContractdetail.getContractdetailSeq();
		if (null != contractdetailSeq && !"".equals(contractdetailSeq)){
			strSql.append(" and contractdetail_seq <> ?");
			params.add(contractdetailSeq);
		}
		List<EpdContractdetail> contractdetails = (List<EpdContractdetail>) super.queryAll(strSql.toString(),
				params,new EpdContractdetail());
		return contractdetails;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdContractdetail> queryByPK(String contractdetailSeq){
		String strSql = "select * from epd_contractdetail where contractdetail_seq=?";
		List params = new ArrayList();
		params.add(contractdetailSeq);
		List<EpdContractdetail> contractdetails = (List<EpdContractdetail>) super.queryAll(strSql.toString(),
				params,new EpdContractdetail());
		return contractdetails;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdContractdetail> queryByAll(){
		String strSql = "select * from epd_contractdetail";
		List<EpdContractdetail> contractdetails = (List<EpdContractdetail>) super.queryAll(strSql.toString(),
				null,new EpdContractdetail());
		return contractdetails;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdContractdetail> queryByContractSeq(String contractSeq) {
		String strSql = "select * from epd_contractdetail where contract_seq=?";
		List params = new ArrayList();
		params.add(contractSeq);
		List<EpdContractdetail> contractdetails = (List<EpdContractdetail>) super.queryAll(strSql.toString(),
				params,new EpdContractdetail());
		return contractdetails;
	}


}
