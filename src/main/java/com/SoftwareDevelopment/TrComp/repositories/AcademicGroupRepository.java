package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.AcademicGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicGroupRepository extends
        CrudRepository<AcademicGroup, String> {
};