package com.zhima.traffic.ui.operate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.action.sam.ISamOrganize;
import com.zhima.frame.action.sam.impl.ImpSamOrganize;
import com.zhima.frame.model.SamOrganize;
import com.zhima.traffic.action.operate.IItsLinerdeal;
import com.zhima.traffic.action.operate.impl.ImpItsLinerdeal;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerdeal;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.Validate;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.EditorOption;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class ItsLinerDealUi extends Dialog {
	private GridView gridView;
	private String operType;
	private Object obj;
	
	private CCombo cboIfDeal;
	private CCombo cboIfMain;
	private GridView gridOrganize;
	private GridView gridDeal;

	protected ItsLinerDealUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
		this.obj = this;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("配载设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(745,505);
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
		
		Label lbIfDeal = new Label(compMain, SWT.RIGHT);
		lbIfDeal.setFont(StyleFinal.SYS_FONT);
		lbIfDeal.setText("是否配载:");
		FormData formData = new FormData();
		formData.top = new FormAttachment(0,10);
		formData.left = new FormAttachment(0,10);
		lbIfDeal.setLayoutData(formData);
		
		cboIfDeal = new CCombo(compMain, SWT.BORDER|SWT.READ_ONLY);
		cboIfDeal.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(lbIfDeal,-2,SWT.TOP);
		formData.left = new FormAttachment(lbIfDeal,5);
		formData.width = 135;
		formData.height = StyleFinal.DROP_HEIGHT;
		cboIfDeal.setLayoutData(formData);
		
		Label lbIfMain = new Label(compMain, SWT.RIGHT);
		lbIfMain.setFont(StyleFinal.SYS_FONT);
		lbIfMain.setText("始发站:");
		formData = new FormData();
		formData.top = new FormAttachment(lbIfDeal,0,SWT.TOP);
		formData.right = new FormAttachment(100,-150);
		lbIfMain.setLayoutData(formData);
		
		cboIfMain = new CCombo(compMain, SWT.BORDER|SWT.READ_ONLY);
		cboIfMain.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(cboIfDeal,0,SWT.TOP);
		formData.left = new FormAttachment(lbIfMain,5);
		formData.width = 135;
		formData.height = StyleFinal.DROP_HEIGHT;
		cboIfMain.setLayoutData(formData);
		
		Group gpOrganize = new Group(compMain, SWT.NONE);
		gpOrganize.setLayout(new FormLayout());
		gpOrganize.setText("可选择车站");
		gpOrganize.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(lbIfDeal,10);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(43);
		formData.bottom = new FormAttachment(100);
		gpOrganize.setLayoutData(formData);
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("车站名称","organizeName",220));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowSeq(true);
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridConfig.setObj(obj);
		gridOrganize = new GridView(gpOrganize, SWT.BORDER);
		gridOrganize.CreateTabel(gridConfig);
		formData = new FormData();
		formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(0,5);
		formData.right = new FormAttachment(100,-5);
		formData.bottom = new FormAttachment(100,-5);
		gridOrganize.setLayoutData(formData);
		gridOrganize.bindMouseDoubleClick(obj, "addDeal");

		Group gpDeal = new Group(compMain, SWT.NONE);
		gpDeal.setLayout(new FormLayout());
		gpDeal.setText("已选择车站");
		gpDeal.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(gpOrganize,0,SWT.TOP);
		formData.left = new FormAttachment(gpOrganize,10);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		gpDeal.setLayoutData(formData);
		columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("车站名称","organizeName",220));
		EditorOption editorOption = new EditorOption();
		editorOption.setVerify(true);
		columns.add(new GridColumn("协议号","dealId",100, "Text",editorOption));
		gridConfig = new GridConfig();
		gridConfig.setShowSeq(true);
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridConfig.setObj(obj);
		gridDeal = new GridView(gpDeal, SWT.BORDER);
		gridDeal.CreateTabel(gridConfig);
		formData = new FormData();
		formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(0,5);
		formData.right = new FormAttachment(100,-5);
		formData.bottom = new FormAttachment(100,-5);
		gridDeal.setLayoutData(formData);
		gridDeal.bindMouseDoubleClick(obj, "delDeal");
		initData();
		return compMain;
	}
	
	private void initData(){
		try {
			cboIfDeal.setItems(CommFinal.getAllName(TraffFinal.ARR_IF_DEAL));
			cboIfMain.setItems(CommFinal.getAllName(TraffFinal.ARR_IF_MAIN));
			ISamOrganize iSamOrganize = new ImpSamOrganize();
			List<SamOrganize> organizes = iSamOrganize.queryDealByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		
			gridOrganize.setDataList(organizes);
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				ItsLiner itsLiner = (ItsLiner) gridView.getSelection();
				if (null != itsLiner && !"".equals(itsLiner.getLinerSeq())){
					cboIfDeal.setText(CommFinal.getItemName(TraffFinal.ARR_IF_DEAL, String.valueOf(itsLiner.getIfDeal())));
					cboIfMain.setText(CommFinal.getItemName(TraffFinal.ARR_IF_MAIN, String.valueOf(itsLiner.getIfDeal())));
					IItsLinerdeal iItsLinerdeal = new ImpItsLinerdeal();
					List<ItsLinerdeal> linerdeals = iItsLinerdeal.queryByLinerSeq(itsLiner.getLinerSeq());
					gridDeal.setDataList(linerdeals);
					
					if (null !=  linerdeals && linerdeals.size()>0){
						@SuppressWarnings("unchecked")
						List<SamOrganize> samOrganizes = (List<SamOrganize>) gridOrganize.getDataList();
						if (null != samOrganizes && samOrganizes.size()>0){
							for (int i = 0; i < linerdeals.size(); i++) {
								for (int j = 0; j < samOrganizes.size(); j++) {
									if (linerdeals.get(i).getDealOrganize().equals(samOrganizes.get(j).getOrganizeCode())){
										gridOrganize.deleteRow(j);
										break;
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				
				if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
					String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
					ItsLiner itsLiner = (ItsLiner) gridView.getSelection();
					
					if (Validate.StrNotNull(cboIfDeal.getText())){
						itsLiner.setIfDeal(Integer.valueOf(CommFinal.getItemValue(TraffFinal.ARR_IF_DEAL, cboIfDeal.getText())));
						if (1==itsLiner.getIfDeal()){
							if (null != gridDeal.getDataList() && gridDeal.getDataList().size()>0){
								List<ItsLinerdeal> linerdeals = (List<ItsLinerdeal>) gridDeal.getDataList();
								for (int i = 0; i < linerdeals.size(); i++) {
									if(Validate.StrNotNull(linerdeals.get(i).getDealId())){
									}else{
										MsgBox.warning(getParentShell(),linerdeals.get(i).getOrganizeName()+"请输入协议号！");
										return;
									}
								}
							}else{
								MsgBox.warning(getParentShell(),"请设置计划配载协议！");
								return;
							}
						}

					}else{
						itsLiner.setIfDeal(0);
						itsLiner.setIfMain(1);
					}
					
					if ("1".equals(itsLiner.getIfDeal())){
						if (Validate.StrNotNull(cboIfMain.getText())){
							itsLiner.setIfMain(Integer.valueOf(CommFinal.getItemValue(TraffFinal.ARR_IF_MAIN, cboIfMain.getText())));
						}else{
							MsgBox.warning(getParentShell(),"请选择计划是否始发！");
							cboIfMain.forceFocus();
							return;
						}
					}else{
						itsLiner.setIfMain(1);
					}

					itsLiner.setUpdateTime(currTime);
					IItsLinerdeal iItsLinerdeal = new ImpItsLinerdeal();
					
					iItsLinerdeal.updateAttribute(itsLiner,(List<ItsLinerdeal>) gridDeal.getDataList(),CommFinal.initConfig());
					gridView.updateRow(gridView.getSelectionIndex(), itsLiner);
					close();
				}
				
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
	
	@SuppressWarnings("unchecked")
	public void addDeal(){
		try {
			if (null != gridOrganize.getSelections() && gridOrganize.getSelections().size()>0){
				List<SamOrganize> organizes = (List<SamOrganize>) gridOrganize.getSelections();
				for (int i = 0; i < organizes.size(); i++) {
					ItsLinerdeal itsLinerdeal = new ItsLinerdeal();
					itsLinerdeal.setDealOrganize(organizes.get(i).getOrganizeCode());
					itsLinerdeal.setOrganizeName(organizes.get(i).getOrganizeName());
					gridDeal.addRow(itsLinerdeal);
				}
				gridOrganize.deleteRow(gridOrganize.getSelectionIndexs());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void delDeal(){
		try {
			if (null != gridDeal.getSelections() && gridDeal.getSelections().size()>0){
				List<ItsLinerdeal> linerdeals = (List<ItsLinerdeal>) gridDeal.getSelections();
				for (int i = 0; i < linerdeals.size(); i++) {
					SamOrganize organize = new SamOrganize();
					organize.setOrganizeCode(linerdeals.get(i).getDealOrganize());
					organize.setOrganizeName(linerdeals.get(i).getOrganizeName());
					gridOrganize.addRow(organize);
				}
				gridDeal.deleteRow(gridDeal.getSelectionIndexs());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
