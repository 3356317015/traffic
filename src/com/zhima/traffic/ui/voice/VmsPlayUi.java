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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.voice.IVmsLiner;
import com.zhima.traffic.action.voice.IVmsNotice;
import com.zhima.traffic.action.voice.IVmsParameter;
import com.zhima.traffic.action.voice.IVmsSound;
import com.zhima.traffic.action.voice.IVmsTemplate;
import com.zhima.traffic.action.voice.impl.ImpVmsLiner;
import com.zhima.traffic.action.voice.impl.ImpVmsNotice;
import com.zhima.traffic.action.voice.impl.ImpVmsParameter;
import com.zhima.traffic.action.voice.impl.ImpVmsSound;
import com.zhima.traffic.action.voice.impl.ImpVmsTemplate;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.VmsLiner;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.traffic.model.VmsParameter;
import com.zhima.traffic.model.VmsSound;
import com.zhima.traffic.model.VmsTemplate;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.jTTS.JttsUtil;
import com.zhima.util.jTTS.JttsVoice;
import com.zhima.util.log4j.LogUtil;
import com.zhima.util.mp3.MP3Exception;
import com.zhima.util.mp3.MP3Player;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * VmsPlayUi概要说明：语音播放
 * @author lcy
 */
public class VmsPlayUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	private List<SamModuleRight> rights;
	private CTabFolder tabFolder;
	//界面变化
	private Timer imageTimer = new Timer();
	//班次定时器
	private Timer linerTimer = new Timer();
	//播放定时器
	private Timer voiceTimer = new Timer();
	//播放定时器
	private Timer playTimer = new Timer();
	
	private Text txtVoice;
	
	private Button btnAutoVoice;

	//MP3相关
	
	private CLabel lbPlayMusic;
	private CLabel lbPlayTime;
	private Scale scaleSpeed;
	private Scale scaleVolume;
	
	private MP3Player mp3Player;
	private MP3Player mp3Notice;
	private Thread mp3Thread;
	public boolean flag = true;
	private int loop =1;
	private int index = 0;
	private int playIndex = 0;
	private int volume;

	private GridView gridLiner;
	private GridView gridVoice;
	private GridView gridMusic;
	private GridView gridNotice;
	
	//语音参数
	//语音库领域
	private String pVoiceDomain="";
	//语音库代码集
	private String pVoiceCode="";
	//语音库版本号
	@SuppressWarnings("unused")
	private String pVoiceVersion="";
	//语音库标识号
	private String pVoiceId="";
	//合成基频
	private String pVoicePitch="";
	//合成音量
	private String pVoiceVolume="";
	//合成语速
	private String pVoiceSpeed="";
	//标点符号读法
	private String pVoicePuncmode;
	//数字读法
	private String pVoiceDigitmode;
	//英文读法 
	private String pVoiceEngmode;
	//标记检测
	private String pVoiceTagmode;
	//自动播放
	private String pVoiceAuto = "";
	//公告音量
	private String pVoiceSound= "";
	//班次参数
	private String pLinerSource="";
	private String pLinerRate = "";
	private String pLinerNumber = "";
	private String pLinerEnglish = "";
	private String pLinerGrade = "";
	private String pTextGrade = "";
	private String pVoiceGrade = "";
	private String pLinerMinute = "";
	
	private String fRouteCode = "";
	private String fLinerId = "";
	private String fReportStatus="";
	private String fPrintbillStatus="";
	private boolean fmusic =false;
	private boolean fnotice =false;
	private List<VmsTemplate> templates;
	private List<JttsVoice> jttsVoices = new ArrayList<JttsVoice>();
	/**
	 * 
	 * 构造函数: 城市区域类
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VmsPlayUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				//停止班次定时任务
				dispose();
			}
		});
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
		
		initData();
		initVoice();
		importLiner();
		voiceBuild();
		voicePlay();
	}

	/**
	 * createToolbar方法描述：构建工具面板按钮
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:37:54
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createToolbar(){
		panel.toolbar.setLayout(new FormLayout());
		Composite composite = new Composite(panel.toolbar, SWT.NONE);
		composite.setLayout(new FormLayout());
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		formData.right = new FormAttachment(100);
		composite.setLayoutData(formData);
		
		Composite compbutton =  new Composite(composite, SWT.NONE);
		compbutton.setLayout(new RowLayout());
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compbutton, rights);
		
		btnAutoVoice = new Button(composite, SWT.NONE|SWT.CHECK);
		btnAutoVoice.setText("自动播放(&A)");
		btnAutoVoice.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,12);
		formData.left = new FormAttachment(compbutton,10);
		btnAutoVoice.setLayoutData(formData);
		btnAutoVoice.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (btnAutoVoice.getSelection()){
					pVoiceAuto = "是";
				}else{
					pVoiceAuto = "否";
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
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
		
		Composite compLiner = createLiner(panel.detail);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(55);
		compLiner.setLayoutData(formData);
		
		Composite compVoice = createVoice(panel.detail);
		formData = new FormData();
		formData.top = new FormAttachment(compLiner,5);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(50);
		formData.bottom = new FormAttachment(100);
		compVoice.setLayoutData(formData);
		
		Composite compMusic = createMusic(panel.detail);
		formData = new FormData();
		formData.top = new FormAttachment(compLiner,5);
		formData.left = new FormAttachment(compVoice,5);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		compMusic.setLayoutData(formData);
	}
	
	private Composite createLiner(Composite detail) {
		
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
		
		
		CTabItem tabLiner = new CTabItem(tabFolder, SWT.BORDER);
		tabLiner.setText("班次信息");
		
		Composite compLiner = new Composite(tabFolder, SWT.NONE);
		compLiner.setLayout(new FillLayout());
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("线路名称","routeName",150));
		columns.add(new GridColumn("途经站","stationName",150));
		columns.add(new GridColumn("车型","cargradeName",90));
		columns.add(new GridColumn("发车日期","linerDate",115));
		columns.add(new GridColumn("车次","linerId",80));
		columns.add(new GridColumn("发车时间","linerTime",80));
		
		columns.add(new GridColumn("状态","linerStatus",80));
		columns.add(new GridColumn("类型","linerType",80));
		
		columns.add(new GridColumn("检票口","checkCode",80));
		columns.add(new GridColumn("发车位","parkingCode",80));
		columns.add(new GridColumn("报班状态","reportStatus",TraffFinal.ARR_REPORT_STATUS,90));
		columns.add(new GridColumn("车牌号","carNumber",100));
		columns.add(new GridColumn("打单状态","printbillStatus",TraffFinal.ARR_PRINTBILL_STATUS,90));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		List<SamModuleRight> voiceRights = new ArrayList<SamModuleRight>();

		SamModuleRight rightNormal = new SamModuleRight();
		rightNormal.setRightMethod("normalPlay");
		rightNormal.setRightName("正常播音");
		voiceRights.add(rightNormal);
		
		SamModuleRight rightLate = new SamModuleRight();
		rightLate.setRightMethod("latePlay");
		rightLate.setRightName("晚点播音");
		voiceRights.add(rightLate);
		
		SamModuleRight rightStop = new SamModuleRight();
		rightStop.setRightMethod("stopPlay");
		rightStop.setRightName("停班播音");
		voiceRights.add(rightStop);
		
		SamModuleRight rightUrge = new SamModuleRight();
		rightUrge.setRightMethod("urgePlay");
		rightUrge.setRightName("催促播音");
		voiceRights.add(rightUrge);
		
		gridConfig.setRightBos(voiceRights);
		gridConfig.setObj(obj);
		gridLiner = new GridView(compLiner, SWT.NONE);
		gridLiner.CreateTabel(gridConfig);
		
		tabLiner.setControl(compLiner);
		
		CTabItem tabNotice = new CTabItem(tabFolder, SWT.BORDER);
		tabNotice.setText("公告信息");
		Composite compNotie = new Composite(tabFolder, SWT.NONE);
		compNotie.setLayout(new FillLayout());
		tabNotice.setControl(compNotie);
		
		columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("公告名称","voiceName",235));
		columns.add(new GridColumn("类型","voiceType",TraffFinal.ARR_VOICE_TYPE,100));
		columns.add(new GridColumn("播放时间","voiceTime",230));
		columns.add(new GridColumn("播放内容","voiceContent",700));
		
		gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		
		List<SamModuleRight> noticeRights = new ArrayList<SamModuleRight>();
		SamModuleRight rightNotice = new SamModuleRight();
		rightNotice.setRightMethod("noticePlay");
		rightNotice.setRightName("播放公告");
		noticeRights.add(rightNotice);
		gridConfig.setRightBos(noticeRights);
		gridConfig.setObj(obj);
		gridNotice = new GridView(compNotie, SWT.NONE);
		gridNotice.CreateTabel(gridConfig);
		
		tabFolder.setSelection(0);
		return tabFolder;
	}
	
	private Composite createVoice(Composite detail) {
		Group group = new Group(detail, SWT.NONE);
		group.setText("播音池");
		group.setFont(StyleFinal.SYS_FONT);
		group.setLayout(new FormLayout());
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("播音类型","voiceType",TraffFinal.ARR_VOICE_TYPE,130));
		columns.add(new GridColumn("播音时间","voiceTime",120));
		columns.add(new GridColumn("播音内容","voiceContent",335));
		
		GridConfig gridConfig = new GridConfig();
		gridConfig.setSort(false);
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		List<SamModuleRight> voiceRights = new ArrayList<SamModuleRight>();

		SamModuleRight rightUp = new SamModuleRight();
		rightUp.setRightMethod("upMethod");
		rightUp.setRightName("上移");
		voiceRights.add(rightUp);
		
		SamModuleRight rightLate = new SamModuleRight();
		rightLate.setRightMethod("downMethod");
		rightLate.setRightName("下移");
		voiceRights.add(rightLate);
		
		SamModuleRight rightStop = new SamModuleRight();
		rightStop.setRightMethod("deleteMethod");
		rightStop.setRightName("删除");
		voiceRights.add(rightStop);
		
		gridConfig.setRightBos(voiceRights);
		gridConfig.setObj(obj);
		gridVoice = new GridView(group, SWT.NONE);
		gridVoice.CreateTabel(gridConfig);
		gridVoice.table.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100,-52);
		gridVoice.setLayoutData(formData);
		
		txtVoice = new Text(group, SWT.NONE|SWT.WRAP|SWT.MULTI);
		txtVoice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		txtVoice.setEditable(false);
		formData = new FormData();
		formData.top = new FormAttachment(gridVoice);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		txtVoice.setLayoutData(formData);
		return group;
	}

	private Composite createMusic(Composite detail) {
		Group group = new Group(detail, SWT.NONE);
		group.setText("背景音乐");
		group.setFont(StyleFinal.SYS_FONT);
		group.setLayout(new FormLayout());

		final CLabel lbBack = new CLabel(group, SWT.NONE|SWT.CENTER);
		FormData formData = new FormData();
		formData.bottom = new FormAttachment(100,-9);
		formData.left = new FormAttachment(0,5);
		lbBack.setLayoutData(formData);
		lbBack.setImage(SWTResourceManager.getImage("images/music/back_default.png"));
		//鼠标移入、移开事件
		lbBack.addMouseTrackListener(new MouseTrackListener() {
			@Override
			public void mouseHover(MouseEvent arg0) {
				lbBack.setImage(SWTResourceManager.getImage("images/music/back_select.png"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbBack.setImage(SWTResourceManager.getImage("images/music/back_default.png"));
			}
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbBack.setImage(SWTResourceManager.getImage("images/music/back_select.png"));
			}
		});
		//点击鼠标事件
		lbBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try{
					lbBack.setEnabled(false);
					mp3Back();
					lbBack.setEnabled(true);
				}catch(Exception e){
					MsgBox.warning(getShell(), e.getMessage());
				}
			}
		});
		
		final CLabel lbPlay = new CLabel(group, SWT.NONE|SWT.CENTER);
		formData = new FormData();
		formData.bottom = new FormAttachment(100,-6);
		formData.left = new FormAttachment(lbBack,20);
		lbPlay.setLayoutData(formData);
		lbPlay.setData("播放");
		lbPlay.setImage(SWTResourceManager.getImage("images/music/play_default.png"));
		//播放按钮事件
		lbPlay.addMouseTrackListener(new MouseTrackListener() {
			@Override
			public void mouseHover(MouseEvent arg0) {
				if(lbPlay.getData().equals("播放")){
					lbPlay.setImage(SWTResourceManager.getImage("images/music/play_select.png"));
				}else if(lbPlay.getData().equals("暂停")){
					lbPlay.setImage(SWTResourceManager.getImage("images/music/pause_select.png"));
				}
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				if(lbPlay.getData().equals("播放")){
					lbPlay.setImage(SWTResourceManager.getImage("images/music/play_default.png"));
				}else if(lbPlay.getData().equals("暂停")){
					lbPlay.setImage(SWTResourceManager.getImage("images/music/pause_default.png"));
				}
			}
			@Override
			public void mouseEnter(MouseEvent arg0) {
				if(lbPlay.getData().equals("播放")){
					lbPlay.setImage(SWTResourceManager.getImage("images/music/play_select.png"));
				}else if(lbPlay.getData().equals("暂停")){
					lbPlay.setImage(SWTResourceManager.getImage("images/music/pause_select.png"));
				}
			}
		});
		
		//点击鼠标事件
		lbPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try{
					if(lbPlay.getData().equals("播放")){
						mp3Play();
						lbPlay.setData("暂停");
						lbPlay.setImage(SWTResourceManager.getImage("images/music/pause_default.png"));
					}else if(lbPlay.getData().equals("暂停")){
						mp3Pause();
						lbPlay.setData("播放");
						lbPlay.setImage(SWTResourceManager.getImage("images/music/play_default.png"));
					}
				}catch(Exception e){
					MsgBox.warning(getShell(), e.getMessage());
				}
			}
		});
		
		final CLabel lbSetp = new CLabel(group, SWT.NONE|SWT.CENTER);
		formData = new FormData();
		formData.bottom = new FormAttachment(100,-9);
		formData.left = new FormAttachment(lbPlay,20);
		lbSetp.setLayoutData(formData);
		lbSetp.setImage(SWTResourceManager.getImage("images/music/setp_default.png"));
		//鼠标移入、移开事件
		lbSetp.addMouseTrackListener(new MouseTrackListener() {
			@Override
			public void mouseHover(MouseEvent arg0) {
				lbSetp.setImage(SWTResourceManager.getImage("images/music/setp_select.png"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbSetp.setImage(SWTResourceManager.getImage("images/music/setp_default.png"));
			}
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbSetp.setImage(SWTResourceManager.getImage("images/music/setp_select.png"));
			}
		});
		//点击鼠标事件
		lbSetp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try{
					lbSetp.setEnabled(false);
					mp3Next();
					lbSetp.setEnabled(true);
				}catch(Exception e){
					MsgBox.warning(getShell(), e.getMessage());
				}
			}
		});
		
		scaleVolume = new Scale(group, SWT.NONE|SWT.CENTER|SWT.VERTICAL);
		scaleVolume.setMinimum(0);
		scaleVolume.setMaximum(86);
		formData = new FormData();
		formData.right= new FormAttachment(100,-60);
		formData.top = new FormAttachment(0,25);
		formData.width =45;
		formData.bottom = new FormAttachment(100,-40);
		scaleVolume.setCapture(true);
		scaleVolume.setLayoutData(formData);
		scaleVolume.setVisible(false);
		scaleVolume.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try{
					setVolume(86-scaleVolume.getSelection());
				}catch(Exception e){
					MsgBox.warning(getShell(), e.getMessage());
				}
			}
		});
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("专辑名称","soundName",250));
		columns.add(new GridColumn("时长","soundTime",90));
		columns.add(new GridColumn("演唱","signer",250));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		
		gridMusic = new GridView(group, SWT.NONE);
		gridMusic.CreateTabel(gridConfig);
		gridMusic.table.setForeground(SWTResourceManager.getColor(3,174,218));
		formData = new FormData();
		formData.left= new FormAttachment(0);
		formData.top = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100,-52);
		gridMusic.setLayoutData(formData);
		gridMusic.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				try{
					int index = gridMusic.getSelectionIndex();
					mp3Stop();
					Thread.sleep(100);
					setIndex(index);
					if(lbPlay.getData().equals("播放")){
						mp3Play();
						lbPlay.setData("暂停");
						lbPlay.setImage(SWTResourceManager.getImage("images/music/pause_default.png"));
					}
				}catch(Exception e){
					MsgBox.warning(getShell(), e.getMessage());
				}
			}
		});
		
		
		lbPlayMusic = new CLabel(group, SWT.NONE);
		lbPlayMusic.setFont(StyleFinal.SYS_FONT);
		lbPlayMusic.setForeground(SWTResourceManager.getColor(186,220,220));
		formData = new FormData();
		formData.left= new FormAttachment(lbSetp,10);
		formData.top = new FormAttachment(gridMusic,0);
		formData.right = new FormAttachment(100,-200);
		lbPlayMusic.setLayoutData(formData);
		
		lbPlayTime = new CLabel(group, SWT.RIGHT);
		lbPlayTime.setFont(StyleFinal.SYS_FONT);
		lbPlayTime.setForeground(SWTResourceManager.getColor(186,220,220));
		formData = new FormData();
		formData.left= new FormAttachment(lbPlayMusic);
		formData.top = new FormAttachment(gridMusic,0);
		formData.right = new FormAttachment(100,-110);
		lbPlayTime.setLayoutData(formData);
		
		scaleSpeed = new Scale(group, SWT.NONE);
		formData = new FormData();
		formData.left= new FormAttachment(lbSetp,5);
		formData.top = new FormAttachment(gridMusic,8);
		formData.right = new FormAttachment(100,-105);
		scaleSpeed.setLayoutData(formData);
		scaleSpeed.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int dur = scaleSpeed.getSelection();
				setDur(dur);
			}
		});
		
		final CLabel lbVolume = new CLabel(group, SWT.NONE|SWT.CENTER);
		formData = new FormData();
		formData.left= new FormAttachment(scaleSpeed,5);
		formData.top = new FormAttachment(gridMusic,13);
		lbVolume.setLayoutData(formData);
		lbVolume.setImage(SWTResourceManager.getImage("images/music/volume_default.png"));
		//点击鼠标事件
		lbVolume.addMouseTrackListener(new MouseTrackListener() {
			@Override
			public void mouseHover(MouseEvent arg0) {
				lbVolume.setImage(SWTResourceManager.getImage("images/music/volume_select.png"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbVolume.setImage(SWTResourceManager.getImage("images/music/volume_default.png"));
			}
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbVolume.setImage(SWTResourceManager.getImage("images/music/volume_select.png"));
			}
		});
		//点击鼠标事件
		lbVolume.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try{
					lbVolume.setEnabled(false);
					if (scaleVolume.getVisible()){
						scaleVolume.setVisible(false);
					}else{
						scaleVolume.setVisible(true);
					}
					lbVolume.setEnabled(true);
				}catch(Exception e){
					MsgBox.warning(getShell(), e.getMessage());
				}
			}
		});
		
		final CLabel lbLoopText = new CLabel(group, SWT.NONE|SWT.CENTER);
		lbLoopText.setForeground(SWTResourceManager.getColor(3,174,218));
		lbLoopText.setText("顺环播放");
		formData = new FormData();
		formData = new FormData();
		formData.left= new FormAttachment(lbVolume,5);
		formData.top = new FormAttachment(gridMusic,5);
		formData.right= new FormAttachment(100,-5);
		lbLoopText.setLayoutData(formData);
		lbLoopText.addMouseTrackListener(new MouseTrackListener() {
			@Override
			public void mouseHover(MouseEvent arg0) {
				lbLoopText.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbLoopText.setForeground(SWTResourceManager.getColor(3,174,218));
			}
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbLoopText.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
			}
		});
		
		final CLabel lbLoopIcon = new CLabel(group, SWT.NONE|SWT.CENTER);
		formData = new FormData();
		formData = new FormData();
		formData.left= new FormAttachment(lbVolume,5);
		formData.top = new FormAttachment(lbLoopText,-3);
		formData.right= new FormAttachment(100,-5);
		lbLoopIcon.setLayoutData(formData);
		lbLoopIcon.setImage(SWTResourceManager.getImage("images/music/loop_default.png"));
		//点击鼠标事件
		lbLoopIcon.addMouseTrackListener(new MouseTrackListener() {
			@Override
			public void mouseHover(MouseEvent arg0) {
				lbLoopIcon.setImage(SWTResourceManager.getImage("images/music/loop_select.png"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbLoopIcon.setImage(SWTResourceManager.getImage("images/music/loop_default.png"));
			}
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbLoopIcon.setImage(SWTResourceManager.getImage("images/music/loop_select.png"));
			}
		});
		//点击鼠标事件
		//构建循环下拉菜单
		final Menu loopMenu = new Menu(getShell(),SWT.POP_UP);
		MenuItem item1 = new MenuItem(loopMenu, SWT.RADIO);
		item1.setText("顺序播放");
		
		MenuItem item2 = new MenuItem(loopMenu, SWT.RADIO);
		item2.setText("单曲循环");
		
		MenuItem item3 = new MenuItem(loopMenu, SWT.RADIO);
		item3.setText("随机播放");
		
		switch (Integer.valueOf(loop)) {
		case 1:
			item1.setSelection(true);
			break;
		case 2:
			item2.setSelection(true);
			break;
		case 3:
			item3.setSelection(true);
			break;
		default:
			break;
		}

		item1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				loop=1;
				lbLoopText.setText("顺序播放");
			}
		});
		
		item2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				loop = 2;
				lbLoopText.setText("单曲循环");
			}
		});
		
		item3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				loop =3;
				lbLoopText.setText("随机播放");
			}
		});
		
		
		lbLoopIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				scaleVolume.setVisible(false);
				loopMenu.setVisible(true);
			}
		});
		
		lbLoopText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				scaleVolume.setVisible(false);
				loopMenu.setVisible(true);
			}
		});
		return group;
	}

	private void initData() {
		try {
			IVmsParameter iVmsParameter = new ImpVmsParameter();
			List<VmsParameter> vmsParameters = iVmsParameter.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			if(null != vmsParameters && vmsParameters.size()>0){
				for (int i = 0; i < vmsParameters.size(); i++) {
					if ("voice_domain".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceDomain = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_code".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceCode = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_version".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceVersion = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_id".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceId = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_pitch".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoicePitch = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_volume".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceVolume = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_speed".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceSpeed = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_puncmode".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoicePuncmode = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_digitmode".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceDigitmode = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_engmode".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceEngmode = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_tagmode".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceTagmode = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_auto".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceAuto = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("voice_sound".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceSound = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("liner_source".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pLinerSource = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("liner_rate".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pLinerRate = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					
					if ("liner_number".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pLinerNumber = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					if ("liner_number".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pLinerNumber = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					
					if ("liner_english".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pLinerEnglish = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					
					if ("liner_grade".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pLinerGrade = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					
					if ("text_grade".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pTextGrade = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					
					if ("voice_grade".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pVoiceGrade = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
					
					if ("liner_minute".equals(vmsParameters.get(i).getParameterCode())){
						if (null != vmsParameters.get(i).getParameterValue() && vmsParameters.get(i).getParameterValue().length()>0){
							pLinerMinute = vmsParameters.get(i).getParameterValue();
						}else{
							return;
						}
					}
				}
			}
			mp3Player = new MP3Player();
			mp3Notice = new MP3Player();
			if ("是".equals(pVoiceAuto)){
				btnAutoVoice.setSelection(true);
			}else{
				btnAutoVoice.setSelection(false);
			}
			scaleVolume.setSelection(86-Integer.valueOf(pVoiceSound));
			setVolume(86-scaleVolume.getSelection());
			
			
			//查询模板
			IVmsTemplate iVmsTemplate = new ImpVmsTemplate();
			templates = iVmsTemplate.queryByOrganizeAndType(CommFinal.organize.getOrganizeSeq(),"1");
			queryBase(true,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void queryMethod(){
		VmsPlayFindUi findUi = new VmsPlayFindUi(this.getShell());
		findUi.setfRouteCode(fRouteCode);
		findUi.setfLinerId(fLinerId);
		findUi.setfReportStatus(fReportStatus);
		findUi.setfPrintbillStatus(fPrintbillStatus);
		findUi.setFmusic(fmusic);
		findUi.setFnotice(fnotice);
		findUi.open();
		if (findUi.getBtnId()==1){
			fRouteCode = findUi.getfRouteCode();
			fLinerId= findUi.getfLinerId();
			fReportStatus = findUi.getfReportStatus();
			fPrintbillStatus = findUi.getfPrintbillStatus();
			fmusic = findUi.isFmusic();
			fnotice = findUi.isFnotice();
			ThreadWaiting waiting = new ThreadWaiting(this,"queryLiner",new Class[]{},new String[]{});
			waiting.task();
		}
	}
	
	public void queryLiner(){
		try {
			IVmsLiner iVmsLiner = new ImpVmsLiner();
			List<VmsLiner> vmsLiners = iVmsLiner.queryAllByCustom(CommFinal.organize.getOrganizeSeq(),
					fRouteCode, fLinerId, fReportStatus, fPrintbillStatus);
			gridLiner.setDataList(vmsLiners);
			tabFolder.setSelection(0);
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void queryBase(boolean queryMusic, boolean queryVoice){
		try{
			//查询音乐
			if(queryMusic){
				IVmsSound iVmsSound = new ImpVmsSound();
				List<VmsSound> musics = iVmsSound.queryAllByCustom(CommFinal.organize.getOrganizeSeq(), "1");
				gridMusic.setDataList(musics);
			}
			//查询文本
			if(queryVoice){
				IVmsNotice iVmsNotice = new ImpVmsNotice();
				IVmsSound iVmsSound = new ImpVmsSound();
				final List<JttsVoice> jttsVoices = new ArrayList<JttsVoice>();
				List<VmsNotice> vmsNotices = iVmsNotice.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
				if (null != vmsNotices && vmsNotices.size()>0){
					for (int i = 0; i < vmsNotices.size(); i++) {
						if (null != vmsNotices.get(i).getNoticeStatus() && "1".equals(vmsNotices.get(i).getNoticeStatus())){
							JttsVoice jttsVoice = new JttsVoice();
							jttsVoice.setVoiceSeq(vmsNotices.get(i).getNoticeSeq());
							jttsVoice.setVoiceType("2");
							jttsVoice.setVoiceName(vmsNotices.get(i).getNoticeName());
							jttsVoice.setVoiceTime(vmsNotices.get(i).getPlayTime());
							jttsVoice.setVoiceContent(vmsNotices.get(i).getNoticeContent());
							jttsVoices.add(jttsVoice);
						}
						
					}
				}
				
				//查询语音
				List<VmsSound> vmsSounds = iVmsSound.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
				if (null != vmsSounds && vmsSounds.size()>0){
					for (int i = 0; i < vmsSounds.size(); i++) {
						if (null != vmsSounds.get(i).getSoundType() && "2".equals(vmsSounds.get(i).getSoundType())
							&& null != vmsSounds.get(i).getSoundStatus() && "1".equals(vmsSounds.get(i).getSoundStatus())){
							JttsVoice jttsVoice = new JttsVoice();
							jttsVoice.setVoiceSeq(vmsSounds.get(i).getSoundSeq());
							jttsVoice.setVoiceType("3");
							jttsVoice.setVoiceName(vmsSounds.get(i).getSoundName());
							jttsVoice.setVoiceTime(vmsSounds.get(i).getPlayTime());
							jttsVoice.setVoiceContent(vmsSounds.get(i).getSoundPath());
							jttsVoices.add(jttsVoice);
						}
						
					}
				}
				gridNotice.setDataList(jttsVoices);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * importLiner方法描述：导入班次
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2018-6-11 上午12:19:46
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return void
	 */
	private void importLiner(){
		try {
			final IVmsLiner iVmsLiner = new ImpVmsLiner();
			linerTimer = new Timer();
			linerTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							try {
								iVmsLiner.importTrafficLiner(CommFinal.organize.getOrganizeSeq(),
										pLinerSource, CommFinal.initConfig());
							} catch (final Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			},1000,Integer.parseInt(pLinerRate)*1000);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void normalPlay(){
		try {
			if(null!=gridLiner.getSelection()){
				List<JttsVoice> linerVoices = voicePublish((List<VmsLiner>)gridLiner.getSelections(), "check_voice",
						null, DateUtils.getNow("HH:mm"), 0.000);
				if (null != linerVoices && linerVoices.size()>0){
					for (int i = 0; i < linerVoices.size(); i++) {
						jttsVoices.add(linerVoices.get(i));
					}
					gridVoice.setDataList(jttsVoices);
				}
			}else{
				MsgBox.warning(getShell(), "请选择播音的项。");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void latePlay(){
		try {
			if(null!=gridLiner.getSelection()){
				VmsPlayCauseUi causeUi = new VmsPlayCauseUi(getShell(), gridLiner, CommFinal.OPER_TYPE_UPDATE);
				causeUi.open();
				VmsTemplate vmsCause = causeUi.getVmsCause();
				if (null != vmsCause){
					List<JttsVoice> linerVoices = voicePublish((List<VmsLiner>)gridLiner.getSelections(), "late_voice",
							vmsCause, DateUtils.getNow("HH:mm"), 0.000);
					if (null != linerVoices && linerVoices.size()>0){
						for (int i = 0; i < linerVoices.size(); i++) {
							jttsVoices.add(linerVoices.get(i));
						}
						gridVoice.setDataList(jttsVoices);
					}
				}
			}else{
				MsgBox.warning(getShell(), "请选择播音的项。");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void stopPlay(){
		try {
			if(null!=gridLiner.getSelection()){
				VmsPlayCauseUi causeUi = new VmsPlayCauseUi(getShell(), gridLiner, CommFinal.OPER_TYPE_UPDATE);
				causeUi.open();
				VmsTemplate vmsCause = causeUi.getVmsCause();
				if (null != vmsCause){
					List<JttsVoice> linerVoices = voicePublish((List<VmsLiner>)gridLiner.getSelections(), "stop_voice",
							vmsCause, DateUtils.getNow("HH:mm"), 0.000);
					if (null != linerVoices && linerVoices.size()>0){
						for (int i = 0; i < linerVoices.size(); i++) {
							jttsVoices.add(linerVoices.get(i));
						}
						gridVoice.setDataList(jttsVoices);
					}
				}
			}else{
				MsgBox.warning(getShell(), "请选择播音的项。");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void urgePlay(){
		try {
			if(null!=gridLiner.getSelection()){
				List<JttsVoice> linerVoices = voicePublish((List<VmsLiner>)gridLiner.getSelections(), "urge_voice",
						null, DateUtils.getNow("HH:mm"), 0.000);
				if (null != linerVoices && linerVoices.size()>0){
					for (int i = 0; i < linerVoices.size(); i++) {
						jttsVoices.add(linerVoices.get(i));
					}
					gridVoice.setDataList(jttsVoices);
				}
			}else{
				MsgBox.warning(getShell(), "请选择播音的项。");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void noticePlay(){
		try {
			List<JttsVoice> voices = (List<JttsVoice>) gridNotice.getSelections();
			if (null != voices && voices.size()>0){
				for (int i = 0; i < voices.size(); i++) {
					if ("2".equals(voices.get(i).getVoiceType())){
						JttsVoice jttsVoice = new JttsVoice();
						jttsVoice.setVoiceSeq(voices.get(i).getVoiceSeq());
						jttsVoice.setVoiceType("2");
						jttsVoice.setVoiceName(voices.get(i).getVoiceName());
						jttsVoice.setVoiceTime(DateUtils.getNow("HH:mm"));
						jttsVoice.setVoiceContent(voices.get(i).getVoiceContent());
						jttsVoice.setVoiceOrder(Double.valueOf(pTextGrade)+0.01);
						jttsVoices.add(jttsVoice);
					}else if ("3".equals(voices.get(i).getVoiceType())){
						JttsVoice jttsVoice = new JttsVoice();
						jttsVoice.setVoiceSeq(voices.get(i).getVoiceSeq());
						jttsVoice.setVoiceType("3");
						jttsVoice.setVoiceName(voices.get(i).getVoiceName());
						jttsVoice.setVoiceTime(DateUtils.getNow("HH:mm"));
						jttsVoice.setVoiceContent(voices.get(i).getVoiceContent());
						jttsVoice.setVoiceOrder(Double.valueOf(pVoiceGrade)+0.01);
						jttsVoices.add(jttsVoice);
					}
				}
				gridVoice.setDataList(jttsVoices);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void upMethod(){
		gridVoice.upTableRow();
	}
	
	public void downMethod(){
		gridVoice.downTableRow();
	}
	
	public void deleteMethod(){
		gridVoice.deleteRow(gridVoice.getSelectionIndexs());
	}
	
	/**
	 * voiceBuild方法描述：生成语音池
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2018-6-16 上午01:34:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return void
	 */
	private void voiceBuild(){
		voiceTimer = new Timer();
		voiceTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						try {
							List<JttsVoice> allVoices = new ArrayList<JttsVoice>();
							String currTime = DateUtils.getNow("HH:mm");
							//查询班次
							IVmsLiner iVmsLiner = new ImpVmsLiner();
							IVmsNotice iVmsNotice = new ImpVmsNotice();
							IVmsSound iVmsSound = new ImpVmsSound();
							List<VmsLiner> vmsLiners = iVmsLiner.queryByStatusAndTime(CommFinal.organize.getOrganizeSeq(),
									"1",currTime, pLinerMinute);
							if (null != vmsLiners && vmsLiners.size()>0){
								//按班次时间合并分批
								List<String> strings = new ArrayList<String>();
								boolean isAdd = true;
								for (int i = 0; i < vmsLiners.size(); i++) {
									isAdd = true;
									if (null != strings && vmsLiners.size()>0){
										for (int j = 0; j < strings.size(); j++) {
											if (strings.get(j).equals(vmsLiners.get(i).getLinerTime())){
												isAdd = false;
												break;
											}
										}
									}
									if(isAdd ==true){
										strings.add(vmsLiners.get(i).getLinerTime());
									}
								}
								DecimalFormat df = new DecimalFormat(".0000");
								Double addIndex =0.000;
								Double addIndey =0.000;
								for (int i = 0; i < strings.size(); i++) {
									List<VmsLiner> liners = new ArrayList<VmsLiner>();
									for (int j = 0; j < vmsLiners.size(); j++) {
										if (strings.get(i).equals(vmsLiners.get(j).getLinerTime())){
											liners.add(vmsLiners.get(j));
										}
									}
									List<JttsVoice> linerVoices = voicePublish(liners,"check_voice",null,currTime,addIndex);
									if (null != linerVoices && linerVoices.size()>0){
										addIndey =0.000;
										for (int k = 0; k < Integer.valueOf(pLinerNumber); k++) {
											addIndex +=0.001;
											addIndey +=0.001;
											for (int l = 0; l < linerVoices.size(); l++) {
												JttsVoice jttsVoice = new JttsVoice();
												BeanUtils.copyProperties(jttsVoice, linerVoices.get(l));
												jttsVoice.setVoiceOrder(Double.valueOf(df.format(jttsVoice.getVoiceOrder()))+addIndey);
												allVoices.add(jttsVoice);
											}		
										}
									}
								}
							}
							List<VmsNotice> vmsNotices = iVmsNotice.queryByStatusAndTime(CommFinal.organize.getOrganizeSeq(), "1", currTime);
							if (null != vmsNotices && vmsNotices.size()>0){
								Double addIndex =0.001;
								for (int i = 0; i < vmsNotices.size(); i++) {
									if(i!=0){
										addIndex +=0.001;
									}
									JttsVoice jttsVoice = new JttsVoice();
									jttsVoice.setVoiceSeq(vmsNotices.get(i).getNoticeSeq());
									jttsVoice.setVoiceType("2");
									jttsVoice.setVoiceName(vmsNotices.get(i).getNoticeName());
									jttsVoice.setVoiceTime(vmsNotices.get(i).getPlayTime());
									jttsVoice.setVoiceContent(vmsNotices.get(i).getNoticeContent());
									jttsVoice.setVoiceOrder(Double.valueOf(pTextGrade)+0.01);
									for (int j = 0; j < Integer.valueOf(vmsNotices.get(i).getPlayNumber()); j++) {
										jttsVoice.setVoiceOrder(Double.valueOf(pTextGrade)+addIndex);
										allVoices.add(jttsVoice);
									}
									
								}
							}
							List<VmsSound> vmsSounds = iVmsSound.queryByStatusAndTime(CommFinal.organize.getOrganizeSeq(), "1", currTime);
							if (null != vmsSounds && vmsSounds.size()>0){
								Double addIndex =0.001;
								for (int i = 0; i < vmsSounds.size(); i++) {
									if(i!=0){
										addIndex +=0.001;
									}
									JttsVoice jttsVoice = new JttsVoice();
									jttsVoice.setVoiceSeq(vmsSounds.get(i).getSoundSeq());
									jttsVoice.setVoiceType("3");
									jttsVoice.setVoiceName(vmsSounds.get(i).getSoundName());
									jttsVoice.setVoiceTime(vmsSounds.get(i).getPlayTime());
									jttsVoice.setVoiceContent(vmsSounds.get(i).getSoundPath());
									jttsVoice.setVoiceOrder(Double.valueOf(pVoiceGrade)+0.01);
									for (int j = 0; j < Integer.valueOf(vmsSounds.get(i).getPlayNumber()); j++) {
										jttsVoice.setVoiceOrder(Double.valueOf(pVoiceGrade)+addIndex);
										allVoices.add(jttsVoice);
									}
								}
							}
							Collections.sort(allVoices, new Comparator<JttsVoice>() {
								@Override  
					            public int compare(JttsVoice voiceOne, JttsVoice voiceTwo) {
									if(voiceOne.getVoiceOrder() > voiceTwo.getVoiceOrder()){
					                    return 1;
					                }
					                if(voiceOne.getVoiceOrder() == voiceTwo.getVoiceOrder()){
					                    return 0;
					                }
					                return -1;
					            }
							});
							if (null != allVoices && allVoices.size()>0){
								for (int i = 0; i < allVoices.size(); i++) {
									jttsVoices.add(allVoices.get(i));
								}
								gridVoice.setDataList(jttsVoices);
							}
						} catch (final Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		},1000,59*1000);
	}
	
	@SuppressWarnings("rawtypes")
	private List<JttsVoice> voicePublish(List<VmsLiner> vmsLiners,String templateCode,
			VmsTemplate vmsCause, String playTime, Double addIndex) throws Exception {
		List<JttsVoice> voices = new ArrayList<JttsVoice>();
		if (null == templates || templates.size()<=0){
			throw new Exception("播音模板不存在！");
		}
		for (int i = 0; i < templates.size(); i++) {
			if (templateCode.equals(templates.get(i).getTemplateCode())){
				//中文播音模板
				String templateCn = JSONObject.fromObject(templates.get(i).getTemplateCn()).toString();
				JSONObject templatejsonCn = JSONObject.fromObject(templateCn);
		        Map templateMapCn = (Map)templatejsonCn;
		        //英文播音模板
				String templateEn = JSONObject.fromObject(templates.get(i).getTemplateEn()).toString();
				JSONObject templatejsonEn = JSONObject.fromObject(templateEn);
		        Map templateMapEn = (Map)templatejsonEn;
		        //多班多中文模板
		        String voiceCnMulti = JSONObject.fromObject(templateMapCn.get("voice_multi").toString()).toString();
		        JSONObject jsonCnMulti = JSONObject.fromObject(voiceCnMulti);
		        Map multiCnMap = (Map)jsonCnMulti;
		        String voiceCnHeader = multiCnMap.get("header").toString();
		        String modelCnDetail = multiCnMap.get("detail").toString();
		        String voiceCnDetail = "";
		        String voiceCnFooter = multiCnMap.get("footer").toString();
		        //多班次英文模板
		        String voiceEnMulti = JSONObject.fromObject(templateMapEn.get("voice_multi").toString()).toString();
		        JSONObject jsonEnMulti = JSONObject.fromObject(voiceEnMulti);
				Map multiEnMap = (Map)jsonEnMulti;
				String voiceEnHeader = multiEnMap.get("header").toString();
				String modelEnDetail = multiEnMap.get("detail").toString();
				String voiceEnDetail = "";
				String voiceEnFooter = multiEnMap.get("footer").toString();
		        //单班次模板
		        String voiceCnSingle = templateMapCn.get("voice_single").toString();
		        String voiceEnSingle = templateMapEn.get("voice_single").toString();
				if(vmsLiners.size()>1){
			        if (null != voiceCnMulti && voiceCnMulti.length()>0){
			        	voiceCnHeader = convertVoice(vmsLiners.get(0), vmsCause, voiceCnHeader);
			        	for (int j = 0; j < vmsLiners.size(); j++) {
			        		if (j==0){
			        			voiceCnDetail = convertVoice(vmsLiners.get(j), vmsCause, modelCnDetail);
			        		}else{
			        			voiceCnDetail += convertVoice(vmsLiners.get(j), vmsCause, modelCnDetail);
			        		}
			        		
						}
			        	voiceCnFooter = convertVoice(vmsLiners.get(i), vmsCause, voiceCnFooter);
			        	JttsVoice jttsVoice = new JttsVoice();
		        		jttsVoice.setVoiceSeq(vmsLiners.get(0).getLinerSeq());
		        		jttsVoice.setVoiceName("班次播报");
		        		jttsVoice.setVoiceType("1");
		        		jttsVoice.setVoiceTime(playTime);
		        		jttsVoice.setVoiceContent(voiceCnHeader + voiceCnDetail + voiceCnFooter);
		        		jttsVoice.setVoiceOrder(Double.valueOf(pLinerGrade)+addIndex+0.0001);
		        		voices.add(jttsVoice);
			        }else{
				        if (null != voiceCnSingle && voiceCnSingle.length()>0){
				        	for (int j = 0; j < vmsLiners.size(); j++) {
				        		JttsVoice jttsVoice = new JttsVoice();
				        		jttsVoice.setVoiceSeq(vmsLiners.get(j).getLinerSeq());
				        		jttsVoice.setVoiceName("班次播报");
				        		jttsVoice.setVoiceType("1");
				        		jttsVoice.setVoiceTime(playTime);
				        		jttsVoice.setVoiceContent(convertVoice(vmsLiners.get(j), vmsCause, voiceCnSingle));
				        		jttsVoice.setVoiceOrder(Double.valueOf(pLinerGrade)+addIndex+0.0001);
				        		voices.add(jttsVoice);
							}
				        }else{
				        	throw new Exception("未设置"+templateCode+ "中文播音模板");
				        }
			        }
			        if (pLinerEnglish.equals("是")){
			        	if (null != voiceEnMulti && voiceEnMulti.length()>0){
							voiceEnHeader = convertVoice(vmsLiners.get(0), vmsCause, voiceEnHeader);
							for (int j = 0; j < vmsLiners.size(); j++) {
								if(j==0){
									voiceEnDetail = convertVoice(vmsLiners.get(j), vmsCause, modelEnDetail);
								}else{
									voiceEnDetail += convertVoice(vmsLiners.get(j), vmsCause, modelEnDetail);
								}
							}
							voiceEnFooter = convertVoice(vmsLiners.get(i), vmsCause, voiceEnFooter);
							JttsVoice jttsVoice = new JttsVoice();
							jttsVoice.setVoiceSeq(vmsLiners.get(0).getLinerSeq());
							jttsVoice.setVoiceName("班次播报");
							jttsVoice.setVoiceType("1");
							jttsVoice.setVoiceTime(playTime);
							jttsVoice.setVoiceContent(voiceEnHeader + voiceEnDetail + voiceEnFooter);
							jttsVoice.setVoiceOrder(Double.valueOf(pLinerGrade)+addIndex+0.0002);
							voices.add(jttsVoice);
			        	}else{
					        if (null != voiceEnSingle && voiceEnSingle.length()>0){
					        	for (int j = 0; j < vmsLiners.size(); j++) {
					        		JttsVoice jttsVoice = new JttsVoice();
					        		jttsVoice.setVoiceSeq(vmsLiners.get(j).getLinerSeq());
					        		jttsVoice.setVoiceName("班次播报");
					        		jttsVoice.setVoiceType("1");
					        		jttsVoice.setVoiceTime(playTime);
					        		jttsVoice.setVoiceContent(convertVoice(vmsLiners.get(j), vmsCause, voiceEnSingle));
					        		jttsVoice.setVoiceOrder(Double.valueOf(pLinerGrade)+addIndex+0.0002);
					        		voices.add(jttsVoice);
								}
					        }else{
					        	throw new Exception("未设置"+templateCode+ "英文播音模板");
					        }
			        	}
			        }
				}else{
					if (null != voiceCnSingle && voiceCnSingle.length()>0){
						for (int j = 0; j < vmsLiners.size(); j++) {
			        		JttsVoice jttsVoice = new JttsVoice();
			        		jttsVoice.setVoiceSeq(vmsLiners.get(j).getLinerSeq());
			        		jttsVoice.setVoiceName("班次播报");
			        		jttsVoice.setVoiceType("1");
			        		jttsVoice.setVoiceTime(playTime);
			        		jttsVoice.setVoiceContent(convertVoice(vmsLiners.get(j), vmsCause, voiceCnSingle));
			        		jttsVoice.setVoiceOrder(Double.valueOf(pLinerGrade)+addIndex+0.0001);
			        		voices.add(jttsVoice);
						}
					}else{
						if (null != voiceCnMulti && voiceCnMulti.length()>0){
							voiceCnHeader = convertVoice(vmsLiners.get(0), vmsCause, voiceCnHeader);
							for (int j = 0; j < vmsLiners.size(); j++) {
								if(j==0){
									voiceCnDetail = convertVoice(vmsLiners.get(j), vmsCause, modelCnDetail);
								}else{
									voiceCnDetail += convertVoice(vmsLiners.get(j), vmsCause, modelCnDetail);
								}
							}
							voiceCnFooter = convertVoice(vmsLiners.get(0), vmsCause, voiceCnFooter);
							JttsVoice jttsVoice = new JttsVoice();
							jttsVoice.setVoiceSeq(vmsLiners.get(0).getLinerSeq());
							jttsVoice.setVoiceName("班次播报");
							jttsVoice.setVoiceType("1");
							jttsVoice.setVoiceTime(playTime);
							jttsVoice.setVoiceContent(voiceCnHeader + voiceCnDetail + voiceCnFooter);
							jttsVoice.setVoiceOrder(Double.valueOf(pLinerGrade)+addIndex+0.0001);
							voices.add(jttsVoice);
						}else{
							throw new Exception("未设置"+templateCode+ "中文播音模板");
						}
					}
					if (pLinerEnglish.equals("是")){
			        	if (null != voiceEnSingle && voiceEnSingle.length()>0){
			        		for (int j = 0; j < vmsLiners.size(); j++) {
				        		JttsVoice jttsVoice = new JttsVoice();
				        		jttsVoice.setVoiceSeq(vmsLiners.get(j).getLinerSeq());
				        		jttsVoice.setVoiceName("班次播报");
				        		jttsVoice.setVoiceType("1");
				        		jttsVoice.setVoiceTime(playTime);
				        		jttsVoice.setVoiceContent(convertVoice(vmsLiners.get(j), vmsCause, voiceEnSingle));
				        		jttsVoice.setVoiceOrder(Double.valueOf(pLinerGrade)+addIndex+0.0002);
				        		voices.add(jttsVoice);
							}
			        	}else{
					        if (null != voiceEnMulti && voiceEnMulti.length()>0){
								voiceEnHeader = convertVoice(vmsLiners.get(0), vmsCause, voiceEnHeader);
								for (int j = 0; j < vmsLiners.size(); j++) {
									if(j==0){
										voiceEnDetail = convertVoice(vmsLiners.get(j), vmsCause, modelEnDetail);
									}else{
										voiceEnDetail += convertVoice(vmsLiners.get(j), vmsCause, modelEnDetail);
									}
								}
								voiceEnFooter = convertVoice(vmsLiners.get(0), vmsCause, voiceEnFooter);
								JttsVoice jttsVoice = new JttsVoice();
								jttsVoice.setVoiceSeq(vmsLiners.get(0).getLinerSeq());
								jttsVoice.setVoiceName("班次播报");
								jttsVoice.setVoiceType("1");
								jttsVoice.setVoiceTime(playTime);
								jttsVoice.setVoiceContent(voiceEnHeader + voiceEnDetail + voiceEnFooter);
								jttsVoice.setVoiceOrder(Double.valueOf(pLinerGrade)+addIndex+0.0002);
								voices.add(jttsVoice);
					        }else{
					        	throw new Exception("未设置"+templateCode+ "英文播音模板");
					        }
			        	}
					}
				}
				break;
			}
		}
		return voices;
	}
	
	private String convertVoice(VmsLiner vmsLiner,VmsTemplate vmsCause,String templateInfo){
		if (null != templateInfo && templateInfo.length()>0){
			templateInfo = templateInfo.replace("[route_name]", vmsLiner.getRouteName());
			templateInfo = templateInfo.replace("[liner_id]", vmsLiner.getLinerId());
			templateInfo = templateInfo.replace("[liner_time]", vmsLiner.getLinerTime());
			templateInfo = templateInfo.replace("[cargrade_name]", vmsLiner.getCargradeName());
			templateInfo = templateInfo.replace("[check_code]", vmsLiner.getCheckCode());
			if (null != vmsLiner.getParkingCode() && vmsLiner.getParkingCode().length()>0){
				templateInfo = templateInfo.replace("[parking_name]", vmsLiner.getParkingCode()+"发车位");
			}else{
				templateInfo = templateInfo.replace("[parking_name]", "");
			}
			
			if(null != vmsCause){
				templateInfo = templateInfo.replace("[cause_cn]", vmsCause.getTemplateCn());
				templateInfo = templateInfo.replace("[cause_en]", vmsCause.getTemplateEn());
			}
		}
		return templateInfo;
	}
	
	private void voicePlay(){
		playTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if("否".equals(pVoiceAuto)){
					return;
				}
				int tempVolume = getVolume();
				if (null == jttsVoices || jttsVoices.size() <= 0) {
					while(tempVolume > volume){
						setVolume(getVolume() + 1);
						try {
							Thread.sleep(30);
						} catch (InterruptedException e) {
						}
					}
					return;
				}
				while(null != jttsVoices && jttsVoices.size() >0){
					final JttsVoice jttsVoice = jttsVoices.get(0);
					jttsVoices.remove(jttsVoice);
					// 检测背景音乐是否正在播放
					if (null != jttsVoice && JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus() == 3) {
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								try {
									gridVoice.setDataList(jttsVoices);
									txtVoice.setText(jttsVoice.getVoiceContent());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						while(volume > 0){
							if (null == mp3Player){
								return;
							}
							setVolume((getVolume() - 1));
							try {
								Thread.sleep(30);
							} catch (InterruptedException e) {
							}
						}
						
						if (jttsVoice.getVoiceType().equals("3")) {
							mp3Notice.setFileName(jttsVoice.getVoiceContent());
							float vol = (float) (-80 + (0.86 * Integer.parseInt(pVoiceSound)));
							mp3Notice.setVolume(vol);
							mp3Notice.play();
							try {
								Thread.sleep(3000);
								if (null == mp3Notice){
									return;
								}
								while (mp3Notice.getState() == 1) {
									Thread.sleep(1000);
								}
							} catch (InterruptedException e) {
							}
						}else {
							JttsUtil.CLibrary.INSTANCE.jTTS_Play(jttsVoice.getVoiceContent(), 8192);
							if(JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus() != 0){
								while(JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus() !=3){
									try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
									}
								}
							}else{
								// 设置界面上mp3音量标识可用。
								Display.getDefault().asyncExec(new Runnable() {
									@Override
									public void run() {
										txtVoice.setText("语音库未被初始化，不能播放");
									}
								});
							}
						}
						// 当检测到播放内容列表中为空，或数量为0时，调高音量
						if (null == jttsVoices || jttsVoices.size() <= 0) {
							if (null == mp3Player){
								return;
							}
							while(tempVolume > volume){
								setVolume(getVolume() + 1);
								try {
									Thread.sleep(30);
								} catch (InterruptedException e) {
								}
							}
						}
						// 设置界面上mp3音量标识可用。
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								txtVoice.setText("");
							}
						});
					}
				}
			}
		}, 1000, 1000);
	}
	
	/**
	 * 得到循环播放方式：
	 * 1=顺序播放,2=单曲循环,3=随机播放
	 * @return int
	 */
	public int getLoop() {
		return loop;
	}

	/**
	 * 设置循环播放方式
	 * 1=顺序播放,2=单曲循环,3=随机播放
	 * @param loop 
	 */
	public void setLoop(int loop) {
		this.loop = loop;
	}
	
	/**
	 * 开始播放
	 * @throws MP3Exception 
	 * @throws InterruptedException 
	 */
	public void mp3Play() throws MP3Exception, InterruptedException{
		if(this.mp3Player.getState()==2){
			this.mp3Pause();
		}
		if(null == mp3Thread){
			
			playIndex = gridMusic.getSelectionIndex();
			if (playIndex == -1){
				if (null != gridMusic.getDataList() && gridMusic.getDataList().size()>0){
					playIndex = 0;
				}
			}
			mp3Thread = new Thread(new playThread());
			mp3Thread.start();
		}
	}
	
	class playThread extends Thread{
		public void run(){
			while(true){
				//等待状态
				while(mp3Player != null && mp3Player.getState()!=0){
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							if(flag == false){
								return ;
							}
							scaleSpeed.setSelection(mp3Player.getPrett());
						}
					});
					if(flag == false){
						return ;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
				if(flag == false){
					return ;
				}
				index = playIndex;
				final VmsSound vmsSound = (VmsSound) gridMusic.getSelection(index);
				
				if(null == vmsSound){
					continue;
				}
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						gridMusic.table.deselectAll();
						gridMusic.setSelection(index);
						lbPlayMusic.setText(vmsSound.getSoundName()+ "--" + vmsSound.getSigner());
						lbPlayTime.setText(vmsSound.getSoundTime());
					}
				});
				mp3Player.setFileName(vmsSound.getSoundPath());
				if(mp3Player.getState()==0){
					mp3Player.play();
					//暂停20毫秒，等待播放器播放
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
				}
				//顺序播放
				switch (loop) {
				case 1:
					if(playIndex !=0 && playIndex < gridMusic.getDataList().size() -1){
						playIndex ++;
					}else{
						playIndex = -1;
					}
					break;
				case 2:
					break;
				case 3:
					playIndex = new Random().nextInt(gridMusic.getDataList().size());
					break;
				default:
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	/**
	 * prePlay方法慨述:上一首播放
	 * @throws InterruptedException 
	 * @throws MP3Exception 
	 */
	public void mp3Back() throws MP3Exception, InterruptedException{
		if(index >0){
			index --;
		}else{
			index = 0;
		}
		playIndex = index;
		this.mp3Stop();
		this.mp3Play();
	}
	
	/**
	 * nextPlay方法慨述:播放下首 
	 * @throws MP3Exception
	 * @throws InterruptedException void
	 */
	public void mp3Next() throws MP3Exception, InterruptedException{
		if(index < gridMusic.getDataList().size() -1){
			index = index +1;
		}else{
			index = gridMusic.getDataList().size() -1;
		}	
		playIndex = index;
		this.mp3Stop();
		this.mp3Play();
	}
	
	/**
	 * stop方法慨述:停止播放
	 * void
	 */
	public void mp3Stop(){
		this.mp3Player.stop();
	}
	
	/**
	 * pause方法慨述:暂停播放
	 * void
	 */
	public void mp3Pause(){
		try {
			Thread.sleep(20);
			this.mp3Player.pause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 得到音量控制
	 * @return 0-100
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * 设置音量大小
	 * @param volume 0-100
	 */
	public void setVolume(int volume) {
		this.volume = volume;
		//最小为-80，最大值为6
		float vol = (float) (-80+(0.86*volume));
		this.mp3Player.setVolume(vol);
	}
	
	/**
	 * setDur方法慨述:设置播放进度
	 * @param duration void
	 */
	public void setDur(int duration){
		this.mp3Player.setSetDur(duration);
	}
	/**
	 * setIndex方法慨述:设置播放索引
	 * @param index void
	 */
	public void setIndex(int index){
		this.index = index;
		this.playIndex = index;
	}
	
	/**
	 * getState方法慨述:为0表示停止播放，1表示正在播放，2表示暂停
	 * @return int
	 */
	public int getState(){
		return this.mp3Player.getState();
	}
	
	/**
	 * initVoice方法慨述:初始化语音库
	 * @return int
	 */
	private int initVoice(){
		//初始化JTTS类
		int re = JttsUtil.CLibrary.INSTANCE.jTTS_Init(null, null);
		try {
			if(re !=0 && re !=1){
				MsgBox.warning(getShell(), "初始化JTTS失败");
				return re;
			}
			//语音领域 default =6
			if (null != pVoiceDomain && pVoiceDomain.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_DOMAIN, 
						Integer.parseInt(pVoiceDomain));
				if(re !=0){
					MsgBox.warning(getShell(), "初始化语音库领域失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "语音库领域未设置");
				return re;
			}
			if (null != pVoiceId && pVoiceId.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_PreLoad(pVoiceId);
				if(re !=0){
					MsgBox.warning(getShell(), "初始化语音库标识号失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "语音库标识号未设置");
				return re;
			}
			if (null != pVoiceCode && pVoiceCode.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_CODEPAGE,
						Integer.parseInt(pVoiceCode));
				if(re !=0){
					MsgBox.warning(getShell(), "初始化语音库代码集失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "语音库代码集未设置");
				return re;
			}
			
			//标点符号读法
			if (null != pVoicePuncmode && pVoicePuncmode.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_PUNCMODE, 
						Integer.parseInt(pVoicePuncmode));
				if(re !=0){
					MsgBox.warning(getShell(), "初始化标点读法失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "标点读法未设置");
				return re;
			}
			//数字读法
			if (null != pVoiceDigitmode && pVoiceDigitmode.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_DIGITMODE, 
						Integer.parseInt(pVoiceDigitmode));
				if(re !=0){
					MsgBox.warning(getShell(), "初始化数字读法失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "数字读法未设置");
				return re;
			}
			//英文拼读方式 default =1
			if (null != pVoiceEngmode && pVoiceEngmode.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_ENGMODE, 
						Integer.parseInt(pVoiceEngmode));
				if(re !=0){
					MsgBox.warning(getShell(), "初始化英文读法失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "英文读法未设置");
				return re;
			}
			//标记检测
			if (null != pVoiceTagmode && pVoiceTagmode.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_TAGMODE, 
						Integer.parseInt(pVoiceTagmode));
				if(re !=0){
					MsgBox.warning(getShell(), "初始化标记读法失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "标记读法未设置");
				return re;
			}
			//基频
			if (null !=pVoicePitch && pVoicePitch.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_PITCH, 
						Integer.parseInt(pVoicePitch));
				if(re !=0){
					MsgBox.warning(getShell(), "初始化合成基频失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "合成基频未设置");
				return re;
			}
			//合成语速
			if (null !=pVoiceSpeed && pVoiceSpeed.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_SPEED, 
						Integer.parseInt(pVoiceSpeed));
				if(re !=0){
					MsgBox.warning(getShell(), "初始化合成语速失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "合成语速未设置");
				return re;
			}
			//合成音量
			if (null != pVoiceVolume&& pVoiceVolume.length()>0){
				re = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_VOLUME, 
						Integer.parseInt(pVoiceVolume));
				if(re !=0){
					MsgBox.warning(getShell(), "初始化合成音量失败");
					return re;
				}
			}else{
				MsgBox.warning(getShell(), "合成音量未设置");
				return re;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return re;
	}
	
	/**
	 * stop方法慨述:停止播放函数 
	 * void
	 */
	public void jttsStop(){
		if(this.mp3Player.getState()==1){
			this.mp3Player.stop();
		}
		if(JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus()==1){
			System.out.println(JttsUtil.CLibrary.INSTANCE.jTTS_Stop());
		}
	}
	
	/**
	 * pause方法慨述:暂停播放
	 * void
	 */
	public void jttsPause(){
		if(this.mp3Player.getState()==1){
			this.mp3Player.pause();
		}
		if(JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus()==1){
			System.out.println(JttsUtil.CLibrary.INSTANCE.jTTS_Pause());;
		}
	}
	
	/**
	 * continuePlay方法慨述:恢复播放
	 * <br> void
	 */
	public void jttsResume(){
		if(this.mp3Player.getState()==2){
			this.mp3Player.pause();
		}
		if(JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus()==2){
			System.out.println(JttsUtil.CLibrary.INSTANCE.jTTS_Resume());
		}
	}

	/**
	 * jttsEnd方法描述：释放JTTS
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2018-6-10 上午08:39:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return void
	 */
	public void jttsEnd() {
		if(JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus()!=3){
			JttsUtil.CLibrary.INSTANCE.jTTS_Stop();
		}
		//释放jtts
		JttsUtil.CLibrary.INSTANCE.jTTS_End();
	}

	/**
	 * despose方法慨述:销毁对象方法
	 * void
	 */
	public void dispose() {
		flag = false;
		if (mp3Player != null) {
			mp3Player.stop();
			mp3Player = null;
		}
		if (mp3Notice != null) {
			mp3Notice.stop();
			mp3Notice = null;
		}
		if (imageTimer != null) {
			imageTimer.cancel();
			imageTimer = null;
		}
		if (linerTimer != null) {
			linerTimer.cancel();
			linerTimer = null;
		}
		if (voiceTimer != null) {
			voiceTimer.cancel();
			voiceTimer = null;
		}
		if (playTimer != null) {
			playTimer.cancel();
			playTimer = null;
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
		}
		mp3Thread = null;
		if(JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus()!=3){
			JttsUtil.CLibrary.INSTANCE.jTTS_Stop();
		}
		//释放jtts
		JttsUtil.CLibrary.INSTANCE.jTTS_End();
	}
	
}
