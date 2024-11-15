package com.dk.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends BaseModal {
    private String name;
    private String email;
    private String password;
}
