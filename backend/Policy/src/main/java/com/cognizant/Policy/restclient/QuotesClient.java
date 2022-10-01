package com.cognizant.Policy.restclient;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Headers;

@Headers("Content-Type: application/json")
//@FeignClient(name = "emp-ws", url = "${feign.url}")
@FeignClient(name = "quotes", url = "${QUOTES:http://localhost:8083}")
public interface QuotesClient {
	
	@GetMapping("/quotes-api/getQuotesForPolicy") 
	public String quotesResponse
	(@Valid @RequestParam Long businessValue,@RequestParam Long propertyValue,@RequestParam String propertyType,@RequestHeader("Authorization") String token);

}
