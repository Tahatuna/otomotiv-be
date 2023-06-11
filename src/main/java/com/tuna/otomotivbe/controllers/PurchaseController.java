package com.tuna.otomotivbe.controllers;

import com.tuna.otomotivbe.constants.ApiConstant;
import com.tuna.otomotivbe.payload.response.MessageResponse;
import com.tuna.otomotivbe.services.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = ApiConstant.END_POINT_PURCHASE)
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;


    @PostMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> purchaseProduct(@PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        purchaseService.purchaseProduct(userName, id);

        return ResponseEntity.ok(new MessageResponse("Product purchased successfully."));
    }


}
