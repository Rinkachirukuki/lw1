package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_code")
    private Integer code;

    @Column(name = "last_name")
    private String lName;

    @Column(name = "first_name")
    private String fName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birth_day")
    private Date bDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String pNumber;

    @ManyToOne
    @JoinColumn(name = "group_code")
    private AGroup AGroup;

    public Student() {}
}
