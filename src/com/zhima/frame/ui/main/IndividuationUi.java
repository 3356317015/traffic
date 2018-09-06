
package com.zhima.frame.ui.main;

import java.awt.GraphicsEnvironment;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
import com.zhima.util.log4j.LogUtil;
import com.zhima.util.xml.ReadXml;
import com.zhima.util.xml.UpdateXml;
import com.zhima.widget.TextVerifyListener;

/**
 * IndividuationUi概要说明：个性化设置
 * @author lcy
 */
public class IndividuationUi extends Dialog {
	/**
	 * 窗口按钮对应值
	 */
	private Integer SAVEIN_ID = 0;	//确定按钮
	private Integer SAVEOUT_ID = 1;	//取消按钮
	
	/**
	 * 窗口控件成员
	 */
	private CCombo sysFontType;
	private Text sysFontSize;
	private Button sysFontBold;
	
	private CCombo folderFontType;
	private Text folderFontSize;
	private Button folderFontBold;
	
	private CCombo btnFontType;
	private Text btnFontSize;
	private Button btnFontBold;
	
	private CCombo gridFontType;
	private Text gridFontSize;
	private Button gridFontBold;
	
	private Button toolbarShow;
	private Button folderImageShow;
	private Button btnImageShow;
	
	/**
	 * 构造函数: 构造数据库连接设置窗口
	 * @param parentShell
	 */
	protected IndividuationUi(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * 建立数据库连接窗口
	 */
	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("个性化设置");
		shell.pack();
		shell.setSize(435,275);
		Utils.centerDialogOnScreen(shell);
	}
	
	protected Point getInitialSize(){
        return new Point(435,275);//500是宽400是高
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
		
		Group imageConfig = new Group(compo, SWT.NONE);
		imageConfig.setLayout(new GridLayout(4, false));
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.height = 30;
		imageConfig.setLayoutData(data);
		imageConfig.setText("显示选项");
		
		Label label = new Label(imageConfig, SWT.NONE);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=30;
		label.setLayoutData(gridData);
		
		toolbarShow = new Button(imageConfig, SWT.CHECK);
		toolbarShow.setText("启用系统工具栏");
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		toolbarShow.setLayoutData(gridData);
	
		folderImageShow = new Button(imageConfig, SWT.CHECK);
		folderImageShow.setText("显示选项卡图标");
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		folderImageShow.setLayoutData(gridData);
		
		btnImageShow = new Button(imageConfig, SWT.CHECK);
		btnImageShow.setText("显示按钮图标");
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		btnImageShow.setLayoutData(gridData);
		
		Group fontConfig = new Group(compo, SWT.NONE);
		fontConfig.setLayout(new GridLayout(7, false));
		data = new FormData();
		data.top = new FormAttachment(imageConfig);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100,-5);
		fontConfig.setLayoutData(data);
		fontConfig.setText("字体选项");
		
		/*Label lbSys = new Label(fontConfig, SWT.NONE|SWT.LEFT);
		lbSys.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=8;
		lbSys.setLayoutData(gridData);
		lbSys.setText("系统字体设置");*/
		
		Label lbSysFont = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSysFont.setLayoutData(gridData);
		lbSysFont.setText("系统字型");
		sysFontType = new CCombo(fontConfig, SWT.BORDER|SWT.READ_ONLY);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint=16;
		gridData.widthHint = 150;
		sysFontType.setLayoutData(gridData);
		
		label = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=20;
		label.setLayoutData(gridData);
		
		
		sysFontBold = new Button(fontConfig, SWT.CHECK);
		sysFontBold.setText("加粗");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		sysFontBold.setLayoutData(gridData);
		
		label = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=15;
		label.setLayoutData(gridData);
		
		Label lbSysSize = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSysSize.setLayoutData(gridData);
		lbSysSize.setText("大小");
		sysFontSize = new Text(fontConfig, SWT.BORDER);
		sysFontSize.addVerifyListener(new TextVerifyListener(1));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint=16;
		sysFontSize.setLayoutData(gridData);
		
		
		/*Label lbFolder = new Label(fontConfig, SWT.NONE|SWT.LEFT);
		lbFolder.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=8;
		lbFolder.setLayoutData(gridData);
		lbFolder.setText("选项卡字体设置");*/
		
