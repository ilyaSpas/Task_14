package org.example.objectmapper.controller;

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

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/new")
    public ResponseEntity<Product> saveUser(@RequestBody String productJson){
        return new ResponseEntity<>(productService.save(productJson), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> saveUser(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.get(id), HttpStatus.CREATED);
    }
}
