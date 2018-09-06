
package com.zhima.frame.ui.main;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hexapixel.widgets.generic.Utils;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.StringUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.util.xml.ReadXml;
import com.zhima.util.xml.UpdateXml;
import com.zhima.widget.MsgBox;

/**
 * ConnConfigUi概要说明：数据库连接配置
 * @author lcy
 */
public class ConnConfigUi extends Dialog {
	/**
	 * 窗口按钮对应值
	 */
	private Integer SAVEIN_ID = 0;	//确定按钮
	private Integer SAVEOUT_ID = 1;	//取消按钮
	
	/**
	 * 窗口控件成员
	 */
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
	
	/**
	 * 构造函数: 构造数据库连接设置窗口
	 * @param parentShell
	 */
	protected ConnConfigUi(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * 建立数据库连接窗口
	 */
	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("数据库连接设置");
		shell.pack();
		shell.setSize(500,310);
		Utils.centerDialogOnScreen(shell);
	}
	
	protected Point getInitialSize(){
        return new Point(500,310);//500是宽400是高
    } 

	/**
	 * createDailogArea方法描述：创建窗口对象
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-11 下午01:58:13
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param parent
	 * @return Control
	 */
	protected Control createDialogArea(Composite parent) {
		Composite compo = (Composite) super.createDialogArea(parent);
		compo.setLayout(new FormLayout());
		
		Group groupConn = new Group(compo, SWT.NONE);
		groupConn.setText("数据库连接信息");
		groupConn.setLayout(new FormLayout());
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100, -5);
		groupConn.setLayoutData(data);
		
