package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends
        CrudRepository<Teacher, String> {

    Optional<Teacher> findByPhoneNumber(String phoneNumber);
    Optional<Teacher> findByEmail(String email);
};