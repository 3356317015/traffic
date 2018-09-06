package com.zhima.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.action.sam.ISamUserColumn;
import com.zhima.frame.action.sam.impl.ImpSamUserColumn;
import com.zhima.frame.model.SamUserColumn;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.grid.EditorOption;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class GridCustomEditUi extends Dialog {
	private GridView gridView;
	private GridConfig gridConfig;
	
	public GridCustomEditUi(Shell shell, GridConfig gridConfig) {
		super(shell);
		this.gridConfig = gridConfig;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setText("自定义表格项");
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(635,425);
    }
	
	protected void createButtonsForButtonBar(Composite parent){
		GridData btnData = new GridData(StyleFinal.DIALOGBAR_ALIGNMENT);
		parent.setLayoutData(btnData);
		Button confirm = createButton(parent, 1, "确定(&O)", false);
		confirm.setFont(StyleFinal.SYS_FONT);
		Button cancel = createButton(parent, 0, "取消(&C)", false);
		cancel.setFont(StyleFinal.SYS_FONT);
		if (StyleFinal.BTN_SHOWIMAGE == true){
			confirm.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_OK));
			cancel.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_CANCEL));
		}
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite compMain = (Composite) super.createDialogArea(parent);
		compMain.setLayout(new FormLayout());

		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("表格列名","columnEname",200));
		columns.add(new GridColumn("显示名称","columnCname",200,"Text",new EditorOption()));
		EditorOption editorOption = new EditorOption();
		editorOption.setVerify(true);
		columns.add(new GridColumn("宽度","width",80,"Text",editorOption));
			editorOption = new EditorOption();
			editorOption.setArrayData(new String[]{"center","left","right"});
			editorOption.setMatch(false);
		columns.add(new GridColumn("对齐方式","alignment",120,"ComBox",editorOption));
		
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(true);
		gridConfig.setShowPage(false);
		gridConfig.setShowSeq(false);
		gridConfig.setColumns(columns);
		gridView = new GridView(compMain, SWT.BORDER);
		gridView.CreateTabel(gridConfig);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		gridView.setLayoutData(data);
		
		initData();

		return compMain;
	}
	
	private void initData(){
		try {
			Menu menu = new Menu(gridView.table);
			//表格设置右键菜单

			MenuItem upItem = new MenuItem(menu, SWT.CASCADE);
			upItem.setText("上移");
			upItem.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if (gridView.getSelectionIndex()>=0){
						gridView.upTableRow();
					}
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});
			
			MenuItem downItem = new MenuItem(menu, SWT.CASCADE);
			downItem.setText("下移");
			downItem.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if (gridView.getSelectionIndex()>=0){
						gridView.downTableRow();
					}
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});
			
			new MenuItem(menu, SWT.SEPARATOR);
			
			MenuItem defaultItem = new MenuItem(menu, SWT.CASCADE);
			defaultItem.setText("默认设置");
			defaultItem.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if (null != gridView.getSelection()){
						SamUserColumn userColumn = (SamUserColumn) gridView.getSelection();
						if(null != gridConfig.getBasecols() && gridConfig.getBasecols().size()>0){
							for (int i = 0; i < gridConfig.getBasecols().size(); i++) {
								if (userColumn.getColumnEname().equals(gridConfig.getBasecols().get(i).getEName())){
									userColumn.setColumnCname(gridConfig.getBasecols().get(i).getCName());
									userColumn.setWidth(gridConfig.getBasecols().get(i).getWidth());
									userColumn.setAlignment("left");
									gridView.updateRow(gridView.getSelectionIndex(), userColumn);
								}
							}
						}
					}
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});
			
			gridView.table.setMenu(menu);
			
			ISamUserColumn iSamUserColumn = new ImpSamUserColumn();
			List<SamUserColumn> userColumns = iSamUserColumn.queryByUserColumn(gridConfig.getObj().getClass().getName(), gridConfig.getGridName(),
					CommFinal.user.getUserSeq());
			gridView.setDataList(userColumns);
			if (null != userColumns && userColumns.size()>0){
				for (int i = 0; i < userColumns.size(); i++) {
					gridView.setCheck(i, true);
				}
			}
			List<SamUserColumn> validColumns = iSamUserColumn.queryByValidColumn(gridConfig.getBasecols(),gridConfig.getObj().getClass().getName(), gridConfig.getGridName(),
					CommFinal.user.getUserSeq());
			if (null != validColumns && validColumns.size()>0){
				boolean isAdd = true;
				for (int i = 0; i < validColumns.size(); i++) {
					isAdd = true;
					if (null != userColumns && userColumns.size()>0){
						for (int j = 0; j < userColumns.size(); j++) {
							if (validColumns.get(i).getColumnEname().equals(userColumns.get(j).getColumnEname())){
								isAdd = false;
							}
						}
					}
					if (isAdd == true){
						gridView.addRow(validColumns.get(i));
					}
				}
				if (null != userColumns && userColumns.size()>0){
				}else{
					for (int i = 0; i < validColumns.size(); i++) {
						gridView.setCheck(i, true);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				List<SamUserColumn> userColumns = (List<SamUserColumn>) gridView.getCheckSelections();
				for (int i = 0; i < userColumns.size(); i++) {
					userColumns.get(i).setClassName(gridConfig.getObj().getClass().getName());
					userColumns.get(i).setGridName(gridConfig.getGridName());
					userColumns.get(i).setSn(String.valueOf(i));
					if (null == userColumns.get(i).getWidth() || "".equals(userColumns.get(i).getWidth())){
						userColumns.get(i).setWidth(0);
					}
				}
				ISamUserColumn iSamUserColumn = new ImpSamUserColumn();
				iSamUserColumn.updateUserColumn(
						userColumns,gridConfig.getObj().getClass().getName(),
						gridConfig.getGridName(),
						CommFinal.user.getUserSeq(),
						CommFinal.initConfig());
				close();
			} else if (0 == buttonId) {
				/**
				 * 取消
				 */
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		
	}

	
}
