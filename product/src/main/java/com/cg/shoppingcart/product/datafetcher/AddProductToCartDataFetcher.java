package com.cg.shoppingcart.product.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.product.model.Cart;
import com.cg.shoppingcart.product.repository.CartRepo;
import com.cg.shoppingcart.product.repository.ProductRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AddProductToCartDataFetcher implements DataFetcher<Cart> {

	@Autowired
	CartRepo cartRepo;

	@Autowired
	ProductRepo productRepo;

	@Override
	public Cart get(DataFetchingEnvironment environment) {
		Long cartId = environment.getArgument("productId");
		String product = productRepo.getOne(cartId).toString();
		Long cartTotal = productRepo.getOne(cartId).getProductPrice().longValue();

		Cart cart = new Cart(cartId, product, cartTotal);
		return cartRepo.save(cart);
	}

	;

}
