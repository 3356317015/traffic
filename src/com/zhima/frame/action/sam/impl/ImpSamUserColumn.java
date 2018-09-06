package com.zhima.frame.action.sam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.traffic.business.sam.INetSamUserColumn;
import com.service.traffic.business.sam.impl.ImpNetSamUserColumn;
import com.zhima.basic.CommFinal;
import com.zhima.basic.SamFinal;
import com.zhima.basic.ServiceClient;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamUserColumn;
import com.zhima.frame.model.SamUserColumn;
import com.zhima.util.MapUtil;
import com.zhima.widget.grid.GridColumn;

public class ImpSamUserColumn implements ISamUserColumn {
	
	@SuppressWarnings("rawtypes")
	@Override
	public void updateUserColumn(List<SamUserColumn> userColumns, String className, String gridName,
			String userSeq, Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamUserColumn iNetSamUserColumn = new ImpNetSamUserColumn();
				iNetSamUserColumn.updateUserColumn(userColumns, className, gridName, userSeq, config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("samUserColumns", userColumns);
				map.put("className", className);
				map.put("gridName", gridName);
				map.put("userSeq", userSeq);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamUserColumn_UpdateUserColumn,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
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
	public void deleteByUserGrid(String className, String gridName, String userSeq, Map<String, Object> config)
			throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamUserColumn iNetSamUserColumn = new ImpNetSamUserColumn();
				iNetSamUserColumn.deleteByUserGrid(className,gridName, userSeq,config);
				return;
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("className", className);
				map.put("gridName", gridName);
				map.put("userSeq", userSeq);
				map.put("config", config);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamUserColumn_DeleteByUserGrid,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<SamUserColumn> queryByUserColumn(String className,
			String gridName,String userSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamUserColumn iNetSamUserColumn = new ImpNetSamUserColumn();
				return iNetSamUserColumn.queryByUserColumn(className, gridName, userSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("className", className);
				map.put("gridName", gridName);
				map.put("userSeq", userSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamUserColumn_QueryByUserColumn,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
					List<SamUserColumn> samUserColumns = (List<SamUserColumn>)JsonUtil.convertArray(
							mapResult.get("samUserColumns").toString(), new SamUserColumn());
					return samUserColumns;
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
	public List<GridColumn> queryByGridColumn(List<GridColumn> columns,
			String className, String gridName, String userSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamUserColumn iNetSamUserColumn = new ImpNetSamUserColumn();
				return iNetSamUserColumn.queryByGridColumn(columns, className, gridName, userSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("columns", columns);
				map.put("className", className);
				map.put("gridName", gridName);
				map.put("userSeq", userSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamUserColumn_QueryByGridColumn,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
			    	return (List<GridColumn>)JsonUtil.convertArray(
			    			mapResult.get("gridColumns").toString(), new GridColumn());
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
	public List<SamUserColumn> queryByValidColumn(List<GridColumn> columns,
			String className, String gridName, String userSeq) throws UserBusinessException {
		try{
			switch(CommFinal.CONNECT_MODE) { 
			case 1: 
				INetSamUserColumn iNetSamUserColumn = new ImpNetSamUserColumn();
				return iNetSamUserColumn.queryByValidColumn(columns, className, gridName, userSeq);
			case 2: 
				//1.参数对象为JSONObject
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("columns", columns);
				map.put("className", className);
				map.put("gridName", gridName);
				map.put("userSeq", userSeq);
				String strParameter = MapUtil.packageParameter(map);
				//2.调用WebService接口
				String strResult = ServiceClient.RequestService(strParameter, SamFinal.SamUserColumn_QueryByValidColumn,
						SamFinal.ServerNamespace,SamFinal.ServerAddres);
				JSONObject jsonObject = JSONObject.fromObject(strResult);
			    Map mapResult = (Map)jsonObject;
			    if ("0".equals(mapResult.get("ResultCode"))){
				    	return (List<SamUserColumn>)JsonUtil.convertArray(
				    			mapResult.get("samUserColumns").toString(), new SamUserColumn());
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
