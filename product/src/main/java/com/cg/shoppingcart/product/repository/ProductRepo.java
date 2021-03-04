package com.cg.shoppingcart.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.shoppingcart.product.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
