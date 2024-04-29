package com.gft.boletoservice.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum Status {

    PAGO("PAGO"),
    PENDENTE("PENDENTE"),
    VENCIDO("VENCIDO");

    private final String name;

}
