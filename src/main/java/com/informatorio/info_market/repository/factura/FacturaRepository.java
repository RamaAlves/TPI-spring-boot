package com.informatorio.info_market.repository.factura;

import com.informatorio.info_market.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FacturaRepository extends JpaRepository<Factura, UUID> {
}
