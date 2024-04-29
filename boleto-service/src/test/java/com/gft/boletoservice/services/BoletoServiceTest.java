package com.gft.boletoservice.services;

import com.gft.boletoservice.dtos.BoletoDTO;
import com.gft.boletoservice.dtos.BoletoPagamentoDTO;
import com.gft.boletoservice.entities.enums.Status;
import com.gft.boletoservice.exceptions.BoletoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoletoServiceTest {

    @Autowired
    private BoletoService boletoService;

    private BoletoPagamentoDTO boletoPagamentoDTO;

    private BoletoDTO boletoDTO;

    private static final String ERRO_BUSCAR_BOLETOS_CPF = "Não foi encontrado boletos associados a esse CPF";
    private static final String ERRO_BUSCAR_ID = "Boleto não encontrado";

    @BeforeEach
    void setUp() {
        boletoPagamentoDTO = new BoletoPagamentoDTO();
        boletoPagamentoDTO.setIdBoleto(5L);
        boletoPagamentoDTO.setValorPago(1415.20);
        boletoPagamentoDTO.setDataPagamento("2024-04-29");

        boletoDTO = new BoletoDTO();
        boletoDTO.setIdPessoa("89875866423");
        boletoDTO.setValor(632.90);
        boletoDTO.setDataVencimento("2024-05-01");
        boletoDTO.setStatus(Status.PENDENTE.getName());
    }

    @Test
    @DisplayName("1.0 - Deve retornar todos os boletos vinculados ao CPF")
    @Order(1)
    void deveRetornarBoletosVinculadoCpf() {
        List<BoletoDTO> list = boletoService.retornarBoletos("93163364578");

        assertNotNull(list);
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("1.1 - Quando buscar boletos vinculados ao CPF, e a lista retornar vazia, deve lançar BoletoNaoEncontradoException")
    @Order(2)
    void quandoBuscarBoletosCpfERetornarVaziaLancarBoletoNaoEncontradoException() {
        BoletoNaoEncontradoException exception = assertThrows(BoletoNaoEncontradoException.class, () -> boletoService.retornarBoletos("76391175853"));

        assertEquals(ERRO_BUSCAR_BOLETOS_CPF, exception.getMessage());
    }

    @Test
    @DisplayName("2.0 - Deve retornar um boleto por ID")
    @Order(3)
    void deveRetornarBoletoId() {
        BoletoDTO boletoDTO = boletoService.retornarBoletoId(1L);

        assertNotNull(boletoDTO);
        assertEquals("93163364578", boletoDTO.getIdPessoa());
        assertEquals(526.50, boletoDTO.getValor());
    }

    @Test
    @DisplayName("2.1 - Quando buscar boleto por ID, e não existir na base, deve lançar BoletoNaoEncontradoException")
    @Order(4)
    void quandoBuscarBoletoIdENaoExistirLancarBoletoNaoEncontradoException() {
        BoletoNaoEncontradoException exception = assertThrows(BoletoNaoEncontradoException.class, () -> boletoService.retornarBoletoId(6L));

        assertEquals(ERRO_BUSCAR_ID, exception.getMessage());
    }

    @Test
    @DisplayName("3.0 - Deve realizar o pagamento do boleto e retornar os dados atualizados")
    @Order(5)
    void deveRealizarPagamentoERetornarDadosAtualizados() {
        BoletoDTO boletoDTO = boletoService.retornarBoletoId(5L);
        BoletoDTO boletoAtualizado = boletoService.pagamento(boletoPagamentoDTO);

        assertNotNull(boletoAtualizado);
        assertNotNull(boletoAtualizado.getValorPago());
        assertNotNull(boletoAtualizado.getDataPagamento());
        assertEquals(boletoDTO.getId(), boletoAtualizado.getId());
        assertEquals(boletoDTO.getIdPessoa(), boletoAtualizado.getIdPessoa());
        assertEquals(Status.PAGO.getName(), boletoAtualizado.getStatus());
    }

    @Test
    @DisplayName("3.1 - Quando realizar pagamento do boleto, e não existir na base, deve lançar BoletoNaoEncontradoException")
    @Order(6)
    void quandoRealizarPagamentoBoletoENaoExistirLancarBoletoNaoEncontradoException() {
        boletoPagamentoDTO.setIdBoleto(6L);
        BoletoNaoEncontradoException exception = assertThrows(BoletoNaoEncontradoException.class, () -> boletoService.pagamento(boletoPagamentoDTO));

        assertEquals(ERRO_BUSCAR_ID, exception.getMessage());
    }

    @Test
    @DisplayName("4.0 - Deve deletar um boleto")
    @Order(7)
    void deveDeletarBoleto() {
        boletoService.deletar(2L);

        assertThrows(BoletoNaoEncontradoException.class, () -> boletoService.retornarBoletoId(2L));
    }

    @Test
    @DisplayName("4.1 - Quando deletar um boleto, e não existir na base, deve lançar BoletoNaoEncontradoException")
    @Order(8)
    void quandoDeletarBoletoENaoExistirLancarBoletoNaoEncontradoException() {
        BoletoNaoEncontradoException exception = assertThrows(BoletoNaoEncontradoException.class, () -> boletoService.retornarBoletoId(6L));

        assertEquals(ERRO_BUSCAR_ID, exception.getMessage());
    }

    @Test
    @DisplayName("5.0 - Deve salvar um boleto")
    @Order(9)
    void deveSalvarBoleto() {
        BoletoDTO boletoSalvo = boletoService.salvar(boletoDTO);

        assertNotNull(boletoSalvo);
        assertEquals(boletoSalvo.getIdPessoa(), boletoDTO.getIdPessoa());
        assertEquals(boletoSalvo.getValor(), boletoDTO.getValor());
    }
}