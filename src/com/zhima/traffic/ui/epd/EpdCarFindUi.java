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
import com.zhima.traffic.drop.DropRoute;
import com.zhima.util.SWTResourceManager;

public class EpdCarFindUi extends Dialog {
	private int btnId = 0;
	
	private DropRoute dropRoute;
	private Text txtCarCode;
	private Text txtCarNumber;
	private DropCompany dropCompany;
	private CCombo cboStatus;
	
	private String fRoute = "";
	private String fCarCode = "";
	private String fCarNumber = "";
	private String fCompany = "";
	private String fStatus = "";
	


	public EpdCarFindUi(Shell shell) {
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
        return new Point(315,260);
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
		groupMain.setText("查询信息");
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

		Label lbRoute = new Label(groupMain, SWT.RIGHT);
		lbRoute.setFont(StyleFinal.SYS_FONT);
		lbRoute.setText("运输线路:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRoute.setLayoutData(gridData);
		
		dropRoute = new DropRoute(groupMain, SWT.BORDER);
		dropRoute.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropRoute.setLayoutData(gridData);

		Label lbCarCode = new Label(groupMain, SWT.RIGHT);
		lbCarCode.setFont(StyleFinal.SYS_FONT);
		lbCarCode.setText("车辆编号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCarCode.setLayoutData(gridData);
		
		txtCarCode = new Text(groupMain, SWT.BORDER);
		txtCarCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCarCode.setLayoutData(gridData);
		
		Label lbCarNumber = new Label(groupMain, SWT.RIGHT);
		lbCarNumber.setFont(StyleFinal.SYS_FONT);
		lbCarNumber.setText("车牌号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCarNumber.setLayoutData(gridData);
		
		txtCarNumber = new Text(groupMain, SWT.BORDER);
		txtCarNumber.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCarNumber.setLayoutData(gridData);
		
		Label lbCompany = new Label(groupMain, SWT.RIGHT);
		lbCompany.setFont(StyleFinal.SYS_FONT);
		lbCompany.setText("车属公司:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCompany.setLayoutData(gridData);
		
		dropCompany = new DropCompany(groupMain, SWT.BORDER);
		dropCompany.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropCompany.setLayoutData(gridData);
		
		Label lbStatus = new Label(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStatus.setLayoutData(gridData);
		
		cboStatus = new CCombo(groupMain, SWT.BORDER);
		cboStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboStatus.setLayoutData(gridData);
		cboStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_CAR_STATUS));
		
		dropRoute.droptext.setValue(fRoute);
		txtCarCode.setText(fCarCode);
		txtCarNumber.setText(fCarNumber);
		dropCompany.droptext.setValue(fCompany);
		cboStatus.setText(CommFinal.getItemName(TraffFinal.ARR_CAR_STATUS,fStatus));
		dropRoute.droptext.txtShow.forceFocus();
		dropRoute.droptext.txtShow.selectAll();
		callMethod.bindEnterEvent(this, dropRoute.droptext.txtShow, txtCarCode, "");
		callMethod.bindEnterEvent(this, txtCarCode, txtCarNumber, "");
		callMethod.bindEnterEvent(this, txtCarNumber, dropRoute.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropRoute.droptext.txtShow, cboStatus, "");
		callMethod.bindEnterEvent(this, cboStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fRoute = dropRoute.droptext.getValue();
			fCarCode = txtCarCode.getText();
			fCarNumber = txtCarNumber.getText();
			fCompany = dropCompany.droptext.getValue();
			fStatus = CommFinal.getItemValue(TraffFinal.ARR_CAR_STATUS, cboStatus.getText());
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

	public String getfRoute() {
		return fRoute;
	}

	public void setfRoute(String fRoute) {
		this.fRoute = fRoute;
	}

	public String getfCarCode() {
		return fCarCode;
	}

	public void setfCarCode(String fCarCode) {
		this.fCarCode = fCarCode;
	}

	public String getfCarNumber() {
		return fCarNumber;
	}

	public void setfCarNumber(String fCarNumber) {
		this.fCarNumber = fCarNumber;
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
