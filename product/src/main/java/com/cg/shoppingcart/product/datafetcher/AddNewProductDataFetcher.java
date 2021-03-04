package com.cg.shoppingcart.product.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.repository.ProductRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AddNewProductDataFetcher implements DataFetcher<Product> {

	@Autowired
	ProductRepo productRepo;

	@Override
	public Product get(DataFetchingEnvironment environment) {
		Long productId = environment.getArgument("productId");
		String productName = environment.getArgument("productName");
		Double productPrice = environment.getArgument("productPrice");
		String description = environment.getArgument("description");
		String type = environment.getArgument("type");

		Product product = new Product(productId, productName, productPrice, description, type);
		return productRepo.save(product);
	}

}
