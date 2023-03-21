package com.wojciech.rithaler.prommtchallenge.payment;

import com.wojciech.rithaler.prommtchallenge.payment.DTO.PaymentDTO;
import com.wojciech.rithaler.prommtchallenge.payment.Entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentDtoCreator {
    public PaymentDTO createDto(Payment payment) {
        return new PaymentDTO(
                payment.getID(),
                payment.getCreated_date(),
                payment.getPayer_email(),
                payment.getStatus(),
                payment.getCurrency(),
                payment.getAmount(),
                payment.getPaid_date()
        );
    }
}
