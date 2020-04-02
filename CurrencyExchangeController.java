package com.exchangeRate.exchangeRate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value = "currency exchange")
public class CurrencyExchangeController {

	@Autowired
	private CurrencyExchangeService exchangeService;

	@ApiOperation(value = "To check today’s exchange rates for UK, USA and HK against Euro. ",response = String.class)
	@GetMapping("/api/currentRate")
	public String getCurrentRate() {

		return exchangeService.getCurrentRateService();

	}
	@ApiOperation(value = "Get historical exchange rates for UK, USA and HK against Euro for the past six months for the same day. ",response = String.class)
	@GetMapping("/api/pastRate")
	public List<String> getPastRate() {
		return exchangeService.getPastRateService();
	}

}
