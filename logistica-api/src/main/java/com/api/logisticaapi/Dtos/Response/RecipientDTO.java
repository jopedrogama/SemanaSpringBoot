package com.api.logisticaapi.Dtos.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientDTO {

    private String name;
    private String street;
    private String buildingNumber;
    private String addressComplement;
    private String district;
    private String cep;
    private String city;
    private String state;

}
