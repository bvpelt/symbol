package com.bsoft.symbol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Line implements Serializable {
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
        Line line = (Line) o;
        return opacity == line.opacity && width == line.width && Objects.equals(name, line.name) && Objects.equals(stroke, line.stroke) && Objects.equals(linecap, line.linecap) && Objects.equals(dasharray, line.dasharray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stroke, opacity, width, linecap, dasharray);
    }

    @Override
    public String toString() {
        return "Line{" +
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