		Label lbFolderFont = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFolderFont.setLayoutData(gridData);
		lbFolderFont.setText("选项卡字型");
		folderFontType = new CCombo(fontConfig, SWT.BORDER|SWT.READ_ONLY);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint=16;
		gridData.widthHint = 150;
		folderFontType.setLayoutData(gridData);
		
		label = new Label(fontConfig, SWT.NONE);
		
		folderFontBold = new Button(fontConfig, SWT.CHECK);
		folderFontBold.setText("加粗");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		folderFontBold.setLayoutData(gridData);
		
		label = new Label(fontConfig, SWT.NONE);
		
		Label lbFolderSize = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbFolderSize.setLayoutData(gridData);
		lbFolderSize.setText("大小");
		folderFontSize = new Text(fontConfig, SWT.BORDER);
		folderFontSize.addVerifyListener(new TextVerifyListener(1));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint=16;
		folderFontSize.setLayoutData(gridData);
		
		/*Label lbGrid = new Label(fontConfig, SWT.NONE|SWT.LEFT);
		lbGrid.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=8;
		lbGrid.setLayoutData(gridData);
		lbGrid.setText("表格字体设置");*/
		
		Label lbGridFont = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbGridFont.setLayoutData(gridData);
		lbGridFont.setText("表格字型");
		gridFontType = new CCombo(fontConfig, SWT.BORDER|SWT.READ_ONLY);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint=16;
		gridData.widthHint = 150;
		gridFontType.setLayoutData(gridData);
		
		label = new Label(fontConfig, SWT.NONE);
		
		gridFontBold = new Button(fontConfig, SWT.CHECK);
		gridFontBold.setText("加粗");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridFontBold.setLayoutData(gridData);
		
		label = new Label(fontConfig, SWT.NONE);
		
		Label lbGridSize = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbGridSize.setLayoutData(gridData);
		lbGridSize.setText("大小");
		gridFontSize = new Text(fontConfig, SWT.BORDER);
		gridFontSize.addVerifyListener(new TextVerifyListener(1));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint=16;
		gridFontSize.setLayoutData(gridData);
		
		/*Label lbBtn = new Label(fontConfig, SWT.NONE|SWT.LEFT);
		lbBtn.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=8;
		lbBtn.setLayoutData(gridData);
		lbBtn.setText("安钮字体设置");*/
		
		Label lbBtnFont = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbBtnFont.setLayoutData(gridData);
		lbBtnFont.setText("按钮字型");
		btnFontType = new CCombo(fontConfig, SWT.BORDER|SWT.READ_ONLY);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint=16;
		gridData.widthHint = 150;
		btnFontType.setLayoutData(gridData);
		
		label = new Label(fontConfig, SWT.NONE);
		
		btnFontBold = new Button(fontConfig, SWT.CHECK);
		btnFontBold.setText("加粗");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		btnFontBold.setLayoutData(gridData);
		
		label = new Label(fontConfig, SWT.NONE);
		
