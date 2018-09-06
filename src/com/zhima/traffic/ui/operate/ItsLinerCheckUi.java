package com.zhima.traffic.ui.operate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.epd.IEpdCheckgate;
import com.zhima.traffic.action.epd.impl.ImpEpdCheckgate;
import com.zhima.traffic.action.operate.IItsLinercheck;
import com.zhima.traffic.action.operate.impl.ImpItsLinercheck;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropParking;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdParking;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;
import com.zhima.widget.stationBar.LinerBar;

public class ItsLinerCheckUi extends Dialog {
	private GridView gridView;
	private String operType;
	private Object obj;
	
	private LinerBar linerBar;
	private GridView gridCheckgate;
	private DropParking dropParking;
	private Text txtParkingName;
	

	protected ItsLinerCheckUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
		this.obj = this;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("检票口设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(600,415);
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
		groupMain.setText("检票口信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		groupMain.setLayout(new FormLayout());
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		linerBar = new LinerBar(groupMain, SWT.NONE);
		data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		linerBar.setLayoutData(data);
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("检票口","checkCode",200));
		columns.add(new GridColumn("检票口名称","checkName",160));
		columns.add(new GridColumn("默认检票口","defaultCheck",TraffFinal.ARR_DEFAULT_CHECK,160));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowSeq(false);
		gridConfig.setCheck(true);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(initRight());
		gridConfig.setObj(obj);
		gridCheckgate = new GridView(groupMain, SWT.BORDER);
		gridCheckgate.CreateTabel(gridConfig);
		data = new FormData();
		data.top = new FormAttachment(linerBar,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100,-35);
		gridCheckgate.setLayoutData(data);
		gridCheckgate.table.addSelectionListener(new SelectionListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				List<EpdCheckgate> checkgates = (List<EpdCheckgate>) gridCheckgate.getCheckSelections();
				if (null != checkgates && checkgates.size()>0){
					dropParking.initByCheckCode(checkgates);
				}else{
					dropParking.initByCheckCode(null);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Label lbParking = new Label(groupMain, SWT.NONE);
		lbParking.setText("发车位代码:");
		lbParking.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.left= new FormAttachment(gridCheckgate,0,SWT.LEFT);
		data.top= new FormAttachment(gridCheckgate,10);
		lbParking.setLayoutData(data);
		
		dropParking = new DropParking(groupMain, SWT.BORDER);
		dropParking.droptext.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.left= new FormAttachment(lbParking,5);
		data.top= new FormAttachment(lbParking,-2,SWT.TOP);
		data.right = new FormAttachment(50);
		data.height = StyleFinal.DROP_HEIGHT;
		dropParking.setLayoutData(data);
		dropParking.droptext.txtShow.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO Auto-generated method stub
				if (null != dropParking.droptext.getValue() && dropParking.droptext.getValue().length()>0){
					EpdParking epdParking = (EpdParking) dropParking.droptext.getObj();
					txtParkingName.setText(epdParking.getParkingName());
				}
			}
		});
		
		Label lbParkingName = new Label(groupMain, SWT.NONE);
		lbParkingName.setText("发车位名称:");
		lbParkingName.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.left= new FormAttachment(dropParking,10);
		data.top= new FormAttachment(lbParking,0,SWT.TOP);
		lbParkingName.setLayoutData(data);
		
