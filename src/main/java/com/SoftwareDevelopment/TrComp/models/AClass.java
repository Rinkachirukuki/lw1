package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;
import java.sql.Date;

public class AClass {

    public String code;
    public Date date;
    public AGroup aGroup;
    public Audience audience;
    public Discipline discipline;
    public Teacher teacher;

    public AClass() {}
}
