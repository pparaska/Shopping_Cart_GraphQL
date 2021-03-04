package com.cg.shoppingcart.product.service;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.cg.shoppingcart.product.model.Cart;
import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.repository.CartRepo;
import com.cg.shoppingcart.product.repository.ProductRepo;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ProductRepo.class, ProductServiceImpl.class, Product.class, Cart.class, CartRepo.class })
@PowerMockIgnore("javax.management.*")
public class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	private ProductRepo productRepositoryImpl;

	@Mock
	private CartRepo cartRepoImpl;

	ArrayList<Product> products = new ArrayList<>();

	ArrayList<Cart> carts = new ArrayList<>();

	public void setUpProducts() {

		products.add(new Product(11111l, "Wireless-Charger", 7.0, "Wireless charger with great reviws", "Electronics"));
		products.add(new Product(11112l, "Headphone", 10.9, "JBL Headphones with best product revies", "Electronics"));
		products.add(new Product(11113l, "IPhone11", 700.9, "IPhone11 with 25% discount", "Electronics"));
		products.add(new Product(11114l, "Wireless Mouse", 11.1, "Wireless-Mouse", "Electronics"));
		products.add(new Product(11115l, "DryFruits Pack", 702.5, "Fresh dry fruits", "Grocery"));

	}

	public void setUpCarts() {

		carts.add(new Cart(11111l,
				"Product(11111l, \"Wireless-Charger\", 7.0, \"Wireless charger with great reviws\", \"Electronics\")",
				145l));
		carts.add(new Cart(11112l,
				"Product(11112l, \"Headphone\", 10.9, \"JBL Headphones with best product revies\", \"Electronics\")",
				10l));

	}

	@Test
	public void getAllProductsTest() {

		setUpProducts();
		when(productRepositoryImpl.findAll()).thenReturn(products);
		List<Product> productsFromRepo = productRepositoryImpl.findAll();
		Assert.assertEquals(productsFromRepo, products);
	}

	@Test
	public void getProductByIdTest() {
		setUpProducts();
		when(productRepositoryImpl.getOne(11111l)).thenReturn(products.get(0));
		Product product = productRepositoryImpl.getOne(11111l);
		Assert.assertEquals(product.getProductId(), products.get(0).getProductId());
	}

	@Test
	public void addNewProductTest() {
		setUpProducts();
		Product product = products.get(0);
		productRepositoryImpl.save(product);
		Assert.assertEquals(product.getProductId().longValue(), 11111l);
	}

	@Test
	public void updateExistingProductTest() {
		setUpProducts();
		Product product = products.get(0);
		when(productRepositoryImpl.getOne(11111l)).thenReturn(products.get(0));
		Assert.assertEquals(product.getProductName(), productRepositoryImpl.getOne(11111l).getProductName());
	}

	@Test
	public void deleteProductById() {
		setUpProducts();
		Product product = products.get(0);
		Long productId = 11112l;
		productRepositoryImpl.deleteById(productId);
		assertNotEquals(11112l, product.getProductId().longValue());
	}

	@Test
	public void getAllCartsTest() {

		setUpCarts();
		when(cartRepoImpl.findAll()).thenReturn(carts);
		List<Cart> cartFromRepo = cartRepoImpl.findAll();
		Assert.assertEquals(cartFromRepo, carts);
	}

	@Test
	public void getCartByIdTest() {
		setUpCarts();
		when(cartRepoImpl.getOne(11111l)).thenReturn(carts.get(0));
		Cart cart = cartRepoImpl.getOne(11111l);
		Assert.assertEquals(cart.getCartId(), carts.get(0).getCartId());

	}
}
