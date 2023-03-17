package com.wojciech.rithaler.prommtchallenge;

import com.wojciech.rithaler.prommtchallenge.DTO.NewPaymentDTO;
import com.wojciech.rithaler.prommtchallenge.Entity.Payment;
import com.wojciech.rithaler.prommtchallenge.Entity.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
                .currency(Currency.getInstance(newPaymentDTO.getCurrency().getCurrencyCode()))
                .amount(newPaymentDTO.getAmount())
                .paid_date(null)
                .build();
    }
}
