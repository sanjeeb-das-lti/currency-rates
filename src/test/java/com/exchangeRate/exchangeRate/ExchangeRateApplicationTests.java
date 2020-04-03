package com.exchangeRate.exchangeRate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ExchangeRateApplicationTests {

	@InjectMocks
	private CurrencyExchangeService exchangeService;
	
	@Mock
	private RestTemplate restTemplate;


	@Test
	public void getCurrentRateServiceTest() {
		String mockResponse = "mock response";
		
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.<HttpMethod> any(), Mockito.<HttpEntity<?>> any(), Mockito.<Class<?>> any())).thenReturn(new ResponseEntity(mockResponse, HttpStatus.OK));
		
		String value = exchangeService.getCurrentRateService();
		System.out.println(value);
		assertNotNull(value);
	}
	
	@Test
	public void getAllEmployeesTestException() {
		String mockResponse = "mock response";
		List<String> respList = new ArrayList<>();
		respList.add(mockResponse);
		
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.<HttpMethod> any(), Mockito.<HttpEntity<?>> any(), Mockito.<Class<?>> any()))
		.thenReturn(new ResponseEntity(mockResponse, HttpStatus.OK));
		
		List<String> listResponse= exchangeService.getPastRateService();
		assertNotNull(listResponse);
	}
}
