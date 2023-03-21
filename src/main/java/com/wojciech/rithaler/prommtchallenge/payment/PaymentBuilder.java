package com.wojciech.rithaler.prommtchallenge.payment;

import com.wojciech.rithaler.prommtchallenge.payment.dto.NewPaymentDto;
import com.wojciech.rithaler.prommtchallenge.payment.entity.Payment;
import com.wojciech.rithaler.prommtchallenge.payment.entity.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Currency;

@Component
@AllArgsConstructor
public class PaymentBuilder {
    private Clock clock;
    public Payment create(NewPaymentDto newPaymentDTO) {
        return Payment.builder()
                .createdDate(LocalDateTime.now(clock))
                .payerEmail(newPaymentDTO.getPayer_email())
                .status(Status.UNPAID)
                .currency(Currency.getInstance(newPaymentDTO.getCurrency()))
                .amount(newPaymentDTO.getAmount().setScale(2, RoundingMode.DOWN))
                .paidDate(null)
                .build();
    }
}
