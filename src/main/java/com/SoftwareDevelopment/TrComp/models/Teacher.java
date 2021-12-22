package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Teacher {
    @Id
    @Column(name = "teacher_code")
    public String teacherCode;

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

    public Teacher() {}
}
