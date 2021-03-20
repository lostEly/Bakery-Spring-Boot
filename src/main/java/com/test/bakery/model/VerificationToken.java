package com.test.bakery.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long tokenId;
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Userr.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userr_id")
    private Userr user;

    public VerificationToken() {
        createdDate = new Date();
        token = UUID.randomUUID().toString();
        }
}
