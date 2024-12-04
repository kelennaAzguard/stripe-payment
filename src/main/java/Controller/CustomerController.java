package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.payment.endpoint.CustomerInterface;
import com.stripe.payment.processor.CustomerProcessor;
import com.yuzee.common.lib.handler.GenericResponseHandlers;
import com.yuzee.local.config.MessageTranslator;

@RestController
public class CustomerController implements CustomerInterface{
	@Autowired
	private CustomerProcessor customerProcessor;
	@Autowired
	private MessageTranslator messageTranslator;

	@Override
	public ResponseEntity<?> createCustomer(String name , String email) throws StripeException {
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setData(customerProcessor.createCustomer(name , email))
				.setMessage(messageTranslator.toLocale("create.added")).create();
	}

	@Override
	public ResponseEntity<?> updateCustomer(String customerId , String orderId) throws StripeException {
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setData(customerProcessor.updateCustomer(customerId, orderId))
				.setMessage(messageTranslator.toLocale("update.customer")).create();
	}

	@Override
	public ResponseEntity<?> deleteCustomer(String customerId) throws StripeException {
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setData(customerProcessor.deleteCustomer(customerId))
				.setMessage(messageTranslator.toLocale("delete.customer")).create();
	}

	@Override
	public ResponseEntity<?> retrieveCustomer(String customerId) throws StripeException {
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setData(customerProcessor.retriveCustomer(customerId))
				.setMessage(messageTranslator.toLocale("retrieve.customer")).create();
	}

}
