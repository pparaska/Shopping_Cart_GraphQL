package com.cg.shoppingcart.product.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.repository.ProductRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllProductsDataFetcher implements DataFetcher<List<Product>>{

    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return productRepo.findAll();
    }
}
