package com.tuna.otomotivbe.controllers;

import com.tuna.otomotivbe.constants.ApiConstant;
import com.tuna.otomotivbe.entities.Product;
import com.tuna.otomotivbe.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = ApiConstant.END_POINT_PRODUCT)
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping(ApiConstant.END_POINT_GET_ALL_PRODUCTS)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

}
