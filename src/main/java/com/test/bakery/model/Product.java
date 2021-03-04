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
    private Long productId;
    private String productName;
    private Double price;
    private String description;
    private Integer count;

    @Column(columnDefinition = "text")
    private String image;

    public Product() {
    }

    public Product(String productName, Double price, String description, Integer count, String image, Category category) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.count = count;
        this.image = image;
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}