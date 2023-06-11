package com.tuna.otomotivbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuna.otomotivbe.constants.DbConstant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = DbConstant.TBL_USERS, uniqueConstraints = {@UniqueConstraint(columnNames = DbConstant.COL_USER_NAME), @UniqueConstraint(columnNames = DbConstant.COL_EMAIL)})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbConstant.COL_ID)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = DbConstant.COL_USER_NAME)
    private String username;

    @NotBlank
    @Size(max = 20)
    @Column(name = DbConstant.COL_USER_FIRST_NAME)
    private String userFirstName;

    @NotBlank
    @Size(max = 20)
    @Column(name = DbConstant.COL_USER_LAST_NAME)
    private String userLastName;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = DbConstant.COL_EMAIL)
    private String email;

    @Column(name = DbConstant.COL_IS_EMAIL_VERIFIED)
    private boolean isEmailVerified;

    @Column(name = DbConstant.COL_VERIFICATION_TOKEN)
    private String verificationToken;

    @Column(name = DbConstant.COL_LOGIN_ATTEMPTS)
    private int loginAttempts;

    @Column(name = DbConstant.COL_BLOCKED)
    @JsonIgnore
    private boolean blocked;

    @NotBlank
    @Size(max = 120)
    @Column(name = DbConstant.COL_PASSWORD)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = DbConstant.TBL_USER_ROLES, joinColumns = @JoinColumn(name = DbConstant.COL_USER_ID),
            inverseJoinColumns = @JoinColumn(name = DbConstant.COL_ROLE_ID))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Purchase> purchases = new HashSet<>();

    public User(String username, String userFirstName, String userLastName, String email, boolean isEmailVerified, String verificationToken, String password) {
        this.username = username;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.verificationToken = verificationToken;
        this.password = password;
        this.loginAttempts = 0;
        this.blocked = false;
    }

    public User() {

    }

}

