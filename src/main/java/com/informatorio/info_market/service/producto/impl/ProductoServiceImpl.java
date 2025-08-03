package com.informatorio.info_market.service.producto.impl;

import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.producto.ProductoCreateDto;
import com.informatorio.info_market.dto.producto.ProductoDto;
import com.informatorio.info_market.exception.badRequest.StockInsuficienteException;
import com.informatorio.info_market.exception.notFound.NotFoundException;
import com.informatorio.info_market.mapper.producto.ProductoCreateMapper;
import com.informatorio.info_market.mapper.producto.ProductoMapper;
import com.informatorio.info_market.repository.producto.ProductoRepository;
import com.informatorio.info_market.repository.producto.ProductoRepositoryStub;
import com.informatorio.info_market.service.producto.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private  ProductoRepository productoRepository;
    private  ProductoMapper productoMapper;
    private  ProductoCreateMapper productoCreateMapper;

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
        }else{
           throw new NotFoundException("No se encontró el producto con id: "+id);
        }
    }
    @Override
    public Producto getProductoEntityById(UUID id){

        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()){
            return producto.get();
        }else{
           throw new NotFoundException("No se encontró el producto con id: "+id);
        }
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
        if(productoRepository.existsById(id)){

        productoRepository.deleteById(id);
        }else{
            throw new NotFoundException("No se encontró el producto con id: "+id);
        }
    }

    @Override
    public void descontarStock(Producto producto, int cantidad) {
        if(producto.getStock()<cantidad){
            throw new StockInsuficienteException("No existe stock suficiente del producto");
        }
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
        }else{
            throw new NotFoundException("No se encontró el producto con id: "+productoId);
        }


    }
}
