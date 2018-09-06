
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdContract;

public class EpdContractDao extends BaseDao{
	public EpdContractDao(Connection conn){
		super(conn);
	}
	
	public EpdContract insert(EpdContract epdContract, Map<String, Object> config){
		String pk = super.insert(epdContract,config);
		epdContract.setContractSeq(pk);
		return epdContract;
	}
	
	public void update(EpdContract epdContract, Map<String, Object> config){
		super.update(epdContract,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String contractSeq){
		String strSql = "delete from epd_contract where contract_seq=?";
		List params = new ArrayList();
		params.add(contractSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdContract> queryByValid(EpdContract epdContract) {
		StringBuffer strSql = new StringBuffer("select * from epd_contract where organize_seq =?" +
				" and contract_code = ?");
		List params = new ArrayList();
		params.add(epdContract.getOrganizeSeq());
		params.add(epdContract.getContractCode());
		String contractSeq = epdContract.getContractSeq();
		if (null != contractSeq && !"".equals(contractSeq)){
			strSql.append(" and contract_seq <> ?");
			params.add(contractSeq);
		}
		List<EpdContract> contracts = (List<EpdContract>) super.queryAll(strSql.toString(),
				params,new EpdContract());
		return contracts;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdContract> queryByPK(String contractSeq){
		String strSql = "select * from epd_contract where contract_seq=?";
		List params = new ArrayList();
		params.add(contractSeq);
		List<EpdContract> contracts = (List<EpdContract>) super.queryAll(strSql,params,new EpdContract());
		return contracts;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdContract> queryByAll(){
		String strSql = "select * from epd_contract";
		List<EpdContract> contracts = (List<EpdContract>) super.queryAll(strSql,null,new EpdContract());
		return contracts;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdContract> queryByOrganizeSeq(String organizeSeq) {
		String strSql = "select * from epd_contract where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<EpdContract> contracts = (List<EpdContract>) super.queryAll(strSql,params,new EpdContract());
		return contracts;
	}

}
