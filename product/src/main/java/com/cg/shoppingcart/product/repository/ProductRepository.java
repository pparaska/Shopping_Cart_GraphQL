package com.cg.shoppingcart.product.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cg.shoppingcart.product.model.Product;

@Repository
public interface ProductRepository {
	
	public void addNewProduct(Product product);
	
	public List<Product> getAllProducts();

	public Product getProductsByProductId(Long productId);

	public void updateProductByProductId(Long productId, Product product);
	
	public void deletProuctByProductId(Long productId);
	
	public void deleteAllProducts();

}

