package com.zhima.traffic.ui.epd;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import com.zhima.frame.model.SamVariables;
import com.zhima.traffic.action.epd.IEpdReturnrate;
import com.zhima.traffic.action.epd.impl.ImpEpdReturnrate;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropVariables;
import com.zhima.traffic.model.EpdReturnrate;
import com.zhima.util.AnalyzeCalculate;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.StringUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridView;

public class EpdReturnrateEditUi extends Dialog{
	private GridView gridView;
	private String operType;
	
	private Text txtReturnrateCode;		//票型代码
	private Text txtReturnrateName;		//票型名称
	private Text txtTimeStart;			//票型类别
	private Text txtTimeLimit;			//票型类别
	private CCombo cboRoundUnit;		//取整单位
	private CCombo cboCarryMode;		//进位方式
	private Text txtRemark;				//备注
	private DropVariables dropVariables;//变量
	private DropVariables dropFunction;	//函数
	private Text txtFormula;			//收费公式
	private Text txtFormulaDesc;		//收费公式
	protected EpdReturnrateEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}
	
	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("退票费率设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}

	@Override
	protected Point getInitialSize(){
		return new Point(525,425);
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

	protected Control createDialogArea(Composite parent){
		Composite compMain = (Composite) super.createDialogArea(parent);
		compMain.setLayout(new FormLayout());
		CallMethod callMethod = new CallMethod();
		
		Group groupReturnrate = new Group(compMain,SWT.NONE);
		groupReturnrate.setText("基础信息");
		groupReturnrate.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupReturnrate.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		groupReturnrate.setLayoutData(data);
		
		KLabel lbReturnrateCode = new KLabel(groupReturnrate, SWT.NONE);
		lbReturnrateCode.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbReturnrateCode.setLayoutData(gridData);
		lbReturnrateCode.setText("费率代码:");

		txtReturnrateCode = new Text(groupReturnrate, SWT.BORDER);
		txtReturnrateCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint=160;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtReturnrateCode.setLayoutData(gridData);

		KLabel lbReturnrateName = new KLabel(groupReturnrate, SWT.NONE);
		lbReturnrateName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbReturnrateName.setLayoutData(gridData);
		lbReturnrateName.setText("费率名称:");

		txtReturnrateName = new Text(groupReturnrate, SWT.BORDER);
		txtReturnrateName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtReturnrateName.setLayoutData(gridData);
		
		KLabel lbTimeStart = new KLabel(groupReturnrate, SWT.NONE);
		lbTimeStart.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTimeStart.setLayoutData(gridData);
		lbTimeStart.setText("上限分钟:");

		txtTimeStart = new Text(groupReturnrate, SWT.BORDER);
		txtTimeStart.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTimeStart.setLayoutData(gridData);
		txtTimeStart.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbTimeLimit = new KLabel(groupReturnrate, SWT.NONE);
		lbTimeLimit.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTimeLimit.setLayoutData(gridData);
		lbTimeLimit.setText("下限分钟:");

		txtTimeLimit = new Text(groupReturnrate, SWT.BORDER);
		txtTimeLimit.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTimeLimit.setLayoutData(gridData);
		txtTimeLimit.addVerifyListener(new TextVerifyListener(1));
		
		KLabel lbRoundUnit = new KLabel(groupReturnrate, SWT.NONE);
		lbRoundUnit.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRoundUnit.setLayoutData(gridData);
		lbRoundUnit.setText("取整单位:");

		cboRoundUnit = new CCombo(groupReturnrate, SWT.BORDER|SWT.READ_ONLY);
		cboRoundUnit.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboRoundUnit.setLayoutData(gridData);

		KLabel lbCarryMode = new KLabel(groupReturnrate, SWT.NONE);
		lbCarryMode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCarryMode.setLayoutData(gridData);
		lbCarryMode.setText("进位方式:");

		cboCarryMode = new CCombo(groupReturnrate, SWT.BORDER|SWT.READ_ONLY);
		cboCarryMode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboCarryMode.setLayoutData(gridData);

		Label lbRemark = new Label(groupReturnrate, SWT.NONE);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		lbRemark.setText("备注:");

		txtRemark = new Text(groupReturnrate, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		Group groupFormula = new Group(compMain,SWT.NONE);
		groupFormula.setText("公式信息");
		groupFormula.setFont(StyleFinal.SYS_FONT);
		groupFormula.setLayout(new FormLayout());

		data = new FormData();
		data.top = new FormAttachment(groupReturnrate,10);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100, -5);
		groupFormula.setLayoutData(data);
		
		Composite compFareFormual = new Composite(groupFormula, SWT.NONE);
		gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		compFareFormual.setLayout(gridLayout);
		data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		compFareFormual.setLayoutData(data);
		
		Label lbVariables = new Label(compFareFormual, SWT.NONE|SWT.RIGHT);
		lbVariables.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=68;
		lbVariables.setLayoutData(gridData);
		lbVariables.setText("变量:");

		dropVariables = new DropVariables(compFareFormual, SWT.BORDER);
		dropVariables.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint=105;
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropVariables.setLayoutData(gridData);

		Label lbFunction = new Label(compFareFormual, SWT.NONE|SWT.RIGHT);
		lbFunction.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=65;
		lbFunction.setLayoutData(gridData);
		lbFunction.setText("函数:");

		dropFunction = new DropVariables(compFareFormual, SWT.BORDER);
		dropFunction.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropFunction.setLayoutData(gridData);

		KLabel lbFormula = new KLabel(groupFormula, SWT.NONE);
		lbFormula.setText("售票公式:");
		lbFormula.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(compFareFormual,5);
		data.left = new FormAttachment(0, 5);
		lbFormula.setLayoutData(data);
		
		txtFormula = new Text(groupFormula, SWT.BORDER|SWT.MULTI|SWT.WRAP);
		txtFormula.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(lbFormula,-2,SWT.TOP);
		data.left = new FormAttachment(lbFormula, 5);
		data.height=50;
		data.right = new FormAttachment(100, -5);
		txtFormula.setLayoutData(data);
		txtFormula.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent m) {
				String string = showChinese();
				txtFormulaDesc.setText(string);
			}
		});

		Label lbFormulaDesc = new Label(groupFormula, SWT.NONE);
		lbFormulaDesc.setText("中文描述:");
		lbFormulaDesc.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(txtFormula,10);
		data.right = new FormAttachment(lbFormula, 0, SWT.RIGHT);
		lbFormulaDesc.setLayoutData(data);

		txtFormulaDesc = new Text(groupFormula, SWT.BORDER|SWT.MULTI|SWT.WRAP|SWT.READ_ONLY);
		txtFormulaDesc.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(lbFormulaDesc,-2,SWT.TOP);
		data.left = new FormAttachment(lbFormulaDesc, 5);
		data.bottom= new FormAttachment(100, -5);
		data.right = new FormAttachment(100, -5);
		txtFormulaDesc.setLayoutData(data);
		
		initData();
		txtReturnrateCode.forceFocus();
		txtReturnrateCode.selectAll();
		
		callMethod.bindEnterEvent(this, txtReturnrateCode, txtReturnrateName, "");
		callMethod.bindEnterEvent(this, txtReturnrateName, txtTimeStart, "");
		callMethod.bindEnterEvent(this, txtTimeStart, txtTimeLimit, "");
		callMethod.bindEnterEvent(this, txtTimeLimit, cboRoundUnit, "");
		callMethod.bindEnterEvent(this, cboRoundUnit, cboCarryMode, "");
		callMethod.bindEnterEvent(this, cboCarryMode, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, dropVariables.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropVariables.droptext.txtShow, null, "setVariabble");
		callMethod.bindEnterEvent(this, dropFunction.droptext.txtShow, null, "setFunction");	
		return parent;
	}
	
	private void initData(){
		cboRoundUnit.setItems(CommFinal.getAllName(TraffFinal.ARR_ROUND_UNIT));
		cboCarryMode.setItems(CommFinal.getAllName(TraffFinal.ARR_CARRY_MODE));
		dropVariables.init("1");
		dropFunction.init("fun");
		
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			try {
				EpdReturnrate epdReturnrate = (EpdReturnrate) gridView.getSelection();
				if (null != epdReturnrate && !"".equals(epdReturnrate.getReturnrateSeq())){
					txtReturnrateCode.setText(epdReturnrate.getReturnrateCode());
					txtReturnrateName.setText(epdReturnrate.getReturnrateName());
					txtTimeStart.setText(String.valueOf(epdReturnrate.getTimeStart()));
					txtTimeLimit.setText(String.valueOf(epdReturnrate.getTimeLimit()));
					cboRoundUnit.setText(CommFinal.getItemName(TraffFinal.ARR_ROUND_UNIT, epdReturnrate.getRoundUnit()));
					cboCarryMode.setText(CommFinal.getItemName(TraffFinal.ARR_CARRY_MODE, epdReturnrate.getCarryMode()));
					txtRemark.setText(epdReturnrate.getRemark());
					txtFormula.setText(epdReturnrate.getFormula());
					txtFormulaDesc.setText(showChinese());
				}
			} catch (Exception e) {
				LogUtil.operLog(e,"E",true,true);
			}
		}
	}
	
	
	public void setVariabble(){
		String variableCode= dropVariables.droptext.getValue();
		if (null != variableCode && variableCode.length()>0) {
			String string = txtFormula.getText()+"["+variableCode+"]";
			txtFormula.setText(string);
			dropVariables.droptext.setValue("");
		}
	}
	
	public void setFunction(){
		String functionCode= dropFunction.droptext.getValue();
		if (null != functionCode && functionCode.length()>0) {
			String string = txtFormula.getText()+functionCode;
			txtFormula.setText(string);
			dropFunction.droptext.setValue("");
		}
	}
	
	@SuppressWarnings("unchecked")
	public String showChinese(){
		String formula = txtFormula.getText();
		try{
			if(null != formula && formula.length()>0){
				List<SamVariables> variables = (List<SamVariables>) dropVariables.droptext.getDataList();
				for(SamVariables samVariables : variables){
					//将的的代码转换成代码名称
					formula = formula.replace("["+samVariables.getVariableCode()+"]",samVariables.getVariableName());
				}
			}
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		return formula;
	}
	
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdReturnrate returnrate = validData();
				if (null != returnrate){
					IEpdReturnrate iEpdReturnrate = new ImpEpdReturnrate();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdReturnrate epdReturnrate = iEpdReturnrate.insert(returnrate,CommFinal.initConfig());
						gridView.addRow(epdReturnrate);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdReturnrate.update(returnrate,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), returnrate);
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
	
	private void clearData(){
		txtReturnrateCode.setText("");
		txtReturnrateName.setText("");
		txtTimeStart.setText("");
		txtTimeLimit.setText("");
		cboRoundUnit.setText("");
		cboCarryMode.setText("");
		txtRemark.setText("");
		dropVariables.droptext.setValue("");
		dropFunction.droptext.setValue("");
		txtFormula.setText("");
		txtFormulaDesc.setText("");
		txtReturnrateCode.forceFocus();
	}

	private EpdReturnrate validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdReturnrate epdReturnrate = new EpdReturnrate();
		epdReturnrate.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdReturnrate = (EpdReturnrate) gridView.getSelection();
		}
		epdReturnrate.setUpdateTime(currTime);
		if (null != txtReturnrateCode.getText() && txtReturnrateCode.getText().length()>0){
			epdReturnrate.setReturnrateCode(txtReturnrateCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入退票费率代码！");
			txtReturnrateCode.forceFocus();
			return null;
		}
		
		if (null != txtReturnrateName.getText() && txtReturnrateName.getText().length()>0){
			epdReturnrate.setReturnrateName(txtReturnrateName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入退票费率名称！");
			txtReturnrateName.forceFocus();
			return null;
		}
		
		if (null != txtTimeStart.getText() && txtTimeStart.getText().length()>0){
			epdReturnrate.setTimeStart(Integer.valueOf(txtTimeStart.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入上限时间分钟数！");
			txtTimeStart.forceFocus();
			return null;
		}
		
		if (null != txtTimeLimit.getText() && txtTimeLimit.getText().length()>0){
			epdReturnrate.setTimeLimit(Integer.valueOf(txtTimeLimit.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入下限时间分钟数！");
			txtTimeLimit.forceFocus();
			return null;
		}
		
		if (Integer.valueOf(txtTimeStart.getText())>=Integer.valueOf(txtTimeLimit.getText())){
			MsgBox.warning(getParentShell(),"请重新输入，上限分钟不允许大于下限分钟！");
			txtTimeStart.forceFocus();
			txtTimeStart.selectAll();
			return null;
		}
		
		if (null != cboRoundUnit.getText() && cboRoundUnit.getText().length()>0){
			epdReturnrate.setRoundUnit(CommFinal.getItemValue(TraffFinal.ARR_ROUND_UNIT, cboRoundUnit.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择取整单位！");
			cboRoundUnit.forceFocus();
			return null;
		}
		
		if (null != cboCarryMode.getText() && cboCarryMode.getText().length()>0){
			epdReturnrate.setCarryMode(CommFinal.getItemValue(TraffFinal.ARR_CARRY_MODE, cboCarryMode.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择进位方式！");
			cboCarryMode.forceFocus();
			return null;
		}
		epdReturnrate.setRemark(txtRemark.getText());
		if (null != txtFormula.getText() && txtFormula.getText().length()>0){
			epdReturnrate.setFormula(txtFormula.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入退票费率设置公式！");
			txtFormula.forceFocus();
			return null;
		}
		//验证收费公式
		if(validFormula() == false){
			MsgBox.warning(getParentShell(),"收费公式不正确，请检查！");
			txtFormula.forceFocus();
			return null;
		}
		epdReturnrate.setFormula(txtFormula.getText());
		epdReturnrate.setFormulaDesc(txtFormulaDesc.getText());
		return epdReturnrate;
	}
	
	@SuppressWarnings("unchecked")
	public boolean validFormula(){
		String text = txtFormula.getText();
		if(null != text && text.length()>0){
			for(SamVariables samVariables : (List<SamVariables>)dropVariables.droptext.getDataList()){
				text = text.replace("["+samVariables.getVariableCode()+"]","1");
			}
		}
		String value = AnalyzeCalculate.calculate(AnalyzeCalculate.strCast(text));
		return StringUtils.isNumeric(value);
	}
	
}
