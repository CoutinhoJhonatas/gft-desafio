package com.gft.peopleservice.feign;

import com.gft.peopleservice.feign.config.FeignConfig;
import com.gft.peopleservice.feign.response.BoletoClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "boleto",
        url = "${boleto-service.url}",
        configuration = FeignConfig.class
)
public interface BoletoClient {

    @GetMapping(path = "/buscar/{cpf}")
    ResponseEntity<List<BoletoClientResponse>> buscarBoletosCpf(@PathVariable String cpf);

}
