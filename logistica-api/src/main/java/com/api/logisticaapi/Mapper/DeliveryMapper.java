package com.api.logisticaapi.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.api.logisticaapi.Domain.Exceptions.DomainException;
import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Dtos.ConsumingApiDTO.AddressDTO;
import com.api.logisticaapi.Dtos.Request.DeliveryRequest;
import com.api.logisticaapi.Dtos.Response.DeliveryDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DeliveryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DeliveryDTO toModel(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryDTO.class);
    }

    public List<DeliveryDTO> toListModel(List<Delivery> delivery) {
        return delivery.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Delivery toEntity(DeliveryRequest deliveryInput) {
        Delivery delivery = modelMapper.map(deliveryInput, Delivery.class);
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
        return delivery;
    }
}
