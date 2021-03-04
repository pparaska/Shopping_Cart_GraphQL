package com.cg.shoppingcart.payment.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.cg.shoppingcart.payment.model.Payment;
import com.cg.shoppingcart.payment.repository.PaymentRepository;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ PaymentServiceImpl.class, Payment.class, PaymentRepository.class})
@PowerMockIgnore("javax.management.*")
public class PaymentServiceImplTest {

	@Mock
	PaymentServiceImpl paymentServiceImpl;

	@Mock
	PaymentRepository paymentRepository;

	ArrayList<Payment> payments = new ArrayList<>();

	@BeforeEach
	public void setUpPayments() {

		payments.add(new Payment(1l, 100.0, 10000.0, 9900.0, "Order(1l, \"Cart(11111l,\\r\\n\"\r\n"
				+ "				+ \"				\\\"Product(11111l, \\\\\\\"Wireless-Charger\\\\\\\", 7.0, \\\\\\\"Wireless charger with great reviws\\\\\\\", \\\\\\\"Electronics\\\\\\\")\\\",\\r\\n\"\r\n"
				+ "				+ \"				145l)\", 12.00)"));
		payments.add(new Payment(2l, 10.0, 100.0, 90.0, " Order(2l, \"Cart(11112l,\\r\\n\"\r\n"
				+ "				+ \"				\\\"Product(11112l, \\\\\\\"Headphone\\\\\\\", 10.9, \\\\\\\"JBL Headphones with best product revies\\\\\\\", \\\\\\\"Electronics\\\\\\\")\\\",\\r\\n\"\r\n"
				+ "				+ \"				10l))\", 10.9)"));

	}

	@Test
	public void getAllPayments() {

		setUpPayments();
		when(paymentRepository.findAll()).thenReturn(payments);
		List<Payment> paymentsFromRepo = paymentRepository.findAll();
		Assert.assertEquals(paymentsFromRepo, payments);
	}

	@Test
	public void getPaymentByTransactionId() {
		setUpPayments();
		when(paymentRepository.getOne(1l)).thenReturn(payments.get(0));
		Payment payment = paymentRepository.getOne(1l);
		Assert.assertEquals(payment.getTransactionId(), payments.get(0).getTransactionId());
	}

	@Test
	public void createPayentTest() {
		setUpPayments();
		Payment payment = payments.get(0);
		paymentRepository.save(payment);
		Assert.assertEquals(payment.getTransactionId().longValue(), 1l);
	}

}
