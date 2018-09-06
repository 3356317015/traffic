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
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.util.SWTResourceManager;

public class SamServiceFindUi extends Dialog {
	
	private int btnId = 0;
	
	private Text txtServiceCode;
	private Text txtServiceName;
	private CCombo cboServiceStatus;
	
	private String fServiceCode = "";
	private String fServiceName = "";
	private String fServiceStatus = "";
	
	protected SamServiceFindUi(Shell shell) {
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

		
		Label lbServiceCode = new Label(groupMain, SWT.RIGHT);
		lbServiceCode.setFont(StyleFinal.SYS_FONT);
		lbServiceCode.setText("销售点代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbServiceCode.setLayoutData(gridData);
		
		txtServiceCode = new Text(groupMain, SWT.BORDER);
		txtServiceCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtServiceCode.setLayoutData(gridData);

		Label lbServiceName = new Label(groupMain, SWT.RIGHT);
		lbServiceName.setFont(StyleFinal.SYS_FONT);
		lbServiceName.setText("销售点名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbServiceName.setLayoutData(gridData);
		
		txtServiceName = new Text(groupMain, SWT.BORDER);
		txtServiceName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtServiceName.setLayoutData(gridData);
			
		Label lbServiceStatus = new Label(groupMain, SWT.RIGHT);
		lbServiceStatus.setFont(StyleFinal.SYS_FONT);
		lbServiceStatus.setText("销售点状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbServiceStatus.setLayoutData(gridData);
		
		cboServiceStatus = new CCombo(groupMain, SWT.BORDER);
		cboServiceStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboServiceStatus.setLayoutData(gridData);
		cboServiceStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_SERVICE_STATUS));

		txtServiceCode.setText(fServiceCode);
		txtServiceName.setText(fServiceName);
		cboServiceStatus.setText(CommFinal.getItemName(TraffFinal.ARR_SERVICE_STATUS, fServiceStatus));
		txtServiceCode.forceFocus();
		txtServiceCode.selectAll();

		callMethod.bindEnterEvent(this, txtServiceCode, txtServiceName, "");
		callMethod.bindEnterEvent(this, txtServiceName, cboServiceStatus, "");
		callMethod.bindEnterEvent(this, cboServiceStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fServiceCode = txtServiceCode.getText();
			fServiceName = txtServiceName.getText();
			fServiceStatus = CommFinal.getItemValue(TraffFinal.ARR_SERVICE_STATUS, cboServiceStatus.getText());
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

	public String getfServiceCode() {
		return fServiceCode;
	}

	public void setfServiceCode(String fServiceCode) {
		this.fServiceCode = fServiceCode;
	}

	public String getfServiceName() {
		return fServiceName;
	}

	public void setfServiceName(String fServiceName) {
		this.fServiceName = fServiceName;
	}

	public String getfServiceStatus() {
		return fServiceStatus;
	}

	public void setfServiceStatus(String fServiceStatus) {
		this.fServiceStatus = fServiceStatus;
	}
	
}
