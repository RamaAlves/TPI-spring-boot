package com.informatorio.info_market.dto.producto;

import com.informatorio.info_market.dto.categoria.CategoriaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Schema(
        name = "Producto DTO",
        description = "DTO para alojar la informacion de un producto"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductoDto {
    @Schema(description = "id de producto", example = "11111111-aaaa-bbbb-cccc-111111111111")
    private UUID id;
    @Schema(description = "nombre del producto", example="Smartphone X100")
    private String nombre;
    @Schema(description = "descripcion del producto", example = "Pantalla AMOLED de 6.5 pulgadas, 128GB")
    private String descripcion;
    @Schema(description = "precio del producto", example = "299999.99")
    private double precio;
    @Schema(description = "stock del producto", example = "50")
    private int stock;
    @Schema(description = "categorias del producto", example = "[informatica]")
    private List<CategoriaDto> categorias;

}
