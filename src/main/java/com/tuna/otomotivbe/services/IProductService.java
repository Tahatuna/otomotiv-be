package com.tuna.otomotivbe.services;

import com.tuna.otomotivbe.entities.Purchase;
import com.tuna.otomotivbe.payload.request.ProductRequest;
import com.tuna.otomotivbe.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    void addProduct(ProductRequest productRequest, MultipartFile file) throws IOException;

    void updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);

    List<Product> getAllProducts();

}
