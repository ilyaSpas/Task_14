package org.example.objectmapper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.objectmapper.entity.Customer;
import org.example.objectmapper.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerController(CustomerService customerService, ObjectMapper objectMapper) {
        this.customerService = customerService;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @PostMapping("/new")
    public ResponseEntity<Customer> saveUser(@RequestBody String customerJson) {
        return new ResponseEntity<>(customerService.save(
                objectMapper.readValue(customerJson, Customer.class)),
                HttpStatus.CREATED);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(
                customerService.getById(id)),
                HttpStatus.CREATED);
    }

    @SneakyThrows
    @GetMapping()
    public ResponseEntity<String> findAll() {
        return new ResponseEntity<>(objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(
                customerService.getAll()),
                HttpStatus.OK);
    }

    @SneakyThrows
    @PutMapping("/{id}/update")
    public ResponseEntity<Customer> update(@PathVariable("id") Long id, @RequestBody String customerJson) {
        return new ResponseEntity<>(
                customerService.update(id, objectMapper.readValue(customerJson, Customer.class)),
                HttpStatus.OK);
    }

    @SneakyThrows
    @DeleteMapping("/{id}/delete")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        customerService.deleteById(id);
        return HttpStatus.OK;
    }
}
