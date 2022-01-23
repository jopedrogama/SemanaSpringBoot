package com.api.logisticaapi.Dtos.Request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryEventRequest {

    @NotBlank
    private String description;
}
