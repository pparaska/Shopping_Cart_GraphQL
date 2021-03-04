package com.cg.shoppingcart.product.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cg.shoppingcart.product.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	ArrayList<Product> products = new ArrayList<Product>();

//	private ArrayList<Product> setUpProducts() {
//		ArrayList<Product> products = new ArrayList<>();
//
//		products.add(new Product(11111l, "Wireless-Charger", new ProductPrice("Euro", 7f),
//				"Wireless charger with great reviws", "Electronics"));
//		products.add(new Product(11112l, "Headphone", new ProductPrice("Euro", 10f),
//				"JBL Headphones with best product revies", "Electronics"));
//		products.add(new Product(11113l, "IPhone11", new ProductPrice("Euro", 700f), "IPhone11 with 25% discount",
//				"Electronics"));
//		products.add(
//				new Product(11114l, "Wireless Mouse", new ProductPrice("Euro", 11f), "Wireless-Mouse", "Electronics"));
//		products.add(new Product(11115l, "DryFruits Pack", new ProductPrice("Rupees", 702.5f), "Fresh dry fruits",
//				"Grocery"));
//
//		return products;
//	}

	@Override
	public List<Product> getAllProducts() {
		return products;
	}

	@Override
	public Product getProductsByProductId(Long productId) {
		return products.get(productId.intValue());

	}

	@Override
	public void updateProductByProductId(Long productId, Product product) {
		products.set(productId.intValue(), product);
	}

	@Override
	public void deletProuctByProductId(Long productId) {
		products.remove(getProductsByProductId(productId));
	}

	@Override
	public void deleteAllProducts() {
		products.removeAll(products);
	}

	@Override
	public void addNewProduct(Product product) {
		products.add(product);
	}

}
