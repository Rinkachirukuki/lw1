package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Driver;
import com.SoftwareDevelopment.TrComp.repositories.DriverSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {

    public DriverService(DriverSqlRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    DriverSqlRepository driverRepository;

    public Driver findById(Integer id) {
        return driverRepository.findById(id);
    }

    public Iterable<Driver> findAll() {
        return driverRepository.findAll();
    }

    public void save(Driver driver) {
        driverRepository.save(driver);
    }

    public void deleteById(Integer id){
        driverRepository.deleteById(id);
    }
}

