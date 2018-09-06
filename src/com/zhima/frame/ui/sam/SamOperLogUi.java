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
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamLogDetail;
import com.zhima.frame.action.sam.ISamLogOper;
import com.zhima.frame.action.sam.impl.ImpSamLogDetail;
import com.zhima.frame.action.sam.impl.ImpSamLogOper;
import com.zhima.frame.model.SamLogDetail;
import com.zhima.frame.model.SamLogOper;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * SamOperLogUi概要说明：操作日志查询
 * @author lcy
 */
public class SamOperLogUi extends Composite {
	private Object obj;
	//功能权限
	private List<SamModuleRight> rights;
	//操作日志
	private GridView gridOper;
	//日志明细
	private GridView gridDetail;
	//查询相关
	private String fModuleCode = "";
	private String fOperType = "";
	private String fUserCode = "";
	private String fStatus = "";
	private String fStartDate = "";
	private String fEndDate = "";

	/**
	 * 
	 * 构造函数:操作日志
	 * @param parent 父面板
	 * @param style 风格
	 * @param list 权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SamOperLogUi(Composite parent, int style, List list) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.obj = this;
		this.rights=list;
		final SashForm sashForm = new SashForm(this,SWT.HORIZONTAL|SWT.FLAT);
        sashForm.setLayout(new FillLayout());
        FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		sashForm.setLayoutData(data);
		createOper(sashForm);
        createDetail(sashForm);
        sashForm.setWeights(new int[] {55,45});
	}

	/**
	 * createOper方法描述：操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午11:18:38
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sashForm
	 * @return Composite
	 */
	public Composite createOper(SashForm sashForm){
		BasicPanel compOper = new BasicPanel(sashForm, SWT.BORDER);
		compOper.setShowBack(false);
		compOper.setInput(false);
		compOper.createPanel();
		//按钮权限
		compOper.toolbar.setLayout(new RowLayout());
		List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		for(int i=0;i<rights.size();i++){
			if ("queryMethod".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("deleteMethod".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("outputOper".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compOper.toolbar, rightBos);
		//操作日志表格
		compOper.detail.setLayout(new FillLayout());
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("模块代码","moduleCode",160));
		columns.add(new GridColumn("模块名称","moduleName",170));
		columns.add(new GridColumn("操作类型","rightName",100));
		columns.add(new GridColumn("状态","status",new String[]{"0-失败","1-正常"},70));
		columns.add(new GridColumn("描述","operDesc",200));
		columns.add(new GridColumn("操作员代码","operCode",120));
		columns.add(new GridColumn("操作员姓名","operName",120));
		columns.add(new GridColumn("操作时间","operTime",200));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		gridConfig.setObj(obj);
		gridOper = new GridView(compOper.detail, SWT.NONE);
		gridOper.CreateTabel(gridConfig);
		gridOper.bindRefresh(obj, "queryThread");
		gridOper.bindWidgetSelected(obj, "queryLogDetail");
		return compOper;	
	}
	
	/**
	 * createDetail方法描述：构建日志明细面板
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private Composite createDetail(SashForm sashForm){
		BasicPanel compDetail = new BasicPanel(sashForm, SWT.BORDER);
		compDetail.setInput(false);
		compDetail.createPanel();
		//按钮权限
		compDetail.toolbar.setLayout(new RowLayout());
		List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		for(int i=0;i<rights.size();i++){
			if ("outputDetail".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compDetail.toolbar, rightBos);
		//日志明细表格
		compDetail.detail.setLayout(new FillLayout());
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("操作表","tableName",160));
		columns.add(new GridColumn("表名","tableDesc",180));
		columns.add(new GridColumn("字段","fieldName",120));
		columns.add(new GridColumn("字段名","fieldDesc",120));
		columns.add(new GridColumn("旧值","oldValue",220));
		columns.add(new GridColumn("新值","newValue",220));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		gridConfig.setObj(obj);
		gridDetail = new GridView(compDetail.detail, SWT.NONE);
		gridDetail.CreateTabel(gridConfig);
		gridDetail.bindMouseDoubleClick(obj,rights,"updateMethod");

		return compDetail;
	}
	
	/**
	 * queryMethod方法描述：查询方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午08:38:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void queryMethod(){
		SamOperLogFindUi findUi = new SamOperLogFindUi(this.getShell());
		findUi.setfModuleCode(fModuleCode);
		findUi.setfOperType(fOperType);
		findUi.setfUserCode(fUserCode);
		findUi.setfStatus(fStatus);
		findUi.setfStartDate(fStartDate);
		findUi.setfEndDate(fEndDate);
		findUi.open();
		if (findUi.getBtnId()==1){
			fModuleCode = findUi.getfModuleCode();
			fOperType = findUi.getfOperType();
			fUserCode = findUi.getfUserCode();
			fStatus = findUi.getfStatus();
			fStartDate = findUi.getfStartDate();
			fEndDate = findUi.getfEndDate();
			queryThread();
		}
	}
	
	public void queryThread(){
		ThreadWaiting waiting = new ThreadWaiting(this,"queryOperLog",new Class[]{},new String[]{});
		waiting.task();
	}
	
	public void queryOperLog(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					int start = gridOper.getStart();
					int limit = gridOper.getLimit();
					ISamLogOper iSamLogOper = new ImpSamLogOper();
					int count = iSamLogOper.queryCountByCustom(CommFinal.organize.getOrganizeSeq(),
							fModuleCode, fOperType, fStatus, fUserCode, fStartDate, fEndDate);
					if (count>0){
						List<SamLogOper> samLogOpers = iSamLogOper.queryPageByCustom(CommFinal.organize.getOrganizeSeq(),
								fModuleCode, fOperType, fStatus, fUserCode, fStartDate, fEndDate, start, limit);
						gridOper.setDataList(samLogOpers);
					}else{
						gridOper.removeAll();
					}
					gridOper.setTotalCount(count);
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void queryLogDetail(){
		try {
			int checkIndex[] = gridOper.getSelectionIndexs();
			if (checkIndex.length>0){
				SamLogOper samLogOper = (SamLogOper) gridOper.getSelection();
				ISamLogDetail iSamLogDetail = new ImpSamLogDetail();
				List<SamLogDetail> samLogDetails = iSamLogDetail.queryByOperSeq(samLogOper.getOperLogSeq());
				gridDetail.setDataList(samLogDetails);
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteMethod(){
		try {
			int checkIndex[] = gridOper.getSelectionIndexs();
			if (checkIndex.length>0){
				int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行数据吗？");
		    	if(isdel == SWT.YES){
		    		ISamLogOper iSamLogOper = new ImpSamLogOper();
					iSamLogOper.deleteByOperSeq((List<SamLogOper>)gridOper.getSelections(),CommFinal.initConfig());
		    		gridOper.deleteRow(checkIndex);
		    	}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void outputOper() throws UserBusinessException{
		gridOper.exportExcel();
	}
	
	public void outputDetail() throws UserBusinessException{
		gridDetail.exportExcel();
	}

}
