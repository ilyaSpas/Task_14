package org.example.objectmapper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.objectmapper.entity.Product;
import org.example.objectmapper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductController(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<Product> saveUser(@RequestBody String productJson){
        return new ResponseEntity<>(productService.save(productJson), HttpStatus.CREATED);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<String> saveUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(objectMapper.writeValueAsString(productService.get(id)) , HttpStatus.CREATED);
    }
}
