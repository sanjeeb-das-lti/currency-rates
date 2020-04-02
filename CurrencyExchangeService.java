package com.exchangeRate.exchangeRate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyExchangeService {

	@Autowired
	private RestTemplate restTemplate;

	public String getCurrentRateService() {

		String conversionCurrency = "GBP,USD,HKD";
		ResponseEntity<String> response = null;
		try {
			// https://api.ratesapi.io/api/latest?symbols=USD,GBP
			String resultApi = "https://api.ratesapi.io/api/latest?symbols=" + conversionCurrency;

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			response = restTemplate.exchange(resultApi, HttpMethod.GET, entity, String.class);

		} catch (Exception e) {
			return e.getMessage();
		}
		return response.getBody();
	}

	public List<String> getPastRateService() {
		String conversionCurrency = "GBP,USD,HKD";
		String resultApi = "";

		Calendar now = Calendar.getInstance();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		List<String> responseEntityList = new ArrayList<>();

		for (int i = 1; i <= 6; i++) {
			now = Calendar.getInstance();
			now.add(Calendar.MONTH, -i);
			String modDate = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
					+ now.get(Calendar.DATE);

			resultApi = "https://api.ratesapi.io/api/" + modDate + "?symbols=" + conversionCurrency;
			ResponseEntity<String> response = restTemplate.exchange(resultApi, HttpMethod.GET, entity, String.class);
			responseEntityList.add(response.getBody());
		}

		return responseEntityList;
	}
}
