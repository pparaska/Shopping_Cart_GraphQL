package com.cg.shoppingcart.product.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.shoppingcart.product.model.Cart;
import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.model.UserRole;
import com.cg.shoppingcart.product.repository.CartRepo;
import com.cg.shoppingcart.product.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo productRepo;

	@Autowired
	CartRepo cartRepo;

	ArrayList<Product> products = new ArrayList<Product>();

	ArrayList<Cart> carts = new ArrayList<Cart>();

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	public List<UserRole> setUpUserRole() {
		List<UserRole> userRole = new ArrayList<>();
		userRole.add(new UserRole(1l, "admin"));
		userRole.add(new UserRole(2l, "user"));
		return userRole;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(Long productId) {
		logger.info("Inside getProductById method");

		return productRepo.findById(productId).get();
	}

	@Override
	public Long deleteProductById(Long productId) {
		logger.info("Inside deleteProductById method");

		productRepo.deleteById(productId);
		return productId;
	}

	@Override
	public void deleteAllProducts() {
		logger.info("Inside deleteAllProducts method");

		productRepo.deleteAll();
	}

	@Override
	public Cart addProductToCart(Long productId) {

		Product product = getProductById(productId);
		Cart cart = new Cart();
		cart.setCartId(productId);
		cart.setCartTotal(product.getProductPrice().longValue());
		cart.setProduct(product.toString());
		return cartRepo.save(cart);
	}

	@Override
	public void deleteProductFromCart(Long productId) {
		cartRepo.deleteById(productId);
	}

	@Override
	public Cart getCartById(Long cartId) {
		// TODO Auto-generated method stub
		return cartRepo.findById(cartId).get();
	}

	@Override
	public List<Cart> viewAllCarts() {
		// TODO Auto-generated method stub
		return cartRepo.findAll();
	}

	@Override
	public Product updateProductById(Long productId, Product product) {
		product = getProductById(productId);
		product.setProductName("ProductNameUpdated");
		return productRepo.save(product);
	}

//	@Override
//	public DataFetcher<Product> addNewProduct() {
//
//		return env -> {
//			Product product = new Product(env.getArgument("productId"), env.getArgument("productName"),
//					env.getArgument("productPrice"), env.getArgument("description"), env.getArgument("type"));
//			productRepo.save((product));
//			return product;
//
//		};
//	}

}
