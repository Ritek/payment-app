package com.wojciech.rithaler.prommtchallenge.payment.dto;

import com.wojciech.rithaler.prommtchallenge.payment.entity.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class PaymentDto {
    @NonNull Long ID;
    @NonNull LocalDateTime created_date;
    @NonNull String payer_email;
    @NonNull Status status;
    @NonNull Currency currency;
    @NonNull BigDecimal amount;
    LocalDateTime paid_date;
}
