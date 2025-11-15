package com.AtleticoBucaramanga.byDanielPerez.club_futbol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "jugadores")
public class Jugador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String apellido;
    
    @NotNull(message = "El número es obligatorio")
    @Min(value = 1, message = "El número debe ser mayor a 0")
    @Max(value = 99, message = "El número debe ser menor a 100")
    @Column(nullable = false)
    private Integer numero;
    
    @NotBlank(message = "La posición es obligatoria")
    @Column(nullable = false, length = 30)
    private String posicion;
    
    @Column(length = 50)
    private String nacionalidad;
    
    @Min(value = 16, message = "La edad mínima es 16 años")
    @Max(value = 45, message = "La edad máxima es 45 años")
    private Integer edad;
    
    @Column(name = "goles_anotados")
    private Integer golesAnotados = 0;
    
    // Constructores
    public Jugador() {
        this.golesAnotados = 0;
    }
    
    public Jugador(Long id, String nombre, String apellido, Integer numero, String posicion, 
                  String nacionalidad, Integer edad, Integer golesAnotados) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.golesAnotados = golesAnotados != null ? golesAnotados : 0;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getGolesAnotados() {
        return golesAnotados;
    }

    public void setGolesAnotados(Integer golesAnotados) {
        this.golesAnotados = golesAnotados;
    }
}