package com.gft.boletoservice.services;

import com.gft.boletoservice.dtos.BoletoDTO;
import com.gft.boletoservice.dtos.BoletoPagamentoDTO;
import com.gft.boletoservice.entities.Boleto;
import com.gft.boletoservice.exceptions.BoletoNaoEncontradoException;
import com.gft.boletoservice.repositorys.BoletoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.gft.boletoservice.entities.enums.Status.PAGO;

@Service
@AllArgsConstructor
public class BoletoService {

    private final BoletoRepository boletoRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<BoletoDTO> retornarBoletos(String cpf) {
        List<Boleto> boletos = boletoRepository.findByIdPessoaOrderByIdDesc(cpf);

        if(boletos.isEmpty()) {
            throw new BoletoNaoEncontradoException("Não foi encontrado boletos associados a esse CPF");
        }

        return boletos.stream().map(b -> modelMapper.map(b, BoletoDTO.class)).toList();
    }

    @Transactional(readOnly = true)
    public BoletoDTO retornarBoletoId(Long id) {
        return modelMapper.map(buscarPorId(id), BoletoDTO.class);
    }

    @Transactional
    public void deletar(Long id) {
        boletoRepository.deleteById(buscarPorId(id).getId());
    }

    @Transactional
    public BoletoDTO pagamento(BoletoPagamentoDTO dto) {
        Boleto boleto = buscarPorId(dto.getIdBoleto());
        boleto.setValorPago(dto.getValorPago());
        boleto.setDataPagamento(LocalDate.parse(dto.getDataPagamento()));
        boleto.setStatus(PAGO);

        boleto = boletoRepository.save(boleto);

        return modelMapper.map(boleto, BoletoDTO.class);
    }

    @Transactional
    public BoletoDTO salvar(BoletoDTO dto) {
        Boleto boleto = modelMapper.map(dto, Boleto.class);
        boleto.setDataVencimento(LocalDate.parse(dto.getDataVencimento()));

        boleto = boletoRepository.save(boleto);

        return modelMapper.map(boleto, BoletoDTO.class);
    }

    private Boleto buscarPorId(Long id) {
        return boletoRepository.findById(id).orElseThrow(() -> new BoletoNaoEncontradoException("Boleto não encontrado"));
    }
}
