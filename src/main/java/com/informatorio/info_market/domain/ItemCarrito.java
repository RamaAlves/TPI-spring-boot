package com.informatorio.info_market.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length=36, columnDefinition="varchar(36)", nullable=false, updatable=false)
    private UUID id;

    @ManyToOne
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Carrito carrito;

    private int cantidad;

    public double calcularSubtotalItem() {
        if (this.producto == null) {
            return 0.0;
        }
        return this.cantidad * this.producto.getPrecio();
    }

}
