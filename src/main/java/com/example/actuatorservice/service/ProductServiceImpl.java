package com.example.actuatorservice.service;

import com.example.actuatorservice.Product;
import com.example.actuatorservice.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{
    private static final Map<Integer, Product> products = new HashMap<>();
    static {
        Product honey = new Product(1, "Honey", "Sweet Honey");
        products.put(1, honey);

        Product almond = new Product(2, "Almond", "Tasty Almond");
        products.put(2, almond);
    }

    @Override
    public Product createProduct(Product product) {
        return new Product(3, product.getName(), product.getDescription());
    }

    @Override
    public Product getProduct(int id) {
        // Handle case when product ID is not found
        if (!products.containsKey(id)) throw new ProductNotFoundException();
        return products.get(id);
    }

    @Override
    public List<Product> getProductList() {
        return products.values().stream().toList();
    }
}
