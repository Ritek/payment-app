package com.wojciech.rithaler.prommtchallenge.payment.controller;

import com.wojciech.rithaler.prommtchallenge.payment.dto.DeletePaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.dto.PaymentDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import com.wojciech.rithaler.prommtchallenge.payment.dto.NewPaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.service.PaymentService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/payment")
public class PaymentController {
    PaymentService paymentService;

    @PostMapping
    ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody NewPaymentDto newPaymentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(newPaymentDTO));
    }

    @GetMapping("/{paymentId}")
    ResponseEntity<PaymentDto> getPayment(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId).map(
            paymentDto -> ResponseEntity.status(HttpStatus.OK).body(paymentDto))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        );
    }

    @PutMapping("/{paymentId}")
    ResponseEntity<PaymentDto> updatePayment(@PathVariable Long paymentId) {
        return paymentService.updatePayment(paymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{paymentId}")
    ResponseEntity<DeletePaymentDto> deletePayment(@PathVariable Long paymentId) {
        return paymentService.deletePaymentById(paymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
