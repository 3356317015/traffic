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
import com.zhima.traffic.action.epd.IEpdFaretype;
import com.zhima.traffic.action.epd.impl.ImpEpdFaretype;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropVariables;
import com.zhima.traffic.model.EpdFaretype;
import com.zhima.util.AnalyzeCalculate;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.StringUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdFaretypeEditUi extends Dialog{
	private GridView gridView;
	private String operType;
	
	private Text txtFaretypeCode;		//票型代码
	private Text txtFaretypeName;		//票型名称
	private CCombo cboFaretypeClass;	//票型类别
	private CCombo cboRoundUnit;		//取整单位
	private CCombo cboCarryMode;		//进位方式
	private CCombo cboIfHavefree;		//是否带免
	private CCombo cboFaretypeStatus;	//票型状态
	private Text txtRemark;				//备注
	private DropVariables dropVariables;//变量
	private DropVariables dropFunction;	//函数
	private Text txtFormula;			//收费公式
	private Text txtFormulaDesc;		//收费公式
	protected EpdFaretypeEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}
	
	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("票型设置-"+operType);
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
		
		Group groupFareType = new Group(compMain,SWT.NONE);
		groupFareType.setText("基础信息");
		groupFareType.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupFareType.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		groupFareType.setLayoutData(data);
		
		KLabel lbFaretypeCode = new KLabel(groupFareType, SWT.NONE);
		lbFaretypeCode.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFaretypeCode.setLayoutData(gridData);
		lbFaretypeCode.setText("票型代码:");

		txtFaretypeCode = new Text(groupFareType, SWT.BORDER);
		txtFaretypeCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint=160;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFaretypeCode.setLayoutData(gridData);

		KLabel lbFaretypeName = new KLabel(groupFareType, SWT.NONE);
		lbFaretypeName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFaretypeName.setLayoutData(gridData);
		lbFaretypeName.setText("票型名称:");

		txtFaretypeName = new Text(groupFareType, SWT.BORDER);
		txtFaretypeName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtFaretypeName.setLayoutData(gridData);
		
		KLabel lbFaretypeClass = new KLabel(groupFareType, SWT.NONE);
		lbFaretypeClass.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFaretypeClass.setLayoutData(gridData);
		lbFaretypeClass.setText("票型类别:");

		cboFaretypeClass = new CCombo(groupFareType, SWT.BORDER|SWT.READ_ONLY);
		cboFaretypeClass.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboFaretypeClass.setLayoutData(gridData);
		
		KLabel lbIfHavefree = new KLabel(groupFareType, SWT.NONE);
		lbIfHavefree.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbIfHavefree.setLayoutData(gridData);
		lbIfHavefree.setText("带免:");

		cboIfHavefree = new CCombo(groupFareType, SWT.BORDER|SWT.READ_ONLY);
		cboIfHavefree.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboIfHavefree.setLayoutData(gridData);
		
		KLabel lbRoundUnit = new KLabel(groupFareType, SWT.NONE);
		lbRoundUnit.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRoundUnit.setLayoutData(gridData);
		lbRoundUnit.setText("取整单位:");

		cboRoundUnit = new CCombo(groupFareType, SWT.BORDER|SWT.READ_ONLY);
		cboRoundUnit.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboRoundUnit.setLayoutData(gridData);

		KLabel lbCarryMode = new KLabel(groupFareType, SWT.NONE);
		lbCarryMode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCarryMode.setLayoutData(gridData);
		lbCarryMode.setText("进位方式:");

		cboCarryMode = new CCombo(groupFareType, SWT.BORDER|SWT.READ_ONLY);
		cboCarryMode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboCarryMode.setLayoutData(gridData);
		
		KLabel lbFaretypeStatus = new KLabel(groupFareType, SWT.NONE);
		lbFaretypeStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFaretypeStatus.setLayoutData(gridData);
		lbFaretypeStatus.setText("票型状态:");

		cboFaretypeStatus = new CCombo(groupFareType, SWT.BORDER|SWT.READ_ONLY);
		cboFaretypeStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboFaretypeStatus.setLayoutData(gridData);

		Label lbRemark = new Label(groupFareType, SWT.NONE);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		lbRemark.setText("备注:");

		txtRemark = new Text(groupFareType, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		Group groupFormula = new Group(compMain,SWT.NONE);
		groupFormula.setText("公式信息");
		groupFormula.setFont(StyleFinal.SYS_FONT);
		groupFormula.setLayout(new FormLayout());

		data = new FormData();
		data.top = new FormAttachment(groupFareType,10);
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
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.widthHint=105;
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
		txtFaretypeCode.forceFocus();
		txtFaretypeCode.selectAll();
		
		callMethod.bindEnterEvent(this, txtFaretypeCode, txtFaretypeName, "");
		callMethod.bindEnterEvent(this, txtFaretypeName, cboFaretypeClass, "");
		callMethod.bindEnterEvent(this, cboFaretypeClass, cboIfHavefree, "");
		callMethod.bindEnterEvent(this, cboIfHavefree, cboRoundUnit, "");
		callMethod.bindEnterEvent(this, cboRoundUnit, cboCarryMode, "");
		callMethod.bindEnterEvent(this, cboCarryMode, cboFaretypeStatus, "");
		callMethod.bindEnterEvent(this, cboFaretypeStatus, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, dropVariables.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropVariables.droptext.txtShow, null, "setVariabble");
		callMethod.bindEnterEvent(this, dropFunction.droptext.txtShow, null, "setFunction");	
		return parent;
	}
	
	private void initData(){
		cboFaretypeClass.setItems(CommFinal.getAllName(TraffFinal.ARR_FARETYPE_CLASS));
		cboIfHavefree.setItems(CommFinal.getAllName(TraffFinal.ARR_IF_HAVAFREE));
		cboRoundUnit.setItems(CommFinal.getAllName(TraffFinal.ARR_ROUND_UNIT));
		cboCarryMode.setItems(CommFinal.getAllName(TraffFinal.ARR_CARRY_MODE));
		cboFaretypeStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_FARETYPE_STATUS));
		dropVariables.init("1");
		dropFunction.init("fun");
		
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			try {
				EpdFaretype epdFaretype = (EpdFaretype) gridView.getSelection();
				if (null != epdFaretype && !"".equals(epdFaretype.getFaretypeSeq())){
					txtFaretypeCode.setText(epdFaretype.getFaretypeCode());
					txtFaretypeName.setText(epdFaretype.getFaretypeName());
					cboFaretypeClass.setText(CommFinal.getItemName(TraffFinal.ARR_FARETYPE_CLASS, epdFaretype.getFaretypeClass()));
					cboIfHavefree.setText(CommFinal.getItemName(TraffFinal.ARR_IF_HAVAFREE, epdFaretype.getIfHavefree()));
					cboRoundUnit.setText(CommFinal.getItemName(TraffFinal.ARR_ROUND_UNIT, epdFaretype.getRoundUnit()));
					cboCarryMode.setText(CommFinal.getItemName(TraffFinal.ARR_CARRY_MODE, epdFaretype.getCarryMode()));
					cboFaretypeStatus.setText(CommFinal.getItemName(TraffFinal.ARR_FARETYPE_STATUS, epdFaretype.getFaretypeStatus()));
					txtRemark.setText(epdFaretype.getRemark());
					txtFormula.setText(epdFaretype.getFormula());
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
				EpdFaretype epdFaretype = validData();
				if (null != epdFaretype){
					IEpdFaretype iEpdFaretype = new ImpEpdFaretype();
					
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdFaretype epdfFaretype = iEpdFaretype.insert(epdFaretype,CommFinal.initConfig());
						gridView.addRow(epdfFaretype);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdFaretype.update(epdFaretype,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdFaretype);
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
		txtFaretypeCode.setText("");
		txtFaretypeName.setText("");
		cboFaretypeClass.setText("");
		cboIfHavefree.setText("");
		cboRoundUnit.setText("");
		cboCarryMode.setText("");
		cboFaretypeStatus.setText("");
		txtRemark.setText("");
		dropVariables.droptext.setValue("");
		dropFunction.droptext.setValue("");
		txtFormula.setText("");
		txtFormulaDesc.setText("");
		txtFaretypeCode.forceFocus();
	}

	private EpdFaretype validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdFaretype epdFaretype = new EpdFaretype();
		epdFaretype.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdFaretype = (EpdFaretype) gridView.getSelection();
		}
		epdFaretype.setUpdateTime(currTime);
		if (null != txtFaretypeCode.getText() && txtFaretypeCode.getText().length()>0){
			epdFaretype.setFaretypeCode(txtFaretypeCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入票型代码！");
			txtFaretypeCode.forceFocus();
			return null;
		}
		
		if (null != txtFaretypeName.getText() && txtFaretypeName.getText().length()>0){
			epdFaretype.setFaretypeName(txtFaretypeName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入票型名称！");
			txtFaretypeName.forceFocus();
			return null;
		}
		
		if (null != cboFaretypeClass.getText() && cboFaretypeClass.getText().length()>0){
			epdFaretype.setFaretypeClass(CommFinal.getItemValue(TraffFinal.ARR_FARETYPE_CLASS, cboFaretypeClass.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择到达方式！");
			cboFaretypeClass.forceFocus();
			return null;
		}
		
		if (null != cboIfHavefree.getText() && cboIfHavefree.getText().length()>0){
			epdFaretype.setIfHavefree(CommFinal.getItemValue(TraffFinal.ARR_IF_HAVAFREE, cboIfHavefree.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择是否带免！");
			cboIfHavefree.forceFocus();
			return null;
		}
		
		if (null != cboRoundUnit.getText() && cboRoundUnit.getText().length()>0){
			epdFaretype.setRoundUnit(CommFinal.getItemValue(TraffFinal.ARR_ROUND_UNIT, cboRoundUnit.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择取整单位！");
			cboRoundUnit.forceFocus();
			return null;
		}
		
		if (null != cboCarryMode.getText() && cboCarryMode.getText().length()>0){
			epdFaretype.setCarryMode(CommFinal.getItemValue(TraffFinal.ARR_CARRY_MODE, cboCarryMode.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择进位方式！");
			cboCarryMode.forceFocus();
			return null;
		}
		
		if (null != cboFaretypeStatus.getText() && cboFaretypeStatus.getText().length()>0){
			epdFaretype.setFaretypeStatus(CommFinal.getItemValue(TraffFinal.ARR_FARETYPE_STATUS, cboFaretypeStatus.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择票型状态！");
			cboFaretypeStatus.forceFocus();
			return null;
		}
		if (null != txtFormula.getText() && txtFormula.getText().length()>0){
			epdFaretype.setFormula(txtFormula.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入票型设置公式！");
			txtFormula.forceFocus();
			return null;
		}
		epdFaretype.setRemark(txtRemark.getText());
		//验证收费公式
		if(validFormula() == false){
			MsgBox.warning(getParentShell(),"收费公式不正确，请检查！");
			txtFormula.forceFocus();
			return null;
		}
		epdFaretype.setFormula(txtFormula.getText());
		epdFaretype.setFormulaDesc(txtFormulaDesc.getText());
		return epdFaretype;
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
