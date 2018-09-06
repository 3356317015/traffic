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
import com.zhima.traffic.action.epd.IEpdCargrade;
import com.zhima.traffic.action.epd.impl.ImpEpdCargrade;
import com.zhima.traffic.model.EpdCargrade;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdCargradeEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtCargradeCode;
	private Text txtCargradeName;
	private Text txtRemark;

	protected EpdCargradeEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("车型等级设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,200);
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
		groupMain.setText("车型信息");
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
		
		KLabel lbCargradeCode = new KLabel(groupMain, SWT.RIGHT);
		lbCargradeCode.setFont(StyleFinal.SYS_FONT);
		lbCargradeCode.setText("车型代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCargradeCode.setLayoutData(gridData);
		
		txtCargradeCode = new Text(groupMain, SWT.BORDER);
		txtCargradeCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCargradeCode.setLayoutData(gridData);
		
		KLabel lbCargradeName = new KLabel(groupMain, SWT.RIGHT);
		lbCargradeName.setFont(StyleFinal.SYS_FONT);
		lbCargradeName.setText("车型名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCargradeName.setLayoutData(gridData);
		
		txtCargradeName = new Text(groupMain, SWT.BORDER);
		txtCargradeName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCargradeName.setLayoutData(gridData);
		
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
		txtCargradeCode.forceFocus();
		txtCargradeCode.selectAll();

		callMethod.bindEnterEvent(this, txtCargradeCode, txtCargradeName, "");
		callMethod.bindEnterEvent(this, txtCargradeName, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdCargrade epdCargrade = (EpdCargrade) gridView.getSelection();
			if (null != epdCargrade && !"".equals(epdCargrade.getCargradeSeq())){
				txtCargradeCode.setText(epdCargrade.getCargradeCode());
				txtCargradeName.setText(epdCargrade.getCargradeName());
				txtRemark.setText(epdCargrade.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdCargrade epdCargrade = validData();
				if (null != epdCargrade){
					IEpdCargrade iEpdCargrade = new ImpEpdCargrade();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdCargrade newCargrade = iEpdCargrade.insert(epdCargrade,CommFinal.initConfig());
						gridView.addRow(newCargrade);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdCargrade.update(epdCargrade,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdCargrade);
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
	
	private EpdCargrade validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdCargrade epdCargrade = new EpdCargrade();
		epdCargrade.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdCargrade = (EpdCargrade) gridView.getSelection();
		}
		epdCargrade.setUpdateTime(currTime);
		if (null != txtCargradeCode.getText() && txtCargradeCode.getText().length()>0){
			epdCargrade.setCargradeCode(txtCargradeCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入车型等级代码！");
			txtCargradeCode.forceFocus();
			return null;
		}
		
		if (null != txtCargradeName.getText() && txtCargradeName.getText().length()>0){
			epdCargrade.setCargradeName(txtCargradeName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入车型等级名称！");
			txtCargradeName.forceFocus();
			return null;
		}
		epdCargrade.setRemark(txtRemark.getText());
		
		return epdCargrade;
	}
	
	private void clearData(){
		txtCargradeCode.setText("");
		txtCargradeName.setText("");
		txtRemark.setText("");
		txtCargradeCode.forceFocus();
	}
	
}
