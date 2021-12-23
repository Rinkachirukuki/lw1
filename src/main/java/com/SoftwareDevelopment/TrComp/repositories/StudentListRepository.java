package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.AcademicClass;
import com.SoftwareDevelopment.TrComp.models.AcademicGroup;
import com.SoftwareDevelopment.TrComp.models.Student;
import com.SoftwareDevelopment.TrComp.models.StudentList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface StudentListRepository extends
        CrudRepository<StudentList, Integer> {

    Optional<StudentList> findByAcademicClassAndStudent(AcademicClass academicClass, Student student);
};