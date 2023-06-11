package com.tuna.otomotivbe.Entites;

import com.tuna.otomotivbe.constants.DbCostant;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = DbCostant.TBL_ROLE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }
}