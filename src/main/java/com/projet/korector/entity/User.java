package com.projet.korector.entity;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity @Inheritance(strategy=InheritanceType.JOINED)
@Table(	name = "Users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    @Size(max = 20)
    private String username;


    @Column(name = "prenom")
    @Size(max = 20)
    private String firstName;

    @Column(name = "nom")
    @Size(max = 20)
    private String lastName;

    @NotBlank
    @Column(name = "email")

    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Column(name = "motDePasse")

    @Size(max = 120)
    private String password;
/*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }*/

    public User(String username, String firstName,String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;

        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Session{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", firstname='").append(firstName).append('\'');
        sb.append(", lastname='").append(firstName).append('\'');
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }



}
