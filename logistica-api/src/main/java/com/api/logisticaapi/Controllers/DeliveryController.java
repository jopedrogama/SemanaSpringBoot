package com.api.logisticaapi.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Domain.Repositories.DeliveryRepository;
import com.api.logisticaapi.Domain.Services.RequestDeliveryService;
import com.api.logisticaapi.Dtos.DeliveryDTO;
import com.api.logisticaapi.Mapper.DeliveryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryDTO RequestDeliveryService(@Valid @RequestBody Delivery delivery) {
        Delivery requestedDelivery = requestDeliveryService.requestDelivery(delivery);
        return deliveryMapper.toModel(requestedDelivery);
    }

    @GetMapping
    public List<DeliveryDTO> listAll() {
        return deliveryMapper.toListModel(deliveryRepository.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryDTO> find(@PathVariable Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .map(deliveryObject -> {

                    // USING MODELMAPPER, IT DOESNT NEED TO DO IT ALL MANUALLY
                    // DeliveryDTO response = new DeliveryDTO(
                    // deliveryObject.getId(),
                    // deliveryObject.getCustomer().getName(),
                    // new RecipientDTO(
                    // deliveryObject.getRecipient().getName(),
                    // deliveryObject.getRecipient().getStreet(),
                    // deliveryObject.getRecipient().getBuildingNumber(),
                    // deliveryObject.getRecipient().getAddressComplement(),
                    // deliveryObject.getRecipient().getDistrict(),
                    // deliveryObject.getRecipient().getCep(),
                    // deliveryObject.getRecipient().getCity(),
                    // deliveryObject.getRecipient().getState()),
                    // deliveryObject.getDelivery_price(),
                    // deliveryObject.getDelivery_status(),
                    // deliveryObject.getOrder_date(),
                    // deliveryObject.getDelivery_date());
                    return ResponseEntity.ok(deliveryMapper.toModel(deliveryObject));
                }).orElse(ResponseEntity.notFound().build());
    }

}
