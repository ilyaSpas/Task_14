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

import java.util.List;

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

    @Override
    public String get(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        String productJson;
        try {
            productJson = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return productJson;
    }

    @SneakyThrows
    @Override
    public String getAll() {
        List<Product> products = productRepository.findAll();
        return objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(products);
    }

    @Override
    public Product update(Long id, String product) {
        Product newProduct;
        Product productFromDB = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        try {
            newProduct = objectMapper.readValue(product, Product.class);
            productFromDB.setName(newProduct.getName());
            productFromDB.setDescription(newProduct.getDescription());
            productFromDB.setPrice(newProduct.getPrice());
            productFromDB.setQuantityInStock(newProduct.getQuantityInStock());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return productRepository.save(productFromDB);
    }
}
