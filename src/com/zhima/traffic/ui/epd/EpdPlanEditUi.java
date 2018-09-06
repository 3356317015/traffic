package com.zhima.traffic.ui.epd;

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
import com.zhima.traffic.action.epd.IEpdPlan;
import com.zhima.traffic.action.epd.IEpdPlancheck;
import com.zhima.traffic.action.epd.IEpdPlandeal;
import com.zhima.traffic.action.epd.IEpdPlanseat;
import com.zhima.traffic.action.epd.IEpdPlanservice;
import com.zhima.traffic.action.epd.IEpdPlanstation;
import com.zhima.traffic.action.epd.IEpdRouteStation;
import com.zhima.traffic.action.epd.impl.ImpEpdCheckgate;
import com.zhima.traffic.action.epd.impl.ImpEpdPlan;
import com.zhima.traffic.action.epd.impl.ImpEpdPlancheck;
import com.zhima.traffic.action.epd.impl.ImpEpdPlandeal;
import com.zhima.traffic.action.epd.impl.ImpEpdPlanseat;
import com.zhima.traffic.action.epd.impl.ImpEpdPlanservice;
import com.zhima.traffic.action.epd.impl.ImpEpdPlanstation;
import com.zhima.traffic.action.epd.impl.ImpEpdRouteStation;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropCargrade;
import com.zhima.traffic.drop.DropParking;
import com.zhima.traffic.drop.DropRoute;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdParking;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdPlancheck;
import com.zhima.traffic.model.EpdPlandeal;
import com.zhima.traffic.model.EpdPlanseat;
import com.zhima.traffic.model.EpdPlanservice;
import com.zhima.traffic.model.EpdPlanstation;
import com.zhima.traffic.model.EpdRoutestation;
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
import com.zhima.widget.stationBar.PlanStationBar;
import com.zhima.widget.stationBar.Station;

