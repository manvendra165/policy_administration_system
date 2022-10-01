package com.cognizant.Policy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.Policy.request.CreatePolicyRequest;
import com.cognizant.Policy.request.IssuePolicyRequest;
import com.cognizant.Policy.response.MessageResponse;
import com.cognizant.Policy.response.PolicyDetailsResponse;
import com.cognizant.Policy.response.QuotesDetailsResponse;
import com.cognizant.Policy.repository.ConsumerPolicyRepository;
import com.cognizant.Policy.repository.PolicyMasterRepository;
import com.cognizant.Policy.service.PolicyService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.cognizant.Policy.feign.FeignAuthorizationServer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class PolicyController {

	@Autowired
	PolicyService policyService;

	@Autowired
	PolicyMasterRepository policyMasterRepository;
	
	@Autowired
	ConsumerPolicyRepository consumerPolicyRepository;
	
	@Autowired
	private FeignAuthorizationServer authorizationclient;


	@PostMapping("/createPolicy")
	//@HystrixCommand(fallbackMethod = "sendPolicyErrorResponse")
	public MessageResponse createPolicy(@Valid @RequestBody CreatePolicyRequest createPolicyRequest,@RequestHeader(name = "Authorization") String token) {
		log.info("Start createPolicy");
		MessageResponse messageResponse;
		if(authorizationclient.validate(token))
		{
		messageResponse = policyService.createPolicy(createPolicyRequest);
		log.debug("MessageResponse : {}", messageResponse);
		log.info("End createPolicy");
		}
		else
		{
			log.debug("Invalid credentials");
			messageResponse=policyService.notcreatePolicy();
		}
		return (messageResponse);
	}

	@PostMapping("/issuePolicy")
	//@HystrixCommand(fallbackMethod = "sendPolicyErrorResponse")
	public MessageResponse issuePolicy(@Valid @RequestBody IssuePolicyRequest issuePolicyRequest,@RequestHeader(name = "Authorization") String token) {
		log.info("Start issuePolicy");
		MessageResponse messageResponse;
		if(authorizationclient.validate(token))
		{
		if (!consumerPolicyRepository.existsByConsumerid(issuePolicyRequest.getConsumerid())) {
			return (new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!policyMasterRepository.existsByPid(issuePolicyRequest.getPolicyid())) {
			return (new MessageResponse("Sorry!!, No Policy Found!!"));
		}
		if (!(issuePolicyRequest.getPaymentdetails().equals("Success"))) {
			return (new MessageResponse("Sorry!!, Payment Failed!! Try Again"));
		}
		if (!(issuePolicyRequest.getAcceptancestatus().equals("Accepted"))) {
			return (new MessageResponse("Sorry!!, Accepted Failed !! Try Again"));
		}
		messageResponse = policyService.issuePolicy(issuePolicyRequest);
		log.debug("MessageResponse : {}", messageResponse);
		log.info("End issuePolicy");
		}
		else
		{
			log.debug("Invalid credentials");
			messageResponse=policyService.notcreatePolicy();
		}
		return (messageResponse);
	}

	@GetMapping("/viewPolicy")
	//@HystrixCommand(fallbackMethod = "sendPolicyErrorResponse")
	public ResponseEntity<?> viewPolicy(@Valid @RequestParam Long consumerid, @RequestParam String policyid,@RequestHeader(name = "Authorization") String token) {
		log.info("Start viewPolicy");
		ResponseEntity<?> responseentity;
		if(authorizationclient.validate(token))
		{
		if (!policyMasterRepository.existsByPid(policyid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Policy Found!!"));
		}
		if (!consumerPolicyRepository.existsByConsumerid(consumerid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Consumer Found!!"));
		}	
		PolicyDetailsResponse policyDetailsResponse = policyService.viewPolicy(consumerid, policyid);
		log.debug("PolicyDetailsResponse: {}", policyDetailsResponse);
		log.info("End viewPolicy");
		 responseentity=ResponseEntity.ok(policyDetailsResponse);
		}
		else
		{
			log.debug("Invalid credentials");
			responseentity=ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Business Found!!"));
		}
		return responseentity;
	}

	@GetMapping("/getQuotes")
	//@HystrixCommand(fallbackMethod = "sendPolicyErrorResponse")
	public ResponseEntity<QuotesDetailsResponse> getQuotes(Long businessValue,Long propertyValue,String propertyType,@RequestHeader(name = "Authorization") String token) {
		log.info("Start getQuotes");
		ResponseEntity<QuotesDetailsResponse> responseentity;
		if(authorizationclient.validate(token))
		{
		QuotesDetailsResponse quotesDetailsResponse = policyService.getQuotes(businessValue, propertyValue,
				propertyType,token);
		log.debug("QuotesMaster: {}", quotesDetailsResponse);
		log.info("End getQuotes");
		  responseentity = ResponseEntity.ok(quotesDetailsResponse);
		}
		else
		{
			log.debug("Invalid credentials");
			responseentity=ResponseEntity.badRequest().body(new QuotesDetailsResponse("Sorry!!, No Quotes Found!!"));
		}
		return responseentity;
	}

	public MessageResponse sendPolicyErrorResponse() {
		return (new MessageResponse("(Policy Error Response!!"));

	}

}
