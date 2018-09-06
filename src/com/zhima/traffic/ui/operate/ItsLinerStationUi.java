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
import com.zhima.traffic.action.operate.IItsLinerstation;
import com.zhima.traffic.action.operate.impl.ImpItsLinerstation;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerstation;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.grid.GridView;
import com.zhima.widget.stationBar.LinerBar;
import com.zhima.widget.stationBar.LinerStationBar;
import com.zhima.widget.stationBar.Station;

public class ItsLinerStationUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private LinerBar linerBar;
	private LinerStationBar routeBar;

	protected ItsLinerStationUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("途经站点设置-"+operType);
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
		groupMain.setText("途径站信息");
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
		
		routeBar = new LinerStationBar(composite, null, SWT.BORDER);
		

		initData();

		return compMain;
	}
	
	private void initData(){
		try {
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				ItsLiner itsLiner = (ItsLiner) gridView.getSelection();
				if (null != itsLiner && !"".equals(itsLiner.getLinerSeq())){
					linerBar.txtRouteName.setText(itsLiner.getRouteName());
					linerBar.txtLinerDate.setText(itsLiner.getLinerDate());
					linerBar.txtLinerId.setText(itsLiner.getLinerId());
					IItsLinerstation iItsLinerstation = new ImpItsLinerstation();
					
					List<ItsLinerstation> linerstations = iItsLinerstation.queryByLinerSeq(itsLiner.getLinerSeq());
					List<Station> stations = new ArrayList<Station>();
					if (null != linerstations && linerstations.size()>0){
						for (int i = 0; i < linerstations.size(); i++) {
							Station routeStation = new Station();
							routeStation.setStationSeq(linerstations.get(i).getLinerstationSeq());
							routeStation.setStationName(linerstations.get(i).getStationName());
							routeStation.setStationMileage(linerstations.get(i).getStationMileage());
							routeStation.setIfSale(linerstations.get(i).getIfSale());
							routeStation.setStationNum(linerstations.get(i).getStationNum());
							routeStation.setSaleNum(linerstations.get(i).getSaleNum());
							routeStation.setStationOrder(linerstations.get(i).getStationOrder());
							stations.add(routeStation);
						}
					}
					routeBar.setData(stations);
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
				List<ItsLinerstation> itsLinerstations = validData();
				if (null != itsLinerstations){
					IItsLinerstation iItsLinerstation = new ImpItsLinerstation();
					if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iItsLinerstation.updateAttribute(itsLinerstations, CommFinal.initConfig());
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
	private List<ItsLinerstation> validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		List<ItsLinerstation> linerstations = new ArrayList<ItsLinerstation>();
		List<Station> stations = routeBar.getData();
		if (null != stations && stations.size()>0){
			for (int i = 0; i < stations.size(); i++) {
				ItsLinerstation linerstation = new ItsLinerstation();
				linerstation.setLinerstationSeq(stations.get(i).getStationSeq());
				linerstation.setIfSale(stations.get(i).getIfSale());
				linerstation.setStationNum(stations.get(i).getStationNum());
				linerstation.setUpdateTime(currTime);
				linerstations.add(linerstation);
			}
		}
		
		return linerstations;
	}
}
