package com.zhima.basic;

import java.io.File;
import java.net.InetAddress;
import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import com.zhima.basic.exception.UserSystemException;
import com.zhima.util.DesUtils;
import com.zhima.util.NetworkUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.xml.ReadXml;

import dog.ActiveKey;

public class StyleFinal {
	/**
	 * 系统号
	 */
	public static int SYS_NUMBER = 1001;
	
	public static String SYS_KEY = "";
	//AV8XL-28J6K-4B5MA-ZC1AW-G9OSH
	//IT5K5-DXDR8-Y6VUV-4Q4KG-EX9O9
	
	public static String SYS_VERSION = "";
	/**
	 * 用户登录
	 */
	public static boolean LOGIN_LOGOUT = false;
	public static boolean LOGIN_BACKPASS = false;
	public static String LOGIN_ORGANIZE = "";
	public static String LOGIN_USERCODE = "";
	public static String LOGIN_PASSWORD = "";
	public static boolean LOGIN_AUTOLOGIN = false;
	
	/**
	 * 系统字体
	 */
	public static String SYS_FONTSTYLE = "宋体";
	public static int SYS_FONTSIZE = 11;
	public static int SYS_FONTBOLD = 1;
	public static Font SYS_FONT = SWTResourceManager.getFont(SYS_FONTSTYLE, SYS_FONTSIZE, SYS_FONTBOLD);
	
	/**
	 * 显示工具栏
	 */
	public static boolean toolbarShow = true;
	
	public static boolean toolbarAll = false;
	
	/**
	 * 系统选项卡字体
	 */
	public static String FOLDER_FONTSTYLE = "宋体";
	public static int FOLDER_FONTSIZE = 11;
	public static int FOLDER_FONTBOLD = 1;
	public static Font FOLDER_FONT = SWTResourceManager.getFont(FOLDER_FONTSTYLE, FOLDER_FONTSIZE, FOLDER_FONTBOLD);
	public static boolean FOLDER_SHOWIMAGE = true;
	
	/**
	 * 按钮字体
	 */
	public static String BTN_FONTSTYLE = "宋体";
	public static int BTN_FONTSIZE = 11;
	public static int BTN_FONTBOLD = 1;
	public static Font BTN_FONT = SWTResourceManager.getFont(BTN_FONTSTYLE, BTN_FONTSIZE, BTN_FONTBOLD);
	public static int BTN_HEIHGT = 27;
	public static int BTN_WIDTH = 80;
	//显示按钮图片
	public static boolean BTN_SHOWIMAGE = true;
	//弹出窗口工具栏对齐方式
	public static int DIALOGBAR_ALIGNMENT = 64;//128居右
	
	public static final Color DIALOG_BACK_GROUND = SWTResourceManager.getColor(255, 255, 255);//240,240,240//128,128,255
	
	/**
	 * 表格字体
	 */
	public static String GRID_FONTSTYLE = "宋体";
	public static int GRID_FONTSIZE = 11;
	public static int GRID_FONTBOLD = 0;
	public static Font GRID_FONT = SWTResourceManager.getFont(GRID_FONTSTYLE, GRID_FONTSIZE, GRID_FONTBOLD);
	
	/**
	 * 表格背景色
	 */
	public static final Color GRID_BACKGROUND = SWTResourceManager.getColor(255, 255, 255);
	
	/**
	 * 表格前景色
	 */
	public static final Color GRID_FOREGROUND = SWTResourceManager.getColor(0, 0, 0);
	
