package com.gft.peopleservice.services;

import com.gft.peopleservice.dtos.PessoaDTO;
import com.gft.peopleservice.exceptions.PessoaNaoEncontradaException;
import com.gft.peopleservice.feign.BoletoClient;
import com.gft.peopleservice.feign.response.BoletoClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoaServiceTest {

    @Mock
    private BoletoClient boletoClient;

    @Autowired
    @InjectMocks
    private PessoaService pessoaService;

    private PessoaDTO pessoaAtualizar;

    private List<BoletoClientResponse> boletoClientResponse;

    private static final String ERRO_BUSCAR_TODAS = "Não existe pessoas cadastradas";
    private static final String ERRO_BUSCAR_ID = "Pessoa não encontrada";

    @BeforeEach
    void setUp() {
        pessoaAtualizar = new PessoaDTO();
        pessoaAtualizar.setNome("João Silva");
        pessoaAtualizar.setCpf("86625741060");
        pessoaAtualizar.setDataNascimento("2000-04-06");
        pessoaAtualizar.setEnderecoLogradouro("Rua João Gonçalves");
        pessoaAtualizar.setEnderecoNumero("200");
        pessoaAtualizar.setEnderecoComplemento(null);
        pessoaAtualizar.setEnderecoBairro("Centro");
        pessoaAtualizar.setEnderecoCidade("Guarulhos");
        pessoaAtualizar.setEnderecoEstado("SP");
        pessoaAtualizar.setEnderecoCep("07010-010");

        boletoClientResponse = List.of(new BoletoClientResponse());
    }

    @Test
    @DisplayName("1.0 - Deve retornar uma pessoa por ID")
    @Order(1)
    void deveRetornarPorId() {
        PessoaDTO pessoaDTO = pessoaService.retornarPorId(2L);

        assertNotNull(pessoaDTO);
        assertEquals("Benedita Cláudia Assunção", pessoaDTO.getNome());
        assertEquals("93163364578", pessoaDTO.getCpf());
    }

    @Test
    @DisplayName("1.1 - Quando buscar uma pessoa por ID, e não existir na base, deve lançar PessoaNaoEncontradaException")
    @Order(2)
    void quandoBuscarPessoaIdENaoExistirLancarPessoaNaoEncontradaException() {
        PessoaNaoEncontradaException exception = assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.retornarPorId(4L));

        assertEquals(ERRO_BUSCAR_ID, exception.getMessage());
    }

    @Test
    @DisplayName("2.0 - Deve atualizar os dados de uma pessoa")
    @Order(3)
    void deveAtualizarPessoa() {
        PessoaDTO pessoaDTO = pessoaService.retornarPorId(1L);
        PessoaDTO pessoaAtualizada = pessoaService.atualizar(1L, pessoaAtualizar);

        assertEquals(pessoaDTO.getId(), pessoaAtualizada.getId());
        assertEquals(pessoaDTO.getNome(), pessoaAtualizada.getNome());
        assertEquals(pessoaDTO.getCpf(), pessoaAtualizada.getCpf());
        assertNotEquals(pessoaDTO.getEnderecoLogradouro(), pessoaAtualizada.getEnderecoLogradouro());
        assertNotEquals(pessoaDTO.getEnderecoNumero(), pessoaAtualizada.getEnderecoNumero());
        assertNotEquals(pessoaDTO.getEnderecoCidade(), pessoaAtualizada.getEnderecoCidade());
        assertNotEquals(pessoaDTO.getEnderecoCep(), pessoaAtualizada.getEnderecoCep());
    }

    @Test
    @DisplayName("2.1 - Quando atualizar uma pessoa, e não existir na base, deve lançar PessoaNaoEncontradaException")
    @Order(4)
    void quandoAtualizarPessoaENaoExistirLancarPessoaNaoEncontradaException() {
        PessoaNaoEncontradaException exception = assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.atualizar(5L, pessoaAtualizar));

        assertEquals(ERRO_BUSCAR_ID, exception.getMessage());
    }

    @Test
    @DisplayName("3.0 - Deve deletar uma pessoa")
    @Order(5)
    void deveDeletarPessoa() {
        pessoaService.deletar(3L);

        assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.retornarPorId(3L));
    }

    @Test
    @DisplayName("3.1 - Quando deletar uma pessoa, e não existir na base, deve lançar PessoaNaoEncontradaException")
    @Order(6)
    void quandoDeletarPessoaENaoExistirLancarPessoaNaoEncontradaException() {
        PessoaNaoEncontradaException exception = assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.deletar(4L));

        assertEquals(ERRO_BUSCAR_ID, exception.getMessage());
    }

    @Test
    @DisplayName("4.0 - Deve retornar todas as pessoas cadastradas na base de dados")
    @Order(7)
    void deveRetornarTodasPessoas() {
        List<PessoaDTO> list = pessoaService.retornarTodasPessoas();

        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("4.1 - Quando buscar todas as pessoa na base de dados e a lista retornar vazia, deve lançar PessoaNaoEncontradaException")
    @Order(8)
    void quandoBuscarPessoasEListaRetornarVaziaLancarPessoaNaoEncontradaException() {
        pessoaService.deletar(1L);
        pessoaService.deletar(2L);

        PessoaNaoEncontradaException exception = assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.retornarTodasPessoas());

        assertEquals(ERRO_BUSCAR_TODAS, exception.getMessage());
    }

    @Disabled("Spring não reconhece o mock do BoletoClient, verificar")
    @Test
    @DisplayName("Deve retornar os boletos de uma pessoa a partir do ID")
    void deveBuscarBoletosPessoa() {
        ResponseEntity<List<BoletoClientResponse>> responseEntity = new ResponseEntity<>(boletoClientResponse, HttpStatus.OK);

        when(boletoClient.buscarBoletosCpf(anyString())).thenReturn(responseEntity);

        List<BoletoClientResponse> response = pessoaService.buscarBoletosPessoa(2L);

        assertNotNull(response);
        assertEquals(response, boletoClientResponse);
        verify(boletoClient, times(1)).buscarBoletosCpf(anyString());
    }

    @Test
    @DisplayName("5.0 - Deve salvar uma pessoa")
    void deveSalvarPessoa() {
        PessoaDTO pessoaDTO = pessoaService.salvar(pessoaAtualizar);

        assertNotNull(pessoaDTO);
        assertEquals(pessoaDTO.getNome(), pessoaAtualizar.getNome());
        assertEquals(pessoaDTO.getCpf(), pessoaAtualizar.getCpf());
    }

}