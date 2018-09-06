/**
 *******************************************************************************
 *
 * (c) Copyright 2012 重庆市志玛信息技术有限公司
 *
 * 系统名称：frameWork
 * 文  件  名 ：SamModuleUi.java
 * 模块名称：(请更改成该模块名称)
 * 创  建  人 ：鲁承毅
 * 创建日期：2013-3-2 下午10:36:15
 * 修  改  人 ：(修改了该文件，请填上修改人的名字)
 * 修改日期：(请填上修改该文件时的日期)
 * 版         本 ： V1.0.0
 *******************************************************************************  
 */

package com.zhima.traffic.ui.operate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.StyleFinal;
import com.zhima.traffic.drop.DropCar;
import com.zhima.util.SWTResourceManager;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.CCalendar;
import com.zhima.widget.FButton;
import com.zhima.widget.KLabel;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdPlanUi概要说明：运营计划设置
 * @author lcy
 */
public class ItsLinerReportUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	/**
	 * 
	 * 构造函数: 车辆档案
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	public ItsLinerReportUi(Composite parent, BasicPanel panel, int style) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.panel = panel;
		Group gpCar = new Group(this, SWT.NONE);
		gpCar.setText("车辆信息");
		gpCar.setFont(StyleFinal.SYS_FONT);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(25);
		formData.bottom = new FormAttachment(55);
		gpCar.setLayoutData(formData);
		createCar(gpCar);
		
		Group gpLiner = new Group(this, SWT.NONE);
		gpLiner.setText("班次信息");
		gpLiner.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(gpCar,0,SWT.TOP);
		formData.left = new FormAttachment(gpCar,5);
		formData.right = new FormAttachment(50);
		formData.bottom = new FormAttachment(55);
		gpLiner.setLayoutData(formData);
		createLiner(gpLiner);
		
		Group gpCarInfo = new Group(this, SWT.NONE);
		gpCarInfo.setText("资质信息");
		gpCarInfo.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(gpCar,0,SWT.TOP);
		formData.left = new FormAttachment(gpLiner,5);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(55);
		gpCarInfo.setLayoutData(formData);
		ccreatCarInfo(gpCarInfo);
		
		Group gpSafeCar = new Group(this, SWT.NONE);
		gpSafeCar.setText("例检信息");
		gpSafeCar.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(gpCar,5);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(25);
		formData.bottom = new FormAttachment(100);
		gpSafeCar.setLayoutData(formData);
		createSafeCar(gpSafeCar);
		
		Group gpDriver = new Group(this, SWT.NONE);
		gpDriver.setText("驾驶员信息");
		gpDriver.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(gpLiner,5);
		formData.left = new FormAttachment(gpSafeCar,5);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100,-40);
		gpDriver.setLayoutData(formData);
		createDriver(gpDriver);
		
		Label lbReport = new Label(this, SWT.NONE);
		lbReport.setText("报班结果:");
		lbReport.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(gpDriver,10);
		formData.left = new FormAttachment(gpSafeCar,10);
		lbReport.setLayoutData(formData);
		
		Text txtReport = new Text(this, SWT.BORDER|SWT.READ_ONLY);
		txtReport.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(lbReport,-3,SWT.TOP);
		formData.left = new FormAttachment(lbReport,5);
		formData.right = new FormAttachment(100,-200);
		formData.height = 20;
		txtReport.setLayoutData(formData);
		
		FButton btReport = new FButton(this, SWT.NONE);
		btReport.setLayout(new RowLayout());
		btReport.createButton(null, "报班","R", 90);
		btReport.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(txtReport,-4,SWT.TOP);
		formData.left = new FormAttachment(txtReport,0);
		formData.bottom = new FormAttachment(100,-5);
		btReport.setLayoutData(formData);
		
		FButton btBack = new FButton(this, SWT.NONE);
		btBack.setLayout(new RowLayout());
		btBack.createButton(null, "返回","B", 90);
		btBack.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(txtReport,-4,SWT.TOP);
		formData.left = new FormAttachment(btReport,0);
		formData.bottom = new FormAttachment(100,-5);
		btBack.setLayoutData(formData);
		btBack.bindMouseListener(obj, "backMethod");
	}

	private void createDriver(Group gpDriver) {
		gpDriver.setLayout(new FormLayout());
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("驾驶员姓名","routeName",150));
		columns.add(new GridColumn("性别","routeName",70));
		columns.add(new GridColumn("身份证号码","stationName",150));
		columns.add(new GridColumn("驾照类型","stationName",100));
		columns.add(new GridColumn("驾驶证有效期","linerDate",120));
		columns.add(new GridColumn("从业资格证有效期","permitValid",150));
		
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowSeq(false);
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);

		GridView gridView = new GridView(gpDriver, SWT.NONE);
		gridView.CreateTabel(gridConfig);
		
		FormData formData = new FormData();
		formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(0,5);
		formData.right = new FormAttachment(100,-200);
		formData.bottom= new FormAttachment(100,-5);
		gridView.setLayoutData(formData);
		
		CLabel lbImage = new CLabel(gpDriver, SWT.BORDER);
		formData = new FormData();
		formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(gridView,5);
		formData.right = new FormAttachment(100,-5);
		formData.bottom= new FormAttachment(100,-5);
		lbImage.setLayoutData(formData);
	}

	private void ccreatCarInfo(Group gpCarInfo) {
		gpCarInfo.setLayout(new FormLayout());
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("证件名称","routeName",160));
		columns.add(new GridColumn("证件号码","stationName",220));
		columns.add(new GridColumn("有效日期","linerDate",120));
		
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowSeq(false);
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);

		GridView gridView = new GridView(gpCarInfo, SWT.NONE);
		gridView.CreateTabel(gridConfig);
		
		FormData formData = new FormData();
		formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(0,5);
		formData.right = new FormAttachment(100,-5);
		formData.bottom= new FormAttachment(100,-5);
		gridView.setLayoutData(formData);
	}

	private void createCar(Group gpCar) {
		// TODO Auto-generated method stub
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		gpCar.setLayout(gridLayout);
		
		KLabel lbCar = new KLabel(gpCar, SWT.RIGHT);
		lbCar.setText("车牌号码:");
		lbCar.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCar.setLayoutData(gridData);
		
		DropCar dropCar = new DropCar(gpCar, SWT.BORDER);
		dropCar.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		dropCar.setLayoutData(gridData);
		
		Label lbPlateColor = new Label(gpCar, SWT.NONE);
		lbPlateColor.setFont(StyleFinal.SYS_FONT);
		lbPlateColor.setText("车牌颜色:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPlateColor.setLayoutData(gridData);
		
		Text txtPlateColor = new Text(gpCar, SWT.BORDER|SWT.READ_ONLY);
		txtPlateColor.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPlateColor.setLayoutData(gridData);
		
		Label lbRouteName = new Label(gpCar, SWT.NONE);
		lbRouteName.setFont(StyleFinal.SYS_FONT);
		lbRouteName.setText("运营线路:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRouteName.setLayoutData(gridData);
		
		Text txtRouteName = new Text(gpCar, SWT.BORDER|SWT.READ_ONLY);
		txtRouteName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteName.setLayoutData(gridData);
		
		Label lbCargrade = new Label(gpCar, SWT.NONE);
		lbCargrade.setFont(StyleFinal.SYS_FONT);
		lbCargrade.setText("车型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCargrade.setLayoutData(gridData);
		
		Text txtCargrade = new Text(gpCar, SWT.BORDER|SWT.READ_ONLY);
		txtCargrade.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCargrade.setLayoutData(gridData);
		
		Label lbSeatNum = new Label(gpCar, SWT.NONE);
		lbSeatNum.setFont(StyleFinal.SYS_FONT);
		lbSeatNum.setText("座位数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSeatNum.setLayoutData(gridData);
		
		Text txtSeatNum = new Text(gpCar, SWT.BORDER|SWT.READ_ONLY);
		txtSeatNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSeatNum.setLayoutData(gridData);
		
		Label lbQuasiNum = new Label(gpCar, SWT.NONE);
		lbQuasiNum.setFont(StyleFinal.SYS_FONT);
		lbQuasiNum.setText("准座数:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbQuasiNum.setLayoutData(gridData);
		
		Text txtQuasiNum = new Text(gpCar, SWT.BORDER|SWT.READ_ONLY);
		txtQuasiNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtQuasiNum.setLayoutData(gridData);
		
		Label lbBrandModel = new Label(gpCar, SWT.NONE);
		lbBrandModel.setFont(StyleFinal.SYS_FONT);
		lbBrandModel.setText("车辆品牌:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbBrandModel.setLayoutData(gridData);
		
		Text txtBrandModel = new Text(gpCar, SWT.BORDER|SWT.READ_ONLY);
		txtBrandModel.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtBrandModel.setLayoutData(gridData);
		
		Label lbCarLoad = new Label(gpCar, SWT.NONE);
		lbCarLoad.setFont(StyleFinal.SYS_FONT);
		lbCarLoad.setText("车辆载重:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCarLoad.setLayoutData(gridData);
		
		Text txtCarLoad = new Text(gpCar, SWT.BORDER|SWT.READ_ONLY);
		txtCarLoad.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCarLoad.setLayoutData(gridData);
		
		Label lbCarCompany = new Label(gpCar, SWT.NONE|SWT.RIGHT);
		lbCarCompany.setFont(StyleFinal.SYS_FONT);
		lbCarCompany.setText("车属公司:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=80;
		lbCarCompany.setLayoutData(gridData);
		
		Text txtCarCompany = new Text(gpCar, SWT.BORDER|SWT.READ_ONLY);
		txtCarCompany.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCarCompany.setLayoutData(gridData);
	}

	private void createSafeCar(Group gpSafeCar) {
		// TODO Auto-generated method stub
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		gpSafeCar.setLayout(gridLayout);
		
		Label lbUpUser = new Label(gpSafeCar, SWT.NONE);
		lbUpUser.setText("车上安检:");
		lbUpUser.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUpUser.setLayoutData(gridData);
		
		Text txtUpUser = new Text(gpSafeCar, SWT.BORDER|SWT.READ_ONLY);
		txtUpUser.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtUpUser.setLayoutData(gridData);
		
		Label lbDownUser = new Label(gpSafeCar, SWT.NONE);
		lbDownUser.setText("车下安检:");
		lbDownUser.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbDownUser.setLayoutData(gridData);
		
		Text txtDownUser = new Text(gpSafeCar, SWT.BORDER|SWT.READ_ONLY);
		txtDownUser.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtDownUser.setLayoutData(gridData);
		
		Label lbSafeDirver = new Label(gpSafeCar, SWT.NONE);
		lbSafeDirver.setText("驾驶员:");
		lbSafeDirver.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSafeDirver.setLayoutData(gridData);
		
		Text txtSafeDriver = new Text(gpSafeCar, SWT.BORDER|SWT.READ_ONLY);
		txtSafeDriver.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSafeDriver.setLayoutData(gridData);
		
		Label lbOperUser = new Label(gpSafeCar, SWT.NONE);
		lbOperUser.setText("操作员:");
		lbOperUser.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOperUser.setLayoutData(gridData);
		
		Text txtOperUser = new Text(gpSafeCar, SWT.BORDER|SWT.READ_ONLY);
		txtOperUser.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtOperUser.setLayoutData(gridData);
		
		Label lbSafeTime = new Label(gpSafeCar, SWT.NONE);
		lbSafeTime.setText("例检时间:");
		lbSafeTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSafeTime.setLayoutData(gridData);
		
		Text txtSafeTime = new Text(gpSafeCar, SWT.BORDER|SWT.READ_ONLY);
		txtSafeTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSafeTime.setLayoutData(gridData);
		
		Label lbSafeResult = new Label(gpSafeCar, SWT.RIGHT);
		lbSafeResult.setText("例检结果:");
		lbSafeResult.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=80;
		lbSafeResult.setLayoutData(gridData);
		
		CLabel txtSafeResult  = new CLabel(gpSafeCar, SWT.BORDER|SWT.CENTER);
		txtSafeResult.setText("合格");
		txtSafeResult.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		txtSafeResult.setFont(SWTResourceManager.getFont("宋体", 30, 1));
		gridData = new GridData(GridData.FILL_BOTH);
		txtSafeResult.setLayoutData(gridData);
		
	}
	
	private void createLiner(Group gpLiner) {
		// TODO Auto-generated method stub
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		gpLiner.setLayout(gridLayout);
		
		KLabel lbLinerDate = new KLabel(gpLiner, SWT.NONE);
		lbLinerDate.setText("班次日期:");
		lbLinerDate.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerDate.setLayoutData(gridData);
		
		CCalendar dLinerDate = new CCalendar(gpLiner, SWT.BORDER);
		dLinerDate.txtDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dLinerDate.setLayoutData(gridData);
		
		KLabel lbLinerId = new KLabel(gpLiner, SWT.NONE);
		lbLinerId.setText("班次号:");
		lbLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerId.setLayoutData(gridData);
		
		Text txtLinerId = new Text(gpLiner, SWT.BORDER);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(gridData);
		
		Label lbLinerTime = new Label(gpLiner, SWT.NONE);
		lbLinerTime.setText("发车时间:");
		lbLinerTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerTime.setLayoutData(gridData);
		
		Text txtLinerTime = new Text(gpLiner, SWT.BORDER|SWT.READ_ONLY);
		txtLinerTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerTime.setLayoutData(gridData);
		
		Label lbLinerType = new Label(gpLiner, SWT.NONE);
		lbLinerType.setText("班次类型:");
		lbLinerType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerType.setLayoutData(gridData);
		
		Text txtLinerType = new Text(gpLiner, SWT.BORDER|SWT.READ_ONLY);
		txtLinerType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerType.setLayoutData(gridData);
		
		Label lbLinerSeat = new Label(gpLiner, SWT.NONE);
		lbLinerSeat.setText("座位数:");
		lbLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		lbLinerSeat.setLayoutData(gridData);
		
		Text txtLinerSeat = new Text(gpLiner, SWT.BORDER|SWT.READ_ONLY);
		txtLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		txtLinerSeat.setLayoutData(gridData);
		
		Label lbCheckName = new Label(gpLiner, SWT.NONE);
		lbCheckName.setText("检票口:");
		lbCheckName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCheckName.setLayoutData(gridData);
		
		Text txtCheckNum = new Text(gpLiner, SWT.BORDER|SWT.READ_ONLY);
		txtCheckNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCheckNum.setLayoutData(gridData);
		
		Label lbIfDeal = new Label(gpLiner, SWT.NONE);
		lbIfDeal.setText("是否配载:");
		lbIfDeal.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbIfDeal.setLayoutData(gridData);
		
		Text txtIfDeal = new Text(gpLiner, SWT.BORDER|SWT.READ_ONLY);
		txtIfDeal.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIfDeal.setLayoutData(gridData);
		
		Label lbIfMain = new Label(gpLiner, SWT.NONE);
		lbIfMain.setText("始发站:");
		lbIfMain.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbIfMain.setLayoutData(gridData);
		
		Text txtIfMain = new Text(gpLiner, SWT.BORDER|SWT.READ_ONLY);
		txtIfMain.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIfMain.setLayoutData(gridData);
		
		Label lbIfNetsale = new Label(gpLiner, SWT.RIGHT);
		lbIfNetsale.setText("是否网售:");
		lbIfNetsale.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=80;
		lbIfNetsale.setLayoutData(gridData);
		
		Text txtIfNetsale = new Text(gpLiner, SWT.BORDER|SWT.READ_ONLY);
		txtIfNetsale.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIfNetsale.setLayoutData(gridData);
	}
	
	public void backMethod(){
		Composite composite = (Composite) obj;
		composite.setVisible(false);
		panel.setVisible(true);
	}
}
