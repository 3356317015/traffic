package com.zhima.traffic.ui.epd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.drop.DropService;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamService;
import com.zhima.traffic.action.epd.IEpdRoute;
import com.zhima.traffic.action.epd.IEpdRouteService;
import com.zhima.traffic.action.epd.IEpdRouteStation;
import com.zhima.traffic.action.epd.impl.ImpEpdRoute;
import com.zhima.traffic.action.epd.impl.ImpEpdRouteService;
import com.zhima.traffic.action.epd.impl.ImpEpdRouteStation;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropRouteType;
import com.zhima.traffic.drop.DropStation;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.traffic.model.EpdRouteservice;
import com.zhima.traffic.model.EpdRoutestation;
import com.zhima.traffic.model.EpdStation;
import com.zhima.util.DateUtils;
import com.zhima.util.PinyinUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class EpdRouteEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Object obj;
	
	private Text txtRouteName;
	private CCombo cboArriveType;
	private Text txtRouteCode;
	private Text txtRouteSpell;
	private DropRouteType dropRouteType;
	private CCombo cboRoadType;
	private CCombo cboRouteStatus;
	private Text txtRemark;
	
	private CTabFolder tabFolder;
	
	private DropStation dropStation;
	private GridView gridRouteStation;
	
	private DropService dropService;
	private GridView gridRouteService;
	
	private List<EpdRoutestation> delRoutestations = new ArrayList<EpdRoutestation>();

	protected EpdRouteEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.obj = this;
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("线路设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(515,455);
    }
	
	protected void createButtonsForButtonBar(Composite parent){
		GridData btnData = new GridData(StyleFinal.DIALOGBAR_ALIGNMENT);
		parent.setLayoutData(btnData);
		Button confirm = createButton(parent, 1, "确定(&O)", false);
		confirm.setFont(StyleFinal.SYS_FONT);
		Button cancel = createButton(parent, 0, "取消(&C)", false);
		cancel.setFont(StyleFinal.SYS_FONT);
		if (StyleFinal.BTN_SHOWIMAGE == true){
			confirm.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_OK));
			cancel.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_CANCEL));
		}
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite compMain = (Composite) super.createDialogArea(parent);
		compMain.setLayout(new FormLayout());
		CallMethod callMethod = new CallMethod();
		
		Group groupMain = new Group(compMain,SWT.NONE);
		groupMain.setText("线路信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		groupMain.setLayout(new FormLayout());
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		groupMain.setLayoutData(data);
		
		Composite compRoute = new Composite(groupMain, SWT.NONE);
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		compRoute.setLayout(gridLayout);
		
		data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		compRoute.setLayoutData(data);
		
		KLabel lbRouteName = new KLabel(compRoute, SWT.RIGHT);
		lbRouteName.setFont(StyleFinal.SYS_FONT);
		lbRouteName.setText("线路名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteName.setLayoutData(gridData);
		
		txtRouteName = new Text(compRoute, SWT.BORDER);
		txtRouteName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteName.setLayoutData(gridData);
		
		KLabel lbArriveType = new KLabel(compRoute, SWT.RIGHT);
		lbArriveType.setFont(StyleFinal.SYS_FONT);
		lbArriveType.setText("到达方式:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbArriveType.setLayoutData(gridData);
		
		cboArriveType = new CCombo(compRoute, SWT.BORDER|SWT.READ_ONLY);
		cboArriveType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboArriveType.setLayoutData(gridData);
		cboArriveType.setItems(CommFinal.getAllName(TraffFinal.ARR_ARRIVE_TYPE));
		
		KLabel lbRouteSpell = new KLabel(compRoute, SWT.RIGHT);
		lbRouteSpell.setFont(StyleFinal.SYS_FONT);
		lbRouteSpell.setText("拼音代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteSpell.setLayoutData(gridData);
		
		txtRouteSpell = new Text(compRoute, SWT.BORDER);
		txtRouteSpell.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteSpell.setLayoutData(gridData);
		
		KLabel lbRouteCode = new KLabel(compRoute, SWT.RIGHT);
		lbRouteCode.setFont(StyleFinal.SYS_FONT);
		lbRouteCode.setText("线路代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteCode.setLayoutData(gridData);
		
		txtRouteCode = new Text(compRoute, SWT.BORDER);
		txtRouteCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteCode.setLayoutData(gridData);
		
		KLabel lbRouteType = new KLabel(compRoute, SWT.RIGHT);
		lbRouteType.setFont(StyleFinal.SYS_FONT);
		lbRouteType.setText("线路类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteType.setLayoutData(gridData);
		
		dropRouteType = new DropRouteType(compRoute, SWT.BORDER);
		dropRouteType.droptext.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 110;
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropRouteType.setLayoutData(gridData);

		KLabel lbRoadType = new KLabel(compRoute, SWT.RIGHT);
		lbRoadType.setFont(StyleFinal.SYS_FONT);
		lbRoadType.setText("道路类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRoadType.setLayoutData(gridData);
		
		cboRoadType = new CCombo(compRoute, SWT.BORDER|SWT.READ_ONLY);
		cboRoadType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboRoadType.setLayoutData(gridData);
		cboRoadType.setItems(CommFinal.getAllName(TraffFinal.ARR_ROAD_TYPE));
		
		KLabel lbRouteStatus = new KLabel(compRoute, SWT.RIGHT);
		lbRouteStatus.setFont(StyleFinal.SYS_FONT);
		lbRouteStatus.setText("线路状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteStatus.setLayoutData(gridData);
		
		cboRouteStatus = new CCombo(compRoute, SWT.BORDER|SWT.READ_ONLY);
		cboRouteStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboRouteStatus.setLayoutData(gridData);
		cboRouteStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_ROUTE_STATUS));
		
		Label lbRemark = new Label(compRoute, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(compRoute, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		tabFolder = new CTabFolder(compMain, SWT.BORDER);
		tabFolder.setFont(StyleFinal.SYS_FONT);
		tabFolder.setBorderVisible(true);
		tabFolder.setSimple(false);
		data = new FormData();
		data.top = new FormAttachment(groupMain,0);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100,-5);
		tabFolder.setLayoutData(data);
		tabFolder.setSelectionForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		tabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		CTabItem tabPlan = new CTabItem(tabFolder, SWT.BORDER);
		tabPlan.setText("到达站信息");
		tabPlan.setControl(createStation(tabFolder));
		
		CTabItem tabCheck = new CTabItem(tabFolder, SWT.BORDER);
		tabCheck.setText("乘车点信息");
		tabCheck.setControl(createService(tabFolder));
		
		tabFolder.setSelection(0);

		initData();
		txtRouteName.forceFocus();
		txtRouteName.selectAll();
		
		callMethod.bindEnterEvent(this, txtRouteName, cboArriveType, "setSpell");
		callMethod.bindEnterEvent(this, cboArriveType, txtRouteSpell, "");
		callMethod.bindEnterEvent(this, txtRouteSpell, txtRouteCode, "");
		callMethod.bindEnterEvent(this, txtRouteCode, dropRouteType.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropRouteType.droptext.txtShow, cboRoadType, "");
		callMethod.bindEnterEvent(this, cboRoadType, cboRouteStatus, "");
		callMethod.bindEnterEvent(this, cboRouteStatus, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, dropStation.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropStation.droptext.txtShow, null, "addStation");
		
		callMethod.bindEnterEvent(this, dropService.droptext.txtShow, null, "addService");
		
		return compMain;
	}
	
	private Control createStation(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder,SWT.NONE);
		composite.setFont(StyleFinal.SYS_FONT);
		composite.setLayout(new FormLayout());
		
		Label lbStation = new Label(composite, SWT.RIGHT);
		lbStation.setFont(StyleFinal.SYS_FONT);
		lbStation.setText("途经站:");
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 25);
		formData.top = new FormAttachment(0, 8);
		lbStation.setLayoutData(formData);
		
		dropStation = new DropStation(composite, SWT.BORDER);
		dropStation.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0, 5);
		formData.left = new FormAttachment(lbStation, 5);
		formData.width = 150;
		dropStation.setLayoutData(formData);
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("站点名称", "stationName",120));
		columns.add(new GridColumn("里程", "stationMileage",90));
		columns.add(new GridColumn("行政区域", "regionName",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		
		gridConfig.setRightBos(initRight());
		gridConfig.setObj(obj);
		gridRouteStation = new GridView(composite, SWT.BORDER);
		gridRouteStation.CreateTabel(gridConfig);
		formData = new FormData();
		formData.top = new FormAttachment(lbStation,10);
		formData.left = new FormAttachment(0,5);
		formData.bottom = new FormAttachment(100,-5);
		formData.right = new FormAttachment(100,-5);
		gridRouteStation.setLayoutData(formData);
		return composite;
	}
	
	private Control createService(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder,SWT.NONE);
		composite.setFont(StyleFinal.SYS_FONT);
		composite.setLayout(new FormLayout());
		
		Label lbService = new Label(composite, SWT.RIGHT);
		lbService.setFont(StyleFinal.SYS_FONT);
		lbService.setText("乘车点:");
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 25);
		formData.top = new FormAttachment(0, 8);
		lbService.setLayoutData(formData);
		
		dropService = new DropService(composite, SWT.BORDER);
		dropService.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0, 5);
		formData.left = new FormAttachment(lbService, 5);
		formData.width = 150;
		dropService.setLayoutData(formData);
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("乘车点代码", "serviceCode",150));
		columns.add(new GridColumn("乘车点名称", "serviceName",150));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		
		gridConfig.setRightBos(initService());
		gridConfig.setObj(obj);
		gridRouteService = new GridView(composite, SWT.BORDER);
		gridRouteService.CreateTabel(gridConfig);
		formData = new FormData();
		formData.top = new FormAttachment(lbService,10);
		formData.left = new FormAttachment(0,5);
		formData.bottom = new FormAttachment(100,-5);
		formData.right = new FormAttachment(100,-5);
		gridRouteService.setLayoutData(formData);
		return composite;
	}
	
	
	private List<SamModuleRight> initRight() {
		List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
		SamModuleRight moduleRight = new SamModuleRight();
		moduleRight.setRightName("删除(&R)");
		moduleRight.setRightMethod("deleteRouteStation");
		rights.add(moduleRight);
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("上移(&U)");
		moduleRight.setRightMethod("upRouteStation");
		rights.add(moduleRight);
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("下移(&D)");
		moduleRight.setRightMethod("downRouteStation");
		rights.add(moduleRight);
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("发布(&F)");
		moduleRight.setRightMethod("releaseStation");
		rights.add(moduleRight);
		return rights;
	}
	
	private List<SamModuleRight> initService() {
		List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
		SamModuleRight moduleRight = new SamModuleRight();
		moduleRight.setRightName("删除(&R)");
		moduleRight.setRightMethod("deleteRouteService");
		rights.add(moduleRight);
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("上移(&U)");
		moduleRight.setRightMethod("upRouteService");
		rights.add(moduleRight);
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("下移(&D)");
		moduleRight.setRightMethod("downRouteService");
		rights.add(moduleRight);
		return rights;
	}

	@SuppressWarnings("unchecked")
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			try {
				EpdRoute epdRoute = (EpdRoute) gridView.getSelection();
				dropService.initType("1");
				if (null != epdRoute && !"".equals(epdRoute.getRouteSeq())){
					txtRouteName.setText(epdRoute.getRouteName());
					cboArriveType.setText(CommFinal.getItemName(TraffFinal.ARR_ARRIVE_TYPE, epdRoute.getArriveType()));
					txtRouteSpell.setText(epdRoute.getRouteSpell());
					txtRouteCode.setText(epdRoute.getRouteCode());
					dropRouteType.droptext.setValue(epdRoute.getRoutetypeSeq());
					cboRoadType.setText(CommFinal.getItemName(TraffFinal.ARR_ROAD_TYPE, epdRoute.getRoadType()));
					cboRouteStatus.setText(CommFinal.getItemName(TraffFinal.ARR_ROUTE_STATUS, epdRoute.getRouteStatus()));
					txtRemark.setText(epdRoute.getRemark());
					IEpdRouteStation iEpdRouteStation = new ImpEpdRouteStation();
					List<EpdRoutestation> routestations = iEpdRouteStation.queryByRouteSeq(epdRoute.getRouteSeq());
					gridRouteStation.setDataList(routestations);
					if (null != routestations && routestations.size()>=0){
						for (int i = 0; i < routestations.size(); i++) {
							if ("1".equals(routestations.get(i).getIfRelease())){
								gridRouteStation.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_RED));
								break;
							}
						}
					}
					
					IEpdRouteService iEpdRouteService = new ImpEpdRouteService();
					List<EpdRouteservice> routeservices = iEpdRouteService.queryByRouteSeq(epdRoute.getRouteSeq());
					List<SamService> samServices = (List<SamService>) dropService.droptext.getDataList();
					if (null != routeservices && routeservices.size()>0){
						for (int i = 0; i < routeservices.size(); i++) {
							for (int j = 0; j < samServices.size(); j++) {
								if (routeservices.get(i).getServiceSeq().equals(samServices.get(j).getServiceSeq())){
									gridRouteService.addRow(samServices.get(j));
									break;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				List<EpdRoutestation> routestations = (List<EpdRoutestation>) gridRouteStation.getDataList();
				EpdRoute epdRoute = validData(routestations);
				if (null != epdRoute){
					IEpdRoute iEpdRoute = new ImpEpdRoute();
					
					epdRoute.setUsingService(0);
					List<EpdRouteservice> routeservices = new ArrayList<EpdRouteservice>();
					List<SamService> samServices = (List<SamService>) gridRouteService.getDataList();
					if (null != samServices && samServices.size()>=0){
						epdRoute.setUsingService(1);
						for (int i = 0; i < samServices.size(); i++) {
							EpdRouteservice epdRouteservice = new EpdRouteservice();
							epdRouteservice.setServiceSeq(samServices.get(i).getServiceSeq());
							epdRouteservice.setServiceOrder(i+1);
							epdRouteservice.setUpdateTime(epdRoute.getUpdateTime());
							routeservices.add(epdRouteservice);
						}
					}
					
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdRoute newEpdRoute = iEpdRoute.insert(epdRoute,routestations,routeservices,CommFinal.initConfig());
						gridView.addRow(newEpdRoute);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						String stationName = "";
						for (int i = 0; i < routestations.size(); i++) {
							if ("".equals(stationName)){
								stationName = routestations.get(i).getStationName();
							}else{
								stationName += "," + routestations.get(i).getStationName();
							}
						}
						epdRoute.setStationName(stationName);
						iEpdRoute.update(epdRoute,routestations,delRoutestations,routeservices,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdRoute);
						close();
					}
				}
			} else if (0 == buttonId) {
				/**
				 * 取消
				 */
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		
	}
	
	@SuppressWarnings({ "null" })
	private EpdRoute validData(List<EpdRoutestation> routestations){
		EpdRoute epdRoute = new EpdRoute();
		epdRoute.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdRoute = (EpdRoute) gridView.getSelection();
		}
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		epdRoute.setUpdateTime(currTime);
		if (null != txtRouteName.getText() && txtRouteName.getText().length()>0){
			epdRoute.setRouteName(txtRouteName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入线路名称！");
			txtRouteName.forceFocus();
			return null;
		}
		
		if (null != cboArriveType.getText() && cboArriveType.getText().length()>0){
			epdRoute.setArriveType(CommFinal.getItemValue(TraffFinal.ARR_ARRIVE_TYPE, cboArriveType.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择到达方式！");
			cboArriveType.forceFocus();
			return null;
		}
		
		if (null != txtRouteSpell.getText() && txtRouteSpell.getText().length()>0){
			epdRoute.setRouteSpell(txtRouteSpell.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入线路拼音代码！");
			txtRouteSpell.forceFocus();
			return null;
		}
		
		if (null != txtRouteCode.getText() && txtRouteCode.getText().length()>0){
			epdRoute.setRouteCode(txtRouteCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入线路代码！");
			txtRouteCode.forceFocus();
			return null;
		}

		if (null != dropRouteType.droptext.getValue() && dropRouteType.droptext.getValue().length()>0){
			epdRoute.setRoutetypeSeq(dropRouteType.droptext.getValue());
		}else{
			MsgBox.warning(getParentShell(),"请设置线路类型！");
			dropRouteType.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != cboRoadType.getText() && cboRoadType.getText().length()>0){
			epdRoute.setRoadType(CommFinal.getItemValue(TraffFinal.ARR_ROAD_TYPE, cboRoadType.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择道路类型！");
			cboRoadType.forceFocus();
			return null;
		}
		
		if (null != cboRouteStatus.getText() && cboRouteStatus.getText().length()>0){
			epdRoute.setRouteStatus(CommFinal.getItemValue(TraffFinal.ARR_ROUTE_STATUS, cboRouteStatus.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择线路状态！");
			cboRouteStatus.forceFocus();
			return null;
		}
		if (null == routestations && routestations.size()<=0){
			MsgBox.warning(getParentShell(),"请添加线路途经站！");
			dropStation.droptext.txtShow.forceFocus();
			dropStation.droptext.txtShow.selectAll();
			return null;
		}

		epdRoute.setRemark(txtRemark.getText());
		
		return epdRoute;
	}
	
	private void clearData(){
		try {
			txtRouteName.setText("");
			cboArriveType.setText("");
			txtRouteSpell.setText("");
			txtRouteCode.setText("");
			dropRouteType.droptext.setValue("");
			cboRoadType.setText("");
			cboRouteStatus.setText("");
			txtRemark.setText("");
			gridRouteStation.removeAll();
			txtRouteName.forceFocus();
			delRoutestations = new ArrayList<EpdRoutestation>();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setSpell(){
		PinyinUtil pinyinUtil = new PinyinUtil();  
		txtRouteSpell.setText(pinyinUtil.String2Alpha(txtRouteName.getText().toLowerCase()));
	}
	
	@SuppressWarnings("unchecked")
	public void addStation(){
		if (null != dropStation.droptext.getValue() && dropStation.droptext.getValue().length()>0){
			try {
				List<EpdRoutestation> routestations = (List<EpdRoutestation>) gridRouteStation.getDataList();
				if (null != routestations && routestations.size()>0){
					for (int i = 0; i < routestations.size(); i++) {
						if (dropStation.droptext.getValue().equals(routestations.get(i).getStationSeq())){
							MsgBox.warning(getShell(), "途经站已存在，不允许重复添加。");
							return;
						}
					}
				}
				if (null != delRoutestations && routestations.size()>0){
					for (int i = 0; i < delRoutestations.size(); i++) {
						if (dropStation.droptext.getValue().equals(delRoutestations.get(i).getStationSeq())){
							gridRouteStation.addRow(delRoutestations.get(i));
							delRoutestations.remove(i);
							return;
						}
					}
				}
				EpdStation station = (EpdStation) dropStation.droptext.getObject();
				EpdRoutestation epdRoutestation = new EpdRoutestation();
				epdRoutestation.setStationSeq(station.getStationSeq());
				epdRoutestation.setStationName(station.getStationName());
				epdRoutestation.setIfRelease("0");
				epdRoutestation.setStationMileage(station.getStationMileage());
				epdRoutestation.setRegionName(station.getRegionName());
				
				gridRouteStation.addRow(epdRoutestation);
			} catch (Exception e) {
				LogUtil.operLog(e,"E",true,true);
			}
		}
		dropStation.droptext.txtShow.forceFocus();
		dropStation.droptext.txtShow.selectAll();
		
	}
	
	@SuppressWarnings("unchecked")
	public void addService(){
		if (null != dropService.droptext.getValue() && dropService.droptext.getValue().length()>0){
			try {
				List<SamService> samServices = (List<SamService>) gridRouteService.getDataList();
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						if (dropService.droptext.getValue().equals(samServices.get(i).getServiceSeq())){
							MsgBox.warning(getShell(), "乘车点已存在，不允许重复添加。");
							return;
						}
					}
				}
				
				SamService service = (SamService) dropService.droptext.getObject();

				
				gridRouteService.addRow(service);
			} catch (Exception e) {
				LogUtil.operLog(e,"E",true,true);
			}
		}
		dropService.droptext.txtShow.forceFocus();
		dropService.droptext.txtShow.selectAll();
	}
	
	
	public void upRouteStation(){
		gridRouteStation.upTableRow();
	}
	
	public void downRouteStation(){
		gridRouteStation.downTableRow();
	}
	
	@SuppressWarnings("unchecked")
	public void deleteRouteStation(){
		int index[] = gridRouteStation.getSelectionIndexs();
		if (null != index && index.length>0){
			List<EpdRoutestation> routestations = (List<EpdRoutestation>) gridRouteStation.getSelections();
			for (int i = 0; i < routestations.size(); i++) {
				if (null != routestations.get(i).getRoutestationSeq()
						&& routestations.get(i).getRoutestationSeq().length()>0){
					delRoutestations.add(routestations.get(i));
				}
			}
			gridRouteStation.deleteRow(index);
		}
	}
	
	public void upRouteService(){
		gridRouteService.upTableRow();
	}
	
	public void downRouteService(){
		gridRouteService.downTableRow();
	}
	
	public void deleteRouteService(){
		int index[] = gridRouteService.getSelectionIndexs();
		if (null != index && index.length>0){
			gridRouteService.deleteRow(index);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void releaseStation(){
		List<EpdRoutestation> routestations = (List<EpdRoutestation>) gridRouteStation.getDataList();
		if (null != routestations && routestations.size()>0){
			for (int i = 0; i < routestations.size(); i++) {
				EpdRoutestation routestation = routestations.get(i);
				routestation.setIfRelease("0");
				gridRouteStation.setRowFontColor(i,
						SWTResourceManager.getColor(SWT.COLOR_BLACK));
			}
		}
		if (gridRouteStation.getSelectionIndex()>=0){
			EpdRoutestation routestation = (EpdRoutestation) gridRouteStation.getSelection();
			routestation.setIfRelease("1");
			gridRouteStation.setRowFontColor(gridRouteStation.getSelectionIndex(),
					SWTResourceManager.getColor(SWT.COLOR_RED));
		}
	}
}
