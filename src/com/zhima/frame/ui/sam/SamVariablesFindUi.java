package com.zhima.frame.ui.sam;

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
import com.zhima.util.SWTResourceManager;

public class SamVariablesFindUi extends Dialog {
	private int btnId = 0;
	
	private CCombo cboVariableType;
	private String fVariableType;
	
	private Text txtVariableCode;
	private String fVariableCode = "";
	
	private Text txtVariableName;
	private String fVariableName = "";
	
	private CCombo cboStatus;
	private String fStatus = "";

	public SamVariablesFindUi(Shell shell) {
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
        return new Point(315,230);
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

		
		Label lbVariableType = new Label(groupMain, SWT.RIGHT);
		lbVariableType.setFont(StyleFinal.SYS_FONT);
		lbVariableType.setText("变量类型:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbVariableType.setLayoutData(gridData);
		
		cboVariableType = new CCombo(groupMain, SWT.BORDER);
		cboVariableType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboVariableType.setLayoutData(gridData);
		cboVariableType.setItems(CommFinal.getAllName(CommFinal.ARR_VARIABLE_TYPE));


		Label lbVariableCode = new Label(groupMain, SWT.RIGHT);
		lbVariableCode.setFont(StyleFinal.SYS_FONT);
		lbVariableCode.setText("变量代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbVariableCode.setLayoutData(gridData);
		
		txtVariableCode = new Text(groupMain, SWT.BORDER);
		txtVariableCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtVariableCode.setLayoutData(gridData);
		
		Label lbVariableName = new Label(groupMain, SWT.RIGHT);
		lbVariableName.setFont(StyleFinal.SYS_FONT);
		lbVariableName.setText("变量名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbVariableName.setLayoutData(gridData);
		
		txtVariableName = new Text(groupMain, SWT.BORDER);
		txtVariableName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtVariableName.setLayoutData(gridData);
		
		Label lbStatus = new Label(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("变量状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStatus.setLayoutData(gridData);
		
		cboStatus = new CCombo(groupMain, SWT.BORDER);
		cboStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboStatus.setLayoutData(gridData);
		cboStatus.setItems(CommFinal.getAllName(CommFinal.ARR_VARIABLE_STATUS));
		
		cboVariableType.setText(CommFinal.getItemName(CommFinal.ARR_VARIABLE_TYPE,fVariableType));
		txtVariableCode.setText(fVariableCode);
		txtVariableName.setText(fVariableName);
		cboStatus.setText(CommFinal.getItemName(CommFinal.ARR_VARIABLE_STATUS,fStatus));
		cboVariableType.forceFocus();
		callMethod.bindEnterEvent(this, cboVariableType, txtVariableCode, "");
		callMethod.bindEnterEvent(this, txtVariableCode, txtVariableName, "");
		callMethod.bindEnterEvent(this, txtVariableName, cboStatus, "");
		callMethod.bindEnterEvent(this, cboStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fVariableType = CommFinal.getItemValue(CommFinal.ARR_VARIABLE_TYPE, cboVariableType.getText());
			fVariableCode = txtVariableCode.getText();
			fVariableName = txtVariableName.getText();
			fStatus = CommFinal.getItemValue(CommFinal.ARR_VARIABLE_STATUS, cboStatus.getText());
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

	public String getfVariableType() {
		return fVariableType;
	}

	public void setfVariableType(String fVariableType) {
		this.fVariableType = fVariableType;
	}

	public String getfVariableCode() {
		return fVariableCode;
	}

	public void setfVariableCode(String fVariableCode) {
		this.fVariableCode = fVariableCode;
	}

	public String getfVariableName() {
		return fVariableName;
	}

	public void setfVariableName(String fVariableName) {
		this.fVariableName = fVariableName;
	}

	public String getfStatus() {
		return fStatus;
	}

	public void setfStatus(String fStatus) {
		this.fStatus = fStatus;
	}
	
}
