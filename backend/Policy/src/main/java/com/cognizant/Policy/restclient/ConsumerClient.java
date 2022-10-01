package com.cognizant.Policy.restclient;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.Policy.response.ConsumerBusinessDetails;

import feign.Headers;

@Headers("Content-Type: application/json")
//@FeignClient(name = "emp-ws", url = "${feign.url}")
@FeignClient(name = "consumer", url = "${CONSUMER:http://localhost:8082}")
public interface ConsumerClient {
	
	@GetMapping("/consumer/viewConsumerBusinessByPolicy")
	public ConsumerBusinessDetails viewConsumerBusiness(@Valid @RequestParam Long consumerid);

}
