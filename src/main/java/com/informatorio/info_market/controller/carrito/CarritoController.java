package com.informatorio.info_market.controller.carrito;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.dto.error.ErrorResponseDto;
import com.informatorio.info_market.dto.producto.ProductoDto;
import com.informatorio.info_market.service.carrito.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name="Carritos REST APIs",
        description= "REST APIs del proyecto para realizar un CRUD de carritos"
)
@RestController
@RequestMapping("/api/v1/carritos")
@AllArgsConstructor
public class CarritoController {

    private CarritoService carritoService;

    @Operation(
            summary = "Post de carritos",
            description = "REST API para agregar productos al carrito"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Request SUCCESS"
        ),
        @ApiResponse(
                responseCode = "400",
                description = "HTTP Status BAD REQUEST",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
    })
    @PostMapping("/agregar-producto/{idUsuario}/{idProducto}")
    public void agregarProducto(@Valid @PathVariable UUID idUsuario,@Valid @PathVariable UUID idProducto){

        carritoService.agregarProductoACarrito(idUsuario,idProducto);
    }

}
