/**
 *******************************************************************************
 *
 * (c) Copyright 2012 重庆市志玛信息技术有限公司
 *
 * 系统名称：frameWork
 * 文  件  名 ：abcd.java
 * 模块名称：(请更改成该模块名称)
 * 创  建  人 ：鲁承毅
 * 创建日期：2013-3-2 下午10:36:15
 * 修  改  人 ：(修改了该文件，请填上修改人的名字)
 * 修改日期：(请填上修改该文件时的日期)
 * 版         本 ： V1.0.0
 *******************************************************************************  
 */

package com.zhima.frame.ui.sam;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamModule;
import com.zhima.frame.action.sam.ISamModuleRight;
import com.zhima.frame.action.sam.impl.ImpSamModule;
import com.zhima.frame.action.sam.impl.ImpSamModuleRight;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * SamModuleUi概要说明：功能权限设置
 * @author lcy
 */
public class SamModuleUi extends Composite {
	private Object obj;
	//功能权限
	private List<SamModuleRight> rights;
	//功能
	private Tree treeModule;
	//权限
	private GridView tbRight;
	/**
	 * 
	 * 构造函数: 构造功能权限类
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SamModuleUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		final SashForm sashForm = new SashForm(this,SWT.HORIZONTAL|SWT.FLAT);
        sashForm.setLayout(new FillLayout());
        FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		sashForm.setLayoutData(data);
        createModule(sashForm);
        createRight(sashForm);
        sashForm.setWeights(new int[] {53,47});
		queryModule();
	}

	/**
	 * createModule方法描述：创建功能面板
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午03:20:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sashForm
	 * @return Composite
	 */
	public Composite createModule(SashForm sashForm){
		BasicPanel compOper = new BasicPanel(sashForm, SWT.BORDER);
		compOper.setShowBack(false);
		compOper.setInput(false);
		compOper.createPanel();
		//按钮权限
		compOper.toolbar.setLayout(new RowLayout());
		final List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		//设置选项卡右键菜单
		for(int i=0;i<rights.size();i++){
			if ("queryModule".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("addModule".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("updateModule".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("deleteModule".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("copyModule".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compOper.toolbar, rightBos);
		
		compOper.detail.setLayout(new FillLayout());
		treeModule = new Tree(compOper.detail, SWT.FULL_SELECTION);
		treeModule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				for (int i = 0; i < rightBos.size(); i++) {
					if ("updateModule".equals(rightBos.get(i).getRightMethod())){
						CallMethod callMethod = new CallMethod();
						callMethod.bindSelectMenu(obj, rightBos.get(i));
					}
				}
			}
		});
		Menu menu = new Menu(treeModule);
		final CallMethod cMenu = new CallMethod();
		if (null != rightBos && rightBos.size()>0){
			for (int i = 0; i < rightBos.size(); i++) {
				final SamModuleRight moduleRight = rightBos.get(i);
				MenuItem menuItem = new MenuItem(menu,SWT.CASCADE);
				menuItem.setText(moduleRight.getRightName());
				try {
					menuItem.addSelectionListener(new SelectionListener() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							cMenu.bindSelectMenu(obj, moduleRight);
						}
						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {}
					});
				} catch (Exception e) {
					LogUtil.operLog(e,"E",true,true);
				}	
			}
		}
		treeModule.setMenu(menu);
		treeModule.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TreeItem ti=(TreeItem)arg0.item;
				SamModule samModule = (SamModule) ti.getData();
				if (null != samModule && null != samModule.getModuleSeq()){
					try {
						ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
						List<SamModuleRight> samModuleRights = iSamModuleRight.queryModuleRight(samModule.getModuleSeq());
						tbRight.setDataList(samModuleRights);
					} catch (UserBusinessException e) {
						LogUtil.operLog(e,"E",true,true);
					} catch (Exception e) {
						LogUtil.operLog(e,"E",true,true);
					}
				}
			}
		});
		treeModule.addListener(SWT.MeasureItem, new Listener() {
            public void handleEvent(Event event) {
                event.width = treeModule.getGridLineWidth(); // 设置宽度
                event.height = (int) Math.floor(event.gc.getFontMetrics()
                        .getHeight() * 1.5); // 设置高度为字体高度的1.5倍
            }
        });
		treeModule.setLinesVisible(true);
		treeModule.setHeaderVisible(true);
		treeModule.setFont(StyleFinal.GRID_FONT);
		treeModule.setBackground(StyleFinal.GRID_BACKGROUND);
		treeModule.setForeground(StyleFinal.GRID_FOREGROUND);
		
		TreeColumn treeColumn = new TreeColumn(treeModule, SWT.NONE);
		treeColumn.setWidth(200);
		treeColumn.setText("功能名称");
		TreeColumn treeColumn_1 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_1.setWidth(180);
		treeColumn_1.setText("类名");
		TreeColumn treeColumn_2 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_2.setWidth(220);
		treeColumn_2.setText("包名");
		TreeColumn treeColumn_3 = new TreeColumn(treeModule, SWT.CENTER);
		treeColumn_3.setWidth(60);
		treeColumn_3.setText("类型");
		TreeColumn treeColumn_4 = new TreeColumn(treeModule, SWT.CENTER);
		treeColumn_4.setWidth(60);
		treeColumn_4.setText("分隔线");
		TreeColumn treeColumn_5 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_5.setWidth(50);
		treeColumn_5.setText("组");
		TreeColumn treeColumn_6 = new TreeColumn(treeModule, SWT.CENTER);
		treeColumn_6.setWidth(50);
		treeColumn_6.setText("序号");
		TreeColumn treeColumn_7 = new TreeColumn(treeModule, SWT.CENTER);
		treeColumn_7.setWidth(50);
		treeColumn_7.setText("状态");
		TreeColumn treeColumn_8 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_8.setWidth(200);
		treeColumn_8.setText("描述");
		TreeColumn treeColumn_9 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_9.setWidth(200);
		treeColumn_9.setText("备注");
		/*TreeColumn treeColumn_10 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_10.setWidth(200);
		treeColumn_10.setText("创建时间");
		TreeColumn treeColumn_11 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_11.setWidth(200);
		treeColumn_11.setText("更新时间");*/
		return compOper;	
	}

	/**
	 * createRight方法描述：创建权限面板
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午03:21:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sashForm
	 * @return Composite
	 */
	public Composite createRight(SashForm sashForm){		
		BasicPanel compMain=new BasicPanel(sashForm,SWT.BORDER);
		compMain.setInput(false);
		compMain.createPanel();
		//按钮权限
		compMain.toolbar.setLayout(new RowLayout());
		final List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		for(int i=0;i<rights.size();i++){
			if ("queryRight".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("addRight".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("updateRight".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("upRight".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));

			}
			if ("downRight".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("deleteRight".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compMain.toolbar, rightBos);
		
		compMain.detail.setLayout(new FillLayout());
		tbRight = new GridView(compMain.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("权限名称","rightName",150));
		columns.add(new GridColumn("运行方法","rightMethod",150));
		//columns.add(new GridColumn("总价","fullFare","[1]+[1]+[1]+[sn]*[20]"));
		columns.add(new GridColumn("类型","rightType",80));
		columns.add(new GridColumn("快捷键","shortcutKey",60));
		columns.add(new GridColumn("记录日志","operLog", new String[]{"1-是","0-否"},80));
		columns.add(new GridColumn("序号","sn",60,"SUM"));
		columns.add(new GridColumn("状态","status", new String[]{"0-无效","1-有效"},60));
		columns.add(new GridColumn("描述","rightDesc",200));
		columns.add(new GridColumn("备注","remark",200));
		//columns.add(new GridColumn("创建时间","createTime",200));
		//columns.add(new GridColumn("更新时间","updateTime",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setVirtual(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);

		gridConfig.setRightBos(rightBos);
		gridConfig.setObj(obj);
		gridConfig.setGridName("tbRight");
		tbRight.CreateTabel(gridConfig);
		tbRight.bindMouseDoubleClick(obj,rights,"updateRight");
		
		return compMain;
	}
	
	/**
	 * queryModule方法描述：查询所有功能
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午03:22:22
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryModule(){
		try {
			treeModule.removeAll();
			tbRight.removeAll();
			ISamModule iSamModule = new ImpSamModule();
			//顶级权限
			List<SamModule> topSamModules = iSamModule.queryTopModule(CommFinal.organize.getOrganizeSeq());
			//子级权限
			List<SamModule> subSamModules = iSamModule.querySubModule(CommFinal.organize.getOrganizeSeq());
			if (null != topSamModules && topSamModules.size()>0){
				for (SamModule samModule : topSamModules) {
					TreeItem topItem = new TreeItem(treeModule,SWT.NONE);
					topItem.setData(samModule);
					topItem.setText(showText(samModule));
					if ("0".equals(samModule.getModuleType())){
						if (null != subSamModules && subSamModules.size()>0){
							for (int i = 0; i < subSamModules.size(); i++) {
								SamModule secondModules = subSamModules.get(i);
								if (secondModules.getParentSeq().equals(samModule.getModuleSeq())){
									TreeItem secondItem = new TreeItem(topItem,SWT.NONE);
									secondItem.setData(secondModules);
									secondItem.setText(showText(secondModules));
									if ("0".equals(secondModules.getModuleType())){
										if (null != subSamModules && subSamModules.size()>0){
											for (int j = 0; j < subSamModules.size(); j++) {
												SamModule thirdModules = subSamModules.get(j);
												if (thirdModules.getParentSeq().equals(secondModules.getModuleSeq())){
													TreeItem thirdItem = new TreeItem(secondItem,SWT.NONE);
													thirdItem.setData(thirdModules);
													thirdItem.setText(showText(thirdModules));
													if (null != subSamModules && subSamModules.size()>0){
														for (int k = 0; k < subSamModules.size(); k++) {
															SamModule fourthModules = subSamModules.get(k);
															if (fourthModules.getParentSeq().equals(thirdModules.getModuleSeq())){
																TreeItem fourthItem = new TreeItem(thirdItem,SWT.NONE);
																fourthItem.setData(fourthModules);
																fourthItem.setText(showText(fourthModules));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * showText方法描述：模块表格树信息格式显示
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午03:21:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModule 模块信息
	 * @return String[]
	 */
	private String[] showText(SamModule samModule){
		String moduleType ="功能";
		if ("0".equals(samModule.getModuleType())){
			 moduleType ="节点";
		}
		String separate = "有";
		if ("0".equals(samModule.getSeparate())){
			separate ="无";
		}
		String status ="有效";
		if ("0".equals(samModule.getStatus())){
			status ="无效";
		}
		return new String[]{samModule.getModuleName(),samModule.getModuleClass(),samModule.getPackName(),
				moduleType,separate,samModule.getGroupName(),samModule.getSn().toString(),status,
				samModule.getModuleDesc(),samModule.getRemark()};
	}
	
	/**
	 * addModule方法描述：添加功能
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-3 下午10:22:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void addModule(){
		SamModuleEditUi samModuleEditUi = new SamModuleEditUi(this.getShell(),treeModule,CommFinal.OPER_TYPE_ADD);
		samModuleEditUi.open();
	}
	
	/**
	 * updateModule方法描述：修改功能
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-3 下午10:23:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void updateModule(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if (treeModule.getSelection().length>0){
					SamModuleEditUi samModuleEditUi = new SamModuleEditUi(this.getShell(),treeModule,CommFinal.OPER_TYPE_UPDATE);
					samModuleEditUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
				break;
			}
		}
	}
	
	public void copyModule(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if (treeModule.getSelection().length>0){
					SamModuleEditUi samModuleEditUi = new SamModuleEditUi(this.getShell(),treeModule,CommFinal.OPER_TYPE_COPY);
					samModuleEditUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
				break;
			}
		}
	}
	
	/**
	 * deleteModule方法描述：删除功能
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-20 下午11:51:48
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void deleteModule(){
		try {
			TreeItem[] treeItem = treeModule.getSelection();
			if (treeItem.length>0){
				int isdel =  MsgBox.confirm(getShell(),"你确定要删除选中行数据吗？");
    	    	if(isdel != SWT.YES){
    	    		return;
    	    	}
				ISamModule iSamModule = new ImpSamModule();
				for (TreeItem item : treeItem) {
					SamModule samModule = (SamModule) item.getData();
					iSamModule.deleteByPk(samModule,CommFinal.initConfig());
					for (TreeItem firstItem : item.getItems()) {
						SamModule firstModule = (SamModule) firstItem.getData();
						iSamModule.deleteByPk(firstModule,CommFinal.initConfig());
						for (TreeItem secondItem : firstItem.getItems()) {
							SamModule secondModule = (SamModule) secondItem.getData();
							iSamModule.deleteByPk(secondModule,CommFinal.initConfig());
							for (TreeItem thirdItem : secondItem.getItems()) {
								SamModule thirdModule = (SamModule) thirdItem.getData();
								iSamModule.deleteByPk(thirdModule,CommFinal.initConfig());
								for (TreeItem fourthItem : thirdItem.getItems()) {
									SamModule fourthModule = (SamModule) fourthItem.getData();
									iSamModule.deleteByPk(fourthModule,CommFinal.initConfig());	
								}
							}
						}
					}
					item.dispose();
					tbRight.removeAll();
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * queryRight方法描述：查询模块权限信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午11:04:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryRight(){
		try {
			ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
			TreeItem[] treeItem = treeModule.getSelection();
			if (treeItem.length>0){
				SamModule samModule = (SamModule) treeItem[0].getData();
				if (null != samModule && null != samModule.getModuleSeq()){
					List<SamModuleRight> samModuleRights = iSamModuleRight.queryModuleRight(samModule.getModuleSeq());
					tbRight.setDataList(samModuleRights);
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	
	public void addRight(){
		TreeItem[] treeItem = treeModule.getSelection();
		if (treeItem.length>0){
			SamModule samModule = (SamModule) treeItem[0].getData();
			if (!"0".equals(samModule.getModuleType())){
				SamModuleRightEditUi samModuleRightEditUi = new SamModuleRightEditUi(this.getShell(),samModule,tbRight,CommFinal.OPER_TYPE_ADD);
				samModuleRightEditUi.open();
			} else {
				MsgBox.warning(getShell(), "功能节点不允许添加权限！");
				return;
			}
		}else{
			MsgBox.warning(getShell(), "请选择功能添加权限！");
		}
	}
	
	public void updateRight(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=tbRight.getSelection()){
					TreeItem[] treeItem = treeModule.getSelection();
					if (treeItem.length>0){
						SamModule samModule = (SamModule) treeItem[0].getData();
						SamModuleRightEditUi samModuleRightEditUi = new SamModuleRightEditUi(this.getShell(),samModule,tbRight,CommFinal.OPER_TYPE_UPDATE);
						samModuleRightEditUi.open();
					}
				}else{
					MsgBox.warning(getShell(), "请选择要修改的权限！");
				}
				break;
			}
		}
	}

	/**
	 * upRight方法描述：权限顺序上移
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午03:22:57
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void upRight(){
		try {
			if (tbRight.getSelectionIndex()>0){
				SamModuleRight upRight = (SamModuleRight) tbRight.getObjIndex(tbRight.getSelectionIndex()-1);
				SamModuleRight currRight = (SamModuleRight) tbRight.getSelection();
				int bacSn = currRight.getSn();
				currRight.setSn(upRight.getSn());
				upRight.setSn(bacSn);
				tbRight.updateRow(tbRight.getSelectionIndex(), currRight);
				tbRight.updateRow(tbRight.getSelectionIndex()-1, upRight);
				ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
				iSamModuleRight.update(currRight,CommFinal.initConfig());
				iSamModuleRight.update(upRight,CommFinal.initConfig());
				this.tbRight.upTableRow();
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * downRight方法描述：权限顺序下移
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午03:23:09
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void downRight(){
		try {
			if (tbRight.getSelectionIndex()>0 &&
					tbRight.getSelectionIndex()<tbRight.table.getItemCount()){
				SamModuleRight currRight = (SamModuleRight) tbRight.getSelection();
				SamModuleRight downRight = (SamModuleRight) tbRight.getObjIndex(tbRight.getSelectionIndex()+1);
				int bacSn = currRight.getSn();
				currRight.setSn(downRight.getSn());
				downRight.setSn(bacSn);
				tbRight.updateRow(tbRight.getSelectionIndex(), currRight);
				tbRight.updateRow(tbRight.getSelectionIndex()+1, downRight);
				ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
				iSamModuleRight.update(currRight,CommFinal.initConfig());
				iSamModuleRight.update(downRight,CommFinal.initConfig());
				this.tbRight.downTableRow();
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}

	/**
	 * deleteRight方法描述：权限删除
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午03:23:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	@SuppressWarnings("unchecked")
	public void deleteRight(){
		try {
			int[] checkindexs = tbRight.getSelectionIndexs();
			List<SamModuleRight> samModuleRights = (List<SamModuleRight>)tbRight.getSelections();
			if(null!=samModuleRights && samModuleRights.size()>0){
    			int isdel =  MsgBox.confirm(getShell(),"你确定要删除此项数据吗？");
    	    	if(isdel == SWT.YES){
    	    		ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
    	    		iSamModuleRight.deleteByPk(samModuleRights,CommFinal.initConfig());
    	    		tbRight.deleteRow(checkindexs);
    	    	}
    		}else{
    			MsgBox.warning(this.getShell(),"请选中删除的项！");
    			return;
    		}
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
}
