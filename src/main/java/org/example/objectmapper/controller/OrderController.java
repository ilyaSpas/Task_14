package org.example.objectmapper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.objectmapper.entity.Customer;
import org.example.objectmapper.entity.Order;
import org.example.objectmapper.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    private final ObjectMapper objectMapper;

    @Autowired
    public OrderController(OrderService orderService, ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @PostMapping("/new")
    public ResponseEntity<Order> saveUser(@RequestBody String orderJson) {
        return new ResponseEntity<>(orderService.save( objectMapper.readValue(orderJson, Order.class)), HttpStatus.CREATED);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(orderService.getById(id)), HttpStatus.CREATED);
    }

    @SneakyThrows
    @GetMapping()
    public ResponseEntity<String> findAll() {
        return new ResponseEntity<>(objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(orderService.getAll()), HttpStatus.OK);
    }

    @SneakyThrows
    @PutMapping("/{id}/update")
    public ResponseEntity<Order> update(@PathVariable("id") Long id, @RequestBody String orderJson) {
        return new ResponseEntity<>(
                orderService.update(id, objectMapper.readValue(orderJson, Order.class)), HttpStatus.OK);
    }

    @SneakyThrows
    @DeleteMapping("/{id}/delete")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return HttpStatus.OK;
    }
}
