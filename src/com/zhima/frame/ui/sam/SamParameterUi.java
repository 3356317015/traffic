/**
 *******************************************************************************
 *
 * (c) Copyright 2012 重庆市志玛信息技术有限公司
 *
 * 系统名称：frameWork
 * 文  件  名 ：SamModuleUi.java
 * 模块名称：(请更改成该模块名称)
 * 创  建  人 ：鲁承毅
 * 创建日期：2013-3-2 下午10:36:15
 * 修  改  人 ：(修改了该文件，请填上修改人的名字)
 * 修改日期：(请填上修改该文件时的日期)
 * 版         本 ： V1.0.0
 *******************************************************************************  
 */

package com.zhima.frame.ui.sam;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamParameter;
import com.zhima.frame.action.sam.impl.ImpSamParameter;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamParameter;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * SamParameterUi概要说明：参数设置
 * @author lcy
 */
public class SamParameterUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	//查询相关
	private String fOrganize = "";
	private String fOrganizeName = "";
	private Tree tree;
	
	/**
	 * 构造函数:参数设置
	 * @param parent
	 * @param style
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SamParameterUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		panel = new BasicPanel(this, SWT.BORDER);
		panel.setInput(false);
		panel.createPanel();
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		panel.setLayoutData(data);
		createToolbar();
		createDetail();
	}

	/**
	 * createToolbar方法描述：构建工具面板按钮
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:37:54
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createToolbar(){
		panel.toolbar.setLayout(new RowLayout());
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, panel.toolbar, rights);
	}
	
	/**
	 * createDetail方法描述：构建细部面板表格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void createDetail(){
		panel.detail.setLayout(new FormLayout());
		tree = new Tree(panel.detail, SWT.VIRTUAL|SWT.BORDER|SWT.FULL_SELECTION);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.width = 200;
		tree.setLayoutData(data);
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					ISamParameter iSamParameter = new ImpSamParameter();
					List<SamParameter> parameters = new ArrayList<SamParameter>();
					int count = 0;
					int start = gridView.getStart();
					int limit = gridView.getEnd();
					TreeItem item = (TreeItem) arg0.item;
					if (item.getText().equals(fOrganizeName)){
						parameters = iSamParameter.queryPageByCustom(fOrganize, null, start, limit);
						count = iSamParameter.queryCountByCustom(fOrganize, null);
					} else if (item.getText().equals("其它")){
						parameters = iSamParameter.queryPageByCustom(fOrganize, null, start, limit);
						for (int i = parameters.size() - 1; i >= 0; i--) {
							if (null != parameters.get(i).getGroupName()
									&& parameters.get(i).getGroupName().length()>0){
								parameters.remove(i);
							}
						}
						count = parameters.size();
					} else{
						parameters = iSamParameter.queryPageByCustom(fOrganize, item.getText(), start, limit);
						count = iSamParameter.queryCountByCustom(fOrganize, item.getText());
					}
					gridView.setDataList(parameters);
					gridView.setTotalCount(count);

				} catch (UserBusinessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("描述","parameterName",200));
		columns.add(new GridColumn("名称","parameterCode",160));
		columns.add(new GridColumn("值","parameterValue",120));
		columns.add(new GridColumn("备注","remark",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rights);
		gridConfig.setObj(obj);
		gridView = new GridView(panel.detail, SWT.BORDER);
		gridView.CreateTabel(gridConfig);
		gridView.bindMouseDoubleClick(obj,rights,"updateMethod");
		gridView.bindRefresh(obj, "queryParameter");
		data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(tree,1);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		gridView.setLayoutData(data);
		fOrganize = CommFinal.organize.getOrganizeSeq();
		fOrganizeName = CommFinal.organize.getOrganizeName();
		queryParameter();
	}
	
	/**
	 * queryMethod方法描述：查询方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryMethod(){
		SamParameterFindUi findUi = new SamParameterFindUi(this.getShell());
		findUi.setfOrganize(fOrganize);
		findUi.open();
		if (findUi.getBtnId()==1){
			fOrganize = findUi.getfOrganize();
			ThreadWaiting waiting = new ThreadWaiting(this,"queryParameter",new Class[]{},new String[]{});
			waiting.task();
		}
	}
	
	public void queryParameter(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					if (null != fOrganize && fOrganize.length()>0){
						tree.removeAll();
						TreeItem mainItem = new TreeItem(tree, SWT.NONE);
						mainItem.setFont(StyleFinal.SYS_FONT);
						mainItem.setText(fOrganizeName);
		
						ISamParameter iSamParameter = new ImpSamParameter();
						List<SamParameter> samParameters = iSamParameter.queryAllByCustom(fOrganize, null);
						List<String> list = new ArrayList<String>();
						if (null != samParameters && samParameters.size()>0){
							boolean isNew = true;
							for (int j = 0; j < samParameters.size(); j++) {
								if (null != samParameters.get(j).getGroupName()
										&& samParameters.get(j).getGroupName().length()>0){
									isNew = true;
									if (null != list && list.size()>0){
										for (int k = 0; k < list.size(); k++) {
											if (list.get(k).equals(samParameters.get(j).getGroupName())){
												isNew = false;
												break;
											}
										}
									}
									if (isNew == true){
										list.add(samParameters.get(j).getGroupName());
									}
								}
								
							}
						}
						list.add("其它");
						for (int j = 0; j < list.size(); j++) {
							TreeItem item = new TreeItem(mainItem, SWT.NONE);
							item.setFont(StyleFinal.SYS_FONT);
							item.setText(list.get(j));
						}
					}
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void updateMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					SamParameterEditUi editUi = new SamParameterEditUi(this.getShell(), gridView, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
				break;
			}
		}
	}
	
	public void outputMethod() throws UserBusinessException{
		gridView.exportExcel();
	}

}
