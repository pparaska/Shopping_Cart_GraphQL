package com.cg.shoppingcart.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.shoppingcart.product.model.Cart;
import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.service.GraphQLService;
import com.cg.shoppingcart.product.service.ProductServiceImpl;

import graphql.ExecutionResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productServiceImpl;


	@Autowired
	GraphQLService graphQLService;

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@PostMapping
	public ResponseEntity<Object> allProducts(@RequestBody String query) {
		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
		System.out.println(query + "query");

		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return productServiceImpl.getAllProducts();
	}

//	@ApiOperation(value = "Get a Product by productId ", response = Iterable.class, tags = "getProductById")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
//			@ApiResponse(code = 404, message = "not found!!!") })
//	@GetMapping("/productById")
//	public ResponseEntity<Object> product(@RequestBody String query) {
//		ExecutionResult result = graphQLService.getGraphQL().execute(query);
//		return new ResponseEntity<Object>(result, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "Add new Product ", response = Iterable.class, tags = "addNewProduct")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
//			@ApiResponse(code = 404, message = "not found!!!") })
//	@PostMapping
//	public ResponseEntity<Object> addNewProduct(@RequestBody String query) {
//		ExecutionResult result = graphQLService.getGraphQL().execute(query);
//		return new ResponseEntity<Object>(result, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "Update Product by productId", response = Iterable.class, tags = "updateProductById")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
//			@ApiResponse(code = 404, message = "not found!!!") })
//	@PutMapping("/updateProduct/{productId}/{userRole}")
//	public void updateProductDetails(@RequestBody Product product, @PathVariable Long productId,
//			@PathVariable String userRole) {
//		logger.info("Inside updateProductDetails method");
//		productService.updateProductById(product.getProductId(), userRole, product);
//	}
//
//	@ApiOperation(value = "Delete a Product by productId ", response = Iterable.class, tags = "deleteProductById")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
//			@ApiResponse(code = 404, message = "not found!!!") })
//	@DeleteMapping("/delete/{productId}/{userRole}")
//	public void deleteProuctById(@PathVariable Long productId, @PathVariable String userRole) {
//		logger.info("Inside deleteProuctById method");
//		productService.deleteProductById(productId, userRole);
//	}
//
//	@ApiOperation(value = "Delete All products", response = Iterable.class, tags = "deleteAllProducts")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
//			@ApiResponse(code = 404, message = "not found!!!") })
//	@DeleteMapping("/delete/all/{userRole}")
//	public void deleteAllProducts(@PathVariable String userRole) {
//		logger.info("Inside deleteAllProducts method");
//		productService.deleteAllProducts(userRole);
//	}
//
//	@ApiOperation(value = "Get Cart Details", response = Iterable.class, tags = "getCartDetails")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
//			@ApiResponse(code = 404, message = "not found!!!") })
//	@GetMapping("/getCartDetailsById")
//	public ResponseEntity<Object> cart(@RequestBody String query) {
//		ExecutionResult result = graphQLService.getGraphQL().execute(query);
//		return new ResponseEntity<Object>(result, HttpStatus.OK);
//	}
//	
	@ApiOperation(value = "Get Cart Details", response = Iterable.class, tags = "getCartDetails")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/getCartById/{cartId}")
	public Cart cartById(@PathVariable("cartId") Long cartId) {
		return productServiceImpl.getCartById(cartId);
		
	}
//
//	@ApiOperation(value = "Get list of Carts", response = Iterable.class, tags = "viewAllCarts")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
//			@ApiResponse(code = 404, message = "not found!!!") })
//	@GetMapping("/allCarts")
//	public ResponseEntity<Object> allCarts(@RequestBody String query) {
//		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
//		System.out.println(query + "query");
//
//		return new ResponseEntity<>(execute, HttpStatus.OK);
//	}
//
	@ApiOperation(value = "Add product to cart ", response = Iterable.class, tags = "addProductToCart")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping("/addToCart/{productId}")
	public Cart addProductToCart(@PathVariable("productId") Long productId) {
		logger.info("Inside addProductToCart method");
		return productServiceImpl.addProductToCart(productId);

	}
}
