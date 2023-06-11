package com.tuna.otomotivbe.services.impl;

import com.tuna.otomotivbe.dao.IProductDao;
import com.tuna.otomotivbe.dao.IPurchaseDao;
import com.tuna.otomotivbe.dao.IUserDao;
import com.tuna.otomotivbe.entities.Product;
import com.tuna.otomotivbe.entities.Purchase;
import com.tuna.otomotivbe.entities.User;
import com.tuna.otomotivbe.exceptionhandling.ResourceNotFoundException;
import com.tuna.otomotivbe.repository.ProductRepository;
import com.tuna.otomotivbe.repository.PurchaseRepository;
import com.tuna.otomotivbe.services.IProductService;
import com.tuna.otomotivbe.services.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

    @Autowired
    private IProductDao productDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IPurchaseDao purchaseDao;

    @Override
    public void purchaseProduct(String username, Long productId) {
        Product product = productDao.getById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        User user = userDao.getByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));


        if (product.getStock() > 0) {
            product.setStock(product.getStock() - 1);

            productDao.save(product);

            Purchase purchase = new Purchase();

            purchase.setProduct(product);
            purchase.setUser(user);

            purchaseDao.save(purchase);

        }
    }


}


