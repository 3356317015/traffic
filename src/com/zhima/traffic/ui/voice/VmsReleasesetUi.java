package com.zhima.traffic.ui.voice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.nebula.widgets.formattedtext.DateTimeFormatter;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.ITextFormatter;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.voice.IVmsNoticeset;
import com.zhima.traffic.action.voice.IVmsSoundset;
import com.zhima.traffic.action.voice.impl.ImpVmsNoticeset;
import com.zhima.traffic.action.voice.impl.ImpVmsSoundset;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.traffic.model.VmsNoticeset;
import com.zhima.traffic.model.VmsSound;
import com.zhima.traffic.model.VmsSoundset;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.Validate;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class VmsReleasesetUi extends Dialog {
	private GridView gridView;
	private String voiceType;
	private String operType;
	private Object obj;
	
	private Text txtPlayTime;
	private Text txtPlayNumber;
	private GridView gridTime;

	protected VmsReleasesetUi(Shell shell,GridView gridView,String voiceType,String operType) {
		super(shell);
		this.gridView = gridView;
		this.voiceType = voiceType;
		this.operType = operType;
		this.obj = this;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("播放设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(325,325);
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
		
		Group groupMain = new Group(compMain,SWT.NONE);
		groupMain.setText("播音选项信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		groupMain.setLayout(new FormLayout());
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		
		Label lbPlayTime = new Label(groupMain, SWT.RIGHT);
		lbPlayTime.setFont(StyleFinal.SYS_FONT);
		lbPlayTime.setText("播放时间:");
		data = new FormData();
		data.top = new FormAttachment(0, 8);
		data.left = new FormAttachment(0, 5);
		lbPlayTime.setLayoutData(data);
		
		FormattedText formattedPlayTime = new FormattedText(groupMain, SWT.BORDER);
		ITextFormatter formatterPlayTime = new DateTimeFormatter("HH:mm");
		formattedPlayTime.setFormatter(formatterPlayTime);
		txtPlayTime = formattedPlayTime.getControl();
		txtPlayTime.setFont(StyleFinal.SYS_FONT);
		SimpleDateFormat formatPlayTime = new SimpleDateFormat("HH:mm");
		Calendar calendarPlayTime = Calendar.getInstance();
		txtPlayTime.setText(formatPlayTime.format(calendarPlayTime.getTime()));
		data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(lbPlayTime, 5);
		data.right = new FormAttachment(50, -5);
		data.height = StyleFinal.TEXT_HEIGHT;
		txtPlayTime.setLayoutData(data);
		
		Label lbPlayNumber = new Label(groupMain, SWT.RIGHT);
		lbPlayNumber.setFont(StyleFinal.SYS_FONT);
		lbPlayNumber.setText("播放次数:");
		data = new FormData();
		data.top = new FormAttachment(0, 8);
		data.left = new FormAttachment(txtPlayTime, 10);
		lbPlayNumber.setLayoutData(data);
		
		txtPlayNumber = new Text(groupMain,SWT.BORDER);
		txtPlayNumber.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(lbPlayNumber, 5);
		data.right = new FormAttachment(100, -5);
		data.height = StyleFinal.TEXT_HEIGHT;
		txtPlayNumber.setLayoutData(data);
		txtPlayNumber.addVerifyListener(new TextVerifyListener(1));
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("播放时间", "playTime",100));
		columns.add(new GridColumn("播放次数", "playNumber",100));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		
		gridConfig.setRightBos(initRight());
		gridConfig.setObj(obj);
		gridTime = new GridView(groupMain, SWT.BORDER);
		gridTime.CreateTabel(gridConfig);
		data = new FormData();
		data.top = new FormAttachment(lbPlayTime,10);
		data.left = new FormAttachment(0,5);
		data.bottom = new FormAttachment(100,-5);
		data.right = new FormAttachment(100,-5);
		gridTime.setLayoutData(data);
		initData();
		return compMain;
	}
	
	private List<SamModuleRight> initRight() {
		List<SamModuleRight> rights = new ArrayList<SamModuleRight>();
		SamModuleRight moduleRight = new SamModuleRight();
		moduleRight.setRightName("删除(&R)");
		moduleRight.setRightMethod("deleteTime");
		rights.add(moduleRight);
		return rights;
	}
	
	private void initData(){
		try {
			CallMethod callMethod = new CallMethod();
			callMethod.bindEnterEvent(this, txtPlayTime, txtPlayNumber, null);
			callMethod.bindEnterEvent(this, txtPlayNumber, txtPlayTime, "addSet");
			if (voiceType.equals("notice")){
				VmsNotice vmsNotice = (VmsNotice) gridView.getSelection();
				IVmsNoticeset iVmsNoticeset = new ImpVmsNoticeset();
				List<VmsNoticeset> vmsNoticesets = iVmsNoticeset.queryByNoticeSeq(vmsNotice.getNoticeSeq());
				gridTime.setDataList(vmsNoticesets);
			}else if (voiceType.equals("sound")){
				VmsSound vmsSound = (VmsSound) gridView.getSelection();
				IVmsSoundset iVmsSoundset = new ImpVmsSoundset();
				List<VmsSoundset> vmsSoundsets = iVmsSoundset.queryBySoundSeq(vmsSound.getSoundSeq());
				gridTime.setDataList(vmsSoundsets);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addSet(){
		try {
			
			
			
			
			if (txtPlayTime.getText().trim().length()!=5){
				MsgBox.warning(getShell(), "播放时间不合法！");
				txtPlayTime.forceFocus();
				return;
				
			}else{
				
			}
			
			if (Validate.StrNotNull(txtPlayNumber.getText())){
				
			}else{
				MsgBox.warning(getParentShell(),"请输入播放次数！");
				txtPlayNumber.forceFocus();
				return;
			}
			if (voiceType.equals("notice")){
				VmsNoticeset vmsNoticeset = new VmsNoticeset();
				vmsNoticeset.setPlayTime(txtPlayTime.getText());
				vmsNoticeset.setPlayNumber(txtPlayNumber.getText());
				gridTime.addRow(vmsNoticeset);
			}else{
				VmsSoundset vmsSoundset = new VmsSoundset();
				vmsSoundset.setPlayTime(txtPlayTime.getText());
				vmsSoundset.setPlayNumber(txtPlayNumber.getText());
				gridTime.addRow(vmsSoundset);
			}
			
			
			
			txtPlayNumber.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteTime(){
		int index[] = gridTime.getSelectionIndexs();
		if (null != index && index.length>0){
			gridTime.deleteRow(index);
		}
		txtPlayTime.forceFocus();
		txtPlayTime.selectAll();
	}
	
	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
				
				if (voiceType.equals("notice")){
					List<VmsNoticeset> vmsNoticesets = (List<VmsNoticeset>) gridTime.getDataList();
					VmsNotice vmsNotice = (VmsNotice) gridView.getSelection();
					IVmsNoticeset iVmsNoticeset = new ImpVmsNoticeset();
					if (null != vmsNoticesets && vmsNoticesets.size()>0){
						for (int i = 0; i < vmsNoticesets.size(); i++) {
							vmsNoticesets.get(i).setNoticeSeq(vmsNotice.getNoticeSeq());
							vmsNoticesets.get(i).setUpdateTime(currTime);
						}
					}
					iVmsNoticeset.update(vmsNotice, vmsNoticesets, CommFinal.initConfig());
				}else if (voiceType.equals("sound")){
					List<VmsSoundset> vmsSoundsets = (List<VmsSoundset>) gridTime.getDataList();
					VmsSound vmsSound = (VmsSound) gridView.getSelection();
					IVmsSoundset iVmsSoundset = new ImpVmsSoundset();
					if (null != vmsSoundsets && vmsSoundsets.size()>0){
						for (int i = 0; i < vmsSoundsets.size(); i++) {
							vmsSoundsets.get(i).setSoundSeq(vmsSound.getSoundSeq());
							vmsSoundsets.get(i).setUpdateTime(currTime);
						}
					}
					iVmsSoundset.update(vmsSound, vmsSoundsets, CommFinal.initConfig());
				}
				close();
			} else if (0 == buttonId) {
				/**
				 * 取消
				 */
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
}
