
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdFaresuitdetail;

public interface IEpdFaresuitdetail {

	public EpdFaresuitdetail insert(EpdFaresuitdetail epdFaresuitdetail,Map<String, Object> config) throws UserBusinessException;

	public void update(EpdFaresuitdetail epdFaresuitdetail,Map<String, Object> config) throws UserBusinessException;

	public void delete(List<EpdFaresuitdetail> epdFaresuitdetails,Map<String, Object> config) throws UserBusinessException;

	public List<EpdFaresuitdetail> queryByPK(String faresuitdetailSeq) throws UserBusinessException;
	
	public List<EpdFaresuitdetail> queryByAll() throws UserBusinessException;

	public List<EpdFaresuitdetail> queryPageByCustom(String faresuitSeq, String routeSeq, String stationSeq,
			String cargradeSeq, int start,int limit) throws UserBusinessException;

	public List<EpdFaresuitdetail> queryAllByCustom(String faresuitSeq, String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException;

	public int queryCountByCustom(String faresuitSeq, String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException;

	public List<EpdFaresuitdetail> queryByRouteSeq(String routeSeq) throws UserBusinessException;

	public void updateBatch(String routeSeq, List<EpdFaresuitdetail> epdFaresuitdetails
			,Map<String, Object> config) throws UserBusinessException;

	public List<EpdFaresuitdetail> queryByRouteSeqAndFaresuitSeq(String routeSeq, String faresuitSeq) throws UserBusinessException;

}
	
	
