package com.gft.boletoservice.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BoletoPagamentoDTO {

    private Long idBoleto;
    private Double valorPago;
    private String dataPagamento;

}
