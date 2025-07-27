package com.informatorio.info_market.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Carrito carrito;

    private LocalDate fechaEmision;

    public Factura() {}

    public Factura(Long id, Carrito carrito, LocalDate fechaEmision) {
        this.id = id;
        this.carrito = carrito;
        this.fechaEmision = fechaEmision;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}
