package com.cg.shoppingcart.order.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="orderTable")
public class Order {
	
	
	@Id
	private Long orderId;
	
	private String cart;
	private Double orderTotal;
	
	
	public Order(Long orderId, String cart, Double orderTotal) {
		super();
		this.orderId = orderId;
		this.cart = cart;
		this.orderTotal = orderTotal;
	}



	public Order() {
	}
	
	

}
