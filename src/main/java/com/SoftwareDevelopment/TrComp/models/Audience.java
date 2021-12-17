package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Audience {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "audience_code")
    private Integer code;

    @Column(name = "audience_name")
    private String aName;

    public Audience() {}
}
