package com.cg.shoppingcart.payment.service;

import org.springframework.stereotype.Service;

import com.cg.shoppingcart.payment.model.Payment;

@Service
public interface PaymentService {
	
	public Payment getPaymentDetailsByTransactionId(Long transactionId);
		
	public Payment payOrderBill(Long orderId);

}