		//连接类型
		Label lbConnType = new Label(groupConn, SWT.NONE);
		lbConnType.setAlignment(SWT.RIGHT);
		lbConnType.setText("数据库类型：");
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, 15);
		lbConnType.setLayoutData(data);
		
		cboConnType = new CCombo(groupConn, SWT.BORDER | SWT.READ_ONLY);
		data = new FormData();
		data.top = new FormAttachment(lbConnType, 0, SWT.TOP);
		data.left = new FormAttachment(lbConnType, 5);
		data.right = new FormAttachment(100,-15);
		cboConnType.setLayoutData(data);
		
		cboConnType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtConnServer.setFocus();
					txtConnServer.selectAll();
				}
			}
		});
		
		Label lbConnServer = new Label(groupConn, SWT.NONE);
		lbConnServer.setAlignment(SWT.RIGHT);
		lbConnServer.setText("服务器地址：");
		data = new FormData();
		data.top = new FormAttachment(lbConnType, 12);
		data.left = new FormAttachment(lbConnType, 0, SWT.LEFT);
		data.right = new FormAttachment(lbConnType, 0, SWT.RIGHT);
		lbConnServer.setLayoutData(data);
		
		txtConnServer = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbConnServer, 0, SWT.TOP);
		data.left = new FormAttachment(cboConnType, 0, SWT.LEFT);
		data.width = 115;
		txtConnServer.setLayoutData(data);
		txtConnServer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtConnPort.setFocus();
					txtConnPort.selectAll();
				}
			}
		});
		
		Label lbConnPort = new Label(groupConn, SWT.NONE);
		lbConnPort.setText("端口：");
		data = new FormData();
		data.top = new FormAttachment(lbConnServer, 0, SWT.TOP);
		data.left = new FormAttachment(txtConnServer, 70);
		lbConnPort.setLayoutData(data);
		
		txtConnPort = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbConnPort, 0, SWT.TOP);
		data.left = new FormAttachment(lbConnPort,5);
		data.width = 60;
		txtConnPort.setLayoutData(data);
		txtConnPort.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtDbName.setFocus();
					txtDbName.selectAll();
				}
			}
		});
		
		Label lbDbName = new Label(groupConn, SWT.NONE);
		lbDbName.setAlignment(SWT.RIGHT);
		lbDbName.setText("数据库名：");
		data = new FormData();
		data.top = new FormAttachment(lbConnServer, 12);
		data.left = new FormAttachment(lbConnType, 0, SWT.LEFT);
		data.right = new FormAttachment(lbConnType, 0, SWT.RIGHT);
		lbDbName.setLayoutData(data);
		
		txtDbName = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbDbName, 0, SWT.TOP);
		data.left = new FormAttachment(cboConnType, 0, SWT.LEFT);
		data.width = 115;
		txtDbName.setLayoutData(data);
		txtDbName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtUser.setFocus();
					txtUser.selectAll();
				}
			}
		});
		
		Label lbUser = new Label(groupConn, SWT.NONE);
		lbUser.setAlignment(SWT.RIGHT);
		lbUser.setText("用户：");
		data = new FormData();
		data.top = new FormAttachment(lbDbName, 12);
		data.left = new FormAttachment(lbConnType, 0, SWT.LEFT);
		data.right = new FormAttachment(lbConnType, 0, SWT.RIGHT);
		lbUser.setLayoutData(data);
		
		txtUser = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbUser, 0, SWT.TOP);
		data.left = new FormAttachment(cboConnType, 0, SWT.LEFT);
		data.width = 115;
		txtUser.setLayoutData(data);
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtPassword.setFocus();
					txtPassword.selectAll();
				}
			}
		});
		
		Label lbPassword = new Label(groupConn, SWT.NONE);
		lbPassword.setText("密码：");
		data = new FormData();
		data.top = new FormAttachment(lbUser, 0, SWT.TOP);
		data.left = new FormAttachment(lbConnPort, 0, SWT.LEFT);
		lbPassword.setLayoutData(data);
		
		
		txtPassword = new Text(groupConn, SWT.BORDER | SWT.PASSWORD);
		data = new FormData();
		data.top = new FormAttachment(lbPassword, 0, SWT.TOP);
		data.left = new FormAttachment(txtConnPort, 0, SWT.LEFT);
		data.width = 115;
		txtPassword.setLayoutData(data);
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtMaxConn.setFocus();
					txtMaxConn.selectAll();
				}
			}
		});
		
		Label lbMaxConn = new Label(groupConn, SWT.NONE);
		lbMaxConn.setText("最大连接数：");
		data = new FormData();
		data.top = new FormAttachment(lbPassword, 25);
		data.left = new FormAttachment(lbConnType, 0, SWT.LEFT);
		lbMaxConn.setLayoutData(data);
		
		txtMaxConn = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbMaxConn, 0, SWT.TOP);
		data.left = new FormAttachment(cboConnType, 0, SWT.LEFT);
		data.width = 55;
		txtMaxConn.setLayoutData(data);
		txtMaxConn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtMinConn.setFocus();
					txtMinConn.selectAll();
				}
			}
		});
		
		Label lbMinConn = new Label(groupConn, SWT.NONE);
		lbMinConn.setText("最小连接数：");
		data = new FormData();
		data.top = new FormAttachment(lbMaxConn, 0, SWT.TOP);
		data.left = new FormAttachment(txtMaxConn, 10, SWT.RIGHT);
		lbMinConn.setLayoutData(data);
		
		txtMinConn = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbMinConn, 0, SWT.TOP);
		data.left = new FormAttachment(lbMinConn, 5, SWT.RIGHT);
		data.width = 55;
		txtMinConn.setLayoutData(data);
		txtMinConn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtActive.setFocus();
					txtActive.selectAll();
				}
			}
		});
		
		Label lbActive = new Label(groupConn, SWT.NONE);
		lbActive.setText("可用连接数：");
		data = new FormData();
		data.top = new FormAttachment(lbMaxConn, 0, SWT.TOP);
		data.left = new FormAttachment(txtMinConn, 10, SWT.RIGHT);
		lbActive.setLayoutData(data);
		
		txtActive = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbActive, 0, SWT.TOP);
		data.left = new FormAttachment(lbActive, 5, SWT.RIGHT);
		data.width = 55;
		txtActive.setLayoutData(data);
		txtActive.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtActieTime.setFocus();
					txtActieTime.selectAll();
				}
			}
		});
		
		Label lbActiveTime = new Label(groupConn, SWT.NONE);
		lbActiveTime.setAlignment(SWT.RIGHT);
		lbActiveTime.setText("活动时间：");
		data = new FormData();
		data.top = new FormAttachment(lbMaxConn, 12);
		data.left = new FormAttachment(lbConnType, 0, SWT.LEFT);
		data.right = new FormAttachment(lbMaxConn, 0, SWT.RIGHT);
		lbActiveTime.setLayoutData(data);
		
		txtActieTime = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbActiveTime, 0, SWT.TOP);
		data.left = new FormAttachment(txtMaxConn, 0, SWT.LEFT);
		data.width = 55;
		txtActieTime.setLayoutData(data);
		txtActieTime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtSleepTime.setFocus();
					txtSleepTime.selectAll();
				}
			}
		});
		
		Label lbSleepTime = new Label(groupConn, SWT.NONE);
		lbSleepTime.setAlignment(SWT.RIGHT);
		lbSleepTime.setText("睡眠时间：");
		data = new FormData();
		data.top = new FormAttachment(lbActiveTime, 0, SWT.TOP);
		data.left = new FormAttachment(lbMinConn, 0, SWT.LEFT);
		data.right = new FormAttachment(lbMinConn, 0, SWT.RIGHT);
		lbSleepTime.setLayoutData(data);
		
		txtSleepTime = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbActiveTime, 0, SWT.TOP);
		data.left = new FormAttachment(txtMinConn, 0, SWT.LEFT);
		data.width = 55;
		txtSleepTime.setLayoutData(data);
		txtSleepTime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == SWT.KEYPAD_CR){
					txtLifeTime.setFocus();
					txtLifeTime.selectAll();
				}
			}
		});
		
		Label lbLifeTime = new Label(groupConn, SWT.NONE);
		lbLifeTime.setAlignment(SWT.RIGHT);
		lbLifeTime.setText("寿命时间：");
		data = new FormData();
		data.top = new FormAttachment(lbActiveTime, 0, SWT.TOP);
		data.left = new FormAttachment(lbActive, 0, SWT.LEFT);
		data.right = new FormAttachment(lbActive, 0, SWT.RIGHT);
		lbLifeTime.setLayoutData(data);
		
		txtLifeTime = new Text(groupConn, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(lbActiveTime, 0, SWT.TOP);
		data.left = new FormAttachment(txtActive, 0, SWT.LEFT);
		data.width = 55;
		txtLifeTime.setLayoutData(data);
		initInfo();
		return parent;
	}
	
	/**
	 * 创建并替换窗口缺省按钮
	 */
	protected void createButtonsForButtonBar(Composite parent){
		GridData data = new GridData(StyleFinal.DIALOGBAR_ALIGNMENT);
		data.heightHint=55;
		
		parent.setLayoutData(data);
		//创建保存按钮
		Button confirm = createButton(parent, SAVEIN_ID,"确定(&O)",false);
		confirm.setFont(StyleFinal.SYS_FONT);
		
		//Button bt_tmp= createButton(parent, SAVEOUT_ID,"",false);
		//bt_tmp.setVisible(false);
		
		//创建退出按钮
		Button cancel = createButton(parent,SAVEOUT_ID,"取消(&C)",false);
		cancel.setFont(StyleFinal.SYS_FONT);
		if (StyleFinal.BTN_SHOWIMAGE == true){
			confirm.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_OK));
			cancel.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_CANCEL));
		}
		
	}

	/**
	 * 按钮点击事件
	 */
	protected void buttonPressed(int buttonId){
		try {
			if(SAVEIN_ID == buttonId){
				updateInfo();
				close();
			} else if (SAVEOUT_ID == buttonId) {
				close();
			}
		} catch (Exception e) {
			MsgBox.warning(this.getShell(),e.getMessage());
			return;
		}
	}
	
	/**
	 * initInfo方法描述：初始化窗口对象信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-12 上午10:20:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	@SuppressWarnings("static-access")
	private void initInfo(){
		//数据连接配置文件
		try {
			String connFile = System.getProperty("user.dir") + "\\proxool.xml";
			cboConnType.add("com.mysql.jdbc.Driver");
			cboConnType.add("oracle.jdbc.OracleDriver");
			ReadXml readXml = new ReadXml();
			int index = readXml.getNodesIndex(connFile, "proxool-config/proxool/alias","traffic");
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
		} catch (Exception e) {
			LogUtil.operLog(e, "E", true, true);
		}
	}
	
	/**
	 * updateInfo方法描述：确认更新
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-12 下午04:28:10
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	@SuppressWarnings("static-access")
	private void updateInfo(){
		try {
			String connFile = System.getProperty("user.dir") + "\\proxool.xml";
			UpdateXml updateXml = new UpdateXml();
			int index = updateXml.getNodesIndex(connFile, "proxool-config/proxool/alias","traffic");
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
		} catch (IOException e) {
			LogUtil.operLog(e, "E", true, true);
		} catch (DocumentException e) {
			LogUtil.operLog(e, "E", true, true);
		} catch (Exception e) {
			LogUtil.operLog(e, "E", true, true);
		}
	}	
	
}
