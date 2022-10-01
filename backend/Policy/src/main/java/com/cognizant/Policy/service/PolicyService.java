package com.cognizant.Policy.service;

import org.springframework.stereotype.Service;

import com.cognizant.Policy.request.CreatePolicyRequest;
import com.cognizant.Policy.request.*;
import com.cognizant.Policy.response.ConsumerBusinessDetails;
import com.cognizant.Policy.response.MessageResponse;
import com.cognizant.Policy.response.PolicyDetailsResponse;
import com.cognizant.Policy.response.QuotesDetailsResponse;

@Service
public interface PolicyService {

	QuotesDetailsResponse getQuotes(Long businessValue, Long propertyValue, String propertyType,String token);

	PolicyDetailsResponse viewPolicy(Long consumerid, String policyid);

	MessageResponse issuePolicy(IssuePolicyRequest issuePolicyRequest);

	MessageResponse createPolicy(CreatePolicyRequest createPolicyRequest);
	
	ConsumerBusinessDetails getConsumerBusiness(Long consumerid);
	
	MessageResponse notcreatePolicy();
	
}
