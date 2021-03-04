package com.cg.shoppingcart.product.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.product.repository.ProductRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class DeleteProductsDataFetcher implements DataFetcher<String> {
	
	@Autowired
	ProductRepo productRepo;

	@Override
	public String get(DataFetchingEnvironment environment) {
		productRepo.deleteAll();
		return "All products has been deleted";
	}

}
