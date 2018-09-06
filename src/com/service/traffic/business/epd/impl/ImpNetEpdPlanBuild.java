package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdPlanBuild;
import com.service.traffic.dao.EpdFareDao;
import com.service.traffic.dao.EpdFaresuitDao;
import com.service.traffic.dao.EpdFaresuitDetailDao;
import com.service.traffic.dao.EpdPlanDao;
import com.service.traffic.dao.EpdPlancheckDao;
import com.service.traffic.dao.EpdPlandealDao;
import com.service.traffic.dao.EpdPlanseatDao;
import com.service.traffic.dao.EpdPlanserviceDao;
import com.service.traffic.dao.EpdPlanstationDao;
import com.service.traffic.dao.ItsLinerDao;
import com.service.traffic.dao.ItsLinercheckDao;
import com.service.traffic.dao.ItsLinerdealDao;
import com.service.traffic.dao.ItsLinerfareDao;
import com.service.traffic.dao.ItsLinerseatDao;
import com.service.traffic.dao.ItsLinerserviceDao;
import com.service.traffic.dao.ItsLinerstationDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdFare;
import com.zhima.traffic.model.EpdFaresuit;
import com.zhima.traffic.model.EpdFaresuitdetail;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdPlancheck;
import com.zhima.traffic.model.EpdPlandeal;
import com.zhima.traffic.model.EpdPlanseat;
import com.zhima.traffic.model.EpdPlanservice;
import com.zhima.traffic.model.EpdPlanstation;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;
import com.zhima.traffic.model.ItsLinerdeal;
import com.zhima.traffic.model.ItsLinerfare;
import com.zhima.traffic.model.ItsLinerseat;
import com.zhima.traffic.model.ItsLinerservice;
import com.zhima.traffic.model.ItsLinerstation;
import com.zhima.util.DateUtils;

public class ImpNetEpdPlanBuild implements INetEpdPlanBuild {

