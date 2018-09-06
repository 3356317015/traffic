package com.jasper.designer;

import com.zhima.basic.jdbc.FieldTag;


public class SaleTicketReport {
	/**
	 * 包含其他车站,网上售票,电话售票
	 */
	@FieldTag(cName = "本站全部售票数量", eName = "saleAllNum",field="saleAll_num")
	private Integer saleAllNum;
	
	@FieldTag(cName = "本站售本站售票数量", eName = "saleTicketOfLocalNum",field="saleTicketOfLocal_num")
	private Integer saleTicketOfLocalNum;
	
	@FieldTag(cName = "本站代售数量", eName = "saleTicketOfOtherNum",field="saleTicketOfOther_num")
	private Integer saleTicketOfOtherNum;
	
	@FieldTag(cName = "异站售本站数量", eName = "otherSaleLocalNum",field="otherSaleLocal_num")
	private Integer otherSaleLocalNum;
	
	@FieldTag(cName = "网上售本站数量", eName = "netSaleLocalNum",field="netSaleLocal_num")
	private Integer netSaleLocalNum;
	
	@FieldTag(cName = "电话售本站数量", eName = "telSaleLocalNum",field="telSaleLocal_num")
	private Integer telSaleLocalNum;
	
	@FieldTag(cName = "邮政售本站数量", eName = "postSaleLocalNum",field="postSaleLocal_num")
	private Integer postSaleLocalNum;

	public Integer getSaleAllNum() {
		return saleAllNum;
	}

	public void setSaleAllNum(Integer saleAllNum) {
		this.saleAllNum = saleAllNum;
	}

	public Integer getSaleTicketOfLocalNum() {
		return saleTicketOfLocalNum;
	}

	public void setSaleTicketOfLocalNum(Integer saleTicketOfLocalNum) {
		this.saleTicketOfLocalNum = saleTicketOfLocalNum;
	}

	public Integer getSaleTicketOfOtherNum() {
		return saleTicketOfOtherNum;
	}

	public void setSaleTicketOfOtherNum(Integer saleTicketOfOtherNum) {
		this.saleTicketOfOtherNum = saleTicketOfOtherNum;
	}

	public Integer getOtherSaleLocalNum() {
		return otherSaleLocalNum;
	}

	public void setOtherSaleLocalNum(Integer otherSaleLocalNum) {
		this.otherSaleLocalNum = otherSaleLocalNum;
	}

	public Integer getNetSaleLocalNum() {
		return netSaleLocalNum;
	}

	public void setNetSaleLocalNum(Integer netSaleLocalNum) {
		this.netSaleLocalNum = netSaleLocalNum;
	}

	public Integer getTelSaleLocalNum() {
		return telSaleLocalNum;
	}

	public void setTelSaleLocalNum(Integer telSaleLocalNum) {
		this.telSaleLocalNum = telSaleLocalNum;
	}

	public Integer getPostSaleLocalNum() {
		return postSaleLocalNum;
	}

	public void setPostSaleLocalNum(Integer postSaleLocalNum) {
		this.postSaleLocalNum = postSaleLocalNum;
	}
	
	
}
