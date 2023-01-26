package com.example.actuatorservice.service;

import com.example.actuatorservice.Product;
import com.example.actuatorservice.ProductController;
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

    @Autowired
    @Qualifier("jdbcProductRepository")
    private ProductRepository productRepository;

    private static final Map<Integer, Product> products = new HashMap<>();
    static {
        Product honey = new Product(1, "Honey", "Sweet Honey");
        products.put(1, honey);

        Product almond = new Product(2, "Almond", "Tasty Almond");
        products.put(2, almond);
    }

    @Override
    public Product createProduct(Product product) {
        productRepository.save(product);
        Optional<Product> productLatest = productRepository.findLatest();
        if (productLatest.isEmpty()) throw new ProductNotFoundException();
        return productLatest.get();
    }

    @Override
    public Product getProduct(int id) {
        // Handle case when product ID is not found
        Optional<Product> product = productRepository.findById(id);
        LOG.info(product.toString());
        if (product.isEmpty()) throw new ProductNotFoundException();
        return product.get();
    }

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }
}
