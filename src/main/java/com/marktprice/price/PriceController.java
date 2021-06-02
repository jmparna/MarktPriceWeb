package com.marktprice.price;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marktprice.price.service.PriceService;

@RestController
@RequestMapping (path="market/v1/price")
public class PriceController {
	
	private final PriceService priceService;
	
	@Autowired
	public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}
	
	@GetMapping
	public List<Price> getLastPrice(){
		return priceService.getLastPrice();
	}
}
