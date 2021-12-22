package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.AcademicClass;
import com.SoftwareDevelopment.TrComp.models.AcademicGroup;
import com.SoftwareDevelopment.TrComp.models.Student;
import com.SoftwareDevelopment.TrComp.repositories.AcademicClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AcademicClassService {
    @Autowired
    AcademicClassRepository repository;
    public Optional<AcademicClass> findById(Integer id) {
        return repository.findById(id);
    }

    public Iterable<AcademicClass> findAll() {
        return repository.findAll();
    }

    public AcademicClass save(AcademicClass i) {
        return repository.save(i);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Optional<AcademicClass> findByAcademicGroupAndClassDate(AcademicGroup academicGroup, LocalDateTime localDateTime){
        return repository.findByAcademicGroupAndClassDate(academicGroup, localDateTime);
    }
    public boolean existsById(Integer id){
        return repository.existsById(id);
    }

}