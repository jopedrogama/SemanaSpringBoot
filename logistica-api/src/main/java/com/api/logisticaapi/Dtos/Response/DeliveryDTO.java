package com.api.logisticaapi.Dtos.Response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.api.logisticaapi.Domain.Models.DeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryDTO {

    private Long id;
    private String customerName;
    private RecipientDTO recipient;
    private BigDecimal delivery_price;
    private DeliveryStatus deliveryStatus;
    private OffsetDateTime orderDate;
    private OffsetDateTime deliveryDate;

}
