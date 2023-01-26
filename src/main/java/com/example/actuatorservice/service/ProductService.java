package com.example.actuatorservice.service;

import com.example.actuatorservice.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product createProduct(Product product);
    Product getProduct(int id);
    List<Product> getProductList();
    List<Product> searchProduct(String name);
}
