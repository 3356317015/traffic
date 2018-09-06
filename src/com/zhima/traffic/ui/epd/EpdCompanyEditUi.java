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
import com.zhima.traffic.action.epd.IEpdCompany;
import com.zhima.traffic.action.epd.impl.ImpEpdCompany;
import com.zhima.traffic.model.EpdCompany;
import com.zhima.util.DateUtils;
import com.zhima.util.PinyinUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdCompanyEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtCompanyCode;
	
	private Text txtCompanySpell;
	
	private Text txtCompanyName;
	
	private Text txtCompanyType;
	
	private Text txtIndustry;
	
	private Text txtAddress;
	
	private Text txtPostalCode;
	
	private Text txtContacts;
	
	private Text txtTelephone;
	
	private Text txtFaxNumber;
	
	private Text txtEmail;
	
	private Text txtRegNum;
	
	private Text txtTaxNum;
	
	private Text txtRemark;

	protected EpdCompanyEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("公司信息设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(500,370);
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
		groupMain.setText("公司信息");
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

		
		KLabel lbCompanyName = new KLabel(groupMain, SWT.RIGHT);
		lbCompanyName.setFont(StyleFinal.SYS_FONT);
		lbCompanyName.setText("公司名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCompanyName.setLayoutData(gridData);
		
		txtCompanyName = new Text(groupMain, SWT.BORDER);
		txtCompanyName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCompanyName.setLayoutData(gridData);

		KLabel lbCompanyCode = new KLabel(groupMain, SWT.RIGHT);
		lbCompanyCode.setFont(StyleFinal.SYS_FONT);
		lbCompanyCode.setText("公司代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCompanyCode.setLayoutData(gridData);
		
		txtCompanyCode = new Text(groupMain, SWT.BORDER);
		txtCompanyCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCompanyCode.setLayoutData(gridData);
		
		KLabel lbCompanySpell = new KLabel(groupMain, SWT.RIGHT);
		lbCompanySpell.setFont(StyleFinal.SYS_FONT);
		lbCompanySpell.setText("拼音代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCompanySpell.setLayoutData(gridData);
		
		txtCompanySpell = new Text(groupMain, SWT.BORDER);
		txtCompanySpell.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCompanySpell.setLayoutData(gridData);
		
		Label lbCompanyType = new Label(groupMain, SWT.RIGHT);
		lbCompanyType.setFont(StyleFinal.SYS_FONT);
		lbCompanyType.setText("单位性质:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCompanyType.setLayoutData(gridData);
		
		txtCompanyType = new Text(groupMain, SWT.BORDER);
		txtCompanyType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.widthHint = 150;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCompanyType.setLayoutData(gridData);

		Label lbIndustry = new Label(groupMain, SWT.RIGHT);
		lbIndustry.setFont(StyleFinal.SYS_FONT);
		lbIndustry.setText("行业类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbIndustry.setLayoutData(gridData);
		
		txtIndustry = new Text(groupMain, SWT.BORDER);
		txtIndustry.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIndustry.setLayoutData(gridData);
		
		Label lbRegNum = new Label(groupMain, SWT.RIGHT);
		lbRegNum.setFont(StyleFinal.SYS_FONT);
		lbRegNum.setText("工商号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRegNum.setLayoutData(gridData);
		
		txtRegNum = new Text(groupMain, SWT.BORDER);
		txtRegNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRegNum.setLayoutData(gridData);
		
		Label lbTaxNum = new Label(groupMain, SWT.RIGHT);
		lbTaxNum.setFont(StyleFinal.SYS_FONT);
		lbTaxNum.setText("税务号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbTaxNum.setLayoutData(gridData);
		
		txtTaxNum = new Text(groupMain, SWT.BORDER);
		txtTaxNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTaxNum.setLayoutData(gridData);
		
		Label lbContacts = new Label(groupMain, SWT.RIGHT);
		lbContacts.setFont(StyleFinal.SYS_FONT);
		lbContacts.setText("联系人:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbContacts.setLayoutData(gridData);
		
		txtContacts = new Text(groupMain, SWT.BORDER);
		txtContacts.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtContacts.setLayoutData(gridData);
		
		Label lbTelephone = new Label(groupMain, SWT.RIGHT);
		lbTelephone.setFont(StyleFinal.SYS_FONT);
		lbTelephone.setText("电话:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbTelephone.setLayoutData(gridData);
		
		txtTelephone = new Text(groupMain, SWT.BORDER);
		txtTelephone.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTelephone.setLayoutData(gridData);
		
		Label lbPostalCode = new Label(groupMain, SWT.RIGHT);
		lbPostalCode.setFont(StyleFinal.SYS_FONT);
		lbPostalCode.setText("邮政编码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbPostalCode.setLayoutData(gridData);
		
		txtPostalCode = new Text(groupMain, SWT.BORDER);
		txtPostalCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPostalCode.setLayoutData(gridData);
		
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
		lbAddress.setText("公司地址:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbAddress.setLayoutData(gridData);
		
		txtAddress = new Text(groupMain, SWT.BORDER);
		txtAddress.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtAddress.setLayoutData(gridData);

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
		txtCompanyName.forceFocus();
		txtCompanyName.selectAll();
		callMethod.bindEnterEvent(this, txtCompanyName, txtCompanyCode, "setSpell");
		callMethod.bindEnterEvent(this, txtCompanyCode, txtCompanySpell, "");
		callMethod.bindEnterEvent(this, txtCompanySpell, txtCompanyType, "");
		callMethod.bindEnterEvent(this, txtCompanyType, txtIndustry, "");
		callMethod.bindEnterEvent(this, txtIndustry, txtRegNum, "");
		callMethod.bindEnterEvent(this, txtRegNum, txtTaxNum, "");
		callMethod.bindEnterEvent(this, txtTaxNum, txtContacts, "");
		callMethod.bindEnterEvent(this, txtContacts, txtTelephone, "");
		callMethod.bindEnterEvent(this, txtTelephone, txtPostalCode, "");
		callMethod.bindEnterEvent(this, txtPostalCode, txtFaxNumber, "");
		callMethod.bindEnterEvent(this, txtFaxNumber, txtEmail, "");
		callMethod.bindEnterEvent(this, txtEmail, txtAddress, "");
		callMethod.bindEnterEvent(this, txtAddress, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdCompany epdCompany = (EpdCompany) gridView.getSelection();
			if (null != epdCompany && !"".equals(epdCompany.getCompanySeq())){
				txtCompanyName.setText(epdCompany.getCompanyName());
				txtCompanyCode.setText(epdCompany.getCompanyCode());
				txtCompanySpell.setText(epdCompany.getCompanySpell());
				txtCompanyType.setText(epdCompany.getCompanyType());
				txtIndustry.setText(epdCompany.getIndustry());
				txtRegNum.setText(epdCompany.getRegNum());
				txtTaxNum.setText(epdCompany.getTaxNum());
				txtContacts.setText(epdCompany.getContacts());
				txtTelephone.setText(epdCompany.getTelephone());
				txtPostalCode.setText(epdCompany.getPostalCode());
				txtFaxNumber.setText(epdCompany.getFaxNumber());
				txtAddress.setText(epdCompany.getAddress());
				txtEmail.setText(epdCompany.getEmail());
				txtRemark.setText(epdCompany.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdCompany company = validData();
				if (null != company){
					IEpdCompany iEpdCompany = new ImpEpdCompany();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdCompany newCompany = iEpdCompany.insert(company,CommFinal.initConfig());
						gridView.addRow(newCompany);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdCompany.update(company,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), company);
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
	
	private EpdCompany validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdCompany company = new EpdCompany();
		company.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			company = (EpdCompany) gridView.getSelection();
		}
		company.setUpdateTime(currTime);
		if (null == txtCompanyName.getText() || txtCompanyName.getText().length()<=0){
			MsgBox.warning(getParentShell(),"请输入公司名称！");
			txtCompanyName.forceFocus();
			return null;
		}
		
		if (null == txtCompanyCode.getText() || txtCompanyCode.getText().length()<=0){
			MsgBox.warning(getParentShell(),"请输入公司代码！");
			txtCompanyCode.forceFocus();
			return null;
		}
		
		if (null == txtCompanySpell.getText() || txtCompanySpell.getText().length()<=0){
			MsgBox.warning(getParentShell(),"请输入拼音代码！");
			txtCompanySpell.forceFocus();
			return null;
		}
		company.setCompanyName(txtCompanyName.getText());
		company.setCompanyCode(txtCompanyCode.getText());
		company.setCompanySpell(txtCompanySpell.getText());
		company.setCompanyType(txtCompanyType.getText());
		company.setIndustry(txtIndustry.getText());
		company.setRegNum(txtRegNum.getText());
		company.setTaxNum(txtTaxNum.getText());
		company.setContacts(txtContacts.getText());
		company.setTelephone(txtTelephone.getText());
		company.setPostalCode(txtPostalCode.getText());
		company.setFaxNumber(txtFaxNumber.getText());
		company.setAddress(txtAddress.getText());
		company.setEmail(txtEmail.getText());
		company.setRemark(txtRemark.getText());
		return company;
	}
	
	private void clearData(){
		txtCompanyName.setText("");
		txtCompanyCode.setText("");
		txtCompanySpell.setText("");
		txtCompanyType.setText("");
		txtIndustry.setText("");
		txtRegNum.setText("");
		txtTaxNum.setText("");
		txtContacts.setText("");
		txtTelephone.setText("");
		txtPostalCode.setText("");
		txtFaxNumber.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
		txtRemark.setText("");
	}
	
	public void setSpell(){
		PinyinUtil pinyinUtil = new PinyinUtil();  
		txtCompanySpell.setText(pinyinUtil.String2Alpha(txtCompanyName.getText().toLowerCase()));
	}
	
}
