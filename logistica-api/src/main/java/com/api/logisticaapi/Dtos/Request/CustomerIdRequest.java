package com.api.logisticaapi.Dtos.Request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerIdRequest {

    @NotNull
    private Long id;

}
