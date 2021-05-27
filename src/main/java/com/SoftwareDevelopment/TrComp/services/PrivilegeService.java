package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Mark;
import com.SoftwareDevelopment.TrComp.models.Privilege;
import com.SoftwareDevelopment.TrComp.repositories.PrivilegeSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrivilegeService {
    @Autowired
    PrivilegeSqlRepository privilegeRepository;

    public Privilege findById(String id) {
        return privilegeRepository.findById(id);
    }

    public Iterable<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

    public void save(Privilege mark) {
        privilegeRepository.save(mark);
    }
}

