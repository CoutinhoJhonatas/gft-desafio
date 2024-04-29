package com.gft.peopleservice.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PessoaDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String enderecoLogradouro;
    private String enderecoNumero;
    private String enderecoComplemento;
    private String enderecoBairro;
    private String enderecoCidade;
    private String enderecoEstado;
    private String enderecoCep;

}
