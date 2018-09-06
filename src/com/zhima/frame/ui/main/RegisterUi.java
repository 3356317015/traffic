
package com.zhima.frame.ui.main;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
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
import com.zhima.util.log4j.LogUtil;
import com.zhima.util.xml.UpdateXml;
import com.zhima.widget.MsgBox;

import dog.ActiveKey;


/**
 * ConnConfigUi概要说明：数据库连接配置
 * @author lcy
 */
public class RegisterUi extends Dialog {
	/**
	 * 窗口按钮对应值
	 */
	private Integer SAVEIN_ID = 0;	//确定按钮
	private Integer SAVEOUT_ID = 1;	//取消按钮
	
	/**
	 * 窗口控件成员
	 */
	private Text txtKey;	//注册码
	
	
	/**
	 * 构造函数: 构造数据库连接设置窗口
	 * @param parentShell
	 */
	protected RegisterUi(Shell parentShell) {
		super(parentShell);
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("软件注册");
		shell.pack();
		shell.setSize(350,250);
		Utils.centerDialogOnScreen(shell);
	}
	
	protected Point getInitialSize(){
        return new Point(350,250);//500是宽400是高
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
	@SuppressWarnings("static-access")
	protected Control createDialogArea(Composite parent) {
		Composite compo = (Composite) super.createDialogArea(parent);
		compo.setLayout(new FormLayout());
		
		Group groupConn = new Group(compo, SWT.NONE);
		groupConn.setText("注册信息");
		groupConn.setLayout(new FormLayout());
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100,-5);
		groupConn.setLayoutData(data);
		Label lbMac = new Label(groupConn, SWT.NONE);
		lbMac.setAlignment(SWT.RIGHT);
		lbMac.setText("MAC地址：");
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, 15);
		lbMac.setLayoutData(data);
		
		org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(groupConn, SWT.BORDER);
		list.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		list.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		list.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		data = new FormData();
		data.top = new FormAttachment(lbMac, 0, SWT.TOP);
		data.left = new FormAttachment(lbMac, 5);
		data.right = new FormAttachment(100, -15);
		data.height=50;
		list.setLayoutData(data);
		ActiveKey activeKey = new ActiveKey();
		List<String> macs = activeKey.getValidAddress();
		if (null != macs && macs.size()>0){
			for (int i = 0; i < macs.size(); i++) {
				list.add(macs.get(i));
			}
		}
		//连接类型
		Label lbSysNumber = new Label(groupConn, SWT.NONE);
		lbSysNumber.setAlignment(SWT.RIGHT);
		lbSysNumber.setText("产品号：");
		data = new FormData();
		data.top = new FormAttachment(lbMac, 45);
		data.left = new FormAttachment(0, 15);
		data.right = new FormAttachment(lbMac, 0, SWT.RIGHT);
		lbSysNumber.setLayoutData(data);
		
		Text txtSysNumber = new Text(groupConn, SWT.BORDER);
		txtSysNumber.setEditable(false);
		data = new FormData();
		data.top = new FormAttachment(lbSysNumber, 0, SWT.TOP);
		data.left = new FormAttachment(lbSysNumber, 5);
		data.right = new FormAttachment(100,-15);
		txtSysNumber.setLayoutData(data);
		txtSysNumber.setText(String.valueOf(StyleFinal.SYS_NUMBER));
		
		Label lbKey = new Label(groupConn, SWT.NONE);
		lbKey.setAlignment(SWT.RIGHT);
		lbKey.setText("注册号码：");
		data = new FormData();
		data.top = new FormAttachment(lbSysNumber, 15);
		data.left = new FormAttachment(0, 15);
		data.right = new FormAttachment(lbMac, 0, SWT.RIGHT);
		lbKey.setLayoutData(data);
		
		txtKey = new Text(groupConn, SWT.BORDER);
		txtKey.setText(StyleFinal.SYS_KEY);
		data = new FormData();
		data.top = new FormAttachment(lbKey, 0, SWT.TOP);
		data.left = new FormAttachment(lbKey, 5);
		data.right = new FormAttachment(100, -15);
		txtKey.setLayoutData(data);
		
		return parent;
	}
	
	/**
	 * 创建并替换窗口缺省按钮
	 */
	protected void createButtonsForButtonBar(Composite parent){
		GridData data = new GridData(StyleFinal.DIALOGBAR_ALIGNMENT);
		
		parent.setLayoutData(data);
		//创建保存按钮
		Button confirm = createButton(parent, SAVEIN_ID,"确定(&O)",false);
		confirm.setFont(StyleFinal.SYS_FONT);
		
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
			updateXml.updateXml(connFile, "system-config/key",txtKey.getText());			
			StyleFinal.initializeConfig();
		} catch (Exception e) {
			LogUtil.operLog(e, "E", true, true);
		}
	}
}
