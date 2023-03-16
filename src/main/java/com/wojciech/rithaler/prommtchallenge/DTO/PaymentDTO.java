package com.wojciech.rithaler.prommtchallenge.DTO;

import com.wojciech.rithaler.prommtchallenge.Entity.Status;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Currency;

@AllArgsConstructor
@Getter
@ToString
public class PaymentDTO {
    @NonNull Long ID;
    @NonNull ZonedDateTime created_date;
    @NonNull String payer_email;
    @NonNull Status status;
    @NonNull Currency currency;
    @NonNull Double amount;
    ZonedDateTime paid_date;
}
