package com.wojciech.rithaler.prommtchallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojciech.rithaler.prommtchallenge.Repository.PaymentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class PrommtChallengeApplicationTests {
	static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			StandardCharsets.UTF_8
	);

	static final String BASE_URL = "http://127.0.0.1:8080/api/payment";

	@Autowired
	private PaymentRepository repository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {}

	private void createPayment() throws Exception {
		String newPaymentJson = "{\"payer_email\": \"email@email.com\", \"currency\": \"USD\", \"amount\": 420.69}";
		mockMvc.perform(post(BASE_URL)
				.contentType(APPLICATION_JSON_UTF8)
				.content(newPaymentJson))
				.andExpect(status().isCreated());
	}
	@Test
	@Order(1)
	void postEndpointShouldCreatePayment() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		createPayment();

		assertTrue(repository.findById(1L).isPresent());
	}

	@Test
	@Order(2)
	void getEndpointShouldRetrievePayment() throws Exception {
		String url = BASE_URL + "/1";

		MvcResult resultResponse = mockMvc.perform(get(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn();

		String responseJson = resultResponse.getResponse().getContentAsString();
		assertTrue(responseJson.contains("\"id\":1"));
	}

	@Test
	@Order(3)
	void getEndpointShouldReturnNotFound() throws Exception {
		String url = BASE_URL + "/2";
		mockMvc.perform(get(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(4)
	void putEndpointShouldUpdatePayment() throws Exception {
		String url = BASE_URL + "/1";

		MvcResult resultResponse = mockMvc.perform(put(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn();

		String responseJson = resultResponse.getResponse().getContentAsString();
		assertTrue(responseJson.contains("\"id\":1") && responseJson.contains("\"status\":\"PAID\""));
	}

	@Test
	@Order(5)
	void putEndpointShouldNotFindPayment() throws Exception {
		String url = BASE_URL + "/2";

		mockMvc.perform(put(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(6)
	void deleteEndpointShouldNotFindPayment() throws Exception {
		String url = BASE_URL + "/2";

		mockMvc.perform(delete(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(7)
	void deleteEndpointShouldNotDeletePaidPayment() throws Exception {
		String url = BASE_URL + "/1";

		MvcResult resultResponse = mockMvc.perform(delete(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest())
				.andReturn();

		String responseJson = resultResponse.getResponse().getContentAsString();
		System.out.println(">>>" + responseJson);
	}

	@Test
	@Order(8)
	void deleteEndpointShouldDeleteUnpaidPayment() throws Exception {
		String url = BASE_URL + "/2";

		createPayment();

		MvcResult resultResponse = mockMvc.perform(delete(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn();

		String responseJson = resultResponse.getResponse().getContentAsString();
		assertEquals("2", responseJson);
	}

}
