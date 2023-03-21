package com.wojciech.rithaler.prommtchallenge.payment.Repository;

import com.wojciech.rithaler.prommtchallenge.payment.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> { }
