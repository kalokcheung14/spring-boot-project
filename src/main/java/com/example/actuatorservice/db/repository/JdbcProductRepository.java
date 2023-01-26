package com.example.actuatorservice.db.repository;

import com.example.actuatorservice.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcProductRepository implements ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        Integer integer = jdbcTemplate
                .queryForObject("select count(*) from products", Integer.class);
        if (integer == null) return -1;
        return integer;
    }

    @Override
    public int save(Product product) {
        return jdbcTemplate
                .update(
                        "insert into products (name, description) values (?,?)",
                        product.getName(),
                        product.getDescription()
                );
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products", (rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description")
        ));
    }

    @Override
    public Optional<Product> findById(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from products where id = ?",
                    (rs, rowNum) -> Optional.of(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description")
                    )), id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Product> findLatest() {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from products order by id desc limit 1",
                    (rs, rowNum) -> Optional.of(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description")
                    )));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
