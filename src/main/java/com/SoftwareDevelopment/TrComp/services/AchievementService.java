package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Achievement;
import com.SoftwareDevelopment.TrComp.models.Customer;
import com.SoftwareDevelopment.TrComp.repositories.AchievementSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AchievementService {
    @Autowired
    AchievementSqlRepository repository;

    public Achievement findById(Integer id) {
        return repository.findById(id);
    }

    public Iterable<Achievement> findAll() {
        return repository.findAll();
    }

    public void save(Achievement achievement) {
        repository.save(achievement);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }
}

