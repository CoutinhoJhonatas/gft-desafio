package com.gft.peopleservice.swagger;


import com.gft.peopleservice.dtos.PessoaDTO;
import com.gft.peopleservice.exceptions.handle.CustomError;
import com.gft.peopleservice.feign.response.BoletoClientResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Pessoas")
public interface PessoaControllerSwagger {

    @Operation(summary = "Retornar todas as pessoas")
    @ApiResponse(responseCode = "200", description = "Retornado com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "404", description = "Não existe pessoas cadastradas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<List<PessoaDTO>> retornarTodasPessoas();

    @Operation(summary = "Retornar pessoa por ID")
    @ApiResponse(responseCode = "200", description = "Retornado com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<PessoaDTO> buscarPorId(@Parameter(description = "ID da pessoa") Long id);

    @Operation(summary = "Atualizar a pessoa")
    @ApiResponse(responseCode = "200", description = "Atualizado com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<PessoaDTO> atualizarPessoa(@Parameter(description = "ID da pessoa") Long id,
                                              @Parameter(description = "Informações atualizadas da pessoa") PessoaDTO dto);

    @Operation(summary = "Deletar a pessoa")
    @ApiResponse(responseCode = "204", description = "Deletado com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<Void> deletarPessoa(@Parameter(description = "ID da pessoa") Long id);

    @Operation(summary = "Retornar boletos vinculados à pessoa")
    @ApiResponse(responseCode = "200", description = "Retornado com suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    @ApiResponse(responseCode = "500", description = "Serviço de boletos indisponível no momento",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<List<BoletoClientResponse>> buscarBoletosPessoa(@Parameter(description = "ID da pessoa") Long id);

    @Operation(summary = "Salvar uma pessoa")
    @ApiResponse(responseCode = "201", description = "Salvo suscesso" )
    @ApiResponse(responseCode = "400", description = "Informações inválidas",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomError.class))})
    ResponseEntity<PessoaDTO> salvarPessoa(@Parameter(description = "Informações atualizadas da pessoa") PessoaDTO dto);

}
