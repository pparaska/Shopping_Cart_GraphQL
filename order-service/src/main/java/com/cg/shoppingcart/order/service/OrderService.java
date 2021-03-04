package com.cg.shoppingcart.order.service;

import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.cg.shoppingcart.order.model.Order;

@Service
public interface OrderService {

	public Order placeOrder(Long orderId) throws URISyntaxException;

	public Order getOrderDetails(Long orderId);

	public void cancelOrder(Long orderId);

}
