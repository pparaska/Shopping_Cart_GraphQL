package com.cg.shoppingcart.product.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.product.model.Cart;
import com.cg.shoppingcart.product.repository.CartRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllCartsDataFetcher implements DataFetcher<List<Cart>>{

    @Autowired
    CartRepo cartRepo;

    @Override
    public List<Cart> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return cartRepo.findAll();
    }
}
