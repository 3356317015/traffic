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
import com.zhima.traffic.action.insurance.IInsTickettype;
import com.zhima.traffic.action.insurance.impl.ImpInsTickettype;
import com.zhima.traffic.drop.DropInsurance;
import com.zhima.traffic.model.InsCompany;
import com.zhima.traffic.model.InsTickettype;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridView;

public class InsTickettypeEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private DropInsurance dropInsurance;
	private Text txtTicketName;
	private Text txtTicketNum;
	private Text txtTicketPages;
	private Text txtTicketWidth;
	private Text txtTicketHeight;
	
	private Text txtRemark;

	protected InsTickettypeEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("票据类型设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,315);
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
		groupMain.setText("票据信息");
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
		
		Label lbInsurance = new Label(groupMain, SWT.RIGHT);
		lbInsurance.setFont(StyleFinal.SYS_FONT);
		lbInsurance.setText("保险名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbInsurance.setLayoutData(gridData);
		
		dropInsurance = new DropInsurance(groupMain, SWT.BORDER|SWT.READ_ONLY);
		dropInsurance.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropInsurance.setLayoutData(gridData);
		
		KLabel lbTicketName = new KLabel(groupMain, SWT.RIGHT);
		lbTicketName.setFont(StyleFinal.SYS_FONT);
		lbTicketName.setText("票据名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTicketName.setLayoutData(gridData);
		
		txtTicketName = new Text(groupMain, SWT.BORDER);
		txtTicketName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTicketName.setLayoutData(gridData);
		
		KLabel lbTicketNum = new KLabel(groupMain, SWT.RIGHT);
		lbTicketNum.setFont(StyleFinal.SYS_FONT);
		lbTicketNum.setText("批次数量:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTicketNum.setLayoutData(gridData);
		
		txtTicketNum = new Text(groupMain, SWT.BORDER);
		txtTicketNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTicketNum.setLayoutData(gridData);
		txtTicketNum.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbTicketPages = new KLabel(groupMain, SWT.RIGHT);
		lbTicketPages.setFont(StyleFinal.SYS_FONT);
		lbTicketPages.setText("联单页数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTicketPages.setLayoutData(gridData);
		
		txtTicketPages = new Text(groupMain, SWT.BORDER);
		txtTicketPages.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTicketPages.setLayoutData(gridData);
		txtTicketPages.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbTicketWidth = new KLabel(groupMain, SWT.RIGHT);
		lbTicketWidth.setFont(StyleFinal.SYS_FONT);
		lbTicketWidth.setText("宽度(mm):");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTicketWidth.setLayoutData(gridData);
		
		txtTicketWidth = new Text(groupMain, SWT.BORDER);
		txtTicketWidth.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTicketWidth.setLayoutData(gridData);
		txtTicketWidth.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbTicketHeight = new KLabel(groupMain, SWT.RIGHT);
		lbTicketHeight.setFont(StyleFinal.SYS_FONT);
		lbTicketHeight.setText("高度(mm):");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTicketHeight.setLayoutData(gridData);
		
		txtTicketHeight = new Text(groupMain, SWT.BORDER);
		txtTicketHeight.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTicketHeight.setLayoutData(gridData);
		txtTicketHeight.addVerifyListener(new TextVerifyListener(1));

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
		dropInsurance.droptext.txtShow.forceFocus();
		callMethod.bindEnterEvent(this, dropInsurance.droptext.txtShow, txtTicketName, "");
		callMethod.bindEnterEvent(this, txtTicketName, txtTicketNum, "");
		callMethod.bindEnterEvent(this, txtTicketNum, txtTicketPages, "");
		callMethod.bindEnterEvent(this, txtTicketPages, txtTicketWidth, "");
		callMethod.bindEnterEvent(this, txtTicketWidth, txtTicketHeight, "");
		callMethod.bindEnterEvent(this, txtTicketHeight, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			InsTickettype insTickettype = (InsTickettype) gridView.getSelection();
			if (null != insTickettype && !"".equals(insTickettype.getTickettypeSeq())){
				dropInsurance.droptext.setValue(insTickettype.getCompanySeq());
				txtTicketName.setText(insTickettype.getTicketName());
				txtTicketNum.setText(String.valueOf(insTickettype.getTicketNum()));
				txtTicketPages.setText(String.valueOf(insTickettype.getTicketPages()));
				txtTicketWidth.setText(String.valueOf(insTickettype.getTicketWidth()));
				txtTicketHeight.setText(String.valueOf(insTickettype.getTicketHeight()));
				txtRemark.setText(insTickettype.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				InsTickettype insTickettype = validData();
				if (null != insTickettype){
					IInsTickettype iInsTickettype = new ImpInsTickettype();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						InsTickettype newInsTickettype = iInsTickettype.insert(insTickettype,CommFinal.initConfig());
						gridView.addRow(newInsTickettype);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iInsTickettype.update(insTickettype,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), insTickettype);
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
	
	private InsTickettype validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		InsTickettype insTickettype = new InsTickettype();
		insTickettype.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			insTickettype = (InsTickettype) gridView.getSelection();
		}
		insTickettype.setUpdateTime(currTime);
		
		if (null != dropInsurance.droptext.getValue() && !"".equals(dropInsurance.droptext.getValue())){
			insTickettype.setCompanySeq(dropInsurance.droptext.getValue());
			InsCompany insCompany = (InsCompany) dropInsurance.droptext.getObject();
			insTickettype.setInsuranceCode(insCompany.getInsuranceCode());
			insTickettype.setInsuranceName(insCompany.getInsuranceName());
		}else{
			MsgBox.warning(getParentShell(),"请选择保险名称！");
			dropInsurance.droptext.txtShow.forceFocus();
			return null;
		}
				
		if (null != txtTicketName.getText() && txtTicketName.getText().length()>0){
			insTickettype.setTicketName(txtTicketName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入票据类型名称！");
			txtTicketName.forceFocus();
			return null;
		}
		
		if (null != txtTicketNum.getText() && txtTicketNum.getText().length()>0){
			insTickettype.setTicketNum(Integer.valueOf(txtTicketNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入票据每件张数！");
			txtTicketNum.forceFocus();
			return null;
		}
		
		if (null != txtTicketPages.getText() && txtTicketPages.getText().length()>0){
			insTickettype.setTicketPages(Integer.valueOf(txtTicketPages.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入票据联数！");
			txtTicketPages.forceFocus();
			return null;
		}
		
		if (null != txtTicketWidth.getText() && txtTicketWidth.getText().length()>0){
			insTickettype.setTicketWidth(Integer.valueOf(txtTicketWidth.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入票据宽度！");
			txtTicketWidth.forceFocus();
			return null;
		}
		
		if (null != txtTicketHeight.getText() && txtTicketHeight.getText().length()>0){
			insTickettype.setTicketHeight(Integer.valueOf(txtTicketHeight.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入票据高度！");
			txtTicketHeight.forceFocus();
			return null;
		}
		
		insTickettype.setRemark(txtRemark.getText());
		
		return insTickettype;
	}
	
	private void clearData(){
		dropInsurance.droptext.txtShow.setText("");
		txtTicketName.setText("");
		txtTicketNum.setText("");
		txtTicketPages.setText("");
		txtTicketWidth.setText("");
		txtTicketHeight.setText("");
		txtRemark.setText("");
		dropInsurance.droptext.txtShow.forceFocus();
	}
	
}
