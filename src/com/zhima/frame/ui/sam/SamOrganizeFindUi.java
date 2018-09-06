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

public class SamOrganizeFindUi extends Dialog {
	
	private int btnId = 0;
	
	private Text txtOrganizeCode;
	private Text txtOrganizeName;
	private CCombo cboOrganizeLevel;
	private CCombo cboOrganizeType;
	private CCombo cboOrganizeStatus;
	
	private String fOrganizeCode = "";
	private String fOrganizeName = "";
	private String fOrganizeLevel = "";
	private String fOrganizeType = "";
	private String fOrganizeStatus = "";
	
	protected SamOrganizeFindUi(Shell shell) {
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
        return new Point(305,260);
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

		
		Label lbOrganizeCode = new Label(groupMain, SWT.RIGHT);
		lbOrganizeCode.setFont(StyleFinal.SYS_FONT);
		lbOrganizeCode.setText("客运站代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbOrganizeCode.setLayoutData(gridData);
		
		txtOrganizeCode = new Text(groupMain, SWT.BORDER);
		txtOrganizeCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtOrganizeCode.setLayoutData(gridData);

		Label lbOrganizeName = new Label(groupMain, SWT.RIGHT);
		lbOrganizeName.setFont(StyleFinal.SYS_FONT);
		lbOrganizeName.setText("客运站名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbOrganizeName.setLayoutData(gridData);
		
		txtOrganizeName = new Text(groupMain, SWT.BORDER);
		txtOrganizeName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtOrganizeName.setLayoutData(gridData);
		
		Label lbOrganizeLevel = new Label(groupMain, SWT.RIGHT);
		lbOrganizeLevel.setFont(StyleFinal.SYS_FONT);
		lbOrganizeLevel.setText("客运站等级:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeLevel.setLayoutData(gridData);
		
		cboOrganizeLevel = new CCombo(groupMain, SWT.BORDER);
		cboOrganizeLevel.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboOrganizeLevel.setLayoutData(gridData);
		cboOrganizeLevel.setItems(CommFinal.getAllName(TraffFinal.ARR_ORGANIZE_LEVEL));
		
		Label lbOrganizeType = new Label(groupMain, SWT.RIGHT);
		lbOrganizeType.setFont(StyleFinal.SYS_FONT);
		lbOrganizeType.setText("客运站类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeType.setLayoutData(gridData);
		
		cboOrganizeType = new CCombo(groupMain, SWT.BORDER);
		cboOrganizeType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboOrganizeType.setLayoutData(gridData);
		cboOrganizeType.setItems(CommFinal.getAllName(TraffFinal.ARR_ORGANIZE_TYPE));
		
		Label lbOrganizeStatus = new Label(groupMain, SWT.RIGHT);
		lbOrganizeStatus.setFont(StyleFinal.SYS_FONT);
		lbOrganizeStatus.setText("客运站状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeStatus.setLayoutData(gridData);
		
		cboOrganizeStatus = new CCombo(groupMain, SWT.BORDER);
		cboOrganizeStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboOrganizeStatus.setLayoutData(gridData);
		cboOrganizeStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_ORGANIZE_STATUS));

		txtOrganizeCode.setText(fOrganizeCode);
		txtOrganizeName.setText(fOrganizeName);
		cboOrganizeLevel.setText(CommFinal.getItemName(TraffFinal.ARR_ORGANIZE_LEVEL, fOrganizeLevel));
		cboOrganizeType.setText(CommFinal.getItemName(TraffFinal.ARR_ORGANIZE_TYPE, fOrganizeType));
		cboOrganizeStatus.setText(CommFinal.getItemName(TraffFinal.ARR_ORGANIZE_STATUS, fOrganizeStatus));
		txtOrganizeCode.forceFocus();
		txtOrganizeCode.selectAll();

		callMethod.bindEnterEvent(this, txtOrganizeCode, txtOrganizeName, "");
		callMethod.bindEnterEvent(this, txtOrganizeName, null, "");

		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fOrganizeCode = txtOrganizeCode.getText();
			fOrganizeName = txtOrganizeName.getText();
			fOrganizeLevel = CommFinal.getItemValue(TraffFinal.ARR_ORGANIZE_LEVEL, cboOrganizeLevel.getText());
			fOrganizeType = CommFinal.getItemValue(TraffFinal.ARR_ORGANIZE_TYPE, cboOrganizeType.getText());
			fOrganizeStatus = CommFinal.getItemValue(TraffFinal.ARR_ORGANIZE_STATUS, cboOrganizeStatus.getText());
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

	public String getfOrganizeCode() {
		return fOrganizeCode;
	}

	public void setfOrganizeCode(String fOrganizeCode) {
		this.fOrganizeCode = fOrganizeCode;
	}

	public String getfOrganizeName() {
		return fOrganizeName;
	}

	public void setfOrganizeName(String fOrganizeName) {
		this.fOrganizeName = fOrganizeName;
	}

	public String getfOrganizeLevel() {
		return fOrganizeLevel;
	}

	public void setfOrganizeLevel(String fOrganizeLevel) {
		this.fOrganizeLevel = fOrganizeLevel;
	}

	public String getfOrganizeType() {
		return fOrganizeType;
	}

	public void setfOrganizeType(String fOrganizeType) {
		this.fOrganizeType = fOrganizeType;
	}

	public String getfOrganizeStatus() {
		return fOrganizeStatus;
	}

	public void setfOrganizeStatus(String fOrganizeStatus) {
		this.fOrganizeStatus = fOrganizeStatus;
	}
	
}
