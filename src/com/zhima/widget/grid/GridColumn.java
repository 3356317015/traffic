package com.zhima.widget.grid;

import java.util.LinkedHashMap;

/**
 * GridColumn概要说明：表格列头属性
 * @author lcy
 */
@SuppressWarnings({ "rawtypes" })
public class GridColumn {
	//列中文显示名称
	private String CName="";
	//列数据英文名称
	private String EName="";
	//列宽
	private int width=80;
	//对齐方式
	private String alignment ="left";
	//列颜色
	private int color = 0;
	//将对应数据转换为文本
	private String[] match;
	//存储键值对
	private LinkedHashMap map;
    //公式计算列"[trfaceFee]+[manageFee]"
    private String Formula="";
    //计算"SUM","COUNT","AVG"
    private String calcType ="";
    //编辑列ComBox,Text,DropBox,DateBox
    private String editType="";
    //编辑列数据
    private EditorOption editorOption=null;
    
    public GridColumn(){}
    
	public GridColumn(String CName, String EName, int width) {
		this.CName = CName;
		this.EName = EName;
		if (width > 0){
			this.width = width;
		}
	}
	public GridColumn(String CName, String EName, int width, int color) {
		this.CName = CName;
		this.EName = EName;
		this.color = color;
		if (width > 0){
			this.width = width;
		}
	}
	
	public GridColumn(String cName, String eName, int width, String editType,
			EditorOption editorOption) {
		super();
		CName = cName;
		EName = eName;
		this.width = width;
		this.editType = editType;
		this.editorOption = editorOption;
	}
	
	public GridColumn(String cName, String eName, int width, String editType,
			EditorOption editorOption, int color) {
		super();
		CName = cName;
		EName = eName;
		this.width = width;
		this.editType = editType;
		this.editorOption = editorOption;
		this.color = color;
	}

	public GridColumn(String CName, String EName, String[] match, int width) {
		this.CName = CName;
		this.EName = EName;
		this.match = match;
		if(width > 0){
			this.width = width;
		}
		convertMap();
	}
	
	public GridColumn(String CName, String EName, String[] match, int width, int color) {
		this.CName = CName;
		this.EName = EName;
		this.match = match;
		if(width > 0){
			this.width = width;
		}
		this.color = color;
		convertMap();
	}

	public GridColumn(String CName, String EName, String Formula){
		this.CName = CName;
		this.EName = EName;
		this.Formula = Formula;
	}
	
	public GridColumn(String CName, String EName, String Formula, int color){
		this.CName = CName;
		this.EName = EName;
		this.Formula = Formula;
		this.color = color;
	}

	public GridColumn(String cName, String eName, int width, String calcType) {
		super();
		CName = cName;
		EName = eName;
		this.width = width;
		this.calcType = calcType;
	}
	
	public GridColumn(String cName, String eName, int width, String calcType, int color) {
		super();
		CName = cName;
		EName = eName;
		this.width = width;
		this.calcType = calcType;
		this.color = color;
	}

	public String getCName() {
		return CName;
	}
	public void setCName(String cName) {
		CName = cName;
	}
	public String getEName() {
		return EName;
	}
	public void setEName(String eName) {
		EName = eName;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getAlignment() {
		return alignment;
	}
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public String[] getMatch() {
		return match;
	}
	public void setMatch(String[] match) {
		this.match = match;
	}
	public LinkedHashMap getMap() {
		return map;
	}
	public void setMap(LinkedHashMap map) {
		this.map = map;
	}
	public String getFormula() {
		return Formula;
	}
	public void setFormula(String formula) {
		Formula = formula;
	}
	public String getCalcType() {
		return calcType;
	}
	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}
	
	public String getEditType() {
		return editType;
	}

	public void setEditType(String editType) {
		this.editType = editType;
	}
	
	public EditorOption getEditorOption() {
		return editorOption;
	}
	public void setEditorOption(EditorOption editorOption) {
		this.editorOption = editorOption;
	}
	
	@SuppressWarnings("unchecked")
	public void convertMap(){
		map = new LinkedHashMap();
		if(match != null && match.length >0){
			for(int i=0;i<match.length;i++){
				String strText = match[i];
				String[] tmpArray = strText.split("-");
				map.put(tmpArray[0], tmpArray[1]);
			}
		}
	}
}
