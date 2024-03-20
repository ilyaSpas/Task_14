package org.example.objectmapper.service;

import org.example.objectmapper.entity.Order;
import org.example.objectmapper.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product getById(Long id);

    List<Product> getAll();

    Product update(Long id, Product product);

    void deleteById(Long id);
}
