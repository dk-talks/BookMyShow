package com.dk.bookmyshow.Modal;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class BaseModal {
    @Id(GeneratedValue = GenerationType.IDENTITY)
    private int id;
    private Date createdAt;
    private Date modifiedAt;
}
