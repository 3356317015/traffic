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
import com.zhima.traffic.action.epd.IEpdCertificate;
import com.zhima.traffic.action.epd.impl.ImpEpdCertificate;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdCertificate;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdCertificateEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	private String cerType="";
	private String cerName="";
	
	private CCombo cboCerType;
	private Text txtCerName;
	private CCombo cboCerStatus;
	private Text txtRemark;

	protected EpdCertificateEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("资质类型设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,230);
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
		groupMain.setText("资质项信息");
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
		
		KLabel lbCerType = new KLabel(groupMain, SWT.RIGHT);
		lbCerType.setFont(StyleFinal.SYS_FONT);
		lbCerType.setText("证件项类型:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCerType.setLayoutData(gridData);
		
		cboCerType = new CCombo(groupMain, SWT.BORDER);
		cboCerType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboCerType.setLayoutData(gridData);
		
		KLabel lbCerName = new KLabel(groupMain, SWT.RIGHT);
		lbCerName.setFont(StyleFinal.SYS_FONT);
		lbCerName.setText("资质项名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCerName.setLayoutData(gridData);
		
		txtCerName = new Text(groupMain, SWT.BORDER);
		txtCerName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCerName.setLayoutData(gridData);
		
		
		KLabel lbCerStatus = new KLabel(groupMain, SWT.RIGHT);
		lbCerStatus.setFont(StyleFinal.SYS_FONT);
		lbCerStatus.setText("资质项状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCerStatus.setLayoutData(gridData);
		
		cboCerStatus = new CCombo(groupMain, SWT.BORDER);
		cboCerStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboCerStatus.setLayoutData(gridData);
		
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
		cboCerType.forceFocus();

		callMethod.bindEnterEvent(this, cboCerType, txtCerName, "");
		callMethod.bindEnterEvent(this, txtCerName, cboCerStatus, "");
		callMethod.bindEnterEvent(this, cboCerStatus, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		cboCerType.setItems(CommFinal.getAllName(TraffFinal.ARR_CER_TYPE));
		cboCerStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_CER_STATUS));
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdCertificate epdCertificate = (EpdCertificate) gridView.getSelection();
			cerType = epdCertificate.getCerType();
			cerName = epdCertificate.getCerName();
			if (null != epdCertificate && !"".equals(epdCertificate.getCertificateSeq())){
				cboCerType.setText(CommFinal.getItemName(TraffFinal.ARR_CER_TYPE, epdCertificate.getCerType()));
				txtCerName.setText(epdCertificate.getCerName());
				cboCerStatus.setText(CommFinal.getItemName(TraffFinal.ARR_CER_STATUS, epdCertificate.getCerStatus()));
				txtRemark.setText(epdCertificate.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdCertificate epdCertificate = validData();
				if (null != epdCertificate){
					IEpdCertificate iEpdCertificate = new ImpEpdCertificate();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdCertificate newEpdCertificate = iEpdCertificate.insert(epdCertificate,CommFinal.initConfig());
						gridView.addRow(newEpdCertificate);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdCertificate.update(epdCertificate,cerType,cerName,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdCertificate);
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
	
	private EpdCertificate validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdCertificate epdCertificate = new EpdCertificate();
		epdCertificate.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdCertificate = (EpdCertificate) gridView.getSelection();
		}
		epdCertificate.setUpdateTime(currTime);
		if (null != cboCerType.getText() && cboCerType.getText().length()>0){
			epdCertificate.setCerType(CommFinal.getItemValue(TraffFinal.ARR_CER_TYPE, cboCerType.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请请选择资质项类型！");
			cboCerType.forceFocus();
			return null;
		}
		
		if (null != txtCerName.getText() && txtCerName.getText().length()>0){
			epdCertificate.setCerName(txtCerName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入资质项名称！");
			txtCerName.forceFocus();
			return null;
		}
		if (null != cboCerStatus.getText() && cboCerStatus.getText().length()>0){
			epdCertificate.setCerStatus(CommFinal.getItemValue(TraffFinal.ARR_CER_STATUS, cboCerStatus.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请请选择资质项状态！");
			cboCerStatus.forceFocus();
			return null;
		}

		epdCertificate.setRemark(txtRemark.getText());
		
		return epdCertificate;
	}
	
	private void clearData(){
		cboCerType.setText("");
		txtCerName.setText("");
		cboCerStatus.setText("");
		txtRemark.setText("");
		cboCerType.forceFocus();
	}
	
}
