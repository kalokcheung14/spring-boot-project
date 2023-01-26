package com.example.actuatorservice.db.repository;

import com.example.actuatorservice.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    int count();

    int save(Product product);

    List<Product> findAll();

    Optional<Product> findById(int id);

    Optional<Product> findLatest();
}
