package com.cg.shoppingcart.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name="product")
@Entity
@ToString
public class Product {

	@Id
	private Long productId;
	private String productName;
	private Double productPrice;
	private String description;
	private String type;

	public Product() {
	}

	public Product(Long productId, String productName, Double productPrice, String description, String type) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.description = description;
		this.type = type;
	}

}
