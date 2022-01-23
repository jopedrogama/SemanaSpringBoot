package com.api.logisticaapi.Domain.Services;

import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Domain.Repositories.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinishDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private SearchDeliveryService searchDeliveryService;

    @Transactional
    public void finishdelivery(Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);
        delivery.finishDelivery();
        deliveryRepository.save(delivery);
    }
}
