package com.cg.shoppingcart.payment.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.payment.model.Payment;
import com.cg.shoppingcart.payment.repository.PaymentRepository;
import com.cg.shoppingcart.payment.service.PaymentServiceImpl;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CreatePaymentDataFetcher implements DataFetcher<Payment> {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PaymentServiceImpl paymentServiceImpl;

	@Override
	public Payment get(DataFetchingEnvironment environment) {
		Long transactionId = 1l;
		String orderDetails = paymentServiceImpl.searchOrderById(transactionId).toString().substring(0, 247);
		Double totalPay = environment.getArgument("totalPay");
		Double totalBalance = environment.getArgument("totalBalance");
		Double remainingBalance = totalBalance - totalPay;
		Payment payment = new Payment(transactionId, totalPay, totalBalance, remainingBalance,
				orderDetails);
		return paymentRepository.save(payment);

	}

}
