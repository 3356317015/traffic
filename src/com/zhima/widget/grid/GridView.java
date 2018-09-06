
package com.zhima.widget.grid;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.exception.UserSystemException;
import com.zhima.basic.jdbc.OperObj;
import com.zhima.frame.action.sam.ISamUserColumn;
import com.zhima.frame.action.sam.impl.ImpSamUserColumn;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.ComparatorUser;
import com.zhima.util.ExpressionUtil;
import com.zhima.util.ImageUtil;
import com.zhima.util.ImageZoom;
import com.zhima.util.ObjectUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.StringUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CCalendar;
import com.zhima.widget.CDropBox;
import com.zhima.widget.GridCustomEditUi;
import com.zhima.widget.GridSecretEditUi;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.ThreadWaiting;

/**
 * GridView概要说明：自定义表格
 * @author lcy
 */
public class GridView extends Composite{
	//表格配置信息
	private GridConfig gridConfig;
	//SWT表格对象
	public Table table;
	//表格分页信息
	private GridPage page;
	//表格列头信息
	private List<GridColumn> columns;
	
	//编辑表格用
	//private Composite composite;
	private Control control;
	private TableEditor editor;
	private int row=0;
	private int col=0;
	private int rowCount = 0;
	private int colCount = 0;
	
	/**
	 * 构造函数:
	 * @param parent 父面板
	 * @param style 样式
	 */
	public GridView(Composite parent, int style){
		super(parent,style);
	}
	
	/**
	 * CreateTabel方法描述：创建表格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 下午11:25:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param gridConfig void
	 */
	public void CreateTabel(GridConfig gridConfig) {
		this.gridConfig = gridConfig;
		setLayout(new FormLayout());
		this.columns = gridConfig.getColumns();
		//this.datas = gridConfig.getDatas();
		//初始化表格
		initGrid();
	}
	
