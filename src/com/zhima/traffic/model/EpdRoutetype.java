package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName = "线路类型", eName = "EpdRoutetype", tabName = "epd_routetype")
public class EpdRoutetype implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName = "线路类型序号", eName = "routetypeSeq", field = "routetype_seq", pk = true)
	private String routetypeSeq = "";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";

	@FieldTag(cName = "线路类型名称", eName = "routetypeName", field = "routetype_name")
	private String routetypeName = "";

	@FieldTag(cName = "线路类型代码", eName = "routetypeCode", field = "routetype_code")
	private String routetypeCode = "";

	@FieldTag(cName = "备注", eName = "remark", field = "remark")
	private String remark = "";

	@FieldTag(cName = "修改时间", eName = "updateTime", field = "update_time")
	private String updateTime = "";

	// Constructors

	/** default constructor */
	public EpdRoutetype() {
	}

	/** full constructor */

	// Property accessors

	public String getRoutetypeSeq() {
		return routetypeSeq;
	}

	public void setRoutetypeSeq(String routetypeSeq) {
		this.routetypeSeq = routetypeSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getRoutetypeName() {
		return routetypeName;
	}

	public void setRoutetypeName(String routetypeName) {
		this.routetypeName = routetypeName;
	}

	public String getRoutetypeCode() {
		return routetypeCode;
	}

	public void setRoutetypeCode(String routetypeCode) {
		this.routetypeCode = routetypeCode;
	}

	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}