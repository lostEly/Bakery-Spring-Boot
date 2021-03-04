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

    public Userr(String userrName, String userrLastName, String login, String password, Role role, String email, Boolean enabled) {
        this.userrName = userrName;
        this.userrLastName = userrLastName;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.enabled = enabled;
    }
}
