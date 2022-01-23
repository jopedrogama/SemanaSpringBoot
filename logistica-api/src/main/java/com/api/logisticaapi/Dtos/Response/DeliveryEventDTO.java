package com.api.logisticaapi.Dtos.Response;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryEventDTO {

    private Long id;
    private String description;
    private OffsetDateTime event_date_time;
}
