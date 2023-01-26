package com.example.actuatorservice.service;

import com.example.actuatorservice.Product;
import com.example.actuatorservice.ProductController;
import com.example.actuatorservice.db.repository.JpaProductRepository;
import com.example.actuatorservice.db.repository.ProductRepository;
import com.example.actuatorservice.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService{
    private static final Logger LOG = Logger.getLogger(String.valueOf(ProductService.class));

//    @Autowired
//    @Qualifier("jdbcProductRepository")
//    private ProductRepository productRepository;

    @Autowired
    JpaProductRepository jpaProductRepository;

    @Override
    public Product createProduct(Product product) {
        return jpaProductRepository.save(product);
    }

    @Override
    public Product getProduct(int id) {
        // Handle case when product ID is not found
        Optional<Product> product = jpaProductRepository.findById(id);
        LOG.info(product.toString());
        if (product.isEmpty()) throw new ProductNotFoundException();
        return product.get();
    }

    @Override
    public List<Product> getProductList() {
        return jpaProductRepository.findAll();
    }

    @Override
    public List<Product> searchProduct(String name) {
        return jpaProductRepository.findProductByName(name);
    }
}
