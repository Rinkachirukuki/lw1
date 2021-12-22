package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;

@Entity
public class StudentList {
    @Id
    @Column(name = "student_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer studentListId;

    @ManyToOne
    @JoinColumn(name = "class_id")
    public AcademicClass academicClass;

    @ManyToOne
    @JoinColumn(name = "student_code")
    public Student student;

    @Column(name = "status")
    public String status;

    public StudentList() {}
}
