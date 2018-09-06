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

package com.zhima.traffic.ui.operate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.operate.IItsLiner;
import com.zhima.traffic.action.operate.impl.ImpItsLiner;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropCargrade;
import com.zhima.traffic.drop.DropRoute;
import com.zhima.traffic.drop.DropStation;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.CCalendar;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdPlanUi概要说明：运营计划设置
 * @author lcy
 */
public class ItsLinerScheduleUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	private ItsLinerReportUi reportUi;
	
	private CCombo cboQueryMode;
	private DropRoute dropRoute;
	private DropStation dropStation;
	private Text txtLinerId;
	private DropCargrade dropCargrade;
	private CCombo cboQueryValue;
	private CCalendar dStartDate;
	private Button btnLimit;
	private CCalendar dLimitDate;
	private Button btnQuery;
	/**
	 * 
	 * 构造函数: 车辆档案
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItsLinerScheduleUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		panel = new BasicPanel(this, SWT.BORDER);
		panel.createPanel();
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		panel.setLayoutData(data);
		
		reportUi = new ItsLinerReportUi(this, panel, SWT.NONE);
		data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		reportUi.setLayoutData(data);
		reportUi.setVisible(false);
		
		createToolbar();
		createInput();
		createDetail();
		initData();
	}

	private void createInput() {
		panel.input.setLayout(new FormLayout());

		Label lbQueryMode = new Label(panel.input, SWT.NONE);
		lbQueryMode.setText("查询条件:");
		lbQueryMode.setFont(StyleFinal.SYS_FONT);
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 15);
		formData.top= new FormAttachment(0, 9);
		formData.height= 22;
		lbQueryMode.setLayoutData(formData);
		
		cboQueryMode = new CCombo(panel.input, SWT.BORDER|SWT.READ_ONLY);
		cboQueryMode.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(lbQueryMode, 5);
		formData.top= new FormAttachment(0, 5);
		formData.width = 100;
		formData.height = StyleFinal.DROP_HEIGHT;
		cboQueryMode.setLayoutData(formData);
		
		dropRoute = new DropRoute(panel.input, SWT.BORDER);
		dropStation = new DropStation(panel.input, SWT.BORDER);
		txtLinerId = new Text(panel.input, SWT.BORDER);
		dropCargrade = new DropCargrade(panel.input, SWT.BORDER);
		dropCargrade.initAll();
		cboQueryValue = new CCombo(panel.input, SWT.BORDER);
		
		dropRoute.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		dropStation.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		dropCargrade.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		cboQueryValue.setFont(StyleFinal.SYS_FONT);
		
		formData = new FormData();
		formData.left = new FormAttachment(cboQueryMode, 5);
		formData.top= new FormAttachment(0, 5);
		formData.width = 160;
		formData.height = StyleFinal.DROP_HEIGHT;
		dropRoute.setLayoutData(formData);
		dropStation.setLayoutData(formData);
		txtLinerId.setLayoutData(formData);
		dropCargrade.setLayoutData(formData);
		cboQueryValue.setLayoutData(formData);
		
		Label lbStartDate = new Label(panel.input, SWT.NONE);
		lbStartDate.setText("班次日期:");
		lbStartDate.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(cboQueryValue, 10);
		formData.top= new FormAttachment(lbQueryMode, 0, SWT.TOP);
		formData.height= 22;
		lbStartDate.setLayoutData(formData);
		
		dStartDate = new CCalendar(panel.input, SWT.BORDER);
		dStartDate.txtDate.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(lbStartDate, 5);
		formData.top= new FormAttachment(0, 5);
		formData.width = 110;
		formData.height = StyleFinal.DROP_HEIGHT;
		dStartDate.setLayoutData(formData);
		
		btnLimit = new Button(panel.input, SWT.NONE|SWT.CHECK);
		btnLimit.setText("至:");
		btnLimit.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(dStartDate, 10);
		formData.top= new FormAttachment(lbQueryMode, -5, SWT.TOP);
		formData.height= 22;
		btnLimit.setLayoutData(formData);
		
		dLimitDate = new CCalendar(panel.input, SWT.BORDER);
		dLimitDate.txtDate.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(btnLimit, 5);
		formData.top= new FormAttachment(0, 5);
		formData.width = 110;
		formData.height = StyleFinal.DROP_HEIGHT;
		dLimitDate.setLayoutData(formData);
		
		btnQuery = new Button(panel.input, SWT.NONE);
		btnQuery.setText("查询(&Q)");
		btnQuery.setFont(StyleFinal.BTN_FONT);
		btnQuery.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		formData = new FormData();
		formData.left = new FormAttachment(dLimitDate, 10);
		formData.top= new FormAttachment(0, 3);
		//formData.width = 80;
		formData.bottom =new FormAttachment(dLimitDate, 1, SWT.BOTTOM);
		btnQuery.setLayoutData(formData);
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
		columns.add(new GridColumn("途经站","stationName",160));
		columns.add(new GridColumn("车型","cargradeName",90));
		columns.add(new GridColumn("发车日期","linerDate",120));
		columns.add(new GridColumn("车次","linerId",80));
		columns.add(new GridColumn("发车时间","linerTime",80));
		columns.add(new GridColumn("票价","price",80));
		
		columns.add(new GridColumn("状态","linerStatus",80));
		columns.add(new GridColumn("类型","linerType",80));

		
		columns.add(new GridColumn("座位数","seatNum",80));
		columns.add(new GridColumn("免票数","freeNum",80));
		columns.add(new GridColumn("半票数","halfNum",80));
		
		columns.add(new GridColumn("余票数", "moreSeat",80));
		columns.add(new GridColumn("余半票", "moreHalf",80));
		columns.add(new GridColumn("余免票", "moreFe",80));
		
		columns.add(new GridColumn("售票数","saleNum",80));
		columns.add(new GridColumn("售半票","saleHalf",80));
		columns.add(new GridColumn("售免票","saleFree",80));
		columns.add(new GridColumn("配载数","dealNum",80));
		columns.add(new GridColumn("预留数","reverseNum",80));
		
		columns.add(new GridColumn("禁售数","stopNum",80));
		columns.add(new GridColumn("退票数","returnNum",80));
		columns.add(new GridColumn("废票数","invalidNum",80));
		
		columns.add(new GridColumn("检票数","checkNum",80));
		
		columns.add(new GridColumn("检票口","checkName",80));
		columns.add(new GridColumn("发车位","parkingName",80));
		columns.add(new GridColumn("乘车点","usingService",TraffFinal.ARR_USING_SERVICE,80));
		
		columns.add(new GridColumn("车牌号","carNumber",80));
		columns.add(new GridColumn("驾驶员","driverName",80));
		
		columns.add(new GridColumn("打印座位","ifPrintseat",TraffFinal.ARR_IF_PRINTSEAT,80));
		columns.add(new GridColumn("允许网售","ifNetsale",TraffFinal.ARR_IF_NETSALE,80));
		columns.add(new GridColumn("始发站","ifMain",TraffFinal.ARR_IF_MAIN,80));
		columns.add(new GridColumn("配载","ifDeal",TraffFinal.ARR_IF_DEAL,80));
		
		columns.add(new GridColumn("报班时间","reportTime",200));
		columns.add(new GridColumn("打单时间","printTime",200));
		columns.add(new GridColumn("出站时间","outstationTime",200));
		columns.add(new GridColumn("创建人","createUser",80));
		columns.add(new GridColumn("创建时间","createTime",200));

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
	
	private void initData(){
		cboQueryMode.setItems(new String[]{"线路名称","站点名称","车次号","车次类型","报班状态","车次状态"});
		cboQueryMode.setVisibleItemCount(10);
		cboQueryMode.select(0);
		cboQueryValue.setVisibleItemCount(10);
		dropRoute.setVisible(true);
		dropStation.setVisible(false);
		txtLinerId.setVisible(false);
		dropCargrade.setVisible(false);
		cboQueryValue.setVisible(false);
		dLimitDate.setEnabled(false);
		cboQueryMode.addSelectionListener(selectionListener_queryMode);
		dropRoute.droptext.txtShow.addKeyListener(keyListener_queryValue);
		dropStation.droptext.txtShow.addKeyListener(keyListener_queryValue);
		txtLinerId.addKeyListener(keyListener_queryValue);
		dropCargrade.droptext.txtShow.addKeyListener(keyListener_queryValue);
		cboQueryValue.addKeyListener(keyListener_queryValue);
		dStartDate.txtDate.addFocusListener(focusListener_dStartDate);
		dLimitDate.txtDate.addFocusListener(focusListener_dLimitDate);
		btnLimit.addSelectionListener(selectionListener_btnLimit);
		btnQuery.addMouseListener(mouseListener_btnQuery);
		setFocus();
	}
	
	public boolean setFocus(){
		dropRoute.droptext.txtShow.setFocus();
		dropRoute.droptext.txtShow.selectAll();
		return true;
	}
	
	/**
	 * queryMethod方法描述：查询方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				ThreadWaiting waiting = new ThreadWaiting(this,"queryLiner",new Class[]{},new String[]{});
				waiting.task();
				break;
			}
		}
	}
	
	public void queryLiner(){
		try {
			String organizeSeq = CommFinal.organize.getOrganizeSeq();
			String routeSeq ="";
			String stationSeq ="";
			String linerId ="";
			String cargradeSeq ="";
			String ifReport = "";
			String linerStatus ="";
			String startDate = dStartDate.getText();
			String limitDate = dLimitDate.getText();
			int start = gridView.getStart();
			int limit = gridView.getLimit();
			
			if (cboQueryMode.getText().equals("线路名称")){
				routeSeq = dropRoute.droptext.getValue();
			}else if (cboQueryMode.getText().equals("站点名称")){
				stationSeq = dropStation.droptext.getValue();
			}else if (cboQueryMode.getText().equals("车次号")){
				linerId = txtLinerId.getText();
			}else if (cboQueryMode.getText().equals("车型")){
				cargradeSeq = dropCargrade.droptext.getValue();
			}else{
				if (cboQueryMode.getText().equals("报班状态")){
					ifReport = CommFinal.getItemValue(TraffFinal.ARR_REPORT_STATUS, cboQueryValue.getText());
				}else{
					linerStatus = cboQueryValue.getText();
				}
			}
			
			IItsLiner iItsLiner = new ImpItsLiner();
			int count = iItsLiner.queryCountByCustom(organizeSeq, routeSeq, stationSeq,
					linerId, cargradeSeq, linerStatus, ifReport, startDate, limitDate);
			if (count>0){
				gridView.removeAll();
				List<ItsLiner> itsLiners = iItsLiner.queryPageByCustom(organizeSeq, routeSeq, stationSeq,
						linerId, cargradeSeq, linerStatus, ifReport, startDate, limitDate, start, limit);
				gridView.setDataList(itsLiners);
			}else{
				gridView.removeAll();
			}
			gridView.setTotalCount(count);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	
	public void linerMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					ItsLinerEditUi editUi = new ItsLinerEditUi(this.getShell(),gridView,CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}
	
	public void stationMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					ItsLinerStationUi editUi = new ItsLinerStationUi(this.getShell(),gridView,CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}
	
	public void seatMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					ItsLinerSeatUi editUi = new ItsLinerSeatUi(this.getShell(),gridView,CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}
	
	public void priceMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					ItsLinerPriceUi editUi = new ItsLinerPriceUi(this.getShell(),gridView,CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}

	public void checkMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					ItsLinerCheckUi editUi = new ItsLinerCheckUi(this.getShell(),gridView,CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}
	
	public void addMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				ItsLinerAddUi editUi = new ItsLinerAddUi(this.getShell(),gridView,CommFinal.OPER_TYPE_ADD);
				editUi.open();
				break;
			}
		}
	}
	
	public void copyMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					ItsLinerAddUi editUi = new ItsLinerAddUi(this.getShell(),gridView,CommFinal.OPER_TYPE_COPY);
					editUi.open();
					break;
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}
	
	public void reportMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					reportUi.setVisible(true);
					panel.setVisible(false);
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}
	
	public void mergeMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					ItsLinerMergeUi mergeUi = new ItsLinerMergeUi(this.getShell(),gridView,CommFinal.OPER_TYPE_UPDATE);
					mergeUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}
	
	public void dealMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					ItsLinerDealUi dealUi = new ItsLinerDealUi(this.getShell(),gridView,CommFinal.OPER_TYPE_UPDATE);
					dealUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}
	
	public void serviceMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					ItsLinerServiceUi editUi = new ItsLinerServiceUi(this.getShell(),gridView,CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择车次。");
				}
				break;
			}
		}
	}
	
	private MouseListener mouseListener_btnQuery = new MouseListener() {
		
		@Override
		public void mouseUp(MouseEvent arg0) {
			// TODO Auto-generated method stub
			queryMethod();
		}
		
		@Override
		public void mouseDown(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseDoubleClick(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private SelectionListener selectionListener_queryMode =new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			dropRoute.droptext.setValue("");
			dropStation.droptext.setValue("");
			txtLinerId.setText("");
			dropCargrade.droptext.setValue("");
			cboQueryValue.clearSelection();
			cboQueryValue.removeAll();
			if (cboQueryMode.getText().equals("线路名称")){
				dropRoute.setVisible(true);
				dropStation.setVisible(false);
				txtLinerId.setVisible(false);
				dropCargrade.setVisible(false);
				cboQueryValue.setVisible(false);
			}else if (cboQueryMode.getText().equals("站点名称")){
				dropRoute.setVisible(false);
				dropStation.setVisible(true);
				txtLinerId.setVisible(false);
				dropCargrade.setVisible(false);
				cboQueryValue.setVisible(false);
			}else if (cboQueryMode.getText().equals("车次号")){
				dropRoute.setVisible(false);
				dropStation.setVisible(false);
				txtLinerId.setVisible(true);
				dropCargrade.setVisible(false);
				cboQueryValue.setVisible(false);
			}else if (cboQueryMode.getText().equals("车次类型")){
				dropRoute.setVisible(false);
				dropStation.setVisible(false);
				txtLinerId.setVisible(false);
				dropCargrade.setVisible(true);
				cboQueryValue.setVisible(false);
			}else{
				dropRoute.setVisible(false);
				dropStation.setVisible(false);
				txtLinerId.setVisible(false);
				dropCargrade.setVisible(false);
				cboQueryValue.setVisible(true);
				if (cboQueryMode.getText().equals("报班状态")){
					cboQueryValue.setItems(CommFinal.getAllName(TraffFinal.ARR_REPORT_STATUS));
				}else{
					cboQueryValue.setItems(new String[]{"销售","停班","撤班","废班","打单","出站"});
				}
			}
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	KeyListener keyListener_queryValue = new KeyListener() {
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			try {
				System.out.println(arg0.keyCode);
				if(arg0.keyCode==16777221){
					if(DateUtils.nDaysBetweenTwoDate(dStartDate.getText(), dLimitDate.getText())<0){
						dLimitDate.setText(dStartDate.getText());
					}
					dStartDate.downDate();
					dLimitDate.downDate();
					queryMethod();
				}else if (arg0.keyCode==16777222){
					if(DateUtils.nDaysBetweenTwoDate(dStartDate.getText(), dLimitDate.getText())<0){
						dLimitDate.setText(dStartDate.getText());
					}
					dStartDate.upDate();
					dLimitDate.upDate();
					queryMethod();
				}else if (arg0.keyCode == 13 || arg0.keyCode==SWT.KEYPAD_CR){
				
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	FocusListener focusListener_dStartDate = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			try {
				if(DateUtils.nDaysBetweenTwoDate(dStartDate.getText(), dLimitDate.getText())<0){
					dLimitDate.setText(dStartDate.getText());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
		}
	};
	
	FocusListener focusListener_dLimitDate = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			try {
				if(DateUtils.nDaysBetweenTwoDate(dStartDate.getText(), dLimitDate.getText())<0){
					dStartDate.setText(dLimitDate.getText());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
		}
	};
	
	SelectionListener selectionListener_btnLimit = new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			if (btnLimit.getSelection()){
				dLimitDate.setEnabled(true);
			}else{
				dLimitDate.setEnabled(false);
			}
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
}
