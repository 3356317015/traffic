package com.zhima.traffic.ui.epd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamParameter;
import com.zhima.traffic.action.epd.IEpdCar;
import com.zhima.traffic.action.epd.IEpdCardriver;
import com.zhima.traffic.action.epd.IEpdCarinfo;
import com.zhima.traffic.action.epd.IEpdCertificate;
import com.zhima.traffic.action.epd.IEpdDriverinfo;
import com.zhima.traffic.action.epd.impl.ImpEpdCar;
import com.zhima.traffic.action.epd.impl.ImpEpdCardriver;
import com.zhima.traffic.action.epd.impl.ImpEpdCarinfo;
import com.zhima.traffic.action.epd.impl.ImpEpdCertificate;
import com.zhima.traffic.action.epd.impl.ImpEpdDriverinfo;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.comm.TraffParam;
import com.zhima.traffic.drop.DropCargrade;
import com.zhima.traffic.drop.DropCompany;
import com.zhima.traffic.drop.DropContract;
import com.zhima.traffic.drop.DropDriver;
import com.zhima.traffic.drop.DropRoute;
import com.zhima.traffic.model.EpdCar;
import com.zhima.traffic.model.EpdCardriver;
import com.zhima.traffic.model.EpdCarinfo;
import com.zhima.traffic.model.EpdCertificate;
import com.zhima.traffic.model.EpdDriverinfo;
import com.zhima.util.DateUtils;
import com.zhima.util.ImageUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.Validate;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CCalendar;
import com.zhima.widget.CImage;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.EditorOption;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class EpdCarEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	private Object obj;
	
	private CTabFolder tabFolder;
	
	private Text txtCarNumber;
	
	private CCalendar txtBuyDate;
	
	private Text txtCarCode;
	
	private Text txtCarId;
	
	private Text txtSeatNum;
	
	private DropCargrade dropCargrade;
	
	private CCalendar txtCargradeValid;

	private Text txtQuasiSeat;
	private Text txtCheckDays;
	private Text txtSafecarValid;
	private CCombo cboSafecarType;
	private Text txtSafecerValid;
	private CCombo cboSafecerType;
	private DropRoute dropRoute;
	private DropContract dropContract;
	
	private DropCompany dropCompany;
	private DropCompany dropCarCompany;
	
	private CImage lbCarImage;

	private CCombo cboCarStatus;

	private Text txtRemark;

	private GridView gridCertificate;
	
	private DropDriver dropDriver;
	private GridView gridDriver;
	

	
	private List<EpdCardriver> delCardrivers = new ArrayList<EpdCardriver>();
	
	protected EpdCarEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.obj = this;
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("车辆设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(465,470);
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
		
		CTabItem tabCar = new CTabItem(tabFolder, SWT.BORDER);
		tabCar.setText("车辆信息");
		tabCar.setControl(createCar(tabFolder));
		
		CTabItem tabCertificate = new CTabItem(tabFolder, SWT.BORDER);
		tabCertificate.setText("资质信息");
		tabCertificate.setControl(createCertificate(tabFolder));
		
		CTabItem tabDriver = new CTabItem(tabFolder, SWT.BORDER);
		tabDriver.setText("驾驶员");
		tabDriver.setControl(createDriver(tabFolder));
		initData();
		return compMain;
	}

	private Control createCar(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder,SWT.NONE);
		composite.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		composite.setLayout(gridLayout);
		
		KLabel lbCarNumber = new KLabel(composite, SWT.RIGHT);
		lbCarNumber.setFont(StyleFinal.SYS_FONT);
		lbCarNumber.setText("车牌号:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbCarNumber.setLayoutData(gridData);
		
		txtCarNumber = new Text(composite, SWT.BORDER);
		txtCarNumber.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		
		txtCarNumber.setLayoutData(gridData);

		KLabel lbBuyDate = new KLabel(composite, SWT.RIGHT);
		lbBuyDate.setFont(StyleFinal.SYS_FONT);
		lbBuyDate.setText("购车日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbBuyDate.setLayoutData(gridData);
		
		txtBuyDate = new CCalendar(composite, SWT.BORDER);
		txtBuyDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint=120;
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtBuyDate.setLayoutData(gridData);
		
		KLabel lbCarCode = new KLabel(composite, SWT.RIGHT);
		lbCarCode.setFont(StyleFinal.SYS_FONT);
		lbCarCode.setText("车辆编号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbCarCode.setLayoutData(gridData);
		
		txtCarCode = new Text(composite, SWT.BORDER);
		txtCarCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCarCode.setLayoutData(gridData);
		
		Label lbCarId = new Label(composite, SWT.RIGHT);
		lbCarId.setFont(StyleFinal.SYS_FONT);
		lbCarId.setText("车辆卡号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbCarId.setLayoutData(gridData);
		
		txtCarId = new Text(composite, SWT.BORDER);
		txtCarId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCarId.setLayoutData(gridData);
		
		KLabel lbSeatNum = new KLabel(composite, SWT.RIGHT);
		lbSeatNum.setFont(StyleFinal.SYS_FONT);
		lbSeatNum.setText("座位数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbSeatNum.setLayoutData(gridData);
		
		txtSeatNum = new Text(composite, SWT.BORDER);
		txtSeatNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSeatNum.setLayoutData(gridData);
		txtSeatNum.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbQuasiSeat = new KLabel(composite, SWT.RIGHT);
		lbQuasiSeat.setFont(StyleFinal.SYS_FONT);
		lbQuasiSeat.setText("准座数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbQuasiSeat.setLayoutData(gridData);
		
		txtQuasiSeat = new Text(composite, SWT.BORDER);
		txtQuasiSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtQuasiSeat.setLayoutData(gridData);
		txtQuasiSeat.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbRoute = new KLabel(composite, SWT.RIGHT);
		lbRoute.setFont(StyleFinal.SYS_FONT);
		lbRoute.setText("运营线路:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbRoute.setLayoutData(gridData);
		
		dropRoute = new DropRoute(composite, SWT.BORDER);
		dropRoute.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropRoute.setLayoutData(gridData);
		
		KLabel lbContract = new KLabel(composite, SWT.RIGHT);
		lbContract.setFont(StyleFinal.SYS_FONT);
		lbContract.setText("运营合同:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbContract.setLayoutData(gridData);
		
		dropContract = new DropContract(composite, SWT.BORDER);
		dropContract.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropContract.setLayoutData(gridData);
		
		KLabel lbCargrade = new KLabel(composite, SWT.RIGHT);
		lbCargrade.setFont(StyleFinal.SYS_FONT);
		lbCargrade.setText("车型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbCargrade.setLayoutData(gridData);
		
		dropCargrade = new DropCargrade(composite, SWT.BORDER);
		dropCargrade.initAll();
		dropCargrade.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropCargrade.setLayoutData(gridData);
		
		KLabel lbCargradeValid = new KLabel(composite, SWT.RIGHT);
		lbCargradeValid.setFont(StyleFinal.SYS_FONT);
		lbCargradeValid.setText("有效日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCargradeValid.setLayoutData(gridData);
		
		txtCargradeValid = new CCalendar(composite, SWT.BORDER);
		txtCargradeValid.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtCargradeValid.setLayoutData(gridData);
		
		KLabel lbCompany = new KLabel(composite, SWT.RIGHT);
		lbCompany.setFont(StyleFinal.SYS_FONT);
		lbCompany.setText("结算公司:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbCompany.setLayoutData(gridData);
		
		dropCompany = new DropCompany(composite, SWT.BORDER);
		dropCompany.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.horizontalSpan=3;
		dropCompany.setLayoutData(gridData);
		
		KLabel lbCarCompany = new KLabel(composite, SWT.RIGHT);
		lbCarCompany.setFont(StyleFinal.SYS_FONT);
		lbCarCompany.setText("车属公司:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbCarCompany.setLayoutData(gridData);
		
		dropCarCompany = new DropCompany(composite, SWT.BORDER);
		dropCarCompany.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.horizontalSpan=3;
		dropCarCompany.setLayoutData(gridData);
		
		KLabel lbCheckDays = new KLabel(composite, SWT.RIGHT);
		lbCheckDays.setFont(StyleFinal.SYS_FONT);
		lbCheckDays.setText("保养日:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbCheckDays.setLayoutData(gridData);
		
		txtCheckDays = new Text(composite, SWT.BORDER);
		txtCheckDays.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCheckDays.setLayoutData(gridData);
		
		lbCarImage = new CImage(composite, SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=2;
		gridData.verticalSpan=4;
		gridData.heightHint=105;
		lbCarImage.setLayoutData(gridData);
		
		KLabel lbSafecarValid = new KLabel(composite, SWT.RIGHT);
		lbSafecarValid.setFont(StyleFinal.SYS_FONT);
		lbSafecarValid.setText("安检有效:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbSafecarValid.setLayoutData(gridData);
		
		Composite compSafecar = new Composite(composite, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		compSafecar.setLayoutData(gridData);
		compSafecar.setLayout(new FormLayout());
		
		txtSafecarValid = new Text(compSafecar, SWT.BORDER);
		txtSafecarValid.setFont(StyleFinal.SYS_FONT);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100, -80);
		formData.bottom = new FormAttachment(100);
		txtSafecarValid.setLayoutData(formData);
		txtSafecarValid.addVerifyListener(new TextVerifyListener(1));
		
		cboSafecarType = new CCombo(compSafecar, SWT.BORDER|SWT.READ_ONLY);
		cboSafecarType.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(txtSafecarValid,5);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		cboSafecarType.setLayoutData(formData);

		KLabel lbSafecerValid = new KLabel(composite, SWT.RIGHT);
		lbSafecerValid.setFont(StyleFinal.SYS_FONT);
		lbSafecerValid.setText("证检有效:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbSafecerValid.setLayoutData(gridData);
		
		Composite compSafecer = new Composite(composite, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		compSafecer.setLayoutData(gridData);
		compSafecer.setLayout(new FormLayout());
		
		txtSafecerValid = new Text(compSafecer, SWT.BORDER);
		txtSafecerValid.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100, -80);
		formData.bottom = new FormAttachment(100);
		txtSafecerValid.setLayoutData(formData);
		txtSafecerValid.addVerifyListener(new TextVerifyListener(1));
		
		cboSafecerType = new CCombo(compSafecer, SWT.BORDER|SWT.READ_ONLY);
		cboSafecerType.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(txtSafecerValid,5);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		cboSafecerType.setLayoutData(formData);

		KLabel lbCarStatus = new KLabel(composite, SWT.RIGHT);
		lbCarStatus.setFont(StyleFinal.SYS_FONT);
		lbCarStatus.setText("车辆状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbCarStatus.setLayoutData(gridData);
		
		cboCarStatus = new CCombo(composite, SWT.BORDER|SWT.READ_ONLY);
		cboCarStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboCarStatus.setLayoutData(gridData);
		
		Label lbRemark = new Label(composite, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(composite, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		gridData.horizontalSpan=3;
		txtRemark.setLayoutData(gridData);
		return composite;
	}
	
	private Control createCertificate(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new FillLayout());
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("证件名称","cardName",135));
		columns.add(new GridColumn("证件号","cardCode",180,"Text",new EditorOption()));
		columns.add(new GridColumn("有效日期","endDate",110,"DateBox",new EditorOption()));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowSeq(false);
		gridConfig.setCheck(true);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridCertificate = new GridView(composite, SWT.NONE);
		gridCertificate.CreateTabel(gridConfig);
		return composite;
	}
	
	private Control createDriver(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new FormLayout());
		Label lbDriver = new Label(composite, SWT.NONE);
		lbDriver.setText("驾驶员:");
		lbDriver.setFont(StyleFinal.SYS_FONT);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0, 10);
		formData.left= new FormAttachment(0, 5);
		lbDriver.setLayoutData(formData);
		
		dropDriver = new DropDriver(composite, SWT.BORDER);
		dropDriver.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0, 8);
		formData.left= new FormAttachment(lbDriver, 5);
		formData.width = 160;
		dropDriver.setLayoutData(formData);

		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("驾驶员","driverName",100));
		columns.add(new GridColumn("性别","sex",TraffFinal.ARR_SEX,80));
		columns.add(new GridColumn("身份证","idNumber",200));
		columns.add(new GridColumn("驾照类型","drivingType",100));
		columns.add(new GridColumn("有效日期","drivingValid",100));
		columns.add(new GridColumn("联系电话","telephone",120));
		columns.add(new GridColumn("公司名称","driverCompanyname",160));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowSeq(false);
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(initRight());
		gridConfig.setObj(obj);
		gridDriver = new GridView(composite, SWT.NONE);
		gridDriver.CreateTabel(gridConfig);
		formData = new FormData();
		formData.top = new FormAttachment(lbDriver, 10);
		formData.left= new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		gridDriver.setLayoutData(formData);
		return composite;
	}
	
	private List<SamModuleRight> initRight() {
		List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
		SamModuleRight moduleRight = new SamModuleRight();
		moduleRight.setRightName("删除(&R)");
		moduleRight.setRightMethod("deleteDriver");
		rights.add(moduleRight);
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("上移(&U)");
		moduleRight.setRightMethod("upDriver");
		rights.add(moduleRight);
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("下移(&D)");
		moduleRight.setRightMethod("downDriver");
		rights.add(moduleRight);
		return rights;
	}

	private void initData(){
		try {
			SamParameter parameter = CommFinal.getParamValue(TraffParam.WarningCarDays);
			String currDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
			cboSafecarType.setItems(CommFinal.getAllName(TraffFinal.ARR_SAFECAR_TYPE));
			cboSafecerType.setItems(CommFinal.getAllName(TraffFinal.ARR_SAFECER_TYPE));
			cboCarStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_CAR_STATUS));
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				EpdCar epdCar = (EpdCar) gridView.getSelection();
				if (null != epdCar && !"".equals(epdCar.getCarSeq())){
					txtCarNumber.setText(epdCar.getCarNumber());
					txtBuyDate.setText(epdCar.getBuyDate());
					txtCarCode.setText(epdCar.getCarCode());
					txtCarId.setText(epdCar.getCarId());
					txtSeatNum.setText(epdCar.getSeatNum().toString());
					txtQuasiSeat.setText(epdCar.getQuasiNum().toString());
					dropRoute.droptext.setValue(epdCar.getRouteSeq());
					dropContract.droptext.setValue(epdCar.getContractSeq());
					dropCargrade.droptext.setValue(epdCar.getCargradeSeq());
					txtCargradeValid.setText(epdCar.getCargradeValid());
					dropCompany.droptext.setValue(epdCar.getCompanySeq());
					dropCarCompany.droptext.setValue(epdCar.getCarCompany());
					txtCheckDays.setText(epdCar.getCheckDays());
					
					lbCarImage.setImage(ImageUtil.Base64ToBlob(epdCar.getCarImage()), 200, 100);
					txtSafecarValid.setText(epdCar.getSafecarValid().toString());
					cboSafecarType.setText(CommFinal.getItemName(TraffFinal.ARR_SAFECAR_TYPE, epdCar.getSafecarType()));
					txtSafecerValid.setText(epdCar.getSafecerValid().toString());
					cboSafecerType.setText(CommFinal.getItemName(TraffFinal.ARR_SAFECER_TYPE, epdCar.getSafecerType()));
					cboCarStatus.setText(CommFinal.getItemName(TraffFinal.ARR_CAR_STATUS,epdCar.getCarStatus()));
					txtRemark.setText(epdCar.getRemark());
					if (DateUtils.nDaysBetweenTwoDate(currDate,epdCar.getCargradeValid())<0){
						txtCargradeValid.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdCar.getCargradeValid())
							<=Integer.valueOf(parameter.getParameterValue())){
						txtCargradeValid.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
				}
				//初始化车辆驾驶员
				IEpdCardriver iEpdCardriver = new ImpEpdCardriver();
				List<EpdCardriver> cardrivers = iEpdCardriver.queryByCarSeq(epdCar.getCarSeq());
				gridDriver.setDataList(cardrivers);
				IEpdDriverinfo iEpdDriverinfo = new ImpEpdDriverinfo();
				SamParameter samParameter = CommFinal.getParamValue(TraffParam.WarningDriverDays);
				for (int j = 0; j < cardrivers.size(); j++) {
					if (DateUtils.nDaysBetweenTwoDate(currDate,cardrivers.get(j).getDrivingValid())<0){
						gridDriver.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
						continue;
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,cardrivers.get(j).getDrivingValid())
							<=Integer.valueOf(samParameter.getParameterValue())){
						gridDriver.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
					List<EpdDriverinfo> driverinfos = iEpdDriverinfo.queryByDriverSeq(cardrivers.get(j).getDriverSeq());
					if(null != driverinfos && driverinfos.size()>0){
						for (int k = 0; k < driverinfos.size(); k++) {
							if (DateUtils.nDaysBetweenTwoDate(currDate,driverinfos.get(k).getEndDate())<0){
								gridDriver.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
								break;
							}else if(DateUtils.nDaysBetweenTwoDate(currDate,driverinfos.get(k).getEndDate())
									<=Integer.valueOf(samParameter.getParameterValue())){
								gridDriver.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
							}
						}
					}
				}
			}
			//初始化车辆资质项
			IEpdCertificate iEpdCertificate = new ImpEpdCertificate();
			List<EpdCertificate> certificates = iEpdCertificate.queryAllByCustom(CommFinal.organize.getOrganizeSeq(),
					"1", null, "1");
			if (null != certificates && certificates.size()>0){
				IEpdCarinfo iEpdCarinfo = new ImpEpdCarinfo();
				List<EpdCarinfo> carinfos = new ArrayList<EpdCarinfo>();
				if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
					EpdCar epdCar = (EpdCar) gridView.getSelection();
					carinfos = iEpdCarinfo.queryByCarSeq(epdCar.getCarSeq());
				}
				for (int i = 0; i < certificates.size(); i++) {
					EpdCarinfo epdCarinfo = new EpdCarinfo();
					epdCarinfo.setCardName(certificates.get(i).getCerName());
					epdCarinfo.setEndDate(DateUtils.getNow(DateUtils.FORMAT_SHORT));
					epdCarinfo.setReview("1");
					if (null != carinfos && carinfos.size()>0){
						for (int j = 0; j < carinfos.size(); j++) {
							if (epdCarinfo.getCardName().equals(carinfos.get(j).getCardName())){
								BeanUtils.copyProperties(epdCarinfo, carinfos.get(j));
								break;
							}
						}
					}
					gridCertificate.addRow(epdCarinfo);
					if ("1".equals(epdCarinfo.getReview())){
						gridCertificate.setCheck(i, true);
					}
					if (DateUtils.nDaysBetweenTwoDate(currDate,epdCarinfo.getEndDate())<0){
						gridCertificate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdCarinfo.getEndDate())
							<=Integer.valueOf(parameter.getParameterValue())){
						gridCertificate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
				}
			}
			
			CallMethod callMethod = new CallMethod();
			callMethod.bindEnterEvent(this, txtCarNumber, txtBuyDate.txtDate, "setCarCode");
			callMethod.bindEnterEvent(this, txtBuyDate.txtDate, txtCarCode, "");
			callMethod.bindEnterEvent(this, txtCarCode, txtCarId, "");
			callMethod.bindEnterEvent(this, txtCarId, txtSeatNum, "");
			callMethod.bindEnterEvent(this, txtSeatNum, txtQuasiSeat, "");
			callMethod.bindEnterEvent(this, txtQuasiSeat, dropRoute.droptext.txtShow, "");
			callMethod.bindEnterEvent(this, dropRoute.droptext.txtShow, dropContract.droptext.txtShow, "");
			callMethod.bindEnterEvent(this, dropContract.droptext.txtShow, dropCargrade.droptext.txtShow, "");
			callMethod.bindEnterEvent(this, dropCargrade.droptext.txtShow, txtCargradeValid.txtDate, "");
			callMethod.bindEnterEvent(this, txtCargradeValid.txtDate, dropCompany.droptext.txtShow, "");
			callMethod.bindEnterEvent(this, dropCompany.droptext.txtShow, dropCarCompany.droptext.txtShow, "");
			callMethod.bindEnterEvent(this, dropCarCompany.droptext.txtShow, txtCheckDays, "");
			callMethod.bindEnterEvent(this, txtCheckDays, txtSafecarValid, "");
			callMethod.bindEnterEvent(this, txtSafecarValid, cboSafecarType, "");
			callMethod.bindEnterEvent(this, cboSafecarType, txtSafecerValid, "");
			callMethod.bindEnterEvent(this, txtSafecerValid, cboSafecerType, "");
			callMethod.bindEnterEvent(this, cboSafecerType, cboCarStatus, "");
			callMethod.bindEnterEvent(this, cboCarStatus, txtRemark, "");
			callMethod.bindEnterEvent(this, txtRemark, null, "");
			callMethod.bindEnterEvent(this, dropDriver.droptext.txtShow, null, "addDriver");
			tabFolder.setSelection(0);
			txtCarNumber.setFocus();
			txtCarNumber.selectAll();
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addDriver(){
		if (null != dropDriver.droptext.getValue() && dropDriver.droptext.getValue().length()>0){
			try {
				List<EpdCardriver> cardrivers = (List<EpdCardriver>) gridDriver.getDataList();
				IEpdDriverinfo iEpdDriverinfo = new ImpEpdDriverinfo();
				SamParameter samParameter = CommFinal.getParamValue(TraffParam.WarningDriverDays);
				String currDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
				if (null != cardrivers && cardrivers.size()>0){
					for (int i = 0; i < cardrivers.size(); i++) {
						if (dropDriver.droptext.getValue().equals(cardrivers.get(i).getDriverSeq())){
							MsgBox.warning(getShell(), "驾驶员已存在，不允许重复添加。");
							return;
						}
					}
				}
				boolean isAdd = true;
				if (null != delCardrivers && delCardrivers.size()>0){
					for (int i = 0; i < delCardrivers.size(); i++) {
						if (dropDriver.droptext.getValue().equals(delCardrivers.get(i).getDriverSeq())){
							gridDriver.addRow(delCardrivers.get(i));
							delCardrivers.remove(i);
							isAdd = false;
							break;
						}
					}
				}
				if(isAdd==true){
					EpdCardriver epdCardriver = new EpdCardriver();
					BeanUtils.copyProperties(epdCardriver, dropDriver.droptext.getObject());
					gridDriver.addRow(epdCardriver);
				}
				cardrivers = (List<EpdCardriver>) gridDriver.getDataList();
				for (int j = 0; j < cardrivers.size(); j++) {
					if (DateUtils.nDaysBetweenTwoDate(currDate,cardrivers.get(j).getDrivingValid())<0){
						gridDriver.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
						continue;
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,cardrivers.get(j).getDrivingValid())
							<=Integer.valueOf(samParameter.getParameterValue())){
						gridDriver.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
					List<EpdDriverinfo> driverinfos = iEpdDriverinfo.queryByDriverSeq(cardrivers.get(j).getDriverSeq());
					if(null != driverinfos && driverinfos.size()>0){
						for (int k = 0; k < driverinfos.size(); k++) {
							if (DateUtils.nDaysBetweenTwoDate(currDate,driverinfos.get(k).getEndDate())<0){
								gridDriver.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
								break;
							}else if(DateUtils.nDaysBetweenTwoDate(currDate,driverinfos.get(k).getEndDate())
									<=Integer.valueOf(samParameter.getParameterValue())){
								gridDriver.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
							}
						}
					}
				}
			} catch (Exception e) {
				LogUtil.operLog(e,"E",true,true);
			}
		}
		dropDriver.droptext.txtShow.forceFocus();
		dropDriver.droptext.txtShow.selectAll();
	}
	
	@SuppressWarnings("unchecked")
	public void deleteDriver(){
		int index[] = gridDriver.getSelectionIndexs();
		if (null != index && index.length>0){
			List<EpdCardriver> cardrivers = (List<EpdCardriver>) gridDriver.getSelections();
			for (int i = 0; i < cardrivers.size(); i++) {
				if (null != cardrivers.get(i).getCardriverSeq()
						&& cardrivers.get(i).getCardriverSeq().length()>0){
					delCardrivers.add(cardrivers.get(i));
				}
			}
			gridDriver.deleteRow(index);
		}
	}
	
	public void upDriver(){
		gridDriver.upTableRow();
	}
	
	public void downDriver(){
		gridDriver.downTableRow();
	}
	
	public void setCarCode(){
		if (null != txtCarNumber.getText() && txtCarNumber.getText().length()>0){
			txtCarCode.setText(txtCarNumber.getText().substring(1));
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				EpdCar epdCar = validData();
				if (null != epdCar){
					List<EpdCarinfo> carinfos = (List<EpdCarinfo>) gridCertificate.getDataList();
					if (null != carinfos && carinfos.size()>0){
						for (int i = 0; i < carinfos.size(); i++) {
							if (Validate.isDate(carinfos.get(i).getEndDate())==false){
								MsgBox.warning(getShell(), "资质信息[" + carinfos.get(i).getCardName() + "]有效日期不合法！");
								return;
							}
							if (gridCertificate.getCheck(i)){
								carinfos.get(i).setReview("1");
							}else{
								carinfos.get(i).setReview("0");
							}
							carinfos.get(i).setUpdateTime(epdCar.getUpdateTime());
						}
					}
					List<EpdCardriver> cardrivers = (List<EpdCardriver>) gridDriver.getDataList();
					if (null != cardrivers && cardrivers.size()>0){
						for (int i = 0; i < cardrivers.size(); i++) {
							cardrivers.get(i).setDriverOrder(i+1);
							cardrivers.get(i).setUpdateTime(epdCar.getUpdateTime());
						}
					}
					IEpdCar iEpdCar = new ImpEpdCar();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdCar newEpdCar = iEpdCar.insert(epdCar,carinfos,cardrivers,CommFinal.initConfig());
						gridView.addRow(newEpdCar);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdCar.update(epdCar,carinfos,cardrivers,delCardrivers,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdCar);
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
	
	private EpdCar validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdCar epdCar = new EpdCar();
		epdCar.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdCar = (EpdCar) gridView.getSelection();
		}
		epdCar.setUpdateTime(currTime);
		if (null != txtCarNumber.getText() && !"".equals(txtCarNumber.getText())){
			epdCar.setCarNumber(txtCarNumber.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入车牌号码！");
			txtCarNumber.forceFocus();
			return null;
		}
		
		if (Validate.isDate(txtBuyDate.getText())==true){
			epdCar.setBuyDate(txtBuyDate.getText());
		}else{
			MsgBox.warning(getShell(), "购车日期不合法！");
			txtBuyDate.forceFocus();
			return null;
		}
		
		if (null != txtCarCode.getText() && !"".equals(txtCarCode.getText())){
			epdCar.setCarCode(txtCarCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入车牌编号！");
			txtCarCode.forceFocus();
			return null;
		}
		
		if (null != txtSeatNum.getText() && !"".equals(txtSeatNum.getText())){
			epdCar.setSeatNum(Integer.valueOf(txtSeatNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入座位数！");
			txtSeatNum.forceFocus();
			return null;
		}
		
		if (null != txtQuasiSeat.getText() && !"".equals(txtQuasiSeat.getText())){
			epdCar.setQuasiNum(Integer.valueOf(txtQuasiSeat.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入准座数！");
			txtQuasiSeat.forceFocus();
			return null;
		}
		
		if (null != dropRoute.droptext.getValue() && !"".equals(dropRoute.droptext.getValue())){
			epdCar.setRouteSeq(dropRoute.droptext.getValue());
			epdCar.setRouteName(dropRoute.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择运营线路！");
			dropRoute.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != dropContract.droptext.getValue() && !"".equals(dropContract.droptext.getValue())){
			epdCar.setContractSeq(dropContract.droptext.getValue());
			epdCar.setContractName(dropContract.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择运营合同！");
			dropContract.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != dropCargrade.droptext.getValue() && !"".equals(dropCargrade.droptext.getValue())){
			epdCar.setCargradeSeq(dropCargrade.droptext.getValue());
			epdCar.setCargradeName(dropCargrade.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择车型！");
			dropCargrade.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (Validate.isDate(txtCargradeValid.getText())==true){
			epdCar.setCargradeValid(txtCargradeValid.getText());
		}else{
			MsgBox.warning(getShell(), "车型有效日期不合法！");
			txtCargradeValid.forceFocus();
			return null;
		}

		if (null != dropCompany.droptext.getValue() && !"".equals(dropCompany.droptext.getValue())){
			epdCar.setCompanySeq(dropCompany.droptext.getValue());
			epdCar.setCompanyName(dropCompany.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入结算公司！");
			dropCompany.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != dropCarCompany.droptext.getValue() && !"".equals(dropCarCompany.droptext.getValue())){
			epdCar.setCarCompany(dropCarCompany.droptext.getValue());
			epdCar.setCarCompanyname(dropCarCompany.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入车属公司！");
			dropCarCompany.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != txtCheckDays.getText() && !"".equals(txtCheckDays.getText())){
			epdCar.setCheckDays(txtCheckDays.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入保养日！");
			txtCheckDays.forceFocus();
			return null;
		}
		
		if (null != txtSafecarValid.getText() && !"".equals(txtSafecarValid.getText())){
			epdCar.setSafecarValid(Integer.valueOf(txtSafecarValid.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入安检有效期！");
			txtSafecarValid.forceFocus();
			return null;
		}
		if (null != cboSafecarType.getText() && !"".equals(cboSafecarType.getText())){
			epdCar.setSafecarType(CommFinal.getItemValue(TraffFinal.ARR_SAFECAR_TYPE, cboSafecarType.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入安检有效期类型！");
			cboSafecarType.forceFocus();
			return null;
		}
		
		if (null != txtSafecerValid.getText() && !"".equals(txtSafecerValid.getText())){
			epdCar.setSafecerValid(Integer.valueOf(txtSafecerValid.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入证检有效期！");
			txtSafecerValid.forceFocus();
			return null;
		}
		if (null != cboSafecerType.getText() && !"".equals(cboSafecerType.getText())){
			epdCar.setSafecerType(CommFinal.getItemValue(TraffFinal.ARR_SAFECAR_TYPE, cboSafecerType.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入证检有效期类型！");
			cboSafecerType.forceFocus();
			return null;
		}
		
		if (null != cboCarStatus.getText() && !"".equals(cboCarStatus.getText())){
			epdCar.setCarStatus(CommFinal.getItemValue(TraffFinal.ARR_CAR_STATUS,cboCarStatus.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择车辆状态！");
			cboCarStatus.forceFocus();
			return null;
		}
		epdCar.setCarId(txtCarId.getText());
		epdCar.setCarImage(ImageUtil.blobToBase64(lbCarImage.getBlob()));	
		epdCar.setRemark(txtRemark.getText());
		return epdCar;
	}

	private void clearData(){
		try {
			txtCarNumber.setText("");
			txtBuyDate.setText("");
			txtCarCode.setText("");
			txtCarId.setText("");
			txtSeatNum.setText("");
			txtQuasiSeat.setText("");
			dropRoute.droptext.setValue("");
			dropContract.droptext.setValue("");
			dropCargrade.droptext.setValue("");
			txtCargradeValid.setText("");
			dropCompany.droptext.setValue("");
			dropCarCompany.droptext.setValue("");
			txtCheckDays.setText("");
			txtSafecarValid.setText("");
			cboSafecarType.setText("");
			txtSafecerValid.setText("");
			cboSafecerType.setText("");
			cboCarStatus.setText("");
			txtRemark.setText("");
			lbCarImage.clear();
			//清空车辆资质信息
			SamParameter parameter = CommFinal.getParamValue(TraffParam.WarningCarDays);
			String currDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
			gridCertificate.removeAll();
			IEpdCertificate iEpdCertificate = new ImpEpdCertificate();
			List<EpdCertificate> certificates = iEpdCertificate.queryAllByCustom(CommFinal.organize.getOrganizeSeq(),
					"1", null, "1");
			if (null != certificates && certificates.size()>0){
				for (int i = 0; i < certificates.size(); i++) {
					EpdCarinfo epdCarinfo = new EpdCarinfo();
					epdCarinfo.setCardName(certificates.get(i).getCerName());
					epdCarinfo.setEndDate(DateUtils.getNow(DateUtils.FORMAT_SHORT));
					epdCarinfo.setReview("1");
					gridCertificate.addRow(epdCarinfo);
					if ("1".equals(epdCarinfo.getReview())){
						gridCertificate.setCheck(i, true);
					}
					if (DateUtils.nDaysBetweenTwoDate(currDate,epdCarinfo.getEndDate())<0){
						gridCertificate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdCarinfo.getEndDate())
							<=Integer.valueOf(parameter.getParameterValue())){
						gridCertificate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
				}
			}
			//清空驾驶员
			dropDriver.droptext.setValue("");
			gridDriver.removeAll();
			tabFolder.setSelection(0);
			txtCarNumber.forceFocus();
			txtCarNumber.selectAll();
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
}