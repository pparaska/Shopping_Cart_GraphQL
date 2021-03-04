package com.cg.shoppingcart.order.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.cg.shoppingcart.order.datafetcher.CancelOrderDataFetcher;
import com.cg.shoppingcart.order.datafetcher.CreateOrderDataFetcher;
import com.cg.shoppingcart.order.datafetcher.OrderDataFetcher;
import com.cg.shoppingcart.order.repository.OrderRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Autowired
	OrderRepository orderRepo;

	@Value("classpath:order.graphql")
	Resource resource;

	private GraphQL graphQL;

	@Autowired
	private OrderDataFetcher orderDataFetcher;

	@Autowired
	private CreateOrderDataFetcher createOrderDataFetcher;

	@Autowired
	private CancelOrderDataFetcher cancelOrderDataFetcher;

	// load schema at application start up
	@PostConstruct
	private void loadSchema() throws IOException {

		// get the schema
		File schemaFile = resource.getFile();
		// parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring.dataFetcher("order", orderDataFetcher))
				.type("Mutation", typeWiring -> typeWiring.dataFetcher("placeOrder", createOrderDataFetcher)
						.dataFetcher("deleteOrder", cancelOrderDataFetcher))
				.build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
