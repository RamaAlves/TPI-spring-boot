package com.informatorio.info_market.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length=36, columnDefinition="varchar(36)", nullable=false, updatable=false)
    private UUID id;

    @OneToOne
    private Carrito carrito;

    private LocalDate fechaEmision;

    public Factura() {}

    public Factura(UUID id, Carrito carrito, LocalDate fechaEmision) {
        this.id = id;
        this.carrito = carrito;
        this.fechaEmision = fechaEmision;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
