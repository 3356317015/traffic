/**
 *******************************************************************************
 *
 * (c) Copyright 2012 重庆市志玛信息技术有限公司
 *
 * 系统名称：frameWork
 * 文  件  名 ：abcd.java
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
import com.zhima.frame.model.SamModuleRight;
import com.zhima.traffic.action.epd.IEpdContract;
import com.zhima.traffic.action.epd.IEpdContractdetail;
import com.zhima.traffic.action.epd.impl.ImpEpdContract;
import com.zhima.traffic.action.epd.impl.ImpEpdContractdetail;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdContract;
import com.zhima.traffic.model.EpdContractdetail;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.BasicPanel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

/**
 * SamGroupUi概要说明：组权限设置
 * @author lcy
 */
public class EpdContractUi extends Composite {
	private Object obj;
	//功能权限
	private List<SamModuleRight> rights;
	//权限
	private GridView gridContract;
	private GridView gridDetail;
	
	/**
	 * 构造函数: 构造组权限设置类
	 * @param parent
	 * @param style
	 * @param list 功能按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EpdContractUi(Composite parent, int style, List list) {
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
        createContract(sashForm);
        createDetail(sashForm);
        sashForm.setWeights(new int[] {35,65});
        queryContract();
	}

	/**
	 * createGroup方法描述：创建组面板
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 上午12:05:34
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sashForm
	 * @return Composite
	 */
	public Composite createContract(SashForm sashForm){
		BasicPanel compOper = new BasicPanel(sashForm, SWT.BORDER);
		compOper.setShowBack(false);
		compOper.setInput(false);
		compOper.createPanel();
		//按钮权限
		compOper.toolbar.setLayout(new RowLayout());
		List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		for(int i=0;i<rights.size();i++){
			if ("queryContract".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("addContract".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("updateContract".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("deleteContract".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compOper.toolbar, rightBos);
		
		compOper.detail.setLayout(new FillLayout());
		
		gridContract = new GridView(compOper.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("合同代码","contractCode",110));
		columns.add(new GridColumn("合同名称","contractName",110));
		columns.add(new GridColumn("备注","remark",300));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setVirtual(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		gridConfig.setObj(obj);
		gridConfig.setGridName("tbGroup");
		gridContract.CreateTabel(gridConfig);
		gridContract.bindMouseDoubleClick(obj, rights, "updateContract");
		gridContract.bindWidgetSelected(obj, "queryDetail");
		return compOper;
	}
	
	/**
	 * createModule方法描述：创建功能面板
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午03:20:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sashForm
	 * @return Composite
	 */
	public Composite createDetail(SashForm sashForm){
		BasicPanel compMain=new BasicPanel(sashForm,SWT.BORDER);
		compMain.setInput(false);
		compMain.createPanel();
		//按钮权限
		compMain.toolbar.setLayout(new RowLayout());
		final List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
		for(int i=0;i<rights.size();i++){
			if ("queryDetail".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("addDetail".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("updateDetail".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
			if ("deleteDetail".equals(rights.get(i).getRightMethod())){
				rightBos.add(rights.get(i));
			}
		}
		CallMethod callMethod = new CallMethod();
		callMethod.createBtnRight(obj, compMain.toolbar, rightBos);
		compMain.detail.setLayout(new FillLayout());
		gridDetail = new GridView(compMain.detail, SWT.NONE);
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("合同项目","itemCode",110));
		columns.add(new GridColumn("归属方","itemBelong",TraffFinal.ARR_ITEM_BELONG,80));
		columns.add(new GridColumn("收费公式","formulaDesc",300));
		columns.add(new GridColumn("取整单位","roundUnit",TraffFinal.ARR_ROUND_UNIT,100));
		columns.add(new GridColumn("进位方式","carryMode",TraffFinal.ARR_CARRY_MODE,100));
		columns.add(new GridColumn("备注","remark",300));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setVirtual(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridConfig.setRightBos(rightBos);
		gridConfig.setObj(obj);
		gridConfig.setGridName("tbGroup");
		gridDetail.CreateTabel(gridConfig);
		gridDetail.bindMouseDoubleClick(obj, rights, "updateDetail");
		return compMain;	
	}
	
	public void queryContract(){
		try {
			IEpdContract iEpdContract = new ImpEpdContract();
			List<EpdContract> epdContracts = iEpdContract.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			gridContract.setDataList(epdContracts);
			gridDetail.setDataList(null);
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void addContract(){
		EpdContractEditUi editUi = new EpdContractEditUi(this.getShell(), gridContract, CommFinal.OPER_TYPE_ADD);
		editUi.open();
	}

	public void updateContract(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridContract.getSelection()){
					EpdContractEditUi editUi = new EpdContractEditUi(this.getShell(), gridContract, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的行。");
				}
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteContract(){
		try {
			int[] checkindexs = gridContract.getSelectionIndexs();
			List<EpdContract> epdContracts = (List<EpdContract>)gridContract.getSelections();

			if(null!=epdContracts && epdContracts.size()>0){
    			int isdel =  MsgBox.confirm(getShell(),"你确定要删除此项数据吗？");
    	    	if(isdel == SWT.YES){
    				IEpdContract iEpdContract = new ImpEpdContract();
    	    		iEpdContract.delete(epdContracts,CommFinal.initConfig());
    	    		gridContract.deleteRow(checkindexs);
    	    	}
    		}else{
    			MsgBox.warning(this.getShell(),"请选中删除的行！");
    			return;
    		}
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void queryDetail(){
		try {
			if (null != gridContract.getDataList() && null != gridContract.getSelection()){
				EpdContract epdContract = (EpdContract) gridContract.getSelection();
				IEpdContractdetail iEpdContractdetail = new ImpEpdContractdetail();
				List<EpdContractdetail> contractdetails = iEpdContractdetail.queryByContractSeq(epdContract.getContractSeq());
				gridDetail.setDataList(contractdetails);
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	public void addDetail(){
		if(null!=gridContract.getSelection()){
			EpdContract epdContract = (EpdContract) gridContract.getSelection();
			EpdContractdetailEditUi editUi = new EpdContractdetailEditUi(this.getShell(), epdContract, gridDetail, CommFinal.OPER_TYPE_ADD);
			editUi.open();
		}else{
			MsgBox.warning(getShell(), "请选择合同添加。");
		}
	}

	public void updateDetail(){
		String thisMethodName = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
		for (int i = 0; i < rights.size(); i++) {
			if (thisMethodName.equals(rights.get(i).getRightMethod())){
				if(null!=gridContract.getSelection()){
					EpdContract epdContract = (EpdContract) gridContract.getSelection();
					EpdContractdetailEditUi editUi = new EpdContractdetailEditUi(this.getShell(), epdContract, gridDetail, CommFinal.OPER_TYPE_UPDATE);
					editUi.open();
				}else{
					MsgBox.warning(getShell(), "请选择修改的行。");
				}
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteDetail(){
		try {
			int[] checkindexs = gridDetail.getSelectionIndexs();
			List<EpdContractdetail> epdContractdetails = (List<EpdContractdetail>)gridDetail.getSelections();

			if(null!=epdContractdetails && epdContractdetails.size()>0){
    			int isdel =  MsgBox.confirm(getShell(),"你确定要删除此项数据吗？");
    	    	if(isdel == SWT.YES){
    				IEpdContractdetail iEpdContractdetail = new ImpEpdContractdetail();
    	    		for(int i=0;i<checkindexs.length;i++){
    	    			iEpdContractdetail.delete(epdContractdetails,CommFinal.initConfig());
    	    		}
    	    		gridDetail.deleteRow(checkindexs);
    	    	}
    		}else{
    			MsgBox.warning(this.getShell(),"请选择删除的行！");
    			return;
    		}
		}catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
}
