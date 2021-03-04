package com.cg.shoppingcart.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.shoppingcart.product.model.Cart;
import com.cg.shoppingcart.product.model.Product;

@Service
public interface ProductService {

//	public DataFetcher<Product> addNewProduct();

	public List<Product> getAllProducts();

	public Product getProductById(Long productId);

	public Product updateProductById(Long productId, Product product);

	public Long deleteProductById(Long productId);

	public void deleteAllProducts();

	public Cart addProductToCart(Long productId);

	public void deleteProductFromCart(Long productId);

	public Cart getCartById(Long cartId);

	public List<Cart> viewAllCarts();

}
