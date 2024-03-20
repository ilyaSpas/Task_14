package org.example.objectmapper.service.Imp;

import org.example.objectmapper.entity.Order;
import org.example.objectmapper.exception.OrderNotFoundException;
import org.example.objectmapper.repository.OrderRepository;
import org.example.objectmapper.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Long id, Order order) {
        Order orderFromDB = getById(id);
        orderFromDB.setCustomer(order.getCustomer());
        orderFromDB.setProducts(order.getProducts());
        orderFromDB.setOrderDate(order.getOrderDate());
        orderFromDB.setShippingAddress(order.getShippingAddress());
        orderFromDB.setOrderStatus(order.getOrderStatus());
        return orderRepository.save(orderFromDB);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
