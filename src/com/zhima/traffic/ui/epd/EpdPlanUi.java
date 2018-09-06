/**
 *******************************************************************************
 *
 * (c) Copyright 2012 重庆市志玛信息技术有限公司
 *
 * 系统名称：frameWork
 * 文  件  名 ：SamModuleUi.java
 * 模块名称：(请更改成该模块名称)
 * 创  建  人 ：鲁承毅
 * 创建日期：2013-3-2 下午10:36:15
 * 修  改  人 ：(修改了该文件，请填上修改人的名字)
 * 修改日期：(请填上修改该文件时的日期)
 * 版         本 ： V1.0.0
 *******************************************************************************  
 */

package com.zhima.traffic.ui.epd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.epd.IEpdPlan;
import com.zhima.traffic.action.epd.IEpdPlanBuild;
import com.zhima.traffic.action.epd.impl.ImpEpdPlan;
import com.zhima.traffic.action.epd.impl.ImpEpdPlanBuild;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.comm.TraffParam;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdPlanUi概要说明：运营计划设置
 * @author lcy
 */
public class EpdPlanUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	//查询相关
	private String fRoute = "";
	private String fStation = "";
	private String fPlanId = "";
	private String fPlanType = "";
	private String fPlanStatus = "";
	/**
	 * 
	 * 构造函数: 车辆档案
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EpdPlanUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		panel = new BasicPanel(this, SWT.BORDER);
		panel.setInput(false);
		panel.createPanel();
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		panel.setLayoutData(data);
		createToolbar();
		createDetail();
	}

	/**
	 * createToolbar方法描述：构建工具面板按钮
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:37:54
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createToolbar(){
		panel.toolbar.setLayout(new RowLayout());
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, panel.toolbar, rights);
	}
	
	/**
	 * createDetail方法描述：构建细部面板表格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createDetail(){
		panel.detail.setLayout(new FormLayout());
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("线路名称","routeName",120));
		columns.add(new GridColumn("车型","cargradeName",100));
		columns.add(new GridColumn("计划车次","planId",100));
		columns.add(new GridColumn("发车时间","planTime",80));
		columns.add(new GridColumn("座位数","seatNum",80));
		columns.add(new GridColumn("免票数","freeNum",80));
		columns.add(new GridColumn("半票数","halfNum",80));
		columns.add(new GridColumn("禁售数","stopNum",80));
		columns.add(new GridColumn("类型","planType",80));
		columns.add(new GridColumn("运行参数","planOption",200));
		columns.add(new GridColumn("状态","planStatus",60));
		columns.add(new GridColumn("开始日期","startDate",110));
		columns.add(new GridColumn("结束日期","endDate",110));
		columns.add(new GridColumn("预售天数","preDays",80));
		columns.add(new GridColumn("检票口","checkName",80));
		columns.add(new GridColumn("发车位","parkingName",80));
		columns.add(new GridColumn("乘车点","usingService",TraffFinal.ARR_USING_SERVICE,80));
		columns.add(new GridColumn("打印座位","ifPrintseat",TraffFinal.ARR_IF_PRINTSEAT,80));
		columns.add(new GridColumn("允许网售","ifNetsale",TraffFinal.ARR_IF_NETSALE,80));
		columns.add(new GridColumn("配载","ifDeal",TraffFinal.ARR_IF_DEAL,80));
		columns.add(new GridColumn("始发站","ifMain",TraffFinal.ARR_IF_MAIN,80));
		columns.add(new GridColumn("配载车次","dealId",80));
		columns.add(new GridColumn("配载车站","dealOrganizename",300));
		columns.add(new GridColumn("备注","remark",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rights);
		gridConfig.setObj(obj);
		gridView = new GridView(panel.detail, SWT.NONE);
		gridView.CreateTabel(gridConfig);
		gridView.bindMouseDoubleClick(obj,rights,"updateMethod");
		gridView.bindRefresh(obj, "queryPlan");
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		gridView.setLayoutData(data);
	}
	
	/**
	 * queryMethod方法描述：查询方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryMethod(){
		EpdPlanFindUi findUi = new EpdPlanFindUi(this.getShell());
		findUi.setfRoute(fRoute);
		findUi.setfStation(fStation);
		findUi.setfPlanId(fPlanId);
		findUi.setfPlanType(fPlanType);
		findUi.setfPlanStatus(fPlanStatus);
		findUi.open();
		if (findUi.getBtnId()==1){
			fRoute = findUi.getfRoute();
			fStation=findUi.getfStation();
			fPlanId=findUi.getfPlanId();
			fPlanType=findUi.getfPlanType();
			fPlanStatus=findUi.getfPlanStatus();
			ThreadWaiting waiting = new ThreadWaiting(this,"queryPlan",new Class[]{},new String[]{});
			waiting.task();
		}
	}
	
	public void queryPlan(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					int start = gridView.getStart();
					int limit = gridView.getLimit();
					IEpdPlan iEpdPlan = new ImpEpdPlan();
					int count = iEpdPlan.queryCountByCustom(CommFinal.organize.getOrganizeSeq(),
							fRoute, fStation, fPlanId, fPlanType, fPlanStatus);
					if (count>0){
						gridView.removeAll();
						List<EpdPlan> epdPlans = iEpdPlan.queryPageByCustom(CommFinal.organize.getOrganizeSeq(),
								fRoute, fStation, fPlanId, fPlanType, fPlanStatus, start, limit);
						gridView.setDataList(epdPlans);
					}else{
						gridView.removeAll();
					}
					gridView.setTotalCount(count);
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void addMethod(){
		EpdPlanEditUi editUi = new EpdPlanEditUi(this.getShell(),gridView,CommFinal.OPER_TYPE_ADD);
		editUi.open();
	}
	
	public void updateMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					EpdPlanEditUi editUi = new EpdPlanEditUi(this.getShell(), gridView, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
				break;
			}
		}
	}
	
	public void copyMethod(){
		if(null!=gridView.getSelection()){
			EpdPlanEditUi editUi = new EpdPlanEditUi(this.getShell(), gridView, CommFinal.OPER_TYPE_COPY);
			editUi.open();
		}else{
			MsgBox.warning(getShell(), "请选择复制的项。");
		}
	}
	
	public void carMethod(){
		if(null!=gridView.getSelection()){
			EpdPlanCarUi carUi = new EpdPlanCarUi(this.getShell(), gridView, CommFinal.OPER_TYPE_UPDATE);
			carUi.open();
		}else{
			MsgBox.warning(getShell(), "请选择设置运车的项。");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteMethod(){
		try {
			int checkIndex[] = gridView.getSelectionIndexs();
			if (checkIndex.length>0){
				int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行数据吗？");
		    	if(isdel == SWT.YES){
		    		IEpdPlan iEpdPlan = new ImpEpdPlan();
		    		iEpdPlan.delete((List<EpdPlan>) gridView.getSelections(),CommFinal.initConfig());
					gridView.deleteRow(checkIndex);
				} 		
		    }
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void buildMethod(){
		int checkIndex[] = gridView.getSelectionIndexs();
		if (checkIndex.length>0){
			int isdel =  MsgBox.confirm(getShell(),"确定要生成选中行车次吗？");
			if(isdel == SWT.NO){
				return;
			}
		}
		ThreadWaiting waiting = new ThreadWaiting(this,"bulidPlan",new Class[]{},new String[]{});
		waiting.task();
	}
	
	@SuppressWarnings("unchecked")
	public void bulidPlan(){
		try {
    		List<EpdPlan> epdPlans = (List<EpdPlan>) gridView.getSelections();
    		IEpdPlanBuild iEpdPlanBuild = new ImpEpdPlanBuild();
    		String buildFare =  CommFinal.getParamValue(TraffParam.BuildLinerFare).getParameterValue();
    		for (int i = 0; i < epdPlans.size(); i++) {
				iEpdPlanBuild.planBuild(epdPlans.get(i),buildFare,CommFinal.initConfig());
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	public void outputMethod() throws UserBusinessException{
		gridView.exportExcel();
	}

}
