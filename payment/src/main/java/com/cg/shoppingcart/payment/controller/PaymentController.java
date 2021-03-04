package com.cg.shoppingcart.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.shoppingcart.payment.service.GraphQLService;
import com.cg.shoppingcart.payment.service.PaymentServiceImpl;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentServiceImpl paymentServiceImpl;
	
	@Autowired
	GraphQLService graphQLService;

	@PostMapping
	public ResponseEntity<Object> payment(@RequestBody String query) {
		ExecutionResult result = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
}
