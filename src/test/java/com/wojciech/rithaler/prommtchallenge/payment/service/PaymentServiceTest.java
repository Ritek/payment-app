package com.wojciech.rithaler.prommtchallenge.payment.service;

import com.wojciech.rithaler.prommtchallenge.payment.dto.DeletePaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.dto.PaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.entity.Payment;
import com.wojciech.rithaler.prommtchallenge.payment.entity.Status;
import com.wojciech.rithaler.prommtchallenge.payment.exception.PaymentException;
import com.wojciech.rithaler.prommtchallenge.payment.PaymentBuilder;
import com.wojciech.rithaler.prommtchallenge.payment.PaymentDtoCreator;
import com.wojciech.rithaler.prommtchallenge.payment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.*;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    Payment EXAMPLE_UNPAID_PAYMENT = new Payment(
            1L,
            1L,
            LocalDateTime.now(),
            "email@email.com",
            Status.UNPAID,
            Currency.getInstance(Locale.US),
            new BigDecimal("19.99"),
            null
    );

    Payment EXAMPLE_PAID_PAYMENT = new Payment(
            1L,
            1L,
            LocalDateTime.now(),
            "email@email.com",
            Status.PAID,
            Currency.getInstance(Locale.US),
            new BigDecimal("19.99"),
            LocalDateTime.now()
    );

    @Mock
    PaymentRepository paymentRepository;
    PaymentBuilder paymentBuilder;
    PaymentService paymentService;
    PaymentDtoCreator paymentDtoCreator;

    @BeforeEach
    void init() {
        Clock clock = Clock.fixed(Instant.parse("2023-01-01T10:00:00Z"), ZoneOffset.UTC);
        paymentBuilder = new PaymentBuilder(clock);
        paymentDtoCreator = new PaymentDtoCreator();
        paymentService = new PaymentService(paymentRepository, paymentBuilder, paymentDtoCreator, clock);
    }

    @Test
    void shouldUpdatePayment() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(EXAMPLE_UNPAID_PAYMENT));
        when(paymentRepository.save(EXAMPLE_UNPAID_PAYMENT)).thenReturn(EXAMPLE_UNPAID_PAYMENT);
        Optional<PaymentDto> result = paymentService.updatePayment(1L);

        // then
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotFindPaymentToUpdate() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<PaymentDto> result = paymentService.updatePayment(1L);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingPaidPayment() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(EXAMPLE_PAID_PAYMENT));
        Exception exception = assertThrows(PaymentException.class, () -> {
            Optional<PaymentDto> result = paymentService.updatePayment(1L);
        });

        // then
        assertEquals(exception.getMessage(), "Payment was already paid!");
    }

    @Test
    void shouldSuccessfullyDeletePayment() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(EXAMPLE_UNPAID_PAYMENT));
        Optional<DeletePaymentDto> result = paymentService.deletePaymentById(1L);

        // then
        assertEquals(1, result.get().getDeletedId());
    }

    @Test
    void shouldNotFindPaymentToDelete() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<DeletePaymentDto> result = paymentService.deletePaymentById(1L);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenDeletingPaidPayment() {
        // when
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(EXAMPLE_PAID_PAYMENT));
        Exception exception = assertThrows(PaymentException.class, () -> {
            Optional<DeletePaymentDto> result = paymentService.deletePaymentById(1L);
        });

        // then
        assertEquals(exception.getMessage(), "Payment was already paid!");
    }
}
