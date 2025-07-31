package com.informatorio.info_market.dto.producto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductoCreateDto {
    @NotBlank(message = "El nombre del producto no debe estar vacío")
    private String nombre;
    @NotBlank(message = "La descripción del producto no debe estar vacía")
    @Size(max = 50)
    private String descripcion;
    @Min(value=0, message = "Se debe tener un precio minimo de 0")
    private double precio;
    @Min(value=0, message = "Se debe tener un stock minimo de 0")
    private int strock;
    @NotEmpty(message = "Se debe tener como minimo una categoría para el producto")
    private List<Long> categorias;
}
