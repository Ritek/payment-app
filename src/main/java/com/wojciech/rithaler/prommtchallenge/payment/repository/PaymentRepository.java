package com.wojciech.rithaler.prommtchallenge.payment.repository;

import com.wojciech.rithaler.prommtchallenge.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> { }
