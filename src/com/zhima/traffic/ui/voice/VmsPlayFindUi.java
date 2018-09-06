package com.zhima.traffic.ui.voice;

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

public class VmsPlayFindUi extends Dialog {
	private int btnId = 0;
	
	private Text txtRouteCode;
	private Text txtLinerId;
	private CCombo cboReportStatus;
	private CCombo cboPrintbillStatus;
	private Button btnMusic;
	private Button btnNotice;
	
	private String fRouteCode="";
	private String fLinerId="";
	private String fReportStatus="";
	private String fPrintbillStatus="";
	private boolean fmusic =false;
	private boolean fnotice =false;

	public VmsPlayFindUi(Shell shell) {
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
        return new Point(300,265);
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

		Label lbRouteCode = new Label(groupMain, SWT.RIGHT);
		lbRouteCode.setFont(StyleFinal.SYS_FONT);
		lbRouteCode.setText("线路名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteCode.setLayoutData(gridData);
		
		txtRouteCode = new Text(groupMain, SWT.BORDER);
		txtRouteCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteCode.setLayoutData(gridData);
		
		Label lbLinerId = new Label(groupMain, SWT.RIGHT);
		lbLinerId.setFont(StyleFinal.SYS_FONT);
		lbLinerId.setText("班次号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerId.setLayoutData(gridData);
		
		txtLinerId = new Text(groupMain, SWT.BORDER);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(gridData);
		
		Label lbReportStatus = new Label(groupMain, SWT.RIGHT);
		lbReportStatus.setFont(StyleFinal.SYS_FONT);
		lbReportStatus.setText("报班状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbReportStatus.setLayoutData(gridData);
		
		cboReportStatus = new CCombo(groupMain, SWT.BORDER);
		cboReportStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboReportStatus.setLayoutData(gridData);
		
		Label lbPrintStatus = new Label(groupMain, SWT.RIGHT);
		lbPrintStatus.setFont(StyleFinal.SYS_FONT);
		lbPrintStatus.setText("打单状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPrintStatus.setLayoutData(gridData);
		
		cboPrintbillStatus = new CCombo(groupMain, SWT.BORDER);
		cboPrintbillStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboPrintbillStatus.setLayoutData(gridData);
		
		Composite composite = new Composite(groupMain, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=2;
		composite.setLayoutData(gridData);
		composite.setLayout(new FormLayout());
		
		btnMusic = new Button(composite, SWT.NONE|SWT.CHECK|SWT.CENTER);
		btnMusic.setFont(StyleFinal.SYS_FONT);
		btnMusic.setText("刷新音乐(&M)");
		FormData formData = new FormData();
		formData.top = new FormAttachment(0,10);
		formData.left = new FormAttachment(0,15);
		btnMusic.setLayoutData(formData);
		
		btnNotice = new Button(composite, SWT.NONE|SWT.CHECK|SWT.CENTER);
		btnNotice.setFont(StyleFinal.SYS_FONT);
		btnNotice.setText("刷新公告(&N)");
		formData = new FormData();
		formData.top = new FormAttachment(0,10);
		formData.right = new FormAttachment(100,-15);
		btnNotice.setLayoutData(formData);
		
		txtRouteCode.setText(fRouteCode);
		txtLinerId.setText(fLinerId);
		if(fmusic){
			btnMusic.setSelection(true);
		}else{
			btnMusic.setSelection(false);
		}
		if(fnotice){
			btnNotice.setSelection(true);
		}else{
			btnNotice.setSelection(false);
		}
		cboReportStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_REPORT_STATUS));
		cboPrintbillStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_PRINTBILL_STATUS));
		cboReportStatus.setText(CommFinal.getItemName(TraffFinal.ARR_REPORT_STATUS, fReportStatus));
		cboPrintbillStatus.setText(CommFinal.getItemName(TraffFinal.ARR_PRINTBILL_STATUS, fPrintbillStatus));
		txtRouteCode.forceFocus();
		txtRouteCode.selectAll();
		callMethod.bindEnterEvent(this, txtRouteCode, txtLinerId, "");
		callMethod.bindEnterEvent(this, txtLinerId, btnMusic, "");
		callMethod.bindEnterEvent(this, btnMusic, btnNotice, "");
		callMethod.bindEnterEvent(this, btnNotice, null, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fRouteCode = txtRouteCode.getText();
			fLinerId= txtLinerId.getText();
			fReportStatus = CommFinal.getItemValue(TraffFinal.ARR_REPORT_STATUS, cboReportStatus.getText());
			fPrintbillStatus = CommFinal.getItemValue(TraffFinal.ARR_PRINTBILL_STATUS, cboPrintbillStatus.getText());
			
			if(btnMusic.getSelection()){
				fmusic = true;
			}else{
				fmusic = false;
			}
			
			if(btnNotice.getSelection()){
				fnotice = true;
			}else{
				fnotice = false;
			}
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

	public String getfRouteCode() {
		return fRouteCode;
	}

	public void setfRouteCode(String fRouteCode) {
		this.fRouteCode = fRouteCode;
	}

	public String getfLinerId() {
		return fLinerId;
	}

	public void setfLinerId(String fLinerId) {
		this.fLinerId = fLinerId;
	}

	public String getfReportStatus() {
		return fReportStatus;
	}

	public void setfReportStatus(String fReportStatus) {
		this.fReportStatus = fReportStatus;
	}

	public String getfPrintbillStatus() {
		return fPrintbillStatus;
	}

	public void setfPrintbillStatus(String fPrintbillStatus) {
		this.fPrintbillStatus = fPrintbillStatus;
	}

	public boolean isFmusic() {
		return fmusic;
	}

	public void setFmusic(boolean fmusic) {
		this.fmusic = fmusic;
	}

	public boolean isFnotice() {
		return fnotice;
	}

	public void setFnotice(boolean fnotice) {
		this.fnotice = fnotice;
	}

}
