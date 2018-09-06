package com.zhima.traffic.ui.insurance;

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
import com.zhima.frame.drop.DropUser;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropInsurancetype;
import com.zhima.traffic.model.InsTickettype;
import com.zhima.util.SWTResourceManager;

public class InsTicketpoolFindUi extends Dialog {
	private int btnId = 0;
	
	private DropInsurancetype dropInsurancetype;
	private CCombo cboOperType;
	private DropUser dropUser;
	private CCombo cboPoolStatus;
	
	private String fCompany = "";
	private String fTickettype = "";
	private String fOperType = "";
	private String fUser = "";
	private String fPoolStatus = "";
	
	public InsTicketpoolFindUi(Shell shell) {
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

		Label lbCargradeCode = new Label(groupMain, SWT.RIGHT);
		lbCargradeCode.setFont(StyleFinal.SYS_FONT);
		lbCargradeCode.setText("票型名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCargradeCode.setLayoutData(gridData);
		
		dropInsurancetype = new DropInsurancetype(groupMain, SWT.BORDER);
		dropInsurancetype.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropInsurancetype.setLayoutData(gridData);
		
		Label lbOperType = new Label(groupMain, SWT.RIGHT);
		lbOperType.setFont(StyleFinal.SYS_FONT);
		lbOperType.setText("操作类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOperType.setLayoutData(gridData);
		
		cboOperType = new CCombo(groupMain, SWT.BORDER);
		cboOperType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboOperType.setLayoutData(gridData);
		cboOperType.setItems(CommFinal.getAllName(TraffFinal.ARR_OPER_TYPE));
		
		Label lbUser = new Label(groupMain, SWT.RIGHT);
		lbUser.setFont(StyleFinal.SYS_FONT);
		lbUser.setText("用户:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUser.setLayoutData(gridData);
		
		dropUser = new DropUser(groupMain, SWT.BORDER);
		dropUser.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropUser.setLayoutData(gridData);
		dropUser.init(CommFinal.organize.getOrganizeSeq());
		
		Label lbPoolStatus = new Label(groupMain, SWT.RIGHT);
		lbPoolStatus.setFont(StyleFinal.SYS_FONT);
		lbPoolStatus.setText("票据状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPoolStatus.setLayoutData(gridData);
		
		cboPoolStatus = new CCombo(groupMain, SWT.BORDER);
		cboPoolStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboPoolStatus.setLayoutData(gridData);
		cboPoolStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_POOL_STATUS));
		
		dropInsurancetype.droptext.setValue(fTickettype);
		cboOperType.setText(CommFinal.getItemName(TraffFinal.ARR_OPER_TYPE,fOperType));
		dropUser.droptext.setValue(fUser);
		cboPoolStatus.setText(CommFinal.getItemName(TraffFinal.ARR_POOL_STATUS,fPoolStatus));
		dropInsurancetype.droptext.txtShow.forceFocus();
		dropInsurancetype.droptext.txtShow.selectAll();
		callMethod.bindEnterEvent(this, dropInsurancetype.droptext.txtShow, cboOperType, "");
		callMethod.bindEnterEvent(this, cboOperType, dropUser.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropUser.droptext.txtShow, cboPoolStatus, "");
		callMethod.bindEnterEvent(this, cboPoolStatus, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fTickettype = dropInsurancetype.droptext.getValue();
			if (null != fTickettype && fTickettype.length()>0){
				InsTickettype insTickettype = (InsTickettype) dropInsurancetype.droptext.getObject();
				fCompany = insTickettype.getCompanySeq();
			}
			fOperType = CommFinal.getItemValue(TraffFinal.ARR_OPER_TYPE,cboOperType.getText());
			fUser = dropUser.droptext.getText();
			fPoolStatus = CommFinal.getItemValue(TraffFinal.ARR_POOL_STATUS,cboPoolStatus.getText());
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

	public String getfCompany() {
		return fCompany;
	}

	public void setfCompany(String fCompany) {
		this.fCompany = fCompany;
	}

	public String getfTickettype() {
		return fTickettype;
	}

	public void setfTickettype(String fTickettype) {
		this.fTickettype = fTickettype;
	}

	public String getfOperType() {
		return fOperType;
	}

	public void setfOperType(String fOperType) {
		this.fOperType = fOperType;
	}

	public String getfUser() {
		return fUser;
	}

	public void setfUser(String fUser) {
		this.fUser = fUser;
	}

	public String getfPoolStatus() {
		return fPoolStatus;
	}

	public void setfPoolStatus(String fPoolStatus) {
		this.fPoolStatus = fPoolStatus;
	}

}
