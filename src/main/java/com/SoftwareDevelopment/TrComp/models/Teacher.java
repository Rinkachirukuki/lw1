package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Teacher {
    @Id
    @Column(name = "teacher_code")
    public String teacher_code;

    @Column(name = "first_name")
    public String first_name;

    @Column(name = "last_name")
    public String last_name;

    @Column(name = "patronymic")
    public String patronymic;

    @Column(name = "birth_date")
    public LocalDate birth_date;

    @Column(name = "email")
    public String email;

    @Column(name = "phone_number")
    public String phone_number;

    public Teacher() {}
}
