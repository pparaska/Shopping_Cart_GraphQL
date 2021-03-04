package com.cg.shoppingcart.product.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class UserRole {
	
	private Long id;
	private String roleName;
	
	public UserRole(Long id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}
	
	

}
