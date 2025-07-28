package com.informatorio.info_market.repository.producto;

import com.informatorio.info_market.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {

    List<Producto> findAllByStockIsGreaterThanAndPrecioIsBetween(int stockMin, double minPrice, double maxPrice);

    List<Producto> findAllByPrecioIsBetween(double minPrice, double maxPrice);

    List<Producto> findAllByStockIsGreaterThan(int minStock);

    List<Producto> findAllByStockIsGreaterThanAndPrecioIsGreaterThan(int minStock, double minPrice);
}
