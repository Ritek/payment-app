package com.wojciech.rithaler.prommtchallenge.Service;

import com.wojciech.rithaler.prommtchallenge.DTO.PaymentDTO;
import com.wojciech.rithaler.prommtchallenge.Entity.Payment;
import com.wojciech.rithaler.prommtchallenge.Entity.Status;
import com.wojciech.rithaler.prommtchallenge.PaymentBuilder;
import com.wojciech.rithaler.prommtchallenge.Repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    Payment EXAMPLE_PAYMENT = new Payment(
            1L,
            ZonedDateTime.now(),
            "email@email.com",
            Status.UNPAID,
            Currency.getInstance(Locale.US),
            420.69,
            null
    );

    @Mock
    PaymentRepository paymentRepository;
    PaymentBuilder paymentBuilder;
    PaymentService paymentService;

    @BeforeEach
    void init() {
        paymentBuilder = new PaymentBuilder();
        paymentService = new PaymentService(paymentRepository, paymentBuilder);
    }

    @Test
    void testEquals() {
        Payment payment2 = new Payment(
                2L,
                ZonedDateTime.now(),
                "email@test.com",
                Status.UNPAID,
                Currency.getInstance("EUR"),
                69.0,
                null
        );

        assertEquals(EXAMPLE_PAYMENT, payment2);
    }

    @Test
    void shouldFindPayment() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(EXAMPLE_PAYMENT));
        ResponseEntity<PaymentDTO> result = paymentService.getPaymentById(1L);

        // then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldNotFindPayment() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<PaymentDTO> result = paymentService.getPaymentById(1L);

        // then
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldUpdatePayment() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(EXAMPLE_PAYMENT));
        ResponseEntity<PaymentDTO> result = paymentService.updatePayment(1L);

        // then
        assertNotNull(Objects.requireNonNull(result.getBody()));
    }

    @Test
    void shouldNotUpdatePayment() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<PaymentDTO> result = paymentService.updatePayment(1L);

        // then
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldSuccessfullyDeletePayment() {
        // when
        when(paymentRepository.deleteByIDAndStatus(1L, Status.UNPAID)).thenReturn(1);
        ResponseEntity<String> result = paymentService.deletePaymentById(1L);

        // then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldUnsuccessfullyDeletePayment() {
        // when
        when(paymentRepository.deleteByIDAndStatus(1L, Status.UNPAID)).thenReturn(0);
        ResponseEntity<String> result = paymentService.deletePaymentById(1L);

        // then
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
