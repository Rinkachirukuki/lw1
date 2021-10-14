package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Priority;
import com.SoftwareDevelopment.TrComp.repositories.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriorityService {
    @Autowired
    PriorityRepository repository;

    public Priority findById(int id) {
        Optional<Priority> result = repository.findById(id);
        Priority n = null;
        if (result.isPresent()) {
            n = result.get();
        } else {
            throw new RuntimeException("Didn't find");
        }
        return n;
    }

    public Iterable<Priority> findAll() {
        return repository.findAll();
    }

    public Iterable<Priority> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Iterable<Priority> findAll(Sort sort) {
        return repository.findAll(sort);
    }
    public void deleteById(Integer id){
        repository.deleteById(id);
    }
    public void save(Priority priority) {
        repository.save(priority);
    }
}