	@Override
	public List<EpdPlan> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			List<EpdPlan> epdPlans = epdPlanDao.queryByAll();
			conn.commit();
			return epdPlans;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<ItsLiner> planBuild(EpdPlan epdPlan,String buildFare,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			List<ItsLiner> liners = buildLiner(conn, epdPlan, buildFare, config);
			conn.commit();
			return liners;
		}catch(Exception e){
			e.printStackTrace();
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException("计划班次:"+epdPlan.getPlanId()+"排班失败!",e.getCause());
		}finally{
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	private List<ItsLiner> buildLiner(Connection conn, EpdPlan epdPlan
			,String buildFare,Map<String, Object> config) throws Exception{
		List<ItsLiner> liners = new ArrayList<ItsLiner>();
		if ("撤班".equals(epdPlan.getPlanStatus())){
			return liners;
		}
		String currTime = DateUtils.getNow("HH:mm");
		int n = 0;
		if (currTime.compareTo(epdPlan.getPlanTime())>=0){
			n = 1;
		}
		String linerDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
		for (int i = n; i < epdPlan.getPreDays(); i++) {
			linerDate = DateUtils.nDaysAfterStringDate(i);
			ItsLiner liner = null;
			if("固定班次".equals(epdPlan.getPlanType())
					|| "流水班次".equals(epdPlan.getPlanType())
					|| "包车".equals(epdPlan.getPlanType())
					|| "其他班次".equals(epdPlan.getPlanType())){
				liner = buildLiner(conn,epdPlan, buildFare,linerDate, config);
			}else if("单日".equals(epdPlan.getPlanType())){
				String day = linerDate.substring(8);
				int li_day = Integer.valueOf(day);
				int li_mod = li_day % 2;
				//判断是否单日
				if(li_mod == 1){
					liner = buildLiner(conn,epdPlan, buildFare,linerDate, config);
				}
			}else if("双日".equals(epdPlan.getPlanType())){
				String day = linerDate.substring(8);
				int li_day = Integer.valueOf(day);
				int li_mod = li_day % 2;
				//判断是否双日
				if(li_mod == 0){
					liners.add(buildLiner(conn,epdPlan, buildFare,linerDate, config));
					liner = buildLiner(conn,epdPlan, buildFare,linerDate, config);
				}
			}else if("隔日".equals(epdPlan.getPlanType())){
				int days = DateUtils.nDaysBetweenTwoDate(epdPlan.getStartDate(), linerDate);
				int li_mod = days %(Integer.valueOf(epdPlan.getPlanOption())+1);
				if(days >=0 && li_mod==0){
					liner = buildLiner(conn,epdPlan, buildFare,linerDate, config);
				}
			}
			if (null != liner){
				liners.add(liner);
			}
		}
		return liners;
	}

	private ItsLiner buildLiner(Connection conn, EpdPlan epdPlan, String buildFare,
			 String linerDate, Map<String, Object> config) throws Exception{
		ItsLinerDao linerDao = new ItsLinerDao(conn);
		//判断是否存在日班次
		int count = linerDao.queryCountByDateAndId(linerDate, epdPlan.getPlanId());
		if (count <= 0){
			if("保班".equals(epdPlan.getPlanStatus())){
				if(linerDate.compareTo(epdPlan.getStartDate())>=0 && linerDate.compareTo(epdPlan.getEndDate())<=0){
					return buildLiner(conn, epdPlan, buildFare, linerDate,epdPlan.getPlanStatus(),config);
				}else{
					return buildLiner(conn, epdPlan, buildFare, linerDate,"正常",config);
				}
			}else if("临时".equals(epdPlan.getPlanStatus())){
				if(linerDate.compareTo(epdPlan.getStartDate())>=0 && linerDate.compareTo(epdPlan.getEndDate())<=0){
					return buildLiner(conn, epdPlan, buildFare, linerDate,"正常",config);
				}else{
					return null;
				}
			}else{
				//验证开始日期和结束日期
				if(DateUtils.nDaysBetweenTwoDate(linerDate, epdPlan.getStartDate())<=0
						|| DateUtils.nDaysBetweenTwoDate(linerDate, epdPlan.getEndDate())>=0){
					return buildLiner(conn, epdPlan, buildFare, linerDate,epdPlan.getPlanStatus(),config);
				}
			}
		}
		return null;
	}
	
	private ItsLiner buildLiner(Connection conn,EpdPlan epdPlan,String buildFare, String linerDate,String linerState
			,Map<String, Object> config){
		ItsLinerDao linerDao = new ItsLinerDao(conn);
		//EpdPlanseatDao planseatDao = new EpdPlanseatDao(conn);
		ItsLiner liner = new ItsLiner();
		liner.setOrganizeSeq(epdPlan.getOrganizeSeq());
		liner.setLinerDate(linerDate);
		liner.setLinerId(epdPlan.getPlanId());
		liner.setFirstTime(epdPlan.getFirstTime());
		liner.setLinerTime(epdPlan.getPlanTime());
		liner.setLinerType(epdPlan.getPlanType());
		liner.setRouteSeq(epdPlan.getRouteSeq());
		liner.setRouteName(epdPlan.getRouteName());
		liner.setCargradeSeq(epdPlan.getCargradeSeq());
		liner.setCargradeName(epdPlan.getCargradeName());
		liner.setIfPrintseat(epdPlan.getIfPrintseat());
		liner.setSeatNum(epdPlan.getSeatNum());
		liner.setFreeNum(epdPlan.getFreeNum());
		liner.setHalfNum(epdPlan.getHalfNum());
		//liner.setStopNum(planseatDao.queryCountByPlanIdAndState(epdPlan.getPlanSeq(),
		//		epdPlan.getPlanId(), 0));
		//liner.setReverseNum(planseatDao.queryCountByPlanIdAndState(epdPlan.getPlanSeq(),
		//		epdPlan.getPlanId(), 3));
		liner.setSaleNum(0);
		liner.setDealNum(0);
		liner.setLockNum(0);
		liner.setReverseNum(epdPlan.getReverseNum());
		liner.setStopNum(epdPlan.getStopNum());
		liner.setReturnNum(0);
		liner.setInvalidNum(0);
		liner.setCheckNum(0);
		liner.setCheckgateSeq(epdPlan.getCheckgateSeq());
		liner.setCheckName(epdPlan.getCheckName());
		liner.setParkingSeq(epdPlan.getParkingSeq());
		liner.setLinerStatus(epdPlan.getPlanStatus());
		liner.setReportStatus(0);
		liner.setCarNumber("");
		liner.setDriverName("");
		liner.setReportTime("");
		liner.setPrintbillStatus(0);
		liner.setPrintbillTime("");
		liner.setOutstationStatus(0);
		liner.setOutstationTime("");
		liner.setEstimateTime("");
		liner.setIfDeal(epdPlan.getIfDeal());
		liner.setIfMain(epdPlan.getIfMain());
		liner.setIfNetsale(epdPlan.getIfNetsale());
		liner.setCreateUser(String.valueOf(config.get("userCode")));
		liner.setCreateTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
		liner.setUpdateTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
		liner = linerDao.insert(liner,config);
		//插入席位信息
		this.buildSeat(conn, epdPlan, liner, config);
		//插入检票口信息
		this.buildCheck(conn, epdPlan, liner, config);
		//插入站点信息
		this.buildStation(conn, epdPlan, liner, config);
		//插入配载信息
		this.buildDeal(conn, epdPlan, liner, config);
		//插入乘车点信息
		this.buildService(conn, epdPlan, liner, config);
		//插入票价
		if ("1".equals(buildFare)){
			this.buildFare(conn, epdPlan, liner, config);
		}
		return liner;
	}
	
	private void buildSeat(Connection conn,EpdPlan epdPlan,ItsLiner liner
			,Map<String, Object> config){
		//计划席位DAO
		EpdPlanseatDao planseatDao = new EpdPlanseatDao(conn);
		//日班次席位DAO
		ItsLinerseatDao linerseatDao = new ItsLinerseatDao(conn);
		//根据班次号查询计划班次席位信息
		List<EpdPlanseat> planseats = planseatDao.queryByPlanSeqAndPlanId(epdPlan.getPlanSeq(), liner.getLinerId());
		for(EpdPlanseat planseat : planseats){
			ItsLinerseat linerseat = new ItsLinerseat();
			linerseat.setLinerSeq(liner.getLinerSeq());
			linerseat.setLinerDate(liner.getLinerDate());
			linerseat.setLinerId(planseat.getPlanId());
			linerseat.setSeatId(planseat.getSeatId());
			linerseat.setSeatStatus(planseat.getSeatState());
			linerseat.setSeatType(planseat.getSeatType());
			linerseat.setUpdateTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
			linerseatDao.insert(linerseat,config);
		}
	}
	
	private void buildCheck(Connection conn,EpdPlan epdPlan,ItsLiner liner
			,Map<String, Object> config){
		//计划检票口DAO
		EpdPlancheckDao plancheckDao = new EpdPlancheckDao(conn);
		//日班次检票口DAO
		ItsLinercheckDao linercheckDao = new ItsLinercheckDao(conn);
		//查询计划班次的检票口信息
		List<EpdPlancheck> planchecks = plancheckDao.queryByPlanSeqAndPlanId(epdPlan.getPlanSeq(), liner.getLinerId());
		for(EpdPlancheck plancheck : planchecks){
			ItsLinercheck linercheck = new ItsLinercheck();
			linercheck.setLinerSeq(liner.getLinerSeq());
			linercheck.setLinerDate(liner.getLinerDate());
			linercheck.setLinerId(liner.getLinerId());
			linercheck.setCheckgateSeq(plancheck.getCheckgateSeq());
			linercheck.setUpdateTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
			linercheckDao.insert(linercheck,config);
		}
	}
	
	private void buildStation(Connection conn,EpdPlan epdPlan,ItsLiner liner
			,Map<String, Object> config){
		//计划站点DAO
		EpdPlanstationDao planstationDao = new EpdPlanstationDao(conn);
		//日班次站点DAO
		ItsLinerstationDao linerstationDao = new ItsLinerstationDao(conn);
		//查询计划班次站点信息
		List<EpdPlanstation> planstations = planstationDao.queryByPlanSeqAndPlanId(epdPlan.getPlanSeq(), liner.getLinerId());
		for(EpdPlanstation planstation : planstations){
			ItsLinerstation linerstation = new ItsLinerstation();
			linerstation.setLinerSeq(liner.getLinerSeq());
			linerstation.setLinerDate(liner.getLinerDate());
			linerstation.setLinerId(liner.getLinerId());
			linerstation.setRouteSeq(planstation.getRouteSeq());
			linerstation.setRouteName(planstation.getRouteName());
			linerstation.setStationSeq(planstation.getStationSeq());
			linerstation.setStationName(planstation.getStationName());
			linerstation.setStationOrder(planstation.getStationOrder());
			linerstation.setIfSale(planstation.getIfSale());
			linerstation.setStationNum(planstation.getStationNum());
			linerstation.setUpdateTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
			linerstationDao.insert(linerstation,config);
		}
	}
	
	private void buildDeal(Connection conn,EpdPlan epdPlan,ItsLiner liner
			,Map<String, Object> config){
		//配载计划DAO
		EpdPlandealDao plandealDao = new EpdPlandealDao(conn);
		//日班次配载Dao
		ItsLinerdealDao linerdealDao = new ItsLinerdealDao(conn);
		List<EpdPlandeal> plandeals = plandealDao.queryByPlanSeqAndPlanId(epdPlan.getPlanSeq(), liner.getLinerId());
		for(EpdPlandeal plandeal : plandeals){
			ItsLinerdeal linerdeal = new ItsLinerdeal();
			linerdeal.setLinerSeq(liner.getLinerSeq());
			linerdeal.setLinerDate(liner.getLinerDate());
			linerdeal.setLinerId(liner.getLinerId());
			linerdeal.setDealId(plandeal.getDealId());
			linerdeal.setDealOrganize(plandeal.getDealOrganize());
			linerdeal.setUpdateTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
			linerdealDao.insert(linerdeal,config);
		}
	}
	
	private void buildService(Connection conn,EpdPlan epdPlan,ItsLiner liner
			,Map<String, Object> config){
		//计划站点DAO
		EpdPlanserviceDao planserviceDao = new EpdPlanserviceDao(conn);
		//日班次站点DAO
		ItsLinerserviceDao linerserviceDao = new ItsLinerserviceDao(conn);
		//查询计划班次站点信息
		List<EpdPlanservice> planservices = planserviceDao.queryByPlanSeq(epdPlan.getPlanSeq());
		if (null != planservices && planservices.size()>0){
			for(EpdPlanservice planservice : planservices){
				ItsLinerservice linerservice = new ItsLinerservice();
				linerservice.setLinerSeq(liner.getLinerSeq());
				linerservice.setLinerDate(liner.getLinerDate());
				linerservice.setLinerId(liner.getLinerId());
				linerservice.setRouteSeq(planservice.getRouteSeq());
				linerservice.setRouteName(planservice.getRouteName());
				linerservice.setServiceSeq(planservice.getServiceSeq());
				linerservice.setServiceName(planservice.getServiceName());
				linerservice.setIfUsing(planservice.getIfUsing());
				linerservice.setServiceOrder(planservice.getServiceOrder());
				linerservice.setUpdateTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
				linerserviceDao.insert(linerservice,config);
			}
		}
	}
	
	private void buildFare(Connection conn,EpdPlan epdPlan,ItsLiner liner
			,Map<String, Object> config){
		EpdPlanstationDao planstationDao = new EpdPlanstationDao(conn);
		EpdFareDao epdFareDao = new EpdFareDao(conn);
		EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
		EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
		ItsLinerfareDao itsLinerfareDao = new ItsLinerfareDao(conn);
		List<EpdPlanstation> planstations = planstationDao.queryByPlanSeqAndPlanId(epdPlan.getPlanSeq(), liner.getLinerId());
		if (null != planstations && planstations.size()>0){
			List<EpdFaresuit> faresuits = epdFaresuitDao.queryByLinerDate(liner.getOrganizeSeq(),
					liner.getLinerDate());
			boolean isFaresuit = false;
			for (EpdPlanstation planstation : planstations) {
				isFaresuit = false;
				if (null != faresuits && faresuits.size()>0){
					//查询价套
					for (EpdFaresuit faresuit : faresuits) {
						List<EpdFaresuitdetail> faresuitdetails = epdFaresuitDetailDao.queryByRouteAndStationAndCargradeAndFaresuiit(
								liner.getRouteSeq(),planstation.getStationSeq(),liner.getCargradeSeq(),faresuit.getFaresuitSeq());
						if (null != faresuitdetails && faresuitdetails.size()>0){
							isFaresuit = true;
							ItsLinerfare linerfare = new ItsLinerfare();
							linerfare.setLinerSeq(liner.getLinerSeq());
							linerfare.setLinerDate(liner.getLinerDate());
							linerfare.setLinerId(liner.getLinerId());
							linerfare.setRouteSeq(planstation.getRouteSeq());
							linerfare.setStationSeq(planstation.getStationSeq());
							linerfare.setBaseFare(faresuitdetails.get(0).getBaseFare());
							linerfare.setStationFare(faresuitdetails.get(0).getStationFare());
							linerfare.setFuelFare(faresuitdetails.get(0).getFuelFare());
							linerfare.setOtherOne(faresuitdetails.get(0).getOtherOne());
							linerfare.setOtherTwo(faresuitdetails.get(0).getOtherTwo());
							linerfare.setOtherThree(faresuitdetails.get(0).getOtherThree());
							linerfare.setOtherFour(faresuitdetails.get(0).getOtherFour());
							linerfare.setOtherFive(faresuitdetails.get(0).getOtherFive());
							linerfare.setFullFare(faresuitdetails.get(0).getFullFare());
							linerfare.setUpdateTime(DateUtils.FORMAT_LONG);
							itsLinerfareDao.insert(linerfare,config);
						}
					}
				}
				if (isFaresuit == false){
					//查询基础价格
					List<EpdFare> fares = epdFareDao.queryByRouteAndStationAndCargrade(liner.getRouteSeq(),
							planstation.getStationSeq(),liner.getCargradeSeq());
					if (null != fares && fares.size()>0){
						isFaresuit = true;
						ItsLinerfare linerfare = new ItsLinerfare();
						linerfare.setLinerSeq(liner.getLinerSeq());
						linerfare.setLinerDate(liner.getLinerDate());
						linerfare.setLinerId(liner.getLinerId());
						linerfare.setRouteSeq(planstation.getRouteSeq());
						linerfare.setStationSeq(planstation.getStationSeq());
						linerfare.setBaseFare(fares.get(0).getBaseFare());
						linerfare.setStationFare(fares.get(0).getStationFare());
						linerfare.setFuelFare(fares.get(0).getFuelFare());
						linerfare.setOtherOne(fares.get(0).getOtherOne());
						linerfare.setOtherTwo(fares.get(0).getOtherTwo());
						linerfare.setOtherThree(fares.get(0).getOtherThree());
						linerfare.setOtherFour(fares.get(0).getOtherFour());
						linerfare.setOtherFive(fares.get(0).getOtherFive());
						linerfare.setFullFare(fares.get(0).getFullFare());
						linerfare.setUpdateTime(DateUtils.FORMAT_LONG);
						itsLinerfareDao.insert(linerfare,config);
					}
				}
			}
		}
	}

}
