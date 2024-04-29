package com.gft.boletoservice.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BoletoDTO {

    private Long id;
    private String idPessoa;
    private Double valor;
    private Double valorPago;
    private String dataVencimento;
    private String dataPagamento;
    private String status;

}
