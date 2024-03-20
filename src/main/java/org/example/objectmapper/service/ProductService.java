package org.example.objectmapper.service;

import org.example.objectmapper.entity.Product;

public interface ProductService {

    Product save(String product);

    Product get(Long id);
}
