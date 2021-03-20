package com.test.bakery.model;


import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "otp")
@EntityListeners(AuditingEntityListener.class)
public class OrderToProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long otpId;
    @Column(nullable = false)
    int amount;
    @Column(nullable = false)
    Double cost;

    @ManyToOne
    @JoinColumn(name="order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;

    public OrderToProduct(int amount, Double cost, Order order, Product product) {
        this.amount = amount;
        this.cost = cost;
        this.order = order;
        this.product = product;
    }

    public OrderToProduct() {
    }
}
