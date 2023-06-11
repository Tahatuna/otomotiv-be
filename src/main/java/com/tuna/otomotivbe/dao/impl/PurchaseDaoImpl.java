package com.tuna.otomotivbe.dao.impl;

import com.tuna.otomotivbe.dao.IPurchaseDao;
import com.tuna.otomotivbe.entities.Purchase;
import com.tuna.otomotivbe.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseDaoImpl implements IPurchaseDao {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

}
