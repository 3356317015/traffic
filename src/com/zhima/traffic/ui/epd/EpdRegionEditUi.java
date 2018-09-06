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
import com.zhima.traffic.action.epd.IEpdRegion;
import com.zhima.traffic.action.epd.impl.ImpEpdRegion;
import com.zhima.traffic.model.EpdRegion;
import com.zhima.util.DateUtils;
import com.zhima.util.PinyinUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdRegionEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtAdministrationCode;
	private Text txtRegionSpell;
	private Text txtCity;
	private Text txtCounty;
	private Text txtTowns;
	private Text txtRemark;

	protected EpdRegionEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("行政区划设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,285);
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
		groupMain.setText("行政区划信息");
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
		
		KLabel lbAdministrationCode = new KLabel(groupMain, SWT.RIGHT);
		lbAdministrationCode.setFont(StyleFinal.SYS_FONT);
		lbAdministrationCode.setText("行政代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbAdministrationCode.setLayoutData(gridData);
		
		txtAdministrationCode = new Text(groupMain, SWT.BORDER);
		txtAdministrationCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtAdministrationCode.setLayoutData(gridData);
		
		Label lbRegionSpell = new Label(groupMain, SWT.RIGHT);
		lbRegionSpell.setFont(StyleFinal.SYS_FONT);
		lbRegionSpell.setText("拼音代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRegionSpell.setLayoutData(gridData);
		
		txtRegionSpell = new Text(groupMain, SWT.BORDER);
		txtRegionSpell.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRegionSpell.setLayoutData(gridData);

		KLabel lbCity = new KLabel(groupMain, SWT.RIGHT);
		lbCity.setFont(StyleFinal.SYS_FONT);
		lbCity.setText("省(市):");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCity.setLayoutData(gridData);
		
		txtCity = new Text(groupMain, SWT.BORDER);
		txtCity.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCity.setLayoutData(gridData);
		
		Label lbCounty = new Label(groupMain, SWT.RIGHT);
		lbCounty.setFont(StyleFinal.SYS_FONT);
		lbCounty.setText("县:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCounty.setLayoutData(gridData);
		
		txtCounty = new Text(groupMain, SWT.BORDER);
		txtCounty.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCounty.setLayoutData(gridData);
		
		Label lbTowns = new Label(groupMain, SWT.RIGHT);
		lbTowns.setFont(StyleFinal.SYS_FONT);
		lbTowns.setText("乡(镇):");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTowns.setLayoutData(gridData);
		
		txtTowns = new Text(groupMain, SWT.BORDER);
		txtTowns.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTowns.setLayoutData(gridData);
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		initData();
		txtAdministrationCode.forceFocus();
		txtAdministrationCode.selectAll();

		callMethod.bindEnterEvent(this, txtAdministrationCode, txtRegionSpell, "");
		callMethod.bindEnterEvent(this, txtRegionSpell, txtCity, "");
		callMethod.bindEnterEvent(this, txtCity, txtCounty, "setSpell");
		callMethod.bindEnterEvent(this, txtCounty, txtTowns, "setSpell");
		callMethod.bindEnterEvent(this, txtTowns, txtRemark, "setSpell");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdRegion epdRegion = (EpdRegion) gridView.getSelection();
			if (null != epdRegion && !"".equals(epdRegion.getRegionSeq())){
				txtAdministrationCode.setText(epdRegion.getAdministrationCode());
				txtRegionSpell.setText(epdRegion.getRegionSpell());
				txtCity.setText(epdRegion.getCity());
				txtCounty.setText(epdRegion.getCounty());
				txtTowns.setText(epdRegion.getTowns());
				txtRemark.setText(epdRegion.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdRegion cityArea = validData();
				if (null != cityArea){
					IEpdRegion iEpdCityArea = new ImpEpdRegion();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdRegion newCityArea = iEpdCityArea.insert(cityArea,CommFinal.initConfig());
						gridView.addRow(newCityArea);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdCityArea.update(cityArea,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), cityArea);
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
	
	private EpdRegion validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdRegion epdRegion = new EpdRegion();
		epdRegion.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdRegion = (EpdRegion) gridView.getSelection();
		}
		epdRegion.setUpdateTime(currTime);
		if (null != txtAdministrationCode.getText() && txtAdministrationCode.getText().length()>0){
			epdRegion.setAdministrationCode(txtAdministrationCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入行政区划代码！");
			txtAdministrationCode.forceFocus();
			return null;
		}
		
		if (null != txtCity.getText() && txtCity.getText().length()>0){
			epdRegion.setCity(txtCity.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入省(市)！");
			txtCity.forceFocus();
			return null;
		}
		epdRegion.setAdministrationCode(txtAdministrationCode.getText());
		epdRegion.setRegionSpell(txtRegionSpell.getText());
		epdRegion.setCity(txtCity.getText());
		epdRegion.setCounty(txtCounty.getText());
		epdRegion.setTowns(txtTowns.getText());
		epdRegion.setRemark(txtRemark.getText());
		
		return epdRegion;
	}
	
	private void clearData(){

		txtAdministrationCode.setText("");
		txtRegionSpell.setText("");
		txtCity.setText("");
		txtCounty.setText("");
		txtTowns.setText("");
		txtRemark.setText("");
		txtAdministrationCode.forceFocus();
	}
	
	public void setSpell(){
		PinyinUtil pinyinUtil = new PinyinUtil();  
		txtRegionSpell.setText(pinyinUtil.String2Alpha(txtCity.getText().toLowerCase()+txtCounty.getText().toLowerCase()+txtTowns.getText().toLowerCase()));
	}
}
