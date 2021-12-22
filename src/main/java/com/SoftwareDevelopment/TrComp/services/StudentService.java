package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Student;
import com.SoftwareDevelopment.TrComp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository repository;
    public Optional<Student> findById(String id) {
        return repository.findById(id);
    }
    public Optional<Student> findByPhoneNumber(String i) {
        return repository.findByPhoneNumber(i);
    }
    public Optional<Student> findByEmail(String i) {
        return repository.findByEmail(i);
    }

    public Iterable<Student> findAll() {
        return repository.findAll();
    }

    public Student save(Student i) {
        return repository.save(i);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public boolean existsById(String id){
        return repository.existsById(id);
    }

}