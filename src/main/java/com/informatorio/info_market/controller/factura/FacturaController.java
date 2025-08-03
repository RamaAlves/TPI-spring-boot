package com.informatorio.info_market.controller.factura;

import com.informatorio.info_market.dto.error.ErrorResponseDto;
import com.informatorio.info_market.dto.factura.FacturaDto;
import com.informatorio.info_market.service.factura.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Tag(
        name="Facturas REST APIs",
        description= "REST APIs del proyecto para realizar un CRUD de facturas"
)
@RestController
@RequestMapping("/api/v1/facturas")
@AllArgsConstructor
public class FacturaController {

    private FacturaService facturaService;

    @Operation(
            summary = "Post de facturas",
            description = "REST API para generar una factura"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Request CREATED",
                    content = @Content(
                            schema = @Schema(implementation = FacturaDto.class)
    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/generar-factura/{idUsuario}")
    public ResponseEntity<FacturaDto> generarFactura(@Valid @PathVariable @Parameter(description = "ID del usuario para generar la factura", example = "55555555-aaaa-bbbb-cccc-555555555555") UUID idUsuario){

        FacturaDto facturaDto = facturaService.generarFactura(idUsuario);
        return ResponseEntity
                .created(URI.create("api/v1/facturas/"+facturaDto.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(facturaDto);
    }
    @Operation(
            summary = "Get para obtener una factura por id",
            description = "REST API para obtener un factura por id"
    )
    @ApiResponses({

            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status SUCCESS",
                    content = @Content(
                            schema = @Schema(implementation = FacturaDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/{idFactura}")
    public FacturaDto getFacturaById(@Valid @PathVariable UUID idFactura){
        return facturaService.getFacturaById(idFactura);
    }
}
