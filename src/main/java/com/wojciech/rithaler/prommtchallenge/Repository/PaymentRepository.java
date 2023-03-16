package com.wojciech.rithaler.prommtchallenge.Repository;

import com.wojciech.rithaler.prommtchallenge.Entity.Payment;
import com.wojciech.rithaler.prommtchallenge.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    int deleteByIDAndStatus(Long paymentId, Status status);
}
