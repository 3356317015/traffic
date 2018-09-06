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

package com.zhima.traffic.ui.epd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.epd.IEpdFaresuit;
import com.zhima.traffic.action.epd.impl.ImpEpdFaresuit;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdFaresuit;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdFaresuitUi概要说明：价套
 * @author lcy
 */
public class EpdFaresuitUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	//查询相关
	private String fFaresuitCode = "";
	private String fFaresuitName = "";
	
	/**
	 * 
	 * 构造函数: 城市区域类
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EpdFaresuitUi(Composite parent, int style, List list) {
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
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("价套代码","faresuitCode",120));
		columns.add(new GridColumn("价套名称","faresuitName",120));
		columns.add(new GridColumn("开始日期","startDate",120));
		columns.add(new GridColumn("结束日期","endDate",120));
		columns.add(new GridColumn("价套状态","faresuitStatus",TraffFinal.ARR_FARESUIT_STATUS,120));
		columns.add(new GridColumn("备注","remark",300));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rights);
		gridConfig.setObj(obj);
		gridView = new GridView(panel.detail, SWT.NONE);
		gridView.CreateTabel(gridConfig);
		gridView.bindMouseDoubleClick(obj,rights,"updateMethod");
		gridView.bindRefresh(obj, "queryRegion");
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		gridView.setLayoutData(data);
	}
	
	/**
	 * queryMethod方法描述：查询方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryMethod(){
		EpdFaresuitFindUi findUi = new EpdFaresuitFindUi(this.getShell());
		findUi.setfFaresuitCode(fFaresuitCode);
		findUi.setfFaresuitName(fFaresuitName);
		findUi.open();
		if (findUi.getBtnId()==1){
			fFaresuitCode = findUi.getfFaresuitCode();
			fFaresuitName = findUi.getfFaresuitName();
			ThreadWaiting waiting = new ThreadWaiting(this,"queryFaresuit",new Class[]{},new String[]{});
			waiting.task();
		}
	}
	
	public void queryFaresuit(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					int start = gridView.getStart();
					int limit = gridView.getLimit();
					IEpdFaresuit iEpdFaresuit = new ImpEpdFaresuit();
					int count = iEpdFaresuit.queryCountByCustom(CommFinal.organize.getOrganizeSeq(),fFaresuitCode, fFaresuitName);
					if (count>0){
						List<EpdFaresuit> faresuits = iEpdFaresuit.queryPageByCustom(CommFinal.organize.getOrganizeSeq(),
								fFaresuitCode, fFaresuitName, start, limit);
						gridView.setDataList(faresuits);
					}else{
						gridView.removeAll();
					}
					gridView.setTotalCount(count);
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void addMethod(){
		EpdFaresuitEditUi editUi = new EpdFaresuitEditUi(this.getShell(),gridView,CommFinal.OPER_TYPE_ADD);
		editUi.open();
	}
	
	public void updateMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					EpdFaresuitEditUi editUi = new EpdFaresuitEditUi(this.getShell(), gridView, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的项。");
				}
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteMethod(){
		try {
			int checkIndex[] = gridView.getSelectionIndexs();
			if (checkIndex.length>0){
				int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行数据吗？");
		    	if(isdel == SWT.YES){
		    		IEpdFaresuit iEpdFaresuit = new ImpEpdFaresuit();
		    		iEpdFaresuit.delete((List<EpdFaresuit>) gridView.getSelections(),CommFinal.initConfig());
					gridView.deleteRow(checkIndex);
				} 
		    }
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void outputMethod() throws UserBusinessException{
		gridView.exportExcel();
	}

}
