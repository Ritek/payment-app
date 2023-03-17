package com.wojciech.rithaler.prommtchallenge.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@AllArgsConstructor
@ToString
public class NewPaymentDTO {
    @NonNull String payer_email;
    @NonNull String currency;

    @NonNull
    @Setter
    BigDecimal amount;
}
