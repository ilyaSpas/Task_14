package org.example.objectmapper.service.Imp;

import org.example.objectmapper.entity.Product;
import org.example.objectmapper.exception.ProductNotFoundException;
import org.example.objectmapper.repository.ProductRepository;
import org.example.objectmapper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Long id, Product product) {
        Product productFromDB = getById(id);
        productFromDB.setName(product.getName());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setQuantityInStock(product.getQuantityInStock());
        return productRepository.save(productFromDB);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
