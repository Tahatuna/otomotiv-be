package com.tuna.otomotivbe.entities;

import com.tuna.otomotivbe.constants.DbConstant;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = DbConstant.TBL_ROLE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbConstant.COL_ID)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = DbConstant.COL_NAME, length = 20)
    private ERole name;

    public Role() {

    }
    public Role(ERole name) {
        this.name = name;
    }

}