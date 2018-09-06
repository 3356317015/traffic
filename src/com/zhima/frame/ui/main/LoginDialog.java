package com.zhima.frame.ui.main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;

import com.hexapixel.widgets.generic.Utils;
import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.basic.jdbc.PoolManager;
import com.zhima.frame.action.login.ILogin;
import com.zhima.frame.action.login.impl.ImpLogin;
import com.zhima.frame.action.sam.ISamUpgrade;
import com.zhima.frame.action.sam.impl.ImpSamUpgrade;
import com.zhima.frame.drop.DropOrganize;
import com.zhima.frame.model.SamOrganize;
import com.zhima.util.DateUtils;
import com.zhima.util.DesUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.util.xml.UpdateXml;
import com.zhima.widget.MsgBox;

/**
 * LoginDialog概要说明：用户登录
 * @author lcy
 */
public class LoginDialog {

	protected static Display loginDis;
	protected static Shell shell;
	private static Font font = SWTResourceManager.getFont("宋体", 11, SWT.NONE);
	
	private static DropOrganize dropOrganize; //组织机构
	private static Text txtUser;	//用户
	private static Text txtPass;	//密码
	
	private static Button btnRempwd;
	private static Button btnAuto;

	/**
	 * Open the dialog.
	 * @return the result
	 * @wbp.parser.entryPoint
	 */
	public static void open(Display display) {
		try {
			loginDis = display;
			shell = new Shell(loginDis,SWT.DIALOG_TRIM);
			createContents();
			Utils.centerDialogOnScreen(shell.getShell());
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			ILogin iLogin = new ImpLogin();
			iLogin.close(CommFinal.user.getOnlineSeq(), CommFinal.organize.getOrganizeSeq(),
					CommFinal.user.getUserCode(), CommFinal.user.getUserName(),CommFinal.initConfig());
			display.dispose();
			System.exit(0);
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}

	}

