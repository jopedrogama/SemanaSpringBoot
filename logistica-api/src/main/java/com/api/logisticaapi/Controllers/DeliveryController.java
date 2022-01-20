package com.api.logisticaapi.Controllers;

import javax.validation.Valid;

import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Domain.Services.RequestDeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private RequestDeliveryService requestDeliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery RequestDeliveryService(@Valid @RequestBody Delivery delivery) {

        return requestDeliveryService.requestDelivery(delivery);
    }
}
