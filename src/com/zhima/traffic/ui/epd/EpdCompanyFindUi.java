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
import com.zhima.util.SWTResourceManager;

public class EpdCompanyFindUi extends Dialog {
	private Text txtCompanyCode;
	private String fCompanyCode = "";
	
	private Text txtCompanyName;
	private String fCompanyName = "";
	
	private Text txtCompanyType;
	private String fCompanyType = "";
	
	private Text txtIndustry;
	private String fIndustry = "";
	
	private int btnId = 0;

	protected EpdCompanyFindUi(Shell shell) {
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
        return new Point(315,225);
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
		
		Label lbCompanyCode = new Label(groupMain, SWT.RIGHT);
		lbCompanyCode.setFont(StyleFinal.SYS_FONT);
		lbCompanyCode.setText("公司代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCompanyCode.setLayoutData(gridData);
		
		txtCompanyCode = new Text(groupMain, SWT.BORDER);
		txtCompanyCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCompanyCode.setLayoutData(gridData);

		Label lbCompanyName = new Label(groupMain, SWT.RIGHT);
		lbCompanyName.setFont(StyleFinal.SYS_FONT);
		lbCompanyName.setText("公司名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCompanyName.setLayoutData(gridData);
		
		txtCompanyName = new Text(groupMain, SWT.BORDER);
		txtCompanyName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCompanyName.setLayoutData(gridData);
		
		Label lbCompanyType = new Label(groupMain, SWT.RIGHT);
		lbCompanyType.setFont(StyleFinal.SYS_FONT);
		lbCompanyType.setText("公司性质:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCompanyType.setLayoutData(gridData);
		
		txtCompanyType = new Text(groupMain, SWT.BORDER);
		txtCompanyType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCompanyType.setLayoutData(gridData);
		
		Label lbIndustry = new Label(groupMain, SWT.RIGHT);
		lbIndustry.setFont(StyleFinal.SYS_FONT);
		lbIndustry.setText("行业类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbIndustry.setLayoutData(gridData);
		
		txtIndustry = new Text(groupMain, SWT.BORDER);
		txtIndustry.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIndustry.setLayoutData(gridData);
		
		txtCompanyCode.setText(fCompanyCode);
		txtCompanyName.setText(fCompanyName);
		txtCompanyType.setText(fCompanyType);
		txtIndustry.setText(fIndustry);
		txtCompanyCode.forceFocus();
		txtCompanyCode.selectAll();

		callMethod.bindEnterEvent(this, txtCompanyCode, txtCompanyName, "");
		callMethod.bindEnterEvent(this, txtCompanyName, txtCompanyType, "");
		callMethod.bindEnterEvent(this, txtCompanyType, txtIndustry, "");
		callMethod.bindEnterEvent(this, txtIndustry, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fCompanyCode = txtCompanyCode.getText();
			fCompanyName = txtCompanyName.getText();
			fCompanyType = txtCompanyType.getText();
			fIndustry = txtIndustry.getText();
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

	public String getfCompanyCode() {
		return fCompanyCode;
	}

	public void setfCompanyCode(String fCompanyCode) {
		this.fCompanyCode = fCompanyCode;
	}

	public String getfCompanyName() {
		return fCompanyName;
	}

	public void setfCompanyName(String fCompanyName) {
		this.fCompanyName = fCompanyName;
	}

	public String getfCompanyType() {
		return fCompanyType;
	}

	public void setfCompanyType(String fCompanyType) {
		this.fCompanyType = fCompanyType;
	}

	public String getfIndustry() {
		return fIndustry;
	}

	public void setfIndustry(String fIndustry) {
		this.fIndustry = fIndustry;
	}
	
}
