package com.tuna.otomotivbe.entities;

import com.tuna.otomotivbe.constants.DbConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = DbConstant.TBL_PURCHASES)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbConstant.COL_ID)
    private Long id;

    @Column(name = DbConstant.COL_PURCHASE_DATE)
    private LocalDateTime purchaseDate;

    @ManyToOne
    @JoinColumn(name = DbConstant.COL_USER_ID)
    private User user;

    @ManyToOne
    @JoinColumn(name = DbConstant.COL_PRODUCT_ID)
    private Product product;

    @PrePersist
    public void setPurchaseDate() {
        this.purchaseDate = LocalDateTime.now();
    }

    public Purchase(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Purchase() {

    }
}
