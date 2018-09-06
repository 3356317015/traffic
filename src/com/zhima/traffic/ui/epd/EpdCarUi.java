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
import com.zhima.frame.model.SamParameter;
import com.zhima.traffic.action.epd.IEpdCar;
import com.zhima.traffic.action.epd.IEpdCarinfo;
import com.zhima.traffic.action.epd.impl.ImpEpdCar;
import com.zhima.traffic.action.epd.impl.ImpEpdCarinfo;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.comm.TraffParam;
import com.zhima.traffic.model.EpdCar;
import com.zhima.traffic.model.EpdCarinfo;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdCarUi概要说明：车辆档案设置
 * @author lcy
 */
public class EpdCarUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	//查询相关
	private String fRoute = "";
	private String fCarCode = "";
	private String fCarNumber = "";
	private String fCompany = "";
	private String fStatus = "";
	/**
	 * 
	 * 构造函数: 车辆档案
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EpdCarUi(Composite parent, int style, List list) {
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
		columns.add(new GridColumn("车牌号","carNumber",100));
		columns.add(new GridColumn("车辆编号","carCode",80));
		columns.add(new GridColumn("购车日期","buyDate",100));
		columns.add(new GridColumn("强保日","checkDays",80));
		columns.add(new GridColumn("座位数","seatNum",80));
		columns.add(new GridColumn("准座数","quasiNum",80));
		columns.add(new GridColumn("车型","cargradeName",100));
		columns.add(new GridColumn("运营线路","routeName",120));
		columns.add(new GridColumn("安检有效","safecarValid",80));
		columns.add(new GridColumn("证检有效","safecerValid",80));
		columns.add(new GridColumn("驾驶员","driverName",160));
		columns.add(new GridColumn("运营合同","contractName",120));
		columns.add(new GridColumn("结算号","companyCode",160));
		columns.add(new GridColumn("结算公司","companyName",300));
		columns.add(new GridColumn("车属公司","carCompanyname",300));
		columns.add(new GridColumn("状态","carStatus",TraffFinal.ARR_CAR_STATUS,100));
		columns.add(new GridColumn("备注","remark",300));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rights);
		gridConfig.setObj(obj);
		gridView = new GridView(panel.detail, SWT.NONE);
		gridView.CreateTabel(gridConfig);
		gridView.bindMouseDoubleClick(obj,rights,"updateMethod");
		gridView.bindRefresh(obj, "queryCar");
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
		EpdCarFindUi findUi = new EpdCarFindUi(this.getShell());
		findUi.setfRoute(fRoute);
		findUi.setfCarCode(fCarCode);
		findUi.setfCarNumber(fCarNumber);
		findUi.setfCompany(fCompany);
		findUi.setfStatus(fStatus);
		findUi.open();
		if (findUi.getBtnId()==1){
			fRoute = findUi.getfRoute();
			fCarCode = findUi.getfCarCode();
			fCarNumber = findUi.getfCarNumber();
			fCompany = findUi.getfCompany();
			fStatus = findUi.getfStatus();
			ThreadWaiting waiting = new ThreadWaiting(this,"queryCar",new Class[]{},new String[]{});
			waiting.task();
		}
	}
	
	public void queryCar(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					int start = gridView.getStart();
					int limit = gridView.getLimit();
					IEpdCar iEpdCar = new ImpEpdCar();
					int count = iEpdCar.queryCountByCustom(CommFinal.organize.getOrganizeSeq(),
							fRoute, fCarCode, fCarNumber, fCompany, fStatus);
					if (count>0){
						gridView.removeAll();
						List<EpdCar> epdCars = iEpdCar.queryPageByCustom(CommFinal.organize.getOrganizeSeq(),
								fRoute, fCarCode, fCarNumber, fCompany, fStatus, start, limit);
						gridView.setDataList(epdCars);
						IEpdCarinfo iEpdCarinfo = new ImpEpdCarinfo();
						SamParameter parameter = CommFinal.getParamValue(TraffParam.WarningCarDays);
						String currDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
						for (int j = 0; j < epdCars.size(); j++) {
							epdCars.get(j).setSafecarLimit(epdCars.get(j).getSafecarValid() + CommFinal.getItemName(
									TraffFinal.ARR_SAFECAR_TYPE, epdCars.get(j).getSafecarType()));
							epdCars.get(j).setSafecerLimit(epdCars.get(j).getSafecerValid() + CommFinal.getItemName(
									TraffFinal.ARR_SAFECER_TYPE, epdCars.get(j).getSafecerType()));
							if (DateUtils.nDaysBetweenTwoDate(currDate,epdCars.get(j).getCargradeValid())<0){
								gridView.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
								continue;
							}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdCars.get(j).getCargradeValid())
									<=Integer.valueOf(parameter.getParameterValue())){
								gridView.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
							}
							
							List<EpdCarinfo> carinfos = iEpdCarinfo.queryByCarSeq(epdCars.get(j).getCarSeq());
							if(null != carinfos && carinfos.size()>0){
								for (int k = 0; k < carinfos.size(); k++) {
									if (DateUtils.nDaysBetweenTwoDate(currDate,carinfos.get(k).getEndDate())<0){
										gridView.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
										break;
									}else if(DateUtils.nDaysBetweenTwoDate(currDate,carinfos.get(k).getEndDate())
											<=Integer.valueOf(parameter.getParameterValue())){
										gridView.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
									}
								}
							}
						}
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
		EpdCarEditUi editUi = new EpdCarEditUi(this.getShell(),gridView,CommFinal.OPER_TYPE_ADD);
		editUi.open();
	}
	
	public void updateMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					EpdCarEditUi editUi = new EpdCarEditUi(this.getShell(), gridView, CommFinal.OPER_TYPE_UPDATE);
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
			int checkIndex[] = gridView.getSelectionIndexs();
			if (checkIndex.length>0){
				int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行数据吗？");
		    	if(isdel == SWT.YES){
		    		IEpdCar iEpdCar = new ImpEpdCar();
		    		iEpdCar.delete((List<EpdCar>) gridView.getSelections(),CommFinal.initConfig());
					gridView.deleteRow(checkIndex);
				} 
			    		
		    }
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void outputMethod() throws UserBusinessException{
		gridView.exportExcel();
	}

}
