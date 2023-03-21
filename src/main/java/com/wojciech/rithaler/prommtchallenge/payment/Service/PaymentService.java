package com.wojciech.rithaler.prommtchallenge.payment.Service;

import com.wojciech.rithaler.prommtchallenge.payment.DTO.DeletePaymentDTO;
import com.wojciech.rithaler.prommtchallenge.payment.DTO.NewPaymentDTO;
import com.wojciech.rithaler.prommtchallenge.payment.DTO.PaymentDTO;
import com.wojciech.rithaler.prommtchallenge.payment.Entity.Payment;
import com.wojciech.rithaler.prommtchallenge.payment.Entity.Status;
import com.wojciech.rithaler.prommtchallenge.payment.Exception.PaymentException;
import com.wojciech.rithaler.prommtchallenge.payment.PaymentBuilder;
import com.wojciech.rithaler.prommtchallenge.payment.PaymentDtoCreator;
import com.wojciech.rithaler.prommtchallenge.payment.Repository.PaymentRepository;
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

    public PaymentDTO createPayment(NewPaymentDTO newPaymentDto) {
        // newPaymentDto.setAmount(newPaymentDto.getAmount().setScale(2, RoundingMode.DOWN));
        Payment payment = paymentRepository.save(paymentBuilder.create(newPaymentDto));
        return paymentDtoCreator.createDto(payment);
    }

    public Optional<PaymentDTO> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).map(paymentDtoCreator::createDto);
    }

    public Optional<PaymentDTO> updatePayment(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> {
                    validateUnpaidPayment(payment);
                    payment.setStatus(Status.PAID);
                    payment.setPaid_date(LocalDateTime.now(clock));
                    Payment result = paymentRepository.save(payment);
                    return paymentDtoCreator.createDto(result);
                });
    }

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
}