		txtParkingName = new Text(groupMain, SWT.BORDER|SWT.READ_ONLY);
		txtParkingName.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.left= new FormAttachment(lbParkingName,5);
		data.top= new FormAttachment(dropParking,0,SWT.TOP);
		data.right = new FormAttachment(gridCheckgate,0,SWT.RIGHT);
		data.height = StyleFinal.TEXT_HEIGHT;
		txtParkingName.setLayoutData(data);
		initData();
		return compMain;
	}
	
	private List<SamModuleRight> initRight() {
		List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
		SamModuleRight moduleRight = new SamModuleRight();
		moduleRight.setRightName("默认检票口(&D)");
		moduleRight.setRightMethod("setCheckGate");
		rights.add(moduleRight);
		return rights;
	}
	
	private void initData(){
		try {
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				ItsLiner itsLiner = (ItsLiner) gridView.getSelection();
				if (null != itsLiner && !"".equals(itsLiner.getLinerSeq())){
					linerBar.txtLinerDate.setText(itsLiner.getLinerDate());
					linerBar.txtLinerId.setText(itsLiner.getLinerId());
					linerBar.txtRouteName.setText(itsLiner.getRouteName());
					IEpdCheckgate iEpdCheckgate = new ImpEpdCheckgate();
					List<EpdCheckgate> checkgates = iEpdCheckgate.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
					gridCheckgate.setDataList(checkgates);
					
					//初始化检票口
					IItsLinercheck iItsLinercheck = new ImpItsLinercheck();
					List<ItsLinercheck> itsLinerchecks = iItsLinercheck.queryByLinerSeq(itsLiner.getLinerSeq());
					if (null != itsLinerchecks && itsLinerchecks.size()>0){
						if (null != checkgates && checkgates.size()>0){
							for (int i = 0; i < checkgates.size(); i++) {
								for (int j = 0; j < itsLinerchecks.size(); j++) {
									if (checkgates.get(i).getCheckgateSeq().equals(itsLinerchecks.get(j).getCheckgateSeq())){
										gridCheckgate.updateRow(i, checkgates.get(i));
										gridCheckgate.setCheck(i, true);
										break;
									}
								}
								if (itsLiner.getCheckgateSeq().equals(checkgates.get(i).getCheckgateSeq())){
									gridCheckgate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_RED));
									checkgates.get(i).setDefaultCheck("1");
									gridCheckgate.updateRow(i, checkgates.get(i));
								}
							}
						}
						//planchecks
						List<EpdCheckgate> epdCheckgates = new ArrayList<EpdCheckgate>();
						for (int i = 0; i < itsLinerchecks.size(); i++) {
							EpdCheckgate epdCheckgate = new EpdCheckgate();
							epdCheckgate.setCheckgateSeq(itsLinerchecks.get(i).getCheckgateSeq());
							epdCheckgates.add(epdCheckgate);
						}
						dropParking.initByCheckCode(epdCheckgates);
						dropParking.droptext.setValue(itsLiner.getParkingSeq());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
				ItsLiner itsLiner = new ItsLiner();
				if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
					itsLiner = (ItsLiner) gridView.getSelection();
				}
				itsLiner.setUpdateTime(currTime);
				
				List<EpdCheckgate> checkgates = (List<EpdCheckgate>) gridCheckgate.getCheckSelections();
				if (null == checkgates || checkgates.size()<=0){
					MsgBox.warning(getShell(), "请设置计划检票口！");
					return;
				}

				List<ItsLinercheck> linerchecks = new ArrayList<ItsLinercheck>();
				boolean defaultCheck = false;
				for (int i = 0; i < checkgates.size(); i++) {
					ItsLinercheck itsLinercheck = new ItsLinercheck();
					itsLinercheck.setLinerSeq(itsLiner.getLinerSeq());
					itsLinercheck.setLinerDate(itsLiner.getLinerDate());
					itsLinercheck.setLinerId(itsLiner.getLinerId());
					itsLinercheck.setCheckgateSeq(checkgates.get(i).getCheckgateSeq());
					itsLinercheck.setUpdateTime(currTime);
					linerchecks.add(itsLinercheck);
					if ("1".equals(checkgates.get(i).getDefaultCheck())){
						defaultCheck = true;
						itsLiner.setCheckgateSeq(checkgates.get(i).getCheckgateSeq());
						itsLiner.setCheckName(checkgates.get(i).getCheckName());
					}
				}
				
				if (null != dropParking.droptext.getValue() && dropParking.droptext.getValue().length()>0){
					itsLiner.setParkingSeq(dropParking.droptext.getValue());
				}
				
				if (defaultCheck == false){
					MsgBox.warning(getParentShell(),"请设置班次默认检票口！");
					gridCheckgate.forceFocus();
					return;
				}
				
				if (null != itsLiner){
					IItsLinercheck iItsLinercheck = new ImpItsLinercheck();
					if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iItsLinercheck.updateAttribute(itsLiner, linerchecks,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), itsLiner);
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
	
	@SuppressWarnings("unchecked")
	public void setCheckGate(){
		List<EpdCheckgate> checkgates = (List<EpdCheckgate>) gridCheckgate.getDataList();
		if (null != checkgates && checkgates.size()>0){
			for (int i = 0; i < checkgates.size(); i++) {
				checkgates.get(i).setDefaultCheck("0");
				gridCheckgate.updateRow(i, checkgates.get(i));
				gridCheckgate.setRowFontColor(i, SWTResourceManager.getColor(SWT.COLOR_BLACK));
				
			}
		}
		gridCheckgate.setCheck(gridCheckgate.getSelectionIndex(), true);
		gridCheckgate.setRowFontColor(gridCheckgate.getSelectionIndex(), SWTResourceManager.getColor(SWT.COLOR_RED));
		EpdCheckgate checkgate = (EpdCheckgate) gridCheckgate.getSelection();
		checkgate.setDefaultCheck("1");
		gridCheckgate.updateRow(gridCheckgate.getSelectionIndex(), checkgate);
		checkgates = (List<EpdCheckgate>) gridCheckgate.getCheckSelections();
		if (null != checkgates && checkgates.size()>0){
			dropParking.initByCheckCode(checkgates);
		}else{
			dropParking.initByCheckCode(null);
		}
	}
}
