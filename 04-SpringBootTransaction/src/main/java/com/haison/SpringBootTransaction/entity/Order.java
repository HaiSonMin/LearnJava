package com.haison.SpringBootTransaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "shopping_cart_id")
    private long shoppingCartId;
    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;
    @Column(name = "total_quantity")
    private int totalQuantity;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "status")
    private String status;
    @Column(name = "date_created")
    @CreationTimestamp // Don't need enter value when use post api in postman
    private LocalDateTime dateCreated;
    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

}
