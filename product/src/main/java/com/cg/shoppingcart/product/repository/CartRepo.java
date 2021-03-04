package com.cg.shoppingcart.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.shoppingcart.product.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Long>{

}
