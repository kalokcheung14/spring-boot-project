package com.example.actuatorservice.db.repository;

import com.example.actuatorservice.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from products where name LIKE %:name%", nativeQuery = true)
    List<Product> findProductByName(@Param("name") String name);
}
