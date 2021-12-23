package com.SoftwareDevelopment.TrComp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AcademicClass {

    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer classId;

    @Column(name = "class_date")
    public LocalDateTime classDate;

    @ManyToOne
    @JoinColumn(name = "group_code")
    public AcademicGroup academicGroup = new AcademicGroup();

    @ManyToOne
    @JoinColumn(name = "audience_code")
    public Audience audience = new Audience();

    @ManyToOne
    @JoinColumn(name = "discipline_code")
    public AcademicDiscipline academicDiscipline = new AcademicDiscipline();

    @ManyToOne
    @JoinColumn(name = "teacher_code")
    public Teacher teacher = new Teacher();

    public AcademicClass() {}
}
