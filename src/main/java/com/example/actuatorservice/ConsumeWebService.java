package com.example.actuatorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;

@RestController
public class ConsumeWebService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${server.port}")
    private int port;

    @GetMapping("/template/products")
    public String getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                "http://localhost:" + port + "/products",
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();
    }

    @PutMapping("/template/product")
    public String createProduct(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<>(product, headers);

        return restTemplate.exchange(
                "http://localhost:" + port + "/product",
                HttpMethod.PUT,
                entity,
                String.class
        ).getBody();
    }

    @GetMapping("/template/itunes")
    public ItunesSearchResult  getItunesList(@RequestParam("keyword") String keyword) {

        ItunesSearchResult result = restTemplate.getForObject(
                "https://itunes.apple.com/search?term=" + keyword,
                ItunesSearchResult.class
        );

        assert result != null;
        // Further sort result by trackId
        result.results().sort(Comparator.comparingInt(ItunesDataItem::trackId));

        return result;
    }
}
