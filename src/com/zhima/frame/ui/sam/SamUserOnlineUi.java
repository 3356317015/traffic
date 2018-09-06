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
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamUserOnline;
import com.zhima.frame.action.sam.impl.ImpSamUserOnline;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamUserOnline;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * SamUserOnlineUi概要说明：在线用记查询
 * @author lcy
 */
public class SamUserOnlineUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	/**
	 * 
	 * 构造函数: 在线用户操作面板
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SamUserOnlineUi(Composite parent, int style, List list) {
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
		columns.add(new GridColumn("用户代码","userCode",160));
		columns.add(new GridColumn("用户名称","userName",180));
		columns.add(new GridColumn("版本号","sysVersion",160));
		columns.add(new GridColumn("登录地址","loginIp",160));
		columns.add(new GridColumn("登录时间","loginTime",200));
		columns.add(new GridColumn("报告时间","onlineTime",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rights);
		gridConfig.setObj(obj);
		gridView = new GridView(panel.detail, SWT.NONE);
		gridView.CreateTabel(gridConfig);
		gridView.bindMouseDoubleClick(obj,rights,"updateMethod");
		gridView.bindRefresh(obj, "queryMethod");
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
		ThreadWaiting waiting = new ThreadWaiting(this,"queryOnlineUser",new Class[]{},new String[]{});
		waiting.task();
	}
	
	public void queryOnlineUser(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					int start = gridView.getStart();
					int limit = gridView.getLimit();
					ISamUserOnline iSamUserOnline = new ImpSamUserOnline();
					int count = iSamUserOnline.queryCountByAll(CommFinal.organize.getOrganizeSeq());
					if (count>0){
						List<SamUserOnline> samUserOnlines = iSamUserOnline.queryPageByAll(CommFinal.organize.getOrganizeSeq(),start, limit);
						gridView.setDataList(samUserOnlines);
						if (null != samUserOnlines && samUserOnlines.size()>0){
							String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
							for (int j = 0; j < samUserOnlines.size(); j++) {
								long cnt = DateUtils.secondInterval(samUserOnlines.get(j).getOnlineTime(), currTime);
								if (cnt -10 > CommFinal.ONLINE_UPDATE_TIME){
									gridView.table.getItem(j).setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
								}
							}
						}
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
	
	public void clearMethod() throws UserBusinessException{
		ThreadWaiting waiting = new ThreadWaiting(this,"clearUser",new Class[]{},new String[]{});
		waiting.task();
	}
	
	public void clearUser() throws UserBusinessException{
		ISamUserOnline iSamUserOnline = new ImpSamUserOnline();
		iSamUserOnline.clearUser(CommFinal.organize.getOrganizeSeq(),CommFinal.ONLINE_UPDATE_TIME);
		queryOnlineUser();
	}
	
	public void outputMethod() throws UserBusinessException{
		gridView.exportExcel();
	}

}
