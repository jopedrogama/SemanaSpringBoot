package com.api.logisticaapi.Domain.Services;

import com.api.logisticaapi.Domain.Exceptions.DomainException;
import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Domain.Repositories.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public Delivery search(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DomainException("Id not found for a Delivery registered in the data base"));

    }
}
