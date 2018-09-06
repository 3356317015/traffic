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
import com.zhima.frame.action.sam.ISamModule;
import com.zhima.frame.action.sam.ISamModuleRight;
import com.zhima.frame.action.sam.ISamUser;
import com.zhima.frame.action.sam.ISamUserGroup;
import com.zhima.frame.action.sam.impl.ImpSamGroup;
import com.zhima.frame.action.sam.impl.ImpSamModule;
import com.zhima.frame.action.sam.impl.ImpSamModuleRight;
import com.zhima.frame.action.sam.impl.ImpSamUser;
import com.zhima.frame.action.sam.impl.ImpSamUserGroup;
import com.zhima.frame.model.SamGroup;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamUser;
import com.zhima.frame.model.SamUserGroup;
import com.zhima.frame.model.SamUserModule;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * SamUserUi概要说明：用户设置
 * @author lcy
 */
public class SamUserUi extends Composite {
	private Object obj;
	//功能权限
	private List<SamModuleRight> rights;
	//功能
	private Tree treeModule;
	//用户
	private GridView tbUser;
	//组
	private GridView tbGroup;
	
	/**
	 * 构造函数: 构造用户设置类
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SamUserUi(Composite parent, int style, List list) {
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
		createUser(sashForm);
        createModule(sashForm);
        sashForm.setWeights(new int[] {40,60});
        queryUser();
        queryGroup();
		queryModule();
	}

	/**
	 * createUser方法描述：创建用户面板
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 上午12:05:34
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sashForm
	 * @return Composite
	 */
	public Composite createUser(SashForm sashForm){
		BasicPanel compOper = new BasicPanel(sashForm, SWT.BORDER);
		compOper.setShowBack(false);
		compOper.setInput(false);
		compOper.createPanel();
		//按钮权限
		compOper.toolbar.setLayout(new RowLayout());
		List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		for(int i=0;i<rights.size();i++){
			if ("queryUser".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("addUser".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("updateUser".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("deleteUser".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compOper.toolbar, rightBos);

		compOper.detail.setLayout(new FillLayout());
		tbUser = new GridView(compOper.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("机构名称","organizeName",180));
		columns.add(new GridColumn("姓名","userName",120));
		columns.add(new GridColumn("帐号","userCode",120));
		columns.add(new GridColumn("电话","telephone",120));
		columns.add(new GridColumn("邮箱","email",200));
		columns.add(new GridColumn("状态","status", new String[]{"0-无效","1-有效"},60));
		columns.add(new GridColumn("备注","remark",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setVirtual(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		gridConfig.setObj(obj);
		gridConfig.setGridName("tbUser");
		tbUser.CreateTabel(gridConfig);
		tbUser.bindMouseDoubleClick(obj,rights,"updateUser");
		tbUser.bindWidgetSelected(obj, "queryUserGroup");
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
		//设置选项卡右键菜单
		for(int i=0;i<rights.size();i++){
			if ("refreshGroup".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("saveRight".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compMain.toolbar, rightBos);
		
		compMain.detail.setLayout(new FormLayout());

		tbGroup = new GridView(compMain.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("岗位名称","groupName",150));
		columns.add(new GridColumn("岗位代码","groupCode",120));
		columns.add(new GridColumn("状态","status", new String[]{"0-无效","1-有效"},60));
		columns.add(new GridColumn("备注","remark",200));
		columns.add(new GridColumn("创建时间","createTime",200));
		columns.add(new GridColumn("更新时间","updateTime",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setVirtual(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(true);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		gridConfig.setObj(obj);
		gridConfig.setGridName("tbGroup");
		tbGroup.CreateTabel(gridConfig);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(50);
		data.right = new FormAttachment(100);
		tbGroup.setLayoutData(data);
		

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
		data = new FormData();
		data.top = new FormAttachment(tbGroup);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		treeModule.setLayoutData(data);
		
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
	 * addUser方法描述：添加用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-25 下午09:45:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void addUser(){
		SamUserEditUi samUserEditUi = new SamUserEditUi(this.getShell(), tbUser, CommFinal.OPER_TYPE_ADD);
		samUserEditUi.open();
	}
	
	/**
	 * updateUser方法描述：修改用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-25 下午09:45:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void updateUser(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=tbUser.getSelection()){
					SamUserEditUi samUserEditUi = new SamUserEditUi(this.getShell(), tbUser, CommFinal.OPER_TYPE_UPDATE);
					samUserEditUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
				break;
			}
		}
	}
	
	/**
	 * queryUser方法描述：查询所有用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午09:47:39
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryUser(){
		try {
			ISamUser iSamUser = new ImpSamUser();
			List<SamUser> samUsers = iSamUser.queryByOrganize(CommFinal.organize.getOrganizeSeq());
			tbUser.setDataList(samUsers);
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * queryGroup方法描述：查询所有角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午10:09:55
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryGroup(){
		try {
			ISamGroup iSamGroup = new ImpSamGroup();
			List<SamGroup> samGroups = iSamGroup.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			tbGroup.setDataList(samGroups);
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * deleteUser方法描述：删除用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午10:15:59
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	@SuppressWarnings("unchecked")
	public void deleteUser(){
		int[] checkindexs = tbUser.getSelectionIndexs();
		List<SamUser> samUsers = (List<SamUser>)tbUser.getSelections();
		try {
			if(null!=samUsers && samUsers.size()>0){
    			int isdel =  MsgBox.confirm(getShell(),"你确定要删除此项数据吗？");
    	    	if(isdel == SWT.YES){
    				ISamUser iSamUser = new ImpSamUser();
    	    		for(int i=0;i<checkindexs.length;i++){
    	    			iSamUser.deleteByPk(samUsers.get(i).getUserSeq(),CommFinal.initConfig());
    	    		}
    	    		tbUser.deleteRow(checkindexs);
    	    	}
    		}else{
    			MsgBox.warning(this.getShell(),"请选中删除的项！");
    			return;
    		}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * refreshGroup方法描述：刷新
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午11:29:57
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void refreshGroup(){
		queryGroup();
		queryUserGroup();
		queryModule();
		queryUserModule();
	}
	
	@SuppressWarnings("unchecked")
	public void saveRight(){
		try {
			List<SamUserModule> samUserModules = new ArrayList<SamUserModule>();
			if (null != tbUser.getSelection()){
				SamUser samUser = (SamUser) tbUser.getSelection();
				String userSeq = samUser.getUserSeq();
				SamUserModule samUserModule = new SamUserModule();
				SamModule samModule = null;
				SamModuleRight samModuleRight = null;
				
				for(int i=0;i<treeModule.getItems().length;i++){
					TreeItem treeItem = treeModule.getItems()[i];
					if (treeItem.getChecked()==true){
						if ("SamModule".equals(getObjectName(treeItem.getData()))){
							samModule = (SamModule) treeItem.getData();
							samUserModule = new SamUserModule();
							samUserModule.setUserSeq(userSeq);
							samUserModule.setModuleSeq(samModule.getModuleSeq());
							samUserModules.add(samUserModule);
						}else{
							samModuleRight = (SamModuleRight) treeItem.getData();
							samUserModule = new SamUserModule();
							samUserModule.setUserSeq(userSeq);
							samUserModule.setModuleSeq(samModuleRight.getModuleSeq());
							samUserModule.setRightSeq(samModuleRight.getRightSeq());
							samUserModules.add(samUserModule);
						}
					}
	
					for(int j=0;j<treeItem.getItems().length;j++){
						if (treeItem.getItems()[j].getChecked()==true){
							if ("SamModule".equals(getObjectName(treeItem.getItems()[j].getData()))){
								samModule = (SamModule) treeItem.getItems()[j].getData();
								samUserModule = new SamUserModule();
								samUserModule.setUserSeq(userSeq);
								samUserModule.setModuleSeq(samModule.getModuleSeq());
								samUserModules.add(samUserModule);
							}else{
								samModuleRight = (SamModuleRight) treeItem.getItems()[j].getData();
								samUserModule = new SamUserModule();
								samUserModule.setUserSeq(userSeq);
								samUserModule.setModuleSeq(samModuleRight.getModuleSeq());
								samUserModule.setRightSeq(samModuleRight.getRightSeq());
								samUserModules.add(samUserModule);
							}
						}
						
						for(int k=0;k<treeItem.getItems()[j].getItems().length;k++){
							if (treeItem.getItems()[j].getItems()[k].getChecked()==true){
								if ("SamModule".equals(getObjectName(treeItem.getItems()[j].getItems()[k].getData()))){
									samModule = (SamModule) treeItem.getItems()[j].getItems()[k].getData();
									samUserModule = new SamUserModule();
									samUserModule.setUserSeq(userSeq);
									samUserModule.setModuleSeq(samModule.getModuleSeq());
									samUserModules.add(samUserModule);
								}else{
									samModuleRight = (SamModuleRight) treeItem.getItems()[j].getItems()[k].getData();
									samUserModule = new SamUserModule();
									samUserModule.setUserSeq(userSeq);
									samUserModule.setModuleSeq(samModuleRight.getModuleSeq());
									samUserModule.setRightSeq(samModuleRight.getRightSeq());
									samUserModules.add(samUserModule);
								}
							}
							
							for(int l=0;l<treeItem.getItems()[j].getItems()[k].getItems().length;l++){
								if (treeItem.getItems()[j].getItems()[k].getItems()[l].getChecked()==true){
									if ("SamModule".equals(getObjectName(treeItem.getItems()[j].getItems()[k].getItems()[l].getData()))){
										samModule = (SamModule) treeItem.getItems()[j].getItems()[k].getItems()[l].getData();
										samUserModule = new SamUserModule();
										samUserModule.setUserSeq(userSeq);
										samUserModule.setModuleSeq(samModule.getModuleSeq());
										samUserModules.add(samUserModule);
									}else{
										samModuleRight = (SamModuleRight) treeItem.getItems()[j].getItems()[k].getItems()[l].getData();
										samUserModule = new SamUserModule();
										samUserModule.setUserSeq(userSeq);
										samUserModule.setModuleSeq(samModuleRight.getModuleSeq());
										samUserModule.setRightSeq(samModuleRight.getRightSeq());
										samUserModules.add(samUserModule);
									}
								}
								
								for (int m = 0; m < treeItem.getItems()[j].getItems()[k].getItems()[l].getItems().length; m++) {
									if (treeItem.getItems()[j].getItems()[k].getItems()[l].getItems()[m].getChecked()==true){
										samModuleRight = (SamModuleRight) treeItem.getItems()[j].getItems()[k].getItems()[l].getItems()[m].getData();
										samUserModule = new SamUserModule();
										samUserModule.setUserSeq(userSeq);
										samUserModule.setModuleSeq(samModuleRight.getModuleSeq());
										samUserModule.setRightSeq(samModuleRight.getRightSeq());
										samUserModules.add(samUserModule);
									}
								}
							}
						}
					}
				}
				ISamUserGroup iSamUserGroup = new ImpSamUserGroup();
				List<SamGroup> samGroups = (List<SamGroup>) tbGroup.getCheckSelections();
				iSamUserGroup.updateUserRight(userSeq,samGroups,samUserModules,CommFinal.initConfig());
				MsgBox.warning(getShell(), samUser.getUserName() +" 用户权限设置成功。");
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * queryUserGroup方法描述：查询用户角色及权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 下午06:43:54
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	@SuppressWarnings("unchecked")
	public void queryUserGroup(){
		try {
			if (null != tbUser.getSelection()){
				SamUser samUser =  (SamUser) tbUser.getSelection();
				ISamUserGroup iSamUserGroup = new ImpSamUserGroup();
				List<SamGroup> samGroups = (List<SamGroup>) tbGroup.getDataList();
				if (null != samGroups && samGroups.size()>0){
					List<SamUserGroup> userGroups = iSamUserGroup.queryByUserSeq(samUser.getUserSeq());
					if (null !=  userGroups && userGroups.size()>0){
						for (int i = 0; i < samGroups.size(); i++) {
							tbGroup.setCheck(i, false);
							if (null != userGroups && userGroups.size()>0){
								for (int j = 0; j < userGroups.size(); j++) {
									if (userGroups.get(j).getGroupSeq().equals(samGroups.get(i).getGroupSeq())){
										tbGroup.setCheck(i, true);
									}
								}
							}
						}
					}
				}
				queryUserModule();
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	/**
	 * queryUserModule方法描述：查询用户权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午08:23:30
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryUserModule(){
		try {
			if (null != tbUser.getSelection()){
				ISamModule iSamModule = new ImpSamModule();
				ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
				SamUser samUser = (SamUser) tbUser.getSelection();
				List<SamModule> samModules = iSamModule.queryByUserSeq(samUser.getUserSeq());
				List<SamModuleRight> samModuleRights = iSamModuleRight.queryByUserSeq(samUser.getUserSeq());
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
