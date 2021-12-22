package com.SoftwareDevelopment.TrComp.models;

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
    public AcademicGroup academicGroup;

    @ManyToOne
    @JoinColumn(name = "audience_code")
    public Audience audience;

    @ManyToOne
    @JoinColumn(name = "discipline_code")
    public AcademicDiscipline academicDiscipline;

    @ManyToOne
    @JoinColumn(name = "teacher_code")
    public Teacher teacher;

    public AcademicClass() {}
}
