package com.zhima.traffic.ui.operate;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.traffic.action.operate.IItsLinerfare;
import com.zhima.traffic.action.operate.impl.ImpItsLinerfare;
import com.zhima.traffic.drop.DropRoute;
import com.zhima.traffic.drop.DropStation;
import com.zhima.traffic.model.ItsLinerfare;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CCalendar;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class ItsLinerFareEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private DropRoute dropRoute;
	private DropStation dropStation;
	private Text txtLinerId;
	private CCalendar txtLinerDate;
	
	private Text txtBaseFare;
	private Text txtStationFare;
	private Text txtFuelFare;
	private Text txtOtherOne;
	private Text txtFullFare;
	

	protected ItsLinerFareEditUi(Shell shell,GridView gridView,String operType) {
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
        return new Point(455,255);
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
		groupMain.setLayoutData(data);
		
		KLabel lbDropRoute = new KLabel(groupMain, SWT.RIGHT);
		lbDropRoute.setFont(StyleFinal.SYS_FONT);
		lbDropRoute.setText("线路名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbDropRoute.setLayoutData(gridData);
		
		dropRoute = new DropRoute(groupMain, SWT.BORDER);
		dropRoute.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropRoute.setLayoutData(gridData);
		
		KLabel lbDropStation = new KLabel(groupMain, SWT.RIGHT);
		lbDropStation.setFont(StyleFinal.SYS_FONT);
		lbDropStation.setText("站点名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbDropStation.setLayoutData(gridData);
		
		dropStation = new DropStation(groupMain, SWT.BORDER);
		dropStation.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropStation.setLayoutData(gridData);
		
		KLabel lbLinerId = new KLabel(groupMain, SWT.RIGHT);
		lbLinerId.setFont(StyleFinal.SYS_FONT);
		lbLinerId.setText("车次:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerId.setLayoutData(gridData);
		
		txtLinerId = new Text(groupMain, SWT.BORDER);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(gridData);
		
		KLabel lbLinerDate = new KLabel(groupMain, SWT.RIGHT);
		lbLinerDate.setFont(StyleFinal.SYS_FONT);
		lbLinerDate.setText("日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerDate.setLayoutData(gridData);
		
		txtLinerDate = new CCalendar(groupMain, SWT.BORDER);
		txtLinerDate.txtDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtLinerDate.setLayoutData(gridData);
		
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
		dropRoute.droptext.txtShow.forceFocus();
		dropRoute.droptext.txtShow.selectAll();

		callMethod.bindEnterEvent(this, dropRoute.droptext.txtShow, dropStation.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropStation.droptext.txtShow, txtLinerId, "");
		callMethod.bindEnterEvent(this, txtLinerId, txtLinerDate.txtDate, "");
		callMethod.bindEnterEvent(this, txtLinerDate.txtDate, txtBaseFare, "");
		callMethod.bindEnterEvent(this, txtBaseFare, txtStationFare, "");
		callMethod.bindEnterEvent(this, txtStationFare, txtFuelFare, "");
		callMethod.bindEnterEvent(this, txtFuelFare, txtOtherOne, "");
		callMethod.bindEnterEvent(this, txtOtherOne, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			ItsLinerfare itsLinerfare = (ItsLinerfare) gridView.getSelection();
			if (null != itsLinerfare && !"".equals(itsLinerfare.getLinerfareSeq())){
				dropRoute.droptext.setValue(itsLinerfare.getRouteSeq());
				dropStation.droptext.setValue(itsLinerfare.getStationSeq());
				txtLinerId.setText(itsLinerfare.getLinerId());
				txtLinerDate.setText(itsLinerfare.getLinerDate());
				
				dropRoute.droptext.setEnabled(false);
				dropStation.droptext.setEnabled(false);
				txtLinerId.setEnabled(false);
				txtLinerDate.setEnabled(false);
				
				txtBaseFare.setText(String.valueOf(itsLinerfare.getBaseFare()));
				txtStationFare.setText(String.valueOf(itsLinerfare.getStationFare()));
				txtFuelFare.setText(String.valueOf(itsLinerfare.getFuelFare()));
				txtOtherOne.setText(String.valueOf(itsLinerfare.getOtherOne()));
				txtFullFare.setText(String.valueOf(itsLinerfare.getFullFare()));
				
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
				ItsLinerfare itsLinerfare = validData();
				if (null != itsLinerfare){
					IItsLinerfare iItsLinerfare = new ImpItsLinerfare();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						ItsLinerfare newItsLinerfare = iItsLinerfare.insert(itsLinerfare,CommFinal.initConfig());
						gridView.addRow(newItsLinerfare);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iItsLinerfare.update(itsLinerfare,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), itsLinerfare);
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
	
	private ItsLinerfare validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		ItsLinerfare iTSLinerfare = new ItsLinerfare();
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			iTSLinerfare = (ItsLinerfare) gridView.getSelection();
		}
		iTSLinerfare.setUpdateTime(currTime);
		if (null != dropRoute.droptext.getValue() && dropRoute.droptext.getValue().length()>0){
			iTSLinerfare.setRouteSeq(dropRoute.droptext.getValue());
			iTSLinerfare.setRouteName(dropRoute.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择线路！");
			dropRoute.droptext.txtShow.forceFocus();
			dropRoute.droptext.txtShow.selectAll();
			return null;
		}
		
		if (null != dropStation.droptext.getValue() && dropStation.droptext.getValue().length()>0){
			iTSLinerfare.setStationSeq(dropStation.droptext.getValue());
			iTSLinerfare.setStationName(dropStation.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择到达站！");
			dropStation.droptext.txtShow.forceFocus();
			dropStation.droptext.txtShow.selectAll();
			return null;
		}
		if (null != txtLinerId.getText() && txtLinerId.getText().length()>0){
			iTSLinerfare.setLinerId(txtLinerId.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入车次！");
			txtLinerId.forceFocus();
			return null;
		}
		if (null != txtLinerDate.getText() && txtLinerDate.getText().length()>0){
			iTSLinerfare.setLinerDate(txtLinerDate.getText());
		}else{
			MsgBox.warning(getParentShell(),"请设置车次日期！");
			txtLinerDate.forceFocus();
			return null;
		}
		iTSLinerfare.setBaseFare(Double.valueOf(txtBaseFare.getText()));
		iTSLinerfare.setStationFare(Double.valueOf(txtStationFare.getText()));
		iTSLinerfare.setFuelFare(Double.valueOf(txtFuelFare.getText()));
		iTSLinerfare.setOtherOne(Double.valueOf(txtOtherOne.getText()));
		iTSLinerfare.setFullFare(Double.valueOf(txtBaseFare.getText())
				+Double.valueOf(txtStationFare.getText())
				+Double.valueOf(txtFuelFare.getText())
				+Double.valueOf(txtOtherOne.getText()));
		
		return iTSLinerfare;
	}
	
	private void clearData(){
		dropRoute.droptext.setValue("");
		dropStation.droptext.setValue("");
		txtLinerId.setText("");
		txtLinerDate.setText(DateUtils.getNow(DateUtils.FORMAT_SHORT));
		txtBaseFare.setText("");
		txtStationFare.setText("");
		txtFuelFare.setText("");
		txtOtherOne.setText("");
		txtFullFare.setText("");
		dropRoute.droptext.txtShow.forceFocus();
	}
	
}
