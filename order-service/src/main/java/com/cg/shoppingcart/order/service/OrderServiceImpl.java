package com.cg.shoppingcart.order.service;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.shoppingcart.order.model.Order;
import com.cg.shoppingcart.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	RestTemplate restTemplate;

	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;

//	public String getCartDetailsById(Long cartId) throws URISyntaxException {
//		URI uri = new URI("http://localhost:8082/product/getCartById/"+ cartId);
//		HttpHeaders headers = new HttpHeaders();
//
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		RestTemplate restTemplate = new RestTemplate();
//		String cart = restTemplate.getForObject(uri, String.class);
//		System.out.println(cart);
//		return cart;
//	}

	public ResponseEntity<String> getCartById(Long cartId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json"); // just modified graphql into json
		String query1 = "{\r\n" + 
				"cart(cartId:1){\r\n" + 
				"cartId\r\n" + 
				"cartTotal\r\n" + 
				"}\r\n" + 
				"}";

		String URL = "http://localhost:8081/product";
		ResponseEntity<String> response = restTemplate.postForEntity(URL, new HttpEntity<>(query1, headers),
				String.class);
		return response;

	}

	@Override
	public Order placeOrder(Long cartId) throws URISyntaxException {

		ResponseEntity<String> cart = getCartById(cartId);
		String cart1 = cart.toString();

		System.out.println(cart);
		Order order = new Order();
		order.setCart(cart1);
		order.setOrderId(cartId);
		order.setOrderTotal(123.4);
		return orderRepository.save(order);

	}

	@Override
	public Order getOrderDetails(Long orderId) {
		logger.info("Inside getOrderDetails method");

		return orderRepository.findById(orderId).get();

	}

	@Override
	public void cancelOrder(Long orderId) {
		Order order = getOrderDetails(orderId);
		orderRepository.delete(order);

	}

}
