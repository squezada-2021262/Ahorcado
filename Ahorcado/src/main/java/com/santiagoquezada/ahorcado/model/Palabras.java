//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.santiagoquezada.ahorcado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "Palabras"
)
public class Palabras {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer codigoPalabra;
    @Column(
            name = "Palabra",
            nullable = false
    )
    private String palabra;
    @Column(
            name = "Pista1",
            nullable = false
    )
    private String pista1;
    @Column(
            name = "Pista2",
            nullable = false
    )
    private String pista2;
    @Column(
            name = "Pista3",
            nullable = false
    )
    private String pista3;

    public Integer getCodigoPalabra() {
        return this.codigoPalabra;
    }

    public void setCodigoPalabra(Integer codigoPalabra) {
        this.codigoPalabra = codigoPalabra;
    }

    public String getPalabra() {
        return this.palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPista1() {
        return this.pista1;
    }

    public void setPista1(String pista1) {
        this.pista1 = pista1;
    }

    public String getPista2() {
        return this.pista2;
    }

    public void setPista2(String pista2) {
        this.pista2 = pista2;
    }

    public String getPista3() {
        return this.pista3;
    }

    public void setPista3(String pista3) {
        this.pista3 = pista3;
    }
}
