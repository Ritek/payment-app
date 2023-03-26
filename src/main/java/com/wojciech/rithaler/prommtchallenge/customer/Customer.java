package com.wojciech.rithaler.prommtchallenge.customer;

import com.wojciech.rithaler.prommtchallenge.payment.entity.Payment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @NonNull
    private Role role;
    @NonNull
    @NotEmpty
    private String name;
    @NonNull
    @NotEmpty
    private String surname;
    @NonNull
    @NotEmpty
    private String email;
    @NonNull
    @NotEmpty
    private String password;
}
