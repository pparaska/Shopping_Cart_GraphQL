package com.cg.shoppingcart.product.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.cg.shoppingcart.product.datafetcher.AddNewProductDataFetcher;
import com.cg.shoppingcart.product.datafetcher.AddProductToCartDataFetcher;
import com.cg.shoppingcart.product.datafetcher.AllCartsDataFetcher;
import com.cg.shoppingcart.product.datafetcher.AllProductsDataFetcher;
import com.cg.shoppingcart.product.datafetcher.CartDataFetcher;
import com.cg.shoppingcart.product.datafetcher.DeleteProductByIdDataFetcher;
import com.cg.shoppingcart.product.datafetcher.DeleteProductsDataFetcher;
import com.cg.shoppingcart.product.datafetcher.ProductDataFetcher;
import com.cg.shoppingcart.product.datafetcher.UpdateProductDataFetcher;
import com.cg.shoppingcart.product.repository.ProductRepo;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Autowired
	ProductRepo productRepo;

	@Value("classpath:products.graphql")
	Resource resource;

	private GraphQL graphQL;
	@Autowired
	private AllProductsDataFetcher allProductsDataFetcher;
	@Autowired
	private ProductDataFetcher productDataFetcher;

	@Autowired
	private AllCartsDataFetcher allCartsDataFetcher;

	@Autowired
	private DeleteProductByIdDataFetcher deleteProductByIdDataFetcher;

	@Autowired
	private DeleteProductsDataFetcher deleteProductsDataFetcher;

	@Autowired
	private UpdateProductDataFetcher updateProductDataFetcher;

	@Autowired
	private CartDataFetcher cartDataFetcher;

	@Autowired
	private AddNewProductDataFetcher addNewProductDataFetcher;

	@Autowired
	private AddProductToCartDataFetcher addProductToCart;

	// load schema at application start up
	@PostConstruct
	private void loadSchema() throws IOException {

		// Load Books into the Book Repository
//		loadDataIntoHSQL();
//
		// get the schema
		File schemaFile = resource.getFile();
//		InputStream schemaFile = resource.getInputStream();

		// parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {

		return RuntimeWiring.newRuntimeWiring()
				.type("Query",
						typeWiring -> typeWiring.dataFetcher("allProducts", allProductsDataFetcher)
								.dataFetcher("product", productDataFetcher).dataFetcher("allCarts", allCartsDataFetcher)
								.dataFetcher("cart", cartDataFetcher))
				.type("Mutation",
						typeWiring -> typeWiring.dataFetcher("createProduct", addNewProductDataFetcher)
								.dataFetcher("deleteProduct", deleteProductByIdDataFetcher)
								.dataFetcher("deleteAllProducts", deleteProductsDataFetcher)
								.dataFetcher("updateProductName", updateProductDataFetcher)
								.dataFetcher("addProductToCart", addProductToCart))
				.build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
