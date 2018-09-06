package test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class PaintTableCell {
 
     public static void main(String[] args) {
         final Display display = Display.getDefault();
         Shell shell = new Shell(display);
         shell.setText("Paint Table Cell");
         shell.setSize(500, 400);
         shell.setLayout(new FillLayout());
         
        final Table table = new Table(shell, SWT.FULL_SELECTION | SWT.SINGLE);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        TableColumn column1 = new TableColumn(table, SWT.NONE);
        column1.setWidth(100);
        column1.setAlignment(SWT.LEFT);
        column1.setText("Column 1");
        
        TableColumn column2 = new TableColumn(table, SWT.NONE);
        column2.setWidth(100);
        column2.setAlignment(SWT.RIGHT);
        column2.setText("Column 2");
        
        TableItem item1 = new TableItem(table, SWT.NONE);
        item1.setText("item 11");
        
        TableItem item2 = new TableItem(table, SWT.NONE);
        item2.setText(new String[] {"item 21", "item 22"});
        
        table.addPaintListener(new PaintListener() {

            @Override
            public void paintControl(PaintEvent e) {
                //获得单元格的位置
                Rectangle rect = table.getItem(0).getBounds(1);
                
                //计算需要绘制渐变效果的长度
                int width = rect.width * 67 / 100;//67%
                
                GC gc = e.gc;
                //设置GC的背景色和前景色
                gc.setForeground(new Color(display, 255, 192, 0));
                gc.setBackground(new Color(display, 255, 230, 151));
                //绘制渐变效果
                gc.fillGradientRectangle(rect.x, rect.y, width, rect.height, false);
                
                //绘制单元格中的文字
                String text = "item 12";
                gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
                int pixels = gc.getFontMetrics().getAverageCharWidth() * text.length();
                gc.drawString(text, rect.x + rect.width - pixels - 6, rect.y, true);
            }

	
            
        });    
        
       shell.open();
       
        while(!shell.isDisposed()) {
            if(!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
