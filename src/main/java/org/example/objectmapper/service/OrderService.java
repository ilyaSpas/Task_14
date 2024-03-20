package org.example.objectmapper.service;

import org.example.objectmapper.entity.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order getById(Long id);

    List<Order> getAll();

    Order update(Long id, Order order);

    void deleteById(Long id);
}
