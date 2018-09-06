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
import com.zhima.frame.action.sam.ISamService;
import com.zhima.frame.action.sam.impl.ImpSamService;
import com.zhima.frame.model.SamService;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.util.DateUtils;
import com.zhima.util.PinyinUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class SamServiceEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtServiceName;
	
	private Text txtServiceCode;
	
	private Text txtServiceSpell;
	
	private CCombo cboSaleType;
	
	private CCombo cboSaleRight;
	
	private Text txtTelephone;
	
	private Text txtFaxNumber;
	
	private Text txtEmail;
	
	private Text txtAddress;
	
	private CCombo cboServiceStatus;
	
	private Text txtRemark;

	protected SamServiceEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("销售点设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(525,340);
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
		
		Group groupMain = new Group(compMain,SWT.NONE);
		groupMain.setText("销售点信息");
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

		KLabel lbServiceName = new KLabel(groupMain, SWT.RIGHT);
		lbServiceName.setFont(StyleFinal.SYS_FONT);
		lbServiceName.setText("销售点名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbServiceName.setLayoutData(gridData);
		
		txtServiceName = new Text(groupMain, SWT.BORDER);
		txtServiceName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtServiceName.setLayoutData(gridData);

		KLabel lbServiceCode = new KLabel(groupMain, SWT.RIGHT);
		lbServiceCode.setFont(StyleFinal.SYS_FONT);
		lbServiceCode.setText("销售点代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbServiceCode.setLayoutData(gridData);
		
		txtServiceCode = new Text(groupMain, SWT.BORDER);
		txtServiceCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		gridData.widthHint = 150;
		txtServiceCode.setLayoutData(gridData);
		
		KLabel lbServiceSpell = new KLabel(groupMain, SWT.RIGHT);
		lbServiceSpell.setFont(StyleFinal.SYS_FONT);
		lbServiceSpell.setText("拼音代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbServiceSpell.setLayoutData(gridData);
		
		txtServiceSpell = new Text(groupMain, SWT.BORDER);
		txtServiceSpell.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtServiceSpell.setLayoutData(gridData);
		
		KLabel lbSaleType = new KLabel(groupMain, SWT.RIGHT);
		lbSaleType.setFont(StyleFinal.SYS_FONT);
		lbSaleType.setText("销售方式:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSaleType.setLayoutData(gridData);
		
		cboSaleType = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cboSaleType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.widthHint = 150;
		cboSaleType.setLayoutData(gridData);
		cboSaleType.setVisibleItemCount(15);
		
		KLabel lbSaleRight = new KLabel(groupMain, SWT.RIGHT);
		lbSaleRight.setFont(StyleFinal.SYS_FONT);
		lbSaleRight.setText("销售权限:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSaleRight.setLayoutData(gridData);
		
		cboSaleRight = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cboSaleRight.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.widthHint = 150;
		cboSaleRight.setLayoutData(gridData);
		cboSaleRight.setVisibleItemCount(15);
		
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
		
		KLabel lbServiceStatus = new KLabel(groupMain, SWT.RIGHT);
		lbServiceStatus.setFont(StyleFinal.SYS_FONT);
		lbServiceStatus.setText("销售点状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbServiceStatus.setLayoutData(gridData);
		
		cboServiceStatus = new CCombo(groupMain, SWT.BORDER);
		cboServiceStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboServiceStatus.setLayoutData(gridData);
		cboServiceStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_SERVICE_STATUS));

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
		txtServiceName.forceFocus();
		txtServiceName.selectAll();

		return compMain;
	}
	
	private void initData(){
		CallMethod callMethod = new CallMethod();
		callMethod.bindEnterEvent(this, txtServiceName, txtServiceCode, "setSpell");
		callMethod.bindEnterEvent(this, txtServiceCode, txtServiceSpell, "");
		callMethod.bindEnterEvent(this, txtServiceSpell, cboSaleType, "");
		callMethod.bindEnterEvent(this, cboSaleType, cboSaleRight, "");
		callMethod.bindEnterEvent(this, cboSaleRight, txtTelephone, "");
		callMethod.bindEnterEvent(this, txtTelephone, txtFaxNumber, "");
		callMethod.bindEnterEvent(this, txtFaxNumber, txtEmail, "");
		callMethod.bindEnterEvent(this, txtEmail, txtAddress, "");
		callMethod.bindEnterEvent(this, txtAddress, cboServiceStatus, "");
		callMethod.bindEnterEvent(this, cboServiceStatus, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		
		cboSaleType.setItems(CommFinal.getAllName(CommFinal.ARR_SALE_TYPE));
		cboSaleRight.setItems(CommFinal.getAllName(CommFinal.ARR_SALE_RIGHT));
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			SamService samService = (SamService) gridView.getSelection();
			if (null != samService && !"".equals(samService.getServiceSeq())){
				txtServiceName.setText(samService.getServiceName());
				txtServiceCode.setText(samService.getServiceCode());
				txtServiceSpell.setText(samService.getServiceSpell());
				cboSaleType.setText(CommFinal.getItemName(CommFinal.ARR_SALE_TYPE, samService.getSaleType()));
				cboSaleRight.setText(CommFinal.getItemName(CommFinal.ARR_SALE_RIGHT, samService.getSaleRight()));
				txtTelephone.setText(samService.getTelephone());
				txtFaxNumber.setText(samService.getFaxNumber());
				txtEmail.setText(samService.getEmail());
				txtAddress.setText(samService.getAddress());
				cboServiceStatus.setText(CommFinal.getItemName(TraffFinal.ARR_SERVICE_STATUS, samService.getStatus()));
				txtRemark.setText(samService.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				SamService service = validData();
				if (null != service){
					ISamService iSamService = new ImpSamService();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						SamService newService = iSamService.insert(service,CommFinal.initConfig());
						gridView.addRow(newService);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamService.update(service,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), service);
						close();
					}
				}
			} else if (0 == buttonId) {
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
			txtServiceName.forceFocus();
			txtServiceName.selectAll();
		}
		
	}
	
	private SamService validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamService samService = new SamService();
		samService.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			samService.setCreateTime(currTime);
		}else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			samService = (SamService) gridView.getSelection();
		}
		samService.setUpdateTime(currTime);
		if (null == txtServiceName.getText() || "".equals(txtServiceName.getText())){
			MsgBox.warning(getParentShell(),"请输入销售点名称！");
			txtServiceName.forceFocus();
			return null;
		}
		
		if (null == txtServiceCode.getText() || "".equals(txtServiceCode.getText())){
			MsgBox.warning(getParentShell(),"请输入销售点代码！");
			txtServiceCode.forceFocus();
			return null;
		}
		
		if (null == txtServiceSpell.getText() || "".equals(txtServiceSpell.getText())){
			MsgBox.warning(getParentShell(),"请输入拼音代码！");
			txtServiceSpell.forceFocus();
			return null;
		}
		
		if (null == cboSaleType.getText() || "".equals(cboSaleType.getText())){
			MsgBox.warning(getParentShell(),"请输入销售点销售方式！");
			cboSaleType.forceFocus();
			return null;
		}
		
		if (null == cboSaleRight.getText() || "".equals(cboSaleRight.getText())){
			MsgBox.warning(getParentShell(),"请输入销售点销售权限！");
			cboSaleRight.forceFocus();
			return null;
		}
		
		if (null == cboServiceStatus.getText() || "".equals(cboServiceStatus.getText())){
			MsgBox.warning(getParentShell(),"请输入销售点状态！");
			cboServiceStatus.forceFocus();
			return null;
		}
		
		samService.setServiceName(txtServiceName.getText());
		samService.setServiceCode(txtServiceCode.getText());
		samService.setServiceSpell(txtServiceSpell.getText());
		samService.setSaleType(CommFinal.getItemValue(CommFinal.ARR_SALE_TYPE, cboSaleType.getText()));
		samService.setSaleRight(CommFinal.getItemValue(CommFinal.ARR_SALE_RIGHT, cboSaleRight.getText()));
		samService.setTelephone(txtTelephone.getText());
		samService.setFaxNumber(txtFaxNumber.getText());
		samService.setEmail(txtEmail.getText());
		samService.setAddress(txtAddress.getText());
		samService.setRemark(txtRemark.getText());
		samService.setStatus(CommFinal.getItemValue(TraffFinal.ARR_SERVICE_STATUS, cboServiceStatus.getText()));
		return samService;
	}
	
	private void clearData(){
		txtServiceName.setText("");
		txtServiceCode.setText("");
		txtServiceSpell.setText("");
		cboSaleType.setText("");
		cboSaleRight.setText("");
		txtTelephone.setText("");
		txtFaxNumber.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		cboServiceStatus.setText("");
		txtRemark.setText("");
		txtServiceName.forceFocus();
	}
	
	public void setSpell(){
		PinyinUtil pinyinUtil = new PinyinUtil();  
		txtServiceSpell.setText(pinyinUtil.String2Alpha(txtServiceName.getText().toLowerCase()));
	}
}
