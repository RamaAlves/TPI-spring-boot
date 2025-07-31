package com.informatorio.info_market.domain;

import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length=36, columnDefinition="varchar(36)", nullable=false, updatable=false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EstadoCarritoEnum estadoCarrito;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "carrito")
    private List<ItemCarrito> items = new ArrayList<>();

    @OneToOne(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private Factura factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDate fechaCreacion;

    private LocalDate fechaActualizacion;


    public Carrito() {
    }

    public Carrito(UUID id, EstadoCarritoEnum estadoCarrito, List<ItemCarrito> items, Factura factura, Usuario usuario, LocalDate fechaCreacion, LocalDate fechaActualizacion) {
        this.id = id;
        this.estadoCarrito = estadoCarrito;
        this.items = items;
        this.factura = factura;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EstadoCarritoEnum getEstadoCarrito() {
        return estadoCarrito;
    }

    public void setEstadoCarrito(EstadoCarritoEnum estadoCarrito) {
        this.estadoCarrito = estadoCarrito;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
