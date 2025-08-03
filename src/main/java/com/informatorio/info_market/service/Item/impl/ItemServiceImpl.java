package com.informatorio.info_market.service.Item.impl;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.ItemCarrito;
import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.repository.item.ItemCarritoRepositoy;
import com.informatorio.info_market.service.Item.ItemService;
import com.informatorio.info_market.service.producto.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemCarritoRepositoy itemCarritoRepositoy;

    private ProductoService productoService;

    @Override
    public ItemCarrito crearItemCarrito(Carrito carrito, Producto producto, int cantidad) {
        Optional<ItemCarrito> itemCarrito = getItemCarritoById( carrito.getItems(), producto.getId() );
        productoService.descontarStock( producto, cantidad );

        if ( itemCarrito.isPresent() ) {
            itemCarrito.get().setCantidad( itemCarrito.get().getCantidad() + cantidad );
            return itemCarrito.get();
        }else {

            ItemCarrito nuevoItemCarrito = new ItemCarrito();
            nuevoItemCarrito.setCantidad( cantidad );
            nuevoItemCarrito.setProducto( producto );
            nuevoItemCarrito.setCarrito( carrito );

            return nuevoItemCarrito ;
        }
    }

    private Optional<ItemCarrito> getItemCarritoById(List<ItemCarrito> items, UUID idProducto) {

        if ( items == null || items.isEmpty() ){
            return Optional.empty();
        }

        Optional<ItemCarrito> itemCarrito = items
                .stream()
                .filter(item -> item.getProducto().getId().equals( idProducto ))
                .findFirst();

        return itemCarrito;
    }
}
