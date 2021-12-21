package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Teacher;
import com.SoftwareDevelopment.TrComp.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository repository;
    public Optional<Teacher> findById(String id) {
        return repository.findById(id);
    }

    public Iterable<Teacher> findAll() {
        return repository.findAll();
    }

    public void save(Teacher i) {
        repository.save(i);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }
}