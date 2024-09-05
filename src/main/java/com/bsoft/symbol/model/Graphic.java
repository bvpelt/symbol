package com.bsoft.symbol.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Graphic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private int size;
    private int rotation;
    private String fill;
    private Float fillopacity;
    private String stroke;
    private Float strokeopacity;
    private Float strokewidth;
    private String name;
    private String welknownname;
}
