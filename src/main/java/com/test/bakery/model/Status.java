package com.test.bakery.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "status")
@EntityListeners(AuditingEntityListener.class)
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long statusId;

    String statusName;
}
