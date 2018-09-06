
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamUserColumn;
import com.service.traffic.business.sam.impl.ImpNetSamUserColumn;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamUserColumn;
import com.zhima.widget.grid.GridColumn;

/**
 * ISamUserColumn概要说明：用户自定义表格接口
 * @author lcy
 */
public class SamUserColumnAction {
	INetSamUserColumn iNetSamUserColumn = new ImpNetSamUserColumn();
	@SuppressWarnings("rawtypes")
	public String queryByUserColumn(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        
	        //3.调用业务
			List<SamUserColumn> samUserColumns = iNetSamUserColumn.queryByUserColumn(
					String.valueOf(mapParameter.get("className")), 
					String.valueOf(mapParameter.get("gridName")),
					String.valueOf(mapParameter.get("userSeq")));
			JSONArray jsonarray = JSONArray.fromObject(samUserColumns);
			result.put("samUserColumns", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryByGridColumn(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        
			List<GridColumn> columns = (List<GridColumn>)JsonUtil.convertArray(
					mapParameter.get("columns").toString(), new GridColumn());

			//3.调用业务
			List<GridColumn> gridColumns = iNetSamUserColumn.queryByGridColumn(
					columns, 
					String.valueOf(mapParameter.get("className")), 
					String.valueOf(mapParameter.get("gridName")),
					String.valueOf(mapParameter.get("userSeq")));
			JSONArray jsonarray = JSONArray.fromObject(gridColumns);
			result.put("gridColumns", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String queryByValidColumn(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        
			List<GridColumn> columns = (List<GridColumn>)JsonUtil.convertArray(
					mapParameter.get("columns").toString(), new GridColumn());
	        //3.调用业务
			List<SamUserColumn> samUserColumns = iNetSamUserColumn.queryByValidColumn(
					columns, 
					String.valueOf(mapParameter.get("className")), 
					String.valueOf(mapParameter.get("gridName")),
					String.valueOf(mapParameter.get("userSeq")));
			JSONArray jsonarray = JSONArray.fromObject(samUserColumns);
			result.put("samUserColumns", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String updateUserColumn(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        
			List<SamUserColumn> samUserColumns = (List<SamUserColumn>)JsonUtil.convertArray(
					mapParameter.get("samUserColumns").toString(), new SamUserColumn());
			String className = String.valueOf(mapParameter.get("className"));
			String gridName = String.valueOf(mapParameter.get("gridName"));
			String userSeq = String.valueOf(mapParameter.get("userSeq"));
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetSamUserColumn.updateUserColumn(
					samUserColumns,
					className,
					gridName,
					userSeq,
					mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteByUserGrid(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        
			String className = String.valueOf(mapParameter.get("className"));
			String gridName = String.valueOf(mapParameter.get("gridName"));
			String userSeq = String.valueOf(mapParameter.get("userSeq"));
			
			String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
			
	        //3.调用业务
			iNetSamUserColumn.deleteByUserGrid(className,gridName,userSeq,mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

}
	
	
