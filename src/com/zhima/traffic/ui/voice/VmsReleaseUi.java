/**
 *******************************************************************************
 *
 * (c) Copyright 2012 重庆市志玛信息技术有限公司
 *
 * 系统名称：frameWork
 * 文  件  名 ：SamModuleUi.java
 * 模块名称：(请更改成该模块名称)
 * 创  建  人 ：鲁承毅
 * 创建日期：2013-3-2 下午10:36:15
 * 修  改  人 ：(修改了该文件，请填上修改人的名字)
 * 修改日期：(请填上修改该文件时的日期)
 * 版         本 ： V1.0.0
 *******************************************************************************  
 */

package com.zhima.traffic.ui.voice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.voice.IVmsNotice;
import com.zhima.traffic.action.voice.IVmsSound;
import com.zhima.traffic.action.voice.impl.ImpVmsNotice;
import com.zhima.traffic.action.voice.impl.ImpVmsSound;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.traffic.model.VmsSound;
import com.zhima.util.ObjectUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.TranCharset;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdRouteUi概要说明：公告信息
 * @author lcy
 */
public class VmsReleaseUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	private CTabFolder tabFolder;
	//权限
	private GridView gridMusic;
	private GridView gridSound;
	private GridView gridNotice;

	
	/**
	 * 
	 * 构造函数: 线路类型
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VmsReleaseUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		panel = new BasicPanel(this, SWT.BORDER);
		panel.setInput(false);
		panel.createPanel();
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		panel.setLayoutData(data);
		createToolbar();
		createDetail();
		queryRelease();
	}

	/**
	 * createToolbar方法描述：构建工具面板按钮
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:37:54
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createToolbar(){
		panel.toolbar.setLayout(new RowLayout());
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, panel.toolbar, rights);
	}
	
	/**
	 * createDetail方法描述：构建细部面板表格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createDetail(){
		panel.detail.setLayout(new FormLayout());
		
		tabFolder = new CTabFolder(panel.detail, SWT.BORDER);
		tabFolder.setFont(StyleFinal.SYS_FONT);
		tabFolder.setBorderVisible(true);
		tabFolder.setSimple(false);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		tabFolder.setLayoutData(data);
		tabFolder.setSelectionForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		tabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		CTabItem tabSys = new CTabItem(tabFolder, SWT.BORDER);
		tabSys.setText("背景音乐");
		tabSys.setControl(createMusic(tabFolder));
		
		CTabItem tabVoice = new CTabItem(tabFolder, SWT.BORDER);
		tabVoice.setText("文字公告");
		tabVoice.setControl(createNotice(tabFolder));
		
		CTabItem tabTemplate = new CTabItem(tabFolder, SWT.BORDER);
		tabTemplate.setText("语音公告");
		tabTemplate.setControl(createSound(tabFolder));
		
		tabFolder.setSelection(0);
	}
	private Control createMusic(Composite detail) {
		Composite composite = new Composite(detail, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(10);
		data.bottom = new FormAttachment(100);
		composite.setLayoutData(data);
		composite.setLayout(new FillLayout());
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("文件名称","fileName",200));
		columns.add(new GridColumn("专辑名称","soundName",150));
		columns.add(new GridColumn("路径","soundPath",400));
		columns.add(new GridColumn("大小","soundSize",80));
		columns.add(new GridColumn("演唱","signer",100));
		columns.add(new GridColumn("时长","soundTime",80));
		columns.add(new GridColumn("状态","soundStatus",TraffFinal.ARR_SOUND_STATUS,80));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(true);
		gridConfig.setColumns(columns);
		List<SamModuleRight> musicRights = new ArrayList<SamModuleRight>();
		if (null != rights && rights.size()>0){
			for (int i = 0; i < rights.size(); i++) {
				musicRights.add(rights.get(i));
			}
		}
		
		SamModuleRight separator = new SamModuleRight();
		separator.setRightName("separator");
		musicRights.add(separator);
		
		SamModuleRight rightYes = new SamModuleRight();
		rightYes.setRightMethod("yesMethod");
		rightYes.setRightName("有效");
		musicRights.add(rightYes);
		
		SamModuleRight rightNo = new SamModuleRight();
		rightNo.setRightMethod("noMethod");
		rightNo.setRightName("无效");
		musicRights.add(rightNo);
		
		gridConfig.setRightBos(musicRights);
		gridConfig.setObj(obj);
		gridMusic = new GridView(composite, SWT.NONE);
		gridMusic.CreateTabel(gridConfig);
		return composite;
	}
	private Control createNotice(Composite detail) {
		Composite composite = new Composite(detail, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(10);
		data.bottom = new FormAttachment(100);
		composite.setLayoutData(data);
		composite.setLayout(new FillLayout());
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("公告名称","noticeName",150));
		columns.add(new GridColumn("公告内容","noticeContent",400));
		columns.add(new GridColumn("状态","noticeStatus",TraffFinal.ARR_NOTICE_STATUS,100));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(true);
		gridConfig.setColumns(columns);
		List<SamModuleRight> noticeRights = new ArrayList<SamModuleRight>();
		if (null != rights && rights.size()>0){
			for (int i = 0; i < rights.size(); i++) {
				noticeRights.add(rights.get(i));
			}
		}
		
		SamModuleRight rightUpdate = new SamModuleRight();
		rightUpdate.setRightMethod("updateMethod");
		rightUpdate.setRightName("修改");
		noticeRights.add(rightUpdate);
		
		SamModuleRight separator = new SamModuleRight();
		separator.setRightName("separator");
		noticeRights.add(separator);
		
		SamModuleRight rightYes = new SamModuleRight();
		rightYes.setRightMethod("yesMethod");
		rightYes.setRightName("有效");
		noticeRights.add(rightYes);
		
		SamModuleRight rightNo = new SamModuleRight();
		rightNo.setRightMethod("noMethod");
		rightNo.setRightName("无效");
		noticeRights.add(rightNo);
		
		separator = new SamModuleRight();
		separator.setRightName("separator");
		noticeRights.add(separator);
		
		SamModuleRight rightTime = new SamModuleRight();
		rightTime.setRightMethod("setMethod");
		rightTime.setRightName("播放设置");
		noticeRights.add(rightTime);
		
		gridConfig.setRightBos(noticeRights);
		gridConfig.setObj(obj);
		gridNotice = new GridView(composite, SWT.NONE);
		gridNotice.CreateTabel(gridConfig);
		gridNotice.bindMouseDoubleClick(obj, "updateMethod");
		return composite;
	}
	
	private Control createSound(Composite detail) {
		Composite composite = new Composite(detail, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(10);
		data.bottom = new FormAttachment(100);
		composite.setLayoutData(data);
		composite.setLayout(new FillLayout());
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("文件名称","fileName",200));
		columns.add(new GridColumn("专辑名称","soundName",150));
		columns.add(new GridColumn("路径","soundPath",400));
		columns.add(new GridColumn("大小","soundSize",80));
		columns.add(new GridColumn("演唱","signer",100));
		columns.add(new GridColumn("时长","soundTime",80));
		columns.add(new GridColumn("状态","soundStatus",TraffFinal.ARR_SOUND_STATUS,80));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(true);
		gridConfig.setColumns(columns);
		List<SamModuleRight> soundRights = new ArrayList<SamModuleRight>();
		if (null != rights && rights.size()>0){
			for (int i = 0; i < rights.size(); i++) {
				soundRights.add(rights.get(i));
			}
		}
		
		SamModuleRight separator = new SamModuleRight();
		separator.setRightName("separator");
		soundRights.add(separator);
		
		SamModuleRight rightYes = new SamModuleRight();
		rightYes.setRightMethod("yesMethod");
		rightYes.setRightName("有效");
		soundRights.add(rightYes);
		
		SamModuleRight rightNo = new SamModuleRight();
		rightNo.setRightMethod("noMethod");
		rightNo.setRightName("无效");
		soundRights.add(rightNo);
		
		separator = new SamModuleRight();
		separator.setRightName("separator");
		soundRights.add(separator);
		
		SamModuleRight rightTime = new SamModuleRight();
		rightTime.setRightMethod("setMethod");
		rightTime.setRightName("播放设置");
		soundRights.add(rightTime);
		
		gridConfig.setRightBos(soundRights);
		gridConfig.setObj(obj);
		gridSound = new GridView(composite, SWT.NONE);
		gridSound.CreateTabel(gridConfig);
		return composite;
	}

	/**
	 * queryMethod方法描述：查询方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryMethod(){
		ThreadWaiting waiting = new ThreadWaiting(this,"queryRelease",new Class[]{},new String[]{});
		waiting.task();
	}
	
	public void queryRelease(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					//查询背景音乐
					IVmsSound iVmsSound = new ImpVmsSound();
					int start = gridMusic.getStart();
					int limit = gridMusic.getLimit();
					int musicCount = iVmsSound.queryCountByCustom(CommFinal.organize.getOrganizeSeq(), "1");
					if (musicCount>0){
						List<VmsSound> vmsMusics = iVmsSound.queryPageByCustom(CommFinal.organize.getOrganizeSeq(), "1",start,limit);
						gridMusic.setDataList(vmsMusics);
					}else{
						gridMusic.removeAll();
					}
					gridMusic.setTotalCount(musicCount);
					//查询语音公告
					start = gridSound.getStart();
					limit = gridSound.getLimit();
					int soundCount = iVmsSound.queryCountByCustom(CommFinal.organize.getOrganizeSeq(), "2");
					if (soundCount >0){
						List<VmsSound> vmsSounds = iVmsSound.queryPageByCustom(CommFinal.organize.getOrganizeSeq(), "2",start,limit);
						gridSound.setDataList(vmsSounds);
					}else{
						gridSound.removeAll();
					}
					gridSound.setTotalCount(soundCount);
					//查询文本公告
					IVmsNotice iVmsNotice = new ImpVmsNotice();
					start = gridNotice.getStart();
					limit = gridNotice.getLimit();
					int noticeCount = iVmsNotice.queryCountByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
					if (noticeCount>0){
						List<VmsNotice> vmsNotices = iVmsNotice.queryPageByOrganizeSeq(CommFinal.organize.getOrganizeSeq(),start,limit);
						gridNotice.setDataList(vmsNotices);
					}
					gridNotice.setTotalCount(noticeCount);
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void addMethod(){
		if(0==tabFolder.getSelectionIndex()||2==tabFolder.getSelectionIndex()){
			try {
				FileDialog dialog = new FileDialog(getShell(),SWT.MULTI);
				/*Properties properties = System.getProperties();
				String parentPath = properties.getProperty("user.dir");
				dialog.setFilterPath(parentPath);*/
				dialog.setFilterExtensions(new String[]{"*.mp3","*.wav"});
				dialog.setFilterNames(new String[]{"*.mp3","*.wav"});
				dialog.setText("歌曲选择");
				dialog.open();
				String[] fileNames = dialog.getFileNames();
				String filePath = dialog.getFilterPath();
				filePath = filePath.replace("\\","/");
				if (fileNames.length<=0){
					return ;
				}
				List<VmsSound> sounds = new ArrayList<VmsSound>();
				String soundType ="1";
				if (2==tabFolder.getSelectionIndex()){
					soundType= "2";
				}
				for(int i=0;i<fileNames.length;i++){
					VmsSound vmsSound = new VmsSound();
					vmsSound.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
					vmsSound.setSoundType(soundType);
					vmsSound.setSoundPath(filePath+"/"+fileNames[i]);
					File file = new File(vmsSound.getSoundPath());
					FileInputStream fileIn = new FileInputStream(file);
					vmsSound.setSoundSize(ObjectUtils.BigDecima(Double.valueOf(fileIn.available())/1024/1024,2)+"M");
					fileIn.close(); 
					vmsSound.setFileName(file.getName());
					//播放时长
					AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(file); 
					Map props = audioFileFormat.properties(); 
	
					Iterator it = props.entrySet().iterator();  
					while (it.hasNext()) {  
						Map.Entry entry = (Map.Entry) it.next();  
						Object key = entry.getKey();  
						Object value = entry.getValue();  
						System.out.println("key=" + key + " value=" + value);
						//播放长度
						if ("duration".equals(key)){
							Date date = new Date((Long)value/1000);
							SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
							vmsSound.setSoundTime(dateFormat.format(date)); 
						}
						//演唱者
						if ("author".equals(key)){
							vmsSound.setSigner(TranCharset.TranEncodeTOGB((String)value));
						}
						//标题
						if ("title".equals(key)){
							String soundName =TranCharset.TranEncodeTOGB((String)value).trim();
							if (null != soundName && soundName.length()>0){
								vmsSound.setSoundName(soundName);
							}
						}
					}
					if (null == vmsSound.getSoundName()
							|| vmsSound.getSoundName().length()<=0){
						vmsSound.setSoundName(vmsSound.getFileName());
					}
					vmsSound.setSoundStatus("1");
					sounds.add(vmsSound);
				}
				IVmsSound iVmsSound = new ImpVmsSound();
				iVmsSound.inserts(sounds, CommFinal.initConfig());
				if (0==tabFolder.getSelectionIndex()){
					gridMusic.addRows(sounds);
				}else {
					gridSound.addRows(sounds);
				}
				
			} catch (FileNotFoundException e1) {
				LogUtil.operLog(e1,"E",true,"文件不存在!");
			} catch (IOException e1) {
				LogUtil.operLog(e1,"E",true,"输入输出错误!");
			} catch (Exception e1) {
				LogUtil.operLog(e1,"E",true,true);
			}
		}else if (1 == tabFolder.getSelectionIndex()){
			VmsReleaseNoticeUi noticeUi = new VmsReleaseNoticeUi(getShell(), gridNotice, CommFinal.OPER_TYPE_ADD);
			noticeUi.open();
		}
	}
	
	public void updateMethod(){
		if(null!=gridNotice.getSelection()){
			VmsReleaseNoticeUi noticeUi = new VmsReleaseNoticeUi(getShell(), gridNotice, CommFinal.OPER_TYPE_UPDATE);
			noticeUi.open();
		}else{
			MsgBox.warning(getShell(), "请选择修改的项。");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteMethod(){
		try {
			if(0==tabFolder.getSelectionIndex()){
				int checkIndex[] = gridMusic.getSelectionIndexs();
				if (checkIndex.length>0){
					int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行数据吗？");
			    	if(isdel == SWT.YES){
			    		IVmsSound iVmsSound = new ImpVmsSound();
			    		iVmsSound.delete((List<VmsSound>) gridMusic.getSelections(),CommFinal.initConfig());
			    		gridMusic.deleteRow(checkIndex);
					} 
			    }
			}else if(1==tabFolder.getSelectionIndex()){
				int checkIndex[] = gridNotice.getSelectionIndexs();
				if (checkIndex.length>0){
					int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行数据吗？");
			    	if(isdel == SWT.YES){
			    		IVmsNotice iVmsNotice = new ImpVmsNotice();
			    		iVmsNotice.delete((List<VmsNotice>) gridNotice.getSelections(),CommFinal.initConfig());
			    		gridNotice.deleteRow(checkIndex);
					} 
			    }
			}else if(2==tabFolder.getSelectionIndex()){
				int checkIndex[] = gridSound.getSelectionIndexs();
				if (checkIndex.length>0){
					int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行数据吗？");
			    	if(isdel == SWT.YES){
			    		IVmsSound iVmsSound = new ImpVmsSound();
			    		iVmsSound.delete((List<VmsSound>) gridSound.getSelections(),CommFinal.initConfig());
			    		gridSound.deleteRow(checkIndex);
					} 
			    }
			}
		
			
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void yesMethod(){
		try {
			if(0==tabFolder.getSelectionIndex()){
				int checkIndex = gridMusic.getSelectionIndex();
				if (checkIndex>=0){
		    		IVmsSound iVmsSound = new ImpVmsSound();
		    		VmsSound vmsSound = (VmsSound) gridMusic.getSelection();
		    		vmsSound.setSoundStatus("1");
		    		iVmsSound.update(vmsSound, CommFinal.initConfig());
		    		gridMusic.updateRow(gridMusic.getSelectionIndex(), vmsSound);
			    }
			}else if(1==tabFolder.getSelectionIndex()){
				int checkIndex = gridNotice.getSelectionIndex();
				if (checkIndex>=0){
		    		IVmsNotice iVmsNotice = new ImpVmsNotice();
		    		VmsNotice vmsNotice = (VmsNotice) gridNotice.getSelection();
		    		vmsNotice.setNoticeStatus("1");
					iVmsNotice.update(vmsNotice, CommFinal.initConfig());
		    		gridNotice.updateRow(gridNotice.getSelectionIndex(), vmsNotice);
			    }
			}else if(2==tabFolder.getSelectionIndex()){
				int checkIndex = gridSound.getSelectionIndex();
				if (checkIndex>=0){
		    		IVmsSound iVmsSound = new ImpVmsSound();
		    		VmsSound vmsSound = (VmsSound) gridSound.getSelection();
		    		vmsSound.setSoundStatus("1");
		    		iVmsSound.update(vmsSound, CommFinal.initConfig());
					gridSound.updateRow(gridSound.getSelectionIndex(), vmsSound);
			    }
			}
		} catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void noMethod(){
		try {
			if(0==tabFolder.getSelectionIndex()){
				int checkIndex = gridMusic.getSelectionIndex();
				if (checkIndex>=0){
		    		IVmsSound iVmsSound = new ImpVmsSound();
		    		VmsSound vmsSound = (VmsSound) gridMusic.getSelection();
		    		vmsSound.setSoundStatus("0");
		    		iVmsSound.update(vmsSound, CommFinal.initConfig());
		    		gridMusic.updateRow(gridMusic.getSelectionIndex(), vmsSound);
			    }
			}else if(1==tabFolder.getSelectionIndex()){
				int checkIndex = gridNotice.getSelectionIndex();
				if (checkIndex>=0){
		    		IVmsNotice iVmsNotice = new ImpVmsNotice();
		    		VmsNotice vmsNotice = (VmsNotice) gridNotice.getSelection();
		    		vmsNotice.setNoticeStatus("0");
					iVmsNotice.update(vmsNotice, CommFinal.initConfig());
		    		gridNotice.updateRow(gridNotice.getSelectionIndex(), vmsNotice);
			    }
			}else if(2==tabFolder.getSelectionIndex()){
				int checkIndex = gridSound.getSelectionIndex();
				if (checkIndex>=0){
		    		IVmsSound iVmsSound = new ImpVmsSound();
		    		VmsSound vmsSound = (VmsSound) gridSound.getSelection();
		    		vmsSound.setSoundStatus("0");
		    		iVmsSound.update(vmsSound, CommFinal.initConfig());
					gridSound.updateRow(gridSound.getSelectionIndex(), vmsSound);
			    }
			}
		} catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setMethod(){
		if(1==tabFolder.getSelectionIndex()){
			int checkIndex = gridNotice.getSelectionIndex();
			if (checkIndex>=0){
	    		VmsReleasesetUi releasesetUi = new VmsReleasesetUi(getShell(), gridNotice, "notice", CommFinal.OPER_TYPE_UPDATE);
	    		releasesetUi.open();
		    }
		}else if(2==tabFolder.getSelectionIndex()){
			int checkIndex = gridSound.getSelectionIndex();
			if (checkIndex>=0){
				VmsReleasesetUi releasesetUi = new VmsReleasesetUi(getShell(), gridSound, "sound", CommFinal.OPER_TYPE_UPDATE);
	    		releasesetUi.open();
		    }
		}
	}

}
