package com.test.bakery.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orderr")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    Date dateOfOrder;
    Date dateOfCompletion;
    Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;


}
