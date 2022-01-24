package com.api.logisticaapi.Domain.Services;

import java.time.OffsetDateTime;

import com.api.logisticaapi.Domain.Exceptions.DomainException;
import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Domain.Models.DeliveryStatus;
import com.api.logisticaapi.Domain.Repositories.DeliveryRepository;
import com.api.logisticaapi.Dtos.ConsumingApiDTO.AddressDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
        delivery.setOrder_date(OffsetDateTime.now());
        delivery.setCustomer(customer);

        try {
            RestTemplate template = new RestTemplate();
            String cep = delivery.getRecipient().getCep().replaceAll("-", "");
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            AddressDTO queryAddress = template.getForObject(url, AddressDTO.class);
            delivery.getRecipient().setStreet(queryAddress.getLogradouro());
            delivery.getRecipient().setDistrict(queryAddress.getBairro());
            delivery.getRecipient().setCity(queryAddress.getLocalidade());
            delivery.getRecipient().setState(queryAddress.getUf());

        } catch (Exception e) {
            throw new DomainException("This CEP is invalid! Try a different one!");
        }

        return deliveryRepository.save(delivery);
    }
}
