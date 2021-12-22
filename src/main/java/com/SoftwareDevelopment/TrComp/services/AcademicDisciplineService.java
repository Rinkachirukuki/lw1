package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.AcademicDiscipline;
import com.SoftwareDevelopment.TrComp.repositories.AcademicDisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcademicDisciplineService {
    @Autowired
    AcademicDisciplineRepository repository;
    public Optional<AcademicDiscipline> findById(String id) {
        return repository.findById(id);
    }

    public Iterable<AcademicDiscipline> findAll() {
        return repository.findAll();
    }

    public AcademicDiscipline save(AcademicDiscipline i) {
        return repository.save(i);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public boolean existsById(String id){
       return repository.existsById(id);
    }

}