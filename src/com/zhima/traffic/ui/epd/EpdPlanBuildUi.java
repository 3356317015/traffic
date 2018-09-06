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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.epd.IEpdPlan;
import com.zhima.traffic.action.epd.IEpdPlanBuild;
import com.zhima.traffic.action.epd.impl.ImpEpdPlan;
import com.zhima.traffic.action.epd.impl.ImpEpdPlanBuild;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.comm.TraffParam;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * EpdPlanUi概要说明：运营计划设置
 * @author lcy
 */
public class EpdPlanBuildUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	//查询相关
	private Label lbInfo;

	/**
	 * 
	 * 构造函数: 车辆档案
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EpdPlanBuildUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		panel = new BasicPanel(this, SWT.BORDER);
		//panel.setInput(false);
		panel.createPanel();
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		panel.setLayoutData(data);
		createToolbar();
		createInput();
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
	
	private void createInput(){
		panel.input.setLayout(new FormLayout());
		lbInfo = new Label(panel.input, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		lbInfo.setLayoutData(data);
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
		columns.add(new GridColumn("线路名称","routeName",120));
		columns.add(new GridColumn("车型","cargradeName",100));
		columns.add(new GridColumn("计划车次","linerId",100));
		columns.add(new GridColumn("发车时间","linerTime",80));
		columns.add(new GridColumn("座位数","seatNum",80));
		columns.add(new GridColumn("免票数","freeNum",80));
		columns.add(new GridColumn("半票数","halfNum",80));
		columns.add(new GridColumn("状态","linerState",60));
		columns.add(new GridColumn("类型","linerType",80));
		columns.add(new GridColumn("检票口","checkName",80));
		columns.add(new GridColumn("发车位","gateName",80));
		columns.add(new GridColumn("打印座位","ifPrintseat",TraffFinal.ARR_IF_PRINTSEAT,80));
		columns.add(new GridColumn("允许网售","ifNetsale",TraffFinal.ARR_IF_NETSALE,80));
		columns.add(new GridColumn("配载","ifDeal",TraffFinal.ARR_IF_DEAL,80));
		columns.add(new GridColumn("始发站","ifMain",TraffFinal.ARR_IF_MAIN,80));
		columns.add(new GridColumn("备注","remark",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rights);
		gridConfig.setObj(obj);
		gridView = new GridView(panel.detail, SWT.NONE);
		gridView.CreateTabel(gridConfig);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		gridView.setLayoutData(data);
	}
	
	/**
	 * bulidMethod方法描述：生成班次方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void buildMethod(){
		/*ThreadWaiting waiting = new ThreadWaiting(this,"bulidPlan",new Class[]{},new String[]{});
		waiting.task();*/
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("buildMethod")){
					IEpdPlan iEpdPlan = new ImpEpdPlan();
					List<EpdPlan> epdPlans = iEpdPlan.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
					
					if (null != epdPlans && epdPlans.size()>0){
						PlanThread pThread = new PlanThread(epdPlans);
						pThread.start();
					}
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	class PlanThread extends Thread{
		private IEpdPlanBuild iEpdPlanBuild = new ImpEpdPlanBuild();
		private List<EpdPlan> epdPlans = new ArrayList<EpdPlan>();
		private List<ItsLiner> itsLiners = new ArrayList<ItsLiner>();
		private int j = 0;
		public PlanThread(EpdPlan epdPlan){
			this.epdPlans.add(epdPlan);
		}
		
		public PlanThread(List<EpdPlan> epdPlans){
			this.epdPlans = epdPlans;
		}
		
		/**
		 * 多线程的运行函数
		 */
		public void run(){
			for(int i=0;i<epdPlans.size();i++){
				Display.getDefault().syncExec(new Runnable(){
					public void run(){
						lbInfo.setText("正在生成车次" + epdPlans.get(j).getPlanId()
								+ " 线路=" + epdPlans.get(j).getRouteName()
								+ " 预售=" + epdPlans.get(j).getPreDays()
								+" "+ Integer.valueOf(j+1) + " of " + epdPlans.size());
					}
				});
				try {
					String buildFare =CommFinal.getParamValue(TraffParam.BuildLinerFare).getParameterValue();
					itsLiners = iEpdPlanBuild.planBuild(epdPlans.get(i), buildFare, CommFinal.initConfig());
				} catch (final UserBusinessException e) {
					Display.getDefault().syncExec(new Runnable(){
						public void run(){
							try {
								gridView.addRow(epdPlans.get(j));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}finally{
					j += 1 ;
					if(null !=itsLiners && itsLiners.size()>0){
						Display.getDefault().syncExec(new Runnable(){
							public void run(){
								try {
									gridView.addRows(itsLiners);
								} catch (final Exception e) {
								}
							}
						});
					}
				}
			}
			Display.getDefault().syncExec(new Runnable(){
				public void run(){
					lbInfo.setText("生成车次任务已完成");
				}
			});
		}
	}
	
	public void outputMethod() throws UserBusinessException{
		gridView.exportExcel();
	}

}
