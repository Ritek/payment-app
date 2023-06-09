package com.wojciech.rithaler.prommtchallenge.payment.dto;

import com.wojciech.rithaler.prommtchallenge.payment.CurrencyConstraint;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
public class NewPaymentDto {
    @NonNull String payerEmail;

    @NonNull Long customerId;

    @NonNull
    @CurrencyConstraint
    String currency;

    @NonNull
    @Setter
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal amount;
}
