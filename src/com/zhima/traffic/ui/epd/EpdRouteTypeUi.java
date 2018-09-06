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
import com.zhima.traffic.action.epd.IEpdRouteType;
import com.zhima.traffic.action.epd.impl.ImpEpdRouteType;
import com.zhima.traffic.model.EpdRoutetype;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdRouteTypeUi概要说明：线路类型设置
 * @author lcy
 */
public class EpdRouteTypeUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	//查询相关
	private String fRoutetypeCode = "";
	private String fRoutetypeName = "";
	/**
	 * 
	 * 构造函数: 线路类型
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EpdRouteTypeUi(Composite parent, int style, List list) {
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
		columns.add(new GridColumn("线路类型代码","routetypeCode",120));
		columns.add(new GridColumn("线路类型名称","routetypeName",120));
		columns.add(new GridColumn("备注","remark",400));
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
		EpdRouteTypeFindUi findUi = new EpdRouteTypeFindUi(this.getShell());
		findUi.setfRoutetypeCode(fRoutetypeCode);
		findUi.setfRoutetypeCode(fRoutetypeName);
		findUi.open();
		if (findUi.getBtnId()==1){
			fRoutetypeCode = findUi.getfRoutetypeCode();
			fRoutetypeName = findUi.getfRoutetypeName();
			ThreadWaiting waiting = new ThreadWaiting(this,"queryRouteType",new Class[]{},new String[]{});
			waiting.task();
		}
	}
	
	public void queryRouteType(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					int start = gridView.getStart();
					int limit = gridView.getLimit();
					IEpdRouteType iEpdRouteType = new ImpEpdRouteType();
					int count = iEpdRouteType.queryCountByCustom(CommFinal.organize.getOrganizeSeq(),
							fRoutetypeCode, fRoutetypeName);
					if (count>0){
						List<EpdRoutetype> routetypes = iEpdRouteType.queryPageByCustom(CommFinal.organize.getOrganizeSeq(),
								fRoutetypeCode, fRoutetypeName, start, limit);
						gridView.setDataList(routetypes);
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
		EpdRouteTypeEditUi editUi = new EpdRouteTypeEditUi(this.getShell(),gridView,CommFinal.OPER_TYPE_ADD);
		editUi.open();
	}
	
	public void updateMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					EpdRouteTypeEditUi editUi = new EpdRouteTypeEditUi(this.getShell(), gridView, CommFinal.OPER_TYPE_UPDATE);
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
		    		IEpdRouteType iEpdRouteType = new ImpEpdRouteType();
		    		iEpdRouteType.delete((List<EpdRoutetype>) gridView.getSelections(),CommFinal.initConfig());
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
