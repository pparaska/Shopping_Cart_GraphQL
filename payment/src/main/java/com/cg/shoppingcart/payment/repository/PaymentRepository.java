package com.cg.shoppingcart.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.shoppingcart.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
