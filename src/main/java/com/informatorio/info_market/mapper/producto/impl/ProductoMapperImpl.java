package com.informatorio.info_market.mapper.producto.impl;

import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.producto.ProductoDto;
import com.informatorio.info_market.mapper.categoria.CategoriaMapper;
import com.informatorio.info_market.mapper.producto.ProductoMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductoMapperImpl implements ProductoMapper {

    private CategoriaMapper categoriaMapper;

    @Override
    public ProductoDto toDto(Producto producto){
        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(producto.getId());
        productoDto.setNombre(producto.getNombre());
        productoDto.setDescripcion(producto.getDescripcion());
        productoDto.setPrecio(producto.getPrecio());
        productoDto.setStock(producto.getStock());
        productoDto.setCategorias(
                producto.getCategorias().stream()
                        .map(categoria -> categoriaMapper.toDto(categoria))
                        .toList()
        );
        return productoDto;
    }

}
