package com.wojciech.rithaler.prommtchallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojciech.rithaler.prommtchallenge.payment.dto.DeletePaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.dto.NewPaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.dto.PaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.entity.Status;
import com.wojciech.rithaler.prommtchallenge.payment.repository.PaymentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = PrommtChallengeApplicationIT.TestConfig.class, properties = {"spring.main.allow-bean-definition-overriding=true"})
@AutoConfigureMockMvc
class PrommtChallengeApplicationIT {
	static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			StandardCharsets.UTF_8
	);

	@TestConfiguration
	public static class TestConfig {
		@Bean
		@Primary
		Clock clock() {
			return Clock.fixed(Instant.parse("2023-01-01T10:00:00Z"), ZoneOffset.UTC);
		}
	}

	static final String BASE_URL = "http://127.0.0.1:8080/api/payment";

	@Autowired
	private PaymentRepository repository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	Clock clock;

	private void createPayment() throws Exception {
		NewPaymentDto newPayment = new NewPaymentDto(
				"email@email.com", 1L, "USD", new BigDecimal("19.99")
		);

		String newPaymentJson = objectMapper.writeValueAsString(newPayment);
		mockMvc.perform(post(BASE_URL)
				.contentType(APPLICATION_JSON_UTF8)
				.content(newPaymentJson))
				.andExpect(status().isCreated());
	}
	@Test
	@Order(1)
	@WithMockUser
	void postEndpointShouldCreatePayment() throws Exception {
		createPayment();

		assertTrue(repository.findById(1L).isPresent());
	}

	@Test
	@Order(2)
	@WithMockUser
	void postEndpointShouldNotCreatePaymentWithUnsupportedCurrency() throws Exception {
		NewPaymentDto newPayment = new NewPaymentDto(
				"email@email.com", 1L, "USD2", new BigDecimal("19.99")
		);

		String newPaymentJson = objectMapper.writeValueAsString(newPayment);

		mockMvc.perform(post(BASE_URL)
				.contentType(APPLICATION_JSON_UTF8)
				.content(newPaymentJson))
				.andExpect(status().isBadRequest());

	}

	@Test
	@Order(3)
	@WithMockUser
	void postEndpointShouldNotCreatePaymentWithWrongAmount() throws Exception {
		NewPaymentDto newPayment = new NewPaymentDto(
				"email@email.com", 1L, "USD2", new BigDecimal("0.0")
		);

		String newPaymentJson = objectMapper.writeValueAsString(newPayment);

		mockMvc.perform(post(BASE_URL)
				.contentType(APPLICATION_JSON_UTF8)
				.content(newPaymentJson))
				.andExpect(status().isBadRequest());

	}

	@Test
	@Order(4)
	@WithMockUser
	void getEndpointShouldRetrievePayment() throws Exception {
		String url = BASE_URL + "/1";

		PaymentDto paymentDTO = new PaymentDto(
				1L, LocalDateTime.now(clock), "email@email.com",
				Status.UNPAID, Currency.getInstance("USD"),
				BigDecimal.valueOf(19.99), null
		);

		String paymentJson = objectMapper.writeValueAsString(paymentDTO);

		mockMvc.perform(get(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().json(paymentJson));
	}

	@Test
	@Order(5)
	@WithMockUser
	void getEndpointShouldReturnNotFound() throws Exception {
		String url = BASE_URL + "/2";
		mockMvc.perform(get(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(6)
	@WithMockUser
	void putEndpointShouldUpdatePayment() throws Exception {
		String url = BASE_URL + "/1";

		PaymentDto paymentDTO = new PaymentDto(
				1L, LocalDateTime.now(clock), "email@email.com",
				Status.PAID, Currency.getInstance("USD"),
				BigDecimal.valueOf(19.99), LocalDateTime.now(clock)
		);

		String paymentJson = objectMapper.writeValueAsString(paymentDTO);

		mockMvc.perform(put(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().json(paymentJson));
	}

	@Test
	@Order(7)
	@WithMockUser
	void putEndpointShouldNotFindPayment() throws Exception {
		String url = BASE_URL + "/2";

		mockMvc.perform(put(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(8)
	@WithMockUser
	void deleteEndpointShouldNotFindPayment() throws Exception {
		String url = BASE_URL + "/2";

		mockMvc.perform(delete(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(9)
	@WithMockUser
	void deleteEndpointShouldNotDeletePaidPayment() throws Exception {
		String url = BASE_URL + "/1";

		MvcResult resultResponse = mockMvc.perform(delete(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest())
				.andReturn();

		String responseJson = resultResponse.getResponse().getContentAsString();
	}

	@Test
	@Order(10)
	@WithMockUser
	void deleteEndpointShouldDeleteUnpaidPayment() throws Exception {
		String url = BASE_URL + "/2";

		createPayment();

		DeletePaymentDto deletePaymentDTO= new DeletePaymentDto(2L);
		String deletePaymentJson = objectMapper.writeValueAsString(deletePaymentDTO);

		mockMvc.perform(delete(url)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().json(deletePaymentJson));
	}

}
