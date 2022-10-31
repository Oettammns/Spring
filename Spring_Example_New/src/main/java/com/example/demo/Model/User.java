package com.example.demo.Model;

import jdk.jfr.DataAmount;
import jdk.jfr.Name;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@Pattern(regexp = "^[a-zA-Z]")
    @NotNull(message = "mandatory insert name")
    @Column(name = "first_name")
    private String firstName;

    //@Pattern(regexp = "^[a-zA-Z]")
    @NotNull(message = "mandatory insert surname")
    @Column(name = "last_name")
    private String lastName;
    //questo dovrebbe permettere la giusta sintassi
    @Email
    @Column(name = "email")
    private String email;

    public User() {}

    public User(String firstName, String lastName, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
