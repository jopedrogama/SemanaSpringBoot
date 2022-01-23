package com.api.logisticaapi.Domain.Services;

import javax.transaction.Transactional;

import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Domain.Repositories.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private SearchDeliveryService searchDeliveryService;

    @Transactional
    public Delivery cancel(Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);
        delivery.cancelDelivery();
        return deliveryRepository.save(delivery);
    }
}
