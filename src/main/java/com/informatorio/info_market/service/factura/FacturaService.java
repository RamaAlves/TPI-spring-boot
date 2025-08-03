package com.informatorio.info_market.service.factura;

import com.informatorio.info_market.domain.Factura;
import com.informatorio.info_market.dto.factura.FacturaDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface FacturaService {
    @Transactional
    FacturaDto generarFactura(UUID idUsuario);

    FacturaDto getFacturaById(UUID idFactura);
}
