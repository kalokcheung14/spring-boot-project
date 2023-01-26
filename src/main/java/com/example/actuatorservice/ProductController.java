package com.example.actuatorservice;

import com.example.actuatorservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@RestController
public class ProductController {
    private static final Logger LOG = Logger.getLogger(String.valueOf(ProductController.class));
    @Autowired
    ProductService productService;

    @Value("${spring.application.name}")
    private String name;

    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public ServiceInfo name() {
        LOG.info("Index API is calling");
        return new ServiceInfo(name);
    }

    @GetMapping("/hello-world")
    public Greeting sayHello(@RequestParam(name="name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/products")
    public List<Product> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productService.getProduct(id);
    }

    @PutMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/product/search")
    public List<Product> searchProduct(@RequestParam String name) {
        return productService.searchProduct(name);
    }
}
