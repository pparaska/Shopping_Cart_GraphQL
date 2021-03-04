package com.cg.shoppingcart.product.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.repository.ProductRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UpdateProductDataFetcher implements DataFetcher<Product> {

	@Autowired
	ProductRepo productRepo;

	@Override
	public Product get(DataFetchingEnvironment environment) {
		Long productId = environment.getArgument("productId");
		String updatedName = environment.getArgument("productName");
		Product product = productRepo.getOne(productId);
		product.setProductName(updatedName);
		return productRepo.save(product);
	}

}
