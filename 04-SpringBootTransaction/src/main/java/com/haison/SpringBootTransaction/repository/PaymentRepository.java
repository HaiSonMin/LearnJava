package com.haison.SpringBootTransaction.repository;

import com.haison.SpringBootTransaction.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
