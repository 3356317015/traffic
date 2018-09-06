package com.zhima.widget.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * EditorOption概要说明：编辑对象选项
 * @author lcy
 */
@SuppressWarnings({ "rawtypes" })
public class EditorOption {
	/**
	 * 必填
	 */
	private boolean required = false;
	/**
	 * 是否配对
	 */
	private boolean isMatch = false;
	/**
	 * 是否编辑
	 */
	private boolean isEdit = false;
	/**
	 * 必须输入数字 Text用;
	 */
	private boolean isVerify = false;
	/**
	 * 下拉控件数据数组
	 */
	private String arrayData[] = null;
	/**
	 * 下拉表格(CDropBox)用
	 */
	private List<GridColumn> dropColumns;
	private List dropDatas = new ArrayList();
	private String dropValueCol;
	private String dropShowCol;
	

	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public boolean isMatch() {
		return isMatch;
	}

	public void setMatch(boolean isMatch) {
		this.isMatch = isMatch;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public boolean isVerify() {
		return isVerify;
	}

	public void setVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}

	public String[] getArrayData() {
		return arrayData;
	}

	public void setArrayData(String[] arrayData) {
		this.arrayData = arrayData;
	}

	public List<GridColumn> getDropColumns() {
		return dropColumns;
	}
	
	public void setDropColumns(List<GridColumn> dropColumns) {
		this.dropColumns = dropColumns;
	}
	
	public List getDropDatas() {
		return dropDatas;
	}
	
	public void setDropDatas(List dropDatas) {
		this.dropDatas = dropDatas;
	}
	
	public String getDropValueCol() {
		return dropValueCol;
	}
	
	public void setDropValueCol(String dropValueCol) {
		this.dropValueCol = dropValueCol;
	}
	
	public String getDropShowCol() {
		return dropShowCol;
	}
	
	public void setDropShowCol(String dropShowCol) {
		this.dropShowCol = dropShowCol;
	}
}
