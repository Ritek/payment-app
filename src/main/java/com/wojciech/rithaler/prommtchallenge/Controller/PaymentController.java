package com.wojciech.rithaler.prommtchallenge.Controller;

import com.wojciech.rithaler.prommtchallenge.DTO.PaymentDTO;
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
        return paymentService.createPayment(newPaymentDTO);
    }

    @GetMapping("/{paymentId}")
    ResponseEntity<PaymentDTO> getPayment(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @PutMapping("/{paymentId}")
    ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long paymentId) {
        return paymentService.updatePayment(paymentId);
    }

    @DeleteMapping("/{paymentId}")
    ResponseEntity<String> deletePayment(@PathVariable Long paymentId) {
        return paymentService.deletePaymentById(paymentId);
    }

}
