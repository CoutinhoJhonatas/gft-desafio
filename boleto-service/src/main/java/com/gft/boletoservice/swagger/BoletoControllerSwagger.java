package com.gft.boletoservice.swagger;

import com.gft.boletoservice.dtos.BoletoDTO;
import com.gft.boletoservice.dtos.BoletoPagamentoDTO;
import com.gft.boletoservice.exceptions.handle.CustomError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Boletos")
public interface BoletoControllerSwagger {

    @Operation(summary = "Retornar boletos a partir do CPF")
    @ApiResponse(responseCode = "200", description = "Retornado com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "404", description = "Não foi encontrado boletos associados a esse CPF",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<List<BoletoDTO>> retornarBoletosCpf(@Parameter(description = "CPF associado ao boleto") String cpf);

    @Operation(summary = "Retornar boletos a partir do ID")
    @ApiResponse(responseCode = "200", description = "Retornado com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "404", description = "Boleto não encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<BoletoDTO> retornarBoletoId(@Parameter(description = "ID do boleto") Long id);

    @Operation(summary = "Deletar boleto")
    @ApiResponse(responseCode = "204", description = "Deletado com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "404", description = "Boleto não encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<Void> deletarBoleto(@Parameter(description = "ID do boleto") Long id);

    @Operation(summary = "Realizar pagamento do boleto")
    @ApiResponse(responseCode = "200", description = "Pago com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "404", description = "Boleto não encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<BoletoDTO> pagamentoBoleto(@Parameter(description = "Informações atualizadas do boleto") BoletoPagamentoDTO dto);

    @Operation(summary = "Salva o boleto")
    @ApiResponse(responseCode = "201", description = "Salvo com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<BoletoDTO> salvarBoleto(@Parameter(description = "Informações atualizadas do boleto") BoletoDTO dto);
}
