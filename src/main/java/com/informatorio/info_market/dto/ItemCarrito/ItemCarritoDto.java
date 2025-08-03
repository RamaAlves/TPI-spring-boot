package com.informatorio.info_market.dto.ItemCarrito;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "Item carrito DTO",
        description = "DTO para alojar la informacion de un producto en el carrito"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemCarritoDto {
    @Schema(description = "nombre del item del carrito", example="cargador super rapido")
    private String nombre;
    @Schema(description = "descripcion del item del carrito", example="carga tu telefono en 30 minutos")
    private String descripcion;
    @Schema(description = "cantidad comprada del item del carrito", example="1")
    private int cantidad;
    @Schema(description = "precio individual del item del item del carrito", example="100")
    private double precio;
}
