package org.example.objectmapper.repository;

import org.example.objectmapper.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
