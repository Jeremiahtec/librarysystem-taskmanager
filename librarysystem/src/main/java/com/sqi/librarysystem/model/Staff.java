package com.sqi.librarysystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "staff")
@Getter
@Setter
public class Staff extends User {

    @Column(name = "staff_number", unique = true)
    private String staffNumber;

    private String position;
}