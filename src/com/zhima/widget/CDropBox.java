package com.zhima.widget;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.exception.UserSystemException;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * userDate概要说明：带表格的下拉列表框，光标离开后自动消失
 * @author lcy
 */

public class CDropBox extends Composite {
	// 是否增加全部监听的信号量
	private boolean isAddListener = false;
	// 自定义面板
	private Composite composite;
	private boolean isEnter = true;
	//值文本框
	public Text txtValue;
	// 值列
	private String valueCol;
	// 显示文本框
	public Text txtShow;
	// 显示列
	private String showCol;
	// 下拉列表框的下箭头控件
	private Label lbIco;
	//弹出窗口
	private Shell shell;
	//弹出窗口的表格控件
	private GridView gridView;
	//表格的列对象
	private List<GridColumn> columns;
	//表格的数据对象
	private List<?> dataList;
	
	private int scX = 0;
	private int scY = 0;

	// 弹出窗口样式
	private int dropX = 0;
	private int dropY = 0;
	private int dropW = 0;
	private int dropH = 200;
	
	//选择表格执行方法
	private Object obj;
	private String methodName;

	/**
	 * 
	 * 构造函数：下拉列表框的构造函数
	 * @param parent 父容器
	 * @param columnList 表格列
	 * @param dataList 数据对象
	 * @param valueCol 值列号
	 * @param showCol 显示列号
	 * @param width   宽度
	 * @param height  高度
	 * @param style 风格
	 */
	public CDropBox(Composite parent, List<GridColumn> columns, List<?> dataList,
			String valueCol, String showCol, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		this.columns = columns;
		this.dataList = dataList;
		this.showCol = showCol;
		this.valueCol = valueCol;
	}
	
	public CDropBox(Composite parent, List<GridColumn> columns, List<?> dataList,
			String valueCol, String showCol, boolean isCreate, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		this.columns = columns;
		this.dataList = dataList;
		this.showCol = showCol;
		this.valueCol = valueCol;
		if (isCreate){
			create();
		}
	}
	
