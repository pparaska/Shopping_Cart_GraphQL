package com.cg.shoppingcart.order.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.shoppingcart.order.repository.OrderRepository;
import com.cg.shoppingcart.order.service.GraphQLService;
import com.cg.shoppingcart.order.service.OrderServiceImpl;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	GraphQLService graphQLService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderServiceImpl orderServiceImpl;

	
	@PostMapping
	public ResponseEntity<Object> getOrderById(@RequestBody String query) throws URISyntaxException {
//		orderServiceImpl.getCartDetailsById(1l);
		ExecutionResult result = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	
	@GetMapping("/{orderId}")
	public String getOrder(@PathVariable Long orderId) throws URISyntaxException {

		return orderRepository.getOne(orderId).toString();
	}


}
