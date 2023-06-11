package com.tuna.otomotivbe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@RequiredArgsConstructor
public class ProductDto {

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    private BigDecimal price;

    private int stock;

}
