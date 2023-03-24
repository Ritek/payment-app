package com.wojciech.rithaler.prommtchallenge.payment.entity;

import jakarta.persistence.*;
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
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NonNull
    private Long customerId;

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
