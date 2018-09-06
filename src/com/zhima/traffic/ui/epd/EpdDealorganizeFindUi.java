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

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.drop.DropOrganize;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.util.SWTResourceManager;

public class EpdDealorganizeFindUi extends Dialog {
	private int btnId = 0;
	
	private DropOrganize dropOrganize;
	private CCombo cboStatus;
	
	private String fOrganize = "";
	private String fDealStatus = "";

	public EpdDealorganizeFindUi(Shell shell) {
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
        return new Point(315,175);
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

		Label lbOrganize = new Label(groupMain, SWT.RIGHT);
		lbOrganize.setFont(StyleFinal.SYS_FONT);
		lbOrganize.setText("机构名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbOrganize.setLayoutData(gridData);
	
		dropOrganize = new DropOrganize(groupMain, SWT.BORDER);
		dropOrganize.initType("2");
		dropOrganize.droptext.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.widthHint = 200;
		dropOrganize.setLayoutData(gridData);
		
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
		cboStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_DEAL_STATUS));

		dropOrganize.droptext.setValue(fOrganize);
		cboStatus.setText(CommFinal.getItemName(TraffFinal.ARR_DEAL_STATUS,fDealStatus));
		dropOrganize.droptext.txtShow.forceFocus();
		dropOrganize.droptext.txtShow.selectAll();
		callMethod.bindEnterEvent(this, dropOrganize.droptext.txtShow, cboStatus, "");
		callMethod.bindEnterEvent(this, cboStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fOrganize = dropOrganize.droptext.getValue();
			fDealStatus = CommFinal.getItemValue(TraffFinal.ARR_CAR_STATUS, cboStatus.getText());
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

	public String getfOrganize() {
		return fOrganize;
	}

	public void setfOrganize(String fOrganize) {
		this.fOrganize = fOrganize;
	}

	public String getfDealStatus() {
		return fDealStatus;
	}

	public void setfDealStatus(String fDealStatus) {
		this.fDealStatus = fDealStatus;
	}
	
}
