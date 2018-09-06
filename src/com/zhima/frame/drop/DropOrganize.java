package com.zhima.frame.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamOrganize;
import com.zhima.frame.action.sam.ISamService;
import com.zhima.frame.action.sam.impl.ImpSamOrganize;
import com.zhima.frame.action.sam.impl.ImpSamService;
import com.zhima.frame.model.SamOrganize;
import com.zhima.frame.model.SamService;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

/**
 * CButton概要说明：自定义用户选择
 * @author lcy
 */
public class DropOrganize extends Composite {
	public CDropBox droptext;

	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 * @throws UserBusinessException 
	 */
	public DropOrganize(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());

		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("客运站代码", "organizeCode",120));
		columns.add(new GridColumn("客运站名称", "organizeName", 200));
		columns.add(new GridColumn("销售点", "ifService",TraffFinal.ARR_IF_SERVICE, 70));
		droptext = new CDropBox(this, columns, null, "organizeSeq", "organizeName", SWT.NONE);
		droptext.create();
	}
	
	/**
	 * initType方法描述：根据类型加载客运站
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2018-5-10 下午05:49:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param type 1=除合作站外；2=合作站；3=车站+服务点
	 * @return void
	 */
	public void initType(String type) {
		try {
			ISamOrganize iSamOrganize = new ImpSamOrganize();
			List<SamOrganize> samOrganizes = iSamOrganize.queryByAll();
			if ("1".equals(type)){
				//移除合作站
				for (int i = samOrganizes.size()-1; i >= 0; i--) {
					if ("4".equals(samOrganizes.get(i).getOrganizeType())){
						samOrganizes.remove(i);
					}
				}
			}else if ("2".equals(type)){
				//保留合作站
				for (int i = samOrganizes.size()-1; i >= 0; i--) {
					if (!"4".equals(samOrganizes.get(i).getOrganizeType())){
						samOrganizes.remove(i);
					}
				}
			}else if("3".equals(type)){
				//移除除登录站
				for (int i = samOrganizes.size()-1; i >= 0; i--) {
					if (!CommFinal.organize.getOrganizeSeq().equals(samOrganizes.get(i).getOrganizeSeq())){
						samOrganizes.remove(i);
					}
				}
				//添加销售点
				ISamService iSamService = new ImpSamService();
				List<SamService> samServices = iSamService.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						SamOrganize samOrganize = new SamOrganize();
						samOrganize.setOrganizeSeq(samServices.get(i).getServiceSeq());
						samOrganize.setOrganizeCode(samServices.get(i).getServiceCode());
						samOrganize.setOrganizeSpell(samServices.get(i).getServiceSpell());
						samOrganize.setOrganizeName(samServices.get(i).getServiceName());
						samOrganize.setAddress(samServices.get(i).getAddress());
						samOrganize.setEmail(samServices.get(i).getEmail());
						samOrganize.setFaxNumber(samServices.get(i).getFaxNumber());
						samOrganize.setTelephone(samServices.get(i).getTelephone());
						samOrganize.setRemark(samServices.get(i).getRemark());
						samOrganize.setStatus(samServices.get(i).getStatus());
						samOrganize.setIfService("1");
						samOrganizes.add(samOrganize);
					}
				}
			}else if("4".equals(type)){
				//移除除登录站
				for (int i = samOrganizes.size()-1; i >= 0; i--) {
					if (!CommFinal.organize.getOrganizeSeq().equals(samOrganizes.get(i).getOrganizeSeq())){
						samOrganizes.remove(i);
					}
				}
				//添加异站
				ISamService iSamService = new ImpSamService();
				List<SamService> samServices = iSamService.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
				if (null != samServices && samServices.size()>0){
					for (int i = 0; i < samServices.size(); i++) {
						if ("3".equals(samServices.get(i).getSaleType())){
							SamOrganize samOrganize = new SamOrganize();
							samOrganize.setOrganizeSeq(samServices.get(i).getServiceSeq());
							samOrganize.setOrganizeCode(samServices.get(i).getServiceCode());
							samOrganize.setOrganizeSpell(samServices.get(i).getServiceSpell());
							samOrganize.setOrganizeName(samServices.get(i).getServiceName());
							samOrganize.setAddress(samServices.get(i).getAddress());
							samOrganize.setEmail(samServices.get(i).getEmail());
							samOrganize.setFaxNumber(samServices.get(i).getFaxNumber());
							samOrganize.setTelephone(samServices.get(i).getTelephone());
							samOrganize.setRemark(samServices.get(i).getRemark());
							samOrganize.setStatus(samServices.get(i).getStatus());
							samOrganize.setIfService("1");
							samOrganizes.add(samOrganize);
						}
					}
				}
			}
			droptext.setDataList(samOrganizes);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			LogUtil.operLog(e,"E",true,true);
		}
	}

}
