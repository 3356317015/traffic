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

package com.zhima.traffic.ui.sale;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.drop.DropOrganize;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropCargrade;
import com.zhima.traffic.drop.DropStation;
import com.zhima.traffic.ui.operate.ItsLinerReportUi;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.CCalendar;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdPlanUi概要说明：运营计划设置
 * @author lcy
 */
@SuppressWarnings("unused")
public class SaleTicketUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	private ItsLinerReportUi reportUi;
	
	
	private CCombo cboQueryMode;
	private DropOrganize dropOrganize;
	private DropStation dropStation;
	private Text txtLinerId;
	private DropCargrade dropCargrade;
	private CCombo cboQueryValue;
	private CCalendar dLinerDate;
	private Button btnLimit;
	private CCalendar dLimitDate;
	private Button btnQuery;
	/**
	 * 
	 * 构造函数: 车辆档案
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SaleTicketUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		panel = new BasicPanel(this, SWT.BORDER);
		panel.createPanel();
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		panel.setLayoutData(data);
		
		reportUi = new ItsLinerReportUi(this, panel, SWT.NONE);
		data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		reportUi.setLayoutData(data);
		reportUi.setVisible(false);
		
		createToolbar();
		createInput();
		createDetail();
	}

	private void createInput() {
		panel.input.setLayout(new FormLayout());

		Label lbOrganize = new Label(panel.input, SWT.NONE);
		lbOrganize.setText("乘车站:");
		lbOrganize.setFont(StyleFinal.SYS_FONT);
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 15);
		formData.top= new FormAttachment(0, 9);
		formData.height= 22;
		lbOrganize.setLayoutData(formData);
		
		dropOrganize = new DropOrganize(panel.input, SWT.BORDER);
		dropOrganize.initType("1");
		dropOrganize.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(lbOrganize, 5);
		formData.top= new FormAttachment(0, 5);
		formData.width = 200;
		formData.height = StyleFinal.DROP_HEIGHT;
		dropOrganize.setLayoutData(formData);
		
		Label lbStation = new Label(panel.input, SWT.NONE);
		lbStation.setText("目的地:");
		lbStation.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(dropOrganize, 10);
		formData.top= new FormAttachment(lbOrganize, 0, SWT.TOP);
		formData.height= 22;
		lbStation.setLayoutData(formData);
		
		dropStation = new DropStation(panel.input, SWT.BORDER);
		dropStation.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(lbStation, 5);
		formData.top= new FormAttachment(0, 5);
		formData.width = 180;
		formData.height = StyleFinal.DROP_HEIGHT;
		dropStation.setLayoutData(formData);
		dropStation.setLayoutData(formData);
		
		Label lbLinerDate = new Label(panel.input, SWT.NONE);
		lbLinerDate.setText("班次日期:");
		lbLinerDate.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(dropStation, 10);
		formData.top= new FormAttachment(lbOrganize, 0, SWT.TOP);
		formData.height= 22;
		lbLinerDate.setLayoutData(formData);
		
		dLinerDate = new CCalendar(panel.input, SWT.BORDER);
		dLinerDate.txtDate.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(lbLinerDate, 5);
		formData.top= new FormAttachment(0, 5);
		formData.width = 120;
		formData.height = StyleFinal.DROP_HEIGHT;
		dLinerDate.setLayoutData(formData);
		
	}

	/**
	 * createToolbar方法描述：构建工具面板按钮
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:37:54
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createToolbar(){
		panel.toolbar.setLayout(new RowLayout());
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, panel.toolbar, rights);
	}
	
	/**
	 * createDetail方法描述：构建细部面板表格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createDetail(){
		panel.detail.setLayout(new FormLayout());
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("线路名称","routeName",120));
		columns.add(new GridColumn("途经站","stationName",160));

		columns.add(new GridColumn("车次","linerId",80));
		columns.add(new GridColumn("发车时间","linerTime",80));
		columns.add(new GridColumn("票价","price",80));
		
		columns.add(new GridColumn("车型","cargradeName",90));
		columns.add(new GridColumn("类型","linerType",80));
		
		columns.add(new GridColumn("座位数","seatNum",80));
		columns.add(new GridColumn("余票数","spareNum",80));
		columns.add(new GridColumn("免票数","freeNum",80));
		columns.add(new GridColumn("半票数","halfNum",80));
		
		columns.add(new GridColumn("检票口","checkName",80));
		
		columns.add(new GridColumn("打印座位","ifPrintseat",TraffFinal.ARR_IF_PRINTSEAT,80));
		columns.add(new GridColumn("始发站","ifMain",TraffFinal.ARR_IF_MAIN,80));
		columns.add(new GridColumn("配载","ifDeal",TraffFinal.ARR_IF_DEAL,80));
		
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rights);
		gridConfig.setObj(obj);
		gridView = new GridView(panel.detail, SWT.NONE);
		gridView.CreateTabel(gridConfig);
		gridView.bindMouseDoubleClick(obj,rights,"updateMethod");
		gridView.bindRefresh(obj, "queryPlan");
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		gridView.setLayoutData(data);
	}
	
	
}
