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
import com.zhima.traffic.action.epd.IEpdFaresuit;
import com.zhima.traffic.action.epd.impl.ImpEpdFaresuit;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdFaresuit;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.Validate;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CCalendar;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdFaresuitEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtFaresuitCode;
	private Text txtFaresuitName;
	private CCalendar txtStartDate;
	private CCalendar txtEndDate;
	private CCombo cboFaresuitStatus;
	private Text txtRemark;

	protected EpdFaresuitEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("价套定义设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(255,285);
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
		groupMain.setText("价套信息");
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
		
		KLabel lbFaresuitCode = new KLabel(groupMain, SWT.RIGHT);
		lbFaresuitCode.setFont(StyleFinal.SYS_FONT);
		lbFaresuitCode.setText("价套代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFaresuitCode.setLayoutData(gridData);
		
		txtFaresuitCode = new Text(groupMain, SWT.BORDER);
		txtFaresuitCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFaresuitCode.setLayoutData(gridData);
		
		KLabel lbFaresuitName = new KLabel(groupMain, SWT.RIGHT);
		lbFaresuitName.setFont(StyleFinal.SYS_FONT);
		lbFaresuitName.setText("价套名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFaresuitName.setLayoutData(gridData);
		
		txtFaresuitName = new Text(groupMain, SWT.BORDER);
		txtFaresuitName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFaresuitName.setLayoutData(gridData);
		
		KLabel lbStartDate = new KLabel(groupMain, SWT.RIGHT);
		lbStartDate.setFont(StyleFinal.SYS_FONT);
		lbStartDate.setText("开始日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStartDate.setLayoutData(gridData);
		
		txtStartDate = new CCalendar(groupMain, SWT.BORDER);
		txtStartDate.txtDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtStartDate.setLayoutData(gridData);
		
		KLabel lbEndDate = new KLabel(groupMain, SWT.RIGHT);
		lbEndDate.setFont(StyleFinal.SYS_FONT);
		lbEndDate.setText("结束日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbEndDate.setLayoutData(gridData);
		
		txtEndDate = new CCalendar(groupMain, SWT.BORDER);
		txtEndDate.txtDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtEndDate.setLayoutData(gridData);
		
		KLabel lbFaresuitStatus = new KLabel(groupMain, SWT.RIGHT);
		lbFaresuitStatus.setFont(StyleFinal.SYS_FONT);
		lbFaresuitStatus.setText("价套状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbFaresuitStatus.setLayoutData(gridData);
		
		cboFaresuitStatus = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cboFaresuitStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboFaresuitStatus.setLayoutData(gridData);
		
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
		txtFaresuitCode.forceFocus();
		txtFaresuitCode.selectAll();

		callMethod.bindEnterEvent(this, txtFaresuitCode, txtFaresuitName, "");
		callMethod.bindEnterEvent(this, txtFaresuitName, txtStartDate.txtDate, "");
		callMethod.bindEnterEvent(this, txtStartDate.txtDate, txtEndDate.txtDate, "");
		callMethod.bindEnterEvent(this, txtEndDate.txtDate, cboFaresuitStatus, "");
		callMethod.bindEnterEvent(this, cboFaresuitStatus, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		cboFaresuitStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_FARESUIT_STATUS));
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdFaresuit epdFaresuit = (EpdFaresuit) gridView.getSelection();
			if (null != epdFaresuit && !"".equals(epdFaresuit.getFaresuitSeq())){
				txtFaresuitCode.setText(epdFaresuit.getFaresuitCode());
				txtFaresuitName.setText(epdFaresuit.getFaresuitName());
				txtStartDate.setText(epdFaresuit.getStartDate());
				txtEndDate.setText(epdFaresuit.getEndDate());
				cboFaresuitStatus.setText(CommFinal.getItemName(TraffFinal.ARR_FARESUIT_STATUS, epdFaresuit.getFaresuitStatus()));
				txtRemark.setText(epdFaresuit.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdFaresuit epdFaresuit = validData();
				if (null != epdFaresuit){
					IEpdFaresuit iEpdFaresuit = new ImpEpdFaresuit();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdFaresuit newFaresuit = iEpdFaresuit.insert(epdFaresuit,CommFinal.initConfig());
						gridView.addRow(newFaresuit);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdFaresuit.update(epdFaresuit,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdFaresuit);
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
	
	private EpdFaresuit validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdFaresuit epdFaresuit = new EpdFaresuit();
		epdFaresuit.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdFaresuit = (EpdFaresuit) gridView.getSelection();
		}
		epdFaresuit.setUpdateTime(currTime);
		if (null != txtFaresuitCode.getText() && txtFaresuitCode.getText().length()>0){
			epdFaresuit.setFaresuitCode(txtFaresuitCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入价套代码！");
			txtFaresuitCode.forceFocus();
			return null;
		}
		
		if (null != txtFaresuitName.getText() && txtFaresuitName.getText().length()>0){
			epdFaresuit.setFaresuitName(txtFaresuitName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入价套名称！");
			txtFaresuitName.forceFocus();
			return null;
		}
		
		if (Validate.isDate(txtStartDate.getText())==true){
			epdFaresuit.setStartDate(txtStartDate.getText());
		}else{
			MsgBox.warning(getShell(), "开始日期不合法！");
			txtStartDate.forceFocus();
			return null;
		}
		
		if (Validate.isDate(txtEndDate.getText())==true){
			epdFaresuit.setEndDate(txtEndDate.getText());
		}else{
			MsgBox.warning(getShell(), "结束日期不合法！");
			txtEndDate.forceFocus();
			return null;
		}
		
		if (null != cboFaresuitStatus.getText() && !"".equals(cboFaresuitStatus.getText())){
			epdFaresuit.setFaresuitStatus(CommFinal.getItemValue(TraffFinal.ARR_CAR_STATUS,cboFaresuitStatus.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择价套状态！");
			cboFaresuitStatus.forceFocus();
			return null;
		}
		epdFaresuit.setRemark(txtRemark.getText());

		return epdFaresuit;
	}
	
	private void clearData(){
		txtFaresuitCode.setText("");
		txtFaresuitName.setText("");
		txtStartDate.setText("    -  -  ");
		txtEndDate.setText("    -  -  ");
		cboFaresuitStatus.setText("");
		txtRemark.setText("");
		txtFaresuitCode.forceFocus();
	}
	
}
