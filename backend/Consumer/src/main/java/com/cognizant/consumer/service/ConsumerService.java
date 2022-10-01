package com.cognizant.consumer.service;

import org.springframework.stereotype.Service;

import com.cognizant.consumer.model.Property;
import com.cognizant.consumer.request.BusinessPropertyRequest;
import com.cognizant.consumer.request.ConsumerBusinessRequest;
import com.cognizant.consumer.response.BusinessPropertyDetails;
import com.cognizant.consumer.response.ConsumerBusinessDetails;
import com.cognizant.consumer.response.MessageResponse;

@Service
public interface ConsumerService {

	MessageResponse createConsumerBusiness(ConsumerBusinessRequest consumerBusinessRequest);
	
	Long calBusinessValue(Long businessturnover,Long capitalinvested);
	
	Long calPropertyValue(Long costoftheasset, Long salvagevalue, Long usefullifeoftheAsset);

	MessageResponse updateConsumerBusiness(ConsumerBusinessDetails consumerBusinessDetails);

	ConsumerBusinessDetails viewConsumerBusiness(Long consumerid);

	MessageResponse createBusinessProperty(BusinessPropertyRequest businessPropertyRequest);

	MessageResponse updateBusinessProperty(BusinessPropertyDetails businessPropertyDetails);

	Property viewConsumerProperty(Long consumerid, Long propertyid);

	Boolean checkBusinessEligibility(ConsumerBusinessRequest consumerBusinessRequest) throws Exception;

	Boolean checkPropertyEligibility(String propertytype, String insurancetype, String buildingtype, Long buildingage) throws Exception;
	
	MessageResponse NotcreatedConsumerBusiness();

	

}
