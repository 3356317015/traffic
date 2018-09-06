package com.zhima.traffic.ui.epd;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
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

public class EpdReturnrateFindUi extends Dialog {
	private int btnId = 0;
	
	private Text txtReturnrateCode;
	private Text txtReturnrateName;
	
	private String fReturnrateCode = "";
	private String fReturnrateName = "";

	public EpdReturnrateFindUi(Shell shell) {
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
        return new Point(315,170);
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

		Label lbReturnrateCode = new Label(groupMain, SWT.RIGHT);
		lbReturnrateCode.setFont(StyleFinal.SYS_FONT);
		lbReturnrateCode.setText("退票费率代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbReturnrateCode.setLayoutData(gridData);
		
		txtReturnrateCode = new Text(groupMain, SWT.BORDER);
		txtReturnrateCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtReturnrateCode.setLayoutData(gridData);
		
		Label lbReturnrateName = new Label(groupMain, SWT.RIGHT);
		lbReturnrateName.setFont(StyleFinal.SYS_FONT);
		lbReturnrateName.setText("退票费率名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbReturnrateName.setLayoutData(gridData);
		
		txtReturnrateName = new Text(groupMain, SWT.BORDER);
		txtReturnrateName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtReturnrateName.setLayoutData(gridData);
		
		txtReturnrateCode.setText(fReturnrateCode);
		txtReturnrateName.setText(fReturnrateName);
		txtReturnrateCode.forceFocus();
		txtReturnrateCode.selectAll();
		callMethod.bindEnterEvent(this, txtReturnrateCode, txtReturnrateName, "");
		callMethod.bindEnterEvent(this, txtReturnrateName, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fReturnrateCode = txtReturnrateCode.getText();
			fReturnrateName = txtReturnrateName.getText();
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

	public String getfReturnrateCode() {
		return fReturnrateCode;
	}

	public void setfReturnrateCode(String fReturnrateCode) {
		this.fReturnrateCode = fReturnrateCode;
	}

	public String getfReturnrateName() {
		return fReturnrateName;
	}

	public void setfReturnrateName(String fReturnrateName) {
		this.fReturnrateName = fReturnrateName;
	}

}
