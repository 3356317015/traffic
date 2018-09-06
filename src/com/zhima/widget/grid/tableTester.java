package com.zhima.widget.grid;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.zhima.frame.model.SamModule;
import com.zhima.traffic.comm.TraffFinal;

public class tableTester {

	protected Shell shell;
	private GridView gridView;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			tableTester window = new tableTester();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(800, 500);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				System.out.println(gridView.getDataList());
				System.out.println("dfsafdas");
			}
		});
		button.setLayoutData(new FormData());
		button.setBounds(0, 0, 80, 27);
		button.setText("New Button");
		
		gridView = new GridView(shell, SWT.NONE);
		FormData data = new FormData();
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		data.top = new FormAttachment(button);
		data.left = new FormAttachment(0);
		gridView.setLayoutData(data);
		
		List<SamModule> parameters = new ArrayList<SamModule>();
		for (int i = 0; i < 100; i++) {
			SamModule parameter = new SamModule();
			parameter.setModuleSeq("PK" + String.valueOf(i));
			parameter.setModuleCode("3");
			parameter.setModuleClass(String.valueOf(i));
			parameter.setModuleName("AA" + String.valueOf(i));
			parameter.setCreateTime("2012-12-25");
			parameter.setModuleType(String.valueOf(i));
			if (i<10){
				parameter.setStatus("1");
				
			}else{
				parameter.setStatus("0");
			}
			parameters.add(parameter);
		}
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("参数序号", "moduleSeq", 105));
			EditorOption editorOption = new EditorOption();
			editorOption.setRequired(true);
			//editorOption.setArrayData(new String[]{"1","2","3"});
			editorOption.setMatch(true);
			editorOption.setArrayData(TraffFinal.ARR_ORGANIZE_TYPE);
		columns.add(new GridColumn("编号", "moduleCode", 100,"ComBox",editorOption));
		columns.add(new GridColumn("名称", "moduleName",100,"Text",new EditorOption()));
		columns.add(new GridColumn("日期", "createTime", 120, "DateBox", new EditorOption()));
			editorOption = new EditorOption();
			List<GridColumn> cols=new ArrayList<GridColumn>();
			cols.add(new GridColumn("编号", "moduleCode",100));
			cols.add(new GridColumn("名称", "moduleName", 100));
			editorOption.setDropColumns(cols);
			editorOption.setDropDatas(parameters);
			editorOption.setDropValueCol("moduleCode");
			editorOption.setDropShowCol("moduleName");
		columns.add(new GridColumn("类型","moduleType",100,"DropBox",editorOption));
		columns.add(new GridColumn("状态","status",new String[]{"1-有效","0-无效"},90));
		
		
		
		
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowPage(true);
		
		gridConfig.setVirtual(false);
		gridConfig.setShowSeq(true);
		gridConfig.setCheck(false);
		gridConfig.setShowHeader(true);
		//gridConfig.setShowLines(false);
		gridConfig.setColumns(columns);
		gridConfig.setDatas(parameters);
		gridView.CreateTabel(gridConfig);
		try {
			//gridView.setDataList(parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);*/

	}
}
