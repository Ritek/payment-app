package com.wojciech.rithaler.prommtchallenge.payment.DTO;

import com.wojciech.rithaler.prommtchallenge.payment.Entity.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class PaymentDTO {
    @NonNull Long ID;
    @NonNull LocalDateTime created_date;
    @NonNull String payer_email;
    @NonNull Status status;
    @NonNull Currency currency;
    @NonNull BigDecimal amount;
    LocalDateTime paid_date;
}
