package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Report;
import com.SoftwareDevelopment.TrComp.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    ReportRepository repository;

    public Report findById(Integer id) {
        Optional<Report> result = repository.findById(id);
        Report n = null;
        if(result.isPresent()) {
            n = result.get();
        } else {
            throw new RuntimeException("Didn't find");
        }
        return n;
    }

    public Iterable<Report> findAll(){
        return repository.findAll();
    }
    public Iterable<Report> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public Iterable<Report> findAll(Sort sort){
        return repository.findAll(sort);

    }

    public void save(Report vehicle){
        repository.save(vehicle);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }
}
