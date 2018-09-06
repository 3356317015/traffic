package com.zhima.traffic.ui.epd;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropCompany;
import com.zhima.util.SWTResourceManager;

public class EpdDriverFindUi extends Dialog {
	private int btnId = 0;
	
	private Text txtIdNumber;
	private String fIdNumber = "";

	private Text txtDriver;
	private String fDriver = "";
	
	private DropCompany dropCompany;
	private String fCompany = "";
	
	private CCombo comStatus;
	private String fStatus = "";

	public EpdDriverFindUi(Shell shell) {
		super(shell);
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("查询条件设置");
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,225);
    }
	
	protected void createButtonsForButtonBar(Composite parent){
		GridData btnData = new GridData(StyleFinal.DIALOGBAR_ALIGNMENT);
		parent.setLayoutData(btnData);
		Button confirm = createButton(parent, 1, "查询(&Q)", false);
		confirm.setFont(StyleFinal.SYS_FONT);
		Button cancel = createButton(parent, 0, "取消(&C)", false);
		cancel.setFont(StyleFinal.SYS_FONT);
		if (StyleFinal.BTN_SHOWIMAGE == true){
			confirm.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_FIND));
			cancel.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_CANCEL));
		}
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite compMain = (Composite) super.createDialogArea(parent);
		compMain.setLayout(new FormLayout());
		CallMethod callMethod = new CallMethod();
		
		Group groupMain = new Group(compMain,SWT.NONE);
		groupMain.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		Label lbIdNumber = new Label(groupMain, SWT.RIGHT);
		lbIdNumber.setFont(StyleFinal.SYS_FONT);
		lbIdNumber.setText("身份证:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbIdNumber.setLayoutData(gridData);
		
		txtIdNumber = new Text(groupMain, SWT.BORDER);
		txtIdNumber.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIdNumber.setLayoutData(gridData);

		Label lbDriver = new Label(groupMain, SWT.RIGHT);
		lbDriver.setFont(StyleFinal.SYS_FONT);
		lbDriver.setText("驾驶员:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbDriver.setLayoutData(gridData);
		
		txtDriver = new Text(groupMain, SWT.BORDER);
		txtDriver.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtDriver.setLayoutData(gridData);
		
		Label lbCompany = new Label(groupMain, SWT.RIGHT);
		lbCompany.setFont(StyleFinal.SYS_FONT);
		lbCompany.setText("所属公司:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCompany.setLayoutData(gridData);
		
		dropCompany = new DropCompany(groupMain, SWT.BORDER);
		dropCompany.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropCompany.setLayoutData(gridData);
		
		Label lbStatus = new Label(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStatus.setLayoutData(gridData);
		
		comStatus = new CCombo(groupMain, SWT.BORDER);
		comStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		comStatus.setLayoutData(gridData);
		comStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_DRIVER_STATUS));
		
		txtIdNumber.setText(fIdNumber);
		txtDriver.setText(fDriver);
		dropCompany.droptext.setValue(fCompany);
		comStatus.setText(CommFinal.getItemName(TraffFinal.ARR_DRIVER_STATUS,fStatus));
		txtIdNumber.forceFocus();
		txtIdNumber.selectAll();
		callMethod.bindEnterEvent(this, txtIdNumber, txtDriver, "");
		callMethod.bindEnterEvent(this, txtDriver, dropCompany.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropCompany.droptext.txtShow, comStatus, "");
		callMethod.bindEnterEvent(this, comStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fIdNumber = txtIdNumber.getText();
			fDriver = txtDriver.getText();
			fCompany = dropCompany.droptext.getText();
			fStatus = CommFinal.getItemValue(TraffFinal.ARR_DRIVER_STATUS, comStatus.getText());
			btnId = buttonId;
			close();
		} else if (0 == buttonId) {
			/**
			 * 取消
			 */
			close();
		}
		
	}
	
	public int getBtnId() {
		return btnId;
	}

	public void setBtnId(int btnId) {
		this.btnId = btnId;
	}

	public String getfIdNumber() {
		return fIdNumber;
	}

	public void setfIdNumber(String fIdNumber) {
		this.fIdNumber = fIdNumber;
	}

	public String getfDriver() {
		return fDriver;
	}

	public void setfDriver(String fDriver) {
		this.fDriver = fDriver;
	}

	public String getfCompany() {
		return fCompany;
	}

	public void setfCompany(String fCompany) {
		this.fCompany = fCompany;
	}

	public String getfStatus() {
		return fStatus;
	}

	public void setfStatus(String fStatus) {
		this.fStatus = fStatus;
	}
	
}
