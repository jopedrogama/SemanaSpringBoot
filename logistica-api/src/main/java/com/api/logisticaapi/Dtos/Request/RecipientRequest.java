package com.api.logisticaapi.Dtos.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientRequest {

    @NotBlank
    private String name;

    private String buildingNumber;

    private String addressComplement;

    @NotBlank
    @Size(min = 7, max = 8)
    private String cep;

}
