package com.microservices.signin;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    private String firstname;
    private String lastname;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    private Integer id;


    public User(String firstname, String lastname, String username, String password) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public User(String firstname, String lastname, String username, String password, Integer id) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
