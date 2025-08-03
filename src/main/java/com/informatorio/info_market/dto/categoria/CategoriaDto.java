package com.informatorio.info_market.dto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "Categoria DTO",
        description = "DTO para alojar la informacion de una categoria"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoriaDto {
    @Schema(description = "nombre de la categoría", example = "informática")
    private String nombre;
}
