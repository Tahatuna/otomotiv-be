package com.tuna.otomotivbe.entities;

import com.tuna.otomotivbe.constants.DbConstant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = DbConstant.TBL_PRODUCT)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbConstant.COL_ID)
    private Long id;

    @NotBlank
    @Column(name = DbConstant.COL_PRODUCT_NAME)
    private String productName;

    @NotBlank
    @Column(name = DbConstant.COL_DESCRIPTION)
    private String description;

    @Column(name = DbConstant.COL_PRICE)
    private BigDecimal price;

    @Column(name = DbConstant.COL_STOCK)
    private int stock;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbConstant.COL_IMAGE_ID, referencedColumnName = DbConstant.COL_ID)
    private Image image;

    @OneToMany(mappedBy = "product")
    private Set<Purchase> purchases = new HashSet<>();

}