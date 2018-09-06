package com.zhima.traffic.ui.operate;

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
import com.zhima.traffic.drop.DropRoute;
import com.zhima.traffic.drop.DropStation;
import com.zhima.util.SWTResourceManager;
import com.zhima.widget.CCalendar;

public class ItsLinerFareFindUi extends Dialog {
	private int btnId = 0;
	
	private DropRoute dropRoute;
	private DropStation dropStation;
	private Text txtLinerId;
	private CCalendar txtStartDate;
	private CCalendar txtLimitDate;

	private String fRoute = "";
	private String fStation = "";
	private String fLinerId = "";
	private String fStartDate="";
	private String fLimitDate="";

	public ItsLinerFareFindUi(Shell shell) {
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
        return new Point(315,255);
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

		Label lbRoute = new Label(groupMain, SWT.RIGHT);
		lbRoute.setFont(StyleFinal.SYS_FONT);
		lbRoute.setText("线路名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRoute.setLayoutData(gridData);
		
		dropRoute = new DropRoute(groupMain, SWT.BORDER);
		dropRoute.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropRoute.setLayoutData(gridData);
		
		Label lbStation = new Label(groupMain, SWT.RIGHT);
		lbStation.setFont(StyleFinal.SYS_FONT);
		lbStation.setText("站点名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStation.setLayoutData(gridData);
		
		dropStation = new DropStation(groupMain, SWT.BORDER);
		dropStation.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropStation.setLayoutData(gridData);

		Label lbLinerId = new Label(groupMain, SWT.RIGHT);
		lbLinerId.setFont(StyleFinal.SYS_FONT);
		lbLinerId.setText("车次号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbLinerId.setLayoutData(gridData);
		
		txtLinerId = new Text(groupMain, SWT.BORDER);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(gridData);
		
		Label lbStartDate = new Label(groupMain, SWT.RIGHT);
		lbStartDate.setFont(StyleFinal.SYS_FONT);
		lbStartDate.setText("开始日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStartDate.setLayoutData(gridData);
		
		txtStartDate = new CCalendar(groupMain, SWT.BORDER);
		txtStartDate.txtDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtStartDate.setLayoutData(gridData);
		
		Label lbLimitDate = new Label(groupMain, SWT.RIGHT);
		lbLimitDate.setFont(StyleFinal.SYS_FONT);
		lbLimitDate.setText("结束日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbLimitDate.setLayoutData(gridData);
		
		txtLimitDate = new CCalendar(groupMain, SWT.BORDER);
		txtLimitDate.txtDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtLimitDate.setLayoutData(gridData);

		dropRoute.droptext.setValue(fRoute);
		dropStation.droptext.setValue(fStation);
		txtLinerId.setText(fLinerId);
		txtStartDate.setText(fStartDate);
		txtLimitDate.setText(fLimitDate);
		
		
		dropRoute.droptext.txtShow.forceFocus();
		dropRoute.droptext.txtShow.selectAll();
		callMethod.bindEnterEvent(this, dropRoute.droptext.txtShow, dropStation.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropStation.droptext.txtShow, txtLinerId, "");
		callMethod.bindEnterEvent(this, txtLinerId, txtStartDate.txtDate, "");
		callMethod.bindEnterEvent(this, txtStartDate.txtDate, txtLimitDate.txtDate, "");
		callMethod.bindEnterEvent(this, txtLimitDate.txtDate, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fRoute = dropRoute.droptext.getValue();
			fStation = dropStation.droptext.getValue();
			fLinerId = txtLinerId.getText();
			fStartDate = txtStartDate.getText();
			fLimitDate = txtLimitDate.getText();
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

	public String getfLinerId() {
		return fLinerId;
	}

	public void setfLinerId(String fLinerId) {
		this.fLinerId = fLinerId;
	}

	public String getfStartDate() {
		return fStartDate;
	}

	public void setfStartDate(String fStartDate) {
		this.fStartDate = fStartDate;
	}

	public String getfLimitDate() {
		return fLimitDate;
	}

	public void setfLimitDate(String fLimitDate) {
		this.fLimitDate = fLimitDate;
	}


}
