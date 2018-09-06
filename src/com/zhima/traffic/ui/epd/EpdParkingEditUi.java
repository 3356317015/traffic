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
import com.zhima.traffic.action.epd.IEpdParking;
import com.zhima.traffic.action.epd.impl.ImpEpdParking;
import com.zhima.traffic.drop.DropCheckgate;
import com.zhima.traffic.model.EpdParking;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridView;

public class EpdParkingEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private DropCheckgate dropCheckgate;
	private Text txtParkingCode;
	private Text txtParkingName;
	private Text txtSeatNum;
	private Text txtRemark;

	protected EpdParkingEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("发车位设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,250);
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
		groupMain.setText("发车位信息");
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
		
		Label lbCheckgate = new Label(groupMain, SWT.RIGHT);
		lbCheckgate.setFont(StyleFinal.SYS_FONT);
		lbCheckgate.setText("检票口名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCheckgate.setLayoutData(gridData);
		
		dropCheckgate = new DropCheckgate(groupMain, SWT.BORDER);
		dropCheckgate.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropCheckgate.setLayoutData(gridData);

		KLabel lbParkingCode = new KLabel(groupMain, SWT.RIGHT);
		lbParkingCode.setFont(StyleFinal.SYS_FONT);
		lbParkingCode.setText("发车位代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbParkingCode.setLayoutData(gridData);
		
		txtParkingCode = new Text(groupMain, SWT.BORDER);
		txtParkingCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtParkingCode.setLayoutData(gridData);
		
		KLabel lbParkingName = new KLabel(groupMain, SWT.RIGHT);
		lbParkingName.setFont(StyleFinal.SYS_FONT);
		lbParkingName.setText("发车位名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbParkingName.setLayoutData(gridData);
		
		txtParkingName = new Text(groupMain, SWT.BORDER);
		txtParkingName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtParkingName.setLayoutData(gridData);
		
		KLabel lbSeatNum = new KLabel(groupMain, SWT.RIGHT);
		lbSeatNum.setFont(StyleFinal.SYS_FONT);
		lbSeatNum.setText("车辆座位数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSeatNum.setLayoutData(gridData);
		
		txtSeatNum = new Text(groupMain, SWT.BORDER);
		txtSeatNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSeatNum.setLayoutData(gridData);
		txtSeatNum.addVerifyListener(new TextVerifyListener(1));
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		initData();
		dropCheckgate.droptext.txtShow.forceFocus();
		dropCheckgate.droptext.txtShow.selectAll();
		
		callMethod.bindEnterEvent(this, dropCheckgate.droptext.txtShow, txtParkingCode, "");
		callMethod.bindEnterEvent(this, txtParkingCode, txtParkingName, "");
		callMethod.bindEnterEvent(this, txtParkingName, txtSeatNum, "");
		callMethod.bindEnterEvent(this, txtSeatNum, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdParking epdParking = (EpdParking) gridView.getSelection();
			if (null != epdParking && !"".equals(epdParking.getParkingSeq())){
				dropCheckgate.droptext.setValue(epdParking.getCheckgateSeq());
				txtParkingCode.setText(epdParking.getParkingCode());
				txtParkingName.setText(epdParking.getParkingName());
				txtSeatNum.setText(String.valueOf(epdParking.getSeatNum()));
				txtRemark.setText(epdParking.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdParking epdParking = validData();
				if (null != epdParking){
					IEpdParking iEpdParking = new ImpEpdParking();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdParking newEpdParking = iEpdParking.insert(epdParking,CommFinal.initConfig());
						gridView.addRow(newEpdParking);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdParking.update(epdParking,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdParking);
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
	
	private EpdParking validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdParking epdParking = new EpdParking();
		epdParking.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdParking = (EpdParking) gridView.getSelection();
		}
		epdParking.setUpdateTime(currTime);
		
		if (null != dropCheckgate.droptext.getValue() && !"".equals(dropCheckgate.droptext.getValue())){
			epdParking.setCheckgateSeq(dropCheckgate.droptext.getValue());
			epdParking.setCheckName(dropCheckgate.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择检票口！");
			dropCheckgate.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != txtParkingCode.getText() && txtParkingCode.getText().length()>0){
			epdParking.setParkingCode(txtParkingCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入发车位代码！");
			txtParkingCode.forceFocus();
			return null;
		}
		
		if (null != txtParkingName.getText() && txtParkingName.getText().length()>0){
			epdParking.setParkingName(txtParkingName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入发车位名称！");
			txtParkingName.forceFocus();
			return null;
		}
		
		if (null != txtSeatNum.getText() && !"".equals(txtSeatNum.getText())){
			epdParking.setSeatNum(Integer.valueOf(txtSeatNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入发车位允许车辆座位数！");
			txtSeatNum.forceFocus();
			return null;
		}
		
		epdParking.setRemark(txtRemark.getText());
		
		return epdParking;
	}
	
	private void clearData(){
		dropCheckgate.droptext.setValue("");
		txtParkingCode.setText("");
		txtParkingName.setText("");
		txtSeatNum.setText("");
		txtRemark.setText("");
		dropCheckgate.droptext.txtShow.forceFocus();
	}
	
}
