package com.example.actuatorservice;

import com.example.actuatorservice.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ProductController {
    @Value("${spring.application.name}")
    private String name;

    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    private static final Map<Integer, Product> products = new HashMap<>();
    static {
        Product honey = new Product(1, "Honey", "Sweet Honey");
        products.put(1, honey);

        Product almond = new Product(2, "Almond", "Tasty Almond");
        products.put(2, almond);
    }

    @GetMapping("/")
    public ServiceInfo name() {
        return new ServiceInfo(name);
    }

    @GetMapping("/hello-world")
    public Greeting sayHello(@RequestParam(name="name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/products")
    public List<Product> getProductList() {
        return products.values().stream().toList();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        // Handle case when product ID is not found
        if (!products.containsKey(id)) throw new ProductNotFoundException();
        return products.get(id);
    }

    @PutMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return new Product(3, product.getName(), product.getDescription());
    }
}
