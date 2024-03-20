package org.example.objectmapper.service.Imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.objectmapper.entity.Product;
import org.example.objectmapper.exception.ProductNotFoundException;
import org.example.objectmapper.repository.ProductRepository;
import org.example.objectmapper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Product save(String productJson) {
        Product product;
        try {
            JsonNode jsonNode = objectMapper.readTree(productJson);
            product = Product.builder()
                    .name(jsonNode.get("name").asText())
                    .description(jsonNode.get("description").asText())
                    .price(jsonNode.get("price").asDouble())
                    .quantityInStock(jsonNode.get("quantityInStock").asLong())
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return productRepository.save(product);
    }

    @SneakyThrows
    @Override
    public String get(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return objectMapper.writeValueAsString(product);
    }
}
