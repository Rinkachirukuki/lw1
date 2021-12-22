package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AcademicGroup {

    @Id
    @Column(name = "group_code")
    public String groupCode;

    @Column(name = "group_name")
    public String groupName;

    @Column(name = "education_start_date")
    public LocalDate educationStartDate;

    public AcademicGroup() {}
}
