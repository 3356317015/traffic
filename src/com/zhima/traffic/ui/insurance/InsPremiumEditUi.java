package com.zhima.traffic.ui.insurance;

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
import com.zhima.traffic.action.insurance.IInsPremium;
import com.zhima.traffic.action.insurance.impl.ImpInsPremium;
import com.zhima.traffic.drop.DropInsurance;
import com.zhima.traffic.model.InsCompany;
import com.zhima.traffic.model.InsPremium;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridView;

public class InsPremiumEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private DropInsurance dropInsurance;
	
	private Text txtPremium;
	
	private Text txtStartPrice;
	
	private Text txtLimitPrice;
	
	private Text txtAmountOne;
	
	private Text txtAmountTwo;
	
	private Text txtRemark;

	protected InsPremiumEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("保险费设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(415,255);
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
		groupMain.setText("保险费信息");
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
		
		KLabel lbInsurance = new KLabel(groupMain, SWT.RIGHT);
		lbInsurance.setFont(StyleFinal.SYS_FONT);
		lbInsurance.setText("保险名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbInsurance.setLayoutData(gridData);
		
		dropInsurance = new DropInsurance(groupMain, SWT.BORDER);
		dropInsurance.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropInsurance.setLayoutData(gridData);

		KLabel lbPremium = new KLabel(groupMain, SWT.RIGHT);
		lbPremium.setFont(StyleFinal.SYS_FONT);
		lbPremium.setText("保险费:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPremium.setLayoutData(gridData);
		
		txtPremium = new Text(groupMain, SWT.BORDER);
		txtPremium.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		gridData.widthHint=95;
		txtPremium.setLayoutData(gridData);
		txtPremium.addVerifyListener(new TextVerifyListener(1));
		
		Label label = new Label(groupMain, SWT.RIGHT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=2;
		label.setLayoutData(gridData);
		
		KLabel lbStratPrice = new KLabel(groupMain, SWT.RIGHT);
		lbStratPrice.setFont(StyleFinal.SYS_FONT);
		lbStratPrice.setText("票价下限:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStratPrice.setLayoutData(gridData);
		
		txtStartPrice = new Text(groupMain, SWT.BORDER);
		txtStartPrice.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStartPrice.setLayoutData(gridData);
		txtStartPrice.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbLimitPrice = new KLabel(groupMain, SWT.RIGHT);
		lbLimitPrice.setFont(StyleFinal.SYS_FONT);
		lbLimitPrice.setText("票价下限:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLimitPrice.setLayoutData(gridData);
		
		txtLimitPrice = new Text(groupMain, SWT.BORDER);
		txtLimitPrice.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLimitPrice.setLayoutData(gridData);
		txtLimitPrice.addVerifyListener(new TextVerifyListener(1));

		KLabel lbAmountOne = new KLabel(groupMain, SWT.RIGHT);
		lbAmountOne.setFont(StyleFinal.SYS_FONT);
		lbAmountOne.setText("意外伤害险:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbAmountOne.setLayoutData(gridData);
		
		txtAmountOne = new Text(groupMain, SWT.BORDER);
		txtAmountOne.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtAmountOne.setLayoutData(gridData);
		txtAmountOne.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbAmountTwo = new KLabel(groupMain, SWT.RIGHT);
		lbAmountTwo.setFont(StyleFinal.SYS_FONT);
		lbAmountTwo.setText("意外医疗险:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbAmountTwo.setLayoutData(gridData);
		
		txtAmountTwo = new Text(groupMain, SWT.BORDER);
		txtAmountTwo.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtAmountTwo.setLayoutData(gridData);
		txtAmountTwo.addVerifyListener(new TextVerifyListener(1));

		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		initData();
		dropInsurance.droptext.txtShow.forceFocus();
		dropInsurance.droptext.txtShow.selectAll();
		callMethod.bindEnterEvent(this, dropInsurance.droptext.txtShow, txtPremium, "");
		callMethod.bindEnterEvent(this, txtPremium, txtStartPrice, "");
		callMethod.bindEnterEvent(this, txtStartPrice, txtLimitPrice, "");
		callMethod.bindEnterEvent(this, txtLimitPrice, txtAmountOne, "");
		callMethod.bindEnterEvent(this, txtAmountOne, txtAmountTwo, "");
		callMethod.bindEnterEvent(this, txtAmountTwo, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			InsPremium insPremium = (InsPremium) gridView.getSelection();
			if (null != insPremium && !"".equals(insPremium.getPremiumSeq())){
				dropInsurance.droptext.setValue(insPremium.getCompanySeq());
				txtPremium.setText(String.valueOf(insPremium.getPremium()));
				txtStartPrice.setText(String.valueOf(insPremium.getStartPrice()));
				txtLimitPrice.setText(String.valueOf(insPremium.getLimitPrice()));
				txtAmountOne.setText(String.valueOf(insPremium.getAmountOne()));
				txtAmountTwo.setText(String.valueOf(insPremium.getAmountTwo()));
				txtRemark.setText(insPremium.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				InsPremium premium = validData();
				if (null != premium){
					IInsPremium iInsPremium = new ImpInsPremium();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						InsPremium newPremium = iInsPremium.insert(premium,CommFinal.initConfig());
						gridView.addRow(newPremium);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iInsPremium.update(premium,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), premium);
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
	
	private InsPremium validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		InsPremium premium = new InsPremium();
		premium.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			premium = (InsPremium) gridView.getSelection();
		}
		premium.setUpdateTime(currTime);
		if (null == dropInsurance.droptext.getValue() || dropInsurance.droptext.getValue().length()<=0){
			MsgBox.warning(getParentShell(),"请输入或选择保险名称！");
			dropInsurance.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null == txtPremium.getText() || txtPremium.getText().length()<=0){
			MsgBox.warning(getParentShell(),"请输入保险费！");
			txtPremium.forceFocus();
			return null;
		}
		
		if (null == txtStartPrice.getText() || txtStartPrice.getText().length()<=0){
			MsgBox.warning(getParentShell(),"请输入票价下限！");
			txtStartPrice.forceFocus();
			return null;
		}
		
		if (null == txtLimitPrice.getText() || txtLimitPrice.getText().length()<=0){
			MsgBox.warning(getParentShell(),"请输入票价上限！");
			txtLimitPrice.forceFocus();
			return null;
		}
		
		if (null == txtAmountOne.getText() || txtAmountOne.getText().length()<=0){
			MsgBox.warning(getParentShell(),"请输入意外伤害险！");
			txtAmountOne.forceFocus();
			return null;
		}
		
		if (null == txtAmountTwo.getText() || txtAmountTwo.getText().length()<=0){
			MsgBox.warning(getParentShell(),"请输入意外医疗险！");
			txtAmountTwo.forceFocus();
			return null;
		}
		
		premium.setCompanySeq(dropInsurance.droptext.getValue());
		InsCompany insCompany = (InsCompany) dropInsurance.droptext.getObject();
		premium.setInsuranceCode(insCompany.getInsuranceCode());
		premium.setInsuranceName(insCompany.getInsuranceName());
		premium.setPremium(Double.valueOf(txtPremium.getText()));
		premium.setStartPrice(Double.valueOf(txtStartPrice.getText()));
		premium.setLimitPrice(Double.valueOf(txtLimitPrice.getText()));
		premium.setAmountOne(Double.valueOf(txtAmountOne.getText()));
		premium.setAmountTwo(Double.valueOf(txtAmountTwo.getText()));
		premium.setRemark(txtRemark.getText());
		return premium;
	}
	
	private void clearData(){
		dropInsurance.droptext.setValue("");
		txtPremium.setText("");
		txtStartPrice.setText("");
		txtLimitPrice.setText("");
		txtAmountOne.setText("");
		txtAmountTwo.setText("");
		txtRemark.setText("");
		dropInsurance.droptext.txtShow.forceFocus();
	}
	
}
