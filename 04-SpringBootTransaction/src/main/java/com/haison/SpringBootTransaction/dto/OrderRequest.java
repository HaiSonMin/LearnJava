package com.haison.SpringBootTransaction.dto;


import com.haison.SpringBootTransaction.entity.Order;
import com.haison.SpringBootTransaction.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Order order;
    private Payment payment;
}
