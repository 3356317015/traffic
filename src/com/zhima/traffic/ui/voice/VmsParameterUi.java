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

package com.zhima.traffic.ui.voice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.voice.IVmsParameter;
import com.zhima.traffic.action.voice.IVmsTemplate;
import com.zhima.traffic.action.voice.impl.ImpVmsParameter;
import com.zhima.traffic.action.voice.impl.ImpVmsTemplate;
import com.zhima.traffic.model.VmsParameter;
import com.zhima.traffic.model.VmsTemplate;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.StringUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.util.xml.ReadXml;
import com.zhima.util.xml.UpdateXml;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * SamVariablesUi概要说明：系统变量设置
 * @author lcy
 */
public class VmsParameterUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	private CTabFolder tabFolder;
	//站务对接设置
	private CCombo cboConnType;	//数据库连接类型
	private Text txtConnServer;	//连接服务地址
	private Text txtConnPort;	//连接端口
	private Text txtDbName;		//数据库名
	private Text txtUser;		//用户
	private Text txtPassword;	//密码
	
	private Text txtMaxConn;	//最大连接数
	private Text txtMinConn;	//最小连接数
	private Text txtActive;		//可用连接数
	
	private Text txtActieTime;	//连接活动时间
	private Text txtSleepTime;	//连接睡眠时间
	private Text txtLifeTime;	//连接寿命时间
	
	private Text txtRouteSource;	//站务线路信息
	private Text txtLinerSource;	//站务班次信息
	
	private GridView gridVoice;
	private GridView gridTemplate;
	private GridView gridCause;
	private GridView gridLiner;
	private Button btnEdit;
	private Button btnSave;

	/**
	 * 
	 * 构造函数:系统变量构告面板
	 * @param parent
	 * @param style
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VmsParameterUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		panel = new BasicPanel(this, SWT.BORDER);
		panel.setInput(false);
		panel.createPanel();
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		panel.setLayoutData(data);
		createToolbar();
		createDetail();
		refreshMethod();
		initContrl(false);
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
		
		tabFolder = new CTabFolder(panel.detail, SWT.BORDER);
		tabFolder.setFont(StyleFinal.SYS_FONT);
		tabFolder.setBorderVisible(true);
		tabFolder.setSimple(false);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		tabFolder.setLayoutData(data);
		tabFolder.setSelectionForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		tabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		CTabItem tabSys = new CTabItem(tabFolder, SWT.BORDER);
		tabSys.setText("站务数据源");
		tabSys.setControl(createSys(tabFolder));
		
		CTabItem tabVoice = new CTabItem(tabFolder, SWT.BORDER);
		tabVoice.setText("语音参数");
		tabVoice.setControl(createVoice(tabFolder));
		
		CTabItem tabTemplate = new CTabItem(tabFolder, SWT.BORDER);
		tabTemplate.setText("播音模板");
		tabTemplate.setControl(createTemplate(tabFolder));
		
		CTabItem tabCause = new CTabItem(tabFolder, SWT.BORDER);
		tabCause.setText("异常模板");
		tabCause.setControl(createCause(tabFolder));
		
		CTabItem tabPlay = new CTabItem(tabFolder, SWT.BORDER);
		tabPlay.setText("班次参数");
		tabPlay.setControl(createPlay(tabFolder));
		tabFolder.setSelection(0);
	}
	
	private Composite createSys(Composite detail) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(detail, SWT.NONE);
		composite.setLayout(new FormLayout());
		//composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		Group groupConn = new Group(composite, SWT.NONE);
		groupConn.setText("站务连接信息");
		groupConn.setFont(StyleFinal.SYS_FONT);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(50);
		groupConn.setLayoutData(data);
		GridLayout gridLayout = new GridLayout(6,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupConn.setLayout(gridLayout);
		
		//连接类型
		Label lbConnType = new Label(groupConn, SWT.NONE);
		lbConnType.setAlignment(SWT.RIGHT);
		lbConnType.setText("数据库类型：");
		lbConnType.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbConnType.setLayoutData(gridData);
		
		cboConnType = new CCombo(groupConn, SWT.BORDER | SWT.READ_ONLY);
		cboConnType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=5;
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboConnType.setLayoutData(gridData);
		
		Label lbConnServer = new Label(groupConn, SWT.NONE);
		lbConnServer.setAlignment(SWT.RIGHT);
		lbConnServer.setText("服务器地址：");
		lbConnServer.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbConnServer.setLayoutData(gridData);
		
		txtConnServer = new Text(groupConn, SWT.BORDER);
		txtConnServer.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtConnServer.setLayoutData(gridData);
		
		Label lbConnPort = new Label(groupConn, SWT.NONE);
		lbConnPort.setText("端口：");
		lbConnPort.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbConnPort.setLayoutData(gridData);
		
		txtConnPort = new Text(groupConn, SWT.BORDER);
		txtConnPort.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtConnPort.setLayoutData(gridData);
		
		Label lbDbName = new Label(groupConn, SWT.NONE);
		lbDbName.setAlignment(SWT.RIGHT);
		lbDbName.setText("数据库名：");
		lbDbName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbDbName.setLayoutData(gridData);
		
		txtDbName = new Text(groupConn, SWT.BORDER);
		txtDbName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtDbName.setLayoutData(gridData);
		
		Label lbUser = new Label(groupConn, SWT.NONE);
		lbUser.setAlignment(SWT.RIGHT);
		lbUser.setText("用户：");
		lbUser.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUser.setLayoutData(gridData);
		
		txtUser = new Text(groupConn, SWT.BORDER);
		txtUser.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtUser.setLayoutData(gridData);
		
		Label lbPassword = new Label(groupConn, SWT.NONE);
		lbPassword.setText("密码：");
		lbPassword.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPassword.setLayoutData(gridData);
		
		txtPassword = new Text(groupConn, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPassword.setLayoutData(gridData);
		
		Label label = new Label(groupConn, SWT.NONE);
		label.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=2;
		label.setLayoutData(gridData);
		
		Label lbMaxConn = new Label(groupConn, SWT.NONE);
		lbMaxConn.setText("最大连接数：");
		lbMaxConn.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbMaxConn.setLayoutData(gridData);
		
		txtMaxConn = new Text(groupConn, SWT.BORDER);
		txtMaxConn.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtMaxConn.setLayoutData(gridData);

		Label lbMinConn = new Label(groupConn, SWT.NONE);
		lbMinConn.setFont(StyleFinal.SYS_FONT);
		lbMinConn.setText("最小连接数：");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbMinConn.setLayoutData(gridData);
		
		txtMinConn = new Text(groupConn, SWT.BORDER);
		txtMinConn.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtMinConn.setLayoutData(gridData);
		
		Label lbActive = new Label(groupConn, SWT.NONE);
		lbActive.setText("可用连接数：");
		lbActive.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbActive.setLayoutData(gridData);
		
		txtActive = new Text(groupConn, SWT.BORDER);
		txtActive.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtActive.setLayoutData(gridData);
		
		Label lbActiveTime = new Label(groupConn, SWT.NONE);
		lbActiveTime.setAlignment(SWT.RIGHT);
		lbActiveTime.setText("活动时间：");
		lbActiveTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbActiveTime.setLayoutData(gridData);
		
		txtActieTime = new Text(groupConn, SWT.BORDER);
		txtActieTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtActieTime.setLayoutData(gridData);
		
		Label lbSleepTime = new Label(groupConn, SWT.NONE);
		lbSleepTime.setAlignment(SWT.RIGHT);
		lbSleepTime.setText("睡眠时间：");
		lbSleepTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSleepTime.setLayoutData(gridData);
		
		txtSleepTime = new Text(groupConn, SWT.BORDER);
		txtSleepTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSleepTime.setLayoutData(gridData);
		
		Label lbLifeTime = new Label(groupConn, SWT.NONE);
		lbLifeTime.setAlignment(SWT.RIGHT);
		lbLifeTime.setText("寿命时间：");
		lbLifeTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLifeTime.setLayoutData(gridData);
		
		txtLifeTime = new Text(groupConn, SWT.BORDER);
		txtLifeTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLifeTime.setLayoutData(gridData);
		
		
		btnEdit = new Button(composite, SWT.NONE);
		btnEdit.setText("修改(&E)");
		btnEdit.setFont(StyleFinal.BTN_FONT);
		data = new FormData();
		data.top = new FormAttachment(0,10);
		data.left = new FormAttachment(100,-150);
		data.height = StyleFinal.BTN_HEIHGT;
		btnEdit.setLayoutData(data);
		btnEdit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				initContrl(true);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnSave = new Button(composite, SWT.NONE);
		btnSave.setText("保存(&S)");
		btnSave.setFont(StyleFinal.BTN_FONT);
		data = new FormData();
		data.top = new FormAttachment(0,10);
		data.left = new FormAttachment(btnEdit,10);
		data.height = StyleFinal.BTN_HEIHGT;
		btnSave.setLayoutData(data);
		btnSave.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				saveMethod();
				initContrl(false);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Group groupRoute = new Group(composite, SWT.NONE);
		groupRoute.setFont(StyleFinal.SYS_FONT);
		groupRoute.setText("站务线路信息");
		data = new FormData();
		data.top = new FormAttachment(groupConn,5);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.height = 100;
		groupRoute.setLayoutData(data);
		groupRoute.setLayout(new FillLayout());
		txtRouteSource = new Text(groupRoute, SWT.BORDER|SWT.MULTI|SWT.WRAP);
		txtRouteSource.setFont(StyleFinal.SYS_FONT);
		
		Group groupLiner = new Group(composite, SWT.NONE);
		groupLiner.setFont(StyleFinal.SYS_FONT);
		groupLiner.setText("站务班次信息");
		data = new FormData();
		data.top = new FormAttachment(groupRoute,5);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		groupLiner.setLayoutData(data);
		groupLiner.setLayout(new FillLayout());
		txtLinerSource = new Text(groupLiner, SWT.BORDER|SWT.MULTI|SWT.WRAP);
		txtLinerSource.setFont(StyleFinal.SYS_FONT);
		return composite;
	}
	
	private Composite createVoice(Composite detail) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(detail, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(10);
		data.bottom = new FormAttachment(100);
		composite.setLayoutData(data);
		composite.setLayout(new FillLayout());
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("参数名称","parameterName",350));
		columns.add(new GridColumn("参数值","parameterValue",300));
		columns.add(new GridColumn("参数描述","parameterDesc",400));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridVoice = new GridView(composite, SWT.NONE);
		gridVoice.CreateTabel(gridConfig);
		gridVoice.table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(null!=gridVoice.getSelection()){
					VmsParameterEditUi editUi = new VmsParameterEditUi(getShell(), gridVoice, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
			}
		});
		return composite;
	}

	private Composite createTemplate(Composite detail) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(detail, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(10);
		data.bottom = new FormAttachment(100);
		composite.setLayoutData(data);
		composite.setLayout(new FillLayout());
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("模板名称","templateName",200));
		columns.add(new GridColumn("中文模板","templateCn",350));
		columns.add(new GridColumn("英文模板","templateEn",350));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		
		gridTemplate = new GridView(composite, SWT.NONE);
		gridTemplate.CreateTabel(gridConfig);
		gridTemplate.table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(null!=gridTemplate.getSelection()){
					VmsParameterTemplateUi editUi = new VmsParameterTemplateUi(getShell(), gridTemplate, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
			}
		});
		return composite;
	}
	
	private Composite createCause(Composite detail) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(detail, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(10);
		data.bottom = new FormAttachment(100);
		composite.setLayoutData(data);
		composite.setLayout(new FillLayout());
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("中文模板","templateCn",350));
		columns.add(new GridColumn("英文模板","templateEn",350));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		List<SamModuleRight> causeRights = new ArrayList<SamModuleRight>();

		SamModuleRight rightAdd = new SamModuleRight();
		rightAdd.setRightMethod("addMethod");
		rightAdd.setRightName("添加");
		causeRights.add(rightAdd);
		
		SamModuleRight rightUpdate = new SamModuleRight();
		rightUpdate.setRightMethod("updateMethod");
		rightUpdate.setRightName("修改");
		causeRights.add(rightUpdate);
		
		SamModuleRight rightDelete = new SamModuleRight();
		rightDelete.setRightMethod("deleteMethod");
		rightDelete.setRightName("删除");
		causeRights.add(rightDelete);
		
		gridConfig.setRightBos(causeRights);
		gridConfig.setObj(obj);
		gridCause = new GridView(composite, SWT.NONE);
		gridCause.CreateTabel(gridConfig);
		gridCause.bindMouseDoubleClick(obj, "updateMethod");
		return composite;
	}
	
	private Composite createPlay(Composite detail) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(detail, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(10);
		data.bottom = new FormAttachment(100);
		composite.setLayoutData(data);
		composite.setLayout(new FillLayout());
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("参数名称","parameterName",350));
		columns.add(new GridColumn("参数值","parameterValue",300));
		columns.add(new GridColumn("参数描述","parameterDesc",400));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridLiner = new GridView(composite, SWT.NONE);
		gridLiner.CreateTabel(gridConfig);
		gridLiner.bindMouseDoubleClick(obj,rights,"updateMethod");
		gridLiner.table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(null!=gridLiner.getSelection()){
					VmsParameterEditUi editUi = new VmsParameterEditUi(getShell(), gridLiner, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
			}
		});
		return composite;
	}
	
	private void initContrl(boolean isEdit){
		cboConnType.setEnabled(isEdit);
		txtConnServer.setEnabled(isEdit);
		txtConnPort.setEnabled(isEdit);
		txtDbName.setEnabled(isEdit);
		txtUser.setEnabled(isEdit);
		txtPassword.setEnabled(isEdit);
		txtMaxConn.setEnabled(isEdit);
		txtMinConn.setEnabled(isEdit);
		txtActive.setEnabled(isEdit);
		txtActieTime.setEnabled(isEdit);
		txtSleepTime.setEnabled(isEdit);
		txtLifeTime.setEnabled(isEdit);
		txtRouteSource.setEnabled(isEdit);
		txtLinerSource.setEnabled(isEdit);
		if (isEdit){
			btnEdit.setEnabled(false);
			btnSave.setEnabled(true);
		}else{
			btnEdit.setEnabled(true);
			btnSave.setEnabled(false);
		}
	}

	@SuppressWarnings("static-access")
	public void refreshMethod(){
		//数据连接配置文件
		try {
			String connFile = System.getProperty("user.dir") + "\\proxool.xml";
			cboConnType.add("com.mysql.jdbc.Driver");
			cboConnType.add("oracle.jdbc.OracleDriver");
			ReadXml readXml = new ReadXml();
			int index = readXml.getNodesIndex(connFile, "proxool-config/proxool/alias","voice");
			readXml.readElement(connFile, "proxool-config/proxool/driver-class",index);
			cboConnType.setText(readXml.strData);
			readXml.readElement(connFile, "proxool-config/proxool/driver-url",index);
			String driverUrl[] = readXml.strData.split(":");
			if (null != driverUrl && driverUrl.length>0){
				if (driverUrl[1].equals("mysql")){
					txtConnServer.setText(driverUrl[2].substring(2)); 
					txtConnPort.setText(driverUrl[3].split("/")[0]);
					StringUtils stringUtils = new StringUtils();
					txtDbName.setText(stringUtils.substringBefore(driverUrl[3].split("/")[1],"?"));
				} else if (driverUrl[1].equals("oracle")){
					txtConnServer.setText(driverUrl[3].substring(1));
					txtConnPort.setText(driverUrl[4]);
					txtDbName.setText(driverUrl[5]);
				}
			}
			readXml.readProperty(connFile, "proxool-config/proxool/driver-properties","property","user",index);
			txtUser.setText(readXml.strData);
			readXml.readProperty(connFile, "proxool-config/proxool/driver-properties","property","password",index);
			txtPassword.setText(readXml.strData);
			readXml.readElement(connFile, "proxool-config/proxool/maximum-connection-count",index);
			txtMaxConn.setText(readXml.strData);
			readXml.readElement(connFile, "proxool-config/proxool/minimum-connection-count",index);
			txtMinConn.setText(readXml.strData);
			readXml.readElement(connFile, "proxool-config/proxool/prototype-count",index);
			txtActive.setText(readXml.strData);
			readXml.readElement(connFile, "proxool-config/proxool/maximum-active-time",index);
			txtActieTime.setText(readXml.strData);
			readXml.readElement(connFile, "proxool-config/proxool/house-keeping-sleep-time",index);
			txtSleepTime.setText(readXml.strData);
			readXml.readElement(connFile, "proxool-config/proxool/maximum-connection-lifetime",index);
			txtLifeTime.setText(readXml.strData);
			gridVoice.removeAll();
			gridLiner.removeAll();
			IVmsParameter iVmsParameter = new ImpVmsParameter();
			List<VmsParameter> vmsParameters = iVmsParameter.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			if (null != vmsParameters && vmsParameters.size()>0){
				for (int i = 0; i < vmsParameters.size(); i++) {
					if ("1".equals(vmsParameters.get(i).getParameterType())){
						if("route_source".equals(vmsParameters.get(i).getParameterCode())){
							txtRouteSource.setData(vmsParameters.get(i));
							txtRouteSource.setText(vmsParameters.get(i).getParameterValue());
						}else if("liner_source".equals(vmsParameters.get(i).getParameterCode())){
							txtLinerSource.setData(vmsParameters.get(i));
							txtLinerSource.setText(vmsParameters.get(i).getParameterValue());
						}
					}else if ("2".equals(vmsParameters.get(i).getParameterType())){
						gridVoice.addRow(vmsParameters.get(i));
					}else if ("3".equals(vmsParameters.get(i).getParameterType())){
						gridLiner.addRow(vmsParameters.get(i));
					}
				}
			}
			
			IVmsTemplate iVmsTemplate = new ImpVmsTemplate();
			List<VmsTemplate> vmsTemplates = iVmsTemplate.queryByOrganizeAndType(CommFinal.organize.getOrganizeSeq(),"1");
			gridTemplate.setDataList(vmsTemplates);
			List<VmsTemplate> vmsCauses = iVmsTemplate.queryByOrganizeAndType(CommFinal.organize.getOrganizeSeq(),"2");
			gridCause.setDataList(vmsCauses);
		} catch (Exception e) {
			LogUtil.operLog(e, "E", true, true);
		}
	}

	public void addMethod(){
		VmsParameterCauseUi editUi = new VmsParameterCauseUi(this.getShell(),gridCause,CommFinal.OPER_TYPE_ADD);
		editUi.open();
	}
	
	public void updateMethod(){
		if(null!=gridCause.getSelection()){
			VmsParameterCauseUi editUi = new VmsParameterCauseUi(getShell(), gridCause, CommFinal.OPER_TYPE_UPDATE);
			editUi.open();
		}else{
			MsgBox.warning(getShell(), "请选择修改的项。");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteMethod(){
		try {
			int checkIndex[] = gridCause.getSelectionIndexs();
			if (checkIndex.length>0){
				int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行数据吗？");
		    	if(isdel == SWT.YES){
		    		IVmsTemplate iVmsTemplate = new ImpVmsTemplate();
		    		iVmsTemplate.delete((List<VmsTemplate>) gridCause.getSelections(),CommFinal.initConfig());
		    		gridCause.deleteRow(checkIndex);
				} 
			    		
		    }
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}

	/**
	 * saveMethod方法描述：保存方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	@SuppressWarnings({ "static-access" })
	public void saveMethod(){
		try {
			String connFile = System.getProperty("user.dir") + "\\proxool.xml";
			UpdateXml updateXml = new UpdateXml();
			int index = updateXml.getNodesIndex(connFile, "proxool-config/proxool/alias","voice");
			updateXml.updateXml(connFile, "proxool-config/proxool/driver-class", cboConnType.getText(), index);
			if (cboConnType.getText().equals("com.mysql.jdbc.Driver")){
				updateXml.updateXml(connFile, "proxool-config/proxool/driver-url",
						"jdbc:mysql://" + txtConnServer.getText() + ":" 
						+ txtConnPort.getText() + "/" + txtDbName.getText()
						+ "?useUnicode=true&characterEncoding=utf-8", index);
			} else if (cboConnType.getText().equals("oracle.jdbc.OracleDriver")){
				updateXml.updateXml(connFile, "proxool-config/proxool/driver-url",
						"jdbc:oracle:thin:@" + txtConnServer.getText() + ":" 
						+ txtConnPort.getText() + ":" + txtDbName.getText(), index);
			}
			updateXml.writeProperty(connFile,"proxool-config/proxool/driver-properties","property","user",txtUser.getText(), index);
			updateXml.writeProperty(connFile,"proxool-config/proxool/driver-properties","property","password",txtPassword.getText(), index);
			updateXml.updateXml(connFile, "proxool-config/proxool/maximum-connection-count", txtMaxConn.getText(), index);
			updateXml.updateXml(connFile, "proxool-config/proxool/minimum-connection-count", txtMinConn.getText(), index);
			updateXml.updateXml(connFile, "proxool-config/proxool/prototype-count", txtActive.getText(), index);
			updateXml.updateXml(connFile, "proxool-config/proxool/maximum-active-time", txtActieTime.getText(), index);
			updateXml.updateXml(connFile, "proxool-config/proxool/house-keeping-sleep-time", txtSleepTime.getText(), index);
			updateXml.updateXml(connFile, "proxool-config/proxool/maximum-connection-lifetime", txtLifeTime.getText(), index);
			List<VmsParameter> vmsParameters = new ArrayList<VmsParameter>();
			//站务数据参数
			VmsParameter routeSource  = (VmsParameter) txtRouteSource.getData();
			routeSource.setParameterValue(txtRouteSource.getText());
			vmsParameters.add(routeSource);
			VmsParameter linerSource  = (VmsParameter) txtLinerSource.getData();
			linerSource.setParameterValue(txtLinerSource.getText());
			vmsParameters.add(linerSource);
			//语音参数
			/*List<VmsParameter> voiceParameters = (List<VmsParameter>) gridVoice.getDataList();
			if (null != voiceParameters && voiceParameters.size()>0){
				for (int i = 0; i < voiceParameters.size(); i++) {
					vmsParameters.add(voiceParameters.get(i));
				}
			}*/
			//班次参数
			/*List<VmsParameter> linerParameters = (List<VmsParameter>) gridLiner.getDataList();
			if (null != linerParameters && linerParameters.size()>0){
				for (int i = 0; i < linerParameters.size(); i++) {
					vmsParameters.add(linerParameters.get(i));
				}
			}*/
			//更新语音参数、班次参数
			IVmsParameter iVmsParameter = new ImpVmsParameter();
			iVmsParameter.update(vmsParameters, CommFinal.initConfig());
			
			//更新语音模板
			/*IVmsTemplate iVmsTemplate = new ImpVmsTemplate();
			iVmsTemplate.update((List<VmsTemplate>)gridTemplate.getDataList(), CommFinal.initConfig());*/
		} catch (IOException e) {
			LogUtil.operLog(e, "E", true, true);
		} catch (DocumentException e) {
			LogUtil.operLog(e, "E", true, true);
		} catch (Exception e) {
			LogUtil.operLog(e, "E", true, true);
		}
	}
	
}
