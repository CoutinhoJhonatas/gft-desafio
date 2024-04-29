# Desafio GFT


### Passo a passo para testar o projeto:
1. Baixar os dois projetos em um pasta de sua preferência.
  
2. Abrir cada projeto em uma janela separada no IntelliJ conforme immagem abaixo:
    ![image](https://github.com/jhonatas8020/gft-desafio/assets/78003079/30bfd223-5e4d-4be9-80ca-67eb08acc6ab)

3. Rodar os dois projetos ao mesmo tempo. O people-service estará rodando na porta 8080 e o boleto-service na porta 8081.

4. Com os dois projetos rodando, abrir o navegador de sua preferência (exemplo Google Chrome).

5. Abrir uma aba e digitar a URL http://localhost:8080/swagger-ui/index.html
    ![image](https://github.com/jhonatas8020/gft-desafio/assets/78003079/f7372e30-3abb-41f9-b89f-314d3d9db1e5)


6. Abrir uma nova aba e digitar a URL http://localhost:8081/swagger-ui/index.html
    ![image](https://github.com/jhonatas8020/gft-desafio/assets/78003079/f8820eeb-093b-4007-8e91-6593c1403bea)


Pronto, agora é só testar os endpoints conforme desejar.

Segue abaixo exemplo de JSONs e IDs para testes:

* Endpoint /pessoas/atualizar/1:
  {
    "nome": "João Silva",
    "cpf": "86625741060",
    "dataNascimento": "2000-04-06",
    "enderecoLogradouro": "Rua João Gonçalves",
    "enderecoNumero": "200",
    "enderecoComplemento": null,
    "enderecoBairro": "Centro",
    "enderecoCidade": "Guarulhos",
    "enderecoEstado": "SP",
    "enderecoCep": "07010010"
}

* Endpoint /pessoas/salvar:
  {
    "nome": "Fernando Gael Lucas Souza",
    "cpf": "89875866423",
    "dataNascimento": "1993-03-10",
    "enderecoLogradouro": "Rua B",
    "enderecoNumero": "418",
    "enderecoComplemento": null,
    "enderecoBairro": "Novo Horizonte",
    "enderecoCidade": "Imperatriz",
    "enderecoEstado": "MA",
    "enderecoCep": "65905418"
}

* Endpoint /pessoas/buscar-boletos-pessoa/1

* Endpoint /pessoas/deletar/1

* Endpoint /boletos/pagamento:
   {
    "idBoleto": 3,
    "valorPago": 212.13,
    "dataPagamento": "2024-04-26"
  }

* Endpoint /boletos/salvar: 
{
    "idPessoa": "89875866423",
    "valor": 632.90,
    "valorPago": null,
    "dataVencimento": "2024-05-01",
    "dataPagamento": null,
    "status": "PENDENTE"
}

* Endpoint /boletos/buscar/93163364578

* Endpont /boletos/buscar-id/5

* Endpoint /boletos/deletar/5

