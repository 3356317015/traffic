package test;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class NeTest {

    /**
     * Launch the application
     * @param args
     */
    @SuppressWarnings("unused")
	public static void main(String[] args) {
        final Display display = Display.getDefault();
        final Shell shell = new Shell();
        shell.setSize(500, 375);
        shell.setText("SWT Application");
        shell.setLayout(new FillLayout());
        //创建表单对象
        FormToolkit ft = new FormToolkit(shell.getDisplay());
        //通过表单工具对象创建可滚动的表单对象
        final ScrolledForm form = ft.createScrolledForm(shell);
        //表单文本
        form.setText("swt表单example");
                                          //设置表单布局
        form.getBody().setLayout(new TableWrapLayout());            
        //创建可折叠的面板
        ExpandableComposite ec = ft.createExpandableComposite(form.getBody(), 
                                                                                                          ExpandableComposite.TWISTIE);
        ec.setText("可折叠面板ExpandableComposite");
        //定义字符串
        String txt = "测试";
        //创建一个标签并显示字符串
        Label lb = ft.createLabel(ec, txt,SWT.WRAP);
        //将Label作为折叠面板的折叠区域
        ec.setClient(lb);
        //为折叠面板添加展开 折叠的监听器
        ec.addExpansionListener(new ExpansionAdapter(){
            public void expansionStateChanged(ExpansionEvent e ){
                //根据部件的新尺寸重新定位和更新滚动条
                form.reflow(true);
            }
        });
                        
        //创建内容区域  样式TWISTIE  显示背景标题TITLE_BAR
        Section st = ft.createSection(form.getBody(), Section.TWISTIE|Section.TITLE_BAR);
        st.setText("内容区域Section");
        //创建一个面板 作为内容折叠区域放置的控件
        Composite cs = ft.createComposite(st);
        cs.setLayout(new GridLayout());
        Button btt1;
        Button btt2;
        Text text;
        btt1 = ft.createButton(cs,"系统资料",SWT.TOGGLE | SWT.FLAT | SWT.BORDER);
        btt2 = ft.createButton(cs,"个人资料", SWT.TOGGLE);
        text = ft.createText(cs, txt, SWT.NONE);
        text.setText("填写数据");
        st.setClient(cs);        
        //为折叠面板添加展开 折叠的监听器
        st.addExpansionListener(new ExpansionAdapter(){
            public void expansionStateChanged(ExpansionEvent e ){
                //根据部件的新尺寸重新定位和更新滚动条
                form.reflow(true);
            }
        });
        DateTime dt1 = new DateTime(cs,SWT.CALENDAR);
        DateTime time = new DateTime(cs,SWT.TIME);
        DateTime data = new DateTime(cs,SWT.DATE); 
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        ft.dispose();
        display.dispose();
    }
}