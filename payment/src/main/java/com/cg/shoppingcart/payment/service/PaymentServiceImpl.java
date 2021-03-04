package com.cg.shoppingcart.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.shoppingcart.payment.model.Payment;
import com.cg.shoppingcart.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepo;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public Payment getPaymentDetailsByTransactionId(Long transactionId) {
		return paymentRepo.getOne(transactionId);
	}

//	public String searchOrderById(Long orderId) {
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//		return restTemplate.exchange("http://localhost:8083/order/" + orderId, HttpMethod.GET, entity, String.class)
//				.getBody();
//	}

	public ResponseEntity<String> searchOrderById(Long orderId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json"); // just modified graphql into json
		String query1 = "{\r\n" + " order(orderId: 1){\r\n" + "     orderId\r\n" + "     cart\r\n"
				+ "     orderTotal\r\n" + " }\r\n" + "}\r\n" + "";

		String URL = "http://localhost:8083/order/";
		ResponseEntity<String> response = restTemplate.postForEntity(URL, new HttpEntity<>(query1, headers),
				String.class);
		return response;

	}

//	Object stringToOrder(Long orderId) {
//		String orderDetail = searchOrderById(orderId).toString();
//		Object order = new Order();
//		order = orderDetail;
//		return order;
//
//	}

	@Override
	public Payment payOrderBill(Long orderId) {
		Double totalBalance = 100000.0;
		Payment paymentModel = new Payment();
		ResponseEntity<String> order = searchOrderById(orderId);
		paymentModel.setTotalPay(123.2);
		paymentModel.setTransactionId(orderId.longValue());
		paymentModel.setOrderDetails(order.toString());
		if (totalBalance > paymentModel.getTotalPay()) {
			paymentModel.setTotalBalance(totalBalance - paymentModel.getTotalPay());
		}
		paymentRepo.save(paymentModel);
		return paymentModel;
	}

}
