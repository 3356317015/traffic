package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamVariables;
import com.zhima.frame.action.sam.impl.ImpSamVariables;
import com.zhima.frame.model.SamVariables;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

/**
 * CButton概要说明：自定义用户选择
 * @author lcy
 */
public class DropVariables extends Composite {
	public CDropBox droptext;
	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 */
	public DropVariables(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("变量代码", "variableCode",150));
		columns.add(new GridColumn("变量名称", "variableName", 180));
		droptext = new CDropBox(this, columns, null, "variableCode", "variableName", SWT.NONE);
		droptext.create();
		
	}
	
	public void init(String variableType){
		try {
			ISamVariables iSamVariables = new ImpSamVariables();
			if (null != variableType && variableType.length()>0){
				List<SamVariables> samVariables = iSamVariables.queryByVariableType(CommFinal.organize.getOrganizeSeq(),variableType.substring(0,1));
				if("1all".equals(variableType)){
					List<SamVariables> variables = iSamVariables.queryByVariableType(CommFinal.organize.getOrganizeSeq(),"0");
					if (null != variables && variables.size()>0){
						for (int i = variables.size(); i >= 0; i--) {
							samVariables.add(0, variables.get(i));
						}
					}
				}else if("2all".equals(variableType)){
					List<SamVariables> variables = iSamVariables.queryByVariableType(CommFinal.organize.getOrganizeSeq(),"0");
					if (null != variables && variables.size()>0){
						for (int i = variables.size()-1; i >= 0; i--) {
							samVariables.add(0, variables.get(i));
						}
					}
					variables = iSamVariables.queryByVariableType(CommFinal.organize.getOrganizeSeq(),"1");
					if (null != variables && variables.size()>0){
						for (int i = variables.size()-1; i >= 0; i--) {
							samVariables.add(0, variables.get(i));
						}
					}
				}else if ("fun".equals(variableType)){
					List<SamVariables> variables = iSamVariables.queryByVariableType(CommFinal.organize.getOrganizeSeq(),"fun");
					if (null != variables && variables.size()>0){
						for (int i = 0; i < variables.size(); i++) {
							samVariables.add(variables.get(i));
						}
					}
				}
				droptext.setDataList(samVariables);
			}else{
				droptext.setDataList(iSamVariables.queryByAll());
			}
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}

	
}
