package com.api.logisticaapi.Domain.Services;

import javax.transaction.Transactional;

import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Domain.Models.DeliveryEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryEventService {

    @Autowired
    private SearchDeliveryService searchDeliveryService;

    @Transactional
    public DeliveryEvent register(Long deliveryId, String description) {

        Delivery delivery = searchDeliveryService.search(deliveryId);

        return delivery.addDeliveryEvent(description);
    }
}
