package com.cg.shoppingcart.payment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString

public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private Double totalPay;
	private Double totalBalance;
	private Double remainingBalance;
	private String orderDetails;
	
	
	public Payment(Long transactionId, Double totalPay, Double totalAccountBalance, Double remainingAccountBalance,
			String order) {
		super();
		this.transactionId = transactionId;
		this.totalPay = totalPay;
		this.totalBalance = totalAccountBalance;
		this.remainingBalance = remainingAccountBalance;
		this.orderDetails = order;
	}


	public Payment() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
