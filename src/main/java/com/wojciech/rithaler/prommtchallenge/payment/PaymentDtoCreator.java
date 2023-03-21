package com.wojciech.rithaler.prommtchallenge.payment;

import com.wojciech.rithaler.prommtchallenge.payment.dto.PaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentDtoCreator {
    public PaymentDto createDto(Payment payment) {
        return new PaymentDto(
                payment.getID(),
                payment.getCreatedDate(),
                payment.getPayerEmail(),
                payment.getStatus(),
                payment.getCurrency(),
                payment.getAmount(),
                payment.getPaidDate()
        );
    }
}
