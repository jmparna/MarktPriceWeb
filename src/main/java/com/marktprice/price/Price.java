package com.marktprice.price;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Price {

	private Long id;
	private String name;
	private BigDecimal bid;
	private BigDecimal ask;
	private Timestamp priceData;
	
	public Price() {
		super();
	}
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBid() {
		return bid;
	}	
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}	
	public BigDecimal getAsk() {
		return ask;
	}	
	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}
	public Timestamp getPriceData() {
		return priceData;
	}
	public void setPriceData(Timestamp priceData) {
		this.priceData = priceData;
	}
}
