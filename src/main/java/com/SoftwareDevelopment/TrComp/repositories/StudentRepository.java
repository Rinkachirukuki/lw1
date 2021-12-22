package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends
        CrudRepository<Student, String> {

    Optional<Student> findByPhoneNumber(String phoneNumber);
    Optional<Student> findByEmail(String email);
};