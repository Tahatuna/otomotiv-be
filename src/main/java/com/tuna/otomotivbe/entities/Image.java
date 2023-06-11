package com.tuna.otomotivbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuna.otomotivbe.constants.DbConstant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = DbConstant.TBL_IMAGE)
public class Image implements Serializable {

    @Serial
    private static final long serialVersionUID = 850230557190233491L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DbConstant.COL_ID, nullable = false)
    private Long id;

    @Column(name = DbConstant.COL_FILE_NAME)
    private String fileName;

    @Column(name = DbConstant.COL_FILE_TYPE)
    private String fileType;

    @Lob
    @Column(length = 100000)
    private byte[] imageByte;

    @OneToOne(mappedBy = "image")
    @JsonIgnore
    private Product product;

    public Image(String fileName, String fileType, byte[] imageByte) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.imageByte = imageByte;
    }

}