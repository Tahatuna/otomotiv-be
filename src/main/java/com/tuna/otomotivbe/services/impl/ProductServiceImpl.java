package com.tuna.otomotivbe.services.impl;

import com.tuna.otomotivbe.dao.IProductDao;
import com.tuna.otomotivbe.dao.IUserDao;
import com.tuna.otomotivbe.entities.Image;
import com.tuna.otomotivbe.entities.Product;
import com.tuna.otomotivbe.exceptionhandling.ResourceNotFoundException;
import com.tuna.otomotivbe.payload.request.ProductRequest;
import com.tuna.otomotivbe.repository.PurchaseRepository;
import com.tuna.otomotivbe.services.IProductService;
import com.tuna.otomotivbe.util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDao productDao;

    @Autowired
    IUserDao userDao;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public void addProduct(ProductRequest productRequest, MultipartFile file) throws IOException {

        Product product = new Product();
        Image image = new Image(file.getOriginalFilename(), file.getContentType(), ImageUtility.compressBytes(file.getBytes()));

        product.setImage(image);
        image.setProduct(product);
        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        productDao.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        for (Product product : products) {
            if (product.getImage() != null) {
                product.getImage().setImageByte(ImageUtility.decompressBytes(product.getImage().getImageByte()));
            }
        }
        return products;
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {

        Product product = productDao.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        productDao.save(product);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productDao.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        productDao.delete(product);
    }

}
