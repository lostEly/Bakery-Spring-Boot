package com.test.bakery.model;


import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Data
@Entity
@Table(name = "userr")
@EntityListeners(AuditingEntityListener.class)
public class Userr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userrId;

    private String userrName;

    private String userrLastName;

    @Column
    private String login;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Userr() {
        this.setEnabled(false);
    }

    private String email;

    private Boolean enabled;

}
