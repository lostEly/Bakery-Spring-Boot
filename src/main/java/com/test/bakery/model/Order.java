package com.test.bakery.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "orderr")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;
    @Column(nullable = false)
    Date dateOfOrder;
    Date dateOfCompletion;
    Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;


    @ManyToOne
    @JoinColumn(name = "userr_id")
    private Userr userr;

    public Order() {
        this.dateOfOrder = new Date();
        this.status = new Status();
        this.status.setStatusId(2L); // по дефолту установить статус как новый
    }
}
