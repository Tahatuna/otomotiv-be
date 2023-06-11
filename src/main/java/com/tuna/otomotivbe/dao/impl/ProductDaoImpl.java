package com.tuna.otomotivbe.dao.impl;

import com.tuna.otomotivbe.dao.IProductDao;
import com.tuna.otomotivbe.entities.Product;
import com.tuna.otomotivbe.entities.Purchase;
import com.tuna.otomotivbe.repository.ProductRepository;
import com.tuna.otomotivbe.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoImpl implements IProductDao {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productRepository.delete(product);
    }

}
