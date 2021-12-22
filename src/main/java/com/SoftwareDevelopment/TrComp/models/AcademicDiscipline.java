package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;

@Entity
public class AcademicDiscipline {

    @Id
    @Column(name = "discipline_code")
    public String disciplineCode;

    @Column(name = "discipline_name")
    public String disciplineName;

    public AcademicDiscipline() {}
}
