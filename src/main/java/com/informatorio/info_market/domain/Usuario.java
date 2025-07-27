package com.informatorio.info_market.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length=36, columnDefinition="varchar(36)", nullable=false, updatable=false)
    private UUID id;

    @Column (nullable = false, unique = true, updatable = false)
    private int dni;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @OneToMany(mappedBy = "usuario", cascade= CascadeType.ALL, orphanRemoval = true)
    private List<Carrito> carritos;

    private LocalDate FechaCreacion;

    private LocalDate FechaActualizacion;

    public Usuario() {
    }

    public Usuario(UUID id, int dni, String nombre, String apellido, List<Carrito> carritos, LocalDate fechaCreacion, LocalDate fechaActualizacion) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carritos = carritos;
        FechaCreacion = fechaCreacion;
        FechaActualizacion = fechaActualizacion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
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

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }

    public LocalDate getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaActualizacion() {
        return FechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        FechaActualizacion = fechaActualizacion;
    }
}
