package com.api.logisticaapi.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.api.logisticaapi.Domain.Services.DeliveryEventService;
import com.api.logisticaapi.Domain.Services.SearchDeliveryService;
import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Domain.Models.DeliveryEvent;
import com.api.logisticaapi.Dtos.Request.DeliveryEventRequest;
import com.api.logisticaapi.Dtos.Response.DeliveryEventDTO;
import com.api.logisticaapi.Mapper.DeliveryEventMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery/{deliveryId}/events")
public class DeliveryEventController {

    @Autowired
    private DeliveryEventService deliveryEventService;

    @Autowired
    private DeliveryEventMapper deliveryEventMapper;

    @Autowired
    private SearchDeliveryService searchDeliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryEventDTO register(@PathVariable Long deliveryId,
            @Valid @RequestBody DeliveryEventRequest deliveryEventRequest) {

        DeliveryEvent deliveryEvent = deliveryEventService.register(deliveryId, deliveryEventRequest.getDescription());

        return deliveryEventMapper.toModel(deliveryEvent);
    }

    @GetMapping
    public List<DeliveryEventDTO> list(@PathVariable Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);
        return deliveryEventMapper.toList(delivery.getDeliveryEvents());
    }

}