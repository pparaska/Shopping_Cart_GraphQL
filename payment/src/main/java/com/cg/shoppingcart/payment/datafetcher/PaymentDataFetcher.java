package com.cg.shoppingcart.payment.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.payment.model.Payment;
import com.cg.shoppingcart.payment.repository.PaymentRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class PaymentDataFetcher implements DataFetcher<Payment> {

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payment get(DataFetchingEnvironment dataFetchingEnvironment) {

		Long transactionId = dataFetchingEnvironment.getArgument("transactionId");

		return paymentRepository.findById(transactionId).get();
	}
}
