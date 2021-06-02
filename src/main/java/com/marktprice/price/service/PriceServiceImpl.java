package com.marktprice.price.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.marktprice.price.Price;

@Service
public class PriceServiceImpl implements PriceService{
	
	private final String EUR_USD = "EUR/USD";
	private final String GBP_USD = "GBP/USD";
	private final String EUR_JPY = "EUR/JPY";
	
	public List<Price> getLastPrice() {
		String message = onMessage();
		String[] lineas = message.split("\n");
		Price priceEurUsdPrice=null;
		Price priceGbpUsdPrice=null;
		Price priceEurJpyPrice=null;
		
		for(int i=0;i<lineas.length;i++) {
			String[] args = lineas[i].split(",");
			switch(args[1].trim()) {
			 case EUR_USD:
				if(priceEurUsdPrice==null) {
					priceEurUsdPrice = new Price();
					setPriceValues(args,priceEurUsdPrice);
				}else if(priceEurUsdPrice.getPriceData().before(stringToTimestamp(args[4]))) {
					setPriceValues(args,priceEurUsdPrice);
				}
				break;
			 case GBP_USD:
				if(priceGbpUsdPrice==null) {
					priceGbpUsdPrice = new Price();
					setPriceValues(args,priceGbpUsdPrice);
				}else if(priceGbpUsdPrice.getPriceData().before(stringToTimestamp(args[4]))) {
					setPriceValues(args,priceGbpUsdPrice);
				}
				break;
			 case EUR_JPY:
				if(priceEurJpyPrice==null) {
					priceEurJpyPrice = new Price();
					setPriceValues(args,priceEurJpyPrice);
				}else if(priceEurJpyPrice.getPriceData().before(stringToTimestamp(args[4]))) {
					setPriceValues(args,priceEurJpyPrice);
				}
				break;
			}
		}
		return List.of(priceEurUsdPrice, priceGbpUsdPrice, priceEurJpyPrice);
	}

	private String onMessage() {
		String message = "106,EUR/USD,1.1000,1.2000,01-06-2020 12:01:01:001\n"
				+ "107,EUR/JPY,119.60,119.90,01-06-2020 12:01:02:002\n"
				+ "108,GBP/USD,1.2500,1.2560,01-06-2020 12:01:02:002\n"
				+ "109,GBP/USD,1.2499,1.2561,01-06-2020 12:01:02:100\n"
				+ "110,EUR/JPY,119.61,119.91,01-06-2020 12:01:02:110";
		return message;
	}

	private void setPriceValues(String[] args, Price price) {
		price.setId(Long.parseLong(args[0]));
		price.setName(args[1]);
		BigDecimal bid = new BigDecimal(args[2]);
		bid = bid.subtract(bid.multiply(new BigDecimal("0.1")));
		price.setBid(bid);
		BigDecimal ask = new BigDecimal(args[3]);
		ask = ask.add(bid.multiply(new BigDecimal("0.1")));
		price.setBid(bid);
		price.setAsk(ask);
		price.setPriceData(stringToTimestamp(args[4]));
	}
	
	private Timestamp stringToTimestamp(String date) {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(date));
		return Timestamp.valueOf(localDateTime);
	}

}
