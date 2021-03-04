package com.cg.shoppingcart.product.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.repository.ProductRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ProductDataFetcher implements DataFetcher<Product>{

    @Autowired
    ProductRepo productRepo;

    @Override
    public Product get(DataFetchingEnvironment dataFetchingEnvironment) {

        Long productId = dataFetchingEnvironment.getArgument("productId");

        return productRepo.findById(productId).get();
    }
}
