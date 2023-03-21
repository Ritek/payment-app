package com.wojciech.rithaler.prommtchallenge.payment.service;

import com.wojciech.rithaler.prommtchallenge.payment.dto.DeletePaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.dto.NewPaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.dto.PaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.entity.Payment;
import com.wojciech.rithaler.prommtchallenge.payment.entity.Status;
import com.wojciech.rithaler.prommtchallenge.payment.exception.PaymentException;
import com.wojciech.rithaler.prommtchallenge.payment.PaymentBuilder;
import com.wojciech.rithaler.prommtchallenge.payment.PaymentDtoCreator;
import com.wojciech.rithaler.prommtchallenge.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {
    private PaymentRepository paymentRepository;
    private PaymentBuilder paymentBuilder;
    private PaymentDtoCreator paymentDtoCreator;
    private Clock clock;

    public PaymentDto createPayment(NewPaymentDto newPaymentDto) {
        // newPaymentDto.setAmount(newPaymentDto.getAmount().setScale(2, RoundingMode.DOWN));
        Payment payment = paymentRepository.save(paymentBuilder.create(newPaymentDto));
        return paymentDtoCreator.createDto(payment);
    }

    public Optional<PaymentDto> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).map(paymentDtoCreator::createDto);
    }

    public Optional<PaymentDto> updatePayment(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> {
                    validateUnpaidPayment(payment);
                    payment.setStatus(Status.PAID);
                    payment.setPaidDate(LocalDateTime.now(clock));
                    Payment result = paymentRepository.save(payment);
                    return paymentDtoCreator.createDto(result);
                });
    }

    public Optional<DeletePaymentDto> deletePaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> {
                    validateUnpaidPayment(payment);
                    paymentRepository.delete(payment);
                    return new DeletePaymentDto(payment.getID());
                });
    }

    private void validateUnpaidPayment(Payment payment) {
        if (payment.getStatus().equals(Status.PAID)) throw new PaymentException("Payment was already paid!");
    }
}
