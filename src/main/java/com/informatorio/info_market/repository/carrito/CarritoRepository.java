package com.informatorio.info_market.repository.carrito;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CarritoRepository extends JpaRepository<Carrito, UUID> {

    @Query("SELECT c FROM Carrito c JOIN FETCH c.items WHERE c.usuario.id = :usuarioId AND c.estadoCarrito = :estado")
    Optional<Carrito> findByUsuarioIdAndEstadoWithItems(@Param("usuarioId") UUID usuarioId, @Param("estado") EstadoCarritoEnum estado);

}
