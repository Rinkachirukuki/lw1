package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class AClass {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_code")
    private Integer code;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "a_group")
    private AGroup AGroup;

    @ManyToOne
    @JoinColumn(name = "discipline")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "audience_code")
    private Audience audience;

    public AClass() {}
}
