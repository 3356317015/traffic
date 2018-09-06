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
import com.zhima.traffic.action.epd.IEpdContract;
import com.zhima.traffic.action.epd.impl.ImpEpdContract;
import com.zhima.traffic.model.EpdContract;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdContractEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtContractCode;
	private Text txtContractName;
	private Text txtRemark;

	protected EpdContractEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("运营合同设置-"+operType);
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
		groupMain.setText("合同信息");
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
		
		KLabel lbContractCode = new KLabel(groupMain, SWT.RIGHT);
		lbContractCode.setFont(StyleFinal.SYS_FONT);
		lbContractCode.setText("合同代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbContractCode.setLayoutData(gridData);
		
		txtContractCode = new Text(groupMain, SWT.BORDER);
		txtContractCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtContractCode.setLayoutData(gridData);
		
		KLabel lbContractName = new KLabel(groupMain, SWT.RIGHT);
		lbContractName.setFont(StyleFinal.SYS_FONT);
		lbContractName.setText("合同名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbContractName.setLayoutData(gridData);
		
		txtContractName = new Text(groupMain, SWT.BORDER);
		txtContractName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtContractName.setLayoutData(gridData);
		
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
		txtContractCode.forceFocus();
		txtContractCode.selectAll();

		callMethod.bindEnterEvent(this, txtContractCode, txtContractName, "");
		callMethod.bindEnterEvent(this, txtContractName, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdContract epdContract = (EpdContract) gridView.getSelection();
			if (null != epdContract && !"".equals(epdContract.getContractSeq())){
				txtContractCode.setText(epdContract.getContractCode());
				txtContractName.setText(epdContract.getContractName());
				txtRemark.setText(epdContract.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdContract contract = validData();
				if (null != contract){
					IEpdContract iEpdContract = new ImpEpdContract();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdContract newContract = iEpdContract.insert(contract,CommFinal.initConfig());
						gridView.addRow(newContract);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdContract.update(contract,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), contract);
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
	
	private EpdContract validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdContract epdContract = new EpdContract();
		epdContract.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdContract = (EpdContract) gridView.getSelection();
		}
		epdContract.setUpdateTime(currTime);
		if (null != txtContractCode.getText() && txtContractCode.getText().length()>0){
			epdContract.setContractCode(txtContractCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入合同代码！");
			txtContractCode.forceFocus();
			return null;
		}
		
		if (null != txtContractName.getText() && txtContractName.getText().length()>0){
			epdContract.setContractName(txtContractName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入合同名称！");
			txtContractName.forceFocus();
			return null;
		}
		epdContract.setRemark(txtRemark.getText());
		
		return epdContract;
	}
	
	private void clearData(){
		txtContractCode.setText("");
		txtContractName.setText("");
		txtRemark.setText("");
		txtContractCode.forceFocus();
	}
	
}
