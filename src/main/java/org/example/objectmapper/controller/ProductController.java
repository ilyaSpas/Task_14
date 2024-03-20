package org.example.objectmapper.controller;

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

    @SneakyThrows
    @PostMapping("/new")
    public ResponseEntity<Product> saveUser(@RequestBody String productJson) {
        return new ResponseEntity<>(productService.save(objectMapper.readValue(productJson, Product.class)), HttpStatus.CREATED);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(productService.getById(id)), HttpStatus.CREATED);
    }

    @SneakyThrows
    @GetMapping()
    public ResponseEntity<String> findAll() {
        return new ResponseEntity<>(objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(productService.getAll()), HttpStatus.OK);
    }

    @SneakyThrows
    @PutMapping("/{id}/update")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody String productJson) {
        return new ResponseEntity<>(
                productService.update(id, objectMapper.readValue(productJson, Product.class)), HttpStatus.OK);
    }

    @SneakyThrows
    @DeleteMapping("/{id}/delete")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return HttpStatus.OK;
    }
}
