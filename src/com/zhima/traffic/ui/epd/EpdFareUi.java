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
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.epd.IEpdFare;
import com.zhima.traffic.action.epd.IEpdRoute;
import com.zhima.traffic.action.epd.impl.ImpEpdFare;
import com.zhima.traffic.action.epd.impl.ImpEpdRoute;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdFare;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdFareUi概要说明：基础价格设置
 * @author lcy
 */
public class EpdFareUi extends Composite {
	private Object obj;
	//功能权限
	private List<SamModuleRight> rights;
	private GridView gridRoute;
	private GridView gridFare;
	//查询相关
	private String fRouteCode="";
	private String fRouteSpell="";
	private String fRouteName="";
	private String fRouteType="";
	private String fRoadType="";
	private String fRouteStatus="";
	
	/**
	 * 
	 * 构造函数: 城市区域类
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EpdFareUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		final SashForm sashForm = new SashForm(this,SWT.HORIZONTAL|SWT.FLAT);
        sashForm.setLayout(new FillLayout());
        FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		sashForm.setLayoutData(data);
		createRoute(sashForm);
		createFare(sashForm);
        sashForm.setWeights(new int[] {45,55});
        queryRoute();
	}

	public Composite createRoute(SashForm sashForm){
		BasicPanel compOper = new BasicPanel(sashForm, SWT.BORDER);
		compOper.setShowBack(false);
		compOper.setInput(false);
		compOper.createPanel();
		//按钮权限
		compOper.toolbar.setLayout(new RowLayout());
		List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		for(int i=0;i<rights.size();i++){
			if ("queryMethod".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}else if ("outputMethod".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compOper.toolbar, rightBos);

		compOper.detail.setLayout(new FillLayout());
		gridRoute = new GridView(compOper.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("线路名称","routeName",120));
		columns.add(new GridColumn("拼音代码","routeSpell",120));
		columns.add(new GridColumn("线路代码","routeCode",120));
		columns.add(new GridColumn("线路类型","routetypeName",120));
		columns.add(new GridColumn("到达方式","arriveType",TraffFinal.ARR_ARRIVE_TYPE,100));
		columns.add(new GridColumn("道路类型","roadType",TraffFinal.ARR_ROAD_TYPE,100));
		columns.add(new GridColumn("线路状态","routeStatus",TraffFinal.ARR_ROUTE_STATUS,100));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rights);
		gridConfig.setObj(obj);
		gridRoute.CreateTabel(gridConfig);
		gridRoute.bindRefresh(obj, "queryRoute");
		gridRoute.bindWidgetSelected(obj, "queryFare");
		gridRoute.bindMouseDoubleClick(obj,rights,"fareMethod");
		return compOper;
	}
	
	public Composite createFare(SashForm sashForm){
		BasicPanel compMain=new BasicPanel(sashForm,SWT.BORDER);
		compMain.setInput(false);
		compMain.createPanel();
		//按钮权限
		compMain.toolbar.setLayout(new RowLayout());
		final List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		//设置选项卡右键菜单
		for(int i=0;i<rights.size();i++){
			if ("fareMethod".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}else if ("updateMethod".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}else if ("deleteMethod".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compMain.toolbar, rightBos);
		
		compMain.detail.setLayout(new FormLayout());

		gridFare = new GridView(compMain.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("车型名称","cargradeName",100));
		columns.add(new GridColumn("目的地","stationName",120));
		columns.add(new GridColumn("总价","fullFare",100));	
		columns.add(new GridColumn("基础价","baseFare",80));
		columns.add(new GridColumn("站务费","stationFare",80));
		columns.add(new GridColumn("燃油附加费","fuelFare",100));
		columns.add(new GridColumn("其他费","otherOne",80));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setVirtual(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		gridFare.CreateTabel(gridConfig);
		gridFare.bindMouseDoubleClick(obj,rights,"updateMethod");
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		gridFare.setLayoutData(data);

		return compMain;	
	}
	
	
	/**
	 * queryMethod方法描述：查询方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryMethod(){
		EpdFareFindUi findUi = new EpdFareFindUi(this.getShell());
		findUi.setfRouteCode(fRouteCode);
		findUi.setfRouteSpell(fRouteSpell);
		findUi.setfRouteName(fRouteName);
		findUi.setfRouteType(fRouteType);
		findUi.setfRoadType(fRoadType);
		findUi.setfRouteStatus(fRouteStatus);
		findUi.open();
		if (findUi.getBtnId()==1){
			fRouteCode = findUi.getfRouteCode();
			fRouteSpell= findUi.getfRouteSpell();
			fRouteName = findUi.getfRouteName();
			fRouteType = findUi.getfRouteType();
			fRoadType  = findUi.getfRoadType();
			fRouteStatus = findUi.getfRouteStatus();
			ThreadWaiting waiting = new ThreadWaiting(this,"queryRoute",new Class[]{},new String[]{});
			waiting.task();
		}
	}
	
	public void queryRoute(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					int start = gridRoute.getStart();
					int limit = gridRoute.getLimit();
					IEpdRoute iEpdRoute = new ImpEpdRoute();
					int count = iEpdRoute.queryCountByCustom(CommFinal.organize.getOrganizeSeq(),
							fRouteCode, fRouteSpell, fRouteName, fRouteType, fRoadType, fRouteStatus);
					if (count>0){
						List<EpdRoute> routes = iEpdRoute.queryPageByCustom(CommFinal.organize.getOrganizeSeq(),
								fRouteCode, fRouteSpell, fRouteName, fRouteType,
								fRoadType, fRouteStatus, start, limit);
						gridRoute.setDataList(routes);
					}else{
						gridRoute.removeAll();
					}
					gridRoute.setTotalCount(count);
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void queryFare(){
		if(null!=gridRoute.getSelection()){
			try {
				EpdRoute epdRoute = (EpdRoute) gridRoute.getSelection();
				IEpdFare iEpdFare = new ImpEpdFare();
				List<EpdFare> epdFares = iEpdFare.queryByRouteSeq(epdRoute.getRouteSeq());
				gridFare.setDataList(epdFares);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			MsgBox.warning(getShell(), "请选择修改的项。");
		}
	}
	
	public void fareMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridRoute.getSelection()){
					EpdFareEditUi editUi = new EpdFareEditUi(this.getShell(), gridRoute, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
					queryFare();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
				break;
			}
		}
	}
	
	public void updateMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridFare.getSelection()){
					EpdFareItemEditUi editUi = new EpdFareItemEditUi(this.getShell(), gridFare, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteMethod(){
		try {
			int checkIndex[] = gridFare.getSelectionIndexs();
			if (checkIndex.length>0){
				int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行线路运价数据吗？");
		    	if(isdel == SWT.YES){
		    		IEpdFare iEpdFare = new ImpEpdFare();
		    		iEpdFare.delete((List<EpdFare>) gridFare.getSelections(),CommFinal.initConfig());
		    		gridFare.deleteRow(checkIndex);
				} 
		    }
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void outputMethod() throws UserBusinessException{
		gridRoute.exportExcel();
	}

}
