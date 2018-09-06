package com.zhima.traffic.ui.epd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zhima.frame.action.sam.ISamVariables;
import com.zhima.frame.action.sam.impl.ImpSamVariables;
import com.zhima.frame.model.SamVariables;
import com.zhima.traffic.action.epd.IEpdCargrade;
import com.zhima.traffic.action.epd.IEpdFare;
import com.zhima.traffic.action.epd.IEpdPlan;
import com.zhima.traffic.action.epd.IEpdRoute;
import com.zhima.traffic.action.epd.IEpdRouteStation;
import com.zhima.traffic.action.epd.impl.ImpEpdCargrade;
import com.zhima.traffic.action.epd.impl.ImpEpdFare;
import com.zhima.traffic.action.epd.impl.ImpEpdPlan;
import com.zhima.traffic.action.epd.impl.ImpEpdRoute;
import com.zhima.traffic.action.epd.impl.ImpEpdRouteStation;
import com.zhima.traffic.drop.DropRoute;
import com.zhima.traffic.model.EpdCargrade;
import com.zhima.traffic.model.EpdFare;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.traffic.model.EpdRoutestation;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.EditorOption;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class EpdFareEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private DropRoute dropRoute;
	private Button btnUp;
	private Button btnDown;
	private GridView gridRoutestation;
	private GridView gridCargrade;
	private GridView gridFare;
	private Object obj;

	protected EpdFareEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
		this.obj = this;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("运价设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(800,535);
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
		try {
			CallMethod callMethod = new CallMethod();
			
			Group groupMain = new Group(compMain,SWT.NONE);
			groupMain.setText("运价信息");
			groupMain.setFont(StyleFinal.SYS_FONT);
			groupMain.setLayout(new FormLayout());
			FormData data = new FormData();
			data.top = new FormAttachment(0,5);
			data.left = new FormAttachment(0,5);
			data.right = new FormAttachment(100,-5);
			data.bottom = new FormAttachment(100);
			groupMain.setLayoutData(data);
			
			Label lbRoute = new Label(groupMain, SWT.RIGHT);
			lbRoute.setFont(StyleFinal.SYS_FONT);
			lbRoute.setText("线路名称:");
			data = new FormData();
			data.top = new FormAttachment(0,5);
			data.left = new FormAttachment(0,5);
			lbRoute.setLayoutData(data);
			
			dropRoute = new DropRoute(groupMain, obj, "queryRoutestation", "2", SWT.BORDER);
			dropRoute.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
			data = new FormData();
			data.top = new FormAttachment(lbRoute,-3,SWT.TOP);
			data.left = new FormAttachment(lbRoute,5);
			data.height = StyleFinal.DROP_HEIGHT;
			data.width = 160;
			dropRoute.setLayoutData(data);
			
			btnUp = new Button(groupMain, SWT.NONE);
			btnUp.setText("上一步");
			btnUp.setFont(StyleFinal.BTN_FONT);
			data = new FormData();
			data.top = new FormAttachment(dropRoute,-2,SWT.TOP);
			data.right = new FormAttachment(100,-80);
			data.height = 25;
			data.width = 60;
			btnUp.setLayoutData(data);
			btnUp.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDown(MouseEvent arg0) {
					// TODO Auto-generated method stub
					if (gridCargrade.getVisible()){
						if (CommFinal.OPER_TYPE_ADD.equals(operType)){
							dropRoute.droptext.setEnabled(true);
						}
						gridRoutestation.setVisible(true);
						gridCargrade.setVisible(false);
						gridFare.setVisible(false);
						btnUp.setEnabled(false);
						btnDown.setEnabled(true);
					}else if (gridFare.getVisible()){
						gridRoutestation.setVisible(false);
						gridCargrade.setVisible(true);
						gridFare.setVisible(false);
						btnUp.setEnabled(true);
						btnDown.setEnabled(true);
					}
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			btnDown = new Button(groupMain, SWT.NONE);
			btnDown.setText("下一步");
			btnDown.setFont(StyleFinal.BTN_FONT);
			data = new FormData();
			data.top = new FormAttachment(dropRoute,-2,SWT.TOP);
			data.left = new FormAttachment(btnUp,10);
			data.height = 25;
			data.width = 60;
			btnDown.setLayoutData(data);
			btnDown.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@SuppressWarnings("unchecked")
				@Override
				public void mouseDown(MouseEvent arg0) {
					// TODO Auto-generated method stub
					try {
						if (gridRoutestation.getVisible()){
							List<EpdRoutestation> routestations = (List<EpdRoutestation>) gridRoutestation.getCheckSelections();
							if (null == routestations || routestations.size()<=0){
								MsgBox.warning(getShell(), "请选择线路站点。");
								return;
							}
							dropRoute.droptext.setEnabled(false);
							gridRoutestation.setVisible(false);
							gridCargrade.setVisible(true);
							gridFare.setVisible(false);
							btnUp.setEnabled(true);
							btnDown.setEnabled(true);
						}else if (gridCargrade.getVisible()){
							List<EpdCargrade> cargrades = (List<EpdCargrade>) gridCargrade.getCheckSelections();
							if (null == cargrades || cargrades.size()<=0){
								MsgBox.warning(getShell(), "请选择车型。");
								return;
							}
							
							List<EpdCargrade> epdCargrades = (List<EpdCargrade>) gridCargrade.getDataList();
							IEpdPlan iEpdPlan = new ImpEpdPlan();
							List<EpdPlan> epdPlans = iEpdPlan.queryByRouteSeq(dropRoute.droptext.getValue());
							if (null != epdPlans && epdPlans.size()>0){
								for (int i = 0; i < epdCargrades.size(); i++) {
									for (int j = 0; j < epdPlans.size(); j++) {
										if(epdCargrades.get(i).getCargradeSeq().equals(epdPlans.get(j).getCargradeSeq())){
											boolean isWarning = true;
											if (null != cargrades && cargrades.size()>0){
												for (int k = 0; k < cargrades.size(); k++) {
													if (epdCargrades.get(i).getCargradeSeq().equals(cargrades.get(k).getCargradeSeq())){
														isWarning = false;
													}
												}
											}
											if (isWarning == true){
												MsgBox.warning(getShell(), epdCargrades.get(i).getCargradeName()+"存在运营计划，必须设置运价。");
												return;
											}
										}
									}
								}
							}
							List<EpdFare> fares = (List<EpdFare>) gridFare.getDataList();
							List<EpdRoutestation> routestations = (List<EpdRoutestation>) gridRoutestation.getCheckSelections();
							boolean isNew = true;
							List<EpdFare> epdFares = new ArrayList<EpdFare>();
							for (int i = 0; i < cargrades.size(); i++) {
								for (int j = 0; j < routestations.size(); j++) {
									EpdFare epdFare = new EpdFare();
									isNew = true;
									if (null != fares && fares.size()>0){
										for (int k = 0; k < fares.size(); k++) {
											if (routestations.get(j).getRouteSeq().equals(fares.get(k).getRouteSeq())
													&& cargrades.get(i).getCargradeSeq().equals(fares.get(k).getCargradeSeq())
													&& routestations.get(j).getStationSeq().equals(fares.get(k).getStationSeq())){
												BeanUtils.copyProperties(epdFare, fares.get(k));
												isNew = false;
												break;
											}
										}
									}
									if (isNew==true){
										epdFare.setRouteSeq(routestations.get(j).getRouteSeq());
										epdFare.setStationSeq(routestations.get(j).getStationSeq());
										epdFare.setStationName(routestations.get(j).getStationName());
										epdFare.setCargradeSeq(cargrades.get(i).getCargradeSeq());
										epdFare.setCargradeName(cargrades.get(i).getCargradeName());
									}
									epdFares.add(epdFare);
								}
							}
							gridFare.setDataList(epdFares);
							gridRoutestation.setVisible(false);
							gridCargrade.setVisible(false);
							gridFare.setVisible(true);
							btnUp.setEnabled(true);
							btnDown.setEnabled(false);
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						LogUtil.operLog(e,"E",true,true);
					}
					
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});

			List<GridColumn> columns=new ArrayList<GridColumn>();
			columns.add(new GridColumn("站点名称", "stationName",150));
			columns.add(new GridColumn("里程", "stationMileage",150));
			columns.add(new GridColumn("行政区域", "regionName",150));
			GridConfig gridConfig = new GridConfig();
			gridConfig.setCheck(true);
			gridConfig.setShowPage(false);
			gridConfig.setColumns(columns);
			gridRoutestation = new GridView(groupMain, SWT.BORDER);
			gridRoutestation.CreateTabel(gridConfig);
			data = new FormData();
			data.top = new FormAttachment(lbRoute,10);
			data.left = new FormAttachment(0,5);
			data.bottom = new FormAttachment(100,-5);
			data.right = new FormAttachment(100,-5);
			gridRoutestation.setLayoutData(data);
			
			columns=new ArrayList<GridColumn>();
			columns.add(new GridColumn("车型代码", "cargradeCode",150));
			columns.add(new GridColumn("车型名称", "cargradeName",150));
			gridConfig = new GridConfig();
			gridConfig.setCheck(true);
			gridConfig.setShowPage(false);
			gridConfig.setColumns(columns);
			gridCargrade = new GridView(groupMain, SWT.BORDER);
			gridCargrade.CreateTabel(gridConfig);
			data = new FormData();
			data.top = new FormAttachment(lbRoute,10);
			data.left = new FormAttachment(0,5);
			data.bottom = new FormAttachment(100,-5);
			data.right = new FormAttachment(100,-5);
			gridCargrade.setLayoutData(data);
			
			columns=new ArrayList<GridColumn>();
			columns.add(new GridColumn("车型名称", "cargradeName",135));
			columns.add(new GridColumn("站点名称", "stationName",135));
			columns.add(new GridColumn("总价", "fullFares","[baseFare]+[stationFare]+[fuelFare]"));
			EditorOption editorOption = new EditorOption();
			editorOption.setVerify(true);
			columns.add(new GridColumn("基础价", "baseFare",100, "Text",editorOption));
			columns.add(new GridColumn("站务费", "stationFare",100, "Text",editorOption));
			columns.add(new GridColumn("燃油附加费", "fuelFare",130, "Text",editorOption));
			ISamVariables iSamVariables = new ImpSamVariables();
			List<SamVariables> variables = iSamVariables.queryByVariableType(CommFinal.organize.getOrganizeSeq(),"1");
			if (null != variables && variables.size()>0){
				for (int i = 0; i < variables.size(); i++) {
					if ("1".equals(variables.get(i).getStatus())){
						if ("otherOne".equals(variables.get(i).getVariableCode())){
							columns.add(new GridColumn(variables.get(i).getVariableName(), "ohterOne",90, "Text",editorOption));
							continue;
						}else if ("otherTwo".equals(variables.get(i).getVariableCode())){
							columns.add(new GridColumn(variables.get(i).getVariableName(), "ohterTwo",90, "Text",editorOption));
							continue;
						}else if ("otherThree".equals(variables.get(i).getVariableCode())){
							columns.add(new GridColumn(variables.get(i).getVariableName(), "ohterThree",90, "Text",editorOption));
							continue;
						}else if ("otherFour".equals(variables.get(i).getVariableCode())){
							columns.add(new GridColumn(variables.get(i).getVariableName(), "ohterFour",90, "Text",editorOption));
							continue;
						}else if ("otherFive".equals(variables.get(i).getVariableCode())){
							columns.add(new GridColumn(variables.get(i).getVariableName(), "ohterFive",90, "Text",editorOption));
							continue;
						}
					}
				}
			}

			gridConfig = new GridConfig();
			gridConfig.setCheck(false);
			gridConfig.setShowPage(false);
			gridConfig.setColumns(columns);
			gridFare = new GridView(groupMain, SWT.BORDER);
			gridFare.CreateTabel(gridConfig);
			data = new FormData();
			data.top = new FormAttachment(lbRoute,10);
			data.left = new FormAttachment(0,5);
			data.bottom = new FormAttachment(100,-5);
			data.right = new FormAttachment(100,-5);
			gridFare.setLayoutData(data);
			
			initData();
			dropRoute.droptext.txtShow.forceFocus();
			dropRoute.droptext.txtShow.selectAll();

			callMethod.bindEnterEvent(this, dropRoute.droptext.txtShow, null, "queryRoutestation");
			
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		return compMain;
	}
	
	@SuppressWarnings("unchecked")
	private void initData(){
		try {
			IEpdCargrade iEpdCargrade = new ImpEpdCargrade();
			List<EpdCargrade> cargrades = iEpdCargrade.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			gridCargrade.setDataList(cargrades);
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				gridRoutestation.setVisible(false);
				gridCargrade.setVisible(false);
				gridFare.setVisible(true);
				dropRoute.droptext.setEnabled(false);
				IEpdRoute iEpdRoute = new ImpEpdRoute();
				dropRoute.droptext.setDataList(iEpdRoute.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq()));
				EpdRoute epdRoute = (EpdRoute) gridView.getSelection();
				dropRoute.droptext.setValue(epdRoute.getRouteSeq());
				queryRoutestation();
				btnUp.setEnabled(true);
				btnDown.setEnabled(false);
				IEpdFare iEpdFare = new ImpEpdFare();
				List<EpdFare> fares = iEpdFare.queryByRouteSeq(epdRoute.getRouteSeq());
				if (null != fares && fares.size()>0){
					List<EpdRoutestation> routestations = (List<EpdRoutestation>) gridRoutestation.getDataList();
					if (null != routestations && routestations.size()>0){
						for (int i = 0; i < routestations.size(); i++) {
							for (int j = 0; j < fares.size(); j++) {
								if (routestations.get(i).getStationSeq().equals(fares.get(j).getStationSeq())){
									gridRoutestation.setCheck(i, true);
									break;
								}
							}
						}
					}
					if (null != cargrades && cargrades.size()>0){
						for (int i = 0; i < cargrades.size(); i++) {
							for (int j = 0; j < fares.size(); j++) {
								if (cargrades.get(i).getCargradeSeq().equals(fares.get(j).getCargradeSeq())){
									gridCargrade.setCheck(i, true);
									break;
								}
							}
						}
					}
					gridFare.setDataList(fares);
				}else{
					gridRoutestation.setVisible(true);
					gridCargrade.setVisible(false);
					gridFare.setVisible(false);
					btnUp.setEnabled(false);
					btnDown.setEnabled(true);
				}
			}else{
				gridRoutestation.setVisible(true);
				gridCargrade.setVisible(false);
				gridFare.setVisible(false);
				btnUp.setEnabled(false);
				btnDown.setEnabled(true);
				
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void queryRoutestation(){
		try {
			IEpdRouteStation iEpdRouteStation = new ImpEpdRouteStation();
			List<EpdRoutestation> routestations = iEpdRouteStation.queryByRouteSeq(dropRoute.droptext.getValue());
			gridRoutestation.setDataList(routestations);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}

	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				List<EpdFare> epdFares = (List<EpdFare>) gridFare.getDataList();
				if (null != epdFares && epdFares.size()>0){
					String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
					Double fullFare =0.00;
					for (int i = 0; i < epdFares.size(); i++) {
						epdFares.get(i).setFareSeq("");
						epdFares.get(i).setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
						fullFare =0.00;
						fullFare += epdFares.get(i).getBaseFare();
						fullFare += epdFares.get(i).getStationFare();
						fullFare += epdFares.get(i).getFuelFare();
						ISamVariables iSamVariables = new ImpSamVariables();
						List<SamVariables> variables = iSamVariables.queryByVariableType(CommFinal.organize.getOrganizeSeq(),"1");
						if (null != variables && variables.size()>0){
							for (int j = 0; j < variables.size(); j++) {
								if ("1".equals(variables.get(j).getStatus())){
									if ("otherOne".equals(variables.get(j).getVariableCode())){
										fullFare += epdFares.get(i).getOtherOne();
										continue;
									}else if ("otherTwo".equals(variables.get(j).getVariableCode())){
										fullFare += epdFares.get(i).getOtherTwo();
										continue;
									}else if ("otherThree".equals(variables.get(j).getVariableCode())){
										fullFare += epdFares.get(i).getOtherThree();
										continue;
									}else if ("otherFour".equals(variables.get(j).getVariableCode())){
										fullFare += epdFares.get(i).getOtherFour();
										continue;
									}else if ("otherFive".equals(variables.get(j).getVariableCode())){
										fullFare += epdFares.get(i).getOtherFive();
										continue;
									}
								}
							}
						}
						
						epdFares.get(i).setFullFare(fullFare);
						epdFares.get(i).setUpdateTime(currTime);
						BigDecimal data1 = new BigDecimal(fullFare);  
						BigDecimal data2 = new BigDecimal(0.00);  
						if (data1.compareTo(data2) <=0){
							MsgBox.warning(getShell(), "第[" + Integer.valueOf(i+1) + "]行未设置价格 。");
							return;
						}
					}
					EpdRoute epdRoute = (EpdRoute) gridView.getSelection();
					IEpdFare iEpdFare = new ImpEpdFare();
					iEpdFare.updateBatch(epdRoute.getRouteSeq(), epdFares,CommFinal.initConfig());
					close();
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
}
