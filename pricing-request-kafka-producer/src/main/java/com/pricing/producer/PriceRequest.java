package com.pricing.producer;

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

	@Override
	public String toString() {
		StringBuilder reqString = new StringBuilder();
		reqString.append("{").append("\"priceid\":"+ this.priceid).append(",\"requestPrice\":"+ this.requestPrice)
				.append(",\"desc\":"+"\""+this.desc+"\"").append("}");
		return reqString.toString();
	}
}
