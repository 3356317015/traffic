
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCar;
import com.zhima.traffic.model.EpdCardriver;
import com.zhima.traffic.model.EpdCarinfo;


public interface IEpdCar {
	
	public EpdCar insert(EpdCar epdCar, List<EpdCarinfo> carinfos, List<EpdCardriver> cardrivers
			,Map<String, Object> config) throws UserBusinessException;
	
	public void update(EpdCar epdCar, List<EpdCarinfo> carinfos, List<EpdCardriver> cardrivers, List<EpdCardriver> delCardrivers
			,Map<String, Object> config) throws UserBusinessException;
	
	public void delete(List<EpdCar> epdCars,Map<String, Object> config) throws UserBusinessException;
	
	public List<EpdCar> queryPageByCustom(String organizeSeq, String routeSeq,String carCode,String carNumber,
			String companyName,String status,int start,int limit) throws UserBusinessException;
	
	public List<EpdCar> queryAllByCustom(String organizeSeq, String routeSeq,String carCode,String carNumber,
			String companyName,String status) throws UserBusinessException;
	
	public int queryCountByCustom(String organizeSeq, String routeSeq,String carCode,String carNumber,
			String companyName,String status) throws UserBusinessException;

	public List<EpdCar> queryByAll() throws UserBusinessException;
	
	public List<EpdCar> queryByPlanId(String organizeSeq, String planId) throws UserBusinessException;

	public List<EpdCar> queryByRouteSeq(String routeSeq) throws UserBusinessException;

	public List<EpdCar> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}
	
	
