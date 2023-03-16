package com.wojciech.rithaler.prommtchallenge.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wojciech.rithaler.prommtchallenge.DTO.PaymentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.Currency;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@EqualsAndHashCode
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    @NonNull
    @EqualsAndHashCode.Exclude
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    ZonedDateTime created_date;
    @NonNull String payer_email;
    Status status;
    @NonNull Currency currency;
    @NonNull Double amount;
    @EqualsAndHashCode.Exclude
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    ZonedDateTime paid_date;

//    public PaymentDTO createDto() {
//        return new PaymentDTO(
//                this.ID,
//                this.created_date,
//                this.payer_email,
//                this.status,
//                this.currency,
//                this.amount,
//                this.paid_date
//        );
//    }
}
