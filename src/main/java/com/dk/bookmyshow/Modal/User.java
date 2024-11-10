package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseModal {
    private String name;
    private String email;
    private String password;
}
