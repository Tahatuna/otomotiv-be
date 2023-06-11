package com.tuna.otomotivbe.controllers;

import com.tuna.otomotivbe.constants.ApiConstant;
import com.tuna.otomotivbe.payload.request.ProductRequest;
import com.tuna.otomotivbe.payload.response.MessageResponse;
import com.tuna.otomotivbe.services.IAdminService;
import com.tuna.otomotivbe.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = ApiConstant.END_POINT_ADMIN)
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IProductService productService;

    @PutMapping(ApiConstant.END_POINT_UNBLOCK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> unblockUser(@PathVariable("id") Long id) {
        adminService.unblockUser(id);

        return ResponseEntity.ok(new MessageResponse("User unblocked successfully."));
    }

    @PostMapping(ApiConstant.END_POINT_ADD_PRODUCT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addProduct(@ModelAttribute ProductRequest productRequest, @RequestParam("imageFile") MultipartFile file) throws IOException {
        productService.addProduct(productRequest, file);

        return ResponseEntity.ok(new MessageResponse("Product added successfully."));
    }

    @PutMapping(ApiConstant.END_POINT_UPDATE_PRODUCT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);

        return ResponseEntity.ok(new MessageResponse("Product updated successfully."));
    }

    @DeleteMapping(ApiConstant.END_POINT_DELETE_PRODUCT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.ok(new MessageResponse("Product deleted successfully."));
    }

}
