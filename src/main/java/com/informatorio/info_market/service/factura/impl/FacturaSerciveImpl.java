package com.informatorio.info_market.service.factura.impl;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.Factura;
import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.dto.factura.FacturaDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import com.informatorio.info_market.exception.notFound.NotFoundException;
import com.informatorio.info_market.mapper.factura.FacturaMapper;
import com.informatorio.info_market.repository.carrito.CarritoRepository;
import com.informatorio.info_market.repository.factura.FacturaRepository;
import com.informatorio.info_market.service.carrito.CarritoService;
import com.informatorio.info_market.service.factura.FacturaService;
import com.informatorio.info_market.service.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FacturaSerciveImpl implements FacturaService {

    private FacturaRepository facturaRepository;
    private CarritoRepository carritoRepository;
    private UsuarioService usuarioService;
    private CarritoService carritoService;
    private FacturaMapper facturaMapper;

    @Override
    public FacturaDto generarFactura(UUID idUsuario) {
        Usuario usuario = usuarioService.getUsuarioEntityById(idUsuario);

        Optional<Carrito> carrito = carritoRepository.findByUsuarioIdAndEstadoWithItems(idUsuario, EstadoCarritoEnum.ABIERTO);

        if(carrito.isEmpty()){
            throw new NotFoundException("No se encontró ningún carrito con estado para "+ EstadoCarritoEnum.ABIERTO + " el usuario con id: "+idUsuario);
        }
        carrito.get().setEstadoCarrito(EstadoCarritoEnum.CERRADO);
        carritoRepository.save(carrito.get());
        Factura factura = new Factura();
        factura.setCarrito(carrito.get());
        factura.setFechaEmision(LocalDate.now());

        return facturaMapper.toDto(facturaRepository.save(factura));
    }

    @Override
    public FacturaDto getFacturaById(UUID idFactura) {
        Optional<Factura> factura = facturaRepository.findById(idFactura);
        if (factura.isPresent()){
            return facturaMapper.toDto(factura.get());
        }else{
            throw new NotFoundException("No se encontró el factura con id: "+idFactura);
        }
    }
}
