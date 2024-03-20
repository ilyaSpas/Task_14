package org.example.objectmapper.service;

import org.example.objectmapper.entity.Product;

public interface ProductService {

    Product save(String product);

    String get(Long id);

    String getAll();

    Product update(Long id, String product);
}
