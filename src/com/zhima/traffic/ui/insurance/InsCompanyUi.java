/**
 *******************************************************************************
 *
 * (c) Copyright 2012 重庆市志玛信息技术有限公司
 *
 * 系统名称：frameWork
 * 文  件  名 ：EpdCompanyUi.java
 * 模块名称：(请更改成该模块名称)
 * 创  建  人 ：鲁承毅
 * 创建日期：2013-3-2 下午10:36:15
 * 修  改  人 ：(修改了该文件，请填上修改人的名字)
 * 修改日期：(请填上修改该文件时的日期)
 * 版         本 ： V1.0.0
 *******************************************************************************  
 */

package com.zhima.traffic.ui.insurance;

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
import com.zhima.traffic.action.insurance.IInsCompany;
import com.zhima.traffic.action.insurance.impl.ImpInsCompany;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.InsCompany;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.ThreadWaiting;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * InsCompanyUi概要说明：公司设置
 * @author lcy
 */
public class InsCompanyUi extends Composite {
	private Object obj;
	private BasicPanel panel;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridView;
	//查询相关
	private String fInsuranceCode = "";
	private String fInsuranceName = "";
	private String fCompanyType = "";
	private String fIndustry = "";
	/**
	 * 
	 * 构造函数: 城市区域类
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InsCompanyUi(Composite parent, int style, List list) {
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
		columns.add(new GridColumn("保险代码","insuranceCode",160));
		columns.add(new GridColumn("保险名称","insuranceName",160));
		columns.add(new GridColumn("销售次序","salesOrder",100));
		columns.add(new GridColumn("公司状态","companyStatus",TraffFinal.ARR_COMPANY_STATUS,100));
		columns.add(new GridColumn("公司名称","companyName",300));
		columns.add(new GridColumn("单位性质","companyType",100));
		columns.add(new GridColumn("行业分类","industry",100));
		columns.add(new GridColumn("公司地址","address",300));
		columns.add(new GridColumn("邮编","postalCode",100));
		columns.add(new GridColumn("联系人","contacts",100));
		columns.add(new GridColumn("电话","telephone",120));
		columns.add(new GridColumn("传真","faxNumber",120));
		columns.add(new GridColumn("邮箱","email",200));
		columns.add(new GridColumn("工商注册号","regNum",160));
		columns.add(new GridColumn("税务号","taxNum",160));
		columns.add(new GridColumn("备注","remark",300));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rights);
		gridConfig.setObj(obj);
		gridView = new GridView(panel.detail, SWT.NONE);
		gridView.CreateTabel(gridConfig);
		gridView.bindMouseDoubleClick(obj,rights,"updateMethod");
		gridView.bindRefresh(obj, "queryCompany");
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
		InsCompanyFindUi findUi = new InsCompanyFindUi(this.getShell());
		findUi.setfInsuranceCode(fInsuranceCode);
		findUi.setfInsuranceName(fInsuranceName);
		findUi.setfCompanyType(fCompanyType);
		findUi.setfIndustry(fIndustry);
		findUi.open();
		if (findUi.getBtnId()==1){
			fInsuranceCode = findUi.getfInsuranceCode();
			fInsuranceName = findUi.getfInsuranceName();
			fCompanyType = findUi.getfCompanyType();
			fIndustry = findUi.getfIndustry();
			ThreadWaiting waiting = new ThreadWaiting(this,"queryCompany",new Class[]{},new String[]{});
			waiting.task();
		}
	}
	
	public void queryCompany(){
		try {
			for (int i = 0; i < rights.size(); i++) {
				if (rights.get(i).getRightMethod().equals("queryMethod")){
					int start = gridView.getStart();
					int limit = gridView.getLimit();
					IInsCompany iInsCompany = new ImpInsCompany();
					int count = iInsCompany.queryCountByCustom(CommFinal.organize.getOrganizeSeq(),
							fInsuranceCode, fInsuranceName, fCompanyType, fIndustry);
					if (count>0){
						List<InsCompany> companies = iInsCompany.queryPageByCustom(CommFinal.organize.getOrganizeSeq(),
								fInsuranceCode, fInsuranceName, fCompanyType, fIndustry, start, limit);
						gridView.setDataList(companies);
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
		InsCompanyEditUi editUi = new InsCompanyEditUi(this.getShell(),gridView,CommFinal.OPER_TYPE_ADD);
		editUi.open();
	}
	
	public void updateMethod(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridView.getSelection()){
					InsCompanyEditUi editUi = new InsCompanyEditUi(this.getShell(), gridView, CommFinal.OPER_TYPE_UPDATE);
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
		try{
			int checkIndex[] = gridView.getSelectionIndexs();
			if (checkIndex.length>0){
				int isdel =  MsgBox.confirm(getShell(),"确定要删除选中行数据吗？");
		    	if(isdel == SWT.YES){
		    		IInsCompany iInsCompany = new ImpInsCompany();
		    		iInsCompany.delete((List<InsCompany>) gridView.getSelections(),CommFinal.initConfig());
		    		gridView.deleteRow(checkIndex);
		    	}
			} 
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void outputMethod() throws UserBusinessException{
		gridView.exportExcel();
	}

}
