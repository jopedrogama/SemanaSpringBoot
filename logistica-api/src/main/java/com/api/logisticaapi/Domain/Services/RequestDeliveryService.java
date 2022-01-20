package com.api.logisticaapi.Domain.Services;

import java.time.LocalDateTime;

import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Domain.Models.DeliveryStatus;
import com.api.logisticaapi.Domain.Repositories.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private CustomerCatalogService customerCatalogService;

    @Transactional
    public Delivery requestDelivery(Delivery delivery) {

        var customer = customerCatalogService.findCustomer(delivery.getCustomer().getId());

        delivery.setDelivery_status(DeliveryStatus.PENDING);
        delivery.setOrder_date(LocalDateTime.now());
        delivery.setCustomer(customer);
        // CEP pesquisa e seta valores

        return deliveryRepository.save(delivery);
    }
}
