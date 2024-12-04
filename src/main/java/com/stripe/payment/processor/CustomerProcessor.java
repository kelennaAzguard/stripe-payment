package com.stripe.payment.processor;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.payment.dto.CustomerDto;
import com.stripe.payment.service.CustomerService;
import com.yuzee.common.lib.exception.InternalServerException;
import com.yuzee.common.lib.exception.NotFoundException;
import com.yuzee.local.config.MessageTranslator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerProcessor {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private MessageTranslator messageTranslator;

	private ModelMapper modelMapper;

	public CustomerDto createCustomer(String name, String email) throws StripeException {
		log.info("inside customer processor....");
		if (ObjectUtils.isEmpty(email) || ObjectUtils.isEmpty(name)) {
			log.info("name or email must not be null");
			throw new NotFoundException(messageTranslator.toLocale("name or email.is null"));
		}
		Customer customer = new Customer();
		try {
			customer = customerService.createCustomer(name, email);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific HTTP exceptions
			log.error("HTTP error occurred while creating customer: {} - {}", e.getStatusCode(),
					e.getResponseBodyAsString());
			throw new InternalServerException(
					"HTTP error occurred: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
		} catch (RestClientException e) {
			// Handle generic RestTemplate exceptions
			log.error("Exception occurred while creating customer: ", e);
			throw new InternalServerException("Exception occurred while creating customer", e);
		}
		return modelMapper.map(customer, CustomerDto.class);

	}

	public CustomerDto updateCustomer(String customerId, String orderId) throws StripeException {
		log.info("inside customer processor....");
		if (ObjectUtils.isEmpty(customerId) || ObjectUtils.isEmpty(orderId)) {
			log.info("customerId or orderId must not be null");
			throw new NotFoundException(messageTranslator.toLocale("customerId or orderId must not be null"));
		}
		Customer customer = new Customer();
		try {
			customer = customerService.updateCustomer(customerId, orderId);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific HTTP exceptions
			log.error("HTTP error occurred while creating customer: {} - {}", e.getStatusCode(),
					e.getResponseBodyAsString());
			throw new InternalServerException(
					"HTTP error occurred: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
		} catch (RestClientException e) {
			// Handle generic RestTemplate exceptions
			log.error("Exception occurred while creating customer: ", e);
			throw new InternalServerException("Exception occurred while creating customer", e);
		}
		return modelMapper.map(customer, CustomerDto.class);
	}

	public CustomerDto deleteCustomer(String customerId) throws StripeException {
		log.info("inside customer processor....");
		if (ObjectUtils.isEmpty(customerId)) {
			log.info("customerId must not be null");
			throw new NotFoundException(messageTranslator.toLocale("customerId.is null"));
		}
		Customer customer = new Customer();
		try {
			customer = customerService.deleteCustomer(customerId);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific HTTP exceptions
			log.error("HTTP error occurred while creating customer: {} - {}", e.getStatusCode(),
					e.getResponseBodyAsString());
			throw new InternalServerException(
					"HTTP error occurred: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
		} catch (RestClientException e) {
			// Handle generic RestTemplate exceptions
			log.error("Exception occurred while creating customer: ", e);
			throw new InternalServerException("Exception occurred while creating customer", e);
		}
		return modelMapper.map(customer, CustomerDto.class);
	}

	public CustomerDto retriveCustomer(String customerId) throws StripeException {
		log.info("inside customer processor....");
		if (ObjectUtils.isEmpty(customerId)) {
			log.info("customerId must not be null");
			throw new NotFoundException(messageTranslator.toLocale("customerId.is null"));
		}
		Customer customer = new Customer();
		try {
			customer = customerService.retrieveCustomer(customerId);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific HTTP exceptions
			log.error("HTTP error occurred while creating customer: {} - {}", e.getStatusCode(),
					e.getResponseBodyAsString());
			throw new InternalServerException(
					"HTTP error occurred: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
		} catch (RestClientException e) {
			// Handle generic RestTemplate exceptions
			log.error("Exception occurred while creating customer: ", e);
			throw new InternalServerException("Exception occurred while creating customer", e);
		}
		return modelMapper.map(customer, CustomerDto.class);
	}
}
