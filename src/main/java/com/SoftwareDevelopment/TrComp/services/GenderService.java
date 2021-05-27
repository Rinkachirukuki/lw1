package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Gender;
import com.SoftwareDevelopment.TrComp.models.Mark;
import com.SoftwareDevelopment.TrComp.repositories.GenderSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenderService {
    @Autowired
    GenderSqlRepository genderRepository;

    public Gender findById(String id) {
        return genderRepository.findById(id);
    }

    public Iterable<Gender> findAll() {
        return genderRepository.findAll();
    }

    public void save(Gender mark) {
        genderRepository.save(mark);
    }


}