	/**
	 * create方法慨述:创建
	 * 创 建  人：鲁承毅
	 * 创建时间：2012-7-4 下午03:51:42
	 * 修 改  人：鲁承毅
	 * 修改日期：2012-7-4 下午03:51:42
	 * @return Composite 
	 */
	public Composite create() {
		composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FormLayout());
		//隐藏值控件
		txtValue = new Text(composite, SWT.NONE);
		FormData data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(0);
		data.width=0;
		data.bottom = new FormAttachment(100);
		txtValue.setLayoutData(data);
		txtValue.setVisible(false);
		//显示值控件
		txtShow = new Text(composite, SWT.NONE);
		data = new FormData();
		data.left = new FormAttachment(0);
		data.top = new FormAttachment(0);
		data.right = new FormAttachment(100,-16);
		data.bottom = new FormAttachment(100);
		txtShow.setLayoutData(data);
		//键盘事件
		txtShow.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if(null == txtShow.getText() || txtShow.getText().length() == 0){
						if (e.keyCode==16777218){
							if(shell==null || shell.isDisposed()){
								createDrop(); 
							}else{
								shellLocation();
							}
							gridView.setDataList(dataList);
							gridView.setFocus();
							return;
						}else{
							txtValue.setText("");
							txtValue.setData(null);
							if(shell != null && !shell.isDisposed()){
								shell.close();
							}
							return;
						}
					}else{
						if(null == shell || shell.isDisposed()){
							if(e.keyCode == 16777219 ){
								return;
							}
							if(e.keyCode == 16777220 ){
								return;
							}
							if(e.stateMask == 131072){
								return;
							}
							createDrop();
						}
					}
					if(e.keyCode == 13 || e.keyCode==SWT.KEYPAD_CR){
						//回车事件
						if (isEnter == true){
							keySetText();
							txtShow.selectAll();
						}
					}else if(e.keyCode==16777218){
						//向下方向键
						gridView.setFocus();
						if (gridView.getSelectionIndex()==-1 && gridView.table.getItemCount()>0){
							gridView.setSelection(0);
						}
					}else if(e.keyCode == 27){
						//ESC键
						if(null != shell && shell.isDisposed()==false){
							shell.close();
						}
					}else{
						txtValue.setText("");
						txtValue.setData(null);
						shell.setRedraw(false);
						gridView.setDataList(queryList());
						shell.setRedraw(true);
						txtShow.setFocus();
					}
				} catch (Exception e1) {
					LogUtil.operLog(e1, "E", true, true);
				}
			}
	
		});
		txtShow.getShell().addControlListener(new ControlAdapter() {
			@Override
			public void controlMoved(ControlEvent arg0) {
				//重新计算shell
				if(shell != null && !shell.isDisposed()){
					shell.close();
					//shellLocation();
				}
			}
			@Override
			public void controlResized(ControlEvent arg0) {
				//重新计算shell
				if(shell != null && !shell.isDisposed()){
					shell.close();
					//shellLocation();
				}
			}
		});
		//下拉箭头控件
		lbIco = new Label(composite, SWT.NONE | SWT.RIGHT);
		lbIco.setImage(SWTResourceManager.getImage(System.getProperty("user.dir") + "\\images\\control\\drop.png"));
		data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(txtShow);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		lbIco.setLayoutData(data);
		lbIco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					if(shell==null || shell.isDisposed()){
						createDrop(); 
					}else{
						shellLocation();
					}
					gridView.setDataList(dataList);
					//gridView.setDataList(queryList());
				} catch (Exception e) {
					LogUtil.operLog(e, "E", true, true);
				}
			}
		});
		return composite;
	}
	
	/**
	 * createPop方法描述：创建弹出窗
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-26 下午07:15:23
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createDrop(){
		if(null == shell || shell.isDisposed()){
			shell = new Shell(getShell(),SWT.NO_TRIM | SWT.CLOSE);
			//增加全局的鼠标监听事件
			shell.getDisplay().addFilter(SWT.MouseDown, allListener);
			this.isAddListener = true;
			shell.setLayout(new FormLayout());
			//初始化表格
			GridConfig gridConfig = new GridConfig();
			gridConfig.setCheck(false);
			gridConfig.setColumns(columns);
			gridConfig.setShowPage(false);
			gridConfig.setShowSeq(false);
			gridView = new GridView(shell, SWT.BORDER | SWT.SHORT);
			gridView.CreateTabel(gridConfig);
			FormData data = new FormData();
			data.left = new FormAttachment(0);
			data.top = new FormAttachment(0);
			data.height = dropH;
			if (dropW >0){
				data.width = dropW;
			}
			data.bottom = new FormAttachment(100);
			gridView.setLayoutData(data);	
			//焦点事件
			/*gridView.table.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					if ("".equals(txtShow.getText())){
						shell.setVisible(false);
					}
				}
			});*/
			//键盘事件
			gridView.table.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.keyCode==8){
						txtShow.setFocus();
					}
					if(e.keyCode == 13 || e.keyCode==SWT.KEYPAD_CR){
						//暂时将shell设为不可见,txtShow回车时关闭shell
						/*shell.setVisible(false);
						txtShow.selectAll();*/
						Object objCol = gridView.getSelection();
						setInfo(objCol);
						txtShow.forceFocus();
						txtShow.selectAll();
						shell.close();
					}
					/*if(e.keyCode==16777217 || e.keyCode==16777218){
						if (gridView.table.getItemCount()<=0 || gridView.getSelectionIndex()==0){
							txtShow.forceFocus();
						}
					}*/
				}
			});
			//鼠标点击事件
			gridView.table.addMouseListener(new MouseAdapter() {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public void mouseDoubleClick(MouseEvent arg0) {
					Object objCol = gridView.getSelection();
					setInfo(objCol);
					txtShow.forceFocus();
					txtShow.selectAll();
					if (null !=  obj && null != methodName){
						try {
							Class cls = obj.getClass();
							cls.getDeclaredMethod(methodName).invoke(obj);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					shell.close();
				}
				/*@Override
				public void mouseDown(MouseEvent arg0) {
					Object objCol = gridView.getSelection();
					setInfo(objCol);
				}*/
			});
			//设置对话框位置
			shellLocation();
		}
	}
	
	//全局鼠标监听
	private Listener allListener = new Listener() {
		@Override
		public void handleEvent(Event e) {
			if(SWT.MouseDown == e.type){
				//判断鼠标的当前shell是否为弹出框的shell，如果不是则关闭
				if(e.display.getActiveShell() != shell){
					closeDialogShell();
				}
			}
		}
	};
	private void closeDialogShell(){
		if(gridView !=null && gridView.isDisposed()==false){
			gridView.dispose();
		}
		if(shell != null && shell.isDisposed() == false){
			//移除全局监听事件
			if(isAddListener){
				shell.getDisplay().removeFilter(SWT.MouseDown, allListener);
			}
			shell.dispose();
		}
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
		getPoint(txtShow.getParent());
		
		int screnWidth = Display.getCurrent().getClientArea().width;
		int borderWidth = (txtShow.getShell().getBounds().width - txtShow.getShell().getClientArea().width)/2;
		
		int screnHeight = Display.getCurrent().getClientArea().height;
		int borderHight = txtShow.getShell().getBounds().height - txtShow.getShell().getClientArea().height;
		
		int x=dropX+txtShow.getBounds().x + borderWidth;
		int y=dropY+txtShow.getBounds().y + txtShow.getBounds().height+borderHight - borderWidth/2;
		if(x + dropW>screnWidth){
			x=x-(dropW-composite.getBounds().width);//+borderWidth/2
		}
		if(y+dropH>screnHeight){
			y=y-dropH-composite.getBounds().height-borderWidth/2;		
		}
		
		if(shell !=null && shell.isDisposed()==false){
			shell.setLocation(x+scX,y+scY);
			shell.pack();
			shell.open();
		}
	}
	private void getPoint(Composite parent){
		dropX += parent.getBounds().x;
		dropY += parent.getBounds().y;
		if(parent.getParent() != null && !(parent instanceof Shell) ){
			if ("Composite".equals(parent.getParent().getClass().getSimpleName())){
				dropY +=1;
			}
			getPoint(parent.getParent());
		}
	}
	public void keySetText(){
		if(shell != null && shell.isDisposed() == false){
			if(gridView.table.getItemCount()>0){
				if (gridView.getSelectionIndex()>0){
					
				}else{
					gridView.setSelection(0);
				}
				Object objCol = gridView.getSelection();
				setInfo(objCol);
			}
			shell.close();
		}
	}
	
	/**
	 * getUserList方法慨述:按用户输入内容过滤数据
	 * 创 建  人：鲁承毅
	 * 创建时间：2012-7-13 上午11:39:43
	 * 修 改  人：鲁承毅
	 * 修改日期：2012-7-13 上午11:39:43
	 * @return List<Object> 传入的数据对象
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	private List<Object> queryList(){
		List<Object> userList = new ArrayList<Object>();
		try {
			if (null != dataList && dataList.size()>0){
				for (int i = 0; i < dataList.size(); i++) {
					Object objCol = dataList.get(i);
					Class clas = Class.forName(objCol.getClass().getName());
					Field[] fields = clas.getDeclaredFields();
					boolean isNext = true;
					for (int j = 0; j < columns.size(); j++) {
						isNext = true;
						for(Field f : fields){
							if (f.getName().equals(columns.get(j).getEName())){
								f.setAccessible(true);
								if (null != f.get(objCol) && !"serialVersionUID".equals(f.getName())){
									if(String.valueOf(f.get(objCol).toString()).toLowerCase().indexOf(txtShow.getText().toLowerCase())!=-1){
										userList.add(dataList.get(i));
										isNext = false;
										break;
									}
								}
							}
						}
						if (isNext == false){
							break;
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			throw new UserSystemException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new UserSystemException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new UserSystemException(e.getMessage());
		}
		return userList;
	}
	
	/**
	 * setSelectItem方法慨述:将表格选择行值设置到自定义文本框
	 * 创 建  人：鲁承毅
	 * 创建时间：2012-7-12 下午04:00:15
	 * 修 改  人：鲁承毅
	 * 修改日期：2012-7-12 下午04:00:15
	 * @param objCol void 表格选择行数据对象
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	private void setInfo(Object objCol){
		try{
			if(objCol == null){
				return;
			}
			Class clas = Class.forName(objCol.getClass().getName());
			Field[] fields = clas.getDeclaredFields();
			String colName ="";
			for(Field f : fields){
				f.setAccessible(true);
				colName = f.getName();
				if (colName.equals(showCol)){
					String userText = f.get(objCol).toString();
					txtShow.setText(userText);
					txtShow.setSelection(txtShow.getTextLimit());
				}
				if (colName.equals(valueCol)){
					String userText = f.get(objCol).toString();
					txtValue.setText(userText);
					txtValue.setData(objCol);
					setObj(objCol);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new UserSystemException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new UserSystemException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new UserSystemException(e.getMessage());
		}
	}
	//设置字体颜色
	public void setForeground(Color color) {
		txtShow.setForeground(color);
	}
	//设置字体
	public void setFont(Font font) {
		txtShow.setFont(font);
	}
	//是否可编辑
	public void setEditable(boolean isEdit){
		txtShow.setEditable(isEdit);
	}
	public void setEnabled(boolean isEnabled){
		if (true == isEnabled){
			if (true == txtShow.getEditable()){
				txtShow.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			}else{
				txtShow.setBackground(SWTResourceManager.getColor(240, 240 ,240));
			}
		}else{
			txtShow.setBackground(SWTResourceManager.getColor(240, 240 ,240));
		}
		txtShow.setEnabled(isEnabled);
		lbIco.setEnabled(isEnabled);
	}
	//得到显示文本
	public String getText() {
		return txtShow.getText();
	}
	//设置显示文本
	public void setText(String text) {
		txtShow.setText(text);
	}
	//得到值
	public String getValue() {
		return txtValue.getText();
	}
	//设置值
	@SuppressWarnings("rawtypes")
	public void setValue(String text) {
		try {
			if (null == text || "".equals(text)){
				txtShow.setText("");
			}else{
				txtValue.setText(text);
				if (null != dataList && dataList.size()>0){
					boolean isFind = true;
					for (int i = 0; i < dataList.size(); i++) {
						Object objCol = dataList.get(i);
						Class clas = Class.forName(objCol.getClass().getName());
						Field[] fields = clas.getDeclaredFields();
						for(Field f : fields){
							isFind = true;
							f.setAccessible(true);
							if (f.getName().equals(valueCol)){
								if (String.valueOf(f.get(objCol).toString()).toLowerCase().equals(text.toLowerCase())){
									setInfo(dataList.get(i));
									isFind = false;
									break;
								}
							}
						}
						if (isFind == false){
							break;
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			throw new UserSystemException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new UserSystemException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new UserSystemException(e.getMessage());
		}
	}
	
	//得到对象
	public Object getObject() {
		return txtValue.getData();
	}

	public int getDropW() {
		return dropW;
	}

	public void setDropW(int dropW) {
		this.dropW = dropW;
	}

	public int getDropH() {
		return dropH;
	}

	public void setDropH(int dropH) {
		this.dropH = dropH;
	}

	public List<?> getDataList() {
		return dataList;
	}
	
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public boolean isEnter() {
		return isEnter;
	}

	public void setEnter(boolean isEnter) {
		this.isEnter = isEnter;
	}

	public int getScX() {
		return scX;
	}

	public void setScX(int scX) {
		this.scX = scX;
	}

	public int getScY() {
		return scY;
	}

	public void setScY(int scY) {
		this.scY = scY;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
