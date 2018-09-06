package com.zhima.widget.stationBar;



public class Station {
	//站点序号
	private String stationSeq;
	//站点名称
	private String stationName;
	//里程
	private Double stationMileage;
	//站点顺序
	private int stationOrder;
	//是否可售,0表示禁售,1表示可售
	private int ifSale;
	//站点售票数量
	private int stationNum;
	//站点售票数
	private int saleNum;
	//票数
	private int ticketNum;
	//检票数
	private int checkNum;
	//票价序号
	private String linerfareSeq="";
	//基础价
	private Double baseFare=0.0;
	//站务费
	private Double stationFare=0.0;
	//燃油附加费
	private Double fuelFare=0.0;
	//其他费用1
	private Double otherOne=0.0;
	//其他费用2
	private Double otherTwo=0.0;
	//其他费用3
	private Double otherThree=0.0;
	//其他费用4
	private Double otherFour=0.0;
	//其他费用5
	private Double otherFive=0.0;
	//总价
	private Double fullFare=0.0;
	//发布票价
	private String ifRelease="";
	
	public Station(){
		
	}
	
	public String getStationSeq() {
		return stationSeq;
	}
	public void setStationSeq(String stationSeq) {
		this.stationSeq = stationSeq;
	}

	public Double getStationMileage() {
		return stationMileage;
	}

	public void setStationMileage(Double stationMileage) {
		this.stationMileage = stationMileage;
	}

	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public int getStationOrder() {
		return stationOrder;
	}
	public void setStationOrder(int stationOrder) {
		this.stationOrder = stationOrder;
	}
	public int getIfSale() {
		return ifSale;
	}
	public void setIfSale(int ifSale) {
		this.ifSale = ifSale;
	}
	public int getStationNum() {
		return stationNum;
	}
	public void setStationNum(int stationNum) {
		this.stationNum = stationNum;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}
	
	public String getLinerfareSeq() {
		return linerfareSeq;
	}

	public void setLinerfareSeq(String linerfareSeq) {
		this.linerfareSeq = linerfareSeq;
	}

	public Double getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(Double baseFare) {
		this.baseFare = baseFare;
	}

	public Double getStationFare() {
		return stationFare;
	}

	public void setStationFare(Double stationFare) {
		this.stationFare = stationFare;
	}

	public Double getFuelFare() {
		return fuelFare;
	}

	public void setFuelFare(Double fuelFare) {
		this.fuelFare = fuelFare;
	}

	public Double getOtherOne() {
		return otherOne;
	}

	public void setOtherOne(Double otherOne) {
		this.otherOne = otherOne;
	}

	public Double getOtherTwo() {
		return otherTwo;
	}

	public void setOtherTwo(Double otherTwo) {
		this.otherTwo = otherTwo;
	}

	public Double getOtherThree() {
		return otherThree;
	}

	public void setOtherThree(Double otherThree) {
		this.otherThree = otherThree;
	}

	public Double getOtherFour() {
		return otherFour;
	}

	public void setOtherFour(Double otherFour) {
		this.otherFour = otherFour;
	}

	public Double getOtherFive() {
		return otherFive;
	}

	public void setOtherFive(Double otherFive) {
		this.otherFive = otherFive;
	}

	public Double getFullFare() {
		return fullFare;
	}

	public void setFullFare(Double fullFare) {
		this.fullFare = fullFare;
	}

	public String getIfRelease() {
		return ifRelease;
	}

	public void setIfRelease(String ifRelease) {
		this.ifRelease = ifRelease;
	}
	
}
