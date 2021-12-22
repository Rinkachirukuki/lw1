package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.AcademicDiscipline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicDisciplineRepository extends
        CrudRepository<AcademicDiscipline, String> {
};