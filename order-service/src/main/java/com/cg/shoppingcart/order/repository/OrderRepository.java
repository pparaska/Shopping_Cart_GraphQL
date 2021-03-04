package com.cg.shoppingcart.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.shoppingcart.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
