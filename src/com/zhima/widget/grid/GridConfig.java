package com.zhima.widget.grid;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamUserColumn;
import com.zhima.frame.action.sam.impl.ImpSamUserColumn;
import com.zhima.frame.model.SamModuleRight;

@SuppressWarnings("rawtypes")
public class GridConfig {
	/**
	 * 是否大数据表格（false/否;true/是）
	 */
	private boolean virtual = false;
	
	/**
	 *是否有行序号（false/无;true/有）
	 */
	private boolean showSeq = true;
	
	/**
	 * 是否有复选框（false/无;true/有）
	 */
	private boolean check = true;
	private boolean isAll = true;
	
	/**
	 * 是否允许多选（false/不允许;true/允许）
	 */
	private boolean multi = true;
	
	/**
	 * 是否显示网格（false/不显示;true/显示）
	 */
	private boolean showLines = true;
	
	/**
	 * 是否显示表头（false/不显示;true/显示）
	 */
	private boolean showHeader = true;
	/**
	 * 设置表格颜色
	 */
	private boolean showColor = false;
	/**
	 * 颜色类型(background背景色;foreground/前景色)
	 */
	private String colorType ="foreground";
	/**
	 * 鼠标点击列头是否排序
	 */
	private boolean isSort = true;
	
	/**
	 * 是否分页显示（false/不显示;true/显示）
	 */
	private boolean showPage = true;
	
	/**
	 * 是否显示合计行（false/不显示;true/显示）
	 */
	private boolean showSum = false;
	/**
	 * 分页起始行
	 */
	private int start = 0;
	
	/**
	 * 分页结束行
	 */
	private int limit = 100;
	
	/**
	 * 表格总记录数 
	 */
	private int totalCount = 0;
	
	/**
	 * 表格标准列头
	 */
	private List<GridColumn> basecols = new ArrayList<GridColumn>();
	
	/**
	 * 列头定义规范
	 */
	private List<GridColumn> columns = new ArrayList<GridColumn>();
	
	/**
	 * 表格显示数据集合
	 */
	private List datas = new ArrayList();
	
	/**
	 * 表格菜单权限
	 */
	private List<SamModuleRight> rightBos = new ArrayList<SamModuleRight>();
	/**
	 * 与权限关联使用，权限所属类
	 */
	private Object obj;
	/**
	 * 表格名称
	 */
	private String gridName ="";
	
	/**
	 * isVirtual方法描述：
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-21 下午02:03:41
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return boolean
	 */
	
	public boolean isVirtual() {
		return virtual;
	}

	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}

	public boolean isShowSeq() {
		return showSeq;
	}

	public void setShowSeq(boolean showSeq) {
		this.showSeq = showSeq;
	}

	public boolean isShowColor() {
		return showColor;
	}

	public void setShowColor(boolean showColor) {
		this.showColor = showColor;
	}

	public String getColorType() {
		return colorType;
	}

	public void setColorType(String colorType) {
		this.colorType = colorType;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isAll() {
		return isAll;
	}

	public void setAll(boolean isAll) {
		this.isAll = isAll;
	}

	public boolean isMulti() {
		return multi;
	}

	public void setMulti(boolean multi) {
		this.multi = multi;
	}

	public boolean isShowLines() {
		return showLines;
	}

	public void setShowLines(boolean showLines) {
		this.showLines = showLines;
	}

	public boolean isShowHeader() {
		return showHeader;
	}

	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}

	public boolean isSort() {
		return isSort;
	}

	public void setSort(boolean isSort) {
		this.isSort = isSort;
	}

	public boolean isShowPage() {
		return showPage;
	}

	public void setShowPage(boolean showPage) {
		this.showPage = showPage;
	}

	public boolean isShowSum() {
		return showSum;
	}

	public void setShowSum(boolean showSum) {
		this.showSum = showSum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	

	public List<GridColumn> getBasecols() {
		return basecols;
	}

	public void setBasecols(List<GridColumn> basecols) {
		this.basecols = basecols;
	}

	public List<GridColumn> getColumns() {
		if (null != gridName && !"".equals(gridName)){
			try {
				ISamUserColumn iSamUserColumn = new ImpSamUserColumn();
				columns = iSamUserColumn.queryByGridColumn(basecols, obj.getClass().getName(), gridName,
						CommFinal.user.getUserSeq());
			} catch (UserBusinessException e) {
				e.printStackTrace();
			}
		}
		return columns;
	}

	public void setColumns(List<GridColumn> columns) {
		try {
			this.basecols = columns;
			if (null != columns && columns.size()>0){
				for (int i = 0; i < columns.size(); i++) {
					GridColumn gridColumn = new GridColumn();
					BeanUtils.copyProperties(gridColumn, columns.get(i));
					this.columns.add(gridColumn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List getDatas() {
		return datas;
	}

	public void setDatas(List datas) {
		if (null == datas){
			this.datas = new ArrayList();
		}else{
			this.datas = datas;
		}
	}

	public List<SamModuleRight> getRightBos() {
		return rightBos;
	}

	public void setRightBos(List<SamModuleRight> rightBos) {
		this.rightBos = rightBos;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getGridName() {
		return gridName;
	}

	public void setGridName(String gridName) {
		this.gridName = gridName;
	}
	
}
