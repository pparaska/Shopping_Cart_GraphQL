package com.cg.shoppingcart.order.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.order.repository.OrderRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CancelOrderDataFetcher implements DataFetcher<Long>{
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public Long get(DataFetchingEnvironment environment) {
		Long orderId = environment.getArgument("orderId");
		orderRepository.deleteById(orderId);
		return orderId;
	}

}
