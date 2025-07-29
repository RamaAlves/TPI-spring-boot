package com.informatorio.info_market.service.producto.impl;

import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.producto.ProductoCreateDto;
import com.informatorio.info_market.dto.producto.ProductoDto;
import com.informatorio.info_market.mapper.producto.ProductoCreateMapper;
import com.informatorio.info_market.mapper.producto.ProductoMapper;
import com.informatorio.info_market.repository.producto.ProductoRepository;
import com.informatorio.info_market.repository.producto.ProductoRepositoryStub;
import com.informatorio.info_market.service.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final ProductoCreateMapper productoCreateMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper, ProductoCreateMapper productoCreateMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.productoCreateMapper = productoCreateMapper;
    }

    @Override
    public List<ProductoDto> getAllProductos(int minStock, double minPrice, double maxPrice){
        List<Producto> productos;

        if(minStock==0 && maxPrice==0){
            productos = productoRepository.findAll();
        }else if(minStock>0 && maxPrice>0){
            productos = productoRepository.findAllByStockIsGreaterThanAndPrecioIsBetween(minStock, minPrice, maxPrice);
        }else if(minStock>0 && minPrice>0){
            productos = productoRepository.findAllByStockIsGreaterThanAndPrecioIsGreaterThan(minStock, minPrice);
        }else if(maxPrice>0){
            productos = productoRepository.findAllByPrecioIsBetween(minPrice,maxPrice);
        }else{
            productos = productoRepository.findAllByStockIsGreaterThan(minStock);
        }
        return productos.stream()
                .map(producto -> productoMapper.toDto(producto))
                .toList();
    }

    @Override
    public ProductoDto getProductoById(UUID id){

        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()){
            return productoMapper.toDto(producto.get());
        }

        return null;
    }

    @Override
    public ProductoDto createProducto(ProductoCreateDto producto){
        Producto productoToCreate = productoCreateMapper.productoDtoCreateToProducto(producto);
        productoToCreate.setFechaCreacion(LocalDate.now());
        productoToCreate.setFechaActualizacion(LocalDate.now());

        return productoMapper.toDto(productoRepository.save(productoToCreate));
    }

    @Override
    public void deleteProductoById(UUID id){
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDto updateProducto(ProductoCreateDto producto, UUID productoId) {

        Optional<Producto> productoToUpdate = productoRepository.findById(productoId);

        if (productoToUpdate.isPresent()){
            Producto productoUpdated = productoCreateMapper.productoDtoCreateToProducto(producto);

            productoUpdated.setId(productoId);
            productoUpdated.setFechaCreacion( productoToUpdate.get().getFechaCreacion());
            productoUpdated.setFechaActualizacion(LocalDate.now());

            return productoMapper.toDto(productoRepository.save(productoUpdated));
        }

        return null;
    }
}
