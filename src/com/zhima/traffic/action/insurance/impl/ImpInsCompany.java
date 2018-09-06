package com.zhima.traffic.action.insurance.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.insurance.INetInsCompany;
import com.service.traffic.business.insurance.impl.ImpNetInsCompany;
import com.zhima.basic.CommFinal;
import com.zhima.basic.InsFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.insurance.IInsCompany;
import com.zhima.traffic.model.InsCompany;
import com.zhima.util.MapUtil;

/**
 * ImpLogin概要说明：用户登录接口
 * @author lcy
 */
public class ImpInsCompany implements IInsCompany {

	@SuppressWarnings("rawtypes")
	@Override
	public InsCompany insert(InsCompany insCompany,Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsCompany iNetInsCompany = new ImpNetInsCompany();
				return iNetInsCompany.insert(insCompany, config);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insCompany", insCompany);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsCompany_Insert,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (InsCompany)JsonUtil.convertObject(
			    			mapResult.get("insCompany").toString(), InsCompany.class);
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void update(InsCompany insCompany,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsCompany iNetInsCompany = new ImpNetInsCompany();
				iNetInsCompany.update(insCompany, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insCompany", insCompany);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsCompany_Update,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return;
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void delete(List<InsCompany> companies,Map<String, Object> config) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsCompany iNetInsCompany = new ImpNetInsCompany();
				iNetInsCompany.delete(companies, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("insCompanyies", companies);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsCompany_Delete,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return;
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<InsCompany> queryPageByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType, String industry,int start,int limit) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsCompany iNetInsCompany = new ImpNetInsCompany();
				return iNetInsCompany.queryPageByCustom(organizeSeq, insuranceCode, insuranceName, companyType,
						industry, start, limit);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("insuranceCode", insuranceCode);
				map.put("insuranceName", insuranceName);
				map.put("companyType", companyType);
				map.put("industry", industry);
				map.put("start", start);
				map.put("limit", limit);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsCompany_QueryPageByCustom,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsCompany>)JsonUtil.convertArray(
			    			mapResult.get("insCompanies").toString(), new InsCompany());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<InsCompany> queryAllByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType, String industry) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsCompany iNetInsCompany = new ImpNetInsCompany();
				return iNetInsCompany.queryAllByCustom(organizeSeq, insuranceCode, insuranceName,
						companyType, industry);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("insuranceCode", insuranceCode);
				map.put("insuranceName", insuranceName);
				map.put("companyType", companyType);
				map.put("industry", industry);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsCompany_QueryAllByCustom,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsCompany>)JsonUtil.convertArray(
			    			mapResult.get("insCompanies").toString(), new InsCompany());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int queryCountByCustom(String organizeSeq, String insuranceCode,String insuranceName,
			String companyType, String industry)throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsCompany iNetInsCompany = new ImpNetInsCompany();
				return iNetInsCompany.queryCountByCustom(organizeSeq, insuranceCode, insuranceName,
						companyType, industry);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				map.put("insuranceCode", insuranceCode);
				map.put("insuranceName", insuranceName);
				map.put("companyType", companyType);
				map.put("industry", industry);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsCompany_QueryCountByCustom,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return Integer.valueOf(mapResult.get("count").toString());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return 0;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<InsCompany> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1:
				INetInsCompany iNetInsCompany = new ImpNetInsCompany();
				return iNetInsCompany.queryByOrganizeSeq(organizeSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("organizeSeq", organizeSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, InsFinal.InsCompany_QueryByOrganizeSeq,
						InsFinal.ServerNamespace,InsFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<InsCompany>)JsonUtil.convertArray(
			    			mapResult.get("insCompanies").toString(), new InsCompany());
			    }else{
			    	throw new UserBusinessException(String.valueOf(mapResult.get("Message")));
			    }
			}
			return null;
		} catch (Exception e) {
			throw new UserBusinessException(e.getMessage());
		}
	}

}