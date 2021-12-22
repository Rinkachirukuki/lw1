package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.AcademicGroup;
import com.SoftwareDevelopment.TrComp.repositories.AcademicGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcademicGroupService {
    @Autowired
    AcademicGroupRepository repository;
    public Optional<AcademicGroup> findById(String id) {
        return repository.findById(id);
    }

    public Iterable<AcademicGroup> findAll() {
        return repository.findAll();
    }

    public AcademicGroup save(AcademicGroup i) {
        return repository.save(i);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public boolean existsById(String id){
        return repository.existsById(id);
    }

}