package com.zhima.traffic.ui.epd;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import com.zhima.traffic.action.epd.IEpdFare;
import com.zhima.traffic.action.epd.impl.ImpEpdFare;
import com.zhima.traffic.model.EpdFare;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.StringUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.grid.GridView;

public class EpdFareItemEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtCargradeName;
	private Text txtStationName;
	
	private Text txtBaseFare;
	private Text txtStationFare;
	private Text txtFuelFare;
	private Text txtOtherOne;
	private Text txtFullFare;
	

	protected EpdFareItemEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("车次价格设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(415,230);
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
		groupMain.setText("价格信息");
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
		
		Label lbDropRoute = new Label(groupMain, SWT.RIGHT);
		lbDropRoute.setFont(StyleFinal.SYS_FONT);
		lbDropRoute.setText("车型名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbDropRoute.setLayoutData(gridData);
		
		txtCargradeName = new Text(groupMain, SWT.BORDER);
		txtCargradeName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCargradeName.setLayoutData(gridData);
		
		Label lbStationName = new Label(groupMain, SWT.RIGHT);
		lbStationName.setFont(StyleFinal.SYS_FONT);
		lbStationName.setText("目的地:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStationName.setLayoutData(gridData);
		
		txtStationName = new Text(groupMain, SWT.BORDER);
		txtStationName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStationName.setLayoutData(gridData);
		
		KLabel lbBaseFare = new KLabel(groupMain, SWT.RIGHT);
		lbBaseFare.setFont(StyleFinal.SYS_FONT);
		lbBaseFare.setText("基础价:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbBaseFare.setLayoutData(gridData);
		
		txtBaseFare = new Text(groupMain, SWT.BORDER);
		txtBaseFare.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtBaseFare.setLayoutData(gridData);
		txtBaseFare.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO Auto-generated method stub
				clacInit();
			}
		});
		
		KLabel lbStationFare = new KLabel(groupMain, SWT.RIGHT);
		lbStationFare.setFont(StyleFinal.SYS_FONT);
		lbStationFare.setText("站务费:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStationFare.setLayoutData(gridData);
		
		txtStationFare = new Text(groupMain, SWT.BORDER);
		txtStationFare.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStationFare.setLayoutData(gridData);
		txtStationFare.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO Auto-generated method stub
				clacInit();
			}
		});
		
		KLabel lbFuelFare = new KLabel(groupMain, SWT.RIGHT);
		lbFuelFare.setFont(StyleFinal.SYS_FONT);
		lbFuelFare.setText("然油附加费:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFuelFare.setLayoutData(gridData);
		
		txtFuelFare = new Text(groupMain, SWT.BORDER);
		txtFuelFare.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFuelFare.setLayoutData(gridData);
		txtFuelFare.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO Auto-generated method stub
				clacInit();
			}
		});
		
		KLabel lbOtherOne = new KLabel(groupMain, SWT.RIGHT);
		lbOtherOne.setFont(StyleFinal.SYS_FONT);
		lbOtherOne.setText("其他费:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOtherOne.setLayoutData(gridData);
		
		txtOtherOne = new Text(groupMain, SWT.BORDER);
		txtOtherOne.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtOtherOne.setLayoutData(gridData);
		txtOtherOne.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO Auto-generated method stub
				clacInit();
			}
		});
		
		KLabel lbFuellFare = new KLabel(groupMain, SWT.RIGHT);
		lbFuellFare.setFont(StyleFinal.SYS_FONT);
		lbFuellFare.setText("总价:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFuellFare.setLayoutData(gridData);
		
		txtFullFare = new Text(groupMain, SWT.BORDER|SWT.READ_ONLY);
		txtFullFare.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFullFare.setLayoutData(gridData);

		initData();
		txtBaseFare.forceFocus();
		txtBaseFare.selectAll();

		callMethod.bindEnterEvent(this, txtCargradeName, txtStationName, "");
		callMethod.bindEnterEvent(this, txtStationName, txtBaseFare, "");
		callMethod.bindEnterEvent(this, txtBaseFare, txtStationFare, "");
		callMethod.bindEnterEvent(this, txtStationFare, txtFuelFare, "");
		callMethod.bindEnterEvent(this, txtFuelFare, txtOtherOne, "");
		callMethod.bindEnterEvent(this, txtOtherOne, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdFare epdFare = (EpdFare) gridView.getSelection();
			if (null != epdFare && !"".equals(epdFare.getFareSeq())){
				txtCargradeName.setText(epdFare.getCargradeName());
				txtStationName.setText(epdFare.getStationName());
				
				txtCargradeName.setEnabled(false);
				txtStationName.setEnabled(false);
				
				txtBaseFare.setText(String.valueOf(epdFare.getBaseFare()));
				txtStationFare.setText(String.valueOf(epdFare.getStationFare()));
				txtFuelFare.setText(String.valueOf(epdFare.getFuelFare()));
				txtOtherOne.setText(String.valueOf(epdFare.getOtherOne()));
				txtFullFare.setText(String.valueOf(epdFare.getFullFare()));
				
				txtBaseFare.forceFocus();
				txtBaseFare.selectAll();
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdFare epdFare = validData();
				if (null != epdFare){
					IEpdFare iEpdFare = new ImpEpdFare();
					if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdFare.update(epdFare,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdFare);
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
	
	private EpdFare validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdFare epdFare = new EpdFare();
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdFare = (EpdFare) gridView.getSelection();
		}
		epdFare.setUpdateTime(currTime);
		epdFare.setBaseFare(Double.valueOf(txtBaseFare.getText()));
		epdFare.setStationFare(Double.valueOf(txtStationFare.getText()));
		epdFare.setFuelFare(Double.valueOf(txtFuelFare.getText()));
		epdFare.setOtherOne(Double.valueOf(txtOtherOne.getText()));
		epdFare.setFullFare(Double.valueOf(txtBaseFare.getText())
				+Double.valueOf(txtStationFare.getText())
				+Double.valueOf(txtFuelFare.getText())
				+Double.valueOf(txtOtherOne.getText()));
		
		return epdFare;
	}
	
	private void clacInit(){
		String baseFare = txtBaseFare.getText();
		String stationFare = txtStationFare.getText();
		String fuelFare = txtFuelFare.getText();
		String otherOne = txtOtherOne.getText();
		if (StringUtils.isEmpty(baseFare)){
			baseFare ="0";
		}
		if (StringUtils.isEmpty(stationFare)){
			stationFare ="0";
		}
		if (StringUtils.isEmpty(fuelFare)){
			fuelFare ="0";
		}
		if (StringUtils.isEmpty(otherOne)){
			otherOne ="0";
		}
		txtFullFare.setText(String.valueOf(
				Double.valueOf(baseFare)
				+Double.valueOf(stationFare)
				+Double.valueOf(fuelFare)
				+Double.valueOf(otherOne)));
	}
	
}