	/**
	 * initGrid方法描述：初始化表格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 下午11:25:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void initGrid(){
		try {
			//设置表格
			setTable();
			//设置表格布局
			FormData data = new FormData();
			data.top = new FormAttachment(0);
			data.left = new FormAttachment(0);
			data.right = new FormAttachment(100);
			if (gridConfig.isShowPage() == true){
				data.bottom = new FormAttachment(100,-25);
			}else{
				data.bottom = new FormAttachment(100);
			}
			table.setLayoutData(data);
			//设置分页布局
			if (gridConfig.isShowPage() == true){
				page = new GridPage(this,SWT.NONE);
				data = new FormData();
				data.top = new FormAttachment(table,2);
				data.left = new FormAttachment(0);
				data.right = new FormAttachment(100);
				data.bottom = new FormAttachment(100);
				page.setLayoutData(data);
			}
			
			Menu menu = new Menu(table);
			//表格设置右键菜单
			if (null != gridConfig.getGridName() && !"".equals(gridConfig.getGridName())){
				MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
				menuItem.setText("表格管理");
				//menuItem.setImage(ImageZoom.getImage(secondModules.get(i).getModuleIcon(),18,18));
				Menu gridDrop = new Menu(menu.getShell(), SWT.DROP_DOWN);  
				menuItem.setMenu(gridDrop);
				
				
				MenuItem customItem = new MenuItem(gridDrop, SWT.POP_UP);
				//customItem.setImage(ImageZoom.getImage(module.getModuleIcon(),18,18));
				customItem.setText("自定义设置");
				customItem.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						//打开表格设置窗口
						GridCustomEditUi editUi = new GridCustomEditUi(getShell(), gridConfig);
						editUi.open();
					}
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
				MenuItem restoreItem = new MenuItem(gridDrop, SWT.POP_UP);
				//customItem.setImage(ImageZoom.getImage(module.getModuleIcon(),18,18));
				restoreItem.setText("还原表格");
				restoreItem.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						ISamUserColumn iSamUserColumn = new ImpSamUserColumn();
						try {
							iSamUserColumn.deleteByUserGrid(
									gridConfig.getObj().getClass().getName(),
									gridConfig.getGridName(),
									CommFinal.user.getUserSeq(),
									CommFinal.initConfig());
						} catch (UserBusinessException e) {
							LogUtil.operLog(e, "E", true, false);
						}
					}
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
				/**
				 * 通过用户权限判断是否允许设置保密项
				 */
				new MenuItem(gridDrop, SWT.SEPARATOR);
				MenuItem secretItem = new MenuItem(gridDrop, SWT.POP_UP);
				//customItem.setImage(ImageZoom.getImage(module.getModuleIcon(),18,18));
				secretItem.setText("保密项设置");
				secretItem.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						GridSecretEditUi editUi = new GridSecretEditUi(getShell(), gridConfig);
						editUi.open();
					}
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
			}

			//功能操作右键菜单
			if (null != gridConfig.getRightBos() && gridConfig.getRightBos().size()>0){
				if (null != gridConfig.getGridName() && !"".equals(gridConfig.getGridName())){
					new MenuItem(menu, SWT.SEPARATOR);
				}
				final CallMethod cMenu = new CallMethod();
				for (int i = 0; i < gridConfig.getRightBos().size(); i++) {
					final SamModuleRight moduleRight = gridConfig.getRightBos().get(i);
					if ("separator".equals(moduleRight.getRightName())){
						new MenuItem(menu, SWT.SEPARATOR);
						continue;
					}
					MenuItem menuItem = new MenuItem(menu,SWT.CASCADE);
					menuItem.setText(moduleRight.getRightName());
					menuItem.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(moduleRight.getRightIcon()),18,18));
					menuItem.addSelectionListener(new SelectionListener() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							cMenu.bindSelectMenu(gridConfig.getObj(), moduleRight);
						}
						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {}
					});
				}
			}
			table.setMenu(menu);
		} catch (Exception e) {
			//throw new UserSystemException(e.getMessage());
			LogUtil.operLog(e, "E", true, false);
		}
	}

	/**
	 * setTable方法描述：设置表格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-13 下午08:29:38
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @throws Exception void
	 */
	private void setTable() throws Exception{
		//是否大数据量表格
		if(gridConfig.isVirtual()==true){
			//大数据表格
			if(gridConfig.isCheck()==true){
				table = new Table(this,SWT.NONE | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL | SWT.CHECK);
			}else{
				table = new Table(this,SWT.NONE | SWT.MULTI | SWT.FULL_SELECTION | SWT.VIRTUAL);
			}
		}else{
			//普通表格
			if(gridConfig.isCheck()==true){
				table = new Table(this,SWT.NONE | SWT.MULTI | SWT.FULL_SELECTION | SWT.CHECK);
			}else{
				table = new Table(this,SWT.NONE | SWT.MULTI | SWT.FULL_SELECTION);
			}
		}
		editor = new TableEditor(table);
		//是否显示表头
		if (gridConfig.isShowHeader()==true){
			table.setHeaderVisible(true);
		}else{
			table.setHeaderVisible(false);
		}
		//是否显示格线
		if(gridConfig.isShowLines()==true){
			table.setLinesVisible(true);
		}else{
			table.setLinesVisible(false);
		}
		table.setFont(StyleFinal.GRID_FONT);
		
		table.setBackground(StyleFinal.GRID_BACKGROUND);
		table.setForeground(StyleFinal.GRID_FOREGROUND);
		tableAddListener();
		table.addListener(SWT.MeasureItem, new Listener() {
            public void handleEvent(Event event) {
            	//设置宽度
                event.width = table.getGridLineWidth();
                //设置高度为字体高度的1.5倍
                event.height = (int) Math.floor(event.gc.getFontMetrics()
                        .getHeight() * 1.5); 
            }
        });
		table.addMouseListener(tableMouseListener());
		if(this.gridConfig.isCheck() == true){
			//按上下键时选中整行
			table.addKeyListener(tableKeyListener());
		}
		//表格得到焦点
		table.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void focusGained(FocusEvent arg0) {
				if (null != control && control.isDisposed()==false){
					List<String> list = (List<String>) control.getData();
					setItemText(list.get(2));
					control.dispose();
				}
			}
		});
		//用col来表示表格列是否从0或者1开始
		int index = 0;
		//表格有序号
		if(gridConfig.isShowSeq() == true){
			final TableColumn tableColumn = new TableColumn(table,SWT.LEFT);
			tableColumn.setWidth(50);
			tableColumn.setText("序号");
			tableColumn.setAlignment(SWT.CENTER);
			if(gridConfig.isCheck() == true){
				tableColumn.setWidth(80);
				tableColumn.setImage(SWTResourceManager.getImage("images/grid/NotSelect.jpg"));
				tableColumn.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						//设置复选框
						setTableChecked(tableColumn);
					}
				});
			}
		}

		//表格有复选框
		if(gridConfig.isCheck() == true){
			if(gridConfig.isShowSeq() == false){
				final GridColumn column = columns.get(0);
				final TableColumn tableColumn = new TableColumn(table,SWT.LEFT|SWT.FLAT);
				tableColumn.setWidth(column.getWidth());
				tableColumn.setText(column.getCName());
				tableColumn.setImage(SWTResourceManager.getImage("images/grid/NotSelect.jpg"));
				tableColumn.addSelectionListener(new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e) {
						//设置复选框
						setTableChecked(tableColumn);
					}
				});
				index = 1;
			}
		}

		//向表格添加列
		if (null != columns && columns.size()>0){
			for(;index<columns.size();index++){
				final GridColumn column = columns.get(index);
				final TableColumn tableColumn = new TableColumn(table,SWT.LEFT|SWT.FLAT);
				tableColumn.setWidth(column.getWidth());
				tableColumn.setText(column.getCName());
				tableColumn.setMoveable(true);
				if ("right".equals(column.getAlignment())){
					tableColumn.setAlignment(SWT.RIGHT);
				} else if ("center".equals(column.getAlignment())){
					tableColumn.setAlignment(SWT.CENTER);
				} else{
					tableColumn.setAlignment(SWT.LEFT);
				}
				if (!"".equals(column.getEditType())){
					EditorOption option = column.getEditorOption();
					if (null != option){
						if (option.isRequired() == true){
							tableColumn.setImage(ImageZoom.getImage(SWTResourceManager.getImage("images/grid/required.ico"),16,16));
						}else{
							tableColumn.setImage(ImageZoom.getImage(SWTResourceManager.getImage("images/grid/optional.ico"),16,16));
						}
					}
				}
				if (gridConfig.isSort() == true){
					tableColumn.setToolTipText("点击进行排序");
					tableColumn.addSelectionListener(new SelectionAdapter(){
						@SuppressWarnings("unchecked")
						public void widgetSelected(SelectionEvent arg0) {
							try {
								int direction = table.getSortDirection();
								if(direction == SWT.UP){
									table.setSortColumn(tableColumn);
									table.setSortDirection(SWT.DOWN);
									if(column.getFormula()==null || column.getFormula().length()<=0){
										//倒序排序
										ComparatorUser comparator = new ComparatorUser(column.getEName(),false);
										Collections.sort(gridConfig.getDatas(), comparator);
										setDataList(gridConfig.getDatas());
									}
								}else{
									table.setSortColumn(tableColumn);
									table.setSortDirection(SWT.UP);
									if(column.getFormula()==null || column.getFormula().length()<=0){
										//正序排序
										ComparatorUser comparator = new ComparatorUser(column.getEName(),true);
										Collections.sort(gridConfig.getDatas(), comparator);
										setDataList(gridConfig.getDatas());
									}
								}
							} catch (Exception e) {
								//throw new UserSystemException(e.getMessage());
								LogUtil.operLog(e, "E", true, false);
							}
						}
					});
				}
			}
		}
		//设置表格数据
		setDataList(gridConfig.getDatas());
	}
	
	/**
	 * tableKeyListener方法描述：方向键上下时选中表格行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午07:28:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return KeyListener
	 */
	private KeyListener tableKeyListener(){
		KeyListener keyListener = new KeyListener() {
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				int index = table.getSelectionIndex();
				if(gridConfig.isCheck() == true){
					if(e.keyCode == SWT.ARROW_UP){
						if(index != -1){
							if(index != 0){
								table.getItem(index).setChecked(false);
								table.getItem(index-1).setChecked(true);
							}
						}
					} else if(e.keyCode == SWT.ARROW_DOWN){
						if(index != -1){
							if((index+1) != (table.getItemCount())){
								table.getItem(index).setChecked(false);
								table.getItem(index+1).setChecked(true);
							}
						}
					}
				}
			}
		};
		return keyListener;
	}
	
	/**
	 * tableAddListener方法描述：设置表格整行选中效果
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2016-3-29 下午10:58:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return void
	 */
	private void tableAddListener(){
		table.addListener(SWT.EraseItem, new Listener() {
			public void handleEvent(Event event) {
				event.detail &= ~SWT.HOT;
				if ((event.detail & SWT.SELECTED) == 0) return;
				int clientWidth = table.getClientArea().width;
				GC gc = event.gc;
				Color oldForeground = gc.getForeground();
				Color oldBackground = gc.getBackground();
				Color background = SWTResourceManager.getColor(SWT.COLOR_GRAY);
				Color foreground =SWTResourceManager.getColor(SWT.COLOR_WHITE);
				gc.setBackground(background);
				gc.setForeground(foreground);
				//gc.fillGradientRectangle(0, event.y, clientWidth, event.height, false);
				gc.fillRectangle(0, event.y, clientWidth, event.height);
				gc.setForeground(oldForeground);
				gc.setBackground(oldBackground);
				event.detail &= ~SWT.SELECTED;
			}
		});
	}
	
	private MouseListener tableMouseListener(){
		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {}
			@Override
			public void mouseDown(MouseEvent arg0) {
				// 获得单元格的位置
				TableItem[] items = table.getItems();
				Point pt = new Point(arg0.x, arg0.y);
				for (int i = 0; i < items.length; i++) {
					for (int j = 0; j < table.getColumnCount(); j++) {
						Rectangle rect = items[i].getBounds(j);
					    if (rect.contains(pt)) {
					    	row=i;
					    	col=j;
					    	int c = j;
					    	if(gridConfig.isShowSeq() == true){
						    	c -= 1;
					    	}
					    	if (c>=0){
						    	if (!"".equals(columns.get(c).getEditType())){
									createEditor(row,row,col,columns.get(c).getEditType());
						    	}
					    	}
					    	break;
					    }
					 }
				}
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {}
		};
		return mouseListener;
	}
	
	/**
	 * setTableChecked方法描述：设置表格复选
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-13 下午10:46:03
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param tableColumn 表格列关
	 */
	private void setTableChecked(TableColumn tableColumn){
		/*if(selectionAdapter !=null){
		selectionAdapter.widgetSelected(null);
		}*/
		if (gridConfig.isAll() == true){
			TableItem[] items = table.getItems();
			if(null != getCheckSelections() && getCheckSelections().size()>0){
				tableColumn.setImage(SWTResourceManager.getImage("images/grid/NotSelect.jpg"));
				for(int i=0;i<items.length;i++){
					items[i].setChecked(false);
				}
			}else {
				tableColumn.setImage(SWTResourceManager.getImage("images/grid/TheSelect.jpg"));	
				for(int i=0;i<items.length;i++){
					items[i].setChecked(true);
				}
			}
		}
	}
	
	/**
	 * setItem方法描述：设置表格项显示数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午03:58:14
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param tabItem 表格行对象
	 * @param obj 数据对象
	 * @param row 行
	 */
	private void setItem(TableItem tabItem,Object obj,int row){
		try {
			if(null == tabItem){
				tabItem = new TableItem(table,SWT.NONE);
			}
			tabItem.setText(convertArray(obj,row));
			//隔行换色
			if(row%2!=0){
				tabItem.setBackground(SWTResourceManager.getColor(250,250,250));
			}
			//表格行字体颜色
			//tabItem.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			//设置表格单元格颜色
			if (gridConfig.isShowColor() == true){
				if(null != columns&&columns.size()>0){
					int n = 0;
					if(gridConfig.isShowSeq() == true){
						n = 1;
					}
					for (int i = 0; i < columns.size(); i++) {
						if (0 != columns.get(i).getColor()){
							if ("foreground".equals(gridConfig.getColorType())){
								tabItem.setForeground(i+n, SWTResourceManager.getColor(columns.get(i).getColor()));
							}else if ("background".equals(gridConfig.getColorType())){
								tabItem.setBackground(i+n, SWTResourceManager.getColor(columns.get(i).getColor()));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			//throw new UserSystemException(e.getMessage());
			LogUtil.operLog(e, "E", true, false);
		}
	}
	
    /**
     * createEditor方法描述：在表格中创建编辑对象
     * 创  建  人 ：鲁承毅
     * 创建时间：2013-6-21 上午09:08:29
     * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
     * 修改时间：(请填上修改时间)
     * @param x 行
     * @param y 列
     * @param type void
     */
	@SuppressWarnings("unchecked")
	private void createEditor(final int row, int x, final int y, final String type){
		try {
			final TableItem item = table.getItem(x);
	    	rowCount = table.getItemCount();
	    	colCount = table.getColumnCount();
			//得表编辑列表格列头
			GridColumn gridColumn = null;
			if(gridConfig.isShowSeq() == true){
				gridColumn = columns.get(y-1);
	    	}else{
	    		gridColumn = columns.get(y);
	    	}
			if (null != control && control.isDisposed()==false){
				List<String> list = (List<String>) control.getData();
				setItemText(list.get(2));
				control.dispose();
			}
			if (type.equals("ComBox")){
				control = new CCombo(table, SWT.NONE);
				CCombo combo = (CCombo) control;
				EditorOption editorOption = gridColumn.getEditorOption();
				if (editorOption.isEdit()==false){
					combo.setEditable(false);
				}
				if (editorOption.isMatch()==true){
					String arrayName[] = CommFinal.getAllName(editorOption.getArrayData());
					if (null != arrayName){
						combo.setItems(arrayName);
						String temp = CommFinal.getItemName(editorOption.getArrayData(), item.getText(y));
						if (!"".equals(temp)){
							combo.setText(temp);
						}else{
							combo.setText(item.getText(y));
						}
					}else{
						combo.setText(item.getText(y));
					}
				}else{
					if (null != editorOption.getArrayData()){
						combo.setItems(editorOption.getArrayData());
					}
					combo.setText(item.getText(y));
				}			
			} else if(type.equals("Text")){
				control = new Text(table, SWT.NONE);
				Text text = (Text) control;
				EditorOption editorOption = gridColumn.getEditorOption();
				if (editorOption.isVerify() == true){
					text.addVerifyListener(new TextVerifyListener(1));
				}
				text.setText(item.getText(y));
				text.forceFocus();
				text.selectAll();
			} else if (type.equals("DateBox")){
				control = new CCalendar(table, SWT.NONE);
				CCalendar calendar = (CCalendar) control;
				calendar.setText(item.getText(y));
			} else if (type.equals("DropBox")){
				EditorOption editorOption = gridColumn.getEditorOption();
				control = new CDropBox(table, editorOption.getDropColumns(), editorOption.getDropDatas(), 
						editorOption.getDropValueCol(), editorOption.getDropShowCol(),true, SWT.NONE);
				CDropBox cDropBox = (CDropBox) control;
				cDropBox.setValue(item.getText(y));
				cDropBox.txtShow.selectAll();
			}
			//记录表格相关属性
			List<String> ctlData = new ArrayList<String>();
			ctlData.add(String.valueOf(x));
			ctlData.add(String.valueOf(y));
			ctlData.add(type);
			ctlData.add(gridColumn.getEName());
			control.setData(ctlData);
			
			control.setFont(StyleFinal.SYS_FONT);
			control.forceFocus();
			control.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					setItemText(type);
				}
			});
			if (type.equals("ComBox")){
				CCombo combo = (CCombo) control;
				combo.addKeyListener(editorKeyListener(type));
				combo.addFocusListener(focusListener());
			} else if(type.equals("Text")){
				Text text = (Text) control;
				text.addKeyListener(editorKeyListener(type));
				text.addFocusListener(focusListener());
			} else if (type.equals("DateBox")){
				CCalendar calendar = (CCalendar) control;
				calendar.txtDate.addKeyListener(editorKeyListener(type));
				calendar.txtDate.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent arg0) {
						// TODO Auto-generated method stub
						setItemText("DateBox");
					}
				});
				calendar.txtDate.addFocusListener(focusListener());
			} else if (type.equals("DropBox")){
				CDropBox cDropBox = (CDropBox) control;
				cDropBox.txtShow.addKeyListener(editorKeyListener(type));
				cDropBox.txtShow.addFocusListener(focusListener());
			}
			editor.grabHorizontal = true;
			editor.setEditor(control, item, y);
			Object obj = getObjIndex(row);
			final TableItem tableItem = table.getItem(row);
			tableItem.setText(convertArray(obj,row));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
	
	private FocusListener focusListener(){
		FocusListener focusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Object obj = getObjIndex(row);
					final TableItem tableItem = table.getItem(row);
					tableItem.setText(convertArray(obj,row));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}
		};
		return focusListener;
	}
	
	private KeyListener editorKeyListener(final String type){
		KeyListener keyListener = new KeyListener() {
			public void keyReleased(KeyEvent e) {
			}
			@SuppressWarnings("unchecked")
			public void keyPressed(KeyEvent e) {
				int bacRow = row;
				if(e.keyCode == 13 || e.keyCode==SWT.KEYPAD_CR){
					//回车
					if (type.equals("DropBox")){
						CDropBox cDropBox = (CDropBox) control;
						cDropBox.keySetText();
					}
					
					col = rightEditCol(col,colCount);
					List<String> list = (List<String>) control.getData();
					
					if (col <= Integer.valueOf(list.get(1))){
						if (row<rowCount-1){
							row +=1;
						}else{
							row =0;
						}
					}
					table.setSelection(row);
					createEditor(bacRow,row,col,getEditType(col));
				} else if (e.keyCode == 16777217){
					//上
					if (type.equals("ComBox")||type.equals("DropBox")||type.equals("DateBox")){
						return;
					}
					if (row == 0){
						row = 0;
					}else{
						row -= 1;
					}
					table.setSelection(row);
					createEditor(bacRow,row,col,getEditType(col));
					try {
						Object obj = getObjIndex(bacRow);
						final TableItem item = table.getItem(bacRow);
						item.setText(convertArray(obj,bacRow));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (e.keyCode == 16777218){
					//下
					if (type.equals("ComBox")||type.equals("DropBox")||type.equals("DateBox")){
						return;
					}
					if (row == rowCount-1){
						row = rowCount-1;
					}else{
						row += 1;
					}
					table.setSelection(row);
					createEditor(bacRow,row,col,getEditType(col));
				} else if (e.stateMask == 131072 && e.keyCode == 16777219){
					//shift+左
					col = leftEditCol(col,colCount);
					List<String> list = (List<String>) control.getData();
					if (col >= Integer.valueOf(list.get(1))){
						if (row==0){
							row =0;
						}else{
							row -=1;
						}
					}
					table.setSelection(row);
					createEditor(bacRow,row,col,getEditType(col));
				} else if (e.stateMask == 131072 && e.keyCode == 16777220){
					//shift+右
					col = rightEditCol(col,colCount);
					List<String> list = (List<String>) control.getData();
					if (col <= Integer.valueOf(list.get(1))){
						if (row<rowCount-1){
							row +=1;
						}else{
							row =rowCount-1;
						}
					}
					table.setSelection(row);
					createEditor(bacRow,row,col,getEditType(col));
				}
			}
		};
		return keyListener;
	}

	/**
	 * getEditType方法描述：得到编辑单元格类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-21 下午02:08:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param col
	 * @return String
	 */
	private String getEditType(int col){
		String type = "";
		if (gridConfig.isShowSeq() == true){
			type = columns.get(col-1).getEditType();
		}else {
			type = columns.get(col).getEditType();
		}
		return type;
	}
	
	/**
	 * leftEditCol方法描述：得到向左下一个单位格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-21 下午02:08:27
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param col
	 * @param colCount
	 * @return int
	 */
	private int leftEditCol(int col,int colCount){
		if (gridConfig.isShowSeq() == true){
			colCount -= 1;
			col -= 1;
		}
		for (int i = col-1; i >= 0; i--) {
			if (!"".equals(columns.get(i).getEditType())){
				if (gridConfig.isShowSeq() == true){
					return i+1;
				}else{
					return i;
				}
			}
		}
		for (int i = colCount-1; i >= 0; i--) {
			if (!"".equals(columns.get(i).getEditType())){
				if (gridConfig.isShowSeq() == true){
					return i+1;
				}else{
					return i;
				}
			}
		}
		return 0;
	}
	
	/**
	 * rightEditCol方法描述：得到向右一下个单位格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-21 下午02:07:19
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param col
	 * @param colCount
	 * @return int
	 */
	private int rightEditCol(int col,int colCount){
		if (gridConfig.isShowSeq() == true){
			colCount -= 1;
			col -= 1;
		}
		for (int i = col+1; i < colCount; i++) {
			if (!"".equals(columns.get(i).getEditType())){
				if (gridConfig.isShowSeq() == true){
					return i+1;
				}else{
					return i;
				}
			}
		}
		for (int i = 0; i < colCount; i++) {
			if (!"".equals(columns.get(i).getEditType())){
				if (gridConfig.isShowSeq() == true){
					return i+1;
				}else{
					return i;
				}
			}
		}
		return 0;
	}
	
	/**
	 * setItemText方法描述：设置表格单元格文本
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-21 上午09:04:35
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param type void
	 */
    @SuppressWarnings({ "unchecked" })
	private void setItemText(String type){
    	List<String> list = (List<String>) control.getData();
		TableItem tableItem = table.getItem(Integer.valueOf(list.get(0)));
		String strText = "";
		if (type.equals("ComBox")){
			CCombo combo = (CCombo) control;
			strText = combo.getText();
			EditorOption editorOption = columns.get(Integer.valueOf(list.get(1))).getEditorOption();
			if (gridConfig.isShowSeq() == true){
				editorOption = columns.get(Integer.valueOf(list.get(1))-1).getEditorOption();
			}
			if (editorOption.isMatch() == true){
				String temp = CommFinal.getItemValue(editorOption.getArrayData(), strText);
				if (!"".equals(temp)){
					strText = temp;
					tableItem.setText(Integer.valueOf(list.get(1)), temp);
				} else{
					tableItem.setText(Integer.valueOf(list.get(1)), strText);
				}
			} else{
				tableItem.setText(Integer.valueOf(list.get(1)), strText);
			}
		} else if(type.equals("Text")){
			Text text = (Text) control;
			strText = text.getText();
			tableItem.setText(Integer.valueOf(list.get(1)), strText);
		} else if (type.equals("DateBox")){
			CCalendar calendar = (CCalendar) control;
			strText = calendar.getText();
			tableItem.setText(Integer.valueOf(list.get(1)), strText);
		} else if (type.equals("DropBox")){
			CDropBox cDropBox = (CDropBox) control;
			strText = cDropBox.getValue();
			tableItem.setText(Integer.valueOf(list.get(1)), strText);
		}
		//反射机制将value更新到object中
		Object object = gridConfig.getDatas().get(Integer.valueOf(list.get(0)));
		OperObj operObj = new OperObj();
		operObj.setObjValue(object, list.get(3), strText);
    }
    
	/**
	 * convertArray方法描述：将对象转换为字符数组
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-14 上午01:24:16
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @param seq 行序号
	 * @return String[] 表格值数据
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	private String[] convertArray(Object obj,int seq) throws Exception{
		List<String> strArray = new ArrayList<String>();
		if(gridConfig.isShowSeq() == true){
			//设置行序号
			strArray.add(String.valueOf(seq+1));
		}
		if(null != columns&&columns.size()>0){
			StringUtils stringUtils = new StringUtils();
			ExpressionUtil expressionUtil = new ExpressionUtil();
			for(int i=0;i<columns.size();i++){
				GridColumn gridColumn = columns.get(i);
				if(null != gridColumn.getMatch() && gridColumn.getMatch().length >0){
					strArray.add((String)gridColumn.getMap().get(ObjectUtils.getPropertyValueFormObject(obj, gridColumn.getEName())));
				}else{
					//根据列的英文名称取出对象对应属性的值
					String value = ObjectUtils.getPropertyValueFormObject(obj, gridColumn.getEName());
					//按用户公式计算
					if((value == null || value.equals("") || value.length() <= 0) && 
							(gridColumn.getFormula() != null && gridColumn.getFormula().length() > 0) ){
						String formula = gridColumn.getFormula();
						List<String> list = new ArrayList<String>();
						String str = formula;
						String strValue = formula;
						while((formula.length()-str.length()) < formula.length()){
							list.add(str.substring(str.indexOf("[")+1,str.indexOf("]")));
							str = str.substring(str.indexOf("]")+1,str.length());
						}
						for(int j=0;j<list.size();j++){
							String tmpValue = String.valueOf(ObjectUtils.getPropertyValueFormObject(obj,list.get(j)));
							if (stringUtils.isNumeric(list.get(j)) == true){
								strValue = strValue.replace("["+list.get(j)+"]",list.get(j));
							}else{
								strValue = strValue.replace("["+list.get(j)+"]",tmpValue);
							}
						}
						value = String.valueOf(expressionUtil.eval(strValue + "#"));
						value = ObjectUtils.BigDecima(Double.valueOf(value),2);
					}
					Class cls = null;
					//对象不为空时,根据列英文名称取出值的数据类型
					if(null != obj){
						cls = ObjectUtils.getPropertyByType(obj, gridColumn.getEName());
					}
					//判断值的数据类型不为空，且为Double或者Float型时，用ObjectUtils转换为BigDecial.toString保留2位小数
					if(null != cls && (cls.getName().equals("java.lang.Float")
							|| cls.getName().equals("float")
							|| cls.getName().equals("double") 
							|| cls.getName().equals("java.lang.Double") )){
						value = ObjectUtils.BigDecima(Double.valueOf(value),2);
					}else{
						if ("Integer".equals(gridColumn.getEName())){
							value = ObjectUtils.BigDecima(Double.valueOf(value),0);
						}
					}
					strArray.add(value);
				}
			}
		}else{
			if (null != this.gridConfig.getDatas() && this.gridConfig.getDatas().size()>0){
				if (gridConfig.isShowSeq()== true && table.getColumnCount()<=1||
						table.getColumnCount()<=0){
					Object objCol = this.gridConfig.getDatas().get(0);
					if(objCol != null){
						Class clas = Class.forName(objCol.getClass().getName());
						//获取所有成员变量
						Field[] fields = clas.getDeclaredFields();
						for(Field f : fields){
							f.setAccessible(true);
							final TableColumn tabColumn = new TableColumn(table,SWT.LEFT);
							tabColumn.setWidth(100);
							tabColumn.setText(f.getName());
							tabColumn.setAlignment(SWT.CENTER);
						}
					}
				}
				Iterator iterator = ObjectUtils.getObjectElements(obj);
				while (iterator.hasNext()) {
					 Map.Entry entry = (Map.Entry) iterator.next();
					 strArray.add((String) entry.getValue());
				}
			}
		}
		return strArray.toArray(new String[strArray.size()]);
	}
	
	/**
	 * sumBar 方法描述：表格合计列
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 上午11:45:22
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param datas 数据对象
	 * @throws Exception void
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	private void sumBar(List<Object> datas) throws Exception{
		if(null != this.columns && null != datas && datas.size() >0){
			StringUtils stringUtils = new StringUtils();
			ExpressionUtil expressionUtil = new ExpressionUtil();
			List<String> sumList = new ArrayList<String>();
			for(int i=0;i<columns.size();i++){
				GridColumn gridColumn = columns.get(i);
				if(gridColumn.getCalcType().length()>0){
					String sumValue = "0";
					//如果不是数量统计话，就需要计算合计
					if(!"COUNT".equals(gridColumn.getCalcType().toUpperCase())){
						for(int j=0;j<datas.size();j++){
							Object obj = datas.get(j);
							String value = ObjectUtils.getPropertyValueFormObject(obj, gridColumn.getEName());
							if (value.equals("NaN")){
								value = "0";
							}
							//按用户公式计算
							if((value == null || value.equals("") || value.length() <= 0) && 
									(gridColumn.getFormula() != null && gridColumn.getFormula().length() > 0) ){
								String formula = gridColumn.getFormula();
								List<String> list = new ArrayList<String>();
								String str = formula;
								String strValue = formula;
								while((formula.length()-str.length()) < formula.length()){
									list.add(str.substring(str.indexOf("[")+1,str.indexOf("]")));
									str = str.substring(str.indexOf("]")+1,str.length());
								}
								for(int k=0;k<list.size();k++){
									String tmpValue = String.valueOf(ObjectUtils.getPropertyValueFormObject(obj,list.get(k)));
									if (stringUtils.isNumeric(list.get(k)) == true){
										strValue = strValue.replace("["+list.get(k)+"]",list.get(k));
									}else{
										strValue = strValue.replace("["+list.get(k)+"]",tmpValue);
									}
								}
								value = String.valueOf(expressionUtil.eval(strValue + "#"));
								value = ObjectUtils.BigDecima(Double.valueOf(value),2);
							}

							
							//将取出的值进行合计，在合计需要判断是否含“%”号
							if(value.indexOf("%")>=0){
								value = String.valueOf(value).replace("%","");
								sumValue = sumValue.replace("%", "");
								if(stringUtils.isNumeric(value)==false){
									value = "0";
								}
								sumValue =ObjectUtils.BigDecima(Double.valueOf(value)+Double.valueOf(sumValue),2)+"%";
							}else{
								if(stringUtils.isNumeric(value)==false){
									value = "0";
								}
								sumValue =ObjectUtils.BigDecima(Double.valueOf(value)+Double.valueOf(sumValue),2);
							}
						}
					}
					//根据对象属性的类型进行判断，如果不为double或者float则转为Int
					Class cls = ObjectUtils.getPropertyByType(datas.get(0), gridColumn.getEName());
					if(cls != null && !cls.getName().equals("java.lang.Double") && !cls.getName().equals("java.lang.Float")
							&& !cls.getName().equals("double") && !cls.getName().equals("float") && !cls.getName().equals("java.lang.String")){
						sumValue = sumValue.substring(0,sumValue.indexOf("."));
					}
					if(gridColumn.getCalcType().toUpperCase().equals("SUM")){
						//列求和
						sumList.add(sumValue);
					}else if(gridColumn.getCalcType().toUpperCase().equals("COUNT")){
						//求记录数
						sumList.add(datas.size()+"");
					}else if(gridColumn.getCalcType().toUpperCase().equals("AVG")){
						//求平均值
						if(sumValue.indexOf("%")>=0){
							sumValue = sumValue.replace("%", "");
							Double avg = Double.valueOf(sumValue)/datas.size();
							sumList.add(ObjectUtils.BigDecima(avg,2)+"%");
						}else{
							if(datas.size()>0){
								Double avg = Double.valueOf(sumValue)/datas.size();
								sumList.add(ObjectUtils.BigDecima(avg,2));
							}else{
								sumList.add(sumValue);
							}
						}
					}
				}else{
					sumList.add("");
				}
			}
			TableItem tableItem = new TableItem(table,SWT.NONE);
			/*Font font = SWTResourceManager.getFont("FONTSTYLE", 11, SWT.BOLD);
			tableItem.setFont(font);
			if(dataList !=null && dataList.size()>0){
				tableItem.setImage(IMAGE);
			}*/
			if(this.gridConfig.isCheck() == true || this.gridConfig.isShowSeq() == true){
				sumList.add(0, "合计");
			}else{
				sumList.set(0, "合计");
			}
			tableItem.setText(sumList.toArray(new String[sumList.size()]));
			table.redraw();
		}
	}
	
	/**
	 * getColumnCount方法描述：得到表格总列数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:39:39
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return Integer 总列数
	 */
	public int getColumnCount(){
		return table.getColumnCount();
	}
	
	/**
	 * getDataList方法描述：得到表格数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:17:23
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<?> 表格数据
	 */
	public List<?> getDataList(){
		return this.gridConfig.getDatas();
	}
	
	/**
	 * setDataList方法描述：设置表格数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 上午11:41:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param list 数据
	 * @throws Exception void
	 */
	@SuppressWarnings("unchecked")
	public void setDataList(final List<?> list) throws Exception{
		gridConfig.setDatas(list);
		Control[] controls = table.getChildren();
		if(null != controls && controls.length>0){
			for(int i=(controls.length-1);i>=0;i--){
				controls[i].dispose();
			}
		}
		table.removeAll();
		if(null != list && list.size()>0){
			if(gridConfig.isVirtual() == false){
				for(int i=0; i<list.size(); i++){
					this.setItem(null,list.get(i),i);
				}
			} else {
				final int PAGE_SIZE = 100;
				table.addListener(SWT.SetData, new Listener(){
					//向表格中增加监听事件
					public void handleEvent(Event event) {
						try{
							TableItem item = (TableItem) event.item;
			  		        int index = event.index; 
			  		        int page = index / PAGE_SIZE; 
			  		        int start = page * PAGE_SIZE; 
			  		        int end = start + PAGE_SIZE; 
			  		        end = Math.min (end,table.getItemCount()); 
		  		        	for (int i = start; i < end; i++) { 
		  		        		if(i < list.size()){
		  		        			item = table.getItem (i); 
		  		        			setItem(item,list.get(i),i);
		  		        		}
			  		        }
		  		        }catch(Exception e){
		  		        	throw new UserSystemException(e.getMessage());
		  		        }
					}
				});
			}
			table.setItemCount(gridConfig.getDatas().size());
		}
		table.redraw();
		//显示合计
		if(this.gridConfig.isShowSum() == true){
			this.sumBar(this.gridConfig.getDatas());
		}
	}
	
	/**
	 * upTableRow方法描述：选中行向前移动
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午03:41:43
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	@SuppressWarnings("unchecked")
	public void upTableRow(){
		int index = table.getSelectionIndex();
		if (index != -1){
			int newIndex = index-1;
			if(index != 0){
				Object obj = this.gridConfig.getDatas().get(index);
				Object bakObj = this.gridConfig.getDatas().get(newIndex);
				boolean sel = table.getItem(index).getChecked();
				boolean backSel = table.getItem(newIndex).getChecked();
				this.gridConfig.getDatas().set(index,bakObj);
				this.gridConfig.getDatas().set(newIndex,obj);
				this.updateRow(index, bakObj);
				table.getItem(index).setChecked(backSel);
				this.updateRow(newIndex, obj);
				table.getItem(newIndex).setChecked(sel);
				table.deselectAll();
				this.setSelection(newIndex);
			}
		}
	}
	
	/**
	 * downTableRow方法描述：选中行向后移动
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午03:42:00
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	@SuppressWarnings("unchecked")
	public void downTableRow(){
		int index = table.getSelectionIndex();
		if (index != -1){
			int newIndex = index+1;
			if((newIndex) != this.gridConfig.getDatas().size()){
				Object obj = this.gridConfig.getDatas().get(index);
				Object bakObj = this.gridConfig.getDatas().get(newIndex);
				boolean sel = table.getItem(index).getChecked();
				boolean backSel = table.getItem(newIndex).getChecked();
				this.gridConfig.getDatas().set(index,bakObj);
				this.gridConfig.getDatas().set(newIndex,obj);
				this.updateRow(index, bakObj);
				table.getItem(index).setChecked(backSel);
				this.updateRow(newIndex, obj);
				table.getItem(newIndex).setChecked(sel);
				table.deselectAll();
				this.setSelection(newIndex);
			}
		}
	}

	/**
	 * setSelection方法描述：设置表格选中行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午11:37:33
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param index 行号
	 */
	public void setSelection(int index){
		//table.setSelection(index);
		table.select(index);
	}
	
	/**
	 * addRow方法描述：添加行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午03:42:39
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 数据对象
	 * @throws Exception void
	 */
	@SuppressWarnings({ "unchecked", })
	public void addRow(Object obj) throws Exception{
		int seq = 0;
		if (this.gridConfig.isShowSeq() == true){
			seq = this.table.getItemCount();
			if (this.gridConfig.isShowSum() == true){
				seq = this.table.getItemCount()-1;
			}
		}
		TableItem tabItem = null;
		if(this.gridConfig.isShowSum() == true && table.getItemCount() > 1){
			tabItem = table.getItem(table.getItemCount()-1);
		}else{
			tabItem = new TableItem(table,SWT.NONE);
			//tabItem.setImage(IMAGE);
		}
		tabItem.setText(convertArray(obj,seq));
		this.gridConfig.getDatas().add(obj);
		//显示合计
		if(this.gridConfig.isShowSum() == true){
			this.sumBar(this.gridConfig.getDatas());
		}
	}
	
	/**
	 * addRows方法描述：添加多行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:50:41
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param list 数据对象
	 * @throws Exception void
	 */
	@SuppressWarnings({ "unchecked" })
	public void addRows(List<?> list) throws Exception{
		if(this.gridConfig.isShowSum() == true && table.getItemCount() > 1){
			table.remove(table.getItemCount()-1);
		}
		int seq = 0;
		for(int i=0;i<list.size();i++){
			Object obj = list.get(i);
			if(obj != null){
				if (this.gridConfig.isShowSeq() == true){
					seq = this.table.getItemCount()+i;
					if (this.gridConfig.isShowSum() == true){
						seq = this.table.getItemCount()+i-1;
					}
				}
				TableItem tabItem = new TableItem(table,SWT.NONE);
				tabItem.setText(convertArray(obj,seq));
				this.gridConfig.getDatas().add(obj);
			}
		}
		if(this.gridConfig.isShowSum() == true){
			this.sumBar(this.gridConfig.getDatas());
		}
	}
	
	/**
	 * updateRow方法描述：修改指定行数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 上午11:50:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param index 行下标
	 * @param obj 数据对象
	 */
	@SuppressWarnings("unchecked")
	public void updateRow(int index,Object obj){
		try {
			TableItem tabItem = table.getItem(index);
			this.gridConfig.getDatas().set(index, obj);
			if(this.gridConfig.isShowSeq() == true){
				tabItem.setText(convertArray(obj,index));
			}else{
				tabItem.setText(convertArray(obj,0));
			}
			table.redraw();
			if(this.gridConfig.isShowSum() == true){
				if(table.getItemCount() > 1){
					table.remove(table.getItemCount()-1);
				}
				this.sumBar(this.gridConfig.getDatas());
			}
		} catch (Exception e) {
			//throw new UserSystemException(e.getMessage());
			LogUtil.operLog(e,"E",true,false);
		}
	}
	
	/**
	 * updateRows方法描述：更新多行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:49:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param indexArray 行下标
	 * @param list 数据对象
	 * @throws Exception void
	 */
	public void updateRows(int [] indexArray,List<?> list) throws Exception{
		for(int i=0;i<indexArray.length;i++){
			int index = indexArray[i];
			Object obj = list.get(i);
			updateRow(index,obj);
		}
		table.redraw();
	}
	
	/**
	 * deleteRow方法描述：删除表格指定行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:49:00
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param index 行号
	 */
	public void deleteRow(int index){
		if(index < this.gridConfig.getDatas().size()){
			table.remove(index);
			this.gridConfig.getDatas().remove(index);
		}
	}
	
	/**
	 * deleteRow方法描述：删除表格多行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:49:31
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param indexArray 表格行号数据
	 */
	public void deleteRow(int [] indexArray){
		Arrays.sort(indexArray);
		for(int i=indexArray.length -1;i>=0;i--){
			int index = indexArray[i];
			if(index < this.gridConfig.getDatas().size()){
				table.remove(index);
				this.gridConfig.getDatas().remove(index);
			}
		}
	}
	
	/**
	 * getObjectIndex方法描述：得到指定行对象
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 上午09:47:10
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param index 行
	 * @return Object 对象
	 */
	public Object getObjIndex(int index){
		if (index > -1 && index < this.gridConfig.getDatas().size()){
			Object obj = this.gridConfig.getDatas().get(index);
			return obj;
		}
		return null;
	}
	
	/**
	 * getSelectionIndex方法描述：得到选中行下标
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午04:14:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 下标
	 */
	public int getSelectionIndex(){
		int index = table.getSelectionIndex();
		if(null != this.gridConfig.getDatas() && index >= this.gridConfig.getDatas().size()){
			index = this.gridConfig.getDatas().size() - 1;
		}
		return index;
	}
	
	/**
	 * getSelection方法描述：得到选中行数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午04:15:08
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return Object 选中行数据
	 */
	public Object getSelection(){
		List<Object> list = new ArrayList<Object>();
		int[] indexArray = table.getSelectionIndices();
		for(int i=0;i<indexArray.length;i++){
			if(i < this.gridConfig.getDatas().size() && indexArray[i] < this.gridConfig.getDatas().size() ){
				Object obj = this.gridConfig.getDatas().get(indexArray[i]);
				list.add(obj);
			}
		}
		if(null !=list && list.size() > 0){
			return list.get(0);	
		}else{
			return null;
		}
	}
	
	public Object getSelection(int index) {
		if (index > -1 && index < this.gridConfig.getDatas().size()){
			Object obj = this.gridConfig.getDatas().get(index);
			return obj;
		}
		return null;
	}
	
	
	/**
	 * getSelectionIndexs方法描述：得到选中行下标
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:48:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int[] 选中行下标
	 */
	public int[] getSelectionIndexs(){
		List<Integer> indexs = new ArrayList<Integer>();
		int[] indexArray = table.getSelectionIndices();
		for(int i=0;i<indexArray.length;i++){
			if(i < this.gridConfig.getDatas().size() && indexArray[i] < this.gridConfig.getDatas().size() ){
				indexs.add(indexArray[i]);
			}
		}
		int[] retArr = new int[indexs.size()];
		for(int i=0;i<indexs.size();i++){
			retArr[i] = indexs.get(i);
		}
		return retArr;
	}
	
	/**
	 * getSelections方法描述：得到选中行数据对象
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:37:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<?> 选中行对象
	 */
	public List<?> getSelections(){
		List<Object> list = new ArrayList<Object>();
		int[] indexArray = table.getSelectionIndices();
		for(int i=0;i<indexArray.length;i++){
			if(i < this.gridConfig.getDatas().size() && indexArray[i] < this.gridConfig.getDatas().size() ){
				Object obj = this.gridConfig.getDatas().get(indexArray[i]);
				list.add(obj);
			}
		}
		return list;
	}
	
	/**
	 * clearAndAddRow方法描述：清理表格后添加一行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午06:06:39
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 添加项
	 * @throws Exception void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void clearAndAddRow(Object obj) throws Exception{
		this.removeAll();
		this.addRow(obj);
		List list = new ArrayList();
		list.add(obj);
		this.gridConfig.setDatas(list);
	}
	
	/**
	 * clearAndAddRows方法描述：清理表格后添加多行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午06:07:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param list 添加项
	 * @throws Exception void
	 */
	public void clearAndAddRows(List<?> list) throws Exception{
		this.removeAll();
		this.addRows(list);
		this.gridConfig.setDatas(list);
	}
	
	/**
	 * removeAll方法描述：清空表格数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午08:19:39
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @throws Exception void
	 */
	public void removeAll() throws Exception{
		table.removeAll();
		if(null != this.gridConfig.getDatas()){
			this.gridConfig.getDatas().clear();
		}
	}
	
	/**
	 * getItem方法描述：得到指定表格行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:18:55
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param index 行号
	 * @return TableItem 行对象
	 */
	public TableItem getItem(Integer index){
		if(index >= table.getItemCount()){
			return table.getItem(table.getItemCount() -1);
		}
		return table.getItem(index);
	}
	
	/**
	 * getItems方法描述：得到所有表格行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:19:16
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return TableItem[] 表格对象
	 */
	public TableItem[] getItems(){
		TableItem[] tableItems = table.getItems();
		if(this.gridConfig.isShowSum() == true){
			TableItem[] tmps = new TableItem[tableItems.length -1];
			for(int i=0;i<tmps.length;i++){
				tmps[i] = tableItems[i];
			}
			tableItems = tmps;
		}
		return tableItems;
	}
	
	/**
	 * getCheckSelections方法描述：获取表格选中行数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-13 下午10:13:13
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<?> 选中行数据数据对象
	 */
	public List<?> getCheckSelections(){		
		List<Object> list = new ArrayList<Object>();
		TableItem[] indexArray = table.getItems();
		int count = table.getItemCount();
		if (this.gridConfig.isShowSum()==true){
			count = count-1;
		}
		for(int i=0;i<count;i++){
			if(indexArray[i].getChecked() == true){
				Object obj = this.gridConfig.getDatas().get(i);
				list.add(obj);
			}
		}
		return list;
	}
	
	/**
	 * setCheck方法描述：设置表格行选中
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:15:43
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param index 行
	 */
	public void setCheck(int index,boolean checkState){
		if (this.gridConfig.isCheck() == true){
			table.getItem(index).setChecked(checkState);
		}
	}
	
	public boolean getCheck(int index){
		return table.getItem(index).getChecked();
	}
	
	/**
	 * getStart方法描述：得到起始行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:26:49
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 起始行
	 */
	public int getStart() {
		int start =(this.page.getPageNo() -1) * this.page.getLimit();
		return start;
	}

	/**
	 * getEnd方法描述：得到结束行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:27:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 结束行
	 */
	public int getEnd() {
		int end = this.page.getPageNo() * this.page.getLimit();
		return end;
	}
	
	/**
	 * getPageNo方法描述：得到当前页号
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:31:00
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 当前页号
	 */
	public int getPageNo(){
		if (page != null){
			return this.page.getPageNo();
		}else{
			return 0;
		}
	}

	/**
	 * getLimit方法描述：得到当前显示页行数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:30:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 显示页行数
	 */
	public int getLimit(){
		if (page != null){
			return this.page.getLimit();
		}else{
			return 0;
		}
	}
	
	/**
	 * setTotalCount方法描述：设置分页栏总记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午03:36:35
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param totalCount 总记录数
	 */
	public void setTotalCount(int totalCount){
		if(page != null){
			this.page.setSubtotal(totalCount);
		}
	}
	
	/**
	 * bindRefresh方法描述：绑定刷新
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:35:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @param funName 执行方法
	 */
	public void bindRefresh(final Object obj,final String funName){
		if (page != null){
			MouseAdapter mouseAdpater = new MouseAdapter(){
				@SuppressWarnings({ "rawtypes", "unchecked" })
				public void mouseDown(MouseEvent e) {
					try {
						Class cls = obj.getClass();
						cls.getDeclaredMethod(funName).invoke(obj);
					} catch (Exception ex) {
						//throw new UserSystemException(ex.getMessage());
						LogUtil.operLog(ex,"E",true,false);
					}
				}
			};
			this.page.refreshData(mouseAdpater);
		}
	}
	
	/**
	 * setCellFontColor方法慨述:设置表格某个单元格的字体颜色
	 * @param rowIndex 行号
	 * @param columnIndex 列号，需要计算前面的序号列和复选框列
	 * @param color 颜色
	 */
	public void setCellFontColor(int rowIndex,int columnIndex,Color color){
		table.getItem(rowIndex).setForeground(columnIndex,color); 
	}
	
	/**
	 * setCellBackGroundColor方法慨述:设置表格某个单元格的背景颜色
	 * @param rowIndex 行号
	 * @param columnIndex 列号，需要计算前面的序号列和复选框列
	 * @param color 颜色
	 */
	public void setCellBackGroundColor(int rowIndex,int columnIndex,Color color){
		table.getItem(rowIndex).setBackground(columnIndex,color); 
	}
	
	/**
	 * setRowFontColor方法慨述:设置表格某行的字体颜色
	 * @param rowIndex 行号
	 * @param color 颜色
	 */
	public void setRowFontColor(int rowIndex,Color color){
		table.getItem(rowIndex).setForeground(color);
	}
	
	/**
	 * setRowBackGroundColor方法慨述:设置表格某行的背景颜色
	 * @param rowIndex 行号
	 * @param color 颜色
	 */
	public void setRowBackGroundColor(int rowIndex,Color color){
		table.getItem(rowIndex).setBackground(color);
	}
	
	/**
	 * bindWidgetSelected方法描述：绑定选择事件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午06:30:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @param funName 执行方法
	 */
	public void bindWidgetSelected(final Object obj,final String funName){
		SelectionAdapter selectionAdpater = new SelectionAdapter(){
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void widgetSelected(SelectionEvent e){
				try {
					Class cls = obj.getClass();
					cls.getDeclaredMethod(funName).invoke(obj);
				} catch (Exception ex) {
					//throw new UserSystemException(ex.getMessage());
					LogUtil.operLog(ex,"E",true,false);
				}
			}
		};
		table.addSelectionListener(selectionAdpater);
	}

	/**
	 * bindMouseDown方法描述：绑定鼠标按下事件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午06:26:03
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @param funName 执行方法
	 */
	public void bindMouseDown(final Object obj,final String funName){
		MouseAdapter mouseAdappter = new MouseAdapter(){
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void mouseDown(MouseEvent arg0) {
				try {
					Class cls = obj.getClass();
					cls.getDeclaredMethod(funName).invoke(obj);
				} catch (Exception ex) {
					//throw new UserSystemException(ex.getMessage());
					LogUtil.operLog(ex,"E",true,false);
				}
			}
		};
		table.addMouseListener(mouseAdappter);
    }
	
	public void bindMouseDoubleClick(final Object obj,final String funName){
		MouseAdapter mouseAdappter = new MouseAdapter(){
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void mouseDoubleClick(MouseEvent arg0) {
				try {
					Class cls = obj.getClass();
					cls.getDeclaredMethod(funName).invoke(obj);
				} catch (Exception ex) {
					//throw new UserSystemException(ex.getMessage());
					LogUtil.operLog(ex,"E",true,false);
				}
			}
		};
		table.addMouseListener(mouseAdappter);
    }
	
	/**
	 * bindMouseDoubleClick方法描述：绑定鼠标双击事件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午04:38:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @param funName 执行方法
	 */
	public void bindMouseDoubleClick(final Object obj, final List<SamModuleRight> rightBos, final String funName){
		MouseAdapter mouseAdappter = new MouseAdapter(){
			public void mouseDoubleClick(MouseEvent arg0) {
				try {
					if (null != rightBos && rightBos.size()>0){
						for (int i = 0; i < rightBos.size(); i++) {
							if (funName.equals(rightBos.get(i).getRightMethod())){
								CallMethod callMethod = new CallMethod();
								callMethod.bindSelectMenu(obj, rightBos.get(i));
								break;
							}
						}
					}
				} catch (Exception ex) {
					//throw new UserSystemException(ex.getMessage());
					LogUtil.operLog(ex,"E",true,false);
				}
			}
		};
		table.addMouseListener(mouseAdappter);
    }
	
	/**
	 * 将表格内容导出为Excel文件
	 * @throws UserBusinessException 
	 */
	public void exportExcel() throws UserBusinessException{
		//显示有保存的按钮的文件对话框
	    FileDialog dialog = new FileDialog(getShell(),SWT.SAVE);
	    //设置所打开文件的扩展名
	    dialog.setFilterExtensions (new String [] {"*.xls", "*.xlsx"}); 
	    //下拉框中的扩展名
	    dialog.setFilterNames(new String [] {"*.xls", "*.xlsx"});
	    //设置默认路径
	    dialog.setFilterPath ("c:\\");
        //返回用户所选的文件目录
        String path =  dialog.open ();
        ThreadWaiting threadWaiting = new ThreadWaiting(this, "output", new Class[]{String.class}, new String[]{path});
        threadWaiting.task();
	}

	public void output(String path) throws UserBusinessException{
	    String targetfile = path;
	    String worksheet = "excel";
	    //设置标题
	    TableColumn[] tableColumns = table.getColumns();
	    //工作表
	    WritableWorkbook workbook;
	    try {
	    	if(targetfile!=null && targetfile.length()>0){
	    		//new一个文件输出字节流
	            OutputStream os = new FileOutputStream(targetfile);
	            //创建一个可写的工作簿
	            workbook = Workbook.createWorkbook(os);
	            // 添加第一个工作表
	            WritableSheet sheet = workbook.createSheet(worksheet,0);
	            
	            //表头格式
	            WritableFont wfTableColumn = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
	            WritableCellFormat formatTableColumn = new WritableCellFormat(wfTableColumn);
	            formatTableColumn.setAlignment(Alignment.CENTRE);
	            formatTableColumn.setBorder(Border.ALL, BorderLineStyle.THIN);
	            formatTableColumn.setWrap(false);
	            
	            //添加标题
	            for (int i = 0; i < tableColumns.length; i++) {  
	          	 //Label(列号,行号 ,内容 )
	               jxl.write.Label tableColumn = new jxl.write.Label(i,0, tableColumns[i].getText()); 
	               tableColumn.setCellFormat(formatTableColumn);
	               sheet.addCell(tableColumn);
	            }
	            //添加行
	            jxl.write.Label tableItem;
	            jxl.write.Number tableNumber;
	            int itemCount = table.getItemCount();
	            WritableFont wfTableItem = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD);
	            WritableCellFormat formatTableItem = new WritableCellFormat(wfTableItem);
	            formatTableItem.setAlignment(Alignment.LEFT);
	            formatTableItem.setBorder(Border.ALL, BorderLineStyle.THIN);
	            formatTableItem.setWrap(false);
	      	  	for(int j=0;j<tableColumns.length;j++){
	      		  int maxLength = 0;
	      		  for(int i=0;i<itemCount;i++){
	      			  //判断内容是否为数值型
	      			 if(StringUtils.isNumeric(table.getItem(i).getText(j)) == true){
	      				 tableNumber = new jxl.write.Number(j,i+1,Double.parseDouble(table.getItem(i).getText(j)));
	      				 tableNumber.setCellFormat(formatTableItem);
	                	 sheet.addCell(tableNumber);
	      			 }else{
	      				 // Label(列号,行号 ,内容 )
	                	 tableItem = new jxl.write.Label(j,i+1,table.getItem(i).getText(j));
	                	//设置单元格格式
	                	 tableItem.setCellFormat(formatTableItem);
	                	 sheet.addCell(tableItem);
	      			 }
	            	 //得到最长的单元格长度
	            	 int length = table.getItem(i).getText(j).length();
	            	 maxLength = Math.max(maxLength, length);
	          	  }
	      		  maxLength = Math.max(maxLength, tableColumns[j].getText().length());
	      		  sheet.setColumnView(j, (int)(maxLength * 3.25));
	            }
	            workbook.write();
	            workbook.close();
	            os.close();
	    	}
	    } catch (Exception e) {
			throw new UserBusinessException("导出Excel失败，请确认该文件是否已经打开。",e);
		}
	}



}