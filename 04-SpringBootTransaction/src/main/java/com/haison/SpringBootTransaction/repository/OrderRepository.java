package com.haison.SpringBootTransaction.repository;

import com.haison.SpringBootTransaction.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
