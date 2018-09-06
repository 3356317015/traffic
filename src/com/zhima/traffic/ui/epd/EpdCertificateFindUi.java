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
import com.zhima.util.SWTResourceManager;

public class EpdCertificateFindUi extends Dialog {
	private int btnId = 0;
	
	private CCombo cboCerType;
	private Text txtCerName;
	private CCombo cboCerStatus;
	
	private String fCerType = "";
	private String fCerName = "";
	private String fCerStatus = "";

	public EpdCertificateFindUi(Shell shell) {
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
        return new Point(315,200);
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
		groupMain.setText("查询条件");
		groupMain.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);

		Label lbCerType = new Label(groupMain, SWT.RIGHT);
		lbCerType.setFont(StyleFinal.SYS_FONT);
		lbCerType.setText("资质项类型:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCerType.setLayoutData(gridData);
		
		cboCerType = new CCombo(groupMain, SWT.BORDER);
		cboCerType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboCerType.setLayoutData(gridData);
		
		Label lbCerName = new Label(groupMain, SWT.RIGHT);
		lbCerName.setFont(StyleFinal.SYS_FONT);
		lbCerName.setText("资质项名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCerName.setLayoutData(gridData);
		
		txtCerName = new Text(groupMain, SWT.BORDER);
		txtCerName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCerName.setLayoutData(gridData);
		
		Label lbCerStatus = new Label(groupMain, SWT.RIGHT);
		lbCerStatus.setFont(StyleFinal.SYS_FONT);
		lbCerStatus.setText("资质项状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCerStatus.setLayoutData(gridData);
		
		cboCerStatus = new CCombo(groupMain, SWT.BORDER);
		cboCerStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboCerStatus.setLayoutData(gridData);
		
		cboCerType.setItems(CommFinal.getAllName(TraffFinal.ARR_CER_TYPE));
		cboCerStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_CER_STATUS));
		
		cboCerType.setText(CommFinal.getItemName(TraffFinal.ARR_CER_TYPE, fCerType));
		txtCerName.setText(fCerName);
		cboCerStatus.setText(CommFinal.getItemName(TraffFinal.ARR_CER_STATUS, fCerStatus));
		cboCerType.forceFocus();
		callMethod.bindEnterEvent(this, cboCerType, txtCerName, "");
		callMethod.bindEnterEvent(this, txtCerName, cboCerStatus, "");
		callMethod.bindEnterEvent(this, cboCerStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fCerType = CommFinal.getItemValue(TraffFinal.ARR_CER_TYPE, cboCerType.getText());
			fCerName = txtCerName.getText();
			fCerStatus = CommFinal.getItemValue(TraffFinal.ARR_CER_STATUS, cboCerStatus.getText());
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

	public String getfCerType() {
		return fCerType;
	}

	public void setfCerType(String fCerType) {
		this.fCerType = fCerType;
	}

	public String getfCerName() {
		return fCerName;
	}

	public void setfCerName(String fCerName) {
		this.fCerName = fCerName;
	}

	public String getfCerStatus() {
		return fCerStatus;
	}

	public void setfCerStatus(String fCerStatus) {
		this.fCerStatus = fCerStatus;
	}

}