	/**
	 * 得到焦点控制背景颜色
	 */
	public static final Color FOCUS_BACKGROUND = SWTResourceManager.getColor(166, 200, 230);
	/**
	 * Grid布局间距
	 */
	public static final int VERTICAL_SPACING = 7;
	/**
	 * 文本框高度
	 */
	public static final int TEXT_HEIGHT = 15;
	/**
	 * 下拉框高度
	 */
	public static final int DROP_HEIGHT = 18;
	
	
	@SuppressWarnings({ "static-access" })
	public static void initializeConfig(){
		try {
			//获取客户端MAC
			ActiveKey activeKey = new ActiveKey();
			List<String> macs = activeKey.getValidAddress();
			if (null != macs && macs.size()>0){
				for (int i = 0; i < macs.size(); i++) {
					CommFinal.CLIENT_MAC = macs.get(i);
				}
			}
			//获取客户端IP地址
			InetAddress localIp = NetworkUtils.getLocalHostAddress();
			if (null != localIp){
				CommFinal.CLIENT_ADDRESS = localIp.toString();
			}
			
			//初始化系统样式
			String styleFile = System.getProperty("user.dir") + "\\config_system.xml";
			if(new File(styleFile).exists()){
				ReadXml readXml = new ReadXml();
				//系统版本号
				readXml.readElement(styleFile, "system-config/version");
				String sys_version = readXml.strData;
				if (null != sys_version || !"".equals(sys_version)){
					SYS_VERSION=sys_version;
				}
				
				//注册码
				readXml.readElement(styleFile, "system-config/key");
				String sys_key = readXml.strData;
				if (null != sys_key || !"".equals(sys_key)){
					SYS_KEY=sys_key;
				}
				
				//连接模式
				readXml.readElement(styleFile, "system-config/connectmode");
				String connectmode = readXml.strData;
				CommFinal.CONNECT_MODE=Integer.valueOf(connectmode);
				//客户端类型
				readXml.readElement(styleFile, "system-config/clienttype");
				String clienttype = readXml.strData;
				CommFinal.CLIENT_TYPE=Integer.valueOf(clienttype);
				if (null != sys_key || !"".equals(sys_key)){
					if (2==CommFinal.CONNECT_MODE){
						//初始化连接服务
						String serviceFile = System.getProperty("user.dir") + "\\service.xml";
						if(new File(serviceFile).exists()){
							//连接用户
							readXml.readProperty(serviceFile, "service-config/service/user-properties","property","user");
							CommFinal.CLIENT_USERCODE=readXml.strData;
							//连接密码
							readXml.readProperty(serviceFile, "service-config/service/user-properties","property","password");
							CommFinal.CLIENT_PASSWORD=readXml.strData;
							//超时时间
							readXml.readElement(serviceFile, "service-config/service/timeout");
							CommFinal.TIME_OUT=Integer.valueOf(readXml.strData);
							//测试连接
							readXml.readElement(serviceFile, "service-config/service/telnet-conn");
							CommFinal.TELNEET_CONN=readXml.strData;
							//SAM SERVER
							readXml.readProperty(serviceFile, "service-config/service/services/sam-properties","property","namespace");
							SamFinal.ServerNamespace=readXml.strData;
							readXml.readProperty(serviceFile, "service-config/service/services/sam-properties","property","address");
							SamFinal.ServerAddres=readXml.strData;
							
							//EPD SERVER
							readXml.readProperty(serviceFile, "service-config/service/services/epd-properties","property","namespace");
							EpdFinal.ServerNamespace=readXml.strData;
							readXml.readProperty(serviceFile, "service-config/service/services/epd-properties","property","address");
							EpdFinal.ServerAddres=readXml.strData;
							
							//ITS SERVER
							readXml.readProperty(serviceFile, "service-config/service/services/its-properties","property","namespace");
							ItsFinal.ServerNamespace=readXml.strData;
							readXml.readProperty(serviceFile, "service-config/service/services/its-properties","property","address");
							ItsFinal.ServerAddres=readXml.strData;
						}
					}
				}
				
				//用户登录
				readXml.readElement(styleFile, "system-config/login/backpass");
				String login_backpass = readXml.strData;
				if (null != login_backpass || !"".equals(login_backpass)){
					if ("true".equals(login_backpass)){
						LOGIN_BACKPASS=true;
						DesUtils desUtils = new DesUtils();

						readXml.readElement(styleFile, "system-config/login/organize");
						String organize = readXml.strData;
						if (null != organize || !"".equals(organize)){
							LOGIN_ORGANIZE=desUtils.getDesString(organize);
						}
						readXml.readElement(styleFile, "system-config/login/usercode");
						String login_usercode = readXml.strData;
						if (null != login_usercode || !"".equals(login_usercode)){
							LOGIN_USERCODE=desUtils.getDesString(login_usercode);
						}
						readXml.readElement(styleFile, "system-config/login/password");
						String login_password = readXml.strData;
						if (null != login_password || !"".equals(login_password)){
							LOGIN_PASSWORD=desUtils.getDesString(login_password);
						}
					}else{
						LOGIN_BACKPASS=false;
					}
				}
				
				readXml.readElement(styleFile, "system-config/login/autologin");
				String login_autologin = readXml.strData;
				if (null != login_autologin || !"".equals(login_autologin)){
					if ("true".equals(login_autologin)){
						LOGIN_AUTOLOGIN=true;
					}else{
						LOGIN_AUTOLOGIN=false;
					}
				}
				//启用工具栏
				readXml.readElement(styleFile, "system-config/style/image/toolbarshow");
				String toolbar_show = readXml.strData;
				if (null != toolbar_show || !"".equals(toolbar_show)){
					if ("true".equals(toolbar_show)){
						toolbarShow=true;
					}else{
						toolbarShow=false;
					}
				}
				//显示选项卡图片
				readXml.readElement(styleFile, "system-config/style/image/foldershowimage");
				String folder_showimage = readXml.strData;
				if (null != folder_showimage || !"".equals(folder_showimage)){
					if ("true".equals(folder_showimage)){
						FOLDER_SHOWIMAGE=true;
					}else{
						FOLDER_SHOWIMAGE=false;
					}
				}
				//显示按钮图片
				readXml.readElement(styleFile, "system-config/style/image/btnshowimage");
				String btn_showimage = readXml.strData;
				if (null != btn_showimage || !"".equals(btn_showimage)){
					if ("true".equals(btn_showimage)){
						BTN_SHOWIMAGE=true;
					}else{
						BTN_SHOWIMAGE=false;
					}
				}
				//系统字体
				readXml.readElement(styleFile, "system-config/style/font/sysfont/name");
				String sys_fontstyle = readXml.strData;
				if (null != sys_fontstyle || !"".equals(sys_fontstyle)){
					SYS_FONTSTYLE=sys_fontstyle;
				}
				
				readXml.readElement(styleFile, "system-config/style/font/sysfont/size");
				String sys_fontsize = readXml.strData;
				if (null != sys_fontsize || !"".equals(sys_fontsize)){
					SYS_FONTSIZE=Integer.valueOf(sys_fontsize);
				}
				readXml.readElement(styleFile, "system-config/style/font/sysfont/bold");
				String sys_fontbold = readXml.strData;
				if (null != sys_fontbold || !"".equals(sys_fontbold)){
					if ("true".equals(sys_fontbold)){
						SYS_FONTBOLD =1;
					}else{
						SYS_FONTBOLD =0;
					}
				}
				SYS_FONT=SWTResourceManager.getFont(SYS_FONTSTYLE, SYS_FONTSIZE, SYS_FONTBOLD);

				//选项卡字体
				readXml.readElement(styleFile, "system-config/style/font/folderfont/name");
				String folder_fontstyle = readXml.strData;
				if (null != folder_fontstyle || !"".equals(folder_fontstyle)){
					FOLDER_FONTSTYLE=folder_fontstyle;
				}
				readXml.readElement(styleFile, "system-config/style/font/folderfont/size");
				String folder_fontsize = readXml.strData;
				if (null != folder_fontsize || !"".equals(folder_fontsize)){
					FOLDER_FONTSIZE=Integer.valueOf(folder_fontsize);
				}
				readXml.readElement(styleFile, "system-config/style/font/folderfont/bold");
				String folder_fontbold = readXml.strData;
				if (null != folder_fontbold || !"".equals(folder_fontbold)){
					if ("true".equals(folder_fontbold)){
						FOLDER_FONTBOLD =1;
					}else{
						FOLDER_FONTBOLD =0;
					}
				}
				FOLDER_FONT=SWTResourceManager.getFont(FOLDER_FONTSTYLE, FOLDER_FONTSIZE, FOLDER_FONTBOLD);
				
				//表格字体
				readXml.readElement(styleFile, "system-config/style/font/gridfont/name");
				String grid_fontstyle = readXml.strData;
				if (null != grid_fontstyle || !"".equals(grid_fontstyle)){
					GRID_FONTSTYLE = grid_fontstyle;
				}
				readXml.readElement(styleFile, "system-config/style/font/gridfont/size");
				String grid_fontsize = readXml.strData;
				if (null != grid_fontsize || !"".equals(grid_fontsize)){
					GRID_FONTSIZE = Integer.valueOf(grid_fontsize);
				}
				readXml.readElement(styleFile, "system-config/style/font/gridfont/bold");
				String grid_fontbold = readXml.strData;
				if (null != grid_fontbold || !"".equals(grid_fontbold)){
					if ("true".equals(grid_fontbold)){
						GRID_FONTBOLD =1;
					}else{
						GRID_FONTBOLD =0;
					}
				}
				GRID_FONT=SWTResourceManager.getFont(GRID_FONTSTYLE, GRID_FONTSIZE, GRID_FONTBOLD);

				//按钮字体
				readXml.readElement(styleFile, "system-config/style/font/btnfont/name");
				String btn_fontstyle = readXml.strData;
				if (null != btn_fontstyle || !"".equals(btn_fontstyle)){
					BTN_FONTSTYLE = btn_fontstyle;
				}
				readXml.readElement(styleFile, "system-config/style/font/btnfont/size");
				String btn_fontsize = readXml.strData;
				if (null != btn_fontsize || !"".equals(btn_fontsize)){
					BTN_FONTSIZE = Integer.valueOf(btn_fontsize);
				}
				readXml.readElement(styleFile, "system-config/style/font/btnfont/bold");
				String btn_fontbold = readXml.strData;
				if (null != btn_fontbold || !"".equals(btn_fontbold)){
					if ("true".equals(btn_fontbold)){
						BTN_FONTBOLD =1;
					}else{
						BTN_FONTBOLD =0;
					}
				}
				BTN_FONT=SWTResourceManager.getFont(BTN_FONTSTYLE, BTN_FONTSIZE, BTN_FONTBOLD);
				//弹出窗口按钮对齐方式
				readXml.readElement(styleFile, "system-config/style/dialogbar/alignment");
				String alignment = readXml.strData;
				if (null != btn_fontstyle || !"".equals(btn_fontstyle)){
					DIALOGBAR_ALIGNMENT = Integer.valueOf(alignment);
				}
			}
		} catch (Exception e) {
			throw new UserSystemException(e.getMessage());
		}
	}
	
}