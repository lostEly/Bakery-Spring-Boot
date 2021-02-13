package com.test.bakery.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;

    String productName;
    Double price;
    String description;

    Integer count;

    @Column(columnDefinition = "text")
    String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}