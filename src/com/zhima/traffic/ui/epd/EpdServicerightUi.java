/**
 *******************************************************************************
 *
 * (c) Copyright 2012 重庆市志玛信息技术有限公司
 *
 * 系统名称：frameWork
 * 文  件  名 ：abcd.java
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
import com.zhima.frame.action.sam.ISamService;
import com.zhima.frame.action.sam.impl.ImpSamService;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamService;
import com.zhima.traffic.action.epd.IEpdRoute;
import com.zhima.traffic.action.epd.IEpdServiceright;
import com.zhima.traffic.action.epd.impl.ImpEpdRoute;
import com.zhima.traffic.action.epd.impl.ImpEpdServiceright;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.traffic.model.EpdServiceright;
import com.zhima.util.DateUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * SamUserUi概要说明：用户设置
 * @author lcy
 */
public class EpdServicerightUi extends Composite {
	private Object obj;
	//功能权限
	private List<SamModuleRight> rights;
	//用户
	private GridView tbService;
	//组
	private GridView tbRoute;
	
	/**
	 * 构造函数: 构造用户设置类
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EpdServicerightUi(Composite parent, int style, List list) {
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
		createService(sashForm);
        createRoute(sashForm);
        sashForm.setWeights(new int[] {30,70});
        queryMethod();
	}

	public Composite createService(SashForm sashForm){
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
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compOper.toolbar, rightBos);

		compOper.detail.setLayout(new FillLayout());
		tbService = new GridView(compOper.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("销售点名称","serviceName",220));
		columns.add(new GridColumn("销售类型","saleType",CommFinal.ARR_SALE_TYPE,100));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setVirtual(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		tbService.CreateTabel(gridConfig);
		tbService.bindWidgetSelected(obj, "queryRight");
		return compOper;
	}
	
	public Composite createRoute(SashForm sashForm){
		BasicPanel compMain=new BasicPanel(sashForm,SWT.BORDER);
		compMain.setInput(false);
		compMain.createPanel();
		//按钮权限
		compMain.toolbar.setLayout(new RowLayout());
		final List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		//设置选项卡右键菜单
		for(int i=0;i<rights.size();i++){
			if ("saveMethod".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compMain.toolbar, rightBos);
		
		compMain.detail.setLayout(new FormLayout());

		tbRoute = new GridView(compMain.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("线路名称","routeName",120));
		columns.add(new GridColumn("拼音代码","routeSpell",120));
		columns.add(new GridColumn("线路代码","routeCode",120));
		columns.add(new GridColumn("途经站","stationName",300));
		columns.add(new GridColumn("线路类型","routetypeName",120));
		columns.add(new GridColumn("到达方式","arriveType",TraffFinal.ARR_ARRIVE_TYPE,100));
		columns.add(new GridColumn("道路类型","roadType",TraffFinal.ARR_ROAD_TYPE,100));
		columns.add(new GridColumn("线路状态","routeStatus",TraffFinal.ARR_ROUTE_STATUS,100));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setVirtual(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(true);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		gridConfig.setObj(obj);
		gridConfig.setGridName("tbGroup");
		tbRoute.CreateTabel(gridConfig);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		tbRoute.setLayoutData(data);

		return compMain;	
	}
	

	public void queryMethod(){
		try {
			ISamService iSamService = new ImpSamService();
			List<SamService> samServices = iSamService.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			tbService.setDataList(samServices);
			tbRoute.removeAll();
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}

	public void queryRight(){
		try {
			IEpdRoute iEpdRoute = new ImpEpdRoute();
			List<EpdRoute> epdRoutes = iEpdRoute.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			tbRoute.setDataList(epdRoutes);
			
			if(null!=tbService.getSelection()){
				if (null != epdRoutes && epdRoutes.size()>0){
					SamService samService = (SamService) tbService.getSelection();
					IEpdServiceright iEpdServiceright = new ImpEpdServiceright();
					List<EpdServiceright> epdServicerights = iEpdServiceright.queryByServiceSeq(samService.getServiceSeq());
					if (null != epdServicerights && epdServicerights.size()>0){
						for (int i = 0; i < epdRoutes.size(); i++) {
							tbRoute.setCheck(i, false);
							for (int j = 0; j < epdServicerights.size(); j++) {
								if (epdRoutes.get(i).getRouteSeq().equals(epdServicerights.get(j).getRouteSeq())){
									tbRoute.setCheck(i, true);
									break;
								}
							}
						}
					}
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public void saveMethod(){
		try {

			if(null!=tbService.getSelection()){
				String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
				SamService samService = (SamService) tbService.getSelection();
				List<EpdRoute> routes = (List<EpdRoute>) tbRoute.getCheckSelections();
				List<EpdServiceright> addRights = new ArrayList<EpdServiceright>();
				if (null != routes && routes.size()>0){
					for (int i = 0; i < routes.size(); i++) {
						EpdServiceright serviceright = new EpdServiceright();
						serviceright.setServiceSeq(samService.getServiceSeq());
						serviceright.setRouteSeq(routes.get(i).getRouteSeq());
						serviceright.setUpdateTime(currTime);
						addRights.add(serviceright);
					}
				}
				IEpdServiceright iEpdServiceright = new ImpEpdServiceright();
				iEpdServiceright.saveRight(samService.getServiceSeq(), addRights, CommFinal.initConfig());
				MsgBox.warning(getShell(), "销售点权限保存成功！");
			}else{
				MsgBox.warning(getShell(), "请选择销售点保存权限！");
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}	
}
