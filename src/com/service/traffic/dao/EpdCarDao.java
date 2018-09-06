
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdCar;

public class EpdCarDao extends BaseDao{
	public EpdCarDao(Connection conn){
		super(conn);
	}
	
	public EpdCar insert(EpdCar epdCar,Map<String, Object> config){
		String pk = super.insert(epdCar,config);
		epdCar.setCarSeq(pk);
		return epdCar;
	}
	
	public void update(EpdCar epdCar,Map<String, Object> config){
		super.update(epdCar,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String carSeq){
		String strSql = "delete from epd_car where car_seq=?";
		List params = new ArrayList();
		params.add(carSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCar> queryByValid(EpdCar epdCar) {
		StringBuffer strSql = new StringBuffer("select * from epd_car where organize_seq =?" +
				" and car_code = ? and car_number = ? and car_id = ?");
		List params = new ArrayList();
		params.add(epdCar.getOrganizeSeq());
		params.add(epdCar.getCarCode());
		params.add(epdCar.getCarNumber());
		params.add(epdCar.getCarId());
		String carSeq = epdCar.getCarSeq();
		if (null != carSeq && !"".equals(carSeq)){
			strSql.append(" and car_seq <> ?");
			params.add(carSeq);
		}
		List<EpdCar> cars = (List<EpdCar>) super.queryAll(strSql.toString(),
				params,new EpdCar());
		return cars;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCar> queryByPK(String carSeq){
		String strSql = "select * from epd_car where car_seq=?";
		List params = new ArrayList();
		params.add(carSeq);
		List<EpdCar> cars = (List<EpdCar>) super.queryAll(strSql,params,new EpdCar());
		return cars;
	}
	
	@SuppressWarnings("unchecked")
	public List<EpdCar> queryByAll(){
		String strSql = "select * from epd_car";
		List<EpdCar> cars = (List<EpdCar>) super.queryAll(strSql,null,new EpdCar());
		return cars;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByContractSeq(String contractSeq){
		String strSql = "select count(1) from epd_car where contract_seq=?";
		List params = new ArrayList();
		params.add(contractSeq);
		int count =  super.queryCount(strSql,params);
		return count;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCar> queryPageByCustom(String organizeSeq, String routeSeq,String carCode,String carNumber,
			String companyName,String status,int start,int limit){
		StringBuffer strSql = new StringBuffer("select epd_car.*," +
					"epd_route.route_name," +
					"epd_cargrade.cargrade_name,"+
					"epd_company.company_code," +
					"epd_company.company_code," +
					"epd_company.company_name," +
					"epd_contract.contract_name," +
					"a.driver_name" +
				" from epd_car" +
					" left join epd_route on epd_car.route_seq = epd_route.route_seq" +
					" left join epd_cargrade on epd_car.cargrade_seq = epd_cargrade.cargrade_seq" +
					" left join epd_company on epd_car.company_seq = epd_company.company_seq" +
					" left join epd_contract on epd_car.contract_seq = epd_contract.contract_seq" +
					" LEFT JOIN (" +
						" SELECT epd_cardriver.car_seq AS car_seq,GROUP_CONCAT(epd_driver.driver_name) AS driver_name" +
						" FROM epd_cardriver,epd_driver" +
						" WHERE epd_cardriver.driver_seq = epd_driver.driver_seq GROUP BY epd_cardriver.car_seq) a" +
						" ON a.car_seq=epd_car.car_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_car.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_car.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != carCode && !"".equals(carCode)){
			strSql.append(" and epd_car.car_code like ?");
			params.add("%" + carCode + "%");
		}
		if (null != carNumber && !"".equals(carNumber)){
			strSql.append(" and epd_car.car_number like ?");
			params.add("%" + carNumber + "%");
		}
		if (null != companyName && !"".equals(companyName)){
			strSql.append(" and epd_car.company_seq = ?");
			params.add(companyName);
		}
		
		if (null != status && !"".equals(status)){
			strSql.append(" and epd_car.status = ?");
			params.add(status);
		}
		strSql.append(" order by epd_car.car_code");
		List<EpdCar> cars = (List<EpdCar>) super.queryPage(strSql.toString(),params,new EpdCar(),start,limit);
		return cars;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCar> queryAllByCustom(String organizeSeq, String routeSeq,String carCode,String carNumber,
			String companyName,String status){
		StringBuffer strSql = new StringBuffer("select epd_car.*," +
					"epd_route.route_name," +
					"epd_cargrade.cargrade_name,"+
					"epd_company.company_code," +
					"epd_company.company_code," +
					"epd_company.company_name," +
					"epd_contract.contract_name," +
					"a.driver_name" +
				" from epd_car" +
					" left join epd_route on epd_car.route_seq = epd_route.route_seq" +
					" left join epd_cargrade on epd_car.cargrade_seq = epd_cargrade.cargrade_seq" +
					" left join epd_company on epd_car.company_seq = epd_company.company_seq" +
					" left join epd_contract on epd_car.contract_seq = epd_contract.contract_seq" +
					" LEFT JOIN (" +
						" SELECT epd_cardriver.car_seq AS car_seq,GROUP_CONCAT(epd_driver.driver_name) AS driver_name" +
						" FROM epd_cardriver,epd_driver" +
						" WHERE epd_cardriver.driver_seq = epd_driver.driver_seq GROUP BY epd_cardriver.car_seq) a" +
						" ON a.car_seq=epd_car.car_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_car.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_car.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != carNumber && !"".equals(carNumber)){
			strSql.append(" and epd_car.car_number like ?");
			params.add("%" + carNumber + "%");
		}
		if (null != companyName && !"".equals(companyName)){
			strSql.append(" and epd_car.company_seq = ?");
			params.add(companyName);
		}
		
		if (null != status && !"".equals(status)){
			strSql.append(" and epd_car.status = ?");
			params.add(status);
		}
		strSql.append(" order by epd_car.car_code");
		List<EpdCar> cars = (List<EpdCar>) super.queryAll(strSql.toString(),params,new EpdCar());
		return cars;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCar> queryByPlanId(String organizeSeq, String planId){
		StringBuffer strSql = new StringBuffer("select epd_car.*," +
					"epd_route.route_name," +
					"epd_cargrade.cargrade_name,"+
					"epd_company.company_code," +
					"epd_company.company_code," +
					"epd_company.company_name," +
					"epd_contract.contract_name," +
					"a.driver_name" +
				" from epd_plancar,epd_car" +
					" left join epd_route on epd_car.route_seq = epd_route.route_seq" +
					" left join epd_cargrade on epd_car.cargrade_seq = epd_cargrade.cargrade_seq" +
					" left join epd_company on epd_car.company_seq = epd_company.company_seq" +
					" left join epd_contract on epd_car.contract_seq = epd_contract.contract_seq" +
					" LEFT JOIN (" +
						" SELECT epd_cardriver.car_seq AS car_seq,GROUP_CONCAT(epd_driver.driver_name) AS driver_name" +
						" FROM epd_cardriver,epd_driver" +
						" WHERE epd_cardriver.driver_seq = epd_driver.driver_seq GROUP BY epd_cardriver.car_seq) a" +
						" ON a.car_seq=epd_car.car_seq" +
				" where epd_plancar.car_number=epd_car.car_number");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_car.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != planId && !"".equals(planId)){
			strSql.append(" and epd_plancar.plan_id = ?");
			params.add(planId);
		}
		strSql.append(" order by epd_car.car_code");
		List<EpdCar> cars = (List<EpdCar>) super.queryAll(strSql.toString(),params,new EpdCar());
		return cars;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String routeSeq,String carCode,String carNumber,
			String companyName,String status){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_car where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and epd_car.organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_car.route_seq = ?");
			params.add(routeSeq);
		}
		if (null != carNumber && !"".equals(carNumber)){
			strSql.append(" and epd_car.car_number like ?");
			params.add("%" + carNumber + "%");
		}
		if (null != companyName && !"".equals(companyName)){
			strSql.append(" and epd_car.company_seq = ?");
			params.add(companyName);
		}
		
		if (null != status && !"".equals(status)){
			strSql.append(" and epd_car.status = ?");
			params.add(status);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCar> queryByRouteSeq(String routeSeq) {
		StringBuffer strSql = new StringBuffer("select epd_car.*," +
				"epd_route.route_name," +
				"epd_cargrade.cargrade_name,"+
				"epd_company.company_code," +
				"epd_company.company_code," +
				"epd_company.company_name," +
				"epd_contract.contract_name," +
				"a.driver_name" +
			" from epd_car" +
				" left join epd_route on epd_car.route_seq = epd_route.route_seq" +
				" left join epd_cargrade on epd_car.cargrade_seq = epd_cargrade.cargrade_seq" +
				" left join epd_company on epd_car.company_seq = epd_company.company_seq" +
				" left join epd_contract on epd_car.contract_seq = epd_contract.contract_seq" +
				" LEFT JOIN (" +
					" SELECT epd_cardriver.car_seq AS car_seq,GROUP_CONCAT(epd_driver.driver_name) AS driver_name" +
					" FROM epd_cardriver,epd_driver" +
					" WHERE epd_cardriver.driver_seq = epd_driver.driver_seq GROUP BY epd_cardriver.car_seq) a" +
					" ON a.car_seq=epd_car.car_seq" +
			" where 1=1");
		List params = new ArrayList();
		if (null != routeSeq && !"".equals(routeSeq)){
			strSql.append(" and epd_car.route_seq = ?");
			params.add(routeSeq);
		}
		strSql.append(" order by epd_car.car_code");
		List<EpdCar> cars = (List<EpdCar>) super.queryAll(strSql.toString(),params,new EpdCar());
		return cars;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdCar> queryByOrganizeSeq(String organizeSeq) {
		String strSql = "select * from epd_car where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<EpdCar> cars = (List<EpdCar>) super.queryAll(strSql,params,new EpdCar());
		return cars;
	}

}
