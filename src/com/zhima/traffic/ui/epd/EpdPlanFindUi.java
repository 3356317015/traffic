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
import com.zhima.traffic.drop.DropRoute;
import com.zhima.traffic.drop.DropStation;
import com.zhima.util.SWTResourceManager;

public class EpdPlanFindUi extends Dialog {
	private int btnId = 0;
	
	private DropRoute dropRoute;
	private DropStation dropStation;
	private Text txtPlanId;
	private CCombo cboPlanType;
	private CCombo cboPlanStatus;
	
	private String fRoute = "";
	private String fStation = "";
	private String fPlanId = "";
	private String fPlanType = "";
	private String fPlanStatus = "";

	public EpdPlanFindUi(Shell shell) {
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
        return new Point(345,260);
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

		Label lbStation = new Label(groupMain, SWT.RIGHT);
		lbStation.setFont(StyleFinal.SYS_FONT);
		lbStation.setText("目的地:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStation.setLayoutData(gridData);
		
		dropStation = new DropStation(groupMain, SWT.BORDER);
		dropStation.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropStation.setLayoutData(gridData);
		
		Label lbPlanId = new Label(groupMain, SWT.RIGHT);
		lbPlanId.setFont(StyleFinal.SYS_FONT);
		lbPlanId.setText("计划车次:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbPlanId.setLayoutData(gridData);
		
		txtPlanId = new Text(groupMain, SWT.BORDER);
		txtPlanId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPlanId.setLayoutData(gridData);
		
		Label lbPlanType = new Label(groupMain, SWT.RIGHT);
		lbPlanType.setFont(StyleFinal.SYS_FONT);
		lbPlanType.setText("计划类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbPlanType.setLayoutData(gridData);
		
		cboPlanType = new CCombo(groupMain, SWT.BORDER);
		cboPlanType.setFont(StyleFinal.SYS_FONT);
		cboPlanType.setVisibleItemCount(10);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboPlanType.setLayoutData(gridData);
		cboPlanType.setItems(TraffFinal.ARR_PLAN_TYPE);
		
		Label lbPlanState = new Label(groupMain, SWT.RIGHT);
		lbPlanState.setFont(StyleFinal.SYS_FONT);
		lbPlanState.setText("计划状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbPlanState.setLayoutData(gridData);
		
		cboPlanStatus = new CCombo(groupMain, SWT.BORDER);
		cboPlanStatus.setFont(StyleFinal.SYS_FONT);
		cboPlanStatus.setVisibleItemCount(10);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboPlanStatus.setLayoutData(gridData);
		cboPlanStatus.setItems(TraffFinal.ARR_PLAN_STATUS);
		
		dropRoute.droptext.setValue(fRoute);
		dropStation.droptext.setValue(fStation);
		txtPlanId.setText(fPlanId);
		cboPlanType.setText(fPlanType);
		cboPlanStatus.setText(fPlanStatus);
		dropRoute.droptext.txtShow.forceFocus();
		dropRoute.droptext.txtShow.selectAll();
		callMethod.bindEnterEvent(this, dropRoute.droptext.txtShow, dropStation.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropStation.droptext.txtShow, txtPlanId, "");
		callMethod.bindEnterEvent(this, txtPlanId, cboPlanType, "");
		callMethod.bindEnterEvent(this, cboPlanType, cboPlanStatus, "");
		callMethod.bindEnterEvent(this, cboPlanStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fRoute = dropRoute.droptext.getValue();
			fStation = dropStation.droptext.getValue();
			fPlanId = txtPlanId.getText();
			fPlanType = cboPlanType.getText();
			fPlanStatus = cboPlanStatus.getText();
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

	public String getfStation() {
		return fStation;
	}

	public void setfStation(String fStation) {
		this.fStation = fStation;
	}

	public String getfPlanId() {
		return fPlanId;
	}

	public void setfPlanId(String fPlanId) {
		this.fPlanId = fPlanId;
	}

	public String getfPlanType() {
		return fPlanType;
	}

	public void setfPlanType(String fPlanType) {
		this.fPlanType = fPlanType;
	}

	public String getfPlanStatus() {
		return fPlanStatus;
	}

	public void setfPlanStatus(String fPlanStatus) {
		this.fPlanStatus = fPlanStatus;
	}



}
