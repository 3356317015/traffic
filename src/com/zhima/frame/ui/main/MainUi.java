package com.zhima.frame.ui.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.hexapixel.widgets.generic.Utils;
import com.hexapixel.widgets.ribbon.RibbonButton;
import com.hexapixel.widgets.ribbon.RibbonGroup;
import com.hexapixel.widgets.ribbon.RibbonGroupSeparator;
import com.hexapixel.widgets.ribbon.RibbonShell;
import com.hexapixel.widgets.ribbon.RibbonTab;
import com.hexapixel.widgets.ribbon.RibbonTabFolder;
import com.hexapixel.widgets.ribbon.RibbonTooltip;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.login.ILogin;
import com.zhima.frame.action.login.impl.ImpLogin;
import com.zhima.frame.action.sam.ISamModule;
import com.zhima.frame.action.sam.ISamModuleRight;
import com.zhima.frame.action.sam.impl.ImpSamModule;
import com.zhima.frame.action.sam.impl.ImpSamModuleRight;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.DateUtils;
import com.zhima.util.ImageUtil;
import com.zhima.util.ImageZoom;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;

public class MainUi {
	protected Display loginDis;
	protected RibbonShell shell;
	private int shellWidth = 0;
	private boolean bigMenu = false;
	private MenuItem changeToolbar;
	//系统时间线程
	public static Timer timer;
	private static ILogin iLogin = new ImpLogin();
	private static PanelUi panelUi;
	private StatusBar statusBar;
	private int countOnlineTime = 0;
	private RibbonTabFolder menuFolder;
	private Menu menuMain;
	private static List<SamModule> modules = new ArrayList<SamModule>();
	private static List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
	private static List<SamModule> toolbars = new ArrayList<SamModule>();
	private static Image defaultToolImage = SWTResourceManager.getImage("images/module.ico");
	private static Image defaultMenuImage = ImageZoom.getImage(SWTResourceManager.getImage("images/module.ico"),16,16);
	
	/**
	 * Open the dialog.
	 * @return the result
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "static-access"})
	public void open(Display display) {
		try {
			this.loginDis = display;
			shell = new RibbonShell(display,StyleFinal.SYS_NUMBER, StyleFinal.SYS_KEY);
			//shell.setButtonImage(SWTResourceManager.getImage("images/selection_recycle.png"));
			shell.setButtonImage(SWTResourceManager.getImage("images/big_image.png"));
			shell.setText("道路交通站务管理系统v2017");
			shell.getShell().setImage(SWTResourceManager.getImage("images/login/express.ico"));
			shell.setSize(1024, 600);
			
			//创建系统菜单
			shell.setBigButtonTooltip(new RibbonTooltip("工具栏", "系统功能总菜单"));
			menuMain = shell.getBigButtonMenu();
			shell.addBigButtonListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent e) {
				}
				public void widgetSelected(SelectionEvent e) {
					shell.showBigButtonMenu();
				}
			});

			shell.getShell().addControlListener(new ControlAdapter() {
				@Override
				public void controlResized(ControlEvent e) {
					Point size=shell.getShell().getSize();
					int width=size.x;//长
					//int height=size.y;//宽
					if(width!=shellWidth){
						String selectTabName ="";
						if (null != shell.getRibbonTabFolder().getSelectedTab()){
							selectTabName = shell.getRibbonTabFolder().getSelectedTab().getName();
						}
						menuFolder.disposeAllChildren();
						createRibbon(modules);
						if (null != selectTabName && selectTabName.length()>0){
							for (RibbonTab ribbonTab : shell.getRibbonTabFolder().getTabs()) {
								if (ribbonTab.getName().equals(selectTabName)){
									shell.getRibbonTabFolder().selectTab(ribbonTab);
								}
							}
						}

						//shell.getShell().layout();
					}
				}
			});
			
			shell.getShell().addShellListener(new ShellAdapter() {
	            public void shellClosed(ShellEvent e) {
	            	MessageBox msgbox = new MessageBox(shell.getShell(), SWT.OK|SWT.CANCEL| 
						    SWT.ICON_WARNING);
	            	msgbox.setText("确认");
	                msgbox.setMessage("确定要退出系统吗?"); 
	                int message = msgbox.open();
	                e.doit = message == SWT.OK;
	            }
			});

			
			shell.getShell().addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent arg0) {
					timer.cancel();
				}
			});
			
			ISamModule iSamModule = new ImpSamModule();
			ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
			//ISamUserToolbar iSamUserToolbar = new ImpSamUserToolbar();
			//查询用户所有模块
			modules = iSamModule.queryModuleByUser(CommFinal.user.getUserSeq());
			//查询用户所有权限
			rights = iSamModuleRight.queryRightByUser(CommFinal.user.getUserSeq());
			//查询用户工具栏
			//toolbars = iSamUserToolbar.queryByUserSeq(CommFinal.user.getUserSeq(),null);
			toolbars = iSamModule.queryToolbarByUserSeq(CommFinal.user.getUserSeq());

			Utils.centerDialogOnScreen(shell.getShell());
			shell.getShell().setMaximized(true);
			//创建菜单
			menuFolder = shell.getRibbonTabFolder();
			/*menuFolder.setHelpImage(SWTResourceManager.getImage("images/questionmark.gif"));
			menuFolder.getHelpButton().setToolTip(new RibbonTooltip("工具栏", "获取系统帮助"));*/
			
			menuFolder.setMenuImage(SWTResourceManager.getImage("images/small_menu.png"));
			menuFolder.getmMenuButton().setToolTip(new RibbonTooltip("工具栏", "获取收藏夹"));
			
			menuFolder.setUserImage(SWTResourceManager.getImage("images/small_config.png"));
			menuFolder.getmUserButton().setToolTip(new RibbonTooltip("工具栏", "系统选项"));
			Menu menuUser = new Menu(shell.getShell());
			menuFolder.mUserButton.setMenu(menuUser);
			menuFolder.mMenuButton.setMenu(menuUser);
			
