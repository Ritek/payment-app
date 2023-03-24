package com.wojciech.rithaler.prommtchallenge.payment.repository;

import com.wojciech.rithaler.prommtchallenge.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByCustomerId(Long customerId);
}
