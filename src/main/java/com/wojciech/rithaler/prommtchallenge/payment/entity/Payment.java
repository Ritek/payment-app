package com.wojciech.rithaler.prommtchallenge.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NonNull
    private LocalDateTime createdDate;

    @NonNull
    private String payerEmail;

    @NotNull
    private Status status;

    @NonNull
    private Currency currency;

    @NonNull
    private BigDecimal amount;

    private LocalDateTime paidDate;
}
