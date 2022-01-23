package com.api.logisticaapi.Dtos.ConsumingApiDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDTO {
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

}
