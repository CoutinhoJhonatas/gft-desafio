package com.gft.peopleservice.controller;

import com.gft.peopleservice.dtos.PessoaDTO;
import com.gft.peopleservice.feign.response.BoletoClientResponse;
import com.gft.peopleservice.services.PessoaService;
import com.gft.peopleservice.swagger.PessoaControllerSwagger;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController implements PessoaControllerSwagger {

    private final PessoaService pessoaService;

    @GetMapping("/buscar-todas")
    public ResponseEntity<List<PessoaDTO>> retornarTodasPessoas() {
        return new ResponseEntity<>(pessoaService.retornarTodasPessoas(), HttpStatus.OK);
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(pessoaService.retornarPorId(id), HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        return new ResponseEntity<>(pessoaService.atualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar-boletos-pessoa/{id}")
    public ResponseEntity<List<BoletoClientResponse>> buscarBoletosPessoa(@PathVariable Long id) {
        return new ResponseEntity<>(pessoaService.buscarBoletosPessoa(id), HttpStatus.OK);
    }

    @PostMapping("/salvar")
    public ResponseEntity<PessoaDTO> salvarPessoa(@RequestBody PessoaDTO dto) {
        return new ResponseEntity<>(pessoaService.salvar(dto), HttpStatus.CREATED);
    }

}
