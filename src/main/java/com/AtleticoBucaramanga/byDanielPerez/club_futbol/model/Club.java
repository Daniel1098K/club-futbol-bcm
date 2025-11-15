package com.AtleticoBucaramanga.byDanielPerez.club_futbol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clubes")
public class Club {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre del club es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100, unique = true)
    private String nombre;
    
    @NotBlank(message = "La ciudad es obligatoria")
    @Column(nullable = false, length = 50)
    private String ciudad;
    
    @Column(name = "año_fundacion")
    private Integer añoFundacion;
    
    @Column(length = 100)
    private String estadio;
    
    @Column(length = 7)
    private String colorPrincipal;
    
    // Relación @OneToOne con Entrenador
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrenador_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Entrenador entrenador;
    
    // Relación @OneToMany con Jugador
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_club")
    private List<Jugador> jugadores = new ArrayList<>();
    
    // Relación @ManyToOne con Asociacion
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asociacion_id", referencedColumnName = "id")
    private Asociacion asociacion;
    
    // Relación @ManyToMany con Competicion
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "club_competiciones",
        joinColumns = @JoinColumn(name = "club_id"),
        inverseJoinColumns = @JoinColumn(name = "competicion_id")
    )
    private List<Competicion> competiciones = new ArrayList<>();
    
    // Constructores
    public Club() {
        this.jugadores = new ArrayList<>();
        this.competiciones = new ArrayList<>();
    }
    
    public Club(Long id, String nombre, String ciudad, Integer añoFundacion, String estadio, 
                String colorPrincipal, Entrenador entrenador, Asociacion asociacion) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.añoFundacion = añoFundacion;
        this.estadio = estadio;
        this.colorPrincipal = colorPrincipal;
        this.entrenador = entrenador;
        this.asociacion = asociacion;
        this.jugadores = new ArrayList<>();
        this.competiciones = new ArrayList<>();
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(Integer añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getColorPrincipal() {
        return colorPrincipal;
    }

    public void setColorPrincipal(String colorPrincipal) {
        this.colorPrincipal = colorPrincipal;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Jugador> getJugadores() {
        if (jugadores == null) {
            jugadores = new ArrayList<>();
        }
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public List<Competicion> getCompeticiones() {
        if (competiciones == null) {
            competiciones = new ArrayList<>();
        }
        return competiciones;
    }

    public void setCompeticiones(List<Competicion> competiciones) {
        this.competiciones = competiciones;
    }
    
    // Métodos auxiliares para gestionar relaciones
    public void addJugador(Jugador jugador) {
        if (jugadores == null) {
            jugadores = new ArrayList<>();
        }
        jugadores.add(jugador);
    }
    
    public void removeJugador(Jugador jugador) {
        if (jugadores != null) {
            jugadores.remove(jugador);
        }
    }
    
    public void addCompeticion(Competicion competicion) {
        if (competiciones == null) {
            competiciones = new ArrayList<>();
        }
        if (!competiciones.contains(competicion)) {
            competiciones.add(competicion);
        }
    }
    
    public void removeCompeticion(Competicion competicion) {
        if (competiciones != null) {
            competiciones.remove(competicion);
        }
    }
}