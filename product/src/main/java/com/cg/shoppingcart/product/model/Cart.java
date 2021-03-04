package com.cg.shoppingcart.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table
@Entity
@ToString
public class Cart {

	@Id
	private Long cartId;
	private String product;
	private Long cartTotal;

	public Cart(Long cartId, String product, Long cartTotal) {
		super();
		this.cartId = cartId;
		this.product = product;
		this.cartTotal = cartTotal;

	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

}
