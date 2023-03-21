package com.wojciech.rithaler.prommtchallenge.payment.DTO;

import com.wojciech.rithaler.prommtchallenge.payment.CurrencyConstraint;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
public class NewPaymentDTO {
    @NonNull String payer_email;

    @NonNull
    @CurrencyConstraint
    String currency;

    @NonNull
    @Setter
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal amount;
}
