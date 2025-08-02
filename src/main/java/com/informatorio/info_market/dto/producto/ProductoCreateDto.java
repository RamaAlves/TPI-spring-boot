package com.informatorio.info_market.dto.producto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Schema(
        name = "Producto Create DTO",
    description = "DTO para alojar la informacion de un producto al crear o actualizar"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductoCreateDto {
    @Schema(description = "nombre del producto", example="cargador super rapido")
    @NotBlank(message = "El nombre del producto no debe estar vacío")
    private String nombre;
    @Schema(description = "descripcion del producto", example="carga tu celular en tan solo 30 minutos.")
    @NotBlank(message = "La descripción del producto no debe estar vacía")
    @Size(max = 50)
    private String descripcion;
    @Schema(description = "precio del producto", example="50000")
    @Min(value=0, message = "Se debe tener un precio minimo de 0")
    private double precio;
    @Schema(description = "stock del producto", example="100")
    @Min(value=0, message = "Se debe tener un stock minimo de 0")
    private int stock;
    @Schema(description = "lista de ids de las categorias", example="[1]")
    @NotEmpty(message = "Se debe tener como minimo una categoría para el producto")
    private List<Long> categorias;
}
