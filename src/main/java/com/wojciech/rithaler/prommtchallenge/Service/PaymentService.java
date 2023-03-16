package com.wojciech.rithaler.prommtchallenge.Service;

import com.wojciech.rithaler.prommtchallenge.DTO.NewPaymentDTO;
import com.wojciech.rithaler.prommtchallenge.DTO.PaymentDTO;
import com.wojciech.rithaler.prommtchallenge.Entity.Payment;
import com.wojciech.rithaler.prommtchallenge.Entity.Status;
import com.wojciech.rithaler.prommtchallenge.PaymentBuilder;
import com.wojciech.rithaler.prommtchallenge.Repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {
    private PaymentRepository paymentRepository;
    private PaymentBuilder paymentBuilder;

    public ResponseEntity<PaymentDTO> createPayment(NewPaymentDTO newPaymentDto) {
        Payment payment = paymentRepository.save(paymentBuilder.create(newPaymentDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(payment.createDto());
    }

    public ResponseEntity<PaymentDTO> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).map(
                payment -> ResponseEntity.status(HttpStatus.OK).body(payment.createDto()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        );
    }

    public ResponseEntity<PaymentDTO> updatePayment(Long paymentId) {
        Optional<Payment> found = paymentRepository.findById(paymentId);

        return found.map(
            payment -> {
                Payment updated = update(payment);
                paymentRepository.save(updated);
                return ResponseEntity.status(HttpStatus.OK).body(updated.createDto());
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        );
    }

    @Transactional
    public ResponseEntity<String> deletePaymentById(Long paymentId) {
        int deleted = paymentRepository.deleteByIDAndStatus(paymentId, Status.UNPAID);

        if (deleted == 1) return ResponseEntity.status(HttpStatus.OK).body("Payment deleted!");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment of provided ID does not exists or was payed!");
    }

    public Payment update(Payment payment) {
        payment.setStatus(Status.PAID);
        payment.setPaid_date(ZonedDateTime.now());
        return payment;
    }

}
