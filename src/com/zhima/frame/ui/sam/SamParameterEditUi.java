package com.zhima.frame.ui.sam;

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
import com.zhima.frame.action.sam.ISamParameter;
import com.zhima.frame.action.sam.impl.ImpSamParameter;
import com.zhima.frame.model.SamParameter;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class SamParameterEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtParameterName;
	
	private Text txtParameterValue;
	
	private Text txtGroupName;
	
	private Text txtRemark;

	protected SamParameterEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("参数设置-"+operType);
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
		groupMain.setText("名称");
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

		KLabel lbParameterName = new KLabel(groupMain, SWT.RIGHT);
		lbParameterName.setFont(StyleFinal.SYS_FONT);
		lbParameterName.setText("描述:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbParameterName.setLayoutData(gridData);
		
		txtParameterName = new Text(groupMain, SWT.BORDER);
		txtParameterName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtParameterName.setLayoutData(gridData);
		
		KLabel lbParameterValue = new KLabel(groupMain, SWT.RIGHT);
		lbParameterValue.setFont(StyleFinal.SYS_FONT);
		lbParameterValue.setText("值:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbParameterValue.setLayoutData(gridData);
		
		txtParameterValue = new Text(groupMain, SWT.BORDER);
		txtParameterValue.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtParameterValue.setLayoutData(gridData);
		
		Label lbGroupName = new Label(groupMain, SWT.RIGHT);
		lbGroupName.setFont(StyleFinal.SYS_FONT);
		lbGroupName.setText("分组:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbGroupName.setLayoutData(gridData);
		
		txtGroupName = new Text(groupMain, SWT.BORDER);
		txtGroupName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtGroupName.setLayoutData(gridData);
		
		
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
		txtParameterName.forceFocus();
		txtParameterName.selectAll();
	
		callMethod.bindEnterEvent(this, txtParameterName, txtParameterValue, "");
		callMethod.bindEnterEvent(this, txtParameterValue, txtGroupName, "");
		callMethod.bindEnterEvent(this, txtGroupName, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			SamParameter samParameter = (SamParameter) gridView.getSelection();
			if (null != samParameter && samParameter.getParameterSeq().length()>0){
				txtParameterName.setText(samParameter.getParameterName());
				txtParameterValue.setText(samParameter.getParameterValue());
				txtGroupName.setText(samParameter.getGroupName());
				txtRemark.setText(samParameter.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				SamParameter parameter = validData();
				if (null != parameter){
					ISamParameter iSamParameter = new ImpSamParameter();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						SamParameter newParameter = iSamParameter.insert(parameter,CommFinal.initConfig());
						gridView.addRow(newParameter);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamParameter.update(parameter,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), parameter);
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
	
	private SamParameter validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamParameter samParameter = new SamParameter();
		samParameter.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			samParameter.setCreateTime(currTime);
		}else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			samParameter = (SamParameter) gridView.getSelection();
		}
		samParameter.setUpdateTime(currTime);
		if (null != txtParameterName.getText() && txtParameterName.getText().length()>0){
			samParameter.setParameterName(txtParameterName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入参数名称！");
			txtParameterName.forceFocus();
			return null;
		}
		if (null != txtParameterValue.getText() && txtParameterValue.getText().length()>0){
			samParameter.setParameterValue(txtParameterValue.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入参数值！");
			txtParameterValue.forceFocus();
			return null;
		}
		samParameter.setGroupName(txtGroupName.getText());
		samParameter.setRemark(txtRemark.getText());
		
		return samParameter;
	}
	
	private void clearData(){
		txtParameterName.setText("");
		txtParameterValue.setText("");
		txtGroupName.setText("");
		txtRemark.setText("");
	}
	
}