public class EpdPlanEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	private Object obj;

	private CTabFolder tabFolder;

	private DropRoute dropRoute;
	private DropCargrade dropCargrade;
	
	private CTabFolder tabRoute;
	
	private PlanStationBar stationBar;
	private CCombo cboPlanType;
	private Text txtPlanId;
	private Text txtFirstTime;
	private Text txtPlanTime;
	private Text txtPreDays;
	private CCombo cboPlanStatus;
	private CCalendar txtStartDate;
	private CCalendar txtEndDate;
	private Text txtSeatNum;
	private Text txtHalfNum;
	private Text txtFreeNum;
	private Label lbPlanOption;
	private Text txtPlanOption;

	private Button btnIfPrintseat;
	private CCombo cboIfNetsale;
	private Text txtRemark;
	
	private DropService dropService;
	private GridView gridRouteService;
	
	private GridView gridCheckgate;
	private DropParking dropParking;
	private Text txtParkingName;
	
	private CCombo cboIfDeal;
	private CCombo cboIfMain;
	private GridView gridOrganize;
	private GridView gridDeal;
	
	private SeatBar seatBar;

	private EpdCheckgate checkgate = null;
	
	List<Seat> seats = new ArrayList<Seat>();
	
	protected EpdPlanEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.obj = this;
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("计划设置-"+operType);
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
		tabPlan.setText("计划信息");
		tabPlan.setControl(createPlan(tabFolder));
		
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

	private Control createPlan(CTabFolder tabFolder) {
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
				setStations();
				dropCargrade.droptext.setValue("");
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
		
		KLabel lbPreDays = new KLabel(compLiner, SWT.RIGHT);
		lbPreDays.setFont(StyleFinal.SYS_FONT);
		lbPreDays.setText("预售天数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPreDays.setLayoutData(gridData);
		
		txtPreDays = new Text(compLiner, SWT.BORDER);
		txtPreDays.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPreDays.setLayoutData(gridData);
		txtPreDays.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbPlanType = new KLabel(compLiner, SWT.RIGHT);
		lbPlanType.setFont(StyleFinal.SYS_FONT);
		lbPlanType.setText("计划类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlanType.setLayoutData(gridData);
		
		cboPlanType = new CCombo(compLiner, SWT.BORDER|SWT.READ_ONLY);
		cboPlanType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboPlanType.setLayoutData(gridData);
		cboPlanType.setVisibleItemCount(10);
		cboPlanType.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO Auto-generated method stub
				if (cboPlanType.getText().equals("流水班次")){
					txtFirstTime.setEditable(true);
					txtPlanOption.setEditable(true);
					txtPlanTime.setEditable(true);
					lbPlanOption.setText("间隔分钟");
				}else if (cboPlanType.getText().equals("隔日")){
					txtFirstTime.setEditable(false);
					txtPlanOption.setEditable(true);
					txtPlanTime.setEditable(true);
					txtFirstTime.setText("00:00");
					lbPlanOption.setText("隔日天数");
				}else{
					txtFirstTime.setEditable(false);
					txtPlanOption.setEditable(false);
					txtPlanTime.setEditable(true);
					txtFirstTime.setText("00:00");
					lbPlanOption.setText("隔日天数");
					txtPlanOption.setText("");
				}
			}
		});
		
		KLabel lbPlanId = new KLabel(compLiner, SWT.RIGHT);
		lbPlanId.setFont(StyleFinal.SYS_FONT);
		lbPlanId.setText("计划车次:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlanId.setLayoutData(gridData);
		
		txtPlanId = new Text(compLiner, SWT.BORDER);
		txtPlanId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPlanId.setLayoutData(gridData);
		
		KLabel lbPlanState = new KLabel(compLiner, SWT.RIGHT);
		lbPlanState.setFont(StyleFinal.SYS_FONT);
		lbPlanState.setText("计划状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlanState.setLayoutData(gridData);
		
		cboPlanStatus = new CCombo(compLiner, SWT.BORDER|SWT.READ_ONLY);
		cboPlanStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboPlanStatus.setLayoutData(gridData);
		cboPlanStatus.setVisibleItemCount(10);
		
		Label lbFirstTime = new Label(compLiner, SWT.RIGHT);
		lbFirstTime.setFont(StyleFinal.SYS_FONT);
		lbFirstTime.setText("首发时间:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFirstTime.setLayoutData(gridData);
		
		FormattedText formattedFirstTime = new FormattedText(compLiner, SWT.BORDER);
		ITextFormatter formatterFirstTime = new DateTimeFormatter("HH:mm");
		formattedFirstTime.setFormatter(formatterFirstTime);
		txtFirstTime = formattedFirstTime.getControl();
		txtFirstTime.setFont(StyleFinal.SYS_FONT);
		SimpleDateFormat formatFirstTime = new SimpleDateFormat("HH:mm");
		Calendar calendarFirstTime = Calendar.getInstance();
		txtFirstTime.setText(formatFirstTime.format(calendarFirstTime.getTime()));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFirstTime.setLayoutData(gridData);
		
		lbPlanOption = new Label(compLiner, SWT.RIGHT);
		lbPlanOption.setFont(StyleFinal.SYS_FONT);
		lbPlanOption.setText("隔日天数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlanOption.setLayoutData(gridData);
		
		txtPlanOption = new Text(compLiner, SWT.BORDER);
		txtPlanOption.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPlanOption.setLayoutData(gridData);
		txtPlanOption.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbPlanTime = new KLabel(compLiner, SWT.RIGHT);
		lbPlanTime.setFont(StyleFinal.SYS_FONT);
		lbPlanTime.setText("发车时间:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlanTime.setLayoutData(gridData);
		
		FormattedText formattedPlanTime = new FormattedText(compLiner, SWT.BORDER);
		ITextFormatter formatterPlanTime = new DateTimeFormatter("HH:mm");
		formattedPlanTime.setFormatter(formatterPlanTime);
		txtPlanTime = formattedPlanTime.getControl();
		txtPlanTime.setFont(StyleFinal.SYS_FONT);
		SimpleDateFormat formatPlanTime = new SimpleDateFormat("HH:mm");
		Calendar calendarPlanTime = Calendar.getInstance();
		txtPlanTime.setText(formatPlanTime.format(calendarPlanTime.getTime()));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPlanTime.setLayoutData(gridData);
		
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

		KLabel lbStartDate = new KLabel(compLiner, SWT.RIGHT);
		lbStartDate.setFont(StyleFinal.SYS_FONT);
		lbStartDate.setText("开始日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStartDate.setLayoutData(gridData);
		
		txtStartDate = new CCalendar(compLiner, SWT.BORDER);
		txtStartDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtStartDate.setLayoutData(gridData);
		
		KLabel lbEndDate = new KLabel(compLiner, SWT.RIGHT);
		lbEndDate.setFont(StyleFinal.SYS_FONT);
		lbEndDate.setText("结束日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbEndDate.setLayoutData(gridData);
		
		txtEndDate = new CCalendar(compLiner, SWT.BORDER);
		txtEndDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtEndDate.setLayoutData(gridData);
		
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
	
	private Control createStation(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder,SWT.NONE);
		composite.setFont(StyleFinal.SYS_FONT);
		composite.setLayout(new FillLayout());
		stationBar = new PlanStationBar(composite, null, SWT.BORDER);
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
		columns.add(new GridColumn("车站名称","organizeName",220));
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
		checkgates = (List<EpdCheckgate>) gridCheckgate.getCheckSelections();
		if (null != checkgates && checkgates.size()>0){
			dropParking.initByCheckCode(checkgates);
		}else{
			dropParking.initByCheckCode(null);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initData(){
		try {
			cboPlanType.setItems(TraffFinal.ARR_PLAN_TYPE);
			cboPlanStatus.setItems(TraffFinal.ARR_PLAN_STATUS);
			cboIfDeal.setItems(CommFinal.getAllName(TraffFinal.ARR_IF_DEAL));
			cboIfMain.setItems(CommFinal.getAllName(TraffFinal.ARR_IF_MAIN));
			cboIfNetsale.setItems(CommFinal.getAllName(TraffFinal.ARR_IF_NETSALE));
			txtFirstTime.setEditable(false);
			txtPlanOption.setEditable(false);
			txtPlanOption.setEditable(false);
			
			IEpdCheckgate iEpdCheckgate = new ImpEpdCheckgate();
			List<EpdCheckgate> checkgates = iEpdCheckgate.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			gridCheckgate.setDataList(checkgates);
			dropService.initType("1");
			ISamOrganize iSamOrganize = new ImpSamOrganize();
			List<SamOrganize> organizes = iSamOrganize.queryDealByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			gridOrganize.setDataList(organizes);
			if (!CommFinal.OPER_TYPE_ADD.equals(operType)){
				EpdPlan epdPlan = (EpdPlan) gridView.getSelection();
				dropCargrade.initByRouteSeq(epdPlan.getRouteSeq());
				if (null != epdPlan && !"".equals(epdPlan.getPlanSeq())){
					dropRoute.droptext.setValue(epdPlan.getRouteSeq());
					dropCargrade.droptext.setValue(epdPlan.getCargradeSeq());
					txtPreDays.setText(String.valueOf(epdPlan.getPreDays()));
					cboPlanType.setText(epdPlan.getPlanType());
					txtPlanId.setText(epdPlan.getPlanId());
					if (cboPlanType.getText().equals("流水班次")){
						txtFirstTime.setEditable(true);
						txtFirstTime.setText(epdPlan.getFirstTime());
						txtPlanOption.setEditable(true);
						txtPlanOption.setText(epdPlan.getPlanOption());
						lbPlanOption.setText("间隔分钟");
					}else if (cboPlanType.getText().equals("隔日")){
						txtFirstTime.setEditable(false);
						txtPlanOption.setEditable(true);
						txtPlanOption.setText(epdPlan.getPlanOption());
						lbPlanOption.setText("隔日天数");
					}else{
						txtFirstTime.setEditable(false);
						txtPlanOption.setEditable(false);
						lbPlanOption.setText("隔日天数");
					}
					txtPlanTime.setText(epdPlan.getPlanTime());
					cboPlanStatus.setText(epdPlan.getPlanStatus());
					txtStartDate.setText(epdPlan.getStartDate());
					txtEndDate.setText(epdPlan.getEndDate());
					txtSeatNum.setText(String.valueOf(epdPlan.getSeatNum()));
					txtHalfNum.setText(String.valueOf(epdPlan.getHalfNum()));
					txtFreeNum.setText(String.valueOf(epdPlan.getFreeNum()));

					txtPlanOption.setText(epdPlan.getPlanOption());
					if (1==epdPlan.getIfPrintseat()){
						btnIfPrintseat.setSelection(true);
					}else{
						btnIfPrintseat.setSelection(false);
					}
					cboIfNetsale.setText(CommFinal.getItemName(TraffFinal.ARR_IF_NETSALE, String.valueOf(epdPlan.getIfNetsale())));
					txtRemark.setText(epdPlan.getRemark());
					//初始化站点
					IEpdPlanstation iEpdPlanstation = new ImpEpdPlanstation();
					List<EpdPlanstation> planstations = iEpdPlanstation.queryByPlanSeqAndPlanId(epdPlan.getPlanSeq(), epdPlan.getPlanId());
					if (null != planstations && planstations.size()>0){
						List<Station> stations = new ArrayList<Station>();
						for (int i = 0; i < planstations.size(); i++) {
							Station station = new Station();
							station.setStationSeq(planstations.get(i).getStationSeq());
							station.setStationName(planstations.get(i).getStationName());
							station.setIfSale(planstations.get(i).getIfSale());
							station.setStationNum(planstations.get(i).getStationNum());
							station.setSaleNum(0);
							station.setStationOrder(planstations.get(i).getStationOrder());
							stations.add(station);
						}
						stationBar.setData(stations);
					}
					//初始化乘车点
					IEpdPlanservice iEpdPlanservice = new ImpEpdPlanservice();
					List<EpdPlanservice> planservices = iEpdPlanservice.queryByPlanSeq(epdPlan.getPlanSeq());
					List<SamService> samServices = (List<SamService>) dropService.droptext.getDataList();
					if (null != planservices && planservices.size()>0){
						for (int i = 0; i < planservices.size(); i++) {
							for (int j = 0; j < samServices.size(); j++) {
								if (planservices.get(i).getServiceSeq().equals(samServices.get(j).getServiceSeq())){
									samServices.get(j).setIfUsing(planservices.get(i).getIfUsing());
									gridRouteService.addRow(samServices.get(j));
									break;
								}
							}
						}
					}
					
					//初始化检票口
					IEpdPlancheck iEpdPlancheck = new ImpEpdPlancheck();
					List<EpdPlancheck> planchecks = iEpdPlancheck.queryByPlanSeqAndPlanId(epdPlan.getPlanSeq(),epdPlan.getPlanId());
					if (null != planchecks && planchecks.size()>0){
						if (null != checkgates && checkgates.size()>0){
							for (int i = 0; i < checkgates.size(); i++) {
								for (int j = 0; j < planchecks.size(); j++) {
									if (checkgates.get(i).getCheckgateSeq().equals(planchecks.get(j).getCheckgateSeq())){
										gridCheckgate.updateRow(i, checkgates.get(i));
										gridCheckgate.setCheck(i, true);
										break;
									}
								}
								if (epdPlan.getCheckgateSeq().equals(checkgates.get(i).getCheckgateSeq())){
									gridCheckgate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_RED));
									checkgate = checkgates.get(i);
									checkgates.get(i).setDefaultCheck("1");
									gridCheckgate.updateRow(i, checkgates.get(i));
								}
							}
						}
						//planchecks
						List<EpdCheckgate> epdCheckgates = new ArrayList<EpdCheckgate>();
						for (int i = 0; i < planchecks.size(); i++) {
							EpdCheckgate epdCheckgate = new EpdCheckgate();
							epdCheckgate.setCheckgateSeq(planchecks.get(i).getCheckgateSeq());
							epdCheckgates.add(epdCheckgate);
						}
						dropParking.initByCheckCode(epdCheckgates);
						dropParking.droptext.setValue(epdPlan.getParkingSeq());
					}
					//初始化座位
					IEpdPlanseat iEpdPlanseat = new ImpEpdPlanseat();
					List<EpdPlanseat> planseats = iEpdPlanseat.queryByPlanSeqAndPlanId(epdPlan.getPlanSeq(),epdPlan.getPlanId());
					if (null != planseats && planseats.size()>0){
						for (int i = 0; i < planseats.size(); i++) {
							Seat seat = new Seat();
							seat.setSeatSeq(planseats.get(i).getPlanseatSeq());
							seat.setSeatId(String.valueOf(planseats.get(i).getSeatId()));
							seat.setSeatState(planseats.get(i).getSeatState());
							seats.add(seat);
						}
						pageMethod();
					}
				}
				//初始化配载
				cboIfDeal.setText(CommFinal.getItemName(TraffFinal.ARR_IF_DEAL, String.valueOf(epdPlan.getIfDeal())));
				cboIfMain.setText(CommFinal.getItemName(TraffFinal.ARR_IF_MAIN, String.valueOf(epdPlan.getIfDeal())));
				IEpdPlandeal iEpdPlandeal = new ImpEpdPlandeal();
				List<EpdPlandeal> plandeals = iEpdPlandeal.queryByPlanSeqAndPlanId(epdPlan.getPlanSeq(),epdPlan.getPlanId());
				gridDeal.setDataList(plandeals);
				if (null !=  plandeals && plandeals.size()>0){
					List<SamOrganize> samOrganizes = (List<SamOrganize>) gridOrganize.getDataList();
					if (null != samOrganizes && samOrganizes.size()>0){
						for (int i = 0; i < plandeals.size(); i++) {
							for (int j = 0; j < samOrganizes.size(); j++) {
								if (plandeals.get(i).getDealOrganize().equals(samOrganizes.get(j).getOrganizeCode())){
									gridOrganize.deleteRow(j);
									break;
								}
							}
						}
					}
				}
			}
			CallMethod callMethod = new CallMethod();
			callMethod.bindEnterEvent(this, dropRoute.droptext.txtShow, dropCargrade.droptext.txtShow, "setStations");
			callMethod.bindEnterEvent(this, dropCargrade.droptext.txtShow, txtPreDays, "");
			callMethod.bindEnterEvent(this, txtPreDays, cboPlanType, "");
			callMethod.bindEnterEvent(this, cboPlanType, txtPlanId, "");
			callMethod.bindEnterEvent(this, txtPlanId, cboPlanStatus, "");
			callMethod.bindEnterEvent(this, cboPlanStatus, txtFirstTime, "");
			callMethod.bindEnterEvent(this, txtFirstTime, txtPlanOption, "");
			callMethod.bindEnterEvent(this, txtPlanOption, txtPlanTime, "");
			callMethod.bindEnterEvent(this, txtPlanTime, cboIfNetsale, "");
			callMethod.bindEnterEvent(this, cboIfNetsale, txtStartDate.txtDate, "");
			callMethod.bindEnterEvent(this, txtStartDate.txtDate, txtEndDate.txtDate, "");
			callMethod.bindEnterEvent(this, txtEndDate.txtDate, txtRemark, "");
			callMethod.bindEnterEvent(this, txtRemark, null, "");
			
			callMethod.bindEnterEvent(this, dropService.droptext.txtShow, null, "addService");
			
			callMethod.bindEnterEvent(this, txtSeatNum, txtHalfNum, "");
			callMethod.bindEnterEvent(this, txtHalfNum, txtFreeNum, "");
			callMethod.bindEnterEvent(this, txtFreeNum, btnIfPrintseat, "");
			callMethod.bindEnterEvent(this, btnIfPrintseat, null, "");
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
			
			tabFolder.setSelection(0);
			dropRoute.droptext.txtShow.setFocus();
			dropRoute.droptext.txtShow.selectAll();
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void setStations(){
		try {
			if (null != dropRoute.droptext.getValue()){
				IEpdRouteStation iEpdRouteStation = new ImpEpdRouteStation();
				List<EpdRoutestation> routestations;
				routestations = iEpdRouteStation.queryByRouteSeq(dropRoute.droptext.getValue());
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
						stations.add(station);
					}
					stationBar.setData(stations);
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
					EpdPlandeal plandeal = new EpdPlandeal();
					plandeal.setDealOrganize(organizes.get(i).getOrganizeCode());
					plandeal.setOrganizeName(organizes.get(i).getOrganizeName());
					gridDeal.addRow(plandeal);
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
				List<EpdPlandeal> plandeals = (List<EpdPlandeal>) gridDeal.getSelections();
				for (int i = 0; i < plandeals.size(); i++) {
					SamOrganize organize = new SamOrganize();
					organize.setOrganizeCode(plandeals.get(i).getDealOrganize());
					organize.setOrganizeName(plandeals.get(i).getOrganizeName());
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
			seats = new ArrayList<Seat>();
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
	
	public void setObjOne(){
		String planType = cboPlanType.getText();
		if ("隔日".equals(planType)){
			txtPlanOption.setEnabled(true);
			txtPlanOption.forceFocus();
			txtPlanOption.selectAll();
		}else{
			txtPlanOption.setText("");
			txtPlanOption.setEnabled(false);
			txtPlanTime.forceFocus();
		}
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
				service.setIfUsing("1");
				gridRouteService.addRow(service);
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
			int index[] = gridRouteService.getSelectionIndexs();
			if (null != index && index.length>0){
				List<SamService> samServices = (List<SamService>) gridRouteService.getSelections();
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						samServices.get(i).setIfUsing("1");
					}
					gridRouteService.updateRows(index, samServices);
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
			int index[] = gridRouteService.getSelectionIndexs();
			if (null != index && index.length>0){
				List<SamService> samServices = (List<SamService>) gridRouteService.getSelections();
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						samServices.get(i).setIfUsing("0");
					}
					gridRouteService.updateRows(index, samServices);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void upService(){
		gridRouteService.upTableRow();
	}
	
	public void downService(){
		gridRouteService.downTableRow();
	}
	
	public void deleteService(){
		int index[] = gridRouteService.getSelectionIndexs();
		if (null != index && index.length>0){
			gridRouteService.deleteRow(index);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				EpdPlan epdPlan = validData();
				if (null != epdPlan){
					List<EpdCheckgate> checkgates = (List<EpdCheckgate>) gridCheckgate.getCheckSelections();
					if (null == checkgates || checkgates.size()<=0){
						MsgBox.warning(getShell(), "请设置计划检票口！");
						return;
					}
					List<Station> stations = stationBar.getData();
					
					epdPlan.setUsingService(0);
					List<EpdPlanservice> planservices = new ArrayList<EpdPlanservice>();
					List<SamService> samServices = (List<SamService>) gridRouteService.getDataList();
					if (null != samServices && samServices.size()>=0){
						epdPlan.setUsingService(1);
						for (int i = 0; i < samServices.size(); i++) {
							EpdPlanservice epdPlanservice = new EpdPlanservice();
							epdPlanservice.setServiceSeq(samServices.get(i).getServiceSeq());
							epdPlanservice.setServiceOrder(i+1);
							epdPlanservice.setIfUsing(samServices.get(i).getIfUsing());
							epdPlanservice.setUpdateTime(epdPlan.getUpdateTime());
							planservices.add(epdPlanservice);
						}
					}

					int stopNum = 0;
					int reverseNum = 0;
					if (null == seats || seats.size()<=0){
						MsgBox.warning(getShell(), "请确认座位数，未创建计划座位，！");
						return;
					}else{
						for (int i = 0; i < seats.size(); i++) {
							if (seats.get(i).getSeatState()==0){
								stopNum+=1;
							}
							if (seats.get(i).getSeatState()==3){
								reverseNum+=1;
							}
						}
						epdPlan.setStopNum(stopNum);
						epdPlan.setReverseNum(reverseNum);
					}
					List<EpdPlandeal> plandeals = new ArrayList<EpdPlandeal>();
					if (1==epdPlan.getIfDeal()){
						plandeals = (List<EpdPlandeal>) gridDeal.getDataList();
					}
					IEpdPlan iEpdPlan = new ImpEpdPlan();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)||CommFinal.OPER_TYPE_COPY.equals(operType)){
						EpdPlan newEpdPlan = iEpdPlan.insert(epdPlan,checkgates,stations,seats,plandeals,
								planservices,CommFinal.initConfig());
						gridView.addRow(newEpdPlan);
						clearData();
						if(CommFinal.OPER_TYPE_COPY.equals(operType)){
							close();
						}
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdPlan.update(epdPlan,checkgates,stations,seats,plandeals,
								planservices,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdPlan);
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
	
	@SuppressWarnings("unchecked")
	private EpdPlan validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdPlan epdPlan = new EpdPlan();
		epdPlan.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdPlan = (EpdPlan) gridView.getSelection();
		}
		epdPlan.setUpdateTime(currTime);
		if (null != dropRoute.droptext.getValue() && !"".equals(dropRoute.droptext.getValue())){
			epdPlan.setRouteSeq(dropRoute.droptext.getValue());
			epdPlan.setRouteName(dropRoute.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择计划运营线路！");
			dropRoute.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != dropCargrade.droptext.getValue() && !"".equals(dropCargrade.droptext.getValue())){
			epdPlan.setCargradeSeq(dropCargrade.droptext.getValue());
			epdPlan.setCargradeName(dropCargrade.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择计划车型！");
			dropCargrade.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(txtPreDays.getText())){
			epdPlan.setPreDays(Integer.valueOf(txtPreDays.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入计划预售天数！");
			txtPreDays.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(cboPlanType.getText())){
			epdPlan.setPlanType(cboPlanType.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择计划类型！");
			cboPlanType.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(txtPlanId.getText())){
			epdPlan.setPlanId(txtPlanId.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入计划车次！");
			txtPlanId.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(cboPlanStatus.getText())){
			epdPlan.setPlanStatus(cboPlanStatus.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择计划状态！");
			cboPlanStatus.forceFocus();
			return null;
		}
		
		if (cboPlanType.getText().equals("流水班")){
			if (txtFirstTime.getText().trim().length()==5){
				epdPlan.setFirstTime(txtFirstTime.getText());
			}else{
				MsgBox.warning(getShell(), "计划首发时间不合法！");
				txtFirstTime.forceFocus();
				return null;
			}
		}
		
		if (cboPlanType.getText().equals("隔日")){
			if (Validate.StrNotNull(txtPlanOption.getText())){
				epdPlan.setPlanOption(txtPlanOption.getText());
			}else{
				MsgBox.warning(getParentShell(),"请输入隔日天数！");
				txtPlanOption.forceFocus();
				return null;
			}
		}
		
		if (txtPlanTime.getText().trim().length()==5){
			epdPlan.setPlanTime(txtPlanTime.getText());
		}else{
			MsgBox.warning(getShell(), "计划发车时间不合法！");
			txtPlanTime.forceFocus();
			return null;
		}

		if (Validate.isDate(txtStartDate.getText())==true){
			epdPlan.setStartDate(txtStartDate.getText());
		}else{
			MsgBox.warning(getShell(), "计划开始日期不合法！");
			txtStartDate.forceFocus();
			return null;
		}

		if (Validate.isDate(txtEndDate.getText())==true){
			epdPlan.setEndDate(txtEndDate.getText());
		}else{
			MsgBox.warning(getShell(), "计划结束日期不合法！");
			txtEndDate.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(txtSeatNum.getText())){
			epdPlan.setSeatNum(Integer.valueOf(txtSeatNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入计划座位数！");
			txtSeatNum.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(txtHalfNum.getText())){
			epdPlan.setHalfNum(Integer.valueOf(txtHalfNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入计划半票数！");
			txtHalfNum.forceFocus();
			return null;
		}
		
		if (Validate.StrNotNull(txtFreeNum.getText())){
			epdPlan.setFreeNum(Integer.valueOf(txtFreeNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入计划免票数！");
			txtFreeNum.forceFocus();
			return null;
		}
		
		if ("隔日".equals(cboPlanType.getText())){
			if (Validate.StrNotNull(txtPlanOption.getText())){
				epdPlan.setPlanOption(txtPlanOption.getText());
			}else{
				MsgBox.warning(getParentShell(),"请输入计划隔日天数！");
				txtPlanOption.forceFocus();
				return null;
			}
		}
		
		if (btnIfPrintseat.getSelection()){
			epdPlan.setIfPrintseat(1);
		}else{
			epdPlan.setIfPrintseat(0);
		}
		
		if (Validate.StrNotNull(cboIfNetsale.getText())){
			epdPlan.setIfNetsale(Integer.valueOf(CommFinal.getItemValue(TraffFinal.ARR_IF_NETSALE, cboIfNetsale.getText())));
		}else{
			MsgBox.warning(getParentShell(),"请选择计划是否允许网售！");
			cboIfNetsale.forceFocus();
			return null;
		}
		
		epdPlan.setRemark(txtRemark.getText());
		
		if (null!=checkgate){
			epdPlan.setCheckgateSeq(checkgate.getCheckgateSeq());
			epdPlan.setCheckName(checkgate.getCheckName());
		}else{
			MsgBox.warning(getParentShell(),"请设置计划默认检票口！");
			gridCheckgate.forceFocus();
			return null;
		}
		
		if (null != dropParking.droptext.getValue() && dropParking.droptext.getValue().length()>0){
			epdPlan.setParkingSeq(dropParking.droptext.getValue());
		}
		
		if (Validate.StrNotNull(cboIfDeal.getText())){
			epdPlan.setIfDeal(Integer.valueOf(CommFinal.getItemValue(TraffFinal.ARR_IF_DEAL, cboIfDeal.getText())));
			if (1==epdPlan.getIfDeal()){
				if (null != gridDeal.getDataList() && gridDeal.getDataList().size()>0){
					List<EpdPlandeal> plandeals = (List<EpdPlandeal>) gridDeal.getDataList();
					for (int i = 0; i < plandeals.size(); i++) {
						if(Validate.StrNotNull(plandeals.get(i).getDealId())){
						}else{
							MsgBox.warning(getParentShell(),plandeals.get(i).getOrganizeName()+"请输入协议号！");
							tabFolder.setSelection(3);
							return null;
						}
					}
				}else{
					MsgBox.warning(getParentShell(),"请设置计划配载协议！");
					tabFolder.setSelection(3);
					return null;
				}
			}

		}else{
			epdPlan.setIfDeal(0);
			epdPlan.setIfMain(1);
		}
		
		if ("1".equals(epdPlan.getIfDeal())){
			if (Validate.StrNotNull(cboIfMain.getText())){
				epdPlan.setIfMain(Integer.valueOf(CommFinal.getItemValue(TraffFinal.ARR_IF_MAIN, cboIfMain.getText())));
			}else{
				MsgBox.warning(getParentShell(),"请选择计划是否始发！");
				tabFolder.setSelection(3);
				cboIfMain.forceFocus();
				return null;
			}
		}else{
			epdPlan.setIfMain(1);
		}
		return epdPlan;
	}

	@SuppressWarnings("unchecked")
	private void clearData(){
		try {
			dropRoute.droptext.setValue("");
			dropCargrade.droptext.setValue("");
			txtPreDays.setText("");
			cboPlanType.setText("");
			txtPlanId.setText("");
			txtPlanTime.setText("");
			cboPlanStatus.setText("");
			txtStartDate.setText("");
			txtEndDate.setText("");
			txtSeatNum.setText("");
			txtHalfNum.setText("");
			txtFreeNum.setText("");
			txtPlanOption.setText("");
			txtPlanOption.setEnabled(false);
			btnIfPrintseat.setSelection(true);
			cboIfNetsale.setText("");
			txtRemark.setText("");
			cboIfDeal.setText("");
			cboIfMain.setText(CommFinal.getItemName(TraffFinal.ARR_IF_MAIN, "1"));
			cboIfMain.setEnabled(false);
			//初始化站点
			stationBar.clear();
			//初始化检票口
			checkgate=null;
			List<EpdCheckgate> checkgates = (List<EpdCheckgate>) gridCheckgate.getDataList();
			if (null != checkgates && checkgates.size()>0){
				for (int i = 0; i < checkgates.size(); i++) {
					checkgates.get(i).setGateName("");
					gridCheckgate.updateRow(i, checkgates.get(i));
					gridCheckgate.setCheck(i, false);
				}
			}
			//初始化座位
			seatBar.clearSeat();
			dropRoute.droptext.txtShow.forceFocus();
			dropRoute.droptext.txtShow.selectAll();
			//初始化配载
			ISamOrganize iSamOrganize = new ImpSamOrganize();
			gridOrganize.setDataList(iSamOrganize.queryByOrganizeType("2"));
			gridDeal.removeAll();
			tabFolder.setSelection(0);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
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
	
}