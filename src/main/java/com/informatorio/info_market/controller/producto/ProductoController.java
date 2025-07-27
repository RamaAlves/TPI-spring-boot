package com.informatorio.info_market.controller.producto;

import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.service.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping()
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    @PostMapping()
    public Producto createProducto(@RequestBody Producto producto){
        return productoService.createProducto(producto);
    }
}
