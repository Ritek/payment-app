package com.wojciech.rithaler.prommtchallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojciech.rithaler.prommtchallenge.Entity.Payment;
import com.wojciech.rithaler.prommtchallenge.Entity.Status;
import com.wojciech.rithaler.prommtchallenge.Repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Currency;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	void contextLoads() {
	}

	@Test
	void postEndpointShouldCreatePayment() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String newPaymentJson = "{\"payer_email\": \"email@email.com\", \"currency\": \"USD\", \"amount\": 420.69}";
		mockMvc.perform(post(BASE_URL)
				.contentType(APPLICATION_JSON_UTF8)
				.content(newPaymentJson))
				.andExpect(status().isCreated());
	}

	@Test
	void getEndpointShouldRetrievePayment() throws Exception {
		String url = BASE_URL + "/1";
		Payment expectedResult =
				new Payment(1L, ZonedDateTime.now(), "email@email.com",
						Status.UNPAID, Currency.getInstance("USD"), 420.69, null);

		MvcResult result = mockMvc.perform(get(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(resultBody -> resultBody.equals(expectedResult))
				.andReturn();
	}

	@Test
	void getEndpointShouldReturnNotFound() throws Exception {
		String url = BASE_URL + "/2";
		mockMvc.perform(get(url).contentType(APPLICATION_JSON_UTF8)).andExpect(status().isNotFound());
	}

}
