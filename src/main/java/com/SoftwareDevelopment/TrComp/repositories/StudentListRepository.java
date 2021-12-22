package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.StudentList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentListRepository extends
        CrudRepository<StudentList, Integer> {
};