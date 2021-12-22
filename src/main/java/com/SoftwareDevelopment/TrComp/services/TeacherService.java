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
    public Optional<Teacher> findByPhoneNumber(String i) {
        return repository.findByPhoneNumber(i);
    }
    public Optional<Teacher> findByEmail(String i) {
        return repository.findByEmail(i);
    }

    public Iterable<Teacher> findAll() {
        return repository.findAll();
    }

    public Teacher save(Teacher i) {
        return repository.save(i);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public boolean existsById(String id){
        return repository.existsById(id);
    }

}