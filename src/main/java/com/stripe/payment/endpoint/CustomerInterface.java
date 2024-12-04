package com.stripe.payment.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stripe.exception.StripeException;

@RequestMapping("/api/v1")
public interface CustomerInterface {

	@PostMapping("/create-customer")
	public ResponseEntity<?> createCustomer(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true) String email) throws StripeException;

	@PutMapping("/update-customer")
	public ResponseEntity<?> updateCustomer(@RequestParam(value = "customer_id", required = true) String customerId,
			@RequestParam(value = "order_id", required = true) String orderId) throws StripeException;

	@PutMapping("/update-customer")
	public ResponseEntity<?> deleteCustomer(@RequestParam(value = "customer_id", required = true) String customerId)
			throws StripeException;

	@PutMapping("/retrive-customer")
	public ResponseEntity<?> retrieveCustomer(@RequestParam(value = "customer_id", required = true) String customerId)
			throws StripeException;
}
