package com.cg.shoppingcart.order.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.cg.shoppingcart.order.model.Order;
import com.cg.shoppingcart.order.repository.OrderRepository;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ OrderServiceImpl.class, Order.class, OrderRepository.class })
@PowerMockIgnore("javax.management.*")
public class OrderServiceImplTest {

	@Mock
	OrderServiceImpl orderServiceImpl;

	@Mock
	OrderRepository orderRepositoryImpl;

	ArrayList<Order> orders = new ArrayList<>();

	public void setUpOrders() {

		orders.add(new Order(1l, "Cart(11111l,\r\n"
				+ "				\"Product(11111l, \\\"Wireless-Charger\\\", 7.0, \\\"Wireless charger with great reviws\\\", \\\"Electronics\\\")\",\r\n"
				+ "				145l)", 12.00));
		orders.add(new Order(2l, "Cart(11112l,\r\n"
				+ "				\"Product(11112l, \\\"Headphone\\\", 10.9, \\\"JBL Headphones with best product revies\\\", \\\"Electronics\\\")\",\r\n"
				+ "				10l))", 10.9));

	}

	@Test
	public void getAllOrders() {

		setUpOrders();
		when(orderRepositoryImpl.findAll()).thenReturn(orders);
		List<Order> ordersFromRepo = orderRepositoryImpl.findAll();
		Assert.assertEquals(ordersFromRepo, orders);
	}

	@Test
	public void getOrderByIdTest() {
		setUpOrders();
		when(orderRepositoryImpl.getOne(1l)).thenReturn(orders.get(0));
		Order order = orderRepositoryImpl.getOne(1l);
		Assert.assertEquals(order.getOrderId(), orders.get(0).getOrderId());
	}

	@Test
	public void placeNewOrderTest() {
		setUpOrders();
		Order order = orders.get(0);
		orderRepositoryImpl.save(order);
		Assert.assertEquals(order.getOrderId().longValue(), 1l);
	}

}
