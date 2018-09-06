package com.zhima.traffic.ui.voice;

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
import com.zhima.traffic.action.voice.IVmsParameter;
import com.zhima.traffic.action.voice.IVmsRoute;
import com.zhima.traffic.action.voice.impl.ImpVmsParameter;
import com.zhima.traffic.action.voice.impl.ImpVmsRoute;
import com.zhima.traffic.model.VmsParameter;
import com.zhima.traffic.model.VmsRoute;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class VmsRouteAddUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private GridView gridRoute;

	protected VmsRouteAddUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("播音线路设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(635,415);
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
		groupMain.setText("线路信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		groupMain.setLayout(new FillLayout());
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("线路名称", "routeName",150));
		columns.add(new GridColumn("线路代码", "routeCode",100));
		columns.add(new GridColumn("途径站", "stationName",250));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(true);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridRoute = new GridView(groupMain, SWT.BORDER);
		gridRoute.CreateTabel(gridConfig);
		initData();
		return compMain;
	}
	
	private void initData(){
		try {
			IVmsParameter iVmsParameter = new ImpVmsParameter();
			IVmsRoute iVmsRoute = new ImpVmsRoute();
			List<VmsParameter> vmsParameters = iVmsParameter.queryByParameterCode(CommFinal.organize.getOrganizeSeq(),
					"route_source");
			if (null != vmsParameters && vmsParameters.size()>0){
				if (null != vmsParameters.get(0).getParameterValue() && vmsParameters.get(0).getParameterValue().length()>0){
					List<VmsRoute> vmsRoutes = iVmsRoute.queryTrafficRoute(vmsParameters.get(0).getParameterValue());
					List<VmsRoute> routes = iVmsRoute.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
					if (null != vmsRoutes && vmsRoutes.size()>0){
						for (int i = vmsRoutes.size()-1; i >=0; i--) {
							for (int j = 0; j < routes.size(); j++) {
								if (vmsRoutes.get(i).getRouteCode().equals(routes.get(j).getRouteCode())){
									vmsRoutes.remove(i);
									break;
								}
							}
						}
						gridRoute.setDataList(vmsRoutes);
					}
					
				}else{
					MsgBox.warning(getShell(), "播音线路数据源未设置!");
				}
			}else{
				MsgBox.error(getShell(), "获取播单线路数据源失败!");
			}
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
				String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
				List<VmsRoute> vmsRoutes = (List<VmsRoute>) gridRoute.getCheckSelections();
				if (null != vmsRoutes && vmsRoutes.size()>0){
					for (int i = 0; i < vmsRoutes.size(); i++) {
						vmsRoutes.get(i).setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
						vmsRoutes.get(i).setVoiceStatus("1");
						vmsRoutes.get(i).setUpdateTime(currTime);
						
					}
					IVmsRoute iVmsRoute = new ImpVmsRoute();
					List<VmsRoute> routes = iVmsRoute.inserts(vmsRoutes, CommFinal.initConfig());
					gridView.addRows(routes);
				}
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
