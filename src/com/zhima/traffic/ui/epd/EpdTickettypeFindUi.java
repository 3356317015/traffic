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

import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.util.SWTResourceManager;

public class EpdTickettypeFindUi extends Dialog {
	private int btnId = 0;
	
	private CCombo cboCategory;
	
	private String fCategory = "";

	public EpdTickettypeFindUi(Shell shell) {
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
        return new Point(315,145);
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

		Label lbCategory = new Label(groupMain, SWT.RIGHT);
		lbCategory.setFont(StyleFinal.SYS_FONT);
		lbCategory.setText("票据类型:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCategory.setLayoutData(gridData);
		
		cboCategory = new CCombo(groupMain, SWT.BORDER);
		cboCategory.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		cboCategory.setLayoutData(gridData);
		cboCategory.setItems(CommFinal.getAllName(TraffFinal.ARR_CATEGORY));
		
		cboCategory.setText(CommFinal.getItemName(TraffFinal.ARR_CATEGORY,fCategory));
		cboCategory.forceFocus();
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fCategory = CommFinal.getItemValue(TraffFinal.ARR_CATEGORY, cboCategory.getText());
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

	public String getfCategory() {
		return fCategory;
	}

	public void setfCategory(String fCategory) {
		this.fCategory = fCategory;
	}

}