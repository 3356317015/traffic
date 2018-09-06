package com.zhima.traffic.ui.epd;

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
import com.zhima.traffic.action.epd.IEpdTickettype;
import com.zhima.traffic.action.epd.impl.ImpEpdTickettype;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdTickettype;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridView;

public class EpdTickettypeEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private CCombo cboCategory;
	private Text txtTicketName;
	private Text txtTicketNum;
	private Text txtTicketPages;
	private Text txtTicketWidth;
	private Text txtTicketHeight;
	
	private Text txtRemark;

	protected EpdTickettypeEditUi(Shell shell,GridView gridView,String operType) {
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
		
		Label lbCategory = new Label(groupMain, SWT.RIGHT);
		lbCategory.setFont(StyleFinal.SYS_FONT);
		lbCategory.setText("票据类型:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCategory.setLayoutData(gridData);
		
		cboCategory = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cboCategory.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboCategory.setLayoutData(gridData);
		
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
		cboCategory.forceFocus();
		callMethod.bindEnterEvent(this, cboCategory, txtTicketName, "");
		callMethod.bindEnterEvent(this, txtTicketName, txtTicketNum, "");
		callMethod.bindEnterEvent(this, txtTicketNum, txtTicketPages, "");
		callMethod.bindEnterEvent(this, txtTicketPages, txtTicketWidth, "");
		callMethod.bindEnterEvent(this, txtTicketWidth, txtTicketHeight, "");
		callMethod.bindEnterEvent(this, txtTicketHeight, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		cboCategory.setItems(CommFinal.getAllName(TraffFinal.ARR_CATEGORY));
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdTickettype epdTickettype = (EpdTickettype) gridView.getSelection();
			if (null != epdTickettype && !"".equals(epdTickettype.getTickettypeSeq())){
				cboCategory.setText(CommFinal.getItemName(TraffFinal.ARR_CATEGORY,epdTickettype.getCategory()));
				txtTicketName.setText(epdTickettype.getTicketName());
				txtTicketNum.setText(String.valueOf(epdTickettype.getTicketNum()));
				txtTicketPages.setText(String.valueOf(epdTickettype.getTicketPages()));
				txtTicketWidth.setText(String.valueOf(epdTickettype.getTicketWidth()));
				txtTicketHeight.setText(String.valueOf(epdTickettype.getTicketHeight()));
				txtRemark.setText(epdTickettype.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdTickettype epdTickettype = validData();
				if (null != epdTickettype){
					IEpdTickettype iEpdTickettype = new ImpEpdTickettype();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdTickettype newEpdTickettype = iEpdTickettype.insert(epdTickettype,CommFinal.initConfig());
						gridView.addRow(newEpdTickettype);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdTickettype.update(epdTickettype,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdTickettype);
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
	
	private EpdTickettype validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdTickettype epdTickettype = new EpdTickettype();
		epdTickettype.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdTickettype = (EpdTickettype) gridView.getSelection();
		}
		epdTickettype.setUpdateTime(currTime);
		
		if (null != cboCategory.getText() && !"".equals(cboCategory.getText())){
			epdTickettype.setCategory(CommFinal.getItemValue(TraffFinal.ARR_CATEGORY,cboCategory.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择票据类型！");
			cboCategory.forceFocus();
			return null;
		}
				
		if (null != txtTicketName.getText() && txtTicketName.getText().length()>0){
			epdTickettype.setTicketName(txtTicketName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入票据类型名称！");
			txtTicketName.forceFocus();
			return null;
		}
		
		if (null != txtTicketNum.getText() && txtTicketNum.getText().length()>0){
			epdTickettype.setTicketNum(Integer.valueOf(txtTicketNum.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入票据每件张数！");
			txtTicketNum.forceFocus();
			return null;
		}
		
		if (null != txtTicketPages.getText() && txtTicketPages.getText().length()>0){
			epdTickettype.setTicketPages(Integer.valueOf(txtTicketPages.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入票据联数！");
			txtTicketPages.forceFocus();
			return null;
		}
		
		if (null != txtTicketWidth.getText() && txtTicketWidth.getText().length()>0){
			epdTickettype.setTicketWidth(Integer.valueOf(txtTicketWidth.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入票据宽度！");
			txtTicketWidth.forceFocus();
			return null;
		}
		
		if (null != txtTicketHeight.getText() && txtTicketHeight.getText().length()>0){
			epdTickettype.setTicketHeight(Integer.valueOf(txtTicketHeight.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请输入票据高度！");
			txtTicketHeight.forceFocus();
			return null;
		}
		
		epdTickettype.setRemark(txtRemark.getText());
		
		return epdTickettype;
	}
	
	private void clearData(){
		cboCategory.setText("");
		txtTicketName.setText("");
		txtTicketNum.setText("");
		txtTicketPages.setText("");
		txtTicketWidth.setText("");
		txtTicketHeight.setText("");
		txtRemark.setText("");
		cboCategory.forceFocus();
	}
	
}
