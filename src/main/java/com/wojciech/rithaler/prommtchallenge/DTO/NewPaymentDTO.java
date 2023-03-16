package com.wojciech.rithaler.prommtchallenge.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Currency;

@Getter
@AllArgsConstructor
@ToString
public class NewPaymentDTO {
    @NonNull String payer_email;
    @NonNull Currency currency;
    @NonNull Double amount;
}
