package com.cognizant.consumer.controller;

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

import com.cognizant.consumer.feign.FeignAuthorizationServer;
import com.cognizant.consumer.model.Property;
import com.cognizant.consumer.repository.BusinessRepository;
import com.cognizant.consumer.repository.ConsumerRepository;
import com.cognizant.consumer.repository.PropertyRepository;
import com.cognizant.consumer.request.BusinessPropertyRequest;
import com.cognizant.consumer.request.ConsumerBusinessRequest;
import com.cognizant.consumer.response.BusinessPropertyDetails;
import com.cognizant.consumer.response.ConsumerBusinessDetails;
import com.cognizant.consumer.response.MessageResponse;
import com.cognizant.consumer.service.ConsumerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
//@Api(value = "ConsumerDetails", description = "Consumer details for Agent")
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private FeignAuthorizationServer authorizationclient;;

	//@ApiOperation(value = "Create consumer Business")
	@PostMapping("/createConsumerBusiness")
	public MessageResponse createConsumerBusiness(@Valid @RequestBody ConsumerBusinessRequest consumerBusinessRequest, @RequestHeader(name = "Authorization") String token) throws Exception {
		log.info("Start createConsumerBusiness");
		MessageResponse messageResponse;
		if(authorizationclient.validate(token))
		{
		if (!consumerService.checkBusinessEligibility(consumerBusinessRequest)) {
			return (new MessageResponse("Sorry!!, You are Not Eligibile for Insurance"));
		}
		messageResponse = consumerService.createConsumerBusiness(consumerBusinessRequest);
		log.debug("ConsumerBusiness Status: {}", messageResponse);
		log.info("End createConsumerBusiness");
		}
		else
		{
			log.debug("Invalid credentials");
			messageResponse = consumerService.NotcreatedConsumerBusiness();
		}
		return messageResponse;
	}

	@PostMapping("/updateConsumerBusiness")
	public MessageResponse updateConsumerBusiness(@Valid @RequestBody ConsumerBusinessDetails consumerBusinessDetails, @RequestHeader(name = "Authorization") String token) {
		log.info("Start updateConsumerBusiness");
		MessageResponse messageResponse;
		if(authorizationclient.validate(token))
		{
		if (!consumerRepository.existsById(consumerBusinessDetails.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(consumerBusinessDetails.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!businessRepository.existsById(consumerBusinessDetails.getBusinessid())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		messageResponse = consumerService.updateConsumerBusiness(consumerBusinessDetails);
		log.debug("ConsumerBusiness Status: {}", messageResponse);
		log.info("End updateConsumerBusiness");
		}
		else
		{
			log.debug("Invalid credentials");
			messageResponse = consumerService.NotcreatedConsumerBusiness();
		}
		return (messageResponse);
	}

	@GetMapping("/viewConsumerBusiness")
	public ResponseEntity<?> viewConsumerBusiness(@Valid @RequestParam Long consumerid,@RequestHeader(name = "Authorization") String token) {
		log.info("Start viewConsumerBusiness");
		ResponseEntity<?> responseentity;
		if(authorizationclient.validate(token))
		{
		if (!consumerRepository.existsById(consumerid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(consumerid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Business Found!!"));
		}
		ConsumerBusinessDetails consumerBusinessDetails = consumerService.viewConsumerBusiness(consumerid);
		log.debug("ConsumerBusiness Details: {}", consumerBusinessDetails);
		log.info("End viewConsumerBusiness");
		
		responseentity= ResponseEntity.ok(consumerBusinessDetails);
		}
		else
		{
			log.debug("Invalid credentials");
			responseentity=ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Business Found!!"));
		}
		return responseentity;
	}
	
	@GetMapping("/viewConsumerBusinessByPolicy")
	public ConsumerBusinessDetails viewConsumerBusinessbypolicy(@Valid @RequestParam Long consumerid) {
		log.info("Start viewConsumerBusinessByPolicy");
		ConsumerBusinessDetails consumerBusinessDetails = consumerService.viewConsumerBusiness(consumerid);
		log.debug("ConsumerBusiness Details: {}", consumerBusinessDetails);
		log.info("End viewConsumerBusinessByPolicy");
		return consumerBusinessDetails;
	}

	@PostMapping("/createBusinessProperty")
	public MessageResponse createBusinessProperty(@Valid @RequestBody BusinessPropertyRequest businessPropertyRequest,@RequestHeader(name = "Authorization") String token) throws Exception {
		log.info("Start createBusinessProperty");
		MessageResponse messageResponse;
		if(authorizationclient.validate(token))
		{
		if (!consumerRepository.existsById(businessPropertyRequest.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(businessPropertyRequest.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!businessRepository.existsById(businessPropertyRequest.getBusinessId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!consumerService.checkPropertyEligibility(businessPropertyRequest.getPropertytype(),
				businessPropertyRequest.getInsurancetype(), businessPropertyRequest.getBuildingtype(),businessPropertyRequest.getBuildingage())) {
			return (new MessageResponse("Sorry!!, You are Not Eligibile for Insurance"));
		}
		messageResponse = consumerService.createBusinessProperty(businessPropertyRequest);
		log.debug("BusinessProperty Status: {}", messageResponse);
		log.info("End createBusinessProperty");
		}
		else
		{
			log.debug("Invalid credentials");
			messageResponse = consumerService.NotcreatedConsumerBusiness();
		}
		return (messageResponse);
	}

	@PostMapping("/updateBusinessProperty")
	public MessageResponse updateBusinessProperty(@Valid @RequestBody BusinessPropertyDetails businessPropertyDetails,@RequestHeader(name = "Authorization") String token) {
		log.info("Start updateBusinessProperty");
		MessageResponse messageResponse;
		if(authorizationclient.validate(token))
		{
		if (!propertyRepository.existsById(businessPropertyDetails.getPropertyId())) {
			return (new MessageResponse("Sorry!!, No Property Found!!"));
		}
		if (!consumerRepository.existsById(businessPropertyDetails.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(businessPropertyDetails.getConsumerId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		if (!businessRepository.existsById(businessPropertyDetails.getBusinessId())) {
			return (new MessageResponse("Sorry!!, No Business Found!!"));
		}
		messageResponse = consumerService.updateBusinessProperty(businessPropertyDetails);
		log.debug("BusinessProperty Status: {}", messageResponse);
		log.info("End updateBusinessProperty");
		}
		else
		{
			log.debug("Invalid credentials");
			messageResponse = consumerService.NotcreatedConsumerBusiness();
		}
		return (messageResponse);
	}

	@GetMapping("/viewConsumerProperty")
	public ResponseEntity<?> viewConsumerProperty(@Valid @RequestParam Long consumerid, @RequestParam Long propertyid,@RequestHeader(name = "Authorization") String token) {
		log.info("Start viewConsumerProperty");
		ResponseEntity<?> responseentity;
		if(authorizationclient.validate(token))
		{
		if (!propertyRepository.existsById(propertyid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Property Found!!"));
		}
		if (!consumerRepository.existsById(consumerid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Consumer Found!!"));
		}
		if (!businessRepository.existsByConsumerId(consumerid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Business Found!!"));
		}
		Property property = consumerService.viewConsumerProperty(consumerid, propertyid);
		log.debug("BusinessProperty Details: {}", property);
		log.info("End viewConsumerProperty");
		responseentity= ResponseEntity.ok(property);
		}
		else
		{
			log.debug("Invalid credentials");
			responseentity=ResponseEntity.badRequest().body(new MessageResponse("Sorry!!, No Business Found!!"));
		}
		return responseentity;
	}

	public MessageResponse sendPropertyErrorResponse() {
		return (new MessageResponse("(Property Error Response!!"));

	}

	public MessageResponse sendConsumerErrorResponse() {
		return (new MessageResponse("(Consumer Error Response!!"));

	}
}