	/**
	 * Create contents of the dialog.
	 */
	private static void createContents() {
		try{
			shell.setSize(828, 525);
			shell.setText("客运快件管理系统－用户登录");
			shell.setLayout(new FormLayout());

			shell.setImage(SWTResourceManager.getImage("images/login/express.ico"));
			
			final Composite compLogo = new Composite(shell, SWT.NONE);
			compLogo.setBackgroundMode(SWT.INHERIT_FORCE);
			compLogo.setLayout(new FormLayout());
			FormData data = new FormData();
			data.top = new FormAttachment(0);
			data.left = new FormAttachment(0);
			data.right = new FormAttachment(100);
			data.bottom = new FormAttachment(100);
			compLogo.setLayoutData(data);
			//Image barImage = ImageZoom.getImage(,800,455);
			compLogo.setBackgroundImage(SWTResourceManager.getImage("images/login/logo.png"));
			
			/*Label lbApp = new Label(compLogo, SWT.NONE|SWT.CENTER);
			lbApp.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
			lbApp.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));//COLOR_DARK_CYAN//COLOR_DARK_GREEN
			data = new FormData();
			data.top = new FormAttachment(70);
			data.left = new FormAttachment(20);
			lbApp.setLayoutData(data);
			lbApp.setText("客运快件管理系统");*/

			final Label lbSetup = new Label(compLogo,SWT.NONE);
			lbSetup.setText("设 置");
			lbSetup.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
			lbSetup.setForeground(SWTResourceManager.getColor(239,228,176));
			data = new FormData();
			data.top = new FormAttachment(0, 55);
			data.left = new FormAttachment(0, 55);
			lbSetup.setLayoutData(data);
			lbSetup.addMouseTrackListener(new MouseTrackAdapter() {
				@Override
				public void mouseEnter(MouseEvent e) {
					lbSetup.setForeground(SWTResourceManager.getColor(181,230,29));
				}
				@Override
				public void mouseExit(MouseEvent e) {
					lbSetup.setForeground(SWTResourceManager.getColor(239,228,176));
				}
			});
			lbSetup.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					Menu popupMenu = new Menu(lbSetup);
					
					MenuItem menuRegister = new MenuItem(popupMenu, SWT.POP_UP);
					menuRegister.setText("软件注册");
					//menuConfig.setImage(SWTResourceManager.getImage(System.getProperty("user.dir") + "\\images\\login\\menu_config.gif"));
					menuRegister.setImage(SWTResourceManager.getImage("images/login/register.gif"));
					menuRegister.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							RegisterUi registerUi = new RegisterUi(shell);
							registerUi.open();
						}
					});
					
					MenuItem menuConfig = new MenuItem(popupMenu, SWT.POP_UP);
					menuConfig.setText("个性化");
					//menuConfig.setImage(SWTResourceManager.getImage(System.getProperty("user.dir") + "\\images\\login\\menu_config.gif"));
					menuConfig.setImage(SWTResourceManager.getImage("images/login/menu_config.gif"));
					menuConfig.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							IndividuationUi individuationUi = new IndividuationUi(shell);
							individuationUi.open();
						}
					});
					
					MenuItem menuConn = new MenuItem(popupMenu, SWT.POP_UP);
					menuConn.setText("数据库连接");
					//menuConn.setImage(SWTResourceManager.getImage(System.getProperty("user.dir") + "\\images\\login\\menu_conn.gif"));
					menuConn.setImage(SWTResourceManager.getImage("images/login/menu_conn.gif"));
					menuConn.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							try {
								ConnConfigUi connConfigUi = new ConnConfigUi(shell);
								connConfigUi.open();
								//移除连接池
								if (null != PoolHandler.pool){
									/*ProxoolFacade.removeConnectionPool(PoolAlias.Traffic);
									ProxoolFacade.removeConnectionPool(PoolAlias.Insurance);
									ProxoolFacade.removeConnectionPool(PoolAlias.Voice);*/
									String aliases[] = ProxoolFacade.getAliases();
									if (null != aliases && aliases.length>0){
										for (int i = 0; i < aliases.length; i++) {
											ProxoolFacade.removeConnectionPool(aliases[i]);
										}
									}
								}
								//设置连接池
								PoolHandler.pool = new PoolManager("proxool.xml");
							} catch (ProxoolException e1) {
								LogUtil.operLog(e1,"E",true,true);
							}
						}
					});
					
					lbSetup.setMenu(popupMenu);
					popupMenu.setLocation(shell.getLocation().x+lbSetup.getLocation().x, 
							shell.getLocation().y+lbSetup.getLocation().y+45);
					popupMenu.setVisible(true);
				}
			});
			
			Label lbVal = new Label(compLogo, SWT.NONE|SWT.RIGHT);
			lbVal.setForeground(SWTResourceManager.getColor(100,100,0));
			lbVal.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
			data = new FormData();
			data.bottom = new FormAttachment(100, -20);
			data.left = new FormAttachment(0, 25);
			lbVal.setLayoutData(data);
			lbVal.setText("V-" + StyleFinal.SYS_VERSION);
			
			//组织机构
			dropOrganize = new DropOrganize(compLogo, SWT.BORDER);
			dropOrganize.initType("1");
			dropOrganize.droptext.txtShow.setMessage("组织机构");
			CallMethod callMethod = new CallMethod();
			dropOrganize.droptext.txtShow.addFocusListener(callMethod.focusAdapter(dropOrganize.droptext.txtShow));
			dropOrganize.droptext.txtShow.setFont(font);
			data = new FormData();
			data.top = new FormAttachment(55);
			data.left = new FormAttachment(55);
			data.width = 215;
			dropOrganize.setLayoutData(data);
			dropOrganize.droptext.txtShow.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
						txtUser.setFocus();
						txtUser.selectAll();
					}
				}
			});
			
			
			//用户名
			txtUser = new Text(compLogo, SWT.BORDER);
			txtUser.setMessage("用户名");
			txtUser.addFocusListener(callMethod.focusAdapter(txtUser));
			data = new FormData();
			data.top = new FormAttachment(dropOrganize,15);
			data.left = new FormAttachment(55);
			data.right = new FormAttachment(dropOrganize, 0, SWT.RIGHT);
			txtUser.setLayoutData(data);
			txtUser.setFont(font);
			txtUser.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
						txtPass.setFocus();
						txtPass.selectAll();
					}
				}
			});
			
			//密码		
			txtPass = new Text(compLogo, SWT.BORDER | SWT.PASSWORD);
			txtPass.setMessage("密码");
			txtPass.addFocusListener(callMethod.focusAdapter(txtPass));
			data = new FormData();
			data.top = new FormAttachment(txtUser, 15);
			data.left = new FormAttachment(txtUser, 0, SWT.LEFT);
			data.right = new FormAttachment(dropOrganize, 0, SWT.RIGHT);
			txtPass.setLayoutData(data);
			txtPass.setFont(font);
			txtPass.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
						userLogin();
					}
				}
			});
			
			btnRempwd = new Button(compLogo, SWT.NONE|SWT.CHECK);
			btnRempwd.setText("记住密码");
			data = new FormData();
			data.top = new FormAttachment(txtPass, 40, SWT.TOP);
			data.left = new FormAttachment(txtPass, 10 ,SWT.LEFT);
			btnRempwd.setLayoutData(data);
			btnRempwd.setSelection(StyleFinal.LOGIN_BACKPASS);
			
			
			btnAuto = new Button(compLogo, SWT.NONE|SWT.CHECK);
			btnAuto.setText("自动登录");
			data = new FormData();
			data.top = new FormAttachment(btnRempwd, 0, SWT.TOP);
			data.right = new FormAttachment(txtPass, -5, SWT.RIGHT);
			btnAuto.setLayoutData(data);
			btnAuto.setSelection(StyleFinal.LOGIN_AUTOLOGIN);
			
			btnRempwd.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if (btnRempwd.getSelection()==false){
						btnAuto.setSelection(false);
					}
				}
			});
			btnAuto.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if (btnAuto.getSelection()==true){
						btnRempwd.setSelection(true);
					}
				}
			});
			

			//进入系统
			Button btnLogin = new Button(compLogo, SWT.NONE);
			btnLogin.setFont(font);
			btnLogin.setText("登 录");
			data = new FormData();
			data.top = new FormAttachment(btnRempwd, 10);
			data.left = new FormAttachment(txtPass, -2, SWT.LEFT);
			data.width = 80;
			btnLogin.setLayoutData(data);
			btnLogin.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					userLogin();
				}
			});
			
			Button btnCancel = new Button(compLogo, SWT.NONE);
			btnCancel.setFont(font);
			btnCancel.setText("取 消");
			data = new FormData();
			data.top = new FormAttachment(btnLogin, 0, SWT.TOP);
			data.right = new FormAttachment(txtPass, 0, SWT.RIGHT);
			data.width = 80;
			btnCancel.setLayoutData(data);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			if (StyleFinal.LOGIN_BACKPASS==true){
				dropOrganize.droptext.setValue(StyleFinal.LOGIN_ORGANIZE);
				txtUser.setText(StyleFinal.LOGIN_USERCODE);
				txtPass.setText(StyleFinal.LOGIN_PASSWORD);
			}
			if (StyleFinal.LOGIN_AUTOLOGIN==true){
				if (StyleFinal.LOGIN_LOGOUT==false){
					userLogin();
				}
			}
			
			dropOrganize.droptext.txtShow.forceFocus();
			dropOrganize.droptext.txtShow.selectAll();
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		
	}
	
	/**
	 * userLogin方法描述：用户登录
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午03:30:30
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public static void userLogin(){
		try {
			if (null == dropOrganize.droptext.getValue() || "".equals(dropOrganize.droptext.getValue())){
				MsgBox.warning(shell, "请输入或选择登录车站。");
				dropOrganize.droptext.txtShow.setFocus();
				dropOrganize.droptext.txtShow.selectAll();
				return;
			}
			if (null == txtUser.getText() || "".equals(txtUser.getText())){
				MsgBox.warning(shell, "请输入登录帐号。");
				txtUser.setFocus();
				txtUser.selectAll();
				return;
			} 
			//验证用户
			CommFinal.organize = (SamOrganize) dropOrganize.droptext.getObject();
			ILogin iLogin = new ImpLogin();
			iLogin.login(dropOrganize.droptext.getValue(), txtUser.getText(), txtPass.getText(),
					StyleFinal.SYS_VERSION, CommFinal.CLIENT_ADDRESS,CommFinal.initConfig());
			//检测新版本
			ISamUpgrade iSamUpgrade = new ImpSamUpgrade();
			if (iSamUpgrade.queryUpgradeByVer(CommFinal.organize.getOrganizeSeq(),StyleFinal.SYS_VERSION)>0){
				if (MsgBox.confirm(shell, "检测到系统升级文件，确定是否要升级。")==SWT.YES){
					shell.dispose();
					System.exit(0);
				}
			}
			//登录记录
			String connFile = System.getProperty("user.dir") + "\\config_system.xml";
			UpdateXml updateXml = new UpdateXml();
			if (StyleFinal.LOGIN_BACKPASS!=btnRempwd.getSelection()){
				StyleFinal.LOGIN_BACKPASS = btnRempwd.getSelection();
				if (btnRempwd.getSelection()==true){
					updateXml.updateXml(connFile, "system-config/login/backpass","true");
					DesUtils desUtils = new DesUtils();
					if (!dropOrganize.droptext.getValue().equals(StyleFinal.LOGIN_ORGANIZE)){
						updateXml.updateXml(connFile, "system-config/login/organize",desUtils.getEncString(dropOrganize.droptext.getValue()));
					}
					if (!txtUser.getText().equals(StyleFinal.LOGIN_USERCODE)){
						updateXml.updateXml(connFile, "system-config/login/usercode",desUtils.getEncString(txtUser.getText()));
					}
					if (!txtPass.getText().equals(StyleFinal.LOGIN_PASSWORD)){
						updateXml.updateXml(connFile, "system-config/login/password",desUtils.getEncString(txtPass.getText()));
					}
					StyleFinal.LOGIN_ORGANIZE = dropOrganize.droptext.getValue();
					StyleFinal.LOGIN_USERCODE = txtUser.getText();
					StyleFinal.LOGIN_PASSWORD = txtPass.getText();
				} else {
					updateXml.updateXml(connFile, "system-config/login/backpass","false");
					updateXml.updateXml(connFile, "system-config/login/usercode","");
					updateXml.updateXml(connFile, "system-config/login/password","");
					StyleFinal.LOGIN_ORGANIZE = "";
					StyleFinal.LOGIN_USERCODE = "";
					StyleFinal.LOGIN_PASSWORD = "";
				}
			}
			if (StyleFinal.LOGIN_AUTOLOGIN!=btnAuto.getSelection()){
				StyleFinal.LOGIN_AUTOLOGIN = btnAuto.getSelection();
				if (btnAuto.getSelection()==true){
					updateXml.updateXml(connFile, "system-config/login/autologin","true");
				} else {
					updateXml.updateXml(connFile, "system-config/login/autologin","false");
				}
			}
			
			//同步服务器时间
			DateUtils.setComputerDate(iLogin.getServerTime());
			//登录
			shell.dispose();
			MainUi mainUi = new MainUi();
			mainUi.open(loginDis);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
			if (!shell.isDisposed()){
				txtUser.setFocus();
				txtUser.selectAll();
			}
		}
	}
}