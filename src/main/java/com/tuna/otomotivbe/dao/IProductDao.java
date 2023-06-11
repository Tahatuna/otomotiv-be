package com.tuna.otomotivbe.dao;

import com.tuna.otomotivbe.entities.Product;
import com.tuna.otomotivbe.entities.Purchase;

import java.util.List;
import java.util.Optional;

public interface IProductDao {

    Optional<Product> getById(Long id);

    void save(Product product);

    void delete(Product product);

    List<Product> getAllProducts();

}
