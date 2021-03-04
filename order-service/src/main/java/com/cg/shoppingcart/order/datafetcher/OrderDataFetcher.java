package com.cg.shoppingcart.order.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.shoppingcart.order.model.Order;
import com.cg.shoppingcart.order.repository.OrderRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class OrderDataFetcher implements DataFetcher<Order>{

    @Autowired
    OrderRepository orderRepo;

    @Override
    public Order get(DataFetchingEnvironment dataFetchingEnvironment) {

        Long orderId = dataFetchingEnvironment.getArgument("orderId");

        return orderRepo.findById(orderId).get();
    }
}
