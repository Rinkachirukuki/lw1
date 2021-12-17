package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;

@Entity
public class Discipline {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "discipline_code")
    private Integer code;

    @Column(name = "discipline_name")
    private String dName;

    public Discipline() {}
}