			changeToolbar = new MenuItem(menuUser, SWT.CHECK);
			changeToolbar.setText("工具栏");
			changeToolbar.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if(menuFolder.isCollapsed()){
						menuFolder.expandTabFolder();
						panelUi.folder.setMaximized(false);
					}else{
						menuFolder.collapseTabFolder();
						panelUi.folder.setMaximized(true);
					}
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});
			if (StyleFinal.toolbarShow == true){
				changeToolbar.setSelection(true);
			}else{
				changeToolbar.setEnabled(false);
			}
			
			
			new MenuItem(menuUser, SWT.SEPARATOR);
			MenuItem changePwd = new MenuItem(menuUser, SWT.POP_UP);
			changePwd.setText("修改密码");
			changePwd.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					PasswordUi passwordUi = new PasswordUi(menuFolder.getShell());
					passwordUi.open();
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});
			
			new MenuItem(menuUser, SWT.SEPARATOR);
			MenuItem logoutItem = new MenuItem(menuUser, SWT.POP_UP);
			logoutItem.setText("注销");
			logoutItem.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if(64==MsgBox.confirm(new Shell(), "确定要注销吗?")){
						try {
							StyleFinal.LOGIN_LOGOUT=true;
							timer.cancel();
							ILogin iLogin = new ImpLogin();
							iLogin.close(CommFinal.user.getOnlineSeq(), CommFinal.organize.getOrganizeSeq(),
									CommFinal.user.getUserCode(), CommFinal.user.getUserName(),CommFinal.initConfig());
							menuFolder.getShell().dispose();
							LoginDialog login = new LoginDialog();
							login.open(loginDis);
						} catch (Exception e) {
							LogUtil.operLog(e,"E",true,true);
						}
					}
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});
			MenuItem exitItem = new MenuItem(menuUser, SWT.POP_UP);
			exitItem.setText("退出系统");
			//logoutItem.setImage(getModuleImage(module,18,18));
			exitItem.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					try {
						if(64==MsgBox.confirm(new Shell(), "确定要退出系统吗?")){
							timer.cancel();
							ILogin iLogin = new ImpLogin();
							iLogin.close(CommFinal.user.getOnlineSeq(), CommFinal.organize.getOrganizeSeq(),
									CommFinal.user.getUserCode(), CommFinal.user.getUserName(),CommFinal.initConfig());
							menuFolder.getShell().dispose();
							loginDis.dispose();
							System.exit(0);
						}
					} catch (Exception e) {
						LogUtil.operLog(e,"E",true,true);
					}

				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});

			menuFolder.setHelpImage(SWTResourceManager.getImage("images/small_help.png"));
			menuFolder.getHelpButton().setToolTip(new RibbonTooltip("工具栏", "获取系统帮助"));
			Menu menuHelp = new Menu(shell.getShell());
			MenuItem mUpgrade = new MenuItem(menuHelp, SWT.POP_UP);
			mUpgrade.setText("在线升级");
			
			new MenuItem(menuHelp, SWT.SEPARATOR);
			
			MenuItem mHelp = new MenuItem(menuHelp, SWT.POP_UP);
			mHelp.setText("帮助");
			mHelp.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					SamModule module = new SamModule();
					module.setModuleCode("help");
					module.setModuleName("帮助");
					module.setModuleClass("SamHelpUi");
					module.setPackName("com.server.frame.ui.sam.");
					getModuleTab(module);

				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});
			
			
			new MenuItem(menuHelp, SWT.SEPARATOR);
			MenuItem mSysinfo = new MenuItem(menuHelp, SWT.POP_UP);
			mSysinfo.setText("系统信息");
			MenuItem mAbout = new MenuItem(menuHelp, SWT.POP_UP);
			mAbout.setText("关于");
			menuFolder.mHelpButton.setMenu(menuHelp);			
			
			menuFolder.setShowToolbar(StyleFinal.toolbarShow);
			menuFolder.addMouseListener(new MouseAdapter() {
				Menu tabMenu;
				@Override
				public void mouseDown(MouseEvent arg0) {
					//得到鼠标当前坐标
					Point point = new Point(arg0.x,arg0.y);
					//得到面板执行执行范围
					int x = 0;
					int y = 0;
					int width = 0;
					int height = 0;
					List<RibbonTab> ribbonTabs = menuFolder.getTabs();
					//计算坐标宽度
					for(RibbonTab ribbonTab : ribbonTabs){
						width += ribbonTab.getBounds().width;
					}
					//计算x、y、height
					if(null != ribbonTabs && ribbonTabs.size() > 0){
						x = ribbonTabs.get(0).getBounds().x;
						y = ribbonTabs.get(0).getBounds().y;
						height = ribbonTabs.get(0).getBounds().height;
					}
					Rectangle rectangle = new Rectangle(x, y, width, height);
					if(rectangle.contains(point) && menuFolder.isCollapsed() && arg0.button == 1){
						if (menuFolder.mCollapsedTabFolder == true){
							RibbonTabFolder ftftemp = (RibbonTabFolder) arg0.widget;
							RibbonTab ribbonTab = ftftemp.getSelectedTab();
							tabMenu = new Menu(shell.getShell());
							tabMenu.setLocation(shell.getShell().getBounds().x+ribbonTab.getBounds().x+2,
									shell.getShell().getBounds().y+ribbonTab.getBounds().y+ribbonTab.getBounds().height+35);
							createToolbarProp(tabMenu,ribbonTab.getData(),modules);
							tabMenu.setVisible(true);
						}
					}
				}
				@Override
				public void mouseDoubleClick(MouseEvent arg0) {
					if (null != tabMenu){
						tabMenu.setVisible(false);
					}
				}
			});
			//创建主窗口面板
			Composite compMain = new Composite(shell.getShell(), SWT.NONE);
			compMain.setLayout(new FormLayout());
			//创建操作台
			panelUi = new PanelUi(compMain, SWT.NONE, modules);
			panelUi.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
			FormData data = new FormData();
			data.top = new FormAttachment(0);
			data.left = new FormAttachment(0);
			data.right = new FormAttachment(100);
			data.bottom = new FormAttachment(100, -25);
			panelUi.setLayoutData(data);
			//注册事件
			if (StyleFinal.toolbarShow==true){
				panelUi.folder.addCTabFolder2Listener(new CTabFolder2Adapter(){
					public void maximize(CTabFolderEvent event){
						menuFolder.collapseTabFolder();
						panelUi.folder.setMaximized(true);
						changeToolbar.setSelection(false);
					}
					public void restore(CTabFolderEvent event){
						menuFolder.expandTabFolder();
						panelUi.folder.setMaximized(false);
						changeToolbar.setSelection(true);
					}
				});
				panelUi.folder.addMouseListener(new MouseListener() {
					@Override
					public void mouseDown(MouseEvent arg0) {
					}
					@Override
					public void mouseUp(MouseEvent arg0) {
					}
					@Override
					public void mouseDoubleClick(MouseEvent arg0) {
						if(menuFolder.isCollapsed()){
							menuFolder.expandTabFolder();
							panelUi.folder.setMaximized(false);
						}else{
							menuFolder.collapseTabFolder();
							panelUi.folder.setMaximized(true);
						}
					}
				});
				/*if (null != toolbars && toolbars.size()>0){
					
				}else{
					menuFolder.collapseTabFolder();
					panelUi.folder.setMaximized(true);
				}*/
			}else{
				menuFolder.collapseTabFolder();
				panelUi.folder.setMaximized(true);
			}
			statusBar = new StatusBar(compMain,SWT.BORDER);
			data = new FormData();
			data.top = new FormAttachment(panelUi,-1);
			data.left = new FormAttachment(0);
			data.right = new FormAttachment(100);
			data.bottom = new FormAttachment(100);
			statusBar.setLayoutData(data);
			
			statusBar.lbOrganize.setText(CommFinal.organize.getOrganizeName());
			statusBar.lbUser.setText(CommFinal.user.getUserName());
			AutoRun();
			shell.open();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			timer.cancel();
			ILogin iLogin = new ImpLogin();
			iLogin.close(CommFinal.user.getOnlineSeq(), CommFinal.organize.getOrganizeSeq(),
					CommFinal.user.getUserCode(), CommFinal.user.getUserName(),CommFinal.initConfig());
			if(null != shell && !shell.isDisposed()){
				shell.getShell().dispose();
			}
			if (null != display && !display.isDisposed()){
				display.dispose();
			}
			System.exit(0);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,false);
		}
	}

	/**
	 * updateConnectTime方法描述：向服务器更新连接系统的时间
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 上午11:16:33
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void600000
	 */
	public void AutoRun() {
		timer = new Timer();
		//运行
		timer.schedule(new TimerTask() {
			public void run() {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						try {
							//同步用户登录时间
							countOnlineTime += 1;
							if (countOnlineTime == CommFinal.ONLINE_UPDATE_TIME){
								if (null != CommFinal.user && !"".equals(CommFinal.user.getUserCode())){
									iLogin.update(CommFinal.user.getOnlineSeq());	
								}
								countOnlineTime = 0;
							}
							statusBar.lbTime.setText(DateUtils.getNow("yyyy-MM-dd hh:mm:ss (EE)"));
						} catch (Exception e) {
							LogUtil.operLog(e,"E",true,false);
						}
						new TestThread().start();
					}
				});
			}
		}, 0, 1000);
	}

	class TestThread extends Thread {
		public void run() {
			/*try {
				if (llockList.size() > 0) {
					for (int i = 0; i < llockList.size(); i++) {
						// System.out.println("TestThread"
						// +Thread.currentThread().getName()+" is running");
						iAutoUndoseat.Undoseat(llockList.get(i).getTransId());
					}
				}
			} catch (Exception e) {
				lb_msg.setText(e.getMessage());
			}*/
		}
	}
	
	/**
	 * createToolbar方法描述：创建工具栏
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-2-7 上午01:08:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createRibbon(List<SamModule> samModules){
		if (null != samModules && samModules.size()>0){
			if (StyleFinal.toolbarShow == false){
				//按用户设置显示菜单工具栏
				createUserRibbon(samModules);
			}else{
				//创建选择权限菜单及工具栏
				createUserRibbonAndBar(samModules);
			}
			createSystemMenu();
			bigMenu = true;
		}
	}
	
	/**
	 * createSystemMenu方法描述：创建系统菜单
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-8-16 下午4:36:48
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return void
	 */
	private void createSystemMenu(){
		if (bigMenu==true){
			return;
		}
		new MenuItem(menuMain, SWT.SEPARATOR);
		MenuItem changePwd = new MenuItem(menuMain, SWT.POP_UP);
		changePwd.setText("修改密码");
		changePwd.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				PasswordUi passwordUi = new PasswordUi(menuFolder.getShell());
				passwordUi.open();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		new MenuItem(menuMain, SWT.SEPARATOR);
		
		MenuItem logoutItem = new MenuItem(menuMain, SWT.POP_UP);
		logoutItem.setText("注销");
		logoutItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(64==MsgBox.confirm(new Shell(), "确定要注销吗?")){
					try {
						StyleFinal.LOGIN_LOGOUT=true;
						timer.cancel();
						ILogin iLogin = new ImpLogin();
						iLogin.close(CommFinal.user.getOnlineSeq(), CommFinal.organize.getOrganizeSeq(),
								CommFinal.user.getUserCode(), CommFinal.user.getUserName(),CommFinal.initConfig());
						menuFolder.getShell().dispose();
						LoginDialog.open(loginDis);
					} catch (Exception e) {
						LogUtil.operLog(e,"E",true,true);
					}
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		MenuItem exitItem = new MenuItem(menuMain, SWT.POP_UP);
		exitItem.setText("退出系统");
		//logoutItem.setImage(getModuleImage(module,18,18));
		exitItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					if(64==MsgBox.confirm(new Shell(), "确定要退出系统吗?")){
						timer.cancel();
						ILogin iLogin = new ImpLogin();
						iLogin.close(CommFinal.user.getOnlineSeq(), CommFinal.organize.getOrganizeSeq(),
								CommFinal.user.getUserCode(), CommFinal.user.getUserName(),CommFinal.initConfig());
						menuFolder.getShell().dispose();
						loginDis.dispose();
						System.exit(0);
					}
				} catch (Exception e) {
					LogUtil.operLog(e,"E",true,true);
				}

			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
	}
	
	/**
	 * createAllMneu方法描述：创建所有工具栏菜单
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2014-2-21 下午11:27:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModules
	 * @return void
	 */
	private void createUserRibbon(List<SamModule> samModules) {
		//不显示工具栏
		if (null != samModules && samModules.size()>0){
			shellWidth = shell.getShell().getSize().x;
			int a = Integer.valueOf(shell.getShell().getBounds().x);
			int b = Integer.valueOf(shell.getShell().getBounds().width);
			int c = 0;
			//收藏夹菜单
			Menu menuFavorites = new Menu(shell.getShell());
			menuFolder.mMenuButton.setMenu(menuFavorites);
			//收藏夹我的工作
			createWorkMenu(menuFavorites);
			
			for (SamModule samModule : samModules) {
				if ("".equals(samModule.getParentSeq())
						|| "0".equals(samModule.getParentSeq())){
					if (!"1".equals(samModule.getStatus())){
						continue;
					}
					List<SamModule> secondModules = getModuleItem(samModule.getModuleSeq(),samModules);
					if (null != secondModules && secondModules.size()>0){
						//菜单1级菜单
						Menu firstDrop = new Menu(shell.getShell(), SWT.DROP_DOWN);
						if (c+315<a+b){
							//创建Ribbon
							RibbonTab ribbonTab = new RibbonTab(menuFolder, samModule.getModuleName());
							ribbonTab.setData(samModule.getModuleSeq());
							c = ribbonTab.getBounds().x+a;
						}else{
							//创建收藏夹
							MenuItem favoritesItem = new MenuItem(menuFavorites, SWT.CASCADE);
							favoritesItem.setText(samModule.getModuleName());
							favoritesItem.setMenu(firstDrop);
						}
						if (bigMenu==false){
							MenuItem firstItem = new MenuItem(menuMain, SWT.CASCADE);
							firstItem.setText(samModule.getModuleName());
							firstItem.setMenu(firstDrop);
						}
						for (int i = 0; i < secondModules.size(); i++) {
							if ("0".equals(secondModules.get(i).getModuleType())){
								//菜单2级菜单
								MenuItem secondItem = new MenuItem(firstDrop, SWT.CASCADE);
								secondItem.setText(secondModules.get(i).getModuleName());
								secondItem.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(secondModules.get(i).getModuleIcon()),18,18));
								Menu secondDrop = new Menu(shell.getShell(), SWT.DROP_DOWN);
								secondItem.setMenu(secondDrop);
								for (int j = 0; j < samModules.size(); j++) {
									if (secondModules.get(i).getModuleSeq().equals(samModules.get(j).getParentSeq())){
										if ("0".equals(samModules.get(j).getModuleType())){
											final SamModule module = samModules.get(j);
											Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
											//菜单3级菜单
											MenuItem thirdItem = new MenuItem(secondDrop, SWT.CASCADE);
											thirdItem.setText(module.getModuleName());
											thirdItem.setImage(image);
											Menu thirdDrop = new Menu(shell.getShell(), SWT.DROP_DOWN);
											thirdItem.setMenu(thirdDrop);
											for (int k = 0; k < samModules.size(); k++) {
												if (samModules.get(j).getModuleSeq().equals(samModules.get(k).getParentSeq())){
													final SamModule childModule = samModules.get(k);
													Image fourthimage = ImageZoom.getImage(ImageUtil.Base64ToBlob(childModule.getModuleIcon()),18,18);
													//菜单4级
													MenuItem fourthItem = new MenuItem(thirdDrop, SWT.POP_UP);
													fourthItem.setText(childModule.getModuleName());
													fourthItem.setImage(fourthimage);
													fourthItem.addSelectionListener(new SelectionListener() {
														@Override
														public void widgetSelected(SelectionEvent arg0) {
															//打开权限管理界面
															getModuleTab(childModule);
														}
														@Override
														public void widgetDefaultSelected(SelectionEvent arg0) {}
													});
												}
											}
										}else{
											final SamModule module = samModules.get(j);
											Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
											//菜单3级
											MenuItem thirdItem = new MenuItem(secondDrop, SWT.POP_UP);
											thirdItem.setText(samModules.get(j).getModuleName());
											thirdItem.setImage(image);
											thirdItem.addSelectionListener(new SelectionListener() {
												@Override
												public void widgetSelected(SelectionEvent arg0) {
													//打开权限管理界面
													getModuleTab(module);
												}
												@Override
												public void widgetDefaultSelected(SelectionEvent arg0) {}
											});
										}
									}
								}
							} else{
								final SamModule module = secondModules.get(i);
								//菜单2级
								MenuItem secondItem = new MenuItem(firstDrop, SWT.POP_UP);
								secondItem.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18));
								secondItem.setText(secondModules.get(i).getModuleName());
								secondItem.addSelectionListener(new SelectionListener() {
									@Override
									public void widgetSelected(SelectionEvent arg0) {
										//打开权限管理界面
										getModuleTab(module);
									}
									@Override
									public void widgetDefaultSelected(SelectionEvent arg0) {}
								});
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * createUserMenu方法描述：创建用户工具菜单
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2014-2-21 下午11:30:22
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModules
	 * @return void
	 */
	private void createUserRibbonAndBar(List<SamModule> samModules) {
		shellWidth = shell.getShell().getSize().x;
		if (null != samModules && samModules.size()>0){
			shellWidth = shell.getShell().getSize().x;
			int a = Integer.valueOf(shell.getShell().getBounds().x);
			int b = Integer.valueOf(shell.getShell().getBounds().width);
			int c = 0;

			//收藏夹菜单
			Menu menuFavorites = new Menu(shell.getShell());
			menuFolder.mMenuButton.setMenu(menuFavorites);
			//收藏夹我的工作
			createWorkMenu(menuFavorites);

			for (SamModule samModule : samModules) {
				if ("".equals(samModule.getParentSeq())
						|| "0".equals(samModule.getParentSeq())){
					if (!"1".equals(samModule.getStatus())){
						continue;
					}
					List<SamModule> secondModules = getModuleItem(samModule.getModuleSeq(),samModules);

					if (null != secondModules && secondModules.size()>0){
						/**
						 * 创建菜单
						 */
						//菜单1级菜单
						Menu firstDrop = new Menu(shell.getShell(), SWT.DROP_DOWN);
						if (bigMenu==false){
							MenuItem firstItem = new MenuItem(menuMain, SWT.CASCADE);
							firstItem.setText(samModule.getModuleName());
							firstItem.setMenu(firstDrop);
						}
						for (int i = 0; i < secondModules.size(); i++) {
							if ("0".equals(secondModules.get(i).getModuleType())){
								//菜单2级节点
								MenuItem secondItem = new MenuItem(firstDrop, SWT.CASCADE);
								secondItem.setText(secondModules.get(i).getModuleName());
								secondItem.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(secondModules.get(i).getModuleIcon()),18,18));
								Menu secondDrop = new Menu(secondItem);
								secondItem.setMenu(secondDrop);
								for (int j = 0; j < samModules.size(); j++) {
									if (secondModules.get(i).getModuleSeq().equals(samModules.get(j).getParentSeq())){
										if ("0".equals(samModules.get(j).getModuleType())){
											final SamModule module = samModules.get(j);
											Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
											//菜单3级节点
											MenuItem thirdItem = new MenuItem(secondDrop, SWT.CASCADE);
											thirdItem.setText(module.getModuleName());
											thirdItem.setImage(image);
											Menu thirdDrop = new Menu(thirdItem);
											thirdItem.setMenu(thirdDrop);
											for (int k = 0; k < samModules.size(); k++) {
												if (samModules.get(j).getModuleSeq().equals(samModules.get(k).getParentSeq())){
													final SamModule childModule = samModules.get(k);
													Image fourthimage = ImageZoom.getImage(ImageUtil.Base64ToBlob(childModule.getModuleIcon()),18,18);
													//菜单4级
													MenuItem fourthItem = new MenuItem(thirdDrop, SWT.POP_UP);
													fourthItem.setText(childModule.getModuleName());
													fourthItem.setImage(fourthimage);
													fourthItem.addSelectionListener(new SelectionListener() {
														@Override
														public void widgetSelected(SelectionEvent arg0) {
															//打开权限管理界面
															getModuleTab(childModule);
														}
														@Override
														public void widgetDefaultSelected(SelectionEvent arg0) {}
													});
												}
											}
										}else{
											final SamModule module = samModules.get(j);
											Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
											//菜单3级
											MenuItem thirdItem = new MenuItem(secondDrop, SWT.POP_UP);
											thirdItem.setText(samModules.get(j).getModuleName());
											thirdItem.setImage(image);
											thirdItem.addSelectionListener(new SelectionListener() {
												@Override
												public void widgetSelected(SelectionEvent arg0) {
													//打开权限管理界面
													getModuleTab(module);
												}
												@Override
												public void widgetDefaultSelected(SelectionEvent arg0) {}
											});
										}
									}
								}
							} else{
								final SamModule module = secondModules.get(i);
								//菜单2级
								MenuItem secondItem = new MenuItem(firstDrop, SWT.POP_UP);
								secondItem.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18));
								secondItem.setText(secondModules.get(i).getModuleName());
								secondItem.addSelectionListener(new SelectionListener() {
									@Override
									public void widgetSelected(SelectionEvent arg0) {
										//打开权限管理界面
										getModuleTab(module);
									}
									@Override
									public void widgetDefaultSelected(SelectionEvent arg0) {}
								});
							}
						}
						
						/**
						 * 创建ribbon工具栏
						 */
						RibbonButton favoritesButton = null;
						if (getToolbar(samModule)==false){
							continue;
						}
						if (c+315<a+b){
							//工具栏1级节点
							RibbonTab ribbonTab = new RibbonTab(menuFolder, samModule.getModuleName());
							ribbonTab.setData(samModule.getModuleSeq());
							c = ribbonTab.getBounds().x+a;
							RibbonGroup ribbonGroup = null;
							
							int d = 0;
							int x = 0;
							int y = 0;
							for (int i = 0; i < secondModules.size(); i++) {
								if (getToolbar(secondModules.get(i))==false){
									continue;
								}
								if (a+d+100>a+b) {
									//收藏工具栏
									if (null == favoritesButton){
										ribbonGroup = new RibbonGroup(ribbonTab, "");
										Image barImage = SWTResourceManager.getImage("images/arrow.png");
										final RibbonButton ribbonButton = new RibbonButton(ribbonGroup, barImage, "收藏夹 \n ", RibbonButton.STYLE_NO_DEPRESS|RibbonButton.STYLE_TWO_LINE_TEXT|RibbonButton.STYLE_ARROW_DOWN);
										ribbonButton.addSelectionListener(new SelectionListener() {
											public void widgetSelected(SelectionEvent e) {
												ribbonButton.setChecked(false);
												ribbonButton.setHoverButton(false);
												ribbonButton.showMenu();
											}
											public void widgetDefaultSelected(SelectionEvent e) {}
										});
										favoritesButton = ribbonButton;
									}

									//设置分隔
									if (y!=0){
										if (null != secondModules.get(i).getGroupName() 
												&& !"".equals(secondModules.get(i).getGroupName())) {
											if (!secondModules.get(i).getGroupName().equals(secondModules.get(i-1).getGroupName())){
												new MenuItem(favoritesButton.getMenu(), SWT.SEPARATOR);
											}
										} else {
											new MenuItem(favoritesButton.getMenu(), SWT.SEPARATOR);	
										}
									}
									y += 1;
									if ("0".equals(secondModules.get(i).getModuleType())){
										//工具栏2级节点
										Image barImage =  ImageZoom.getImage(ImageUtil.Base64ToBlob(secondModules.get(i).getModuleIcon()),16,16);
										if (null == barImage){
											barImage = defaultMenuImage;
										}
										MenuItem favoritesItem = new MenuItem(favoritesButton.getMenu(), SWT.CASCADE);
										favoritesItem.setText(secondModules.get(i).getModuleName());
										favoritesItem.setImage(barImage);
										Menu favoritesDrop = new Menu(ribbonGroup.getParent().getShell(), SWT.DROP_DOWN);
										favoritesItem.setMenu(favoritesDrop);
										if ("1".equals(secondModules.get(i).getSeparate())){
											new MenuItem(favoritesButton.getMenu(), SWT.SEPARATOR);
										}
										for (int j = 0; j < samModules.size(); j++) {
											if (getToolbar(samModules.get(j))==false){
												continue;
											}
											if (secondModules.get(i).getModuleSeq().equals(samModules.get(j).getParentSeq())){
												if ("0".equals(samModules.get(j).getModuleType())){
													final SamModule module = samModules.get(j);
													Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
													//工具栏3级节点
													MenuItem barItem = new MenuItem(favoritesDrop, SWT.CASCADE);
													barItem.setText(module.getModuleName());
													barItem.setImage(image);
													Menu barDrop = new Menu(ribbonGroup.getParent().getShell(), SWT.DROP_DOWN);
													barItem.setMenu(barDrop);
													if ("1".equals(samModules.get(j).getSeparate())){
														new MenuItem(favoritesButton.getMenu(), SWT.SEPARATOR);
													}
													for (int k = 0; k < samModules.size(); k++) {
														if (getToolbar(samModules.get(j))==false){
															continue;
														}
														if (samModules.get(j).getModuleSeq().equals(samModules.get(k).getParentSeq())){
															final SamModule childModule = samModules.get(k);
															Image fourthimage = ImageZoom.getImage(ImageUtil.Base64ToBlob(childModule.getModuleIcon()),18,18);
															//工具栏4级
													        final MenuItem childItem = new MenuItem(barDrop, SWT.PUSH);  
													        childItem.setText(childModule.getModuleName());
													        childItem.setImage(fourthimage);
													        childItem.addSelectionListener(new SelectionListener() {
																@Override
																public void widgetSelected(SelectionEvent arg0) {
																	//打开权限管理界面
																	getModuleTab(childModule);
																}
																@Override
																public void widgetDefaultSelected(SelectionEvent arg0) {}
															});
															if ("1".equals(samModules.get(k).getSeparate())){
																new MenuItem(barDrop, SWT.SEPARATOR);
															}
														}
													}
												}else{
													final SamModule module = samModules.get(j);
													Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
													//工具栏3级
													MenuItem menuItem = new MenuItem(favoritesDrop, SWT.POP_UP);
													menuItem.setText(samModules.get(j).getModuleName());
													menuItem.setImage(image);
													menuItem.addSelectionListener(new SelectionListener() {
														@Override
														public void widgetSelected(SelectionEvent arg0) {
															//打开权限管理界面
															getModuleTab(module);
														}
														@Override
														public void widgetDefaultSelected(SelectionEvent arg0) {}
													});
													if ("1".equals(samModules.get(j).getSeparate())){
														new MenuItem(favoritesButton.getMenu(), SWT.SEPARATOR);
													}
												}
											}
										}
									}else{
										final SamModule module = secondModules.get(i);
										//工具栏2级
										Image barImage = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),16,16);
										if (null == barImage){
											barImage = defaultMenuImage;
										}
										MenuItem menuItem = new MenuItem(favoritesButton.getMenu(), SWT.CASCADE);
										menuItem.setText(secondModules.get(i).getModuleName());
										menuItem.setImage(barImage);
										menuItem.addSelectionListener(new SelectionListener() {
											@Override
											public void widgetSelected(SelectionEvent arg0) {
												//打开权限管理界面
												getModuleTab(module);
											}
											@Override
											public void widgetDefaultSelected(SelectionEvent arg0) {}
										});
										if ("1".equals(secondModules.get(i).getSeparate())){
											new MenuItem(favoritesButton.getMenu(), SWT.SEPARATOR);
										}
									}
								}else{
									//设置分组
									if (null != secondModules.get(i).getGroupName() 
											&& !"".equals(secondModules.get(i).getGroupName())) {
										x += 1;
										if (x == 1){
											ribbonGroup = new RibbonGroup(ribbonTab,secondModules.get(i).getGroupName());
										} else if (!secondModules.get(i).getGroupName().equals(secondModules.get(i-1).getGroupName())){
											ribbonGroup = new RibbonGroup(ribbonTab, secondModules.get(i).getGroupName());
										}
									} else {
										ribbonGroup = new RibbonGroup(ribbonTab, "");
									}
									String btnName = secondModules.get(i).getModuleName();
									if (btnName.length()>4){
										btnName = btnName.substring(0,4) + " \n" + btnName.substring(4);
									}
									if ("0".equals(secondModules.get(i).getModuleType())){
										if (btnName.length()<=4){
											btnName += " \n ";
										}
										//工具栏2级节点
										Image barImage =  ImageZoom.getImage(ImageUtil.Base64ToBlob(secondModules.get(i).getModuleIcon()),32,32);
										if (null == barImage){
											barImage = defaultToolImage;
										}
										final RibbonButton ribbonButton = new RibbonButton(ribbonGroup, barImage, btnName, RibbonButton.STYLE_NO_DEPRESS|RibbonButton.STYLE_TWO_LINE_TEXT|RibbonButton.STYLE_ARROW_DOWN);//RibbonButton.STYLE_ARROW_DOWN_SPLIT);
										ribbonButton.addSelectionListener(new SelectionListener() {
											public void widgetSelected(SelectionEvent e) {
												ribbonButton.setChecked(false);
												ribbonButton.setHoverButton(false);
												ribbonButton.showMenu();
											}
											public void widgetDefaultSelected(SelectionEvent e) {}
										});
										if ("1".equals(secondModules.get(i).getSeparate())){
											new RibbonGroupSeparator(ribbonGroup);
										}
										for (int j = 0; j < samModules.size(); j++) {
											if (getToolbar(samModules.get(j))==false){
												continue;
											}
											if (secondModules.get(i).getModuleSeq().equals(samModules.get(j).getParentSeq())){
												if ("0".equals(samModules.get(j).getModuleType())){
													final SamModule module = samModules.get(j);
													Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
													//工具栏3级节点
													MenuItem barItem = new MenuItem(ribbonButton.getMenu(), SWT.CASCADE);
													barItem.setText(module.getModuleName());
													barItem.setImage(image);
													Menu barDrop = new Menu(ribbonGroup.getParent().getShell(), SWT.DROP_DOWN);  
													barItem.setMenu(barDrop);
													if ("1".equals(samModules.get(j).getSeparate())){
														new MenuItem(ribbonButton.getMenu(), SWT.SEPARATOR);
													}
													for (int k = 0; k < samModules.size(); k++) {
														if (getToolbar(samModules.get(j))==false){
															continue;
														}
														if (samModules.get(j).getModuleSeq().equals(samModules.get(k).getParentSeq())){
															final SamModule childModule = samModules.get(k);
															Image fourthimage = ImageZoom.getImage(ImageUtil.Base64ToBlob(childModule.getModuleIcon()),18,18);
															//工具栏4级
													        final MenuItem childItem = new MenuItem(barDrop, SWT.PUSH);
													        childItem.setText(childModule.getModuleName());
													        childItem.setImage(fourthimage);
													        childItem.addSelectionListener(new SelectionListener() {
																@Override
																public void widgetSelected(SelectionEvent arg0) {
																	//打开权限管理界面
																	getModuleTab(childModule);
																}
																@Override
																public void widgetDefaultSelected(SelectionEvent arg0) {}
															});
															if ("1".equals(samModules.get(k).getSeparate())){
																new MenuItem(barDrop, SWT.SEPARATOR);
															}
														}
													}
												}else{
													final SamModule module = samModules.get(j);
													Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
													//工具栏3级
													MenuItem menuItem = new MenuItem(ribbonButton.getMenu(), SWT.POP_UP);
													menuItem.setText(samModules.get(j).getModuleName());
													menuItem.setImage(image);
													menuItem.addSelectionListener(new SelectionListener() {
														@Override
														public void widgetSelected(SelectionEvent arg0) {
															//打开权限管理界面
															getModuleTab(module);
														}
														@Override
														public void widgetDefaultSelected(SelectionEvent arg0) {}
													});
													if ("1".equals(samModules.get(j).getSeparate())){
														new MenuItem(ribbonButton.getMenu(), SWT.SEPARATOR);
													}
												}
											}
										}
										if (Integer.valueOf(ribbonButton.getBounds().x)<=d){
											d += 12;
										}
										d += ribbonButton.getBounds().width;
									}else{
										final SamModule module = secondModules.get(i);
										if (btnName.length()==4){
											btnName += " ";
										}
										//工具栏2级
										Image barImage = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),32,32);
										if (null == barImage){
											barImage = defaultToolImage;
										}
										final RibbonButton ribbonButton = new RibbonButton(ribbonGroup, barImage, btnName, RibbonButton.STYLE_NO_DEPRESS|RibbonButton.STYLE_TWO_LINE_TEXT);
										ribbonButton.addSelectionListener(new SelectionListener() {
											@Override
											public void widgetSelected(SelectionEvent arg0) {
												//ribbonButton.setBottomSelected(false);
												ribbonButton.setChecked(false);
												ribbonButton.setHoverButton(false);
												//打开权限管理界面
												getModuleTab(module);
											}
											@Override
											public void widgetDefaultSelected(SelectionEvent arg0) {}
										});
										if ("1".equals(secondModules.get(i).getSeparate())){
											new RibbonGroupSeparator(ribbonGroup);
										}
										if (Integer.valueOf(ribbonButton.getBounds().x)<=10){
											d += 12;
										}
										d += ribbonButton.getBounds().width;
									}
								}
							}
						}else{
							//收藏Ribbon为菜单
							MenuItem favoritesItem = new MenuItem(menuFavorites, SWT.CASCADE);
							favoritesItem.setText(samModule.getModuleName());
							favoritesItem.setMenu(firstDrop);
						}
					}
				}
			}
		}
	}
	
	private void createWorkMenu(Menu menuFavorites){
		if (null != toolbars && toolbars.size()>0){
			Menu workDrop = new Menu(shell.getShell(), SWT.DROP_DOWN);
			MenuItem myworkItem = new MenuItem(menuFavorites, SWT.CASCADE);
			myworkItem.setText("我的收藏");
			myworkItem.setMenu(workDrop);
			for (int i = 0; i < toolbars.size(); i++) {
				final SamModule childModule = toolbars.get(i);
				Image fourthimage = ImageZoom.getImage(ImageUtil.Base64ToBlob(childModule.getModuleIcon()),18,18);
				if (!"0".equals(childModule.getModuleType())){
					final MenuItem childItem = new MenuItem(workDrop, SWT.PUSH);  
			        childItem.setText(childModule.getModuleName());
			        childItem.setImage(fourthimage);
			        childItem.addSelectionListener(new SelectionListener() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							//打开权限管理界面
							getModuleTab(childModule);
						}
						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {}
					});
				}
			}
		}
	}

	/**
	 * getToolbar方法描述：判断是否显示为工具栏
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2014-2-19 下午09:54:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param module
	 * @return boolean
	 */
	private boolean getToolbar(SamModule module) {
		if (StyleFinal.toolbarAll==true){
			return true;
		}else{
			if (StyleFinal.toolbarShow==false){
				return true;
			}
			/*if (null != toolbars && toolbars.size()>0){
				for (SamUserToolbar toolbar : toolbars) {
					if (toolbar.getModuleSeq().equals(module.getModuleSeq())){
						return true;
					}
				}
			}*/
			return true;
		}
	}

	/**
	 * createProp方法描述：创建弹出菜单
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-8 下午02:49:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param tabMenu 
	 * @param moduleSeq
	 * @param samModules void
	 */
	private void createToolbarProp(Menu tabMenu,String moduleSeq,List<SamModule> samModules){
		List<SamModule> secondModules = getModuleItem(moduleSeq,samModules);
		if (null != secondModules && secondModules.size()>0){
			for (int i = 0; i < secondModules.size(); i++) {
				if (getToolbar(secondModules.get(i))==false){
					continue;
				}
				if ("0".equals(secondModules.get(i).getModuleType())){
					//菜单2级节点
					MenuItem secondItem = new MenuItem(tabMenu, SWT.CASCADE);
					secondItem.setText(secondModules.get(i).getModuleName());
					secondItem.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(secondModules.get(i).getModuleIcon()),18,18));
					Menu secondDrop = new Menu(menuFolder.getShell(), SWT.DROP_DOWN);  
					secondItem.setMenu(secondDrop);
					if ("1".equals(secondModules.get(i).getSeparate())){
						new MenuItem(tabMenu, SWT.SEPARATOR);
					}
					List<SamModule> modules = new ArrayList<SamModule>();
					modules.add(secondModules.get(i));
					for (int j = 0; j < samModules.size(); j++) {
						if (secondModules.get(i).getModuleSeq().equals(samModules.get(j).getParentSeq())){
							if (getToolbar(samModules.get(j))==false){
								continue;
							}
							if ("0".equals(samModules.get(j).getModuleType())){
								final SamModule module = samModules.get(j);
								Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
								//菜单3级节点
								MenuItem thirdItem = new MenuItem(secondDrop, SWT.CASCADE);
								thirdItem.setText(module.getModuleName());
								thirdItem.setImage(image);
								Menu thirdDrop = new Menu(menuFolder.getShell(), SWT.DROP_DOWN);  
								thirdItem.setMenu(thirdDrop);
								if ("1".equals(samModules.get(j).getSeparate())){
									new MenuItem(secondDrop, SWT.SEPARATOR);
								}
								for (int k = 0; k < samModules.size(); k++) {
									if (samModules.get(j).getModuleSeq().equals(samModules.get(k).getParentSeq())){
										if (getToolbar(samModules.get(k))==false){
											continue;
										}
										final SamModule childModule = samModules.get(k);
										Image fourthimage = ImageZoom.getImage(ImageUtil.Base64ToBlob(childModule.getModuleIcon()),18,18);
										//菜单4级
										MenuItem fourthItem = new MenuItem(thirdDrop, SWT.POP_UP);
										fourthItem.setText(childModule.getModuleName());
										fourthItem.setImage(fourthimage);
										fourthItem.addSelectionListener(new SelectionListener() {
											@Override
											public void widgetSelected(SelectionEvent arg0) {
												//打开权限管理界面
												getModuleTab(childModule);
											}
											@Override
											public void widgetDefaultSelected(SelectionEvent arg0) {}
										});
										if ("1".equals(samModules.get(k).getSeparate())){
											new MenuItem(thirdDrop, SWT.SEPARATOR);
										}
									}
								}
							}else{
								final SamModule module = samModules.get(j);
								Image image = ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18);
								//菜单3级
								MenuItem thirdItem = new MenuItem(secondDrop, SWT.POP_UP);
								thirdItem.setText(samModules.get(j).getModuleName());
								thirdItem.setImage(image);
								thirdItem.addSelectionListener(new SelectionListener() {
									@Override
									public void widgetSelected(SelectionEvent arg0) {
										//打开权限管理界面
										getModuleTab(module);
									}
									@Override
									public void widgetDefaultSelected(SelectionEvent arg0) {}
								});
								if ("1".equals(samModules.get(j).getSeparate())){
									new MenuItem(secondDrop, SWT.SEPARATOR);
								}
							}
						}
					}
				} else{
					final SamModule module = secondModules.get(i);
					//菜单2级
					MenuItem secondItem = new MenuItem(tabMenu, SWT.POP_UP);
					secondItem.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(module.getModuleIcon()),18,18));
					secondItem.setText(secondModules.get(i).getModuleName());
					secondItem.addSelectionListener(new SelectionListener() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							//打开权限管理界面
							getModuleTab(module);
						}
						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {}
					});
					//ribbonButton.setImage(getModuleImage(module,"module"));
					if ("1".equals(secondModules.get(i).getSeparate())){
						new MenuItem(tabMenu, SWT.SEPARATOR);
					}
				}
			}
		}
	}
	
	
	/**
	 * createItem方法描述：创建功能菜单项
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-2-6 下午11:13:30
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param parentSeq
	 * @param samModules
	 * @return List<SamModule>
	 */
	private List<SamModule> getModuleItem(String parentSeq, List<SamModule> samModules){
		List<SamModule> modules = new ArrayList<SamModule>();
		if (null != samModules && samModules.size()>0){
			for (SamModule samModule : samModules) {
				List<SamModule> tmp = new ArrayList<SamModule>();
				if ("1".equals(samModule.getStatus()) && samModule.getParentSeq().equals(parentSeq)){
					//记录功能信息
					if ("0".equals(samModule.getModuleType())){
						boolean addLevel = false;
						tmp.add(samModule);
						if (null != tmp && tmp.size()>0){
							for (int i = 0; i < tmp.size(); i++) {
								for (int j = 0; j <samModules.size(); j++) {
									addLevel = false;
									if ("1".equals(samModules.get(j).getStatus())
											&& tmp.get(i).getModuleSeq().equals(samModules.get(j).getParentSeq())){
										if ("1".equals(samModules.get(j).getModuleType())){
											addLevel = true;
											break;
										}  else if ("0".equals(samModules.get(j).getModuleType())){
											tmp.add(samModules.get(j));
										}
									}
								}
								if (addLevel == true){
									modules.add(samModule);
									break;
								}
							}
						}
					}else if ("1".equals(samModule.getModuleType())){
						modules.add(samModule);
					}
				}
			}
		}
		return modules;
	}
	
	
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public static void getModuleTab(SamModule samModule) {
		try {
			final CTabFolder folder = panelUi.folder;
			folder.addCTabFolder2Listener(new CTabFolder2Adapter() {
				public void close(CTabFolderEvent cf) {
					try {
						for(int i=0;i<folder.getItemCount();i++){
							if(folder.getItems()[i].equals(cf.item)){
								if(!folder.getItems()[i].getControl().isDisposed()){
									folder.getItems()[i].getControl().dispose();
								}
								if(!cf.item.isDisposed()){
									cf.item.dispose();
								}
							}
						}
					} catch (Exception e) {
						LogUtil.operLog(e,"E",true,false);
					}
				}
			});
			CTabItem[] CItem=folder.getItems();
			//判断当前要添加到选项卡是否已存在，如存在，直接选中即可;如不存在，执行添加
			boolean isInsert = true;
			CTabItem moduleTab = null;
			for(int i=0;i<CItem.length;i++){
				//只要有一个选项卡于当前要添加的相同，则退出循环，执行不添加动作
				SamModule module = (SamModule) CItem[i].getData();
				if (null != module){
					if(samModule.getModuleSeq().equals(module.getModuleSeq())){
						isInsert = false;
						moduleTab = CItem[i];
						break;
					}
				}
			}
			if(isInsert){
				//加载类 
				Class c = Class.forName(samModule.getPackName() + samModule.getModuleClass());
				//添加选项卡
				CTabItem cTabItem = new CTabItem(folder,SWT.CLOSE);
				cTabItem.setText(samModule.getModuleName());
				cTabItem.setData(samModule);
				CommFinal.insertOperLog(samModule, "open", "打开", "1", "");
				cTabItem.setToolTipText(samModule.getModuleDesc());
				if (StyleFinal.FOLDER_SHOWIMAGE==true){
					cTabItem.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(samModule.getModuleIcon()),18,18));
				}
				//选中添加选项卡
				folder.setSelection(cTabItem);
				//重新构造该类的构造方法
				Constructor constructor = c.getConstructor(new Class[] {Composite.class,int.class,List.class});
				//通过重新构造的构造方法构造创建对象实例  
				List<SamModuleRight> moduleRights = new ArrayList<SamModuleRight>();
				if (null != rights && rights.size()>0){
					for (int i = 0; i < rights.size(); i++) {
						if (samModule.getModuleSeq().equals(rights.get(i).getModuleSeq())){
							moduleRights.add(rights.get(i));
						}
					}
				}
				Object[] intArgs = new Object[] {folder,SWT.NONE,moduleRights};
				Composite composite = (Composite) constructor.newInstance(intArgs);
				//将当前创建的数据添加到当前创建的选项卡中
				cTabItem.setControl(composite);
				Method setFocusMethod = c.getMethod("setFocus");
				if(null != setFocusMethod){
					setFocusMethod.setAccessible(true);
					setFocusMethod.invoke(composite);
				}
			}else{
				//选中已存在选项卡
				if(null != moduleTab){
					folder.setSelection(moduleTab);
				}
			}
		} catch (Exception e) {
			try {
				CommFinal.insertOperLog(samModule, "open", "打开", "0", "运行失败,功能未实现或系统报错。");
			} catch (UserBusinessException e1) {
				LogUtil.operLog(e,"E",true,
						"[" + samModule.getModuleName() + "] 运行失败。\r\n功能未实现或与管理员联系。");
			}
			LogUtil.operLog(e,"E",true,
					"[" + samModule.getModuleName() + "] 运行失败。\r\n功能未实现或与管理员联系。");
		}
	}

}
