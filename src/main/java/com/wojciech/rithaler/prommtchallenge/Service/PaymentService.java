package com.wojciech.rithaler.prommtchallenge.Service;

import com.wojciech.rithaler.prommtchallenge.DTO.DeletePaymentDTO;
import com.wojciech.rithaler.prommtchallenge.DTO.NewPaymentDTO;
import com.wojciech.rithaler.prommtchallenge.DTO.PaymentDTO;
import com.wojciech.rithaler.prommtchallenge.Entity.Payment;
import com.wojciech.rithaler.prommtchallenge.Entity.Status;
import com.wojciech.rithaler.prommtchallenge.Exception.PaymentException;
import com.wojciech.rithaler.prommtchallenge.PaymentBuilder;
import com.wojciech.rithaler.prommtchallenge.PaymentDtoCreator;
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
    private PaymentDtoCreator paymentDtoCreator;

    public PaymentDTO createPayment(NewPaymentDTO newPaymentDto) {
        Payment payment = paymentRepository.save(paymentBuilder.create(newPaymentDto));
        return paymentDtoCreator.createDto(payment);
    }

    public Optional<PaymentDTO> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).map(paymentDtoCreator::createDto);
    }

    public Optional<PaymentDTO> updatePayment(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> {
                    Payment result = paymentRepository.save(update(payment));
                    return paymentDtoCreator.createDto(result);
                });
    }

    @Transactional
    public Optional<DeletePaymentDTO> deletePaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> {
                    validateUnpaidPayment(payment);
                    paymentRepository.delete(payment);
                    return new DeletePaymentDTO(payment.getID());
                });
    }

    private void validateUnpaidPayment(Payment payment) {
        if (payment.getStatus().equals(Status.PAID)) throw new PaymentException("Payment was already paid!");
    }

    public Payment update(Payment payment) {
        payment.setStatus(Status.PAID);
        payment.setPaid_date(ZonedDateTime.now());
        return payment;
    }

}
