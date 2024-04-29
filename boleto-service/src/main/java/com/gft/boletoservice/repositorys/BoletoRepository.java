package com.gft.boletoservice.repositorys;

import com.gft.boletoservice.entities.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {

    List<Boleto> findByIdPessoaOrderByIdDesc(String idPessoa);

}
