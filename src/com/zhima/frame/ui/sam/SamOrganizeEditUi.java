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
import com.zhima.frame.action.sam.ISamOrganize;
import com.zhima.frame.action.sam.impl.ImpSamOrganize;
import com.zhima.frame.model.SamOrganize;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.util.DateUtils;
import com.zhima.util.PinyinUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.Validate;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CCalendar;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class SamOrganizeEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtOrganizeName;
	
	private Text txtOrganizeCode;
	
	private Text txtOrganizeSpell;
	
	private Text txtTelephone;
	
	private Text txtFaxNumber;
	
	private Text txtOperateCode;
	
	private CCalendar txtOperateValid;
	
	private CCombo cboOrganizeLevel;
	
	private CCombo cboOrganizeType;
	
	private Text txtEmail;
	
	private Text txtAddress;
	
	private CCombo cboOrganizeStatus;
	
	private Text txtRemark;

	protected SamOrganizeEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("组织机构设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(525,375);
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
		groupMain.setText("客运站信息");
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

		KLabel lbOrganizeName = new KLabel(groupMain, SWT.RIGHT);
		lbOrganizeName.setFont(StyleFinal.SYS_FONT);
		lbOrganizeName.setText("客运站名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeName.setLayoutData(gridData);
		
		txtOrganizeName = new Text(groupMain, SWT.BORDER);
		txtOrganizeName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtOrganizeName.setLayoutData(gridData);

		KLabel lbOrganizeCode = new KLabel(groupMain, SWT.RIGHT);
		lbOrganizeCode.setFont(StyleFinal.SYS_FONT);
		lbOrganizeCode.setText("客运站代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeCode.setLayoutData(gridData);
		
		txtOrganizeCode = new Text(groupMain, SWT.BORDER);
		txtOrganizeCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		gridData.widthHint = 150;
		txtOrganizeCode.setLayoutData(gridData);
		
		KLabel lbOrganizeSpell = new KLabel(groupMain, SWT.RIGHT);
		lbOrganizeSpell.setFont(StyleFinal.SYS_FONT);
		lbOrganizeSpell.setText("拼音代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeSpell.setLayoutData(gridData);
		
		txtOrganizeSpell = new Text(groupMain, SWT.BORDER);
		txtOrganizeSpell.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtOrganizeSpell.setLayoutData(gridData);
		
		Label lbTelephone = new Label(groupMain, SWT.RIGHT);
		lbTelephone.setFont(StyleFinal.SYS_FONT);
		lbTelephone.setText("联系电话:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbTelephone.setLayoutData(gridData);
		
		txtTelephone = new Text(groupMain, SWT.BORDER);
		txtTelephone.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTelephone.setLayoutData(gridData);
		
		Label lbFaxNumber = new Label(groupMain, SWT.RIGHT);
		lbFaxNumber.setFont(StyleFinal.SYS_FONT);
		lbFaxNumber.setText("传真:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbFaxNumber.setLayoutData(gridData);
		
		txtFaxNumber = new Text(groupMain, SWT.BORDER);
		txtFaxNumber.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFaxNumber.setLayoutData(gridData);
		
		KLabel lbOperateCode = new KLabel(groupMain, SWT.RIGHT);
		lbOperateCode.setFont(StyleFinal.SYS_FONT);
		lbOperateCode.setText("经营许可证:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbOperateCode.setLayoutData(gridData);
		
		txtOperateCode = new Text(groupMain, SWT.BORDER);
		txtOperateCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		gridData.widthHint = 125;
		txtOperateCode.setLayoutData(gridData);
		
		KLabel lbOperateValid = new KLabel(groupMain, SWT.RIGHT);
		lbOperateValid.setFont(StyleFinal.SYS_FONT);
		lbOperateValid.setText("有效日期:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOperateValid.setLayoutData(gridData);
		
		txtOperateValid = new CCalendar(groupMain, SWT.BORDER);
		txtOperateValid.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtOperateValid.setLayoutData(gridData);
		
		KLabel lbOrganizeLevel = new KLabel(groupMain, SWT.RIGHT);
		lbOrganizeLevel.setFont(StyleFinal.SYS_FONT);
		lbOrganizeLevel.setText("客运站等级:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeLevel.setLayoutData(gridData);
		
		cboOrganizeLevel = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cboOrganizeLevel.setVisibleItemCount(10);
		cboOrganizeLevel.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboOrganizeLevel.setLayoutData(gridData);
		cboOrganizeLevel.setItems(CommFinal.getAllName(TraffFinal.ARR_ORGANIZE_LEVEL));
		
		KLabel lbOrganizeType = new KLabel(groupMain, SWT.RIGHT);
		lbOrganizeType.setFont(StyleFinal.SYS_FONT);
		lbOrganizeType.setText("客运站类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeType.setLayoutData(gridData);
		
		cboOrganizeType = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cboOrganizeType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboOrganizeType.setLayoutData(gridData);
		cboOrganizeType.setItems(CommFinal.getAllName(TraffFinal.ARR_ORGANIZE_TYPE));
		
		Label lbEmail = new Label(groupMain, SWT.RIGHT);
		lbEmail.setFont(StyleFinal.SYS_FONT);
		lbEmail.setText("邮箱地址:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbEmail.setLayoutData(gridData);
		
		txtEmail = new Text(groupMain, SWT.BORDER);
		txtEmail.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtEmail.setLayoutData(gridData);
		
		Label lbAddress = new Label(groupMain, SWT.RIGHT);
		lbAddress.setFont(StyleFinal.SYS_FONT);
		lbAddress.setText("联系地址:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbAddress.setLayoutData(gridData);
		
		txtAddress = new Text(groupMain, SWT.BORDER);
		txtAddress.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtAddress.setLayoutData(gridData);
		
		KLabel lbOrganizeStatus = new KLabel(groupMain, SWT.RIGHT);
		lbOrganizeStatus.setFont(StyleFinal.SYS_FONT);
		lbOrganizeStatus.setText("客运站状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganizeStatus.setLayoutData(gridData);
		
		cboOrganizeStatus = new CCombo(groupMain, SWT.BORDER);
		cboOrganizeStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboOrganizeStatus.setLayoutData(gridData);
		cboOrganizeStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_ORGANIZE_STATUS));

		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		initData();
		txtOrganizeName.forceFocus();
		txtOrganizeName.selectAll();

		callMethod.bindEnterEvent(this, txtOrganizeName, txtOrganizeCode, "setSpell");
		callMethod.bindEnterEvent(this, txtOrganizeCode, txtOrganizeSpell, "");
		callMethod.bindEnterEvent(this, txtOrganizeSpell, txtTelephone, "");
		callMethod.bindEnterEvent(this, txtTelephone, txtFaxNumber, "");
		callMethod.bindEnterEvent(this, txtFaxNumber, txtOperateCode, "");
		callMethod.bindEnterEvent(this, txtOperateCode, txtOperateValid.txtDate, "");
		callMethod.bindEnterEvent(this, txtOperateValid.txtDate, cboOrganizeLevel, "");
		callMethod.bindEnterEvent(this, cboOrganizeLevel, cboOrganizeType, "");
		callMethod.bindEnterEvent(this, cboOrganizeType,txtEmail, "");
		callMethod.bindEnterEvent(this, txtEmail, txtAddress, "");
		callMethod.bindEnterEvent(this, txtAddress, cboOrganizeStatus, "");
		callMethod.bindEnterEvent(this, cboOrganizeStatus, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			SamOrganize samOrganize = (SamOrganize) gridView.getSelection();
			if (null != samOrganize && !"".equals(samOrganize.getOrganizeSeq())){
				txtOrganizeName.setText(samOrganize.getOrganizeName());
				txtOrganizeCode.setText(samOrganize.getOrganizeCode());
				txtOrganizeSpell.setText(samOrganize.getOrganizeSpell());
				txtTelephone.setText(samOrganize.getTelephone());
				txtFaxNumber.setText(samOrganize.getFaxNumber());
				txtOperateCode.setText(samOrganize.getOperateCode());
				txtOperateValid.setText(samOrganize.getOperateValid());
				cboOrganizeLevel.setText(CommFinal.getItemName(TraffFinal.ARR_ORGANIZE_LEVEL, samOrganize.getOrganizeLevel()));
				cboOrganizeType.setText(CommFinal.getItemName(TraffFinal.ARR_ORGANIZE_TYPE, samOrganize.getOrganizeType()));
				txtEmail.setText(samOrganize.getEmail());
				txtAddress.setText(samOrganize.getAddress());
				cboOrganizeStatus.setText(CommFinal.getItemName(TraffFinal.ARR_ORGANIZE_STATUS, samOrganize.getStatus()));
				txtRemark.setText(samOrganize.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				SamOrganize organize = validData();
				if (null != organize){
					ISamOrganize iSamOrganize = new ImpSamOrganize();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						SamOrganize newOrganize = iSamOrganize.insert(organize,CommFinal.initConfig());
						gridView.addRow(newOrganize);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamOrganize.update(organize,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), organize);
						close();
					}
				}
			} else if (0 == buttonId) {
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
			txtOrganizeName.forceFocus();
			txtOrganizeName.selectAll();
		}
		
	}
	
	private SamOrganize validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamOrganize samOrganize = new SamOrganize();
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			samOrganize.setCreateTime(currTime);
		}else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			samOrganize = (SamOrganize) gridView.getSelection();
		}
		samOrganize.setUpdateTime(currTime);
		if (null == txtOrganizeName.getText() || "".equals(txtOrganizeName.getText())){
			MsgBox.warning(getParentShell(),"请输入机构名称！");
			txtOrganizeName.forceFocus();
			return null;
		}
		
		if (null == txtOrganizeCode.getText() || "".equals(txtOrganizeCode.getText())){
			MsgBox.warning(getParentShell(),"请输入机构代码！");
			txtOrganizeCode.forceFocus();
			return null;
		}
		
		if (null == txtOrganizeSpell.getText() || "".equals(txtOrganizeSpell.getText())){
			MsgBox.warning(getParentShell(),"请输入拼音代码！");
			txtOrganizeSpell.forceFocus();
			return null;
		}
		
		
		if (null == txtOperateCode.getText() || "".equals(txtOperateCode.getText())){
			MsgBox.warning(getParentShell(),"请输入经营许可证！");
			txtOperateCode.forceFocus();
			return null;
		}
		
		if (Validate.isDate(txtOperateValid.getText())==false){
			MsgBox.warning(getParentShell(),"经营许可证有效日期不合法！");
			txtOperateValid.forceFocus();
			return null;
		}
		
		if (null == cboOrganizeLevel.getText() || "".equals(cboOrganizeLevel.getText())){
			MsgBox.warning(getParentShell(),"请输入客运站等级！");
			cboOrganizeLevel.forceFocus();
			return null;
		}
		
		if (null == cboOrganizeType.getText() || "".equals(cboOrganizeType.getText())){
			MsgBox.warning(getParentShell(),"请输入客运站类型！");
			cboOrganizeType.forceFocus();
			return null;
		}
		
		if (null == cboOrganizeStatus.getText() || "".equals(cboOrganizeStatus.getText())){
			MsgBox.warning(getParentShell(),"请输入客运站状态！");
			cboOrganizeStatus.forceFocus();
			return null;
		}
		
		
		samOrganize.setOrganizeName(txtOrganizeName.getText());
		samOrganize.setOrganizeCode(txtOrganizeCode.getText());
		samOrganize.setOrganizeSpell(txtOrganizeSpell.getText());
		samOrganize.setTelephone(txtTelephone.getText());
		samOrganize.setFaxNumber(txtFaxNumber.getText());
		samOrganize.setOperateCode(txtOperateCode.getText());
		samOrganize.setOperateValid(txtOperateValid.getText());
		samOrganize.setOrganizeLevel(CommFinal.getItemValue(TraffFinal.ARR_ORGANIZE_LEVEL, cboOrganizeLevel.getText()));
		samOrganize.setOrganizeType(CommFinal.getItemValue(TraffFinal.ARR_ORGANIZE_TYPE, cboOrganizeType.getText()));
		samOrganize.setEmail(txtEmail.getText());
		samOrganize.setAddress(txtAddress.getText());
		samOrganize.setRemark(txtRemark.getText());
		samOrganize.setStatus(CommFinal.getItemValue(TraffFinal.ARR_ORGANIZE_STATUS, cboOrganizeStatus.getText()));
		return samOrganize;
	}
	
	private void clearData(){
		txtOrganizeName.setText("");
		txtOrganizeCode.setText("");
		txtOrganizeSpell.setText("");
		txtTelephone.setText("");
		txtFaxNumber.setText("");
		txtOperateCode.setText("");
		cboOrganizeLevel.setText("");
		cboOrganizeType.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		cboOrganizeStatus.setText("");
		txtRemark.setText("");
		txtOrganizeName.forceFocus();
	}
	
	public void setSpell(){
		PinyinUtil pinyinUtil = new PinyinUtil();  
		txtOrganizeSpell.setText(pinyinUtil.String2Alpha(txtOrganizeName.getText().toLowerCase()));
	}
}
