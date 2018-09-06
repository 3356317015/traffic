package com.zhima.widget.grid;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.StyleFinal;
import com.zhima.widget.CCalendar;

public class EditorDemo {
	public static  Table table;
	private static Composite composite;
	private static Control control;
	static TableEditor editor;
	private static int row=0;
	private static int col=0;
	private static int rowCount = 0;
	private static int colCount = 0;
	private static Text text_1;
	
	
    public static void main(String[] args) {
		Display display = new Display();
		final Color red = display.getSystemColor(SWT.COLOR_RED);
	    final Color yellow = display.getSystemColor(SWT.COLOR_YELLOW);
		Shell shell = new Shell(display);
		shell.setMinimumSize(new Point(500, 400));
		shell.setSize(546, 399);
		shell.setLayout(new FormLayout());
		
		text_1 = new Text(shell, SWT.BORDER|SWT.SEARCH);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(0);
		fd_text_1.left = new FormAttachment(0);
		text_1.setLayoutData(fd_text_1);
		
		table = new Table(shell, SWT.BORDER | SWT.MULTI|SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(text_1);
		fd_table.left = new FormAttachment(0);
		fd_table.right = new FormAttachment(100);
		fd_table.bottom = new FormAttachment(100);
		table.setLayoutData(fd_table);
		table.setFont(StyleFinal.SYS_FONT);
		table.setLinesVisible(true);
		
		editor = new TableEditor(table);
		for (int i = 0; i < 3; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setWidth(100);
		}
		for (int i = 0; i < 10; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
		    item.setText( new String[] { "column 1", "column 2", "column 3" } );
		}
		table.addListener(SWT.MeasureItem, new Listener() {
            public void handleEvent(Event event) {
                event.width = table.getGridLineWidth(); //设置宽度
                event.height = (int) Math.floor(event.gc.getFontMetrics()
                        .getHeight() * 1.5); //设置高度为字体高度的1.5倍
            }
        });
		table.addListener(SWT.EraseItem, new Listener() {
	        public void handleEvent(Event event) {
	            event.detail &= ~SWT.HOT;
	            if ((event.detail & SWT.SELECTED) == 0) return;
	            int clientWidth = table.getClientArea().width;
	            GC gc = event.gc;
	            Color oldForeground = gc.getForeground();
	            Color oldBackground = gc.getBackground();
	            gc.setForeground(red);
	            gc.setBackground(yellow);
	            gc.fillGradientRectangle(0, event.y, clientWidth, event.height, false);
	            gc.fillRectangle(0, event.y, clientWidth, event.height);
	            gc.setForeground(oldForeground);
	            gc.setBackground(oldBackground);
	            event.detail &= ~SWT.SELECTED;
	        }
	    });
		//
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				// 获得单元格的位置
				TableItem[] items = table.getItems();
				Point pt = new Point(arg0.x, arg0.y);
				for (int i = 0; i < items.length; i++) {
					for (int j = 0; j < table.getColumnCount(); j++) {
						Rectangle rect = items[i].getBounds(j);
					    if (rect.contains(pt)) {
					    	//System.out.println("第" + i + "行" + ", 第" + j + "列" );
					    	row=i;
					    	col=j;
							createEditor(row,col,"text");
					    	break;
					    }
					 }
				}
			}
		});
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
		  if (!display.readAndDispatch())
		    display.sleep();
		}
		display.dispose();
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
	public static void createEditor(int x, final int y, final String type){
		final TableItem item = table.getItem(x);
    	rowCount = table.getItemCount();
    	colCount = table.getColumnCount();
		if (null != control){
			setItemText(type);
			control.dispose();
			composite.dispose();
		}

		composite = new Composite(table, SWT.NONE);
		composite.setLayout(new FormLayout());
		if (type.equals("combo")){
			control = new CCombo(composite, SWT.NONE);
			CCombo combo = (CCombo) control;
			combo.setText(item.getText(y));
		} else if(type.equals("text")){
			control = new Text(composite, SWT.NONE);
			Text text = (Text) control;
			text.setText(item.getText(y));
			text.selectAll();
		} else if (type.equals("date")){
			control = new CCalendar(composite, SWT.NONE);
			CCalendar calendar = (CCalendar) control;
			calendar.setText(item.getText(y));
		} else if (type.equals("check")){
			control = new Button(composite, SWT.CHECK);
		}
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		formData.right = new FormAttachment(100);
		control.setLayoutData(formData);
		List<Integer> arrXY = new ArrayList<Integer>();
		arrXY.add(x);
		arrXY.add(y);
		control.setData(arrXY);
		control.setFont(StyleFinal.SYS_FONT);
		control.forceFocus();
		control.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				setItemText(type);
			}
		});
		control.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode==SWT.KEYPAD_CR){
					//回车
					if (col == colCount-1){
						col = 0;
						if (row == rowCount-1){
							row = 0;
						}else{
							row += 1;
						}
					}else{
						col += 1;
					}
					table.setSelection(row);
					createEditor(row,col,type);
				} else if (e.keyCode == 16777217){
					//上
					if (row == 0){
						row = 0;
					}else{
						row -= 1;
					}
					table.setSelection(row);
					createEditor(row,col,type);
				} else if (e.keyCode == 16777218){
					//下
					if (row == rowCount-1){
						row = rowCount-1;
					}else{
						row += 1;
					}
					table.setSelection(row);
					createEditor(row,col,type);
				} else if (e.stateMask == 131072 && e.keyCode == 16777219){
					//shift+左
					if (col == 0){
						if (row == 0){
							col = 0;
							row = 0;
						}else{
							col = colCount-1;
							row -= 1;
						}
					}else{
						col -=1;
					}
					table.setSelection(row);
					createEditor(row,col,type);
				} else if (e.stateMask == 131072 && e.keyCode == 16777220){
					//shift+右
					if (col == colCount-1){
						if (row == rowCount-1){
							col = colCount-1;
							row = rowCount-1;
						}else{
							col = 0;
							row += 1;
						}
					}else{
						col += 1;
					}
					table.setSelection(row);
					createEditor(row,col,type);
				}
			}
		});
		editor.grabHorizontal = true;
		editor.setEditor(composite, item, y);
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
	private static void setItemText(String type){
    	List<Integer> list = (List<Integer>) control.getData();
		TableItem tableItem = table.getItem(list.get(0));
		if (type.equals("combo")){
			CCombo combo = (CCombo) control;
			tableItem.setText(list.get(1), combo.getText());
		} else if(type.equals("text")){
			Text text = (Text) control;
			tableItem.setText(list.get(1), text.getText());
		} else if (type.equals("date")){
			CCalendar calendar = (CCalendar) control;
			tableItem.setText(list.get(1), calendar.getText());
		} else if (type.equals("check")){
			control = new Button(table, SWT.CHECK);
		}
    }
}
