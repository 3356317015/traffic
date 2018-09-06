package com.zhima.traffic.ui.operate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.nebula.widgets.formattedtext.DateTimeFormatter;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.ITextFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
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
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamOrganize;
import com.zhima.frame.action.sam.impl.ImpSamOrganize;
import com.zhima.frame.drop.DropService;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamOrganize;
import com.zhima.frame.model.SamService;
import com.zhima.traffic.action.epd.IEpdCheckgate;
import com.zhima.traffic.action.epd.IEpdFare;
import com.zhima.traffic.action.epd.IEpdRouteStation;
import com.zhima.traffic.action.epd.impl.ImpEpdCheckgate;
import com.zhima.traffic.action.epd.impl.ImpEpdFare;
import com.zhima.traffic.action.epd.impl.ImpEpdRouteStation;
import com.zhima.traffic.action.operate.IItsLiner;
import com.zhima.traffic.action.operate.IItsLinercheck;
import com.zhima.traffic.action.operate.IItsLinerdeal;
import com.zhima.traffic.action.operate.impl.ImpItsLiner;
import com.zhima.traffic.action.operate.impl.ImpItsLinercheck;
import com.zhima.traffic.action.operate.impl.ImpItsLinerdeal;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropCargrade;
import com.zhima.traffic.drop.DropParking;
import com.zhima.traffic.drop.DropRoute;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdFare;
import com.zhima.traffic.model.EpdParking;
import com.zhima.traffic.model.EpdRoutestation;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;
import com.zhima.traffic.model.ItsLinerdeal;
import com.zhima.traffic.model.ItsLinerfare;
import com.zhima.traffic.model.ItsLinerseat;
import com.zhima.traffic.model.ItsLinerservice;
import com.zhima.traffic.model.ItsLinerstation;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.Validate;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CCalendar;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.EditorOption;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;
import com.zhima.widget.seatBar.Seat;
import com.zhima.widget.seatBar.SeatBar;
import com.zhima.widget.stationBar.AddFareBar;
import com.zhima.widget.stationBar.Station;

public class ItsLinerAddUi extends Dialog {
	private GridView gridView;
	private String operType;
	private Object obj;

	private CTabFolder tabFolder;

	private DropRoute dropRoute;
	private DropCargrade dropCargrade;
	private CTabFolder tabRoute;
	private AddFareBar addFareBar;
	private CCombo cboLinerType;
	private CCalendar dLinerDate;
	private Text txtLinerId;
	private Text txtLinerTime;
	private CCombo cboLinerStatus;
	private Text txtSeatNum;
	private Text txtHalfNum;
	private Text txtFreeNum;

	private Button btnIfPrintseat;
	private CCombo cboIfNetsale;
	private Text txtRemark;
	
	private DropService dropService;
	private GridView gridService;
	
	private GridView gridCheckgate;
	private DropParking dropParking;
	private Text txtParkingName;
	
	private CCombo cboIfDeal;
	private CCombo cboIfMain;
	private GridView gridOrganize;
	private GridView gridDeal;
	
	private SeatBar seatBar;

	List<Seat> seats = new ArrayList<Seat>();
	
	private EpdCheckgate checkgate = null;
	
	protected ItsLinerAddUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.obj = this;
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("加班设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(745,555);
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
	
	/**
	 * 框体信息
	 * */
	protected Control createDialogArea(Composite parent){
		Composite compMain = (Composite) super.createDialogArea(parent);
		compMain.setLayout(new FormLayout());
		
		tabFolder = new CTabFolder(compMain, SWT.BORDER);
		tabFolder.setFont(StyleFinal.SYS_FONT);
		tabFolder.setBorderVisible(true);
		tabFolder.setSimple(false);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100,-10);
		tabFolder.setLayoutData(data);
		tabFolder.setSelectionForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		tabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		CTabItem tabPlan = new CTabItem(tabFolder, SWT.BORDER);
		tabPlan.setText("班次信息");
		tabPlan.setControl(createLiner(tabFolder));
		
		CTabItem tabCheck = new CTabItem(tabFolder, SWT.BORDER);
		tabCheck.setText("检票口");
		tabCheck.setControl(createCheck(tabFolder));
		
		CTabItem tabSeat = new CTabItem(tabFolder, SWT.BORDER);
		tabSeat.setText("座位信息");
		tabSeat.setControl(createSeat(tabFolder));
		
		CTabItem tabDeal = new CTabItem(tabFolder, SWT.BORDER);
		tabDeal.setText("配载信息");
		tabDeal.setControl(createDeal(tabFolder));

		initData();
		return compMain;
	}

	private Control createLiner(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder,SWT.NONE);
		composite.setFont(StyleFinal.SYS_FONT);
		composite.setLayout(new FormLayout());
		
