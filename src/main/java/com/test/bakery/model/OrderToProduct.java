package com.test.bakery.model;


import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "otp")
@EntityListeners(AuditingEntityListener.class)
public class OrderToProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long otpId;

    int amount;
    Double cost;

    @ManyToOne
    @JoinColumn(name="order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;


}
