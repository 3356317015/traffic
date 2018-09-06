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

public class EpdRegionFindUi extends Dialog {
	private int btnId = 0;
	
	private Text txtAdministrationCode;
	private Text txtRegionSpell;
	private Text txtCity;
	private Text txtCounty;
	private Text txtTowns;
	
	private String fAdministrationCode = "";
	private String fRegionSpell = "";
	private String fCity = "";
	private String fCounty = "";
	private String fTowns = "";

	public EpdRegionFindUi(Shell shell) {
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

		Label lbAdministrationCode = new Label(groupMain, SWT.RIGHT);
		lbAdministrationCode.setFont(StyleFinal.SYS_FONT);
		lbAdministrationCode.setText("行政区代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbAdministrationCode.setLayoutData(gridData);
		
		txtAdministrationCode = new Text(groupMain, SWT.BORDER);
		txtAdministrationCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtAdministrationCode.setLayoutData(gridData);
		
		Label lbRegionSpell = new Label(groupMain, SWT.RIGHT);
		lbRegionSpell.setFont(StyleFinal.SYS_FONT);
		lbRegionSpell.setText("拼音代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRegionSpell.setLayoutData(gridData);
		
		txtRegionSpell = new Text(groupMain, SWT.BORDER);
		txtRegionSpell.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRegionSpell.setLayoutData(gridData);

		Label lbCity = new Label(groupMain, SWT.RIGHT);
		lbCity.setFont(StyleFinal.SYS_FONT);
		lbCity.setText("省(市):");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCity.setLayoutData(gridData);
		
		txtCity = new Text(groupMain, SWT.BORDER);
		txtCity.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCity.setLayoutData(gridData);
		
		Label lbCounty = new Label(groupMain, SWT.RIGHT);
		lbCounty.setFont(StyleFinal.SYS_FONT);
		lbCounty.setText("县:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCounty.setLayoutData(gridData);
		
		txtCounty = new Text(groupMain, SWT.BORDER);
		txtCounty.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCounty.setLayoutData(gridData);
		
		Label lbTowns = new Label(groupMain, SWT.RIGHT);
		lbTowns.setFont(StyleFinal.SYS_FONT);
		lbTowns.setText("乡(镇):");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbTowns.setLayoutData(gridData);
		
		txtTowns = new Text(groupMain, SWT.BORDER);
		txtTowns.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTowns.setLayoutData(gridData);
		
		txtAdministrationCode.setText(fAdministrationCode);
		txtRegionSpell.setText(fRegionSpell);
		txtCity.setText(fCity);
		txtCounty.setText(fCounty);
		txtTowns.setText(fTowns);
		txtAdministrationCode.forceFocus();
		txtAdministrationCode.selectAll();
		callMethod.bindEnterEvent(this, txtAdministrationCode, txtRegionSpell, "");
		callMethod.bindEnterEvent(this, txtRegionSpell, txtCity, "");
		callMethod.bindEnterEvent(this, txtCity, txtCounty, "");
		callMethod.bindEnterEvent(this, txtCounty, txtTowns, "");
		callMethod.bindEnterEvent(this, txtTowns, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fAdministrationCode = txtAdministrationCode.getText();
			fRegionSpell = txtRegionSpell.getText();
			fCity = txtCity.getText();
			fCounty = txtCounty.getText();
			fTowns = txtTowns.getText();
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

	public String getfAdministrationCode() {
		return fAdministrationCode;
	}

	public void setfAdministrationCode(String fAdministrationCode) {
		this.fAdministrationCode = fAdministrationCode;
	}

	public String getfRegionSpell() {
		return fRegionSpell;
	}

	public void setfRegionSpell(String fRegionSpell) {
		this.fRegionSpell = fRegionSpell;
	}

	public String getfCity() {
		return fCity;
	}

	public void setfCity(String fCity) {
		this.fCity = fCity;
	}

	public String getfCounty() {
		return fCounty;
	}

	public void setfCounty(String fCounty) {
		this.fCounty = fCounty;
	}

	public String getfTowns() {
		return fTowns;
	}

	public void setfTowns(String fTowns) {
		this.fTowns = fTowns;
	}

}
