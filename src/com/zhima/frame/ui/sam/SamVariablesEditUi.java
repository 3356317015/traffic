package com.zhima.frame.ui.sam;

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
import com.zhima.frame.action.sam.ISamVariables;
import com.zhima.frame.action.sam.impl.ImpSamVariables;
import com.zhima.frame.model.SamVariables;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class SamVariablesEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private CCombo cboVariableType;
	
	private Text txtVariableCode;
	
	private Text txtVariableName;
	
	private CCombo cboStatus;
	
	private Text txtRemark;

	protected SamVariablesEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("变量设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,260);
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
		groupMain.setText("变量信息");
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

		KLabel lbVariableType = new KLabel(groupMain, SWT.RIGHT);
		lbVariableType.setFont(StyleFinal.SYS_FONT);
		lbVariableType.setText("变量类型:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbVariableType.setLayoutData(gridData);
		
		cboVariableType = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cboVariableType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboVariableType.setLayoutData(gridData);
		cboVariableType.setItems(CommFinal.getAllName(CommFinal.ARR_VARIABLE_TYPE));


		KLabel lbVariableCode = new KLabel(groupMain, SWT.RIGHT);
		lbVariableCode.setFont(StyleFinal.SYS_FONT);
		lbVariableCode.setText("变量代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbVariableCode.setLayoutData(gridData);
		
		txtVariableCode = new Text(groupMain, SWT.BORDER);
		txtVariableCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtVariableCode.setLayoutData(gridData);
		
		Label lbVariableName = new Label(groupMain, SWT.RIGHT);
		lbVariableName.setFont(StyleFinal.SYS_FONT);
		lbVariableName.setText("变量名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbVariableName.setLayoutData(gridData);
		
		txtVariableName = new Text(groupMain, SWT.BORDER);
		txtVariableName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtVariableName.setLayoutData(gridData);
		
		KLabel lbStatus = new KLabel(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("变量状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStatus.setLayoutData(gridData);
		
		cboStatus = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cboStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboStatus.setLayoutData(gridData);
		cboStatus.setItems(CommFinal.getAllName(CommFinal.ARR_VARIABLE_STATUS));
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		initData();
		cboVariableType.forceFocus();
		
		callMethod.bindEnterEvent(this, cboVariableType, txtVariableCode, "");
		callMethod.bindEnterEvent(this, txtVariableCode, txtVariableName, "");
		callMethod.bindEnterEvent(this, txtVariableName, cboStatus, "");
		callMethod.bindEnterEvent(this, cboStatus, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			SamVariables samVariables = (SamVariables) gridView.getSelection();
			if (null != samVariables && !"".equals(samVariables.getVariablesSeq())){
				cboVariableType.setText(CommFinal.getItemName(CommFinal.ARR_VARIABLE_TYPE,samVariables.getVariableType()));
				txtVariableCode.setText(samVariables.getVariableCode());
				txtVariableName.setText(samVariables.getVariableName());
				cboStatus.setText(CommFinal.getItemName(CommFinal.ARR_VARIABLE_STATUS,samVariables.getStatus()));
				txtRemark.setText(samVariables.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				SamVariables samVariables = validData();
				if (null != samVariables){
					ISamVariables iSamVariables = new ImpSamVariables();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						SamVariables newSamVariables = iSamVariables.insert(samVariables,CommFinal.initConfig());
						gridView.addRow(newSamVariables);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamVariables.update(samVariables,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), samVariables);
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
	
	private SamVariables validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamVariables samVariables = new SamVariables();
		samVariables.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			samVariables.setCreateTime(currTime);
		}else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			samVariables = (SamVariables) gridView.getSelection();
		}
		samVariables.setUpdateTime(currTime);
		if (null != cboVariableType.getText() && cboVariableType.getText().length()>0){
			samVariables.setVariableType(CommFinal.getItemValue(CommFinal.ARR_VARIABLE_TYPE,cboVariableType.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入变量类型！");
			cboVariableType.forceFocus();
			return null;
		}
		if (null != txtVariableCode.getText() && txtVariableCode.getText().length()>0){
			samVariables.setVariableCode(txtVariableCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入变量代码！");
			txtVariableCode.forceFocus();
			return null;
		}
		if (null != cboStatus.getText() && cboStatus.getText().length()>0){
			samVariables.setStatus(CommFinal.getItemValue(CommFinal.ARR_VARIABLE_STATUS,cboStatus.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择变量状态！");
			cboStatus.forceFocus();
			return null;
		}
		samVariables.setVariableName(txtVariableName.getText());
		samVariables.setRemark(txtRemark.getText());
		
		return samVariables;
	}
	
	private void clearData(){
		cboVariableType.setText("");
		txtVariableCode.setText("");
		txtVariableName.setText("");
		cboStatus.setText("");
		txtRemark.setText("");
		cboVariableType.forceFocus();
	}
	
}
