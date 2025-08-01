package com.informatorio.info_market.mapper.producto;

import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.producto.ProductoCreateDto;
import com.informatorio.info_market.dto.producto.ProductoDto;

public interface ProductoMapper {
    ProductoDto toDto(Producto producto);
}
