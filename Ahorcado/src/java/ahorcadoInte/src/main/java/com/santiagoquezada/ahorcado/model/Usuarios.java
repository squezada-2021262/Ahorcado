package com.santiagoquezada.ahorcado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "Usuarios"
)
public class Usuarios {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer codigoUsuario;
    @Column(
            name = "correoUsuario",
            nullable = false
    )
    private String correoUsuario;
    @Column(
            name = "contrasena",
            nullable = false
    )
    private String contrasena;

    public Integer getCodigoUsuario() {
        return this.codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getCorreoUsuario() {
        return this.correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        // Validación del correo electrónico
        if (correoUsuario != null &&
                (correoUsuario.endsWith("@gmail.com") ||
                        correoUsuario.endsWith("@kinal.edu.gt") ||
                        correoUsuario.endsWith("@hotmail.com")))
        {
            this.correoUsuario = correoUsuario;
        } else {
            throw new IllegalArgumentException("El correo debe terminar en @gmail.com, @kinal.org.gt o @hotmail.com");
        }
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}