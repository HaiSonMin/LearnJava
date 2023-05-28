package com.haison.SpringBootTransaction.service;

import com.haison.SpringBootTransaction.dto.OrderRequest;
import com.haison.SpringBootTransaction.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
