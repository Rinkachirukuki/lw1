package com.SoftwareDevelopment.TrComp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Student {

    @Id
    @Column(name = "student_code")
    public String studentCode;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "patronymic")
    public String patronymic;

    @Column(name = "birth_date")
    public LocalDate birthDate;

    @Column(name = "email")
    public String email;

    @Column(name = "phone_number")
    public String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "group_code")
    public AcademicGroup academicGroup;

    public Student() {}
}
