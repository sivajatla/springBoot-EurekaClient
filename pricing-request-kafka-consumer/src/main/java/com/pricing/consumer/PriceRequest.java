package com.pricing.consumer;

public class PriceRequest {
	private int priceid;
	private int requestPrice;
	private String desc;
	public PriceRequest() {
	}
	public PriceRequest(int priceid, int requestPrice, String desc) {
		super();
		this.priceid = priceid;
		this.requestPrice = requestPrice;
		this.desc = desc;
	}
	public int getPriceid() {
		return priceid;
	}
	public void setPriceid(int priceid) {
		this.priceid = priceid;
	}
	public int getRequestPrice() {
		return requestPrice;
	}
	public void setRequestPrice(int requestPrice) {
		this.requestPrice = requestPrice;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
