package com.SoftwareDevelopment.TrComp.models.answers;

import com.SoftwareDevelopment.TrComp.models.Student;

public class AcademicGroupStatistics {
    public Student student = new Student();

    public int classVisits;
    public float visitPercentage;
    public float reasonableMissesPercentage;

    public AcademicGroupStatistics() {
    }
}
