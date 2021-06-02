package com.marktprice.price.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marktprice.price.Price;

@Service
public interface PriceService {

	public List<Price> getLastPrice();

}
