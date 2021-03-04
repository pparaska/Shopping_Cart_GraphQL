package com.cg.shoppingcart.product.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.product.repository.ProductRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class DeleteProductByIdDataFetcher implements DataFetcher<Long> {
	
	@Autowired
	ProductRepo productRepo;

	@Override
	public Long get(DataFetchingEnvironment environment) {
		Long productId = environment.getArgument("productId");
		productRepo.deleteById(productId);
		return productId;
	}

}
