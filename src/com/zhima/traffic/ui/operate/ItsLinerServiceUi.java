package com.zhima.traffic.ui.operate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
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
import com.zhima.frame.drop.DropService;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamService;
import com.zhima.traffic.action.operate.IItsLinerservice;
import com.zhima.traffic.action.operate.impl.ImpItsLinerservice;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerservice;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;
import com.zhima.widget.stationBar.LinerBar;

public class ItsLinerServiceUi extends Dialog {
	private GridView gridView;
	private String operType;
	private Object obj;
	
	private LinerBar linerBar;
	private DropService dropService;
	private GridView gridService;

	protected ItsLinerServiceUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
		this.obj = this;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("乘车点设置-"+operType);
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
		groupMain.setText("乘车点信息");
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
		
		Label lbService = new Label(groupMain, SWT.RIGHT);
		lbService.setFont(StyleFinal.SYS_FONT);
		lbService.setText("乘车点:");
		data = new FormData();
		data.top = new FormAttachment(linerBar, 8);
		data.left = new FormAttachment(0, 23);
		lbService.setLayoutData(data);
		
		dropService = new DropService(groupMain, SWT.BORDER);
		dropService.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(linerBar, 5);
		data.left = new FormAttachment(lbService, 5);
		data.width = 150;
		dropService.setLayoutData(data);
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("乘车点代码", "serviceCode",120));
		columns.add(new GridColumn("乘车点名称", "serviceName",120));
		columns.add(new GridColumn("启用", "ifUsing", TraffFinal.ARR_IF_USING,70));
		columns.add(new GridColumn("已售数量", "saleNum",90));
		columns.add(new GridColumn("检票数量", "checkNum",90));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		
		gridConfig.setRightBos(initService());
		gridConfig.setObj(obj);
		gridService = new GridView(groupMain, SWT.BORDER);
		gridService.CreateTabel(gridConfig);
		data = new FormData();
		data.top = new FormAttachment(lbService,10);
		data.left = new FormAttachment(0,5);
		data.bottom = new FormAttachment(100,-5);
		data.right = new FormAttachment(100,-5);
		gridService.setLayoutData(data);
		initData();
		return compMain;
	}
	
	private List<SamModuleRight> initService() {
		List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
		SamModuleRight moduleRight = new SamModuleRight();
		moduleRight.setRightName("启用(&Q)");
		moduleRight.setRightMethod("enableService");
		rights.add(moduleRight);
		
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("停用(&S)");
		moduleRight.setRightMethod("disableService");
		rights.add(moduleRight);
		
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("上移(&U)");
		moduleRight.setRightMethod("upService");
		rights.add(moduleRight);
		
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("下移(&D)");
		moduleRight.setRightMethod("downService");
		rights.add(moduleRight);
		
		moduleRight = new SamModuleRight();
		moduleRight.setRightName("删除(&R)");
		moduleRight.setRightMethod("deleteService");
		rights.add(moduleRight);
		return rights;
	}
	
	@SuppressWarnings("unchecked")
	private void initData(){
		try {
			dropService.initType("1");
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				ItsLiner itsLiner = (ItsLiner) gridView.getSelection();
				if (null != itsLiner && !"".equals(itsLiner.getLinerSeq())){
					linerBar.txtLinerDate.setText(itsLiner.getLinerDate());
					linerBar.txtLinerId.setText(itsLiner.getLinerId());
					linerBar.txtRouteName.setText(itsLiner.getRouteName());
					
					//初始化乘车点
					IItsLinerservice iItsLinerservice = new ImpItsLinerservice();
					List<ItsLinerservice> linerservices = iItsLinerservice.queryByLinerSeq(itsLiner.getLinerSeq());
					List<SamService> samServices = (List<SamService>) dropService.droptext.getDataList();
					if (null != linerservices && linerservices.size()>0){
						for (int i = 0; i < linerservices.size(); i++) {
							for (int j = 0; j < samServices.size(); j++) {
								if (linerservices.get(i).getServiceSeq().equals(samServices.get(j).getServiceSeq())){
									samServices.get(j).setIfUsing(linerservices.get(i).getIfUsing());
									samServices.get(j).setSaleNum(linerservices.get(i).getSaleNum());
									samServices.get(j).setCheckNum(linerservices.get(i).getCheckNum());
									gridService.addRow(samServices.get(j));
									break;
								}
							}
						}
					}
				}
			}
			dropService.droptext.txtShow.forceFocus();
			dropService.droptext.txtShow.selectAll();
			CallMethod callMethod = new CallMethod();
			callMethod.bindEnterEvent(this, dropService.droptext.txtShow, null, "addService");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	public void addService(){
		if (null != dropService.droptext.getValue() && dropService.droptext.getValue().length()>0){
			try {
				List<SamService> samServices = (List<SamService>) gridService.getDataList();
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						if (dropService.droptext.getValue().equals(samServices.get(i).getServiceSeq())){
							MsgBox.warning(getShell(), "乘车点已存在，不允许重复添加。");
							return;
						}
					}
				}
				
				SamService service = (SamService) dropService.droptext.getObject();
				service.setIfUsing("1");
				service.setSaleNum(0);
				service.setCheckNum(0);
				gridService.addRow(service);
			} catch (Exception e) {
				LogUtil.operLog(e,"E",true,true);
			}
		}
		dropService.droptext.txtShow.forceFocus();
		dropService.droptext.txtShow.selectAll();
	}
	
	@SuppressWarnings("unchecked")
	public void enableService(){
		try {
			int index[] = gridService.getSelectionIndexs();
			if (null != index && index.length>0){
				List<SamService> samServices = (List<SamService>) gridService.getSelections();
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						samServices.get(i).setIfUsing("1");
					}
					gridService.updateRows(index, samServices);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void disableService(){
		try {
			int index[] = gridService.getSelectionIndexs();
			if (null != index && index.length>0){
				List<SamService> samServices = (List<SamService>) gridService.getSelections();
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						samServices.get(i).setIfUsing("0");
					}
					gridService.updateRows(index, samServices);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void upService(){
		gridService.upTableRow();
	}
	
	public void downService(){
		gridService.downTableRow();
	}
	
	public void deleteService(){
		int index[] = gridService.getSelectionIndexs();
		if (null != index && index.length>0){
			gridService.deleteRow(index);
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
				
				itsLiner.setUsingService(0);
				List<ItsLinerservice> linerservices = new ArrayList<ItsLinerservice>();
				List<SamService> samServices = (List<SamService>) gridService.getDataList();
				if (null != samServices && samServices.size()>=0){
					itsLiner.setUsingService(1);
					for (int i = 0; i < samServices.size(); i++) {
						ItsLinerservice itsLinerservice = new ItsLinerservice();
						itsLinerservice.setLinerSeq(itsLiner.getLinerSeq());
						itsLinerservice.setLinerDate(itsLiner.getLinerDate());
						itsLinerservice.setLinerId(itsLiner.getLinerId());
						itsLinerservice.setRouteSeq(itsLiner.getRouteSeq());
						itsLinerservice.setRouteName(itsLiner.getRouteName());
						itsLinerservice.setServiceSeq(samServices.get(i).getServiceSeq());
						itsLinerservice.setServiceName(samServices.get(i).getServiceName());
						itsLinerservice.setServiceOrder(i+1);
						itsLinerservice.setIfUsing(samServices.get(i).getIfUsing());
						itsLinerservice.setUpdateTime(itsLiner.getUpdateTime());
						linerservices.add(itsLinerservice);
					}
				}
				IItsLinerservice iItsLinerservice = new ImpItsLinerservice();
				iItsLinerservice.updateAttribute(itsLiner, linerservices, CommFinal.initConfig());
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
