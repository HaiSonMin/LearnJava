package com.haison.SpringBootTransaction.service.impl;

import com.haison.SpringBootTransaction.dto.OrderRequest;
import com.haison.SpringBootTransaction.dto.OrderResponse;
import com.haison.SpringBootTransaction.entity.Order;
import com.haison.SpringBootTransaction.entity.Payment;
import com.haison.SpringBootTransaction.exception.PaymentException;
import com.haison.SpringBootTransaction.repository.OrderRepository;
import com.haison.SpringBootTransaction.repository.PaymentRepository;
import com.haison.SpringBootTransaction.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    @Override
    @Transactional(rollbackFor = PaymentException.class) // When transaction have exc then RollBack
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();

        order.setStatus("InProgress");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());

        this.orderRepository.save(order);

        Payment payment = orderRequest.getPayment();
        payment.setOrderId(order.getId());

        if(!payment.getType().equals("Credit")) throw new PaymentException("Payment card type do not support!!!");

        this.paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatus(order.getStatus());
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setMessage("SUCCESSFULLY");

        return orderResponse;

    }
}
