package com.informatorio.info_market.dto.factura;

import com.informatorio.info_market.dto.ItemCarrito.ItemCarritoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Schema(
        name = "Factura DTO",
        description = "DTO para alojar la informacion de una factura al crear o actualizar"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FacturaDto {
    @Schema(description = "id de la factura", example="55555555-aaaa-bbbb-cccc-555555523555")
    UUID id;
    @Schema(description = "total de la factura", example="100")
    double total;
    @Schema(description = "nombre del producto", example="[{nombre:'cargador super rapido',descripcion:'carga tu telefono en 30 minutos', cantidad:1, precio:100 }]")
    List<ItemCarritoDto> detalle;
    @Schema(description = "Fecha de emision", example="2025-08-03")
    LocalDate fechaEmision;
}
