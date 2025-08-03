package com.informatorio.info_market.mapper.factura;

import com.informatorio.info_market.domain.Factura;
import com.informatorio.info_market.domain.ItemCarrito;
import com.informatorio.info_market.dto.ItemCarrito.ItemCarritoDto;
import com.informatorio.info_market.dto.factura.FacturaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "total", expression = "java(factura.getCarrito().calcularTotal())")
    @Mapping(target = "detalle", source = "carrito.items")
    @Mapping(target = "fechaEmision")
    FacturaDto toDto(Factura factura);

    @Mapping(target = "nombre", source = "producto.nombre")
    @Mapping(target = "descripcion", source = "producto.descripcion")
    @Mapping(target = "cantidad", source = "cantidad")
    @Mapping(target = "precio", source = "producto.precio")
    ItemCarritoDto toItemCarritoDto(ItemCarrito itemCarrito);

    List<ItemCarritoDto> toItemCarritoDtoList(List<ItemCarrito> items);
}

