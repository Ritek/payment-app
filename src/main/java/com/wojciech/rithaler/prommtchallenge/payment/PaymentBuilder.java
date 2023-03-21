package com.wojciech.rithaler.prommtchallenge.payment;

import com.wojciech.rithaler.prommtchallenge.payment.DTO.NewPaymentDTO;
import com.wojciech.rithaler.prommtchallenge.payment.Entity.Payment;
import com.wojciech.rithaler.prommtchallenge.payment.Entity.Status;
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
    public Payment create(NewPaymentDTO newPaymentDTO) {
        return Payment.builder()
                .created_date(LocalDateTime.now(clock))
                .payer_email(newPaymentDTO.getPayer_email())
                .status(Status.UNPAID)
                .currency(Currency.getInstance(newPaymentDTO.getCurrency()))
                .amount(newPaymentDTO.getAmount().setScale(2, RoundingMode.DOWN))
                .paid_date(null)
                .build();
    }
}
