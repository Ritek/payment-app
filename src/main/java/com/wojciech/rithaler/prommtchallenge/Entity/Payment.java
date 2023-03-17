package com.wojciech.rithaler.prommtchallenge.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;

    @NonNull
    LocalDateTime created_date;

    @NonNull String payer_email;

    @Setter Status status;

    @NonNull
    Currency currency;

    @NonNull
    BigDecimal amount;

    @Setter
    LocalDateTime paid_date;
}
