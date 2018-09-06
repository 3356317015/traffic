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
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.drop.DropOrganize;
import com.zhima.traffic.action.epd.IEpdDealorganize;
import com.zhima.traffic.action.epd.impl.ImpEpdDealorganize;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdDealorganize;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdDealorganizeEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private DropOrganize dropOrganize;
	private CCombo cboDealStatus;
	private Text txtOrganizeUrl;
	private Text txtRemark;
	private Button btnSendCar;
	private Button btnSendDriver;
	private Button btnSendSafe;
	private Button btnReceiveCar;
	private Button btnReceiveDriver;
	private Button btnReceiveSafe;
	

	protected EpdDealorganizeEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("配载站设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(515,305);
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
		groupMain.setText("配载站信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		groupMain.setLayoutData(data);
		
		KLabel lbOrganize = new KLabel(groupMain, SWT.RIGHT);
		lbOrganize.setFont(StyleFinal.SYS_FONT);
		lbOrganize.setText("配载站:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganize.setLayoutData(gridData);
	
		dropOrganize = new DropOrganize(groupMain, SWT.BORDER);
		dropOrganize.initType("2");
		dropOrganize.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropOrganize.setLayoutData(gridData);
		
		KLabel lbDealStatus = new KLabel(groupMain, SWT.RIGHT);
		lbDealStatus.setFont(StyleFinal.SYS_FONT);
		lbDealStatus.setText("状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbDealStatus.setLayoutData(gridData);
		
		cboDealStatus = new CCombo(groupMain, SWT.BORDER);
		cboDealStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboDealStatus.setLayoutData(gridData);
		
		KLabel lbOrganizeUrl = new KLabel(groupMain, SWT.RIGHT);
		lbOrganizeUrl.setFont(StyleFinal.SYS_FONT);
		lbOrganizeUrl.setText("服务地址:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeUrl.setLayoutData(gridData);
		
		txtOrganizeUrl = new Text(groupMain, SWT.BORDER);
		txtOrganizeUrl.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtOrganizeUrl.setLayoutData(gridData);
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		Group groupSend = new Group(compMain,SWT.NONE);
		groupSend.setText("发送数据信息");
		groupSend.setFont(StyleFinal.SYS_FONT);
		gridLayout = new GridLayout(10,true);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupSend.setLayout(gridLayout);
		
		data = new FormData();
		data.top = new FormAttachment(groupMain,10);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		groupSend.setLayoutData(data);
		
		new Label(groupSend, SWT.NONE);
		
		btnSendCar = new Button(groupSend, SWT.CHECK);
		btnSendCar.setText("车辆档案");
		btnSendCar.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		btnSendCar.setLayoutData(gridData);
		
		btnSendDriver = new Button(groupSend, SWT.CHECK);
		btnSendDriver.setText("驾驶员档案");
		btnSendDriver.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		btnSendDriver.setLayoutData(gridData);
		
		btnSendSafe = new Button(groupSend, SWT.CHECK);
		btnSendSafe.setText("安检记录");
		btnSendSafe.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		btnSendSafe.setLayoutData(gridData);
		
		Group groupReceive = new Group(compMain,SWT.NONE);
		groupReceive.setText("接收数据信息");
		groupReceive.setFont(StyleFinal.SYS_FONT);
		gridLayout = new GridLayout(10,true);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupReceive.setLayout(gridLayout);

		data = new FormData();
		data.top = new FormAttachment(groupSend,10);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupReceive.setLayoutData(data);
		
		new Label(groupReceive, SWT.NONE);
		
		btnReceiveCar = new Button(groupReceive, SWT.CHECK);
		btnReceiveCar.setText("车辆档案");
		btnReceiveCar.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		btnReceiveCar.setLayoutData(gridData);
		
		btnReceiveDriver = new Button(groupReceive, SWT.CHECK);
		btnReceiveDriver.setText("驾驶员档案");
		btnReceiveDriver.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		btnReceiveDriver.setLayoutData(gridData);
		
		btnReceiveSafe = new Button(groupReceive, SWT.CHECK);
		btnReceiveSafe.setText("安检记录");
		btnReceiveSafe.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		btnReceiveSafe.setLayoutData(gridData);
		
		initData();
		dropOrganize.droptext.txtShow.forceFocus();
		dropOrganize.droptext.txtShow.selectAll();

		callMethod.bindEnterEvent(this, dropOrganize.droptext.txtShow, cboDealStatus, "");
		callMethod.bindEnterEvent(this, cboDealStatus, txtOrganizeUrl, "");
		callMethod.bindEnterEvent(this, txtOrganizeUrl, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		cboDealStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_DEAL_STATUS));
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdDealorganize dealorganize = (EpdDealorganize) gridView.getSelection();
			if (null != dealorganize && !"".equals(dealorganize.getDealorganizeSeq())){
				dropOrganize.droptext.setValue(dealorganize.getDealorganize());
				cboDealStatus.setText(CommFinal.getItemName(TraffFinal.ARR_DEAL_STATUS,String.valueOf(dealorganize.getDealStatus())));
				txtOrganizeUrl.setText(dealorganize.getOrganizeUrl());
				txtRemark.setText(dealorganize.getRemark());
				if(1== dealorganize.getSendCar()){
					btnSendCar.setSelection(true);
				}else{
					btnSendCar.setSelection(false);
				}
				
				if(1== dealorganize.getSendDriver()){
					btnSendDriver.setSelection(true);
				}else{
					btnSendDriver.setSelection(false);
				}
				
				if(1== dealorganize.getSendSafe()){
					btnSendSafe.setSelection(true);
				}else{
					btnSendSafe.setSelection(false);
				}
				
				if(1== dealorganize.getReceiveCar()){
					btnReceiveCar.setSelection(true);
				}else{
					btnReceiveCar.setSelection(false);
				}
				
				if(1== dealorganize.getReceiveDriver()){
					btnReceiveDriver.setSelection(true);
				}else{
					btnReceiveDriver.setSelection(false);
				}
				
				if(1== dealorganize.getReceiveSafe()){
					btnReceiveSafe.setSelection(true);
				}else{
					btnReceiveSafe.setSelection(false);
				}
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdDealorganize epdDealorganize = validData();
				if (null != epdDealorganize){
					IEpdDealorganize iEpdDealorganize = new ImpEpdDealorganize();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdDealorganize newDealorganize = iEpdDealorganize.insert(epdDealorganize,CommFinal.initConfig());
						gridView.addRow(newDealorganize);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdDealorganize.update(epdDealorganize,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdDealorganize);
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
	
	private EpdDealorganize validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdDealorganize epdDealorganize = new EpdDealorganize();
		epdDealorganize.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdDealorganize = (EpdDealorganize) gridView.getSelection();
		}
		epdDealorganize.setUpdateTime(currTime);
		if (null != dropOrganize.droptext.getValue() && dropOrganize.droptext.getValue().length()>0){
			epdDealorganize.setDealorganize(dropOrganize.droptext.getValue());
			epdDealorganize.setOrganizeName(dropOrganize.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择配载站！");
			dropOrganize.droptext.txtShow.forceFocus();
			dropOrganize.droptext.txtShow.selectAll();
			return null;
		}
		
		if (null != cboDealStatus.getText() && cboDealStatus.getText().length()>0){
			epdDealorganize.setDealStatus(Integer.valueOf(CommFinal.getItemValue(TraffFinal.ARR_DEAL_STATUS,
					cboDealStatus.getText())));
		}else{
			MsgBox.warning(getParentShell(),"请选择配载站状态！");
			cboDealStatus.forceFocus();
			return null;
		}
		if (null != txtOrganizeUrl.getText() && txtOrganizeUrl.getText().length()>0){
			epdDealorganize.setOrganizeUrl(txtOrganizeUrl.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入配载站服务地址！");
			txtOrganizeUrl.forceFocus();
			return null;
		}
		
		if (btnSendCar.getSelection()==true){
			epdDealorganize.setSendCar(1);
		}else{
			epdDealorganize.setSendCar(0);
		}
		
		if (btnSendDriver.getSelection()==true){
			epdDealorganize.setSendDriver(1);
		}else{
			epdDealorganize.setSendDriver(0);
		}
		
		if (btnSendSafe.getSelection()==true){
			epdDealorganize.setSendSafe(1);
		}else{
			epdDealorganize.setSendSafe(0);
		}
		
		if (btnReceiveCar.getSelection()==true){
			epdDealorganize.setReceiveCar(1);
		}else{
			epdDealorganize.setReceiveCar(0);
		}
		
		if (btnReceiveDriver.getSelection()==true){
			epdDealorganize.setReceiveDriver(1);
		}else{
			epdDealorganize.setReceiveDriver(0);
		}
		
		if (btnReceiveSafe.getSelection()==true){
			epdDealorganize.setReceiveSafe(1);
		}else{
			epdDealorganize.setReceiveSafe(0);
		}

		epdDealorganize.setRemark(txtRemark.getText());
		
		return epdDealorganize;
	}
	
	private void clearData(){
		dropOrganize.droptext.setValue("");
		cboDealStatus.setText("");
		txtOrganizeUrl.setText("");
		txtRemark.setText("");
		btnSendCar.setSelection(false);
		btnSendDriver.setSelection(false);
		btnSendSafe.setSelection(false);
		btnReceiveCar.setSelection(false);
		btnReceiveDriver.setSelection(false);
		btnReceiveSafe.setSelection(false);
		dropOrganize.droptext.txtShow.forceFocus();
	}
	
}
