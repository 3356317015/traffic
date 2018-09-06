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
import com.zhima.frame.model.SamParameter;
import com.zhima.traffic.action.epd.IEpdCertificate;
import com.zhima.traffic.action.epd.IEpdDriver;
import com.zhima.traffic.action.epd.IEpdDriverinfo;
import com.zhima.traffic.action.epd.impl.ImpEpdCertificate;
import com.zhima.traffic.action.epd.impl.ImpEpdDriver;
import com.zhima.traffic.action.epd.impl.ImpEpdDriverinfo;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.comm.TraffParam;
import com.zhima.traffic.drop.DropCompany;
import com.zhima.traffic.model.EpdCertificate;
import com.zhima.traffic.model.EpdDriver;
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
import com.zhima.widget.grid.EditorOption;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class EpdDriverEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private CTabFolder tabFolder;

	private Text txtDriverName;
	
	private CCombo cboSex;
	
	private Text txtIdNumber;
	
	private DropCompany dropCompany;
	
	private Text txtDrivingType;
	
	private CCalendar txtDrivingValid;
	
	private Text txtPermitNumber;
	
	private CCalendar txtPermitValid;
	
	private Text txtTelephone;
	
	private CImage lbDriverImage;

	private CCombo cboStatus;

	private Text txtRemark;
	
	private GridView gridCertificate;

	protected EpdDriverEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("驾驶员设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(465,410);
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
		
		CTabItem tabDriver = new CTabItem(tabFolder, SWT.BORDER);
		tabDriver.setText("驾驶员信息");
		tabDriver.setControl(createDriver(tabFolder));
		
		CTabItem tabCertificate = new CTabItem(tabFolder, SWT.BORDER);
		tabCertificate.setText("资质信息");
		tabCertificate.setControl(createCertificate(tabFolder));
		initData();
		return compMain;
	}

	private Control createDriver(CTabFolder tabFolder) {
		Composite composite = new Composite(tabFolder, SWT.NONE);
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		composite.setLayout(gridLayout);
		
		KLabel lbDriverName = new KLabel(composite, SWT.RIGHT);
		lbDriverName.setFont(StyleFinal.SYS_FONT);
		lbDriverName.setText("驾驶员姓名:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbDriverName.setLayoutData(gridData);
		
		txtDriverName = new Text(composite, SWT.BORDER);
		txtDriverName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		gridData.widthHint = 110;
		txtDriverName.setLayoutData(gridData);

		KLabel lbSex = new KLabel(composite, SWT.RIGHT);
		lbSex.setFont(StyleFinal.SYS_FONT);
		lbSex.setText("性别:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSex.setLayoutData(gridData);
		
		cboSex = new CCombo(composite, SWT.BORDER);
		cboSex.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboSex.setLayoutData(gridData);

		Label lbCompany = new Label(composite, SWT.RIGHT);
		lbCompany.setFont(StyleFinal.SYS_FONT);
		lbCompany.setText("公司名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCompany.setLayoutData(gridData);
		
		dropCompany = new DropCompany(composite, SWT.BORDER);
		dropCompany.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.horizontalSpan=3;
		dropCompany.setLayoutData(gridData);
		
		KLabel lbIdNumber = new KLabel(composite, SWT.RIGHT);
		lbIdNumber.setFont(StyleFinal.SYS_FONT);
		lbIdNumber.setText("身份证号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbIdNumber.setLayoutData(gridData);
		
		txtIdNumber = new Text(composite, SWT.BORDER);
		txtIdNumber.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIdNumber.setLayoutData(gridData);
		
		lbDriverImage = new CImage(composite, SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=2;
		gridData.verticalSpan=7;
		gridData.heightHint=185;
		lbDriverImage.setLayoutData(gridData);
		
		Label lbTelephone = new Label(composite, SWT.RIGHT);
		lbTelephone.setFont(StyleFinal.SYS_FONT);
		lbTelephone.setText("联系电话:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTelephone.setLayoutData(gridData);
		
		txtTelephone = new Text(composite, SWT.BORDER);
		txtTelephone.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTelephone.setLayoutData(gridData);
		
		KLabel lbDrivingType = new KLabel(composite, SWT.RIGHT);
		lbDrivingType.setFont(StyleFinal.SYS_FONT);
		lbDrivingType.setText("驾驶类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbDrivingType.setLayoutData(gridData);
		
		txtDrivingType = new Text(composite, SWT.BORDER);
		txtDrivingType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtDrivingType.setLayoutData(gridData);
		
		KLabel lbDrivingValid = new KLabel(composite, SWT.RIGHT);
		lbDrivingValid.setFont(StyleFinal.SYS_FONT);
		lbDrivingValid.setText("有效日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbDrivingValid.setLayoutData(gridData);
		
		txtDrivingValid = new CCalendar(composite, SWT.BORDER);
		txtDrivingValid.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtDrivingValid.setLayoutData(gridData);
		
		KLabel lbPermitNumber = new KLabel(composite, SWT.RIGHT);
		lbPermitNumber.setFont(StyleFinal.SYS_FONT);
		lbPermitNumber.setText("从业资格证:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPermitNumber.setLayoutData(gridData);
		
		txtPermitNumber = new Text(composite, SWT.BORDER);
		txtPermitNumber.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPermitNumber.setLayoutData(gridData);
		
		KLabel lbPermitValid = new KLabel(composite, SWT.RIGHT);
		lbPermitValid.setFont(StyleFinal.SYS_FONT);
		lbPermitValid.setText("从业证有效期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPermitValid.setLayoutData(gridData);
		
		txtPermitValid = new CCalendar(composite, SWT.BORDER);
		txtPermitValid.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtPermitValid.setLayoutData(gridData);
		
		KLabel lbStatus = new KLabel(composite, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("驾驶员状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStatus.setLayoutData(gridData);
		
		cboStatus = new CCombo(composite, SWT.BORDER|SWT.READ_ONLY);
		cboStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboStatus.setLayoutData(gridData);

		Label lbRemark = new Label(composite, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(composite, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
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

	private void initData(){
		try {
			SamParameter parameter = CommFinal.getParamValue(TraffParam.WarningDriverDays);
			String currDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
			cboStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_DRIVER_STATUS));
			cboSex.setItems(CommFinal.getAllName(TraffFinal.ARR_SEX));
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				EpdDriver epdDriver = (EpdDriver) gridView.getSelection();
				if (null != epdDriver && !"".equals(epdDriver.getDriverSeq())){
					txtDriverName.setText(epdDriver.getDriverName());
					cboSex.setText(CommFinal.getItemName(TraffFinal.ARR_SEX,epdDriver.getSex()));
					txtIdNumber.setText(epdDriver.getIdNumber());
					dropCompany.droptext.setValue(epdDriver.getDriverCompany());
					txtTelephone.setText(epdDriver.getTelephone());
					txtDrivingType.setText(epdDriver.getDrivingType());
					txtDrivingValid.setText(epdDriver.getDrivingValid());
					txtPermitNumber.setText(epdDriver.getPermitNumber());
					txtPermitValid.setText(epdDriver.getPermitValid());
					lbDriverImage.setImage(ImageUtil.Base64ToBlob(epdDriver.getDriverImage()),80,100);
					cboStatus.setText(CommFinal.getItemName(TraffFinal.ARR_DRIVER_STATUS,epdDriver.getDriverStatus()));
					txtRemark.setText(epdDriver.getRemark());
					if (DateUtils.nDaysBetweenTwoDate(currDate,epdDriver.getDrivingValid())<0){
						txtDrivingValid.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdDriver.getDrivingValid())
							<=Integer.valueOf(parameter.getParameterValue())){
						txtDrivingValid.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
					if (DateUtils.nDaysBetweenTwoDate(currDate,epdDriver.getDrivingValid())<0){
						txtPermitValid.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdDriver.getDrivingValid())
							<=Integer.valueOf(parameter.getParameterValue())){
						txtPermitValid.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
				}
			}
			IEpdCertificate iEpdCertificate = new ImpEpdCertificate();
			List<EpdCertificate> certificates = iEpdCertificate.queryAllByCustom(CommFinal.organize.getOrganizeSeq(),
					"2", null, "1");
			if (null != certificates && certificates.size()>0){
				IEpdDriverinfo iEpdDriverinfo = new ImpEpdDriverinfo();
				List<EpdDriverinfo> driverinfos = new ArrayList<EpdDriverinfo>();
				if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
					EpdDriver epdDriver = (EpdDriver) gridView.getSelection();
					driverinfos = iEpdDriverinfo.queryByDriverSeq(epdDriver.getDriverSeq());
				}
				for (int i = 0; i < certificates.size(); i++) {
					EpdDriverinfo epdDriverinfo = new EpdDriverinfo();
					epdDriverinfo.setCardName(certificates.get(i).getCerName());
					epdDriverinfo.setEndDate(DateUtils.getNow(DateUtils.FORMAT_SHORT));
					epdDriverinfo.setReview("1");
					if (null != driverinfos && driverinfos.size()>0){
						for (int j = 0; j < driverinfos.size(); j++) {
							if (epdDriverinfo.getCardName().equals(driverinfos.get(j).getCardName())){
								BeanUtils.copyProperties(epdDriverinfo, driverinfos.get(j));
								break;
							}
						}
					}
					gridCertificate.addRow(epdDriverinfo);
					if ("1".equals(epdDriverinfo.getReview())){
						gridCertificate.setCheck(i, true);
					}
					if (DateUtils.nDaysBetweenTwoDate(currDate,epdDriverinfo.getEndDate())<0){
						gridCertificate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdDriverinfo.getEndDate())
							<=Integer.valueOf(parameter.getParameterValue())){
						gridCertificate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
				}
			}
			
			CallMethod callMethod = new CallMethod();
			callMethod.bindEnterEvent(this, txtDriverName, cboSex, "");
			callMethod.bindEnterEvent(this, cboSex, dropCompany.droptext.txtShow, "");
			callMethod.bindEnterEvent(this, dropCompany.droptext.txtShow, txtIdNumber, "");
			callMethod.bindEnterEvent(this, txtIdNumber, txtTelephone, "");
			callMethod.bindEnterEvent(this, txtTelephone, txtDrivingType, "");
			callMethod.bindEnterEvent(this, txtDrivingType, txtDrivingValid.txtDate, "");
			callMethod.bindEnterEvent(this, txtDrivingValid.txtDate, txtPermitNumber, "");
			callMethod.bindEnterEvent(this, txtPermitNumber, txtPermitValid.txtDate, "");
			callMethod.bindEnterEvent(this, txtPermitValid.txtDate, cboStatus, "");
			callMethod.bindEnterEvent(this, cboStatus, txtRemark, "");
			callMethod.bindEnterEvent(this, txtRemark, null, "");
			tabFolder.setSelection(0);
			txtDriverName.setFocus();
			txtDriverName.selectAll();
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				EpdDriver epdDriver = validData();
				if (null != epdDriver){
					List<EpdDriverinfo> driverinfos = (List<EpdDriverinfo>) gridCertificate.getDataList();
					if (null != driverinfos && driverinfos.size()>0){
						for (int i = 0; i < driverinfos.size(); i++) {
							if (Validate.isDate(driverinfos.get(i).getEndDate())==false){
								MsgBox.warning(getShell(), "资质信息[" + driverinfos.get(i).getCardName() + "]有效日期不合法！");
								return;
							}
							if (gridCertificate.getCheck(i)){
								driverinfos.get(i).setReview("1");
							}else{
								driverinfos.get(i).setReview("0");
							}
							driverinfos.get(i).setUpdateTime(epdDriver.getUpdateTime());
						}
					}
					IEpdDriver iEpdDriver = new ImpEpdDriver();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdDriver newEpdDriver = iEpdDriver.insert(epdDriver,driverinfos,CommFinal.initConfig());
						gridView.addRow(newEpdDriver);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdDriver.update(epdDriver,driverinfos,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdDriver);
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
	
	private EpdDriver validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdDriver epdDriver = new EpdDriver();
		epdDriver.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdDriver = (EpdDriver) gridView.getSelection();
		}
		epdDriver.setUpdateTime(currTime);
	
		if (null != txtDriverName.getText() && !"".equals(txtDriverName.getText())){
			epdDriver.setDriverName(txtDriverName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入驾驶员姓名！");
			txtDriverName.forceFocus();
			return null;
		}
		
		if (null != cboSex.getText() && !"".equals(cboSex.getText())){
			epdDriver.setSex(CommFinal.getItemValue(TraffFinal.ARR_SEX,cboSex.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择驾驶员性别！");
			cboSex.forceFocus();
			return null;
		}
		
		if (null != txtIdNumber.getText() && !"".equals(txtIdNumber.getText())){
			epdDriver.setIdNumber(txtIdNumber.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入身份证号码！");
			txtIdNumber.forceFocus();
			return null;
		}
		
		if (null != txtDrivingType.getText() && !"".equals(txtDrivingType.getText())){
			epdDriver.setDrivingType(txtDrivingType.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入驾驶类型！");
			txtDrivingType.forceFocus();
			return null;
		}
		
		if (Validate.isDate(txtDrivingValid.getText())==true){
			epdDriver.setDrivingValid(txtDrivingValid.getText());
		}else{
			MsgBox.warning(getShell(), "驾照类型有效日期不合法！");
			txtDrivingValid.forceFocus();
			return null;
		}
		
		if (null != txtPermitNumber.getText() && !"".equals(txtPermitNumber.getText())){
			epdDriver.setPermitNumber(txtPermitNumber.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入从业资格证号码！");
			txtPermitNumber.forceFocus();
			return null;
		}
		
		if (Validate.isDate(txtPermitValid.getText())==true){
			epdDriver.setPermitValid(txtPermitValid.getText());
		}else{
			MsgBox.warning(getShell(), "从业资格证有效日期不合法！");
			txtPermitValid.forceFocus();
			return null;
		}

		if (null != cboStatus.getText() && !"".equals(cboStatus.getText())){
			epdDriver.setDriverStatus(CommFinal.getItemValue(TraffFinal.ARR_DRIVER_STATUS,cboStatus.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择驾驶员状态！");
			cboStatus.forceFocus();
			return null;
		}
		epdDriver.setDriverCode(txtIdNumber.getText());
		epdDriver.setDriverCompany(dropCompany.droptext.getValue());
		epdDriver.setDriverCompanyname(dropCompany.droptext.getText());
		epdDriver.setTelephone(txtTelephone.getText());
		epdDriver.setDriverImage(ImageUtil.blobToBase64(lbDriverImage.getBlob()));
		epdDriver.setDriverStatus(CommFinal.getItemValue(TraffFinal.ARR_DRIVER_STATUS, cboStatus.getText()));
		epdDriver.setRemark(txtRemark.getText());
		return epdDriver;
	}
	

	private void clearData(){
		
		try {
			txtDriverName.setText("");
			cboSex.setText("");
			txtIdNumber.setText("");
			dropCompany.droptext.setValue("");
			txtTelephone.setText("");
			txtDrivingType.setText("");
			txtDrivingValid.setText("");
			txtPermitNumber.setText("");
			txtPermitValid.setText("");
			cboStatus.setText("");
			txtRemark.setText("");
			lbDriverImage.clear();
			IEpdCertificate iEpdCertificate = new ImpEpdCertificate();
			List<EpdCertificate> certificates;
			certificates = iEpdCertificate.queryAllByCustom(CommFinal.organize.getOrganizeSeq(),
					"2", null, "1");
			if (null != certificates && certificates.size()>0){
				SamParameter parameter = CommFinal.getParamValue(TraffParam.WarningDriverDays);
				String currDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
				for (int i = 0; i < certificates.size(); i++) {
					EpdDriverinfo epdDriverinfo = new EpdDriverinfo();
					epdDriverinfo.setCardName(certificates.get(i).getCerName());
					epdDriverinfo.setEndDate(DateUtils.getNow(DateUtils.FORMAT_SHORT));
					epdDriverinfo.setReview("1");
					gridCertificate.addRow(epdDriverinfo);
					if ("1".equals(epdDriverinfo.getReview())){
						gridCertificate.setCheck(i, true);
					}
					if (DateUtils.nDaysBetweenTwoDate(currDate,epdDriverinfo.getEndDate())<0){
						gridCertificate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_RED));
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdDriverinfo.getEndDate())
							<=Integer.valueOf(parameter.getParameterValue())){
						gridCertificate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
				}
			}
			tabFolder.setSelection(0);
			txtDriverName.forceFocus();
			txtDriverName.selectAll();
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		
	}
	
}