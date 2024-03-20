package org.example.objectmapper.service.Imp;

import org.example.objectmapper.entity.Customer;
import org.example.objectmapper.exception.CustomerNotFoundException;
import org.example.objectmapper.repository.CustomerRepository;
import org.example.objectmapper.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer customerFromDB = getById(id);
        customerFromDB.setFirstName(customerFromDB.getFirstName());
        customerFromDB.setLastName(customer.getLastName());
        customerFromDB.setEmail(customer.getEmail());
        customerFromDB.setContactNumber(customer.getContactNumber());
        customerFromDB.setOrders(customer.getOrders());
        return customerRepository.save(customerFromDB);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
