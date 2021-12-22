package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.AcademicClass;
import com.SoftwareDevelopment.TrComp.models.AcademicGroup;
import com.SoftwareDevelopment.TrComp.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AcademicClassRepository extends
        CrudRepository<AcademicClass, Integer> {

    Optional<AcademicClass> findByAcademicGroupAndClassDate(AcademicGroup academicGroup, LocalDateTime localDateTime);
};