		Label lbBtnSize = new Label(fontConfig, SWT.NONE);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbBtnSize.setLayoutData(gridData);
		lbBtnSize.setText("大小");
		btnFontSize = new Text(fontConfig, SWT.BORDER);
		btnFontSize.addVerifyListener(new TextVerifyListener(1));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint=16;
		btnFontSize.setLayoutData(gridData);

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
			LogUtil.operLog(e, "E", true, true);
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
		try {
			GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
			String[] fontName = e.getAvailableFontFamilyNames();
			sysFontType.setItems(fontName);
			folderFontType.setItems(fontName);
			btnFontType.setItems(fontName);
			gridFontType.setItems(fontName);
			
			String connFile = System.getProperty("user.dir") + "\\config_system.xml";
			ReadXml readXml = new ReadXml();
			//启用工具栏
			readXml.readElement(connFile, "system-config/style/image/toolbarshow");
			String toolbar_show = readXml.strData;
			if (null != toolbar_show || !"".equals(toolbar_show)){
				if ("true".equals(toolbar_show)){
					toolbarShow.setSelection(true);
				}else{
					toolbarShow.setSelection(false);
				}
			}else{
				toolbarShow.setSelection(StyleFinal.FOLDER_SHOWIMAGE);
			}
			//显示选项卡图片
			readXml.readElement(connFile, "system-config/style/image/foldershowimage");
			String folder_showimage = readXml.strData;
			if (null != folder_showimage || !"".equals(folder_showimage)){
				if ("true".equals(folder_showimage)){
					folderImageShow.setSelection(true);
				}else{
					folderImageShow.setSelection(false);
				}
			}else{
				folderImageShow.setSelection(StyleFinal.FOLDER_SHOWIMAGE);
			}
			//显示按钮图片
			readXml.readElement(connFile, "system-config/style/image/btnshowimage");
			String btn_showimage = readXml.strData;
			if (null != btn_showimage || !"".equals(btn_showimage)){
				if ("true".equals(btn_showimage)){
					btnImageShow.setSelection(true);
				}else{
					btnImageShow.setSelection(false);
				}
			}else{
				btnImageShow.setSelection(StyleFinal.BTN_SHOWIMAGE);
			}
			//系统字体
			readXml.readElement(connFile, "system-config/style/font/sysfont/name");
			String sys_fontstyle = readXml.strData;
			if (null != sys_fontstyle || !"".equals(sys_fontstyle)){
				sysFontType.setText(sys_fontstyle);
			}else{
				sysFontType.setText(StyleFinal.SYS_FONTSTYLE);
			}
			readXml.readElement(connFile, "system-config/style/font/sysfont/size");
			String sys_fontsize = readXml.strData;
			if (null != sys_fontsize || !"".equals(sys_fontsize)){
				sysFontSize.setText(sys_fontsize);
			}else{
				sysFontSize.setText(String.valueOf(StyleFinal.SYS_FONTSIZE));
			}
			readXml.readElement(connFile, "system-config/style/font/sysfont/bold");
			String sys_fontbold = readXml.strData;
			if (null != sys_fontbold || !"".equals(sys_fontbold)){
				if ("true".equals(sys_fontbold)){
					sysFontBold.setSelection(true);
				}else{
					sysFontBold.setSelection(false);
				}
			}else{
				if (1 == StyleFinal.SYS_FONTBOLD){
					sysFontBold.setSelection(true);
				}else{
					sysFontBold.setSelection(false);
				}
			}
			//选项卡字体
			readXml.readElement(connFile, "system-config/style/font/folderfont/name");
			String folder_fontstyle = readXml.strData;
			if (null != folder_fontstyle || !"".equals(folder_fontstyle)){
				folderFontType.setText(folder_fontstyle);
			}else{
				folderFontType.setText(StyleFinal.FOLDER_FONTSTYLE);
			}
			readXml.readElement(connFile, "system-config/style/font/folderfont/size");
			String folder_fontsize = readXml.strData;
			if (null != folder_fontsize || !"".equals(folder_fontsize)){
				folderFontSize.setText(folder_fontsize);
			}else{
				folderFontSize.setText(String.valueOf(StyleFinal.FOLDER_FONTSIZE));
			}
			readXml.readElement(connFile, "system-config/style/font/folderfont/bold");
			String folder_fontbold = readXml.strData;
			if (null != folder_fontbold || !"".equals(folder_fontbold)){
				if ("true".equals(folder_fontbold)){
					folderFontBold.setSelection(true);
				}else{
					folderFontBold.setSelection(false);
				}
			}else{
				if (1 == StyleFinal.FOLDER_FONTBOLD){
					folderFontBold.setSelection(true);
				}else{
					folderFontBold.setSelection(false);
				}
			}
			//表格字体
			readXml.readElement(connFile, "system-config/style/font/gridfont/name");
			String grid_fontstyle = readXml.strData;
			if (null != grid_fontstyle || !"".equals(grid_fontstyle)){
				gridFontType.setText(grid_fontstyle);
			}else{
				gridFontType.setText(StyleFinal.GRID_FONTSTYLE);
			}
			readXml.readElement(connFile, "system-config/style/font/gridfont/size");
			String grid_fontsize = readXml.strData;
			if (null != grid_fontsize || !"".equals(grid_fontsize)){
				gridFontSize.setText(grid_fontsize);
			}else{
				gridFontSize.setText(String.valueOf(StyleFinal.GRID_FONTSIZE));
			}
			readXml.readElement(connFile, "system-config/style/font/gridfont/bold");
			String grid_fontbold = readXml.strData;
			if (null != grid_fontbold || !"".equals(grid_fontbold)){
				if ("true".equals(grid_fontbold)){
					gridFontBold.setSelection(true);
				}else{
					gridFontBold.setSelection(false);
				}
			}else{
				if (1 == StyleFinal.GRID_FONTBOLD){
					gridFontBold.setSelection(true);
				}else{
					gridFontBold.setSelection(false);
				}
			}
			//按钮字体
			readXml.readElement(connFile, "system-config/style/font/btnfont/name");
			String btn_fontstyle = readXml.strData;
			if (null != btn_fontstyle || !"".equals(btn_fontstyle)){
				btnFontType.setText(btn_fontstyle);
			}else{
				btnFontType.setText(StyleFinal.BTN_FONTSTYLE);
			}
			readXml.readElement(connFile, "system-config/style/font/btnfont/size");
			String btn_fontsize = readXml.strData;
			if (null != btn_fontsize || !"".equals(btn_fontsize)){
				btnFontSize.setText(btn_fontsize);
			}else{
				btnFontSize.setText(String.valueOf(StyleFinal.BTN_FONTSIZE));
			}
			readXml.readElement(connFile, "system-config/style/font/btnfont/bold");
			String btn_fontbold = readXml.strData;
			if (null != btn_fontbold || !"".equals(btn_fontbold)){
				if ("true".equals(btn_fontbold)){
					btnFontBold.setSelection(true);
				}else{
					btnFontBold.setSelection(false);
				}
			}else{
				if (1 == StyleFinal.BTN_FONTBOLD){
					btnFontBold.setSelection(true);
				}else{
					btnFontBold.setSelection(false);
				}
			}
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
	private void updateInfo(){
		try {
			String connFile = System.getProperty("user.dir") + "\\config_system.xml";
			UpdateXml updateXml = new UpdateXml();
			if (toolbarShow.getSelection()==true){
				updateXml.updateXml(connFile, "system-config/style/image/toolbarshow","true");
			} else{
				updateXml.updateXml(connFile, "system-config/style/image/toolbarshow","false");
			}
			if (folderImageShow.getSelection()==true){
				updateXml.updateXml(connFile, "system-config/style/image/foldershowimage","true");
			} else{
				updateXml.updateXml(connFile, "system-config/style/image/foldershowimage","false");
			}
			if (btnImageShow.getSelection()==true){
				updateXml.updateXml(connFile, "system-config/style/image/btnshowimage","true");
			} else{
				updateXml.updateXml(connFile, "system-config/style/image/btnshowimage","false");
			}
			updateXml.updateXml(connFile, "system-config/style/font/sysfont/name",sysFontType.getText());
			updateXml.updateXml(connFile, "system-config/style/font/sysfont/size",sysFontSize.getText());
			if (sysFontBold.getSelection()==true){
				updateXml.updateXml(connFile, "system-config/style/font/sysfont/bold","true");
			} else{
				updateXml.updateXml(connFile, "system-config/style/font/sysfont/bold","false");
			}
		
			updateXml.updateXml(connFile, "system-config/style/font/folderfont/name",folderFontType.getText());
			updateXml.updateXml(connFile, "system-config/style/font/folderfont/size",folderFontSize.getText());
			if (folderFontBold.getSelection()==true){
				updateXml.updateXml(connFile, "system-config/style/font/folderfont/bold","true");
			} else{
				updateXml.updateXml(connFile, "system-config/style/font/folderfont/bold","false");
			}

			updateXml.updateXml(connFile, "system-config/style/font/gridfont/name",gridFontType.getText());
			updateXml.updateXml(connFile, "system-config/style/font/gridfont/size",gridFontSize.getText());
			if (gridFontBold.getSelection()==true){
				updateXml.updateXml(connFile, "system-config/style/font/gridfont/bold","true");
			} else{
				updateXml.updateXml(connFile, "system-config/style/font/gridfont/bold","false");
			}

			updateXml.updateXml(connFile, "system-config/style/font/btnfont/name",btnFontType.getText());
			updateXml.updateXml(connFile, "system-config/style/font/btnfont/size",btnFontSize.getText());
			if (btnFontBold.getSelection()==true){
				updateXml.updateXml(connFile, "system-config/style/font/btnfont/bold","true");
			} else {
				updateXml.updateXml(connFile, "system-config/style/font/btnfont/bold","false");
			}
			StyleFinal.initializeConfig();
		} catch (Exception e) {
			LogUtil.operLog(e, "E", true, true);
		}
	}

	
}
