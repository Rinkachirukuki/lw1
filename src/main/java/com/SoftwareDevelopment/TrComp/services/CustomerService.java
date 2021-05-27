package com.SoftwareDevelopment.TrComp.services;

import com.SoftwareDevelopment.TrComp.models.Customer;
import com.SoftwareDevelopment.TrComp.repositories.CustomerSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerSqlRepository customerRepository;
    public Customer findById(Integer id) {
        return customerRepository.findById(id);
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void save(Customer customer) {

        customerRepository.save(customer);
    }

    public void deleteById(Integer id){
        customerRepository.deleteById(id);
    }
}

