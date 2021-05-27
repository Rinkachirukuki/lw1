package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Vehicle;
import com.SoftwareDevelopment.TrComp.repositories.VehicleSqlRepository;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    VehicleSqlRepository vehicleRepository;

    public Vehicle findById(Integer id) {
        return vehicleRepository.findById(id);
    }

    public Iterable<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }

    public void save(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    public void deleteById(Integer id){
        vehicleRepository.deleteById(id);
    }

}
