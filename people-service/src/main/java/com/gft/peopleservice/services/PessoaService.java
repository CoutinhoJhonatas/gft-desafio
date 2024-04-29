package com.gft.peopleservice.services;

import com.gft.peopleservice.dtos.PessoaDTO;
import com.gft.peopleservice.entities.Pessoa;
import com.gft.peopleservice.exceptions.PessoaNaoEncontradaException;
import com.gft.peopleservice.feign.BoletoClient;
import com.gft.peopleservice.feign.exceptions.RecursoNaoEncontradoException;
import com.gft.peopleservice.feign.exceptions.ServiceIndisponivelException;
import com.gft.peopleservice.feign.response.BoletoClientResponse;
import com.gft.peopleservice.repositorys.PessoaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final BoletoClient boletoClient;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<PessoaDTO> retornarTodasPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        if(pessoas.isEmpty()) {
            throw new PessoaNaoEncontradaException("Não existe pessoas cadastradas");
        }

        return pessoas.stream().map(p -> modelMapper.map(p, PessoaDTO.class)).toList();
    }

    @Transactional(readOnly = true)
    public PessoaDTO retornarPorId(Long id) {
        return modelMapper.map(buscarPorId(id), PessoaDTO.class);
    }

    @Transactional
    public PessoaDTO atualizar(Long id, PessoaDTO dto) {
        Pessoa pessoa = buscarPorId(id);
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setDataNascimento(LocalDate.parse(dto.getDataNascimento()));
        pessoa.getEndereco().setLogradouro(dto.getEnderecoLogradouro());
        pessoa.getEndereco().setNumero(dto.getEnderecoNumero());
        pessoa.getEndereco().setComplemento(dto.getEnderecoComplemento());
        pessoa.getEndereco().setBairro(dto.getEnderecoBairro());
        pessoa.getEndereco().setCidade(dto.getEnderecoCidade());
        pessoa.getEndereco().setEstado(dto.getEnderecoEstado());
        pessoa.getEndereco().setCep(dto.getEnderecoCep());

        pessoa = pessoaRepository.save(pessoa);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    @Transactional
    public void deletar(Long id) {
        pessoaRepository.deleteById(buscarPorId(id).getId());
    }

    @Transactional(readOnly = true)
    public List<BoletoClientResponse> buscarBoletosPessoa(Long id) {
        List<BoletoClientResponse> boletoClientResponse;
        try {
            ResponseEntity<List<BoletoClientResponse>> responseBoleto = boletoClient.buscarBoletosCpf(buscarPorId(id).getCpf());
            boletoClientResponse = responseBoleto.getBody();
        } catch (RecursoNaoEncontradoException e) {
            throw new RecursoNaoEncontradoException("Boleto vinculado ao CPF não encontrado");
        } catch (Exception e) {
            throw new ServiceIndisponivelException("Serviço de boletos indisponível no momento");
        }

        return boletoClientResponse;
    }

    @Transactional
    public PessoaDTO salvar(PessoaDTO dto) {
        Pessoa pessoa = modelMapper.map(dto, Pessoa.class);
        pessoa.setDataNascimento(LocalDate.parse(dto.getDataNascimento()));

        pessoa = pessoaRepository.save(pessoa);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    private Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));
    }
}
