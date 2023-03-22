package com.wojciech.rithaler.prommtchallenge.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    @NonNull
    Role role;
    @NonNull
    @NotEmpty
    String name;
    @NonNull
    @NotEmpty
    String surname;
    @NonNull
    @NotEmpty
    String email;
    @NonNull
    @NotEmpty
    String password;
}
