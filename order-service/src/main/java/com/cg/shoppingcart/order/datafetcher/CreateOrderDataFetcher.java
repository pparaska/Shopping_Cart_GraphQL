package com.cg.shoppingcart.order.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.order.model.Order;
import com.cg.shoppingcart.order.repository.OrderRepository;
import com.cg.shoppingcart.order.service.OrderServiceImpl;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CreateOrderDataFetcher implements DataFetcher<Order> {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderServiceImpl orderServiceImpl;

	@Override
	public Order get(DataFetchingEnvironment environment) {
		Long orderId = environment.getArgument("cartId");
		String cart = orderServiceImpl.getCartById(orderId).toString();
		Double orderTotal = environment.getArgument("orderTotal");

		Order order = new Order(orderId, cart, orderTotal);
		return orderRepository.save(order);
	}

}
