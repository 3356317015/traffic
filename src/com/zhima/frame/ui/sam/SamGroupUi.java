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
import com.zhima.frame.action.sam.ISamGroup;
import com.zhima.frame.action.sam.ISamGroupModule;
import com.zhima.frame.action.sam.ISamModule;
import com.zhima.frame.action.sam.ISamModuleRight;
import com.zhima.frame.action.sam.impl.ImpSamGroup;
import com.zhima.frame.action.sam.impl.ImpSamGroupModule;
import com.zhima.frame.action.sam.impl.ImpSamModule;
import com.zhima.frame.action.sam.impl.ImpSamModuleRight;
import com.zhima.frame.model.SamGroup;
import com.zhima.frame.model.SamGroupModule;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * SamGroupUi概要说明：组权限设置
 * @author lcy
 */
public class SamGroupUi extends Composite {
	private Object obj;
	//功能权限
	private List<SamModuleRight> rights;
	//功能
	private Tree treeModule;
	//权限
	private GridView tbGroup;
	
	/**
	 * 构造函数: 构造组权限设置类
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SamGroupUi(Composite parent, int style, List list) {
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
        createGroup(sashForm);
        createModule(sashForm);
        sashForm.setWeights(new int[] {40,60});
        queryGroup();
		
	}

	/**
	 * createGroup方法描述：创建组面板
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 上午12:05:34
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sashForm
	 * @return Composite
	 */
	public Composite createGroup(SashForm sashForm){
		BasicPanel compOper = new BasicPanel(sashForm, SWT.BORDER);
		compOper.setShowBack(false);
		compOper.setInput(false);
		compOper.createPanel();
		//按钮权限
		compOper.toolbar.setLayout(new RowLayout());
		List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		for(int i=0;i<rights.size();i++){
			if ("queryGroup".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("addGroup".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("updateGroup".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("deleteGroup".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compOper.toolbar, rightBos);
		
		compOper.detail.setLayout(new FillLayout());
		
		tbGroup = new GridView(compOper.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("岗位名称","groupName",150));
		columns.add(new GridColumn("岗位代码","groupCode",120));
		columns.add(new GridColumn("状态","status", new String[]{"0-无效","1-有效"},60));
		columns.add(new GridColumn("备注","remark",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setVirtual(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		gridConfig.setObj(obj);
		gridConfig.setGridName("tbGroup");
		tbGroup.CreateTabel(gridConfig);
		tbGroup.bindMouseDoubleClick(obj, rights, "updateGroup");
		tbGroup.bindWidgetSelected(obj, "queryGroupModule");
		return compOper;
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
		BasicPanel compMain=new BasicPanel(sashForm,SWT.BORDER);
		compMain.setInput(false);
		compMain.createPanel();
		//按钮权限
		compMain.toolbar.setLayout(new RowLayout());
		final List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		for(int i=0;i<rights.size();i++){
			if ("refreshRight".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("saveRight".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compMain.toolbar, rightBos);
		
		compMain.detail.setLayout(new FillLayout());
		treeModule = new Tree(compMain.detail, SWT.CHECK | SWT.FULL_SELECTION);
		treeModule.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TreeItem treeItem=(TreeItem)arg0.item;
				boolean checkState = treeItem.getChecked();
				for(int i=0;i<treeItem.getItems().length;i++){
					treeItem.getItems()[i].setChecked(checkState);
					for(int j=0;j<treeItem.getItems()[i].getItems().length;j++){
						treeItem.getItems()[i].getItems()[j].setChecked(checkState);
						for(int k=0;k<treeItem.getItems()[i].getItems()[j].getItems().length;k++){
							treeItem.getItems()[i].getItems()[j].getItems()[k].setChecked(checkState);
							for(int l=0;l<treeItem.getItems()[i].getItems()[j].getItems()[k].getItems().length;l++){
								treeItem.getItems()[i].getItems()[j].getItems()[k].getItems()[l].setChecked(checkState);
							}
						}
					}
				}
				if (checkState == true){
					if (null != treeItem.getParentItem() 
							&& treeItem.getParentItem().getChecked()==false){
						treeItem.getParentItem().setChecked(true);
						if (null != treeItem.getParentItem().getParentItem() 
								&& treeItem.getParentItem().getParentItem().getChecked()==false){
							treeItem.getParentItem().getParentItem().setChecked(true);
							if (null != treeItem.getParentItem().getParentItem().getParentItem()
								&& treeItem.getParentItem().getParentItem().getParentItem().getChecked()==false){
								treeItem.getParentItem().getParentItem().getParentItem().setChecked(true);
								if (null != treeItem.getParentItem().getParentItem().getParentItem().getParentItem()
									&& treeItem.getParentItem().getParentItem().getParentItem().getParentItem().getChecked()==false){
									treeItem.getParentItem().getParentItem().getParentItem().getParentItem().setChecked(true);
								}
							}
						}
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
		treeColumn_1.setWidth(120);
		treeColumn_1.setText("类名");
		TreeColumn treeColumn_2 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_2.setWidth(150);
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
		TreeColumn treeColumn_10 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_10.setWidth(200);
		treeColumn_10.setText("创建时间");
		TreeColumn treeColumn_11 = new TreeColumn(treeModule, SWT.NONE);
		treeColumn_11.setWidth(200);
		treeColumn_11.setText("更新时间");
		return compMain;	
	}
	
	/**
	 * queryGroup方法描述：查询所有用户组
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午01:07:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryGroup(){
		try {
			ISamGroup iSamGroup = new ImpSamGroup();
			List<SamGroup> samGroups = iSamGroup.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			tbGroup.setDataList(samGroups);
			queryModule();
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * addGroup方法描述：添加角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午01:26:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void addGroup(){
		SamGroupEditUi samGroupEditUi = new SamGroupEditUi(this.getShell(), tbGroup, CommFinal.OPER_TYPE_ADD);
		samGroupEditUi.open();
	}

	/**
	 * updateGroup方法描述：修改角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午01:41:53
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void updateGroup(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=tbGroup.getSelection()){
					SamGroupEditUi samGroupEditUi = new SamGroupEditUi(this.getShell(), tbGroup, CommFinal.OPER_TYPE_UPDATE);
					samGroupEditUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteGroup(){
		try {
			int[] checkindexs = tbGroup.getSelectionIndexs();
			List<SamGroup> samGroups = (List<SamGroup>)tbGroup.getSelections();

			if(null!=samGroups && samGroups.size()>0){
    			int isdel =  MsgBox.confirm(getShell(),"你确定要删除此项数据吗？");
    	    	if(isdel == SWT.YES){
    				ISamGroup iSamGroup = new ImpSamGroup();
    				iSamGroup.deleteByPk(samGroups,CommFinal.initConfig());
    	    		tbGroup.deleteRow(checkindexs);
    	    	}
    		}else{
    			MsgBox.warning(this.getShell(),"请选中删除的项！");
    			return;
    		}
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * refreshRight方法描述：刷新功能及角色权限信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午12:17:43
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void refreshRight(){
		queryModule();
		queryGroupModule();
	}
	
	/**
	 * saveRight方法描述：保存角色权限信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午12:23:09
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void saveRight(){
		try {
			List<SamGroupModule> samGroupModules = new ArrayList<SamGroupModule>();
			if (null != tbGroup.getSelection()){
				SamGroup samGroup = (SamGroup) tbGroup.getSelection();
				String groupSeq = samGroup.getGroupSeq();
				SamGroupModule samGroupModule = new SamGroupModule();
				SamModule samModule = null;
				SamModuleRight samModuleRight = null;
				
				for(int i=0;i<treeModule.getItems().length;i++){
					TreeItem treeItem = treeModule.getItems()[i];
					if (treeItem.getChecked()==true){
						if ("SamModule".equals(getObjectName(treeItem.getData()))){
							samModule = (SamModule) treeItem.getData();
							samGroupModule = new SamGroupModule();
							samGroupModule.setGroupSeq(groupSeq);
							samGroupModule.setModuleSeq(samModule.getModuleSeq());
							samGroupModules.add(samGroupModule);
						}else{
							samModuleRight = (SamModuleRight) treeItem.getData();
							samGroupModule = new SamGroupModule();
							samGroupModule.setGroupSeq(groupSeq);
							samGroupModule.setModuleSeq(samModuleRight.getModuleSeq());
							samGroupModule.setRightSeq(samModuleRight.getRightSeq());
							samGroupModules.add(samGroupModule);
						}
					}
	
					for(int j=0;j<treeItem.getItems().length;j++){
						if (treeItem.getItems()[j].getChecked()==true){
							if ("SamModule".equals(getObjectName(treeItem.getItems()[j].getData()))){
								samModule = (SamModule) treeItem.getItems()[j].getData();
								samGroupModule = new SamGroupModule();
								samGroupModule.setGroupSeq(groupSeq);
								samGroupModule.setModuleSeq(samModule.getModuleSeq());
								samGroupModules.add(samGroupModule);
							}else{
								samModuleRight = (SamModuleRight) treeItem.getItems()[j].getData();
								samGroupModule = new SamGroupModule();
								samGroupModule.setGroupSeq(groupSeq);
								samGroupModule.setModuleSeq(samModuleRight.getModuleSeq());
								samGroupModule.setRightSeq(samModuleRight.getRightSeq());
								samGroupModules.add(samGroupModule);
							}
						}
						
						for(int k=0;k<treeItem.getItems()[j].getItems().length;k++){
							if (treeItem.getItems()[j].getItems()[k].getChecked()==true){
								if ("SamModule".equals(getObjectName(treeItem.getItems()[j].getItems()[k].getData()))){
									samModule = (SamModule) treeItem.getItems()[j].getItems()[k].getData();
									samGroupModule = new SamGroupModule();
									samGroupModule.setGroupSeq(groupSeq);
									samGroupModule.setModuleSeq(samModule.getModuleSeq());
									samGroupModules.add(samGroupModule);
								}else{
									samModuleRight = (SamModuleRight) treeItem.getItems()[j].getItems()[k].getData();
									samGroupModule = new SamGroupModule();
									samGroupModule.setGroupSeq(groupSeq);
									samGroupModule.setModuleSeq(samModuleRight.getModuleSeq());
									samGroupModule.setRightSeq(samModuleRight.getRightSeq());
									samGroupModules.add(samGroupModule);
								}
							}
							
							for(int l=0;l<treeItem.getItems()[j].getItems()[k].getItems().length;l++){
								if (treeItem.getItems()[j].getItems()[k].getItems()[l].getChecked()==true){
									if ("SamModule".equals(getObjectName(treeItem.getItems()[j].getItems()[k].getItems()[l].getData()))){
										samModule = (SamModule) treeItem.getItems()[j].getItems()[k].getItems()[l].getData();
										samGroupModule = new SamGroupModule();
										samGroupModule.setGroupSeq(groupSeq);
										samGroupModule.setModuleSeq(samModule.getModuleSeq());
										samGroupModules.add(samGroupModule);
									}else{
										samModuleRight = (SamModuleRight) treeItem.getItems()[j].getItems()[k].getItems()[l].getData();
										samGroupModule = new SamGroupModule();
										samGroupModule.setGroupSeq(groupSeq);
										samGroupModule.setModuleSeq(samModuleRight.getModuleSeq());
										samGroupModule.setRightSeq(samModuleRight.getRightSeq());
										samGroupModules.add(samGroupModule);
									}
								}
								
								for (int m = 0; m < treeItem.getItems()[j].getItems()[k].getItems()[l].getItems().length; m++) {
									if (treeItem.getItems()[j].getItems()[k].getItems()[l].getItems()[m].getChecked()==true){
										samModuleRight = (SamModuleRight) treeItem.getItems()[j].getItems()[k].getItems()[l].getItems()[m].getData();
										samGroupModule = new SamGroupModule();
										samGroupModule.setGroupSeq(groupSeq);
										samGroupModule.setModuleSeq(samModuleRight.getModuleSeq());
										samGroupModule.setRightSeq(samModuleRight.getRightSeq());
										samGroupModules.add(samGroupModule);
									}
								}
							}
						}
					}
				}
				ISamGroupModule iSamGroupModule = new ImpSamGroupModule();
				iSamGroupModule.updateGroupRight(groupSeq, samGroupModules,CommFinal.initConfig());
				MsgBox.warning(getShell(), samGroup.getGroupName() +" 角色权限设置成功。");
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
		
	/**
	 * queryGroupModule方法描述：查询角序权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午08:23:30
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryGroupModule(){
		try {
			if (null != tbGroup.getSelection()){
				ISamModule iSamModule = new ImpSamModule();
				ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
				SamGroup samGroup = (SamGroup) tbGroup.getSelection();
				List<SamModule> samModules = iSamModule.queryByGroupSeq(samGroup.getGroupSeq());
				List<SamModuleRight> samModuleRights = iSamModuleRight.queryByGroupSeq(samGroup.getGroupSeq());
				boolean checkState = false;
				SamModule samModule = null;
				SamModuleRight samModuleRight = null;
				for(int i=0;i<treeModule.getItems().length;i++){
					TreeItem treeItem = treeModule.getItems()[i];
					treeItem.setChecked(checkState);
					if ("SamModule".equals(getObjectName(treeItem.getData()))){
						samModule = (SamModule) treeItem.getData();
						if (null != samModules && samModules.size()>0){
							for (int j = 0; j < samModules.size(); j++) {
								if (samModule.getModuleSeq().equals(samModules.get(j).getModuleSeq())){
									treeItem.setChecked(true);
									break;
								}
							}
						}
					}else{
						samModuleRight = (SamModuleRight) treeItem.getData();
						if (null != samModuleRights && samModuleRights.size()>0){
							for (int j = 0; j < samModuleRights.size(); j++) {
								if (samModuleRight.getRightSeq().equals(samModuleRights.get(j).getRightSeq())){
									treeItem.setChecked(true);
									break;
								}
							}
						}
					}
					for(int j=0;j<treeItem.getItems().length;j++){
						treeItem.getItems()[j].setChecked(checkState);
						if ("SamModule".equals(getObjectName(treeItem.getItems()[j].getData()))){
							samModule = (SamModule) treeItem.getItems()[j].getData();
							if (null != samModules && samModules.size()>0){
								for (int k = 0; k < samModules.size(); k++) {
									if (samModule.getModuleSeq().equals(samModules.get(k).getModuleSeq())){
										treeItem.getItems()[j].setChecked(true);
										break;
									}
								}
							}
						}else{
							samModuleRight = (SamModuleRight) treeItem.getItems()[j].getData();
							if (null != samModuleRights && samModuleRights.size()>0){
								for (int k = 0; k < samModuleRights.size(); k++) {
									if (samModuleRight.getRightSeq().equals(samModuleRights.get(k).getRightSeq())){
										treeItem.getItems()[j].setChecked(true);
										break;
									}
								}
							}
						}
						for(int k=0;k<treeItem.getItems()[j].getItems().length;k++){
							treeItem.getItems()[j].getItems()[k].setChecked(checkState);
							if ("SamModule".equals(getObjectName(treeItem.getItems()[j].getItems()[k].getData()))){
								samModule = (SamModule) treeItem.getItems()[j].getItems()[k].getData();
								if (null != samModules && samModules.size()>0){
									for (int l = 0; l < samModules.size(); l++) {
										if (samModule.getModuleSeq().equals(samModules.get(l).getModuleSeq())){
											treeItem.getItems()[j].getItems()[k].setChecked(true);
											break;
										}
									}
								}
							}else{
								samModuleRight = (SamModuleRight) treeItem.getItems()[j].getItems()[k].getData();
								if (null != samModuleRights && samModuleRights.size()>0){
									for (int l = 0; l < samModuleRights.size(); l++) {
										if (samModuleRight.getRightSeq().equals(samModuleRights.get(l).getRightSeq())){
											treeItem.getItems()[j].getItems()[k].setChecked(true);
											break;
										}
									}
								}
							}
							for(int l=0;l<treeItem.getItems()[j].getItems()[k].getItems().length;l++){
								treeItem.getItems()[j].getItems()[k].getItems()[l].setChecked(checkState);
								if ("SamModule".equals(getObjectName(treeItem.getItems()[j].getItems()[k].getItems()[l].getData()))){
									samModule = (SamModule) treeItem.getItems()[j].getItems()[k].getItems()[l].getData();
									if (null != samModules && samModules.size()>0){
										for (int m = 0; m < samModules.size(); m++) {
											if (samModule.getModuleSeq().equals(samModules.get(m).getModuleSeq())){
												treeItem.getItems()[j].getItems()[k].getItems()[l].setChecked(true);
												break;
											}
										}
									}
								}else{
									samModuleRight = (SamModuleRight) treeItem.getItems()[j].getItems()[k].getItems()[l].getData();
									if (null != samModuleRights && samModuleRights.size()>0){
										for (int n = 0; n < samModuleRights.size(); n++) {
											if (samModuleRight.getRightSeq().equals(samModuleRights.get(n).getRightSeq())){
												treeItem.getItems()[j].getItems()[k].getItems()[l].setChecked(true);
												break;
											}
										}
									}
								}
								for (int m = 0; m < treeItem.getItems()[j].getItems()[k].getItems()[l].getItems().length; m++) {
									treeItem.getItems()[j].getItems()[k].getItems()[l].getItems()[m].setChecked(checkState);
									samModuleRight = (SamModuleRight) treeItem.getItems()[j].getItems()[k].getItems()[l].getItems()[m].getData();
									if (null != samModuleRights && samModuleRights.size()>0){
										for (int n = 0; n < samModuleRights.size(); n++) {
											if (samModuleRight.getRightSeq().equals(samModuleRights.get(n).getRightSeq())){
												treeItem.getItems()[j].getItems()[k].getItems()[l].getItems()[m].setChecked(true);
												break;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			treeModule.showSelection();
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * getObjectName方法描述：获取对象名称
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午09:43:28
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj
	 * @return String
	 */
	public String getObjectName(Object obj){
		String className = obj.getClass().getName();
		if (!"".equals(className)){
			String str[] = className.split("\\.");
			return str[str.length-1];
		}
		return "";
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
			ISamModule iSamModule = new ImpSamModule();
			ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
			//顶级权限查询
			List<SamModule> topSamModules = iSamModule.queryTopModule(CommFinal.organize.getOrganizeSeq());
			//子级权限查询
			List<SamModule> subSamModules = iSamModule.querySubModule(CommFinal.organize.getOrganizeSeq());
			if (null != topSamModules && topSamModules.size()>0){
				//功能权限查询
				List<SamModuleRight> samModuleRights = iSamModuleRight.queryRightByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
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
									//samModuleRights = iSamModuleRight.queryByModuleSeq(secondModules.getModuleSeq());
									if (null != samModuleRights && samModuleRights.size()>0){
										for (int j = 0; j < samModuleRights.size(); j++) {
											if (secondModules.getModuleSeq().equals(samModuleRights.get(j).getModuleSeq())){
												TreeItem secondRight = new TreeItem(secondItem,SWT.NONE);
												secondRight.setData(samModuleRights.get(j));
												secondRight.setText(showText(samModuleRights.get(j)));
											}
										}
									}
									if ("0".equals(secondModules.getModuleType())){
										if (null != subSamModules && subSamModules.size()>0){
											for (int j = 0; j < subSamModules.size(); j++) {
												SamModule thirdModules = subSamModules.get(j);
												if (thirdModules.getParentSeq().equals(secondModules.getModuleSeq())){
													TreeItem thirdItem = new TreeItem(secondItem,SWT.NONE);
													thirdItem.setData(thirdModules);
													thirdItem.setText(showText(thirdModules));
													//samModuleRights = iSamModuleRight.queryByModuleSeq(thirdModules.getModuleSeq());
													if (null != samModuleRights && samModuleRights.size()>0){
														for (int k = 0; k < samModuleRights.size(); k++) {
															if (thirdModules.getModuleSeq().equals(samModuleRights.get(j).getModuleSeq())){
																TreeItem thirdRight = new TreeItem(thirdItem,SWT.NONE);
																thirdRight.setData(samModuleRights.get(k));
																thirdRight.setText(showText(samModuleRights.get(k)));
															}
														}
													}
													if (null != subSamModules && subSamModules.size()>0){
														for (int k = 0; k < subSamModules.size(); k++) {
															SamModule fourthModules = subSamModules.get(k);
															if (fourthModules.getParentSeq().equals(thirdModules.getModuleSeq())){
																TreeItem fourthItem = new TreeItem(thirdItem,SWT.NONE);
																fourthItem.setData(fourthModules);
																fourthItem.setText(showText(fourthModules));
																//samModuleRights = iSamModuleRight.queryByModuleSeq(fourthModules.getModuleSeq());
																if (null != samModuleRights && samModuleRights.size()>0){
																	for (int l = 0; l < samModuleRights.size(); l++) {
																		if (fourthModules.getModuleSeq().equals(samModuleRights.get(j).getModuleSeq())){
																			TreeItem fourthRight = new TreeItem(fourthItem,SWT.NONE);
																			fourthRight.setData(samModuleRights.get(l));
																			fourthRight.setText(showText(samModuleRights.get(l)));
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
					}
				}
				treeModule.setSelection(treeModule.getItem(0));
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
				samModule.getModuleDesc(),samModule.getRemark(),samModule.getCreateTime(),samModule.getUpdateTime()};
	}
	
	private String[] showText(SamModuleRight samModuleRight){
		String status ="有效";
		if ("0".equals(samModuleRight.getStatus())){
			status ="无效";
		}
		return new String[]{samModuleRight.getRightName(),samModuleRight.getRightMethod(),samModuleRight.getRightType(),
				"","","",samModuleRight.getSn().toString(),status,
				samModuleRight.getRightDesc(),samModuleRight.getRemark(),samModuleRight.getCreateTime(),samModuleRight.getUpdateTime()};
	}
}
