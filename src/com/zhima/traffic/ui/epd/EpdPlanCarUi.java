package com.zhima.traffic.ui.epd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamParameter;
import com.zhima.traffic.action.epd.IEpdCar;
import com.zhima.traffic.action.epd.IEpdCarinfo;
import com.zhima.traffic.action.epd.IEpdPlancar;
import com.zhima.traffic.action.epd.impl.ImpEpdCar;
import com.zhima.traffic.action.epd.impl.ImpEpdCarinfo;
import com.zhima.traffic.action.epd.impl.ImpEpdPlancar;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.comm.TraffParam;
import com.zhima.traffic.drop.DropCar;
import com.zhima.traffic.model.EpdCar;
import com.zhima.traffic.model.EpdCarinfo;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class EpdPlanCarUi extends Dialog {
	private GridView gridView;
	private String operType;
	private Object obj;
	
	private DropCar dropCar;
	private GridView gridCar;	

	protected EpdPlanCarUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.obj = this;
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("计划运力设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(700,515);
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
		groupMain.setText("计划运力信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(new FormLayout());
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		Label lbCar = new Label(groupMain, SWT.NONE);
		lbCar.setText("车牌号:");
		lbCar.setFont(StyleFinal.SYS_FONT);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0, 5);
		formData.left= new FormAttachment(0, 5);
		lbCar.setLayoutData(formData);
		
		dropCar = new DropCar(groupMain, SWT.BORDER);
		dropCar.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0, 3);
		formData.left= new FormAttachment(lbCar, 5);
		formData.height = StyleFinal.DROP_HEIGHT;
		formData.width = 160;
		dropCar.setLayoutData(formData);
		
		Button btnAdd = new Button(groupMain, SWT.NONE);
		btnAdd.setText("添加(&A)");
		btnAdd.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0, 2);
		formData.left= new FormAttachment(dropCar, 5);
		formData.width = 80;
		btnAdd.setLayoutData(formData);
		btnAdd.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDown(MouseEvent arg0) {
				addCar();
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		Button btnInput = new Button(groupMain, SWT.NONE);
		btnInput.setText("导入(&I)");
		btnInput.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0, 2);
		formData.right= new FormAttachment(100, -5);
		formData.width = 80;
		btnInput.setLayoutData(formData);
		btnInput.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDown(MouseEvent arg0) {
				inputCar();
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});

		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("车牌号","carNumber",100));
		columns.add(new GridColumn("购车日期","buyDate",100));
		columns.add(new GridColumn("强保日","checkDays",80));
		columns.add(new GridColumn("座位数","seatNum",80));
		columns.add(new GridColumn("准座数","quasiNum",80));
		columns.add(new GridColumn("车型","cargradeName",100));
		columns.add(new GridColumn("运营合同","contractName",120));
		columns.add(new GridColumn("结算号","companyCode",160));
		columns.add(new GridColumn("结算公司","companyName",300));
		columns.add(new GridColumn("车属公司","carCompanyname",300));
		columns.add(new GridColumn("状态","carStatus",TraffFinal.ARR_CAR_STATUS,100));
		columns.add(new GridColumn("备注","remark",300));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowSeq(true);
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(initRight());
		gridConfig.setObj(obj);
		gridCar = new GridView(groupMain, SWT.NONE);
		gridCar.CreateTabel(gridConfig);
		formData = new FormData();
		formData.top = new FormAttachment(lbCar, 10);
		formData.left= new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		gridCar.setLayoutData(formData);
		
		initData();
		dropCar.droptext.txtShow.forceFocus();
		dropCar.droptext.txtShow.selectAll();

		callMethod.bindEnterEvent(this, dropCar.droptext.txtShow, null, "addCar");
		return compMain;
	}
	
	private void initData(){
		try {
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				EpdPlan epdPlan = (EpdPlan) gridView.getSelection();
				if (null != epdPlan && !"".equals(epdPlan.getPlanSeq())){
					IEpdCar iEpdCar = new ImpEpdCar();
					List<EpdCar> epdCars = iEpdCar.queryByPlanId(CommFinal.organize.getOrganizeSeq(),epdPlan.getPlanId());
					gridCar.setDataList(epdCars);
					IEpdCarinfo iEpdCarinfo = new ImpEpdCarinfo();
					SamParameter parameter = CommFinal.getParamValue(TraffParam.WarningCarDays);
					String currDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
					for (int j = 0; j < epdCars.size(); j++) {
						epdCars.get(j).setSafecarLimit(epdCars.get(j).getSafecarValid() + CommFinal.getItemName(
								TraffFinal.ARR_SAFECAR_TYPE, epdCars.get(j).getSafecarType()));
						epdCars.get(j).setSafecerLimit(epdCars.get(j).getSafecerValid() + CommFinal.getItemName(
								TraffFinal.ARR_SAFECER_TYPE, epdCars.get(j).getSafecerType()));
						if (DateUtils.nDaysBetweenTwoDate(currDate,epdCars.get(j).getCargradeValid())<0){
							gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
							continue;
						}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdCars.get(j).getCargradeValid())
								<=Integer.valueOf(parameter.getParameterValue())){
							gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
						}
						
						List<EpdCarinfo> carinfos = iEpdCarinfo.queryByCarSeq(epdCars.get(j).getCarSeq());
						if(null != carinfos && carinfos.size()>0){
							for (int k = 0; k < carinfos.size(); k++) {
								if (DateUtils.nDaysBetweenTwoDate(currDate,carinfos.get(k).getEndDate())<0){
									gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
									break;
								}else if(DateUtils.nDaysBetweenTwoDate(currDate,carinfos.get(k).getEndDate())
										<=Integer.valueOf(parameter.getParameterValue())){
									gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addCar(){
		if (null != dropCar.droptext.getValue() && dropCar.droptext.getValue().length()>0){
			try {
				List<EpdCar> epdCars = (List<EpdCar>) gridCar.getDataList();
				IEpdCarinfo iEpdCarinfo = new ImpEpdCarinfo();
				SamParameter parameter = CommFinal.getParamValue(TraffParam.WarningDriverDays);
				String currDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
				if (null != epdCars && epdCars.size()>0){
					for (int i = 0; i < epdCars.size(); i++) {
						if (dropCar.droptext.getValue().equals(epdCars.get(i).getCarNumber())){
							MsgBox.warning(getShell(), "车辆已存在，不允许重复添加。");
							return;
						}
					}
				}
				gridCar.addRow(dropCar.droptext.getObject());
				epdCars = (List<EpdCar>) gridCar.getDataList();
				for (int j = 0; j < epdCars.size(); j++) {
					epdCars.get(j).setSafecarLimit(epdCars.get(j).getSafecarValid() + CommFinal.getItemName(
							TraffFinal.ARR_SAFECAR_TYPE, epdCars.get(j).getSafecarType()));
					epdCars.get(j).setSafecerLimit(epdCars.get(j).getSafecerValid() + CommFinal.getItemName(
							TraffFinal.ARR_SAFECER_TYPE, epdCars.get(j).getSafecerType()));
					if (DateUtils.nDaysBetweenTwoDate(currDate,epdCars.get(j).getCargradeValid())<0){
						gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
						continue;
					}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdCars.get(j).getCargradeValid())
							<=Integer.valueOf(parameter.getParameterValue())){
						gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
					}
					
					List<EpdCarinfo> carinfos = iEpdCarinfo.queryByCarSeq(epdCars.get(j).getCarSeq());
					if(null != carinfos && carinfos.size()>0){
						for (int k = 0; k < carinfos.size(); k++) {
							if (DateUtils.nDaysBetweenTwoDate(currDate,carinfos.get(k).getEndDate())<0){
								gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
								break;
							}else if(DateUtils.nDaysBetweenTwoDate(currDate,carinfos.get(k).getEndDate())
									<=Integer.valueOf(parameter.getParameterValue())){
								gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
							}
						}
					}
				}
			} catch (Exception e) {
				LogUtil.operLog(e,"E",true,true);
			}
		}
		dropCar.droptext.txtShow.forceFocus();
		dropCar.droptext.txtShow.selectAll();
	}
	
	@SuppressWarnings("unchecked")
	public void inputCar(){
		try {
			IEpdCar iEpdCar = new ImpEpdCar();
			EpdPlan epdPlan = (EpdPlan) gridView.getSelection();
			List<EpdCar> cars = iEpdCar.queryByRouteSeq(epdPlan.getRouteSeq());
			List<EpdCar> epdCars = (List<EpdCar>) gridCar.getDataList();
			if (null != cars && cars.size()>0){
				boolean isInput = true;
				for (int i = 0; i < cars.size(); i++) {
					isInput = true;
					if (null != epdCars && epdCars.size()>0){
						for (int j = 0; j < epdCars.size(); j++) {
							if(cars.get(i).getCarNumber().equals(epdCars.get(j).getCarNumber())){
								isInput = false;
								break;
							}
						}
					}
					if(isInput == true){
						gridCar.addRow(cars.get(i));
					}
				}
			}
			IEpdCarinfo iEpdCarinfo = new ImpEpdCarinfo();
			SamParameter parameter = CommFinal.getParamValue(TraffParam.WarningDriverDays);
			String currDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
			epdCars = (List<EpdCar>) gridCar.getDataList();
			for (int j = 0; j < epdCars.size(); j++) {
				epdCars.get(j).setSafecarLimit(epdCars.get(j).getSafecarValid() + CommFinal.getItemName(
						TraffFinal.ARR_SAFECAR_TYPE, epdCars.get(j).getSafecarType()));
				epdCars.get(j).setSafecerLimit(epdCars.get(j).getSafecerValid() + CommFinal.getItemName(
						TraffFinal.ARR_SAFECER_TYPE, epdCars.get(j).getSafecerType()));
				if (DateUtils.nDaysBetweenTwoDate(currDate,epdCars.get(j).getCargradeValid())<0){
					gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
					continue;
				}else if(DateUtils.nDaysBetweenTwoDate(currDate,epdCars.get(j).getCargradeValid())
						<=Integer.valueOf(parameter.getParameterValue())){
					gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
				}
				
				List<EpdCarinfo> carinfos = iEpdCarinfo.queryByCarSeq(epdCars.get(j).getCarSeq());
				if(null != carinfos && carinfos.size()>0){
					for (int k = 0; k < carinfos.size(); k++) {
						if (DateUtils.nDaysBetweenTwoDate(currDate,carinfos.get(k).getEndDate())<0){
							gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_RED));
							break;
						}else if(DateUtils.nDaysBetweenTwoDate(currDate,carinfos.get(k).getEndDate())
								<=Integer.valueOf(parameter.getParameterValue())){
							gridCar.setRowFontColor(j, SWTResourceManager.getColor(SWT.COLOR_BLUE));
						}
					}
				}
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		
		dropCar.droptext.txtShow.forceFocus();
		dropCar.droptext.txtShow.selectAll();
	}

	private List<SamModuleRight> initRight() {
		List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
		SamModuleRight moduleRight = new SamModuleRight();
		moduleRight.setRightName("删除(&R)");
		moduleRight.setRightMethod("deleteCar");
		rights.add(moduleRight);
		return rights;
	}
	
	public void deleteCar(){
		gridCar.deleteRow(gridCar.getSelectionIndexs());
	}

	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				IEpdPlancar iEpdPlancar = new ImpEpdPlancar();
				EpdPlan epdPlan = (EpdPlan) gridView.getSelection();
				List<EpdCar> epdCars = (List<EpdCar>) gridCar.getDataList();
				iEpdPlancar.updateBatch(epdPlan,epdCars,CommFinal.initConfig());
				close();
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
	
}
