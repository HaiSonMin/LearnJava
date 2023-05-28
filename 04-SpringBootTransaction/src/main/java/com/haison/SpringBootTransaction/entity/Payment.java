package com.haison.SpringBootTransaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "type")
    private String type;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expire_year")
    private int expireYear;
    @Column(name = "expire_month")
    private int expireMonth;
    @Column(name = "cvc")
    private int cvc;
    @Column(name = "order_id")
    private long orderId;

}
