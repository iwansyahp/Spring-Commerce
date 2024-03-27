package com.springcommerce.userservice.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(schema = "public", name = "users")
public class User {
    @Id
    private Long id;
    private UUID uuid = UUID.randomUUID();
    @Column("email")
    private String email;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;

    public User() {
    }

    public User(UUID uuid, String email, String firstName, String lastName) {
        this.email = email;
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email,String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

}
