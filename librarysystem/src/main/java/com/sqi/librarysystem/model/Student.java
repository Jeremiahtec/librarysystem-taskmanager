package com.sqi.librarysystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends User {

    @Column(name = "student_number", unique = true)
    private String studentNumber;

    private String department;
}