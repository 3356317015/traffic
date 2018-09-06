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
import com.zhima.traffic.action.epd.IEpdContractdetail;
import com.zhima.traffic.action.epd.impl.ImpEpdContractdetail;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropVariables;
import com.zhima.traffic.model.EpdContract;
import com.zhima.traffic.model.EpdContractdetail;
import com.zhima.util.AnalyzeCalculate;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.StringUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdContractdetailEditUi extends Dialog{
	private GridView gridView;
	private String operType;
	private EpdContract epdContract;
	
	private DropVariables dropItemCode;		//合同项目
	private CCombo cboItemBelong;		//票型名称
	private CCombo cboRoundUnit;		//取整单位
	private CCombo cboCarryMode;		//进位方式
	private Text txtRemark;				//备注
	
	private DropVariables dropVariables;//变量
	private DropVariables dropFunction;	//函数
	private Text txtFormula;			//收费公式
	private Text txtFormulaDesc;		//收费公式
	protected EpdContractdetailEditUi(Shell shell,EpdContract epdContract,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
		this.epdContract = epdContract;
	}
	
	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("全同项目设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}

	@Override
	protected Point getInitialSize(){
		return new Point(525,395);
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
		
		KLabel lbItemCode = new KLabel(groupReturnrate, SWT.NONE);
		lbItemCode.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbItemCode.setLayoutData(gridData);
		lbItemCode.setText("合同项目:");

		dropItemCode = new DropVariables(groupReturnrate, SWT.BORDER);
		dropItemCode.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint=160;
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropItemCode.setLayoutData(gridData);

		KLabel lbItemBelong = new KLabel(groupReturnrate, SWT.NONE);
		lbItemBelong.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbItemBelong.setLayoutData(gridData);
		lbItemBelong.setText("费用归属:");

		cboItemBelong = new CCombo(groupReturnrate, SWT.BORDER);
		cboItemBelong.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboItemBelong.setLayoutData(gridData);

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
		dropItemCode.droptext.txtShow.forceFocus();
		dropItemCode.droptext.txtShow.selectAll();
		
		callMethod.bindEnterEvent(this, dropItemCode.droptext.txtShow, cboItemBelong, "");
		callMethod.bindEnterEvent(this, cboItemBelong, cboRoundUnit, "");
		callMethod.bindEnterEvent(this, cboRoundUnit, cboCarryMode, "");
		callMethod.bindEnterEvent(this, cboCarryMode, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, dropVariables.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropVariables.droptext.txtShow, null, "setVariabble");
		callMethod.bindEnterEvent(this, dropFunction.droptext.txtShow, null, "setFunction");	
		return parent;
	}
	
	private void initData(){
		dropItemCode.init("2");
		cboItemBelong.setItems(CommFinal.getAllName(TraffFinal.ARR_ITEM_BELONG));
		cboRoundUnit.setItems(CommFinal.getAllName(TraffFinal.ARR_ROUND_UNIT));
		cboCarryMode.setItems(CommFinal.getAllName(TraffFinal.ARR_CARRY_MODE));
		dropVariables.init("2all");
		dropFunction.init("fun");
		
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			try {
				EpdContractdetail epdContractdetail = (EpdContractdetail) gridView.getSelection();
				if (null != epdContractdetail && !"".equals(epdContractdetail.getContractdetailSeq())){
					dropItemCode.droptext.setValue(epdContractdetail.getItemCode());
					cboItemBelong.setText(CommFinal.getItemName(TraffFinal.ARR_ITEM_BELONG, epdContractdetail.getItemBelong()));
					cboRoundUnit.setText(CommFinal.getItemName(TraffFinal.ARR_ROUND_UNIT, epdContractdetail.getRoundUnit()));
					cboCarryMode.setText(CommFinal.getItemName(TraffFinal.ARR_CARRY_MODE, epdContractdetail.getCarryMode()));
					txtRemark.setText(epdContractdetail.getRemark());
					txtFormula.setText(epdContractdetail.getFormula());
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
				EpdContractdetail contractdetail = validData();
				if (null != contractdetail){
					IEpdContractdetail iEpdContractdetail = new ImpEpdContractdetail();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdContractdetail newContractdetail = iEpdContractdetail.insert(contractdetail,CommFinal.initConfig());
						gridView.addRow(newContractdetail);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdContractdetail.update(contractdetail,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), contractdetail);
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
		dropItemCode.droptext.setValue("");
		cboItemBelong.setText("");
		cboRoundUnit.setText("");
		cboCarryMode.setText("");
		txtRemark.setText("");
		dropVariables.droptext.setValue("");
		dropFunction.droptext.setValue("");
		txtFormula.setText("");
		txtFormulaDesc.setText("");
		dropItemCode.droptext.txtShow.forceFocus();
	}

	private EpdContractdetail validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdContractdetail epdContractdetail = new EpdContractdetail();
		epdContractdetail.setContractSeq(epdContract.getContractSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdContractdetail = (EpdContractdetail) gridView.getSelection();
		}
		epdContractdetail.setUpdateTime(currTime);
		if (null != dropItemCode.droptext.getText() && dropItemCode.droptext.getText().length()>0){
			epdContractdetail.setItemCode(dropItemCode.droptext.getValue());
		}else{
			MsgBox.warning(getParentShell(),"请输入或选择合同项目代码！");
			dropItemCode.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != cboItemBelong.getText() && dropItemCode.droptext.getText().length()>0){
			epdContractdetail.setItemBelong(CommFinal.getItemValue(TraffFinal.ARR_ITEM_BELONG, cboItemBelong.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择合同归属！");
			cboItemBelong.forceFocus();
			return null;
		}
		
		if (null != cboRoundUnit.getText() && cboRoundUnit.getText().length()>0){
			epdContractdetail.setRoundUnit(CommFinal.getItemValue(TraffFinal.ARR_ROUND_UNIT, cboRoundUnit.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择取整单位！");
			cboRoundUnit.forceFocus();
			return null;
		}
		
		if (null != cboCarryMode.getText() && cboCarryMode.getText().length()>0){
			epdContractdetail.setCarryMode(CommFinal.getItemValue(TraffFinal.ARR_CARRY_MODE, cboCarryMode.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择进位方式！");
			cboCarryMode.forceFocus();
			return null;
		}
		epdContractdetail.setRemark(txtRemark.getText());
		if (null != txtFormula.getText() && txtFormula.getText().length()>0){
			epdContractdetail.setFormula(txtFormula.getText());
		}else{
			MsgBox.warning(getParentShell(),"请设置合同项目公式！");
			txtFormula.forceFocus();
			return null;
		}
		//验证收费公式
		if(validFormula() == false){
			MsgBox.warning(getParentShell(),"合同项目公式设置不正确，请检查！");
			txtFormula.forceFocus();
			return null;
		}
		epdContractdetail.setFormula(txtFormula.getText());
		epdContractdetail.setFormulaDesc(txtFormulaDesc.getText());
		return epdContractdetail;
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
