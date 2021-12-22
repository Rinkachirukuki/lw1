package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Audience;
import com.SoftwareDevelopment.TrComp.repositories.AudienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AudienceService {
    @Autowired
    AudienceRepository repository;
    public Optional<Audience> findById(String id) {
        return repository.findById(id);
    }

    public Iterable<Audience> findAll() {
        return repository.findAll();
    }

    public Audience save(Audience i) {
        return repository.save(i);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public boolean existsById(String id){
        return repository.existsById(id);
    }

}