package com.stripe.payment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.payment.dto.CustomerDto;

@Service
public class CustomerService {

	@Value("${stripe.api.key}")
	private String stripeApiKey;

	public Customer createCustomer(String name, String email) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		CustomerCreateParams params = CustomerCreateParams.builder().setName(name).setEmail(email).build();
		Customer customer = Customer.create(params);
		return customer;

	}

	public Customer updateCustomer(String customerId, String orderId) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		Customer resource = Customer.retrieve(customerId);
		CustomerUpdateParams params = CustomerUpdateParams.builder().putMetadata("order_id", orderId).build();
		Customer customer = resource.update(params);
		return customer;
	}

	public Customer deleteCustomer(String customerId) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		Customer resource = Customer.retrieve(customerId);
		return resource.delete();
	}

	public Customer retrieveCustomer(String customerId) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		Customer customer = Customer.retrieve(customerId);
		return customer;
	}
}
