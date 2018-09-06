package com.zhima.frame.ui.sam;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
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
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamModuleRight;
import com.zhima.frame.action.sam.impl.ImpSamModuleRight;
import com.zhima.frame.drop.DropModule;
import com.zhima.frame.drop.DropUser;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CCalendar;

public class SamOperLogFindUi extends Dialog {
	
	private int btnId = 0;
	
	private DropModule dropModuleCode;
	private String fModuleCode = "";
	
	private CCombo cOperType;
	private String fOperType = "";
	
	private DropUser dropUserCode;
	private String fUserCode = "";
	
	private CCombo cStatus;
	private String fStatus = "";
	
	private CCalendar dStartDate;
	private String fStartDate = "";
	
	private CCalendar dEndDate;
	private String fEndDate = "";

	
	protected SamOperLogFindUi(Shell shell) {
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
        return new Point(425,205);
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
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);

		
		Label lbModuleCode = new Label(groupMain, SWT.RIGHT);
		lbModuleCode.setFont(StyleFinal.SYS_FONT);
		lbModuleCode.setText("模块名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbModuleCode.setLayoutData(gridData);
		
		dropModuleCode = new DropModule(groupMain, SWT.BORDER);
		dropModuleCode.droptext.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropModuleCode.setLayoutData(gridData);

		Label lbOperType = new Label(groupMain, SWT.RIGHT);
		lbOperType.setFont(StyleFinal.SYS_FONT);
		lbOperType.setText("操作类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOperType.setLayoutData(gridData);
		
		cOperType = new CCombo(groupMain, SWT.BORDER);
		cOperType.setItems(new String[] {"系统登录", "系统退出", "打开", "关闭"});
		cOperType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cOperType.setLayoutData(gridData);
		cOperType.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try {
					String temp = cOperType.getText();
					cOperType.removeAll();
					ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
					List<SamModuleRight> samModuleRights=new ArrayList<SamModuleRight>();
					String moduleCode = dropModuleCode.droptext.getValue();
					if (null != moduleCode && !"".equals(moduleCode)){
						if ("SYSTEM".equals(moduleCode)){
							cOperType.setItems(new String[]{"系统登录","系统退出"});
						}else{
							SamModule module = (SamModule) dropModuleCode.droptext.getObject();
							samModuleRights = iSamModuleRight.queryRightByModuleSeq(module.getModuleSeq());
						}
					} else {
						cOperType.setItems(new String[]{"系统登录","系统退出"});
						samModuleRights = iSamModuleRight.queryRightByModuleSeq(null);
					}
					if (null != samModuleRights && samModuleRights.size()>0){
						for (int i = 0; i < samModuleRights.size(); i++) {
							cOperType.add(samModuleRights.get(i).getRightName());
						}
					}
					cOperType.setText(temp);
				} catch (UserBusinessException e) {
					LogUtil.operLog(e,"E",true,true);
				}
			}
		});

		Label lbUserCode = new Label(groupMain, SWT.RIGHT);
		lbUserCode.setFont(StyleFinal.SYS_FONT);
		lbUserCode.setText("操作员:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUserCode.setLayoutData(gridData);
		
		dropUserCode = new DropUser(groupMain, SWT.BORDER);
		dropUserCode.init(null);
		dropUserCode.droptext.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropUserCode.setLayoutData(gridData);
		
		Label lbStatus = new Label(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("运行状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStatus.setLayoutData(gridData);
		
		cStatus = new CCombo(groupMain, SWT.BORDER);
		cStatus.setItems(CommFinal.getAllName(CommFinal.ARR_LOG_STATUS));
		cStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cStatus.setLayoutData(gridData);
		
		Label lbStartDate = new Label(groupMain, SWT.RIGHT);
		lbStartDate.setFont(StyleFinal.SYS_FONT);
		lbStartDate.setText("开始日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStartDate.setLayoutData(gridData);
		
		dStartDate = new CCalendar(groupMain, SWT.BORDER);
		dStartDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dStartDate.setLayoutData(gridData);
		
		Label lbEndDate = new Label(groupMain, SWT.RIGHT);
		lbEndDate.setFont(StyleFinal.SYS_FONT);
		lbEndDate.setText("结束日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbEndDate.setLayoutData(gridData);
		
		dEndDate = new CCalendar(groupMain, SWT.BORDER);
		dEndDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dEndDate.setLayoutData(gridData);
		
		dropModuleCode.droptext.setValue(fModuleCode);
		cOperType.setText(fOperType);
		dropUserCode.droptext.setValue(fUserCode);
		cStatus.setText(CommFinal.getItemName(CommFinal.ARR_LOG_STATUS, fStatus));
		dStartDate.setText(fStartDate);
		dEndDate.setText(fEndDate);

		dropModuleCode.droptext.txtShow.forceFocus();
		dropModuleCode.droptext.txtShow.selectAll();

		callMethod.bindEnterEvent(this, dropModuleCode.droptext.txtShow, cOperType, "");
		callMethod.bindEnterEvent(this, cOperType, dropUserCode.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropUserCode.droptext.txtShow, cStatus, "");
		callMethod.bindEnterEvent(this, cStatus, dStartDate.txtDate, "");
		callMethod.bindEnterEvent(this, dStartDate.txtDate, dEndDate.txtDate, "");
		callMethod.bindEnterEvent(this, dEndDate.txtDate, null, "");

		return compMain;
	}

	protected void buttonPressed(int buttonId){
		if(1 == buttonId){
			/**
			 * 确定
			 */
			fModuleCode = dropModuleCode.droptext.getValue();
			fOperType = cOperType.getText();
			fUserCode = dropUserCode.droptext.getValue();
			fStatus = CommFinal.getItemValue(CommFinal.ARR_LOG_STATUS, cStatus.getText());
			fStartDate = dStartDate.getText();
			fEndDate = dEndDate.getText();
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

	public String getfModuleCode() {
		return fModuleCode;
	}

	public void setfModuleCode(String fModuleCode) {
		this.fModuleCode = fModuleCode;
	}

	public String getfOperType() {
		return fOperType;
	}

	public void setfOperType(String fOperType) {
		this.fOperType = fOperType;
	}

	public String getfUserCode() {
		return fUserCode;
	}

	public void setfUserCode(String fUserCode) {
		this.fUserCode = fUserCode;
	}

	public String getfStatus() {
		return fStatus;
	}

	public void setfStatus(String fStatus) {
		this.fStatus = fStatus;
	}

	public String getfStartDate() {
		return fStartDate;
	}

	public void setfStartDate(String fStartDate) {
		this.fStartDate = fStartDate;
	}

	public String getfEndDate() {
		return fEndDate;
	}

	public void setfEndDate(String fEndDate) {
		this.fEndDate = fEndDate;
	}
	
}
