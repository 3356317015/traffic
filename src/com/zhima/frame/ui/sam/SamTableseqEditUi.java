package com.zhima.frame.ui.sam;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamTableseq;
import com.zhima.frame.action.sam.impl.ImpSamTableseq;
import com.zhima.frame.model.SamTableseq;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridView;

public class SamTableseqEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtTableName;
	
	private Text txtSeqLen;
	
	private Text txtSeqValue;
	
	private Button chkUnit;
	
	private Button chkUser;
	
	private Button chkDate;
	
	private Text txtCustom;
	
	private Text txtRemark;

	protected SamTableseqEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("数据库表主键设置-"+operType);
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
		groupMain.setText("主键信息");
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

		KLabel lbTableName = new KLabel(groupMain, SWT.RIGHT);
		lbTableName.setFont(StyleFinal.SYS_FONT);
		lbTableName.setText("表名:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTableName.setLayoutData(gridData);
		
		txtTableName = new Text(groupMain, SWT.BORDER);
		txtTableName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTableName.setLayoutData(gridData);

		KLabel lbSeqLen = new KLabel(groupMain, SWT.RIGHT);
		lbSeqLen.setFont(StyleFinal.SYS_FONT);
		lbSeqLen.setText("位数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSeqLen.setLayoutData(gridData);
		
		txtSeqLen = new Text(groupMain, SWT.BORDER);
		txtSeqLen.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.widthHint = 60;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSeqLen.setLayoutData(gridData);
		txtSeqLen.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbSeqValue = new KLabel(groupMain, SWT.RIGHT);
		lbSeqValue.setFont(StyleFinal.SYS_FONT);
		lbSeqValue.setText("当前值:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbSeqValue.setLayoutData(gridData);
		
		txtSeqValue = new Text(groupMain, SWT.BORDER);
		txtSeqValue.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSeqValue.setLayoutData(gridData);
		txtSeqValue.addVerifyListener(new TextVerifyListener(1));
		
		Label lbSeqRules = new Label(groupMain, SWT.RIGHT);
		lbSeqRules.setFont(StyleFinal.SYS_FONT);
		lbSeqRules.setText("格式:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.verticalSpan=2;
		lbSeqRules.setLayoutData(gridData);
		
		Composite composite = new Composite(groupMain, SWT.BORDER);
		composite.setLayout(new FormLayout());
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = 50;
		gridData.horizontalSpan=3;
		gridData.verticalSpan =2;
		composite.setLayoutData(gridData);
		
		
		chkUnit = new Button(composite, SWT.CHECK);
		chkUnit.setFont(StyleFinal.SYS_FONT);
		chkUnit.setText("单位代码");
		FormData formData = new FormData();
		formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(0,10);
		chkUnit.setLayoutData(formData);
		
		chkUser = new Button(composite, SWT.CHECK);
		chkUser.setFont(StyleFinal.SYS_FONT);
		chkUser.setText("用户代码");
		chkUser.setEnabled(false);
		formData = new FormData();
		formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(60);
		chkUser.setLayoutData(formData);
		
		chkDate = new Button(composite, SWT.CHECK);
		chkDate.setFont(StyleFinal.SYS_FONT);
		chkDate.setText("自定义:");
		formData = new FormData();
		formData.top = new FormAttachment(chkUnit,5);
		formData.left = new FormAttachment(0,10);
		chkDate.setLayoutData(formData);
		chkDate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (chkDate.getSelection()==true){
					txtCustom.setEditable(true);
				}else{
					txtCustom.setText("");
					txtCustom.setEditable(false);
				}
			}
		});
		
		txtCustom = new Text(composite, SWT.BORDER);
		txtCustom.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(chkUnit,4);
		formData.left = new FormAttachment(chkDate,5);
		formData.right = new FormAttachment(100,-5);
		txtCustom.setLayoutData(formData);
		txtCustom.setEditable(false);
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		initData();
		txtTableName.forceFocus();
		txtTableName.selectAll();
		callMethod.bindEnterEvent(this, txtTableName, txtSeqLen, "");
		callMethod.bindEnterEvent(this, txtSeqLen, txtSeqValue, "");
		callMethod.bindEnterEvent(this, txtSeqValue, chkUnit, "");
		callMethod.bindEnterEvent(this, chkUnit, txtRemark, "");
		callMethod.bindEnterEvent(this, chkUser, txtRemark, "");
		callMethod.bindEnterEvent(this, chkDate, txtRemark, "");
		callMethod.bindEnterEvent(this, txtCustom, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			SamTableseq samTableseq = (SamTableseq) gridView.getSelection();
			if (null != samTableseq && !"".equals(samTableseq.getTableName())){
				txtTableName.setText(samTableseq.getTableName());
				txtSeqLen.setText(samTableseq.getSeqLen().toString());
				txtSeqValue.setText(samTableseq.getSeqValue());
				String rules[]= samTableseq.getSeqRules().split(";");
				if (null != rules && rules.length>0){
					for (int i = 0; i < rules.length; i++) {
						if ("unit".equals(rules[i])){
							chkUnit.setSelection(true);
						}else if ("user".equals(rules[i])){
							chkUser.setSelection(true);
						}else{
							chkDate.setSelection(true);
							txtCustom.setText(rules[i]);
							txtCustom.setEditable(true);
						}
					}
				}
				txtRemark.setText(samTableseq.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				SamTableseq tableseq = validData();
				if (null != tableseq){
					ISamTableseq iSamTableseq = new ImpSamTableseq();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						SamTableseq newTableseq = iSamTableseq.insert(tableseq,CommFinal.initConfig());
						gridView.addRow(newTableseq);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamTableseq.update(tableseq,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), tableseq);
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
	
	private SamTableseq validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamTableseq tableseq = new SamTableseq();
		tableseq.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			tableseq.setCreateTime(currTime);
		}else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			tableseq = (SamTableseq) gridView.getSelection();
		}
		tableseq.setUpdateTime(currTime);
		if (null == txtTableName.getText() || "".equals(txtTableName.getText())){
			MsgBox.warning(getParentShell(),"请输入表名！");
			txtTableName.forceFocus();
			return null;
		}
		
		if (null == txtSeqLen.getText() || "".equals(txtSeqLen.getText())){
			MsgBox.warning(getParentShell(),"请输入表主键位数！");
			txtSeqLen.forceFocus();
			return null;
		}
		
		if (null == txtSeqValue.getText() || "".equals(txtSeqValue.getText())){
			MsgBox.warning(getParentShell(),"请输入表主键当前值！");
			txtSeqValue.forceFocus();
			return null;
		}
		ISamTableseq iSamTableseq = new ImpSamTableseq();
		
		try {
			SamTableseq samTableseq = iSamTableseq.queryByPk(CommFinal.organize.getOrganizeSeq(), txtTableName.getText());
			if (null != samTableseq){
				if (!"".equals(txtSeqValue.getText())){
					if (Integer.valueOf(txtSeqValue.getText())<Integer.valueOf(samTableseq.getSeqValue())){
						MsgBox.warning(getParentShell(),"主键当前值不能往回调整！");
						txtSeqValue.forceFocus();
						return null;
					}
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}

		tableseq.setTableName(txtTableName.getText());
		String rules = "";
		if (chkUnit.getSelection() == true){
			if ("".equals(rules)){
				rules = "unit";
			}else{
				rules += ";unit";
			}
		}
		if (chkUser.getSelection() == true){
			if ("".equals(rules)){
				rules = "user";
			}else{
				rules += ";user";
			}
		}
		if (null != txtCustom.getText() && !"".equals(txtCustom.getText())){
			if ("".equals(rules)){
				rules = txtCustom.getText();
			}else{
				rules += ";" + txtCustom.getText();
			}
		}
		tableseq.setSeqRules(rules);
		tableseq.setSeqLen(Integer.valueOf(txtSeqLen.getText()));
		tableseq.setSeqValue(txtSeqValue.getText());
		tableseq.setRemark(txtRemark.getText());
		return tableseq;
	}
	
	private void clearData(){
		txtTableName.setText("");
		txtSeqLen.setText("");
		txtSeqValue.setText("");
		chkUnit.setSelection(false);
		chkUser.setSelection(false);
		chkDate.setSelection(false);
		txtCustom.setText("");
		txtCustom.setEditable(false);
		txtRemark.setText("");
	}
	
}
