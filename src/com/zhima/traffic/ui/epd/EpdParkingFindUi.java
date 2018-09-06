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
import com.zhima.traffic.drop.DropCheckgate;
import com.zhima.util.SWTResourceManager;

public class EpdParkingFindUi extends Dialog {
	private int btnId = 0;
	
	private DropCheckgate dropCheckgate;
	private Text txtParkingCode;
	private Text txtParkingName;
	
	private String fCheckgate = "";
	private String fParkingCode = "";
	private String fParkingName = "";

	public EpdParkingFindUi(Shell shell) {
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
		
		Label lbCheckgate = new Label(groupMain, SWT.RIGHT);
		lbCheckgate.setFont(StyleFinal.SYS_FONT);
		lbCheckgate.setText("检票口名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCheckgate.setLayoutData(gridData);
		
		dropCheckgate = new DropCheckgate(groupMain, SWT.BORDER);
		dropCheckgate.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropCheckgate.setLayoutData(gridData);

		Label lbParkingCode = new Label(groupMain, SWT.RIGHT);
		lbParkingCode.setFont(StyleFinal.SYS_FONT);
		lbParkingCode.setText("发车位代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbParkingCode.setLayoutData(gridData);
		
		txtParkingCode = new Text(groupMain, SWT.BORDER);
		txtParkingCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtParkingCode.setLayoutData(gridData);
		
		Label lbParkingName = new Label(groupMain, SWT.RIGHT);
		lbParkingName.setFont(StyleFinal.SYS_FONT);
		lbParkingName.setText("发车位名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbParkingName.setLayoutData(gridData);
		
		txtParkingName = new Text(groupMain, SWT.BORDER);
		txtParkingName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtParkingName.setLayoutData(gridData);
		
		dropCheckgate.droptext.setValue(fCheckgate);
		txtParkingCode.setText(fParkingCode);
		txtParkingName.setText(fParkingName);
		dropCheckgate.droptext.txtShow.forceFocus();
		dropCheckgate.droptext.txtShow.selectAll();
		callMethod.bindEnterEvent(this, dropCheckgate.droptext.txtShow, txtParkingCode, "");
		callMethod.bindEnterEvent(this, txtParkingCode, txtParkingName, "");
		callMethod.bindEnterEvent(this, txtParkingName, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fParkingCode = txtParkingCode.getText();
			fParkingName = txtParkingName.getText();
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

	public String getfCheckgate() {
		return fCheckgate;
	}

	public void setfCheckgate(String fCheckgate) {
		this.fCheckgate = fCheckgate;
	}

	public String getfParkingCode() {
		return fParkingCode;
	}

	public void setfParkingCode(String fParkingCode) {
		this.fParkingCode = fParkingCode;
	}

	public String getfParkingName() {
		return fParkingName;
	}

	public void setfParkingName(String fParkingName) {
		this.fParkingName = fParkingName;
	}

}
