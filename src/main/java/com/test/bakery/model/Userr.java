package com.test.bakery.model;


import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.Pattern;


@Data
@Entity
@Table(name = "userr")
@EntityListeners(AuditingEntityListener.class)
public class Userr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userrId;

    @Column(nullable = false)
    private String userrName;

    @Column(nullable = false)
    private String userrLastName;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Userr() {
        this.setEnabled(false);
    }
    @Column(nullable = false)
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
