package com.zhima.frame.ui.sam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamUpgrade;
import com.zhima.frame.action.sam.ISamUpinfo;
import com.zhima.frame.action.sam.impl.ImpSamUpgrade;
import com.zhima.frame.action.sam.impl.ImpSamUpinfo;
import com.zhima.frame.model.SamUpblob;
import com.zhima.frame.model.SamUpgrade;
import com.zhima.util.DateUtils;
import com.zhima.util.ImageUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.blob.BlobImpl;
import com.zhima.util.blob.SerializableBlob;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class SamUpgradeEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtPath;

	private GridView gridFile;
	
	private Text txtFileVer;
	
	private Text txtRemark;

	protected SamUpgradeEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("升级文件-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(615,420);
    }
	
	protected void createButtonsForButtonBar(Composite parent){
		GridData btnData = new GridData(StyleFinal.DIALOGBAR_ALIGNMENT);
		parent.setLayoutData(btnData);
		Button confirm = createButton(parent, 1, "确定(&O)", false);
		confirm.setFont(StyleFinal.SYS_FONT);
		Button cancel = createButton(parent, 0, "取消(&C)", false);
		cancel.setFont(StyleFinal.SYS_FONT);
		if (StyleFinal.BTN_SHOWIMAGE == true){
			confirm.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_OK));
			cancel.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_CANCEL));
		}
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite compMain = (Composite) super.createDialogArea(parent);
		compMain.setLayout(new FormLayout());
		CallMethod callMethod = new CallMethod();
		
		Group groupMain = new Group(compMain,SWT.NONE);
		groupMain.setText("升级信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		groupMain.setLayout(new FormLayout());
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);

		Label lbPath = new Label(groupMain, SWT.NONE);
		lbPath.setFont(StyleFinal.SYS_FONT);
		lbPath.setText("路径:");
		data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0);
		lbPath.setLayoutData(data);
		
		txtPath = new Text(groupMain, SWT.BORDER|SWT.READ_ONLY);
		txtPath.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(0,2);
		data.left = new FormAttachment(lbPath,5);
		data.right = new FormAttachment(100,-80);
		data.height = StyleFinal.TEXT_HEIGHT;
		txtPath.setLayoutData(data);
		
		Button button = new Button(groupMain, SWT.NONE);
		button.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(0,1);
		data.left = new FormAttachment(txtPath, 5);
		data.right = new FormAttachment(100);
		data.height = 24;
		button.setLayoutData(data);
		button.setText("浏览...");
		button.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				try {
					FileDialog dialog = new FileDialog(getShell(),SWT.OPEN|SWT.MULTI);
					Properties properties = System.getProperties();
					String parentPath = properties.getProperty("user.dir");
					dialog.setFilterPath(parentPath);
					dialog.setFilterExtensions(new String[]{"*.*","*.jar","*.xml","*.png","*.jpg","*.gif","*.jasper"});
					dialog.setFilterNames(new String[]{"*.*","*.jar","*.xml","*.png","*.jpg","*.gif","*.jasper"});
					String fillePath = dialog.open();
					if(null != fillePath){
						String[] files = dialog.getFileNames();
						if(null != files && files.length > 0){
							//文件所在目录
							String fileDir = dialog.getFilterPath();
							txtPath.setText(fileDir);
							List<SamUpgrade> samUpgrades = new ArrayList<SamUpgrade>();
							for(int i=0;i<files.length;i++){
								if(null != files[i] && !"".equals(files[i])){
									String fileName = files[i];
									String filePath = fileDir + "\\"+fileName;
									File file = new File(filePath);
									if(file.isFile()){
										SamUpgrade samUpgrade = new SamUpgrade();
										samUpgrade.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
										samUpgrade.setFileName(fileName);

										FileInputStream in = new FileInputStream(filePath);
										int fileSize = in.available();
										samUpgrade.setFileSize(String.valueOf(fileSize));
									
									samUpgrade.setFilePath(filePath.substring(parentPath.length(), filePath.length()-fileName.length()));
									samUpgrade.setFileUrl(filePath);
									samUpgrades.add(samUpgrade);
								}	
							}
					    } 
						gridFile.setDataList(samUpgrades);
					}else {
						//MsgBox.warning(getShell(),"请选择要升级的文件!");
					}
				} else {
					//MsgBox.warning(getShell(),"请选择升级文件所在目录!");
				}
				} catch (FileNotFoundException e1) {
					LogUtil.operLog(e1,"E",true,"文件不存在!");
				} catch (IOException e1) {
					LogUtil.operLog(e1,"E",true,"输入输出错误!");
				} catch (Exception e1) {
					LogUtil.operLog(e1,"E",true,true);
				}
			}
		});
		
		Label lbFileVer = new Label(groupMain, SWT.NONE);
		lbFileVer.setFont(StyleFinal.SYS_FONT);
		lbFileVer.setText("版本:");
		data = new FormData();
		data.top = new FormAttachment(txtPath,8);
		data.left = new FormAttachment(0);
		lbFileVer.setLayoutData(data);
		
		txtFileVer = new Text(groupMain, SWT.BORDER);
		txtFileVer.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(txtPath,5);
		data.left = new FormAttachment(lbFileVer,5);
		data.width = 160;
		data.height = StyleFinal.TEXT_HEIGHT;
		txtFileVer.setLayoutData(data);
		
		Label lbRemark = new Label(groupMain, SWT.NONE);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		data = new FormData();
		data.top = new FormAttachment(txtFileVer,8);
		data.left = new FormAttachment(0);
		lbRemark.setLayoutData(data);
		
		txtRemark = new Text(groupMain, SWT.BORDER|SWT.WRAP);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(txtFileVer,5);
		data.left = new FormAttachment(lbRemark,5);
		data.right = new FormAttachment(100);
		data.height = 50;
		txtRemark.setLayoutData(data);
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("文件名","fileName",200));
		columns.add(new GridColumn("大小","fileSize",80));
		columns.add(new GridColumn("路径","filePath",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(true);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridFile = new GridView(groupMain, SWT.BORDER);
		gridFile.CreateTabel(gridConfig);
		data = new FormData();
		data.top = new FormAttachment(txtRemark,5);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		gridFile.setLayoutData(data);
		
		initData();
		txtPath.forceFocus();

		callMethod.bindEnterEvent(this, txtPath, txtFileVer, "");
		callMethod.bindEnterEvent(this, txtFileVer, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		try {
			ISamUpgrade iSamUpgrade = new ImpSamUpgrade();
			SamUpgrade samUpgrade = iSamUpgrade.queryMaxVer(CommFinal.organize.getOrganizeSeq());
			if (null != samUpgrade){
				txtFileVer.setText(samUpgrade.getFileVer());
			}else{
				txtFileVer.setText(DateUtils.getNow("yyyyMMddHHmm"));
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				ThreadWaiting waiting = new ThreadWaiting(this,"upgrade",new Class[]{},new String[]{});
				waiting.task();
			} else if (0 == buttonId) {
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void upgrade(){
		try {
			List<SamUpgrade> upgrades = (List<SamUpgrade>) gridFile.getCheckSelections();
			if (null != upgrades && upgrades.size()>0){
				ISamUpgrade iSamUpgrade = new ImpSamUpgrade();
				
				for (int i = 0; i < upgrades.size(); i++) {
					List<SamUpblob> samUpblobs = new ArrayList<SamUpblob>();
					File file = new File(upgrades.get(i).getFileUrl());
					FileInputStream in = new FileInputStream(file);
					//当文件小于4M时
					if(Integer.valueOf(upgrades.get(i).getFileSize())<4194304){
						byte[] by = new byte[Integer.valueOf(upgrades.get(i).getFileSize())];
						int j = 0;
						int len;
						while((len=in.read(by))>0){
							SamUpblob samUpblob = new SamUpblob();
							samUpblob.setFileBlob(ImageUtil.blobToBase64(new SerializableBlob(new BlobImpl(by))));
							samUpblob.setOrderId(j);
							samUpblobs.add(samUpblob);
							j++;
						}
					//大于4M时4194304
					} else {
						int number = Integer.valueOf(upgrades.get(i).getFileSize()) / 4194304;
						int modSize = Integer.valueOf(upgrades.get(i).getFileSize()) % 4194304;
						int j=0;
						for(;j<number;j++){
							byte[] by = new byte[4194304];
							in.read(by);
							SamUpblob samUpblob = new SamUpblob();
							samUpblob.setFileBlob(ImageUtil.blobToBase64(new SerializableBlob(new BlobImpl(by))));
							samUpblob.setOrderId(j);
							samUpblobs.add(samUpblob);
						}
						if(modSize > 0){
							byte[] by = new byte[modSize];
							in.read(by);
							SamUpblob samUpblob = new SamUpblob();
							samUpblob.setFileBlob(ImageUtil.blobToBase64(new SerializableBlob(new BlobImpl(by))));
							samUpblob.setOrderId(j+1);
							samUpblobs.add(samUpblob);
						}
					}

					upgrades.get(i).setFileVer(txtFileVer.getText());
					gridView.addRow(iSamUpgrade.insert(upgrades.get(i),samUpblobs,CommFinal.initConfig()));
				}
				if (null != txtRemark.getText() && txtRemark.getText().length()>0){
					ISamUpinfo iSamUpinfo = new ImpSamUpinfo();
					iSamUpinfo.insert(txtFileVer.getText(), txtRemark.getText(),CommFinal.initConfig());
				}
			}
			close();
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
			return;
		}
	}
	
	
	public static void main(String[] args) {
		//获取当前类文件所在包的根目录
		String parentPath = System.getProperty("user.dir").replace("\\", "/");
		System.out.println(parentPath); 
		//获取当前类文件所在的目录
		System.out.println(SamUpgradeEditUi.class.getResource("").getPath().replaceAll("%20", " ")); 

		Properties properties = System.getProperties();
		System.out.println(properties.getProperty("user.dir"));
		 
		File file=new File(".");
		System.out.println(file.getAbsolutePath());
	}
}
