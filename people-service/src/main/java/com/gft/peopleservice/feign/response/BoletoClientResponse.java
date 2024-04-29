package com.gft.peopleservice.feign.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletoClientResponse {

    private Long id;
    private String idPessoa;
    private Double valor;
    private Double valorPago;
    private String dataVencimento;
    private String dataPagamento;
    private String status;

}
