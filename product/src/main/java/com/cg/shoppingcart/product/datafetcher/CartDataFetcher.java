package com.cg.shoppingcart.product.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.product.model.Cart;
import com.cg.shoppingcart.product.repository.CartRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CartDataFetcher implements DataFetcher<Cart> {

	@Autowired
	CartRepo cartRepo;

	@Override
	public Cart get(DataFetchingEnvironment dataFetchingEnvironment) {

		Long cartId = dataFetchingEnvironment.getArgument("cartId");

		return cartRepo.findById(cartId).get();
	}
}
