package com.cognizant.Quotes.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.Quotes.feign.FeignAuthorizationServer;
import com.cognizant.Quotes.model.QuotesMaster;
import com.cognizant.Quotes.repository.QuotesMasterRepository;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@CrossOrigin
public class QuotesController {
	
	@Autowired
	QuotesMasterRepository quotesMasterRepository;
	
	@Autowired
	FeignAuthorizationServer authclient;
	
	@GetMapping("/getQuotesForPolicy")
	public String getQuotesForPolicy
	(@Valid @RequestParam Long businessValue,@RequestParam Long propertyValue,@RequestParam String propertyType
			,@RequestHeader(name = "Authorization") String token){
		log.info("Start");
		String quotes;
		if(authclient.validate(token))
		{
		try
        {
		QuotesMaster quotesMaster=quotesMasterRepository
				.findByBusinessValueAndPropertyValueAndPropertyType(businessValue, propertyValue, propertyType);
		log.debug("QuotesMaster: {}", quotesMaster);
		quotes=quotesMaster.getQuotes();
        }catch(NullPointerException e) 
        { 
        	quotes= "No Quotes, Contact Insurance Provider";
        	return quotes;  
        } 
		log.info("End");
		}
		else
		{
			log.debug("Invalid credentials");
			quotes= "No Quotes, Contact Insurance Provider";
		}
		return quotes; 
	}
	

}
