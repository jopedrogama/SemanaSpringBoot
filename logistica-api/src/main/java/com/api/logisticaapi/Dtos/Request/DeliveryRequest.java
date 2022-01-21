package com.api.logisticaapi.Dtos.Request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRequest {

    @Valid
    @NotNull
    private CustomerIdRequest customer;

    @Valid
    @NotNull
    private RecipientRequest recipient;

    @NotNull
    private BigDecimal delivery_price;

}
