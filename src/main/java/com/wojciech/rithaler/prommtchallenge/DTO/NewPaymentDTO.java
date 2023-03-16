package com.wojciech.rithaler.prommtchallenge.DTO;

import com.wojciech.rithaler.prommtchallenge.Entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Currency;

@Getter
@AllArgsConstructor
@ToString
public class NewPaymentDTO {
    String payer_email;
    Currency currency;
    Double amount;
}
