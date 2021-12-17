package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;

@Entity
public class AGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "group_code")
    private Integer code;

    @Column(name = "group_name")
    private String gName;

    public AGroup() {}
}
