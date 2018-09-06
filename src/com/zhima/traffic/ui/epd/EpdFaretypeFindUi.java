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

public class EpdFaretypeFindUi extends Dialog {
	private int btnId = 0;
	
	private Text txtFaretypeCode;
	private Text txtFaretypeName;
	private CCombo cboFaretypeStatus;
	
	private String fFaretypeCode = "";
	private String fFaretypeName = "";
	private String fFaretypeStatus = "";

	public EpdFaretypeFindUi(Shell shell) {
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

		Label lbFaretypeCode = new Label(groupMain, SWT.RIGHT);
		lbFaretypeCode.setFont(StyleFinal.SYS_FONT);
		lbFaretypeCode.setText("票型代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFaretypeCode.setLayoutData(gridData);
		
		txtFaretypeCode = new Text(groupMain, SWT.BORDER);
		txtFaretypeCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFaretypeCode.setLayoutData(gridData);
		
		Label lbFaretypeName = new Label(groupMain, SWT.RIGHT);
		lbFaretypeName.setFont(StyleFinal.SYS_FONT);
		lbFaretypeName.setText("票型名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFaretypeName.setLayoutData(gridData);
		
		txtFaretypeName = new Text(groupMain, SWT.BORDER);
		txtFaretypeName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFaretypeName.setLayoutData(gridData);
		
		Label lbFaretypeStatus = new Label(groupMain, SWT.RIGHT);
		lbFaretypeStatus.setFont(StyleFinal.SYS_FONT);
		lbFaretypeStatus.setText("票型状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFaretypeStatus.setLayoutData(gridData);
		
		cboFaretypeStatus = new CCombo(groupMain, SWT.BORDER);
		cboFaretypeStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboFaretypeStatus.setLayoutData(gridData);
		cboFaretypeStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_FARETYPE_STATUS));
		
		txtFaretypeCode.setText(fFaretypeCode);
		txtFaretypeName.setText(fFaretypeName);
		cboFaretypeStatus.setText(CommFinal.getItemName(TraffFinal.ARR_FARETYPE_STATUS,fFaretypeStatus));
		txtFaretypeCode.forceFocus();
		txtFaretypeCode.selectAll();
		callMethod.bindEnterEvent(this, txtFaretypeCode, txtFaretypeName, "");
		callMethod.bindEnterEvent(this, txtFaretypeName, cboFaretypeStatus, "");
		callMethod.bindEnterEvent(this, cboFaretypeStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fFaretypeCode = txtFaretypeCode.getText();
			fFaretypeName = txtFaretypeName.getText();
			fFaretypeStatus = CommFinal.getItemValue(TraffFinal.ARR_FARETYPE_STATUS, cboFaretypeStatus.getText());
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

	public String getfFaretypeCode() {
		return fFaretypeCode;
	}

	public void setfFaretypeCode(String fFaretypeCode) {
		this.fFaretypeCode = fFaretypeCode;
	}

	public String getfFaretypeName() {
		return fFaretypeName;
	}

	public void setfFaretypeName(String fFaretypeName) {
		this.fFaretypeName = fFaretypeName;
	}

	public String getfFaretypeStatus() {
		return fFaretypeStatus;
	}

	public void setfFaretypeStatus(String fFaretypeStatus) {
		this.fFaretypeStatus = fFaretypeStatus;
	}

}
