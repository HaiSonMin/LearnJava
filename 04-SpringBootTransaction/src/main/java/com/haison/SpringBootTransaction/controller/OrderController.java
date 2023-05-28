package com.haison.SpringBootTransaction.controller;

import com.haison.SpringBootTransaction.dto.OrderRequest;
import com.haison.SpringBootTransaction.dto.OrderResponse;
import com.haison.SpringBootTransaction.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("orders")
    public OrderResponse orderResponse(@RequestBody OrderRequest orderRequest) {
        return this.orderService.placeOrder(orderRequest);
    }
}
