package com.zhima.widget;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.nebula.widgets.formattedtext.DateTimeFormatter;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.ITextFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.exception.UserSystemException;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;

/**
 * userDate概要说明：点击弹出日历选择日期，光标离开日历后自动消失。
 * @author lcy
 */

public class CCalendar extends Composite {
	private Composite compDate;//自定义日期面板
	public Text txtDate;//日期显示文本
	private Label lbDate;//日期先择标签
	private Shell shell;//弹出日历
	private DateTime dateTime;//弹出显示日历
	private String fmDt;
	private int dropX = 0;
	private int dropY = 0;
	
	/**
	 * 构造函数:默认年月日日期控件
	 * @param parent
	 * @param style
	 */
	public CCalendar(Composite parent,int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		fmDt = "yyyy-MM-dd";
		createDate();
	}
	/**
	 * 构造函数:自定义格式的日期控件
	 * @param parent
	 * @param fmDate
	 * @param style
	 */
	public CCalendar(Composite parent,String fmDate,int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		fmDt = fmDate;
		createDate();
	}
	/**
	 * createDate方法慨述:创建自定义选择日历面板
	 * 创 建  人：鲁承毅
	 * 创建时间：2012-7-4 下午03:51:42
	 * 修 改  人：鲁承毅
	 * 修改日期：2012-7-4 下午03:51:42
	 * @return Composite 
	 */
	public Composite createDate() {
		compDate = new Composite(this, SWT.NONE);
		compDate.setLayout(new FormLayout());
	
		FormattedText formattedText = new FormattedText(compDate, SWT.NONE);
		ITextFormatter dateFormatter = new DateTimeFormatter(fmDt);
		formattedText.setFormatter(dateFormatter);
		txtDate = formattedText.getControl();
		SimpleDateFormat df = new SimpleDateFormat(fmDt);
		Calendar curDate = Calendar.getInstance();
		txtDate.setText(df.format(curDate.getTime()));
		FormData data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(0);
		data.right = new FormAttachment(100,-16);
		data.bottom = new FormAttachment(100);
		txtDate.setLayoutData(data);
		txtDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==44){
					e.doit=false;
					downDate();
				}
				if(e.keyCode==46){
					e.doit=false;
					upDate();
				}
			}
		});
		
		lbDate = new Label(compDate, SWT.NONE | SWT.RIGHT);
		String path = System.getProperty("user.dir");
		lbDate.setImage(SWTResourceManager.getImage(path
				+ "\\images\\control\\calendar.png"));
		data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment();
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		lbDate.setLayoutData(data);

		lbDate.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				if(shell == null || shell.isDisposed()){
					shell = new Shell(getShell(),SWT.NO_TRIM | SWT.CLOSE);
					shell.setLayout(new FillLayout());
					shell.setSize(218, 159);
					dateTime = new DateTime(shell, SWT.CALENDAR | SWT.SHORT);
					dateTime.addSelectionListener(new SelectionAdapter() {
			            public void widgetSelected(SelectionEvent e){
			            	setText(formatDt());
			            }
			        });
					dateTime.addFocusListener(new FocusAdapter() {
						public void focusLost(FocusEvent e){
							setText(formatDt());
						    shell.setVisible(false);
						}
				    });
					dateTime.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDoubleClick(MouseEvent e) {
							txtDate.setFocus();
						}
					});
				}
				shellLocation();
				
			}
		});
		return compDate;
	}
	
	/**
	 * shellLocation方法慨述:设置弹出shell显示位置
	 * 创 建  人：鲁承毅
	 * 创建时间：2012-7-13 上午11:41:34
	 * 修 改  人：鲁承毅
	 * 修改日期：2012-7-13 上午11:41:34 void 
	 * @throws
	 */
	private void shellLocation(){
		dropX = 0;
		dropY = 0;
		getPoint(txtDate.getParent());
		
		int screnWidth = Display.getCurrent().getClientArea().width;
		int borderWidth = (txtDate.getShell().getBounds().width - txtDate.getShell().getClientArea().width)/2;
		int screnHeight = Display.getCurrent().getClientArea().height;
		int borderHight = txtDate.getShell().getBounds().height - txtDate.getShell().getClientArea().height;
		
		int x=dropX+txtDate.getBounds().x + borderWidth;
		int y=dropY+txtDate.getBounds().y + txtDate.getBounds().height+borderHight - borderWidth/2;
		
		if(x+shell.getBounds().width>screnWidth){
			x=x-(shell.getBounds().width-compDate.getBounds().width)+borderWidth/2;	
		}
		if(y+shell.getBounds().height>screnHeight){
			y=y-shell.getBounds().height-compDate.getBounds().height-borderWidth/2;		
		}
		if(shell !=null && shell.isDisposed()==false){
			shell.setLocation(x,y);
			shell.pack();
			shell.open();
		}
	}
	private void getPoint(Composite parent){
		dropX += parent.getBounds().x;
		dropY += parent.getBounds().y;
		if(parent.getParent() != null && !(parent instanceof Shell) ){
			getPoint(parent.getParent());
			if ("Composite".equals(parent.getParent().getClass().getSimpleName())){
				dropY +=1;
			}
		}
	}
	
	/**
	 * 获取日期
	 */
	public String getText() {
		return txtDate.getText();
	}
	/**
	 * 设置日期
	 */
	public void setText(String text) {
		txtDate.setText(text);
	}
	/**
	 * 设置字体颜色
	 */
	public void setForeground(Color color) {
		txtDate.setForeground(color);
	}
	/**
	 * 设置字体
	 */
	public void setFont(Font font) {
		txtDate.setFont(font);
	}
	/**
	 * 是否允许编辑
	 */
	public void setEditable(boolean isEdit){
		txtDate.setEditable(isEdit);
	}
	/**
	 * 格式化日期
	 */
	private String formatDt(){
		SimpleDateFormat df = new SimpleDateFormat(fmDt);
		Date tmpDate = null;
		try{
			SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
			Calendar curDate = Calendar.getInstance();
			tmpDate = df.parse(dateTime.getYear() + "-" + Integer.valueOf(dateTime.getMonth()+1) + "-" + dateTime.getDay()
					+ " " + tf.format(curDate.getTime()));
		}catch(Exception e){
			throw new UserSystemException(e.getMessage());
		}
		return df.format(tmpDate);
	}
	
	
	public void upDate(){
		if("-  -".equals(txtDate.getText().trim())){
			txtDate.setText(DateUtils.getNow(DateUtils.FORMAT_LONG));
		}else{
			String date = DateUtils.upDownDay(txtDate.getText(),1);
			txtDate.setText(date);
		}
	}
	
	public void downDate(){
		if("-  -".equals(txtDate.getText().trim())){
			txtDate.setText(DateUtils.getNow(DateUtils.FORMAT_LONG));
		}else{
			String date = DateUtils.upDownDay(txtDate.getText(),-1);
			txtDate.setText(date);
		}
	}
	
	public void setEnabled(boolean enabled){
		if(enabled==true){
			txtDate.setEnabled(true);
			lbDate.setEnabled(true);
		}else{
			txtDate.setEnabled(false);
			lbDate.setEnabled(false);
		}
	}

	
}
