package test;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
 
public class TreeTest {
    final static Display display = new Display();
    final static Shell shell = new Shell(display);
    
    
    final static Tree tree = new Tree(shell, SWT.BORDER);
 
    
 
    public static void main(String[] args) {
        shell.setText("Tree Sample");
        shell.setLayout(new FillLayout());
 
        TreeItem itemSys = new TreeItem(tree, SWT.NULL|SWT.FLAT);
        
        itemSys.setText("系统设置");
        
        TreeItem itemVoice = new TreeItem(tree, SWT.NULL|SWT.FLAT);
        
        itemVoice.setText("语音设置");
        
        TreeItem itemLiner = new TreeItem(tree, SWT.NULL|SWT.FLAT);
        
        itemLiner.setText("班次设置");

 
        tree.addSelectionListener(new SelectionListener() {
 
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                System.out.println(arg0.item.toString());
            }
 
            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
                System.out.println(arg0.item.toString());
            }
        });
 
        shell.setSize(200, 150);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
 
        display.dispose();
 
    }
 
    @SuppressWarnings("unused")
	private static void initPopup() {
 
        Menu menu = new Menu(tree);
        MenuItem newItem = new MenuItem(menu, SWT.PUSH);
        newItem.setText("新增部门");
        MenuItem newMemberItem = new MenuItem(menu, SWT.PUSH);
 
        newMemberItem.setText("新增员工");
        MenuItem editItem = new MenuItem(menu, SWT.PUSH);
        editItem.setText("编辑");
        MenuItem deleteItem = new MenuItem(menu, SWT.PUSH);
 
        deleteItem.setText("删除");
        tree.setMenu(menu);
 
    }
 
}
