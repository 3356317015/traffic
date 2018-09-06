package com.zhima.traffic.ui.voice;

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

public class VmsRouteFindUi extends Dialog {
	private int btnId = 0;
	
	private Text txtRouteCode;
	private Text txtRouteName;
	private CCombo cboVoiceStatus;
	
	private String fRouteCode="";
	private String fRouteName="";
	private String fVoiceStatus="";

	public VmsRouteFindUi(Shell shell) {
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
        return new Point(305,205);
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

		Label lbRouteCode = new Label(groupMain, SWT.RIGHT);
		lbRouteCode.setFont(StyleFinal.SYS_FONT);
		lbRouteCode.setText("线路代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteCode.setLayoutData(gridData);
		
		txtRouteCode = new Text(groupMain, SWT.BORDER);
		txtRouteCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteCode.setLayoutData(gridData);
		
		Label lbRouteName = new Label(groupMain, SWT.RIGHT);
		lbRouteName.setFont(StyleFinal.SYS_FONT);
		lbRouteName.setText("线路名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteName.setLayoutData(gridData);
		
		txtRouteName = new Text(groupMain, SWT.BORDER);
		txtRouteName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteName.setLayoutData(gridData);
		
		Label lbVoiceStatus = new Label(groupMain, SWT.RIGHT);
		lbVoiceStatus.setFont(StyleFinal.SYS_FONT);
		lbVoiceStatus.setText("是否播音:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbVoiceStatus.setLayoutData(gridData);
		
		cboVoiceStatus = new CCombo(groupMain, SWT.BORDER);
		cboVoiceStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboVoiceStatus.setLayoutData(gridData);
		cboVoiceStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_VOICE_STATUS));
		
		txtRouteCode.setText(fRouteCode);
		txtRouteName.setText(fRouteName);
		cboVoiceStatus.setText(CommFinal.getItemName(TraffFinal.ARR_VOICE_STATUS, fVoiceStatus));
		txtRouteCode.forceFocus();
		txtRouteCode.selectAll();
		callMethod.bindEnterEvent(this, txtRouteCode, txtRouteName, "");
		callMethod.bindEnterEvent(this, txtRouteName, cboVoiceStatus, "");
		callMethod.bindEnterEvent(this, cboVoiceStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fRouteCode = txtRouteCode.getText();
			fRouteName = txtRouteName.getText();
			fVoiceStatus = CommFinal.getItemValue(TraffFinal.ARR_VOICE_STATUS, cboVoiceStatus.getText());
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

	public String getfRouteCode() {
		return fRouteCode;
	}

	public void setfRouteCode(String fRouteCode) {
		this.fRouteCode = fRouteCode;
	}

	public String getfRouteName() {
		return fRouteName;
	}

	public void setfRouteName(String fRouteName) {
		this.fRouteName = fRouteName;
	}


	public String getfVoiceStatus() {
		return fVoiceStatus;
	}

	public void setfVoiceStatus(String fVoiceStatus) {
		this.fVoiceStatus = fVoiceStatus;
	}

}
