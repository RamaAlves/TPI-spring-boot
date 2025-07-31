package com.informatorio.info_market.controller.carrito;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.service.carrito.CarritoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carritos")
@AllArgsConstructor
public class CarritoController {

    private CarritoService carritoService;

    /*@GetMapping
    public CarritoDto obtenerCarrito(){
        return null;
    }*/

    @PostMapping("/agregar-producto/{idUsuario}/{idProducto}")
    public void agregarProducto(@PathVariable UUID idUsuario,@PathVariable UUID idProducto){

        carritoService.agregarProductoACarrito(idUsuario,idProducto);
    }

}
