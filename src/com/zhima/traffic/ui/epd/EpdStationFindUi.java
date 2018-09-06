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

public class EpdStationFindUi extends Dialog {
	private int btnId = 0;
	
	private Text txtStationCode;
	private String fStationCode = "";
	
	private Text txtStationSpell;
	private String fStationSpell = "";
	
	private Text txtStationName;
	private String fStationName = "";
	
	private CCombo comStatus;
	private String fStatus = "";

	public EpdStationFindUi(Shell shell) {
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
		
		Label lbStationCode = new Label(groupMain, SWT.RIGHT);
		lbStationCode.setFont(StyleFinal.SYS_FONT);
		lbStationCode.setText("站点代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStationCode.setLayoutData(gridData);
		
		txtStationCode = new Text(groupMain, SWT.BORDER);
		txtStationCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStationCode.setLayoutData(gridData);

		Label lbStationSpell = new Label(groupMain, SWT.RIGHT);
		lbStationSpell.setFont(StyleFinal.SYS_FONT);
		lbStationSpell.setText("拼音代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStationSpell.setLayoutData(gridData);
		
		txtStationSpell = new Text(groupMain, SWT.BORDER);
		txtStationSpell.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStationSpell.setLayoutData(gridData);
		
		Label lbStationName = new Label(groupMain, SWT.RIGHT);
		lbStationName.setFont(StyleFinal.SYS_FONT);
		lbStationName.setText("站点名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStationName.setLayoutData(gridData);
		
		txtStationName = new Text(groupMain, SWT.BORDER);
		txtStationName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStationName.setLayoutData(gridData);
		
		Label lbStatus = new Label(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("站点状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStatus.setLayoutData(gridData);
		
		comStatus = new CCombo(groupMain, SWT.BORDER);
		comStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		comStatus.setLayoutData(gridData);
		comStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_STATION_STATUS));
		
		txtStationCode.setText(fStationCode);
		txtStationSpell.setText(fStationSpell);
		txtStationName.setText(fStationName);
		comStatus.setText(CommFinal.getItemName(TraffFinal.ARR_STATION_STATUS,fStatus));
		txtStationCode.forceFocus();
		txtStationCode.selectAll();
		callMethod.bindEnterEvent(this, txtStationCode, txtStationSpell, "");
		callMethod.bindEnterEvent(this, txtStationSpell, txtStationName, "");
		callMethod.bindEnterEvent(this, txtStationName, comStatus, "");
		callMethod.bindEnterEvent(this, comStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fStationCode = txtStationCode.getText();
			fStationSpell = txtStationSpell.getText();
			fStationName = txtStationName.getText();
			fStatus = CommFinal.getItemValue(TraffFinal.ARR_STATION_STATUS, comStatus.getText());
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

	public String getfStationCode() {
		return fStationCode;
	}

	public void setfStationCode(String fStationCode) {
		this.fStationCode = fStationCode;
	}

	public String getfStationSpell() {
		return fStationSpell;
	}

	public void setfStationSpell(String fStationSpell) {
		this.fStationSpell = fStationSpell;
	}

	public String getfStationName() {
		return fStationName;
	}

	public void setfStationName(String fStationName) {
		this.fStationName = fStationName;
	}

	public String getfStatus() {
		return fStatus;
	}

	public void setfStatus(String fStatus) {
		this.fStatus = fStatus;
	}
	
}
