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
import com.zhima.traffic.action.voice.IVmsRoute;
import com.zhima.traffic.action.voice.impl.ImpVmsRoute;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.VmsRoute;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.grid.GridView;

public class VmsRouteEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtRouteName;
	private Text txtRouteCode;
	
	private CCombo cboVoiceStatus;

	protected VmsRouteEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("模板设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(300,205);
    }
	
	protected void createButtonsForButtonBar(Composite parent){
		GridData btnData = new GridData(StyleFinal.DIALOGBAR_ALIGNMENT);
		parent.setLayoutData(btnData);
		Button confirm = createButton(parent, 1, "确定(&O)", false);
		confirm.setFont(StyleFinal.SYS_FONT);
		Button cancel = createButton(parent, 0, "取消(&C)", false);
		cancel.setFont(StyleFinal.SYS_FONT);
		if (StyleFinal.BTN_SHOWIMAGE == true){
			confirm.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_OK));
			cancel.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_CANCEL));
		}
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite compMain = (Composite) super.createDialogArea(parent);
		compMain.setLayout(new FormLayout());
		CallMethod callMethod = new CallMethod();
		
		Group groupMain = new Group(compMain,SWT.NONE);
		groupMain.setText("线路信息");
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
		
		Label lbRouteName = new Label(groupMain, SWT.RIGHT);
		lbRouteName.setFont(StyleFinal.SYS_FONT);
		lbRouteName.setText("线路名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteName.setLayoutData(gridData);
		
		txtRouteName = new Text(groupMain, SWT.BORDER);
		txtRouteName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteName.setLayoutData(gridData);
		
		Label lbRouteCode = new Label(groupMain, SWT.RIGHT);
		lbRouteCode.setFont(StyleFinal.SYS_FONT);
		lbRouteCode.setText("线路代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteCode.setLayoutData(gridData);
		
		txtRouteCode = new Text(groupMain, SWT.BORDER);
		txtRouteCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteCode.setLayoutData(gridData);

		Label lbVoiceStatus = new Label(groupMain, SWT.RIGHT);
		lbVoiceStatus.setFont(StyleFinal.SYS_FONT);
		lbVoiceStatus.setText("是否播音:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbVoiceStatus.setLayoutData(gridData);
		
		cboVoiceStatus = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cboVoiceStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboVoiceStatus.setLayoutData(gridData);
		
		initData();
		cboVoiceStatus.forceFocus();
		
		callMethod.bindEnterEvent(this, txtRouteName, txtRouteCode, "");
		callMethod.bindEnterEvent(this, txtRouteCode, cboVoiceStatus, "");
		callMethod.bindEnterEvent(this, cboVoiceStatus, null, "");
		return compMain;
	}
	
	private void initData(){
		cboVoiceStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_VOICE_STATUS));
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			VmsRoute vmsRoute = (VmsRoute) gridView.getSelection();
			txtRouteName.setEditable(false);
			txtRouteCode.setEditable(false);
			txtRouteName.setText(vmsRoute.getRouteName());
			txtRouteCode.setText(vmsRoute.getRouteCode());
			cboVoiceStatus.setText(CommFinal.getItemName(TraffFinal.ARR_VOICE_STATUS, vmsRoute.getVoiceStatus()));
			cboVoiceStatus.forceFocus();
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				VmsRoute vmsRoute = validData();
				if (null != vmsRoute){
					if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						IVmsRoute iVmsRoute = new ImpVmsRoute();
						iVmsRoute.update(vmsRoute, CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), vmsRoute);
						close();
					}
				}
			} else if (0 == buttonId) {
				/**
				 * 取消
				 */
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		
	}
	
	private VmsRoute validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		VmsRoute vmsRoute = new VmsRoute();
		vmsRoute.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			vmsRoute = (VmsRoute) gridView.getSelection();
		}
		vmsRoute.setUpdateTime(currTime);
		vmsRoute.setVoiceStatus(CommFinal.getItemValue(TraffFinal.ARR_VOICE_STATUS, cboVoiceStatus.getText()));
		return vmsRoute;
	}
	
}
