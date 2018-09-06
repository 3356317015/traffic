package com.zhima.traffic.ui.operate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.operate.IItsLinerfare;
import com.zhima.traffic.action.operate.impl.ImpItsLinerfare;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerfare;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.grid.GridView;
import com.zhima.widget.stationBar.LinerBar;
import com.zhima.widget.stationBar.LinerFareBar;
import com.zhima.widget.stationBar.Station;

public class ItsLinerPriceUi extends Dialog {
	private GridView gridView;
	private String operType;
	private LinerBar linerBar;
	private LinerFareBar linerFareBar;

	protected ItsLinerPriceUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
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
        return new Point(600,455);
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
		groupMain.setText("运价信息");
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
		
		Composite composite = new Composite(groupMain, SWT.NONE);
		composite.setLayout(new FillLayout());
		data = new FormData();
		data.top = new FormAttachment(linerBar,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		composite.setLayoutData(data);
		
		linerFareBar = new LinerFareBar(composite, null, 0, SWT.BORDER);
		
		initData();
		return compMain;
	}
	
	private void initData(){
		try {
			linerFareBar.forceFocus();
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				ItsLiner itsLiner = (ItsLiner) gridView.getSelection();
				if (null != itsLiner && !"".equals(itsLiner.getLinerSeq())){
					linerBar.txtLinerDate.setText(itsLiner.getLinerDate());
					linerBar.txtLinerId.setText(itsLiner.getLinerId());
					linerBar.txtRouteName.setText(itsLiner.getRouteName());
					IItsLinerfare iItsLinerfare = new ImpItsLinerfare();
					List<ItsLinerfare> itsLinerfares = iItsLinerfare.queryByLinerSeq(itsLiner.getLinerSeq());
					if (null != itsLinerfares && itsLinerfares.size()>0){
						List<Station> stations = new ArrayList<Station>();
						for (int i = 0; i < itsLinerfares.size(); i++) {
							Station station = new Station();
							station.setLinerfareSeq(itsLinerfares.get(i).getLinerfareSeq());
							station.setStationSeq(itsLinerfares.get(i).getStationSeq());
							station.setStationName(itsLinerfares.get(i).getStationName());
							station.setBaseFare(itsLinerfares.get(i).getBaseFare());
							station.setStationFare(itsLinerfares.get(i).getStationFare());
							station.setFuelFare(itsLinerfares.get(i).getFuelFare());
							station.setFullFare(itsLinerfares.get(i).getFullFare());
							stations.add(station);
						}
						linerFareBar.setData(stations);
					}
				}
			}
		} catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				List<ItsLinerfare> itsLinerfares = validData();
				if (null != itsLinerfares && itsLinerfares.size()>0){
					IItsLinerfare iItsLinerfare = new ImpItsLinerfare();
					if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iItsLinerfare.updateAttribute(itsLinerfares,CommFinal.initConfig());
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
	private List<ItsLinerfare> validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		ItsLiner itsLiner = new ItsLiner();
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			itsLiner = (ItsLiner) gridView.getSelection();
		}
		List<ItsLinerfare> itsLinerfares = new ArrayList<ItsLinerfare>();
		List<Station> stations = linerFareBar.getData();
		if (null != stations && stations.size()>0){
			for (int i = 0; i < stations.size(); i++) {
				ItsLinerfare itsLinerfare = new ItsLinerfare();
				itsLinerfare.setLinerfareSeq(stations.get(i).getLinerfareSeq());
				itsLinerfare.setLinerSeq(itsLiner.getLinerSeq());
				itsLinerfare.setLinerDate(itsLiner.getLinerDate());
				itsLinerfare.setLinerId(itsLiner.getLinerId());
				itsLinerfare.setRouteSeq(itsLiner.getRouteSeq());
				itsLinerfare.setStationSeq(stations.get(i).getStationSeq());
				itsLinerfare.setBaseFare(stations.get(i).getBaseFare());
				itsLinerfare.setStationFare(stations.get(i).getStationFare());
				itsLinerfare.setFuelFare(stations.get(i).getFuelFare());
				itsLinerfare.setOtherOne(stations.get(i).getOtherOne());
				itsLinerfare.setOtherTwo(stations.get(i).getOtherTwo());
				itsLinerfare.setOtherThree(stations.get(i).getOtherThree());
				itsLinerfare.setOtherFour(stations.get(i).getOtherFour());
				itsLinerfare.setOtherFive(stations.get(i).getOtherFive());
				itsLinerfare.setFullFare(stations.get(i).getFullFare());
				itsLinerfare.setUpdateTime(currTime);
				itsLinerfares.add(itsLinerfare);
			}
		}
		return itsLinerfares;
	}
}
