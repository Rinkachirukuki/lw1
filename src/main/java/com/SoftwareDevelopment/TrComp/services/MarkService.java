package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Mark;
import com.SoftwareDevelopment.TrComp.repositories.MarkSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarkService {
    @Autowired
    MarkSqlRepository markRepository;

    public Mark findById(String id) {

        return markRepository.findById(id);
    }

    public Iterable<Mark> findAll() {
        return markRepository.findAll();
    }

    public void save(Mark mark) {
        markRepository.save(mark);
    }
}

