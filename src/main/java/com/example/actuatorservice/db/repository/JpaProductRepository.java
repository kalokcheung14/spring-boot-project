package com.example.actuatorservice.db.repository;

import com.example.actuatorservice.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<Product, Integer> {

}
