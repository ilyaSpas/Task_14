package org.example.objectmapper.service;

import org.example.objectmapper.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);

    Customer getById(Long id);

    List<Customer> getAll();

    Customer update(Long id, Customer customer);

    void deleteById(Long id);
}
