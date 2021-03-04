package com.cg.shoppingcart.payment.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.cg.shoppingcart.payment.datafetcher.CreatePaymentDataFetcher;
import com.cg.shoppingcart.payment.datafetcher.PaymentDataFetcher;
import com.cg.shoppingcart.payment.repository.PaymentRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Autowired
	PaymentRepository paymentRepository;

	@Value("classpath:payments.graphql")
	Resource resource;

	private GraphQL graphQL;

	@Autowired
	private PaymentDataFetcher paymentDataFetcher;

	@Autowired
	private CreatePaymentDataFetcher createPaymentDataFetcher;

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
		return RuntimeWiring
				.newRuntimeWiring().type("Query", typeWiring -> typeWiring
						.dataFetcher("payment", paymentDataFetcher))
				.type("Mutation", typeWiring -> typeWiring.dataFetcher("createPayment", createPaymentDataFetcher))
				.build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
