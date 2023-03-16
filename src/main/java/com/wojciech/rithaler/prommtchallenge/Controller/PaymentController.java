package com.wojciech.rithaler.prommtchallenge.Controller;

import com.wojciech.rithaler.prommtchallenge.DTO.DeletePaymentDTO;
import com.wojciech.rithaler.prommtchallenge.DTO.PaymentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import com.wojciech.rithaler.prommtchallenge.DTO.NewPaymentDTO;
import com.wojciech.rithaler.prommtchallenge.Service.PaymentService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/payment")
public class PaymentController {
    PaymentService paymentService;

    @PostMapping
    ResponseEntity<PaymentDTO> getPayment(@RequestBody NewPaymentDTO newPaymentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(newPaymentDTO));
    }

    @GetMapping("/{paymentId}")
    ResponseEntity<PaymentDTO> getPayment(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId).map(
            paymentDto -> ResponseEntity.status(HttpStatus.OK).body(paymentDto))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        );
    }

    @PutMapping("/{paymentId}")
    ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long paymentId) {
        return paymentService.updatePayment(paymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{paymentId}")
    ResponseEntity<DeletePaymentDTO> deletePayment(@PathVariable Long paymentId) {
        return paymentService.deletePaymentById(paymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}