		Composite compLiner = new Composite(composite, SWT.NONE);
		GridLayout gridLayout = new GridLayout(6,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		compLiner.setLayout(gridLayout);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		compLiner.setLayoutData(formData);

		KLabel lbRoute = new KLabel(compLiner, SWT.RIGHT);
		lbRoute.setFont(StyleFinal.SYS_FONT);
		lbRoute.setText("线路名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRoute.setLayoutData(gridData);
		
		dropRoute = new DropRoute(compLiner, SWT.BORDER);
		dropRoute.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropRoute.setLayoutData(gridData);
		dropRoute.droptext.txtValue.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				dropCargrade.initByRouteSeq(dropRoute.droptext.getValue());
			}
		});
		
		KLabel lbCargrade = new KLabel(compLiner, SWT.NONE);
		lbCargrade.setFont(StyleFinal.SYS_FONT);
		lbCargrade.setText("车型名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCargrade.setLayoutData(gridData);
		
		dropCargrade = new DropCargrade(compLiner, SWT.BORDER);
		dropCargrade.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropCargrade.setLayoutData(gridData);
		dropCargrade.droptext.txtValue.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO Auto-generated method stub
				setStations();
			}
		});
		
		KLabel lbPlanType = new KLabel(compLiner, SWT.RIGHT);
		lbPlanType.setFont(StyleFinal.SYS_FONT);
		lbPlanType.setText("班次类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlanType.setLayoutData(gridData);
		
		cboLinerType = new CCombo(compLiner, SWT.BORDER|SWT.READ_ONLY);
		cboLinerType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboLinerType.setLayoutData(gridData);
		cboLinerType.setVisibleItemCount(10);
		
		KLabel lbLinerDate = new KLabel(compLiner, SWT.NONE);
		lbLinerDate.setText("班次日期:");
		lbLinerDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerDate.setLayoutData(gridData);
		
		dLinerDate = new CCalendar(compLiner, SWT.BORDER);
		dLinerDate.txtDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dLinerDate.setLayoutData(gridData);
		
		KLabel lbPlanId = new KLabel(compLiner, SWT.RIGHT);
		lbPlanId.setFont(StyleFinal.SYS_FONT);
		lbPlanId.setText("班次号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlanId.setLayoutData(gridData);
		
		txtLinerId = new Text(compLiner, SWT.BORDER);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(gridData);
		
		KLabel lbPlanTime = new KLabel(compLiner, SWT.RIGHT);
		lbPlanTime.setFont(StyleFinal.SYS_FONT);
		lbPlanTime.setText("发车时间:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlanTime.setLayoutData(gridData);
		
		FormattedText formattedLinerTime = new FormattedText(compLiner, SWT.BORDER);
		ITextFormatter formatterLinerTime = new DateTimeFormatter("HH:mm");
		formattedLinerTime.setFormatter(formatterLinerTime);
		txtLinerTime = formattedLinerTime.getControl();
		txtLinerTime.setFont(StyleFinal.SYS_FONT);
		SimpleDateFormat formatLinerTime = new SimpleDateFormat("HH:mm");
		Calendar calendarLinerTime = Calendar.getInstance();
		txtLinerTime.setText(formatLinerTime.format(calendarLinerTime.getTime()));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerTime.setLayoutData(gridData);
		
		KLabel lbPlanState = new KLabel(compLiner, SWT.RIGHT);
		lbPlanState.setFont(StyleFinal.SYS_FONT);
		lbPlanState.setText("班次状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlanState.setLayoutData(gridData);
		
		cboLinerStatus = new CCombo(compLiner, SWT.BORDER|SWT.READ_ONLY);
		cboLinerStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboLinerStatus.setLayoutData(gridData);
		cboLinerStatus.setVisibleItemCount(10);
		
		KLabel lbNetsale = new KLabel(compLiner, SWT.RIGHT);
		lbNetsale.setFont(StyleFinal.SYS_FONT);
		lbNetsale.setText("允许网售:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbNetsale.setLayoutData(gridData);
		
		cboIfNetsale = new CCombo(compLiner, SWT.BORDER|SWT.READ_ONLY);
		cboIfNetsale.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboIfNetsale.setLayoutData(gridData);
		
		Label label = new Label(compLiner, SWT.RIGHT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.horizontalSpan=2;
		label.setLayoutData(gridData);
		
		Label lbRemark = new Label(compLiner, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(compLiner, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		gridData.horizontalSpan=5;
		txtRemark.setLayoutData(gridData);
		
		tabRoute = new CTabFolder(composite, SWT.BORDER);
		tabRoute.setFont(StyleFinal.SYS_FONT);
		tabRoute.setBorderVisible(true);
		tabRoute.setSimple(false);
		formData = new FormData();
		formData.top = new FormAttachment(compLiner, 2);
		formData.left = new FormAttachment(0,5);
		formData.right = new FormAttachment(100,-5);
		formData.bottom = new FormAttachment(100,-5);
		tabRoute.setLayoutData(formData);
		tabRoute.setSelectionForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		tabRoute.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		CTabItem tabPlan = new CTabItem(tabRoute, SWT.BORDER);
		tabPlan.setText("到达站信息");
		tabPlan.setControl(createStation(tabRoute));
		
		CTabItem tabCheck = new CTabItem(tabRoute, SWT.BORDER);
		tabCheck.setText("乘车点信息");
		tabCheck.setControl(createService(tabRoute));
		
		tabRoute.setSelection(0);

		

		return composite;
	}
	
	private Control createStation(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder,SWT.NONE);
		composite.setFont(StyleFinal.SYS_FONT);
		composite.setLayout(new FillLayout());
		addFareBar = new AddFareBar(composite, null, 30, SWT.BORDER);
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
		columns.add(new GridColumn("启用乘车点", "ifUsing", TraffFinal.ARR_IF_USING,150));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		
		gridConfig.setRightBos(initService());
		gridConfig.setObj(obj);
		gridService = new GridView(composite, SWT.BORDER);
		gridService.CreateTabel(gridConfig);
		formData = new FormData();
		formData.top = new FormAttachment(lbService,10);
		formData.left = new FormAttachment(0,5);
		formData.bottom = new FormAttachment(100,-5);
		formData.right = new FormAttachment(100,-5);
		gridService.setLayoutData(formData);
		return composite;
	}
	
	private Control createCheck(CTabFolder tabFolder2) {
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new FormLayout());
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("检票口代码","checkCode",215));
		columns.add(new GridColumn("检票口名称","checkName",200));
		columns.add(new GridColumn("默认检票口","defaultCheck",TraffFinal.ARR_DEFAULT_CHECK,200));
		/*EditorOption editorOption = new EditorOption();
		editorOption.setVerify(true);
		columns.add(new GridColumn("发车位","gateName",280, "Text",editorOption));*/
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowSeq(false);
		gridConfig.setCheck(true);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(initRight());
		gridConfig.setObj(obj);
		gridCheckgate = new GridView(composite, SWT.BORDER);
		gridCheckgate.CreateTabel(gridConfig);
		FormData formData = new FormData();
		formData.left= new FormAttachment(0);
		formData.top= new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100, -35);
		gridCheckgate.setLayoutData(formData);
		gridCheckgate.table.addSelectionListener(new SelectionListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				List<EpdCheckgate> checkgates = (List<EpdCheckgate>) gridCheckgate.getCheckSelections();
				if (null != checkgates && checkgates.size()>0){
					dropParking.initByCheckCode(checkgates);
				}else{
					dropParking.initByCheckCode(null);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Label lbParking = new Label(composite, SWT.NONE);
		lbParking.setText("发车位代码:");
		lbParking.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left= new FormAttachment(0);
		formData.top= new FormAttachment(gridCheckgate,10);
		lbParking.setLayoutData(formData);
		
		dropParking = new DropParking(composite, SWT.BORDER);
		dropParking.droptext.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left= new FormAttachment(lbParking,5);
		formData.top= new FormAttachment(lbParking,-2,SWT.TOP);
		formData.right = new FormAttachment(50);
		formData.height = StyleFinal.DROP_HEIGHT;
		dropParking.setLayoutData(formData);
		dropParking.droptext.txtShow.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO Auto-generated method stub
				if (null != dropParking.droptext.getValue() && dropParking.droptext.getValue().length()>0){
					EpdParking epdParking = (EpdParking) dropParking.droptext.getObj();
					txtParkingName.setText(epdParking.getParkingName());
				}
			}
		});
		
		Label lbParkingName = new Label(composite, SWT.NONE);
		lbParkingName.setText("发车位名称:");
		lbParkingName.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left= new FormAttachment(dropParking,10);
		formData.top= new FormAttachment(lbParking,0,SWT.TOP);
		lbParkingName.setLayoutData(formData);
		
		txtParkingName = new Text(composite, SWT.BORDER|SWT.READ_ONLY);
		txtParkingName.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left= new FormAttachment(lbParkingName,5);
		formData.top= new FormAttachment(dropParking,0,SWT.TOP);
		formData.right = new FormAttachment(100,-1);
		formData.height = StyleFinal.TEXT_HEIGHT;
		txtParkingName.setLayoutData(formData);
		return composite;
	}
	
	private Control createDeal(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		Label lbIfDeal = new Label(composite, SWT.RIGHT);
		lbIfDeal.setFont(StyleFinal.SYS_FONT);
		lbIfDeal.setText("是否配载:");
		FormData formData = new FormData();
		formData.top = new FormAttachment(0,10);
		formData.left = new FormAttachment(0,10);
		lbIfDeal.setLayoutData(formData);
		
		cboIfDeal = new CCombo(composite, SWT.BORDER|SWT.READ_ONLY);
		cboIfDeal.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(lbIfDeal,-2,SWT.TOP);
		formData.left = new FormAttachment(lbIfDeal,5);
		formData.width = 135;
		formData.height = StyleFinal.DROP_HEIGHT;
		cboIfDeal.setLayoutData(formData);
		
		Label lbIfMain = new Label(composite, SWT.RIGHT);
		lbIfMain.setFont(StyleFinal.SYS_FONT);
		lbIfMain.setText("始发站:");
		formData = new FormData();
		formData.top = new FormAttachment(lbIfDeal,0,SWT.TOP);
		formData.right = new FormAttachment(100,-150);
		lbIfMain.setLayoutData(formData);
		
		cboIfMain = new CCombo(composite, SWT.BORDER|SWT.READ_ONLY);
		cboIfMain.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(cboIfDeal,0,SWT.TOP);
		formData.left = new FormAttachment(lbIfMain,5);
		formData.width = 135;
		formData.height = StyleFinal.DROP_HEIGHT;
		cboIfMain.setLayoutData(formData);
		
		Group gpOrganize = new Group(composite, SWT.NONE);
		gpOrganize.setLayout(new FormLayout());
		gpOrganize.setText("可选择车站");
		gpOrganize.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(lbIfDeal,10);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(43);
		formData.bottom = new FormAttachment(100);
		gpOrganize.setLayoutData(formData);
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("车站名称","organizeName",220));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowSeq(true);
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridConfig.setObj(obj);
		gridOrganize = new GridView(gpOrganize, SWT.BORDER);
		gridOrganize.CreateTabel(gridConfig);
		formData = new FormData();
		formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(0,5);
		formData.right = new FormAttachment(100,-5);
		formData.bottom = new FormAttachment(100,-5);
		gridOrganize.setLayoutData(formData);
		gridOrganize.bindMouseDoubleClick(obj, "addDeal");

		Group gpDeal = new Group(composite, SWT.NONE);
		gpDeal.setLayout(new FormLayout());
		gpDeal.setText("已选择车站");
		gpDeal.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(gpOrganize,0,SWT.TOP);
		formData.left = new FormAttachment(gpOrganize,10);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		gpDeal.setLayoutData(formData);
		columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("车站名称","organizeName",215));
		EditorOption editorOption = new EditorOption();
		editorOption.setVerify(true);
		columns.add(new GridColumn("协议号","dealId",100, "Text",editorOption));
		gridConfig = new GridConfig();
		gridConfig.setShowSeq(true);
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridConfig.setObj(obj);
		gridDeal = new GridView(gpDeal, SWT.BORDER);
		gridDeal.CreateTabel(gridConfig);
		formData = new FormData();
		formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(0,5);
		formData.right = new FormAttachment(100,-5);
		formData.bottom = new FormAttachment(100,-5);
		gridDeal.setLayoutData(formData);
		gridDeal.bindMouseDoubleClick(obj, "delDeal");
		return composite;
	}
	
	private Control createSeat(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new FormLayout());
		seatBar = new SeatBar(composite, true, SWT.BORDER);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100, -80);
		seatBar.setLayoutData(formData);
		seatBar.bindRefresh(obj, "pageMethod");
		
		Composite compSeat = new Composite(composite, SWT.BORDER);
		compSeat.setLayout(new FormLayout());
		formData = new FormData();
		formData.top = new FormAttachment(seatBar,-1);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100, -40);
		compSeat.setLayoutData(formData);
		
		Composite compUser = new Composite(compSeat, SWT.NONE);
		GridLayout gridLayout = new GridLayout(6,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		compUser.setLayout(gridLayout);
		formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100,-200);
		compUser.setLayoutData(formData);
		
		KLabel lbSeatNum = new KLabel(compUser, SWT.RIGHT);
		lbSeatNum.setFont(StyleFinal.SYS_FONT);
		lbSeatNum.setText("座位数:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSeatNum.setLayoutData(gridData);
		
		txtSeatNum = new Text(compUser, SWT.BORDER);
		txtSeatNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 60;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSeatNum.setLayoutData(gridData);
		txtSeatNum.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbHalfNum = new KLabel(compUser, SWT.RIGHT);
		lbHalfNum.setFont(StyleFinal.SYS_FONT);
		lbHalfNum.setText("半票数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbHalfNum.setLayoutData(gridData);
		
		txtHalfNum = new Text(compUser, SWT.BORDER);
		txtHalfNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 60;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtHalfNum.setLayoutData(gridData);
		txtHalfNum.addVerifyListener(new TextVerifyListener(1));

		KLabel lbFreeNum = new KLabel(compUser, SWT.RIGHT);
		lbFreeNum.setFont(StyleFinal.SYS_FONT);
		lbFreeNum.setText("免票数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFreeNum.setLayoutData(gridData);
		
		txtFreeNum = new Text(compUser, SWT.BORDER);
		txtFreeNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 60;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFreeNum.setLayoutData(gridData);
		txtFreeNum.addVerifyListener(new TextVerifyListener(1));

		btnIfPrintseat = new Button(compSeat, SWT.CHECK);
		btnIfPrintseat.setText("打印座位号(&P)");
		btnIfPrintseat.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,8);
		formData.right = new FormAttachment(100,-10);
		btnIfPrintseat.setLayoutData(formData);

		Button btnOpen = new Button(composite, SWT.NONE);
		btnOpen.setText("可售(&O)");
		btnOpen.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(compSeat,8);
		formData.left = new FormAttachment(30);
		formData.width=80;
		btnOpen.setLayoutData(formData);
		btnOpen.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDown(MouseEvent arg0) {
				updateSeats(1);
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		Button btnClose = new Button(composite, SWT.NONE);
		btnClose.setText("禁售(&C)");
		btnClose.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(compSeat,8);
		formData.left = new FormAttachment(btnOpen,15);
		formData.width=80;
		btnClose.setLayoutData(formData);
		btnClose.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				updateSeats(0);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		Button btnDeal = new Button(composite, SWT.NONE);
		btnDeal.setText("配载(&D)");
		btnDeal.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(compSeat,8);
		formData.left = new FormAttachment(btnClose,15);
		formData.width=80;
		btnDeal.setLayoutData(formData);
		btnDeal.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				updateSeats(4);
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		return composite;
	}
	
	private List<SamModuleRight> initRight() {
		List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
		SamModuleRight moduleRight = new SamModuleRight();
		moduleRight.setRightName("默认检票口(&D)");
		moduleRight.setRightMethod("setCheckGate");
		rights.add(moduleRight);
		return rights;
	}

	@SuppressWarnings("unchecked")
	public void setCheckGate(){
		List<EpdCheckgate> checkgates = (List<EpdCheckgate>) gridCheckgate.getDataList();
		if (null != checkgates && checkgates.size()>0){
			for (int i = 0; i < checkgates.size(); i++) {
				checkgates.get(i).setDefaultCheck("0");
				gridCheckgate.updateRow(i, checkgates.get(i));
				gridCheckgate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_BLACK));
				
			}
		}
		gridCheckgate.setCheck(gridCheckgate.getSelectionIndex(), true);
		gridCheckgate.setRowFontColor(gridCheckgate.getSelectionIndex(), SWTResourceManager.getColor(SWT.COLOR_RED));
		checkgate = (EpdCheckgate) gridCheckgate.getSelection();
		checkgate.setDefaultCheck("1");
		gridCheckgate.updateRow(gridCheckgate.getSelectionIndex(), checkgate);
	}
	

	private void initData(){
		try {

			CallMethod callMethod = new CallMethod();
			callMethod.bindEnterEvent(this, dropRoute.droptext.txtShow, dropCargrade.droptext.txtShow, "");
			callMethod.bindEnterEvent(this, dropCargrade.droptext.txtShow, cboLinerType, "setStations");
			callMethod.bindEnterEvent(this, cboLinerType, dLinerDate.txtDate, "");
			callMethod.bindEnterEvent(this, dLinerDate.txtDate, txtLinerId, "");
			callMethod.bindEnterEvent(this, txtLinerId, txtLinerTime, "");
			callMethod.bindEnterEvent(this, txtLinerTime, cboLinerStatus, "");
			callMethod.bindEnterEvent(this, cboLinerStatus, cboIfNetsale, "");
			callMethod.bindEnterEvent(this, cboIfNetsale, txtRemark, "");
			callMethod.bindEnterEvent(this, txtRemark, null, "");
			
			callMethod.bindEnterEvent(this, dropService.droptext.txtShow, null, "addService");
			
			callMethod.bindEnterEvent(this, txtSeatNum, txtHalfNum, "");
			callMethod.bindEnterEvent(this, txtHalfNum, txtFreeNum, "");
			callMethod.bindEnterEvent(this, txtFreeNum, btnIfPrintseat, "");
			callMethod.bindEnterEvent(this, btnIfPrintseat, null, "");
			
			cboLinerType.setItems(new String[]{"固定班次","加班","包车","其他班次"});
			cboLinerStatus.setItems(TraffFinal.ARR_LINER_STATE);
			cboIfDeal.setItems(CommFinal.getAllName(TraffFinal.ARR_IF_DEAL));
			cboIfMain.setItems(CommFinal.getAllName(TraffFinal.ARR_IF_MAIN));
			cboIfNetsale.setItems(CommFinal.getAllName(TraffFinal.ARR_IF_NETSALE));
			dropService.initType("1");
			IEpdCheckgate iEpdCheckgate = new ImpEpdCheckgate();
			List<EpdCheckgate> checkgates = iEpdCheckgate.queryByAll();
			gridCheckgate.setDataList(checkgates);
			
			ISamOrganize iSamOrganize = new ImpSamOrganize();
			List<SamOrganize> organizes = iSamOrganize.queryDealByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			gridOrganize.setDataList(organizes);
			txtSeatNum.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					setSeats();
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			if (!CommFinal.OPER_TYPE_ADD.equals(operType)){
				ItsLiner itsLiner = (ItsLiner) gridView.getSelection();
				dropCargrade.initByRouteSeq(itsLiner.getRouteSeq());
				
				dropRoute.droptext.setValue(itsLiner.getRouteSeq());
				dropCargrade.droptext.setValue(itsLiner.getCargradeSeq());
				cboLinerType.setText(itsLiner.getLinerType());
				txtLinerId.setText(itsLiner.getLinerId());
				
				txtLinerTime.setText(itsLiner.getLinerTime());
				cboLinerStatus.setText(itsLiner.getLinerStatus());
				txtSeatNum.setText(String.valueOf(itsLiner.getSeatNum()));
				txtHalfNum.setText(String.valueOf(itsLiner.getHalfNum()));
				txtFreeNum.setText(String.valueOf(itsLiner.getFreeNum()));

				if (1==itsLiner.getIfPrintseat()){
					btnIfPrintseat.setSelection(true);
				}else{
					btnIfPrintseat.setSelection(false);
				}
				cboIfNetsale.setText(CommFinal.getItemName(TraffFinal.ARR_IF_NETSALE, String.valueOf(itsLiner.getIfNetsale())));
				txtRemark.setText(itsLiner.getRemark());
				//初始化检票口
				IItsLinercheck iItsLinercheck = new ImpItsLinercheck();
				List<ItsLinercheck> linerchecks = iItsLinercheck.queryByLinerSeq(itsLiner.getLinerSeq());
				if (null != linerchecks && linerchecks.size()>0){
					if (null != checkgates && checkgates.size()>0){
						for (int i = 0; i < checkgates.size(); i++) {
							for (int j = 0; j < linerchecks.size(); j++) {
								if (checkgates.get(i).getCheckgateSeq().equals(linerchecks.get(j).getCheckgateSeq())){
									gridCheckgate.updateRow(i, checkgates.get(i));
									gridCheckgate.setCheck(i, true);
									break;
								}
							}
							if (itsLiner.getCheckgateSeq().equals(checkgates.get(i).getCheckgateSeq())){
								gridCheckgate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_RED));
								checkgate = checkgates.get(i);
								checkgates.get(i).setDefaultCheck("1");
								gridCheckgate.updateRow(i, checkgates.get(i));
							}
						}
					}
					//planchecks
					List<EpdCheckgate> epdCheckgates = new ArrayList<EpdCheckgate>();
					for (int i = 0; i < linerchecks.size(); i++) {
						EpdCheckgate epdCheckgate = new EpdCheckgate();
						epdCheckgate.setCheckgateSeq(linerchecks.get(i).getCheckgateSeq());
						epdCheckgates.add(epdCheckgate);
					}
					dropParking.initByCheckCode(epdCheckgates);
					dropParking.droptext.setValue(itsLiner.getParkingSeq());
				}
				//初始化座位
				for (int i = 0; i < itsLiner.getSeatNum(); i++) {
					Seat seat = new Seat();
					seat.setSeatId(String.valueOf(i+1));
					seat.setSeatState(1);
					seat.setSaleType("1");
					seats.add(seat);
				}
				pageMethod();
				
				//初始化配载
				cboIfDeal.setText(CommFinal.getItemName(TraffFinal.ARR_IF_DEAL, String.valueOf(itsLiner.getIfDeal())));
				cboIfMain.setText(CommFinal.getItemName(TraffFinal.ARR_IF_MAIN, String.valueOf(itsLiner.getIfDeal())));
				IItsLinerdeal iItsLinerdeal = new ImpItsLinerdeal();
				List<ItsLinerdeal> linerdeals = iItsLinerdeal.queryByLinerSeq(itsLiner.getLinerSeq());
				gridDeal.setDataList(linerdeals);
				if (null !=  linerdeals && linerdeals.size()>0){
					@SuppressWarnings("unchecked")
					List<SamOrganize> samOrganizes = (List<SamOrganize>) gridOrganize.getDataList();
					if (null != samOrganizes && samOrganizes.size()>0){
						for (int i = 0; i < linerdeals.size(); i++) {
							for (int j = 0; j < samOrganizes.size(); j++) {
								if (linerdeals.get(i).getDealOrganize().equals(samOrganizes.get(j).getOrganizeCode())){
									gridOrganize.deleteRow(j);
									break;
								}
							}
						}
					}
				}
				txtLinerId.forceFocus();
				txtLinerId.selectAll();
			}else{
				dropRoute.droptext.txtShow.setFocus();
				dropRoute.droptext.txtShow.selectAll();
			}
			tabFolder.setSelection(0);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void setStations(){
		try {
			String routeSeq = dropRoute.droptext.getValue();
			String cargradeSeq = dropCargrade.droptext.getValue();
			if (null != routeSeq && routeSeq.length()>0){
				if (null != cargradeSeq && cargradeSeq.length()>0){
					IEpdRouteStation iEpdRouteStation = new ImpEpdRouteStation();
					IEpdFare iEpdFare = new ImpEpdFare();
					List<EpdRoutestation> routestations = iEpdRouteStation.queryByRouteSeq(dropRoute.droptext.getValue());
					List<EpdFare> epdFares = iEpdFare.queryAllByCustom(routeSeq, null, cargradeSeq);
					if(null != routestations && routestations.size() > 0){
						List<Station> stations = new ArrayList<Station>();
						for (int i = 0; i < routestations.size(); i++) {
							Station station = new Station();
							station.setStationSeq(routestations.get(i).getStationSeq());
							station.setStationName(routestations.get(i).getStationName());
							station.setIfSale(1);
							station.setStationNum(0);
							station.setSaleNum(0);
							station.setStationOrder(i+1);
							station.setIfRelease(routestations.get(i).getIfRelease());
							if (null != epdFares && epdFares.size()>0){
								for (int j = 0; j < epdFares.size(); j++) {
									if(routestations.get(i).getStationSeq().equals(epdFares.get(j).getStationSeq())){
										station.setFullFare(epdFares.get(j).getFullFare());
										station.setBaseFare(epdFares.get(j).getBaseFare());
										station.setStationFare(epdFares.get(j).getStationFare());
										station.setFuelFare(epdFares.get(j).getFuelFare());
										station.setOtherOne(epdFares.get(j).getOtherOne());
										station.setOtherTwo(epdFares.get(j).getOtherTwo());
										station.setOtherThree(epdFares.get(j).getOtherThree());
										station.setOtherFour(epdFares.get(j).getOtherFour());
										station.setOtherFive(epdFares.get(j).getOtherFive());
										break;
									}
								}
							}
							stations.add(station);
						}
						addFareBar.setData(stations);
					}
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addDeal(){
		try {
			if (null != gridOrganize.getSelections() && gridOrganize.getSelections().size()>0){
				List<SamOrganize> organizes = (List<SamOrganize>) gridOrganize.getSelections();
				for (int i = 0; i < organizes.size(); i++) {
					ItsLinerdeal linerdeal = new ItsLinerdeal();
					linerdeal.setDealOrganize(organizes.get(i).getOrganizeCode());
					linerdeal.setOrganizeName(organizes.get(i).getOrganizeName());
					gridDeal.addRow(linerdeal);
				}
				gridOrganize.deleteRow(gridOrganize.getSelectionIndexs());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void delDeal(){
		try {
			if (null != gridDeal.getSelections() && gridDeal.getSelections().size()>0){
				List<ItsLinerdeal> linerdeals = (List<ItsLinerdeal>) gridDeal.getSelections();
				for (int i = 0; i < linerdeals.size(); i++) {
					SamOrganize organize = new SamOrganize();
					organize.setOrganizeCode(linerdeals.get(i).getDealOrganize());
					organize.setOrganizeName(linerdeals.get(i).getOrganizeName());
					gridOrganize.addRow(organize);
				}
				gridDeal.deleteRow(gridDeal.getSelectionIndexs());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setSeats(){
		if(null != txtSeatNum.getText() && txtSeatNum.getText().length()>0){
			for (int i = 0; i < Integer.valueOf(txtSeatNum.getText()); i++) {
				Seat seat = new Seat();
				seat.setSeatId(String.valueOf(i+1));
				seat.setSeatState(1);
				seat.setSaleType("1");
				seats.add(seat);
			}
			pageMethod();
		}
	}
	
	public void updateSeats(int seatState){
		List<Seat> seats  = seatBar.getSelectionSeats();
		if(null != seats && seats.size() > 0){
			int[] indexs = seatBar.getSelectionIndexs();
			List<Composite> comps = seatBar.getSelectionComps();
			for(int i=0;i<seats.size();i++){
				seats.get(i).setSeatState(seatState);
			}
			seatBar.upSeats(seats, indexs,comps);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addService(){
		if (null != dropService.droptext.getValue() && dropService.droptext.getValue().length()>0){
			try {
				List<SamService> samServices = (List<SamService>) gridService.getDataList();
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						if (dropService.droptext.getValue().equals(samServices.get(i).getServiceSeq())){
							MsgBox.warning(getShell(), "乘车点已存在，不允许重复添加。");
							return;
						}
					}
				}
				
				SamService service = (SamService) dropService.droptext.getObject();
				service.setIfUsing("1");
				gridService.addRow(service);
			} catch (Exception e) {
				LogUtil.operLog(e,"E",true,true);
			}
		}
		dropService.droptext.txtShow.forceFocus();
		dropService.droptext.txtShow.selectAll();
	}
	
	@SuppressWarnings("unchecked")
	public void enableService(){
		try {
			int index[] = gridService.getSelectionIndexs();
			if (null != index && index.length>0){
				List<SamService> samServices = (List<SamService>) gridService.getSelections();
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						samServices.get(i).setIfUsing("1");
					}
					gridService.updateRows(index, samServices);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void disableService(){
		try {
			int index[] = gridService.getSelectionIndexs();
			if (null != index && index.length>0){
				List<SamService> samServices = (List<SamService>) gridService.getSelections();
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						samServices.get(i).setIfUsing("0");
					}
					gridService.updateRows(index, samServices);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void upService(){
		gridService.upTableRow();
	}
	
	public void downService(){
		gridService.downTableRow();
	}
	
	public void deleteService(){
		int index[] = gridService.getSelectionIndexs();
		if (null != index && index.length>0){
			gridService.deleteRow(index);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				ItsLiner itsLiner = validData();
				if (null != itsLiner){
					List<EpdCheckgate> checkgates = (List<EpdCheckgate>) gridCheckgate.getCheckSelections();
					if (null == checkgates || checkgates.size()<=0){
						MsgBox.warning(getShell(), "请设置计划检票口！");
						return;
					}
					
					List<ItsLinerstation> itsLinerstations = new ArrayList<ItsLinerstation>();
					List<ItsLinerfare> itsLinerfares = new ArrayList<ItsLinerfare>();
					List<Station> stations = addFareBar.getData();
					String stationName ="";
					
					if (null != stations && stations.size()>0){
						for (int i = 0; i < stations.size(); i++) {
							ItsLinerstation itsLinerstation = new ItsLinerstation();
							itsLinerstation.setLinerDate(itsLiner.getLinerDate());
							itsLinerstation.setLinerId(itsLiner.getLinerId());
							itsLinerstation.setRouteSeq(itsLiner.getRouteSeq());
							itsLinerstation.setRouteName(itsLiner.getRouteName());
							itsLinerstation.setStationSeq(stations.get(i).getStationSeq());
							itsLinerstation.setStationName(stations.get(i).getStationName());
							itsLinerstation.setStationOrder(stations.get(i).getStationOrder());
							itsLinerstation.setIfSale(stations.get(i).getIfSale());
							itsLinerstation.setStationNum(stations.get(i).getStationNum());
							itsLinerstation.setUpdateTime(itsLiner.getUpdateTime());
							itsLinerstations.add(itsLinerstation);
							
							ItsLinerfare itsLinerfare = new ItsLinerfare();
							itsLinerfare.setLinerDate(itsLiner.getLinerDate());
							itsLinerfare.setLinerId(itsLiner.getLinerId());
							itsLinerfare.setRouteSeq(itsLiner.getRouteSeq());
							itsLinerfare.setStationSeq(stations.get(i).getStationSeq());
							itsLinerfare.setFullFare(stations.get(i).getFullFare());
							itsLinerfare.setBaseFare(stations.get(i).getBaseFare());
							itsLinerfare.setStationFare(stations.get(i).getStationFare());
							itsLinerfare.setFuelFare(stations.get(i).getFuelFare());
							itsLinerfare.setOtherOne(stations.get(i).getOtherOne());
							itsLinerfare.setOtherTwo(stations.get(i).getOtherTwo());
							itsLinerfare.setOtherThree(stations.get(i).getOtherThree());
							itsLinerfare.setOtherFour(stations.get(i).getOtherFour());
							itsLinerfare.setOtherFive(stations.get(i).getOtherFive());
							itsLinerfare.setUpdateTime(itsLiner.getUpdateTime());
							itsLinerfares.add(itsLinerfare);
							
							if(i==0){
								stationName = stations.get(i).getStationName();
							}else{
								stationName = stationName + "," + stations.get(i).getStationName();
							}
							if(null != stations.get(i).getIfRelease() && "1".equals(stations.get(i).getIfRelease())){
								itsLiner.setPrice(stations.get(i).getFullFare());
							}
						}
					}
					itsLiner.setStationName(stationName);
					List<ItsLinerseat> itsLinerseats = new ArrayList<ItsLinerseat>();
					int stopNum = 0;
					int reverseNum = 0;
					if (null == seats || seats.size()<=0){
						MsgBox.warning(getShell(), "请确认座位数，未创建计划座位，！");
						return;
					}else{
						for (int i = 0; i < seats.size(); i++) {
							ItsLinerseat itsLinerseat = new ItsLinerseat();
							itsLinerseat.setLinerDate(itsLiner.getLinerDate());
							itsLinerseat.setLinerId(itsLiner.getLinerId());
							itsLinerseat.setSeatId(Integer.valueOf(seats.get(i).getSeatId()));
							itsLinerseat.setSeatStatus(seats.get(i).getSeatState());
							itsLinerseat.setSeatType(Integer.valueOf(seats.get(i).getSaleType()));
							itsLinerseat.setUpdateTime(itsLiner.getUpdateTime());
							itsLinerseats.add(itsLinerseat);
							if (seats.get(i).getSeatState()==0){
								stopNum+=1;
							}
							if (seats.get(i).getSeatState()==3){
								reverseNum+=1;
							}
						}
						itsLiner.setStopNum(stopNum);
						itsLiner.setReverseNum(reverseNum);
					}
					List<ItsLinercheck> itsLinerchecks = new ArrayList<ItsLinercheck>();
					for (int i = 0; i < checkgates.size(); i++) {
						ItsLinercheck itsLinercheck = new ItsLinercheck();
						itsLinercheck.setLinerDate(itsLiner.getLinerDate());
						itsLinercheck.setLinerId(itsLiner.getLinerId());
						itsLinercheck.setCheckgateSeq(checkgates.get(i).getCheckgateSeq());
						itsLinercheck.setUpdateTime(itsLiner.getUpdateTime());
						itsLinerchecks.add(itsLinercheck);
					}
					
					List<ItsLinerdeal> itsLinerdeals = new ArrayList<ItsLinerdeal>();
					if (1==itsLiner.getIfDeal()){
						itsLinerdeals = (List<ItsLinerdeal>) gridDeal.getDataList();
						if (null != itsLinerdeals && itsLinerdeals.size()>0){
							for (int i = 0; i < itsLinerdeals.size(); i++) {
								itsLinerdeals.get(i).setLinerDate(itsLiner.getLinerDate());
								itsLinerdeals.get(i).setLinerId(itsLiner.getLinerId());
								itsLinerdeals.get(i).setUpdateTime(itsLiner.getUpdateTime());
							}
						}
					}
					
					itsLiner.setUsingService(0);
					List<ItsLinerservice> linerservices = new ArrayList<ItsLinerservice>();
					List<SamService> samServices = (List<SamService>) gridService.getDataList();
					if (null != samServices && samServices.size()>=0){
						itsLiner.setUsingService(1);
						for (int i = 0; i < samServices.size(); i++) {
							ItsLinerservice itsLinerservice = new ItsLinerservice();
							itsLinerservice.setLinerDate(itsLiner.getLinerDate());
							itsLinerservice.setLinerId(itsLiner.getLinerId());
							itsLinerservice.setRouteSeq(itsLiner.getRouteSeq());
							itsLinerservice.setRouteName(itsLiner.getRouteName());
							itsLinerservice.setServiceSeq(samServices.get(i).getServiceSeq());
							itsLinerservice.setServiceName(samServices.get(i).getServiceName());
							itsLinerservice.setServiceOrder(i+1);
							itsLinerservice.setIfUsing(samServices.get(i).getIfUsing());
							itsLinerservice.setUpdateTime(itsLiner.getUpdateTime());
							linerservices.add(itsLinerservice);
						}
					}
					IItsLiner iItsLiner = new ImpItsLiner();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)||CommFinal.OPER_TYPE_COPY.equals(operType)){
						ItsLiner newItsLineer = iItsLiner.insert(itsLiner, itsLinerstations, itsLinerseats,
								itsLinerfares, itsLinerchecks, itsLinerdeals, linerservices, CommFinal.initConfig());
						gridView.addRow(newItsLineer);
						close();
					}
				}
			} else if (0 == buttonId) {
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private ItsLiner validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		ItsLiner itsLiner = new ItsLiner();
		itsLiner.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			itsLiner = (ItsLiner) gridView.getSelection();
		}
		itsLiner.setCreateTime(currTime);
		itsLiner.setUpdateTime(currTime);
		if (null != dropRoute.droptext.getValue() && !"".equals(dropRoute.droptext.getValue())){
			itsLiner.setRouteSeq(dropRoute.droptext.getValue());
			itsLiner.setRouteName(dropRoute.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择计划运营线路！");
			dropRoute.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != dropCargrade.droptext.getValue() && !"".equals(dropCargrade.droptext.getValue())){
			itsLiner.setCargradeSeq(dropCargrade.droptext.getValue());
			itsLiner.setCargradeName(dropCargrade.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择计划车型！");
			dropCargrade.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(cboLinerType.getText())){
			itsLiner.setLinerType(cboLinerType.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择班次类型！");
			cboLinerType.forceFocus();
			return null;
		}
		
		if (Validate.isDate(dLinerDate.getText())==true){
			itsLiner.setLinerDate(dLinerDate.getText());
		}else{
			MsgBox.warning(getShell(), "班次日期不合法！");
			dLinerDate.txtDate.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(txtLinerId.getText())){
			itsLiner.setLinerId(txtLinerId.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入班次号！");
			txtLinerId.forceFocus();
			return null;
		}
		
		if (txtLinerTime.getText().trim().length()==5){
			itsLiner.setLinerTime(txtLinerTime.getText());
		}else{
			MsgBox.warning(getShell(), "班次发车时间不合法！");
			txtLinerTime.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(cboLinerStatus.getText())){
			itsLiner.setLinerStatus(cboLinerStatus.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选班次状态！");
			cboLinerStatus.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(cboIfNetsale.getText())){
			itsLiner.setIfNetsale(Integer.valueOf(CommFinal.getItemValue(TraffFinal.ARR_IF_NETSALE, cboIfNetsale.getText())));
		}else{
			MsgBox.warning(getParentShell(),"请选择计划是否允许网售！");
			cboIfNetsale.forceFocus();
			return null;
		}
		
		itsLiner.setRemark(txtRemark.getText());
		itsLiner.setCreateUser(CommFinal.user.getUserCode());
		
		if (Validate.StrNotNull(txtSeatNum.getText())){
			itsLiner.setSeatNum(Integer.valueOf(txtSeatNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入班次座位数！");
			txtSeatNum.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(txtHalfNum.getText())){
			itsLiner.setHalfNum(Integer.valueOf(txtHalfNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入班次半票数！");
			txtHalfNum.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(txtFreeNum.getText())){
			itsLiner.setFreeNum(Integer.valueOf(txtFreeNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入班次免票数！");
			txtFreeNum.forceFocus();
			return null;
		}
		
		if (btnIfPrintseat.getSelection()){
			itsLiner.setIfPrintseat(1);
		}else{
			itsLiner.setIfPrintseat(0);
		}
		
		if (null!=checkgate){
			itsLiner.setCheckgateSeq(checkgate.getCheckgateSeq());
			itsLiner.setCheckName(checkgate.getCheckName());
		}else{
			MsgBox.warning(getParentShell(),"请设置计划默认检票口！");
			gridCheckgate.forceFocus();
			return null;
		}
		
		if (null != dropParking.droptext.getValue() && dropParking.droptext.getValue().length()>0){
			itsLiner.setParkingSeq(dropParking.droptext.getValue());
			itsLiner.setParkingName(dropParking.droptext.getText());
		}
		
		if (Validate.StrNotNull(cboIfDeal.getText())){
			itsLiner.setIfDeal(Integer.valueOf(CommFinal.getItemValue(TraffFinal.ARR_IF_DEAL, cboIfDeal.getText())));
			if (1==itsLiner.getIfDeal()){
				if (null != gridDeal.getDataList() && gridDeal.getDataList().size()>0){
					List<ItsLinerdeal> linerdeals = (List<ItsLinerdeal>) gridDeal.getDataList();
					for (int i = 0; i < linerdeals.size(); i++) {
						if(Validate.StrNotNull(linerdeals.get(i).getDealId())){
						}else{
							MsgBox.warning(getParentShell(),linerdeals.get(i).getOrganizeName()+"请输入协议号！");
							tabFolder.setSelection(2);
							return null;
						}
					}
				}else{
					MsgBox.warning(getParentShell(),"请设置计划配载协议！");
					tabFolder.setSelection(1);
					return null;
				}
			}

		}else{
			itsLiner.setIfDeal(0);
			itsLiner.setIfMain(1);
		}
		
		if ("1".equals(itsLiner.getIfDeal())){
			if (Validate.StrNotNull(cboIfMain.getText())){
				itsLiner.setIfMain(Integer.valueOf(CommFinal.getItemValue(TraffFinal.ARR_IF_MAIN, cboIfMain.getText())));
			}else{
				MsgBox.warning(getParentShell(),"请选择计划是否始发！");
				tabFolder.setSelection(2);
				cboIfMain.forceFocus();
				return null;
			}
		}else{
			itsLiner.setIfMain(1);
		}
		
		return itsLiner;
	}
	
	public void pageMethod(){
		int start = seatBar.getStart();
		int limit = seatBar.getLimit();
		List<Seat> barSeats = new ArrayList<Seat>();
		if (null != seats && seats.size()>0){
			for (int i = start; i < start+limit; i++) {
				if (i<seats.size()){
					barSeats.add(seats.get(i));
				}
			}
		}
		seatBar.createSeats(barSeats);
		seatBar.setTotalCount(Integer.valueOf(txtSeatNum.getText()));
	}
	
	private List<SamModuleRight> initService() {
		List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
		SamModuleRight moduleRight = new SamModuleRight();
		moduleRight.setRightName("启用(&Q)");
		moduleRight.setRightMethod("enableService");
		rights.add(moduleRight);
		
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("停用(&S)");
		moduleRight.setRightMethod("disableService");
		rights.add(moduleRight);
		
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("上移(&U)");
		moduleRight.setRightMethod("upService");
		rights.add(moduleRight);
		
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("下移(&D)");
		moduleRight.setRightMethod("downService");
		rights.add(moduleRight);
		
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("删除(&R)");
		moduleRight.setRightMethod("deleteService");
		rights.add(moduleRight);
		return rights;
	}
	
}