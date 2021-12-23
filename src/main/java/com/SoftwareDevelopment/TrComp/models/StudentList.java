package com.SoftwareDevelopment.TrComp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class StudentList {
    @Id
    @Column(name = "student_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer studentListId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "class_id")
    public AcademicClass academicClass = new AcademicClass();

    @ManyToOne
    @JoinColumn(name = "student_code")
    public Student student = new Student();

    @Column(name = "status")
    public String status;

    public StudentList() {}
}
