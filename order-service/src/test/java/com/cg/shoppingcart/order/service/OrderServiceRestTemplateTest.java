package com.cg.shoppingcart.order.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceRestTemplateTest {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void searchProductByIdTest() throws Exception {

		String expected = "{\"productId\":0,\"productName\":\"XioMi-Android\",\"productPrice\":{\"currency\":\"Euro\",\"price\":123.2},\"description\":\"Latest Android phone with great user reviews\",\"type\":\"Electronics\"},{\"productId\":1,\"productName\":\"Wireless-Charger\",\"productPrice\":{\"currency\":\"Euro\",\"price\":9.8},\"description\":\"Wireless charger with great reviews\",\"type\":\"Electronics\"}";

		ResponseEntity<String> response = restTemplate.getForEntity("/order/searchProductById/0",
				String.class);

		printJSON(response);

		assertEquals("text/plain;charset=UTF-8", response.getHeaders().getContentType().toString());

		JSONAssert.assertEquals(expected, response.getBody().toString(), false);

	}


	private static void printJSON(Object object) {
		String result;
		try {
			result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			System.out.println(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
