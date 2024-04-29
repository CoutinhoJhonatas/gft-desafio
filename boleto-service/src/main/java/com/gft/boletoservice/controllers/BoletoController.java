package com.gft.boletoservice.controllers;

import com.gft.boletoservice.dtos.BoletoDTO;
import com.gft.boletoservice.dtos.BoletoPagamentoDTO;
import com.gft.boletoservice.services.BoletoService;
import com.gft.boletoservice.swagger.BoletoControllerSwagger;
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
@RequestMapping("/boletos")
public class BoletoController implements BoletoControllerSwagger {

    private final BoletoService boletoService;

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<List<BoletoDTO>> retornarBoletosCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(boletoService.retornarBoletos(cpf), HttpStatus.OK);
    }

    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<BoletoDTO> retornarBoletoId(@PathVariable Long id) {
        return new ResponseEntity<>(boletoService.retornarBoletoId(id), HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarBoleto(@PathVariable Long id) {
        boletoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/pagamento")
    public ResponseEntity<BoletoDTO> pagamentoBoleto(@RequestBody BoletoPagamentoDTO dto) {
        return new ResponseEntity<>(boletoService.pagamento(dto), HttpStatus.OK);
    }

    @PostMapping("/salvar")
    public ResponseEntity<BoletoDTO> salvarBoleto(@RequestBody BoletoDTO dto) {
        return new ResponseEntity<>(boletoService.salvar(dto), HttpStatus.CREATED);
    }

}
