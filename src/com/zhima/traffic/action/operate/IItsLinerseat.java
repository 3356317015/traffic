
package com.zhima.traffic.action.operate;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerseat;

public interface IItsLinerseat {

	public List<ItsLinerseat> queryByLinerSeq(String linerSeq) throws UserBusinessException;
	
	public List<ItsLinerseat> queryPageByLinerSeq(String linerSeq,int start, int limit) throws UserBusinessException;
	
	public int queryCountByLinerSeq(String linerSeq) throws UserBusinessException;
	
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinerseat> itsLinerseats,Map<String, Object> config) throws UserBusinessException;

	
}
	
	
