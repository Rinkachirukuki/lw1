package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.StudentList;
import com.SoftwareDevelopment.TrComp.repositories.StudentListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentListService {
    @Autowired
    StudentListRepository repository;
    public Optional<StudentList> findById(Integer id) {
        return repository.findById(id);
    }

    public Iterable<StudentList> findAll() {
        return repository.findAll();
    }

    public StudentList save(StudentList i) {
        return repository.save(i);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public boolean existsById(Integer id){
        return repository.existsById(id);
    }

}