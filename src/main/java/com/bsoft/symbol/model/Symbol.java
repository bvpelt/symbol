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
public class Symbol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private int size;
    private int rotation;
    private String fill;
    private float fillopacity;
    private String stroke;
    private int strokeopacity;
    private int strokewidth;
    private String stokelinecap;
    private String strokedasharray;
    private String strokelinejoinfill;
    private String strokelinejoinstroke;
    private String symbol;
    private String name;
}
