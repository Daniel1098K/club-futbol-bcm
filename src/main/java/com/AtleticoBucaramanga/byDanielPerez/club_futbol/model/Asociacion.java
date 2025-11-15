package com.AtleticoBucaramanga.byDanielPerez.club_futbol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "asociaciones")
public class Asociacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100, unique = true)
    private String nombre;
    
    @NotBlank(message = "El país es obligatorio")
    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    @Column(nullable = false, length = 50)
    private String pais;
    
    @NotBlank(message = "El presidente es obligatorio")
    @Size(max = 100, message = "El presidente no puede exceder 100 caracteres")
    @Column(nullable = false, length = 100)
    private String presidente;
    
    @Column(name = "año_fundacion")
    private Integer añoFundacion;
    
    @Column(length = 200)
    private String sede;
    
    // Constructores
    public Asociacion() {}
    
    public Asociacion(Long id, String nombre, String pais, String presidente, Integer añoFundacion, String sede) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.presidente = presidente;
        this.añoFundacion = añoFundacion;
        this.sede = sede;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public Integer getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(Integer añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}