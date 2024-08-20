package com.bsoft.symbol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Symbol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String stroke;
    private int opacity;
    private int width;
    private String linecap;
    private String dasharray;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return opacity == symbol.opacity && width == symbol.width && Objects.equals(name, symbol.name) && Objects.equals(stroke, symbol.stroke) && Objects.equals(linecap, symbol.linecap) && Objects.equals(dasharray, symbol.dasharray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stroke, opacity, width, linecap, dasharray);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stroke='" + stroke + '\'' +
                ", opacity=" + opacity +
                ", width=" + width +
                ", linecap='" + linecap + '\'' +
                ", dasharray='" + dasharray + '\'' +
                '}';
    }
}
